package Appium.FrameWork.PageObjects.andriod;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AppiumFrameWork.Utils.AndriodAction.AndriodAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class checkOutPage extends AndriodAction {

	AndroidDriver driver;
	public AndriodBrowser andriodbrowser;
	public checkOutPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	WebElement CartPageTitle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	List<WebElement> TotalProductInTheCart;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	WebElement AcutalTotalPrice;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	WebElement termsButton;

	@AndroidFindBy(id = "android:id/button1")
	WebElement termsCloseButton;

	@AndroidFindBy(id = "android.widget.CheckBox")
	WebElement CheckBoxButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	WebElement CompletePurchaseButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
	WebElement Alerttext;

	public void waitTillCartPageIsLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(CartPageTitle, "text", "Cart"));
	}

	public String[] AssertOnPrice() {
		int productCount = TotalProductInTheCart.size();
		Double ExpectedPrice = 0.0;
		for (int i = 0; i < productCount; i++) {
			String ShoePriceString = TotalProductInTheCart.get(i).getText();

			Double ShoePrice = StringToDouble(ShoePriceString);
			System.out.println(ShoePrice);
			ExpectedPrice = ExpectedPrice + ShoePrice;

		}

		Double AcutalPrice = StringToDouble(AcutalTotalPrice.getText());
		return new String[]{AcutalPrice.toString(), ExpectedPrice.toString()};
	}
	
	public String LongPressOnTermsNconditions() {
		Longpress_Action(termsButton);
		String actualText = Alerttext.getText();
		return actualText;
	}
	
	public void closeTermNConditionWindow() {
		termsCloseButton.click();
	}
	
	public AndriodBrowser CompletePurchase() {
		CompletePurchaseButton.click();
		return new AndriodBrowser(driver);
	}
}
