package main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import main.enums.ProfileRole;
import main.enums.ProfileStatus;

@Getter
@Setter
@Entity
@Table (name = "ProfileEntity")
public class ProfileEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;

}