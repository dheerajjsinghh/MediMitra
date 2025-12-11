package com.medimitra.util;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderUtil {
    private static final String ORDER_PREFIX = "MM";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String generateOrderNumber() {
        String datePart = LocalDateTime.now().format(DATE_FORMAT);
        String uniquePart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return ORDER_PREFIX + datePart + uniquePart;
    }

    public static String generateInvoiceNumber(int orderId) {
        String datePart = LocalDateTime.now().format(DATE_FORMAT);
        return "INV-" + datePart + "-" + String.format("%06d", orderId);
    }
}
