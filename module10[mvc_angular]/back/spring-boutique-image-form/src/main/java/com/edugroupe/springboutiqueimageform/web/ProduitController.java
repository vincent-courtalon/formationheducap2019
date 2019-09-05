package com.edugroupe.springboutiqueimageform.web;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import com.edugroupe.springboutiqueimageform.metier.Categorie;
import com.edugroupe.springboutiqueimageform.metier.Image;
import com.edugroupe.springboutiqueimageform.metier.Produit;
import com.edugroupe.springboutiqueimageform.repositories.CategorieRepository;
import com.edugroupe.springboutiqueimageform.repositories.ImageRepository;
import com.edugroupe.springboutiqueimageform.repositories.ProduitRepository;

@Controller
@RequestMapping("/produits")
@CrossOrigin("http://localhost:4200")
public class ProduitController {
	
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private ImageRepository imageRepository;
	
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Page<Produit> findAll(@PageableDefault(page = 0, size = 8) Pageable page) {
		return produitRepository.findAll(page);
	}
	
	@GetMapping(value = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Produit> findById(@PathVariable("id") int id) {
		return produitRepository.findById(id)
								.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
								.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping(value="",
				consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Produit> createProduit(
									@RequestBody Produit produit,
									@RequestParam("categorieId") int categorieId) {
		if (produit.getId() != 0 || categorieId == 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Optional<Categorie> cat = categorieRepository.findById(categorieId);
		if (!cat.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		produit.setCategorie(cat.get());
		produit = produitRepository.save(produit);
		return new ResponseEntity<Produit>(produit, HttpStatus.CREATED);
	}

	@PutMapping(value="",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Produit> updateProduit(
								@RequestBody Produit produit,
								@RequestParam("categorieId") int categorieId) {
	if (produit.getId() == 0 || categorieId == 0)
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	
	// association categorie
	Optional<Categorie> cat = categorieRepository.findById(categorieId);
	if (!cat.isPresent())
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	produit.setCategorie(cat.get());
	
	// association images
	Optional<Produit> originalProduit = produitRepository.findById(produit.getId());
	if (!originalProduit.isPresent())
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	produit.setImages(originalProduit.get().getImages());
	
	produit = produitRepository.save(produit);
	
	return new ResponseEntity<Produit>(produit, HttpStatus.ACCEPTED);
}
	@DeleteMapping(value="/{id:[0-9]+}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteProduit(@PathVariable("id") int id) {
		Optional<Produit> oproduit = produitRepository.findById(id);
		if (!oproduit.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Produit produit = oproduit.get();
		// effacement des images associées
		for (Image img : produit.getImages()) {
			imageRepository.delete(img);
			imageRepository.deleteImageFile(img);
		}
		produit.getImages().clear();
		// effacement produit
		produitRepository.delete(oproduit.get());
		return new ResponseEntity<Map<String,Object>>(
						Collections.singletonMap("id_deleted", produit.getId()),
						HttpStatus.ACCEPTED);
	}
	
	
	
}
