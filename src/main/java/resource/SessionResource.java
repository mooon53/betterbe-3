package resource;

import dao.SessionDao;
import model.Session;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("session")
public class SessionResource {
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


}
