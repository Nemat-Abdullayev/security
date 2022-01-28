package az.security.service.impl;

import az.security.dto.JwtAuthenticationRequestDTO;
import az.security.dto.JwtAuthenticationResponse;
import az.security.exception.AuthenticationException;
import az.security.model.User;
import az.security.security.JwtTokenUtil;
import az.security.service.AuthenticateService;
import az.security.service.UserService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthenticateServiceImpl(JwtTokenUtil jwtTokenUtil,
                                   UserService userService,
                                   AuthenticationManager authenticationManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @SneakyThrows
    public ResponseEntity<?> generateToken(JwtAuthenticationRequestDTO jwtAuthenticationRequest) {
        LOGGER.info("Enter inputs of authentication request {}", jwtAuthenticationRequest);
        String username = jwtAuthenticationRequest.getUsername();
        authenticate(
                jwtAuthenticationRequest.getUsername(),
                jwtAuthenticationRequest.getPassword()
        );
        final User user = userService.findByUsername(username).get();
        LOGGER.debug("generating token");
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(JwtAuthenticationResponse.builder()
                .accessToken(token)
                .build());
    }


    @SneakyThrows
    private void authenticate(String username, String password) {
        if (Objects.nonNull(password) && Objects.nonNull(username)) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            } catch (DisabledException e) {
                LOGGER.error("USER_DISABLED", e);
                throw new AuthenticationException("USER_DISABLED");
            } catch (BadCredentialsException e) {
                LOGGER.error("invalid credentials entered - {}", e.getClass());
                throw new AuthenticationException("INVALID_CREDENTIALS");
            }
        }

    }
}