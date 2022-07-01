package resource;

import dao.Dao;
import dao.SessionDao;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import static utils.JSONUtils.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Path("/cars")
public class CarsResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCars() {
        List<String> carsStrings = Dao.getCars();
        return jsonStringsToCars(carsStrings);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCar(String carString){
        System.out.println(carString);
        JSONObject responseJSON = new JSONObject(carString);
        Long sessionId = Long.parseLong((String) responseJSON.get("sessionId"));
        Session session = SessionDao.instance.getSession(sessionId);
        if (session.getLoggedIn() && session.getAccount().getEmployee()) {
            JSONObject carJSON = (JSONObject) responseJSON.get("car");
            JSONArray optionsRaw = responseJSON.getJSONArray("options");
            JSONArray rulesRaw = responseJSON.getJSONArray("rules");
            List<String> cars = Dao.getAllCars();
            JSONObject lastCar = new JSONObject(cars.get(cars.size() - 1));
            Long carId = lastCar.getLong("id") + 1L;
            carJSON.put("id", carId);
            List<String> oldOptions = Dao.getAllOptions();
            JSONObject lastOption = new JSONObject(oldOptions.get(oldOptions.size() - 1));
            Long optionId = lastOption.getLong("id") + 1L;
            List<JSONObject> rulesJSONS = new ArrayList<>();
            for (Object rule : rulesRaw) {
                JSONObject ruleJSON = (JSONObject) rule;
                JSONArray options = ruleJSON.getJSONArray("options");
                JSONArray newOptions = new JSONArray();
                ruleJSON.remove("options");
                for (Object option : options) {
                    Long oldOption = Integer.toUnsignedLong((Integer) option);
                    newOptions.put(oldOption + optionId);
                }
                ruleJSON.put("options", newOptions);
                ruleJSON.put("car_id", carId);
                rulesJSONS.add(ruleJSON);
            }
            List<JSONObject> optionsJSONs = new ArrayList<>();
            for (Object option : optionsRaw) {
                JSONObject optionJSON = (JSONObject) option;
                optionJSON.put("id", optionId);
                optionId++;
                optionJSON.put("manufacturer", "null");
                optionJSON.put("car_id", carId);
                LocalDate now = LocalDate.now();
                optionJSON.put("start_date", new Date(now.getDayOfMonth(), now.getMonthValue(), now.getYear()).toString());
                optionsJSONs.add(optionJSON);
            }
            List<Option> options = jsonsToOptions(optionsJSONs);
            List<Rule> rules = jsonsToRules(rulesJSONS);
            Car car = jsonToCar(carJSON);
            Dao.addCar(car);
            Dao.addOptions(options);
            Dao.addRules(rules);
        }
    }

    @Path("{car}")
    public CarResource getCar(@PathParam("car") String id) {
        return new CarResource(uriInfo, request, id);
    }


}
