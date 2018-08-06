package app.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
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
	private static String OS = null;
	public static WebDriver driver = null;
	
    // properties
    public static Properties CONFIG;
    public static Properties OR;
    
	public void config() throws Exception {
		
        FileInputStream fs;
		try {
			fs = new FileInputStream("src/test/java/com/totsy/config/config.properties");
			CONFIG= new Properties();
	        CONFIG.load(fs);

	        fs = new FileInputStream("src/test/java/com/totsy/config/or.properties");
	        OR= new Properties();
	        OR.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
	}
	
	public static WebDriver getWebDriver() throws IOException
	{
				
		switch (BROWSER) {
		case "chrome":
			if(isMac()) 
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/assets/mac/chromedriver");
			 else if(isWindows()) 
				 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\assets\\windows\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			if(isMac()) 
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/assets/mac/geckodriver");
			 else if(isWindows()) 
				 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\assets\\windows\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			if(isMac()) 
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/assets/mac/iedriver");
			 else if(isWindows()) 
				 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\assets\\windows\\iedriver.exe");
			driver = new InternetExplorerDriver();
			break;
		case "edge":
			if(isMac()) 
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/assets/mac/edgedriver");
			 else if(isWindows()) 
				 System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\assets\\windows\\edgedriver.exe");
			driver = new EdgeDriver();
			break;
		case "safari":
			if(isMac()) 
				System.setProperty("webdriver.safari.driver", System.getProperty("user.dir")+"/assets/mac/safaridriver");
			 else if(isWindows()) 
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
	

     public static String getOS(){
         if(OS == null) { OS = System.getProperty("os.name"); }
         return OS;
     }
     public static boolean isWindows(){
         return getOS().startsWith("Windows");
     }

     public static boolean isMac(){
         return getOS().startsWith("Mac");
     }
	
	public static String getDataLocation() {
		
		if(isMac()) {
			return System.getProperty("user.dir")+"/assets/data/InputDataVersionPositive1.0.xlsx";
		}else if(isWindows()) {
			return  System.getProperty("user.dir")+"\\assets\\data\\InputDataVersionPositive1.0.xlsx";
		}else 
		{
			return "You are running Tests in invalid Operating System";
		}
	}

}
