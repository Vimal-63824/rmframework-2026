package com.roadmapERP.e2e;


import org.testng.Assert;
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
	@Test(priority = 1, groups = { "Core Process" }, description = "Purchase Request to Payment End to End Process")
	public void VerifyPurchaseRequestToPayment() {
		coreProcessPageFunctions.verifyPurchaseRequestToPayment();
	}

	/**
	 * Open Sale Order to Payment Receipts ETE
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 2, groups = {
			"Core Process" }, description = "Open Sales Order to Payment Receipt End to End Process")
	public void VerifySalesOrderToPaymentReceipts() {
		flag = coreProcessPageFunctions.verifySalesOrderToPaymentReceipts();
		Assert.assertTrue(flag);
	}

	/**
	 * Sale Order to MRP ETE
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 3, groups = { "Core Process" }, description = "Sales Order to MRP End to End Process")
	public void VerifySaleOrderToMRP() {
		flag = coreProcessPageFunctions.verifySaleOrderToMRP();
		Assert.assertTrue(flag);
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
