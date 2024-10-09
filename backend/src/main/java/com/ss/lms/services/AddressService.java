package com.ss.lms.services;

import com.ss.lms.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAll();

    AddressDTO getById(int id);

    AddressDTO create(AddressDTO addressDTO);

    AddressDTO update(AddressDTO addressDTO);

    void deleteById(int id);
}
