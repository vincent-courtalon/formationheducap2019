package com.edugroupe.firstjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edugroupe.firstjdbc.metier.Acteur;

public class ActeurDAO {
	public final static String SELECT_ALL = "select id,nom,prenom,email from contact";
	public final static String SELECT_ONE = "select id,nom,prenom,email from contact where id=?";
	public final static String INSERT_ONE = "insert into contact (nom,prenom,email) values(?,?,?)";
	public final static String UPDATE_ONE = "update contact set nom=?,prenom=?,email=? where id=?";
	public final static String DELETE_ONE = "delete from contact where id=?";
	
	// notre datasource, la connection à la base
	private Connection base;
	// requette paramétrée
	private PreparedStatement findAllStatement;
	private PreparedStatement findOneStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement updateOneStatement;
	private PreparedStatement deleteOneStatement;
	
	
	public ActeurDAO(Connection base) {
		this.base = base;
		
		try {
			this.findAllStatement = base.prepareStatement(SELECT_ALL);
			this.findOneStatement = base.prepareStatement(SELECT_ONE);
			this.insertOneStatement = base.prepareStatement(INSERT_ONE);
			this.updateOneStatement = base.prepareStatement(UPDATE_ONE);
			this.deleteOneStatement = base.prepareStatement(DELETE_ONE);
			
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	
	private Acteur fetchFromResultSet(ResultSet rs) throws SQLException {
		return new Acteur(	rs.getInt("id"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getString("email"));
	}
	
	public List<Acteur> findAll() {
		ArrayList<Acteur> acteurs = new ArrayList<>();
		try {
			ResultSet rs = findAllStatement.executeQuery();
			while(rs.next()) {
				acteurs.add(fetchFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return acteurs;
	}

	public Acteur findOne(int id) {
		Acteur a = null;
		try {
			findOneStatement.clearParameters();
			findOneStatement.setInt(1, id);
			ResultSet rs = findOneStatement.executeQuery();
			if (rs.next()) {
				a = fetchFromResultSet(rs);
			}
		} catch (SQLException e) {e.printStackTrace();}
		return a;
	}
	
	public int save(Acteur a) {
		if (a.getId() == 0) {
			// insertion
			try {
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, a.getNom());
				insertOneStatement.setString(2, a.getPrenom());
				insertOneStatement.setString(3, a.getEmail());
				return insertOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		else {
			// update
			try {
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, a.getNom());
				updateOneStatement.setString(2, a.getPrenom());
				updateOneStatement.setString(3, a.getEmail());
				updateOneStatement.setInt(4, a.getId());
				return updateOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}
	
	public int delete(int id) {
		try {
			deleteOneStatement.clearParameters();
			deleteOneStatement.setInt(1, id);
			return deleteOneStatement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return 0;
	}

}
