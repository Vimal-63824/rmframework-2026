package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Oct 16, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import utils.WebDriverBase;

public class SubcontractTransactionTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SubcontractOrderFunctions SubcontractFunc = new SubcontractOrderFunctions();
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
	 * Direct Subcontract Order With BOM For Standard Type -
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 1, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type - Interstate(Unregistered)")
	public void CreateDirectSubcontractOrderWithBOMForStandardTypeInterstateUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Subcontract Order to GRN without Gate Entry for PR type - Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 2, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for PR type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNTypePR() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNTypePR();
		Assert.assertTrue(flag);
	}

//	/**
//	 * Subcontract Order to GRN without Gate Entry for UP type - Free pass Items
//	 * 
//	 * @author Vimal - RMITS
//	 */
//	@Author("Vimal-RMITS")
//	@Test(priority = 3, groups = {
//			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for UP type - Free pass Items")
//	public void CreateProductionOrderFromSubcontractorderToGRNUnprocessType() {
//		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessType();
//		Assert.assertTrue(flag);
//	}

	/**
	 * Subcontract Order to GRN without Gate Entry for US type - Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 4, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for US type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNUnprocessStandardType() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessStandardType();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 5, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type- Interstate(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeInterstateUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelling Direct Subcontract Order For SCO Value Addition Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 6, groups = {
			"Subcontract" }, description = "Cancelling Direct Subcontract Order For SCO Value Addition Type")
	public void DocumentCancellingForDirectSubcontractOrderValueAdditionType() {
		SubcontractFunc.documentCancellingForDirectSubcontractOrderValueAdditionType();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelling Direct Subcontract Order For Standard Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 7, groups = {
			"Subcontract" }, description = "Cancelling Direct Subcontract Order For Standard Type")
	public void DocumentCancellingForDirectSubcontractOrderStandardType() {
		SubcontractFunc.documentCancellingForDirectSubcontractOrderStandardType();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Direct Subcontract Order For Standard Type In Conversion Factor-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 8, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor- Interstate(Unregistered)")
	public void CreateDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorInterstateUnregistered() {
		flag = SubcontractFunc
				.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 9, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-Local(Registered)")
	public void CreateSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeLocalRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 10, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref- Interstate(Unregistered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateUnregistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 11, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-Local(Registered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefLocalRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor- Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 12, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor- Interstate(Unregistered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorInterstateUnregistered() {
		flag = SubcontractFunc
				.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 13, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-Local(Registered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefandConvFactorLocalRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 14, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor- Interstate(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorInterstateUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref- Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 15, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref- Interstate(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefInterstateUnregistered() {
		flag = SubcontractFunc
				.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with Project-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 16, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project- Interstate(Unregistered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeWithProjectInterstateUnregistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Project-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 17, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-Local(Registered)")
	public void CreateSubcontractOrderFromPendingWithProjectLocalRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithProjectLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Subcontract Order to GRN without Gate Entry for CS type - Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 18, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for CS type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNCutSampleType() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNCutSampleType();
		Assert.assertTrue(flag);
	}

	/**
	 * Subcontract Order to GRN without Gate Entry for PR type - QC Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 19, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for PR type - QC Enabled Items")
	public void CreateProductionOrderFromSubcontractorderToGRNTypePRWithQC() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNTypePRWithQC();
		Assert.assertTrue(flag);
	}

	/**
	 * Subcontract Order to GRN without Gate Entry for UP type - QC Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 20, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for UP type - QC Enabled Items")
	public void CreateProductionOrderFromSubcontractorderToGRNUnprocessTypeWithQC() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessTypeWithQC();
		Assert.assertTrue(flag);
	}

	/**
	 * Subcontract Order to GRN without Gate Entry for US type - QC Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 21, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for US type - QC Enabled Items")
	public void CreateProductionOrderFromSubcontractorderToGRNUnprocessStandardTypeWithQC() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessStandardTypeWithQC();
		Assert.assertTrue(flag);
	}

	/**
	 * Create subcontract orders from pending Production Queue
	 * (OS)-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 22, groups = {
			"Subcontract" }, description = "Create subcontract orders from pending Production Queue (OS)-Local(Registered)")
	public void CreateProductionOrderFromPendingSubcontractorderLocalRegistered() {
		flag = SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create serial Item subcontract orders from pending Production Queue
	 * (OS)-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 86, groups = {
			"Subcontract" }, description = "Create serial Item subcontract orders from pending Production Queue (OS)-Local(Registered)")
	public void CreateProductionOrderFromPendingSerialItemSubcontractorderLocalRegistered() {
		flag = SubcontractFunc.createProductionOrderFromPendingSerialItemSubcontractorderLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 87, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type- Interstate(Unregistered)")
	public void CreateDirectSerialItemSubcontractOrderWithBOMForStandardTypeInterstateUnregistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 88, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- Interstate(Unregistered)")
	public void CreateDirectSerialItemSubcontractOrderForValueAdditionTypeInterstateUnregistered() {
		flag = SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelling Serial Item Direct Subcontract Order For Standard Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 89, groups = {
			"Subcontract" }, description = "Cancelling Serial Item Direct Subcontract Order For Standard Type")
	public void DocumentCancellingForDirectSerialItemSubcontractOrderStandardType() {
		SubcontractFunc.documentCancellingForDirectSerialItemSubcontractOrderStandardType();
	}

	/**
	 * Cancelling Serial Item Direct Subcontract Order For SCO Value Addition Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 90, groups = {
			"Subcontract" }, description = "Cancelling Serial Item Direct Subcontract Order For SCO Value Addition Type")
	public void DocumentCancellingForDirectSerialItemSubcontractOrderValueAdditionType() {
		SubcontractFunc.documentCancellingForDirectSerialItemSubcontractOrderValueAdditionType();
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Sales Order
	 * Ref-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 91, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Sales Order Ref-Local(Registered)")
	public void CreateSerialItemSubcontractOrderFromPendingWithSalesOrderRefLocalRegistered() {
		flag = SubcontractFunc.createSerialItemSubcontractOrderFromPendingWithSalesOrderRefLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 92, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- Interstate(Unregistered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateUnregistered() {
		flag = SubcontractFunc
				.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type with Sales
	 * Order Ref- Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 93, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type with Sales Order Ref- Interstate(Unregistered)")
	public void CreateDirectSerialItemSubcontractOrderForValueAdditionTypeWithSOrefInterstateUnregistered() {
		flag = SubcontractFunc
				.createDirectSerialItemSubcontractOrderForValueAdditionTypeWithSOrefInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with Project-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 94, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project- Interstate(Unregistered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectInterstateUnregistered() {
		flag = SubcontractFunc
				.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With
	 * Project-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 95, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Project-Local(Registered)")
	public void CreateSerialitemSubcontractOrderFromPendingWithProjectLocalRegistered() {
		flag = SubcontractFunc.createSerialitemSubcontractOrderFromPendingWithProjectLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 23, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type-Local(Registered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeLocalRegistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Direct Subcontract Order For Standard Type In Conversion
	 * Factor-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 24, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor-Local(Registered)")
	public void CreateDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorLocalRegistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order
	 * Ref-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 25, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-Local(Registered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalRegistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalRegistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 26, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor-Local(Registered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorLocalRegistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorLocalRegistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv
	 * Factor-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 27, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor-Local(Registered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorLocalRegistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorLocalRegistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 28, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref-Local(Registered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefLocalRegistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefLocalRegistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with
	 * Project-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 29, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project-Local(Registered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeWithProjectLocalRegistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectLocalRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 30, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-Interstate(Unregistered)")
	public void CreateSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeInterstateUnRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeInterstateUnRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order
	 * Ref-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 31, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-Interstate(Unregistered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefInterstateUnregistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefInterstateUnregistered();
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 32, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-Interstate(Unregistered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefandConvFactorInterstateUnregistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorInterstateUnregistered();
	}

	/**
	 * Create Subcontract Order From Pending With Project-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 33, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-Interstate(Unregistered)")
	public void CreateSubcontractOrderFromPendingWithProjectInterstateUnregistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithProjectInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 34, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type-Local(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeLocalUnregistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeLocalUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Direct Subcontract Order For Standard Type In Conversion
	 * Factor-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 35, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor-Local(Unregistered)")
	public void CreateDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorLocalUnRegistered() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order
	 * Ref-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 36, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-Local(Unregistered)")
	public void CirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalUnRegistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 37, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor- Interstate(Unregistered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorLocalUnRegistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorLocalUnRegistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv
	 * Factor-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 38, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor-Local(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorLocalUnRegistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorLocalUnRegistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 39, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref-Local(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefLocalUnRegistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefLocalUnRegistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with
	 * Project-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 40, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project-Local(Unregistered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeWithProjectLocalUnRegistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectLocalUnRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 41, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-Interstate(Registered)")
	public void CreateSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeInterstateRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeInterstateRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order
	 * Ref-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 42, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-Interstate(Registered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefInterstateRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefInterstateRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 43, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-Interstate(Registered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefandConvFactorInterstateRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorInterstateRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Project-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 44, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-Interstate(Registered)")
	public void CreateSubcontractOrderFromPendingWithProjectInterstateRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithProjectInterstateRegistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type - UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 45, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type - UTGST(Unregistered)")
	public void CreateDirectSubcontractOrderWithBOMForStandardTypeUTGSTUnregistered() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUTGSTUnregistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type- UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 46, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type- UTGST(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeUTGSTUnregistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeUTGSTUnregistered();
	}

	/**
	 * Create Direct Subcontract Order For Standard Type In Conversion Factor-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 47, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor- UTGST(Unregistered)")
	public void CreateDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorUTGSTUnregistered() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorUTGSTUnregistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 48, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref- UTGST(Unregistered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTUnregistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTUnregistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 49, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor-UTGST(Unregistered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorUTGSTUnregistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorUTGSTUnregistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 50, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor- UTGST(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorUTGSTUnregistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorUTGSTUnregistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref- UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 51, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref- UTGST(Unregistered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefUTGSTUnregistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefUTGSTUnregistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with Project-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 52, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project- UTGST(Unregistered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTUnregistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTUnregistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type - UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 53, groups = {
			"Subcontract" }, description = " Direct Subcontract Order With BOM For Standard Type - UTGST(Registered)")
	public void CreateDirectSubcontractOrderWithBOMForStandardTypeUTGSTregistered() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUTGSTregistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type- UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 54, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type- UTGST(Registered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeUTGSTregistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeUTGSTregistered();
	}

	/**
	 * Create Direct Subcontract Order For Standard Type In Conversion Factor-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 55, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor- UTGST(Registered)")
	public void CreateDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorUTGSTregistered() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorUTGSTregistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 56, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref- UTGST(Registered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTregistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTregistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 57, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor-UTGST(Registered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorUTGSTregistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorUTGSTregistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 58, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor- UTGST(Registered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorUTGSTregistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorUTGSTregistered();
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref- UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 59, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref- UTGST(Registered)")
	public void CreateDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefUTGSTregistered() {
		SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefUTGSTregistered();
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with Project-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 60, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project- UTGST(Registered)")
	public void DirectSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTregistered() {
		SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTregistered();
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 61, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-UTGST(Registered)")
	public void CreateSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeUTGSTregistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeUTGSTregistered();
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 62, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-UTGST(Registered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefUTGSTregistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefUTGSTregistered();
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 63, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-UTGST(Registered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefandConvFactorUTGSTRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorUTGSTRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Project-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 64, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-UTGST(Registered)")
	public void CreateSubcontractOrderFromPendingWithProjectUTGSTRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithProjectUTGSTRegistered();
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Sales Order
	 * Ref-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 96, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Sales Order Ref-UTGST(Registered)")
	public void CreateSerialItemSubcontractOrderFromPendingWithSalesOrderRefUTGSTRegistered() {
		SubcontractFunc.createSerialItemSubcontractOrderFromPendingWithSalesOrderRefUTGSTRegistered();
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
