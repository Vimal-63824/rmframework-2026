package com.roadmapERP.e2e;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
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
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
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
	 *
	 * Production Order to Completion to MRV ETE
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 4, groups = {
			"Core Process" }, description = "Production Order to Completion to MRV End to End Process")
	public void VerifyProductionOrderToCompletionToMaterialReceiptVoucher() {
		flag = coreProcessPageFunctions.verifyProductionOrderToCompletionToMaterialReceiptVoucher();
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Material Request To MRV Intra Type
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 5, groups = {
			"Core Process" }, description = "Material Request to Material Receipt voucher (Intra Transfer) End to End Process")
	public void VerifyMaterialRequestToMRVIntraTransferType() {
		coreProcessPageFunctions.verifyMaterialRequestToMaterialReceiptVoucherIntraday();
	}

	/**
	 * This Method Is Used To Verify Material Request To MRV Inter Transfer Type
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 6, groups = {
			"Core Process" }, description = "Material Request to Material Receipt voucher (Inter Transfer) End to End Process")
	public void VerifyMaterialRequestToMRVInterTransferType() {
		coreProcessPageFunctions.verifyMaterialRequestToMaterialReceiptVoucherInterday();
	}

	/**
	 * This Method Is Used To Verify Material Request To MRV Department Type
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 7, groups = {
			"Core Process" }, description = "Material Request to Material Issuance voucher (Department)")
	public void VerifyMaterialRequestToMRVDepartmentType() {
		coreProcessPageFunctions.verifyMaterialRequestToMaterialReceiptVoucherDepartmentType();
	}

	/**
	 * This Method Is Used To Verify Material Request To MRV WIP Type
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 8, groups = {
			"Core Process" }, description = "Material Request to Material Receipt voucher (production-WIP)")
	public void VerifyMaterialRequestToMRVWipType() {
		coreProcessPageFunctions.verifyMaterialRequestToMaterialReceiptVoucherWipType();
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
