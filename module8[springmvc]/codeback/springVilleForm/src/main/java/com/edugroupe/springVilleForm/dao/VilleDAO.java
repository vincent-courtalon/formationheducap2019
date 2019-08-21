package com.edugroupe.springVilleForm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.edugroupe.springVilleForm.metier.Ville;

@Service
public class VilleDAO {
	public static final String FIND_ALL_SQL = "SELECT `id`,`nom`,`population`,`surface`,`pays` FROM `villes`";
	public static final String FIND_BY_ID_SQL = "SELECT `id`,`nom`,`population`,`surface`,`pays` FROM `villes` WHERE `id`=?";
	public static final String INSERT_ONE_SQL = "INSERT INTO `villes` (`nom`,`population`,`surface`,`pays`) VALUES(?,?,?,?)";
	public static final String UPDATE_ONE_SQL = "UPDATE `villes` SET `nom`=?,`population`=?,`surface`=?,`pays`=?  WHERE `id`=?";
	public static final String SEARCH_BY_NOM_SQL = "SELECT `id`,`nom`,`population`,`surface`,`pays` FROM `villes` WHERE `nom` like ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class VilleMapper implements RowMapper<Ville> {
		@Override
		public Ville mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Ville(rs.getInt("id"),
							 rs.getString("nom"),
							 rs.getInt("population"),
							 rs.getDouble("surface"),
							 rs.getString("pays"));
		}
	}
	
	public List<Ville> findAll() {
		return jdbcTemplate.query(FIND_ALL_SQL, new VilleMapper());
	}
	
	public Ville findById(int id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID_SQL, new Object[] {id}, new VilleMapper());
	}
	
	public int save(Ville v) {
		if (v.getId() == 0)
			return jdbcTemplate.update(INSERT_ONE_SQL, v.getNom(), v.getPopulation(), v.getSurface(), v.getPays());
		else
			return jdbcTemplate.update(UPDATE_ONE_SQL, v.getNom(), v.getPopulation(), v.getSurface(), v.getPays(), v.getId());
	}
	
	public List<Ville> searchByNom(String searchTerm) {
		Object[] params = {"%" + searchTerm + "%"};
		return jdbcTemplate.query(SEARCH_BY_NOM_SQL, params, new VilleMapper());
	}
	
}
