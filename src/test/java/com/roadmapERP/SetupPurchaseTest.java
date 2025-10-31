package com.roadmapERP;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

/**
 * @author Deepan-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.SetupPurchaseFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class SetupPurchaseTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SetupPurchaseFunctions setupPurchaseFunc = new SetupPurchaseFunctions();

	/**
	 * Buyer Creation
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = { "Buyer Setup" }, description = "Buyer Creation")
	public void VerifyCreateAndUpdateBuyer() {
		setupPurchaseFunc.verifyCreateAndUpdateBuyerName();
		flag = true;
	}

	/**
	 * Update Buyer
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = { "Buyer Setup" }, description = "Update Buyer")
	public void VerifyUpdateBuyer() {
		assertTrue(flag);
	}

	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
