package resource;

import dao.Dao;
import model.Car;
import org.json.JSONObject;

import static utils.JSONUtils.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
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
        System.out.println("car added");
        System.out.println(carString);
        JSONObject carJSON = new JSONObject(carString);
        List<String> cars = Dao.getCars();
        JSONObject lastCar = new JSONObject(cars.get(cars.size() - 1));
        carJSON.put("id", lastCar.getLong("id") + 1L);
        Car car = jsonToCar(carJSON);
        System.out.println(car);
        Dao.addCar(car);
    }

    @Path("{car}")
    public CarResource getCar(@PathParam("car") String id){
        return new CarResource(uriInfo, request, id);
    }
}
