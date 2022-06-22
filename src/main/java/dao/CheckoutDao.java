package dao;

import model.CarOrder;
import model.Option;

import java.util.ArrayList;
import java.util.List;

public enum CheckoutDao {
    instance;

    private List<CarOrder> carsOrdered = new ArrayList<>();

    private CheckoutDao() {
    }

    public List<CarOrder> getModel() {
        return carsOrdered;
    }

    public synchronized void addItem(CarOrder config) {
        CarOrder order;
        boolean found = false;
        int i = 0;

        while(!found && i < carsOrdered.size()){
            order = carsOrdered.get(i);
            if(order.getCarID() == config.getCarID()){
                found = true;
            }
            i++;
        }
        if(!found){
            carsOrdered.add(config);
        }
        return;
    }


}
