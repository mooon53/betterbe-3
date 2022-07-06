package resource;

import dao.Dao;
import dao.SessionDao;
import model.Car;
import model.Option;
import model.Rule;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utils.JSONUtils.*;
import static utils.JSONUtils.jsonStringsToRules;

@Path("timeline")
public class TimelineResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @Path("{car}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHistory(@PathParam("car") String id, @HeaderParam("sessionId") String sessionIdString) {
        if (sessionIdString != null) {
            Long sessionId = Long.parseLong(sessionIdString);
            if (SessionDao.instance.hasSession(sessionId) && SessionDao.instance.getSession(sessionId).hasAccount() && SessionDao.instance.getSession(sessionId).getAccount().getEmployee()) {
                JSONObject response = new JSONObject();
                Car car = jsonStringToCar(Dao.getCar(Long.valueOf(id)));
                response.put("car", car.toJSON());
                List<Option> options = jsonStringsToOptions(Dao.getHistoricalData(Long.valueOf(id)));
                JSONArray optionsJSON = new JSONArray(options);
                response.put("options", optionsJSON);
                List<Rule> rules = jsonStringsToRules(Dao.getRules(Long.valueOf(id)));
                JSONArray rulesJSON = new JSONArray(rules);
                response.put("rules", rulesJSON);
                return response.toString();
            } else return null;
        } else return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String removeOption(String input) {
        JSONObject response = new JSONObject(input);
        Long carId = null;
        if (response.has("carId")) {
            carId = response.getLong("carId");
        }
        Dao.removeCar(carId);
        Dao.removeOption(carId);
        return response.toString();
    }

}