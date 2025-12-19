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
import utils.Mailer;
import utils.WebDriverBase;

public class PoToMRVTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = {
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
	@Test(priority = 2, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralUserDefSameUomWithInspectionPlan() {
		// PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
		// Plan
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
		// PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
		// Plan
		flag = true;
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
		// PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection
		// Plan
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = {
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
	@Test(priority = 9, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractSameUomWithInspectionPlan() {
		// PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With
		// Inspection Plan
		flag = true;
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
		flag = true;
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
		// PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection
		// Plan
		flag = true;
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
		flag = true;
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
		flag = true;
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
		flag = true;
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
		flag = true;
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
		grnPageFunc.poToGrnGeneralPriceListDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 26, groups = {
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
	@Test(priority = 27, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListDifferentUomWithInspectionPlan() {
		flag = true;
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
		flag = true;
		// PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - WO
		// Inspection Plan
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
		// PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With
		// Inspection Plan
		flag = true;
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
		// PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With
		// Inspection Plan
		flag = true;
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
//	@Test(priority = 34, groups = {
//			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnStockTransferUserDefinedDifferentUomWoInspectionPlan() {
		grnPageFunc
				.poToGrnStockTransferUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 35, groups = {
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
	@Test(priority = 36, groups = {
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
	@Test(priority = 37, groups = {
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
	@Test(priority = 38, groups = {
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
	@Test(priority = 39, groups = {
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
	@Test(priority = 40, groups = {
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
	@Test(priority = 41, groups = {
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
	@Test(priority = 42, groups = {
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
	@Test(priority = 43, groups = {
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
	@Test(priority = 44, groups = {
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
	@Test(priority = 45, groups = {
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
	@Test(priority = 46, groups = {
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
	@Test(priority = 47, groups = {
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
	@Test(priority = 48, groups = {
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
	@Test(priority = 49, groups = {
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
	@Test(priority = 50, groups = {
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
	@Test(priority = 51, groups = {
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
	@Test(priority = 52, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPoToGrnStockTransferPriceListSameUomWFreePass() {
		grnPageFunc.poToGrnStockTransferPriceListSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 53, groups = {
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
	@Test(priority = 54, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralUserDefSameUomWithOutQualityEnabledWithOutInspection() {
		// PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
		// Plan
		flag = true;
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
	@Test(priority = 56, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialUserDefSameUomWithOutQualityEnabledWithOutInspection() {
		flag = true;
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 57, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractSameUomWithOutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 58, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralRateContractSameUomWithOutQualityWithOutInspectionPlan() {
		// PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection
		// Plan
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 59, groups = {
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
	@Test(priority = 60, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialRateContractSameUomWithoutQualityWithOutInspectionPlan() {
		// PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection
		// Plan
	}

	/**
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 61, groups = { "PurchaseOrder" }, description = "")
	public void VerifyPoToGrnServiceUserDefSameUomWithOutQualityEnabled() {
		grnPageFunc.poToGrnServiceUserDefinedSameStockAndSupplierUomWithoutQualityEnabledWithGateEntry();
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
//	@Author("Deepan-GWL")
//	@Test(priority = 62, groups = {
//			"PurchaseOrder" }, description = "PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM")
	public void VerifyPoToGrnStockTransferUserDefSameUomWithQualityWithInspectionPlan() {
		grnPageFunc.poToGrnStockTransferUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry();
	}

	/**
	 * PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection
	 * Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 63, groups = {
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
	@Test(priority = 64, groups = {
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
	@Test(priority = 65, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnGeneralPriceListSameUomWithOutQualityWithOutInspectionPlan() {
		// PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
		// Plan
	}

	/**
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 66, groups = { "PurchaseOrder" }, description = "")
	public void VerifyPoToGrnMaterialPriceListSameUomWithOutQualityWithInspectionPlan() {
		grnPageFunc.poToGrnMaterialPriceListSameStockAndSupplierUomWithOutQualityWithGateEntry();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 67, groups = {
			"PurchaseOrder" }, description = "PO to GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnMaterialPriceListSameUomWithOutQualityWithOutInspectionPlan() {
		// PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
		// Plan
	}

	/**
	 * PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 68, groups = {
			"PurchaseOrder" }, description = "PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyPoToGrnServicePriceListSameUomWithOutQualityWithOutInspectionPlan() {
		grnPageFunc.poToGrnServicePriceListSameStockAndSupplierUomWithOutQualityWithGateEntry();
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
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
