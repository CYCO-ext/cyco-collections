package br.com.ifsp.cyco.infra.exception;

import br.com.ifsp.cyco.infra.exception.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);


    @Override
    public Response toResponse(Throwable exception) {

        LOG.error("Erro inesperado", exception);

        var error = new ErrorDTO(
                "Erro interno inesperado",
                exception.getClass().getSimpleName(),
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}
