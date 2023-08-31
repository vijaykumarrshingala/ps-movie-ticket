package com.example.arv.booking.services;

import java.math.BigDecimal;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal applyDiscount(BigDecimal originalPrice) {
        return originalPrice;
    }
}
