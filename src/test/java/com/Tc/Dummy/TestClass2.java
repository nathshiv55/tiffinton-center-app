package com.Tc.Dummy;

import org.testng.annotations.Test;

import com.Tc.BasePackage.BaseInit;

public class TestClass2 extends BaseInit {
	@Test
	public void testMethod1() throws Exception
	{
		basicCapabilities();
		startUp();
	}

}
