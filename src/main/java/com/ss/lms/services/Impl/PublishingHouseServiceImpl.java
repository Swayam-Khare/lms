package com.ss.lms.services.Impl;

import com.ss.lms.dto.PublishingHouseDTO;
import com.ss.lms.dto.UserDTO;
import com.ss.lms.entity.PublishingHouse;
import com.ss.lms.entity.User;
import com.ss.lms.mapper.PublishingHouseMapper;
import com.ss.lms.mapper.UserMapper;
import com.ss.lms.repository.PublishingHouseRepository;
import com.ss.lms.repository.UserRepository;
import com.ss.lms.services.PublishingHouseService;
import com.ss.lms.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private static final Logger log = Logger.getLogger("Publishing House Service Impl");

    private final PublishingHouseRepository publishingHouseRepository;
    private final PublishingHouseMapper publishingHouseMapper;

    public PublishingHouseServiceImpl(PublishingHouseRepository publishingHouseRepository, PublishingHouseMapper publishingHouseMapper) {
        this.publishingHouseRepository = publishingHouseRepository;
        this.publishingHouseMapper = publishingHouseMapper;
    }

    @Override
    public List<PublishingHouseDTO> getAll() {

        return publishingHouseRepository.findAll()
                .stream()
                .map(publishingHouseMapper::toDTO)
                .toList();
    }

    @Override
    public PublishingHouseDTO getById(int id) {

        return publishingHouseMapper.toDTO(Objects.requireNonNull(
                publishingHouseRepository
                        .findById(id)
                        .orElse(null),
                "No Publishing House found with id: " + id
        ));
    }

    @Override
    public PublishingHouseDTO create(PublishingHouseDTO publishingHouseDTO) {

        PublishingHouse publishingHouse = publishingHouseRepository.save(
                publishingHouseMapper.toEntity(publishingHouseDTO)
        );
        return publishingHouseMapper.toDTO(publishingHouse);
    }

    @Override
    public PublishingHouseDTO update(PublishingHouseDTO publishingHouseDTO) {

        PublishingHouse publishingHouse = publishingHouseRepository
                .findById(publishingHouseDTO.getId())
                .orElse(null);

        if (publishingHouse == null) {
            // TODO: throw custom exception "Publishing House not found"
            return null;
        }

        publishingHouse = publishingHouseMapper.toEntity(publishingHouseDTO);

        return publishingHouseMapper.toDTO(
                publishingHouseRepository.save(publishingHouse)
        );
    }

    @Override
    public void deleteById(int id) {

        PublishingHouse publishingHouse = publishingHouseRepository
                .findById(id)
                .orElse(null);

        if (publishingHouse == null) {
            // TODO: throw custom exception "Publishing House not found"
            return;
        }

        publishingHouseRepository.deleteById(id);
    }
}
