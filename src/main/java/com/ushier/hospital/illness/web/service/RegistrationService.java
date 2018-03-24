package com.ushier.hospital.illness.web.service;

import com.ushier.hospital.illness.web.entity.RegistrationEntity;

import java.util.List;

public interface RegistrationService {

    int delById(Integer id);

    com.ushier.hospital.illness.web.dto.RegistrationDTO queryByCode(String code);

}
