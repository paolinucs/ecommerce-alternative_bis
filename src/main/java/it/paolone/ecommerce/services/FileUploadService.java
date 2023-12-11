package it.paolone.ecommerce.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadService {

    private final Path rootDirectory = Paths.get("./uploads");

    public void initDirectory() {
        try {
            Files.createDirectories(this.rootDirectory);
        } catch (IOException exception) {
            throw new RuntimeException("Could not initialize directory: " + exception.getMessage());
        }
    }

    public boolean saveFile(MultipartFile uploadingFile) {
        try {
            Files.copy(uploadingFile.getInputStream(), this.rootDirectory.resolve(uploadingFile.getOriginalFilename()));

        } catch (Exception exception) {
            if (exception instanceof FileAlreadyExistsException) {
                throw new RuntimeException("File (ore another one with the same name) already exists!");
            }
            throw new RuntimeException(exception.getMessage());
        }

        return true;
    }

    public UrlResource loadFile(String filename) {
        try {
            Path filePath = rootDirectory.resolve(filename);
            UrlResource fileResource = new UrlResource(filePath.toUri());

            if (fileResource.exists() && fileResource.isReadable()) {
                return fileResource;
            } else
                throw new RuntimeException("File is not readable or it does not exist.");
        } catch (MalformedURLException exception) {
            throw new RuntimeException("Exception thrown: " + exception.getMessage());
        }

    }

    public boolean deleteFile(String filename) {
        try {
            return FileSystemUtils.deleteRecursively(rootDirectory.resolve(filename));
        } catch (IOException exception) {
            return false;
        }
    }

}
