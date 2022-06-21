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
        JSONObject request = new JSONObject(accountString);
        JSONObject response = new JSONObject();
        String email = "";
        String password = "";
        Long sessionId = Long.parseLong((String) request.get("sessionId"));
        System.out.println(sessionId);
        if (request.has("email")) {
            email = (String) request.get("email");
        }
        if (request.has("password")) {
            password = Integer.toString((int) request.get("password"));
        }
        if(password.equals(Dao.getPass(email)) ) {
            System.out.println("correct password");
            SessionDao.instance.logIn(sessionId, email);
            response.put("success", true);
        } else {
            System.out.println("wrong password or username");
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