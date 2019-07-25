package webjdbcdaoform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webjdbcdaoform.metier.Film;

public class FilmDAO {
	public static final String SELECT_ALL_SQL = "SELECT `id`,`titre`,`longueur`,`annee`,`genre` FROM `films`";
	public static final String SELECT_ONE_SQL = "SELECT `id`,`titre`,`longueur`,`annee`,`genre` FROM `films` WHERE `id`=?";
	public static final String INSERT_ONE_SQL = "INSERT INTO `films` (`titre`,`longueur`,`annee`,`genre`) VALUES(?,?,?,?)";
	public static final String UPDATE_ONE_SQL = "UPDATE `films` set `titre`=?,`longueur`=?,`annee`=?,`genre`=? WHERE `id`=?";
	
	private Connection base;
	
	private PreparedStatement findAllStatement;
	private PreparedStatement findOneStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement updateOneStatement;

	public FilmDAO(Connection base) {
		this.base = base;
		
		try {
			findAllStatement = base.prepareStatement(SELECT_ALL_SQL);
			findOneStatement = base.prepareStatement(SELECT_ONE_SQL);
			insertOneStatement = base.prepareStatement(INSERT_ONE_SQL);
			updateOneStatement = base.prepareStatement(UPDATE_ONE_SQL);
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	
	private Film fetchFromResultSet(ResultSet rs) throws SQLException {
		return new Film(rs.getInt("id"),
						rs.getString("titre"),
						rs.getInt("longueur"),
						rs.getInt("annee"),
						rs.getString("genre"));
	}
	
	public List<Film> findAll() {
		ArrayList<Film> films = new ArrayList<>();
		try {
			ResultSet rs = findAllStatement.executeQuery();
			while (rs.next()) {
				films.add(fetchFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return films;
	}
	
	// eviter d'écraser le parametre d'une autre requete en cours d'execution
	public synchronized Film findOne(int id) {
		Film f = null;
		try {
			findOneStatement.clearParameters();
			findOneStatement.setInt(1, id);
			ResultSet rs = findOneStatement.executeQuery();
			if (rs.next()) {
				f = fetchFromResultSet(rs);
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return f;
	}
	
	public synchronized int save(Film f) {
		if (f.getId() == 0) {
			// insertion
			try {
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, f.getTitre());
				insertOneStatement.setInt(2, f.getLongueur());
				insertOneStatement.setInt(3, f.getAnnee());
				insertOneStatement.setString(4, f.getGenre());
				return insertOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		else {
			// update
			try {
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, f.getTitre());
				updateOneStatement.setInt(2, f.getLongueur());
				updateOneStatement.setInt(3, f.getAnnee());
				updateOneStatement.setString(4, f.getGenre());
				updateOneStatement.setInt(5, f.getId());
				return updateOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}
	

}
