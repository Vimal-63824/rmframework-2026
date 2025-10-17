package com.roadmapERP;

/**
 * @author Harikrishna - RMITS
 * @Company RoadMap It Solutions October 07, 2025
 */
import org.testng.Assert;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
import commonfunctions.supplychainmanagement.SalesCataloguePrice;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonfunctions.supplychainmanagement.SalesPriceListFunctions;
import commonfunctions.supplychainmanagement.SetupSalesFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class MaterialRequestTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesOrderFunctions SalesOrdFunc = new SalesOrderFunctions();
	public ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	public MaterialRequestFunctions materialReqFunc = new MaterialRequestFunctions();
	private static ReportLoger logs = new ReportLoger();

	/**
	 * Checking The Material Request Find Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Test(priority = 1, groups = { "Material Request" }, description = "Material Request Customer Type")
	public void verifyMRFindScreen() {
		flag = materialReqFunc.createMRCustomerType();
		Assert.assertTrue(flag);
	}
}
