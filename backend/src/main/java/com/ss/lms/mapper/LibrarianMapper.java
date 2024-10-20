package com.ss.lms.mapper;

import com.ss.lms.dto.LibrarianDTO;
import com.ss.lms.entity.Librarian;
import org.springframework.stereotype.Component;

@Component
public class LibrarianMapper {

    private final AddressMapper addressMapper;
    private final PhoneNumberMapper phoneNumberMapper;

    public LibrarianMapper(AddressMapper addressMapper, PhoneNumberMapper phoneNumberMapper) {
        this.addressMapper = addressMapper;
        this.phoneNumberMapper = phoneNumberMapper;
    }

    public LibrarianDTO toDTO(Librarian librarian) {
        LibrarianDTO librarianDTO = new LibrarianDTO(
                librarian.getId(),
                librarian.getFirstName(),
                librarian.getEmail(),
                librarian.getLastName(),
                null,
                null,
                librarian.getPassword()
        );

        librarianDTO.setAddress(
                librarian.getAddress() != null ?
                        addressMapper.toDTO(librarian.getAddress()) :
                        null
        );

        librarianDTO.setPhoneNumber(
                librarian.getPhoneNumber() != null ?
                        librarian.getPhoneNumber().stream().map(phoneNumberMapper::toDTO).toList() :
                        null
        );

        return librarianDTO;
    }

    public Librarian toEntity(LibrarianDTO librarianDTO) {
        Librarian librarian = new Librarian(
                librarianDTO.getFirstName(),
                librarianDTO.getLastName(),
                librarianDTO.getEmail(),
                librarianDTO.getPassword()
        );

        librarian.setId(librarianDTO.getId());

        librarian.setAddress(
                librarianDTO.getAddress() != null ?
                        addressMapper.toEntity(librarianDTO.getAddress()) :
                        null
        );

        librarian.setPhoneNumber(
                librarianDTO.getPhoneNumber() != null ?
                        librarianDTO.getPhoneNumber().stream().map(phoneNumberMapper::toEntity).toList() :
                        null
        );

        return librarian;
    }
}
