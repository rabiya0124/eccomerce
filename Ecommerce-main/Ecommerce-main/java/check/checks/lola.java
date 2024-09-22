package check.checks;

public class lola {


    public String username;

    public String getId() {
        return username;
    }

    @Override
    public String toString() {
        return "lola{" +
                "id='" + username + '\'' +
                '}';
    }

    public void setId(String id) {
        this.username = id;
    }
}
