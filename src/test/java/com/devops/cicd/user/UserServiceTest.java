package com.devops.cicd.user;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void registerShouldCreateAndReturnUser() {
        User user = userService.register("bob@test.com", "Password123!", Role.USER);

        assertNotNull(user);
        assertEquals("bob@test.com", user.getEmail());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void registerShouldPropagateExceptions() {
        assertThrows(IllegalArgumentException.class, () ->
            userService.register("invalid", "Azerty1!", Role.USER)
        );
    }
}
