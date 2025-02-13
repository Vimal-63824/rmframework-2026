package com.roadmapERP;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * @author Durai
 */
import commonfunctions.CommonFunctions;
import utils.Mailer;
import utils.WebDriverBase;
import commonfunctions.supplychainmanagement.PricelistPageFunctions;

public class PriselistTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PricelistPageFunctions PriceListfunc = new PricelistPageFunctions();

	/**
	 * Create pricelist for domestic supplier with similar UOM directly to the
	 * system through excel upload
	 * 
	 * @author Durai
	 */
	@Test(priority = 1, groups = {
			"PriceList" }, description = "Create Price List entry for Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyCreationOfPricelistDomesticSimilarUOM() {
		flag = PriceListfunc.verifycreationofpricelistDomesticSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Create pricelist for domestic supplier with Disssimilar UOM directly to the
	 * system through excel upload
	 * 
	 * @author Durai
	 */
	@Test(priority = 2, groups = {
			"PriceList" }, description = "Create Price List entry for Domestic Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyCreationOfPricelistDomesticDissimilarUOM() {
		flag = PriceListfunc.verifycreationofpricelistDomesticDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Create pricelist for Import supplier with Disssimilar UOM directly to the
	 * system through excel upload
	 * 
	 * @author Durai
	 */
	@Test(priority = 3, groups = {
			"PriceList" }, description = "Create Price List entry for Import Supplier with Stock and Supplier UOM similar")
	public void VerifyCreationOfPricelistImportSimilarUOM() {
		flag = PriceListfunc.verifycreationofpricelistImportsimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Create pricelist for Import supplier with Disssimilar UOM directly to the
	 * system through excel upload
	 * 
	 * @author Durai
	 */
	@Test(priority = 4, groups = {
			"PriceList" }, description = "Create Price List entry for Import Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyCreationOfPricelistImportDissimilarUOM() {
		flag = PriceListfunc.verifycreationofpricelistImportDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Durai
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		commonFunc.verifyAfterMethod();
	}
}
