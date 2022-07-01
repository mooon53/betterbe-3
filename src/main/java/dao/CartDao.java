package dao;

import model.Account;
import model.Configuration;
import model.Option;
import model.Session;

import static utils.JSONUtils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CartDao {
    instance;

    private Map<String, Configuration> configurations = new HashMap<>();

    private CartDao() {
    }

    private String getEmail(Long sessionId) {
        return SessionDao.instance.getSession(sessionId).getAccount().getUsername();
    }

    public Configuration getCart(Long sessionId) {
        Session session = SessionDao.instance.getSession(sessionId);
        Account account = session.getAccount();
        String email = account.getUsername();
        return configurations.get(email);
    }

    public void emptyCart(Long sessionId) {
        String email = getEmail(sessionId);
        configurations.put(email, new Configuration());
    }

    public void addOrder(Long sessionId, ArrayList<Long> optionIDs, Long carId) {
        String email = getEmail(sessionId);
        List<Option> options = jsonStringsToOptions(Dao.getOptions(optionIDs));
        Configuration configuration = new Configuration(carId, (ArrayList<Option>) options);
        configurations.put(email, configuration);
        Dao.addConfiguration(email, configuration);
    }
}