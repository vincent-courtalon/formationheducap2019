package com.edugroupe.librairiefnoc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edugroupe.librairiefnoc.metier.Livre;



public class LivreDAO {
	public static final String SELECT_ALL_SQL = "SELECT `id`,`titre`,`isbn`,`nbPages`,`auteur` FROM `livres`";
	public static final String SELECT_ONE_SQL = "SELECT `id`,`titre`,`isbn`,`nbPages`,`auteur` FROM `livres` WHERE `id`=?";
	public static final String INSERT_ONE_SQL = "INSERT INTO `livres` (`titre`,`isbn`,`nbPages`,`auteur`) VALUES(?,?,?,?)";
	public static final String UPDATE_ONE_SQL = "UPDATE `livres` set `titre`=?,`isbn`=?,`nbPages`=?,`auteur`=? WHERE `id`=?";
	public static final String SEARCH_BY_TITRE_SQL =
			"SELECT `id`,`titre`,`isbn`,`nbPages`,`auteur` FROM `livres` WHERE `titre` LIKE ?";
	public static final String SEARCH_BY_NBPAGES_SQL =
			"SELECT `id`,`titre`,`isbn`,`nbPages`,`auteur` FROM `livres` WHERE `nbPages` > ?";
	
	 
	private Connection base;
	private PreparedStatement findAllStatement;
	private PreparedStatement findOneStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement updateOneStatement;
	private PreparedStatement searchByTitreStatement;
	private PreparedStatement searchByNbPagesStatement;
	
	public LivreDAO(Connection base) {
		this.base = base;
		try {
			findAllStatement = base.prepareStatement(SELECT_ALL_SQL);
			findOneStatement = base.prepareStatement(SELECT_ONE_SQL);
			insertOneStatement = base.prepareStatement(INSERT_ONE_SQL);
			updateOneStatement = base.prepareStatement(UPDATE_ONE_SQL);
			searchByTitreStatement = base.prepareStatement(SEARCH_BY_TITRE_SQL);
			searchByNbPagesStatement = base.prepareStatement(SEARCH_BY_NBPAGES_SQL);
		}catch (SQLException e) {e.printStackTrace();}
	}
	
	private Livre fetchFromResultSet(ResultSet rs) throws SQLException {
		return new Livre(rs.getInt("id"),
						rs.getString("titre"),
						rs.getString("isbn"),
						rs.getInt("nbPages"),
						rs.getString("auteur"));
	}
	
	
	public List<Livre> findByNbPages(int nbPages) {
		ArrayList<Livre> livres = new ArrayList<>();
		try {
			searchByNbPagesStatement.clearParameters();
			searchByNbPagesStatement.setInt(1,nbPages);
			ResultSet rs = searchByNbPagesStatement.executeQuery();
			while (rs.next()) {
				livres.add(fetchFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return livres;
	}
	
	public List<Livre> findByTitre(String searchterm) {
		ArrayList<Livre> livres = new ArrayList<>();
		try {
			searchByTitreStatement.clearParameters();
			searchByTitreStatement.setString(1, "%" + searchterm + "%");
			ResultSet rs = searchByTitreStatement.executeQuery();
			while (rs.next()) {
				livres.add(fetchFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return livres;
	}
	
	public List<Livre> findAll() {
		ArrayList<Livre> livres = new ArrayList<>();
		try {
			ResultSet rs = findAllStatement.executeQuery();
			while (rs.next()) {
				livres.add(fetchFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return livres;
	}

	public synchronized int save(Livre l) {
		if (l.getId() == 0) {
			// insertion
			try {
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, l.getTitre());
				insertOneStatement.setString(2, l.getIsbn());
				insertOneStatement.setInt(3, l.getNbPages());
				insertOneStatement.setString(4, l.getAuteur());
				return insertOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		else {
			// update
			try {
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, l.getTitre());
				updateOneStatement.setString(2, l.getIsbn());
				updateOneStatement.setInt(3, l.getNbPages());
				updateOneStatement.setString(4, l.getAuteur());
				updateOneStatement.setInt(5, l.getId());
				return updateOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}
	
}
