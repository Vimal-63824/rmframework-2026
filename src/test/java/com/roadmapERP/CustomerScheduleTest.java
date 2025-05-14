package com.roadmapERP;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.CustomerScheduleFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class CustomerScheduleTest extends RoadMapERPTest{
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesOrderFunctions SalesOrdFunc = new SalesOrderFunctions();
	static SalesInvoiceFunctions SalesInvFunc = new SalesInvoiceFunctions();
	static CustomerScheduleFunctions customerScheduleFunctions = new CustomerScheduleFunctions();
	private static ReportLoger logs = new ReportLoger();

	/**
	 * @author Harikrishna - RMITS Domestic Customer Schedule - price basis is user
	 *         defined- Similar Stock and Customer UOM
	 */
	@Test(priority = 1, groups = {
			"CustomerSchedule" }, description = "Domestic Customer Schedule - price basis is user defined- Similar Stock and Customer UOM")
	public void VerifyDomesticCSGeneralSimilarStockAndCustomerUOM() {
		flag = customerScheduleFunctions.verifyDomesticCSGeneralSimilarStockAndCustomerUOM();
		Assert.assertTrue(flag);
	}
}
