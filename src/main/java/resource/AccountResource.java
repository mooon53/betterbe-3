package resource;

import dao.Dao;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.util.List;

import static utils.JSONUtils.*;

public class AccountResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    String username;
    String password;
    public AccountResource(UriInfo uriInfo, Request request, String username, String password) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.username = username;
        this.password = password;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccountInfo() {
        JSONObject response = new JSONObject();
        Person person = jsonStringToPerson(Dao.getAccountInfo(username));
        response.put("person", person);
        return response.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putTodo(JAXBElement<Account> account) {
        Account a = account.getValue();
        return putAndGetResponse(a);
    }

    private Response putAndGetResponse(Account account) {
        Response res = null;
        return res;
    }


}