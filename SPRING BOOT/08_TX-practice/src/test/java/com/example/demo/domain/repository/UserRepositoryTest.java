package com.example.demo.domain.repository;

import com.example.demo.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void t1() throws Exception {
        User user = User.builder()
                .username("John")
                .password("1234")
                .role("Role_USER")
                .build();
        userRepository.save(user);
    }

    @Test
    public void t2() throws Exception {
//        List<User> list = userRepository.selectByRole("ROLE_USER");

        List<User> list = userRepository.selectAllLikeUsername("1");
        list.stream().forEach(System.out::println);
    }
}