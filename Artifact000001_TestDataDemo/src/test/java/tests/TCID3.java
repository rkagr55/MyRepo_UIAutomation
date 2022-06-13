package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import main.BaseClass;
import pages.Page1;
import pages.Page2;
import utilities.ExcelUtils;

public class TCID3 extends BaseClass{
	ExtentTest test;
	String TestCaseID;

	@Test
	public void Test5() {

		Page1 objPage1 = new Page1(driver,test);
		boolean result = objPage1.launchBrowser();
		if(result) Assert.assertTrue(true);
		else Assert.assertTrue(false);
	}

	@Test(dependsOnMethods={"Test5"})
	public void Test6(){

		Page2 objPage2 = new Page2(driver,test);
		boolean result = objPage2.searchText(ExcelUtils.getData(TestCaseID, "name"), TestCaseID); 
		if(result) Assert.assertTrue(true);
		else Assert.assertTrue(false);
	}

	@BeforeClass  
	public void beforeClass(){  
		test = report.createTest(this.getClass().getSimpleName()); 
		TestCaseID = this.getClass().getSimpleName();
	}
}