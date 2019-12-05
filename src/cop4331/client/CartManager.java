package cop4331.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * Manages the cart and the files for each user.
 */
public class CartManager{ //cart controller
    private static final String cartPath = "data/Cart/"+UserManager.getLoggedInUser().getUsername()+".json";
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static Cart userCart = null;//initialize on login

    /**
     * Initializes the cart with the user cart saved from the JSON.
     * @throws IOException if the folder or file could not be generated or read.
     */
    public static void initCart() throws IOException {
        new File(cartPath).getParentFile().mkdirs();
        new File(cartPath).createNewFile();
        ArrayList<Item> cart = gson.fromJson(new FileReader(cartPath), new TypeToken<ArrayList<Item>>() {}.getType());
        if (cart != null && !cart.isEmpty()) userCart = new Cart(cart);
        else userCart = new Cart();
    }

    /**
     * gets the user cart.
     * @return the user cart.
     */
    public static Cart getUserCart(){
        return userCart;
    }

    /**
     * calculates the total price from the items of the cart.
     * @return the total calculated price.
     */
    public static double totalPrices(){
        double totalPrice = 0;
        for(Item i : userCart.getCartItems())
            totalPrice += (i.getPrice() * i.getQuantity());
        return totalPrice;
    }

    /**
     * Sets the quantity now that the user has purchased, and then copies it to receipt.
     * @throws IOException if the file could not be copied.
     */
    public static void checkout() throws IOException {
        for(Item cartItem: userCart.getCartItems()){
            for(Item storeItem: new StoreManager().getItems()){
                if(cartItem.getID() == storeItem.getID()){
                    StoreManager.saveQuantity(storeItem,storeItem.getQuantity()-cartItem.getQuantity());
                }
            }
        }
        Path src = Paths.get(new File(cartPath).toURI());
        int dest;
        new File("data/Receipts").getParentFile().mkdirs();
        if(new File("data/Receipts").list() != null)
            dest = new File("data/Receipts").list().length;
        else
            dest = 0;
        Path dst = Paths.get("data/Receipts/Receipt-"+dest+".json");
        Files.copy(src,dst, StandardCopyOption.REPLACE_EXISTING);
        userCart.getCartItems().clear();
        new FileWriter(cartPath).close();
    }

    /**
     * Adds the item into cart and updates the available quantity in the StoreManager. If the item already exists,
     * then the quantity will be added to the existing cart item.
     * @param item the item to add.
     * @param addQuantity the quantity to add.
     * @throws IOException If it could not save the file.
     * @throws CloneNotSupportedException if the Item could not be cloned.
     */
    public static void addToCart(Item item, int addQuantity) throws IOException, CloneNotSupportedException { //add an item into the cart list
        Item clone = (Item) item.clone();

        boolean match = false;
        clone.setQuantity(addQuantity);

        for (Item cartItem :userCart.getCartItems()) {
            if (clone.getID() == cartItem.getID()) {
                new StoreManager().getItems().forEach((Item storeItem) -> {
                    if (storeItem.getID() == cartItem.getID()) {
                        cartItem.setAvailableQuantity(storeItem.getAvailableQuantity() - addQuantity);
                        if(cartItem.getAvailableQuantity() >= 0) {
                            cartItem.setQuantity(cartItem.getQuantity() + addQuantity);
                            try {
                                StoreManager.saveAvailableQuantity(storeItem, storeItem.getAvailableQuantity() - addQuantity);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                            cartItem.setAvailableQuantity(storeItem.getAvailableQuantity());
                    }
                });
                match = true;
                break;
            }
        }

        if (!match) {
            userCart.addItem(clone);
            new StoreManager().getItems().forEach((Item storeItem) -> {
                if(clone.getID() == storeItem.getID()) {
                    clone.setAvailableQuantity(storeItem.getAvailableQuantity() - addQuantity);
                    try {
                        StoreManager.saveAvailableQuantity(storeItem, storeItem.getAvailableQuantity() - addQuantity);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        saveCart();
    }

    /**
     * Saves the cart items into the user's cart.
     * @throws IOException if it could not be saved.
     */
    private static void saveCart() throws IOException {
        File file = new File(cartPath);
        FileWriter writer = new FileWriter(file);

        gson.toJson(userCart.getCartItems(), writer);

        writer.flush();
        writer.close();
    }

    /**
     * Subtracts the quantity from the cart, and also updates the store manager to add the available quantity.
     * If the quantity becomes 0, removes it from the cart.
     * @param item item to subtract/lower
     * @param removeQuantity the amount to lower.
     * @throws IOException If it could not save the file.
     */
    public static void removeFromCart(Item item, int removeQuantity) throws IOException { //remove specific item from cart list
        for (Item cartItem :userCart.getCartItems()) {
            if (item.getID() == cartItem.getID()) {
                cartItem.setQuantity(cartItem.getQuantity() - removeQuantity);
                new StoreManager().getItems().forEach((Item storeItem) ->{
                        if(storeItem.getID() == cartItem.getID()){
                            cartItem.setAvailableQuantity(storeItem.getAvailableQuantity()+removeQuantity);
                            try {
                                StoreManager.saveAvailableQuantity(storeItem,storeItem.getAvailableQuantity() + removeQuantity);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                });
                if(cartItem.getQuantity() <= 0)
                    userCart.removeItem(cartItem);
                saveCart();
            }
        }
    }

    /**
     * gets the size of the cart.
     * @return size of the cart.
     */
    public static int getCounter() {
        return userCart.getCartSize();
    }

    /**
     * gets the cart.
     * @return the cart.
     */
    public static ArrayList<Item> getCart(){
        return userCart.getCartItems();
    }//receive the cart items

}
