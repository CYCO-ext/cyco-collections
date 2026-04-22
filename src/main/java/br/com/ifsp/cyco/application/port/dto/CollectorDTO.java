package br.com.ifsp.cyco.application.port.dto;

import java.util.List;
import java.util.UUID;

public record CollectorDTO(
        UUID id,
        double latitude,
        double longitude,
        List<UUID> supportedMaterials
) {}
