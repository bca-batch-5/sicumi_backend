package com.sicumi.project.sicumi.service;

import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.UserDto;

public interface UserService {
    ResponseData<Object> updateInfo(Integer id, UserDto dto);

    ResponseData<Object> updatePassword(Integer id, UserDto dto);

    ResponseData<Object> updatePin(Integer id, UserDto dto);

    ResponseData<Object> updatePhone(Integer id, UserDto dto);

    ResponseData<Object> getdUserById(Integer id);

}
