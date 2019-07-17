package com.edugroupe.exercicejavaance1form.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edugroupe.exercicejavaance1form.metier.Produit;

public class ProduitDAO {
	public final static String SELECT_ALL = "select id,nom,prix,poids from produit";
	public final static String SELECT_ONE = "select id,nom,prix,poids from produit where id=?";
	public final static String INSERT_ONE = "insert into produit (nom,prix,poids) values(?,?,?)";
	public final static String UPDATE_ONE = "update produit set nom=?,prix=?,poids=? where id=?";
	public final static String DELETE_ONE = "delete from produit where id=?";
	public final static String SEARCH_BY_PRICE = "SELECT id,nom,prix,poids FROM produit WHERE prix>? AND prix<?"; 
	
	private Connection base;

	PreparedStatement findAllStatement;
	PreparedStatement findOneStatement;
	PreparedStatement insertOneStatement;
	PreparedStatement updateOneStatement;
	PreparedStatement deleteoneStatement;
	PreparedStatement searchByPriceStatement;
	

	public ProduitDAO(Connection base) {
		this.base = base;
		
		try {
			findAllStatement = base.prepareStatement(SELECT_ALL);
			findOneStatement = base.prepareStatement(SELECT_ONE);
			insertOneStatement = base.prepareStatement(INSERT_ONE, Statement.RETURN_GENERATED_KEYS);
			updateOneStatement = base.prepareStatement(UPDATE_ONE);
			deleteoneStatement = base.prepareStatement(DELETE_ONE);
			searchByPriceStatement = base.prepareStatement(SEARCH_BY_PRICE);
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	
	public List<Produit> findAll() {
		ArrayList<Produit> produits = new ArrayList<>();
		try {
			ResultSet rs =  findAllStatement.executeQuery();
			while (rs.next()) {
				produits.add(fetchProduitFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return produits;
	}
	
	public Produit findOne(int id) {
		Produit produit = null;
		try {
			findOneStatement.clearParameters();
			findOneStatement.setInt(1, id);
			ResultSet rs =  findOneStatement.executeQuery();
			if (rs.next()) {
				produit = fetchProduitFromResultSet(rs);
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return produit;
	}

	public Produit save(Produit p) {
		if (p.getId() == 0) {
			// insertion
			try {
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, p.getNom());
				insertOneStatement.setDouble(2, p.getPrix());
				insertOneStatement.setDouble(3, p.getPoids());
				insertOneStatement.executeUpdate();
				// récupération de la clef primaire générée à l'insertion
				// par mysql
				ResultSet keys =  insertOneStatement.getGeneratedKeys();
				if (keys.next()) {
					p.setId(keys.getInt(1));
				}
			} catch (SQLException e) {e.printStackTrace();}
		}
		else {
			try {
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, p.getNom());
				updateOneStatement.setDouble(2, p.getPrix());
				updateOneStatement.setDouble(3, p.getPoids());
				updateOneStatement.setInt(4, p.getId());
				updateOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return p;
	}
	
	public int delete(int id) {
		try {
			deleteoneStatement.clearParameters();
			deleteoneStatement.setInt(1, id);
			return deleteoneStatement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return 0;
	}
	
	public List<Produit> searchByPrice(double prixMin, double prixMax) {
		ArrayList<Produit> produits = new ArrayList<>();
		try {
			searchByPriceStatement.clearParameters();
			searchByPriceStatement.setDouble(1, prixMin);
			searchByPriceStatement.setDouble(2, prixMax);
			ResultSet rs =  searchByPriceStatement.executeQuery();
			while (rs.next()) {
				produits.add(fetchProduitFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return produits;
	}
	
	
	private Produit fetchProduitFromResultSet(ResultSet rs) throws SQLException {
		return new Produit(rs.getInt("id"),
				rs.getString("nom"),
				rs.getDouble("prix"),
				rs.getDouble("poids"));
	}
	

}
