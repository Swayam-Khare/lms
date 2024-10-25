package com.ss.lms.services.Impl;

import com.ss.lms.dto.LibrarianDTO;
import com.ss.lms.dto.UserDTO;
import com.ss.lms.entity.Librarian;
import com.ss.lms.entity.LibrarianPrincipal;
import com.ss.lms.entity.User;
import com.ss.lms.entity.UserPrincipal;
import com.ss.lms.mapper.LibrarianMapper;
import com.ss.lms.mapper.UserMapper;
import com.ss.lms.repository.LibrarianRepository;
import com.ss.lms.repository.UserRepository;
import com.ss.lms.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = Logger.getLogger("Auth Service Impl");

    private final UserRepository userRepo;
    private final LibrarianRepository librarianRepo;
    private final UserMapper userMapper;
    private final LibrarianMapper librarianMapper;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    private final ApplicationContext context;
    private final MyLibrarianDetailsService librarianDetailsService;
    private final MyUserDetailsService userDetailsService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthServiceImpl(UserRepository userRepo, LibrarianRepository librarianRepo,
                           UserMapper userMapper, LibrarianMapper librarianMapper,
                           AuthenticationManager authManager, JWTService jwtService,
                           ApplicationContext context, MyLibrarianDetailsService librarianDetailsService,
                           MyUserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.librarianRepo = librarianRepo;
        this.userMapper = userMapper;
        this.librarianMapper = librarianMapper;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.context = context;
        this.librarianDetailsService = librarianDetailsService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    public LibrarianDTO registerLibrarian(LibrarianDTO librarianDTO) {
        Librarian librarian = librarianMapper.toEntity(librarianDTO);
        librarian.setPassword(encoder.encode(librarian.getPassword()));

        return librarianMapper.toDTO(librarianRepo.save(librarian));
    }

    @Override
    public String login(String username, String password, String role) {

        if (role.equals("LIBRARIAN")) {
            context.getBean(DaoAuthenticationProvider.class).setUserDetailsService(librarianDetailsService);
        } else {
            context.getBean(DaoAuthenticationProvider.class).setUserDetailsService(userDetailsService);
        }

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(username, role);

        return "fail";
    }

    @Override
    public ResponseEntity<?> getDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GrantedAuthority[] roles = userDetails.getAuthorities().toArray(new GrantedAuthority[0]);
        String role = roles[0].getAuthority();

        if (role.equals("ROLE_LIBRARIAN")) {
            LibrarianPrincipal principal = (LibrarianPrincipal) userDetails;
            LibrarianDTO librarian = librarianMapper.toDTO(principal.getEntity());
            librarian.setPassword(null);
            return new ResponseEntity<>(librarian, HttpStatus.OK);
        }
        else {
            UserPrincipal principal = (UserPrincipal) userDetails;
            UserDTO user = userMapper.toDTO(principal.getEntity());
            user.setPassword(null);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
}
