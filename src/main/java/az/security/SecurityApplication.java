package az.security;

import az.security.enums.UserRole;
import az.security.model.Permission;
import az.security.model.Role;
import az.security.model.User;
import az.security.repository.PermissionRepository;
import az.security.repository.RoleRepository;
import az.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SecurityApplication {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        log.info("inserting processing start");
//
//        Role role = Role.builder()
//                .roleName(UserRole.ADMIN)
//                .build();
//        Role role2 = Role.builder()
//                .roleName(UserRole.SUPERVISOR)
//                .build();
//        Role role1 = Role.builder()
//                .roleName(UserRole.OPERATOR)
//                .build();
//        List<Role> roleList = List.of(role, role1, role2);
//        roleRepository.saveAll(roleList);
//
//
//        List<Permission> permissions = List.of(
//                Permission.builder()
//                        .permissionName("WRITE")
//                        .build(),
//                Permission.builder()
//                        .permissionName("READ")
//                        .build(),
//                Permission.builder()
//                        .permissionName("DELETE")
//                        .build());
//
//        permissionRepository.saveAll(permissions);
//
//
//        User user = User.builder()
//                .username("admin")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .build();
//
//
//        User user1 = User.builder()
//                .username("supervisor")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .build();
//
//
//        User user2 = User.builder()
//                .username("operator")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .build();
////
//        userRepository.saveAll(List.of(user, user1, user2));

//    }
}
