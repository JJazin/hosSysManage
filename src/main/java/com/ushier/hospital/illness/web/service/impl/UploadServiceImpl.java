package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUIUploadMessageBean;
import com.ushier.hospital.illness.web.bean.ResponseMessageBean;
import com.ushier.hospital.illness.web.global.Global;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.service.UploadService;
import com.ushier.hospital.illness.web.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
public class UploadServiceImpl implements UploadService {

    private Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public LayUIUploadMessageBean upload(MultipartFile multipartFile, HttpServletRequest request) {
        LayUIUploadMessageBean bean = new LayUIUploadMessageBean();
        bean.setCode(ServerCode.LAYUI_RETURN_OK);

        String path = Global.FILE_UPLOAD_PATH_OTHER;

        String originalFilename = multipartFile.getOriginalFilename();

        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());

        String savedFileName = StringUtil.uuid();

        if(Arrays.asList(Global.SUFFIX_PICTURES).contains(suffix)){
            path = Global.FILE_UPLOAD_PATH_IMAGE;
        }else if(Arrays.asList(Global.SUFFIX_VIDEOS).contains(suffix)){
            path = Global.FILE_UPLOAD_PATH_VIDEO;
        }

        try {

            String dirPath = request.getServletContext().getRealPath(path);// 存储路径
            String fileName = savedFileName + "." + suffix;// 保存文件名
            String serverPath = path + fileName;// 服务器访问路径

            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(dirPath, fileName)));
            out.write(multipartFile.getBytes());
            out.flush();
            out.close();
            bean.setData(new LayUIUploadMessageBean.Data(serverPath, fileName));
            logger.info("文件保存成功 保存路径为{} / 服务器路径为{}", dirPath + fileName, serverPath);
        } catch (IOException e) {
            e.printStackTrace();
            bean.setCode(ServerCode.LAYUI_RETURN_ERROR);
            bean.setMsg(ServerCode.RETURN_ERROR);
        }

        return bean;
    }
}
