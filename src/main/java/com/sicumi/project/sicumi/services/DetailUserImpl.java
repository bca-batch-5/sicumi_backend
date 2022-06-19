package com.sicumi.project.sicumi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sicumi.project.sicumi.model.DetailUser;
import com.sicumi.project.sicumi.model.dto.DetailUserDto;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.repository.DetailUserRepository;


@Service
@Transactional
public class DetailUserImpl implements DetailUserService {
    
    private ResponseData<Object> responseData;

    @Autowired
    private ModelMapper modelMapper;
   
    @Autowired
    private DetailUserRepository detailUserRepository;


    @Override
    public ResponseData<Object> getByUserId(Integer userId) {
        DetailUser detailUser = detailUserRepository.getByUserIdId(userId);

        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess", detailUser);
        return responseData;
    }

    @Override
    public ResponseData<Object> updateBalance(Integer userId, DetailUserDto detailUserDto) {
        Optional <DetailUser> dOptional = detailUserRepository.findByUserIdId(userId);

        if (dOptional.isPresent()){
            DetailUser detailUser = dOptional.get();

            detailUser.setBalance(detailUserDto.getBalance());

            detailUserRepository.save(detailUser);

            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess",detailUser);
            return responseData;
        }else{
            responseData =new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "not found", null);
            return responseData;
        }
    }

     @Override
    public ResponseData<Object> getAllContact() {
        List <DetailUser> detailUsers = detailUserRepository.findAll();
        List<DetailUserDto> detailUserDtos = new ArrayList<>();

        for (int i=0; i<detailUsers.size();i++) {
          detailUserDtos.add(modelMapper.map(detailUsers.get(i), DetailUserDto.class));
        }
        responseData = new ResponseData<Object>(HttpStatus.FOUND.value(), "data ditemukan", detailUserDtos);
        return responseData;
    }
}