package model;

public class Session {
    Long sessionId;
    String account;
    Boolean loggedIn;

    public Long getSessionId() {return sessionId;}

    public void setSessionId(Long sessionId) {this.sessionId = sessionId;}

    public String getAccount() {return account;}

    public void setAccount(String account) {this.account = account;}

    public Boolean getLoggedIn() {return loggedIn;}

    public void setLoggedIn(Boolean loggedIn) {this.loggedIn = loggedIn;}
}