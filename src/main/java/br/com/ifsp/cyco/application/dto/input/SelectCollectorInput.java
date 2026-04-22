package br.com.ifsp.cyco.application.dto.input;

import java.util.UUID;

public record SelectCollectorInput(
        UUID requestId,
        UUID collectorId
) {}