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
	 * Create Direct Subcontract Order With BOM For Standard Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 1, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order with BOM For Standard Type")
	public void CreateDirectSubcontractOrderWithBOMForStandardType() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardType();
		Assert.assertTrue(flag);
	}
	/**
	 * Subcontract Order to GRN without Gate Entry for PR type - Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
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
	@Test(priority = 4, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for US type - Free pass Items")
	public void CreateProductionOrderFromSubcontractorderToGRNUnprocessStandardType() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessStandardType();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 5, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type")
	public void createDirectSubcontractOrderForValueAdditionType() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionType();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelling Direct Subcontract Order For SCO Value Addition Type
	 * 
	 * @author Vimal - RMITS
	 */
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
	@Test(priority = 7, groups = {
			"Subcontract" }, description = "Cancelling Direct Subcontract Order For Standard Type")
	public void documentCancellingForDirectSubcontractOrderStandardType() {
		SubcontractFunc.documentCancellingForDirectSubcontractOrderStandardType();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Direct Subcontract Order With BOM For Standard Type In Conversion
	 * Factor
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 8, groups = {
			"Subcontract" }, description = "Create Direct Subcontract Order For Standard Type In Conversion Factor")
	public void createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactor() {
		flag = SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactor();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Conversion Factor in GRN PR Type
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 9, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Conversion Factor in GRN PR Type")
	public void createSubcontractOrderFromPendingWithConversionFactorinGRN_PRType() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithConversionFactorinGRN_PRType();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 10, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRef() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRef();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 11, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref")
	public void createSubcontractOrderFromPendingWithSalesOrderRef() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRef();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and
	 * Conv Factor
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 12, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type In Sales Order Ref and Conv Factor")
	public void directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactor() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactor();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Sales Order Ref and Conv Factor
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 13, groups = {
			"Subcontract" }, description = "Create Subcontract Order From Pending With Sales Order Ref and Conv Factor")
	public void createSubcontractOrderFromPendingWithSalesOrderRefandConvFactor() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithSalesOrderRefandConvFactor();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 14, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactor() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactor();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order For SCO Value Addition Type with Conv Factor and
	 * Sales Order Ref
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 15, groups = {
			"Subcontract" }, description = "Direct Subcontract Order For SCO Value Addition Type with Conv Factor and Sales Order Ref")
	public void createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOref() {
		flag = SubcontractFunc.createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOref();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct Subcontract Order With BOM For Standard Type with Project
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 16, groups = {
			"Subcontract" }, description = "Direct Subcontract Order With BOM For Standard Type with Project")
	public void directSubcontractOrderWithBOMForStandardTypeWithProject() {
		flag = SubcontractFunc.directSubcontractOrderWithBOMForStandardTypeWithProject();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Subcontract Order From Pending With Project
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 17, groups = { "Subcontract" }, description = "Create Subcontract Order From Pending With Project")
	public void createSubcontractOrderFromPendingWithProject() {
		flag = SubcontractFunc.createSubcontractOrderFromPendingWithProject();
		Assert.assertTrue(flag);
	}

	/**
	 * Subcontract Order to GRN without Gate Entry for CS type - Free pass Items
	 * 
	 * @author Vimal - RMITS
	 */
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
	@Test(priority = 21, groups = {
			"Subcontract" }, description = "Subcontract Order to GRN without Gate Entry for US type - QC Enabled Items")
	public void createProductionOrderFromSubcontractorderToGRNUnprocessStandardTypeWithQC() {
		flag = SubcontractFunc.createProductionOrderFromSubcontractorderToGRNUnprocessStandardTypeWithQC();
		Assert.assertTrue(flag);
	}
	/**
	 * Create subcontract orders from pending Production Queue (OS)
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 22, groups = {
			"Subcontract" }, description = "Create subcontract orders from pending Production Queue (OS)")
	public void createProductionOrderFromPendingSubcontractorder() {
		flag = SubcontractFunc.createProductionOrderFromPendingSubcontractorder();
		Assert.assertTrue(flag);
	}
	/**
	 * Create subcontract orders from pending Production Queue (OS)
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 23, groups = {
			"Subcontract" }, description = "Create serial Item subcontract orders from pending Production Queue (OS)")
	public void createProductionOrderFromPendingSerialItemSubcontractorder() {
		flag = SubcontractFunc.createProductionOrderFromPendingSerialItemSubcontractorder();
		Assert.assertTrue(flag);
	}
}
