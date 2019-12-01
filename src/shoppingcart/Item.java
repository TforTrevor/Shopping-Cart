package shoppingcart;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class Item implements Cloneable {
    private int ID;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int availableQuantity;
    private String vendorName;
    private String photo;

    public Item(int ID, String name, String description, double price, int quantity, int availableQuantity, String photo) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.photo = photo;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
    public void setAvailableQuantity(int q){this.availableQuantity = q;}

    public String getVendorName() {
        return vendorName;
    }

    public String getImageURL() {
        return photo;
    }

    public Image getImage(int width, int height) {
        try {
            FileInputStream inputStream = new FileInputStream(photo);
            return new Image(inputStream, width, height, true, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
