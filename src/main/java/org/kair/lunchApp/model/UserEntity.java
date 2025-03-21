package org.kair.lunchApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.kair.lunchApp.model.enums.UserRole;

@Setter
@Getter
@NoArgsConstructor // Lombok will generate a constructor with no parameters
@AllArgsConstructor // Lombok will generate a constructor with all parameters
@Builder // Lombok will generate a builder
@ToString
@EqualsAndHashCode // Lombok will generate an equals and hashcode methods

@Entity // JPA annotation to make this class an entity
@Table(name = "user") // JPA annotation to specify the table name
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)", nullable = false, unique = true) // column is necessary and unique
    private UUID uuid;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING) // to specify that the role is an enum
    @Column(name = "role", nullable = false)
    private UserRole userRole = UserRole.USER;

    @Column(name = "created_at", updatable = false) // always when creating entity their should be a date
    private Date createdAt;

    @PrePersist // for take actual date and time
    protected void onCreate() {
        createdAt = new Date();
    }
}
