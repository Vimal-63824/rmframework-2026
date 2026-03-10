package com.roadmapERP;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Feb 10, 2026
 */
import org.testng.Assert;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.financials.SubcontractPurchaseBills;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import utils.WebDriverBase;

public class SubcontractPurchaseBillsTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SubcontractPurchaseBills SubcontractPurchasebillFunc = new SubcontractPurchaseBills();

	/**
	 * Registered Supplier with Local GST- Against Subcontract With Deviation and
	 * Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 1, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract With Deviation and Expenses Account")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccount();
		Assert.assertTrue(flag);
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 2, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract Without Deviation")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAgainstSubcontractWithoutDeviation();
		Assert.assertTrue(flag);
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract Without Deviation and
	 * Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 3, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract Without Deviation and Expenses Account And adjustment")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 4, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract With Deviation")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAgainstSubcontractWithDeviation();
	}

}
