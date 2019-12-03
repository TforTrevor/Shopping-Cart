package shoppingcart;

public class Vendor extends User{
    private String vendorName;


    public Vendor(String username, String password, String vendor, boolean isVendor) {
        super(username, password, vendor, isVendor);
        vendorName = vendor;
    }
}
