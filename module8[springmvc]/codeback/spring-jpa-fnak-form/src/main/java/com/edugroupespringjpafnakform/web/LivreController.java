package com.edugroupespringjpafnakform.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edugroupespringjpafnakform.metier.Livre;
import com.edugroupespringjpafnakform.repositories.AuteurModel;
import com.edugroupespringjpafnakform.repositories.LivreModel;

@Controller
public class LivreController {

	@Autowired
	private LivreModel livreModel;
	
	@Autowired
	private AuteurModel auteurModel;
	
	@GetMapping("/")
	public ModelAndView liste() {
		ModelAndView mv = new ModelAndView("livre/liste");
		mv.addObject("livres", livreModel.findAll(true));
		System.out.println("sortie du controleur");
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("livre/edit");
		mv.addObject("livre", livreModel.findById(id));
		mv.addObject("auteurs", auteurModel.findAll());
		return mv;
	}

	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("livre/edit");
		mv.addObject("livre", new Livre(0, "titre", "", 0));
		mv.addObject("auteurs", auteurModel.findAll());
		return mv;
	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int id,
						@RequestParam("titre") String titre,
						@RequestParam("isbn") String isbn,
						@RequestParam("nbPages") int nbPages,
						@RequestParam("auteurId") int auteurId) {
		livreModel.save(new Livre(id, titre, isbn, nbPages), auteurId);
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("delete_id") int delete_id) {
		livreModel.delete(delete_id);
		return "redirect:/";
	}
	
	@GetMapping("/livre/{id}/editgenres")
	public ModelAndView editLivreGenres(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("livre/genres");
		mv.addObject("livre", livreModel.findById(id));
		mv.addObject("selected_genres",livreModel.findLivreGenre(id));
		mv.addObject("unselected_genres", livreModel.findLivreNotGenre(id));
		return mv;
	}
	
	@PostMapping("/livre/addGenre")
	public String addGenreToLivre(
					@RequestParam("livreId") int livreId,
					@RequestParam("genreId")int genreId) {
		livreModel.addGenreToLivre(livreId, genreId);
		return "redirect:/livre/" + livreId + "/editgenres";
	}

	@PostMapping("/livre/removeGenre")
	public String removeGenreToLivre(
					@RequestParam("livreId") int livreId,
					@RequestParam("genreId")int genreId) {
		livreModel.removeGenreToLivre(livreId, genreId);
		return "redirect:/livre/" + livreId + "/editgenres";
	}

}
