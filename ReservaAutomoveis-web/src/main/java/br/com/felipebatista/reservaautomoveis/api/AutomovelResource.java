package br.com.felipebatista.reservaautomoveis.api;

import br.com.felipebatista.reservaautomoveis.exceptions.AutomovelException;
import br.com.felipebatista.reservaautomoveis.model.Automovel;
import br.com.felipebatista.reservaautomoveis.service.AutomovelRepository;
import br.com.felipebatista.reservaautomoveis.specification.AutomoveisSpecification;
import br.com.felipebatista.reservaautomoveis.util.ResponseException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("automoveis")
public class AutomovelResource {

    @Inject
    private AutomovelRepository repository;

    @Inject
    private AutomoveisSpecification specification;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Automovel.RepresentationClass findAll() {
        return repository.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Automovel findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Automovel automovel) {

        try {
            specification.valida(automovel);
            repository.add(automovel);
            return Response.status(Response.Status.CREATED).entity(automovel).build();
        } catch (AutomovelException ae) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ResponseException.Builder.create()
                            .rootCause(ae.getCause().toString())
                            .rootMessage(ae.getMessage())
                            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                            .type(ae.getClass().getSimpleName())
                            .build())
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Automovel automovel) {
        if (!automovel.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID do objeto não corresponde ao ID da URL")
                    .build();
        }
        try {
            specification.valida(automovel);
            repository.update(automovel);
        } catch (AutomovelException ae) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ResponseException.Builder.create()
                            .rootCause(ae.getCause().getMessage())
                            .rootMessage(ae.getMessage())
                            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                            .type(ae.getClass().getSimpleName())
                            .build())
                    .build();
        }

        return Response.ok(automovel).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        repository.remove(id);
        return Response.noContent().build();
    }

}
