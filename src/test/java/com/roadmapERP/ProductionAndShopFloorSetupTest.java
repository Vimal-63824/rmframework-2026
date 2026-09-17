package com.roadmapERP;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.productionplanning.ProductionAndShopFloorSetupFunctions;
import utils.WebDriverBase;

public class ProductionAndShopFloorSetupTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static ProductionAndShopFloorSetupFunctions ppShopFloorSetupFunc = new ProductionAndShopFloorSetupFunctions();
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
	 * How to define Workcenters
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 1, groups = { "PP&ShopFloor" }, description = "1 - How to define Process master and Workcenters")
	public void HowtodefineWorkcenters() {
		flag = ppShopFloorSetupFunc.howtodefineWorkcenters();
		Assert.assertTrue(flag);
	}

	/**
	 * How to define Process master in Inside Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 2, groups = { "PP&ShopFloor" }, description = "2 - How to define Process master in Inside Type")
	public void HowtodefineInsideTypeProcessAndAssociatedTheProcess() {
		ppShopFloorSetupFunc.howtodefineInsideTypeProcessAndAssociatedTheProcess();
	}

	/**
	 * How to define Process master in OutSide Type
	 * 
	 * @author Vimal - RMITS
	 * @throws IOException
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 3, groups = { "PP&ShopFloor" }, description = "3 - How to define Process master in OutSide Type")
	public void HowtodefineOutsideTypeProcessAndAssociatedTheProcess() throws IOException {
		ppShopFloorSetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		Assert.assertTrue(flag);
	}

	/**
	 * How to define Process master in Both Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 4, groups = { "PP&ShopFloor" }, description = "4 - How to define Process master in Both Type")
	public void HowtodefineBothTypeProcessAndAssociatedTheProcess() {
		ppShopFloorSetupFunc.howtodefineBothTypeProcessAndAssociatedTheProcess();
		Assert.assertTrue(flag);
	}

	/**
	 * How to Create Resource Group and Resources for Machine
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 5, groups = {
			"PP&ShopFloor" }, description = "5 - How to Create Resource Group and Resources for Machine")
	public void HowtoCreateResourceGroupandResourcesforMachine() {
		ppShopFloorSetupFunc.howtoCreateResourceGroupandResourcesforMachine();
	}

	/**
	 * How to Create Resource Group and Resources for manpower
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 6, groups = {
			"PP&ShopFloor" }, description = "6 - How to Create Resource Group and Resources for manpower")
	public void HowtoCreateResourceGroupandResourcesformanpower() {
		ppShopFloorSetupFunc.howtoCreateResourceGroupandResourcesformanpower();
	}

	/**
	 * How to Create Resource Group and Resources for Tool
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 7, groups = {
			"PP&ShopFloor" }, description = "7 - How to Create Resource Group and Resources for Tool")
	public void HowtoCreateResourceGroupandResourcesforTool() {
		ppShopFloorSetupFunc.howtoCreateResourceGroupandResourcesforTool();
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
