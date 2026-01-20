package com.devops.cicd.user;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
public class UserTest {

    @Test
    void validUserCreation() {
        User user = new User("alice@test.com", "Azerty1!", Role.USER);
        assertEquals("alice@test.com", user.getEmail());
        assertEquals("Azerty1!", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void emailShouldBeTrimmed() {
        User user = new User("  alice@test.com  ", "Azerty1!", Role.USER);
        assertEquals("alice@test.com", user.getEmail());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "invalid-email", "bob@test"})
    void invalidEmailThrowsException(String invalidEmail) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new User(invalidEmail, "Azerty1!", Role.USER)
        );
        assertEquals("email must be valid", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"weak", "Azerty1", "azerty1!", "AZERTY1!"})
    void weakPasswordThrowsException(String weakPassword) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new User("alice@test.com", weakPassword, Role.USER)
        );
        assertEquals("password must be strong", exception.getMessage());
    }

    @Test
    void nullRoleThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new User("alice@test.com", "Azerty1!", null)
        );
        assertEquals("role must not be null", exception.getMessage());
    }

    @Test
    void adminCanAccessAdminArea() {
        User admin = new User("admin@test.com", "Azerty1!", Role.ADMIN);
        assertTrue(admin.canAccessAdminArea());
    }

    @Test
    void userCannotAccessAdminArea() {
        User user = new User("user@test.com", "Azerty1!", Role.USER);
        assertFalse(user.canAccessAdminArea());
    }
}
