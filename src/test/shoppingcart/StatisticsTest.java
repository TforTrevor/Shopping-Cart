package test.shoppingcart; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import shoppingcart.Item;
import shoppingcart.Statistics;

/** 
* Statistics Tester. 
* 
* @author <Authors name> 
* @since <pre>Dec 4, 2019</pre> 
* @version 1.0 
*/ 
public class StatisticsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: setItem(Item item) 
* 
*/ 
@Test
public void testSetItem() throws Exception {
    System.out.println("Set Item");

    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Item newItem = new Item(0, "New Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    Statistics expResult = new Statistics(item);
    expResult.setItem(newItem);
    Statistics result = instance;
    result.setItem(newItem);
    assert(expResult.getItem().getName().equals(result.getItem().getName()));
} 

/** 
* 
* Method: getItem() 
* 
*/ 
@Test
public void testGetItem() throws Exception {
    System.out.println("Get Item");

    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    Statistics expResult = new Statistics(item);
    Statistics result = instance;
    assert(expResult.getItem().getName().equals(result.getItem().getName()));
} 

/** 
* 
* Method: setTimesPurchased(int timesPurchased) 
* 
*/ 
@Test
public void testSetTimesPurchased() throws Exception {
    System.out.println("Set Times Purchased");
    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    instance.setTimesPurchased(10);
    Statistics expResult = new Statistics(item);
    expResult.setTimesPurchased(10);
    Statistics result = instance;
    assert(expResult.getTimesPurchased() == (result.getTimesPurchased()));
} 

/** 
* 
* Method: getTimesPurchased() 
* 
*/ 
@Test
public void testGetTimesPurchased() throws Exception {
    System.out.println("Get Times Purchased");
    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    instance.setTimesPurchased(10);
    Statistics expResult = new Statistics(item);
    expResult.setTimesPurchased(10);
    Statistics result = instance;
    assert(expResult.getTimesPurchased() == (result.getTimesPurchased()));
} 

/** 
* 
* Method: setQuantityPurchased(int quantityPurchased) 
* 
*/ 
@Test
public void testSetQuantityPurchased() throws Exception {
    System.out.println("Set Quantity Purchased");
    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    instance.setQuantityPurchased(10);
    Statistics expResult = new Statistics(item);
    expResult.setQuantityPurchased(10);
    Statistics result = instance;
    assert(expResult.getQuantityPurchased() == (result.getQuantityPurchased()));
} 

/** 
* 
* Method: getQuantityPurchased() 
* 
*/ 
@Test
public void testGetQuantityPurchased() throws Exception {
    System.out.println("Get Quantity Purchased");
    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    instance.setQuantityPurchased(10);
    Statistics expResult = new Statistics(item);
    expResult.setQuantityPurchased(10);
    Statistics result = instance;
    assert(expResult.getQuantityPurchased() == (result.getQuantityPurchased()));
} 

/** 
* 
* Method: setProfit(int profit) 
* 
*/ 
@Test
public void testSetProfit() throws Exception {
    System.out.println("Set Profit");
    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    instance.setProfit(10);
    Statistics expResult = new Statistics(item);
    expResult.setProfit(10);
    Statistics result = instance;
    assert(expResult.getProfit() == (result.getProfit()));
}

/** 
* 
* Method: getProfit() 
* 
*/ 
@Test
public void testGetProfit() throws Exception {
    System.out.println("Get Profit");
    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    instance.setProfit(10);
    Statistics expResult = new Statistics(item);
    expResult.setProfit(10);
    Statistics result = instance;
    assert(expResult.getProfit() == (result.getProfit()));
} 

/** 
* 
* Method: add(int quantityPurchased) 
* 
*/ 
@Test
public void testAdd() throws Exception {
    System.out.println("Add");
    Item item = new Item(0, "Item", "Description", 10, 5, 5, "photo/path");
    Statistics instance = new Statistics(item);
    Statistics expResult = new Statistics(item);
    expResult.add(10);
    Statistics result = instance;
    result.add(10);
    assert(expResult.getProfit() == (result.getProfit()) && expResult.getTimesPurchased() == result.getTimesPurchased() && expResult.getProfit() == result.getProfit());
} 


} 
