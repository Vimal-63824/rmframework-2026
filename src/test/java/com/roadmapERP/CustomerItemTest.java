package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class CustomerItemTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static CustomerItemsFunctions customerItemFunc = new CustomerItemsFunctions();
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
	 * Associate item to the customer or mapping of customer
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
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
	@Author("Kowshik-RMITS")
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
	@Author("Kowshik-RMITS")
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
	@Author("Kowshik-RMITS")
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
	@Author("Kowshik-RMITS")
	@Test(priority = 5, groups = {
			"Customer Items" }, description = "Associate item to the customer or mapping of customer")
	public void VerifycreatesCataloguePriceSimilarUOMAndDomesticCustomer() {
		customerItemFunc.createsCataloguePriceSimilarUOMAndDomesticCustomer();
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
