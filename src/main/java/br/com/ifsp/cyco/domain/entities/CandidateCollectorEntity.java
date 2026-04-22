package br.com.ifsp.cyco.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CandidateCollectorEntity {

    public enum Status {
        AVAILABLE,
        REJECTED,
        SELECTED
    }

    private UUID id;
    private UUID collectorCompanyId;
    private double distance;
    private Status status;

    public CandidateCollectorEntity(UUID id, UUID collectorCompanyId, double distance) {
        this.id = id;
        this.collectorCompanyId = collectorCompanyId;
        this.distance = distance;
        this.status = Status.AVAILABLE;
    }

    public void select() {
        this.status = Status.SELECTED;
    }

    public void reject() {
        this.status = Status.REJECTED;
    }
}