package com.appiumtest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public AppiumDriverLocalService service;
	protected AndroidDriver driver;
	
	@BeforeMethod
	public void ConfigureAppium() throws MalformedURLException {
		String mainJS = "C:\\Users\\edgar.velazquez\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
		String app = "C:\\Users\\edgar.velazquez\\eclipse-workspace-demo\\AppiumGeneralStore\\src\\test\\resources\\com\\General-Store.apk";
		
		service = new AppiumServiceBuilder().withAppiumJS(new File(mainJS)).withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("MTPhone5");
		options.setChromedriverExecutable("C:\\Selenium Java\\mobile testing\\chromedriver.exe");
		options.setApp(app);
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
		service.stop();
	}
	
	public void longPressAction(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement)element).getId(), "duration",2000
			));
		Thread.sleep(2000);
	}
	
	public void scrollToElement(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))"));
	}
	
	public void scrollToElementAndClick(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))")).click();
	}
	
	public void scrolltoEnd() {
		boolean canScrollMore;
		
		do {
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 400, "top", 400, "width", 800, "height", 800,
			    "direction", "down",
			    "percent", 3.0
			));
		}while(canScrollMore);
	}
	
	public void swipeAction(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement)element).getId(),
			    "direction", direction.toLowerCase().trim(),
			    "percent", 0.75
			));
	}
	
	public void dragAction(WebElement element, int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "endX", x,
			    "endY", y
			));
	}

}
