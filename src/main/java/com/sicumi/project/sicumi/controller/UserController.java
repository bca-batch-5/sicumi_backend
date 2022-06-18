package com.sicumi.project.sicumi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sicumi.project.sicumi.exception.custom.CustomNullException;
import com.sicumi.project.sicumi.model.dto.ChangePasswordRequest;
import com.sicumi.project.sicumi.model.dto.EmailRequest;
import com.sicumi.project.sicumi.model.dto.LoginRequest;
import com.sicumi.project.sicumi.model.dto.NewUserRequest;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.services.UserServices;
import java.io.IOException;
import java.net.MalformedURLException;
import org.springframework.http.HttpHeaders;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sicumi.project.sicumi.exception.CustomUnauthorizedException;
import com.sicumi.project.sicumi.exception.FileStorageException;
import com.sicumi.project.sicumi.model.dto.UserDto;
import com.sicumi.project.sicumi.service.FileService;
import com.sicumi.project.sicumi.service.UserService;


@CrossOrigin(value = "*")
@RestController
public class UserController {
  
  private ResponseData<Object> responseData;

  @Autowired
  private UserServices userServices;

  @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;


  @PostMapping("/user/signin")
  public ResponseEntity<?> getUser(@RequestBody @Valid LoginRequest loginData) throws CustomNullException{
    responseData =  userServices.getUserLogin(loginData);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PostMapping("/user/signup")
  public ResponseEntity<?> addUser(@RequestBody @Valid NewUserRequest signupData) throws CustomNullException{
    responseData =  userServices.createNewUser(signupData);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PutMapping("/user/resetpassword")
  public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest resetPassword ) throws CustomNullException{
    responseData = userServices.changePassword(resetPassword);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
  
  @PostMapping("/user/find")
  public ResponseEntity<?> getEmail(@RequestBody @Valid EmailRequest dataEmail) throws CustomNullException{
    responseData =  userServices.findEmail(dataEmail);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
  
  @PostMapping("/user/check")
  public ResponseEntity<?> checkEmail(@RequestBody @Valid EmailRequest dataEmail) throws CustomNullException{
    responseData =  userServices.checkEmail(dataEmail);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PutMapping("/users/{id}/info")
    public ResponseEntity<?> updateInfo(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updateInfo(id, dto);
        return ResponseEntity.status(responseData.getStatus())
                .body(responseData);

    }

    @PutMapping("/users/{id}/pass")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePassword(id, dto);
        return ResponseEntity.status(responseData.getStatus())
                .body(responseData);

    }

    @PutMapping("/users/{id}/pin")
    public ResponseEntity<?> updatePin(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePin(id, dto);
        return ResponseEntity.status(responseData.getStatus())
                .body(responseData);

    }

    @PutMapping("/users/{id}/phone")
    public ResponseEntity<?> updatePhone(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePhone(id, dto);
        return ResponseEntity.status(responseData.getStatus())
                .body(responseData);

    }

    @PutMapping("/users/{id}/img")
    public ResponseEntity<?> updatePhoto(@RequestParam("file") MultipartFile file, @PathVariable("id") int id)
            throws FileStorageException, CustomNullException, CustomUnauthorizedException, com.sicumi.project.sicumi.exception.CustomNullException {
        ResponseData<?> responseData = fileService.uploadFile(file, id);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @GetMapping("/users/{id}/{filename:.+}")
    public ResponseEntity<?> getUserImage(@PathVariable("id") Long id, @PathVariable("filename") String filename,
            HttpServletRequest request) throws MalformedURLException, CustomNullException, IOException, com.sicumi.project.sicumi.exception.CustomNullException {
        ResponseData<Map<String, Object>> responseData = fileService.loadFile(filename, request);
        Map<String, Object> data = responseData.getData();
        String contentType = (String) data.get("contentType");
        Resource resource = (Resource) data.get("resource");

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {

        responseData = userService.getdUserById(id);
        return ResponseEntity.status(responseData.getStatus())
                .body(responseData);

    }
}

