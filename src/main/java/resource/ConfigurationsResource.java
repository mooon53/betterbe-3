package resource;

import dao.CartDao;
import dao.Dao;
import dao.SessionDao;
import model.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import static utils.JSONUtils.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("configurations/{sessionID}")
public class ConfigurationsResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Configuration> getConfigurations(@PathParam("sessionID") String input) {
        Long sessionId = Long.parseLong(input);
        String email = SessionDao.instance.getSession(sessionId).getAccount().getUsername();
        return jsonStringsToConfigurations(Dao.getConfigurations(email));
    }

    @Path("{configID}")
    public ConfigurationResource getCart(@PathParam("configID") String configID) {
        return new ConfigurationResource(uriInfo, request, configID);
    }
}
