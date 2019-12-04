package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartManager{ //cart controller
    private static final String cartPath = "data/Cart.json";
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static Cart userCart = null;//initialize on login

    public static void initCart() throws FileNotFoundException {
        ArrayList<Item> cart = gson.fromJson(new FileReader(cartPath), new TypeToken<ArrayList<Item>>() {}.getType());
        if (cart != null) userCart = new Cart(cart);
        else userCart = new Cart();
    }

    public static Cart getUserCart(){
        return userCart;
    }

    public static double totalPrices(){ //function to calculate prices, and possibly tax
        int totalPrice = 0;
        for(Item i : userCart.getCartItems())
            totalPrice += (i.getPrice() * i.getQuantity());//returns total price of cart
        return totalPrice;
    };

    public static void checkout() throws IOException {
        Iterator<Item> iter = userCart.getCartItems().iterator();

        while (iter.hasNext()) {//iterate through the cart, (bc removing while iterating in a for loop causes errors)
            Item i = iter.next();

            iter.remove();//remove each item
        }
        emptyCart();
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

    public static void emptyCart() throws IOException {
        StoreManager store = new StoreManager();
    }
}
