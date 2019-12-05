package test.cop4331;

import cop4331.client.Item;
import cop4331.client.Statistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
