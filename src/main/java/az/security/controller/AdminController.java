package az.security.controller;

import az.security.dto.ApiResponse;
import az.security.dto.UserRequestDTO;
import az.security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@Api(value = "admin operations")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('WRITE')")
    @PostMapping("/user")
    @ApiOperation(value = "user create if admin has authorities for creating")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.save(userRequestDTO);
        return new ResponseEntity<>("user created", HttpStatus.OK);
    }

    @GetMapping("/user")
    @ApiOperation(value = "get user list if role is admin")
    public ResponseEntity<?> getUserList() {
        ApiResponse<?> apiResponse = userService.findAllUserList();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('DELETE')")
    @DeleteMapping("/user/{userId}")
    @ApiOperation(value = "delete user if user has delete permission")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
