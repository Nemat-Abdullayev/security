package az.security.controller;
import az.security.dto.JwtAuthenticationRequestDTO;
import az.security.service.AuthenticateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Api(value = "endpoint for authenticate user", description = "endpoint for authenticate")
@RequestMapping("/auth")
public class AuthenticationRestController {

    private final AuthenticateService authenticateService;

    @ApiOperation("create authenticate token for user")
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequestDTO jwtAuthenticationRequestDTO) throws Exception {
        return ResponseEntity.ok(authenticateService.generateToken(jwtAuthenticationRequestDTO));
    }
}
