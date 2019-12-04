package shoppingcart;

public class Vendor extends User{
    private String vendorName;


    public Vendor(String username, String password, String vendor) {
        super(username, password, vendor);
        vendorName = vendor;
    }
}
