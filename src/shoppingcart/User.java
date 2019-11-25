package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class User {
    private final String userPath = "data/Users.json";
    private UserType user;
    private static User instance;
    private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private User() {

    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public boolean isInitialized(){
        return (this.user != null);
    }
    public boolean isVendor(){
        if(isInitialized())
            return (user.getVendor() != null);
        else
            return false;
    }

    public int checkUser(String username, String password) throws FileNotFoundException {
        List<UserType> usersInfo =  gson.fromJson(new FileReader(userPath), new TypeToken<List<UserType>>(){}.getType());
        boolean userExists = false;
        if (usersInfo.isEmpty()) return -1;
        for (UserType user : usersInfo) {
            if(user.getUsername().equals(username)) {
                userExists = true;
                if (user.getPassword().equals(password)){
                    this.user = user;
                    return 1;
                }

            }
        }
        if(!userExists) return 2;
        else return 0;
    }
    public boolean makeNewUser(String username, String password, String vendor) throws IOException {
        UserType newUser = new UserType(username,password,vendor);
        LinkedList<UserType> test = null;
        LinkedList<UserType> buffer;

        if (new File(userPath).length() != 0)
            test = gson.fromJson(new FileReader(userPath), new TypeToken<LinkedList<UserType>>(){}.getType());
        if(test != null)
            buffer = new LinkedList<>(test);
        else
            buffer = new LinkedList<>();

        for (UserType n: buffer) {
            if(n.getUsername().equals(newUser.getUsername()) || (n.getVendor() != null && n.getVendor().equals(newUser.getVendor())))
                return false;
        }

        buffer.add(newUser);

        File file = new File(userPath);
        FileWriter writer = new FileWriter(file);

        gson.toJson(buffer,writer);

        writer.flush();
        writer.close();
        this.user = newUser;
        return true;
    }

}
