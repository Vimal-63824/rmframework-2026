package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Oct 16, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import utils.WebDriverBase;

public class SubcontractTransactionTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SubcontractOrderFunctions SubcontractFunc = new SubcontractOrderFunctions();

	/**
	 * Direct Subcontract Order With BOM For Standard Type -
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 1, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type - Interstate(Unregistered)")
	public void createDirectSubcontractOrderWithBOMForStandardTypeInterstateUnregistered() {
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

	/**
	 * Subcontract Order to GRN without Gate Entry for UP type - Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 3, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for UP type - Free pass Items")
	public void createProductionOrderFromSubcontractorderToGRNUnprocessType() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessType();
		Assert.assertTrue(flag);
	}
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
	public void createDirectSubcontractOrderForValueAdditionTypeInterstateUnregistered() {
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
	public void documentCancellingForDirectSubcontractOrderValueAdditionType() {
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
	public void documentCancellingForDirectSubcontractOrderStandardType() {
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
	public void createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorInterstateUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorInterstateUnregistered();
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
	public void createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeLocalRegistered() {
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
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateUnregistered() {
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
	public void createSubcontractOrderFromPendingWithSalesOrderRefLocalRegistered() {
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
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorInterstateUnregistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorInterstateUnregistered();
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
	public void createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorLocalRegistered() {
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
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorInterstateUnregistered() {
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
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefInterstateUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefInterstateUnregistered();
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
	public void directSubcontractOrderWithBOMForStandardTypeWithProjectInterstateUnregistered() {
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
	public void createSubcontractOrderFromPendingWithProjectLocalRegistered() {
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
	public void createProductionOrderFromSubcontractorderToGRNCutSampleType() {
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
	public void createProductionOrderFromSubcontractorderToGRNTypePRWithQC() {
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
	public void createProductionOrderFromSubcontractorderToGRNUnprocessTypeWithQC() {
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
	public void createProductionOrderFromSubcontractorderToGRNUnprocessStandardTypeWithQC() {
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
	public void createProductionOrderFromPendingSubcontractorderLocalRegistered() {
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
	@Test(priority = 23, groups = {
			"Subcontract" }, description = "Create serial Item subcontract orders from pending Production Queue (OS)-Local(Registered)")
	public void createProductionOrderFromPendingSerialItemSubcontractorderLocalRegistered() {
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
	@Test(priority = 24, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type- Interstate(Unregistered)")
	public void createDirectSerialItemSubcontractOrderWithBOMForStandardTypeInterstateUnregistered() {
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
	@Test(priority = 25, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- Interstate(Unregistered)")
	public void createDirectSerialItemSubcontractOrderForValueAdditionTypeInterstateUnregistered() {
		flag = SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelling Serial Item Direct Subcontract Order For Standard Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 26, groups = {
			"Subcontract" }, description = "Cancelling Serial Item Direct Subcontract Order For Standard Type")
	public void documentCancellingForDirectSerialItemSubcontractOrderStandardType() {
		SubcontractFunc.documentCancellingForDirectSerialItemSubcontractOrderStandardType();
	}

	/**
	 * Cancelling Serial Item Direct Subcontract Order For SCO Value Addition Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 27, groups = {
			"Subcontract" }, description = "Cancelling Serial Item Direct Subcontract Order For SCO Value Addition Type")
	public void documentCancellingForDirectSerialItemSubcontractOrderValueAdditionType() {
		SubcontractFunc.documentCancellingForDirectSerialItemSubcontractOrderValueAdditionType();
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Sales Order
	 * Ref-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 28, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Sales Order Ref-Local(Registered)")
	public void createSerialItemSubcontractOrderFromPendingWithSalesOrderRefLocalRegistered() {
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
	@Test(priority = 29, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- Interstate(Unregistered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateUnregistered() {
		flag = SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type with Sales
	 * Order Ref- Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 30, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type with Sales Order Ref- Interstate(Unregistered)")
	public void createDirectSerialItemSubcontractOrderForValueAdditionTypeWithSOrefInterstateUnregistered() {
		flag = SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeWithSOrefInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with Project-
	 * Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 31, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project- Interstate(Unregistered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectInterstateUnregistered() {
		flag = SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With
	 * Project-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 32, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Project-Local(Registered)")
	public void createSerialitemSubcontractOrderFromPendingWithProjectLocalRegistered() {
		flag = SubcontractFunc.createSerialitemSubcontractOrderFromPendingWithProjectLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 33, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type-Local(Registered)")
	public void createDirectSubcontractOrderForValueAdditionTypeLocalRegistered() {
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
	@Test(priority = 34, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor-Local(Registered)")
	public void createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorLocalRegistered() {
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
	@Test(priority = 35, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-Local(Registered)")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalRegistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 36, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor-Local(Registered)")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorLocalRegistered() {
		flag = SubcontractFunc
				.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv
	 * Factor-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 37, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor-Local(Registered)")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorLocalRegistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 38, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref-Local(Registered)")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefLocalRegistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with
	 * Project-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 39, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project-Local(Registered)")
	public void directSubcontractOrderWithBOMForStandardTypeWithProjectLocalRegistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 40, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-Interstate(Unregistered)")
	public void createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeInterstateUnRegistered() {
		flag = SubcontractFunc
				.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeInterstateUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order
	 * Ref-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 41, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-Interstate(Unregistered)")
	public void createSubcontractOrderFromPendingWithSalesOrderRefInterstateUnregistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 42, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-Interstate(Unregistered)")
	public void createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorInterstateUnregistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorInterstateUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Project-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 43, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-Interstate(Unregistered)")
	public void createSubcontractOrderFromPendingWithProjectInterstateUnregistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithProjectInterstateUnregistered();
		Assert.assertTrue(flag);
	}
	/**
	 * Direct Subcontract Order For SCO Value Addition Type-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 44, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type-Local(Unregistered)")
	public void createDirectSubcontractOrderForValueAdditionTypeLocalUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeLocalUnregistered();
		Assert.assertTrue(flag);
	}
	/**
	 * Create Direct Subcontract Order For Standard Type In Conversion
	 * Factor-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 45, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor-Local(Unregistered)")
	public void createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorLocalUnRegistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorLocalUnRegistered();
		Assert.assertTrue(flag);
	}
	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order
	 * Ref-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 46, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-Local(Unregistered)")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalUnRegistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalUnRegistered();
		Assert.assertTrue(flag);
	}
	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 47, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor- Interstate(Unregistered)")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorLocalUnRegistered() {
		flag = SubcontractFunc
				.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorLocalUnRegistered();
		Assert.assertTrue(flag);
	}
	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv
	 * Factor-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 48, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor-Local(Unregistered)")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorLocalUnRegistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 49, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref-Local(Unregistered)")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefLocalUnRegistered() {
		flag = SubcontractFunc
				.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with
	 * Project-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 50, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project-Local(Unregistered)")
	public void directSubcontractOrderWithBOMForStandardTypeWithProjectLocalUnRegistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 51, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-Interstate(Registered)")
	public void createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeInterstateRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeInterstateRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order
	 * Ref-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 52, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-Interstate(Registered)")
	public void createSubcontractOrderFromPendingWithSalesOrderRefInterstateRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefInterstateRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 53, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-Interstate(Registered)")
	public void createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorInterstateRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorInterstateRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Project-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 54, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-Interstate(Registered)")
	public void createSubcontractOrderFromPendingWithProjectInterstateRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithProjectInterstateRegistered();
		Assert.assertTrue(flag);
	}
	/**
	 * Direct Subcontract Order With BOM For Standard Type - UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 55, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type - UTGST(Unregistered)")
	public void createDirectSubcontractOrderWithBOMForStandardTypeUTGSTUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUTGSTUnregistered();
		Assert.assertTrue(flag);
	}
	/**
	 * Direct Subcontract Order For SCO Value Addition Type- UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 56, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type- UTGST(Unregistered)")
	public void createDirectSubcontractOrderForValueAdditionTypeUTGSTUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Direct Subcontract Order For Standard Type In Conversion Factor-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 57, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor- UTGST(Unregistered)")
	public void createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorUTGSTUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 58, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref- UTGST(Unregistered)")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTUnregistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 59, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor-UTGST(Unregistered)")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorUTGSTUnregistered() {
		flag = SubcontractFunc
				.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 60, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor- UTGST(Unregistered)")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorUTGSTUnregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref- UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 61, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref- UTGST(Unregistered)")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefUTGSTUnregistered() {
		flag = SubcontractFunc
				.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with Project-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 62, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project- UTGST(Unregistered)")
	public void directSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTUnregistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type - UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 63, groups = {
			"Subcontract" }, description = " Direct Subcontract Order With BOM For Standard Type - UTGST(Registered)")
	public void createDirectSubcontractOrderWithBOMForStandardTypeUTGSTregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type- UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 64, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type- UTGST(Registered)")
	public void createDirectSubcontractOrderForValueAdditionTypeUTGSTregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Direct Subcontract Order For Standard Type In Conversion Factor-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 65, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor- UTGST(Registered)")
	public void createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorUTGSTregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactorUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 66, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref- UTGST(Registered)")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTregistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 67, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor-UTGST(Registered)")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorUTGSTregistered() {
		flag = SubcontractFunc
				.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactorUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 68, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor- UTGST(Registered)")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorUTGSTregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref- UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 69, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref- UTGST(Registered)")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefUTGSTregistered() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOrefUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with Project-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 70, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project- UTGST(Registered)")
	public void directSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTregistered() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 71, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-UTGST(Registered)")
	public void createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeUTGSTregistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 72, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-UTGST(Registered)")
	public void createSubcontractOrderFromPendingWithSalesOrderRefUTGSTregistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefUTGSTregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 73, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-UTGST(Registered)")
	public void createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorUTGSTRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorUTGSTRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Project-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 74, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-UTGST(Registered)")
	public void createSubcontractOrderFromPendingWithProjectUTGSTRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithProjectUTGSTRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Sales Order
	 * Ref-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 75, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Sales Order Ref-UTGST(Registered)")
	public void createSerialItemSubcontractOrderFromPendingWithSalesOrderRefUTGSTRegistered() {
		flag = SubcontractFunc.createSerialItemSubcontractOrderFromPendingWithSalesOrderRefUTGSTRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With
	 * Project-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 76, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Project-UTGST(Registered)")
	public void createSerialitemSubcontractOrderFromPendingWithProjectUTGSTRegistered() {
		flag = SubcontractFunc.createSerialitemSubcontractOrderFromPendingWithProjectUTGSTRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 77, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-UTGST(Unregistered)")
	public void createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeUTGSTUnRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeUTGSTUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order
	 * Ref-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 78, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-UTGST(Unregistered)")
	public void createSubcontractOrderFromPendingWithSalesOrderRefUTGSTUnregistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 79, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-UTGST(Unregistered)")
	public void createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorUTGSTUnRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorUTGSTUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Project-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 80, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-UTGST(Unregistered)")
	public void createSubcontractOrderFromPendingWithProjectUTGSTUnRegistered() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithProjectUTGSTUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 81, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - UTGST(Unregistered)")
	public void createDirectSerialItemSubcontractOrderWithBOMForStandardTypeUTGSTUnregistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 82, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- UTGST(Unregistered)")
	public void createDirectSerialItemSubcontractOrderForValueAdditionTypeUTGSTUnregistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 83, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- UTGST(Unregistered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTUnregistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with Project-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 84, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project- UTGST(Unregistered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTUnregistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTUnregistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 85, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - UTGST(Registered)")
	public void createDirectSerialItemSubcontractOrderWithBOMForStandardTypeUTGSTRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeUTGSTRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 86, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- UTGST(Registered)")
	public void createDirectSerialItemSubcontractOrderForValueAdditionTypeUTGSTRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeUTGSTRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 87, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- UTGST(Registered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with Project-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 88, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project- UTGST(Registered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 89, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - Local(Registered)")
	public void createDirectSerialItemSubcontractOrderWithBOMForStandardTypeLocalRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 90, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- Local(Registered)")
	public void createDirectSerialItemSubcontractOrderForValueAdditionTypeLocalRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 91, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- Local(Registered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with
	 * Project-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 92, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project-Local(Registered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectLocalRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectLocalRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 93, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - Local(Unregistered)")
	public void createDirectSerialItemSubcontractOrderWithBOMForStandardTypeLocalUnRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 94, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- Local(Unregistered)")
	public void createDirectSerialItemSubcontractOrderForValueAdditionTypeLocalUnRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 95, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- Local(Unregistered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalUnRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with
	 * Project-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 96, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- Local(Unregistered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectLocalUnRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectLocalUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Sales Order
	 * Ref-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 97, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Sales Order Ref-Interstate(Unregistered)")
	public void createSerialItemSubcontractOrderFromPendingWithSalesOrderRefInterstateUnRegistered() {
		SubcontractFunc.createSerialItemSubcontractOrderFromPendingWithSalesOrderRefInterstateUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With
	 * Project-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 98, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Project-Interstate(Unregistered)")
	public void createSerialitemSubcontractOrderFromPendingWithProjectInterstateUnRegistered() {
		SubcontractFunc.createSerialitemSubcontractOrderFromPendingWithProjectInterstateUnRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Sales Order Ref-
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 99, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Sales Order Ref- Interstate(Registered)")
	public void createSerialItemSubcontractOrderFromPendingWithSalesOrderRefInterstateRegistered() {
		SubcontractFunc.createSerialItemSubcontractOrderFromPendingWithSalesOrderRefInterstateRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Project-
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 100, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Project- Interstate(Registered)")
	public void createSerialitemSubcontractOrderFromPendingWithProjectInterstateRegistered() {
		SubcontractFunc.createSerialitemSubcontractOrderFromPendingWithProjectInterstateRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 101, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - Interstate(Registered)")
	public void createDirectSerialItemSubcontractOrderWithBOMForStandardTypeInterstateRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 102, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- Interstate(Registered)")
	public void createDirectSerialItemSubcontractOrderForValueAdditionTypeInterstateRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeInterstateRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 103, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref-Interstate(Registered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateRegistered();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with Project-
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 104, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project- Interstate(Registered)")
	public void directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectInterstateRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectInterstateRegistered();
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
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
