package com.jobportal.jobportal.security;

import com.jobportal.jobportal.service.AuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


/**
 * JSON Web Token filter.
 *
 * @since 13.03.2021
 */
@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final int TOKEN_LENGTH = 7;

    private final AuthenticationService authenticationService;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     *
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(AUTHORIZATION);
        String email = null;
        String jwtToken = null;
        if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith(BEARER)) {
            jwtToken = requestTokenHeader.substring(TOKEN_LENGTH);
            email = getEmailFromToken(jwtToken);
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        if (Objects.nonNull(email) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = authenticationService.loadUserByUsername(email);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                setAuthenticationToken(request, userDetails);
            }
        }
        chain.doFilter(request, response);
    }

    private void setAuthenticationToken(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private String getEmailFromToken(String jwtToken) {
        String email = null;
        try {
            email = jwtTokenUtil.getEmailFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            logger.error("Unable to get JWT Token: " + e);
        } catch (ExpiredJwtException e) {
            logger.error("JWT Token has expired");
        }
        return email;
    }
}
