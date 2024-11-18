package com.ss.lms.mapper;

import com.ss.lms.dto.IssueBookDTO;
import com.ss.lms.entity.IssueBook;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class IssueBookMapper {

    private final IssueRecordMapper issueRecordMapper;

    public IssueBookMapper(@Lazy IssueRecordMapper issueRecordMapper) {
        this.issueRecordMapper = issueRecordMapper;
    }

    public IssueBookDTO toDTO(IssueBook issueBook) {
        IssueBookDTO dto = new IssueBookDTO(
                issueBook.getId(),
                issueBook.getIsbnNumber(),
                null
        );

        dto.setIssueRecord(
                issueBook.getIssueRecord() != null ?
                        issueRecordMapper.toDTO(issueBook.getIssueRecord()) :
                        null
        );

        return dto;
    }

    public IssueBook toEntity(IssueBookDTO dto) {
        IssueBook issueBook = new IssueBook(
                dto.getIsbnNumber()
        );

        issueBook.setId(dto.getId());

        issueBook.setIssueRecord(
                dto.getIssueRecord() != null ?
                        issueRecordMapper.toEntity(dto.getIssueRecord()) :
                        null
        );

        return issueBook;
    }
}
