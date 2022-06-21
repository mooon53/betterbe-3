package resource;

import dao.Dao;
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
        //passing the email and password into a Json object
        JSONObject response = new JSONObject(signupString);
        String email = "";
        String password = "";
        String code = "";
        boolean signup;
        if (response.has("email")) {
            email = (String) response.get("email");
        }
        if (response.has("password")) {
            password = (String) response.get("password");
        }
        if (response.has("code")) {
            code = (String) response.get("code");
        }
        if(code.equals("BetterBe_3")){
            Dao.addEmpAccount(email, password);
        } else{
            Dao.addAccount(email, password);
        }
        if(password.equals(Dao.getPass(email))) {
            System.out.println("account added");
            signup = true;
        } else {
            System.out.println("account not added");
            signup = false;
        }
        String signString = "{'signup':'" + signup + "'}";
        response = new JSONObject(signString);
        return response.toString();
    }
}
