package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks December 17, 2025
 */
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SupplierScheduleRequestFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class PoToMRVTest2 extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	private static ReportLoger logs = new ReportLoger();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	private SupplierScheduleRequestFunctions supplierScheduleRequest = new SupplierScheduleRequestFunctions();
	private static int previousStatus = ITestResult.SUCCESS;

	// -------------------------------PO to GRN With Free pass Items-With Gate
	// Entry------------

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM")
	public void VerifyPoToGrnGeneralUserDefSameUomWFreePass() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM")
	public void VerifyPoToGrnMaterialUserDefSameUomWFreePass() {
		grnPageFunc.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Service-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-User Defined-Same Stock and Supplier UOM")
	public void VerifyPoToGrnServiceUserDefSameUomWFreePass() {
		grnPageFunc.poToGrnServiceUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPoToGrnGeneralRateContractSameUomWFreePass() {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPoToGrnMaterialRateContractSameUomWFreePass() {
		grnPageFunc.poToGrnMaterialRateContractSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 6, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPoToGrnServiceRateContractSameUomWFreePass() {
		grnPageFunc.poToGrnServiceRateContractSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 7, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM")
	public void VerifyPoToGrnGeneralPriceListSameUomWFreePass() {
		grnPageFunc.poToGrnGeneralPriceListSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Same Stock and Supplier UOM")
	public void VerifyPoToGrnMaterialPriceListSameUomWFreePass() {
		grnPageFunc.poToGrnMaterialPriceListSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Service-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 9, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Price List-Same Stock and Supplier UOM")
	public void VerifyPoToGrnServicePriceListSameUomWFreePass() {
		grnPageFunc.poToGrnServicePriceListSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 10, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM")
	public void VerifyPoToGrnGeneralRateContractDifferentUomWFreePass() {
		grnPageFunc.poToGrnGeneralRateContractDifferentStockAndSupplierWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 11, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM")
	public void VerifyPoToGrnMaterialRateContractDifferentUomWFreePass() {
		grnPageFunc.poToGrnMaterialRateContractDifferentStockAndSupplierWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Different Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 12, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Different Stock and Supplier UOM")
	public void VerifyPoToGrnGeneralPriceListDifferentUomWFreePass() {
		grnPageFunc.poToGrnGeneralPriceListDifferentStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Price List-Different Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 13, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Different Stock and Supplier UOM")
	public void VerifyPoToGrnMaterialPriceListDifferentUomWFreePass() {
		grnPageFunc.poToGrnMaterialPriceListDifferentStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-General-User defined-Different Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 14, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Different Stock and Supplier UOM")
	public void VerifyPoToGrnGeneralUserdefinedDifferentUomWFreePass() {
		grnPageFunc.poToGrnGeneralUserDefinedDifferentStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User defined-Different Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 15, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User defined-Different Stock and Supplier UOM")
	public void VerifyPoToGrnMaterialUserdefinedDifferentUomWFreePass() {
		grnPageFunc.poToGrnMaterialUserDefinedDifferentStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 16, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM")
	public void VerifyPoToGrnStockTransferUserDefSameUomWFreePass() {
		grnPageFunc.poToGrnStockTransferUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 17, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPoToGrnStockTransferRateContractSameUomWFreePass() {
		grnPageFunc.poToGrnStockTransferRateContractSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 18, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPoToGrnStockTransferPriceListSameUomWFreePass() {
		grnPageFunc.poToGrnStockTransferPriceListSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 19, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPoToGrnStockTransferRateContractDifferentUomWFreePass() {
		grnPageFunc.poToGrnStockTransferRateContractDifferentStockAndSupplierUomWithFreePassWithGateEntry();
	}

	// ------------------------PO to GRN -Without Quality Enabled (Domestic
	// Supplier)-With Gate Entry-------------

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 20, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralUserDefSameUomWithOutQualityEnabled() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithoutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 21, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralUserDefSameUomWithOutQualityEnabledWithOutInspection() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 22, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialUserDefSameUomWithOutQualityEnabled() {
		grnPageFunc.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithoutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 23, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialUserDefSameUomWithOutQualityEnabledWithOutInspection() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 24, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractSameUomWithOutQualityWithInspectionPlan() {
		supplierScheduleRequest.verifyCreatePoToMRVGeneralRateContractSameUOMWithOutQuaityWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 25, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractSameUomWithOutQualityWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 26, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractSameUomWithoutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnMaterialRateContractSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 27, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractSameUomWithoutQualityWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
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
	@Test(priority = 28, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceUserDefSameUomWithOutQualityEnabled() {
		grnPageFunc.poToGrnServiceUserDefinedSameStockAndSupplierUomWithoutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 29, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferUserDefSameUomWithOutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnStockTransferUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 30, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnStockTransferUserDefSameUomWithOutQualityWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
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
	@Test(priority = 31, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceRateContractSameUomWithOutQualiyWithInspectionDocuments() {
		grnPageFunc.poToGrnServiceRateContractSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 32, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListSameUomWithOutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnGeneralPriceListSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 33, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListSameUomWithOutQualityWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 34, groups = { "PurchaseOrder" }, description = "")
	public void VerifyPoToGrnMaterialPriceListSameUomWithOutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnMaterialPriceListSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 35, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListSameUomWithOutQualityWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan");
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
	@Test(priority = 36, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServicePriceListSameUomWithOutQualityWithOutInspectionPlan() {
		grnPageFunc.poToGrnServicePriceListSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 37, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractDifferentUomWoQualityWithInspection() {
		grnPageFunc.poToGrnGeneralRateContractDifferentStockAndSupplierWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 38, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractDifferentUomWoQualityWithOutInspection() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 39, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractDifferentUomWoQualityWithInspection() {
		grnPageFunc.poToGrnMaterialRateContractDifferentStockAndSupplierWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - Wo
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 40, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractDifferentUomWoQualityWithOutInspection() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 41, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralUserdefinedDifferentUomWithOutQualityEnabledWithInspectionPlan() {
		grnPageFunc.poToGrnGeneralUserDefinedDifferentStockAndSupplierUomWithOutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 42, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralUserdefinedDifferentUomWithOutQualityEnabledWOInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 43, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialUserdefinedDifferentUomWithOutQualityEnabledWithGateEntry() {
		grnPageFunc.poToGrnMaterialUserDefinedDifferentStockAndSupplierUomWithOutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 44, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialUserdefinedDifferentUomWithOutQualityEnabledWithGateEntryWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 45, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListDifferentUomWoQualityWithInspection() {
		grnPageFunc.poToGrnGeneralPriceListDifferentStockAndSupplierUomWithOutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WithOut
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 46, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListDifferentUomWoQualityWOInspection() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 47, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListDifferentUomWoQualityWithInspection() {
		grnPageFunc.poToGrnMaterialPriceListDifferentStockAndSupplierUomWithOutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - WithOut
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 48, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListDifferentUomWoQualityWOInspection() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 49, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferRateContractSameUomWOQualityEnabledWithInspectionPlan() {
		grnPageFunc.poToGrnStockTransferRateContractSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Out
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 50, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnStockTransferRateContractSameUomWOQualityEnabledWOInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 51, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnStockTransferPriceListSameUomWOQualityWithGateEntryWithInspectionPlan() {
		grnPageFunc.poToGrnStockTransferPriceListSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 52, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - WithOut Inspection Plan")
	public void VerifyPoToGrnStockTransferPriceListSameUomWOQualityWithGateEntryWithOutInspectionPlan() {
		logs.logging("INFO",
				"PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - WithOut Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Check the process of GRN with Tolerance Quantity
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = { "PurchaseOrder" }, description = "Check  the process of GRN with Tolerance Quantity")
	public void VerifyGRNToleranceQuantity() {
		// Check the process of GRN with Tolerance Quantity
	}

	/**
	 * Check the process of GRN with ExcessQuantity
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = { "PurchaseOrder" }, description = " Check  the process of GRN with ExcessQuantity")
	public void VerifyGRNExcessQuantity() {
		// Check the process of GRN with ExcessQuantity
	}

	/**
	 * Inward GRN with Landed cost and other charges
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = { "PurchaseOrder" }, description = " Inward GRN with Landed cost and other charges")
	public void VerifyInwardGrnWithLandedCostAndOtherCharges() {
		// Inward GRN with Landed cost and other charges
	}

	/**
	 * cancel the GRN lines
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = { "PurchaseOrder" }, description = " cancel the GRN lines")
	public void VerifyCancelTheGRNLines() {
		// cancel the GRN lines
	}

	/**
	 * Cancel GRN Document
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = { "PurchaseOrder" }, description = "Cancel GRN Document")
	public void VerifyCancelTheGRNDocuments() {
		// Cancel GRN Document
	}

	// ------------------PO to GRN -Without Quality Enabled (Import Supplier)-With
	// Gate Entry---------------------

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 53, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportUserDefSameUomWithOutQualityEnabled() {
		grnPageFunc.poToGrnGeneralImportUserDefinedSameStockAndSupplierUomWithoutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 54, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportUserDefSameUomWithOutQualityEnabledWithOutInspection() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 55, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportUserDefSameUomWithOutQualityEnabled() {
		grnPageFunc.poToGrnMaterialImportUserDefinedSameStockAndSupplierUomWithoutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 56, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportUserDefSameUomWithOutQualityEnabledWithOutInspection() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 57, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportRateContractSameUomWithoutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnMaterialImportRateContractSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 58, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportRateContractSameUomWithoutQualityWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 59, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportPriceListSameUomWithOutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnGeneralImportPriceListSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 60, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportPriceListSameUomWithOutQualityWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan");
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
	@Test(priority = 61, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Planf")
	public void VerifyPoToGrnMaterialImportPriceListSameUomWithOutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnMaterialImportPriceListSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 62, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportPriceListSameUomWithOutQualityWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 63, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportUserdefinedDifferentUomWithOutQualityEnabledWithInspectionPlan() {
		grnPageFunc.poToGrnGeneralImportUserDefinedDifferentStockAndSupplierUomWithOutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 64, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportUserdefinedDifferentUomWithOutQualityEnabledWOInspectionPlan() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 65, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportUserdefinedDifferentUomWithOutQualityEnabledWithGateEntry() {
		grnPageFunc.poToGrnMaterialImportUserDefinedDifferentStockAndSupplierUomWithOutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 66, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportUserdefinedDifferentUomWithOutQualityEnabledWithGateEntryWithOutInspectionPlan() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 67, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportRateContractDifferentUomWoQualityWithInspection() {
		grnPageFunc.poToGrnGeneralImportRateContractDifferentStockAndSupplierWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 68, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo Inspection Plan")
	public void VerifyPoToGrnGeneralImportRateContractDifferentUomWoQualityWithOutInspection() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 69, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportRateContractDifferentUomWoQualityWithInspection() {
		grnPageFunc.poToGrnMaterialImportRateContractDifferentStockAndSupplierWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 70, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo Inspection Plan")
	public void VerifyPoToGrnMaterialImportRateContractDifferentUomWoQualityWithOutInspection() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Wo Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 71, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportPriceListDifferentUomWoQualityWithInspection() {
		grnPageFunc.poToGrnGeneralImportPriceListDifferentStockAndSupplierUomWithOutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WithOut
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 72, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralImportPriceListDifferentUomWoQualityWOInspection() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 73, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialImportPriceListDifferentUomWoQualityWithInspection() {
		grnPageFunc.poToGrnMaterialImportPriceListDifferentStockAndSupplierUomWithOutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - WithOut
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 74, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialImportPriceListDifferentUomWoQualityWOInspection() {
		logs.logging("INFO", "PO to GRN-MaterialRate Contract-Different Stock and Supplier UOM - WO Inspection Plan");
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
	@Test(priority = 75, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceImportUserDefSameUomWithOutQualityEnabled() {
		grnPageFunc.poToGrnServiceImportUserDefinedSameStockAndSupplierUomWithoutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 76, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceImportRateContractSameUomWithOutQualiyWithInspectionDocuments() {
		grnPageFunc.poToGrnServiceImportRateContractSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 77, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralImportRateContractSameUomWithOutQualityWithInspectionPlan() {
		supplierScheduleRequest.verifyCreatePoToMRVGeneralImportRateContractSameUOMWithOutQuaityWithGateEntry();
	}

	/**
	 * PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 78, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServiceImportPriceListSameUomWithOutQualityWithOutInspectionPlan() {
		grnPageFunc.poToGrnServiceImportPriceListSameStockAndSupplierUomWithOutQualityWithGateEntry();
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
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
