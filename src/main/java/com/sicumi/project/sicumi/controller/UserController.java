package com.sicumi.project.sicumi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.UserDto;
import com.sicumi.project.sicumi.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    ResponseData<Object> responseData;

    @PutMapping("/{id}/info")
    public ResponseEntity<?> updateInfo(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updateInfo(id, dto);
        return ResponseEntity.ok(userService.updateInfo(id, dto));

    }

    @PutMapping("/{id}/pass")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePassword(id, dto);
        return ResponseEntity.ok(userService.updatePassword(id, dto));

    }

    @PutMapping("/{id}/pin")
    public ResponseEntity<?> updatePin(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePin(id, dto);
        return ResponseEntity.ok(userService.updatePin(id, dto));

    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<?> updatePhone(@PathVariable Integer id, @RequestBody UserDto dto) {

        responseData = userService.updatePhone(id, dto);
        return ResponseEntity.ok(userService.updatePhone(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {

        responseData = userService.deleteUser(id);
        return ResponseEntity.ok(userService.deleteUser(id));

    }
}