package org.acme.retail.order.simulator.rest;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.core.json.JsonObject;
import org.acme.retail.order.simulator.service.OrderSimulatorService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/simulate")
public class OrderSimulatorResource {

    @Inject
    OrderSimulatorService orderSimulatorService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> simulate(String payload) {
        JsonObject json = new JsonObject(payload);
        Integer count = json.getInteger("count");
        String customer = json.getString("customer");
        return Uni.createFrom().item(() -> payload).emitOn(Infrastructure.getDefaultWorkerPool())
                .onItem().transform(s -> orderSimulatorService.simulate(customer, count))
                .onItem().transform(j -> {
                    if (j.containsKey("result")) {
                        return Response.ok(j.toString()).build();
                    } else {
                        return Response.status(400).entity(j.toString()).build();
                    }
                });
    }
}
