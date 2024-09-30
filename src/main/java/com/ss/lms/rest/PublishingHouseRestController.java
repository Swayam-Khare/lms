package com.ss.lms.rest;

import com.ss.lms.dto.PublishingHouseDTO;
import com.ss.lms.services.PublishingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone-number")
public class PublishingHouseRestController {

    private final PublishingHouseService publishingHouseService;

    @Autowired
    public PublishingHouseRestController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping("/")
    public List<PublishingHouseDTO> getAll() {
        return publishingHouseService.getAll();
    }

    @GetMapping("/{id}")
    public PublishingHouseDTO getPublishingHouseById(@PathVariable int id) {
        return publishingHouseService.getById(id);
    }

    @PostMapping("/")
    public PublishingHouseDTO create(@RequestBody PublishingHouseDTO publishingHouseDTO) {
        return publishingHouseService.create(publishingHouseDTO);
    }

    @PutMapping("/")
    public PublishingHouseDTO update(@RequestBody PublishingHouseDTO publishingHouseDTO) {
        return publishingHouseService.update(publishingHouseDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        publishingHouseService.deleteById(id);
    }
}