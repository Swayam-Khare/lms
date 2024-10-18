package com.ss.lms.mapper;

import com.ss.lms.dto.IssueBookDTO;
import com.ss.lms.entity.IssueBook;
import org.springframework.stereotype.Component;

@Component
public class IssueBookMapper {

    private final IssueRecordMapper issueRecordMapper;
    private final BookCopyMapper bookCopyMapper;

    public IssueBookMapper(IssueRecordMapper issueRecordMapper, BookCopyMapper bookCopyMapper) {
        this.issueRecordMapper = issueRecordMapper;
        this.bookCopyMapper = bookCopyMapper;
    }

    public IssueBookDTO toDTO(IssueBook issueBook) {
        IssueBookDTO dto = new IssueBookDTO(
                issueBook.getId(),
                issueBook.getIsbnNumber(),
                issueBook.getFine(),
                issueBook.isReturned(),
                null,
                null
        );

        dto.setIssueRecord(
                issueBook.getIssueRecord() != null ?
                        issueRecordMapper.toDTO(issueBook.getIssueRecord()) :
                        null
        );

        dto.setBookCopy(
                issueBook.getBookCopy() != null ?
                        bookCopyMapper.toDTO(issueBook.getBookCopy()) :
                        null
        );

        return dto;
    }

    public IssueBook toEntity(IssueBookDTO dto) {
        IssueBook issueBook = new IssueBook(
                dto.getIsbnNumber(),
                dto.getFine(),
                dto.isReturned()
        );

        issueBook.setId(dto.getId());

        issueBook.setIssueRecord(
                dto.getIssueRecord() != null ?
                        issueRecordMapper.toEntity(dto.getIssueRecord()) :
                        null
        );

        issueBook.setBookCopy(
                dto.getBookCopy() != null ?
                        bookCopyMapper.toEntity(dto.getBookCopy()) :
                        null
        );

        return issueBook;
    }
}
