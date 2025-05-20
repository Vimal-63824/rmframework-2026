package com.roadmapERP;

import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import commonfunctions.supplychainmanagement.SalesCataloguePrice;
import utils.Mailer;
import utils.WebDriverBase;

public class SalesCataloguePriceTest {

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
	@Test(priority = 1, groups = {
			"Sales Catalogue Price" }, description = "Create Catalog Price with Similar Stock and Customer UOM - Dom. Customer")
	public void veriftyCreateCatalogPricewithSimilarStockandCustomerUOMDomCustomer() {
		Scp.createsCataloguePriceSimilarUomAndDomesticCustomer();
	}

	/**
	 * Create Catalog Price with DisSimilar Stock and Customer UOM - Dom. Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Test(priority = 2, groups = {
			"Sales Catalogue Price" }, description = "Create Catalog Price with DisSimilar Stock and Customer UOM - Dom. Customer")
	public void veriftyCreateCatalogPricewithDisSimilarStockandCustomerUOMDomCustomer() {
		Scp.createsCataloguePriceDisSimilarUomAndDomesticCustomer();
	}

	/**
	 * Create Catalog Price with Similar Stock and Customer UOM - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Test(priority = 3, groups = {
			"Sales Catalogue Price" }, description = "Create Catalog Price with Similar Stock and Customer UOM - Export Customer")
	public void veriftyCreateCatalogPricewithSimilarStockandCustomerUOMExportCustomer() {
		Scp.createsCataloguePriceSimilarUomAndExportCustomer();
	}

	/**
	 * Create Catalog Price with DisSimilar Stock and Customer UOM - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Test(priority = 4, groups = {
			"Sales Catalogue Price" }, description = "Create Catalog Price with DisSimilar Stock and Customer UOM - Export Customer")
	public void veriftyCreateCatalogPricewithDisSimilarStockandCustomerUOMExportCustomer() {
		Scp.createsCataloguePriceDisSimilarUomAndExportCustomer();
	}

	/**
	 * Cancel Catalog pricer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Test(priority = 5, groups = { "Sales Catalogue Price" }, description = "Cancel Catalog price")
	public void veriftyCreateCatalogPricewithCancelCatalogPrice() {
		Scp.createsCancelCatalogPrice();
	}

	/**
	 * Creates Multiples Catalogue Price
	 * 
	 * @author Kowshik-RMITS
	 */
	@Test(priority = 6, groups = { "Sales Catalogue Price" }, description = "Multiples Lines Creates")
	public void veriftyCreatesMultiplesCataloguePrice() {
		Scp.createsMultiplesCataloguePrice();
	}
}
