package check.checks;

public class delete {


    public String username;
    public String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "delete{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
