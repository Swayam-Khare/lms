package com.ss.lms.services.Impl;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.entity.IssueRecord;
import com.ss.lms.mapper.IssueRecordMapper;
import com.ss.lms.repository.IssueRecordRepository;
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

    public IssueRecordServiceImpl(IssueRecordRepository issueRecordRepository, @Lazy IssueRecordMapper issueRecordMapper) {
        this.issueRecordRepository = issueRecordRepository;
        this.issueRecordMapper = issueRecordMapper;
    }

    @Override
    public List<IssueRecordDTO> getAll() {

        return issueRecordRepository.findAll()
                .stream()
                .map(issueRecordMapper::toDTO)
                .toList();
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

        IssueRecord issueRecord = issueRecordRepository.save(
                issueRecordMapper.toEntity(issueRecordDTO)
        );
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
            // TODO: throw custom exception "Issue Record not found"
            return;
        }

        issueRecordRepository.deleteById(id);
    }
}
