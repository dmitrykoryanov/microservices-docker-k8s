package com.infy.cust.dto;

public record CustomerRegistrationRequest(String firstname,
                                          String lastname,
                                          String email) {
}
