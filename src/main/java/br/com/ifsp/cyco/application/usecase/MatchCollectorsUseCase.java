package br.com.ifsp.cyco.application.usecase;

import io.smallrye.mutiny.Uni;

import java.util.UUID;

public interface MatchCollectorsUseCase {

    Uni<Void> execute(UUID requestId);
}