package com.infy.clients.dto;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String message
) {}
