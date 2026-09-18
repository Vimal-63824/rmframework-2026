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

public class DirectGRNFOCTest extends RoadMapERPTest {
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
	 * Direct GRN-General-User Defined-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = {
			"Direct GRN" }, description = "758 Direct GRN-General-User Defined-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-User Defined-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"Direct GRN" }, description = "759 Direct GRN-Material-User Defined-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Service-User Defined-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"Direct GRN" }, description = "760 Direct GRN-Service-User Defined-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-User Defined-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = {
			"Direct GRN" }, description = "770 Direct GRN-General-User Defined-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-User Defined-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = {
			"Direct GRN" }, description = "771 Direct GRN-Material-User Defined-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-Rate Contract-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = {
			"Direct GRN" }, description = "763 Direct GRN-General-Rate Contract-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-Rate Contract-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = {
			"Direct GRN" }, description = "764 Direct GRN-Material-Rate Contract-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Service-Rate Contract-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = {
			"Direct GRN" }, description = "764 Direct GRN-Service-Rate Contract-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-Rate Contract-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = {
			"Direct GRN" }, description = "772 Direct GRN-General-Rate Contract-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-Rate Contract-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
			"Direct GRN" }, description = "773 Direct GRN-Material-Rate Contract-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-Price List-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
			"Direct GRN" }, description = "766 Direct GRN-General-Price List-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-Price List-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 12, groups = {
			"Direct GRN" }, description = "767 Direct GRN-Material-Price List-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Service-Price List-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 13, groups = {
			"Direct GRN" }, description = "768 Direct GRN-Service-Price List-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-Price List-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 14, groups = {
			"Direct GRN" }, description = "776 Direct GRN-General-Price List-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-Price List-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 15, groups = {
			"Direct GRN" }, description = "777 Direct GRN-Material-Price List-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLMaterialItemDissimilarUOM();
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
