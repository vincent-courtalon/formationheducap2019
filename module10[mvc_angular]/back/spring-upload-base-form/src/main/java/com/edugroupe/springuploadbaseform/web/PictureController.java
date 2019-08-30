package com.edugroupe.springuploadbaseform.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edugroupe.springuploadbaseform.metier.Picture;
import com.edugroupe.springuploadbaseform.metier.projections.PictureWithoutFile;
import com.edugroupe.springuploadbaseform.repositories.PictureRepository;

@Controller
public class PictureController {

	@Autowired
	private PictureRepository pictureRepository;
	
	@GetMapping(value="/pictures", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public Page<PictureWithoutFile> findAll(
				@PageableDefault(page = 0, size = 10) Pageable page) {
		return pictureRepository.findAllWithoutFile(page);
	}
	
	@GetMapping(value="/pictures/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public PictureWithoutFile findById(@PathVariable("id") int id) {
		return pictureRepository.findWithoutFileById(id);
	}

	@PostMapping(value="/pictures", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(
			methods = {RequestMethod.POST,RequestMethod.HEAD, RequestMethod.OPTIONS},
			allowedHeaders = "*",
			value = "http://localhost:4200")
	public PictureWithoutFile uploadImage(@RequestParam("file") MultipartFile file,
										  @RequestParam("titre") Optional<String> titre) {
		System.out.println("filename original:" + file.getOriginalFilename());
		System.out.println("taille fichier:" + file.getSize());
		System.out.println("content type:" + file.getContentType());
		
		Picture p = new Picture(0, 
								titre.orElse(file.getOriginalFilename()),
								file.getOriginalFilename(),
								file.getContentType(),
								(int)file.getSize());
		try {
			p.setContent(file.getBytes());
		} catch (IOException e) {e.printStackTrace();}
		p = pictureRepository.save(p);
		return new PictureWithoutFile(p.getId(),
									  p.getTitre(),
									  p.getFileName(),
									  p.getContentType());
	}
	
	@GetMapping(value="/pictures/{id:[0-9]+}/content")
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<ByteArrayResource> pictureContent(
											@PathVariable("id") int id)
	{
		if (!pictureRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Picture p = pictureRepository.findById(id).get();
		// en tete de la preponse
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(p.getContentType()));
		headers.setContentLength(p.getFileSize());
		headers.setContentDispositionFormData("attachment", p.getFileName());
		
		ByteArrayResource bsr = new ByteArrayResource(p.getContent());
		return new ResponseEntity<ByteArrayResource>(bsr, headers, HttpStatus.OK);
		
	}
	
}
