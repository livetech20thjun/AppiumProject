package Appium.FrameWork;

import org.testng.Assert;
import org.testng.annotations.Test;

import Appium.FrameWork.PageObjects.andriod.AndriodBrowser;
import Appium.FrameWork.PageObjects.andriod.checkOutPage;
import Appium.FrameWork.PageObjects.andriod.formPage;
import Appium.FrameWork.PageObjects.andriod.productCataloguePage;

public class E2E_eCommerce_TC extends BaseClass {

	@Test
	public void fillFormDetails() throws InterruptedException {

		// Click on Country Drop down
		formpage.selectUserCountry("Romania");

		// Enter username
		formpage.setUserName("Reyaz");

		// Enter user gender
		formpage.setUserGender("Male");

		// Submit form
		productCataloguePage productcataloguepage = formpage.submitForm();

		// Add to shoe in the cart
		String[] Shoename = { "Jordan Lift Off"};
		productcataloguepage.addProductToCart(Shoename);

		checkOutPage checkOutpage = productcataloguepage.clickCarticon();

		Thread.sleep(2000);

		// Wait until the cart page is loaded
		checkOutpage.waitTillCartPageIsLoad();

		// Assert the total and actual price
		String[] price = checkOutpage.AssertOnPrice();
		Assert.assertEquals(price[0], price[1]);

		// Assert if the Alert message is present
		String actualtext = checkOutpage.LongPressOnTermsNconditions();
		Assert.assertEquals(actualtext, "Terms Of Conditions");

		// Close the Alert Box
		checkOutpage.closeTermNConditionWindow();

		// Complete the Purchase
		AndriodBrowser andriodbrowser = checkOutpage.CompletePurchase();

		Thread.sleep(6000);

		// Get all the context handles used by developer
		//andriodbrowser.SwitchToChromeDriver();
	}

}
