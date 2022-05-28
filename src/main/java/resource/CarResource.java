package resource;

import dao.CarDao;
import model.Car;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;

public class CarResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    String id;

    public CarResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public Car getTodoHTML() {
        Car car = CarDao.instance.getConfiguration().get(id);
        if(car==null)
            throw new RuntimeException("Get: Car with " + id +  " not found");
        return car;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putTodo(JAXBElement<Car> car) {
        Car c = car.getValue();
        return putAndGetResponse(c);
    }

    private Response putAndGetResponse(Car car) {
        Response res;
        if(CarDao.instance.getConfiguration().containsKey(car.getCarId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        CarDao.instance.getConfiguration().put(car.getCarId(), car);
        return res;
    }


}
