package com.ss.lms.filter;

import com.ss.lms.services.Impl.JWTService;
import com.ss.lms.services.Impl.MyLibrarianDetailsService;
import com.ss.lms.services.Impl.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = Logger.getLogger("JWT Filter");

    private final JWTService jwtService;

    private final ApplicationContext context;

    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public JwtFilter(JWTService jwtService, ApplicationContext context, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtService = jwtService;
        this.context = context;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        String role = null;

        try {
            if (authHeader != null && authHeader.startsWith("Bearer")) {
                token = authHeader.split(" ")[1];
                username = jwtService.extractUsername(token);
                role = jwtService.extractRole(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = null;
                if (role.equals("LIBRARIAN")) {
                    userDetails = context.getBean(MyLibrarianDetailsService.class).loadUserByUsername(username);
                }
                else {
                    userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
                }

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e){
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
