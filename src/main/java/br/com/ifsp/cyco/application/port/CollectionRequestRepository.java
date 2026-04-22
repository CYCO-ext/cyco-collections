package br.com.ifsp.cyco.application.port;

import br.com.ifsp.cyco.domain.entities.CollectionRequestEntity;
import io.smallrye.mutiny.Uni;

import java.util.UUID;

public interface CollectionRequestRepository {

    Uni<Void> save(CollectionRequestEntity request);

    Uni<CollectionRequestEntity> findById(UUID id);
}
