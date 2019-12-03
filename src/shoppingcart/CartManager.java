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

    private static Cart yourCart = null;//initialize on login

    public static void setYourCart(Cart c){
        yourCart = c;
    }//used to initialize
    public static Cart getYourCart(){
        return yourCart;
    }
    public static double totalPrices(){ //function to calculate prices, and possibly tax
        int totalPrice = 0;
        for(Item i : yourCart.getCartItems())
            totalPrice += (i.getPrice() * i.getCartQuantity());//returns total price of cart
        return totalPrice;
    };
    public static void checkout() throws IOException {
        Iterator<Item> iter = yourCart.getCartItems().iterator();

        while (iter.hasNext()) {//iterate through the cart, (bc removing while iterating in a for loop causes errors)
            Item i = iter.next();

            iter.remove();//remove each item
        }
        emptyCart();
    }

    public static void addToCart(Item item, int quantity) throws IOException { //add an item into the cart list
        if(!yourCart.getCartItems().contains(item)){ //if the item is not already in the cart
            yourCart.addItem(item); //add it to the cart
        }
        item.setCartQuantity(quantity + item.getCartQuantity()); //set its quantity to previous quantity + the new quantity
        updateCartSize(); //finally, update the cart size with the new quantities
    };
    public static void removeFromCart(Item item){ //remove specific item from cart list
        yourCart.removeItem(item);
    };
    public static int getCounter(){
        return yourCart.getCartSize();
    }


    public static ArrayList<Item> getCart(){return yourCart.getCartItems();}//receive the cart items

    public static void updateCartSize() throws IOException {
        StoreManager store = new StoreManager();
        ArrayList<Integer>quantities = store.getCartQuantities();
        int sum = 0;
        for (Integer x : quantities) {
            sum += x;
        }
        yourCart.setCartSize(sum);
    }
    public static void emptyCart() throws IOException {
        StoreManager store = new StoreManager();
        store.removeCartQuantities();
        yourCart.setCartSize(0);
    }
}
