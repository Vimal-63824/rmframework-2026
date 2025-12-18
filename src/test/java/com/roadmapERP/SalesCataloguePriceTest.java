package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
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
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
