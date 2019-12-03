package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static final String userPath = "data/Users.json";
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static User loggedInUser = null;


    public static User checkUser(String username, String password) throws FileNotFoundException {
        List<User> usersInfo = gson.fromJson(new FileReader(userPath), new TypeToken<List<User>>() {
        }.getType());
        for (User user : usersInfo) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static User makeNewUser(String username, String password, String vendor, boolean isVendor) throws IOException {
        User newUser = new User(username, password, vendor, isVendor);
        ArrayList<User> test = null;
        ArrayList<User> buffer;

        if (new File(userPath).length() != 0)
            test = gson.fromJson(new FileReader(userPath), new TypeToken<ArrayList<User>>() {
            }.getType());
        if (test != null)
            buffer = new ArrayList<>(test);
        else
            buffer = new ArrayList<>();

        for (User user : buffer) {
            if (user.getUsername().equals(newUser.getUsername()) || (user.getVendor() != null && user.getVendor().equals(newUser.getVendor())))
                return null;
        }

        buffer.add(newUser);

        File file = new File(userPath);
        FileWriter writer = new FileWriter(file);

        gson.toJson(buffer, writer);

        writer.flush();
        writer.close();
        return newUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void changePassword(String newPassword) throws IOException {
        User updated = new User(loggedInUser.getUsername(),newPassword,loggedInUser.getVendor(), loggedInUser.checkVendor());
        ArrayList<User> userList = gson.fromJson(new FileReader(userPath), new TypeToken<ArrayList<User>>() {}.getType());
        for (User user : userList) {
            if (user.equals(loggedInUser)){
                userList.remove(user);
                userList.add(updated);

                File file = new File(userPath);
                FileWriter writer = new FileWriter(file);
                gson.toJson(userList, writer);
                writer.flush();
                writer.close();

                setLoggedInUser(updated);
                return;
            }
        }
    }
}
