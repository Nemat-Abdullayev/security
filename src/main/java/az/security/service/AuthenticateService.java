package az.security.service;

import az.security.dto.JwtAuthenticationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticateService {
    ResponseEntity<?> generateToken(JwtAuthenticationRequestDTO jwtAuthenticationRequest)
            throws AuthenticationException, Exception;
}
