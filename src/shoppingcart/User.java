package shoppingcart;

public class User {
    private String username;
    private String password;
    private String vendor;

    public User(String username, String password, String vendor){
        this.username = username;
        this.password = password;
        this.vendor = vendor;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getVendor() {
        return vendor;
    }
}
