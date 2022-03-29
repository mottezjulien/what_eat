package what.eat.user.access.domain;

public class QueryAskAccess {

    private final String login;
    private final String password;
    private final String ip;

    public QueryAskAccess(String login, String password, String ip) {
        this.login = login;
        this.password = password;
        this.ip = ip;
    }

    public String login() {
        return login;
    }

    public String password() {
        return password;
    }

    public String ip() {
        return ip;
    }
}
