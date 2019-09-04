package com.edugroupe.springboutiqueimageform.repositories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.edugroupe.springboutiqueimageform.metier.Image;
import com.edugroupe.springboutiqueimageform.util.FileStorageManager;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

public class ImageRepositoryImpl implements ImageRepositoryCustom {

	@Autowired
	private FileStorageManager fileStorageManager;
	
	// taille des miniatures
	public static final int THUMB_WIDTH = 256;
	public static final int THUMB_HEIGHT = 256;
	
	@Override
	public boolean saveImageFile(Image image, InputStream file) {
		//-----------------------------------------------
		// sauvegarde de l'image originale
		//-----------------------------------------------
		String storageId = fileStorageManager.saveNewFile("images", file);
		image.setStorageId(storageId);
		//-----------------------------------------------
		// generation de la miniature et sa sauvegarde
		//-----------------------------------------------
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			Thumbnails.of(fileStorageManager.getFile(storageId).get())
					  .size(THUMB_WIDTH, THUMB_HEIGHT)
					  .scalingMode(ScalingMode.BICUBIC)
					  .outputFormat("jpg")
					  .toOutputStream(bos);
			String thumbStorageId = fileStorageManager.saveNewFile(
					"imagesthumb", new ByteArrayInputStream(bos.toByteArray()));
			image.setThumbStorageId(thumbStorageId);
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("erreur a la generation de miniature ", e);
		}
		return true;
	}

	@Override
	public Optional<File> getImageFile(String storageId) {
		return fileStorageManager.getFile(storageId);
	}

	@Override
	public boolean deleteImageFile(Image image) {
		boolean successA = fileStorageManager.deleteFile(image.getStorageId());
		boolean successB = fileStorageManager.deleteFile(image.getThumbStorageId());
		return successA && successB;
	}

}
