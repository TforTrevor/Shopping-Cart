package shoppingcart;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class User {
    private final String userPath = "data/Users.json";
    private UserType user;
    private static User instance;

    private User() {

    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public boolean checkUser(String username, String password) throws FileNotFoundException {
        Gson gson = new Gson();
        UserType[] usersInfo =  gson.fromJson(new FileReader(userPath), UserType[].class);
        for (UserType user : usersInfo) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.user = user;
                return true;
            }
        }
        return false;
    }

}
