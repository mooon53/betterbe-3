package resource;

import dao.Dao;
import model.Car;
import model.Option;
import model.Rule;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static utils.JSONUtils.*;
import static utils.JSONUtils.jsonStringsToRules;

@Path("/timeline")
public class TimelineResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @Path("/{car}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHistory(@PathParam("car") String id) {
        JSONObject response = new JSONObject();
        System.out.println(Dao.getCar(Long.valueOf(id)));
        Car car = jsonStringToCar(Dao.getCar(Long.valueOf(id)));
        response.put("car", car.toJSON());
        List<Option> options = jsonStringsToOptions(Dao.getHistoricalData(Long.valueOf(id)));
        System.out.println(Dao.getAllOptions(Long.valueOf(id)));
        System.out.println(options);
        for (Option option : options) {
            System.out.print(option.getCarID());
            System.out.print(option.getManufacturer());
            System.out.println(option.getValue());
        }
        JSONArray optionsJSON = new JSONArray(options);
        response.put("options", optionsJSON);
        List<Rule> rules = jsonStringsToRules(Dao.getRules(Long.valueOf(id)));
        JSONArray rulesJSON = new JSONArray(rules);
        response.put("rules", rulesJSON);
        return response.toString();
    }
}