package com.sicumi.project.sicumi.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sicumi.project.sicumi.config.FileStorageProperties;
import com.sicumi.project.sicumi.exception.CustomNullException;

import com.sicumi.project.sicumi.exception.FileStorageException;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.FileInfo;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.repository.UserRepository;
import com.sicumi.project.sicumi.validation.UserValidator;

@Service
@EnableConfigurationProperties(value = FileStorageProperties.class)
public class FileServiceImpl implements FileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    private Path fileStorageLocation;
    private Map<String, Object> data;

    // dir upload
    @Autowired
    public void fileService(FileStorageProperties fileStorageProperties) throws FileStorageException {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.");
        }
    }

    @Override
    public ResponseData<?> uploadFile(MultipartFile file, int id)
            throws FileStorageException, CustomNullException {
        Optional<User> userFind = userRepository.findById(id);
        userValidator.notFoundUserValidation(userFind);

        User user = userFind.get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetPath = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            user.setPhoto(fileName);
            userRepository.save(user);

            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").path("/{id}/")
                    .path(fileName)
                    .build(id).toString();

            FileInfo fileInfo = new FileInfo(fileName, url, file.getContentType());

            ResponseData<Object> responseData = new ResponseData<>(200, "Success", fileInfo);

            return responseData;
        } catch (Exception e) {

            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    @Override
    public ResponseData<Map<String, Object>> loadFile(String filename, HttpServletRequest request)
            throws CustomNullException, MalformedURLException, IOException {

        Path filePath = this.fileStorageLocation.resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            if (contentType.isEmpty()) {
                contentType = "application/octet-stream";
            }

            data = new HashMap<>();
            data.put("resource", resource);
            data.put("contentType", contentType);

            ResponseData<Map<String, Object>> responseData = new ResponseData<>(200, "success", data);

            return responseData;
        } else {
            throw new CustomNullException("File not found " + filename);
        }
    }
}
