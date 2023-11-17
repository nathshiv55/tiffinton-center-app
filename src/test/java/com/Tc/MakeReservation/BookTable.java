package com.Tc.MakeReservation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Tc.BasePackage.BaseInit;
import com.Tc.Utility.Mylibrary;

public class BookTable extends BaseInit {

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
	public void reservation() throws Exception
	{
		Mylibrary.doReservation();
	}

	@Test(priority=4)
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
