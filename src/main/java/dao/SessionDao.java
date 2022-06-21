package dao;

import model.Session;
import utils.sessionChecker;

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
        return session;
    }

    public static void removeSession(Long sessionId) {
        sessions.remove(sessionId);
    }
}