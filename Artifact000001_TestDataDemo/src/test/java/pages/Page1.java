package pages;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import main.BaseClass;
import utilities.captureSnaps;

public class Page1 extends BaseClass{

	private WebDriver driver;
	private ExtentTest test;
	
    public Page1(WebDriver driver, ExtentTest test) {
		this.driver=driver;
		this.test=test;		
		PageFactory.initElements(driver, this);
	}
	
	public boolean launchBrowser() {
		driver.get("https://www.google.com");
		wait.until(ExpectedConditions.titleIs("Google"));
		String url = driver.getCurrentUrl();
		
	    File destinationPath = captureSnaps.captureScreenshot(driver);

	    try{
			Assert.assertTrue(url.equals("https://www.google.com/")); 
			test.log(Status.PASS, "Google url is launched", MediaEntityBuilder.createScreenCaptureFromPath(destinationPath.getAbsolutePath()).build());  return true; 
		}catch(AssertionError er) {
 			test.log(Status.FAIL, "Google url is not launched", MediaEntityBuilder.createScreenCaptureFromPath(destinationPath.getAbsolutePath()).build()); return false;
		}
	    
	    
	}
}
