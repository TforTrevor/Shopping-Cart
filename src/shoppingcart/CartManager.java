package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Iterator;

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
    public static void checkout(){
        Iterator<Item> iter = yourCart.getCartItems().iterator();

        while (iter.hasNext()) {//iterate through the cart, (bc removing while iterating in a for loop causes errors)
            Item i = iter.next();

            iter.remove();//remove each item
        }
        yourCart.updateCartSize();
    }

    public static void addToCart(Item item, int quantity){ //add an item into the cart list
        if(yourCart.getCartItems().contains(item)){ //when adding items to the cart, set the quantity of the item
            item.setCartQuantity(quantity + item.getCartQuantity()); //set its quantity to previous quantity + the new quantity
        }
        else{ //if the item is not already in the cart
            yourCart.addItem(item); //add it to the cart AND update its quantity
            item.setCartQuantity(quantity);
        }
        yourCart.updateCartSize(); //finally, update the cart size with the new quantities
    };
    public static void removeFromCart(Item item){ //remove specific item from cart list
        yourCart.removeItem(item);
    };
    public static int getCounter(){
        return yourCart.getCartSize();
    }
    public static void changeQuantity(int newQuantity, Item item){ //change the quantity of items inside the cart, based on availability
       yourCart.changeQuantity(newQuantity, item);
    };

    public static ArrayList<Item> getCart(){return yourCart.getCartItems();}//receive the cart items
}
