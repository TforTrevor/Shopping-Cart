package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CartManager {
    //private static final String cartPath = "data/Cart.json";
    //private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static Cart yourCart = null;

    public static void setYourCart(Cart c){
        yourCart = c;
    }
    public static Cart getYourCart(){
        return yourCart;
    }
    public static void checkout(){ //function to calculate prices, and possibly tax

    };
    public static void addItem(Item item){ //add an item into the cart list
        yourCart.addItem(item);
    };
    public static void removeItem(Item item){ //remove specific item from cart list
        yourCart.removeItem(item);
    };
    public static int getCounter(){
        return yourCart.getCartSize();
    }
    public static void changeQuantity(int newQuantity, Item item){ //change the quantity of items inside the cart, based on availability
       yourCart.changeQuantity(newQuantity, item);
    };
}
