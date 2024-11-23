package com.ss.lms.services;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.dto.UserDTO;

import java.util.List;

public interface IssueRecordService {
    List<IssueRecordDTO> getAll();

    List<IssueRecordDTO> getAllByUserId(int userId);

    List<IssueRecordDTO> getAllByLibrarianId(int librarianId);

    IssueRecordDTO getById(int id);

    IssueRecordDTO create(IssueRecordDTO issueRecordDTO);

    IssueRecordDTO update(IssueRecordDTO issueRecordDTO);

    void deleteById(int id);
}
