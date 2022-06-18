package com.sicumi.project.sicumi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicumi.project.sicumi.model.DetailUser;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.UserDto;
import com.sicumi.project.sicumi.repository.DetailUserRepository;
import com.sicumi.project.sicumi.repository.UserRepository;
import com.sicumi.project.sicumi.validation.UserValidator;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DetailUserRepository detailUserRepository;

    @Autowired
    private UserValidator userValidator;

    private String intToString;

    private ResponseData<Object> responseData;

    @Override
    public ResponseData<Object> updateInfo(Integer id, UserDto dto) {
        intToString = id.toString();
        Optional<User> userOpt = userRepository.findById(intToString);
        Optional<DetailUser> detailUserOpt = detailUserRepository.findById(id);
        return userValidator.updateInfoValidation(userOpt, detailUserOpt, dto);

    }

    @Override
    public ResponseData<Object> updatePassword(Integer id, UserDto dto) {
        intToString = id.toString();
        Optional<User> userOpt = userRepository.findById(intToString);
        ResponseData<Object> responseData = userValidator.updatePasswordValidation(userOpt, dto);
        return responseData;
    }

    @Override
    public ResponseData<Object> updatePin(Integer id, UserDto dto) {
        intToString = id.toString();
        Optional<User> userOpt = userRepository.findById(intToString);
        return userValidator.updatePinValidation(userOpt, dto);
    }

    @Override
    public ResponseData<Object> updatePhone(Integer id, UserDto dto) {
        Optional<DetailUser> detailUserOpt = detailUserRepository.findById(id);
        return userValidator.updatePhoneValidation(detailUserOpt, dto);
    }

    @Override
    public ResponseData<Object> getdUserById(Integer id) {
        intToString = id.toString();
        Optional<User> userOpt = userRepository.findById(intToString);
        Optional<DetailUser> detailUserOpt = detailUserRepository.findById(id);

        responseData = new ResponseData<>(HttpStatus.NOT_FOUND.value(), "testing", detailUserOpt);
        return responseData;

        // return userValidator.getUserByIdValidation(userOpt, detailUserOpt);
    }

}
