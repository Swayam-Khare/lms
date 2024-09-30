package com.ss.lms.mapper;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.entity.IssueRecord;
import org.springframework.stereotype.Component;

@Component
public class IssueRecordMapper {

    private final BookMapper bookMapper;
    private final UserMapper userMapper;
    private final LibrarianMapper librarianMapper;

    public IssueRecordMapper(BookMapper bookMapper, UserMapper userMapper, LibrarianMapper librarianMapper) {
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
        this.librarianMapper = librarianMapper;
    }

    public IssueRecordDTO toDTO(IssueRecord issueRecord) {
        return new IssueRecordDTO(
                issueRecord.getId(),
                issueRecord.getIssueDate(),
                issueRecord.getDueDate(),
                bookMapper.toDTO(issueRecord.getBook()),
                userMapper.toDTO(issueRecord.getUser()),
                librarianMapper.toDTO(issueRecord.getLibrarian())
        );
    }

    public IssueRecord toEntity(IssueRecordDTO issueRecordDTO) {
        IssueRecord issueRecord = new IssueRecord(
                issueRecordDTO.getIssueDate(),
                issueRecordDTO.getDueDate()
        );

        issueRecord.setId(issueRecordDTO.getId());

        issueRecord.setBook(
                bookMapper.toEntity(issueRecordDTO.getBook())
        );

        issueRecord.setUser(
                userMapper.toEntity(issueRecordDTO.getUser())
        );

        issueRecord.setLibrarian(
                librarianMapper.toEntity(issueRecordDTO.getLibrarian())
        );

        return issueRecord;
    }
}
