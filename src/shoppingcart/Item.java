package shoppingcart;

import javafx.scene.image.Image;

import java.io.FileInputStream;

/**
 * Item class to be used with gson when importing json data.
 */
public class Item implements Cloneable {
    private int ID;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int availableQuantity;
    private String vendorName;
    private String photo;

    /**
     * Initialization when making a item class.
     * @param ID ID of the item.
     * @param name name of the item.
     * @param description description of the item.
     * @param price price of the item.
     * @param quantity quantity of the item.
     * @param availableQuantity availableQuantity of the item.
     * @param photo photo path of the item.
     */
    public Item(int ID, String name, String description, double price, int quantity, int availableQuantity, String photo) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.photo = photo;
    }
    /**
     * Gets the ID.
     * @return ID.
     */
    public int getID() {
        return ID;
    }
    /**
     * Gets the name.
     * @return name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the description.
     * @return description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the price.
     * @return price.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Sets the quantity. If it is changing the quantity while the item being in the store, it changes the real quantity,
     * but if it changes it while in a cart it changes the quantity the user wants.
     * @param quantity the quantity of the item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Gets the quantity, regardless if its in a cart or not.
     * @return quantity.
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Gets the available quantity, or the quantity that isn't in a cart.
     * @return username.
     */
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * Sets the available quantity.
     * @param q the new available quantity
     */
    public void setAvailableQuantity(int q){
        this.availableQuantity = q;
    }
    /**
     * Gets the vendor name.
     * @return vendor name.
     */
    public String getVendorName() {
        return vendorName;
    }
    /**
     * Gets the image path.
     * @return image path.
     */
    public String getImageURL() {
        return photo;
    }
    /**
     * Gets the Image.
     * @return image.
     */
    public Image getImage(int width, int height) {
        try {
            FileInputStream inputStream = new FileInputStream(photo);
            return new Image(inputStream, width, height, true, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * sets the vendor name.
     * @param vendorName the vendor name to set.
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) return false;
        Item itemObj = (Item) obj;
        return (itemObj.getID() == ID && itemObj.getName().equals(name) && itemObj.getDescription().equals(description) && itemObj.getPrice() == price && itemObj.getQuantity() == quantity  && itemObj.getImageURL().equals(photo));
    }
}
