package com.ss.lms.mapper;

import com.ss.lms.dto.PublishingHouseDTO;
import com.ss.lms.entity.PublishingHouse;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseMapper {

    private final AddressMapper addressMapper;
    private final BookMapper bookMapper;
    private final PhoneNumberMapper phoneNumberMapper;

    public PublishingHouseMapper(AddressMapper addressMapper, BookMapper bookMapper, PhoneNumberMapper phoneNumberMapper) {
        this.addressMapper = addressMapper;
        this.bookMapper = bookMapper;
        this.phoneNumberMapper = phoneNumberMapper;
    }

    public PublishingHouseDTO toDTO(PublishingHouse publishingHouse) {
        PublishingHouseDTO publishingHouseDTO = new PublishingHouseDTO(
                publishingHouse.getId(),
                publishingHouse.getName(),
                publishingHouse.getEmail(),
                addressMapper.toDTO(publishingHouse.getAddress()),
                null,
                null
        );

        publishingHouseDTO.setBook(
                publishingHouse.getBook() != null ?
                        publishingHouse.getBook().stream().map(bookMapper::toDTO).toList() :
                        null
        );

        publishingHouseDTO.setPhoneNumber(
                publishingHouse.getPhoneNumber() != null ?
                        publishingHouse.getPhoneNumber().stream().map(phoneNumberMapper::toDTO).toList() :
                        null
        );

        return publishingHouseDTO;
    }

    public PublishingHouse toEntity(PublishingHouseDTO publishingHouseDTO) {
        PublishingHouse publishingHouse = new PublishingHouse(
                publishingHouseDTO.getName(),
                publishingHouseDTO.getEmail()
        );

        publishingHouse.setId(publishingHouseDTO.getId());

        publishingHouse.setAddress(
                addressMapper.toEntity(publishingHouseDTO.getAddress())
        );

        publishingHouse.setBook(
                publishingHouseDTO.getBook() != null ?
                        publishingHouseDTO.getBook().stream().map(bookMapper::toEntity).toList() :
                        null
        );

        publishingHouse.setPhoneNumber(
                publishingHouseDTO.getPhoneNumber() != null ?
                        publishingHouseDTO.getPhoneNumber().stream().map(phoneNumberMapper::toEntity).toList() :
                        null
        );

        return publishingHouse;
    }
}
