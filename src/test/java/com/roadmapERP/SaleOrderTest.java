package com.roadmapERP;

import java.io.IOException;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import utils.Mailer;
import utils.WebDriverBase;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;

public class SaleOrderTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesOrderFunctions SalesOrdFunc = new SalesOrderFunctions();

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is user
	 *         defined- Similar Stock and Customer UOM
	 */
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
	@Test(priority = 2, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is user defined- Dissimilar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is user defined-
	 *         Similar Stock and Customer UOM
	 */
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
	@Test(priority = 4, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is user defined- Dissimilar Stock and Customer UOM")
	public void VerifyExportSOGeneralDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Rate
	 *         contract- Similar Stock and Customer UOM
	 */
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
	@Test(priority = 7, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Rate contract- Dissimilar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralRateContractDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralRateContractDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Rate
	 *         contract- Dissimilar Stock and Customer UOM
	 */
	@Test(priority = 8, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Rate contract- Dissimilar Stock and Customer UOM")
	public void VerifyExportSOGeneralRateContractDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralRateContractDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Standard
	 *         Price- Similar Stock and Customer UOM
	 */
	@Test(priority = 9, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Standard Price- Similar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralStandardPriceSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralStandardPriceSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Standard
	 *         Price- Similar Stock and Customer UOM
	 */
	@Test(priority = 10, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Standard Price- Similar Stock and Customer UOM")
	public void VerifyExportSOGeneralStandardPriceSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralStandardPriceSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Standard
	 *         Price- DisSimilar Stock and Customer UOM
	 */
	@Test(priority = 11, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Standard Price- DisSimilar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralStandardPriceDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralStandardPriceDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Standard
	 *         Price- DisSimilar Stock and Customer UOM
	 */
	@Test(priority = 12, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Standard Price- DisSimilar Stock and Customer UOM")
	public void VerifyExportSOGeneralStandardPriceDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralStandardPriceDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Catalog
	 *         Price- Similar Stock and Customer UOM
	 */
	@Test(priority = 13, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Catalog Price- Similar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralCatalogPriceSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralCatalogPriceSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is Catalog
	 *         Price- DisSimilar Stock and Customer UOM
	 */
	@Test(priority = 14, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is Catalog Price- DisSimilar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralCatalogPriceDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralCatalogPriceDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Catalog
	 *         Price- Similar Stock and Customer UOM
	 */
	@Test(priority = 15, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Catalog Price- Similar Stock and Customer UOM")
	public void VerifyExportSOGeneralCatalogPriceSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralCatalogPriceSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is Catalog
	 *         Price- DisSimilar Stock and Customer UOM
	 */
	@Test(priority = 16, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is Catalog Price- DisSimilar Stock and Customer UOM")
	public void VerifyExportSOGeneralCatalogPriceDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralCatalogPriceDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Cancel Sales Order Header Document
	 */
	@Test(priority = 17, groups = { "SalesOrder" }, description = "Cancel Sales Order Header Document")
	public void VerifyCancelSalesOrder() {
		flag = SalesOrdFunc.verifyCancelSalesOrder();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is PriceList-
	 *         Similar Stock and Customer UOM
	 */
	@Test(priority = 18, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is PriceList- Similar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralPriceListSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralPriceListSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is PriceList-
	 *         Similar Stock and Customer UOM
	 */
	@Test(priority = 19, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is PriceList- Similar Stock and Customer UOM")
	public void VerifyExportSOGeneralPriceListSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralPriceListSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is PriceList-
	 *         DisSimilar Stock and Customer UOM
	 */
	@Test(priority = 20, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is PriceList- DisSimilar Stock and Customer UOM")
	public void VerifyDomesticSOGeneralPriceListDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralPriceListDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is PriceList-
	 *         DisSimilar Stock and Customer UOM
	 */
	@Test(priority = 21, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is PriceList- DisSimilar Stock and Customer UOM")
	public void VerifyExportSOGeneralPriceListDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralPriceListDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO with Advance Process(PaymentTerm Change)
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 22, groups = { "SalesOrder" }, description = "Create SO with Advance Process(PaymentTerm Change)")
	public void verifyAdvanceSalesOrderWithAdvancePaymentTerm() throws IOException {
		flag = SalesOrdFunc.verifyAdvanceSalesOrderWithAdvancePaymentTerm();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancel SO Lines
	 * 
	 * @author Harikrishna -RMITS
	 * 
	 */
	@Test(priority = 23, groups = { "SalesOrder" }, description = "Cancel SO Lines")
	public void verifySalesOrderLineWiseCancel() {
		flag = SalesOrdFunc.verifyCancelSalesOrderLines();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - Interstate Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 24, groups = { "SalesOrder" }, description = "Create SO - Domestic - Interstate Customer")
	public void SoGeneral() throws IOException {
		flag = SalesOrdFunc.SoGeneral();
		Assert.assertTrue(flag);
	}

	/**
	 * create Free Sample type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 25, groups = { "SalesOrder" }, description = " create Free Sample type of Sales Order")
	public void SoFreeSample() throws IOException {
		flag = SalesOrdFunc.SoFreeSample();
		Assert.assertTrue(flag);
	}

	/**
	 * create Free Replacement type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 26, groups = { "SalesOrder" }, description = " create Free Replacement type of Sales Order")
	public void SoFreeReplacement() throws IOException {
		flag = SalesOrdFunc.SoFreeReplacement();
		Assert.assertTrue(flag);
	}

	/**
	 * create Scrap type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 27, groups = { "SalesOrder" }, description = " create Scrap type of Sales Order")
	public void SoScrap() throws IOException {
		flag = SalesOrdFunc.SoScrap();
		Assert.assertTrue(flag);
	}

	/**
	 * create Service type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 28, groups = { "SalesOrder" }, description = " create Service type of Sales Order")
	public void SoService() throws IOException {
		flag = SalesOrdFunc.SoService();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO with Promotional Items
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 29, groups = { "SalesOrder" }, description = "Create SO with Promotional Items")
	public void SoPromotional() throws IOException {
		flag = SalesOrdFunc.SoPromotional();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - Local Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 30, groups = { "SalesOrder" }, description = "Create SO - Domestic - Local Customer")
	public void SoGeneralLocalCustomer() throws IOException {
		flag = SalesOrdFunc.SoGeneralLocalCustomer();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - SEZ Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 31, groups = { "SalesOrder" }, description = "Create SO - Domestic - SEZ  Customer")
	public void SoGeneralSEZCustomer() throws IOException {
		flag = SalesOrdFunc.SoGeneralSEZCustomer();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Demo Exhibition type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 32, groups = { "SalesOrder" }, description = " Create Demo Exhibition type of Sales Order")
	public void SoDemoExhibition() throws IOException {
		flag = SalesOrdFunc.SoDemoExhibition();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - CESS Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 33, groups = { "SalesOrder" }, description = "Create SO - Domestic - CESS  Customer")
	public void SoCESS() throws IOException {
		flag = SalesOrdFunc.SoCESS();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SO - Domestic - With Charges
	 * 
	 * @author Harikrishna -RMITS
	 * @throws IOException
	 */
	@Test(priority = 34, groups = { "SalesOrder" }, description = "Create SO - Domestic - With Charges")
	public void SoCharges() throws IOException {
		flag = SalesOrdFunc.SoCharges();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS Is Used to set the Variable Handler To Zero After
	 *         Finishing One Test Scenarios
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		SalesOrdFunc.verifyAfterMethod();
	}
}
