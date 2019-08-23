package com.edugroupe.springmoviesrestform.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroupe.springmoviesrestform.metier.Film;
import com.edugroupe.springmoviesrestform.repositories.FilmRepository;

@Controller
public class FilmController {

	@Autowired
	private FilmRepository filmRepository;
	
	@GetMapping(value="/films",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Iterable<Film> liste() {
		return filmRepository.findAll();
	}
	
	@GetMapping(value = "/films/duree/{dureeMin}",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Film> listeDureeMin(@PathVariable("dureeMin") int dureeMin) {
		return filmRepository.findByDureeGreaterThan(dureeMin);
	}
	
}
