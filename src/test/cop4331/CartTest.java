package test.cop4331;

import cop4331.client.Cart;
import cop4331.client.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* Cart Tester. 
* 
* @author <Authors name> 
* @since <pre>Dec 4, 2019</pre> 
* @version 1.0 
*/ 
public class CartTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getCartSize() 
* 
*/ 
@Test
public void testGetCartSize() throws Exception {

    System.out.println("Cart Size");
    Cart instance = new Cart();
    int expResult = 0;
    int result = instance.getCartSize();
    assert(result == (expResult));
} 

/** 
* 
* Method: addItem(Item item) 
* 
*/ 
@Test
public void testAddItem() throws Exception {
    System.out.println("Add Item");
    Item item = new Item(0, "", "", 0,0,0,"");
    Cart instance = new Cart();
    Cart expResult = new Cart();
    expResult.addItem(item);
    Cart result = instance;
    result.addItem(item);
    assert(result.getCartItems().equals(expResult.getCartItems()));
} 

/** 
* 
* Method: removeItem(Item item) 
* 
*/ 
@Test
public void testRemoveItem() throws Exception {
    System.out.println("Remove Item");
    Item item = new Item(0, "", "", 0,0,0,"");
    Cart instance = new Cart();
    instance.addItem(item);
    Cart expResult = new Cart();
    expResult.addItem(item);
    expResult.removeItem(item);
    Cart result = instance;
    result.removeItem(item);
    assert(result.getCartItems().equals(expResult.getCartItems()));
} 

/** 
* 
* Method: getCartItems() 
* 
*/ 
@Test
public void testGetCartItems() throws Exception {
    System.out.println("Get Cart Items");
    Item item = new Item(0, "", "", 0,0,0,"");
    Cart instance = new Cart();
    Cart expResult = new Cart();
    expResult.addItem(item);
    Cart result = instance;
    result.addItem(item);
    assert(result.getCartItems().equals(expResult.getCartItems()));
} 


} 
