package utils;

import static dao.SessionDao.*;

import model.Session;

import java.util.Date;

public class sessionChecker implements Runnable {
    Session session;
    Date now = new Date();

    public sessionChecker() {};

    public sessionChecker(Session session) {
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
        removeSession(session.getSessionId());
    }
}
