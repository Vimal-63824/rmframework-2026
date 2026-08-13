package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import commonfunctions.supplychainmanagement.SalesCataloguePrice;
import utils.Mailer;
import utils.WebDriverBase;

public class SalesCataloguePriceTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static CustomerItemsFunctions customerItemFunc = new CustomerItemsFunctions();
	static SalesCataloguePrice Scp = new SalesCataloguePrice();
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
	 * Create Catalog Price with Similar Stock and Customer UOM - Dom. Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 1, groups = {
			"Sales Catalogue Price" }, description = "Create Catalog Price with Similar Stock and Customer UOM - Dom. Customer")
	public void veriftyCreateCatalogPricewithSimilarStockandCustomerUOMDomCustomer() {
		Scp.createsCataloguePriceSimilarUomAndDomesticCustomer(commonFunc.getDataFromTestDataSheet("min_Disc"),
				commonFunc.getDataFromTestDataSheet("max_Disc"));
	}

	/**
	 * Create Catalog Price with DisSimilar Stock and Customer UOM - Dom. Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 2, groups = {
			"Sales Catalogue Price" }, description = "Create Catalog Price with DisSimilar Stock and Customer UOM - Dom. Customer")
	public void veriftyCreateCatalogPricewithDisSimilarStockandCustomerUOMDomCustomer() {
		Scp.createsCataloguePriceDisSimilarUomAndDomesticCustomer(commonFunc.getDataFromTestDataSheet("min_Disc"),
				commonFunc.getDataFromTestDataSheet("max_Disc"));
	}

	/**
	 * Create Catalog Price with Similar Stock and Customer UOM - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 3, groups = {
			"Sales Catalogue Price" }, description = "Create Catalog Price with Similar Stock and Customer UOM - Export Customer")
	public void veriftyCreateCatalogPricewithSimilarStockandCustomerUOMExportCustomer() {
		Scp.createsCataloguePriceSimilarUomAndExportCustomer(commonFunc.getDataFromTestDataSheet("min_Disc"),
				commonFunc.getDataFromTestDataSheet("max_Disc"));
	}

	/**
	 * Create Catalog Price with DisSimilar Stock and Customer UOM - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 4, groups = {
			"Sales Catalogue Price" }, description = "Create Catalog Price with DisSimilar Stock and Customer UOM - Export Customer")
	public void veriftyCreateCatalogPricewithDisSimilarStockandCustomerUOMExportCustomer() {
		Scp.createsCataloguePriceDisSimilarUomAndExportCustomer(commonFunc.getDataFromTestDataSheet("min_Disc"),
				commonFunc.getDataFromTestDataSheet("max_Disc"));
	}

	/**
	 * Cancel Catalog pricer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 5, groups = { "Sales Catalogue Price" }, description = "Cancel Catalog price")
	public void veriftyCreateCatalogPricewithCancelCatalogPrice() {
		Scp.createsCancelCatalogPrice(commonFunc.getDataFromTestDataSheet("min_Disc"),
				commonFunc.getDataFromTestDataSheet("max_Disc"));
	}

	/**
	 * Creates Multiples Catalogue Price
	 * 
	 * @author Kowshik-RMITS
	 */
//	@Author("Kowshik-RMITS")
//	@Test(priority = 6, groups = { "Sales Catalogue Price" }, description = "Multiples Lines Creates")
	public void veriftyCreatesMultiplesCataloguePrice() {
		Scp.createsMultiplesCataloguePrice();
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
