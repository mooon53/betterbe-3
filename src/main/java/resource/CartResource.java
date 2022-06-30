package resource;

import dao.CartDao;
import model.Configuration;
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
        System.out.println(input);
        Long sessionId = Long.parseLong(input);
        return CartDao.instance.getCart(sessionId).toJSON().toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addToCart(String input) {
        JSONObject json = new JSONObject(input);
        System.out.println(input);
        Long sessionId = Long.parseLong(json.getString("sessionId"));
        JSONArray optionsJSON = json.getJSONArray("options");
        ArrayList<Long> optionIDs = new ArrayList<>();
        for (int i = 0; i < optionsJSON.length(); i++) {
            optionIDs.add(optionsJSON.getLong(i));
        }
        Long carId = json.getLong("carId");
        CartDao.instance.addOrder(sessionId, optionIDs, carId);
        return CartDao.instance.getCart(sessionId).toJSON().toString();
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
