package br.com.ifsp.cyco.domain.entities;

import br.com.ifsp.cyco.domain.enums.RequestStatus;
import br.com.ifsp.cyco.domain.exceptions.EmptyMaterialsException;
import br.com.ifsp.cyco.domain.exceptions.InvalidMaterialException;
import br.com.ifsp.cyco.domain.exceptions.InvalidRequestStateException;
import br.com.ifsp.cyco.domain.exceptions.NoCollectorsFoundException;
import br.com.ifsp.cyco.domain.vo.Address;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class CollectionRequestEntity {

    private UUID id;
    private UUID generatorCompanyId;
    private Address address;
    private RequestStatus status;
    private LocalDateTime createdAt;
    private UUID selectedCollectorId;

    private List<RequestedMaterialEntity> materials;
    private List<CandidateCollectorEntity> candidates;

    public CollectionRequestEntity(UUID id, UUID generatorCompanyId, Address address) {
        this.id = id;
        this.generatorCompanyId = generatorCompanyId;
        this.address = address;
        this.status = RequestStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.materials = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }


    public void addMaterial(RequestedMaterialEntity material) {
        this.materials.add(material);
    }

    public void validate() {
        if (materials.isEmpty()) {
            throw new EmptyMaterialsException();
        }
    }

    public void startMatching() {
        if (status != RequestStatus.CREATED) {
            throw new InvalidRequestStateException("Pedido não pode iniciar matching");
        }
        this.status = RequestStatus.MATCHING;
    }

    public void addCandidate(CandidateCollectorEntity candidate) {
        this.candidates.add(candidate);
    }

    public void finishMatching() {
        if (candidates.isEmpty()) {
            throw new NoCollectorsFoundException();
        }
        this.status = RequestStatus.WAITING_SELECTION;
    }

    public void selectCollector(UUID collectorId) {
        if (status != RequestStatus.WAITING_SELECTION) {
            throw new InvalidRequestStateException("Pedido não está pronto para seleção");
        }

        var selected = candidates.stream()
                .filter(c -> c.getCollectorCompanyId().equals(collectorId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Coletor não encontrado"));

        selected.select();
        this.selectedCollectorId = collectorId;
        this.status = RequestStatus.COLLECTOR_SELECTED;
    }

}