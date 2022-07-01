package resource;

import dao.CartDao;
import dao.Dao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/remove")
public class removeOptionsResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String removeOption(String input) {
        JSONObject response = new JSONObject(input);
        Long id = null;
        if (response.has("id")) {
            id = response.getLong("id");
        }
        Dao.editOption(id);
        return response.toString();
    }
}
