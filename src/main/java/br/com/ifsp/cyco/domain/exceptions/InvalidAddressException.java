package br.com.ifsp.cyco.domain.exceptions;

public class InvalidAddressException extends ValidationException {

    public InvalidAddressException(String message) {
        super(message);
    }
}