package com.ss.lms.mapper;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.entity.IssueRecord;
import org.springframework.stereotype.Component;

@Component
public class IssueRecordMapper {

    private final UserMapper userMapper;
    private final LibrarianMapper librarianMapper;
    private final IssueBookMapper issueBookMapper;

    public IssueRecordMapper(UserMapper userMapper, LibrarianMapper librarianMapper, IssueBookMapper issueBookMapper) {
        this.userMapper = userMapper;
        this.librarianMapper = librarianMapper;
        this.issueBookMapper = issueBookMapper;
    }

    public IssueRecordDTO toDTO(IssueRecord issueRecord) {

        IssueRecordDTO issueRecordDTO = new IssueRecordDTO(
                issueRecord.getId(),
                issueRecord.getIssueDate(),
                issueRecord.getDueDate(),
                issueRecord.isReturned(),
                userMapper.toDTO(issueRecord.getUser()),
                librarianMapper.toDTO(issueRecord.getLibrarian()),
                null
        );

        issueRecordDTO.setIssueBook(
                issueRecord.getIssueBook() != null ?
                        issueRecord.getIssueBook().stream().map(issueBookMapper::toDTO).toList() :
                        null
        );

        return issueRecordDTO;
    }

    public IssueRecord toEntity(IssueRecordDTO issueRecordDTO) {
        IssueRecord issueRecord = new IssueRecord(
                issueRecordDTO.getIssueDate(),
                issueRecordDTO.getDueDate(),
                issueRecordDTO.isReturned()
        );

        issueRecord.setId(issueRecordDTO.getId());

        issueRecord.setUser(
                userMapper.toEntity(issueRecordDTO.getUser())
        );

        issueRecord.setLibrarian(
                librarianMapper.toEntity(issueRecordDTO.getLibrarian())
        );

        issueRecord.setIssueBook(
                issueRecordDTO.getIssueBook() != null ?
                        issueRecordDTO.getIssueBook().stream().map(issueBookMapper::toEntity).toList() :
                        null
        );

        return issueRecord;
    }
}
