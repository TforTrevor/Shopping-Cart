package shoppingcart;

import java.io.IOException;
import java.util.ArrayList;

import static shoppingcart.CartManager.updateCartSize;


public class Cart { //cart model
    private ArrayList<Item> cartItems; //array list of items
    private int cartSize;//the cart size (same as cartItems.size)

    public Cart(){
        cartItems = new ArrayList<>(); //list of items in the cart
        cartSize = 0;
    }
    public int getCartSize() {
        try {
            updateCartSize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cartSize;
    }
    public void setCartSize(int size){
        this.cartSize = size;
    }
    public void addItem(Item item){
        cartItems.add(item);
    }

    public void removeItem(Item item){ //remove specific item from cart list
        cartItems.remove(item);
    }

    public ArrayList<Item> getCartItems(){
        return cartItems;
    }//return the cart itself

}

