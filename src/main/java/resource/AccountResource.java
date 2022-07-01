package resource;

import dao.Dao;
import dao.SessionDao;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.util.List;

@Path("/account")
public class AccountResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    String username;
    String password;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAccountInfo(String accountString) {
        JSONObject request = new JSONObject(accountString);
        JSONObject response = new JSONObject();
        String email = "";
        String password = "";
        Long sessionId = Long.parseLong(request.getString("sessionId"));
        if (request.has("email")) {
            email = request.getString("email");
        }
        if (request.has("password")) {
            password = request.getString("password");
        }
        if(password.equals(Dao.getPass(email)) ) {
            SessionDao.instance.logIn(sessionId, email);
            response.put("success", true);
        } else {
            response.put("success", false);
        }
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