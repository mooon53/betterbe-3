package resource;

import dao.Dao;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.util.List;

import static dao.SessionDao.*;
import static utils.JSONUtils.*;

@Path("/account")
public class AccountResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    String username;
    String password;
//    public AccountResource(UriInfo uriInfo, Request request, String username, String password) {
//        this.uriInfo = uriInfo;
//        this.request = request;
//        this.username = username;
//        this.password = password;
//    }

    //the password match does not work
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAccountInfo(String accountString) {
        System.out.println(accountString);
        JSONObject response = new JSONObject(accountString);
        String email = "";
        String password = "";
        Long sessionId = Long.parseLong((String) response.get("sessionId"));
        System.out.println(sessionId);
        if (response.has("email")) {
            email = (String) response.get("email");
        }
        if (response.has("password")) {
            password = (String) response.get("password");
        }
        if(password.equals(Dao.getPass(email)) ) {
            System.out.println("correct password");
            logIn(sessionId, email);
        } else {
            System.out.println("wrong password or username");
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