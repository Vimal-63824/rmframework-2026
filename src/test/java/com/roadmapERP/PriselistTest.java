package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
/**
 * @author Durai-RMITS
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.PricelistPageFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class PriselistTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PricelistPageFunctions PriceListfunc = new PricelistPageFunctions();
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
	 * Create pricelist for domestic supplier with similar UOM directly to the
	 * system through excel upload
	 * 
	 * @author Durai-RMITS-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 1, groups = {
			"PriceList" }, description = "Create Price List entry for Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyCreationOfPriceListDomesticSimilarUOM() {
		PriceListfunc.verifyCreationOfPriceListDomesticSimilarUom();
	}

	/**
	 * Create pricelist for domestic supplier with Disssimilar UOM directly to the
	 * system through excel upload
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 2, groups = {
			"PriceList" }, description = "Create Price List entry for Domestic Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyCreationOfPriceListDomesticDissimilarUOM() {
		PriceListfunc.verifyCreationOfPriceListDomesticDissimilarUom();
	}

	/**
	 * Create pricelist for Import supplier with Disssimilar UOM directly to the
	 * system through excel upload
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 3, groups = {
			"PriceList" }, description = "Create Price List entry for Import Supplier with Stock and Supplier UOM similar")
	public void VerifyCreationOfPriceListImportSmilarUOM() {
		PriceListfunc.verifyCreationOfPriceListImportSimilarUom();
	}

	/**
	 * Create pricelist for Import supplier with Disssimilar UOM directly to the
	 * system through excel upload
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 4, groups = {
			"PriceList" }, description = "Create Price List entry for Import Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyCreationOfPriceListImportDissimilarUOM() {
		PriceListfunc.verifyCreationOfPriceListImportDissimilarUom();
	}

	/**
	 * PriceListEntryCancel
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 5, groups = { "PriceList" }, description = "PriceListEntryCancel")
	public void VerifyPriceListEntryCancel() {
		PriceListfunc.priceListEntryCancel();
	}

	/**
	 * PriceListEntry Find Checking
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 6, groups = { "PriceList" }, description = "Check whether all the filter fields are working?")
	public void VerifyCheckingOfPriceListFindScreenChecking() {
		PriceListfunc.priceListEntryFindScreenChecking();
	}

	/**
	 * Create Price List entry for Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 7, groups = {
			"PriceList" }, description = "Amend Price List entry for Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyCreationOfPriceListAmendmentDomesticSimilarUom() {
		PriceListfunc.verifyCreationOfPriceListAmendmentDomesticSimilarUom();
	}

	/**
	 * Create Price List entry for Domestic Supplier with Stock and Supplier UOM
	 * Dissimilar
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 8, groups = {
			"PriceList" }, description = "Amend Price List entry for Domestic Supplier with Stock and Supplier UOM Dissimilar")
	public void VerifyCreationOfPriceListAmendmentDomesticDisSimilarUom() {
		PriceListfunc.verifyCreationOfPriceListAmendmentDomesticDisSimilarUom();
	}

	/**
	 * Create Price List entry for Import Supplier with Stock and Supplier UOM
	 * Dissimilar
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 9, groups = {
			"PriceList" }, description = "Amend Price List entry for Import Supplier with Stock and Supplier UOM similar")
	public void VerifyCreationOfPriceListAmendmentImportSimilarUom() {
		PriceListfunc.verifyCreationOfPriceListAmendmentImportSimilarUom();
	}

	/**
	 * Create Price List entry for Import Supplier with Stock and Supplier UOM
	 * Dissimilar
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 10, groups = {
			"PriceList" }, description = "Amend Price List entry for Import Supplier with Stock and Supplier UOM Dissimilar")
	public void VerifyCreationOfPriceListAmendmentImportDisSimilarUom() {
		PriceListfunc.verifyCreationOfPriceListAmendmentImportDisSimilarUom();
	}

	/**
	 * Create Price List entry for Import Supplier with Stock and Supplier UOM
	 * Dissimilar
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 11, groups = { "PriceList" }, description = "Amend Price List entry Cancel")
	public void VerifyCreationOfPriceListAmendmentCancel() {
		PriceListfunc.verifyCreationOfPriceListAmendmentCancel();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 12, groups = { "GRN" }, description = "Check whether all the filter fields are working?")
	public void verifyCreationOfPricelistFilterAllFieldWorking() {
		PriceListfunc.verifyCreationOfPricelistFilterAllFieldWorking();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 13, groups = { "GRN" }, description = "Check whether all the filter fields are working?")
	public void verifyCreationOfPricelistAmdFilterAllFieldWorking() {
		PriceListfunc.verifyCreationOfPricelistAmdFilterAllFieldWorking();
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
