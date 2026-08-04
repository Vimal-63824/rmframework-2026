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
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class SaleOrderTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesOrderFunctions SalesOrdFunc = new SalesOrderFunctions();
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
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is user
	 *         defined- Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 1, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is user defined- Similar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is user
	 *         defined- Dissimilar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 2, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is user defined- Dissimilar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralDisSimilarStockAndCustomerUOM() {
		// TODO Covered in VerifyDomesticSOGeneralSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyDomesticSOGeneralSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is user defined-
	 *         Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 3, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is user defined- Similar Stock and Customer UOM")
	public void VerifyExportSOGeneralSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is user defined-
	 *         Dissimilar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 4, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is user defined- Dissimilar Stock and Customer UOM")
	public void VerifyExportSOGeneralDisSimilarStockAndCustomerUOM() {
		// TODO Covered in VerifyExportSOGeneralSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method VerifyExportSOGeneralSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Rate
	 *         contract- Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 5, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Rate contract- Similar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralRateContractSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralRateContractSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Rate
	 *         contract- Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 6, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Rate contract- Similar Stock and Customer UOM")
	public void VerifyExportSOGeneralRateContractSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralRateContractSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Rate
	 *         contract- Dissimilar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 7, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Rate contract- Dissimilar Stock and Customer UOM")
	public void verifyDomesticSOGeneralRateContractDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// verifyDomesticSOGeneralRateContractSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method verifyDomesticSOGeneralRateContractSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Rate
	 *         contract- Dissimilar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 8, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Rate contract- Dissimilar Stock and Customer UOM")
	public void verifyExportSOGeneralRateContractDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// verifyExportSOGeneralRateContractSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method verifyExportSOGeneralRateContractSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Standard
	 *         Price- Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 9, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Standard Price- Similar Stock and Customer UOM")
	public void verifyDomesticSOGeneralStandardPriceSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralStandardPriceSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Standard
	 *         Price- Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 10, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Standard Price- Similar Stock and Customer UOM")
	public void verifyExportSOGeneralStandardPriceSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralStandardPriceSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Standard
	 *         Price- DisSimilar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 11, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Standard Price- DisSimilar Stock and Customer UOM")
	public void verifyDomesticSOGeneralStandardPriceDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// verifyDomesticSOGeneralStandardPriceSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method verifyDomesticSOGeneralStandardPriceSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Standard
	 *         Price- DisSimilar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 12, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Standard Price- DisSimilar Stock and Customer UOM")
	public void verifyExportSOGeneralStandardPriceDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// verifyExportSOGeneralStandardPriceSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method verifyExportSOGeneralStandardPriceSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Catalog
	 *         Price- Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 13, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Catalog Price- Similar Stock and Customer UOM")
	public void verifyDomesticSOGeneralCatalogPriceSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralCatalogPriceSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Catalog
	 *         Price- DisSimilar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 14, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Catalog Price- DisSimilar Stock and Customer UOM")
	public void verifyDomesticSOGeneralCatalogPriceDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// verifyDomesticSOGeneralCatalogPriceSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method verifyDomesticSOGeneralCatalogPriceSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Catalog
	 *         Price- Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 15, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Catalog Price- Similar Stock and Customer UOM")
	public void verifyExportSOGeneralCatalogPriceSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralCatalogPriceSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Catalog
	 *         Price- DisSimilar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 16, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Catalog Price- DisSimilar Stock and Customer UOM")
	public void verifyExportSOGeneralCatalogPriceDisSimilarStockAndCustomerUOM() {
		// TODO Covered in
		// verifyExportSOGeneralCatalogPriceSimilarStockAndCustomerUOM() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method verifyExportSOGeneralCatalogPriceSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Cancel Sales Order Header Document
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 17, groups = { "SalesOrder" }, description = "Cancel Sales Order Header Document")
	public void verifyCancelSalesOrder() {
		flag = SalesOrdFunc.verifyCancelSalesOrder();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is PriceList-
	 *         Similar Stock and Customer UOM
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 18, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is PriceList- Similar Stock and Customer UOM")
	public void verifyDomesticSOGeneralPriceListSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralPriceListSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Export Sales Order - price basis is PriceList- Similar Stock and Customer UOM
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 19, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is PriceList- Similar Stock and Customer UOM")
	public void verifyExportSOGeneralPriceListSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralPriceListSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 20, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is PriceList- DisSimilar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralPriceListDisSimilarStockAndCustomerUOM() {
		// TODO Covered in verifyDomesticSOGeneralPriceListSimilarStockAndCustomerUOM()
		// Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method verifyDomesticSOGeneralPriceListSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 21, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is PriceList- DisSimilar Stock and Customer UOM")
	public void VerifyExportSOGeneralPriceListDisSimilarStockAndCustomerUOM() {
		// TODO Covered in verifyExportSOGeneralPriceListSimilarStockAndCustomerUOM()
		// Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method verifyExportSOGeneralPriceListSimilarStockAndCustomerUOM() ");
		Assert.assertTrue(flag);
	}

	/**
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 22, groups = { "SalesOrder" }, description = "Create SO with Advance Process(PaymentTerm Change)")
	public void verifyAdvanceSalesOrderWithAdvancePaymentTerm() throws IOException {
		flag = SalesOrdFunc.verifyAdvanceSalesOrderWithAdvancePaymentTerm();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancel SO Lines
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 23, groups = { "SalesOrder" }, description = "Cancel SO Lines")
	public void verifySalesOrderLineWiseCancel() {
		SalesOrdFunc.verifyCancelSalesOrderLines();
	}

	/**
	 * Create SO - Domestic - Interstate Customer
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 24, groups = { "SalesOrder" }, description = "Create SO - Domestic - Interstate Customer")
	public void SoGeneral() throws IOException {
		flag = SalesOrdFunc.SoGeneral();
		Assert.assertTrue(flag);
	}

	/**
	 * create Free Sample type of Sales Order
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 25, groups = { "SalesOrder" }, description = " create Free Sample type of Sales Order")
	public void SoFreeSample() throws IOException {
		flag = SalesOrdFunc.SoFreeSample();
		Assert.assertTrue(flag);
	}

	/**
	 * create Free Replacement type of Sales Order
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 26, groups = { "SalesOrder" }, description = " create Free Replacement type of Sales Order")
	public void SoFreeReplacement() throws IOException {
		flag = SalesOrdFunc.SoFreeReplacement();
		Assert.assertTrue(flag);
	}

	/**
	 * create Scrap type of Sales Order
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 27, groups = { "SalesOrder" }, description = " create Scrap type of Sales Order")
	public void SoScrap() throws IOException {
		flag = SalesOrdFunc.SoScrap();
		Assert.assertTrue(flag);
	}

	/**
	 * create Service type of Sales Order
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 28, groups = { "SalesOrder" }, description = " create Service type of Sales Order")
	public void SoService() throws IOException {
		flag = SalesOrdFunc.SoService();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO with Promotional Items
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 29, groups = { "SalesOrder" }, description = "Create SO with Promotional Items")
	public void SoPromotional() throws IOException {
		flag = SalesOrdFunc.SoPromotional();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - Local Customer
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 30, groups = { "SalesOrder" }, description = "Create SO - Domestic - Local Customer")
	public void SoGeneralLocalCustomer() throws IOException {
		flag = SalesOrdFunc.SoGeneralLocalCustomer();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - SEZ Customer
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 31, groups = { "SalesOrder" }, description = "Create SO - Domestic - SEZ  Customer")
	public void SoGeneralSEZCustomer() throws IOException {
		flag = SalesOrdFunc.SoGeneralSEZCustomer();
		Assert.assertTrue(flag);
	}

	/**
	 * create Demo Exhibition type of Sales Order
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 32, groups = { "SalesOrder" }, description = " create Demo Exhibition type of Sales Order")
	public void SoDemoExhibition() throws IOException {
		flag = SalesOrdFunc.SoDemoExhibition();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - CESS Customer
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 33, groups = { "SalesOrder" }, description = "Create SO - Domestic - CESS  Customer")
	public void SoCESS() throws IOException {
		flag = SalesOrdFunc.SoCESS();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - With Charges
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 34, groups = { "SalesOrder" }, description = "Create SO - Domestic - With Charges")
	public void SoCharges() throws IOException {
		flag = SalesOrdFunc.SoCharges();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO with Multiple Items - Same Price class
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 35, groups = { "SalesOrder" }, description = "Create SO with Multiple Items - Same Price class")
	public void SoMultipleWithSamePriceClass() throws IOException {
		// TODO Covered in SoGeneral()
		// Method
		logs.logging("INFO", "This Scenario Is Already Covered In This Method SoGeneral() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO with Multiple Items - Different Price class
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 36, groups = {
			"SalesOrder" }, description = "Create SO with Multiple Items - Different Price class")
	public void SOMultipleWithDifferentPriceClass() {
		flag = SalesOrdFunc.SOMultipleWithDifferentPriceClass();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO with Shipset Items
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 37, groups = { "SalesOrder" }, description = "Create SO with Shipset Items")
	public void SOShipSet() {
		flag = SalesOrdFunc.SOShipSet();
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO -Qty Change - Domestic Customer - Interstate
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 38, groups = {
			"SalesOrderAmendment" }, description = "Amend SO -Qty Change - Domestic Customer - Interstate")
	public void SalesOrderAmendmentQtyChangeInterstateCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentQtyChange(commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), webDB.getDataFromProperties("SOAmendQtyChange"),
				webDB.getDataFromProperties("SalesOrder"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO - Price Change - Domestic Customer - Interstate
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 39, groups = {
			"SalesOrderAmendment" }, description = "Amend SO - Price Change - Domestic Customer - Interstate")
	public void SalesOrderAmendmentPriceChangeInterstateCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentPriceChange(commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), webDB.getDataFromProperties("SOAmendPriceChange"),
				webDB.getDataFromProperties("SalesOrder"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO -Disc Change - Domestic Customer - Interstate
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 40, groups = {
			"SalesOrderAmendment" }, description = "Amend SO -Disc Change - Domestic Customer - Interstate")
	public void SalesOrderAmendmentDiscountChangeInterstateCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentDiscountChange(commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"),
				webDB.getDataFromProperties("SOAmendDiscountChange"), webDB.getDataFromProperties("SalesOrder"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO - Addition of Item -Domestic Customer - Interstate
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 41, groups = {
			"SalesOrderAmendment" }, description = "Amend SO - Addition of Item -Domestic Customer - Interstate")
	public void SalesOrderAmendmentAdditionOfLinesInterstateCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentAdditionOfLines(commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"),
				webDB.getDataFromProperties("SOAmendmentAdditionOfItem"), webDB.getDataFromProperties("SalesOrder"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO -Qty Change - Domestic Customer - Local
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 42, groups = {
			"SalesOrderAmendment" }, description = "Amend SO -Qty Change - Domestic Customer - Local")
	public void SalesOrderAmendmentQtyChangeLocalCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentQtyChange(commonFunc.getDataFromTestDataSheet("SO_Local_Customer"),
				commonFunc.getDataFromTestDataSheet("SO_Local_Customer_Desc"),
				webDB.getDataFromProperties("LocalQtyChange"), webDB.getDataFromProperties("SOLocal"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO - Price Change - Domestic Customer - Local
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 43, groups = {
			"SalesOrderAmendment" }, description = "Amend SO - Price Change - Domestic Customer - Local")
	public void SalesOrderAmendmentPriceChangeLocalCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentPriceChange(commonFunc.getDataFromTestDataSheet("SO_Local_Customer"),
				commonFunc.getDataFromTestDataSheet("SO_Local_Customer_Desc"),
				webDB.getDataFromProperties("LocalPriceChange"), webDB.getDataFromProperties("SOLocal"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO -Disc Change - Domestic Customer - LocalF
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 44, groups = {
			"SalesOrderAmendment" }, description = "Amend SO -Disc Change - Domestic Customer - Local")
	public void SalesOrderAmendmentDiscountChangeLocalCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentDiscountChange(commonFunc.getDataFromTestDataSheet("SO_Local_Customer"),
				commonFunc.getDataFromTestDataSheet("SO_Local_Customer_Desc"),
				webDB.getDataFromProperties("LocalDiscChange"), webDB.getDataFromProperties("SOLocal"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO - Addition of Item -Domestic Customer - Local
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 45, groups = {
			"SalesOrderAmendment" }, description = "Amend SO - Addition of Item -Domestic Customer - Local")
	public void SalesOrderAmendmentAdditionOfLinesLocalCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentAdditionOfLines(commonFunc.getDataFromTestDataSheet("SO_Local_Customer"),
				commonFunc.getDataFromTestDataSheet("SO_Local_Customer_Desc"),
				webDB.getDataFromProperties("LocalAddOfItem"), webDB.getDataFromProperties("SOLocal"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		Assert.assertTrue(flag);
	}

	/**
	 * Closeshort Single sales Order
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 46, groups = { "SalesOrderAmendment" }, description = "Closeshort Single sales Order")
	public void SalesOrderAmendmentSingleCloseshort() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentSingleCloseshort();
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO with Charges
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 47, groups = { "SalesOrderAmendment" }, description = "Amend SO with Charges")
	public void SalesOrderAmendmentWithCharges() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentWithCharges();
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO with diff. price class
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 48, groups = { "SalesOrderAmendment" }, description = "Amend SO with diff. price class")
	public void SalesOrderAmendmentWithDifferentPriceClass() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentWithDifferentPriceClass();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - UTGST Customer
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 49, groups = { "SalesOrder" }, description = "Create SO - Domestic - UTGST Customer")
	public void SOUTGST() {
		flag = SalesOrdFunc.SOUTGST();
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO -Qty Change - Domestic Customer - UTGST
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 50, groups = {
			"SalesOrderAmendment" }, description = "Amend SO -Qty Change - Domestic Customer - UTGST")
	public void SalesOrderAmendmentQtyChangeUTGSTCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentQtyChangeUTGST(commonFunc.getDataFromTestDataSheet("UTGSTCustomer"),
				commonFunc.getDataFromTestDataSheet("UTGST_cus"), webDB.getDataFromProperties("UTGSTQtyChange"),
				webDB.getDataFromProperties("SOUTGST"), commonFunc.getDataFromTestDataSheet("UTGSTLocation1"),
				commonFunc.getDataFromTestDataSheet("UTGSTSOAPrefix"),
				commonFunc.getDataFromTestDataSheet("UTGSTUnit1"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO - Price Change - Domestic Customer - UTGST
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 51, groups = {
			"SalesOrderAmendment" }, description = "Amend SO - Price Change - Domestic Customer - UTGST")
	public void SalesOrderAmendmentPriceChangeUTGSTCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentPriceChangeUTGST(commonFunc.getDataFromTestDataSheet("UTGSTCustomer"),
				commonFunc.getDataFromTestDataSheet("UTGST_cus"), webDB.getDataFromProperties("UTGSTPriceChange"),
				webDB.getDataFromProperties("SOUTGST"), commonFunc.getDataFromTestDataSheet("UTGSTLocation1"),
				commonFunc.getDataFromTestDataSheet("UTGSTSOAPrefix"),
				commonFunc.getDataFromTestDataSheet("UTGSTUnit1"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO -Disc Change - Domestic Customer - UTGST
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 52, groups = {
			"SalesOrderAmendment" }, description = "Amend SO -Disc Change - Domestic Customer - UTGST")
	public void SalesOrderAmendmentDiscountChangeUTGSTCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentDiscountChangeUTGST(commonFunc.getDataFromTestDataSheet("UTGSTCustomer"),
				commonFunc.getDataFromTestDataSheet("UTGST_cus"), webDB.getDataFromProperties("UTGSTDiscChange"),
				webDB.getDataFromProperties("SOUTGST"), commonFunc.getDataFromTestDataSheet("UTGSTLocation1"),
				commonFunc.getDataFromTestDataSheet("UTGSTSOAPrefix"),
				commonFunc.getDataFromTestDataSheet("UTGSTUnit1"));
		Assert.assertTrue(flag);
	}

	/**
	 * Amend SO - Addition of Item -Domestic Customer - UTGST
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 53, groups = {
			"SalesOrderAmendment" }, description = "Amend SO - Addition of Item -Domestic Customer - UTGST")
	public void SalesOrderAmendmentAdditionOfLinesUTGSTCustomer() throws IOException {
		flag = SalesOrdFunc.SalesOrderAmendmentAdditionOfLinesUTGST(
				commonFunc.getDataFromTestDataSheet("UTGSTCustomer"), commonFunc.getDataFromTestDataSheet("UTGST_cus"),
				webDB.getDataFromProperties("UTGSTAddOfItem"), webDB.getDataFromProperties("SOUTGST"),
				commonFunc.getDataFromTestDataSheet("UTGSTLocation1"),
				commonFunc.getDataFromTestDataSheet("UTGSTSOAPrefix"),
				commonFunc.getDataFromTestDataSheet("UTGSTUnit1"));
		Assert.assertTrue(flag);
	}

	/**
	 * Closeshort Multiple sales Orders
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 54, groups = { "SalesOrderAmendment" }, description = "Closeshort Multiple sales Orders")
	public void closeShortMultipleSalesOrder() throws IOException {
		flag = SalesOrdFunc.closeshortMultipleSalesOrder(commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), webDB.getDataFromProperties("SalesOrder"));
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
