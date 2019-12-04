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
    private static final String cartPath = "data/Cart.json";
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static Cart userCart = null;//initialize on login

    public static void initCart() throws FileNotFoundException {
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

    public static void addToCart(Item item, int quantity) throws IOException, CloneNotSupportedException { //add an item into the cart list
        Item clone = (Item) item.clone();
        boolean match = false;
        clone.setQuantity(quantity);
        for (Item cartItem :userCart.getCartItems()) {
            if (clone.getID() == cartItem.getID()) {
                cartItem.setAvailableQuantity(item.getQuantity());
                if(cartItem.getAvailableQuantity() - quantity > 0)
                    cartItem.setQuantity(cartItem.getQuantity()+quantity);
                match = true;
                break;
            }
        }
        if (!match) userCart.addItem(clone);
        saveCart();
    }

    private static void saveCart() throws IOException {
        File file = new File(cartPath);
        FileWriter writer = new FileWriter(file);

        gson.toJson(userCart.getCartItems(), writer);

        writer.flush();
        writer.close();
    }

    public static int removeFromCart(Item item) throws IOException { //remove specific item from cart list
        for (Item cartItem :userCart.getCartItems()) {
            if (item.getID() == cartItem.getID()) {
                int returnQuantity = cartItem.getQuantity();
                userCart.removeItem(cartItem);
                saveCart();
                return returnQuantity;
            }
        }
        return -1;
    }

    public static int getCounter() {
        return userCart.getCartSize();
    }

    public static ArrayList<Item> getCart(){
        return userCart.getCartItems();
    }//receive the cart items

}
