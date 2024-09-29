package Appium.FrameWork;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import Appium.FrameWork.PageObjects.andriod.formPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public formPage formpage;
	@BeforeClass
	public void ConfigureAppiumServer() throws MalformedURLException, URISyntaxException {
		//WebDriverManager.chromedriver().driverVersion("113.0.5672").setup();
		//To Start Appium server automatically
		   service = new AppiumServiceBuilder()
		  .withAppiumJS(new File("C:\\Users\\riyaz\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")) //location of main.js file 
		  .withIPAddress("127.0.0.1") //ip address
		  .usingPort(4723) //port number
		  .withTimeout(Duration.ofSeconds(30))
		  .build();
		  
		  service.start();
		 
		
		//All Mobile details
		UiAutomator2Options options= new UiAutomator2Options();
		options.setDeviceName("Utkarshemulator"); //emulator i.e Device name
		//options.setChromedriverExecutable("C:\\Users\\Utkarsh\\eclipse-workspace\\Appium\\Drivers\\chromedriver.exe");
		options.setApp("D:\\012LivetechWS\\AppiumMobileTesting\\General-Store.apk"); // location of the app which we have to test.
		//It is first get install on the emulator(mobile)
		
													          //URL                    //MobileDetails
		 driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 
		 formpage = new formPage(driver);
		 
	}
	
	public void Longpress_Action(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) (element)).getId(), "duration", 2000));

	}

	public void scrollToEndAction() {
		boolean canScrollMore;

		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);
	}

	public void swipeAction(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.05));
	}

	public Double StringToDouble(String S1) {

		String priceWithSign = S1.replace("$", " ").trim();
		Double ActualPrice = Double.parseDouble(priceWithSign);

		return ActualPrice;

	}

	public void scrollToSpecificText(String text) {
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));

	}

	//@AfterClass
	public void tearDown() {
		driver.quit(); // Stop the App

		service.stop(); // Stop the Appium Server
	}
	 

}
