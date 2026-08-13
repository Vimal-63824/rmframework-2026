package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks May 22, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.productionplanning.BillOfMaterialsFunctions;
import commonfunctions.productionplanning.MRPFunctions;

public class BillOfMaterialsMRPTest extends RoadMapERPTest {
	boolean flag = false;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static BillOfMaterialsFunctions bomFunc = new BillOfMaterialsFunctions();
	private static MRPFunctions mrpFunc = new MRPFunctions();
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
	 * How to do BOM Migration?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = { "BOM" }, description = "6 How to do BOM Migration?")
	public void VerifyBOMMigration() {
		flag = bomFunc.verifyBOMMigration();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create Bill of materials for the Item with single process and
	 * assembly?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"BOM" }, description = "7 How to create Bill of materials for the Item with single process and assembly?")
	public void VerifyCreateBOMItemSingleProcessAssembly() {
		flag = bomFunc.verifyCreateBOMItemSingleProcessAssembly();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create Bill of materials for the Item with multiple Sub assembly?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"BOM" }, description = "8 How to create Bill of materials for the Item with multiple Sub assembly?")
	public void VerifyCreateBOMItemMultipleSubAssembly() {
		flag = bomFunc.verifyCreateBOMItemMultipleSubAssembly();
		Assert.assertTrue(flag);
	}

	/**
	 * How to Create Multiple BOM for the Same Item?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = { "BOM" }, description = "9 How to Create Multiple BOM for the Same Item?")
	public void VerifyCreateMultipleBOMSameItem() {
		flag = bomFunc.verifyCreateMultipleBOMSameItem();
		Assert.assertTrue(flag);
	}

	/**
	 * How to Correct the Bill of Materials?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = { "BOM" }, description = "10 How to Correct the Bill of Materials?")
	public void VerifyCorrectBillOfMaterials() {
		flag = bomFunc.verifyCorrectBillOfMaterials();
		Assert.assertTrue(flag);
	}

	/**
	 * How to Copy the BOM?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = { "BOM" }, description = "11 How to Copy the BOM?")
	public void VerifyCopyBillOfMaterials() {
		flag = bomFunc.verifyCopyBillOfMaterials();
		Assert.assertTrue(flag);
	}

	/**
	 * How to revise the BOM?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = { "BOM" }, description = "12 How to revise the BOM?")
	public void VerifyReviseBillOfMaterials() {
		flag = bomFunc.verifyReviseBillOfMaterials();
		Assert.assertTrue(flag);
	}

	/**
	 * How to Inactivate the BOM?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = { "BOM" }, description = "13 How to Inactivate the BOM?")
	public void VerifyInactivateBillOfMaterials() {
		flag = bomFunc.verifyInactivateBillOfMaterials();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create and Run MRP through sales Order?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 19, groups = { "BOM" }, description = "19 How to create and Run MRP through sales Order?")
	public void VerifyCreateAndRunMRPThroughSalesOrder() {
		flag = mrpFunc.verifyCreateAndRunMRPThroughSalesOrder();
		Assert.assertTrue(flag);
	}

	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.verifyAfterMethod();
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
