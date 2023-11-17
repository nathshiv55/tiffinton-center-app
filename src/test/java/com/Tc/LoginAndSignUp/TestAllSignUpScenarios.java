package com.Tc.LoginAndSignUp;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Tc.BasePackage.BaseInit;
import com.Tc.Utility.Mylibrary;

public class TestAllSignUpScenarios extends BaseInit{

	@BeforeClass
	public void setUp() throws Exception
	{
		startUp();
	}
	@Test(priority=1)
	public void testSignUpCase1() throws Exception
	{
		Thread.sleep(2000);
		System.out.println("-------------*** Positive TestCase*** ---------------\r\n");
		System.out.println("- TestCase#1 - SignUp with All Valid Credentials");
		Mylibrary.signUp();		

		try {
			Mylibrary.enterPostCode(appdata.getProperty("pc1"));  //enter postCode whenever enterPostCode method call.
			System.out.println("      Status : PASS\r\n");	
			Mylibrary.logOut();
			System.out.println("-------------------------------------------------------------------\r\n");
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("      Status : FAIL\r\n");
			System.out.println("-------------------------------------------------------------------\r\n");

			//Agar signup nhi hua h to clear kr do ..taki aage ke testcase chale
			driver.findElement(By.id("com.tiffintom:id/etFirstName")).clear();
			driver.findElement(By.id("com.tiffintom:id/etLastName")).clear();
			driver.findElement(By.id("com.tiffintom:id/etEmail")).clear();
			driver.findElement(By.id("com.tiffintom:id/etMobile")).clear();
			driver.findElement(By.id("com.tiffintom:id/etPassword")).clear();
			driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).clear();
		}
	}
	@Test(priority=2)
	public void testSignUpCase2() throws Exception
	{
		System.out.println("-------------*** Negative TestCase*** ---------------\r\n");
		//Case#1 - Invalid email
		System.out.println("- TestCase#2 - Invalid Enail Address");

		//Clcik on Signup with Email
		driver.findElement(By.xpath("//android.widget.TextView[@text='Signup with Email']")).click();
		//Enter signup details
		driver.findElement(By.id("com.tiffintom:id/etFirstName")).sendKeys("Shiv");
		driver.findElement(By.id("com.tiffintom:id/etLastName")).sendKeys("Nath");

		//Generate random email id every time
		String userName = ""+(int)(Math.random()*Integer.MAX_VALUE);
		String emailID = "user"+userName+"@yopmail";
		//Print Email id
		System.out.println("    - Email id is : "+emailID);	 

		driver.findElement(By.id("com.tiffintom:id/etEmail")).sendKeys(emailID);
		driver.findElement(By.id("com.tiffintom:id/etMobile")).sendKeys("0700000000");
		System.out.println("    - Mobile Number : 0700000000");
		driver.findElement(By.id("com.tiffintom:id/etPassword")).sendKeys("Shiv@123");
		//Print Password
		System.out.println("    - Password is    :    Shiv@123");
		driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).sendKeys("Shiv@123");
		System.out.println("  - Confirm Password is : Shiv@123"+"\r\n");

		//Click on SIGN UP button
		driver.findElement(By.id("com.tiffintom:id/btnSignup")).click();
		Thread.sleep(3000);
		//Verify SIGN UP
		try {
			boolean checkSignUp = driver.findElement(By.id("com.tiffintom:id/etPostcode")).isDisplayed(); //if this element present, it means login successful.
			System.out.println(" A Bug Found - User can SignUp with a Invalid Email Address...\r\n" + "");
			logs.info(" A Bug Found - User can SignUp with a Invalid Email Address...");
			System.out.println("      Status : FAIL\r\n");


			//agar wrong email ke sath signup ho gya to ....logout karwa do.
			Mylibrary.enterPostCode(appdata.getProperty("pc1"));
			Mylibrary.logOut();

			//Clcik on Signup with Email
			driver.findElement(By.xpath("//android.widget.TextView[@text='Signup with Email']")).click();
			System.out.println("-------------------------------------------------------------------\r\n");
		}
		catch(Exception e)
		{
			System.out.println(" Warning - Invalid Email Address\r\n" + "");
			logs.info(" Warning - Invalid Email Address");
			System.out.println("      Status : PASS\r\n");
			System.out.println("-------------------------------------------------------------------\r\n");

			driver.findElement(By.id("com.tiffintom:id/etFirstName")).clear();
			driver.findElement(By.id("com.tiffintom:id/etLastName")).clear();
			driver.findElement(By.id("com.tiffintom:id/etEmail")).clear();
			driver.findElement(By.id("com.tiffintom:id/etMobile")).clear();
			driver.findElement(By.id("com.tiffintom:id/etPassword")).clear();
			driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).clear();
		}

	}
	@Test(priority=3)
	public void testSignUpCase3() throws Exception
	{
		//Case#2 - MisMAtch Password 
		System.out.println("- TestCase#3 - MisMAtch Password");

		//Enter signup details
		driver.findElement(By.id("com.tiffintom:id/etFirstName")).sendKeys("Shiv");
		driver.findElement(By.id("com.tiffintom:id/etLastName")).sendKeys("Nath");

		//Generate random email id every time
		String userName1 = ""+(int)(Math.random()*Integer.MAX_VALUE);
		String emailID1 = "user"+userName1+"@yopmail.com";
		//Print Email id
		System.out.println("  - Email id is : "+emailID1);	 

		driver.findElement(By.id("com.tiffintom:id/etEmail")).sendKeys(emailID1);
		driver.findElement(By.id("com.tiffintom:id/etMobile")).sendKeys("0700000000");
		System.out.println("    - Mobile Number : 0700000000");
		driver.findElement(By.id("com.tiffintom:id/etPassword")).sendKeys("Shiv@123");
		//Print Password
		System.out.println("    - Password is    :    Shiv@123");
		driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).sendKeys("Shiv@456");
		System.out.println("  - Confirm Password is : Shiv@456"+"\r\n");

		//Click on SIGN UP button
		driver.findElement(By.id("com.tiffintom:id/btnSignup")).click();
		Thread.sleep(3000);
		//Verify SIGN UP
		try {
			boolean checkSignUp = driver.findElement(By.id("com.tiffintom:id/etPostcode")).isDisplayed(); //if this element present, it means login successful.
			System.out.println(" A Bug Found - User can SignUp with missMatch Password...\r\n" + "");
			logs.info(" A Bug Found - User can SignUp with a with missMatch Password...");
			System.out.println("      Status : FAIL\r\n");


			//agar wrong email ke sath signup ho gya to ....logout karwa do.
			Mylibrary.enterPostCode(appdata.getProperty("pc1"));
			Mylibrary.logOut();

			//Clcik on Signup with Email
			driver.findElement(By.xpath("//android.widget.TextView[@text='Signup with Email']")).click();
			System.out.println("-------------------------------------------------------------------\r\n");
		}
		catch(Exception e)
		{
			System.out.println(" Warning - Password does not match\r\n" + "");
			logs.info(" Warning - Password does not match");
			System.out.println("      Status : PASS\r\n");
			System.out.println("-------------------------------------------------------------------\r\n");

			driver.findElement(By.id("com.tiffintom:id/etFirstName")).clear();
			driver.findElement(By.id("com.tiffintom:id/etLastName")).clear();
			driver.findElement(By.id("com.tiffintom:id/etEmail")).clear();
			driver.findElement(By.id("com.tiffintom:id/etMobile")).clear();
			driver.findElement(By.id("com.tiffintom:id/etPassword")).clear();
			driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).clear();
		}

	}
	@Test(priority=4)
	public void testSignUpCase4() throws Exception
	{
		//Case#3 - Invalid Phone Number
		System.out.println("- TestCase#4 -  Invalid Phone Number");

		//Enter signup details
		driver.findElement(By.id("com.tiffintom:id/etFirstName")).sendKeys("Shiv");
		driver.findElement(By.id("com.tiffintom:id/etLastName")).sendKeys("Nath");

		//Generate random email id every time
		String userName2 = ""+(int)(Math.random()*Integer.MAX_VALUE);
		String emailID2 = "user"+userName2+"@yopmail.com";
		//Print Email id
		System.out.println("  - Email id is : "+emailID2);	 

		driver.findElement(By.id("com.tiffintom:id/etEmail")).sendKeys(emailID2);
		driver.findElement(By.id("com.tiffintom:id/etMobile")).sendKeys("070000");
		System.out.println("    - Mobile Number : 070000");
		driver.findElement(By.id("com.tiffintom:id/etPassword")).sendKeys("Shiv@123");
		//Print Password
		System.out.println("    - Password is    :    Shiv@123");
		driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).sendKeys("Shiv@123");
		System.out.println("  - Confirm Password is : Shiv@123"+"\r\n");

		//Click on SIGN UP button
		driver.findElement(By.id("com.tiffintom:id/btnSignup")).click();
		Thread.sleep(3000);
		//Verify SIGN UP
		try {
			boolean checkSignUp = driver.findElement(By.id("com.tiffintom:id/etPostcode")).isDisplayed(); //if this element present, it means login successful.
			System.out.println(" A Bug Found - User can SignUp with Invalid Mobile number...\r\n" + "");
			logs.info(" A Bug Found - User can SignUp with a Invalid mobile Number...");
			System.out.println("      Status : FAIL\r\n");

			//agar wrong phone number ke sath signup ho gya to ....logout karwa do.
			Mylibrary.enterPostCode(appdata.getProperty("pc1"));
			Mylibrary.logOut();

			//Clcik on Signup with Email
			driver.findElement(By.xpath("//android.widget.TextView[@text='Signup with Email']")).click();
			System.out.println("-------------------------------------------------------------------\r\n");
		}
		catch(Exception e)
		{
			System.out.println(" Warning - Please enter valid mobile number\r\n" + "");
			logs.info(" Warning - Please enter valid mobile number");
			System.out.println("      Status : PASS\r\n");
			System.out.println("-------------------------------------------------------------------\r\n");

			driver.findElement(By.id("com.tiffintom:id/etFirstName")).clear();
			driver.findElement(By.id("com.tiffintom:id/etLastName")).clear();
			driver.findElement(By.id("com.tiffintom:id/etEmail")).clear();
			driver.findElement(By.id("com.tiffintom:id/etMobile")).clear();
			driver.findElement(By.id("com.tiffintom:id/etPassword")).clear();
			driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).clear();
		}

	}

	@Test(priority=5)
	public void testSignUpCase5() throws Exception
	{
		//Case#4 - Wrong mobile number
		System.out.println("- TestCase#5 -  Invalid Phone Number");

		//Enter signup details
		driver.findElement(By.id("com.tiffintom:id/etFirstName")).sendKeys("Shiv");
		driver.findElement(By.id("com.tiffintom:id/etLastName")).sendKeys("Nath");

		//Generate random email id every time
		String userName3 = ""+(int)(Math.random()*Integer.MAX_VALUE);
		String emailID3 = "user"+userName3+"@yopmail.com";
		//Print Email id
		System.out.println("  - Email id is : "+emailID3);	 

		driver.findElement(By.id("com.tiffintom:id/etEmail")).sendKeys(emailID3);
		driver.findElement(By.id("com.tiffintom:id/etMobile")).sendKeys("0812345678");
		System.out.println("    - Mobile Number : 0812345678");
		driver.findElement(By.id("com.tiffintom:id/etPassword")).sendKeys("Shiv@123");
		//Print Password
		System.out.println("    - Password is    :    Shiv@123");
		driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).sendKeys("Shiv@123");
		System.out.println("  - Confirm Password is : Shiv@123"+"\r\n");

		//Click on SIGN UP button
		driver.findElement(By.id("com.tiffintom:id/btnSignup")).click();
		Thread.sleep(3000);
		//Verify SIGN UP
		try {
			boolean checkSignUp = driver.findElement(By.id("com.tiffintom:id/etPostcode")).isDisplayed(); //if this element present, it means login successful.
			System.out.println(" A Bug Found - User can SignUp with Invalid Mobile number...\r\n" + "");
			logs.info(" A Bug Found - User can SignUp with a Invalid mobile Number...");
			System.out.println("      Status : FAIL\r\n");		

			//agar wrong phone number ke sath signup ho gya to ....logout karwa do.
			Mylibrary.enterPostCode(appdata.getProperty("pc1"));
			Mylibrary.logOut();
			System.out.println("-------------------------------------------------------------------\r\n");
		}
		catch(Exception e)
		{
			System.out.println(" Warning - Please enter a mobile number which start with 07\r\n" + "");
			logs.info(" Warning - Please enter a mobile number which start with 07");
			System.out.println("      Status : PASS\r\n");
			System.out.println("-------------------------------------------------------------------\r\n");
		}
	}
	@AfterClass
	public  void tearDown() throws Exception 
	{
		Mylibrary.appClosed();
	} 
}


