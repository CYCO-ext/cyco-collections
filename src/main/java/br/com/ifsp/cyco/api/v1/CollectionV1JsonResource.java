package br.com.ifsp.cyco.api.v1;

import br.com.ifsp.cyco.application.dto.input.CreateCollectionRequestInput;
import br.com.ifsp.cyco.application.dto.output.CreateCollectionRequestOutput;
import br.com.ifsp.cyco.application.usecase.CreateCollectionRequestUseCase;
import br.com.ifsp.cyco.application.usecase.MatchCollectorsUseCase;
import br.com.ifsp.cyco.application.usecase.SelectCollectorUseCase;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.Map;

@Path("/v1/collection")
@Authenticated
public class CollectionV1JsonResource {

    private final CreateCollectionRequestUseCase createCollectionRequestUseCase;
    private final MatchCollectorsUseCase matchCollectorsUseCase;
    private final SelectCollectorUseCase   selectCollectorUseCase;

    public CollectionV1JsonResource(CreateCollectionRequestUseCase createCollectionRequestUseCase, MatchCollectorsUseCase matchCollectorsUseCase, SelectCollectorUseCase selectCollectorUseCase) {
        this.createCollectionRequestUseCase = createCollectionRequestUseCase;
        this.matchCollectorsUseCase = matchCollectorsUseCase;
        this.selectCollectorUseCase = selectCollectorUseCase;
    }

    @POST
    public Uni<RestResponse<CreateCollectionRequestOutput>> createCollection(CreateCollectionRequestInput input) {
        return Uni.createFrom().item(input)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> this.createCollectionRequestUseCase.execute(it))
                .onItem()
                .ifNotNull()
                .transform(it -> RestResponse.accepted(it));
    }
}
