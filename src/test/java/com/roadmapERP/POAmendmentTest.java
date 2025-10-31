package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 10, 2025
 */
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.POAmendmentFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class POAmendmentTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static POAmendmentFunctions poAmendmentFunc = new POAmendmentFunctions();

	/**
	 * Direct PO - Domestic Supplier With Local Registered - General Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local Registered - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registered - General Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate Registered - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Local UnRegistered - General Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local UnRegistered - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate UnRegistered - General Items
	 * with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate UnRegistered - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Local Registered - Material Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local Registered - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc.verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registered - Material Items
	 * with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 6, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate Registered - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc.verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Local UnRegistered - Material Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 7, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local UnRegistered - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc.verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate UnRegistered - Material Items
	 * with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate UnRegistered - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Local registered - Service Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 9, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local registered - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOServiceSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc.verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate registered - Service Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 10, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate registered - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOserviceSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc.verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Local Unregistered - Service Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 11, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local Unregistered - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOServiceSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc.verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Unregistered - Service Items
	 * with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 12, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate Unregistered - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOServiceSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc.verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Local registered - General Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 13, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Local registered - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate registered - General Items
	 * with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 14, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Interstate registered - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Local Un-registered - General Items
	 * with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 15, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Local registered - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalUnReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate Un-registered - General
	 * Items with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 16, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Interstate registered - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnReg();
	}

	/**
	 * Direct PO - Import Supplier - General Items with Similar Stock and Supplier
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 17, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralSimilarStockAndSupplierUOMWithImport() {
		poAmendmentFunc.verifyDirectPOGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithImport();
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Similar Stock and Supplier
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 18, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialSimilarStockAndSupplierUOMWithImport() {
		poAmendmentFunc.verifyDirectPOMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithImport();
	}

	/**
	 * From PR to PO - Domestic Supplier With Local registered - Service Items with
	 * Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 19, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Local registered - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticServiceSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc.verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate registered - Service Items
	 * with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 20, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Interstate registered - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticServiceSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc.verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Local Un-registered - Service Items
	 * with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 21, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Local registered - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticServiceSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc.verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalUnReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate Un-registered - Service
	 * Items with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 22, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Interstate registered - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticServiceSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc.verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnReg();
	}

	/**
	 * From PR to PO - Import Supplier General Items with Similar Stock and Supplier
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 23, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPOGeneralSimilarStockAndSupplierUOMWithImport() {
		poAmendmentFunc.verifyPRToPOGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithImport();
	}

	/**
	 * Direct PO - Domestic Supplier With Local Registered - General Items with
	 * Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 24, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local Registered - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralDissimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocalRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registered - General Items with
	 * Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 25, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate Registered - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralDissimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Local UnRegistered - General Items with
	 * Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 26, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local UnRegistered - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralDissimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate UnRegistered - General Items
	 * with Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 27, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate UnRegistered - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralDissimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Local Registered - Material Items with
	 * Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 28, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local Registered - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialDissimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocalRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registered - Material Items
	 * with Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 29, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate Registered - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialDissimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Local UnRegistered - Material Items with
	 * Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 30, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Local UnRegistered - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialDissimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc.verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg();
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate UnRegistered - Material Items
	 * with Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 31, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier With Interstate UnRegistered - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialDissimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg();
	}

	/**
	 * Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 32, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOGeneralDissimilarStockAndSupplierUOMWithImport() {
		poAmendmentFunc.verifyDirectPOGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithImport();
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 33, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOMaterialDissimilarStockAndSupplierUOMWithImport() {
		poAmendmentFunc.verifyDirectPOMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithImport();
	}

	/**
	 * From PR to PO - Domestic Supplier With Local registered - General Items with
	 * Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 34, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Local registered - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralDissimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocalReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate registered - General Items
	 * with Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 35, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Interstate registered - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralDissimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Local Un-registered - General Items
	 * with Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 36, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Local registered - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralDissimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocalUnReg();
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate Un-registered - General
	 * Items with Dissimilar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 37, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier With Interstate registered - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralDissimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnReg();
	}

	/**
	 * From PR to PO - Import Supplier General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 38, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPOGeneralDissimilarStockAndSupplierUOMWithImport() {
		poAmendmentFunc.verifyPRToPOGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithImport();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM With Local Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 39, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Local Registered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM With Interstate Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 40, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Intestate Registered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM With Local UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 41, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Local UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM With Interstate UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 42, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM With Local Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 43, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM With Local Registered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM With Interstate Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 44, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Intestate Registered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM With Local UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 45, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Local UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM With Interstate UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 46, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM With Local Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 47, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM With Local Registered?")
	public void VerifyPoAmendModifyDirectPODomesticServiceSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM With Interstate Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 48, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Intestate Registered?")
	public void VerifyPoAmendModifyDirectPODomesticServiceSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM With Local UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 49, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Local UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticServicelSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM With Interstate UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 50, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticServiceSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg();
	}

	/**
	 * 130 Direct PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 51, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPOGeneralSimilarStockAndSupplierUOMImport() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport();
	}

	/**
	 * 130 Direct PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 52, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPOMaterialSimilarStockAndSupplierUOMImport() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Local Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 53, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Local Registered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Interstate Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 54, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Intestate Registered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Local UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 55, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Local UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Interstate UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 56, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM With Local Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 57, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM With Local Registered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Interstate Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 58, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Intestate Registered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Local UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 59, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Local UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Interstate UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 60, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg();
	}

	/**
	 * 130 Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 61, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPOGeneralDissimilarStockAndSupplierUOMImport() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport();
	}

	/**
	 * 130 Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 62, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPOMaterialDissimilarStockAndSupplierUOMImport() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 63, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 131 Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 64, groups = {
			"Purchase Order Amendment" }, description = "131 Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 132 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 65, groups = {
			"Purchase Order Amendment" }, description = "132 Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 133 Direct PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 66, groups = {
			"Purchase Order Amendment" }, description = "133 Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPOImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 134 Direct PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 67, groups = {
			"Purchase Order Amendment" }, description = "134 Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPOImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 68, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortPrToPoDomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticGenaeralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 69, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortPrToPoDomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 70, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortPrToPoImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPOImportGenaeralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 71, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 72, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 73, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 74, groups = {
			"Purchase Order Amendment" }, description = "128 From PR to PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortAmendPRToPOImportGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * cancel the purchase Order amendment document?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 75, groups = {
			"Purchase Order Amendment" }, description = "cancel the purchase Order amendment document?")
	public void VerifyPoAmendmentCancelLineAndCancelDocuments() {
		poAmendmentFunc.verifyPoAmendmentCancelDocumentsAndCancelLine();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 76, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 77, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 78, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 79, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 80, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoImportGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 81, groups = {
			"Purchase Order Amendment" }, description = "126 From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPODomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticGenaeralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 82, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 83, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 84, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPOImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 85, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 86, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 87, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 88, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 89, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 90, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPODomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 91, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPOImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 92, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPODomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 93, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
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
