package az.security.service.impl;

import az.security.dto.ApiResponse;
import az.security.dto.UserRequestDTO;
import az.security.model.Permission;
import az.security.model.Role;
import az.security.model.User;
import az.security.repository.PermissionRepository;
import az.security.repository.RoleRepository;
import az.security.repository.UserRepository;
import az.security.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        log.info("find user by username {} if exists ", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(UserRequestDTO userRequestDTO) {
        Optional<User> optionalUser = userRepository.findByUsername(userRequestDTO.getUsername());
        Optional<Role> optionalRole = roleRepository.findById(userRequestDTO.getRoleId());
        List<Permission> permissions = permissionRepository.findAllById(userRequestDTO.getPermissionIdList());
        User user = User.builder()
                .password(new BCryptPasswordEncoder().encode(userRequestDTO.getPassword()))
                .createDate(LocalDate.now())
                .username(userRequestDTO.getUsername())
                .role(optionalRole.get())
                .permissions(permissions)
                .build();
        optionalUser.ifPresent(value -> user.setId(value.getId()));
        userRepository.save(user);
    }

    @Override
    public ApiResponse<?> findAllUserList() {
        return new ApiResponse<>(true, userRepository.findAll());
    }

    @Override
    @Transactional
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }
}
