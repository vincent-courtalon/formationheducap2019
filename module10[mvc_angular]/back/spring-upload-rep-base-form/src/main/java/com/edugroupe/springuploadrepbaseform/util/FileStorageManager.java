package com.edugroupe.springuploadrepbaseform.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileStorageManager {

	@Value("${filestorage.base-repertoire}")
	private File storageRoot;

	private Random rd = new Random();

	public String saveNewFile(String collection, InputStream data) {
		// si le rpertoire de base n'est pas bon, sauve qui peut
		if (storageRoot == null || !storageRoot.exists() || !storageRoot.isDirectory()) {
			throw new RuntimeException("storage root invalid");
		}
		String name = collection + "#" + rd.nextLong() + LocalDateTime.now().getNano();
		String sha1Name = DigestUtils.sha1Hex(name);
		String sousRep = sha1Name.substring(0, 2);

		// je me positionne sur le sous repertoire devant contenir mon image
		File rep = Paths.get(storageRoot.getAbsolutePath(), sousRep).toFile();

		// on creer le sous-rep s'il n'existe pas déjà
		if (!rep.exists())
			rep.mkdirs();
		if (!rep.isDirectory())
			throw new RuntimeException("unable to create storage dir for " + sha1Name);

		System.out.println("sauvegarde de " + sha1Name);
		try {
			Files.copy(data, Paths.get(rep.getAbsolutePath(), sha1Name), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("unable to save file", e);
		}
		return sha1Name;
	}

	public Optional<File> getImageFile(String storageId) {
		// si le répertoire de base n'est pas bon, sauve qui peut
		if (storageRoot == null || !storageRoot.exists() || !storageRoot.isDirectory()) {
			throw new RuntimeException("storage root invalid");
		}

		// repbase/##
		File rep = Paths.get(storageRoot.getAbsolutePath(), storageId.substring(0, 2)).toFile();
		if (!rep.exists() || !rep.isDirectory()) {
			return Optional.empty(); // empty -> pas de fichier
		}
		// chemin complet du fichier
		File f = Paths.get(rep.getAbsolutePath(), storageId).toFile();
		if (f != null && f.exists() && f.isFile())
			return Optional.of(f);
		else
			return Optional.empty();
	}

	public boolean deleteFile(String storageId) {
		if (storageRoot == null 
			|| !storageRoot.exists()
			|| !storageRoot.isDirectory()) {
			return false;
		}
		// repbase/##
		File rep = Paths.get(storageRoot.getAbsolutePath(), storageId.substring(0, 2)).toFile();
		if (!rep.exists() || !rep.isDirectory()) {
			return false; 
		}
		File f = Paths.get(rep.getAbsolutePath(), storageId).toFile();
		if (f != null && f.exists() && f.isFile())
			return f.delete();
		else
			return false;
	}
}
