package com.sicumi.project.sicumi.services;

import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.TopUpDto;

public interface TopUpService {
    ResponseData <Object> createTopUp(TopUpDto topUpDto);

    ResponseData<Object> getAll();
}
