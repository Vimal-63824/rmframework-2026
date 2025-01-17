package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.PurchaseRequestFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseRequestFunctions PRFunc = new PurchaseRequestFunctions();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

	/**
	 * Verify Before Test
	 * 
	 * @author Sakthi-GWL
	 */
	@BeforeTest
	public void BrowserSetup() {
		commonFunc.browserOpen();
		commonFunc.enterUrl();
	}

	/**
	 * How to create the purchase request(s) for General Items?
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(description = "68 - How to create the purchase request(s) for General Items?", priority = 0, groups = {
			"PurchaseRequest" })
	public void verifyPurchaseRequestGeneralTypeCreation() {
		flag = PRFunc.verifyCreationOfPRGeneral();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?", groups = {
			"PurchaseOrder" })
	public void VerifyDirectPODomesticGeneralStockAndSupplierSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPODomesticGeneralSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?", groups = {
			"PurchaseOrder" })
	public void VerifyDirectPODomesticMaterialStockAndSupplierSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPODomesticMaterialSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * verify Tear Down
	 * 
	 * @author Deepan-GWL
	 */
	@AfterTest
	public void TearDown() {
		webDB.tearDown();
	}

	/**
	 * Verify After Suite
	 * 
	 * @author Deepan-GWL
	 */
	@AfterSuite
	public void SendMail() {
		mail.execute(this.getClass().getSimpleName());
	}
}
