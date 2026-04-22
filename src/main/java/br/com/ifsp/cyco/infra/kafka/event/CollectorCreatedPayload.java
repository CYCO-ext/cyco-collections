package br.com.ifsp.cyco.infra.kafka.event;

import java.util.List;
import java.util.UUID;

public class CollectorCreatedPayload {
    public UUID collectorId;
    public String name;
    public List<UUID> materials;
    public double latitude;
    public double longitude;
}