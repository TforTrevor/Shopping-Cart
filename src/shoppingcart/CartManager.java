package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CartManager{ //cart controller
    private static final String cartPath = "data/Cart/"+UserManager.getLoggedInUser().getUsername()+".json";
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static Cart userCart = null;//initialize on login

    public static void initCart() throws IOException {
        new File(cartPath).createNewFile();
        ArrayList<Item> cart = gson.fromJson(new FileReader(cartPath), new TypeToken<ArrayList<Item>>() {}.getType());
        if (cart != null && !cart.isEmpty()) userCart = new Cart(cart);
        else userCart = new Cart();
    }

    public static Cart getUserCart(){
        return userCart;
    }

    public static double totalPrices(){ //function to calculate prices, and possibly tax
        double totalPrice = 0;
        for(Item i : userCart.getCartItems())
            totalPrice += (i.getPrice() * i.getQuantity());//returns total price of cart
        return totalPrice;
    };

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
        if(new File("data/Receipts").list() != null)
            dest = new File("data/Receipts").list().length;
        else
            dest = 0;
        Path dst = Paths.get("data/Receipts/Receipt-"+dest+".json");
        Files.copy(src,dst, StandardCopyOption.REPLACE_EXISTING);
        userCart.getCartItems().clear();
        new FileWriter(cartPath).close();
    }

    public static void addToCart(Item item, int addQuantity) throws IOException, CloneNotSupportedException { //add an item into the cart list
        Item clone = (Item) item.clone();

        boolean match = false;
        clone.setQuantity(addQuantity);

        for (Item cartItem :userCart.getCartItems()) {
            if (clone.getID() == cartItem.getID()) {
                new StoreManager().getItems().forEach((Item storeItem) -> {
                    if (storeItem.getID() == cartItem.getID()) {
                        cartItem.setAvailableQuantity(storeItem.getAvailableQuantity() - addQuantity);
                        if(cartItem.getAvailableQuantity() > 0) {
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

    private static void saveCart() throws IOException {
        File file = new File(cartPath);
        FileWriter writer = new FileWriter(file);

        gson.toJson(userCart.getCartItems(), writer);

        writer.flush();
        writer.close();
    }

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

    public static int getCounter() {
        return userCart.getCartSize();
    }

    public static ArrayList<Item> getCart(){
        return userCart.getCartItems();
    }//receive the cart items

}
