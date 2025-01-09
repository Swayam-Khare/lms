package com.ss.lms.services.Impl;

import com.ss.lms.dto.PublishingHouseDTO;
import com.ss.lms.entity.PublishingHouse;
import com.ss.lms.exception.CustomEntityNotFoundException;
import com.ss.lms.mapper.AddressMapper;
import com.ss.lms.mapper.BookMapper;
import com.ss.lms.mapper.PublishingHouseMapper;
import com.ss.lms.repository.PublishingHouseRepository;
import com.ss.lms.services.PublishingHouseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private static final Logger log = Logger.getLogger("Publishing House Service Impl");

    private final PublishingHouseRepository publishingHouseRepository;
    private final PublishingHouseMapper publishingHouseMapper;
    private final AddressMapper addressMapper;
    private final BookMapper bookMapper;

    public PublishingHouseServiceImpl(PublishingHouseRepository publishingHouseRepository, PublishingHouseMapper publishingHouseMapper, @Lazy AddressMapper addressMapper, @Lazy BookMapper bookMapper) {
        this.publishingHouseRepository = publishingHouseRepository;
        this.publishingHouseMapper = publishingHouseMapper;
        this.addressMapper = addressMapper;
        this.bookMapper = bookMapper;
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
    @Transactional
    public PublishingHouseDTO create(PublishingHouseDTO publishingHouseDTO) {

        PublishingHouse publishingHouse = publishingHouseRepository.save(
                publishingHouseMapper.toEntity(publishingHouseDTO)
        );
        return publishingHouseMapper.toDTO(publishingHouse);
    }

    @Override
    @Transactional
    public PublishingHouseDTO update(PublishingHouseDTO dto) {

        PublishingHouse publishingHouse = publishingHouseRepository
                .findById(dto.getId())
                .orElse(null);

        if (publishingHouse == null) {
            throw new CustomEntityNotFoundException("Publishing House not found with id: " + dto.getId());
        }

        publishingHouse.setName(dto.getName() == null ? publishingHouse.getName() : dto.getName());
        publishingHouse.setEmail(dto.getEmail() == null ? publishingHouse.getEmail() : dto.getEmail());

        publishingHouse.setAddress(
                dto.getAddress() == null ?
                        publishingHouse.getAddress() :
                        addressMapper.toEntity(dto.getAddress())
        );

        publishingHouse.setBook(
                dto.getBook() == null ?
                        publishingHouse.getBook() :
                        dto.getBook().stream().map(bookMapper::toEntity).toList()
        );

        return publishingHouseMapper.toDTO(
                publishingHouseRepository.save(publishingHouse)
        );
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        PublishingHouse publishingHouse = publishingHouseRepository
                .findById(id)
                .orElse(null);

        if (publishingHouse == null) {
            throw new CustomEntityNotFoundException("Publishing House not found with id: " + id);
        }

        publishingHouseRepository.deleteById(id);
    }
}
