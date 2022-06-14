package com.sicumi.project.sicumi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public ResponseData<Object> updateInfo(int id, UserDto dto) {
        Optional<User> userOpt = userRepository.findById(id);
        Optional<DetailUser> detailUserOpt = detailUserRepository.findById(id);
        return userValidator.updateInfoValidation(userOpt, detailUserOpt, dto);

    }

    @Override
    public ResponseData<Object> updatePassword(int id, UserDto dto) {
        Optional<User> userOpt = userRepository.findById(id);
        ResponseData<Object> responseData = userValidator.updatePasswordValidation(userOpt, dto);
        return responseData;
    }

    @Override
    public ResponseData<Object> updatePin(int id, UserDto dto) {
        Optional<User> userOpt = userRepository.findById(id);
        return userValidator.updatePinValidation(userOpt, dto);
    }

    @Override
    public ResponseData<Object> updatePhone(int id, UserDto dto) {
        Optional<User> userOpt = userRepository.findById(id);
        return userValidator.updatePhoneValidation(userOpt, dto);
    }

}
