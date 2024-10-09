package com.ss.lms.services;

import com.ss.lms.dto.PhoneNumberDTO;

import java.util.List;

public interface PhoneNumberService {
    List<PhoneNumberDTO> getAll();

    PhoneNumberDTO getById(int id);

    PhoneNumberDTO create(PhoneNumberDTO phoneNumberDTO);

    PhoneNumberDTO update(PhoneNumberDTO phoneNumberDTO);

    void deleteById(int id);
}
