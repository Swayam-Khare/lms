package com.ss.lms.mapper;

import com.ss.lms.dto.PhoneNumberDTO;
import com.ss.lms.entity.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberMapper {

    public PhoneNumberDTO toDTO(PhoneNumber phoneNumber) {
        return new PhoneNumberDTO(
                phoneNumber.getNumber(),
                phoneNumber.getId()
        );
    }

    public PhoneNumber toEntity(PhoneNumberDTO phoneNumberDTO) {
        PhoneNumber phoneNumber = new PhoneNumber(
                phoneNumberDTO.getNumber()
        );

        phoneNumber.setId(phoneNumberDTO.getId());

        return phoneNumber;
    }
}
