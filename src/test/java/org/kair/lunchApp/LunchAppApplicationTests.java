package org.kair.lunchApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kair.lunchApp.dto.User;
import org.kair.lunchApp.model.UserEntity;
import org.kair.lunchApp.model.enums.UserRole;
import org.kair.lunchApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class LunchAppApplicationTests {
    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.26");

    // for check UserController
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);

    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; // for convert object -> json and json -> object like in Postman
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateUser() throws Exception {
        // given
        User user = getUser(); // get and create
        String userConvertAsString = objectMapper.writeValueAsString(user); // convert object -> json

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users") // for check UserController
                .with(httpBasic("user", "121417"))  // for check security
                .contentType(MediaType.APPLICATION_JSON)
                .content(userConvertAsString)) // send json
                .andExpect(status().isCreated()); // check status
    }

    private User getUser() {
        return User.builder()
                .uuid(UUID.randomUUID())
                .username("Kair_20")
                .password("123")
                .name("Kair")
                .surname("Karypzhanov")
                .balance(new BigDecimal(100))
                .userRole(UserRole.ADMIN)
                .createdAt(new Date(System.currentTimeMillis()))
                .build();

    }

    @Test
    void shouldReturnAllUsers(){
        UserEntity user1 = new UserEntity();
        user1.setUsername("Kair_20");
        user1.setPassword("123");
        userRepository.save(user1);

        UserEntity user2 = new UserEntity();
        user2.setUsername("Kair_40");
        user2.setPassword("321");
        userRepository.save(user2);

        List<UserEntity> users = userRepository.findAll();

        Assertions.assertEquals(2, users.size());
    }
}
