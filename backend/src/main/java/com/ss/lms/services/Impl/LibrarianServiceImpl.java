package com.ss.lms.services.Impl;

import com.ss.lms.dto.AddressDTO;
import com.ss.lms.dto.LibrarianDTO;
import com.ss.lms.entity.Address;
import com.ss.lms.entity.Librarian;
import com.ss.lms.exception.CustomEntityNotFoundException;
import com.ss.lms.mapper.AddressMapper;
import com.ss.lms.mapper.LibrarianMapper;
import com.ss.lms.repository.LibrarianRepository;
import com.ss.lms.services.LibrarianService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    private static final Logger log = Logger.getLogger("Librarian Service Impl");

    private final LibrarianRepository librarianRepository;
    private final LibrarianMapper librarianMapper;
    private final AddressMapper addressMapper;

    public LibrarianServiceImpl(LibrarianRepository librarianRepository, LibrarianMapper librarianMapper, AddressMapper addressMapper) {
        this.librarianRepository = librarianRepository;
        this.librarianMapper = librarianMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<LibrarianDTO> getAll() {

        return librarianRepository.findAll()
                .stream()
                .map(librarianMapper::toDTO)
                .toList();
    }

    @Override
    public LibrarianDTO getById(int id) {

        return librarianMapper.toDTO(Objects.requireNonNull(
                librarianRepository
                        .findById(id)
                        .orElse(null),
                "No Librarian found with id: " + id
        ));
    }

    @Override
    public LibrarianDTO create(LibrarianDTO librarianDTO) {

        Librarian librarian = librarianRepository.save(
                librarianMapper.toEntity(librarianDTO)
        );
        return librarianMapper.toDTO(librarian);
    }

    @Override
    public LibrarianDTO update(LibrarianDTO librarianDTO) {

        Librarian librarian = librarianRepository
                .findById(librarianDTO.getId())
                .orElse(null);

        if (librarian == null) {
            throw new CustomEntityNotFoundException("Librarian not found");
        }

        log.info(librarianDTO.toString());

        librarian.setFirstName(librarianDTO.getFirstName() == null ? librarian.getFirstName() : librarianDTO.getFirstName());
        librarian.setLastName(librarianDTO.getLastName() == null ? librarian.getLastName() : librarianDTO.getLastName());
        librarian.setPassword(librarianDTO.getPassword() == null ? librarian.getPassword() : librarianDTO.getPassword());
        librarian.setEmail(librarianDTO.getEmail() == null ? librarian.getEmail() : librarianDTO.getEmail());

        AddressDTO addressDTO = librarianDTO.getAddress();
        Address address = librarian.getAddress();

        if (address == null) {
            address = new Address();
        }

        if (addressDTO == null) {
            librarian.setAddress(
                    null
            );
        }
        else {
            address.setLane1(addressDTO.getLane1() == null ? address.getLane1() : addressDTO.getLane1());
            address.setLane2(addressDTO.getLane2() == null ? address.getLane2() : addressDTO.getLane2());
            address.setCity(addressDTO.getCity() == null ? address.getCity() : addressDTO.getCity());
            address.setState(addressDTO.getState() == null ? address.getState() : addressDTO.getState());
            address.setCountry(addressDTO.getCountry() == null ? address.getCountry() : addressDTO.getCountry());
            address.setPincode(addressDTO.getPincode() <= 0 ? address.getPincode() : addressDTO.getPincode());
            address.setPhoneNumber(addressDTO.getPhoneNumber() == null ? address.getPhoneNumber() : addressDTO.getPhoneNumber());

            librarian.setAddress(
                    address
            );
        }

        LibrarianDTO responseDTO = librarianMapper.toDTO(
                librarianRepository.save(librarian)
        );

        responseDTO.setPassword(null);
        return responseDTO;
    }

    @Override
    public void deleteById(int id) {

        Librarian librarian = librarianRepository
                .findById(id)
                .orElse(null);

        if (librarian == null) {
            // TODO: throw custom exception "Librarian not found"
            return;
        }

        librarianRepository.deleteById(id);
    }
}
