package resource;

import dao.CartDao;
import model.CarOrder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CarOrder> getCart(String input) {
        JSONObject response = new JSONObject(input);
        Long sessionId = Long.parseLong(response.getString("sessionId"));
        return CartDao.instance.getCart(sessionId);
    }

    @DELETE
    public void deleteShoppingCart(){
//        CartDao.instance.getModel().clear();
        }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public double total(){
//        Map<Long, ArrayList<CarOrder>> cars = CartDao.instance.getModel();
        double cost = 0;
//        for(CarOrder item : cars){
//            cost += 1;
//        }
        return  cost;
    }
}
