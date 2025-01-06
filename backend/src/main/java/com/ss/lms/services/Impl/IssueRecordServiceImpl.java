package com.ss.lms.services.Impl;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.entity.IssueBook;
import com.ss.lms.entity.IssueRecord;
import com.ss.lms.entity.Librarian;
import com.ss.lms.entity.User;
import com.ss.lms.exception.CustomEntityNotFoundException;
import com.ss.lms.mapper.IssueBookMapper;
import com.ss.lms.mapper.IssueRecordMapper;
import com.ss.lms.mapper.LibrarianMapper;
import com.ss.lms.repository.IssueBookRepository;
import com.ss.lms.repository.IssueRecordRepository;
import com.ss.lms.repository.LibrarianRepository;
import com.ss.lms.repository.UserRepository;
import com.ss.lms.services.IssueRecordService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


@Service
public class IssueRecordServiceImpl implements IssueRecordService {

    private static final Logger log = Logger.getLogger("Issue Record Service Impl");

    private final IssueRecordRepository issueRecordRepository;
    private final IssueRecordMapper issueRecordMapper;
    private final LibrarianRepository librarianRepository;
    private final UserRepository userRepository;
    private final IssueBookMapper issueBookMapper;
    private final IssueBookRepository issueBookRepository;

    public IssueRecordServiceImpl(IssueRecordRepository issueRecordRepository, @Lazy IssueRecordMapper issueRecordMapper, LibrarianRepository librarianRepository, UserRepository userRepository, IssueBookMapper issueBookMapper, IssueBookRepository issueBookRepository) {
        this.issueRecordRepository = issueRecordRepository;
        this.issueRecordMapper = issueRecordMapper;
        this.librarianRepository = librarianRepository;
        this.userRepository = userRepository;
        this.issueBookMapper = issueBookMapper;
        this.issueBookRepository = issueBookRepository;
    }

    @Override
    public List<IssueRecordDTO> getAll() {
        List<IssueRecord> issueRecords = issueRecordRepository.findAll();
        setIssueBooks(issueRecords);
        return issueRecords.stream().map(issueRecordMapper::toDTO).toList();
    }

    @Override
    public List<IssueRecordDTO> getAllByUserId(int userId) {
        List<IssueRecord> issueRecords = issueRecordRepository.findIssueRecordByUser_Id(userId);
        setIssueBooks(issueRecords);
        return issueRecords.stream().map(issueRecordMapper::toDTO).toList();
    }

    @Override
    public List<IssueRecordDTO> getAllByLibrarianId(int librarianId) {
        List<IssueRecord> issueRecords = issueRecordRepository.findIssueRecordByLibrarian_Id(librarianId);
        setIssueBooks(issueRecords);
        return issueRecords.stream().map(issueRecordMapper::toDTO).toList();
    }

    @Override
    public IssueRecordDTO getById(int id) {

        return issueRecordMapper.toDTO(Objects.requireNonNull(
                issueRecordRepository
                        .findById(id)
                        .orElse(null),
                "No Issue Record found with id: " + id
        ));
    }

    @Override
    public IssueRecordDTO create(IssueRecordDTO issueRecordDTO) {
        int librarianId = issueRecordDTO.getLibrarian().getId();
        int userId = issueRecordDTO.getUser().getId();

        Librarian librarian = librarianRepository.findById(librarianId).orElseThrow(() ->
                new CustomEntityNotFoundException("Librarian not found with id: " + librarianId));

        User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomEntityNotFoundException("User not found with id: " + userId));

        IssueRecord issueRecord = issueRecordMapper.toEntity(issueRecordDTO);

        issueRecord.setIssueBook(null);
        issueRecord.setLibrarian(librarian);
        issueRecord.setUser(user);

        issueRecord = issueRecordRepository.save(
                issueRecord
        );

        List<IssueBook> issueBooks =  issueRecordDTO
                .getIssueBook()
                .stream()
                .map(issueBookMapper::toEntity)
                .toList();

        IssueRecord finalIssueRecord = issueRecord;
        issueBooks.forEach((book) -> {
            book.setIssueRecord(finalIssueRecord);
        });

        List<IssueBook> createdBooks =  issueBookRepository.saveAll(issueBooks);
        issueRecord.setIssueBook(createdBooks);

        return issueRecordMapper.toDTO(issueRecord);
    }

    @Override
    public IssueRecordDTO update(IssueRecordDTO issueRecordDTO) {

        IssueRecord issueRecord = issueRecordRepository
                .findById(issueRecordDTO.getId())
                .orElse(null);

        if (issueRecord == null) {
            // TODO: throw custom exception "Issue Record not found"
            return null;
        }

        issueRecord = issueRecordMapper.toEntity(issueRecordDTO);

        return issueRecordMapper.toDTO(
                issueRecordRepository.save(issueRecord)
        );
    }

    @Override
    public void deleteById(int id) {

        IssueRecord issueRecord = issueRecordRepository
                .findById(id)
                .orElse(null);

        if (issueRecord == null) {
            throw new CustomEntityNotFoundException("Issue Record not found with id: " + id);
        }

        issueRecord.setLibrarian(null);
        issueRecord.setUser(null);
        issueRecord = issueRecordRepository.saveAndFlush(issueRecord);
        issueRecordRepository.delete(issueRecord);
    }

    private void setIssueBooks(List<IssueRecord> issueRecords) {
        for (IssueRecord record : issueRecords) {
            List<IssueBook> issueBooks = issueBookRepository.findIssueBookByIssueRecord(record);
            record.setIssueBook(issueBooks);
        }
    }
}
