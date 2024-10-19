package com.ss.lms.mapper;

import com.ss.lms.dto.BookCopyDTO;
import com.ss.lms.entity.BookCopy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {

    private final BookMapper bookMapper;

    public BookCopyMapper(@Lazy BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public BookCopyDTO toDTO(BookCopy bookCopy) {
        BookCopyDTO dto = new BookCopyDTO(
                bookCopy.getCopyId(),
                bookCopy.isAvailable(),
                null
        );

        dto.setBook(
                bookCopy.getBook() != null ?
                        bookMapper.toDTO(bookCopy.getBook()) :
                        null
        );

        return dto;
    }

    public BookCopy toEntity(BookCopyDTO dto) {
        BookCopy bookCopy = new BookCopy(
                dto.isAvailable()
        );

        bookCopy.setCopyId(dto.getCopyId());

        bookCopy.setBook(
                dto.getBook() != null ?
                        bookMapper.toEntity(dto.getBook()) :
                        null
        );

        return bookCopy;
    }
}
