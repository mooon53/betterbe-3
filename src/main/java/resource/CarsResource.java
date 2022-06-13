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
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCar(String carString){
        System.out.println("car added");
        System.out.println(carString);
        Car car = jsonStringToCar(carString);
        System.out.println(car);
        Dao.addCar(car);
    }

    @Path("{car}")
    public CarResource getCar(@PathParam("car") String id){
        return new CarResource(uriInfo, request, id);
    }
}
