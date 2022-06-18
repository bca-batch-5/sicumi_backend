package com.sicumi.project.sicumi.serivice;

import java.util.Optional;

import javax.transaction.Transactional;

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
    
}
