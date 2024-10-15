package com.ss.lms.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LibrarianPrincipal implements UserDetails {

    private final Librarian librarian;

    public LibrarianPrincipal(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_LIBRARIAN"));

//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_LIBRARIAN"));
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return authorities;
    }

    @Override
    public String getPassword() {
        return librarian.getPassword();
    }

    @Override
    public String getUsername() {
        return librarian.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
