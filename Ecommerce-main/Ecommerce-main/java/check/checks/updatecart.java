package check.checks;

public class updatecart {

    public String mehendi;
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
        return "updatecart{" +
                "mehendi='" + mehendi + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMehendi() {
        return mehendi;
    }

    public void setMehendi(String mehendi) {
        this.mehendi = mehendi;
    }
}