package br.com.ifsp.cyco.application.usecase.impl;

import br.com.ifsp.cyco.application.dto.input.SelectCollectorInput;
import br.com.ifsp.cyco.application.port.CollectionRequestRepository;
import br.com.ifsp.cyco.application.usecase.SelectCollectorUseCase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SelectCollectorUseCaseImpl implements SelectCollectorUseCase {

    private final CollectionRequestRepository repository;

    public SelectCollectorUseCaseImpl(CollectionRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Uni<Void> execute(SelectCollectorInput input) {

        return repository.findById(input.requestId())
                .onItem().ifNull().fail()
                .invoke(request -> request.selectCollector(input.collectorId()))
                .flatMap(repository::save);
    }
}