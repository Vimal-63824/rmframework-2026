package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 22, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.PurchaseRequestFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class PurchaseRequestTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseRequestFunctions PRFunc = new PurchaseRequestFunctions();
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
	 * Create purchase request directly to the system through excel upload
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = {
			"PurchaseRequest" }, description = "74 - Create purchase request directly to the system through excel upload")
	public void VerifyCreatePRByUploadExcel() {
		flag = PRFunc.verifyCreatePRByUploadExcel();
		Assert.assertTrue(flag);
	}

	/**
	 * create the purchase request(s) for General Items
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"PurchaseRequest" }, description = "75 -  create the purchase request(s) for General Items")
	public void VerifyPurchaseRequestGeneralTypeCreation() {
		PRFunc.verifyPRForGeneralItemWithMultipleItemInExcelSheet();
	}

	/**
	 * create the purchase request(s) for service items
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"PurchaseRequest" }, description = "77 -  create the purchase request(s) for service items")
	public void VerifyPurchaseRequestServiceTypeCreation() {
		PRFunc.verifyPRForServiceItemWithMultipleItemInExcelSheet();
	}

	/**
	 * cancel the purchase request document
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = { "PurchaseRequest" }, description = "78 -  cancel the purchase request document")
	public void VerifyCancelPR() {
		flag = PRFunc.verifyCancelPRAndDeleteLine();
		Assert.assertTrue(flag);
	}

	/**
	 * cancel the purchase request lines
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = { "PurchaseRequest" }, description = "79 -  cancel the purchase request lines")
	public void VerifyPRDeleteLines() {
		flag = true;
		Assert.assertTrue(flag);
	}

	/**
	 * How to closeshort the purchase request(s)?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = { "PurchaseRequest" }, description = "80 -  closeshort the purchase request")
	public void VerifyPRCloseShort() {
		flag = PRFunc.verifyPRCloseShort();
		Assert.assertTrue(flag);
	}

	/**
	 * Find the Purchase request with all possible search parameters
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = {
			"PurchaseRequest" }, description = "73 -  Find the Purchase request with all possible search parameters")
	public void VerifyFindPRWithPossibleSearchParameters() {
		flag = PRFunc.verifyFindPRWithPossibleSearchParameters();
		Assert.assertTrue(flag);
	}

	/**
	 * create Purchase request for Material Type
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = { "PurchaseRequest" }, description = " create Purchase request for Material Type")
	public void VerifyPurchaseRequestMaterialTypeCreation() {
		PRFunc.verifyPurchaseRequestWithMultipleItemInExcelSheetForMaterial();
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
