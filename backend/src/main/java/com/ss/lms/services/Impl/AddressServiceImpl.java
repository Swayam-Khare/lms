package com.ss.lms.services.Impl;

import com.ss.lms.dto.AddressDTO;
import com.ss.lms.entity.Address;
import com.ss.lms.mapper.AddressMapper;
import com.ss.lms.repository.AddressRepository;
import com.ss.lms.services.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger log = Logger.getLogger("Address Service Impl");

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<AddressDTO> getAll() {

        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDTO)
                .toList();
    }

    @Override
    public AddressDTO getById(int id) {

        return addressMapper.toDTO(Objects.requireNonNull(
                addressRepository
                        .findById(id)
                        .orElse(null),
                "No address found with id: " + id
        ));
    }

    @Override
    public AddressDTO create(AddressDTO addressDTO) {

        Address address = addressRepository.save(
                addressMapper.toEntity(addressDTO)
        );
        return addressMapper.toDTO(address);
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {

        Address address = addressRepository
                .findById(addressDTO.getId())
                .orElse(null);

        if (address == null) {
            // TODO: throw custom exception "address not found"
            return null;
        }

        address = addressMapper.toEntity(addressDTO);

        return addressMapper.toDTO(
                addressRepository.save(address)
        );
    }

    @Override
    public void deleteById(int id) {

        Address address = addressRepository
                .findById(id)
                .orElse(null);

        if (address == null) {
            // TODO: throw custom exception "address not found"
            return;
        }

        addressRepository.deleteById(id);
    }
}
