package com.edugroupe.springmoviesrestform.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroupe.springmoviesrestform.metier.Film;
import com.edugroupe.springmoviesrestform.repositories.FilmRepository;

@Controller
public class FilmController {

	@Autowired
	private FilmRepository filmRepository;
	
	@GetMapping(value="/nopagefilms",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"})
	public Iterable<Film> liste() {
		return filmRepository.findAll();
	}

	
	// pour la pagination, un PageableDefault extraira les parametres
	//   page=...  -> no page
	//   size=...  -> taille des pages
	
	// quand une page est renvoyée, elle contient, en plus des infos de pagination
	// un champ "content", qui est le tableau des données actuelles de la page (ici les films)
	// il execute réelement des requetes SQL paginées
	@GetMapping(value="/films",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"})
	public Page<Film> pliste(@PageableDefault(page = 0, size = 3) Pageable page) {
		//PageRequest pr = new PageRequest(page, size)
		return filmRepository.findAll(page);
	}

	@GetMapping(value = "/films/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"})
	public ResponseEntity<Film> findById(@PathVariable("id") int id) {
		
		return filmRepository
						.findById(id)
						.map(f -> new ResponseEntity<>(f, HttpStatus.OK))
						.orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
		
		// le type Optional a été introduit entre autre pour les stream
		// comme son nom l'indique, le contenu peut être présent ou non
		// cela évite d'avoir directement a gérer les NULL
		// pour vérifier s'il est présent --> isPresent
		// pour récupérer --> get
		/*Optional<Film> film = filmRepository.findById(id);
		if (film.isPresent())
			return film.get();
		else
			return null;*/
	}
	
	@PostMapping(value="/films",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"})
	public ResponseEntity<Film> insertFilm(@RequestBody Film film) {
		if (film.getId() != 0)
			return new ResponseEntity<Film>(HttpStatus.BAD_REQUEST);
		// @RequestBody -> le corp de requette va contenir, en json ici, notre objet
		film = filmRepository.save(film);
		return new ResponseEntity<Film>(film, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/films",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"})
	public ResponseEntity<Film> updateFilm(@RequestBody Film film) {
		Optional<Film> f = filmRepository.findById(film.getId());
		if (!f.isPresent())	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Film f2 = f.get();
		f2.setDateSortie(film.getDateSortie());
		f2.setDuree(film.getDuree());
		f2.setNom(film.getNom());
		f2 = filmRepository.save(f2);
		return new ResponseEntity<Film>(f2, HttpStatus.OK);
	}

	@DeleteMapping(value="/films/{id:[0-9]+}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"})
	public ResponseEntity<Map<String, Object>> deleteFilm(@PathVariable("id") int id) {
		Optional<Film> f =filmRepository.findById(id);
		if (!f.isPresent()) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NOT_FOUND);
		}
		else {
			filmRepository.delete(f.get());
			return new ResponseEntity<Map<String,Object>>(
						Collections.singletonMap("ligne effacee", 1),
						HttpStatus.ACCEPTED);
		}
	}
	
	
	@GetMapping(value = "/films/duree/{dureeMin}",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Film> listeDureeMin(@PathVariable("dureeMin") int dureeMin) {
		return filmRepository.findByDureeGreaterThan(dureeMin);
	}
	
}
