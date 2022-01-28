package az.security.service;

import az.security.dto.ApiResponse;
import az.security.dto.UserRequestDTO;
import az.security.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    void save(UserRequestDTO userRequestDTO);

    ApiResponse<?> findAllUserList();

    void delete(long userId);
}
