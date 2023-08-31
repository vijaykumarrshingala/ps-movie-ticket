package com.example.arv.booking.services;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal applyDiscount(BigDecimal originalPrice);
}
