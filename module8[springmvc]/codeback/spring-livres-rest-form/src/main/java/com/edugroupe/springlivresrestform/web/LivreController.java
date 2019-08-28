package com.edugroupe.springlivresrestform.web;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroupe.springlivresrestform.metier.Auteur;
import com.edugroupe.springlivresrestform.metier.Livre;
import com.edugroupe.springlivresrestform.repositories.AuteurRepository;
import com.edugroupe.springlivresrestform.repositories.LivreRepository;

@Controller
public class LivreController {

	@Autowired
	private LivreRepository livreRepository;
	
	@Autowired
	private AuteurRepository auteurRepository;
	
	
	@GetMapping(value="/livres", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public Page<Livre> findAll(
				@RequestParam("searchTerm") Optional<String> searchTerm,
				@PageableDefault(page = 0, size = 10) Pageable page) {
		if (searchTerm.isPresent())
			return livreRepository.findByTitreContaining(searchTerm.get(), page);
		else
			return livreRepository.findAll(page);
	}
	
	@GetMapping(value="/livres/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Livre> findById(@PathVariable("id") int id) {
		return livreRepository.findById(id)
							  .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping(value="/livres",
				 produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Livre> createLivre(@RequestBody Livre l,
											 @RequestParam("auteurId") int auteurId){
		Auteur a = auteurRepository.findById(auteurId).orElse(null);
		if ( a == null) return new ResponseEntity<Livre>(HttpStatus.NOT_ACCEPTABLE);
		l.setAuteur(a);
		l = livreRepository.save(l);
		return new ResponseEntity<Livre>(l, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/livres",
			 produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Livre> updateLivre(@RequestBody Livre l,
											 @RequestParam("auteurId") int auteurId){
		Auteur a = auteurRepository.findById(auteurId).orElse(null);
		if ( a == null) return new ResponseEntity<Livre>(HttpStatus.NOT_ACCEPTABLE);
		l.setAuteur(a);
		l = livreRepository.save(l);
		return new ResponseEntity<Livre>(l, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value="livres/{id:[0-9]+}",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Map<String, Object>> deleteLivre(@PathVariable("id") int id) {
		if (livreRepository.existsById(id)) {
			livreRepository.deleteById(id);
			return new ResponseEntity<>(
					Collections.singletonMap("deletedId", id),
					HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
