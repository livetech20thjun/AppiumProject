package Appium.FrameWork.PageObjects.andriod;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AppiumFrameWork.Utils.AndriodAction.AndriodAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AndriodBrowser extends AndriodAction {

	AndroidDriver driver;

	public AndriodBrowser(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void SwitchToChromeDriver() throws InterruptedException {
		Set<String> ContextHandles = driver.getContextHandles();

		for (String contextName : ContextHandles) {
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");

		Thread.sleep(6000);

		// driver.findElement(By.xpath("(//div[@class='SDkEP'])")).sendKeys(Keys.TAB);
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(1));
		WebElement element = wait2
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='q']")));

		element.sendKeys("Air Jordan 4 Retro");
		element.sendKeys(Keys.ENTER);
		
		Thread.sleep(6000);
		
	}

}
