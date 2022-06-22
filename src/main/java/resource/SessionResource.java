package resource;

import dao.SessionDao;
import model.Account;
import model.Session;
import org.json.JSONObject;

import static dao.SessionDao.*;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public class SessionResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    Long sessionId;

    public SessionResource(UriInfo uriInfo, Request request, Long sessionId) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.sessionId = sessionId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getStatus() {
        Session session = instance.getSession(sessionId);
        JSONObject response = new JSONObject();
        response.put("sessionId", sessionId);
        response.put("loggedIn", session.getLoggedIn());
        if (session.getLoggedIn()) {
            JSONObject accountJSON = new JSONObject();
            Account account = session.getAccount();
            accountJSON.put("email", account.getUsername());
            accountJSON.put("employee", account.getEmployee());
            response.put("account", accountJSON);
        } else {
            response.put("account", new JSONObject());
        }
        return response.toString();
    }
}
