package com.ss.lms.services.Impl;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.dto.UserDTO;
import com.ss.lms.dto.UserInfoResponse;
import com.ss.lms.entity.IssueBook;
import com.ss.lms.entity.IssueRecord;
import com.ss.lms.entity.User;
import com.ss.lms.entity.UserPrincipal;
import com.ss.lms.exception.CustomEntityNotFoundException;
import com.ss.lms.mapper.AddressMapper;
import com.ss.lms.mapper.IssueRecordMapper;
import com.ss.lms.mapper.UserMapper;
import com.ss.lms.repository.IssueRecordRepository;
import com.ss.lms.repository.UserRepository;
import com.ss.lms.services.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger("User Service Impl");

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final IssueRecordMapper issueRecordMapper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final IssueRecordRepository issueRecordRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, @Lazy AddressMapper addressMapper, @Lazy IssueRecordMapper issueRecordMapper, IssueRecordRepository issueRecordRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.issueRecordMapper = issueRecordMapper;
        this.issueRecordRepository = issueRecordRepository;
    }

    @Override
    public List<UserDTO> searchUsers(String searchText) {
        List<UserDTO> userDTOList =  userRepository.findUserBySearch(searchText)
                .stream()
                .map(userMapper::toDTO)
                .toList();

        userDTOList.forEach(dto -> {dto.setPassword(null);});
        return userDTOList;
    }

    @Override
    public List<UserDTO> getAll() {

        List<UserDTO> userDTOList =  userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();

        userDTOList.forEach(dto -> {dto.setPassword(null);});
        return userDTOList;
    }

    @Override
    public UserDTO getById(int id) {

        UserDTO userDTO =  userMapper.toDTO(Objects.requireNonNull(
                userRepository
                        .findById(id)
                        .orElse(null),
        "No user found with id: " + id
        ));

        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public UserDTO getSelfDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUsername());
        UserDTO userDTO = userMapper.toDTO(user);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO) {

        User user = userRepository
                .findById(userDTO.getId())
                .orElse(null);

        if (user == null) {
            throw new CustomEntityNotFoundException("User not found");
        }

        log.info(userDTO.toString());

        user.setFirstName(userDTO.getFirstName() == null ? user.getFirstName() : userDTO.getFirstName());
        user.setLastName(userDTO.getLastName() == null ? user.getLastName() : userDTO.getLastName());
        user.setEmail(userDTO.getEmail() == null ? user.getEmail() : userDTO.getEmail());
        user.setPassword(userDTO.getPassword() == null ? user.getPassword() : encoder.encode(userDTO.getPassword()));
        user.setJoinDate(userDTO.getJoinDate() == null ? user.getJoinDate() : userDTO.getJoinDate());
        user.setDueDate(userDTO.getDueDate() == null ? user.getDueDate() : userDTO.getDueDate());

        user.setAddress(
                userDTO.getAddress() == null ?
                        user.getAddress() :
                        addressMapper.toEntity(userDTO.getAddress())
        );

        user.setIssueRecord(
                userDTO.getIssueRecord() == null ?
                        user.getIssueRecord() :
                        userDTO.getIssueRecord().stream().map(issueRecordMapper::toEntity).collect(Collectors.toList())
        );

        UserDTO responseDTO =  userMapper.toDTO(
                userRepository.save(user)
        );

        responseDTO.setPassword(null);
        return responseDTO;
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        User user = userRepository
                .findById(id)
                .orElse(null);

        if (user == null) {
            throw new CustomEntityNotFoundException("User not found with the given id: " + id);
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserInfoResponse getInfo() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getEntity();

        // Total fine
        float totalFine = user.getTotalFine();
        Date membershipEndDate = user.getDueDate();

        PageRequest pageRequest = PageRequest.of(0, 5);
        List<IssueRecordDTO> latestIssueRecords = issueRecordRepository
                .findByUserOrderByDueDateDesc(user, pageRequest)
                .stream()
                .map(issueRecordMapper::toDTO)
                .toList();


        int numberOfBooksToReturn = 0;
        int totalNumberOfBooksBorrowed = 0;

        List<IssueRecord> records = user.getIssueRecord();

        for (IssueRecord record : records) {
            if (!record.isReturned()) {
                numberOfBooksToReturn++;
            }

            List<IssueBook> books = record.getIssueBook();
            totalNumberOfBooksBorrowed += books.size();
        }

        return new UserInfoResponse(
                totalFine,
                numberOfBooksToReturn,
                totalNumberOfBooksBorrowed,
                membershipEndDate,
                latestIssueRecords
        );
    }
}
