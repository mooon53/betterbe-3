package resource;

import dao.Dao;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public String removeOption(String input) {
        System.out.println(input);
        JSONObject response = new JSONObject(input);
        Long id = null;
        if (response.has("id")) {
            id = response.getLong("id");
        }
        String option = Dao.getOption(id);
        System.out.println("option: " + option);
        return option;
    }
}
