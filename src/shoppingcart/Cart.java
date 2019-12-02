package shoppingcart;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> cartItems;
    private int cartSize;
    public Cart(){
        cartItems = new ArrayList<>(); //list of items in the cart
        cartSize = cartItems.size();
    }
    public int getCartSize() {
        return cartSize;
    }
    public void addItem(Item item){
        cartItems.add(item);
    }
    public void updateCartSize(){
        int size = 0;
        for(Item i : cartItems){
            size += i.getCartQuantity();
        }
        cartSize = size;
    }
    public void removeItem(Item item){ //remove specific item from cart list
        cartItems.remove(item);
        item.setCartQuantity(0);
    };
    public void changeQuantity(int newQuantity, Item item){ //change the quantity of items inside the cart, based on availability
        for(Item i : cartItems){
            if (i.getName().equals(item.getName())) {
                i.setAvailableQuantity(newQuantity);
            }
        }

    };
    public ArrayList<Item> getCartItems(){
        return cartItems;
    }

}

