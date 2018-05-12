package app.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import app.helper.BasePage;


public class SearchPage extends BasePage{
	int row;
	
	public SearchPage(WebDriver driver)
	{
		super(driver);
		readExcel();
		row = cellLocation("SearchPage");
	}
	
	//============================ List of locators=====================
	private By search = By.id("lst-ib");
	private By searchBtn = By.name("btnK");
	
	//=========================== End of locators list =================
	
	public void enterSearchText() {
		
		enterTextTo(search, getData(0, row, 1));
	}
	
	public void clickSearchBtn() {
		
		clickOn(searchBtn);
	}
}
