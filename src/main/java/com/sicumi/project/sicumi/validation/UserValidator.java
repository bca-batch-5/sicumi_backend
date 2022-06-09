package com.sicumi.project.sicumi.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sicumi.project.sicumi.exception.DataNotFoundException;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.UserDto;
import com.sicumi.project.sicumi.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;

@Service
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ResponseData<Object> responseData;
    private Map<String, Object> responseMap = new HashMap<>();

    public ResponseData<Object> updateInfoValidation(Optional<User> userOpt, UserDto dto) {

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            User result = modelMapper.map(dto, User.class);
            result.setId(user.getId());
            result.setIsDeleted(user.getIsDeleted());
            result.setPassword(user.getPassword());
            result.setPhone(user.getPhone());
            result.setPin(user.getPin());
            result.setUsername(user.getUsername());

            userRepository.save(result);
            responseMap = new HashMap<>();

            responseMap.put("First Name", user.getFirstname());
            responseMap.put("Last Name", user.getLastname());
            responseMap.put("Email", user.getEmail());

            responseData = new ResponseData<>(HttpStatus.OK.value(), "update info success", responseMap);
            return responseData;
        } else {
            throw new DataNotFoundException("user is not found");
        }
    }

    public ResponseData<Object> updatePasswordValidation(Optional<User> userOpt, UserDto dto) {

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            user.setPassword(dto.getPassword());
            userRepository.save(user);
            responseData = new ResponseData<>(HttpStatus.OK.value(), "update password success", user.getPassword());
            return responseData;
        } else {
            throw new DataNotFoundException("user is not found");
        }
    }

    public ResponseData<Object> updatePinValidation(Optional<User> userOpt, UserDto dto) {

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            user.setPin(dto.getPin());
            userRepository.save(user);
            responseData = new ResponseData<>(HttpStatus.OK.value(), "update pin success", user.getPin());
            return responseData;
        } else {
            throw new DataNotFoundException("user is not found");
        }
    }

    public ResponseData<Object> updatePhoneValidation(Optional<User> userOpt, UserDto dto) {

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            user.setPhone(dto.getPhone());
            userRepository.save(user);
            responseData = new ResponseData<>(HttpStatus.OK.value(), "update phone number success", user.getPhone());
            return responseData;
        } else {
            throw new DataNotFoundException("user is not found");
        }
    }

    public ResponseData<Object> deleteUserValidation(Optional<User> userOpt) {

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (user.getIsDeleted().equals(false)) {
                user.setIsDeleted(true);

                userRepository.save(user);
                String message = user.getUsername() + " berhasil dihapus";
                responseData = new ResponseData<>(HttpStatus.OK.value(), message, null);
                return responseData;
            } else {
                String message = user.getUsername() + " telah dihapus";
                responseData = new ResponseData<>(HttpStatus.OK.value(), message, null);
                return responseData;
            }

        } else {

            throw new DataNotFoundException("username is not found");
        }
    }

}