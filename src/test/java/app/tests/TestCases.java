package app.tests;


import org.openqa.selenium.WebDriver;

import app.helper.BasePage;
import app.pages.SearchPage;
import app.utils.AutomationUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCases {

	public static WebDriver testQA;

	SearchPage searchPage;
	BasePage basePage;

	@BeforeAll
	public static void startApp() throws IOException {
		System.out.println("==================Welcome to Automation ===================");

		if (testQA == null) {
			testQA = AutomationUtils.getWebDriver();
			testQA.manage().window().maximize();
		}
	}

	@AfterAll
	public static void closeAll() {
		if (testQA != null) {
			System.out.println("==================Thanks for Automation ===================");
			testQA.close();
			testQA = null;
		}

	}

	@Test
	public void test01() {
		searchPage = new SearchPage(testQA);
		basePage = new BasePage(testQA);

		searchPage.searchFunctionality();

		assertAll("test title", () -> assertEquals("Selenium - GoogleSearch", basePage.pageTitle()));
	}
}
