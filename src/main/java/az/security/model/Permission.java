package az.security.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CC_PERMISSIONS")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permissionName;

    private LocalDate createDate;

    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDate.now();
    }
}
