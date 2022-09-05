package com.appiumtest;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class TC_Scrolling_ProductList extends BaseTest{

	@Test(description = "Scrolling in the product list")
	public void scollingInTheProductList() {
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Angola']")).click();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Edgar");
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")));
		
		String textElement = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText().trim();
		
		Assert.assertEquals(textElement, "Products");
		
		//Scrolling to the product list
		scrollToElement("Jordan 6 Rings");
		
		driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])[2]")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")));
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")));
		
		//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		
		String titleText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText().trim();
		
		String productText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText().trim();
		
		Assert.assertEquals(titleText, "Cart");
		
		Assert.assertEquals(productText, "Jordan 6 Rings");
		
	}
	
}
