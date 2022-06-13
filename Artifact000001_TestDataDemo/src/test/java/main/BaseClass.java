package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	private ExtentSparkReporter reporter;
	public static ExtentReports report;
	public static WebDriverWait wait;
	public static XSSFWorkbook wb;  
	public static XSSFSheet sh; 
	
  @BeforeSuite
  @Parameters("browser")
  public void beforeSuite(String browser) throws IOException{
			 
		  if(browser.equalsIgnoreCase("chrome"))  WebDriverManager.chromedriver().setup();
		  
		  reporter = new ExtentSparkReporter("ExtentReports/extentreport_"+new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss_SSS").format(new Date())+".html");	  //ExtentReports/extentreport_...
		  reporter.config().setDocumentTitle("DocumentTitle_01");
		  reporter.config().setReportName("ReportName_01");
		  reporter.config().setTheme(Theme.DARK); 
		  report = new ExtentReports();
		  report.attachReporter(reporter); 
		  report.setSystemInfo("OS", "OS_Windows 10");
		  report.setSystemInfo("Environment", "Env_QA");
		 
		  FileInputStream f = new FileInputStream ("TestData.xlsx");
		  wb = new XSSFWorkbook(f);
		  sh = wb.getSheet("Sheet1");
    }
	
  @BeforeTest
  @Parameters("browser")
  public void beforeTest(String browser) {
 
      if(browser.equalsIgnoreCase("chrome"))
    	  	driver = new ChromeDriver();
      driver.manage().window().maximize(); 
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
      wait = new WebDriverWait(driver, 120); //2 mins
   }
  
  @AfterTest  public void afterTest(){ driver.close(); }

  @AfterSuite public void afterSuite(){ report.flush(); }
}