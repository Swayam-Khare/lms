package com.ss.lms.mapper;

import com.ss.lms.dto.PublishingHouseDTO;
import com.ss.lms.entity.PublishingHouse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseMapper {

    private final AddressMapper addressMapper;
    private final ApplicationContext context;

    public PublishingHouseMapper(@Lazy AddressMapper addressMapper, ApplicationContext context) {
        this.addressMapper = addressMapper;
        this.context = context;
    }

    public PublishingHouseDTO toDTO(PublishingHouse publishingHouse) {
        PublishingHouseDTO publishingHouseDTO = new PublishingHouseDTO(
                publishingHouse.getId(),
                publishingHouse.getName(),
                publishingHouse.getEmail(),
                addressMapper.toDTO(publishingHouse.getAddress()),
                null
        );

        publishingHouseDTO.setBook(null);

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
                null
        );

        return entity;
    }
}
