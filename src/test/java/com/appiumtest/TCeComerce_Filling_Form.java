package com.appiumtest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class TCeComerce_Filling_Form extends BaseTest{
	
	@Test(description = "Test for the filling form")
	public void fillDemoForm() {
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		
		//driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Angola']")).click();
		
		scrollToElementAndClick("Mexico");
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Edgar");
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String textElement = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText().trim();
		
		Assert.assertEquals(textElement, "Products");
	}
	
}
