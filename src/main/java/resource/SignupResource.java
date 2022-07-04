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
    @Consumes(MediaType.APPLICATION_JSON)
    public void getAccountInfo(String signupString) {
        JSONObject response = new JSONObject(signupString);
        String email = "";
        String password = "";
        String code = "";
        if (response.has("email")) {
            email = (String) response.get("email");
        }
        if (response.has("password")) {
            password = response.getString("password");
        }
        if (response.has("code")) {
            System.out.println(response);
            code = (String) response.get("code");
        }
        addAccount(email, password, code.equals("923375975"));
    }
}
