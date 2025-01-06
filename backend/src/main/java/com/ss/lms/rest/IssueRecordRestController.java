package com.ss.lms.rest;

import com.ss.lms.dto.IssueRecordDTO;
import com.ss.lms.services.IssueRecordService;
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

    @GetMapping("/librarian/{id}")
    public List<IssueRecordDTO> getAllByLibrarianId(@PathVariable int id) {
        return issueRecordService.getAllByLibrarianId(id);
    }

    @GetMapping("/user/{id}")
    public List<IssueRecordDTO> getAllByUserId(@PathVariable int id) {
        return issueRecordService.getAllByUserId(id);
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
        System.out.println(id);
        issueRecordService.deleteById(id);
    }
}