package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.bean.LayUIUploadMessageBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UploadService {

    LayUIUploadMessageBean upload(MultipartFile multipartFile, HttpServletRequest request);
}
