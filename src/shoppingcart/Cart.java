package shoppingcart;

import java.util.ArrayList;

public class Cart { //cart model
    private ArrayList<Item> cartItems; //array list of items
    private int cartSize;//the cart size (same as cartItems.size)
    public Cart(){
        cartItems = new ArrayList<>(); //list of items in the cart
        cartSize = cartItems.size();
    }
    public int getCartSize() {
        return cartSize;
    }
    public void addItem(Item item){
        cartItems.add(item);
        updateCartSize();
    }
    public void updateCartSize(){ //updates the cart size variable based on item quantity and items themselves
        int size = 0;
        for(Item i : cartItems){//add the quantity of each item to the size
            size += i.getCartQuantity();
        }
        cartSize = size;
    }
    public void removeItem(Item item){ //remove specific item from cart list
        cartItems.remove(item);
        updateCartSize();
    }
    public void changeQuantity(int newQuantity, Item item){ //change the quantity of items inside the cart, based on availability
        for(Item i : cartItems){//for each item
            if (i.equals(item)) {//if it is equal
                i.setAvailableQuantity(newQuantity); //set the quantity
            }
        }

    }
    public ArrayList<Item> getCartItems(){
        return cartItems;
    }//return the cart itself

}

