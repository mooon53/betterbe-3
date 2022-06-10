package resource;

import dao.Dao;
import model.Account;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccountInfo() {
        JSONObject response = new JSONObject();
        MultivaluedMap<String, String> parameters = uriInfo.getQueryParameters();
        String email = "";
        String password = "";
        if (parameters.containsKey("email")) {
            email = parameters.get("email").get(0);
        }
        if (parameters.containsKey("password")) {
            password = parameters.get("password").get(0);
        }
        System.out.println("test");
        System.out.println(email);
        System.out.println(password);
        String query = "SELECT password\n" +
                "FROM Test.account\n" +
                "WHERE username =" + username + " AND password =" + password;
        //Account account = jsonStringToCar(Dao.getAccount(username, password));
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