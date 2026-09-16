package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
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

	@BeforeMethod(alwaysRun = true)
	private void VerifyLogin() {
		if (VariableHandler.getSessionID() == null) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
		} else {
			commonFunc.navigateToHomePage();
		}
	}

	/**
	 * Define Inspection Plan - InComing inspection?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = { "Ins Plan" }, description = "Define Inspection Plan - InComing inspection?")
	public void VerifyInspectionPlanWithIncoming() {
		InsDocFunc.verifyInspectionDocumentsIncoming();
	}

	/**
	 * Define Inspection Plan - In Process for manufacturing process ?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = {
			"Ins Plan" }, description = "Define Inspection Plan - In Process for manufacturing process ?")
	public void VerifyInspectionPlanWithInProcessForManufacturing() {
		InsDocFunc.verifyInspectionPlanWithInprocessForManufacturing();
	}

	/**
	 * Define Inspection Plan - Final inspection?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = { "Ins Plan" }, description = "Define Inspection Plan - Final inspection?")
	public void VerifyInspectionPlanWithFinalInspection() {
		InsDocFunc.verifyInspectionPlanWithFinalInspection();
	}

	/**
	 * Define Inspection Plan - Pre Dispatch?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = { "Ins Plan" }, description = "Define Inspection Plan - Pre Dispatch?")
	public void VerifyInspectionPlanWithPreDispatch() {
		InsDocFunc.verifyInspectionPlanWithPreDispatch();
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
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
