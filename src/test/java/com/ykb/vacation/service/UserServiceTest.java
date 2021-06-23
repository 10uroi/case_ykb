package com.ykb.vacation.service;

import com.ykb.vacation.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getUser() {

        User actual = userService.getUser(1l);
        User expected = new User();
        expected.setName("Onur");

        assertEquals(actual.getName(), expected.getName());
    }
}
