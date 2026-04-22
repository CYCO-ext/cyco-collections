package br.com.ifsp.cyco.domain.entities;

import br.com.ifsp.cyco.domain.exceptions.InvalidMaterialException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RequestedMaterialEntity {

    private UUID id;
    private UUID materialId;
    private double weight;

    public RequestedMaterialEntity(UUID id, UUID materialId, double weight) {
        if (weight <= 0) {
            throw new InvalidMaterialException("Peso deve ser maior que zero");
        }

        this.id = id;
        this.materialId = materialId;
        this.weight = weight;
    }

}