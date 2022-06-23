package resource;

import dao.CartDao;
import model.Cart;
import model.Option;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("cart")
public class CartResource {

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCart(String input) {
        Long sessionId = Long.parseLong(input);
        Cart cart = CartDao.instance.getCart(sessionId);
        JSONObject response = new JSONObject();
        response.put("carId", cart.getCarObj().getId());
        JSONArray options = new JSONArray(cart.getOptions());
        response.put("options", options);
        return response.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addToCart(String input){
        JSONObject json = new JSONObject(input);
        Long sessionId = Long.parseLong(json.getString("sessionId"));
        Long optionId = json.getLong("optionId");
        Long carId = json.getLong("carId");
        CartDao.instance.addOrder(sessionId, optionId, carId);
        return getCart(sessionId.toString());
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteShoppingCart(String input) {
        JSONObject response = new JSONObject(input);
        Long sessionId = Long.parseLong(response.getString("sessionId"));
        CartDao.instance.emptyCart(sessionId);
        }

    /*@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public double total(String input){
        Long sessionId = Long.parseLong(input);
        Cart cart = CartDao.instance.getCart(sessionId);
        double cost = cart.getCarObj().getPrice();
        ArrayList<Option> options = cart.getOptions();
        for(Option option : options){
            cost += option.getPrice();
        }
        return  cost;
    }*/
}
