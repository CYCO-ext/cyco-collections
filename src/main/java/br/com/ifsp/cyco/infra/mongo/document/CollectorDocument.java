package br.com.ifsp.cyco.infra.mongo.document;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;
import java.util.UUID;

@MongoEntity(collection = "collectors")
public class CollectorDocument {

    public UUID id;
    public String name;
    public List<UUID> materials;

    public Location location;

    public static class Location {
        public String type = "Point";
        public List<Double> coordinates;
    }
}