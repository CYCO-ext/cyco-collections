package br.com.ifsp.cyco.infra.kafka.event;

import java.time.Instant;
import java.util.UUID;

public class EventEnvelope<T> {

    public UUID eventId;
    public String eventType;
    public int version;
    public Instant occurredAt;
    public T payload;
}
