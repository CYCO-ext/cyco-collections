package br.com.ifsp.cyco.application.dto.input;

import java.util.List;
import java.util.UUID;

public record CreateCollectionRequestInput(
        UUID generatorCompanyId,
        AddressInput address,
        List<MaterialInput> materials
) {}