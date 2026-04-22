package br.com.ifsp.cyco.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MaterialEntity {

    private UUID id;
    private String name;

    public MaterialEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}