package com.Tc.MakeOrder;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Tc.BasePackage.BaseInit;
import com.Tc.Utility.Mylibrary;

public class CollectionOrderWithLogOut extends BaseInit {

	@BeforeClass
	public void setUp() throws Exception
	{
		startUp();
	}

	
	@Test(priority=1)
	public void testLogIn() throws Exception
	{

		//Login
		Mylibrary.logIn(appdata.getProperty("username1") , appdata.getProperty("pswd1")); 

		//Enter PostCode
		Mylibrary.enterPostCode(appdata.getProperty("pc1"));  //enter postCode whenever enterPostCode method call.
		Thread.sleep(18000);

	}

	
	@Test(priority=2)
	public void searchRestaurantMethod() throws Exception 
	{
		Mylibrary.searchRestaurant();
	}
	
	
	@Test(priority=3)
	public void searchItemMethod() throws Exception 
	{
		Mylibrary.searchItem();
	}
	

	@Test(priority=4)
	public void OrderPlace() throws Exception
	{
		Mylibrary.collectionPlaceOrder();
	}
	
	@Test(priority=5)
	public void doPayment() throws Exception
	{
		Mylibrary.Payment();
	}	
	
	@Test(priority=6)
	public void appLogout() throws Exception
	{
		Mylibrary.logOut();
	}
	
	@AfterClass
	public  void tearDown() throws Exception 
	{
		Mylibrary.appClosed();
	}
}


