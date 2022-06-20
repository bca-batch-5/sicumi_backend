package com.sicumi.project.sicumi.services;

import com.sicumi.project.sicumi.model.dto.DetailUserDto;
import com.sicumi.project.sicumi.model.dto.ResponseData;

public interface DetailUserService {
    ResponseData <Object> getAllContact ();
    ResponseData <Object> getByUserId(Integer userId);
    ResponseData <Object> updateBalance(Integer userId, DetailUserDto detailUserDto);
    ResponseData <Object> getReceiver(Integer userId);
}
