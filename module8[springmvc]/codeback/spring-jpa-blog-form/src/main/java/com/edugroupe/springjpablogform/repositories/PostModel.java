package com.edugroupe.springjpablogform.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.edugroupe.springjpablogform.metier.Post;

public interface PostModel {

	List<Post> findAll();

	Post findByID(int id);

	Post save(Post p);

	boolean delete(int id);

	List<Post> searchByAuteur(String searchTerm);

}