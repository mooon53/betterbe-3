package dao;

import model.Account;
import model.Session;
import org.json.JSONObject;
import utils.sessionChecker;

import static dao.Dao.*;

import java.util.*;

public class SessionDao {
    private static Map<Long, Session> sessions = new HashMap<>();

    public static Session newSession() {
        Random random = new Random();
        Date now = new Date();
        Long sessionId = random.nextLong();
        while (sessions.containsKey(sessionId)) {
            sessionId = random.nextLong();
        }
        Session session = new Session(sessionId, now.getTime() + 3600000L);
        sessions.put(sessionId, session);
        Runnable sessionChecker = new sessionChecker(session);
        Thread thread = new Thread(sessionChecker, "sessionChecker" + sessionId);
        thread.start();
        System.out.println(sessionId);
        return session;
    }

    public static void logIn(Long sessionId, String email) {
        JSONObject account = new JSONObject(getAccount(email));
        Session session = sessions.get(sessionId);
        session.setAccount(new Account(account.getString("username"), account.getString("password"), account.getBoolean("employee")));
        session.setLoggedIn(true);
        System.out.println(sessionId);
        System.out.println(email);
    }

    public static void removeSession(Long sessionId) {
        sessions.remove(sessionId);
    }
}