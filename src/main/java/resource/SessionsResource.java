package resource;

import dao.SessionDao;
import model.Session;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("sessions")
public class SessionsResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSessionId() {
        System.out.println("pog");
        Session session = SessionDao.instance.newSession();
        JSONObject response = new JSONObject();
        response.put("sessionId", session.getSessionId().toString());
        response.put("expiration", session.getExpiration());
        return response.toString();
    }

    @Path("/{n}")
    public SessionResource getSession(@PathParam("n") String sessionId) { return new SessionResource(uriInfo, request, Long.parseLong(sessionId)); }
}
