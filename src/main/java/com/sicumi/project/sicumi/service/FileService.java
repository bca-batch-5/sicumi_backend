package com.sicumi.project.sicumi.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sicumi.project.sicumi.exception.CustomNullException;
import com.sicumi.project.sicumi.exception.CustomUnauthorizedException;
import com.sicumi.project.sicumi.exception.FileStorageException;
import com.sicumi.project.sicumi.model.dto.ResponseData;

public interface FileService {
        ResponseData<?> uploadFile(MultipartFile file, int id)
                        throws FileStorageException, CustomNullException, CustomUnauthorizedException;

        ResponseData<Map<String, Object>> loadFile(String filename, HttpServletRequest request)
                        throws CustomNullException, MalformedURLException, IOException;
}
