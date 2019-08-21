package com.edugroupe.springVilleForm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edugroupe.springVilleForm.dao.VilleDAO;
import com.edugroupe.springVilleForm.metier.Ville;

@Controller
public class VilleController {

	@Autowired
	private VilleDAO villeDAO;
	
	@Value("${spring.application.name}")
	private String nomApplication;
	
	@GetMapping("/")
	public ModelAndView liste() {
		ModelAndView mv = new ModelAndView("liste");
		mv.addObject("villes", villeDAO.findAll());
		mv.addObject("nomApplication", nomApplication);
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("edit");
		mv.addObject("ville", villeDAO.findById(id));
		mv.addObject("nomApplication", nomApplication);
		return mv;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("edit");
		mv.addObject("ville", new Ville(0, "nom ville", 0, 0, "inconnu"));
		mv.addObject("nomApplication", nomApplication);
		return mv;
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("id") int id,
						@RequestParam("nom") String nom,
						@RequestParam("population") int population,
						@RequestParam("surface") double surface,
						@RequestParam("pays") String pays) {
		villeDAO.save(new Ville(id, nom, population, surface, pays));
		return "redirect:/";
	}
	
	@GetMapping("/search_by_nom")
	public ModelAndView searchByNom(@RequestParam("searchTerm") String searchTerm) {
		ModelAndView mv = new ModelAndView("liste");
		mv.addObject("villes", villeDAO.searchByNom(searchTerm));
		mv.addObject("nomApplication", nomApplication);
		return mv;
	}
	
	
}
