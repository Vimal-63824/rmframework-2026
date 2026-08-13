package com.roadmapERP;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import utils.WebDriverBase;

public class SaleInvoiceTest2 extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesInvoiceFunctions salesInvoiceFunc = new SalesInvoiceFunctions();
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
	 * Create SI - Domestic - SEZ Customer
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 32, groups = { "SalesInvoice" }, description = "      161 - Create SI - Domestic - SEZ Customer")
	public void VerifyCreateSalesInvoiceDomesticSEZCustomer() {
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceDomesticSEZCustomer();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Sales Invoice Domestic from approved sales orders
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 33, groups = {
			"SalesInvoice" }, description = "      130 - Create Sales Invoice from approved sales orders")
	public void VerifyCreateSalesInvoiceDomesticFromApprovedSalesorders() {
		salesInvoiceFunc.verifyCreateSalesInvoiceDomesticFromApprovedSalesorders();
	}

	/**
	 * Create Sales Invoice Export from approved sales orders
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 34, groups = {
			"SalesInvoice" }, description = "      144 - Create Sales Invoice from approved sales orders")
	public void VerifyCreateSalesInvoiceExportFromApprovedSalesorders() {
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceExportFromApprovedSalesorders();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Free Sample Type of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 37, groups = {
			"SalesInvoice" }, description = "      164 -  create Free Sample type of Sales Invoice")
	public void VerifyCreateFreeSampletypeofSalesInvoice() {
		flag = salesInvoiceFunc.verifyCreateFreeSampletypeofSalesInvoice();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Free Replacement type of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 38, groups = {
			"SalesInvoice" }, description = "      165 - create Free Replacement type of Sales Invoice")
	public void VerifyCreateFreeReplacementTypeofSalesInvoice() {
		flag = salesInvoiceFunc.verifyCreateFreeReplacementTypeofSalesInvoice();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Scrap type of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 39, groups = { "SalesInvoice" }, description = "      168 -  create Scrap type of Sales Invoice")
	public void VerifyCreateScrapTypeofSalesInvoice() {
		flag = salesInvoiceFunc.verifyCreateScrapTypeofSalesInvoice();
		Assert.assertTrue(flag);
	}

	/**
	 * Unpick the Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 40, groups = { "SalesInvoice" }, description = "      177 -  Unpick the Sales Invoice")
	public void VerifyUnpickTheSalesInvoice() {
		salesInvoiceFunc.verifyUnpickTheSalesInvoice();
	}

	/**
	 * Cancel the Sales Invoice document
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 41, groups = { "SalesInvoice" }, description = "       175 - Cancel the Sales Invoice document")
	public void VerifyCancelTheSalesInvoiceDocument() {
		flag = salesInvoiceFunc.verifyCancelTheSalesInvoiceDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancel the Sales Invoice Lines
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 42, groups = { "SalesInvoice" }, description = "       178 - Cancel Invoice Lines")
	public void VerifyCancelSalesInvoiceLines() {
		flag = salesInvoiceFunc.verifyCancelSalesInvoiceLines();
		Assert.assertTrue(flag);
	}

	/**
	 * create Service type of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 43, groups = {
			"SalesInvoice" }, description = "       169 -  create Service type of Sales Invoice")
	public void VerifyCreateServiceOfSalesInvoice() {
		flag = salesInvoiceFunc.verifyCreateServiceOfSalesInvoice();
		Assert.assertTrue(flag);
	}

	/**
	 * create Service type of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 44, groups = { "SalesInvoice" }, description = "       170 -  Create SI with Shipset Items")
	public void VerifyCreateSalesInvoiceWithShipsetItem() {
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceWithShipsetItem();
		Assert.assertTrue(flag);
	}

	/**
	 * create Service type of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 45, groups = { "SalesInvoice" }, description = "       171 -  Create SI with Promotional Items")
	public void VerifyCreateSalesInvoiceWithPromotionalItems() {
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceWithPromotionalItems();
		Assert.assertTrue(flag);
	}

	/**
	 * createSalesReturnwithGoodsfromPendingSalesReturnDomestic
	 * 
	 * @author Vimal - RMITS
	 * @throws IOException
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 46, groups = {
			"SalesInvoice" }, description = "       180 -   create Sales Return with Goods from Pending Sales Return - Domestic")
	public void CreateSalesReturnwithGoodsfromPendingSalesReturnDomestic() {
		flag = salesInvoiceFunc.createSalesReturnwithGoodsfromPendingSalesReturnDomestic();
		Assert.assertTrue(flag);
	}

	/**
	 * createSalesReturnwithGoodsfromPendingSalesReturnDomestic
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 47, groups = {
			"SalesInvoice" }, description = "       181 -    create Sales Return without Goods from pending Sales return - Domestic")
	public void CreateSalesReturnwithoutGoodsfromPendingSalesReturnDomestic() {
		flag = salesInvoiceFunc.createSalesReturnwithoutGoodsfromPendingSalesReturnDomestic();
		Assert.assertTrue(flag);
	}

	/**
	 * createSalesReturnwithGoodsfromPendingSalesReturnDomestic
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 48, groups = {
			"SalesInvoice" }, description = "       184 -     create direct Sales return WO Reference - Domestic")
	public void CreateDirectSalesReturnWithOutReferenceDomestic() {
		flag = salesInvoiceFunc.createDirectSalesReturnWithOutReferenceDomestic();
		Assert.assertTrue(flag);
	}

	/**
	 * createSalesReturnwithReferencefromPendingSalesReturnDomestic
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 49, groups = {
			"SalesInvoice" }, description = "       183 -       create Sales return with Reference - Domestic")
	public void CreateSalesReturnwithReferencefromPendingSalesReturnDomestic() {
		flag = salesInvoiceFunc.createSalesReturnwithReferencefromPendingSalesReturnDomestic();
		Assert.assertTrue(flag);
	}

	/**
	 * createSalesReturnwithGoodsfromPendingSalesReturnExport
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 50, groups = {
			"SalesInvoice" }, description = "       185 -    create Sales Return with Goods from Pending Sales Return - Export")
	public void CreateSalesReturnwithGoodsfromPendingSalesReturnExport() {
		flag = salesInvoiceFunc.createSalesReturnwithGoodsfromPendingSalesReturnExport();
		Assert.assertTrue(flag);
	}

	/**
	 * createSalesReturnwithOutGoodsfromPendingSalesReturnExport
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 51, groups = {
			"SalesInvoice" }, description = "       186 -     create Sales Return without Goods from pending Sales return - Export")
	public void CreateSalesReturnwithoutGoodsfromPendingSalesReturnExport() {
		flag = salesInvoiceFunc.createSalesReturnwithoutGoodsfromPendingSalesReturnExport();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Sales return WO Reference - Export
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 52, groups = {
			"SalesInvoice" }, description = "       189 -      create direct Sales return WO Reference - Export")
	public void CreateDirectSalesReturnWithOutReferenceExport() {
		flag = salesInvoiceFunc.createDirectSalesReturnWithOutReferenceExport();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Sales return with Reference - Export
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 53, groups = {
			"SalesInvoice" }, description = "       188 -       create direct Sales return with Reference - Export")
	public void CreateSalesReturnwithReferencefromPendingSalesReturnExport() {
		flag = salesInvoiceFunc.createSalesReturnwithReferencefromPendingSalesReturnExport();
		Assert.assertTrue(flag);
	}

	/**
	 * create Sales Return through gate Entry - Domestic
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 54, groups = {
			"SalesInvoice" }, description = "       182 -        create Sales Return through gate Entry - Domestic")
	public void CreateSalesReturnthroughgateEntryDomestic() {
		flag = salesInvoiceFunc.createSalesReturnthroughgateEntryDomestic();
		Assert.assertTrue(flag);
	}

	/**
	 * create Sales Return through gate Entry - Domestic
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 55, groups = {
			"SalesInvoice" }, description = "       188 -         create direct Sales return with Reference - Export")
	public void CreateSalesReturnthroughgateEntryExport() {
		flag = salesInvoiceFunc.createSalesReturnthroughgateEntryExport();
		Assert.assertTrue(flag);
	}

	/**
	 * create Sales Return through gate Entry - Domestic
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 56, groups = { "SalesInvoice" }, description = "       179 -         Reverse Sales Invoice")
	public void ReverseTheInvoicedSalesInvoice() {
		flag = salesInvoiceFunc.reverseTheInvoicedSalesInvoice();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SI - Domestic - CESS Customer
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 57, groups = { "SalesInvoice" }, description = "      162 - Create SI - Domestic - CESS Customer")
	public void VerifyCreateSalesInvoiceDomesticCESSCustomer() {
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceDomesticCESSCustomer();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SI - Domestic - With Charges
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 58, groups = {
			"SalesInvoice" }, description = "       163 -         Create SI - Domestic - With Charges")
	public void CreateSalesInvoiceWithChargeItem() {
		flag = salesInvoiceFunc.createSalesInvoiceWithChargeItem();
		Assert.assertTrue(flag);
	}

//	/**
//	 * Create SI with PDI process
//	 * 
//	 * @author Vimal - RMITS
//	 * 
//	 */
//	@Author("Vimal-RMITS")
//	@Test(priority = 59, groups = { "SalesInvoice" }, description = "       172 -         Create SI with  PDI process")
//	public void CreateSalesInvoiceWithPreDispatchDocument() {
//		flag = salesInvoiceFunc.createSalesInvoiceWithPreDispatchDocument();
//		Assert.assertTrue(flag);
//	}

	/**
	 * Multiple Lines In Sales Invoice With DB Validation
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 60, groups = {
			"SalesInvoice" }, description = "              Multiple Lines In Sales Invoice With DB Validation")
	public void MultipleLinesInSalesInvoice() {
		flag = salesInvoiceFunc.multipleLinesInSalesInvoice();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Repair - rework order for Sales return rejection
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 61, groups = {
			"SalesInvoice" }, description = "       190 -         Create Repair - rework order for Sales return rejection")
	public void CreateRepairReworkorderforSalesreturnrejection() {
		flag = salesInvoiceFunc.createRepairReworkorderforSalesreturnrejection();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Scrap - rework order for Sales return rejection
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 62, groups = {
			"SalesInvoice" }, description = "       190 -         Create Scrap - rework order for Sales return rejection")
	public void CreateScrapReworkorderforSalesreturnrejection() {
		flag = salesInvoiceFunc.createScrapReworkorderforSalesreturnrejection();
		Assert.assertTrue(flag);
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
