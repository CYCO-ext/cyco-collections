package br.com.ifsp.cyco.infra.kafka.event;

import java.util.UUID;

public class CollectorLocationUpdatedPayload {
    public UUID collectorId;
    public double latitude;
    public double longitude;
}