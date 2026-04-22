package br.com.ifsp.cyco.application.usecase.impl;

import br.com.ifsp.cyco.application.dto.input.CreateCollectionRequestInput;
import br.com.ifsp.cyco.application.dto.input.MaterialInput;
import br.com.ifsp.cyco.application.dto.output.CreateCollectionRequestOutput;
import br.com.ifsp.cyco.application.port.CollectionRequestRepository;
import br.com.ifsp.cyco.application.usecase.CreateCollectionRequestUseCase;
import br.com.ifsp.cyco.domain.entities.CollectionRequestEntity;
import br.com.ifsp.cyco.domain.entities.RequestedMaterialEntity;
import br.com.ifsp.cyco.domain.vo.Address;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class CreateCollectionRequestUseCaseImpl implements CreateCollectionRequestUseCase {

    private final CollectionRequestRepository repository;

    public CreateCollectionRequestUseCaseImpl(CollectionRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Uni<CreateCollectionRequestOutput> execute(CreateCollectionRequestInput input) {

        Address address = new Address(
                input.address().street(),
                input.address().number(),
                input.address().city(),
                input.address().state(),
                input.address().zipCode(),
                input.address().latitude(),
                input.address().longitude()
        );

        var request = new CollectionRequestEntity(
                UUID.randomUUID(),
                input.generatorCompanyId(),
                address
        );

        for (MaterialInput material : input.materials()) {
            request.addMaterial(
                    new RequestedMaterialEntity(
                            UUID.randomUUID(),
                            material.materialId(),
                            material.weight()
                    )
            );
        }

        request.validate();

        return repository.save(request)
                .replaceWith(CreateCollectionRequestOutput.create(request.getId()));
    }
}