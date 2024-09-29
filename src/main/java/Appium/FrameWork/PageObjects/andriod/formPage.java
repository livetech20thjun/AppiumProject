package Appium.FrameWork.PageObjects.andriod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import AppiumFrameWork.Utils.AndriodAction.AndriodAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class formPage extends AndriodAction{
	
	AndroidDriver driver;
	public productCataloguePage productcataloguepage;
	public formPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// Click on Country Drop down
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;
	                                           	
	
	//Send User Name
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement UserName;
	
	//Female Gender
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement FemaleGender;
	
	//Male Gender
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement MaleGender;
	
	//Submit button
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement SubmitButton;
	
	public void selectUserCountry(String countryname) {
		countryDropdown.click();
		scrollToSpecificText(countryname);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryname+"']")).click();
	}
	
	
	public void setUserName(String userName) {
		UserName.sendKeys(userName);
		driver.hideKeyboard();
	}
	
	public void setUserGender(String userGender) {
		if (userGender.equalsIgnoreCase("Male")) {
			MaleGender.click();
		} else {
			FemaleGender.click();
		}
	}
	
	public productCataloguePage submitForm() {
		 SubmitButton.click();
		 return new productCataloguePage(driver);
	}
	
	

}
