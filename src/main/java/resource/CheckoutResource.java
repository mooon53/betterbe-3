package resource;

import dao.CheckoutDao;
import model.CarOrder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("checkout")
public class CheckoutResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCart(List<CarOrder> input){
        for(CarOrder item : input){
            CheckoutDao.instance.addItem(item);
            System.out.println(item+"\n");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarOrder> getCart() {return CheckoutDao.instance.getModel();}

    @DELETE
    public void deleteShoppingCart(){CheckoutDao.instance.getModel().clear();}

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public double total(){
        List<CarOrder> cars = CheckoutDao.instance.getModel();
        double cost = 0;
        for(CarOrder item : cars){
            cost += 1;
        }
        return  cost;
    }
}
