package com.Tc.BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseInit {

	public static AndroidDriver driver;   //public static so we can use it anyWhere in Project.
	public static Properties appdata;     //public so we can use it anyWhere in Project. static so we can call it without class name.
	public static DesiredCapabilities caps;
	public static Logger logs;


	public  void startUp() throws Exception

	{
		logs = Logger.getLogger("devpinoyLogger");

		appdata = new Properties();
		FileInputStream fi = new FileInputStream("./src/test/resources/PropertiesData/AppData.properties");
		appdata.load(fi);

		caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME,appdata.getProperty("deviceName"));
		caps.setCapability(MobileCapabilityType.UDID,appdata.getProperty("udid"));
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME,appdata.getProperty("platformName"));
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,appdata.getProperty("platformVersion"));
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,appdata.getProperty("automatorName"));

		try {
			caps.setCapability("appPackage", appdata.getProperty("packageName"));
			caps.setCapability("appActivity", appdata.getProperty("activityName"));


			caps.setCapability(MobileCapabilityType.NO_RESET, "true");      //do not clear cache
			caps.setCapability(MobileCapabilityType.FULL_RESET, "false");   //do not install the app again 

			driver = new AndroidDriver (new URL ("http://0.0.0.0:4723/wd/hub"),caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			try
			{
				boolean checkLogin = driver.findElement(By.id("com.tiffintom:id/tvSelectedCountry")).isDisplayed(); 
				//Select Country
				driver.findElement(By.id("com.tiffintom:id/tvSelectedCountry")).click();
				driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView")).click();
				driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView")).click();

				System.out.println("\r\n"+"******App already installed & open Successfully...******"+"\r\n");
				logs.info("App already installed & open Successfully...");
				driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
			}
			catch(Exception e1)
			{
				System.out.println("\r\n"+"******App already installed & open Successfully...******"+"\r\n");
				logs.info("App already installed & open Successfully...");
				driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

			}

		}
		catch(Exception e) {

			caps.setCapability(MobileCapabilityType.APP, new File("C:\\Users\\Lenovo\\Documents\\apkFiles\\TiffinTom_5.2.apk").getAbsolutePath());

			driver = new AndroidDriver (new URL ("http://0.0.0.0:4723/wd/hub"),caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			System.out.println("\r\n"+"******App installed & open successfully...******"+"\r\n");
			logs.info("App installed & open successfully...");
			//Select Country
			driver.findElement(By.id("com.tiffintom:id/tvSelectedCountry")).click();
			driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView")).click();
			driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView")).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

		}
	}

	public void appUnstalled() throws IOException, InterruptedException{

		// Uninstall the app
		String appPackage = appdata.getProperty("packageName");    //Give package name of the app you want to uninstall
		driver.removeApp(appPackage);
		Thread.sleep(3000);

		System.out.println("******App uninstalled successfully...******\r\n");
		logs.info("App uninstalled successfully...");
		System.out.println("****************************************************************");
		// Close the driver
		driver.quit();
	}

}
