package dao;

import model.Account;
import model.Cart;
import model.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum CartDao {
    instance;

    private Map<String, Cart> carts = new HashMap<>();

    private CartDao() {
    }

    private String getEmail(Long sessionId) {
        return SessionDao.instance.getSession(sessionId).getAccount().getUsername();
    }

    public Cart getCart(Long sessionId) {
        Session session = SessionDao.instance.getSession(sessionId);
        Account account = session.getAccount();
        String email = account.getUsername();
        System.out.println("getCart");
        System.out.println(sessionId);
        System.out.println(email);
        System.out.println(carts);
        return carts.get(email);
    }

    public void emptyCart(Long sessionId) {
        String email = getEmail(sessionId);
        carts.put(email, new Cart());
    }

    public void addOrder(Long sessionId, Long orderId, Long carId) {
        String email = getEmail(sessionId);
        if (!carts.containsKey(email)) {
            carts.put(email, new Cart(carId));
        }
        System.out.println(carts);
        System.out.println(email);
        Cart cart = carts.get(email);
        System.out.println(cart);
        cart.addOption(orderId);
    }


}
