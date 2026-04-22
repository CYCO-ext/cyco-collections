package br.com.ifsp.cyco.infra.mongo.repository;

import br.com.ifsp.cyco.infra.mongo.document.CollectorDocument;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class CollectorRepository implements ReactivePanacheMongoRepository<CollectorDocument> {

    public io.smallrye.mutiny.Uni<CollectorDocument> findByCollectorId(UUID id) {
        return find("id", id).firstResult();
    }

    public io.smallrye.mutiny.Uni<Void> deleteByCollectorId(UUID id) {
        return delete("id", id).replaceWithVoid();
    }
}
