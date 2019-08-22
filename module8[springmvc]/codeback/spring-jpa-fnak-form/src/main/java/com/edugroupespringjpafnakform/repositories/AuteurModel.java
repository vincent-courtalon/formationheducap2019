package com.edugroupespringjpafnakform.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.edugroupespringjpafnakform.metier.Auteur;

public interface AuteurModel {

	List<Auteur> findAll();

}