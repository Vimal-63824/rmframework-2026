package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import commonfunctions.supplychainmanagement.SalesCataloguePrice;
import commonfunctions.supplychainmanagement.SalesStandardPriceFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class SalesStandardPriceTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static CustomerItemsFunctions customerItemFunc = new CustomerItemsFunctions();
	static SalesCataloguePrice Scp = new SalesCataloguePrice();
	static SalesStandardPriceFunctions Std = new SalesStandardPriceFunctions();
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
	 * Create Standard Price with Similar Stock and Customer UOM - Dom. Customer
	 * 
	 * @author Kowshik-RMITS
	 */

	@Author("Kowshik-RMITS")
	@Test(priority = 1, groups = {
			"Sales Standard Price" }, description = "Create Standard Price with Similar Stock and Customer UOM - Dom. Customer")
	public void veriftyCreateStandardPricewithSimilarStockandCustomerUOMDomesticCustomer() {
		Std.CreateStandardPriceWithSimilarStockandCustomerUOMDomesticCustomer();
	}

	/**
	 * Create Standard Price with Different Similar Stock and Customer UOM - Dom.
	 * Customer
	 * 
	 * @author Kowshik-RMITS
	 */

	@Author("Kowshik-RMITS")
	@Test(priority = 2, groups = {
			"Sales Standard Price" }, description = "Create Standard Price with Different Similar Stock and Customer UOM - Dom. Customer")
	public void veriftyCreateStandardPricewithDisSimilarStockandCustomerUOMDomesticCustomer() {
		Std.CreateStandardPriceWithDifferentStockandCustomerUOMDomesticCustomer();
	}

	/**
	 * Create Standard Price with Similar Stock and Customer UOM - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 3, groups = {
			"Sales Standard Price" }, description = "Create Standard Price with Different Similar Stock and Customer UOM - Export Customer")
	public void veriftyCreateStandardPricewithSimilarStockandCustomerUOMExportCustomer() {
		Std.CreateStandardPricewithSimilarStockandCustomerUOMExportCustomer();
	}

	/**
	 * Create Standard Price with Different Similar Stock and Customer UOM - Export
	 * Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 4, groups = {
			"Sales Standard Price" }, description = "Create Standard Price with Different Similar Stock and Customer UOM - Export Customer")
	public void veriftyCreateStandardPricewithDifferSimilarStockandCustomerUOMExportCustomer() {
		Std.CreateStandardPricewithDifferSimilarStockandCustomerUOMExportCustomer();
	}

	/**
	 * Create Cancel Record
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 5, groups = { "Sales Standard Price" }, description = "Creates Cancel Record ")
	public void veriftyCreateDocumnetCancelStages() {
		Std.CancelStandardPrice();
	}

	/**
	 * Creates Multiples Item Same Price Class
	 * 
	 * @author Kowshik-RMITS
	 */
//	@Author("Kowshik-RMITS")
//	@Test(priority = 6, groups = { "Sales Standard Price" }, description = "Creates Multiples Item Same Price Class ")
	public void veriftyCreateItemMultipleStdPrice() {
		Std.createsMultiplesItemsStdPrice();
	}

	/**
	 * Creates Multiples Item Same Price Class With Different PriceClass
	 * 
	 * @author Kowshik-RMITS
	 */
//	@Author("Kowshik-RMITS")
//	@Test(priority = 7, groups = {
//			"Sales Standard Price" }, description = "Creates Multiples Item Same Price Class With Different PriceClass")
	public void veriftyCreatesItemsMultiplesStdPriceDifferentPriceClass() {
		Std.createsMultiplesItemStdPriceDifferentPriceClass();
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
