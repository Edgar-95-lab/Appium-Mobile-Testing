package com.appiumtest;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class TC_ValidatingFunctionality  extends BaseTest{
	
	@Test(description = "Validating total amound generated functionality")
	public void testValidatingFunctionality() throws InterruptedException {
		
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
		
		driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
		
		driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")));
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")));
		
		//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		
		String titleText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText().trim();
		
		String productText1 = driver.findElement(AppiumBy.xpath("(//android.widget.TextView)[2]")).getText().trim();
		
		String productText2 = driver.findElement(AppiumBy.xpath("(//android.widget.TextView)[4]")).getText().trim();
		
		Assert.assertEquals(titleText, "Cart");
		
		Assert.assertEquals(productText1, "Air Jordan 9 Retro");
		
		Assert.assertEquals(productText2, "Jordan 6 Rings");
		
//		double price1 = Double.parseDouble(driver.findElement(AppiumBy.xpath("(//android.widget.TextView)[3]")).getText().substring(1));
//		
//		System.out.println("Price 1 = " + price1);
//		
//		double price2 = Double.parseDouble(driver.findElement(AppiumBy.xpath("(//android.widget.TextView)[5]")).getText().replace("$", ""));
//		
//		System.out.println("Price 2 = " + price2);
//		
//		double total = price1 + price2;
		
		List<WebElement> priceElements = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
		
		int sizeList = priceElements.size();
		
		double total = 0;
		
		for (int i = 0; i < sizeList; i++) {
			
			String product = priceElements.get(i).getText();
			
			total += Double.parseDouble(product.substring(1));
			
		}
		
		System.out.println("total = " + total);
		
		driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox)[1]")).click();
		
		String totalprice = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().replace("$", "");
		
		Assert.assertEquals(total, Double.parseDouble(totalprice.trim()));
		
		System.out.println("totalprice = " + totalprice);
		
		WebElement termsElement = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
		
		longPressAction(termsElement);
		
		String termsTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText();
		
		String termsMessage = driver.findElement(AppiumBy.id("android:id/message")).getText();
		
		Assert.assertTrue(termsTitle.contains("Terms Of Conditions"));
		
		Assert.assertTrue(!termsMessage.isEmpty());
		
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		Thread.sleep(5000);
		
	}

}
