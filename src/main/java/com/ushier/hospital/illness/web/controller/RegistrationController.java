package com.ushier.hospital.illness.web.controller;

import com.ushier.hospital.illness.web.bean.ResponseMessageBean;
import com.ushier.hospital.illness.web.dto.RegistrationDTO;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ResponseMessageBean<?> queryByCode(@PathVariable(value = "code") String code){
        ResponseMessageBean<RegistrationDTO> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        RegistrationDTO dto = service.queryByCode(code);
        if(null == dto){
            bean.setStatus(ServerCode.RETURN_FAIL);
            bean.setMsg("挂号信息不存在");
        }
        bean.setData(dto);
        return bean;
    }
}
