package com.devops.cicd.order;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("unit")
class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    void computeTotalSimpleOrder() {
        Order order = new Order("ord-1", 2, 10.0, false);
        assertEquals(20.0, orderService.computeTotal(order));
    }

    @Test
    void computeTotalWithDiscount() {
        // Subtotal = 100, Discount 5% = 95.0
        Order order = new Order("ord-1", 10, 10.0, false);
        assertEquals(95.0, orderService.computeTotal(order));
    }

    @Test
    void computeTotalWithPriority() {
        // Subtotal = 20, Priority + 9.99 = 29.99
        Order order = new Order("ord-1", 2, 10.0, true);
        assertEquals(29.99, orderService.computeTotal(order));
    }

    @Test
    void computeTotalWithDiscountAndPriority() {
        // Subtotal = 100, Discount 5% = 95.0, Priority + 9.99 = 104.99
        Order order = new Order("ord-1", 10, 10.0, true);
        assertEquals(104.99, orderService.computeTotal(order));
    }

    @Test
    void computeTotalRounding() {
        // Subtotal = 1 * 10.123 -> 10.12
        Order order = new Order("ord-1", 1, 10.123, false);
        assertEquals(10.12, orderService.computeTotal(order));

        // Subtotal = 1 * 10.127 -> 10.13
        Order order2 = new Order("ord-2", 1, 10.127, false);
        assertEquals(10.13, orderService.computeTotal(order2));
    }

    @Test
    void computeTotalShouldValidateFirst() {
        Order invalidOrder = new Order("ord-1", 0, 10.0, false);
        assertThrows(IllegalArgumentException.class, () -> orderService.computeTotal(invalidOrder));
    }
}
