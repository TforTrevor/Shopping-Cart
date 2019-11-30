package shoppingcart;

public class ItemType implements Cloneable{
    private int ID;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int availableQuantity;
    private String vendorName;
    private String photo;

    public ItemType(int ID, String name, String description, double price, int quantity, int availableQuantity, String photo){
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

    public String getVendorName() {
        return vendorName;
    }

    public String getPhoto() { return photo; }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Object clone() throws CloneNotSupportedException{ return super.clone(); }
}
