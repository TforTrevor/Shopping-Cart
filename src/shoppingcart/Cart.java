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
    public void removeItem(Item item){ //remove specific item from cart list
        cartItems.remove(item);
    };
    public void changeQuantity(int newQuantity, Item item){ //change the quantity of items inside the cart, based on availability
        for(Item i : cartItems){
            if (i.getName().equals(item.getName())) {
                i.setAvailableQuantity(newQuantity);
            }
        }

    };

}