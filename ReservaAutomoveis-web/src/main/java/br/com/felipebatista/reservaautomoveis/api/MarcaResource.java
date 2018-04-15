package br.com.felipebatista.reservaautomoveis.api;

import br.com.felipebatista.reservaautomoveis.exceptions.MarcaException;
import br.com.felipebatista.reservaautomoveis.model.Marca;
import br.com.felipebatista.reservaautomoveis.service.MarcaRepository;
import br.com.felipebatista.reservaautomoveis.specification.MarcasSpecification;
import br.com.felipebatista.reservaautomoveis.util.ResponseException;
import java.util.List;
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

@Path("marcas")
public class MarcaResource {

    @Inject
    private MarcaRepository repository;

    @Inject
    private MarcasSpecification specification;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public List<Marca> findAll() {
        return repository.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Marca findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Marca marca) {
        try {
            specification.valida(marca);
            repository.add(marca);
            return Response.status(Response.Status.CREATED).entity(marca).build();
        } catch (MarcaException me) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ResponseException.Builder.create()
                            .rootCause(me.getCause().toString())
                            .rootMessage(me.getMessage())
                            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                            .type(me.getClass().getSimpleName())
                            .build())
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Marca marca) {

        if (!marca.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(marca)
                    .build();
        }

        try {
            specification.valida(marca);
            repository.update(marca);
        } catch (MarcaException me) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ResponseException.Builder.create()
                            .rootCause(me.getCause().toString())
                            .rootMessage(me.getMessage())
                            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                            .type(me.getClass().getSimpleName())
                            .build())
                    .build();
        }

        return Response.ok(marca).build();

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        repository.remove(id);
        return Response.noContent().build();
    }

}
