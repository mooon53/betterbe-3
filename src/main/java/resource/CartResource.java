package resource;

import dao.CartDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public class CartResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    private final Long sessionId;

    public CartResource(UriInfo uriInfo, Request request, String sessionId) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.sessionId = Long.parseLong(sessionId);
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCart() {
        System.out.println(sessionId);
        return CartDao.instance.getCart(sessionId).toJSON().toString();
    }
}
