package br.com.ifsp.cyco.application.dto.output;

import java.util.UUID;

public record CreateCollectionRequestOutput(
        UUID id
) {

    public static CreateCollectionRequestOutput create(UUID id) {
        return new CreateCollectionRequestOutput(id);
    }
}
