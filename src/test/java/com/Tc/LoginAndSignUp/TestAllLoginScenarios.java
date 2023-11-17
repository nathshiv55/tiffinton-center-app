package com.Tc.LoginAndSignUp;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Tc.BasePackage.BaseInit;
import com.Tc.Utility.Mylibrary;

public class TestAllLoginScenarios extends BaseInit {
	@BeforeMethod
	public void setUp() throws Exception
	{
		startUp();
	}

	@Test(priority=1)
	public void testLogIn() throws Exception
	{

		System.out.println("1. Valid username and an invalid password");
		Mylibrary.logIn(appdata.getProperty("username2") , appdata.getProperty("pswd2"));	//enter username and password Whenever login method call.

		System.out.println("2. Invalid username and an valid password");
		Mylibrary.logIn(appdata.getProperty("username3") , appdata.getProperty("pswd3"));

		System.out.println("3. both field is blank");
		Mylibrary.logIn(appdata.getProperty("username4") , appdata.getProperty("pswd4"));

		System.out.println("4. Username blank and correct password");
		Mylibrary.logIn(appdata.getProperty("username5") , appdata.getProperty("pswd5"));

		System.out.println("5. Username blank and correct password");
		Mylibrary.logIn(appdata.getProperty("username6") , appdata.getProperty("pswd6"));

		System.out.println("6. Valid username and valid password.");
		Mylibrary.logIn(appdata.getProperty("username1") , appdata.getProperty("pswd1")); 

		//Screenshot
		Mylibrary.getScreenShot("enterPostcode", driver);  //enter imageName whenever getScreenshot method call.

		Mylibrary.enterPostCode(appdata.getProperty("pc1"));  //enter postCode whenever enterPostCode method call.

		Mylibrary.logOut();

	}

	@AfterMethod
	public  void tearDown() throws Exception 
	{
		Mylibrary.appClosed();
	} 
}
