package com.roadmapERP;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Oct 16, 2025
 */
import org.testng.Assert;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import utils.WebDriverBase;

public class SubcontractTransactionTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SubcontractOrderFunctions SubcontractFunc = new SubcontractOrderFunctions();

	/**
	 * Create Direct Subcontract Order For Standard Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 1, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type")
	public void CreateDirectSubcontractOrderWithBOMForStandardType() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardType();
		Assert.assertTrue(flag);
	}
	/**
	 *Create Production Order From Subcontract order To GRN
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 2, groups = {
			"Subcontract" }, description = "Create Production Order From Subcontract order To GRN PR Type")
	public void CreateProductionOrderFromSubcontractorderToGRNTypePR() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNTypePR();
		Assert.assertTrue(flag);
	}
	/**
	 * Create Production Order From Subcontract order To GRN Unprocess Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 3, groups = {
			"Subcontract" }, description = "Create Production Order From Subcontract order To GRN Unprocess Type")
	public void createProductionOrderFromSubcontractorderToGRNUnprocessType() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessType();
		Assert.assertTrue(flag);
	}
	/**
	 * Create Production Order From Subcontract order To GRN Unprocess Standard Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 4, groups = {
			"Subcontract" }, description = "Create Production Order From Subcontract order To GRN Unprocess Standard Type")
	public void CreateProductionOrderFromSubcontractorderToGRNUnprocessStandardType() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessStandardType();
		Assert.assertTrue(flag);
	}

}
