package resource;

import dao.Dao;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.util.List;

import static utils.JSONUtils.*;

public class CarResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    private final Long id;

    public CarResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = Long.parseLong(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarInfo() {
        JSONObject response = new JSONObject();
        Car car = jsonStringToCar(Dao.getCar(id));
        response.put("car", car.toJSON());
        List<Option> options = jsonStringsToOptions(Dao.getAllOptions(id));
        JSONArray optionsJSON = new JSONArray(options);
        response.put("options", optionsJSON);
        List<Rule> rules = jsonStringsToRules(Dao.getRules(id));
        JSONArray rulesJSON = new JSONArray(rules);
        response.put("rules", rulesJSON);
        return response.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCar(JAXBElement<Car> car) {
        Car c = car.getValue();
        return putAndGetResponse(c);
    }

    private Response putAndGetResponse(Car car) {
        Response res;
        List<String> cars = Dao.getAllCars();
        if(cars.size() > car.getId()) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
            Dao.addCar(car);
        }
        return res;
    }



}
