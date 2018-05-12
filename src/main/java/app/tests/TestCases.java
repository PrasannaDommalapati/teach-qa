package app.tests;


import org.openqa.selenium.WebDriver;

import app.pages.SearchPage;
import app.utils.AutomationUtils;

import java.io.IOException;

import org.junit.*;

public class TestCases {
	
	public static WebDriver testQA;
		
    SearchPage searchPage;
 
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
		searchPage = new SearchPage(testQA);
		
		searchPage.enterSearchText();
		searchPage.clickSearchBtn();
		
		Assert.assertTrue(testQA.getTitle().equals("Selenium - Google Search"));			
	}
}
