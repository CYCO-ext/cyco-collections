package br.com.ifsp.cyco.application.port;

import br.com.ifsp.cyco.application.port.dto.CollectorDTO;
import io.smallrye.mutiny.Multi;


public interface CollectorGateway {

    Multi<CollectorDTO> findNearbyCollectors(double latitude, double longitude);
}