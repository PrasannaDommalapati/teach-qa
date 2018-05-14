package app.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AutomationUtils
{
	private static String baseUrl = "https://www.google.co.uk";//keep ur own base URL
	private static String OS = null; 
	
	public static WebDriver getWebDriver() throws IOException
	{
		
		setDriverPath();
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\assets\\windows\\geckodriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		
		return driver;
	}
	
	public static String getOS() {
		
		OS = System.getProperty("os.name");
		
		return OS;
	}
	
	public static void setDriverPath() {
		if(getOS().equals("Mac OS X")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/assets/mac/chromedriver"); 
		} else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\assets\\windows\\chromedriver.exe");
		}
	}
	
	public static String getDataLocation() {
		if(getOS() == null) {
			return "";
		}
		else if(getOS().equals("Mac OS X")) {
			return System.getProperty("user.dir")+"/assets/data/InputDataVersionPositive1.0.xlsx";
		}else {
			return  System.getProperty("user.dir")+"\\assets\\data\\InputDataVersionPositive1.0.xlsx";
		}
	}

}
