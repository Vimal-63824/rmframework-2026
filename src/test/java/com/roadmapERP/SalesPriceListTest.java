package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.SalesPriceListFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class SalesPriceListTest extends RoadMapERPTest {

	private static final String String = null;
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesPriceListFunctions Spl = new SalesPriceListFunctions();

	/**
	 * Create price List Entry
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 1, groups = { "Sales Price List" }, description = "Create price List Entry")
	public void veriftyCreatePriceListEntry() {
		Spl.veriftyCreatePriceListEntry();
	}

	/**
	 * Create price List Entry with different UOM
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 2, groups = { "Sales Price List" }, description = "Create price List Entry")
	public void CreatePriceListEntryWithDifferentUOM() {
		Spl.CreatePriceListEntryWithDifferentUOM();
	}

	/**
	 * Create price List Entry with different price classes
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 3, groups = { "Sales Price List" }, description = "Create price List Entry")
	public void CreatePriceListEntryWithDifferentPriceClasses() {
		Spl.CreatePriceListEntryWithDifferentPriceClasses();
	}

	/**
	 * Amend the Price List Entry
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 4, groups = { "Sales Price List" }, description = "Create price List Entry")
	public void AmendthePriceListEntry() {
		Spl.CreatePriceListEntryApprovedWithAmendRecord();
	}

	/**
	 * Amend the Price List Entry
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 5, groups = { "Sales Price List" }, description = "Create price List Entry")
	public void CancelPriceListEntry() {
		Spl.createCancelPriceListEntry();
	}
	
	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
