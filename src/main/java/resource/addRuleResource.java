package resource;

import dao.Dao;
import model.Rule;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/addRule")
public class addRuleResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addRule(Rule rule) {
        System.out.println(rule);
//        JSONObject response = new JSONObject(input);
        Dao.addRule(rule);
        return rule.toString();
    }

}
