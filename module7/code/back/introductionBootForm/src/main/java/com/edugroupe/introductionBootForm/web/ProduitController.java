package com.edugroupe.introductionBootForm.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edugroupe.introductionBootForm.dao.ProduitDAO;
import com.edugroupe.introductionBootForm.metier.Produit;

@Controller
public class ProduitController {

	@Autowired
	private ProduitDAO produitDAO;
	
	@GetMapping("/hello")
	@ResponseBody
	public String bienvenue() {
		return 	"<h1>bonjour "+ LocalDateTime.now() +"</h1>";
	}
	
	@GetMapping("/greetings")
	@ResponseBody
	public String bienvenueAvance(@RequestParam("nom") String nom) 
	{
		return 	"<h1>bonjour "+ nom +"</h1>";
	}
	
	@GetMapping("/bonjour")
	public String bonjour(Model model) 
	{
		Produit p = produitDAO.findById(1);
		model.addAttribute("message", "voulez vous du " + p.getNom());
		return 	"home";
	}
	
	/*
	 * 
	 *  gestion des produits
	 * 
	 */
	
	@GetMapping("/liste")
	public ModelAndView listeProduit() {
		// ModelAndView est une classe de spring combinant la séléction d'une vue (jsp par exemple)
		// avec le stockage des données destinée à cette vue
		ModelAndView mv = new ModelAndView("liste");
		mv.addObject("produits", produitDAO.findAll());
		return mv;
	}
	// une PathVariable est mise en correspondance en fonction de son nom
	// elle apparait dans l'url en {}, les accolades ne sont pas présente dans la vrai url
	// GET /edit/2   --> int pid = 2
	@GetMapping("/edit/{produit_id}")
	public ModelAndView editProduit(@PathVariable("produit_id") int pid) {
		ModelAndView mv = new ModelAndView("edit");
		Produit p = produitDAO.findById(pid);
		if (p == null)
			mv.setViewName("redirect:/liste");
		else
			mv.addObject("produit", p);
		return mv;
	}
	
	@GetMapping("/create")
	public ModelAndView createProduit() {
		ModelAndView mv = new ModelAndView("edit");
		mv.addObject("produit", new Produit(0, "nom", 0.0, 0.0));
		return mv;
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("id") int id,
						@RequestParam("nom") String nom,
						@RequestParam("prix") double prix,
						@RequestParam("poids") double poids) {
		Produit p = new Produit(id, nom, prix, poids);
		produitDAO.save(p);
		return "redirect:/liste"; // c'est une redirection vers la liste
	}
	
}
