package br.com.ifsp.cyco.application.usecase;

import br.com.ifsp.cyco.application.dto.input.SelectCollectorInput;
import io.smallrye.mutiny.Uni;

public interface SelectCollectorUseCase {

    Uni<Void> execute(SelectCollectorInput input);
}