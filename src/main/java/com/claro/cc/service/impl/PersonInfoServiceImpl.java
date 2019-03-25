package com.claro.cc.service.impl;

import com.claro.cc.service.PersonInfoService;
import com.claro.cc.service.dto.PersonFullDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonInfoServiceImpl implements PersonInfoService {
    @Override
    public PersonFullDTO save(PersonFullDTO personFullDTO) {
        return null;
    }
}
