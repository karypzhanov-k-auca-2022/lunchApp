package org.kair.lunchApp.repository;

import java.util.UUID;

import org.kair.lunchApp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
