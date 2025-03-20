package org.kair.lunchApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder 
@toString
@EqualsAndHashCode

@Entity
@Table(name = "user")
public class UserEntity {

}
