package com.ss.lms.mapper;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;
    private final IssueRecordMapper issueRecordMapper;
    private final PhoneNumberMapper phoneNumberMapper;

    public UserMapper(AddressMapper addressMapper, IssueRecordMapper issueRecordMapper, PhoneNumberMapper phoneNumberMapper) {
        this.addressMapper = addressMapper;
        this.issueRecordMapper = issueRecordMapper;
        this.phoneNumberMapper = phoneNumberMapper;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO =  new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getJoinDate(),
                user.getDueDate(),
                addressMapper.toDTO(user.getAddress()),
                null,
                null
        );

//        userDTO.setIssueRecord(user.getIssueRecord().stream().map(issueRecordMapper::toDTO).toList());
//        userDTO.setPhoneNumber(user.getPhoneNumber().stream().map(phoneNumberMapper::toDTO).toList());

        userDTO.setIssueRecord(null);
        userDTO.setPhoneNumber(null);

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setJoinDate(userDTO.getJoinDate());
        user.setDueDate(userDTO.getDueDate());
        user.setAddress(addressMapper.toEntity(userDTO.getAddress()));
//        user.setIssueRecord(userDTO.getIssueRecord().stream().map(issueRecordMapper::toEntity).toList());
//        user.setPhoneNumber(userDTO.getPhoneNumber().stream().map(phoneNumberMapper::toEntity).toList());

        user.setIssueRecord(null);
        user.setPhoneNumber(null);

        return user;
    }
}
