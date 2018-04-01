package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.dto.RegistrationDTO;

public interface RegistrationService {

    int delById(Integer id);

    RegistrationDTO queryByCode(String code);

}
