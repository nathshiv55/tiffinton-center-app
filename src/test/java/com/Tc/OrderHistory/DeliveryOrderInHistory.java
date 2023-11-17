package com.Tc.OrderHistory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Tc.BasePackage.BaseInit;
import com.Tc.Utility.Mylibrary;

public class DeliveryOrderInHistory extends BaseInit{

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
	}
	@Test(priority=2)
	public void entringPostCode() throws Exception
	{
		//Enter PostCode
				Mylibrary.enterPostCode(appdata.getProperty("pc1"));  //enter postCode whenever enterPostCode method call.
			
	}

	@Test(priority=3)
	public void searchRestaurantMethod() throws Exception 
	{
		Mylibrary.searchRestaurant();
	}



	@Test(priority=4)
	public void searchItemMethod() throws Exception 
	{
		Mylibrary.searchItem();
	}


	@Test(priority=5)
	public void OrderPlace() throws Exception
	{
		Mylibrary.deliveryPlaceOrder();	
	}


	@Test(priority=6)
	public void doPayment() throws Exception
	{
		Mylibrary.Payment();
	}

	@Test(priority=7)
	public void verifyOrderHistory() throws Exception
	{

		Mylibrary.verifyOrderHistory();
	}
	@AfterClass
	public  void tearDown() throws Exception 
	{
		Mylibrary.appClosed();
	}
}
