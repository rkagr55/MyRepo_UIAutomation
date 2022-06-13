package utilities;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class captureSnaps {

	public static File captureScreenshot(WebDriver driver){
		File destinationPath = null;
		try { 
		  File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  String destination = "Screenshots/screenshot_"+new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss_SSS").format(new Date())+".png"; //target/screenshot..
		  destinationPath = new File(destination);
		  FileHandler.copy(sourcePath, destinationPath);
		  
		 }catch(Exception e) { e.printStackTrace(); }
		return destinationPath;
	  }
}
