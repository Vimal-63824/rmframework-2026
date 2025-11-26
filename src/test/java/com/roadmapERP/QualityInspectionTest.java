package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonfunctions.qualitycontrol.InspectionPlanFunctions;
import utils.ReportLoger;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks October 27, 2025
 */
public class QualityInspectionTest extends RoadMapERPTest {
	private boolean flag = false;
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static InspectionPlanFunctions InsPlanFunc = new InspectionPlanFunctions();
	private static InspectionDocumentFunctions InsDocFunc = new InspectionDocumentFunctions();
	private static int previousStatus = ITestResult.SUCCESS;

	/**
	 * Define Inspection Plan - In Process for manufacturing process
	 * 
	 * @author Sakthi-GWL
	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 1, groups = {
//			"Ins Plan" }, description = "25 Define Inspection Plan - In Process for manufacturing process ?")
	public void VerifyInspectionPlanInProcessForManufacturingProcess() {
		flag = InsPlanFunc.verifyInspectionPlanInProcessForManufacturingProcess();
		Assert.assertTrue(flag);
	}

	/**
	 * Define Inspection Plan - Pre Dispatch?
	 * 
	 * @author Sakthi-GWL
	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 2, groups = { "Ins Plan" }, description = "26 Define Inspection Plan - Pre Dispatch?")
	public void VerifyInspectionPlanPreDispatch() {
		flag = InsPlanFunc.verifyInspectionPlanPreDispatch();
		Assert.assertTrue(flag);
	}

	/**
	 * Define Inspection Plan - Final inspection?
	 * 
	 * @author Sakthi-GWL
	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 3, groups = { "Ins Plan" }, description = "27 Define Inspection Plan - Final inspection?")
	public void VerifyInspectionPlanFinalInspection() {
		flag = InsPlanFunc.verifyInspectionPlanFinalInspection();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create Inspection document from Pending Inspection Request - Whole?
	 * 
	 * @author Sakthi-GWL
	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 4, groups = {
//			"Ins Docs" }, description = "145 How to create Inspection document from Pending Inspection Request - Whole?")
	public void VerifyInspectionDocumentsFromRequestWholeType() {
		flag = InsDocFunc.verifyInspectionDocumentsFromRequestWholeType();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create Inspection document from Pending Inspection Request - Partial?
	 * 
	 * @author Sakthi-GWL
	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 5, groups = {
//			"Ins Docs" }, description = "146 How to create Inspection document from Pending Inspection Request - Partial?")
	public void VerifyInspectionDocumentsFromRequestPartialType() {
		flag = InsDocFunc.verifyInspectionDocumentsFromRequestPartialType();
		Assert.assertTrue(flag);
	}

	/**
	 * How to Cancel the Inspection document?
	 * 
	 * @author Sakthi-GWL
	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 6, groups = { "Ins Docs" }, description = "148 How to Cancel the Inspection document?")
	public void VerifyCancelInspectionDocuments() {
		flag = InsDocFunc.verifyCancelInspectionDocuments();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create Inspection document for Multiple Pending Inspection request?
	 * 
	 * @author Sakthi-GWL
	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 7, groups = {
//			"Ins Docs" }, description = "147 How to create Inspection document for Multiple Pending Inspection request?")
	public void VerifyInspectionDocumentsFromRequestMultiDocuments() {
		flag = InsDocFunc.verifyInspectionDocumentsFromRequestMultiDocuments();
		Assert.assertTrue(flag);
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Sakthi-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		previousStatus = result.getStatus();
		commonFunc.enterAuthorNamesInExtentReport(method);
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
	}
}
