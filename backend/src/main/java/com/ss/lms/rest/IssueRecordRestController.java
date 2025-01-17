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

    @GetMapping("/librarian/{id}")
    public List<IssueRecordDTO> getAllByLibrarianId(@RequestParam(name = "user", required = false) String user, @PathVariable int id) {

        if (user != null) {
            return issueRecordService.searchByUser(user, id);
        }

        return issueRecordService.getAllByLibrarianId(id);
    }

    @GetMapping("/user/{id}")
    public List<IssueRecordDTO> getAllByUserId(@RequestParam("librarian") String librarian, @PathVariable int id) {

        if (librarian != null) {
            return issueRecordService.searchByLibrarian(librarian, id);
        }

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