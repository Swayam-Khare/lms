package com.ss.lms.rest;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.dto.UserDTO;
import com.ss.lms.services.IssueRecordService;
import com.ss.lms.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/issue-record")
@CrossOrigin(origins = "http://localhost:5173")
public class IssueRecordRestController {

    private final IssueRecordService issueRecordService;

    @Autowired
    public IssueRecordRestController(IssueRecordService issueRecordService) {
        this.issueRecordService = issueRecordService;
    }

    @GetMapping("/")
    public List<IssueRecordDTO> getAll() {
        return issueRecordService.getAll();
    }

    @GetMapping("/{id}")
    public IssueRecordDTO getIssueRecordById(@PathVariable int id) {
        return issueRecordService.getById(id);
    }

    @PostMapping("/")
    public IssueRecordDTO create(@Valid @RequestBody IssueRecordDTO issueRecordDTO) {
        return issueRecordService.create(issueRecordDTO);
    }

    @PutMapping("/")
    public IssueRecordDTO update(@Valid @RequestBody IssueRecordDTO issueRecordDTO) {
        return issueRecordService.update(issueRecordDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        issueRecordService.deleteById(id);
    }
}