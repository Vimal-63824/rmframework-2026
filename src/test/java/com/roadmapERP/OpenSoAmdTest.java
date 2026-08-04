package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.OpenSalesOrderFunctions;
import commonfunctions.supplychainmanagement.OpenSoAmdFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class OpenSoAmdTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static OpenSalesOrderFunctions OSO = new OpenSalesOrderFunctions();
	static OpenSoAmdFunctions OSOA = new OpenSoAmdFunctions();
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
	 * Amend Sales Order -Price Change - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 1, groups = {
			"Open Sales Order Amdenment" }, description = "Amend Sales Order -Price Change - Domestic Customer - Local")
	public void verifyOpenSoAmdinLocalCustomer() {
		OSOA.verifyDomesticCustomerLocalGstTypePriceChange("");
	}

	/**
	 * Amend Sales Order -Price Class Change - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 2, groups = {
			"Open Sales Amdenment" }, description = "Amend Sales Order -Price Class Change - Domestic Customer - Local")
	public void verifyOpenSoAmdinLocalCustomerPriceClass() {
		OSOA.verifyDomesticCustomerLocalGstTypePriceClassChange();

	}

	/**
	 * Amend Sales Order - Addition of Item - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 3, groups = {
			"Open Sales Amdenment" }, description = "Amend Sales Order - Addition of Item - Domestic Customer - Local")
	public void verifyOpenSoAmdinLocalCustomerAdditionOfItem() {
		OSOA.veriftyLocalCustomerDomesticAdditionofItem();
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 4, groups = {
			"Open Sales Amdenment" }, description = "Amend Sales Order - Deletion of Item - Domestic Customer - Local")
	public void verifyOpenSoAmdinLocalCustomerDeleitionOfItem() {
		OSOA.verifyLocalCustomerDomesticDeletationofItem();
	}

	/**
	 * Cancel Open SO Amendment
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 5, groups = { "Open Sales Order Amedenment" }, description = "Cancel Open SO Amendment")
	public void verifyCancelStageScanrios() {
		OSOA.verifyaCancelStagesRecord();

	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 6, groups = {
			"Open Sales Order " }, description = "Amend Sales Order -Price Change - Domestic Customer - Interstate")
	public void verifyOpenSoAmdPriceChangeInterStateCustomer() {
		OSOA.verifyDomesticCustomerInterstateGstTypePriceChange();
	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 7, groups = {
			"Open Sales Order" }, description = "Amend Sales Order -Price Change - Domestic Customer - Interstate")
	public void verifyOpenSoAmdPriceClassChangeInterstateCustomer() {
		OSOA.verifyDomesticCustomerInterstateGstTypePriceClassChange();
	}

	/**
	 * Amend Sales Order - Addition of Item - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 8, groups = {
			"Open Sales Order " }, description = "Amend Sales Order - Addition of Item - Domestic Customer - Interstate")
	public void verifyOpenSoAmdAdditionOfItemInterStateCustomer() {
		OSOA.veriftyInterStateCustomerDomesticAdditionofItem();
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 9, groups = {
			"Open Sales Order " }, description = "Amend Sales Order - Deletion of Item - Domestic Customer - Interstate")
	public void verifyOpenSoAmdDeletationOfItemInterstateCustomer() {
		OSOA.verifyInterStateCustomerDomesticDeletationofItem();
	}

	/**
	 * Amend Sales Order -Price Change - Export Customer / *
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 10, groups = {
			"Open Sales Order " }, description = "Amend Sales Order -Price Change - Export Customer")
	public void verifyOpenSoPriceChangeOfItemExportCustomer() {
		OSOA.verifyExportCustomerPriceChange();
	}

	/**
	 * Amend Sales Order -Price Class Change - Export Customer
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 11, groups = {
			"Open Sales Order " }, description = "Amend Sales Order -Price Class Change - Export Customer")
	public void verifyOpenSoPriceClassChangesExportCustomer() {
		OSOA.verifyExportCustomerPriceClassChanges();
	}

	/**
	 * Amend Sales Order - Addition of Item - Export Customer
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 12, groups = {
			"Open Sales Order " }, description = "Amend Sales Order - Addition of Item - Export Customer ")
	public void verifyOpenSoAdditionOfItemsExportCustomer() {
		OSOA.verifyExportCustomerAdditionOfItem();
	}

	/**
	 * Amend Sales Order - Deletion of Item - Export Customer
	 * 
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 13, groups = {
			"Open Sales Order " }, description = "Amend Sales Order - Deletion of Item - Export Customer")
	public void verifyOpenSoDeletationOfItemsExportCustomer() {
		OSOA.verifyExportCustomerDeletationOfItem();
	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - UTGST
	 *
	 * @author Kowshik
	 */

	@Author("Kowshik-RMITS")
	@Test(priority = 14, groups = {
			"Open Sales Order " }, description = "Amend Sales Order -Price Change - Domestic Customer - UTGST")
	public void verifyOpenSoAmdUTGSTpriceChange() {
		OSOA.verifyUTGSTpriceChanges();
	}

	/**
	 * Amend Sales Order -Price Class Change - Domestic Customer - UTGST
	 *
	 * @author Kowshik
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 15, groups = {
			"Open Sales Order " }, description = "Amend Sales Order -Price Class Change - Domestic Customer - UTGST")
	public void verifyOpenSoAmdUTGSTCustomerPriceClassChanges() {
		OSOA.verifyUTGSTpriceclassChanges();
	}

	/**
	 * Amend Sales Order - Addition of Item - Domestic Customer - UTGST
	 *
	 * @author Kowshik
	 */

	@Author("Kowshik-RMITS")
	@Test(priority = 16, groups = {
			"Open Sales Order " }, description = "Amend Sales Order - Addition of Item - Domestic Customer - UTGST")
	public void veriftyOpenSoAmdUTGSTCustomerAdditionOfItem() {
		OSOA.verifyUTGSTAdditionOfItem();
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - UTGST
	 *
	 * @author Kowshik
	 */

	@Author("Kowshik-RMITS")
	@Test(priority = 17, groups = {
			"Open Sales Order " }, description = "Amend Sales Order - Deletion of Item - Domestic Customer - UTGST")
	public void verifyOpenSoAmdUTGSTCustomerDeletationOfitem() {
		OSOA.verifyUTGSTDeletationOfItem();
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - UTGST
	 *
	 * @author Kowshik
	 */
//	@Author("Kowshik-RMITS")
//	@Test(priority = 18, groups = { "Open Sales Order " }, description = "verifyOpenSoamdMultiplesLines")
	public void verifyOpenSoamdMultiplesLines() {
		OSOA.verifyMutiplesAddTypes();
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
