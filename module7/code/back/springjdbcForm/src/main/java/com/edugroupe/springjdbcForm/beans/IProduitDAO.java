package com.edugroupe.springjdbcForm.beans;

import java.util.List;

import com.edugroupe.springjdbcForm.metier.Produit;

public interface IProduitDAO {

	List<Produit> findAll();

	Produit findById(int id);

	int save(Produit p);

}