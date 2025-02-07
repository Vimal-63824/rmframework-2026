package com.roadmapERP;

import org.testng.Assert;
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
	public void verifyDomesticSOGeneralSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Domestic Sales Order - price basis is user
	 *         defined- Dissimilar Stock and Customer UOM
	 */
	@Test(priority = 2, groups = {
			"SalesOrder" }, description = "Domestic Sales Order - price basis is user defined- Dissimilar Stock and Customer UOM")
	public void verifyDomesticSOGeneralDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyDomesticSOGeneralDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is user defined-
	 *         Similar Stock and Customer UOM
	 */
	@Test(priority = 3, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is user defined- Similar Stock and Customer UOM")
	public void verifyExportSOGeneralSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS Export Sales Order - price basis is user defined-
	 *         Dissimilar Stock and Customer UOM
	 */
	@Test(priority = 4, groups = {
			"SalesOrder" }, description = "Export Sales Order - price basis is user defined- Dissimilar Stock and Customer UOM")
	public void verifyExportSOGeneralDisSimilarStockAndCustomerUOM() {
		flag = SalesOrdFunc.verifyExportSOGeneralDisSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}
}
