package com.appiumtest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class TC_ToastMessages extends BaseTest{
	
	@Test(description = "Verifying toast messages")
	public void toastMessages() {
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Angola']")).click();
		
		driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String toastMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getText().trim();
		
		WebElement element = driver.findElement(AppiumBy.xpath("(//android.widget.FrameLayout)[1]"));
		
		Assert.assertTrue(element.isDisplayed());
		
		Assert.assertEquals(toastMessage, "Please enter your name");
		
	}

}
