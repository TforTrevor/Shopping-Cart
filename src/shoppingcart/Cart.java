package shoppingcart;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Cart object to hold the items in the cart.
 */
public class Cart { //cart model
    private ArrayList<Item> cartItems;

    /**
     * initializes the cart.
     */
    public Cart(){
        cartItems = new ArrayList<>();
    }

    /**
     * Initializes the cart with an existing Arraylist.
     * @param cart ArrayList to Initialize from.
     */
    public Cart(ArrayList<Item> cart){
        cartItems = new ArrayList<>(cart);
    }

    /**
     * gets the quantity of items in the cart.
     * @return quantity of items in cart.
     */
    public int getCartSize() {
        int sum = 0;
        for (Item cartItem :cartItems) {
            sum += cartItem.getQuantity();
        }
        return sum;
    }

    /**
     * adds an item into the cart.
     * @param item The item to add.
     */
    public void addItem(Item item){
        cartItems.add(item);
    }

    /**
     * removes an item into the cart.
     * @param item The item to add.
     */
    public void removeItem(Item item){
        cartItems.remove(item);
    }

    /**
     * gets the cart.
     * @return the cart.
     */
    public ArrayList<Item> getCartItems(){
        return cartItems;
    }

}

