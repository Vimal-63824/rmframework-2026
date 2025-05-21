package com.roadmapERP;

/**
 * @author Deepan-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import static org.testng.Assert.assertTrue;

/**
 * @author Deepan-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.ShareOfBusinessFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class ShareOfBusinessTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static ShareOfBusinessFunctions shareOfBusinessFunc = new ShareOfBusinessFunctions();

	/**
	 * create Share of Business for an Item under Purchase Type
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 1, groups = {
			"Share Of Business" }, description = " create Share of Business for an Item under Purchase Type")
	public void VerifyCreateSOBForItemUnderPurchaseType() {
		shareOfBusinessFunc.verifyCreateShareOfBusinessForMnufactureAndPurchaseType();
		flag = true;
	}

	/**
	 * create Share of Business for an Item under Manufacture Type
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 2, groups = {
			"Share Of Business" }, description = " create Share of Business for an Item under Manufacture Type")
	public void VerifyCreateSOBForItemUnderManufactureType() {
		assertTrue(flag);
	}

	/**
	 * Share of Business Lines Cancel
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 3, groups = { "Share Of Business" }, description = "Share of Business Lines Cancel")
	public void VerifyCancelShareOfBusinessLineAndDocuments() {
		shareOfBusinessFunc.verifyCancelShareOfBusinessLineAndDocuments();
		flag = true;
	}

	/**
	 * Share of Business Document Cancel
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 4, groups = { "Share Of Business" }, description = "Share of Business Document Cancel")
	public void VerifyCancelSOBDocuments() {
		assertTrue(flag);
	}

	/**
	 * Check whether all the filter fields are working
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 5, groups = {
			"Share Of Business" }, description = "Check whether all the filter fields are working")
	public void VerifyFilterSOBFieldWorking() {
		shareOfBusinessFunc.verifySOBFilterFieldAreWorking();
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		commonFunc.verifyAfterMethod();
	}

}
