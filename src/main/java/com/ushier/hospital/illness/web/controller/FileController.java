package com.ushier.hospital.illness.web.controller;

import com.ushier.hospital.illness.web.bean.LayUIUploadMessageBean;
import com.ushier.hospital.illness.web.global.Global;
import com.ushier.hospital.illness.web.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public LayUIUploadMessageBean uploadVideo(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletRequest request){
        return this.uploadService.upload(multipartFile, request);
    }

}
