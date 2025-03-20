package org.kair.lunchApp.service;

import lombok.RequiredArgsConstructor;
import org.kair.lunchApp.dto.User;
import org.kair.lunchApp.model.UserEntity;
import org.kair.lunchApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public void createUser(User user) {
        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .balance(user.getBalance())
                .userRole(user.getUserRole())
                .createdAt((Date) user.getCreatedAt())
                .build();

        userRepository.save(userEntity);
    }

    public List<User> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(this::mapToUser).toList();
    }

    private User mapToUser(UserEntity userEntity) {
        return User.builder()
                .uuid(userEntity.getUuid())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .balance(userEntity.getBalance())
                .userRole(userEntity.getUserRole())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }
}
