package macromania.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import macromania.metier.Jeux;

public class JeuxDAO {
	public final static String SELECT_ALL_SQL = "SELECT `id`,`titre`,`description`,`plateforme`,`annee` FROM `jeux`";
	public final static String SELECT_ONE_SQL = "SELECT `id`,`titre`,`description`,`plateforme`,`annee` FROM `jeux` WHERE `id`=?";
	public final static String INSERT_ONE_SQL = "INSERT INTO `jeux` (`titre`,`description`,`plateforme`,`annee`) VALUES(?,?,?,?)";
	public final static String UPDATE_ONE_SQL = "UPDATE `jeux` SET `titre`=?,`description`=?,`plateforme`=?,`annee`=? WHERE `id`=?";
	public final static String DELETE_ONE_SQL = "DELETE FROM `jeux` WHERE `id`=?";
	public final static String SELECT_BY_PLATEFORME_SQL = "SELECT `id`,`titre`,`description`,`plateforme`,`annee` FROM `jeux` WHERE `plateforme`=?";
	
	private Connection base;
	private PreparedStatement findAllStatement;
	private PreparedStatement findOneStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement updateOneStatement;
	private PreparedStatement deleteOneStatement;
	private PreparedStatement findByplateformeStatement;
	

	public JeuxDAO(Connection base) {
		this.base = base;
		try {
			findAllStatement = base.prepareStatement(SELECT_ALL_SQL);
			findOneStatement = base.prepareStatement(SELECT_ONE_SQL);
			insertOneStatement = base.prepareStatement(INSERT_ONE_SQL);
			updateOneStatement = base.prepareStatement(UPDATE_ONE_SQL);
			deleteOneStatement = base.prepareStatement(DELETE_ONE_SQL);
			findByplateformeStatement = base.prepareStatement(SELECT_BY_PLATEFORME_SQL);
		} catch (SQLException e) {e.printStackTrace();}
	}

	// transforme une ligne du resulset provenant de l'execution d'une requette select
	// en un objet jeux, en lisant les colonnes appropriée
	private Jeux fetchOneFormResultSet(ResultSet rs) throws SQLException {
		return new Jeux(rs.getInt("id"),
						rs.getString("titre"),
						rs.getString("description"),
						rs.getString("plateforme"),
						rs.getInt("annee"));
	}
	

	public List<Jeux> findAllByplateforme(String plateforme) {
		ArrayList<Jeux> jeux = new ArrayList<>();
		try {
			findByplateformeStatement.clearParameters();
			findByplateformeStatement.setString(1, plateforme);
			ResultSet rs = findByplateformeStatement.executeQuery();
			while (rs.next()) {
				jeux.add(fetchOneFormResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return jeux;
	}
	
	
	public List<Jeux> findAll() {
		ArrayList<Jeux> jeux = new ArrayList<>();
		try {
			ResultSet rs = findAllStatement.executeQuery();
			while (rs.next()) {
				jeux.add(fetchOneFormResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return jeux;
	}
	
	public synchronized Jeux findOne(int id) {
		Jeux j = null;
		try {
			findOneStatement.clearParameters();
			findOneStatement.setInt(1, id);
			ResultSet rs = findOneStatement.executeQuery();
			if (rs.next()) {
				j = fetchOneFormResultSet(rs);
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return j;
	}
	
	public synchronized int save(Jeux jeux) {
		if (jeux.getId() == 0) {
			try {
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, jeux.getTitre());
				insertOneStatement.setString(2, jeux.getDescription());
				insertOneStatement.setString(3, jeux.getPlateforme());
				insertOneStatement.setInt(4, jeux.getAnnee());
				return insertOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		else {
			try {
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, jeux.getTitre());
				updateOneStatement.setString(2, jeux.getDescription());
				updateOneStatement.setString(3, jeux.getPlateforme());
				updateOneStatement.setInt(4, jeux.getAnnee());
				updateOneStatement.setInt(5, jeux.getId());
				return updateOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}
	
	public synchronized int deleteOne(int id) {
		try {
			deleteOneStatement.clearParameters();
			deleteOneStatement.setInt(1, id);
			return deleteOneStatement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return 0;
	}
	
	
}
