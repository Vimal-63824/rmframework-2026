package com.roadmapERP.e2e;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.coreprocess.CoreProcessPageFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class CoreProcessE2ETest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static CoreProcessPageFunctions coreProcessPageFunctions = new CoreProcessPageFunctions();

	/**
	 * Purchase Request to Payment
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 1, groups = { "Core Process" }, description = "Purchase Request to Payment")
	public void VerifyPurchaseRequestToPayment() {
		coreProcessPageFunctions.verifyPurchaseRequestToPayment();
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
