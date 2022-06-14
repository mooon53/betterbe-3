package resource;

import dao.Dao;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import static utils.JSONUtils.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
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
        JSONObject responseJSON = new JSONObject(carString);
        JSONObject carJSON =(JSONObject) responseJSON.get("car");
        JSONArray optionsRaw = responseJSON.getJSONArray("options");
        List<String> cars = Dao.getCars();
        JSONObject lastCar = new JSONObject(cars.get(cars.size() - 1));
        Long carId = lastCar.getLong("id") + 1L;
        carJSON.put("id", carId);
        List<String> oldOptions = Dao.getOptions();
        JSONObject lastOption = new JSONObject(oldOptions.get(oldOptions.size() - 1));
        Long optionId = lastOption.getLong("id") + 1L;
        List<JSONObject> optionsJSONs = new ArrayList<>();
        for (Object option : optionsRaw) {
            JSONObject optionJSON = (JSONObject) option;
            optionJSON.put("id", optionId);
            optionId++;
            optionJSON.put("car_id", carId);
            LocalDate now = LocalDate.now();
            optionJSON.put("start_date", new Date(now.getDayOfMonth(), now.getMonthValue(), now.getYear()).toString());
            optionsJSONs.add(optionJSON);
        }
        List<Option> options = jsonsToOptions(optionsJSONs);
        Car car = jsonToCar(carJSON);
        Dao.addCar(car);
        Dao.addOptions(options);
    }

    @Path("{car}")
    public CarResource getCar(@PathParam("car") String id){
        return new CarResource(uriInfo, request, id);
    }
}
