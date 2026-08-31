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

public class SubcontractTransactionTest2 extends RoadMapERPTest {

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
	 * Create Serial Item Subcontract Order From Pending With
	 * Project-UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 97, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Project-UTGST(Registered)")
	public void CreateSerialitemSubcontractOrderFromPendingWithProjectUTGSTRegistered() {
		SubcontractFunc.createSerialitemSubcontractOrderFromPendingWithProjectUTGSTRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR
	 * Type-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 65, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type-UTGST(Unregistered)")
	public void CreateSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeUTGSTUnRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRTypeUTGSTUnRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order
	 * Ref-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 66, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref-UTGST(Unregistered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefUTGSTUnregistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefUTGSTUnregistered();
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv
	 * Factor-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 67, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor-UTGST(Unregistered)")
	public void CreateSubcontractOrderFromPendingWithSalesOrderRefandConvFactorUTGSTUnRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactorUTGSTUnRegistered();
	}

	/**
	 * Create Subcontract Order From Pending With Project-UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 68, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Project-UTGST(Unregistered)")
	public void CreateSubcontractOrderFromPendingWithProjectUTGSTUnRegistered() {
		SubcontractFunc.createSubcontractOrderFromPendingWithProjectUTGSTUnRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 98, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - UTGST(Unregistered)")
	public void CreateDirectSerialItemSubcontractOrderWithBOMForStandardTypeUTGSTUnregistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeUTGSTUnregistered();
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 99, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- UTGST(Unregistered)")
	public void CreateDirectSerialItemSubcontractOrderForValueAdditionTypeUTGSTUnregistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeUTGSTUnregistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 100, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- UTGST(Unregistered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTUnregistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTUnregistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with Project-
	 * UTGST(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 101, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project- UTGST(Unregistered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTUnregistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTUnregistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 102, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - UTGST(Registered)")
	public void CreateDirectSerialItemSubcontractOrderWithBOMForStandardTypeUTGSTRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeUTGSTRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 103, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- UTGST(Registered)")
	public void CreateDirectSerialItemSubcontractOrderForValueAdditionTypeUTGSTRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeUTGSTRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 104, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- UTGST(Registered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefUTGSTRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with Project-
	 * UTGST(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 105, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project- UTGST(Registered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectUTGSTRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 106, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - Local(Registered)")
	public void CreateDirectSerialItemSubcontractOrderWithBOMForStandardTypeLocalRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeLocalRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 107, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- Local(Registered)")
	public void CreateDirectSerialItemSubcontractOrderForValueAdditionTypeLocalRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeLocalRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 108, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- Local(Registered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with
	 * Project-Local(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 109, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project-Local(Registered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectLocalRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectLocalRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 110, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - Local(Unregistered)")
	public void CreateDirectSerialItemSubcontractOrderWithBOMForStandardTypeLocalUnRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 111, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- Local(Unregistered)")
	public void CreateDirectSerialItemSubcontractOrderForValueAdditionTypeLocalUnRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeLocalUnRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref- Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 112, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- Local(Unregistered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalUnRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefLocalUnRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with
	 * Project-Local(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 113, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref- Local(Unregistered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectLocalUnRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectLocalUnRegistered();
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Sales Order
	 * Ref-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 114, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Sales Order Ref-Interstate(Unregistered)")
	public void CreateSerialItemSubcontractOrderFromPendingWithSalesOrderRefInterstateUnRegistered() {
		SubcontractFunc.createSerialItemSubcontractOrderFromPendingWithSalesOrderRefInterstateUnRegistered();
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With
	 * Project-Interstate(Unregistered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 115, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Project-Interstate(Unregistered)")
	public void CreateSerialitemSubcontractOrderFromPendingWithProjectInterstateUnRegistered() {
		SubcontractFunc.createSerialitemSubcontractOrderFromPendingWithProjectInterstateUnRegistered();
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Sales Order Ref-
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 116, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Sales Order Ref- Interstate(Registered)")
	public void CreateSerialItemSubcontractOrderFromPendingWithSalesOrderRefInterstateRegistered() {
		SubcontractFunc.createSerialItemSubcontractOrderFromPendingWithSalesOrderRefInterstateRegistered();
	}

	/**
	 * Create Serial Item Subcontract Order From Pending With Project-
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 117, groups = {
			"Subcontract" }, description = "Create Serial Item Subcontract Order From Pending With Project- Interstate(Registered)")
	public void CreateSerialitemSubcontractOrderFromPendingWithProjectInterstateRegistered() {
		SubcontractFunc.createSerialitemSubcontractOrderFromPendingWithProjectInterstateRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type -
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 118, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type - Interstate(Registered)")
	public void CreateDirectSerialItemSubcontractOrderWithBOMForStandardTypeInterstateRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order For SCO Value Addition Type-
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 119, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order For SCO Value Addition Type- Interstate(Registered)")
	public void CreateDirectSerialItemSubcontractOrderForValueAdditionTypeInterstateRegistered() {
		SubcontractFunc.createDirectSerialItemSubcontractOrderForValueAdditionTypeInterstateRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type In Sales
	 * Order Ref-Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 120, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type In Sales Order Ref-Interstate(Registered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeInSalesOrderRefInterstateRegistered();
	}

	/**
	 * Direct Serial Item Subcontract Order With BOM For Standard Type with Project-
	 * Interstate(Registered)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 121, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order With BOM For Standard Type with Project- Interstate(Registered)")
	public void DirectSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectInterstateRegistered() {
		SubcontractFunc.directSerialItemSubcontractOrderWithBOMForStandardTypeWithProjectInterstateRegistered();
	}

	/**
	 * Pending Subcontract Order to GRN With Gate Entry for PR type - Free pass
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 69, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN With Gate Entry for PR type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNWithGateEntryTypePR() {
		SubcontractFunc.createProductionOrderFromSubcontractorderToGRNWithGateEntryTypePR();
	}

	/**
	 * Pending Subcontract Order to GRN With Gate Entry for UP type - Free pass
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 70, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN With Gate Entry for UP type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNWithGateEntryTypeUP() {
		SubcontractFunc.createProductionOrderFromSubcontractorderToGRNWithGateEntryTypeUP();
	}

	/**
	 * Pending Subcontract Order to GRN With Gate Entry for US type - Free pass
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 71, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN With Gate Entry for US type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNWithGateEntryTypeUS() {
		SubcontractFunc.createProductionOrderFromSubcontractorderToGRNWithGateEntryTypeUS();
	}

	/**
	 * Pending Subcontract Order to GRN With Gate Entry for CS type - Free pass
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 72, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN With Gate Entry for CS type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNWithGateEntryTypeCS() {
		SubcontractFunc.createProductionOrderFromSubcontractorderToGRNWithGateEntryTypeCS();
	}

	/**
	 * Pending Subcontract Order to GRN With Gate Entry for PR type - QC Enabled
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 73, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN With Gate Entry for PR type - QC Enabled Items")
	public void CreateProductionOrderFromSubcontractorderToGRNQCEnableWithGateEntryTypePR() {
		SubcontractFunc.createProductionOrderFromSubcontractorderToGRNQCEnableWithGateEntryTypePR();
	}

	/**
	 * Pending Subcontract Order to GRN With Gate Entry for UP type - QC Enabled
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 74, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN With Gate Entry for UP type - QC Enabled Items")
	public void CreateProductionOrderFromSubcontractorderToGRNWithQCEnableGateEntryTypeUP() {
		SubcontractFunc.createProductionOrderFromSubcontractorderToGRNWithQCEnableGateEntryTypeUP();
	}

	/**
	 * Pending Subcontract Order to GRN With Gate Entry for US type - QC Enabled
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 75, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN With Gate Entry for US type - QC Enabled Items")
	public void CreateProductionOrderFromSubcontractorderToGRNQCEnableWithGateEntryTypeUS() {
		SubcontractFunc.createProductionOrderFromSubcontractorderToGRNQCEnableWithGateEntryTypeUS();
	}

	/**
	 * Linewise Cancel The Subcontract GRN
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 76, groups = { "Subcontract" }, description = "Linewise Cancel The Subcontract GRN")
	public void LineWiseCancelTheSubcontractGRN() {
		SubcontractFunc.lineWiseCancelTheSubcontractGRN();
	}

	/**
	 * Cancel The Subcontract GRN Document
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 77, groups = { "Subcontract" }, description = "Cancel The Subcontract GRN Document")
	public void HeaderWiseCancelTheSubcontractGRN() {
		SubcontractFunc.headerWiseCancelTheSubcontractGRN();
	}

	/**
	 * Direct Subcontract Order to GRN without Gate Entry for PR type - Free pass
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 78, groups = {
			"Subcontract" }, description = "Direct Subcontract Order to GRN without Gate Entry for PR type - Free pass Items")
	public void DirectSubcontractorderToGRNTypePR() {
		SubcontractFunc.directSubcontractorderToGRNTypePR();
	}

	/**
	 * Direct Subcontract Order to GRN without Gate Entry for CS type - Free pass
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 79, groups = {
			"Subcontract" }, description = "Direct Subcontract Order to GRN without Gate Entry for CS type - Free pass Items")
	public void DirectSubcontractorderToGRNTypeCS() {
		SubcontractFunc.directSubcontractorderToGRNTypeCS();
	}

	/**
	 * Direct Subcontract Order to GRN With Gate Entry for PR type - Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 80, groups = {
			"Subcontract" }, description = "Direct  Subcontract Order to GRN With Gate Entry for PR type - Free pass Items")
	public void DirectSubcontractorderToGRNWithGateEntryTypePR() {
		SubcontractFunc.directSubcontractorderToGRNWithGateEntryTypePR();
	}

	/**
	 * Direct Subcontract Order to GRN With Gate Entry for CS type - Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 81, groups = {
			"Subcontract" }, description = "Direct  Subcontract Order to GRN With Gate Entry for CS type - Free pass Items")
	public void DirectSubcontractorderToGRNWithGateEntryTypeCS() {
		SubcontractFunc.directSubcontractorderToGRNWithGateEntryTypeCS();
	}

	/**
	 * Direct Subcontract Order to GRN without Gate Entry for PR type - QC Enabled
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 82, groups = {
			"Subcontract" }, description = "Direct  Subcontract Order to GRN without Gate Entry for PR type - QC Enabled Items")
	public void DirectSubcontractorderToGRNWithQCTypePR() {
		SubcontractFunc.directSubcontractorderToGRNWithQCTypePR();
	}

	/**
	 * Direct Subcontract Order to GRN With Gate Entry for PR type - QC Enabled
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 83, groups = {
			"Subcontract" }, description = "Direct  Subcontract Order to GRN With Gate Entry for PR type - QC Enabled Items")
	public void DirectSubcontractorderToGRNWithQCEnableGateEntryTypePR() {
		SubcontractFunc.directSubcontractorderToGRNWithQCEnableGateEntryTypePR();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN without Gate Entry for PR type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 122, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN without Gate Entry for PR type - Free pass Items")
	public void PendingSerialSubcontractOrderToGRNWithOutGateEntryPRtype() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithOutGateEntryPRtype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN without Gate Entry for UP type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 123, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN without Gate Entry for UP type - Free pass Items")
	public void PendingSerialSubcontractOrderToGRNWithOutGateEntryUPtype() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithOutGateEntryUPtype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN without Gate Entry for US type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 124, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN without Gate Entry for US type - Free pass Items")
	public void PendingSerialSubcontractOrderToGRNWithOutGateEntryUStype() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithOutGateEntryUStype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN without Gate Entry for CS type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 125, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN without Gate Entry for CS type - Free pass Items")
	public void PendingSerialSubcontractOrderToGRNWithOutGateEntryCStype() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithOutGateEntryCStype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN With Gate Entry for PR type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 126, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN With Gate Entry for PR type - Free pass Items")
	public void PendingSerialSubcontractOrderToGRNWithGateEntryPRtype() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithGateEntryPRtype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN With Gate Entry for PR type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 127, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN With Gate Entry for UP type - Free pass Items")
	public void PendingSerialSubcontractOrderToGRNWithGateEntryUPtype() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithGateEntryUPtype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN With Gate Entry for US type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 128, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN With Gate Entry for US type - Free pass Items")
	public void PendingSerialSubcontractOrderToGRNWithGateEntryUStype() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithGateEntryUStype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN With Gate Entry for CS type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 129, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN With Gate Entry for CS type - Free pass Items")
	public void PendingSerialSubcontractOrderToGRNWithGateEntryCStype() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithGateEntryCStype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN without Gate Entry for PR type -
	 * QC Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 130, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN without Gate Entry for PR type - QC Enabled Items")
	public void PendingSerialSubcontractOrderToGRNWithOutGateEntryPRtypeQCEnable() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithOutGateEntryPRtypeQCEnable();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN without Gate Entry for UP type -
	 * QC Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 131, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN without Gate Entry for UP type - QC Enabled Items")
	public void PendingSerialSubcontractOrderToGRNWithOutGateEntryUPtypeQCEnable() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithOutGateEntryUPtypeQCEnable();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN without Gate Entry for CS type -
	 * QC Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 132, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN without Gate Entry for CS type - QC Enabled Items")
	public void PendingSerialSubcontractOrderToGRNWithOutGateEntryCStypeQCEnable() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithOutGateEntryCStypeQCEnable();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN With Gate Entry for PR type - QC
	 * Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 133, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN With Gate Entry for PR type - QC Enabled Items")
	public void PendingSerialSubcontractOrderToGRNWithGateEntryPRtypeQCenable() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithGateEntryPRtypeQCenable();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN With Gate Entry for UP type - QC
	 * Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 134, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN With Gate Entry for UP type - QC Enabled Items")
	public void PendingSerialSubcontractOrderToGRNWithGateEntryUPtypeQCenable() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithGateEntryUPtypeQCenable();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN With Gate Entry for US type - QC
	 * Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 135, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN With Gate Entry for US type - QC Enabled Items")
	public void PendingSerialSubcontractOrderToGRNWithGateEntryUStypeQCenable() {
		SubcontractFunc.pendingSerialSubcontractOrderToGRNWithGateEntryUStypeQCenable();
	}

	/**
	 * Linewise Serial Item Cancel The Subcontract GRN
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 136, groups = { "Subcontract" }, description = "Linewise Serial Item Cancel The Subcontract GRN")
	public void LineWiseSerialCancelTheSubcontractGRN() {
		SubcontractFunc.lineWiseSerialCancelTheSubcontractGRN();
	}

	/**
	 * Cancel The Serial Item Subcontract GRN Document
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 85, groups = { "Subcontract" }, description = "Cancel The Serial Item Subcontract GRN Document")
	public void HeaderWiseSerialCancelTheSubcontractGRN() {
		SubcontractFunc.headerWiseSerialCancelTheSubcontractGRN();
	}

	/**
	 * Direct Serial Item Subcontract Order to GRN without Gate Entry for PR type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 137, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order to GRN without Gate Entry for PR type - Free pass Items")
	public void DirectSerialItemSubcontractOrdertoGRNwithoutGateEntryforPRtype() {
		SubcontractFunc.directSerialItemSubcontractOrdertoGRNwithoutGateEntryforPRtype();
	}

	/**
	 * Direct Serial Item Subcontract Order to GRN without Gate Entry for CS type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 138, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order to GRN without Gate Entry for CS type - Free pass Items")
	public void DirectSerialItemSubcontractOrdertoGRNwithoutGateEntryforCStype() {
		SubcontractFunc.directSerialItemSubcontractOrdertoGRNwithoutGateEntryforCStype();
	}

	/**
	 * Direct Serial Item Subcontract Order to GRN with Gate Entry for PR type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 139, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order to GRN with Gate Entry for PR type - Free pass Items")
	public void DirectSerialItemSubcontractOrdertoGRNwithGateEntryforPRtype() {
		SubcontractFunc.directSerialItemSubcontractOrdertoGRNwithGateEntryforPRtype();
	}

	/**
	 * Direct Serial Item Subcontract Order to GRN with Gate Entry for CS type -
	 * Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 140, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order to GRN with Gate Entry for CS type - Free pass Items")
	public void DirectSerialItemSubcontractOrdertoGRNwithGateEntryforCStype() {
		SubcontractFunc.directSerialItemSubcontractOrdertoGRNwithGateEntryforCStype();
	}

	/**
	 * Direct Serial Item Subcontract Order to GRN without Gate Entry for PR type -
	 * QC Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 141, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order to GRN without Gate Entry for PR type - QC Enabled Items")
	public void DirectSerialItemSubcontractOrdertoGRNwithoutGateEntryforPRtypeQCenable() {
		SubcontractFunc.directSerialItemSubcontractOrdertoGRNwithoutGateEntryforPRtypeQCenable();
	}

	/**
	 * Direct Serial Item Subcontract Order to GRN with Gate Entry for PR type - QC
	 * Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 142, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order to GRN with Gate Entry for PR type - QC Enabled Items")
	public void DirectSerialItemSubcontractOrdertoGRNwithGateEntryforPRtypeQCenable() {
		SubcontractFunc.directSerialItemSubcontractOrdertoGRNwithGateEntryforPRtypeQCenable();
	}

	/**
	 * Standard SCO Type Changing Batch Qty And Post
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 143, groups = { "Subcontract" }, description = "Standard SCO Type Changing Batch Qty And Post")
	public void StandardSCOTypeChangingBatchQtyAndPost() {
		SubcontractFunc.standardSCOTypeChangingBatchQtyAndPost();
	}

	/**
	 * Standard SCO Type Changing Batch Cost And Post
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 144, groups = { "Subcontract" }, description = "Standard SCO Type Changing Batch Cost And Post")
	public void StandardSCOTypeChangingBatchCostAndPost() {
		SubcontractFunc.standardSCOTypeChangingBatchCostAndPost();
	}

	/**
	 * Standard SCO Type Changing Batch Qty, Batch Cost, SAC Code And Post
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 145, groups = {
			"Subcontract" }, description = "Standard SCO Type Changing Batch Qty, Batch Cost, SAC Code And Post")
	public void StandardSCOTypeChangingBatchQtyBatchCostAndPost() {
		SubcontractFunc.standardSCOTypeChangingBatchQtyBatchCostAndPost();
	}

	/**
	 * Value Addition Type Changing Batch Qty And Post
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 146, groups = { "Subcontract" }, description = "Value Addition Type Changing Batch Qty And Post")
	public void ValueAdditionSCOTypeChangingBatchQtyAndPost() {
		SubcontractFunc.valueAdditionSCOTypeChangingBatchQtyAndPost();
	}

	/**
	 * Value Addition Type Changing Batch Cost And Post
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 147, groups = { "Subcontract" }, description = "Value Addition Type Changing Batch Cost And Post")
	public void ValueAdditionSCOTypeChangingBatchcostAndPost() {
		SubcontractFunc.valueAdditionSCOTypeChangingBatchcostAndPost();
	}

	/**
	 * SCO Value Addition Type Changing Batch Qty, Batch Cost, SAC Code And Post
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 148, groups = {
			"Subcontract" }, description = "SCO Value Addition Type Changing Batch Qty, Batch Cost, SAC Code And Post")
	public void ValueAdditionSCOTypeChangingBatchQtyBatchcostAndPost() {
		SubcontractFunc.valueAdditionSCOTypeChangingBatchQtyBatchcostAndPost();
	}

	/**
	 * Standard SCO Type Changing Batch Qty, Batch Cost And CloseShort The Line
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 149, groups = {
			"Subcontract" }, description = "Standard SCO Type Changing Batch Qty, Batch Cost And CloseShort The Line")
	public void StandardSCOTypeChangingBatchQtyBatchCostCloseShortAndPost() {
		SubcontractFunc.standardSCOTypeChangingBatchQtyBatchCostCloseShortAndPost();
	}

	/**
	 * SCO Value Addition Type Changing Batch Qty, Batch Cost And CloseShort The
	 * Line
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 150, groups = {
			"Subcontract" }, description = "SCO Value Addition Type Changing Batch Qty, Batch Cost And CloseShort The Line")
	public void ValueAdditionSCOTypeChangingBatchQtyBatchcostCloseShortAndPost() {
		SubcontractFunc.valueAdditionSCOTypeChangingBatchQtyBatchcostCloseShortAndPost();
	}

	/**
	 * Cancel The Subcontract Amendment Document
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 151, groups = { "Subcontract" }, description = "Cancel The Subcontract Amendment Document")
	public void CancelTheSCOCloseShortDocument() {
		SubcontractFunc.cancelTheSCOCloseShortDocument();
	}

	/**
	 * Create Direct GRN With Subcontract Referance
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 152, groups = { "Subcontract" }, description = "Create Direct GRN With Subcontract Referance")
	public void CreateDirectGRNWithSubcontractReferance() {
		SubcontractFunc.createDirectGRNWithSubcontractReferance();
	}

	/**
	 * Create Rework Subcontract Order For Standard Rework Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 153, groups = {
			"Subcontract" }, description = "Create Rework Subcontract Order For Standard Rework Type")
	public void CreateReworkSubcontractOrderForStandardReworkType() {
		SubcontractFunc.createReworkSubcontractOrderForStandardReworkType();
	}

	/**
	 * How to create Repair - rework order for Subcontract Primary rejection?
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 154, groups = {
			"Subcontract" }, description = "How to create Repair - rework order for Subcontract Primary rejection?")
	public void CreateRepairreworkorderforSubcontractPrimaryrejection() {
		SubcontractFunc.createRepairreworkorderforSubcontractPrimaryrejection();
	}

	/**
	 * How to create Scrap - rework order for Subcontract Primary rejection?
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 155, groups = {
			"Subcontract" }, description = "How to create Scrap - rework order for Subcontract Primary rejection?")
	public void CreateScrapreworkorderforSubcontractPrimaryrejection() {
		SubcontractFunc.createScrapreworkorderforSubcontractPrimaryrejection();
	}

	/**
	 * Create Rework Subcontract Order For Value Addition Rework Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 156, groups = {
			"Subcontract" }, description = "Create Rework Subcontract Order For Value Addition Rework Type")
	public void CreateReworkSubcontractOrderForValueAdditionReworkType() {
		SubcontractFunc.createReworkSubcontractOrderForValueAdditionReworkType();
	}

	/**
	 * Pending Subcontract Order to GRN without Gate Entry for SDS type - Free pass
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 157, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN without Gate Entry for SDS type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNSDSType() {
		SubcontractFunc.createProductionOrderFromSubcontractorderToGRNSDSType();
	}

	/**
	 * Pending Subcontract Order to GRN without Gate Entry for SDS type - Free pass
	 * Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 158, groups = {
			"Subcontract" }, description = "Pending Subcontract Order to GRN without Gate Entry for SDS type - Free pass Items")
	public void PendingSubcontractOrdertoGRNwithGateEntryforSDStype() {
		SubcontractFunc.pendingSubcontractOrdertoGRNwithGateEntryforSDStype();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN without Gate Entry for SDS type
	 * - QC Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 159, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN without Gate Entry for SDS type - QC Items")
	public void PendingSerialItemSubcontractOrdertoGRNwithoutGateEntryforPRtypeQCItems() {
		SubcontractFunc.pendingSerialItemSubcontractOrdertoGRNwithoutGateEntryforPRtypeQCItems();
	}

	/**
	 * Pending Serial Item Subcontract Order to GRN with Gate Entry for SDS type -
	 * QC Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 160, groups = {
			"Subcontract" }, description = "Pending Serial Item Subcontract Order to GRN with Gate Entry for SDS type - QC Items")
	public void PendingSerialSubcontractOrdertoGRNwithGateEntryforSDStype() {
		SubcontractFunc.pendingSerialSubcontractOrdertoGRNwithGateEntryforSDStype();
	}

	/**
	 * Create Serial Item Direct GRN With Subcontract Referance
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 161, groups = {
			"Subcontract" }, description = "Create Serial Item Direct GRN With Subcontract Referance")
	public void createDirectSerialGRNWithSubcontractReferance() {
		SubcontractFunc.createDirectSerialGRNWithSubcontractReferance();
	}

	/**
	 * Direct Subcontract Order to GRN without Gate Entry for SDS type - QC Item
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 162, groups = {
			"Subcontract" }, description = "Direct Subcontract Order to GRN without Gate Entry for SDS type - QC Item")
	public void DirectSubcontractOrdertoGRNwithoutGateEntryforSDStypeQCItem() {
		SubcontractFunc.directSubcontractOrdertoGRNwithoutGateEntryforSDStypeQCItem();
	}

	/**
	 * Direct Serial Item Subcontract Order to GRN With Gate Entry for SDS type - QC
	 * Enabled Items
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 163, groups = {
			"Subcontract" }, description = "Direct Serial Item Subcontract Order to GRN With Gate Entry for SDS type - QC Enabled Items")
	public void directSerialItemSubcontractOrdertoGRNwithGateEntryforSDStypeQCenable() {
		SubcontractFunc.directSerialItemSubcontractOrdertoGRNwithGateEntryforSDStypeQCenable();
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
