package com.devops.cicd.order;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class OrderValidatorTest {

    @Test
    void nullOrderThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            OrderValidator.validate(null)
        );
        assertEquals("order must not be null", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void blankIdThrowsException(String blankId) {
        Order order = new Order(blankId, 1, 10.0, false);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            OrderValidator.validate(order)
        );
        assertEquals("id must not be blank", exception.getMessage());
    }

    @Test
    void nullIdThrowsException() {
        Order order = new Order(null, 1, 10.0, false);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            OrderValidator.validate(order)
        );
        assertEquals("id must not be blank", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void invalidQuantityThrowsException(int invalidQuantity) {
        Order order = new Order("ord-1", invalidQuantity, 10.0, false);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            OrderValidator.validate(order)
        );
        assertEquals("quantity must be > 0", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0})
    void invalidUnitPriceThrowsException(double invalidPrice) {
        Order order = new Order("ord-1", 1, invalidPrice, false);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            OrderValidator.validate(order)
        );
        assertEquals("unitPrice must be > 0", exception.getMessage());
    }

    @Test
    void validOrderDoesNotThrow() {
        Order order = new Order("ord-1", 1, 10.0, false);
        assertDoesNotThrow(() -> OrderValidator.validate(order));
    }
}
