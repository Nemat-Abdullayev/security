package az.security.model;


import az.security.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CC_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    private LocalDate createDate;

    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDate.now();
    }
}
