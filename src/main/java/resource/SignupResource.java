package resource;

import static dao.Dao.*;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/signup")
public class SignupResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAccountInfo(String signupString) {
        System.out.println("signup: " + signupString);
        JSONObject response = new JSONObject(signupString);
        String email = "";
        String password = "";
        String code = "";
        System.out.println("in front");
        if (response.has("email")) {
            email = (String) response.get("email");
            System.out.println("gets the email" + email);
        }
        if (response.has("password")) {
            password = (String) response.get("password");
            System.out.println("gets the password: ");
        }
        System.out.println("signup: " + email);
        System.out.println("signup" + password);
        if (response.has("code")) {
            code = (String) response.get("code");
        }
        System.out.println("got here");
        addAccount(email, password, code.equals("BetterBe_3"));
        if (password.equals(getPass(email))) {
            System.out.println("correct password");
        } else {
            System.out.println("not added");
        }
        return response.toString();
    }
}
