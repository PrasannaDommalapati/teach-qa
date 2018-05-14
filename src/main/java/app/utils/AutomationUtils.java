package app.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;


public class AutomationUtils
{
	private static String baseUrl = "https://www.google.co.uk";//keep ur own base URL
	public static final String BROWSER = "chrome";
	public static WebDriver driver = null;
	
	public static WebDriver getWebDriver() throws IOException
	{
				
		switch (BROWSER) {
		case "chrome":
			if(getOS().equals("Mac OS X")) 
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/assets/mac/chromedriver");
			 else
				 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\assets\\windows\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			if(getOS().equals("Mac OS X")) 
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/assets/mac/geckodriver");
			 else
				 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\assets\\windows\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			if(getOS().equals("Mac OS X")) 
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/assets/mac/iedriver");
			 else
				 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\assets\\windows\\iedriver.exe");
			driver = new InternetExplorerDriver();
			break;
		case "edge":
			if(getOS().equals("Mac OS X")) 
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/assets/mac/edgedriver");
			 else
				 System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\assets\\windows\\edgedriver.exe");
			driver = new EdgeDriver();
			break;
		case "safari":
			if(getOS().equals("Mac OS X")) 
				System.setProperty("webdriver.safari.driver", System.getProperty("user.dir")+"/assets/mac/safaridriver");
			 else
				 System.setProperty("webdriver.safari.driver", System.getProperty("user.dir")+"\\assets\\windows\\safaridriver.exe");
			driver = new SafariDriver();
			break;

		default:
			System.out.println("no such browser");
			break;
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		
		return driver;
	}
	
	public static String getOS() {
		
		return System.getProperty("os.name");
	}
	
	public static String getDataLocation() {
		if(getOS() == null) {
			return "You are running Tests in invalid Operating System! Please try in valid operating System";
		}
		else if(getOS().equals("Mac OS X")) {
			return System.getProperty("user.dir")+"/assets/data/InputDataVersionPositive1.0.xlsx";
		}else {
			return  System.getProperty("user.dir")+"\\assets\\data\\InputDataVersionPositive1.0.xlsx";
		}
	}

}
