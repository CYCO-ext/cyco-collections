package br.com.ifsp.cyco.application.usecase.impl;

import br.com.ifsp.cyco.application.port.CollectionRequestRepository;
import br.com.ifsp.cyco.application.port.CollectorGateway;
import br.com.ifsp.cyco.application.port.dto.CollectorDTO;
import br.com.ifsp.cyco.application.usecase.MatchCollectorsUseCase;
import br.com.ifsp.cyco.domain.entities.CandidateCollectorEntity;
import br.com.ifsp.cyco.domain.entities.CollectionRequestEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class MatchCollectorsUseCaseImpl implements MatchCollectorsUseCase {

    private final CollectionRequestRepository repository;
    private final CollectorGateway gateway;

    public MatchCollectorsUseCaseImpl(CollectionRequestRepository repository,
                                      CollectorGateway gateway) {
        this.repository = repository;
        this.gateway = gateway;
    }

    @Override
    public Uni<Void> execute(UUID requestId) {

        return repository.findById(requestId)
                .onItem().ifNull().fail()
                .invoke(CollectionRequestEntity::startMatching)
                .flatMap(request ->

                        gateway.findNearbyCollectors(
                                        request.getAddress().getLatitude(),
                                        request.getAddress().getLongitude()
                                )
                                .onItem().transform(dto -> mapToCandidate(request, dto))
                                .collect().asList()
                                .invoke(list -> list.forEach(request::addCandidate))
                                .invoke(request::finishMatching)
                                .flatMap(x -> repository.save(request))
                );
    }

    private CandidateCollectorEntity mapToCandidate(CollectionRequestEntity request, CollectorDTO dto) {

        double distance = calculateDistance(
                request.getAddress().getLatitude(),
                request.getAddress().getLongitude(),
                dto.latitude(),
                dto.longitude()
        );

        return new CandidateCollectorEntity(
                UUID.randomUUID(),
                dto.id(),
                distance
        );
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2));
    }
}