package com.sicumi.project.sicumi.service;

import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.UserDto;

public interface UserService {
    ResponseData<Object> updateInfo(int id, UserDto dto);

    ResponseData<Object> updatePassword(int id, UserDto dto);

    ResponseData<Object> updatePin(int id, UserDto dto);

    ResponseData<Object> updatePhone(int id, UserDto dto);

    ResponseData<Object> getdUserById(int id);

}
