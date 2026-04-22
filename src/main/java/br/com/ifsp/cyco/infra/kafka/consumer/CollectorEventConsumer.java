package br.com.ifsp.cyco.infra.kafka.consumer;

import br.com.ifsp.cyco.infra.kafka.event.*;
import br.com.ifsp.cyco.infra.mongo.document.CollectorDocument;
import br.com.ifsp.cyco.infra.mongo.repository.CollectorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.List;

@ApplicationScoped
public class CollectorEventConsumer {

    private final ObjectMapper objectMapper;
    private final CollectorRepository repository;

    public CollectorEventConsumer(ObjectMapper objectMapper,
                                  CollectorRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @Incoming("collector-events")
    public Uni<Void> consume(String message) {

        try {
            EventEnvelope<?> envelope = objectMapper.readValue(message, EventEnvelope.class);

            switch (envelope.eventType) {

                case "CollectorCreated":
                    return handleCreated(message);

                case "CollectorUpdated":
                    return handleUpdated(message);

                case "CollectorLocationUpdated":
                    return handleLocationUpdated(message);

                case "CollectorDeleted":
                    return handleDeleted(message);

                default:
                    return Uni.createFrom().voidItem();
            }

        } catch (Exception e) {
            return Uni.createFrom().failure(e);
        }
    }

    private Uni<Void> handleCreated(String message) throws Exception {

        EventEnvelope<CollectorCreatedPayload> event =
                objectMapper.readValue(message,
                        objectMapper.getTypeFactory()
                                .constructParametricType(EventEnvelope.class, CollectorCreatedPayload.class));

        var doc = new CollectorDocument();
        doc.id = event.payload.collectorId;
        doc.name = event.payload.name;
        doc.materials = event.payload.materials;

        doc.location = new CollectorDocument.Location();
        doc.location.coordinates = List.of(
                event.payload.longitude,
                event.payload.latitude
        );

        return repository.persist(doc).replaceWithVoid();
    }

    private Uni<Void> handleUpdated(String message) throws Exception {

        EventEnvelope<CollectorUpdatedPayload> event =
                objectMapper.readValue(message,
                        objectMapper.getTypeFactory()
                                .constructParametricType(EventEnvelope.class, CollectorUpdatedPayload.class));

        return repository.findByCollectorId(event.payload.collectorId)
                .onItem().ifNotNull().invoke(doc -> {
                    doc.name = event.payload.name;
                    doc.materials = event.payload.materials;
                })
                .flatMap(repository::update)
                .replaceWithVoid();
    }

    private Uni<Void> handleLocationUpdated(String message) throws Exception {

        EventEnvelope<CollectorLocationUpdatedPayload> event =
                objectMapper.readValue(message,
                        objectMapper.getTypeFactory()
                                .constructParametricType(EventEnvelope.class, CollectorLocationUpdatedPayload.class));

        return repository.findByCollectorId(event.payload.collectorId)
                .onItem().ifNotNull().invoke(doc -> {
                    doc.location.coordinates = List.of(
                            event.payload.longitude,
                            event.payload.latitude
                    );
                })
                .flatMap(repository::update)
                .replaceWithVoid();
    }

    private Uni<Void> handleDeleted(String message) throws Exception {

        EventEnvelope<CollectorDeletedPayload> event =
                objectMapper.readValue(message,
                        objectMapper.getTypeFactory()
                                .constructParametricType(EventEnvelope.class, CollectorDeletedPayload.class));

        return repository.deleteByCollectorId(event.payload.collectorId);
    }
}