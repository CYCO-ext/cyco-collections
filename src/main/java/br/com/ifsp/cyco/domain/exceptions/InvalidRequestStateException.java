package br.com.ifsp.cyco.domain.exceptions;

public class InvalidRequestStateException extends CollectionRequestException {

    public InvalidRequestStateException(String message) {
        super(message);
    }
}