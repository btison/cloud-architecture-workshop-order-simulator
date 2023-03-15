package org.acme.retail.order.simulator.rest;

import org.acme.retail.order.simulator.dto.OrderDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/services/order")
@RegisterRestClient(configKey="order-service")
public interface OrderService {

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response placeOrder(OrderDto order);

}
