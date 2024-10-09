package com.ss.lms.services.Impl;

import com.ss.lms.dto.PhoneNumberDTO;
import com.ss.lms.entity.PhoneNumber;
import com.ss.lms.mapper.PhoneNumberMapper;
import com.ss.lms.repository.PhoneNumberRepository;
import com.ss.lms.services.PhoneNumberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private static final Logger log = Logger.getLogger("Phone Number Service Impl");

    private final PhoneNumberRepository phoneNumberRepository;
    private final PhoneNumberMapper phoneNumberMapper;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository, PhoneNumberMapper phoneNumberMapper) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.phoneNumberMapper = phoneNumberMapper;
    }

    @Override
    public List<PhoneNumberDTO> getAll() {

        return phoneNumberRepository.findAll()
                .stream()
                .map(phoneNumberMapper::toDTO)
                .toList();
    }

    @Override
    public PhoneNumberDTO getById(int id) {

        return phoneNumberMapper.toDTO(Objects.requireNonNull(
                phoneNumberRepository
                        .findById(id)
                        .orElse(null),
                "No Phone Number found with id: " + id
        ));
    }

    @Override
    public PhoneNumberDTO create(PhoneNumberDTO phoneNumberDTO) {

        PhoneNumber phoneNumber = phoneNumberRepository.save(
                phoneNumberMapper.toEntity(phoneNumberDTO)
        );
        return phoneNumberMapper.toDTO(phoneNumber);
    }

    @Override
    public PhoneNumberDTO update(PhoneNumberDTO phoneNumberDTO) {

        PhoneNumber phoneNumber = phoneNumberRepository
                .findById(phoneNumberDTO.getId())
                .orElse(null);

        if (phoneNumber == null) {
            // TODO: throw custom exception "Phone Number not found"
            return null;
        }

        phoneNumber = phoneNumberMapper.toEntity(phoneNumberDTO);

        return phoneNumberMapper.toDTO(
                phoneNumberRepository.save(phoneNumber)
        );
    }

    @Override
    public void deleteById(int id) {

        PhoneNumber phoneNumber = phoneNumberRepository
                .findById(id)
                .orElse(null);

        if (phoneNumber == null) {
            // TODO: throw custom exception "Phone Number not found"
            return;
        }

        phoneNumberRepository.deleteById(id);
    }
}
