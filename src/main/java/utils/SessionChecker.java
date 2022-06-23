package utils;

import dao.SessionDao;
import model.Session;

import java.util.Date;

public class SessionChecker implements Runnable {
    Session session;
    Date now = new Date();

    public SessionChecker() {};

    public SessionChecker(Session session) {
        this.session = session;
    }

    public void run() {
        while (session.getExpiration() > now.getTime()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SessionDao.instance.removeSession(session.getSessionId());
    }
}
