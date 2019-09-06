package com.edugroupe.springuploadrepbaseform.web;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edugroupe.springuploadrepbaseform.metier.Picture;
import com.edugroupe.springuploadrepbaseform.repositories.PictureRepository;

@Controller
@RequestMapping("/pictures")
public class PictureController {

	@Autowired
	private PictureRepository pictureRepository;
	
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public Page<Picture> findAll(
			@PageableDefault(page = 0, size = 10) Pageable page) {
		return pictureRepository.findAll(page);
	}
	
	@GetMapping(value="/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Picture> findById(@PathVariable("id") long id) {
		return pictureRepository.findById(id)
								.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
								.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping(value="/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	//@PreAuthorize("permitAll")
	public ResponseEntity<Picture> upload(@RequestParam("file") MultipartFile file,
										  @RequestParam("titre") Optional<String> titre){
		try {
			Picture p = new Picture(0,
					titre.orElse(file.getOriginalFilename()),
					null,
					file.getOriginalFilename(),
					file.getContentType(),
					0,
					0,
					"",
					DigestUtils.sha1Hex(file.getInputStream()),
					"");
			// sauvegarde fichier et generation thumbnail
			pictureRepository.savePictureFile(p, file.getInputStream());
			p = pictureRepository.save(p);
			return new ResponseEntity<Picture>(p, HttpStatus.CREATED);
		} catch (IOException e) {e.printStackTrace();}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{id:[0-9]+}/data")
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	//@PreAuthorize("permitAll")
	public ResponseEntity<FileSystemResource> pictureData(@PathVariable("id")long id) {
		Optional<Picture> op = pictureRepository.findById(id);
		if (!op.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Optional<File> ofile = pictureRepository.getPictureFile(
										op.get().getStorageid());
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
	//@PreAuthorize("permitAll")
	public ResponseEntity<FileSystemResource> pictureThumbData(@PathVariable("id")long id) {
		Optional<Picture> op = pictureRepository.findById(id);
		if (!op.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Optional<File> ofile = pictureRepository.getPictureFile(
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
	
	@DeleteMapping(value="/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Map<String, Object>> deletePicture(
												@PathVariable("id") long id) {
		return pictureRepository.findById(id)
						.map(p -> {
							pictureRepository.deletePictureFile(p);
							pictureRepository.delete(p);
							return new ResponseEntity<Map<String,Object>>(
									Collections.singletonMap("nbdeleted", 1),
									HttpStatus.ACCEPTED);
						})
						.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping(value="/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin("http://localhost:4200")
	@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Picture> updatePicture(
					@PathVariable("id") long id,
					@RequestParam("titre") String titre) {
		Optional<Picture> opict = pictureRepository.findById(id);
		if (!opict.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Picture p = opict.get();
		p.setTitre(titre);
		p = pictureRepository.save(p); // sauvegarde
		return new ResponseEntity<Picture>(p, HttpStatus.ACCEPTED);
	}
	
	
}
