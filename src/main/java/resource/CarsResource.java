package resource;

import dao.CarDao;
import model.Car;

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
    @Produces(MediaType.TEXT_XML)
    public List<Car> getCarList() {
        List<Car> cars = new ArrayList<>();
        cars.addAll(CarDao.instance.getConfiguration().values());
        return cars;
    }


    @Path("{car}")
    public CarResource getCar(@PathParam("car") String id){
        return new CarResource(uriInfo, request, id);
    }
}
