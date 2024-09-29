package Appium.FrameWork;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class LanchApp {

	public static void main(String[] args) throws MalformedURLException {


		UiAutomator2Options options=new UiAutomator2Options();
		
		options.setDeviceName("Samsung A32");
		
		options.setApp("D:\\012LivetechWS\\AppiumMobileTesting\\General-Store.apk");
		
		AndroidDriver driver=new AndroidDriver(new URL("http://127.0.0.1:4723/ "), options);
		
		

	}

}
