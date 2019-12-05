package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages everything relating to user. It does not have to be initialized.
 */
public class UserManager {

    private static final String userPath = "data/Users.json";
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static User loggedInUser = null;

    /**
     * This function will check the file to see if the user exists and has the right password. If it is successful,
     * the function will return the user object. else, it will return null.
     * @param username username to check.
     * @param password password to check.
     * @return the user object if it was successful, else null.
     * @throws FileNotFoundException if the Users.json file could not be found.
     */
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

    /**
     * This function will create a new user and insert it into the json.
     * @param username Username of the new user.
     * @param password Password of the new user.
     * @param vendor Vendor name of the new user. can be null if it is a regular user.
     * @return the new user object.
     * @throws IOException if the file was not found or it could not write to file.
     */
    public static User makeNewUser(String username, String password, String vendor) throws IOException {
        User newUser = new User(username, password, vendor);
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
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        gson.toJson(buffer, writer);

        writer.flush();
        writer.close();

        if(vendor != null)
            new File("data/Vendors/"+vendor+".json").createNewFile();

        return newUser;
    }

    /**
     * Sets the logged in user.
     * @param user the user.
     */
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    /**
     * Gets the logged in user.
     * @return the user.
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Changes the password in the json for the user.
     * @param newPassword new password for the user.
     * @throws IOException if it could not find or write to the file.
     */
    public static void changePassword(String newPassword) throws IOException {
        User updated = new User(loggedInUser.getUsername(),newPassword,loggedInUser.getVendor());
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
