package br.com.ifsp.cyco.application.dto.input;

import java.util.UUID;

public record MaterialInput(
        UUID materialId,
        double weight
) {}
