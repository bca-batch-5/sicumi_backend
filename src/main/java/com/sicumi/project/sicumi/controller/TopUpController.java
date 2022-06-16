package com.sicumi.project.sicumi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicumi.project.sicumi.model.DetailUser;
import com.sicumi.project.sicumi.model.TopUp;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.TopUpDto;
import com.sicumi.project.sicumi.repository.DetailUserRepository;
import com.sicumi.project.sicumi.repository.TopUpRepository;

@RestController
@RequestMapping ("/topup")
public class TopUpController {
    @Autowired
    private TopUpRepository topUpRepository;

    @Autowired
    private DetailUserRepository detailUserRepository;

    ResponseData<Object> responseData;

    @PostMapping
    public ResponseEntity<?> createTopUp (@RequestBody TopUpDto topUpDto){
        Optional<DetailUser> detailUserOps = detailUserRepository.findByPhone(topUpDto.getPhone());

        DetailUser dUser= detailUserOps.get();

        TopUp topUp = new TopUp(topUpDto.getSource(), topUpDto.getAmount(), dUser);

        topUpRepository.save(topUp);

        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess ", topUp);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<TopUp> topUps = topUpRepository.findAll();

        responseData= new ResponseData<Object>(HttpStatus.OK.value(),"succsess", topUps);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
}
