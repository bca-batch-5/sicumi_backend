package com.sicumi.project.sicumi.services;

import com.sicumi.project.sicumi.exception.custom.CustomNullException;
import com.sicumi.project.sicumi.model.dto.LoginRequest;
import com.sicumi.project.sicumi.model.dto.NewUserRequest;
import com.sicumi.project.sicumi.model.dto.ResponseData;

public interface UserServices {
  ResponseData<Object> getUserLogin(LoginRequest loginData) throws CustomNullException;
  ResponseData<Object> createNewUser(NewUserRequest signUpData) throws CustomNullException;
}
