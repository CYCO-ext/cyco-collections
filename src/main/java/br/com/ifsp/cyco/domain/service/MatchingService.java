package br.com.ifsp.cyco.domain.service;

import br.com.ifsp.cyco.domain.entities.CandidateCollectorEntity;
import br.com.ifsp.cyco.domain.entities.CollectionRequestEntity;

import java.util.List;

public interface MatchingService {

    List<CandidateCollectorEntity> findCandidates(CollectionRequestEntity request);
}
