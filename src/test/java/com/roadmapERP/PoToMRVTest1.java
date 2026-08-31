package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks December 17, 2025
 */
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SupplierScheduleRequestFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class PoToMRVTest1 extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	private static ReportLoger logs = new ReportLoger();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	private static int previousStatus = ITestResult.SUCCESS;

	// --------------------------------------------------------- PO to GRN -With
	// Quality Enabled (Domestic Supplier)-With Gate Entry
	// -------------------------------

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
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 70, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralUserDefSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 71, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralUserDefSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
		flag = true;
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialUserDefSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialUserDefSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceUserDefSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnServiceUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 6, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 7, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 72, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnMaterialRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 73, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 10, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceRateContractSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnServiceRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 11, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnGeneralPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 12, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 13, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnMaterialPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 14, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 15, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServicePriceListSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnServicePriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 16, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without Inspection Plan")
	public void VerifyPoToGrnGeneralUserDefDifferentUomWoInspectionPlan() {
		grnPageFunc.poToGrnGeneralUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 17, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralUserDefDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - Without
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 18, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - Without Inspection Plan")
	public void VerifyPoToGrnMaterialUserDefDifferentUomWoInspectionPlan() {
		grnPageFunc.poToGrnMaterialUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 19, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialUserDefDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 20, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractDifferentUomWoInspectionPlan() {
		grnPageFunc.poToGrnGeneralRateContractDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 21, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - Wo
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 22, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractDifferentUomWoInspectionPlan() {
		grnPageFunc.poToGrnMaterialRateContractDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 23, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractDifferentUomWithInspectionPlan() {
		logs.logging("INFO",
				"PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 24, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListDifferentUomWoInspectionPlan() {
		grnPageFunc.poToGrnGeneralPriceListDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 25, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 74, groups = {
			"PurchaseOrder" }, description = " PO to GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListDifferentUomWoInspectionPlan() {
		grnPageFunc.poToGrnMaterialPriceListDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Price List-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 75, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 28, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferUserDefinedSameUomWithInspectionPlan() {
		grnPageFunc.poToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 29, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnStockTransferUserDefinedSameUomWoInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - WithOut
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 30, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan")
	public void VerifyPoToGrnStockTransferRateContractSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnStockTransferRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 31, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferRateContractSameUomWithInspectionPlan() {
		logs.logging("INFO",
				"PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 32, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnStockTransferPriceListSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnStockTransferPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 33, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferPriceListSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 34, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnStockTransferUserDefinedDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnStockTransferUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 35, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferUserDefinedDifferentUomWithInspectionPlan() {
		logs.logging("INFO",
				"PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 36, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnStockTransferRateContractDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnStockTransferRateContractDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 37, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferRateContractDifferentUomWithInspectionPlan() {
		logs.logging("INFO",
				"PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 38, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnStockTransferPriceListDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnStockTransferPriceListDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 39, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferPriceListDifferentUomWithInspectionPlan() {
		logs.logging("INFO",
				"PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	// ------------------------End-----------------------------------

	// -----------POtoGRN-WithQualityEnabled(ImportSupplier)-WithGateEntry-----------------

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 40, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportUserDefSameUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntryWithImportSupplier();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 41, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportUserDefSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 42, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportUserDefSameUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntryWithImportSupplier();
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 43, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportUserDefSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 44, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportRateContractSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnGeneralImportRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 45, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportRateContractSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 46, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportRateContractSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnMaterialImportRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 47, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportRateContractSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 48, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportPriceListSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnGeneralImportPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 49, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportPriceListSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 50, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportPriceListSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnMaterialImportPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 51, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportPriceListSameUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 52, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without Inspection Plan")
	public void VerifyPoToGrnGeneralImportUserDefDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnGeneralImportUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 53, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportUserDefDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - Without
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 54, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - Without Inspection Plan")
	public void VerifyPoToGrnMaterialImportUserDefDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnMaterialImportUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 55, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportUserDefDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 56, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportRateContractDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnGeneralImportRateContractDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 57, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportRateContractDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 58, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportRateContractDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnMaterialImportRateContractDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 59, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportRateContractDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 60, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportPriceListDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnGeneralImportPriceListDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 61, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportPriceListDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 62, groups = {
			"PurchaseOrder" }, description = " PO to GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportPriceListDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnMaterialImportPriceListDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Price List-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 63, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportPriceListDifferentUomWithInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 64, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceImportUserDefSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnServiceImportUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 65, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceImportRateContractSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnServiceImportRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 66, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceImportPriceListSameUomWoInspectionPlan() {
		grnPageFunc.poToGrnServiceImportPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	// -----------------------------------------End----------------------------------

	/**
	 * Verify Add Line In Grn Documents
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 67, groups = { "PoToGrn" }, description = "Verify Add Line In Grn Documents")
	public void VerifyAddLineInGrn() {
		grnPageFunc.verifyAddLineInGRN();
	}

	/**
	 * Po Closure With Accept Quantity
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 68, groups = { "PoToGrn" }, description = "Po Closure With Accept Quantity")
	public void VerifyPoclosureWithAcceptQuantity() {
		grnPageFunc.verifyPoClosureAcceptQtyFromPrToMRV();
	}

	/**
	 * Po Closure With Receipt Quantity
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 69, groups = { "PoToGrn" }, description = "Po Closure With Receipt Quantity")
	public void VerifyPoclosureWithReceiptQuantity() {
		grnPageFunc.verifyPoClosureReceiptQtyFromPrToMRV();
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
