package com.example.arv.booking.utils;

import java.time.LocalDateTime;

public class LocalDateTimeUtils {

    public static boolean isAfternoon(LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        return hour >= 12 && hour < 18; // Assuming afternoon is from 12 PM to 6 PM
    }
}
