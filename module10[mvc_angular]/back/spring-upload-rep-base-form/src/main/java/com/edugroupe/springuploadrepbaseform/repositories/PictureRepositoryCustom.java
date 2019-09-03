package com.edugroupe.springuploadrepbaseform.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

import com.edugroupe.springuploadrepbaseform.metier.Picture;

public interface PictureRepositoryCustom {
	boolean savePictureFile(Picture picture, InputStream file);
	Optional<File> getPictureFile(String storageid);
	boolean deletePictureFile(Picture picture);
	
}
