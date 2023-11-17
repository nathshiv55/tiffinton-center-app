package com.Tc.Dummy;

import org.testng.annotations.Test;

import com.Tc.BasePackage.BaseInit;

public class TestClass1 extends BaseInit{

	String orderIDinHistory = "100123";
	String orderId = "#100123";
	@Test
	public void testMethod1() throws Exception
	{
	
		if(orderId.endsWith(orderIDinHistory))
		{
			System.out.println("Order found in order history");
		}else {
			System.out.println("Order not found in order History");
		}
	}
}