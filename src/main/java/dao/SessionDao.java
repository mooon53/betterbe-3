package dao;

import model.Account;
import model.Session;
import org.json.JSONObject;
import utils.SessionChecker;

import static dao.Dao.*;

import java.util.*;

public enum SessionDao {
    instance;

    private Map<Long, Session> sessions = new HashMap<>();

    public Session newSession() {
        Random random = new Random();
        Date now = new Date();
        Long sessionId = random.nextLong();
        while (sessions.containsKey(sessionId)) {
            sessionId = random.nextLong();
        }
        Session session = new Session(sessionId, now.getTime() + 3600000);
        System.out.println(sessionId);
        System.out.println(session.getSessionId());
        System.out.println(sessions);
        sessions.put(sessionId, session);
        Runnable sessionChecker = new SessionChecker(session);
        Thread thread = new Thread(sessionChecker, "sessionChecker" + sessionId);
        thread.start();
        System.out.println(sessionId);
        return session;
    }

    public Session getSession(Long sessionId) {
        return sessions.get(sessionId);
    }

    public void logIn(Long sessionId, String email) {
        System.out.println("sup");
        JSONObject account = new JSONObject(getAccount(email));
        System.out.println("ur cute");
        Session session = sessions.get(sessionId);
        System.out.println(":)");
        System.out.println("{" + sessionId);
        System.out.println(sessions.toString());
        System.out.println(session);
        session.setAccount(new Account(account.getString("email"), account.getString("password"), account.getBoolean("employee")));
        System.out.println("cool");
        session.setLoggedIn(true);
        System.out.println(sessionId);
        System.out.println(email);
    }

    public void removeSession(Long sessionId) {
        sessions.remove(sessionId);
    }
}