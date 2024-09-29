package Appium.FrameWork.PageObjects.andriod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import AppiumFrameWork.Utils.AndriodAction.AndriodAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class productCataloguePage extends AndriodAction {
	AndroidDriver driver;
    public checkOutPage checkOutpage;
	public productCataloguePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> productList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCartButtons;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartbutton;

	public void addProductToCart(String[] productNames) {
		for (String customerShoeName : productNames) {

			// Scroll to the product
			scrollToSpecificText(customerShoeName);

			// Get the list of product names
			List<WebElement> productList = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
			// Get the list of product names cartbutton
			List<WebElement> addToCartButtons = driver
					.findElements(By.id("com.androidsample.generalstore:id/productAddCart"));

			int productCartsize = productList.size();

			// System.out.println(productCartsize);
			for (int i = 0; i < productCartsize; i++) {

				String ShoeName = productList.get(i).getText();
				// System.out.println(ShoeName);
				if (ShoeName.equalsIgnoreCase(customerShoeName)) {
					addToCartButtons.get(i).click();
					System.out.println(customerShoeName + " added to the cart");
					// System.out.println("End of loop i"+i);
				}
			}

		}
	}

	public checkOutPage clickCarticon() {
		cartbutton.click();
		return new checkOutPage(driver);
	}
}
