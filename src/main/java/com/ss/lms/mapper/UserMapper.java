package com.ss.lms.mapper;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;
    private final IssueRecordMapper issueRecordMapper;
    private final PhoneNumberMapper phoneNumberMapper;

    public UserMapper(AddressMapper addressMapper, @Lazy IssueRecordMapper issueRecordMapper, PhoneNumberMapper phoneNumberMapper) {
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
                null,
                null,
                null
        );

        userDTO.setAddress(
                user.getAddress() != null ?
                        addressMapper.toDTO(user.getAddress()) :
                        null
        );

        userDTO.setIssueRecord(
                user.getIssueRecord() != null ?
                        user.getIssueRecord()
                                .stream()
                                .map(issueRecordMapper::toDTO)
                                .toList() :
                        null);

        userDTO.setPhoneNumber(
                user.getPhoneNumber() != null ?
                        user.getPhoneNumber().stream().map(phoneNumberMapper::toDTO).toList() :
                        null);


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
        user.setAddress(
                userDTO.getAddress() != null ?
                        addressMapper.toEntity(userDTO.getAddress()) :
                        null
        );

        user.setIssueRecord(userDTO.getIssueRecord() != null ?
                userDTO.getIssueRecord().stream().map(issueRecordMapper::toEntity).toList() :
                null);

        user.setPhoneNumber(userDTO.getPhoneNumber() != null ?
                userDTO.getPhoneNumber().stream().map(phoneNumberMapper::toEntity).toList() :
                null);


        return user;
    }
}
