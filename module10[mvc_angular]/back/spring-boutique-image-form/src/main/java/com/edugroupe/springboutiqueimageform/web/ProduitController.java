package com.edugroupe.springboutiqueimageform.web;

import java.io.File;
import java.io.IOException;
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

	@PostMapping(value="/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")		
	public ResponseEntity<Image> upload(@RequestParam("file") MultipartFile file,
										@RequestParam("produitId") int produitId){
		Optional<Produit> p = produitRepository.findById(produitId);
		if (!p.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			Image img = new Image(0,
					file.getOriginalFilename(),
					file.getOriginalFilename(),
					file.getContentType(),
					"",
					"");
			// sauvegarde fichier et generation thumbnail
			imageRepository.saveImageFile(img, file.getInputStream());
			img.setProduit(p.get());
			img = imageRepository.save(img);
			return new ResponseEntity<Image>(img, HttpStatus.CREATED);
		} catch (IOException e) {e.printStackTrace();}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{id:[0-9]+}/data")
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<FileSystemResource> imageData(@PathVariable("id")int id) {
		Optional<Image> op = imageRepository.findById(id);
		if (!op.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Optional<File> ofile = imageRepository.getImageFile(
										op.get().getStorageId());
		if (!ofile.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(op.get().getContentType()));
		headers.setContentLength(ofile.get().length());
		headers.setContentDispositionFormData("attachment", op.get().getFileName());
		ResponseEntity<FileSystemResource> re = 
			new ResponseEntity<FileSystemResource>(
				new FileSystemResource(ofile.get()),
				headers,
				HttpStatus.OK);
		return re;
	}
	
	@GetMapping(value = "/{id:[0-9]+}/thumbdata")
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<FileSystemResource> imageThumbData(@PathVariable("id")int id) {
		Optional<Image> op = imageRepository.findById(id);
		if (!op.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Optional<File> ofile = imageRepository.getImageFile(
										op.get().getThumbStorageId());
		if (!ofile.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		headers.setContentLength(ofile.get().length());
		headers.setContentDispositionFormData("attachment", op.get().getFileName());
		ResponseEntity<FileSystemResource> re = 
			new ResponseEntity<FileSystemResource>(
				new FileSystemResource(ofile.get()),
				headers,
				HttpStatus.OK);
		return re;
	}	
	
	
	
}
