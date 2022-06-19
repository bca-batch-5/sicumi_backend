package com.sicumi.project.sicumi.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sicumi.project.sicumi.model.DetailUser;
import com.sicumi.project.sicumi.model.TopUp;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.TopUpDto;
import com.sicumi.project.sicumi.repository.DetailUserRepository;
import com.sicumi.project.sicumi.repository.TopUpRepository;

@Service
@Transactional
public class TopUpServiceImpl implements TopUpService {

    private ResponseData<Object> responseData;

    @Autowired
    private DetailUserRepository detailUserRepository;

    @Autowired 
    private TopUpRepository topUpRepository;

    @Override
    public ResponseData<Object> createTopUp(TopUpDto topUpDto) {
        Optional<DetailUser> detailUserOps = detailUserRepository.findByPhone(topUpDto.getPhone());

        DetailUser dUser= detailUserOps.get();

        TopUp topUp = new TopUp(topUpDto.getSource(), topUpDto.getAmount(), dUser);

        topUpRepository.save(topUp);

        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess ", topUp);
        return responseData;
    }

    @Override
    public ResponseData<Object> getAll() {
        List<TopUp> topUps = topUpRepository.findAll();

        responseData= new ResponseData<Object>(HttpStatus.OK.value(),"succsess", topUps);
        return responseData;
    }
    
}
