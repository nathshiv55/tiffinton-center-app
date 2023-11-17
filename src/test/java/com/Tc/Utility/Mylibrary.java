package com.Tc.Utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.Tc.BasePackage.BaseInit;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Mylibrary extends BaseInit  {

	//Frequently used Method will be here.
	//Mylibrary class is going to extends BaseInit Class. AndroidDriver ko whi per mention kiya hai , isaliye
	static String orderId;
	static String totalInSummary;
	static List<WebElement> foodItemsNumberInOrderSummary ;
	static List<WebElement> foodItemsNameInOrderSummary ;
	static List<WebElement> foodItemsPriceInOrderSummary ;
	public static void logIn(String email, String pwd) throws Exception
	{
		//LogIn
		driver.findElement(By.id("com.tiffintom:id/etEmail")).sendKeys(email);
		driver.findElement(By.id("com.tiffintom:id/etPassword")).sendKeys(pwd);
		driver.findElement(By.id("com.tiffintom:id/btnSignin")).click();
		Thread.sleep(4000);
		//Verify LogIn
		try {

			boolean checkLogin = driver.findElement(By.id("com.tiffintom:id/etPostcode")).isDisplayed(); //if this element present, it means login successful.
			System.out.println(" - User is logged in Successfully...\r\n" + "");
			logs.info(" - User is logged in Successfully...");

		}
		catch(Exception e)
		{
			System.out.println(" - Invalid Email Address Or Password\r\n" + "");
			logs.info(" - Invalid Email Address Or Password");

		}

		Thread.sleep(1000);
	}


	public static void signUp() throws InterruptedException
	{
		//Clcik on Signup with Email
		driver.findElement(By.xpath("//android.widget.TextView[@text='Signup with Email']")).click();
		//Enter signup details
		driver.findElement(By.id("com.tiffintom:id/etFirstName")).sendKeys("Shiv");
		driver.findElement(By.id("com.tiffintom:id/etLastName")).sendKeys("Nath");

		//Generate random email id every time
		String userName = ""+(int)(Math.random()*Integer.MAX_VALUE);
		String emailID = "user"+userName+"@yopmail.com";
		//Print Email id
		System.out.println("  - Email id is : "+emailID);	 

		driver.findElement(By.id("com.tiffintom:id/etEmail")).sendKeys(emailID);
		driver.findElement(By.id("com.tiffintom:id/etMobile")).sendKeys("0700000000");
		System.out.println("  - Mobile Number : 0700000000");
		driver.findElement(By.id("com.tiffintom:id/etPassword")).sendKeys("Shiv@123");
		//Print Password
		System.out.println("  - Password is : Shiv@123"+"\r\n");
		driver.findElement(By.id("com.tiffintom:id/etConfirmPassword")).sendKeys("Shiv@123");

		//Click on SIGN UP button
		driver.findElement(By.id("com.tiffintom:id/btnSignup")).click();
		Thread.sleep(6000);
		//Verify SIGN UP
		try {

			boolean checkSignUp = driver.findElement(By.id("com.tiffintom:id/etPostcode")).isDisplayed(); //if this element present, it means login successful.
			System.out.println(" - User is SignUp Successfully...\r\n" + "");
			logs.info(" - User is SignUp Successfully...");

		}
		catch(Exception e)
		{
			System.out.println(" - Invalid SignUp Credentials" + "");
			logs.info(" - Invalid SignUp Credentials");

		}
	}



	public static void enterPostCode(String pstcd) throws Exception
	{
		//enterPostCode
		driver.findElement(By.id("com.tiffintom:id/etPostcode")).sendKeys(pstcd);
		driver.findElement(By.id("com.tiffintom:id/btnConfirm")).click();
		logs.info(" - PostCode Entered Successfully...");
		System.out.println(" - PostCode Entered Successfully...\r\n" + "");
		Thread.sleep(18000); 
	}

	public static void logOut() throws Exception
	{
		//LogOut
		driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Settings\"]/android.widget.FrameLayout[2]/android.widget.ImageView")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.tiffintom:id/cvLogout")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("com.tiffintom:id/tvConfirm")).click();
		//Verify LogOut
		try {

			boolean checkLogout = driver.findElement(By.id("com.tiffintom:id/btnSignin")).isDisplayed(); //if this element present, it means logOut successful.
			logs.info(" - LogOut Successfully...");
			System.out.println(" - LogOut Successfully...\r\n" + "");
		}
		catch(Exception e)
		{
			System.out.println(" - User Not LogOut...");
			logs.info(" - User Not LogOut...");

		}
	}



	public static void appClosed()
	{	
		try {
			Thread.sleep(2000);
			driver.quit();

			System.out.println(" - App Closed...\r\n" + "");
			System.out.println("---------------**------------------------***---------------------------**---------------\r\n");

			logs.info(" - App Closed...");
			logs.info("---------------**------------------------***---------------------------**---------------");
		}
		catch(Exception e)
		{
			//When an exception is chained, the getCause method is used to get the original cause. 
			System.out.println("Cause is : "+e.getCause());
			System.out.println("Message is : "+e.getMessage());
			e.printStackTrace();
		}

	}



	public static String getScreenShot(String imageName, AndroidDriver driver)
	{

		TakesScreenshot ts = (TakesScreenshot) driver;  //ts will point where driver is pointing

		File scrFile = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\" +imageName+ System.currentTimeMillis() + ".png";

		// System.out.println(path);
		File destination = new File(path);

		try {

			FileUtils.copyFile(scrFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return path;
	}



	public static void searchRestaurant() throws Exception
	{
		//Search Restaurant
		driver.findElement(By.id("com.tiffintom:id/etSearch")).sendKeys(appdata.getProperty("RestaurantName1"));
		System.out.println(" - Searching Restaurant ->"+appdata.getProperty("RestaurantName1")+"\r\n" + "");
		logs.info(" - Searching Restaurant ->"+ appdata.getProperty("RestaurantName1"));
		Thread.sleep(2000);	
		//Click on Restaurant
		String rstName = appdata.getProperty("RestaurantName1");
		// Construct the XPath expression
		String xpathExpression1 = "//android.widget.TextView[@text='" + rstName + "']";
		driver.findElement(By.xpath(xpathExpression1)).click();
		Thread.sleep(8000);
		System.out.println(" - "+appdata.getProperty("RestaurantName1")+ " open...\r\n " + "");
		logs.info(" - "+appdata.getProperty("RestaurantName1")+ " open...");
	}


	@SuppressWarnings({ "deprecation", "rawtypes", "static-access" })
	public static void swipeScreen()
	{
		
		TouchAction act = new TouchAction(driver);
		//AndroidTouchAction act = new AndroidTouchAction(driver);
		PointOption p = new PointOption();
		

		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		System.out.println("Height = "+height +","+"Width = "+width);    //Height = 2034,Width = 1080
		
		int startx=0;

		int endx=0;

		int starty=(int) (height*0.20);

		int endy=(int) (height*0.80);

		
		act.press(p.point(startx,starty)).waitAction(new WaitOptions().withDuration(Duration.ofMillis(900))).moveTo(p.point(endx,endy)).release().perform();				

	}



	public static void searchItem() throws Exception
	{

		
		//Item add to basket
		System.out.println(" - Selected Food items");
		
		//Scroll down
		driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" + "Chicken Kurma" + "\").instance(0))"));  //enter text in this lines where you want to go.

		//swipeScreen();
		//Select 2 X Chicken kurma
		WebElement ele1=driver.findElement(By.xpath("//android.widget.TextView[@text='Chicken Kurma']"));
		ele1.click();
		ele1.click();
		System.out.println("   1. 2 X Chicken Kurma");

		//Scroll down
		driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" + "Tandoori Chicken" + "\").instance(0))"));  //enter text in this lines where you want to go.

		//Select Tandoori Chicken 
		driver.findElement(By.xpath("//android.widget.TextView[@text='Tandoori Chicken']")).click();
		System.out.println("   2. Tandoori Chicken");


		//Scroll down
		driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" + "Tikka Pakora" + "\").instance(0))"));  //enter text in this lines where you want to go.

		//Select Tikka Pakora
		driver.findElement(By.xpath("//android.widget.TextView[@text='Tikka Pakora']")).click();
		System.out.println("   3. Tikka Pakora\r\n" + "");
		
		//Select Meat Samosa
				driver.findElement(By.xpath("//android.widget.TextView[@text='Nargis Kebab']")).click();
				System.out.println("   4. Nargis Kebab");


		//Click on CheckOut bar button
		driver.findElement(By.id("com.tiffintom:id/tvTotal")).click();
		Thread.sleep(7000);
	}	




	public static void deliveryPlaceOrder() throws Exception
	{	
		try {
			//if new addess entered
			driver.findElement(By.id("com.tiffintom:id/llPlaceOrder")).click();
			System.out.println(" - Order type : Delivery\r\n");
			logs.info("Order type : Delivery");
			driver.findElement(By.id("com.tiffintom:id/etFlatNo")).sendKeys("A/96");
			System.out.println(" - House NO. entered successfully...\r\n" + "");
			driver.findElement(By.id("com.tiffintom:id/tvConfirm")).click();
			System.out.println(" - New address saved and selected...\r\n " + "");
			Thread.sleep(3000);
		}
		catch(Exception e) {
			//if saved address Dispalyed
			try {
				//Select a saved address
				//Hamne sabhi saved addresses ko ek List me store karwaya. jinka index 0,1,2,... iss tarah hai . Hame jis address par click karwana hai , uska 
				//index number get() ke andar likh de. agar 1st address ko click karna hai, to usak index 0 likh de . agar 2nd ko click karwana hai to 1 likh de. 
				//Note:- yha par findElements liya gaya hai , na ki findElement.
				List<WebElement> savedAddress =  driver.findElements(By.id("com.tiffintom:id/tvSubtitle"));
				savedAddress.get(0).click();
				System.out.println(" - Selected a saved Address\r\n " + "");
				Thread.sleep(3000);
			}
			catch(Exception e1) {
				//agar upar wale dono case nhi hua , yani first try me jyo hi place order bar par click hua , wo sidha hi payment page par chala gaya
				System.out.println(" - Saved address already selected \r\n " + "");
				System.out.println(" - User Directly redirected to payment page\r\n " + "");
				Thread.sleep(3000);
			}
		}
	}



	public static void collectionPlaceOrder() throws Exception
	{
		//Select Collection
		driver.findElement(By.id("com.tiffintom:id/tvCustomPickup")).click();
		System.out.println("Order type : Collection\r\n");
		logs.info(" - Order type : Collection");
		Thread.sleep(2000);
		//Click on Place Order button
		driver.findElement(By.id("com.tiffintom:id/llPlaceOrder")).click();
		Thread.sleep(3000);
	}




	public static void Payment() throws InterruptedException
	{
		//Select Payment Mode
		driver.findElement(By.xpath("//android.widget.TextView[@text='Cash on delivery']")).click();
		System.out.println(" - Payment Mode -> Cash On Delivery, selected\r\n " + "");
		Thread.sleep(3000);	
		//Click on place order
		driver.findElement(By.id("com.tiffintom:id/tvPlaceOrder")).click();
		Thread.sleep(9000);	
		//Verify Placed Order
		try {

			boolean checkPlacedOrder = driver.findElement(By.id("com.tiffintom:id/tvOrderTitle")).isDisplayed(); //if this element present, it order placed successful.
			//OrderId put into a Varible & Print it.
			orderId =  driver.findElement(By.id("com.tiffintom:id/tvOrderTitle")).getText();	
			System.out.println(" - Your order id is = "+orderId+"\r\n"+ "\r\n"+" - Order SucessFully placed\r\n");
			logs.info(" - Order Sucessfully Placed");
			//print food items Number, Name , Price
			foodItemsNumberInOrderSummary = driver.findElements(By.id("com.tiffintom:id/tvCount"));
			foodItemsNameInOrderSummary = driver.findElements(By.id("com.tiffintom:id/tvName"));
			foodItemsPriceInOrderSummary = driver.findElements(By.id("com.tiffintom:id/tvPrice"));
			System.out.println(foodItemsNameInOrderSummary.get(0).getText());
			System.out.println(foodItemsNameInOrderSummary.get(1).getText());
			System.out.println("No. of Items          Item Name        Price\r\n");
			for(int count =0;count<foodItemsNumberInOrderSummary.size();count++)
			{
				for(int count1 =0;count1<foodItemsNameInOrderSummary.size();count1++)
				{
					for(int count2 =0;count2<foodItemsPriceInOrderSummary.size();count2++)
					{	
						System.out.println("   "+foodItemsNumberInOrderSummary.get(count).getText()+"                "+foodItemsNameInOrderSummary.get(count1).getText()+"                "
								+(foodItemsPriceInOrderSummary.get(count2).getText()+"\r\n"));
						
						
					}
				}	
				
			}
			totalInSummary = driver.findElement(By.id("com.tiffintom:id/tvTotal")).getText();
			System.out.println("       -  Total is : "+totalInSummary+"\r\n");
			//Agar order sucessfully placed ho gya hai to Home page par nevigate karwa do.
			//Scroll down
			driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
					+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" + "Back to homepage" + "\").instance(0))"));  //enter text in this lines where you want to go.
			//Click on Back to Home Page
			driver.findElement(By.xpath("//android.widget.TextView[@text='Back to homepage']")).click();
			Thread.sleep(2000);		
		}
		catch(Exception e)
		{
			System.out.println(" - Order Not Placed\r\n" + "");
			logs.info(" - Order Not Placed");
			
		}
	}




	public static void verifyOrderHistory() throws Exception
	{

		//Click on bottom profile icon
		driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Profile\"]/android.widget.FrameLayout/android.widget.ImageView")).click();
		Thread.sleep(3000);
		//Click on Order History Button
		driver.findElement(By.xpath("//android.widget.TextView[@text='Order History']")).click();
		Thread.sleep(4000);
		try {
			boolean checkOrderIDinHistory = driver.findElement(By.id("com.tiffintom:id/tvOrderId")).isDisplayed(); //if this element present, it means this order is present in order History.
			System.out.println(" - Order found in order history\r\n");
			//Agar order id yeh element wha hai to isko print krwake verify krwado
			String orderIDinHistory =  driver.findElement(By.id("com.tiffintom:id/tvOrderId")).getText();
			System.out.println(" - Order id in order history is : "+orderIDinHistory+"\r\n");
			//Agar order id mil gya to , verify order id
			if(orderId.endsWith(orderIDinHistory))
			{		
				System.out.println(" - OrderID in History is  Same as Summary\r\n");
				System.out.println("         Status : PASS\r\n");
			}else {
				System.out.println(" A Bug Found - OrderID in History is not same as Summary\r\n");
				//when thr order not found in history, Logout karwa do
			}
			//Agar order id mil gya to , verify food items Number , Name,Price
			List<WebElement> foodItemsNumberInOrderHistory = driver.findElements(By.id("com.tiffintom:id/tvCount"));
			List<WebElement> foodItemsNameInOrderHistory = driver.findElements(By.id("com.tiffintom:id/tvName"));
			List<WebElement> foodItemsPriceInOrderHistory = driver.findElements(By.id("com.tiffintom:id/tvPrice"));
			for(int count =0;count<foodItemsNumberInOrderHistory.size();count++)
			{
				for(int count1 =0;count<foodItemsNameInOrderHistory.size();count1++)
				{
					for(int count2 =0;count<foodItemsPriceInOrderHistory.size();count2++)
					{	
						if(foodItemsNumberInOrderHistory.get(count).getText().equals(foodItemsNumberInOrderSummary.get(count).getText()))
						{
							if(foodItemsNameInOrderHistory.get(count1).getText().equals(foodItemsNameInOrderSummary.get(count1).getText())) 
							{
								if(foodItemsPriceInOrderHistory.get(count2).getText().equals(foodItemsPriceInOrderSummary.get(count2).getText()))
								{
									System.out.println("ItemName : " +foodItemsNameInOrderSummary.get(count1).getText()+" is Correct\r\n");
								}
								else 
								{
									System.out.println(" - FoodItem : " +foodItemsNameInOrderSummary.get(count1).getText()+" is NOT Correct\r\n");
								}
							}                	   
						}
					}
				}	
			}
			//verify Order Total
			String totalInHistory = driver.findElement(By.id("com.tiffintom:id/tvTotal")).getText();
			System.out.println("       -  Total is : "+totalInHistory+"\r\n");
			if(totalInHistory.equalsIgnoreCase(totalInSummary))
			{
				System.out.println(" - Order total in History is same as Summary");
			}
			else {
				System.out.println(" A Bug Found :  Order total in History is NOT same as Summary");
			}
		}catch(Exception e){

			System.out.println(" A Bug Found - This Order is not present in History\r\n");
			//if order not found , logour karwa do
			logOut();
		}
	}




	public static void doReservation() throws Exception
	{
		//click on Make Reservation
		driver.findElement(By.id("com.tiffintom:id/tvReservation")).click();
		Thread.sleep(3000);	

		//Click on Select date
		driver.findElement(By.id("com.tiffintom:id/tvReservationDate")).click();	
		//Click on OK
		driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
		Thread.sleep(2000);
		//print date
		WebElement dateIs= driver.findElement(By.id("com.tiffintom:id/tvReservationDate"));
		System.out.println(" - Booking Date : "+dateIs.getText()+"\r\n");
		logs.info(" - Booking Date : "+dateIs.getText());

		//click on Select time
		driver.findElement(By.id("com.tiffintom:id/tvReservationTime")).click();
		Thread.sleep(2000);
		//Store all time in a list 
		List<WebElement> bookingTime =  driver.findElements(By.id("com.tiffintom:id/tvTime"));
		//Click on index 0 element
		bookingTime.get(0).click();
		//print time
		WebElement timeIs= driver.findElement(By.id("com.tiffintom:id/tvReservationTime"));
		System.out.println(" - Booking time : "+timeIs.getText()+"\r\n");
		logs.info(" - Booking time : "+timeIs.getText());

		//Store all Number of people in a list 
		List<WebElement> noOfPeople =  driver.findElements(By.id("com.tiffintom:id/rbGuest"));
		//Click on index 1 element
		noOfPeople.get(1).click();	
		//print no of people
		System.out.println(" - No of people : "+noOfPeople.get(1).getText()+"\r\n");
		logs.info(" - No of people : "+noOfPeople.get(1).getText());

		//Add special instructions
		driver.findElement(By.id("com.tiffintom:id/etInstructions")).sendKeys("We will be there!");	
		//click on Confirm Booking
		driver.findElement(By.id("com.tiffintom:id/btnConfirm")).click();
		//Verify Reservation Booking
		try {
			boolean checkReservation = driver.findElement(By.id("com.tiffintom:id/tvOrderTitle")).isDisplayed(); //if this element present, it order placed successful.
			//bookingId put into a Varible & Print it.
			WebElement bookingId =  driver.findElement(By.id("com.tiffintom:id/tvOrderTitle"));		
			System.out.println(" - Your Booking id is = "+bookingId.getText()+"\r\n"+ "\r\n"+" - Your reservation hasbeen placed\r\n");
			logs.info(" - Your Booking id is = "+bookingId.getText()+" - Your reservation hasbeen successfully placed");
			//Agar order sucessfully placed ho gya hai to Home page par nevigate karwa do.

			//Click on Back to Home Page
			driver.findElement(By.xpath("//android.widget.TextView[@text='Back to homepage']")).click();
			Thread.sleep(2000);	
		}
		catch(Exception e)
		{
			System.out.println(" - Reservation Not Placed\r\n" + "");
			logs.info(" - Reservation Not Placed");
			Thread.sleep(2000);	
		}
	}	
}



