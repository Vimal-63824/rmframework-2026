package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.CustomerScheduleFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class CustomerScheduleTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesOrderFunctions SalesOrdFunc = new SalesOrderFunctions();
	static SalesInvoiceFunctions SalesInvFunc = new SalesInvoiceFunctions();
	static CustomerScheduleFunctions customerScheduleFunctions = new CustomerScheduleFunctions();
	private static ReportLoger logs = new ReportLoger();
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
	 * Domestic Customer Schedule - price basis is user defined- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 1, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is user defined- Similar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("SO_Similar_Item"), webDB.getDataFromProperties("CS"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is user defined- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 2, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is user defined- Similar Stock and Customer UOM")
	public void VerifyExportCSGeneralSimilarStockAndCustomerUOM() {
		// TODO Covered in VerifyDomesticCSGeneralSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is user defined- DisSimilar Stock
	 * and Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 3, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is user defined- DisSimilar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralDisSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("SO_DisSimilarItem"), webDB.getDataFromProperties("CSDisSimilar"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is user defined- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 4, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is user defined- DisSimilar Stock and Customer UOM")
	public void VerifyExportCSGeneralDisSimilarStockAndCustomerUOM() {
		// TODO Covered in VerifyDomesticCSGeneralDisSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralDisSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is rate Contract- Similar Stock and
	 * Customer UOM
	 * 
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 5, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is Rate Contract - Similar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralRateContractSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralRateContractSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("CSSimilarItem"),
				webDB.getDataFromProperties("CSRateContractSimilar"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is rate Contract- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 6, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is rate Contract - Similar Stock and Customer UOM")
	public void VerifyExportCSGeneralRateContractSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// VerifyDomesticCSGeneralRateContractSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralRateContractSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is rate Contract- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 7, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is Rate Contract - DisSimilar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralRateContractDisSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralRateContractSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("CSDisSimilarItem"),
				webDB.getDataFromProperties("CSRateContractDisSimilar"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is rate Contract- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 8, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is rate Contract - DisSimilar Stock and Customer UOM")
	public void VerifyExportCSGeneralRateContractDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// VerifyDomesticCSGeneralRateContractDisSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralRateContractDisSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is PriceList- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 9, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is PriceList - Similar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralPriceListSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralPriceListSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("CSSimilarItem"), webDB.getDataFromProperties("CSPriceListSimilar"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is PriceList- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 10, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is PriceList - Similar Stock and Customer UOM")
	public void VerifyExportCSGeneralPriceListSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// VerifyDomesticCSGeneralPriceListSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralPriceListSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is PriceList- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 11, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is PriceList - DisSimilar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralPriceListDisSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralPriceListSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("CSDisSimilarItem"),
				webDB.getDataFromProperties("CSPriceListDisSimilar"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is PriceList- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 12, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is PriceList - DisSimilar Stock and Customer UOM")
	public void VerifyExportCSGeneralPriceListDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// VerifyDomesticCSGeneralPriceListDisSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralPriceListDisSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is Standard Price- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 13, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is Standard Price - Similar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralStandardPriceSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralStandardPriceSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("CSSimilarItem"),
				webDB.getDataFromProperties("CSStandardPriceSimilar"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is Standard Price- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 14, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is Standard Price - Similar Stock and Customer UOM")
	public void VerifyExportCSGeneralStandardPriceSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// VerifyDomesticCSGeneralStandardPriceSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralStandardPriceSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is Standard Price- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 15, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is Standard Price - DisSimilar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralStandardPricetDisSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralStandardPriceSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("CSDisSimilarItem"),
				webDB.getDataFromProperties("CSStandardPriceDisSimilar"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is Standard Price- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 16, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is Standard Price - DisSimilar Stock and Customer UOM")
	public void VerifyExportCSGeneralStandardPriceDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// VerifyDomesticCSGeneralStandardPricetDisSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralStandardPricetDisSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is Catalog Price - Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 17, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is Catalog Price - Similar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralCatalogPriceSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralCatalogPriceSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("CSSimilarItem"),
				webDB.getDataFromProperties("CSCatalogPriceSimilar"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is Standard Price- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 18, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is Catalog Price - Similar Stock and Customer UOM")
	public void VerifyExportCSGeneralCatalogPriceSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// VerifyDomesticCSGeneralCatalogPriceSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralCatalogPriceSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Domestic Customer Schedule - price basis is Catalog Price- Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 19, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is Catalog Price - DisSimilar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralCatalogPriceDisSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralCatalogPriceSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("CSDisSimilarItem"),
				webDB.getDataFromProperties("CSCatalogPriceDisSimilar"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Export Customer Schedule - price basis is Catalog Price - Similar Stock and
	 * Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 20, groups = {
			"CustomerSchedule" }, description = "Export Customer Schedule - price basis is Catalog Price - DisSimilar Stock and Customer UOM")
	public void VerifyExportCSGeneralCatalogPriceDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// VerifyDomesticCSGeneralCatalogPriceDisSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticCSGeneralCatalogPriceDisSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Cancel Customer Schedule
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 21, groups = { "CustomerSchedule" }, description = "Cancel Customer Schedule")
	public void CancelCustomerSchedule() {
		flag = customerScheduleFunctions.cancelCustomerScheduleDocument(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * Cancel Customer Schedule Amendment
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 22, groups = { "CustomerSchedule" }, description = "Cancel Customer Schedule Amendment")
	public void CancelCustomerScheduleAmendment() {
		flag = customerScheduleFunctions.cancelCustomerScheduleAmendmentDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Closeshort Customer Schedule
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 23, groups = { "CustomerSchedule" }, description = "Closeshort Customer Schedule")
	public void CloseshortCustomerSchedule() {
		flag = customerScheduleFunctions.CustomerScheduleAmendmentCloseShort();
		Assert.assertTrue(flag);
	}

	/**
	 * CS Amend - Qty Change
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 24, groups = { "CustomerSchedule" }, description = "CS Amend  - Qty Change")
	public void CustomerScheduleAmendmentQtyChange() {
		flag = customerScheduleFunctions.CustomerScheduleAmendmentQtyChange();
		Assert.assertTrue(flag);
	}

	/**
	 * CS Amend - Price Change
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 25, groups = { "CustomerSchedule" }, description = "CS Amend  - Price Change")
	public void CustomerScheduleAmendmentPriceChange() {
		flag = customerScheduleFunctions.CustomerScheduleAmendmentPriceChange();
		Assert.assertTrue(flag);
	}

	/**
	 * CS Amend - Schedule Change
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 26, groups = { "CustomerSchedule" }, description = "CS Amend  - Schedule Change")
	public void CustomerScheduleAmendmentScheduleChange() {
		flag = customerScheduleFunctions.CustomerScheduleAmendmentScheduleChange();
		Assert.assertTrue(flag);
	}

	/**
	 * CS - Upload Template
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 27, groups = { "CustomerSchedule" }, description = "CS - Upload Template")
	public void customerScheduleTemplateUpload() {
		flag = customerScheduleFunctions.customerScheduleTemplateUpload(
				commonFunc.getDataFromTestDataSheet("SO_Similar_Item"), webDB.getDataFromProperties("CSUploadTemplate"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
		Assert.assertTrue(flag);
	}

	/**
	 * This Is Used to set the Variable Handler To Zero After Finishing One Test
	 * Scenarios
	 * 
	 * @author Harikrishna -RMITS
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		customerScheduleFunctions.verifyAfterMethod();
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
