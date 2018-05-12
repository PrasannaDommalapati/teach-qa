package app.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AutomationUtils
{
	private static String baseUrl = "https://www.google.co.uk";//keep ur own base URL
	
	public static WebDriver getWebDriver() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\assets\\windows\\chromedriver.exe");
		
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\assets\\windows\\geckodriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		
		return driver;
	}

}
