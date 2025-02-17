package com.roadmapERP;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import utils.WebDriverBase;

public class SaleInvoiceTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesInvoiceFunctions salesInvoiceFunc = new SalesInvoiceFunctions();

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 1, groups = {
			"SalesInvoice" }, description = "134 - create direct Domestic Sales Invoice for the customer - price basis user defined- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoice() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoice();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 2, groups = {
			"SalesInvoice" }, description = "148 - create direct Export Sales Invoice for the customer - price basis user defined- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceExport() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceExport();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Rate
	 * Contract- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 3, groups = {
			"SalesInvoice" }, description = "136 - create direct Domestic Sales Invoice for the customer - price basis Rate Contract- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceRateContractDomestic() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceRateContractDomestic();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Rate
	 * Contract- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 4, groups = {
			"SalesInvoice" }, description = "150 - create direct Export Sales Invoice for the customer - price basis Rate Contract- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceRateContractExport() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceRateContractExport();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis user
	 * defined- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 5, groups = {
			"SalesInvoice" }, description = "135 - create direct Domestic Sales Invoice for the customer - price basis user defined- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceUserDefinedDisimilarStockUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceDomesticUserDefinedDisimilarStockUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis user
	 * defined- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 6, groups = {
			"SalesInvoice" }, description = "149 - create direct Export Sales Invoice for the customer - price basis user defined- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceExportUserDefinedDisimilarStockUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceExportUserDefinedDisimilarStockUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Rate
	 * Contract- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 7, groups = {
			"SalesInvoice" }, description = "137 - create direct Domestic Sales Invoice for the customer - price basis Rate Contract- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceRateContractDomesticWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceRateContractDomesticWithDisimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Rate
	 * Contract- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 8, groups = {
			"SalesInvoice" }, description = "151 - create direct Export Sales Invoice for the customer - price basis Rate Contract- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceRateContractExportWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceRateContractExportWithDisimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Price
	 * List- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 9, groups = {
			"SalesInvoice" }, description = "138 - create direct Domestic Sales Invoice for the customer - price basis Price List- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoicePriceListDomesticWithsimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoicePriceListDomesticWithsimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Price List-
	 * Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 10, groups = {
			"SalesInvoice" }, description = "152 - create direct Export Sales Invoice for the customer - price basis Price List- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoicePriceListExportWithsimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoicePriceListExportWithsimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Price
	 * List- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 11, groups = {
			"SalesInvoice" }, description = "139 - create direct Domestic Sales Invoice for the customer - price basis Price List- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoicePriceListDomesticWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoicePriceListDomesticWithDisimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Price List-
	 * Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 12, groups = {
			"SalesInvoice" }, description = "153 - create direct Export Sales Invoice for the customer - price basis Price List- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoicePriceListExportWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoicePriceListExportWithDisimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Standard
	 * Price- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */

	@Test(priority = 13, groups = {
			"SalesInvoice" }, description = "140 - create direct Domestic Sales Invoice for the customer - price basis Standard Price- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceStandardPriceDomesticWithsimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceStandardPriceDomesticWithsimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Standard
	 * Price- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 14, groups = {
			"SalesInvoice" }, description = "154 - create direct Export Sales Invoice for the customer - price basis Standard Price- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceStandardPriceExportWithsimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceStandardPriceExportWithsimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Standard
	 * Price- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 15, groups = {
			"SalesInvoice" }, description = " 141 - create direct Domestic Sales Invoice for the customer - price basis Standard Price- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceStandardPriceDomesticWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceStandardPriceDomesticWithDisimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Standard
	 * Price- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 16, groups = {
			"SalesInvoice" }, description = "  155 - create direct Export Sales Invoice for the customer - price basis Standard Price- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceStandardPriceExportWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceStandardPriceExportWithDisimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Catalog
	 * Price- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 17, groups = {
			"SalesInvoice" }, description = "   142 - create direct Domestic Sales Invoice for the customer - price basis Catalog Price- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceCatalogPriceDomesticWithsimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceCatalogPriceDomesticWithsimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Catalog
	 * Price- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 18, groups = {
			"SalesInvoice" }, description = "    156 -  create direct Export Sales Invoice for the customer - price basis Catalog Price- Similar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceCatalogPriceExportWithsimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceCatalogPriceExportWithsimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Catalog
	 * Price- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 19, groups = {
			"SalesInvoice" }, description = "     143 -   create direct Domestic Sales Invoice for the customer - price basis Catalog Price- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceCatalogPriceDomesticWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceCatalogPriceDomesticWithDisimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Catalog
	 * Price- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 20, groups = {
			"SalesInvoice" }, description = "      157 -    create direct Export Sales Invoice for the customer - price basis Catalog Price- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceCatalogPriceExportWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceCatalogPriceExportWithDisimilarUOM();
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
