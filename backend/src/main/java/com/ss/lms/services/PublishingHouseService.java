package com.ss.lms.services;

import com.ss.lms.dto.PublishingHouseDTO;

import java.util.List;

public interface PublishingHouseService {
    List<PublishingHouseDTO> getAll();

    PublishingHouseDTO getById(int id);

    PublishingHouseDTO create(PublishingHouseDTO publishingHouseDTO);

    PublishingHouseDTO update(PublishingHouseDTO publishingHouseDTO);

    void deleteById(int id);
}
