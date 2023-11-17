package com.Tc.Dummy;
import java.awt.Color;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Tc.BasePackage.BaseInit;

public class TestClass5 extends BaseInit {
   @BeforeClass
	public static void uygg()
	{
		System.out.println("d1ffd\r\n"+"\r\n" + "eeeeeee\r");
		System.out.println("ggggg\r\n");
		System.out.println("mmmmmmm\r");
		
		System.out.println(" - Your order id is = "+"\r\n"+ "\r\n"+" - Order SucessFully placed\r\n");
		//System.out.println(" - Your order id is = "+orderId.getText()\r\n " +  "\"- Order Sucessfully Placed\r\n" + "\");
	}

   @Test
  	public static void date()
  	{
	   String userName = ""+(int)(Math.random()*Integer.MAX_VALUE);
	   String emailID = "user"+userName+"@yopmail.com";
	   System.out.println(emailID);
	   
	 
}}