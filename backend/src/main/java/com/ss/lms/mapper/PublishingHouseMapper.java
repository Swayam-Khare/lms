package com.ss.lms.mapper;

import com.ss.lms.dto.PublishingHouseDTO;
import com.ss.lms.entity.PublishingHouse;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseMapper {

    private final AddressMapper addressMapper;
    private final BookMapper bookMapper;

    public PublishingHouseMapper(AddressMapper addressMapper, BookMapper bookMapper) {
        this.addressMapper = addressMapper;
        this.bookMapper = bookMapper;
    }

    public PublishingHouseDTO toDTO(PublishingHouse publishingHouse) {
        PublishingHouseDTO publishingHouseDTO = new PublishingHouseDTO(
                publishingHouse.getId(),
                publishingHouse.getName(),
                publishingHouse.getEmail(),
                addressMapper.toDTO(publishingHouse.getAddress()),
                null
        );

        publishingHouseDTO.setBook(
                publishingHouse.getBook() != null ?
                        publishingHouse.getBook().stream().map(bookMapper::toDTO).toList() :
                        null
        );

        return publishingHouseDTO;
    }

    public PublishingHouse toEntity(PublishingHouseDTO dto) {
        PublishingHouse entity = new PublishingHouse(
                dto.getName(),
                dto.getEmail()
        );

        entity.setId(dto.getId());

        entity.setAddress(
                dto.getAddress() != null ?
                        addressMapper.toEntity(dto.getAddress()) :
                        null
        );

        entity.setBook(
                dto.getBook() != null ?
                        dto.getBook().stream().map(bookMapper::toEntity).toList() :
                        null
        );

        return entity;
    }
}
