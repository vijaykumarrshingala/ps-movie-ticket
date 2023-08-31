package com.example.arv.booking.services;

import java.math.BigDecimal;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private double percentage = 20; //Default

    public PercentageDiscountStrategy(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal originalPrice) {
        BigDecimal discountAmount = originalPrice.multiply(BigDecimal.valueOf(percentage / 100));
        return originalPrice.subtract(discountAmount);
    }
}
