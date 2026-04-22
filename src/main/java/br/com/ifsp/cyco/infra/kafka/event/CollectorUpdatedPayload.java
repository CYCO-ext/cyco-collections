package br.com.ifsp.cyco.infra.kafka.event;

import java.util.List;
import java.util.UUID;

public class CollectorUpdatedPayload {
    public UUID collectorId;
    public String name;
    public List<UUID> materials;
}
