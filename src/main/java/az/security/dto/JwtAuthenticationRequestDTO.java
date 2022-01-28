package az.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationRequestDTO implements Serializable {
    private static final long serialVersionUID = 270383254869437136L;

    private String username;
    private String password;
}
