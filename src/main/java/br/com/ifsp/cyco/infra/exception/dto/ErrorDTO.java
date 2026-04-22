package br.com.ifsp.cyco.infra.exception.dto;

import java.time.LocalDateTime;

public class ErrorDTO {

    public String message;
    public String error;
    public int status;
    public LocalDateTime timestamp;

    public ErrorDTO(String message, String error, int status) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
