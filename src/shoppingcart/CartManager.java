package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class CartManager{
    //private static final String cartPath = "data/Cart.json";
    //private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static Cart yourCart = null;

    public static void setYourCart(Cart c){
        yourCart = c;
    }
    public static Cart getYourCart(){
        return yourCart;
    }
    public static double checkout(){ //function to calculate prices, and possibly tax
        int totalPrice = 0;
        for(Item i : yourCart.getCartItems())
            totalPrice += (i.getPrice() * i.getCartQuantity());
        return totalPrice;
    };
    public static void addToCart(Item item, int quantity){ //add an item into the cart list
        if(yourCart.getCartItems().contains(item)){
            item.setCartQuantity(quantity + item.getCartQuantity());
        }
        else{
            yourCart.addItem(item);
            item.setCartQuantity(quantity + item.getCartQuantity());
        }
        yourCart.updateCartSize();
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

    public static ArrayList<Item> getCart(){return yourCart.getCartItems();}
}
