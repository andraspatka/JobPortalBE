package com.jobportal.jobportal.config;

import com.jobportal.jobportal.dto.AuthenticationDto;
import com.jobportal.jobportal.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Handles the creation of a token for an user.
 *
 * @since 13.03.2021
 */
@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -3472430070998029466L;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final String CLAIM_KEY_ROLE = "role";
    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_ID = "id";

    //TODO put it in a config file
    private static String SERVER_PASSWORD = "SuperSecret";

    private final AuthenticationService authenticationService;

    /**
     * Returns the email from the token.
     *
     * @param token the current token
     * @return String containing the email of the user
     */
    String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Generates a token containing the email, role, expiration date and the secret
     * encoded backend password.
     *
     * @param userDetails of the logged users
     * @return String containing the generated token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        AuthenticationDto authenticationDto = authenticationService.getUserByEmail(userDetails.getUsername());
        claims.put(CLAIM_KEY_ROLE, authenticationDto.getRole());
        claims.put(CLAIM_KEY_USERNAME, authenticationDto.getEmail());
        claims.put(CLAIM_KEY_ID, authenticationDto.getId());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(getAllClaimsFromToken(token));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SERVER_PASSWORD).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SERVER_PASSWORD)
                .compact();
    }

    Boolean validateToken(String token, UserDetails userDetails) {
        return getEmailFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
