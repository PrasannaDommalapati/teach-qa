package app.tests;


import org.openqa.selenium.WebDriver;

import app.pages.SearchPage;
import app.utils.AutomationUtils;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCases {
	
	public static WebDriver testQA;
		
    SearchPage searchPage;
 
	@BeforeAll
	public static void startApp() throws IOException
	{
		System.out.println("==================Welcome to Automation ===================");		

		if(testQA == null)
		{
			testQA = AutomationUtils.getWebDriver();
			testQA.manage().window().maximize();
	    }		
	}
	
	@AfterAll
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
