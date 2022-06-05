package resource;

import dao.CarDao;
import dao.Dao;
import model.Car;
import utils.JSONUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
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
        return JSONUtils.jsonStringsToCars(carsStrings);
    }


    @Path("{car}")
    public CarResource getCar(@PathParam("car") String id){
        return new CarResource(uriInfo, request, id);
    }
}
