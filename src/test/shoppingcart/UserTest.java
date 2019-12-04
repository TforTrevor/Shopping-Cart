package test.shoppingcart; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import shoppingcart.User;

/** 
* User Tester. 
* 
* @author <Authors name> 
* @since <pre>Dec 4, 2019</pre> 
* @version 1.0 
*/ 
public class UserTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getUsername() 
* 
*/ 
@Test
public void testGetUsername() throws Exception {
    System.out.println("Get Username");
    User instance = new User("username", "password", "VendorName");
    String expResult = "username";
    String result = instance.getUsername();
    assert(result.equals(expResult));
} 

/** 
* 
* Method: getPassword() 
* 
*/ 
@Test
public void testGetPassword() throws Exception {
    System.out.println("Get Password");
    User instance = new User("username", "password", "VendorName");
    String expResult = "password";
    String result = instance.getPassword();
    assert(result.equals(expResult));
} 

/** 
* 
* Method: getVendor() 
* 
*/ 
@Test
public void testGetVendor() throws Exception {
    System.out.println("Get Vendor");
    User instance = new User("username", "password", "VendorName");
    String expResult = "VendorName";
    String result = instance.getVendor();
    assert(result.equals(expResult));
} 

/** 
* 
* Method: equals(Object obj) 
* 
*/ 
@Test
public void testEquals() throws Exception {
    System.out.println("Equals");
    User instance = new User("username", "password", "VendorName");
    User other = new User("blank", "blank", null);
    User same = new User("username", "password", "VendorName");
    boolean expResult = false;
    boolean result = instance.equals(other);
    assert(result == (expResult));
    expResult = true;
    result = instance.equals(same);
    assert(result == expResult);
} 


} 
