package az.security.controller;

import az.security.dto.UserRequestDTO;
import az.security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/access/permit")
@Api("access operation")
public class AccessController {

    private final UserService userService;

    @PostMapping("/user")
    @ApiOperation(value = "user create if admin has authorities for creating")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.save(userRequestDTO);
        return new ResponseEntity<>("user created", HttpStatus.OK);
    }


}
