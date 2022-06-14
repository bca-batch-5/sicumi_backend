package com.sicumi.project.sicumi.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import org.springframework.http.HttpHeaders;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sicumi.project.sicumi.exception.CustomNullException;
import com.sicumi.project.sicumi.exception.CustomUnauthorizedException;
import com.sicumi.project.sicumi.exception.FileStorageException;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.UserDto;
import com.sicumi.project.sicumi.service.FileService;
import com.sicumi.project.sicumi.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    ResponseData<Object> responseData;

    @PutMapping("/{id}/info")
    public ResponseEntity<?> updateInfo(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updateInfo(id, dto);
        return ResponseEntity.ok(responseData);

    }

    @PutMapping("/{id}/pass")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePassword(id, dto);
        return ResponseEntity.ok(responseData);// dicek ulang

    }

    @PutMapping("/{id}/pin")
    public ResponseEntity<?> updatePin(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePin(id, dto);
        return ResponseEntity.ok(responseData);

    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<?> updatePhone(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePhone(id, dto);
        return ResponseEntity.ok(responseData);

    }

    @PutMapping("/{id}/img")
    public ResponseEntity<?> updatePhoto(@RequestParam("file") MultipartFile file, @PathVariable("id") int id)
            throws FileStorageException, CustomNullException, CustomUnauthorizedException {
        ResponseData<?> responseData = fileService.uploadFile(file, id);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @GetMapping("/{id}/{filename:.+}")
    public ResponseEntity<?> getUserImage(@PathVariable("id") Long id, @PathVariable("filename") String filename,
            HttpServletRequest request) throws MalformedURLException, CustomNullException, IOException {
        ResponseData<Map<String, Object>> responseData = fileService.loadFile(filename, request);
        Map<String, Object> data = responseData.getData();
        String contentType = (String) data.get("contentType");
        Resource resource = (Resource) data.get("resource");

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}