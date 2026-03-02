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

public class SubcontractPurchaseBillsTest extends RoadMapERPTest{
	
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SubcontractPurchaseBills SubcontractPurchasebillFunc = new SubcontractPurchaseBills();

	/**
	 * Direct Subcontract Order With BOM For Standard Type -
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 1, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type - Interstate(Unregistered)")
	public void SubcontractOrderToPurchaseBill() {
		 SubcontractPurchasebillFunc.subcontractOrderToPurchaseBill();
		Assert.assertTrue(flag);
	}

}
