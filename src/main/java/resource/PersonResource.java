package resource;

import dao.Dao;
import model.Person;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

public class PersonResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    String username;

    public PersonResource(UriInfo uriInfo, Request request, String username) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.username = username;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonInfo() {
        JSONObject response = new JSONObject();
        return response.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putTodo(Person person) {
        Person p = null;
        return putAndGetResponse(p);
    }

    private Response putAndGetResponse(Person person) {
        Response res = null;
        return res;
    }

}