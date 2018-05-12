package app.tests;


import org.openqa.selenium.WebDriver;

import app.helper.BasePage;
import app.utils.AutomationUtils;

import java.io.IOException;

import org.junit.*;

public class TestCases {
	
	public static WebDriver testQA;
	
    BasePage basepage;	
 
	@BeforeClass
	public static void startApp() throws IOException
	{
		System.out.println("==================Welcome to Automation ===================");		

		if(testQA == null)
		{
			testQA = AutomationUtils.getWebDriver();
			testQA.manage().window().maximize();
	    }		
	}
	
	@AfterClass
	public static void closeAll()
	{
		if(testQA!=null)
		{
			System.out.println("==================Thanks for Automation ===================");		
			testQA.close();
			testQA= null;
		}
		
	}
	
	@Test
	public void test01() 
	{
		basepage = new BasePage(testQA);
		
		Assert.assertTrue(testQA.getTitle().equals("Googleghgh"));			
	}
}
