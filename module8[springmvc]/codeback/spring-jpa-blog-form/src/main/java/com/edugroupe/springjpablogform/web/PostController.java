package com.edugroupe.springjpablogform.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edugroupe.springjpablogform.metier.Post;
import com.edugroupe.springjpablogform.repositories.PostModel;

@Controller
public class PostController {

	@Autowired
	private PostModel postModel;
	
	
	@GetMapping("/")
	public ModelAndView liste() {
		ModelAndView mv = new ModelAndView("liste");
		mv.addObject("posts", postModel.findAll());
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("edit");
		mv.addObject("post", postModel.findByID(id));
		return mv;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("edit");
		mv.addObject("post", new Post(0, "", "", null, ""));
		return mv;
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("id") int id,
						@RequestParam("titre") String titre,
						@RequestParam("corps") String corps,
						@RequestParam("auteur") String auteur) {
		postModel.save(new Post(id, titre, corps, null, auteur));
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("delete_id") int delete_id) {
		postModel.delete(delete_id);
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public ModelAndView searchByAuteur(@RequestParam("searchTerm") String searchTerm) {
		ModelAndView mv = new ModelAndView("liste");
		mv.addObject("posts", postModel.searchByAuteur(searchTerm));
		return mv;
	}
	
	
}
