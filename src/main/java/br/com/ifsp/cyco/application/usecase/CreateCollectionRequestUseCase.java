package br.com.ifsp.cyco.application.usecase;

import br.com.ifsp.cyco.application.dto.input.CreateCollectionRequestInput;
import br.com.ifsp.cyco.application.dto.output.CreateCollectionRequestOutput;
import io.smallrye.mutiny.Uni;

import java.util.UUID;

public interface CreateCollectionRequestUseCase {

    Uni<CreateCollectionRequestOutput> execute(CreateCollectionRequestInput input);
}