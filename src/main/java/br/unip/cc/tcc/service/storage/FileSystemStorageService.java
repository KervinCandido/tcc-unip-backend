package br.unip.cc.tcc.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService {

	private final Path rootLocation;

	public FileSystemStorageService() {
		this.rootLocation = Paths.get("upload");
	}
	
	public void store(MultipartFile file, String subPath, String filename) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			Path path;
			if (subPath != null && !subPath.isBlank()) {
				path = sanitizeSubPath(subPath);
				createDirectories(path);
			} else {
				path = rootLocation;
			}
			
			Path destinationFile = path.resolve(
					Paths.get(filename))
					.normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(path.toAbsolutePath())) {
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	public Path load(String filename, String subPath) {
		Path path;
		if (subPath != null && !subPath.isBlank()) {
			path = sanitizeSubPath(subPath);
		} else {
			path = rootLocation;
		}
		return path.resolve(filename);
	}

	private Path sanitizeSubPath(String subPath) {
		Path path;
		if (!subPath.startsWith("/")) {
			subPath = "/" + subPath;
		}
		path = Paths.get("upload" + subPath);
		return path;
	}

	public Resource loadAsResource(String filename, String subPath) {
		try {
			Path file = load(filename, subPath);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		createDirectories(rootLocation);
	}
	
	private void createDirectories(Path path) {
		try {
			Files.createDirectories(path);
		}
		catch (IOException e) {
			throw new StorageException("Could not create directories", e);
		}
	}
}
