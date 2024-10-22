package com.ss.lms.mapper;

import com.ss.lms.dto.AddressDTO;
import com.ss.lms.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toDTO(Address address) {

        return new AddressDTO(
                address.getId(),
                address.getLane1(),
                address.getLane2(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPincode(),
                address.getPhoneNumber()
        );
    }

    public Address toEntity(AddressDTO addressDTO) {
        Address address = new Address(
                addressDTO.getLane1(),
                addressDTO.getLane2(),
                addressDTO.getCity(),
                addressDTO.getState(),
                addressDTO.getCountry(),
                addressDTO.getPincode(),
                addressDTO.getPhoneNumber()
        );

        address.setId(addressDTO.getId());

        return address;
    }
}
