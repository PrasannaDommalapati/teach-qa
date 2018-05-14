package app.helper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	WebDriver testQA;
	public static XSSFWorkbook workBook;
	public static XSSFSheet mySheet;
//	public static final String projStructure = System.getProperty("user.dir")+"\\assets\\data\\InputDataVersionPositive1.0.xlsx";
	public static final String projStructure = System.getProperty("user.dir")+"/assets/data/InputDataVersionPositive1.0.xlsx";

	public BasePage(WebDriver driv)
	{
		this.testQA = driv;
	}

	//==============================Excel Functions========================================
	public void readExcel()
	{
		try {
			File ff = new File(projStructure);
			FileInputStream fis = new FileInputStream(ff);
			workBook = new XSSFWorkbook(fis);
			 
		} catch (Exception e) {
			System.out.println("Exception::"+e);
		}
	}
	
	
	public String getData(int sheetnum, int row, int col)
	{
		mySheet = workBook.getSheetAt(sheetnum);
		String data = mySheet.getRow(row).getCell(col).getStringCellValue();
		return data;
		
	}
	public int rowCount(int sheetIndex)
	{		
		int count = workBook.getSheetAt(sheetIndex).getLastRowNum();
		count = count+1;
		return count;	
	}

	public int cellLocation(String textSearch) {
		
		int k =0;
		for (int i=0; i<rowCount(0); i++)
		{
			for(int j=0 ;j< 10; j++)
			{
			
				try
				{
					String testID = getData(0, i, j);// reading excel in the sheet1 from i row and j col
					Set<String> testData = new TreeSet<String>();
					testData.add(testID);
					
					Iterator<String> testdata_finder = testData.iterator();
					String passingData = (String)testdata_finder.next();
					
					//System.out.println(passingData);
					if(!passingData.isEmpty())
					{
						//System.out.println("passing data not empty"+i+"============="+j);
							if(passingData.contains(textSearch))
							{
								//System.out.println("Location of a row is::"+(i)+"====Column location is "+(j));
								k = i;
							}//finding the text in the cell starts with...
					}//end of finding empty cell (exit place if the cell is empty in excel sheet)	
				}
				catch(Exception e)
				{
				//catching an exception if the cell is empty
				}
			}
		}
		return k;
	}
	
	//################################# Element Methods ###################################
	
	public void enterTextTo(By identifier,String textToEnter)
	{
		try
		{
			testQA.findElement(identifier).sendKeys(textToEnter);
		}catch(Exception e)
		{
			System.out.println("Element is:::"+identifier);
			System.out.println("Exception is:::"+e);
		}
	}
	
	public void enterKeys(By locator, Keys keys) {
		try
		{
			testQA.findElement(locator).sendKeys(keys);
		}catch(Exception e)
		{
			System.out.println("Element is:::"+locator);
			System.out.println("Exception is:::"+e);
		}
	}
	public void clearTextIn(By identifier)
	{
		try
		{
			testQA.findElement(identifier).clear();
		}catch(Exception e)
		{
			System.out.println("Element is:::"+identifier);
			System.out.println("Exception is:::"+e);		}
	}
	public void clickOn(By identifier)
	{
		try
		{
			testQA.findElement(identifier).click();
		}catch(Exception e)
		{
			System.out.println("Element is:::"+identifier);
			System.out.println("Exception is:::"+e);		}
	}
	public void selectDropDown(By identifier, String value)
	{
		try{
			Select dropDown = new Select(testQA.findElement(identifier));
			dropDown.selectByIndex(0);
			enterTextTo(identifier, value);
		}catch(Exception e)
		{
			System.out.println("Element is:::"+identifier);
			System.out.println("Exception is:::"+e);
		}
	}

	public void selectDropDownByIndex(By identifier, int indexValue)
	{
		try{
			Select dropDown = new Select(testQA.findElement(identifier));
			dropDown.selectByIndex(indexValue);
		}catch(Exception e)
		{
			System.out.println("Element is:::"+identifier);
			System.out.println("Exception is:::"+e);
		}
	}

	public String getText(String locatorType, String value)
	{
		try
		{
			return elementFinder(locatorType, value).getText();
		}catch(Exception e)
		{
			System.out.println("Exception is::"+e);
			return null;
		}
	}
	
	public void waitUntill(By identifier)
	{
		WebDriverWait wait = new WebDriverWait(testQA, 10);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
		
	}
	
	public boolean isElementEnabled(By identifier)
	{
		try{
			return testQA.findElement(identifier).isEnabled();
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	//################################# Utility Methods ###################################
	
	public WebElement elementFinder(By locator)
	{
		try{
			return testQA.findElement(locator);
		}catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	public void alertHandler()
	{
		try{
			testQA.switchTo().alert().accept();
		}catch(Exception e)
		{
			System.out.println("Alert not present");
		}
	}
	public WebElement elementFinder(String locatorTpye, String value) 
	{
		
		if(locatorTpye.equals("id")) 
			return testQA.findElement(By.id(value));
		else if(locatorTpye.equals("name")) 
			return testQA.findElement(By.name(value));
		else if(locatorTpye.equals("xpath")) 
			return testQA.findElement(By.xpath(value));
		else if(locatorTpye.equals("css")) 
			return testQA.findElement(By.cssSelector(value));
		else if(locatorTpye.equals("tagName")) 
			return testQA.findElement(By.tagName(value));
		else if(locatorTpye.equals("linkText")) 
			return testQA.findElement(By.linkText(value));
		else if(locatorTpye.equals("partialLinkText"))
			return testQA.findElement(By.partialLinkText(value));
		else{
			System.out.println("No such element type available");
			return null;
		}
		
	}
	
	public List<WebElement> listOfElements(By locator)
	{
		List<WebElement> elementsList = testQA.findElements(locator);
		
		return elementsList;
	}	
	
	public void waitForSomeTime()
	{
		try{
			testQA.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void waitForParicularTime()
	{
		try {
			Thread.sleep(3000L);
			} 
		catch(InterruptedException e){
			e.printStackTrace();
			}

	}
	
	public void explicitWait(String xpath)
	{
		 WebDriverWait wait = new WebDriverWait(testQA, 10);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	public String pastDate(int days)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        Date myDate = new Date(System.currentTimeMillis());
       // System.out.println("result is "+ dateFormat.format(myDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, -days);
        //System.out.println(dateFormat.format(cal.getTime()));	
        return dateFormat.format(cal.getTime());
	}
	public String futureDate(int days)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        Date myDate = new Date(System.currentTimeMillis());
       // System.out.println("result is "+ dateFormat.format(myDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, +days);
        //System.out.println(dateFormat.format(cal.getTime()));	
        return dateFormat.format(cal.getTime());
	}

	public String pageTitle()
	{
		try
		{
			return testQA.getTitle();
		}catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	
	
	public void switchWindow()
	{
		String parentWindow = testQA.getWindowHandle();
		Set<String> handles =  testQA.getWindowHandles();
		   for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		    	   testQA.switchTo().window(windowHandle);//<!--Perform your operation here for new window/ to child window-->
		         //bcl.switchTo().window(parentWindow); //Control to parent window
		         testQA.manage().window().maximize();
		          }
		       }
		   
	} // End of switching window method.
	
	public void uploadDocument(String file) throws AWTException
	{
		 StringSelection ss = new StringSelection(file);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		 
		    //imitate mouse events like ENTER, CTRL+C, CTRL+V
		    Robot robot = new Robot();
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.delay(2000);
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);

	}
	
	public void downloadDocHandler() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
	    robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	public void dropDownHandle(By locator, String value)
	{
		Select dropDown = new Select(elementFinder(locator));
		dropDown.selectByIndex(0);
		elementFinder(locator).sendKeys(value);
	}
}
