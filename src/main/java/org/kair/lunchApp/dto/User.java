package org.kair.lunchApp.dto;

import lombok.*;
import org.kair.lunchApp.model.enums.UserRole;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor // Lombok will generate a constructor with no parameters
@AllArgsConstructor // Lombok will generate a constructor with all parameters
@Builder // Lombok will generate a builder
@ToString
@EqualsAndHashCode // Lombok will generate an equals and hashcode methods

public class User {
    private UUID uuid;
    private String username;
    private String password;
    private String name;
    private String surname;
    private BigDecimal balance = BigDecimal.ZERO;
    private UserRole userRole = UserRole.USER;
    private Date createdAt;
}
