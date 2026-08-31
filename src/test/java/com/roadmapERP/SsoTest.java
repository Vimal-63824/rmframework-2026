package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
/**
 /**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.SsoPageFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class SsoTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SsoPageFunctions Ssofunc = new SsoPageFunctions();
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
	 * Create Supplier Schedule Order For Domestic Supplier Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 1, groups = {
			"Request For Quotation" }, description = "Domestic Supplier - General Items with Similar Stock and Supplier UOM")
	public void verifyCreationOfSsoDomesticSupplierSimilarUom() {
		Ssofunc.verifyCreationOfSsoDomesticSupplierSimilarUom();
	}

	/**
	 * Create Supplier Schedule Order For Import Supplier Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 2, groups = {
			"Request For Quotation" }, description = "Import Supplier - General Items with Similar Stock and Supplier UOM")
	public void verifyCreationOfSsoImportSupplierSimilarUom() {
		Ssofunc.verifyCreationOfSsoImportSupplierSimilarUom();
	}

	/**
	 * Create Supplier Schedule Order For Domestic Supplier DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 3, groups = {
			"Request For Quotation" }, description = "Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void verifyCreationOfSsoDomesticSupplierDisSimilarUom() {
		Ssofunc.verifyCreationOfSsoDomesticSupplierDisSimilarUom();
	}

	/**
	 * Create Supplier Schedule Order For Import Supplier DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 4, groups = {
			"Request For Quotation" }, description = "Import Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void verifyCreationOfSsoImportSupplierDisSimilarUom() {
		Ssofunc.verifyCreationOfSsoImportSupplierDisSimilarUom();
	}

	/**
	 * Cancel Supplier Schedule Order For Domestic Supplier Lines
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 5, groups = { "Request For Quotation" }, description = " cancel Supplier Schedule Order Lines")
	public void CancelSupplierScheduleOrderLines() {
		Ssofunc.cancelSupplierScheduleOrderLines();
	}

	/**
	 * Cancel Supplier Schedule Order For Domestic Supplier Document
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 6, groups = { "Request For Quotation" }, description = " cancel Supplier Schedule Order Document")
	public void CancelSupplierScheduleOrderDocument() {
		Ssofunc.cancelSupplierScheduleOrderDocument();
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
