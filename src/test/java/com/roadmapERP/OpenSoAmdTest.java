package com.roadmapERP;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.OpenSalesOrderFunctions;
import commonfunctions.supplychainmanagement.OpenSoAmdFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class OpenSoAmdTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static OpenSalesOrderFunctions OSO = new OpenSalesOrderFunctions();
	static OpenSoAmdFunctions OSOA = new OpenSoAmdFunctions();

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 */
	@Test(priority = 1, groups = {
			"Open Sales Order Amdenment" }, description = "Amend Sales Order -Price Change - Domestic Customer - Local")
	public void verifyOpenSoAmdinLocalCustomer() {
		OSOA.verifyDomesticCustomerLocalGstTypePriceChange();
	}

	/**
	 * Amend Sales Order -Price Class Change - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 */
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
	@Test(priority = 5, groups = { "Open Sales Order Amedenment" }, description = "Cancel Open SO Amendment")
	public void verifyCancelStageScanrios() {
		OSOA.verifyaCancelStagesRecord();

	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 */
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
	@Test(priority = 9, groups = {
			"Open Sales Order " }, description = "Amend Sales Order - Deletion of Item - Domestic Customer - Interstate")
	public void verifyOpenSoAmdDeletationOfItemInterstateCustomer() {
		OSOA.verifyInterStateCustomerDomesticDeletationofItem();
	}

	/**
	 * Amend Sales Order -Price Change - Export Customer
	 * 
	 * @author Kowshik
	 */
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
	@Test(priority = 13, groups = {
			"Open Sales Order " }, description = "Amend Sales Order - Deletion of Item - Export Customer")
	public void verifyOpenDeletationOfItemsExportCustomer() {
		OSOA.verifyExportCustomerDeletationOfItem();
	}
	
	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Kowshik
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod() {
		commonFunc.verifyAfterMethod();
	}

	
}
