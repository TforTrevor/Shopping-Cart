package shoppingcart;

public class Cart {
    ArrayList<Item> cartItems;
    int cartSize;
    Cart(){
        cartItems = new ArrayList<>(); //list of items in the cart
    }
    public void checkout(){ //function to calculate prices, and possibly tax

    };
    public void addItem(){ //add an item into the cart list

    };
    public void removeItem(Item item){ //remove specific item from cart list

    };
    public void updateCounter(){ //update the cart counter in the top right based on quantity

    };
    public void changeQuantity(){ //change the quantity of items inside the cart, based on availability

    };

}
