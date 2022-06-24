package resource;

import dao.CartDao;
import dao.Dao;
import model.Cart;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/edit")
public class editOptionsResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editOption(String input) {
        System.out.println(input);
        JSONObject response = new JSONObject(input);
        Long id = null;
        if (response.has("id")) {
            id = response.getLong("id");
        }
        Dao.editOption(id);
        return response.toString();
    }
}
