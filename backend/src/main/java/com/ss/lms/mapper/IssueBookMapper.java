package com.ss.lms.mapper;

import com.ss.lms.dto.IssueBookDTO;
import com.ss.lms.entity.IssueBook;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class IssueBookMapper {

    public IssueBookDTO toDTO(IssueBook issueBook) {

        return new IssueBookDTO(
                issueBook.getId(),
                issueBook.getIsbnNumber(),
                null
        );
    }

    public IssueBook toEntity(IssueBookDTO dto) {
        IssueBook issueBook = new IssueBook(
                dto.getIsbnNumber()
        );

        issueBook.setId(dto.getId());

        return issueBook;
    }
}
