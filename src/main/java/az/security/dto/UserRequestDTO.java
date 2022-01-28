package az.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    private String username;
    private String password;

    private List<Long> permissionIdList;
    private long roleId;
}
