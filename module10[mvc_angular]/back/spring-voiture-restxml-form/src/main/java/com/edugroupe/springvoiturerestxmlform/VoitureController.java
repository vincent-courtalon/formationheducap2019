package com.edugroupe.springvoiturerestxmlform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroupe.springvoiturerestxmlform.metier.Voiture;
import com.edugroupe.springvoiturerestxmlform.metier.VoitureListe;
import com.edugroupe.springvoiturerestxmlform.repositories.VoitureRepository;

@Controller
public class VoitureController {

	@Autowired
	private VoitureRepository voitureRepository;
	
	@GetMapping(value= "/voitures/{id:[0-9]+}",
				produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public Voiture findById(@PathVariable("id") int id) {
		return voitureRepository.findById(id).get();
	}
	
	@GetMapping(value= "/voitures",
			produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody	
	public VoitureListe findAll() {
		Iterable<Voiture> voitures = voitureRepository.findAll();

		// copie dans la liste
		List<Voiture> voitures2 = new ArrayList<>();
		voitures.forEach(v -> voitures2.add(v));
		
		return new VoitureListe(voitures2);
	}
}
