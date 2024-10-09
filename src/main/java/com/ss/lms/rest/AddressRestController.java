package com.ss.lms.rest;

import com.ss.lms.dto.AddressDTO;
import com.ss.lms.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "http://localhost:5173")
public class AddressRestController {

    private final AddressService addressService;

    @Autowired
    public AddressRestController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public List<AddressDTO> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public AddressDTO getAddressById(@PathVariable int id) {
        return addressService.getById(id);
    }

    @PostMapping("/")
    public AddressDTO create(@Valid @RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

    @PutMapping("/")
    public AddressDTO update(@Valid @RequestBody AddressDTO addressDTO) {
        return addressService.update(addressDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        addressService.deleteById(id);
    }
}