public class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValid() {
        return username.matches("[a-zA-Z0-9]+") && password.matches("[a-zA-Z0-9]+");
    }

    @Override
    public String toString() {
        return username + ":" + password;
    }
}


