package com.roadmapERP;

import org.testng.annotations.Test;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class CustomerItemTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static CustomerItemsFunctions customerItemFunc = new CustomerItemsFunctions();
	
	/**
	 * Associate item to the customer or mapping of customer
	 * 
	 * @author Kowshik
	 */
	@Test(priority = 1, groups = {
			"Customer Items" }, description = "Associate item to the customer or mapping of customer")
	public void VerifyCustomerItemProcessPriceMaster() {
		customerItemFunc.verifyAssociateItemToCustomeMappingOfCustomer();
	}
	
	/**
	 * Associate item to the customer or mapping of customer
	 * 
	 * @author Kowshik
	 */
	@Test(priority = 2, groups = {
			"Customer Items" }, description = "Associate item to the customer or mapping of customer")
	public void VerifyCustomerItemProcessPriceMasterForPriceListItem() {
		customerItemFunc.itemAssociateWithPriceListItem();
	}
	
	/**
	 * Associate item to the customer or mapping of customer
	 * 
	 * @author Kowshik
	 */
	@Test(priority = 3, groups = {
			"Customer Items" }, description = "Associate item to the customer or mapping of customer")
	public void VerifyCustomerItemProcessDifferentUomPriceLItem() {
		customerItemFunc.itemAssociatePriceListItemDifferentUOM();
	}
	
	/**
	 * Associate item to the customer or mapping of customer
	 * 
	 * @author Kowshik
	 */
	@Test(priority = 4, groups = {
			"Customer Items" }, description = "Associate item to the customer or mapping of customer")
	public void VerifyCustomerItemProcessDifferentPriceClassesPriceLItem() {
		customerItemFunc.itemAssociatePriceListItemDifferentPriceClass();
	}
	
	/**
	 * Associate item to the customer or mapping of customer
	 * 
	 * @author Kowshik
	 */
	@Test(priority = 5, groups = {
			"Customer Items" }, description = "Associate item to the customer or mapping of customer")
	public void VerifycreatesCataloguePriceSimilarUOMAndDomesticCustomer() {
		customerItemFunc.createsCataloguePriceSimilarUOMAndDomesticCustomer();
	}
}
