package br.com.ifsp.cyco.infra.exception;

import br.com.ifsp.cyco.domain.exceptions.DomainException;
import br.com.ifsp.cyco.infra.exception.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DomainExceptionMapper implements ExceptionMapper<DomainException> {

    @Override
    public Response toResponse(DomainException exception) {

        var error = new ErrorDTO(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                422
        );

        return Response.status(422)
                .entity(error)
                .build();
    }
}
