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
@Path("/addOption")
public class optionToCarResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addOption(String input) {
        JSONObject response = new JSONObject(input);
        Long id = null;
        String value = "";
        int price = 0;
        String option_type = "";
        if (response.has("carId")) {
            id = response.getLong("carId");
        }
        if (response.has("value")) {
            value = response.getString("value");
        }
        if (response.has("price")) {
            price = response.getInt("price");
        }
        if (response.has("option_type")) {
            option_type = response.getString("option_type");
        }
        int options_id = Dao.getAllOptions().size() + 1;
        Dao.addOptionToCar(options_id, id, value, price, option_type);
        return response.toString();
    }
}
