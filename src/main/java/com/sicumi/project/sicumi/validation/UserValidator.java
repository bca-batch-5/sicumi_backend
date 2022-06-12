package com.sicumi.project.sicumi.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sicumi.project.sicumi.exception.CustomNullException;
import com.sicumi.project.sicumi.exception.DataNotFoundException;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.UserDto;
import com.sicumi.project.sicumi.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    private ResponseData<Object> responseData;
    private Map<String, Object> responseMap = new HashMap<>();

    public ResponseData<Object> updateInfoValidation(Optional<User> userOpt, UserDto dto) {
        User user = userOpt.get();
        if (userOpt.isPresent()) { // eror
            user.setFirstname(dto.getFirstname());
            user.setLastname(dto.getLastname());
            user.setEmail(dto.getEmail());
            userRepository.save(user);
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
        User user = userOpt.get();
        if (userOpt.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(dto.getCurrentpassword(), user.getPassword())) {
                if (dto.getNewpassword().equals(dto.getRenewpassword())) {
                    String password = dto.getRenewpassword();

                    String hashedPassword = passwordEncoder.encode(password);
                    user.setPassword(hashedPassword);
                    userRepository.save(user);

                    responseData = new ResponseData<>(HttpStatus.OK.value(), "update password success",
                            user.getPassword());

                    return responseData;
                } else {
                    responseData = new ResponseData<>(HttpStatus.BAD_REQUEST.value(),
                            "please enter the same password",
                            null);
                    return responseData;
                }

            } else {
                responseData = new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "please enter your current password",
                        null);
                return responseData;
            }

        } else {
            throw new DataNotFoundException("user is not found");
        }
    }

    public ResponseData<Object> updatePinValidation(Optional<User> userOpt, UserDto dto) {

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Integer pin = Integer.parseInt(
                    dto.getPin1() + dto.getPin2() + dto.getPin3() + dto.getPin4() + dto.getPin5() + dto.getPin6());

            user.setPin(pin);
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

    public void notFoundUserValidation(Optional<User> userFind) throws CustomNullException {
        if (userFind.isEmpty()) {
            // if (userFind == null) {
            throw new CustomNullException("User not found.");
        }
    }

}