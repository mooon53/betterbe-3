package model;

public class Session {
    Long sessionId;
    Account account;
    Boolean loggedIn;
    Long expiration;

    public Session() {}

    public Session(Long sessionId, Long expiration) {
        this.sessionId = sessionId;
        this.expiration = expiration;
        this.loggedIn = false;
    }
    public Session(Long sessionId, Account account, Long expiration) {
        this.sessionId = sessionId;
        this.account = account;
        this.loggedIn = true;
        this.expiration = expiration;
    }

    public Long getSessionId() {return sessionId;}
    public void setSessionId(Long sessionId) {this.sessionId = sessionId;}

    public Account getAccount() {return account;}
    public void setAccount(Account account) {this.account = account;}

    public Boolean getLoggedIn() {return loggedIn;}
    public void setLoggedIn(Boolean loggedIn) {this.loggedIn = loggedIn;}

    public Long getExpiration() {return expiration;}
    public void setExpiration(Long expiration) {this.expiration = expiration;}
}