package com.edugroupe.springboutiqueimageform.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroupe.springboutiqueimageform.metier.Categorie;
import com.edugroupe.springboutiqueimageform.repositories.CategorieRepository;

@Controller
@RequestMapping("/categories")
@CrossOrigin("http://localhost:4200")
public class CategorieController {
	@Autowired
	private CategorieRepository categorieRepository;
	
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Iterable<Categorie> findAll() {
		return categorieRepository.findAll();
	}
	
}
