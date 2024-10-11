package com.ss.lms.services.Impl;

import com.ss.lms.entity.Librarian;
import com.ss.lms.entity.LibrarianPrincipal;
import com.ss.lms.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyLibrarianDetailsService implements UserDetailsService {

    private final LibrarianRepository librarianRepository;

    @Autowired
    public MyLibrarianDetailsService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Librarian librarian = librarianRepository.findLibrarianByEmail(username);

        if (librarian == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        return new LibrarianPrincipal(librarian);
    }
}
