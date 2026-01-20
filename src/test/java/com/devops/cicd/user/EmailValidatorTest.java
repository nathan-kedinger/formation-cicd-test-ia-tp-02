package com.devops.cicd.user;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
class EmailValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"alice@test.com", "bob.smith@company.io"})
    void validEmails(String email) {
        assertTrue(EmailValidator.isValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "alice", "alice@", "@test.com", "alice@test", "alice@@test.com"})
    void invalidEmails(String email) {
        assertFalse(EmailValidator.isValid(email));
    }

    @Test
    void nullEmailIsInvalid() {
        assertFalse(EmailValidator.isValid(null));
    }
}
