package com.edugroupe.springuploadrepbaseform.repositories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.edugroupe.springuploadrepbaseform.metier.Picture;
import com.edugroupe.springuploadrepbaseform.util.FileStorageManager;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

public class PictureRepositoryImpl implements PictureRepositoryCustom {

	@Autowired
	private FileStorageManager fileStorageManager;
	
	// taille des miniatures
	public static final int THUMB_WIDTH = 164;
	public static final int THUMB_HEIGHT = 164;
	
	@Override
	public boolean savePictureFile(Picture picture, InputStream file) {
		//-----------------------------------------------
		// sauvegarde de l'image originale
		//-----------------------------------------------
		String storageId = fileStorageManager.saveNewFile("pictures", file);
		picture.setStorageid(storageId);
		//-----------------------------------------------
		// generation de la miniature et sa sauvegarde
		//-----------------------------------------------
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			Thumbnails.of(fileStorageManager.getImageFile(storageId).get())
					  .size(THUMB_WIDTH, THUMB_HEIGHT)
					  .scalingMode(ScalingMode.BICUBIC)
					  .outputFormat("jpg")
					  .toOutputStream(bos);
			String thumbStorageId = fileStorageManager.saveNewFile(
					"picturesthumb", new ByteArrayInputStream(bos.toByteArray()));
			picture.setThumbStorageId(thumbStorageId);
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("erreur a la generation de miniature ", e);
		}
		return true;
	}

	@Override
	public Optional<File> getPictureFile(String storageid) {
		return fileStorageManager.getImageFile(storageid);
	}

}
