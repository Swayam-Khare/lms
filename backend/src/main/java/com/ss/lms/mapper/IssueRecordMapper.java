package com.ss.lms.mapper;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.entity.IssueRecord;
import org.springframework.stereotype.Component;

@Component
public class IssueRecordMapper {

    private final UserMapper userMapper;
    private final LibrarianMapper librarianMapper;

    public IssueRecordMapper(UserMapper userMapper, LibrarianMapper librarianMapper) {
        this.userMapper = userMapper;
        this.librarianMapper = librarianMapper;
    }

    public IssueRecordDTO toDTO(IssueRecord issueRecord) {

        return new IssueRecordDTO(
                issueRecord.getId(),
                issueRecord.getIssueDate(),
                issueRecord.getDueDate(),
                issueRecord.isReturned(),
                userMapper.toDTO(issueRecord.getUser()),
                librarianMapper.toDTO(issueRecord.getLibrarian()),
                null
        );
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

        issueRecord.setIssueBook(null);

        return issueRecord;
    }
}
