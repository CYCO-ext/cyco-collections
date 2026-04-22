package br.com.ifsp.cyco.domain.exceptions;

public class InvalidMaterialException extends ValidationException {

    public InvalidMaterialException(String message) {
        super(message);
    }
}