package com.ss.lms.services;

import com.ss.lms.dto.IssueRecordDTO;

import java.util.List;

public interface IssueRecordService {
    List<IssueRecordDTO> getAll();

    IssueRecordDTO getById(int id);

    IssueRecordDTO create(IssueRecordDTO issueRecordDTO);

    IssueRecordDTO update(IssueRecordDTO issueRecordDTO);

    void deleteById(int id);
}
