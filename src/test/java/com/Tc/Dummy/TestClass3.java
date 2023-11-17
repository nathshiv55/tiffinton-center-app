package com.Tc.Dummy;

import java.io.FileInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestClass3 {


	public static AndroidDriver driver;   //public static so we can use it anyWhere in Project.
	public static Properties appdata;     //public so we can use it anyWhere in Project. static so we can call it without class name.
	DesiredCapabilities caps;
	public static Logger logs;

	@SuppressWarnings("deprecation")
	@Test
	public  void basicCapabilities() throws Exception

	{
		logs = Logger.getLogger("devpinoyLogger");

		appdata = new Properties();
		FileInputStream fi = new FileInputStream("./src/test/resources/PropertiesData/AppData.properties");
		appdata.load(fi);
		try {
			/*
			caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,appdata.getProperty("deviceName"));
			caps.setCapability(MobileCapabilityType.UDID,appdata.getProperty("udid"));
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,appdata.getProperty("platformName"));
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,appdata.getProperty("platformVersion"));
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,appdata.getProperty("automationName"));
			caps.setCapability("newCommandTimeout", "200");
*/

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Nokia 8.1");
			caps.setCapability(MobileCapabilityType.UDID,"PNXID18121001695");
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"11");

			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			
			
			driver = new AndroidDriver (new URL ("http://0.0.0.0:4723/wd/hub"),caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		catch(Exception e)
		{
			//When an exception is chained, the getCause method is used to get the original cause. 
			System.out.println("Cause is : "+e.getCause());
			System.out.println("Message is : "+e.getMessage());
			e.printStackTrace();

		}

		boolean IsAppInstalled = driver.isAppInstalled("com.tiffintom");

		if(IsAppInstalled)
		{
			driver.startActivity(new Activity("com.tiffintom", "com.tiffintom.ui.base.MainActivity"));   //package name & ActivityName.
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			logs.info("App successfully open...");  //ye log me print karega.
			System.out.println("App successfully open...");   //Ye console window me print karega. 



		}
		else
		{
			driver.installApp(System.getProperty("user.dir")+"/APK/TiffinTom_5.2.apk");    //Paste build in APK folder.

			try
			{
				driver.startActivity(new Activity("com.tiffintom", "com.tiffintom.ui.base.MainActivity"));   //this will start the activity.
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				logs.info("App successfully launched...");
				System.out.println("App successfully launched...");

				//when new installation , its need to select country before loginPage
				//Select Country
				driver.findElement(By.id("com.tiffintom:id/tvSelectedCountry")).click();
				driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView")).click();
				driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView")).click();

			}
			catch(Exception e1)
			{
				System.out.println("App not open....");

			}
		}



		//Sign In
		driver.findElement(By.id("com.tiffintom:id/etEmail")).sendKeys("testap1@yopmail.com");
		driver.findElement(By.id("com.tiffintom:id/etPassword")).sendKeys("Shiv@123");
		driver.findElement(By.id("com.tiffintom:id/btnSignin")).click();


		//Enter PostCode
		driver.findElement(By.id("com.tiffintom:id/etPostcode")).sendKeys("WS14BA");
		driver.findElement(By.id("com.tiffintom:id/btnConfirm")).click();

		Thread.sleep(3000);


		//Log Out
		driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Settings\"]/android.widget.FrameLayout[2]/android.widget.ImageView")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.tiffintom:id/cvLogout")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("com.tiffintom:id/tvConfirm")).click();

		Thread.sleep(1000);

		try {

			boolean checkLogout = driver.findElement(By.id("com.tiffintom:id/btnSignin")).isDisplayed(); //if this element present, it means logOut successful.
			//logs.info("LogOut Successful...");
			System.out.println("LogOut Successful...");
		}
		catch(Exception e)
		{
			System.out.println("User Not LogOut...");
			//logs.info("User Not LogOut...");

		}
		
		driver.quit();
	}
}






