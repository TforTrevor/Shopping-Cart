package test.shoppingcart; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import shoppingcart.Cart;
import shoppingcart.Item;

/** 
* Item Tester. 
* 
* @author <Authors name> 
* @since <pre>Dec 4, 2019</pre> 
* @version 1.0 
*/ 
public class ItemTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getID() 
* 
*/ 
@Test
public void testGetID() throws Exception {
    System.out.println("Get ID");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    int result = instance.getID();
    assert(result == (expResult.getID()));
} 

/** 
* 
* Method: getName() 
* 
*/ 
@Test
public void testGetName() throws Exception {
    System.out.println("Get Name");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    String result = instance.getName();
    assert(result.equals(expResult.getName()));
} 

/** 
* 
* Method: getDescription() 
* 
*/ 
@Test
public void testGetDescription() throws Exception {
    System.out.println("Get Description");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    String result = instance.getDescription();
    assert(result.equals(expResult.getDescription()));
} 

/** 
* 
* Method: getPrice() 
* 
*/ 
@Test
public void testGetPrice() throws Exception {
    System.out.println("Get Price");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    double result = instance.getPrice();
    assert(result == (expResult.getPrice()));
} 

/** 
* 
* Method: setQuantity(int quantity) 
* 
*/ 
@Test
public void testSetQuantity() throws Exception {
    System.out.println("Set Quantity");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    instance.setQuantity(10);
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    expResult.setQuantity(10);
    double result = instance.getQuantity();
    assert(result == (expResult.getQuantity()));
} 

/** 
* 
* Method: getQuantity() 
* 
*/ 
@Test
public void testGetQuantity() throws Exception {
    System.out.println("Get Quantity");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    int result = instance.getQuantity();
    assert(result == (expResult.getQuantity()));
} 

/** 
* 
* Method: getAvailableQuantity() 
* 
*/ 
@Test
public void testGetAvailableQuantity() throws Exception {
    System.out.println("Get Available Quantity");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    int result = instance.getAvailableQuantity();
    assert(result == (expResult.getAvailableQuantity()));
} 

/** 
* 
* Method: setAvailableQuantity(int q) 
* 
*/ 
@Test
public void testSetAvailableQuantity() throws Exception {
    System.out.println("Set Available Quantity");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    instance.setAvailableQuantity(10);
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    expResult.setAvailableQuantity(10);
    double result = instance.getAvailableQuantity();
    assert(result == (expResult.getAvailableQuantity()));
} 

/** 
* 
* Method: getVendorName() 
* 
*/ 
@Test
public void testGetVendorName() throws Exception {
    System.out.println("Get Vendor");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    instance.setVendorName("Vendor");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    expResult.setVendorName("Vendor");
    String result = instance.getVendorName();
    assert(result.equals(expResult.getVendorName()));
} 

/** 
* 
* Method: getImageURL() 
* 
*/ 
@Test
public void testGetImageURL() throws Exception {
    System.out.println("Get image URL");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    String result = instance.getImageURL();
    assert(result.equals(expResult.getImageURL()));
}

/** 
* 
* Method: setVendorName(String vendorName) 
* 
*/ 
@Test
public void testSetVendorName() throws Exception {
    System.out.println("Set Vendor");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    instance.setVendorName("Vendor");
    Item expResult = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    expResult.setVendorName("Vendor");
    String result = instance.getVendorName();
    assert(result.equals(expResult.getVendorName()));
} 

/** 
* 
* Method: clone() 
* 
*/ 
@Test
public void testClone() throws Exception {
    System.out.println("Clone");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item expResult = instance;
    Item result = (Item) instance.clone();
    assert(result.getName() == expResult.getName());
    assert(result.getPrice() == expResult.getPrice());
} 

/** 
* 
* Method: equals(Object obj) 
* 
*/ 
@Test
public void testEquals() throws Exception {
    System.out.println("Equals");
    Item instance = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item other = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    boolean expResult = true;
    boolean result = instance.equals(other);
    assert(result == (expResult));
}

} 
