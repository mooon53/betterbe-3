package dao;

import model.CarOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CartDao {
    instance;

    private Map<Long, ArrayList<CarOrder>> carts = new HashMap<>();

    private CartDao() {
    }

    public void addOrder(Long sessionId, CarOrder order) {
        if (!carts.containsKey(sessionId)) {
            carts.put(sessionId, new ArrayList<>());
        }
        ArrayList<CarOrder> cart = carts.get(sessionId);
        cart.add(order);
    }


}
