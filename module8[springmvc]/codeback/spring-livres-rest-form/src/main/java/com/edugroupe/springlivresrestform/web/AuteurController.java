package com.edugroupe.springlivresrestform.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroupe.springlivresrestform.metier.Auteur;
import com.edugroupe.springlivresrestform.repositories.AuteurRepository;

@Controller
public class AuteurController {
	@Autowired
	private AuteurRepository auteurRepository;
	
	@GetMapping(value="/auteurs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public Iterable<Auteur> findAll() {
		return auteurRepository.findAll();
	}
}
