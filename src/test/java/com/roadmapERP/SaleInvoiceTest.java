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
import org.testng.annotations.Test;

import commonfunctions.Author;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import utils.WebDriverBase;

public class SaleInvoiceTest extends RoadMapERPTest {
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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

	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
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
	@Author("Vimal-RMITS")
	@Test(priority = 20, groups = {
			"SalesInvoice" }, description = "      157 -    create direct Export Sales Invoice for the customer - price basis Catalog Price- Disimilar Stock and Customer UOM")
	public void VerifyDirectSalesInvoiceCatalogPriceExportWithDisimilarUOM() {
		flag = salesInvoiceFunc.verifyDirectSalesInvoiceCatalogPriceExportWithDisimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Proforma Invoice creation - Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 21, groups = { "SalesInvoice" }, description = "127 - Proforma Invoice creation - Direct")
	public void VerifyDirectProformaInvoiceCreation() {
		flag = salesInvoiceFunc.verifyDirectProformaInvoiceCreation();
		Assert.assertTrue(flag);
	}

	/**
	 * Proforma Invoice - Advance
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 22, groups = { "SalesInvoice" }, description = "128 - Proforma Invoice - Advance")
	public void VerifyDirectAdvanceProformaInvoiceCreation() {
		flag = salesInvoiceFunc.verifyDirectAdvanceProformaInvoiceCreation();
		Assert.assertTrue(flag);
	}

	/**
	 * Proforma Invoice - Pre Dispatch
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 23, groups = { "SalesInvoice" }, description = "129 - Proforma Invoice - Pre Dispatch")
	public void VerifyDirectPreDispatchProformaInvoiceCreation() {
		flag = salesInvoiceFunc.verifyDirectPreDispatchProformaInvoiceCreation();
		Assert.assertTrue(flag);
	}

	/**
	 * Proforma Invoice creation Export - Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 24, groups = { "SalesInvoice" }, description = "195 - Proforma Invoice creation Export - Direct")
	public void VerifyDirectExportProformaInvoiceCreation() {
		flag = salesInvoiceFunc.verifyDirectExportProformaInvoiceCreation();
		Assert.assertTrue(flag);
	}

	/**
	 * Proforma Invoice Export - Advance
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 25, groups = { "SalesInvoice" }, description = "196 - Proforma Invoice Export - Advance")
	public void VerifyDirectExportAdvanceProformaInvoiceCreation() {
		flag = salesInvoiceFunc.verifyDirectExportAdvanceProformaInvoiceCreation();
		Assert.assertTrue(flag);
	}

	/**
	 * Proforma Invoice Export - Pre Dispatch
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 26, groups = { "SalesInvoice" }, description = "197 - Proforma Invoice Export - Pre Dispatch")
	public void VerifyDirectExportPreDispatchProformaInvoiceCreation() {
		flag = salesInvoiceFunc.verifyDirectExportPreDispatchProformaInvoiceCreation();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Proforma Invoice creation pending SO
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 27, groups = { "SalesInvoice" }, description = "126 - Direct Proforma Invoice creation pending SO")
	public void VerifyDirectDomesticProformaInvoiceCreationPendingSO() {
		flag = salesInvoiceFunc.verifyDirectDomesticProformaInvoiceCreationPendingSO();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Proforma Invoice creation pending SO Export
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 28, groups = {
			"SalesInvoice" }, description = "194 - Direct Proforma Invoice creation pending SO Export")
	public void VerifyDirectExportProformaInvoiceCreationPendingSO() {
		flag = salesInvoiceFunc.verifyDirectExportProformaInvoiceCreationPendingSO();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SI - Domestic - Local Customer
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 29, groups = { "SalesInvoice" }, description = "      158 - Create SI - Domestic - Local Customer")
	public void VerifyCreateSalesInvoiceDomesticLocalCustomer() {
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceDomesticLocalCustomer();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SI - Domestic - Interstate Customer
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 30, groups = {
			"SalesInvoice" }, description = "      159 - Create SI - Domestic - Interstate Customer")
	public void VerifyCreateSalesInvoiceDomesticInterStateCustomer() {
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceDomesticInterStateCustomer();
		Assert.assertTrue(flag);
	}

	/**
	 * Create SI - Domestic - UTGST Customer
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 31, groups = { "SalesInvoice" }, description = "      160 - Create SI - Domestic - UTGST Customer")
	public void VerifyCreateSalesInvoiceDomesticUTGSTCustomer() {
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceDomesticUTGSTCustomer();
		Assert.assertTrue(flag);
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
		flag = salesInvoiceFunc.verifyCreateSalesInvoiceDomesticFromApprovedSalesorders();
		Assert.assertTrue(flag);
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
		flag = salesInvoiceFunc.verifyUnpickTheSalesInvoice();
		Assert.assertTrue(flag);
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

	/**
	 * Create SI with PDI process
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 59, groups = { "SalesInvoice" }, description = "       172 -         Create SI with  PDI process")
	public void CreateSalesInvoiceWithPreDispatchDocument() {
		flag = salesInvoiceFunc.createSalesInvoiceWithPreDispatchDocument();
		Assert.assertTrue(flag);
	}

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
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
