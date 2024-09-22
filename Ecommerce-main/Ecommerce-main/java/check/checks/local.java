package check.checks;

public class local {

    String username;
    String password;
    String emailid;

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "local{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailid='" + emailid + '\'' +
                '}';
    }
}
