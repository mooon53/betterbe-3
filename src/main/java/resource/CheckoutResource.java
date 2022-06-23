package resource;

import dao.CartDao;
import model.CarOrder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("checkout")
public class CheckoutResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToCart(String input){
        JSONObject JSON = new JSONObject(input);
        JSONArray options = JSON.getJSONArray("chosenOptions");
        for (Object i : options) {
            int item = (Integer) i;
            //TODO: add items to cart
            System.out.println(item+"\n");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarOrder> getCart() {return CartDao.instance.getModel();}

    @DELETE
    public void deleteShoppingCart(){
        CartDao.instance.getModel().clear();}

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public double total(){
        List<CarOrder> cars = CartDao.instance.getModel();
        double cost = 0;
        for(CarOrder item : cars){
            cost += 1;
        }
        return  cost;
    }
}
