package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.OpenSalesOrderFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class OpenSOTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static OpenSalesOrderFunctions OpenSOFunc = new OpenSalesOrderFunctions();
	private static int previousStatus = ITestResult.SUCCESS;

	@BeforeMethod(alwaysRun = true)
	private void VerifyLogin() {
		if (VariableHandler.getSessionID() == null || VariableHandler.getSessionID().equalsIgnoreCase("")
				|| VariableHandler.getSessionOpened() == false || VariableHandler.getSessionID().equals(null)) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
		} else {
			commonFunc.navigateToHomePage();
		}
	}

	/**
	 * Open SO (Rate Contract) - Domestic Customer
	 * 
	 * @author Kowshik-RMITS
	 * 
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 1, groups = {
			"Open Sales Order" }, description = "Open SO (Rate Contract) - Domestic Customer , Open SO (Rate Contract) ")
	public void VerifyOpenSoForDomesticCustomer() {
		OpenSOFunc.verifyOpenSO();
	}

	/**
	 * Open SO (Rate Contract) - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 * 
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 2, groups = {
			"Open Sales Order" }, description = "Open SO (Rate Contract) -  Customer , Open SO (Rate Contract) - Export Customer")
	public void VerifyOpenSoForExportCustomer() {
		OpenSOFunc.verifyOpenSoForExportCustomer();
	}

	/**
	 * Cancel Open SO
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 3, groups = { "Cancel Open SO" }, description = "Cancelled Open SO")
	public void VerifyCancelStatement() {
		OpenSOFunc.verifyCancelStatement();
	}

	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
