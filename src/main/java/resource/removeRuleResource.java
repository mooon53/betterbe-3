package resource;

import dao.Dao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;

@Path("/removeRule")
public class removeRuleResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String removeOption(String input) {
        JSONObject response = new JSONObject(input);
        Long[] id = new Long[0];
        if (response.has("ruleArray")) {
            JSONArray options = response.getJSONArray("ruleArray");
            id = new Long[options.length()];
            for (int i = 0; i < id.length; i++) {
                id[i] = options.getLong(i);
            }
        }
        Dao.removeRule(id);
        return response.toString();
    }
}
