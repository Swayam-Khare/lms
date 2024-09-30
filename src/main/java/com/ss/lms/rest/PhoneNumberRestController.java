package com.ss.lms.rest;

import com.ss.lms.dto.PhoneNumberDTO;
import com.ss.lms.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone-number")
public class PhoneNumberRestController {

    private final PhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumberRestController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/")
    public List<PhoneNumberDTO> getAll() {
        return phoneNumberService.getAll();
    }

    @GetMapping("/{id}")
    public PhoneNumberDTO getPhoneNumberById(@PathVariable int id) {
        return phoneNumberService.getById(id);
    }

    @PostMapping("/")
    public PhoneNumberDTO create(@RequestBody PhoneNumberDTO phoneNumberDTO) {
        return phoneNumberService.create(phoneNumberDTO);
    }

    @PutMapping("/")
    public PhoneNumberDTO update(@RequestBody PhoneNumberDTO phoneNumberDTO) {
        return phoneNumberService.update(phoneNumberDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        phoneNumberService.deleteById(id);
    }
}