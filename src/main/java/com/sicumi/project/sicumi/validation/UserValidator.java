package com.sicumi.project.sicumi.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sicumi.project.sicumi.exception.CustomNullException;
import com.sicumi.project.sicumi.exception.DataNotFoundException;
import com.sicumi.project.sicumi.model.DetailUser;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.UserDto;
import com.sicumi.project.sicumi.repository.DetailUserRepository;
import com.sicumi.project.sicumi.repository.UserRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetailUserRepository detailUserRepository;

    private ResponseData<Object> responseData;
    private Map<String, Object> responseMap = new HashMap<>();

    public ResponseData<Object> updateInfoValidation(Optional<User> userOpt, Optional<DetailUser> detailUserOpt,
            UserDto dto) {
        User user = userOpt.get();
        DetailUser detailuser = detailUserOpt.get();
        if (userOpt.isPresent()) {
            responseMap = new HashMap<>();
            user.setName(dto.getFirstname());
            detailuser.setFirstname(user.getName());
            detailuser.setLastname(dto.getLastname());
            user.setEmail(dto.getEmail());
            detailUserRepository.save(detailuser);
            userRepository.save(user);

            responseMap.put("First Name", detailuser.getFirstname());
            responseMap.put("Last Name", detailuser.getLastname());
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
                String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
                if (dto.getNewpassword() != "" && dto.getNewpassword().matches(regex)) {
                    if (dto.getNewpassword().equals(dto.getRenewpassword())) {
                        String password = dto.getRenewpassword();

                        String hashedPassword = passwordEncoder.encode(password);
                        user.setPassword(hashedPassword);
                        user.setLast_update(new Date());
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
                    responseData = new ResponseData<>(HttpStatus.NOT_ACCEPTABLE.value(),
                            "password must contain at least one digit(0-9), lower case, uppercase, special character(!@&()) and between 8-20 char ",
                            null);
                    return responseData;

                }

            } else {
                responseData = new ResponseData<>(HttpStatus.FORBIDDEN.value(),
                        "please enter your current password",
                        null);
                return responseData;
            }

        } else {
            throw new DataNotFoundException("user is not found");
        }
    }

    public ResponseData<Object> updatePinValidation(Optional<User> userOpt, UserDto dto) {
        User user = userOpt.get();
        if (userOpt.isPresent()) {
            String pin = dto.getPin1() + dto.getPin2() + dto.getPin3() + dto.getPin4() + dto.getPin5() + dto.getPin6();
            user.setLast_update(new Date());
            user.setPin(pin);
            userRepository.save(user);
            responseData = new ResponseData<>(HttpStatus.OK.value(), "update pin success", user.getPin());
            return responseData;
        } else {
            throw new DataNotFoundException("user is not found");
        }
    }

    public ResponseData<Object> updatePhoneValidation(Optional<DetailUser> detailUserOpt,
            UserDto dto) {

        if (detailUserOpt.isPresent()) {
            if (dto.getPhone() != " " && dto.getPhone().matches("[0-9]+")) {
                DetailUser detailuser = detailUserOpt.get();

                detailuser.setPhone(dto.getPhone());
                detailUserRepository.save(detailuser);
                responseData = new ResponseData<>(HttpStatus.OK.value(), "update phone number success",
                        detailuser.getPhone());
                return responseData;
            } else {
                responseData = new ResponseData<>(HttpStatus.NOT_ACCEPTABLE.value(),
                        "phone number must be a number and not null ",
                        null);
                return responseData;

            }
        } else {
            throw new DataNotFoundException("user is not found");
        }
    }

    public void notFoundUserValidation(Optional<DetailUser> userFind) throws CustomNullException {
        if (userFind.isEmpty()) {
            throw new CustomNullException("User is not found.");
        }
    }

    public ResponseData<Object> getUserByIdValidation(Optional<User> userOpt, Optional<DetailUser> detailUserOpt) {
        if (detailUserOpt.isPresent()) {
            DetailUser detailUser = detailUserOpt.get();
            User user = userOpt.get();
            responseMap = new HashMap<>();
            String fullname = user.getName() + " " + detailUser.getLastname();
            responseMap.put("firstname", user.getName());
            responseMap.put("lastname", detailUser.getLastname());
            responseMap.put("fullname", fullname);
            responseMap.put("photo", detailUser.getPhoto());

            responseMap.put("phone", "+62" + detailUser.getPhone());
            responseMap.put("email", user.getEmail());

            responseData = new ResponseData<>(HttpStatus.OK.value(), "success",
                    responseMap);
            return responseData;
        } else {
            responseData = new ResponseData<>(HttpStatus.NOT_FOUND.value(), "user is not found", null);
            return responseData;

        }

    }
}