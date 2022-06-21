package resource;

import model.Session;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static dao.SessionDao.*;

@Path("session")
public class SessionResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getSessionId() {
        System.out.println("pog");
        Session session = newSession();
        JSONObject response = new JSONObject();
        response.put("sessionId", session.getSessionId());
        response.put("expiration", session.getExpiration());
        return response.toString();
    }
}
