package shoppingcart;

import java.io.IOException;
import java.util.ArrayList;


public class Cart { //cart model
    private ArrayList<Item> cartItems; //array list of items

    public Cart(){
        cartItems = new ArrayList<>(); //list of items in the cart
    }

    public Cart(ArrayList<Item> cart){ cartItems.addAll(cart); }

    public int getCartSize() {
        int sum = 0;
        for (Item cartItem :cartItems) {
            sum += cartItem.getQuantity();
        }
        return sum;
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

