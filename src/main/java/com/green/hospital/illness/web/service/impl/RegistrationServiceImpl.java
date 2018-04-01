package com.green.hospital.illness.web.service.impl;

import com.green.hospital.illness.web.dto.RegistrationDTO;
import com.green.hospital.illness.web.mapper.RegistrationMapper;
import com.green.hospital.illness.web.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private RegistrationMapper mapper;


    @Override
    public int delById(Integer id) {
        return mapper.delById(id);
    }

    @Override
    public RegistrationDTO queryByCode(String code) {
        return mapper.queryByCode(code);
    }
}
