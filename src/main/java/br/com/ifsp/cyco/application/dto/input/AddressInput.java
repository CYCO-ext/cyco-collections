package br.com.ifsp.cyco.application.dto.input;

public record AddressInput(
        String street,
        String number,
        String city,
        String state,
        String zipCode,
        double latitude,
        double longitude
) {}