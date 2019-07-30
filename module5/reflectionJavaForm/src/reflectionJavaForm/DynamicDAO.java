package reflectionJavaForm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DynamicDAO <T> {
	private PreparedStatement selectAllStatement;
	
	private Connection base;
	private Class clazz;

	private List<DescripteurChamp> champs;
	
	private static class DescripteurChamp {
		public String nomChamp;
		public Method getter;
		public Method setter;
		public Class  typeChamp;
		
		public DescripteurChamp(String nomChamp, Method getter, Method setter, Class typeChamp) {
			super();
			this.nomChamp = nomChamp;
			this.getter = getter;
			this.setter = setter;
			this.typeChamp = typeChamp;
		}
		
		
		
	}
	
	// la connection, et le type de l'objet
	public DynamicDAO(Connection base, Class clazz) throws NoSuchMethodException, SecurityException {
		this.base = base;
		this.clazz = clazz;
		
		initialiseChamps(clazz);
		initialiseRequette(clazz);
	}
	
	private void initialiseRequette(Class clazz) {
		QueryBuilder qb = new QueryBuilder(clazz.getSimpleName().toLowerCase(), base);
		for (DescripteurChamp d : champs) {
			qb.addField(d.nomChamp);
		}
		selectAllStatement=  qb.select().build();
	}
	

	private void initialiseChamps(Class clazz) throws NoSuchMethodException {
		champs = new ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			 // si ce n'est pas publique, ca ne nous interesse pas
			if (!Modifier.isPublic(m.getModifiers()))
				continue;
			// si static, pas intéréssant
			if (Modifier.isStatic(m.getModifiers()))
				continue;
			// on ne veut que les methode qui on un parametre
			Class[] params = m.getParameterTypes();
			if (params.length != 1)
				continue;
			if (m.getName().startsWith("set")) {
				// c'est bon, c'est un setter valide
				// setTitre  -> titre
				String nomChamp = m.getName().substring(3);
				nomChamp = nomChamp.substring(0, 1).toLowerCase() + nomChamp.substring(1);
				Method setter = m;
				Method getter = clazz.getDeclaredMethod(
						m.getName().replaceFirst("set", "get"));
				Class type = m.getParameterTypes()[0];
				
				// un des champs de notre bean (objet metier)
				champs.add(new DescripteurChamp(nomChamp, getter, setter, type));
			}
		}
	}
	

	public List<T> findAll() {
		ArrayList<T> data = new ArrayList<>();
		try {
			ResultSet rs=selectAllStatement.executeQuery();
			while (rs.next()) {
				data.add(fetchRowFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return data;
	}
	// renvoyer un objet metier a partir d'une ligne du resultset
	private T fetchRowFromResultSet(ResultSet rs) {
		try {
			Object instance = clazz.newInstance();
			for (DescripteurChamp d : champs) {
				if (d.typeChamp.equals(String.class)) {
					// exempler => setTitre(rs.getString("titre"))
					d.setter.invoke(instance, rs.getString(d.nomChamp));
				}
				if (d.typeChamp.equals(int.class)) {
					// exempler => setId(rs.getInt("id"))
					d.setter.invoke(instance, rs.getInt(d.nomChamp));
				}
				if (d.typeChamp.equals(double.class)) {
					// exempler => setPrix(rs.getDouble("prix"))
					d.setter.invoke(instance, rs.getDouble(d.nomChamp));
				}
			}
			return (T)instance;
		} 
		catch (InstantiationException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();} 
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	
}
