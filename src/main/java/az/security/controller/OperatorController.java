package az.security.controller;


import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/operator")
@Api(value = "endpoint for operators")
@PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
public class OperatorController {


    @PreAuthorize("hasAnyAuthority('WRITE')")
    @GetMapping(value = "/operator-info")
    public ResponseEntity<?> getOperatorInfo() {
        return new ResponseEntity<>("this info is test info", HttpStatus.OK);
    }
}
