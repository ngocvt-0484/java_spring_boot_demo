package ngocvt.local.ngocvt.helpers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ngocvt.local.ngocvt.modules.users.services.impl.CustomUserDetailsService;
import ngocvt.local.ngocvt.modules.users.services.impl.UserService;
import ngocvt.local.ngocvt.services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class jwtAuthFilter extends org.springframework.web.filter.OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService CustomUserDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(jwtAuthFilter.class);

    public jwtAuthFilter(JwtService jwtService, CustomUserDetailsService CustomUserDetailsService) {
        this.jwtService = jwtService;
        this.CustomUserDetailsService = CustomUserDetailsService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain
                                ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userId;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.error("Missing or invalid Authorization header");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userId = jwtService.extractUserName(jwt);
        logger.info("userId: {}", userId);
        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = CustomUserDetailsService.loadUserByUsername(userId);
            logger.info(userDetails.getUsername());
        }
}}
