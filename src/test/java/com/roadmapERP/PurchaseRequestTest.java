package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 22, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.PurchaseRequestFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class PurchaseRequestTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseRequestFunctions PRFunc = new PurchaseRequestFunctions();

	/**
	 * How to create the purchase request(s) for General Items?
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 1, groups = {
			"PurchaseRequest" }, description = "68 - How to create the purchase request(s) for General Items?")
	public void VerifyPurchaseRequestGeneralTypeCreation() {
		flag = PRFunc.verifyCreationOfPRGeneral();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create the purchase request(s) for Service Items?
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 2, groups = {
			"PurchaseRequest" }, description = "70 - How to create the purchase request(s) for service items?")
	public void VerifyPurchaseRequestServiceTypeCreation() {
		flag = PRFunc.verifyCreationOfPRService();
		Assert.assertTrue(flag);
	}

	/**
	 * How to cancel the purchase request(s) How to cancel the purchase request
	 * lines?
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 3, groups = { "PurchaseRequest" }, description = "71 - How to cancel the purchase request(s)"
			+ "72 - How to cancel the purchase request lines?")
	public void VerifyCancelPRAndDeleteLines() {
		flag = PRFunc.verifyCancelPRAndDeleteLine();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create the purchase request directly to the system through excel
	 * upload?
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 4, groups = {
			"PurchaseRequest" }, description = "67 - How to create the purchase request directly to the system through excel upload?")
	public void VerifyCreatePRByUploadExcel() {
		flag = PRFunc.verifyCreatePRByUploadExcel();
		Assert.assertTrue(flag);
	}
	
	/**
	 * How to closeshort the purchase request(s)?
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 5, groups = {
	"PurchaseRequest" }, description = "73 - How to closeshort the purchase request(s)?")
	public void VerifyPRCloseShort() {
		flag = PRFunc.verifyPRCloseShort();
		Assert.assertTrue(flag);
	}
	
	/**
	 * Verify After Method
	 * 
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			commonFunc.verifyAfterMethod();
		}
	}

}
