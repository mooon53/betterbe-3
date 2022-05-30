package resource;

import controller.Database;
import dao.CarDao;
import model.Car;
import model.Option;
import model.Rule;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.util.List;

public class CarResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    private Long id;

    public CarResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        System.out.println(id);
        this.id = Long.parseLong(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarInfo() {
        System.out.println("got request for car " + id);
        JSONObject response = new JSONObject();
        Car car = CarDao.instance.getCars().get(id);
        if(car == null) throw new RuntimeException("Get: Car with " + id +  " not found");
        JSONObject carJSON = car.getCar();
        response.put("car", carJSON);
        List<Option> options = Database.getOptions(id);
        System.out.println(options);
        JSONArray optionsJSON = new JSONArray(options);
        System.out.println(optionsJSON);
        response.put("options", optionsJSON);
        List<Rule> rules = Database.getRules(id);
        System.out.println(rules);
        JSONArray rulesJSON = new JSONArray(rules);
        System.out.println(rulesJSON);
        response.put("rules", rulesJSON);
        System.out.println(response);
        return response.toJSONString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putTodo(JAXBElement<Car> car) {
        Car c = car.getValue();
        return putAndGetResponse(c);
    }

    private Response putAndGetResponse(Car car) {
        Response res;
        if(CarDao.instance.getCars().containsKey(car.getCarId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        CarDao.instance.getCars().put(car.getCarId(), car);
        return res;
    }


}
