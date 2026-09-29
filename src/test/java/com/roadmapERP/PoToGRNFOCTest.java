package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks February 12, 2026
 */
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.Purchase_GRN_FOCFunctions;

public class PoToGRNFOCTest extends RoadMapERPTest {
	private boolean flag = false;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static Purchase_GRN_FOCFunctions purchaseFocFunc = new Purchase_GRN_FOCFunctions();
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
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = {
			"Po to GRN" }, description = "716 PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"Po to GRN" }, description = "717 PO to GRN-General-User Defined-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"Po to GRN" }, description = "718 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = {
			"Po to GRN" }, description = "719 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Service-User Defined-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = {
			"Po to GRN" }, description = "720 PO to GRN-Service-User Defined-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMServiceUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMServiceUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 6, groups = {
//			"Po to GRN" }, description = "721 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMStockTransferUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMStockTransferUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 7, groups = {
//			"Po to GRN" }, description = "722 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMStockTransferUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMStockTransferUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = {
			"Po to GRN" }, description = "723 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = {
			"Po to GRN" }, description = "724 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
			"Po to GRN" }, description = "725 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
			"Po to GRN" }, description = "726 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 12, groups = {
			"Po to GRN" }, description = "727 PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMServiceRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMServiceRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 13, groups = {
//			"Po to GRN" }, description = "728 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMStockTransferRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMStockTransferRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 14, groups = {
//			"Po to GRN" }, description = "729 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMStockTransferRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMStockTransferRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 15, groups = {
			"Po to GRN" }, description = "730 PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - Without Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 16, groups = {
			"Po to GRN" }, description = "731 PO to GRN-General-Price List-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 17, groups = {
			"Po to GRN" }, description = "732 PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 18, groups = {
			"Po to GRN" }, description = "733 PO to GRN-Material-Price List-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Service-Price List-Same Stock and Supplier UOM - Without Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 19, groups = {
			"Po to GRN" }, description = "734 PO to GRN-Service-Price List-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMServicePLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMServicePLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 20, groups = {
//			"Po to GRN" }, description = "735 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMStockTransferPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMStockTransferPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 21, groups = {
//			"Po to GRN" }, description = "736 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMStockTransferPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMStockTransferPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 22, groups = {
			"Po to GRN" }, description = "737 PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 23, groups = {
			"Po to GRN" }, description = "738 PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 24, groups = {
			"Po to GRN" }, description = "739 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 25, groups = {
			"Po to GRN" }, description = "740 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Diff Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 26, groups = {
//			"Po to GRN" }, description = "742 PO to GRN-Stock Transfer-User Defined-Diff Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMStockTransferUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMStockTransferUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-User Defined-Diff Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 27, groups = {
//			"Po to GRN" }, description = "743 PO to GRN-Stock Transfer-User Defined-Diff Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMStockTransferUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMStockTransferUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 28, groups = {
			"Po to GRN" }, description = "744 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 29, groups = {
			"Po to GRN" }, description = "745 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 30, groups = {
			"Po to GRN" }, description = "746 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 31, groups = {
			"Po to GRN" }, description = "747 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Diff Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 32, groups = {
//			"Po to GRN" }, description = "749 PO to GRN-Stock Transfer-Rate Contract-Diff Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMStockTransferRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMStockTransferRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-Rate Contract-Diff Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 33, groups = {
//			"Po to GRN" }, description = "750 PO to GRN-Stock Transfer-Rate Contract-Diff Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMStockTransferRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMStockTransferRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Price List-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 34, groups = {
			"Po to GRN" }, description = "751 PO to GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Price List-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 35, groups = {
			"Po to GRN" }, description = "752 PO to GRN-General-Price List-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Price List-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 36, groups = {
			"Po to GRN" }, description = "753 PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Price List-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 37, groups = {
			"Po to GRN" }, description = "754 PO to GRN-Material-Price List-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Diff Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 38, groups = {
//			"Po to GRN" }, description = "756 PO to GRN-Stock Transfer-Price List-Diff Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMStockTransferPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMStockTransferPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Stock Transfer-Price List-Diff Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 39, groups = {
//			"Po to GRN" }, description = "757 PO to GRN-Stock Transfer-Price List-Diff Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMStockTransferPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMStockTransferPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * Verify After Method
	 * 
	 * @author Sakthi-GWL
	 * @param result
	 * @param method
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
