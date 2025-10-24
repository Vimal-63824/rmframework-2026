package com.roadmapERP;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.productionplanning.ProductionAndShopFloorSetupFunctions;
import utils.WebDriverBase;

public class ProductionAndShopFloorSetupTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static ProductionAndShopFloorSetupFunctions ppShopFloorSetupFunc = new ProductionAndShopFloorSetupFunctions();

//	/**
//	 * How to define Workcenters
//	 * 
//	 * @author Vimal - RMITS
//	 */
//	@Test(priority = 1, groups = {
//			"PP&ShopFloor" }, description = "1 - How to define Process master and Workcenters")
//	public void HowtodefineWorkcenters() {
//		flag = ppShopFloorSetupFunc.howtodefineWorkcenters();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * How to define Process master in Inside Type
//	 * 
//	 * @author Vimal - RMITS
//	 */
//	@Test(priority = 2, groups = {
//			"PP&ShopFloor" }, description = "2 - How to define Process master in Inside Type")
//	public void HowtodefineInsideTypeProcessAndAssociatedTheProcess() {
//		flag = ppShopFloorSetupFunc.howtodefineInsideTypeProcessAndAssociatedTheProcess();
//		Assert.assertTrue(flag);
//	}

	/**
	 * How to define Process master in OutSide Type
	 * 
	 * @author Vimal - RMITS
	 * @throws IOException
	 */
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
	@Test(priority = 4, groups = { "PP&ShopFloor" }, description = "4 - How to define Process master in Both Type")
	public void HowtodefineBothTypeProcessAndAssociatedTheProcess() {
		ppShopFloorSetupFunc.howtodefineBothTypeProcessAndAssociatedTheProcess();
		Assert.assertTrue(flag);
//	}
//	/**
//	 * How to Create Resource Group and Resources for Machine
//	 * 
//	 * @author Vimal - RMITS
//	 */
//	@Test(priority = 5, groups = {
//			"PP&ShopFloor" }, description = "5 - How to Create Resource Group and Resources for Machine")
//	public void HowtoCreateResourceGroupandResourcesforMachine() {
//		flag = ppShopFloorSetupFunc.howtoCreateResourceGroupandResourcesforMachine();
//		Assert.assertTrue(flag);
//	}
//	/**
//	 * How to Create Resource Group and Resources for manpower
//	 * 
//	 * @author Vimal - RMITS
//	 */
//	@Test(priority = 6, groups = {
//			"PP&ShopFloor" }, description = "6 - How to Create Resource Group and Resources for manpower")
//	public void HowtoCreateResourceGroupandResourcesformanpower() {
//		flag = ppShopFloorSetupFunc.howtoCreateResourceGroupandResourcesformanpower();
//		Assert.assertTrue(flag);
//	}
//	/**
//	 * How to Create Resource Group and Resources for Tool
//	 * 
//	 * @author Vimal - RMITS
//	 */
//	@Test(priority = 7, groups = {
//			"PP&ShopFloor" }, description = "7 - How to Create Resource Group and Resources for Tool")
//	public void HowtoCreateResourceGroupandResourcesforTool() {
//		flag = ppShopFloorSetupFunc.howtoCreateResourceGroupandResourcesforTool();
//		Assert.assertTrue(flag);
//	}
	}
}
