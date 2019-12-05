package cop4331.client;

/**
 * User class to be used with Gson when importing json data.
 */
public class User {
    private String username;
    private String password;
    private String vendor;

    /**
     * Initialization when making a user class.
     * @param username Username of the user.
     * @param password Password of the user.
     * @param vendor Vendor name of the user. If it is null, the user is not a vendor.
     */
    public User(String username, String password, String vendor) {
        this.username = username;
        this.password = password;
        this.vendor = vendor;
    }

    /**
     * Gets the username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Gets the vendor name.
     * @return vendor name.
     */
    public String getVendor() {
        return vendor;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        User userObj = (User) obj;
        return (userObj.getUsername().equals(username) && userObj.getPassword().equals(password) && userObj.getVendor().equals(vendor));
    }
}
