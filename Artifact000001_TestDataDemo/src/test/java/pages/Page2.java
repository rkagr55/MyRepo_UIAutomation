package pages;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import main.BaseClass;
import utilities.ExcelUtils;
import utilities.captureSnaps;

public class Page2 extends BaseClass{

	private WebDriver driver;
	private ExtentTest test;
	
	@FindBy (xpath=".//*[@name='q']") public WebElement searchbar;	
	
	public Page2(WebDriver driver, ExtentTest test) {
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}
	
	public boolean searchText(String input, String TestCaseID) {
		wait.until(ExpectedConditions.visibilityOf(searchbar));
		searchbar.sendKeys(input);
		String inputText = searchbar.getAttribute("value");	
		File destinationPath = captureSnaps.captureScreenshot(driver);
		
		try{
			Assert.assertEquals(inputText,ExcelUtils.getData(TestCaseID, "name")); 
			test.log(Status.PASS, "Correct input text is entered into search bar", MediaEntityBuilder.createScreenCaptureFromPath(destinationPath.getAbsolutePath()).build()); return true;
		}catch(AssertionError er) {
 			test.log(Status.FAIL, "Correct input text is not entered into search bar", MediaEntityBuilder.createScreenCaptureFromPath(destinationPath.getAbsolutePath()).build()); return false;
		}
	}
}
