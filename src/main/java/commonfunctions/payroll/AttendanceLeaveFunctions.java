package commonfunctions.payroll;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.payroll.AttendanceLeaveLocators;
import commonlocators.payroll.EmployeeMasterLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class AttendanceLeaveFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	public CommonPayrollFunctions commonPayrollFunctions = new CommonPayrollFunctions();
	public EmployeeMasterFunctions empMasterFunc = new EmployeeMasterFunctions();
	public ExcelUtils excelUtils = new ExcelUtils();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();

	/**
	 * This method is used to To Check Permission based on Employee Data
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean verifyCheckpermissionEntryWithEmployeeData() {
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEncashmentScreen();
			addPermissionEntry();
			permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
			permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
					commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
					commonFunc.getDataFromTestDataSheet("PermissionReference"));
			permissionSaveAndForwardDocument();
			verifyWaitingForApproval();
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getPermissionDocNo(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateLeaveEncashmentScreen();
			PermissionForwardApprovalReverse(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
					commonFunc.getDataFromTestDataSheet("PermissionFindType"));
			VariableHandler.setCheckEmployeeDataPermissionDefaultExecution(true);
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while Executing 'verifyCheckpermissionEntryWithEmployeeData' method: " + e);
			return false;
		}
	}

	/**
	 * This method is used to To Check On-Duty based On Employee Data
	 * 
	 * @author Kishore-RMITS
	 * @return
	 */
	public boolean verifyCheckOnDutyEntrWithEmployeeData() {
		try {
			if (!VariableHandler.getCheckEmployeeDataPermissionDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
						"HrRqtUsername");
				commonFunc.navigateLeaveEncashmentScreen();
				addPermissionEntry();
				permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
				checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
				permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
						commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
						commonFunc.getDataFromTestDataSheet("PermissionReference"));
				permissionSaveAndForwardDocument();
				verifyWaitingForApproval();
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getPermissionDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				commonFunc.navigateLeaveEncashmentScreen();
				PermissionForwardApprovalReverse(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
						commonFunc.getDataFromTestDataSheet("PermissionFindType"));
			}
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEncashmentScreen();
			addPermissionEntry();
			permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			permissionSelctType(commonFunc.getDataFromTestDataSheet("PermissionType"));
			checkOnDutyBasedonDateWise(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
			permissionReference(commonFunc.getDataFromTestDataSheet("PermissionReference"));
			permissionSaveAndForwardDocument();
			verifyWaitingForApproval();
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getPermissionDocNo(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateLeaveEncashmentScreen();
			PermissionForwardApprovalReverse(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
					commonFunc.getDataFromTestDataSheet("OnDutyFindType"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while Executing 'verifyCheckOnDutyEntrWithEmployeeData' method: " + e);
			return false;
		}
	}

	/**
	 * This method is used to for search permission find page with all fields and
	 * Verify the Permission Report
	 * 
	 * @param Employee
	 * @param PermissionFindType
	 * @author Kishore-RMITS
	 */
	public void checkPermissionreportfetchesvaluescorrectly(String EmployeeDurai, String PermissionFindType) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.PermissionFindPageEmployeeLOV, EmployeeDurai);
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
				ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
					PermissionFindType, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Permission Type is" + PermissionFindType);
				webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindPageSearchButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick,
						ElementType.CssSelector);
				if (flag) {
					commonFunc.hardWait(2000);
					webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick,
							ElementType.CssSelector);
					flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionReverse, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Permission Document is Clicked");
						webDB.clickAnElement(AttendanceLeaveLocators.PermissionReverse, ElementType.Xpath);
						flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionReverseOk, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Permission Document is Reversed");
							webDB.clickAnElement(AttendanceLeaveLocators.PermissionReverseOk, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(5000);
								webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionEntryFindButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(AttendanceLeaveLocators.EmpoloyeeFindPermissionAddButton,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "On-Duty Entry Add Button Clicked");
									webDB.clickAnElement(AttendanceLeaveLocators.EmpoloyeeFindPermissionAddButton,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to select Employee in find page LOV and all datas verify
	 * reports
	 * 
	 * @param Employee
	 * @param OnDutyFindType
	 * @author Kishore-RMITS
	 */
	public void checkOnDutyreportfetchesvaluescorrectly(String EmployeeDurai, String OnDutyFindType) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.PermissionFindPageEmployeeLOV, EmployeeDurai);
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
				ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
					OnDutyFindType, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Type selected is" + OnDutyFindType);
				webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindPageSearchButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick,
						ElementType.CssSelector);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick,
							ElementType.CssSelector);
					flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionReverse, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "On-Duty Document is Clicked");
						webDB.clickAnElement(AttendanceLeaveLocators.PermissionReverse, ElementType.Xpath);
						flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionReverseOk, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "On-Duty Document is Reversed");
							webDB.clickAnElement(AttendanceLeaveLocators.PermissionReverseOk, ElementType.Xpath);
							commonFunc.hardWait(5000);
							flag = commonFunc.logoutUser();

						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Check Permission entry document to forward to approval
	 * user
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkPermissionOnDutybasedonFowardtoApprovalReord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateLeaveEncashmentScreen();
		addPermissionEntry();
		permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
		permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
				commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
				commonFunc.getDataFromTestDataSheet("PermissionReference"));
		permissionSaveAndForwardDocument();
		verifyWaitingForApproval();
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getPermissionDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.navigateLeaveEncashmentScreen();
		PermissionForwardApprovalReverse(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("PermissionFindType"));
		return flag;
	}

	/**
	 * This method is used to Return the Permission Document from Approval to
	 * Request User return process.
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkPermissionOnDutyBasedOnReturnToRequestRecord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateLeaveEncashmentScreen();
		addPermissionEntry();
		permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
		permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
				commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
				commonFunc.getDataFromTestDataSheet("PermissionReference"));
		permissionSaveAndForwardDocument();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getPermissionDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"), webDB.getDataFromProperties("HRUSEREmpId"));
		commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getPermissionDocNo(),
				webDB.getDataFromProperties("HRUSEREmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HrRqtUsername"), webDB.getDataFromProperties("HRUSEREmpId"));
		commonFunc.navigateLeaveEncashmentScreen();
		PermissionDocumentCancel(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("PermissionFindType"));
		return flag;
	}

	/**
	 * This method is used to Check Leave Encashment Based On RequestDate
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckLeaveEncashmentbasedonRequestDate() {
		{
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEncashmentScreen();
			EncashmentEntry(commonFunc.getDataFromTestDataSheet("EncashmentRequestDate"));
			EncashmentEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			EncashmentLeaveLOVEntry(commonFunc.getDataFromTestDataSheet("EncashmentLeaveLOV"));
			EncashmentDaysEntry(commonFunc.getDataFromTestDataSheet("EncashDaysField"),
					commonFunc.getDataFromTestDataSheet("EncashReasonField"));
			EncashmentForward();
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEncashmentDocNo(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateLeaveEncashmentScreen();
			CheckLeaveEncashmentBasedOnPendingEncashment(VariableHandler.getEncashmentDocNo());
			CheckLeaveEncashmentBasedOnEncashInPayroll();
			CheckLeaveEncashmentReportFetchesValuesCorrectly(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			flag = commonFunc.logoutUser();
		}
		return flag;
	}

	/**
	 * This method is used to Forward The Encashment Document to Approval Person
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckLeaveEncashmentBasedOnForwardToApprovalRecord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateLeaveEncashmentScreen();
		EncashmentEntry(commonFunc.getDataFromTestDataSheet("EncashmentRequestDate"));
		EncashmentEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EncashmentLeaveLOVEntry(commonFunc.getDataFromTestDataSheet("EncashmentLeaveLOV"));
		EncashmentDaysEntry(commonFunc.getDataFromTestDataSheet("EncashDaysField"),
				commonFunc.getDataFromTestDataSheet("EncashReasonField"));
		EncashmentForward();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEncashmentDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method is used to Return The Encashment Document to the Request User
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckLeaveEncashmentBasedOnReturnToRequestRecord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateLeaveEncashmentScreen();
		EncashmentEntry(commonFunc.getDataFromTestDataSheet("EncashmentRequestDate"));
		EncashmentEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EncashmentLeaveLOVEntry(commonFunc.getDataFromTestDataSheet("EncashmentLeaveLOV"));
		EncashmentDaysEntry(commonFunc.getDataFromTestDataSheet("EncashDaysField"),
				commonFunc.getDataFromTestDataSheet("EncashReasonField"));
		EncashmentForward();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEncashmentDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"), webDB.getDataFromProperties("HRUSEREmpId"));
		commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEncashmentDocNo(),
				webDB.getDataFromProperties("HRUSEREmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HrRqtUsername"), webDB.getDataFromProperties("HRUSEREmpId"));
		commonFunc.navigateLeaveEncashmentScreen();
		EncashmentCancel();
		return flag;
	}

	/**
	 * This method is used To Check Comp-Off Entry based on Leave Types
	 *
	 * @author Kishore-RMITS
	 */
	public boolean CheckCompOffEntryBasedOnLeaveTypes() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateLeaveEncashmentScreen();
		CompOffEntry();
		compOffEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		checkCompOffBasedonDatewise(commonFunc.getDataFromTestDataSheet("PermFromDate2"),
				commonFunc.getDataFromTestDataSheet("CompOffDays"),
				commonFunc.getDataFromTestDataSheet("CompOffReferenceField"));
		CompOffEntrySaveAndPostDocument();
//		verifyWaitingForApproval();
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
//			approvalFunc.navigateToApprovals();
//			filterDocumentsforPermissionApproval(VariableHandler.getCompOffDocNo());
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getCompOffDocNo());
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getCompOffDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method is used to Return The CompOff Document to the Request User Return
	 * Document
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckCompOffBasedOnReturnToApprovalRecord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateLeaveEncashmentScreen();
		CompOffEntry();
		compOffEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		checkCompOffBasedonDatewise(commonFunc.getDataFromTestDataSheet("PermFromDate2"),
				commonFunc.getDataFromTestDataSheet("CompOffDays"),
				commonFunc.getDataFromTestDataSheet("CompOffReferenceField"));
		CompOffEntrySaveAndPostDocument();
//		verifyWaitingForApproval();
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
//			approvalFunc.navigateToApprovals();
//			filterDocumentsforPermissionApproval(VariableHandler.getCompOffDocNo());
//			verifyReturnWithDocNumber(VariableHandler.getCompOffDocNo());
//			flag = commonFunc.logoutUser();
//			if (flag) {
//				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"));
//				approvalFunc.navigateToApprovals();
//				filterDocumentsforPermissionApproval(VariableHandler.getCompOffDocNo());
//				verifyReturnWithDocNumber(VariableHandler.getCompOffDocNo());
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getCompOffDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"), webDB.getDataFromProperties("HRUSEREmpId"));
		commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getCompOffDocNo(),
				webDB.getDataFromProperties("HRUSEREmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HrRqtUsername"), webDB.getDataFromProperties("HRUSEREmpId"));
		CheckCompOffFindPageWithAllLineWiseRecord(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method is used to Find the Line Wise Record
	 * 
	 * @author Kishore-RMITS
	 * @param EmployeeDurai
	 */
	public boolean CheckCompOffFindPageWithAllLineWiseRecord(String EmployeeDurai) {
		commonFunc.navigateLeaveEncashmentScreen();
		flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffButtonClick, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.CompOffButtonClick, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.CompOffButtonClick, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CompOff Button is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffFindEmployeeLOV, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.CompOffFindEmployeeLOV,
							EmployeeDurai);
					if (flag) {
						logs.logging("INFO", "CompOff Employee Is Selected");
						flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffFindpageSearchButton,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AttendanceLeaveLocators.CompOffFindpageSearchButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Search Button is Clicked");
								flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffFindpageClearButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AttendanceLeaveLocators.CompOffFindpageClearButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Clear Button is Clicked");
										commonFunc.hardWait(2000);
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * This method is used to Check Leave Encashment Report Fetches Values Correctly
	 * 
	 * @param EmployeeDurai
	 * @author Kishore-RMITS
	 */
	public void CheckLeaveEncashmentReportFetchesValuesCorrectly(String EmployeeDurai) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.EncashmentFindPageEmployeeLOV, EmployeeDurai);
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Reverse The Permission Document
	 * 
	 * @author Kishore-RMITS
	 * @param EmployeeDurai
	 * @param PermissionFindType
	 */
	public void PermissionForwardApprovalReverse(String EmployeeDurai, String PermissionFindType) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			logs.logging("INFO", "Permission Button Is Clicked");
			flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindPageEmployeeLOV, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.PermissionFindPageEmployeeLOV,
					EmployeeDurai);
			flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
					ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.selectDropDownByText(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
						PermissionFindType, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Permission Type is" + PermissionFindType);
					webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindPageSearchButton, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick,
							ElementType.CssSelector);
					commonFunc.hardWait(3000);
					webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick,
							ElementType.CssSelector);
					if (flag) {
						logs.logging("INFO", "Permission Document Is Clicked");
						flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionReverse, ElementType.Xpath);
						commonFunc.hardWait(3000);
						webDB.clickAnElement(AttendanceLeaveLocators.PermissionReverse, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Permission Document is Reversed");
							flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionReverseOk, ElementType.Xpath);
							webDB.clickAnElement(AttendanceLeaveLocators.PermissionReverseOk, ElementType.Xpath);
							flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
							if (flag) {
								if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath)) {
									webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
											ElementType.Xpath);
								}
								flag = commonFunc.logoutUser();
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Cancel The Permission Document
	 * 
	 * @author Kishore-RMITS
	 * @param PermissionFindType
	 * @param EmployeeDurai
	 */
	public void PermissionDocumentCancel(String EmployeeDurai, String PermissionFindType) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			logs.logging("INFO", "Permission Button Is Clicked");
			flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindPageEmployeeLOV, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindPageSearchButton, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.PermissionFindPageEmployeeLOV,
						EmployeeDurai);
				flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.selectDropDownByText(
							AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField, PermissionFindType,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Permission Type is" + PermissionFindType);
						webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindPageSearchButton, ElementType.Xpath);
						flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick,
								ElementType.CssSelector);
						if (flag) {
							commonFunc.hardWait(2000);
							webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick,
									ElementType.CssSelector);
							if (flag) {
								logs.logging("INFO", "Permission Document is Clicked");
								flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionCancelButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AttendanceLeaveLocators.PermissionCancelButton,
											ElementType.Xpath);
									commonFunc.hardWait(2000);
									logs.logging("INFO", "Permission Document is Cancelled");
									flag = commonFunc.logoutUser();
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to add PermissionEntry
	 * 
	 * @author Kishore-RMITS
	 */
	public void addPermissionEntry() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			logs.logging("INFO", "Permission Button Is Clicked");
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.EmpoloyeeFindPermissionAddButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionFromDateField, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Permission Add Button Is Clicked");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Click
	 * 
	 * @author Kishore-RMITS
	 */
	public void PermissionButton() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.waitForElement(AttendanceLeaveLocators.EmpoloyeeFindPermissionAddButton, ElementType.Xpath);
			logs.logging("INFO", "Permission Button Is Clicked");
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used Select permission Employee LOVEntry
	 * 
	 * @param Employee
	 * @author Kishore-RMITS
	 */
	public void permissionEmployeeLOVEntry(String Employee) {

		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.PermissionEmployeeLOV, Employee);
	}

	/**
	 * This method is used Select CompOff Employee LOVEntry
	 * 
	 * @param Employee
	 * @author Kishore-RMITS
	 */
	public void compOffEmployeeLOVEntry(String EmployeeDurai) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.CompOffEmployeeLOV, EmployeeDurai);
	}

	/**
	 * This method is used to add permission From And ToDate
	 * 
	 * @param PermFromDate
	 * @author Kishore-RMITS
	 */
	public void checkPermissionBasedonFromDate(String PermFromDate) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionFromDateField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(AttendanceLeaveLocators.EmployeePermissionFromDateField, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.EmployeePermissionFromDateField, PermFromDate,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Permission From Date is Entered");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to add permission TimeFrom Entry
	 * 
	 * @param PermissionTimeFrom
	 * @param PermissionTimeTo
	 * @author Kishore-RMITS
	 */
	public void permissionTimeFromEntry(String PermissionTimeFrom, String PermissionTimeTo,
			String PermissionReference) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionTimeFromField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionTimeFromField, ElementType.Xpath);
			webDB.sendTextToAnElement(AttendanceLeaveLocators.EmployeePermissionTimeFromField, PermissionTimeFrom,
					ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionTimeToField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Permission Time From is Entered");
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionTimeToField, ElementType.Xpath);
					webDB.sendTextToAnElement(AttendanceLeaveLocators.EmployeePermissionTimeToField, PermissionTimeTo,
							ElementType.Xpath);
					logs.logging("INFO", "Permission Time To is Entered");
					flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionReasonField,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionReasonField, ElementType.Xpath);
						flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.EmployeePermissionReasonField,
								PermissionReference, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Save And Post Permission Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void permissionSaveAndPostDocument() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionSaveButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(2000);
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionPostButon, ElementType.Xpath);
			logs.logging("INFO", "Permission Entry is Saved");
			if (flag) {
				commonFunc.hardWait(5000);
				webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionPostButon, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionSaveButton, ElementType.Xpath);
				logs.logging("INFO", "Permission Entry is posted");
				if (flag) {
					commonFunc.hardWait(5000);
					webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionEntryFindButton, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindPageEmployeeLOV,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Permission Entry find icon is clicked");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Select the Permission Types
	 * 
	 * @param PermissionType
	 * @author Kishore-RMITS
	 */
	public void permissionSelctType(String PermissionType) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionTypeSelectField, ElementType.Xpath);
		if (flag) {
			webDB.waitForClickElement(AttendanceLeaveLocators.EmployeePermissionTypeSelectField, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.EmployeePermissionTypeSelectField, PermissionType,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "permission type" + PermissionType + "is selected");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to enter On-Duty FromDate
	 * 
	 * @param OnDutyFromDate
	 * @author Kishore-RMITS
	 */
	public void checkOnDutyBasedonDateWise(String OnDutyFromDate) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionFromDateField, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.EmployeePermissionFromDateField, OnDutyFromDate,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "On Duty from date is entered");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to enter permission reference
	 * 
	 * @param PermissionReference
	 * @author Kishore-RMITS
	 */
	public void permissionReference(String PermissionReference) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionReasonField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionReasonField, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.EmployeePermissionReasonField, PermissionReference,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "On Duty Reference is entered");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Forward the permission Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void permissionSaveAndForwardDocument() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				webDB.waitForElement(AttendanceLeaveLocators.PermissionDocumentNo, ElementType.Xpath);
				String PermssionDocNo = webDB.getDomPropertyFromElement(AttendanceLeaveLocators.PermissionDocumentNo,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				VariableHandler.setPermssionDocNo(PermssionDocNo);
				logs.logging("info", "Permssion No is:" + VariableHandler.getPermissionDocNo());
				flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionPostButon, ElementType.Xpath);
				logs.logging("INFO", "Permission Entry is Saved");
				if (flag) {
					commonFunc.hardWait(3000);
					webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionPostButon, ElementType.Xpath);
					logs.logging("INFO", "Post Button Is Clicked Sucessfully");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Forward the permission Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void permissionReverseDocument() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick, ElementType.CssSelector);
		if (flag) {
			commonFunc.hardWait(3000);
			webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocumentNoClick, ElementType.CssSelector);
		}
	}

	/**
	 * This method is used to Select the Forward User for Permission Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public void verifyWaitingForApproval() {
		flag = webDB.waitForElementUntilGivenTime(AttendanceLeaveLocators.PermissionWorkApprovalFrame,
				ElementType.Xpath, 45);
		if (flag) {
			webDB.switchToFrame(AttendanceLeaveLocators.PermissionWorkApprovalFrame, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval,
					commonFunc.getDataFromTestDataSheet("KishoreValue"));
			logs.logging("INFO", "Forward User is Selected");
			webDB.switchToFrame(AttendanceLeaveLocators.PermissionWorkApprovalFrame, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.WorkApprovalOkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.WorkApprovalOkButton, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Select the Permission request Document no to Search
	 * 
	 * @param Number
	 * @author Kishore-RMITS
	 */
	public void filterDocumentsforPermissionApproval(String Number) {
		flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Search Box IS Displayed");
			webDB.clickAnElement(ApprovalPageLocators.SearchBoxContainer, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
			if (flag) {
				webDB.clearTextField(ApprovalPageLocators.SearchBox, ElementType.Xpath);
				logs.logging("INFO", "Clear Text Search Box");
				webDB.sendTextToAnElement(ApprovalPageLocators.SearchBox, Number, ElementType.Xpath);
				logs.logging("INFO", "Entered Document Number Is: " + Number);
				flag = webDB.waitForClickElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
					flag = webDB.waitForElement(ApprovalPageLocators.SearchTableResult, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search Go Button Is Clicked");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Return Permission Entry from Approval Screen
	 * 
	 * @param Number
	 * @author Kishore-RMITS
	 */
	public void verifyReturnWithDocNumber(String Number) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
			flag = webDB.waitForClickElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CheckBox Is Clicked And Process Button IS Displayed");
				webDB.javaScriptClickAnElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.ApprovalReturnButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"CheckBox select for Document Number " + Number + " and Process button clicked");
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(AttendanceLeaveLocators.ApprovalReturnButton, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.ReturnUserLOV,
							commonFunc.getDataFromTestDataSheet("ReturnUser"));
					flag = webDB.waitForElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
					commonFunc.hardWait(1000);
					if (flag) {
						logs.logging("INFO", "Approval Process Button IS Displayed");
						webDB.clickAnElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
						flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Toast Message Displayed, Posted Successfully");
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Create Encashment Adding and Request Date Entry
	 * 
	 * @param EncashmentRequestDate
	 * @author Kishore-RMITS
	 */
	public void EncashmentEntry(String EncashmentRequestDate) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Encashment Button is Clicked");
			webDB.clickAnElement(AttendanceLeaveLocators.EncashmentButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentAddButon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Encashment Add Button is Clicked");
				webDB.clickAnElement(AttendanceLeaveLocators.EncashmentAddButon, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentRequestDateField, ElementType.Xpath);
				if (flag) {
					webDB.clearText(AttendanceLeaveLocators.EncashmentRequestDateField, ElementType.Xpath);
					webDB.sendTextToAnElement(AttendanceLeaveLocators.EncashmentRequestDateField, EncashmentRequestDate,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Encashment Request Date " + EncashmentRequestDate + " is Entered");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used Select the Employee From Employee LOV Field
	 * 
	 * @param EmployeeDurai
	 * @author Kishore-RMITS
	 */
	public void EncashmentEmployeeLOVEntry(String EmployeeDurai) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.EncashmentEmployeeLOV, EmployeeDurai);
		if (flag) {
			logs.logging("INFO", "Employee" + EmployeeDurai + "is selected");
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used Select the Leave Type From Leave LOV Field
	 * 
	 * @param EncashmentLeaveLOV
	 * @author Kishore-RMITS
	 */
	public void EncashmentLeaveLOVEntry(String EncashmentLeaveLOV) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.EncashmentLeaveLOV, EncashmentLeaveLOV);
		if (flag) {
			logs.logging("INFO", "Employee" + EncashmentLeaveLOV + "is selected");
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Enter Day and Reason Field Entry
	 * 
	 * @param EncashDaysField
	 * @param EncashReasonField
	 * @author Kishore-RMITS
	 */
	public void EncashmentDaysEntry(String EncashDaysField, String EncashReasonField) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EncashDaysField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(AttendanceLeaveLocators.EncashDaysField, EncashDaysField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Encashment Days" + EncashDaysField + " is Entered");
				flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentReasonField, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(AttendanceLeaveLocators.EncashmentReasonField, EncashReasonField,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Encashment Reason " + EncashReasonField + " is Entered");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Save and Post the Encashment Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void EncashmentSaveAndPostDocument() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EncashmentSaveButton, ElementType.Xpath);
			logs.logging("INFO", "Encashment Entry is Saved");

			flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentViewAmount, ElementType.Xpath);
			if (flag) {
				String EncashmentDocNo = webDB.getDomPropertyFromElement(AttendanceLeaveLocators.EncashmentDocNumber,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				VariableHandler.setEncashmentDocNo(EncashmentDocNo);
				System.out.println(EncashmentDocNo);
				logs.logging("info", "Permssion No is:" + VariableHandler.getEncashmentDocNo());
				flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentViewAmount, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.EncashmentViewAmount, ElementType.Xpath);
					logs.logging("INFO", "Encashment Amount is Viewed");
					commonFunc.hardWait(5000);
					webDB.pressEscapeKey();
//					webDB.clickAnElement(AttendanceLeaveLocators.EncashmentViewAmountClose, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentPostButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.EncashmentPostButton, ElementType.Xpath);
						logs.logging("INFO", "Encashment Entry is posted");
						commonFunc.hardWait(12000);
						flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentFindIcon, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AttendanceLeaveLocators.EncashmentFindIcon, ElementType.Xpath);
							logs.logging("INFO", "Encashment Entry Find Icon is Clicked");
							flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentFindPageEmployeeLOV,
									ElementType.Xpath);
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Create Pending Encashment Leave
	 * 
	 * @author Kishore-RMITS
	 */
	public void CheckLeaveEncashmentBasedOnPendingEncashment(String Number) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EncashmentButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Encashment Button is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.PendingEncashmentButton, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					webDB.clickAnElement(AttendanceLeaveLocators.PendingEncashmentButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Pending Enashment Button is Clicked");
						webDB.switchToFrame(AttendanceLeaveLocators.PendingEncashmentscreen, ElementType.Xpath);
						flag = webDB.waitForElement(AttendanceLeaveLocators.PendingEncashmentSelectFlagIcon,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AttendanceLeaveLocators.PendingEncashmentSelectFlagIcon,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Pending Encashment Doc No Flag Is Checked");
								flag = webDB.waitForElement(AttendanceLeaveLocators.CreateEncashmentLeave,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(2000);
									webDB.clickAnElement(AttendanceLeaveLocators.CreateEncashmentLeave,
											ElementType.Xpath);
									logs.logging("INFO", "Encashment Leave Created");
									flag = webDB.waitForElement(AttendanceLeaveLocators.CreateEncashmentOkButton,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Select Encashment Based on Payroll
	 * 
	 * @author Kishore-RMITS
	 */
	public void CheckLeaveEncashmentBasedOnEncashInPayroll() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.CreateEncashmentOkButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.CreateEncashmentOkButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Encashment OK Button Is Clicked");
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentFindPageEmployeeLOV, ElementType.Xpath);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Forward The Encashment Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void EncashmentForward() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EncashmentSaveButton, ElementType.Xpath);
			logs.logging("INFO", "Encashment Entry is Saved");

			flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentViewAmount, ElementType.Xpath);
			if (flag) {
				String EncashmentDocNo = webDB.getDomPropertyFromElement(AttendanceLeaveLocators.EncashmentDocNumber,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				VariableHandler.setEncashmentDocNo(EncashmentDocNo);
				System.out.println(EncashmentDocNo);
				logs.logging("info", "Permssion No is:" + VariableHandler.getEncashmentDocNo());
				flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentViewAmount, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.EncashmentViewAmount, ElementType.Xpath);
					logs.logging("INFO", "Encashment Amount is Viewed");
					commonFunc.hardWait(5000);
					webDB.clickAnElement(AttendanceLeaveLocators.EncashmentViewAmountClose, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentPostButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.EncashmentPostButton, ElementType.Xpath);
						logs.logging("INFO", "Encashment Entry is posted");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Cancel The Encashment Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void EncashmentCancel() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EncashmentButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Encashment Button is Clicked");
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentFindSearchButon, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(1000);
					webDB.clickAnElement(AttendanceLeaveLocators.EncashmentFindSearchButon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Encashment Search Button is Clicked");
						flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentFindDocSelect,
								ElementType.CssSelector);
						if (flag) {
							commonFunc.hardWait(1000);
							webDB.clickAnElement(AttendanceLeaveLocators.EncashmentFindDocSelect,
									ElementType.CssSelector);
							if (flag) {
								logs.logging("INFO", "Encashment Doc is Selected");
								flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentCancelButton,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(1000);
									webDB.clickAnElement(AttendanceLeaveLocators.EncashmentCancelButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Encashment Document Cancelled Successfully");
										commonFunc.hardWait(2000);
										flag = commonFunc.logoutUser();
									}
									Assert.assertTrue(flag);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This method is used to Check the CompOff Entry
	 *
	 * @author Kishore-RMITS
	 */
	public void CompOffEntry() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffButtonClick, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.CompOffButtonClick, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.CompOffButtonClick, ElementType.Xpath);
			commonFunc.hardWait(1000);
			logs.logging("INFO", "Permission Button Is Clicked");
			flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffFindAddIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.CompOffFindAddIcon, ElementType.Xpath);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Check the CompOff Date wise
	 * 
	 * @param PermFromDate2
	 * @param CompOffDays
	 * @param CompOffReferenceField
	 * @author Kishore-RMITS
	 */
	public void checkCompOffBasedonDatewise(String PermFromDate2, String CompOffDays, String CompOffReferenceField) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffDateField, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(1000);
			flag = webDB.clickAnElement(AttendanceLeaveLocators.CompOffDateField, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffDateFieldToday, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(1000);
				flag = webDB.clickAnElement(AttendanceLeaveLocators.CompOffDateFieldToday, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Comp-Off From Date is Entered");
					flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffDaysField, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(1000);
						webDB.clearTextField(AttendanceLeaveLocators.CompOffDaysField, ElementType.Xpath);
						webDB.sendTextToAnElement(AttendanceLeaveLocators.CompOffDaysField, CompOffDays,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Employee CompOff days " + CompOffDays + " is Entered");
							flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffReferenceField,
									ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(AttendanceLeaveLocators.CompOffReferenceField,
										CompOffReferenceField, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Encashment Reason " + CompOffReferenceField + " is Entered");
								}
							}
						}
					}

				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Save and Post the CompOff Entry
	 * 
	 * @author Kishore-RMITS
	 */
	public void CompOffEntrySaveAndPostDocument() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffEntrySaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.CompOffEntrySaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				webDB.waitForElement(AttendanceLeaveLocators.CompOffDocumentNo, ElementType.Xpath);
				String CompOffDocNo = webDB.getDomPropertyFromElement(AttendanceLeaveLocators.CompOffDocumentNo,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				VariableHandler.setCompOffDocNo(CompOffDocNo);
				logs.logging("info", "CompOff No is:" + VariableHandler.getCompOffDocNo());
				flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffEntryPostIcon, ElementType.Xpath);
				logs.logging("INFO", "CompOff Entry is Saved");
				if (flag) {
					commonFunc.hardWait(3000);
					webDB.clickAnElement(AttendanceLeaveLocators.CompOffEntryPostIcon, ElementType.Xpath);
					logs.logging("INFO", "Post Button Is Clicked Sucessfully");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Check Loan Request based on Required Date
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckLoanRequestBasedOnRequiredDate() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToLoanRequestScreen();
		LoanRequestEntry(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("EmployeeDurai"), commonFunc.getDataFromTestDataSheet("LoanTypes"),
				commonFunc.getDataFromTestDataSheet("LoanReference"));
		LoanRequiredDetails(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("LoanAmount"),
				commonFunc.getDataFromTestDataSheet("LoanInstallmentAmount"));
		LoanRequestDocumentProcess();
//		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
//		approvalFunc.navigateToApprovals();
//		filterDocumentsforPermissionApproval(VariableHandler.getLoanRequestDocNo());
//		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getLoanRequestDocNo());
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getLoanRequestDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	// --------------- "Leave Module Test Scenarios Functions" -----------------

	/**
	 * This Method used To Check Leave Entry Based on the Request date, To Check
	 * Leave Entry Based on Leave types in Leave Master, To check Leave Entry Based
	 * on the Leave Balances, To Check Leave Entry Based on different Start session
	 * and End session
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyLeaveEntryDateTypeBalanceSession() {
		LocalDate date = LocalDate.now();
		LocalDate date1 = LocalDate.now().plusMonths(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String requestDate = date1.format(formatter);
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEncashmentScreen();
			chooseLeaveAndClickAddLeaveIcon();
			fillRequestDateEmpIdAndLeaveType(Today, commonFunc.getDataFromTestDataSheet("Emp_GWP0001"),
					commonFunc.getDataFromTestDataSheet("CL-Text"));
			selectStartDateAndSessionWithAppliedDays(requestDate,
					commonFunc.getDataFromTestDataSheet("FullSessionText"), "1");
			selectEndDateAndSessionWithReason(requestDate, commonFunc.getDataFromTestDataSheet("FullSessionText"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			saveLeaveEntryAndStoreLeaveRequestNo();
			clickPostButtonInLeaveRequest();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("ROAUAdminEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getLeaveRequestNo(),
						webDB.getDataFromProperties("ROAUAdminEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigatePayrollApplicationScreen();
			verifyAutoPostLeaveEntryInLeaveSetupScreen();
			if (!VariableHandler.getEmpLeaveEntryAutoPost()) {
				commonFunc.navigateLeaveEntryScreen();
				clickFetchButtonAndSearchLeaveRequestNo(VariableHandler.getLeaveRequestNo());
				selectLeaveRequestAndClickCreateLeave(VariableHandler.getLeaveRequestNo());
				clickSearchAndFilterLeaveEntryDocNo(VariableHandler.getLeaveEntryDocNo());
				clickSaveAndPostInLeaveEntry();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(System.getProperty("HariUsername"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
							VariableHandler.getLeaveEntryDocNo(), webDB.getDataFromProperties("ROAUAdminEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
//				commonFunc.navigateLeaveEntryScreen();
//				clickSearchAndFilterLeaveEntryDocNo(VariableHandler.getLeaveEntryDocNo());
				clickReverseAndVerifyStatusInLeveEntry();
				flag = commonFunc.logoutUser();
			} else if (VariableHandler.getEmpLeaveEntryAutoPost()) {
				commonFunc.navigateLeaveEntryScreen();
				clickSearchAndClickFirstLeaveEntryWithEmpName(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
				clickReverseAndVerifyStatusInLeveEntry();
				flag = commonFunc.logoutUser();
			}
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyLeaveEntryDateTypeBalanceSession': " + e);
			return false;
		}
	}

	/**
	 * This Method used To Check Leave LOP Upload Migration with Templates
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyLeaveLOPUploadMigrationWithTemplates() {
		LocalDate date1 = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		String Month = date1.format(formatter).toUpperCase();
		String requestDay = date1.format(formatter1);
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEncashmentScreen();
			clickMigrationAndSelectLOPLeave();
			clickAddNewLOPLeaveButton();
			selectCalendarYearPeriodAndRefInLOP(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("Year202526"), Month,
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			uploadTemplateInLOPLeaveMigration(requestDay);
			checkExceptionAndSaveHeaderInLOPMigration();
			clickPostLOPLeaveButton();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("ROAUAdminEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getLeaveEntryDocNo(), webDB.getDataFromProperties("ROAUAdminEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateLeaveEntryScreen();
			webDB.navigateToRefresh();
			commonFunc.hardWait(2000);
			clickSearchAndClickFirstLeaveEntryWithEmpName(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			clickReverseAndVerifyStatusInLeveEntry();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyLeaveLOPUploadMigrationWithTemplates': " + e);
			return false;
		}
	}

	/**
	 * This Method used To Check Leave Accrual Upload Migration with Templates
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyLeaveAccrualUploadMigrationWithTemplates() {
		LocalDate date1 = LocalDate.now();
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMMM");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String Month = date1.format(formatter1).toUpperCase();
		String requestDate = date1.format(formatter);
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEncashmentScreen();
			clickMigrationAndSelectAccrualLeave();
			clickAddNewAccrualLeaveButton();
			selectCalendarYearPeriodAndRefInAccrual(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("Year202526"), Month,
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			uploadTemplateInAccrualLeaveMigration(requestDate);
			checkExceptionAndSaveHeaderInAccrualMigration();
			clickPostAccrualLeaveButton();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("ROAUAdminEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getLeaveEntryDocNo(), webDB.getDataFromProperties("ROAUAdminEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateLeaveEntryScreen();
			webDB.navigateToRefresh();
			commonFunc.hardWait(2000);
			clickSearchAndClickFirstLeaveEntryWithEmpName(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			clickReverseAndVerifyStatusInLeveEntry();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyLeaveAccrualUploadMigrationWithTemplates': " + e);
			return false;
		}
	}

	/**
	 * This Method used to Cancel the leave data and checking the Leave status
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCancelLeaveCheckLeaveStatus() {
		LocalDate date = LocalDate.now();
		LocalDate date1 = LocalDate.now().plusDays(2);
		LocalDate date2 = LocalDate.now().plusDays(2).plusDays(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String requestDate = date1.format(formatter);
		String AfterThreeDate = date2.format(formatter);
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEntryScreen();
			chooseLeaveAndClickAddLeaveIconInLE();
			fillEntryDateEmpIdAndLeaveType(Today, commonFunc.getDataFromTestDataSheet("Emp_GWP0001"),
					commonFunc.getDataFromTestDataSheet("CL-Text"));
			selectStartDateAndSessionWithAppliedDaysInLE(requestDate,
					commonFunc.getDataFromTestDataSheet("FullSessionText"), "3");
			selectEndDateAndSessionWithReasonInLE(AfterThreeDate,
					commonFunc.getDataFromTestDataSheet("FullSessionText"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			saveLeaveEntryAndStoreLeaveEntryNo();
			clickAttachmentInLeaveEntry();
			fillDocTypeDocNoDocRevInLeaveEntryAttachment(commonFunc.getDataFromTestDataSheet("ApplicationOption"),
					Today, "0");
			fillFromToDateAndFileUploadInLeaveEntryAttachment(Today, Today,
					VariableHandler.testdataDir + webDB.getDataFromProperties("RoadMapImage"));
			clickUploadButtonInAttachmentLeaveEntry();
			clickReportAndVerifyDisplayedInLeaveEntry();
			clickSaveAndCancelInLeaveEntry();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCancelLeaveCheckLeaveStatus': " + e);
			return false;
		}
	}

	/**
	 * This method used to select leave option and click add leave icon
	 * 
	 * @author Sakthi-GWL
	 */
	private void chooseLeaveAndClickAddLeaveIcon() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveRequest, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveRequest, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveRequest, ElementType.Xpath);
			logs.logging("INFO", "Leave Button is Clicked");
			flag = webDB.waitForClickElement(AttendanceLeaveLocators.LeaveAddButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(AttendanceLeaveLocators.LeaveAddButton, ElementType.Xpath);
				webDB.clickAnElement(AttendanceLeaveLocators.LeaveAddButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestEmpIdLovBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Leave Add Button Is Clicked");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Leave Request Screen
	 * 
	 * @author Sakthi-GWL
	 * @param Date
	 * @param EmpId
	 * @param LeaveType
	 */
	private void fillRequestDateEmpIdAndLeaveType(String Date, String EmpId, String LeaveType) {
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LeaveRequestDateInput,
				AttendanceLeaveLocators.LeaveRequestDateInput, Date);
		logs.logging("INFO", "Leave Request Date Entered: " + Date);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LeaveRequestEmpIdLovBtn, EmpId);
		logs.logging("INFO", "Leave Emp Id Selected: " + EmpId);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LeaveRequestLeaveTypeLovBtn, LeaveType);
		logs.logging("INFO", "Leave Type Selected: " + LeaveType);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestBalanceDisplay, ElementType.Xpath);
		if (flag) {
			String balance = webDB.getTextFromElement(AttendanceLeaveLocators.LeaveRequestBalanceDisplay,
					ElementType.Xpath);
			VariableHandler.setCasualLeaveBalance(balance);
			logs.logging("INFO", "Leave Balance Displayed: " + balance);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select start date and session and Applied Days in Leave
	 * Request
	 * 
	 * @author Sakthi-GWL
	 * @param Date
	 * @param Session
	 * @param AppliedDays
	 */
	private void selectStartDateAndSessionWithAppliedDays(String Date, String Session, String AppliedDays) {
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LeaveRequestStartDateInput,
				AttendanceLeaveLocators.LeaveRequestStartDateInput, Date);
		logs.logging("INFO", "Leave Start Date Entered: " + Date);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestStartSessionSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.LeaveRequestStartSessionSelect, Session,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Start Session Selected: " + Session);
				flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LeaveRequestAppliedDaysInput, AppliedDays,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Applied Date Entered: " + AppliedDays);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select leave end date and session and Reason in Leave
	 * Request
	 * 
	 * @author Sakthi-GWL
	 * @param Date
	 * @param Session
	 * @param Reason
	 */
	private void selectEndDateAndSessionWithReason(String Date, String Session, String Reason) {
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LeaveRequestEndDateInput,
				AttendanceLeaveLocators.LeaveRequestEndDateInput, Date);
		logs.logging("INFO", "Leave End Date Entered: " + Date);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestEndSessionSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.LeaveRequestEndSessionSelect, Session,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave End Session Selected: " + Session);
				flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LeaveRequestLeaveReason, Reason,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Leave Reason Entered: " + Reason);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to save header and store leave request number in leave
	 * request screen
	 * 
	 * @author Sakthi-GWL
	 */
	private void saveLeaveEntryAndStoreLeaveRequestNo() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveRequestHeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveRequestHeaderSaveIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestNumberDisplay, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				logs.logging("INFO", "Header Saved Successfully in Leave Request");
				String ReqNo = webDB.getDomPropertyFromElement(AttendanceLeaveLocators.LeaveRequestNumberDisplay,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
//				logs.logging("INFO", "Before Substring total text Displayed: " + totalText);
//				int startIndex = totalText.indexOf(": ") + 1;
//				int endIndex = totalText.indexOf("(", startIndex) - 1;
//				String ReqNo = (totalText.substring(startIndex, endIndex)).trim();
				logs.logging("INFO", "Displayed Request No: " + ReqNo);
				VariableHandler.setLeaveRequestNo(ReqNo);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click post icon in leave request
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPostButtonInLeaveRequest() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestPostBtn, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(AttendanceLeaveLocators.LeaveRequestPostBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveRequestPostBtn, ElementType.Xpath);
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Leave Request Posted Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to switch pending request frame in leave entry
	 * 
	 * @author Sakthi-GWL
	 */
	private void switchPendingRequestIframeLeaveEntry() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryPendingRequestIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(AttendanceLeaveLocators.LeaveEntryPendingRequestIframe, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryPendReqSearchBar, ElementType.Xpath);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click fetch button and search leave request number in
	 * pending leave request screen
	 * 
	 * @author Sakthi-GWL
	 * @param LeaveRequestNo
	 */
	private void clickFetchButtonAndSearchLeaveRequestNo(String LeaveRequestNo) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveEntry, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveEntry, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFetchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryFetchBtn, ElementType.Xpath);
				switchPendingRequestIframeLeaveEntry();
				logs.logging("INFO", "Leave Entry Fetch Button Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryPendReqSearchBar, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(AttendanceLeaveLocators.LeaveEntryPendReqSearchBar, LeaveRequestNo,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryPendReqSearchGoBtn,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryPendReqSearchGoBtn, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryPendingRequestIframe,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Searched Leave Request No in Search bar");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select leave request number and click create leave in
	 * pending leave request screen
	 * 
	 * @author Sakthi-GWL
	 * @param LeaveRequestNo
	 */
	private void selectLeaveRequestAndClickCreateLeave(String LeaveRequestNo) {
		switchPendingRequestIframeLeaveEntry();
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryPendReqCheckBox(LeaveRequestNo),
				ElementType.Xpath);
		if (flag) {
			webDB.waitUntilElementToNotBeStale(AttendanceLeaveLocators.LeaveEntryPendReqCheckBox(LeaveRequestNo),
					ElementType.Xpath);
			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryPendReqCheckBox(LeaveRequestNo), ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryPendReqCheckBox(LeaveRequestNo), ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Leave Request Number '" + LeaveRequestNo + "' CheckBox Clicked");
			flag = webDB.isElementEnabled(AttendanceLeaveLocators.LeaveEntryPendReqCreateLeaveBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryPendReqCreateLeaveBtn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Create Leave Button Clicked");
					String DisplayedToastMsg = webDB.getTextFromElement(CommonUiLocators.PostToastMessage,
							ElementType.Xpath);
					logs.logging("INFO", "Total Text in Toast Message");
					String DocNo = (DisplayedToastMsg.split("No. : ")[1]);
					logs.logging("INFO", "After Splited Doc Number from Total Text: " + DocNo);
					VariableHandler.setLeaveEntryDocNo(DocNo);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to search and filter using search bar and enter leave entry
	 * with leave entry document number
	 * 
	 * @author Sakthi-GWL
	 * @param LeaveEntryDocNo
	 */
	private void clickSearchAndFilterLeaveEntryDocNo(String LeaveEntryDocNo) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitUntilElementToNotBeStale(AttendanceLeaveLocators.LeaveEntryFindSearchBar, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFindSearchBar, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Entry Search Button Clicked");
				webDB.sendTextToAnElement(AttendanceLeaveLocators.LeaveEntryFindSearchBar, LeaveEntryDocNo,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFindSearchGoBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryFindSearchGoBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					logs.logging("INFO", "Leave Entry Search Bar Go Button Clicked");
					flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntrySearchDocNo(LeaveEntryDocNo),
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Leave Entry Document Number Displayed");
						webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntrySearchDocNo(LeaveEntryDocNo),
								ElementType.Xpath);
						flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryNumberDisplay, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Leave Entry Document Number Clicked and Navigated");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to search and filter using Emp Name and click with First
	 * document number
	 * 
	 * @author Sakthi-GWL
	 * @param LeaveEntryDocNo
	 */
	private void clickSearchAndClickFirstLeaveEntryWithEmpName(String EmpName) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LeaveEntryFindEmpIdLovBtn, EmpName);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntrySearchFirstEmpName(EmpName),
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Entry Document Number Displayed");
				webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntrySearchFirstEmpName(EmpName), ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryNumberDisplay, ElementType.Xpath);
				if (flag) {
					String DocNo = webDB.getTextFromElement(AttendanceLeaveLocators.LeaveEntryNumberDisplay,
							ElementType.Xpath);
					VariableHandler.setLeaveEntryDocNo(DocNo);
					logs.logging("INFO", "Leave Entry Document Number Clicked and Navigated");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click header save and post button in leave entry
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveAndPostInLeaveEntry() {
//		flag = webDB.waitForClickElement(AttendanceLeaveLocators.LeaveEntryHeaderSaveIcon, ElementType.Xpath);
//		if (flag) {
//			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryHeaderSaveIcon, ElementType.Xpath);
//			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryHeaderSaveIcon, ElementType.Xpath);
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.LeaveEntryPostBtn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Save Icon Clicked");
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryPostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Icon Clicked");
				String DisplayText = webDB.getTextFromElement(AttendanceLeaveLocators.LeaveEntryDisplayDocNoAndStatus,
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Leave Entry Number And Status: " + DisplayText);
				flag = DisplayText.contains("Posted");
			}
		}
//		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click migration and select accrual leave type
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickMigrationAndSelectAccrualLeave() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestMigrationBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveRequestMigrationBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveRequestMigrationBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestMigrationIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Request Migration Clicked Successfully");
				webDB.switchToFrame(AttendanceLeaveLocators.LeaveRequestMigrationIframe, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccrualLeaveRadioBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationAccrualLeaveRadioBtn, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccrualAddNewBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Leave Request Migration Accrual Leave Clicked Successfully");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add new leave button in accrual leave migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddNewAccrualLeaveButton() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.LRMigrationAccrualAddNewBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LRMigrationAccrualAddNewBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationAccrualAddNewBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccrualReferenceInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - Accrual Leave Add Button is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select calendar, year, reference and period in accrual
	 * leave migration
	 * 
	 * @author Sakthi-GWL
	 * @param calendar
	 * @param year
	 * @param period
	 * @param ref
	 */
	private void selectCalendarYearPeriodAndRefInAccrual(String calendar, String year, String period, String ref) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LRMigrationAccrualCalendarLovBtn, calendar);
		logs.logging("INFO", "Migration - Accrual Leave Calendar Selected: " + calendar);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LRMigrationAccrualYearLovBtn, year);
		logs.logging("INFO", "Migration - Accrual Leave Year Selected: " + year);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LRMigrationAccrualPeriodLovBtn, period);
		logs.logging("INFO", "Migration - Accrual Leave Period Selected: " + period);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccrualReferenceInput, ElementType.Xpath);
		if (flag) {
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LRMigrationAccrualReferenceInput, ref,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - Accrual Leave Reference Entered: " + ref);
				flag = webDB.waitForClickElement(AttendanceLeaveLocators.LRMigrationAccrualCreateSaveIcon,
						ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(AttendanceLeaveLocators.LRMigrationAccrualCreateSaveIcon, ElementType.Xpath);
					webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationAccrualCreateSaveIcon, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccrualUploadIcon,
							ElementType.Xpath);
					if (flag) {
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						logs.logging("INFO", "Save Icon Clicked in Accrual Leave Migration and Created new Document");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to upload template in accrual leave migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void uploadTemplateInAccrualLeaveMigration(String date) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccrualUploadIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LRMigrationAccrualUploadIcon, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationAccrualUploadIcon, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccrualUploadInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Upload Icon Clicked and Input Box Displayed");
				String file1 = webDB.getDataFromProperties("AccrualLeaveMigrationFileName");
				String Path = VariableHandler.testdataDir + file1;
				ExcelUtils excel = new ExcelUtils();
				excel.writeDataInExcel(Path, "Sheet1", 1, 3, date);
				File file = new File(VariableHandler.testdataDir + file1);
				flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LRMigrationAccrualUploadInput,
						file.getAbsolutePath(), ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForClickElement(AttendanceLeaveLocators.LRMigrationAccrualUploadBtn,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationAccrualUploadBtn, ElementType.Xpath);
						commonFunc.hardWait(2000);
						flag = webDB.isElementDisplayed(AttendanceLeaveLocators.LRMigrationAccrualCheckExcptionBtn,
								ElementType.Xpath)
								&& webDB.isElementNotDisplayed(AttendanceLeaveLocators.LRMigrationAccrualUploadBtn,
										ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "File Uploaded Succesfully for Accrual Leave Migration");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to check Exception and Save Header in Accrual Migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void checkExceptionAndSaveHeaderInAccrualMigration() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccrualCheckExcptionBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationAccrualCheckExcptionBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Check Exception Button Clicked");
				String toastText = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				flag = !toastText.contains("Exception exists");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Displayed Toast Text for Check Exception: " + toastText);
				if (flag) {
					logs.logging("INFO", "No Exception Found for LOP Migration");
//					webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationAccrualHeaderSaveIcon, ElementType.Xpath);
//					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
//					if (flag) {
//						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
//							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
//						}
//						logs.logging("INFO", "Header Saved Succesfully for Accrual Leave Migration");
					flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationAccuralDisplayDocNo,
							ElementType.Xpath);
					if (flag) {
						String DisplayDocNo = webDB.getAttributeFromElement(
								AttendanceLeaveLocators.LRMigrationAccuralDisplayDocNo, ElementType.Xpath,
								commonFunc.getDataFromTestDataSheet("Value"));
						logs.logging("INFO", "Lop Migration Doc No: " + DisplayDocNo);
						VariableHandler.setLeaveEntryDocNo(DisplayDocNo);
					}
				}
//				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click post button in accrual leave migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPostAccrualLeaveButton() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.LRMigrationAccrualPostBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LRMigrationAccrualPostBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationAccrualPostBtn, ElementType.Xpath);
//			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Migration - Accrual Leave Posted Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click migration and select LOP leave type
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickMigrationAndSelectLOPLeave() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestMigrationBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveRequestMigrationBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveRequestMigrationBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRequestMigrationIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Request Migration Clicked Successfully");
				webDB.switchToFrame(AttendanceLeaveLocators.LeaveRequestMigrationIframe, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPRadioBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationLOPRadioBtn, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPAddNewBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Leave Request Migration LOP Leave Clicked Successfully");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add new leave button in LOP leave migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddNewLOPLeaveButton() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.LRMigrationLOPAddNewBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LRMigrationLOPAddNewBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationLOPAddNewBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPReferenceInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - LOP Leave Add Button is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select calendar, year, reference and period in LOP leave
	 * migration
	 * 
	 * @author Sakthi-GWL
	 * @param calendar
	 * @param year
	 * @param period
	 * @param ref
	 */
	private void selectCalendarYearPeriodAndRefInLOP(String calendar, String year, String period, String ref) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LRMigrationLOPCalendarLovBtn, calendar);
		logs.logging("INFO", "Migration - LOP Leave Calendar Selected: " + calendar);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LRMigrationLOPYearLovBtn, year);
		logs.logging("INFO", "Migration - LOP Leave Year Selected: " + year);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LRMigrationLOPPeriodLovBtn, period);
		logs.logging("INFO", "Migration - LOP Leave Period Selected: " + period);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPReferenceInput, ElementType.Xpath);
		if (flag) {
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LRMigrationLOPReferenceInput, ref,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - LOP Leave Reference Entered: " + ref);
				flag = webDB.waitForClickElement(AttendanceLeaveLocators.LRMigrationLOPCreateSaveIcon,
						ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(AttendanceLeaveLocators.LRMigrationLOPCreateSaveIcon, ElementType.Xpath);
					webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationLOPCreateSaveIcon, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPUploadIcon, ElementType.Xpath);
					if (flag) {
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						logs.logging("INFO", "Save Icon Clicked in LOP Leave Migration and Created new Document");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to upload template in LOP leave migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void uploadTemplateInLOPLeaveMigration(String date) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPUploadIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LRMigrationLOPUploadIcon, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationLOPUploadIcon, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPUploadInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Upload Icon Clicked and Input Box Displayed");
				String file1 = webDB.getDataFromProperties("LOPLeaveMigrationFileName");
				String Path = VariableHandler.testdataDir + file1;
				ExcelUtils excel = new ExcelUtils();
				excel.writeDataInExcel(Path, "Sheet1", 1, 3, date);
				excel.writeDataInExcel(Path, "Sheet1", 1, 4, date);
				File file = new File(Path);
				flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LRMigrationLOPUploadInput,
						file.getAbsolutePath(), ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForClickElement(AttendanceLeaveLocators.LRMigrationLOPUploadBtn,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationLOPUploadBtn, ElementType.Xpath);
						commonFunc.hardWait(2000);
						flag = webDB.isElementDisplayed(AttendanceLeaveLocators.LRMigrationLOPCheckExcptionBtn,
								ElementType.Xpath)
								&& webDB.isElementNotDisplayed(AttendanceLeaveLocators.LRMigrationLOPUploadBtn,
										ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "File Uploaded Succesfully for LOP Leave Migration");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to check Exception and Save Header in LOP Migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void checkExceptionAndSaveHeaderInLOPMigration() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPCheckExcptionBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationLOPCheckExcptionBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Check Exception Button Clicked");
				String toastText = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				flag = !toastText.contains("Exception exists");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Displayed Toast Text for Check Exception: " + toastText);
				if (flag) {
					logs.logging("INFO", "No Exception Found for LOP Migration");
					webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationLOPHeaderSaveIcon, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						logs.logging("INFO", "Header Saved Succesfully for LOP Leave Migration");
						flag = webDB.waitForElement(AttendanceLeaveLocators.LRMigrationLOPDisplayDocNo,
								ElementType.Xpath);
						if (flag) {
							String DisplayDocNo = webDB.getTextFromElement(
									AttendanceLeaveLocators.LRMigrationLOPDisplayDocNo, ElementType.Xpath);
							logs.logging("INFO", "Lop Migration Doc No: " + DisplayDocNo);
							VariableHandler.setLeaveEntryDocNo(DisplayDocNo);
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click post button in LOP leave migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPostLOPLeaveButton() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.LRMigrationLOPPostBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LRMigrationLOPPostBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LRMigrationLOPPostBtn, ElementType.Xpath);
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Migration - LOP Leave Posted Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to save header and cancel leave and verify status
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveAndCancelInLeaveEntry() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryHeaderSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.hardWait(1500);
				commonFunc.checkSuccessToastMessageDisappear();
				flag = webDB.waitForClickElement(AttendanceLeaveLocators.LeaveEntryCancelBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Header Save Icon Clicked");
					commonFunc.checkSuccessToastMessageDisappear();
					webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryCancelBtn, ElementType.Xpath);
					webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryCancelBtn, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.AdjustmentReverseOkBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentReverseOkBtn, ElementType.Xpath);
						flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Cancel Button Icon Clicked");
							String DisplayText = webDB.getTextFromElement(
									AttendanceLeaveLocators.LeaveEntryDisplayDocNoAndStatus, ElementType.Xpath);
							logs.logging("INFO", "Displayed Leave Entry Number And Status: " + DisplayText);
							flag = DisplayText.contains("Cancelled");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to reverse leave entry and verify status
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickReverseAndVerifyStatusInLeveEntry() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryReverseBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryReverseBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryReverseBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryReverseOkBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryReverseOkBtn, ElementType.Xpath);
				webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryReverseOkBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					String DisplayText = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					logs.logging("INFO", "Displayed Leave Entry Number And Status: " + DisplayText);
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					flag = DisplayText.contains("Reversed");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to modify date in LOP Migration Template
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	private void modifyDateInLOPMigrationTemplate(String FileName, String SheetName) {
		try {
			int dateFromColumn = 3;
			int dateToColumn = 4;
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
			String Today = date.format(formatter);
			excelUtils.readAllDataInExcel(FileName, SheetName);
			excelUtils.writeDataInExcel(FileName, SheetName, 1, dateFromColumn, Today);
			excelUtils.writeDataInExcel(FileName, SheetName, 1, dateToColumn, Today);
		} catch (Exception e) {
			logs.logging("INFO", "failed to modify LOP Migration Template with exception: " + e);
			Assert.fail("failed to modify LOP Migration Template with exception: " + e);
		}
	}

	/**
	 * This method used to modify date in Accrual Migration Template
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	private void modifyDateInAccuralMigrationTemplate(String FileName, String SheetName) {
		try {
			int dateFromColumn = 3;
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			String Today = date.format(formatter);
			excelUtils.readAllDataInExcel(FileName, SheetName);
			excelUtils.writeDataInExcel(FileName, FileName, 1, dateFromColumn, Today);
		} catch (Exception e) {
			logs.logging("INFO", "failed to modify Accrual Migration Template with exception: " + e);
			Assert.fail("failed to modify Accrual Migration Template with exception: " + e);
		}
	}

	/**
	 * This method used to search Emp id and Leave type in Find Page and Store last
	 * posted Doc No
	 * 
	 * @author Sakthi-GWL
	 * @param Leave
	 * @param EmpId
	 */
	private void searchEmpIdLeaveTypeAndStoreLeaveEntryDocNo(String Leave, String EmpId, String requestDay) {
//		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFindLeaveCategorySelect, ElementType.Xpath);
//		if (flag) {
//			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.LeaveEntryFindLeaveCategorySelect, Leave,
//					ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Leave Type Selected: " + Leave);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LeaveEntryFindEmpIdLovBtn, EmpId);
		logs.logging("INFO", "Emp Id Selected: " + EmpId);
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LeaveEntryFindDateFromInput,
				AttendanceLeaveLocators.LeaveEntryFindDateFromInput, requestDay);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryDocNoWithPostedStatus, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked and Post Status Doc No Displayed");
				String DisplayedDocNo = webDB
						.getTextFromElement(AttendanceLeaveLocators.LeaveEntryDocNoWithPostedStatus, ElementType.Xpath);
				VariableHandler.setLeaveEntryDocNo(DisplayedDocNo);
				logs.logging("INFO", "Leave Entry Displayed Doc No:" + DisplayedDocNo);
			}
		}
//			}
//		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click attachment icon in Leave Entry
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAttachmentInLeaveEntry() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryAttachmentBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryAttachmentBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryAttachmentBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Entry Attachment Icon Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill Doc Type , Doc No and Doc Rev in Leave Entry
	 * Attachment
	 * 
	 * @author Sakthi-GWL
	 * @param DocType
	 * @param DocNo
	 * @param DocRev
	 */
	private void fillDocTypeDocNoDocRevInLeaveEntryAttachment(String DocType, String DocNo, String DocRev) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LEAttachmentDocTypeLovBtn, DocType);
			logs.logging("INFO", "Leave Entry Attachment Doc Type Selected: " + DocType);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
				commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LEAttachmentDocNoInput,
						AttendanceLeaveLocators.LEAttachmentDocNoInput, DocNo);
				logs.logging("INFO", "Leave Entry Attachment Doc No Entered: " + DocNo);
				commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LEAttachmentDocRevInput,
						AttendanceLeaveLocators.LEAttachmentDocRevInput, DocRev);
				logs.logging("INFO", "Leave Entry Attachment Doc Rev Entered: " + DocRev);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill From Date To Date and upload file in Leave Entry
	 * Attachment
	 * 
	 * @author Sakthi-GWL
	 * @param FromDate
	 * @param ToDate
	 * @param FileName
	 */
	private void fillFromToDateAndFileUploadInLeaveEntryAttachment(String FromDate, String ToDate, String FileName) {
		File file = new File(FileName);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
			commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LEAttachmentDateFromInput,
					AttendanceLeaveLocators.LEAttachmentDateFromInput, FromDate);
			logs.logging("INFO", "Leave Entry Attachment From Date Entered: " + FromDate);
			commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LEAttachmentDateToInput,
					AttendanceLeaveLocators.LEAttachmentDateToInput, ToDate);
			logs.logging("INFO", "Leave Entry Attachment To Date Entered: " + ToDate);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LEAttachmentChooseFileInput, ElementType.Xpath);
			if (flag) {
				flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LEAttachmentChooseFileInput,
						file.getAbsolutePath(), ElementType.Xpath);
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click upload button in Leave Entry Attachment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickUploadButtonInAttachmentLeaveEntry() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(AttendanceLeaveLocators.LeaveEntryAttachmentIframe, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.LEAttachmentUploadBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(AttendanceLeaveLocators.LEAttachmentUploadBtn, ElementType.Xpath);
				webDB.clickAnElement(AttendanceLeaveLocators.LEAttachmentUploadBtn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(AttendanceLeaveLocators.AttachmentIframeCloseIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.AttachmentIframeCloseIcon, ElementType.Xpath);
					webDB.navigateToRefresh();
					flag = webDB.isElementNotDisplayed(AttendanceLeaveLocators.LeaveEntryAttachmentIframe,
							ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click report button in Leave Entry Attachment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickReportAndVerifyDisplayedInLeaveEntry() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryReportBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryReportBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryReportBtn, ElementType.Xpath);
			commonFunc.hardWait(5000);
			ArrayList<String> tabs = new ArrayList<String>(webDB.getDriver().getWindowHandles());
			flag = tabs.size() > 1;
			logs.loggingconsole("INFO", "After Clicking Report Icon in Leave Entry.. tabs size: " + tabs.size());
			if (flag) {
				logs.logging("INFO", "Leave Entry Report Icon Clicked... New tab Opened");
				webDB.switchToTabByIndex(1);
				commonFunc.hardWait(2000);
				String Title = webDB.getDriver().getTitle();
				logs.logging("INFO", "Switched to Report Page... Displayed Title: " + Title);
				if (flag) {
					webDB.closeExistingTab();
					webDB.switchToMainTab();
					flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryReportBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Existing tab closed and Switch to main window");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	// ----------- Leave Adjustment -----------------

	/**
	 * This Method used To Check Leave Adjustment Based on Leave types in Leave
	 * Master, To Check Leave Adjustment based on the Addition and Deduction Prefix,
	 * To Check Leave Adjustment Based on the Request date, To Check the Leave
	 * Adjustment with the attachment field, To Check Leave Adjustment report
	 * fetches values correctly
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyAdjustmentBasedLeaveTypesPrefixReqDate() {
		LocalDate date1 = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date1.format(formatter);
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEntryScreen();
			clickAdjustmentLabelButton();
			clickAddNewIconInLeaveAdjustment();
			fillRequestDateEmpIdAndLeaveInAdjustment(Today, commonFunc.getDataFromTestDataSheet("Emp_GWP0001"),
					commonFunc.getDataFromTestDataSheet("CL-Text"));
			fillLeavePrefixDaysAndReasonInAdjustment(commonFunc.getDataFromTestDataSheet("AdditionText"), "1",
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			clickSaveAndStoreDocNoInAdjustment();
			clickPostButtonInAdjustmentVerifyStatus();
			clickAttachmentInLeaveEntry();
			fillDocTypeDocNoDocRevInLeaveEntryAttachment(commonFunc.getDataFromTestDataSheet("ApplicationOption"),
					Today, "0");
			fillFromToDateAndFileUploadInLeaveEntryAttachment(Today, Today,
					VariableHandler.testdataDir + webDB.getDataFromProperties("RoadMapImage"));
			clickUploadButtonInAttachmentLeaveEntry();
			clickReportAndVerifyDisplayedInLeaveAdjustment();
			clickReverseButtonInAdjustmentVerifyStatus();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyAdjustmentBasedLeaveTypesPrefixReqDate': " + e);
			return false;
		}
	}

	/**
	 * This Method used To Check Leave Balances Reflecting in Employee Master
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyLeaveAdjustmentBalancesReflectingInEmployeeMaster() {
		LocalDate date1 = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date1.format(formatter);
		String Leave = RandomStringUtils.randomNumeric(1);
		if (Leave.equals("0")) {
			Leave = "1";
		}
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			empMasterFunc.searchAndClickEmpIdInFindEmployeePage(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			empMasterFunc.clickLeaveDetailsSideNavBar();
			empMasterFunc.checkCLBalanceInLeaveDetailsAndStore();
			commonFunc.navigateLeaveEntryScreen();
			clickAdjustmentLabelButton();
			clickAddNewIconInLeaveAdjustment();
			fillRequestDateEmpIdAndLeaveInAdjustment(Today, commonFunc.getDataFromTestDataSheet("Emp_GWP0001"),
					commonFunc.getDataFromTestDataSheet("CL-Text"));
			fillLeavePrefixDaysAndReasonInAdjustment(commonFunc.getDataFromTestDataSheet("AdditionText"), Leave,
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			clickSaveAndStoreDocNoInAdjustment();
			clickPostButtonInAdjustmentVerifyStatus();
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			empMasterFunc.searchAndClickEmpIdInFindEmployeePage(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			empMasterFunc.clickLeaveDetailsSideNavBar();
			empMasterFunc.verifyCLBalanceAddedInLeaveDetails(Leave);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyLeaveAdjustmentBalancesReflectingInEmployeeMaster': " + e);
			return false;
		}
	}

	/**
	 * This method used to Cancel the leave Adjustment and checking the status of
	 * Leave Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCancelCheckStatusLeaveAdjustment() {
		LocalDate date1 = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date1.format(formatter);
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEntryScreen();
			clickAdjustmentLabelButton();
			clickAddNewIconInLeaveAdjustment();
			fillRequestDateEmpIdAndLeaveInAdjustment(Today, commonFunc.getDataFromTestDataSheet("Emp_GWP0001"),
					commonFunc.getDataFromTestDataSheet("CL-Text"));
			fillLeavePrefixDaysAndReasonInAdjustment(commonFunc.getDataFromTestDataSheet("AdditionText"), "1",
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			clickSaveAndStoreDocNoInAdjustment();
			clickCancelButtonInAdjustmentVerifyStatus();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCancelCheckStatusLeaveAdjustment': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check Accrual Leave Balance Migration With Templates in
	 * Leave Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyAccrualLeaveBalanceMigrationWithTemplates() {
		LocalDate date1 = LocalDate.now();
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		String month = date1.format(formatter1);
		String date = date1.format(formatter);
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEntryScreen();
			clickAdjustmentLabelButton();
			clickMigrationButtonInAdjustment();
			clickAddIconInAdjustmentMigration();
			fillHeaderInAdjustmentMigration(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("Year202526"), month,
					commonFunc.getDataFromTestDataSheet("AdditionText"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			clickSaveIconInAdjustmentMigration();
			uploadTemplateInAdjustmentMigration(date);
			clickPostInAdjustmentMigration();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyAccrualLeaveBalanceMigrationWithTemplates': " + e);
			return false;
		}
	}

	/**
	 * This method used to click adjustment label button in Leave Entry Screen
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAdjustmentLabelButton() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.LeaveAdjustmentLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveAdjustmentLabel, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveAdjustmentLabel, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.AdjustmentAddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Adjustment button is Clicked in Leave Entry Screen");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add icon in adjustment in Leave Entry Screen
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddNewIconInLeaveAdjustment() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.AdjustmentAddIcon, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.AdjustmentEmpIdLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Adjustment Add Icon Clicked in Leave Entry Screen");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select request date EmpId and Leave in adjustment
	 * Creation
	 * 
	 * @author Sakthi-GWL
	 * @param RequestDate
	 * @param EmpId
	 * @param Leave
	 */
	private void fillRequestDateEmpIdAndLeaveInAdjustment(String RequestDate, String EmpId, String Leave) {
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.AdjustmentRequestDateInput,
				AttendanceLeaveLocators.AdjustmentRequestDateInput, RequestDate);
		logs.logging("INFO", "Request Date Entered in Adjustment Creation: " + RequestDate);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.AdjustmentEmpIdLovBtn, EmpId);
		logs.logging("INFO", "Emp Id Selected in Adjustment Creation: " + RequestDate);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.AdjustmentLeaveLovBtn, Leave);
		logs.logging("INFO", "Leave Selected in Adjustment Creation: " + Leave);
	}

	/**
	 * This method used to select leave prefix, Days and reason in adjustment
	 * Creation
	 * 
	 * @author Sakthi-GWL
	 * @param Prefix
	 * @param Days
	 * @param Reason
	 */
	private void fillLeavePrefixDaysAndReasonInAdjustment(String Prefix, String Days, String Reason) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.AdjustmentLeavePrefixLovBtn, Prefix);
		logs.logging("INFO", "Leave Prefix Selected in Adjustment Creation: " + Prefix);
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.AdjustmentDaysInput,
				AttendanceLeaveLocators.AdjustmentDaysInput, Days);
		logs.logging("INFO", "Adjustment Days Entered in Adjustment Creation: " + Days);
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.AdjustmentReasonInput,
				AttendanceLeaveLocators.AdjustmentReasonInput, Reason);
		logs.logging("INFO", "Reason Entered in Adjustment Creation: " + Reason);
	}

	/**
	 * This method used to click Save icon in adjustment Creation and store leave
	 * entry doc no
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveAndStoreDocNoInAdjustment() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.AdjustmentHeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentHeaderSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Adjustment Header Save Icon Clicked in Adjustment");
				commonFunc.hardWait(1000);
				flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentDisplayDocNo, ElementType.Xpath);
				if (flag) {
					String DisplayDocNo = webDB.getAttributeFromElement(AttendanceLeaveLocators.AdjustmentDisplayDocNo,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Adjustment Leave Entry Doc No: " + DisplayDocNo);
					VariableHandler.setLeaveEntryDocNo(DisplayDocNo);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click post button in adjustment Creation and verify
	 * status
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPostButtonInAdjustmentVerifyStatus() {
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentPostBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentPostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Icon Clicked");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkRefreshCellsDisappear();
				String DisplayText = webDB.getTextFromElement(AttendanceLeaveLocators.AdjustmentDisplayStatus,
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Leave Entry Number And Status: " + DisplayText);
				flag = DisplayText.contains("Posted");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Reverse button in adjustment Creation
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickReverseButtonInAdjustmentVerifyStatus() {
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentReverseBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentReverseBtn, ElementType.Xpath);
			flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentReverseOkBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentReverseOkBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Reverse Button Icon Clicked");
					String DisplayText = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					logs.logging("INFO", "Displayed Text in toast message: " + DisplayText);
					flag = DisplayText.contains("Reversed");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Cancel button in adjustment Creation and verify
	 * status
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickCancelButtonInAdjustmentVerifyStatus() {
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentCancelBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentCancelBtn, ElementType.Xpath);
			flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentReverseOkBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentReverseOkBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Cancel Button Clicked");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					String DisplayText = webDB.getTextFromElement(AttendanceLeaveLocators.AdjustmentDisplayStatus,
							ElementType.Xpath);
					logs.logging("INFO", "Displayed Leave Entry Number And Status: " + DisplayText);
					flag = DisplayText.contains("Cancelled");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click migration button in leave adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickMigrationButtonInAdjustment() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjustmentMigrationBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.AdjustmentMigrationBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentMigrationBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.AdjmntMigrationCreateAddBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Adjustment Migration Button Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add icon in leave adjustment migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddIconInAdjustmentMigration() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjmntMigrationCreateAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.AdjmntMigrationCreateAddBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.AdjmntMigrationCreateAddBtn, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.AdjmntMigrationCalendarLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Adjustment Migration Add Icon Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill header details in leave adjustment migration
	 * 
	 * @author Sakthi-GWL
	 * @param Calendar
	 * @param Year
	 * @param Period
	 * @param Mode
	 * @param Ref
	 */
	private void fillHeaderInAdjustmentMigration(String Calendar, String Year, String Period, String Mode, String Ref) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.AdjmntMigrationCalendarLovBtn, Calendar);
		logs.logging("INFO", "Adjustment Migration Calendar Selected: " + Calendar);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(AttendanceLeaveLocators.AdjmntMigrationYearSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.AdjmntMigrationYearSelect, Year,
					ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Adjustment Migration Year Selected: " + Year);
				webDB.waitForElement(AttendanceLeaveLocators.AdjmntMigrationPeriodSelectOptions(Period),
						ElementType.Xpath);
				String fullTextToSelect = webDB.getTextFromElement(
						AttendanceLeaveLocators.AdjmntMigrationPeriodSelectOptions(Period), ElementType.Xpath);
				flag = webDB.selectDropDownByText(AttendanceLeaveLocators.AdjmntMigrationPeriodSelect, fullTextToSelect,
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					logs.logging("INFO", "Adjustment Migration Period Selected: " + fullTextToSelect);
					flag = webDB.selectDropDownByText(AttendanceLeaveLocators.AdjmntMigrationModeSelect, Mode,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						logs.logging("INFO", "Adjustment Migration Mode Selected: " + Mode);
						webDB.clearText(AttendanceLeaveLocators.AdjmntMigrationReferenceInput, ElementType.Xpath);
						flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.AdjmntMigrationReferenceInput, Ref,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Adjustment Migration Ref Enterted: " + Ref);
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click save icon in leave adjustment migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveIconInAdjustmentMigration() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjmntMigrationHeaderSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.AdjmntMigrationHeaderSaveBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.AdjmntMigrationHeaderSaveBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Adjustment Migration Save Icon Clicked");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to switch frame in upload adjustment migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void switchToUploadIframeInAdjustmentMigration() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.AdjmntMigrationUploadIFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(AttendanceLeaveLocators.AdjmntMigrationUploadIFrame, ElementType.Xpath);
			flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjmntMigrationUploadInput, ElementType.Xpath);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to upload file in leave adjustment migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void uploadTemplateInAdjustmentMigration(String date) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.AdjmntMigrationUploadIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.AdjmntMigrationUploadIcon, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.AdjmntMigrationUploadIcon, ElementType.Xpath);
			switchToUploadIframeInAdjustmentMigration();
			flag = webDB.waitForElement(AttendanceLeaveLocators.AdjmntMigrationUploadInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Upload Icon Clicked and Input Box Displayed");
				String file1 = webDB.getDataFromProperties("AdjustmentMigrationFileName");
				String Path = VariableHandler.testdataDir + file1;
				ExcelUtils excel = new ExcelUtils();
				excel.writeDataInExcel(Path, "Sheet1", 1, 3, date);
				excel.writeDataInExcel(Path, "Sheet1", 1, 4, date);
				File file = new File(Path);
				flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.AdjmntMigrationUploadInput,
						file.getAbsolutePath(), ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjmntMigrationUploadBtn,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.AdjmntMigrationUploadBtn, ElementType.Xpath);
						commonFunc.hardWait(2000);
						webDB.switchToDefaultContent();
						flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjmntMigrationCheckExceptionBtn,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "File Uploaded Succesfully for Adjustment Migration");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click post button in leave adjustment migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPostInAdjustmentMigration() {
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjmntMigrationCheckExceptionBtn, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(AttendanceLeaveLocators.AdjmntMigrationCheckExceptionBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.AdjmntMigrationCheckExceptionBtn, ElementType.Xpath);
			flag = webDB.waitForClickElement(AttendanceLeaveLocators.AdjmntMigrationPostBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - Adjustment Check Exception Clicked Successfully");
				webDB.moveToElement(AttendanceLeaveLocators.AdjmntMigrationPostBtn, ElementType.Xpath);
				webDB.clickAnElement(AttendanceLeaveLocators.AdjmntMigrationPostBtn, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (flag) {
					logs.logging("INFO", "Migration - Adjustment Posted Successfully");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click report button in Leave Adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickReportAndVerifyDisplayedInLeaveAdjustment() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.AdjustmentReportBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.AdjustmentReportBtn, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentReportBtn, ElementType.Xpath);
			commonFunc.hardWait(5000);
			ArrayList<String> tabs = new ArrayList<String>(webDB.getDriver().getWindowHandles());
			flag = tabs.size() > 1;
			logs.loggingconsole("INFO", "After Clicking Report Icon in Leave Adjustment.. tabs size: " + tabs.size());
			if (flag) {
				logs.logging("INFO", "Leave Adjustment Report Icon Clicked... New tab Opened");
				webDB.switchToTabByIndex(1);
				commonFunc.hardWait(2000);
				String Title = webDB.getDriver().getTitle();
				logs.logging("INFO", "Switched to Report Page... Displayed Title: " + Title);
				if (flag) {
					webDB.closeExistingTab();
					webDB.switchToMainTab();
					flag = webDB.waitForElement(AttendanceLeaveLocators.AdjustmentReportBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Existing tab closed and Switch to main window");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click find Search Button
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickFindSearchButtonInLeave() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryFindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryFindSearchBar, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click find Search Button
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickFindSearchButtonInAdjustment() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.AdjustmentFindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.AdjustmentFindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(AttendanceLeaveLocators.Adj_FindSearchBar, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked Successfully in Adjustment");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to find Leave Details using Leave In Leave
	 * 
	 * @author Sakthi-GWL
	 * @param Leave
	 */
	private void findWithLeaveInLeave(String Leave) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.FindLeaveLovBtn, Leave);
		clickFindSearchButtonInLeave();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.FindLeaveResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Leave)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Leave: " + Leave);
	}

	/**
	 * This method used to find Leave Details using Year In Leave
	 * 
	 * @author Sakthi-GWL
	 * @param Year
	 */
	private void findWithYearInLeave(String Year) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.FindYearLovBtn, Year);
		clickFindSearchButtonInLeave();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.FindYearResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Year)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Year: " + Year);
	}

	/**
	 * This method used to find Leave Details using Designation In Leave
	 * 
	 * @author Sakthi-GWL
	 * @param Designation
	 */
	private void findWithDesignationInLeave(String Designation) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.FindDesignationLovBtn, Designation);
		clickFindSearchButtonInLeave();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.FindDesignationResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Designation)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Designation: " + Designation);
	}

	/**
	 * This method used to find Leave Details using Department In Leave
	 * 
	 * @author Sakthi-GWL
	 * @param Department
	 */
	private void findWithDepartmentInLeave(String Department) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.FindDepartmentLovBtn, Department);
		clickFindSearchButtonInLeave();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.FindDepartmentResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Department)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Department: " + Department);
	}

	/**
	 * This method used to find Leave Details using Unit In Leave
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	private void findWithUnitInLeave(String Unit) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.FindUnitLovBtn, Unit);
		clickFindSearchButtonInLeave();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.FindUnitResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Unit)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Unit: " + Unit);
	}

	/**
	 * This method used to find Leave Details using Leave In Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param Leave
	 */
	private void findWithLeaveInAdjustment(String Leave) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.Adj_FindLeaveLovBtn, Leave);
		clickFindSearchButtonInAdjustment();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.Adj_FindLeaveResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Leave)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Leave in Adjustment: " + Leave);
	}

	/**
	 * This method used to find Leave Details using Year In Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param Year
	 */
	private void findWithYearInAdjustment(String Year) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.Adj_FindYearLovBtn, Year);
		clickFindSearchButtonInAdjustment();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.Adj_FindYearResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Year)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Year in Adjustment: " + Year);
	}

	/**
	 * This method used to find Leave Details using Designation In Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param Designation
	 */
	private void findWithDesignationInAdjustment(String Designation) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.Adj_FindDesignationLovBtn, Designation);
		clickFindSearchButtonInAdjustment();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.Adj_FindDesignationResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Designation)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Designation in Adjustment: " + Designation);
	}

	/**
	 * This method used to find Leave Details using Department In Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param Department
	 */
	private void findWithDepartmentInAdjustment(String Department) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.Adj_FindDepartmentLovBtn, Department);
		clickFindSearchButtonInAdjustment();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.Adj_FindDepartmentResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Department)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Department in Adjustment: " + Department);
	}

	/**
	 * This method used to find Leave Details using Unit In Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	private void findWithUnitInAdjustment(String Unit) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.Adj_FindUnitLovBtn, Unit);
		clickFindSearchButtonInAdjustment();
		List<WebElement> elements = webDB.getListOfElements(AttendanceLeaveLocators.Adj_FindUnitResultTable,
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Unit)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Unit in Adjustment: " + Unit);
	}

	/**
	 * This method used To Check the Leave Adjustment Find page with all line wise
	 * record
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyLeaveAdjustmentFindWithLineWiseRecord() {
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEntryScreen();
			clickAdjustmentLabelButton();
			findWithLeaveInAdjustment(commonFunc.getDataFromTestDataSheet("CasualLeave-Text"));
			webDB.navigateToRefresh();
			findWithYearInAdjustment(commonFunc.getDataFromTestDataSheet("Year202526"));
			webDB.navigateToRefresh();
			findWithDesignationInAdjustment(commonFunc.getDataFromTestDataSheet("ChairmanText"));
			webDB.navigateToRefresh();
			findWithDepartmentInAdjustment(commonFunc.getDataFromTestDataSheet("QualityText"));
			webDB.navigateToRefresh();
			findWithUnitInAdjustment(commonFunc.getDataFromTestDataSheet("Unit1"));
			webDB.navigateToRefresh();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyLeaveAdjustmentFindWithLineWiseRecord': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check the Leave Entry Find page with all line wise record
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyLeaveEntryFindWithLineWiseRecord() {
		try {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateLeaveEntryScreen();
			findWithLeaveInLeave(commonFunc.getDataFromTestDataSheet("CasualLeave-Text"));
			webDB.navigateToRefresh();
			findWithYearInLeave(commonFunc.getDataFromTestDataSheet("Year202526"));
			webDB.navigateToRefresh();
			findWithDesignationInLeave(commonFunc.getDataFromTestDataSheet("ChairmanText"));
			webDB.navigateToRefresh();
			findWithDepartmentInLeave(commonFunc.getDataFromTestDataSheet("QualityText"));
			webDB.navigateToRefresh();
			findWithUnitInLeave(commonFunc.getDataFromTestDataSheet("Unit1"));
			webDB.navigateToRefresh();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyLeaveEntryFindWithLineWiseRecord': " + e);
			return false;
		}
	}

	/**
	 * This method is used to Enter Loan Data with Employee
	 * 
	 * @author Kishore-RMITS
	 * @param PermFromDate2
	 * @param EmployeeDurai
	 * @param LoanTypes
	 * @param LoanReference
	 */
	public void LoanRequestEntry(String PermFromDate1, String EmployeeDurai, String LoanTypes, String LoanReference) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LoanRequestAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Loan Add Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestEntryRequestDate, ElementType.Xpath);
				if (flag) {
					webDB.clearText(AttendanceLeaveLocators.LoanRequestEntryRequestDate, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LoanRequestEntryRequestDate, PermFromDate1,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Request Date Is Entered");
						flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestEntryEmployeeLOV,
								ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(1000);
							commonFunc.searchAndSelectValueInFilters(
									AttendanceLeaveLocators.LoanRequestEntryEmployeeLOV, EmployeeDurai);
							if (flag) {
								logs.logging("INFO", "Employee IS Selected in Employee LOV");
								flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestEntryLoanLOV,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(1000);
									commonFunc.searchAndSelectValueInFilters(
											AttendanceLeaveLocators.LoanRequestEntryLoanLOV, LoanTypes);
									if (flag) {
										logs.logging("INFO", "Loan  Type " + LoanTypes + " is Selected");
										flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestReferenceField,
												ElementType.Xpath);
										if (flag) {
											flag = webDB.sendTextToAnElement(
													AttendanceLeaveLocators.LoanRequestReferenceField, LoanReference,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Loan Refernce " + LoanReference + " is Entered");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Enter The Loan Required Details
	 * 
	 * @author Kishore-RMITS
	 * @param PermFromDate2
	 * @param LoanAmount
	 * @param LoanInstallmentAmount
	 * @param LoanSuretyEmployee1
	 * @param LoanSuretyEmployee2
	 */
	public void LoanRequiredDetails(String PermFromDate1, String LoanAmount, String LoanInstallmentAmount) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestRequiredDetailsDateField, ElementType.Xpath);
		if (flag) {
			webDB.clearText(AttendanceLeaveLocators.LoanRequestRequiredDetailsDateField, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LoanRequestRequiredDetailsDateField, PermFromDate1,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Loan Required Date is Entered");
				flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestAmountField, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(AttendanceLeaveLocators.LoanRequestAmountField, ElementType.Xpath);
					commonFunc.hardWait(1000);
					webDB.clearText(AttendanceLeaveLocators.LoanRequestAmountField, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LoanRequestAmountField, LoanAmount,
							ElementType.Xpath);
					logs.logging("INFO", "Loan amount is Entered");
					flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestNoofInstallmentField,
							ElementType.Xpath);
					if (flag) {
						webDB.doubleClickAnElement(AttendanceLeaveLocators.LoanRequestNoofInstallmentField,
								ElementType.Xpath);
						commonFunc.hardWait(1000);
						webDB.clearText(AttendanceLeaveLocators.LoanRequestNoofInstallmentField, ElementType.Xpath);
						commonFunc.hardWait(1000);
						flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LoanRequestNoofInstallmentField,
								LoanInstallmentAmount, ElementType.Xpath);
						flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestSaveIcon, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(2000);
							webDB.clickAnElement(AttendanceLeaveLocators.LoanRequestSaveIcon, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Loan Request Doc is Saved");
							}
							flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
							if (flag) {
								commonFunc.checkSuccessToastMessageDisappear();
								webDB.waitForElement(AttendanceLeaveLocators.LoanRequestDocNoField, ElementType.Xpath);
								String LoanRequestDocNo = webDB.getDomPropertyFromElement(
										AttendanceLeaveLocators.LoanRequestDocNoField, ElementType.Xpath,
										commonFunc.getDataFromTestDataSheet("Value"));
								VariableHandler.setLoanRequestDocNo(LoanRequestDocNo);
								logs.logging("info", "Loan Request No is:" + VariableHandler.getLoanRequestDocNo());
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Check Loan Request based on Required Date
	 * 
	 * @author Kishore-RMITS
	 */
	public void LoanRequestDocumentProcess() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestPostIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(1000);
			webDB.clickAnElement(AttendanceLeaveLocators.LoanRequestPostIcon, ElementType.Xpath);
//			flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath, 5);
//			if (flag) {
//				VariableHandler.setApprovalFlag(flag);
//				webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
//				logs.logging("INFO", "Switched To Workflow Frame");
//				commonFunc.searchAndSelectValueInFilters(CommonUiLocators.WorkAppoval_ForwardLovBtn, user);
//				logs.logging("INFO", "Document Forwarder Person: " + user);
//				webDB.waitForElement(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
//				webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
//				flag = webDB.waitForElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
//				if (flag) {
//					webDB.moveToElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
//					flag = webDB.clickAnElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
//					commonFunc.checkSuccessToastMessageDisappear();
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(3000);
//					flag = !webDB.verifyIsElementDisplayed(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
//					if (flag) {
//						logs.logging("INFO", "Workflow Approval Ok Button Clicked");
//						webDB.switchToDefaultContent();
//					}
//				}
//			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Check Loan Request Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	public void CheckLoanRequestFindPageWithAllLineWiseRecord() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestFindSearch, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			webDB.clickAnElement(AttendanceLeaveLocators.LoanRequestFindSearch, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Loan Request Find Serach Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestFindDocNoClick, ElementType.CssSelector);
				if (flag) {
					commonFunc.hardWait(3000);
					webDB.clickAnElement(AttendanceLeaveLocators.LoanRequestFindDocNoClick, ElementType.CssSelector);
					if (flag) {
						logs.logging("INFO", "Loan Request document have been clicked");
						flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestDocCancel, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							webDB.clickAnElement(AttendanceLeaveLocators.LoanRequestDocCancel, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Loan Request Document Have been Cancelled");
								flag = webDB.waitForElement(AttendanceLeaveLocators.LoanRequestAmountField,
										ElementType.Xpath);
								commonFunc.hardWait(1000);
								commonFunc.logoutUser();
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Check Loan Request based on Return to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckLoanRequestBasedonReturnToApprovalRecord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToLoanRequestScreen();
		LoanRequestEntry(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("EmployeeDurai"), commonFunc.getDataFromTestDataSheet("LoanTypes"),
				commonFunc.getDataFromTestDataSheet("LoanReference"));
		LoanRequiredDetails(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("LoanAmount"),
				commonFunc.getDataFromTestDataSheet("LoanInstallmentAmount"));
		LoanRequestDocumentProcess();
//		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
//		approvalFunc.navigateToApprovals();
//		filterDocumentsforPermissionApproval(VariableHandler.getLoanRequestDocNo());
//		verifyReturnWithDocNumber(VariableHandler.getLoanRequestDocNo());
//		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"TvsUsername");
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getLoanRequestDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"), webDB.getDataFromProperties("HRUSEREmpId"));
		commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getLoanRequestDocNo(),
				webDB.getDataFromProperties("HRUSEREmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HrRqtUsername"), webDB.getDataFromProperties("HRUSEREmpId"));
		commonFunc.navigateToLoanRequestScreen();
		CheckLoanRequestFindPageWithAllLineWiseRecord();
		return flag;
	}

	/**
	 * This method used to check enable auto post leave entry in Setup Screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyAutoPostLeaveEntryInLeaveSetupScreen() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.SetupLeaveLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.SetupLeaveLabel, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.SetupLeaveLabel, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.SetupCreateLeaveEntryLabel, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Setup Payroll Clicked");
				boolean checkedflag = webDB.isElementSelected(
						AttendanceLeaveLocators.SetupCreateLeaveEntryInputCheckBox, ElementType.Xpath);
				logs.logging("INFO", "Setup Payroll Checkbox value: " + flag);
				if (checkedflag) {
					VariableHandler.setEmpLeaveEntryAutoPost(true);
					logs.logging("INFO",
							"Leave Entry Auto Post while approved Leave Request.. checked in setup screen");
				} else if (!webDB.isElementEnabled(AttendanceLeaveLocators.SetupCreateLeaveEntryInputCheckBox,
						ElementType.Xpath)) {
					VariableHandler.setEmpLeaveEntryAutoPost(false);
					logs.logging("INFO",
							"Leave Entry Manual Post while approved Leave Request.. checked in setup screen");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Leave Entry Screen
	 * 
	 * @author Sakthi-GWL
	 * @param Date
	 * @param EmpId
	 * @param LeaveType
	 */
	private void fillEntryDateEmpIdAndLeaveType(String Date, String EmpId, String LeaveType) {
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LeaveEntryDateInput,
				AttendanceLeaveLocators.LeaveEntryDateInput, Date);
		logs.logging("INFO", "Leave Request Date Entered: " + Date);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LeaveEntryEmpIdLovBtn, EmpId);
		logs.logging("INFO", "Leave Emp Id Selected: " + EmpId);
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.LeaveEntryLeaveTypeLovBtn, LeaveType);
		logs.logging("INFO", "Leave Type Selected: " + LeaveType);
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This method used to select start date and session and Applied Days in Leave
	 * Entry
	 * 
	 * @author Sakthi-GWL
	 * @param Date
	 * @param Session
	 * @param AppliedDays
	 */
	private void selectStartDateAndSessionWithAppliedDaysInLE(String Date, String Session, String AppliedDays) {
		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LeaveEntryStartDateInput,
				AttendanceLeaveLocators.LeaveEntryStartDateInput, Date);
		logs.logging("INFO", "Leave Start Date Entered: " + Date);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryStartSessionSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.LeaveEntryStartSessionSelect, Session,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Start Session Selected: " + Session);
				flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LeaveEntryAppliedDaysInput, AppliedDays,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Applied Date Entered: " + AppliedDays);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select leave end date and session and Reason in Leave
	 * Entry
	 * 
	 * @author Sakthi-GWL
	 * @param Date
	 * @param Session
	 * @param Reason
	 */
	private void selectEndDateAndSessionWithReasonInLE(String Date, String Session, String Reason) {
//		commonPayrollFunctions.addDetailInTableWithoutLovButton(AttendanceLeaveLocators.LeaveEntryEndDateInput,
//				AttendanceLeaveLocators.LeaveRequestEndDateInput, Date);
		logs.logging("INFO", "Leave End Date Entered: " + Date);
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryEndSessionSelect, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryEndDateInput, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryEndDateInput, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitUntilElementToNotBeStale(AttendanceLeaveLocators.LeaveEntryEndDateInput, ElementType.Xpath);
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.LeaveEntryEndSessionSelect, Session,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave End Session Selected: " + Session);
				flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.LeaveEntryLeaveReason, Reason,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Leave Reason Entered: " + Reason);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to save header and store leave request number in leave entry
	 * screen
	 * 
	 * @author Sakthi-GWL
	 */
	private void saveLeaveEntryAndStoreLeaveEntryNo() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryHeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryHeaderSaveIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryNumberDisplay, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(1000);
				logs.logging("INFO", "Header Saved Successfully in Leave Entry");
				String ReqNo = webDB.getDomPropertyFromElement(AttendanceLeaveLocators.LeaveEntryNumberDisplay,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Displayed Entry No: " + ReqNo);
				VariableHandler.setLeaveEntryDocNo(ReqNo);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select leave option and click add leave icon in Leave
	 * Entry
	 * 
	 * @author Sakthi-GWL
	 */
	private void chooseLeaveAndClickAddLeaveIconInLE() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveEntry, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveEntry, ElementType.Xpath);
			webDB.clickAnElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveEntry, ElementType.Xpath);
			logs.logging("INFO", "Leave Button is Clicked in Leave Entry");
			flag = webDB.waitForClickElement(AttendanceLeaveLocators.LeaveEntryAddButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(AttendanceLeaveLocators.LeaveEntryAddButton, ElementType.Xpath);
				webDB.clickAnElement(AttendanceLeaveLocators.LeaveEntryAddButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveEntryEmpIdLovBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Leave Add Button Is Clicked in Leave Entry");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	// --------------- "Loan Payment Test Scenarios Functions" -----------------

	/**
	 * This method is used To Check Loan Payment Entry
	 * 
	 * @author Kishore-RMITS
	 * @param Number
	 */
	public boolean CheckLoanPaymentEntry() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToLoanRequestScreen();
		LoanRequestEntry(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("EmployeeDurai"), commonFunc.getDataFromTestDataSheet("LoanTypes"),
				commonFunc.getDataFromTestDataSheet("LoanReference"));
		LoanRequiredDetails(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("LoanAmount"),
				commonFunc.getDataFromTestDataSheet("LoanInstallmentAmount"));
		LoanRequestDocumentProcess();
//		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
//		approvalFunc.navigateToApprovals();
//		filterDocumentsforPermissionApproval(VariableHandler.getLoanRequestDocNo());
//		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getLoanRequestDocNo());
//		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getLoanRequestDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.navigateToLoanPaymentScreen();
		LoanPaymentDocumentSelect();
		return flag;
	}

	/**
	 * This method is used To Check LoanPayment Document Select
	 * 
	 * @author Kishore-RMITS
	 */
	public void LoanPaymentDocumentSelect() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LoanPaymentFindSearch, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LoanPaymentFindSearch, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LoanPayment Find Search Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.LoanPaymentDocNoClick, ElementType.CssSelector);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.LoanPaymentDocNoClick, ElementType.CssSelector);
					if (flag) {
						logs.logging("INFO", "Loan DocNo Is Clicked");
						flag = webDB.waitForElement(AttendanceLeaveLocators.LoanPaymentCreateLoanButton,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(AttendanceLeaveLocators.LoanPaymentCreateLoanButton,
									ElementType.Xpath);
							flag = webDB.waitForElementUntilGivenTime(
									AttendanceLeaveLocators.LoanCreateLoanPaymentFrame, ElementType.Xpath, 45);
							if (flag) {
								webDB.switchToFrame(AttendanceLeaveLocators.LoanCreateLoanPaymentFrame,
										ElementType.Xpath);
								System.out.println("1");
								flag = webDB.waitForElementUntilGivenTime(
										AttendanceLeaveLocators.LoanCreateLoanPaymentpayrollOption, ElementType.Xpath,
										45);
								if (flag) {
									commonFunc.hardWait(2000);
									webDB.clickAnElement(AttendanceLeaveLocators.LoanCreateLoanPaymentpayrollOption,
											ElementType.Xpath);
									flag = webDB.waitForElement(AttendanceLeaveLocators.LoanCreateLoanPaymentSaveOption,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(AttendanceLeaveLocators.LoanCreateLoanPaymentSaveOption,
												ElementType.Xpath);
										commonFunc.logoutUser();
									}
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To CheckLoan Payment with reverse part option
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckLoanPaymentWithReversePartOption() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToLoanRequestScreen();
		LoanRequestEntry(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("EmployeeDurai"), commonFunc.getDataFromTestDataSheet("LoanTypes"),
				commonFunc.getDataFromTestDataSheet("LoanReference"));
		LoanRequiredDetails(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("LoanAmount"),
				commonFunc.getDataFromTestDataSheet("LoanInstallmentAmount"));
		LoanRequestDocumentProcess();
//		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
//		approvalFunc.navigateToApprovals();
//		filterDocumentsforPermissionApproval(VariableHandler.getLoanRequestDocNo());
//		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getLoanRequestDocNo());
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getLoanRequestDocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToLoanPaymentScreen();
		LoanPaymentDocumentReverse();
		commonFunc.navigateToLoanPaymentScreen();
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method is used To Check LoanPayment Document Select
	 * 
	 * @author Kishore-RMITS
	 */
	public void LoanPaymentDocumentReverse() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.LoanPaymentFindSearch, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.LoanPaymentFindSearch, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LoanPayment Find Search Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.LoanPaymentDocNoClick, ElementType.CssSelector);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.LoanPaymentDocNoClick, ElementType.CssSelector);
					if (flag) {
						logs.logging("INFO", "Loan DocNo Is Clicked");
						flag = webDB.waitForElement(AttendanceLeaveLocators.LoanPaymentReverseButton,
								ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(2000);
							webDB.clickAnElement(AttendanceLeaveLocators.LoanPaymentReverseButton, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "loan Payment Document Is Reversed");
								flag = webDB.waitForElement(AttendanceLeaveLocators.LoanPaymentFindSearch,
										ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Check TDS Declaration based on TDS Revision
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTDSDeclarationBasedOnTDSRevision() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeDocumentRevise();
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeTDSPostDocument();
		return flag;
	}

	/**
	 * This method is used To Check TDS Declaration based on forward to approval
	 * record
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTDSDeclarationBasedOnForwardToApprovalRecord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeDocumentRevise();
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeTDSPostDocument();
		return flag;
	}

	/**
	 * This method is used To Check TDS Declaration based on Return to approval
	 * record record
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTDSDeclarationBasedOnReturnToApprovalRecord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeDocumentRevise();
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeTDSReturnDocument();
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeTDSPostDocument();
		return flag;
	}

	/**
	 * This method is used To CheckTDS Declaration based on TDS Existing Regme and
	 * New Regime
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTDSDeclarationBasedOnTDSExistingRegimeAndNewRegime() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeDocumentRevise();
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeTDSRegimeChoose();
		return flag;
	}

	/**
	 * This method is used To CheckTDS Declaration based on section value assigning
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTDSDeclarationBasedOnSectionValueAssigning() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeDocumentRevise();
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeTDSSectionWise(commonFunc.getDataFromTestDataSheet("TDSAmount"));
		return flag;
	}

	/**
	 * This method is used To Check TDS Declaration based on TDS Revision
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTDSDeclarationBasedOnRegimeWiseChecking() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeDocumentRevise();
		commonFunc.navigateToTDSDeclarationScreen();
		EmployeeDocumentSelect(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EmployeeTDSPostDocument();
		return flag;
	}

	/**
	 * This method is used To Check Employee TDS Declaration Select
	 * 
	 * @author Kishore-RMITS
	 */
	public void EmployeeDocumentSelect(String Employee) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.TDSDeclFindEmpLOV, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TDSDeclFindEmpLOV, Employee);
			if (flag) {
				logs.logging("INFO", "TDS Employee is Selected");
				flag = webDB.waitForElement(AttendanceLeaveLocators.TDSFindSearchButton, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSFindSearchButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "TDS Employee is Searched");
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(AttendanceLeaveLocators.TDSDocumentSelect, ElementType.CssSelector);
						if (flag) {
							commonFunc.hardWait(3000);
							flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDocumentSelect,
									ElementType.CssSelector);
							if (flag) {
								logs.logging("INFO", "TDS Document is Selected");
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Check TDS Declaration to Revise
	 * 
	 * @author Kishore-RMITS
	 */
	public void EmployeeDocumentRevise() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationReviseButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDeclarationReviseButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "TDS Employee is Searched");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Post The TDS Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void EmployeeTDSPostDocument() {
		if (flag) {
			webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationDocNoToPost, ElementType.Xpath);
			String TDSDeclarationDocNo = webDB.getDomPropertyFromElement(
					AttendanceLeaveLocators.TDSDeclarationDocNoToPost, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setTDSDeclarationDocNo(TDSDeclarationDocNo);
			logs.logging("info", "TDS DECLARATION No is:" + VariableHandler.getTDSDeclarationDocNo());
			flag = webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationPostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDeclarationPostButton, ElementType.Xpath);
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getTDSDeclarationDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				flag = commonFunc.logoutUser();
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Return The TDS Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void EmployeeTDSReturnDocument() {
		if (flag) {
			webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationDocNoToPost, ElementType.Xpath);
			String TDSDeclarationDocNo = webDB.getDomPropertyFromElement(
					AttendanceLeaveLocators.TDSDeclarationDocNoToPost, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setTDSDeclarationDocNo(TDSDeclarationDocNo);
			logs.logging("info", "TDS DECLARATION No is:" + VariableHandler.getTDSDeclarationDocNo());
			flag = webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationPostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDeclarationPostButton, ElementType.Xpath);
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getTDSDeclarationDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"),
						webDB.getDataFromProperties("HRUSEREmpId"));
				commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getTDSDeclarationDocNo(), webDB.getDataFromProperties("HRUSEREmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HrRqtUsername"),
						webDB.getDataFromProperties("HRUSEREmpId"));
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Select TDS Tax Regime
	 * 
	 * @author Kishore-RMITS
	 */
	public void EmployeeTDSRegimeChoose() {
		if (flag) {
			webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationRegimeType, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(1000);
				flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDeclarationRegimeType, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "TDS Tax Regime Field Is Clicked");
					webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationNewTax, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(1000);
						flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDeclarationNewTax, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "TDS New Tax Is Selected");
							webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationDocNoToPost, ElementType.Xpath);
							String TDSDeclarationDocNo = webDB.getDomPropertyFromElement(
									AttendanceLeaveLocators.TDSDeclarationDocNoToPost, ElementType.Xpath,
									commonFunc.getDataFromTestDataSheet("Value"));
							VariableHandler.setTDSDeclarationDocNo(TDSDeclarationDocNo);
							logs.logging("info", "TDS DECLARATION No is:" + VariableHandler.getTDSDeclarationDocNo());
							flag = webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationPostButton,
									ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(2000);
								flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDeclarationPostButton,
										ElementType.Xpath);
								approvalFunc.waitingForApprovalWithFordwarder(
										commonFunc.getDataFromTestDataSheet("HodValue"));
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
										VariableHandler.getTDSDeclarationDocNo(),
										webDB.getDataFromProperties("HodEmpId"),
										commonFunc.getDataFromTestDataSheet("Unit_100"),
										System.getProperty("HariUsername"));
								flag = commonFunc.logoutUser();
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Enter Section Wise Amount
	 * 
	 * @author Kishore-RMITS
	 */
	public void EmployeeTDSSectionWise(String TDSAmount) {
		if (flag) {
			webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationDocNoToPost, ElementType.Xpath);
			String TDSDeclarationDocNo = webDB.getDomPropertyFromElement(
					AttendanceLeaveLocators.TDSDeclarationDocNoToPost, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setTDSDeclarationDocNo(TDSDeclarationDocNo);
			logs.logging("info", "TDS DECLARATION No is:" + VariableHandler.getTDSDeclarationDocNo());
			if (flag) {
				webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationSectionWiseField, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(AttendanceLeaveLocators.TDSDeclarationSectionWiseField,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					webDB.clearText(AttendanceLeaveLocators.TDSDeclarationSectionWiseField, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.TDSDeclarationSectionWiseField, TDSAmount,
							ElementType.Xpath);
					webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationSaveButton, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(1000);
						flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDeclarationSaveButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "TDS Declaration is Saved");
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(AttendanceLeaveLocators.TDSDeclarationPostButton,
									ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(5000);
								flag = webDB.clickAnElement(AttendanceLeaveLocators.TDSDeclarationPostButton,
										ElementType.Xpath);
								approvalFunc.waitingForApprovalWithFordwarder(
										commonFunc.getDataFromTestDataSheet("HodValue"));
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
										VariableHandler.getTDSDeclarationDocNo(),
										webDB.getDataFromProperties("HodEmpId"),
										commonFunc.getDataFromTestDataSheet("Unit_100"),
										System.getProperty("HariUsername"));
								flag = commonFunc.logoutUser();
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Check Overtime based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 * @param i
	 */
	public boolean CheckOvertimeBasedOnEmployeeWise() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToEmployeeOvertime();
		OvertimeEmployeeAdd(commonFunc.getDataFromTestDataSheet("Calendar"),
				commonFunc.getDataFromTestDataSheet("Year202526"),
				commonFunc.getDataFromTestDataSheet("OvertimePeriod"),
				commonFunc.getDataFromTestDataSheet("OvertimeReference"));
		EmployeeEntryLineData(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("OvertimeDate"), commonFunc.getDataFromTestDataSheet("OTType"));
		EmployeeOvertimeDataSelect(commonFunc.getDataFromTestDataSheet("OvertimeHours"));
		EmployeeOvertimeDocumentPost();
		return flag;
	}

	/**
	 * This method is used To Check Overtime based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckOvertimeEntryBasedOnForwardToApproval() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToEmployeeOvertime();
		OvertimeEmployeeAdd(commonFunc.getDataFromTestDataSheet("Calendar"),
				commonFunc.getDataFromTestDataSheet("Year202526"),
				commonFunc.getDataFromTestDataSheet("OvertimePeriod"),
				commonFunc.getDataFromTestDataSheet("OvertimeReference"));
		EmployeeEntryLineData(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("OvertimeDate"), commonFunc.getDataFromTestDataSheet("OTType"));
		EmployeeOvertimeDataSelect(commonFunc.getDataFromTestDataSheet("OvertimeHours"));
		EmployeeOvertimeDocumentPost();
		return flag;
	}

	/**
	 * This method is used To Check Overtime Entry based on Return to approval
	 * record
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckOvertimeEntryBasedOnReturnToApprovalRecord() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToEmployeeOvertime();
		OvertimeEmployeeAdd(commonFunc.getDataFromTestDataSheet("Calendar"),
				commonFunc.getDataFromTestDataSheet("Year202526"),
				commonFunc.getDataFromTestDataSheet("OvertimePeriod"),
				commonFunc.getDataFromTestDataSheet("OvertimeReference"));
		EmployeeEntryLineData(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("OvertimeDate"), commonFunc.getDataFromTestDataSheet("OTType"));
		EmployeeOvertimeDataSelect(commonFunc.getDataFromTestDataSheet("OvertimeHours"));
		EmployeeOvertimeDocumentReturn();
		return flag;
	}

	/**
	 * This method is used To Check Overtime Entry Based on Cancelled Document
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckOvertimeEntryBasedOnCancelledDocument() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToEmployeeOvertime();
		OvertimeEmployeeAdd(commonFunc.getDataFromTestDataSheet("Calendar"),
				commonFunc.getDataFromTestDataSheet("Year202526"),
				commonFunc.getDataFromTestDataSheet("OvertimePeriod"),
				commonFunc.getDataFromTestDataSheet("OvertimeReference"));
		EmployeeEntryLineData(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("OvertimeDate"), commonFunc.getDataFromTestDataSheet("OTType"));
		EmployeeOvertimeDataCancel(commonFunc.getDataFromTestDataSheet("OvertimeHours"));
		return flag;
	}

	/**
	 * This method is used To Check TDS Declaration to Revise
	 * 
	 * @author Kishore-RMITS
	 * @param Calendar
	 * @param Year202526
	 * @param OvertimePeriod
	 * @param OvertimeReference
	 */
	public void OvertimeEmployeeAdd(String Calendar, String Year202526, String OvertimePeriod,
			String OvertimeReference) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeFindSearchAddButton, ElementType.Xpath);
		if (flag) {
			flag = webDB.clickAnElement(AttendanceLeaveLocators.OvertimeFindSearchAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Overtime Add Button is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeFrameScreen, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(2000);
					flag = webDB.clickAnElement(AttendanceLeaveLocators.OvertimeFrameScreen, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeFrameScreen, ElementType.Xpath);
					webDB.switchToFrame(AttendanceLeaveLocators.OvertimeFrameScreen, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeDocCalSelectLOV, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.OvertimeDocCalSelectLOV,
								Calendar);
						if (flag) {
							logs.logging("INFO", "Overtime Calendar is Selected");
							webDB.switchToFrame(AttendanceLeaveLocators.OvertimeFrameScreen, ElementType.Xpath);
							flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeYearLOV, ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.OvertimeYearLOV,
									Year202526);
							if (flag) {
								logs.logging("INFO", "Overtime Year is Selected");
								webDB.switchToFrame(AttendanceLeaveLocators.OvertimeFrameScreen, ElementType.Xpath);
								flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimePeriodLOV,
										ElementType.Xpath);
								commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.OvertimePeriodLOV,
										OvertimePeriod);
								if (flag) {
									logs.logging("INFO", "Overtime Period is Selected");
									webDB.switchToFrame(AttendanceLeaveLocators.OvertimeFrameScreen, ElementType.Xpath);
									flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeReferenceField,
											ElementType.Xpath);
									if (flag) {
										flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.OvertimeReferenceField,
												OvertimeReference, ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Overtime Reference Entered");
											flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeDocSave,
													ElementType.Xpath);
											if (flag) {
												flag = webDB.clickAnElement(AttendanceLeaveLocators.OvertimeDocSave,
														ElementType.Xpath);
												if (flag) {
													logs.logging("INFO", "Overtime Document Saved");
													webDB.switchToDefaultContent();
													flag = webDB.waitForElement(
															AttendanceLeaveLocators.OvertimeEntryAddButton,
															ElementType.Xpath);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Enter the Overtime Line Data
	 * 
	 * @author Kishore-RMITS
	 * @param EmployeeDurai
	 * @param OvertimeDate
	 */
	public void EmployeeEntryLineData(String EmployeeDurai, String OvertimeDate, String Type) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeEntryAddButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(5000);
			flag = webDB.clickAnElement(AttendanceLeaveLocators.OvertimeEntryAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Overtime Add Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeEntryEmpLOVField, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(2000);
					flag = webDB.clickAnElement(AttendanceLeaveLocators.OvertimeEntryEmpLOVField, ElementType.Xpath);
					flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeLOVButton, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(2000);
						commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.OvertimeLOVButton,
								EmployeeDurai);
						if (flag) {
							logs.logging("INFO", "Overtime Employee is Selected");
							webDB.pressTabKey();
							flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimePrefixField, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(AttendanceLeaveLocators.OvertimePrefixField, ElementType.Xpath);
								webDB.selectDropDownByValue(AttendanceLeaveLocators.OvertimePrefixSelect, Type,
										ElementType.Xpath);
								logs.logging("INFO", "Prefix Is Selected:" + Type);
								flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeDateField,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AttendanceLeaveLocators.OvertimeDateField, ElementType.Xpath);
									flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeDateSelect,
											ElementType.Xpath);
									commonFunc.hardWait(1000);
									webDB.moveToElement(AttendanceLeaveLocators.OvertimeDateSelect, ElementType.Xpath);
									flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.OvertimeDateSelect,
											OvertimeDate, ElementType.Xpath);
									logs.logging("INFO", "Date is Selected");
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Select the Employee Overtime Data Select
	 * 
	 * @author Kishore-RMITS
	 * @param OvertimeHours
	 */
	public void EmployeeOvertimeDataSelect(String OvertimeHours) {
		webDB.pressTabKey();
		flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeHoursField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.OvertimeHoursField, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeHoursField, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.OvertimeHoursField, OvertimeHours,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Hours is Entered");
				flag = webDB.waitForElementUntilGivenTime(AttendanceLeaveLocators.OvertimeLineSaveButton,
						ElementType.Xpath, 45);
				if (flag) {
					commonFunc.hardWait(2000);
					webDB.clickAnElement(AttendanceLeaveLocators.OvertimeLineSaveButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Overtime Entry IS Saved");
						flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeSAVEButton, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(5000);
							webDB.clickAnElement(AttendanceLeaveLocators.OvertimeSAVEButton, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Overtime Document Have been Saved");
								commonFunc.checkSuccessToastMessageDisappear();
								webDB.waitForElement(AttendanceLeaveLocators.OvertimeDocNoField, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Post The Overtime Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void EmployeeOvertimeDocumentPost() {
		webDB.waitForElement(AttendanceLeaveLocators.OvertimeDocNoField, ElementType.Xpath);
		String OvertimeDocNo = webDB.getTextFromElement(AttendanceLeaveLocators.OvertimeDocNoField, ElementType.Xpath);
		VariableHandler.setOvertimeDocNo(OvertimeDocNo);
		logs.logging("info", "Overtime No is:" + VariableHandler.getOvertimeDocNo());
		flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeUploadAmtButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(5000);
			webDB.clickAnElement(AttendanceLeaveLocators.OvertimeUploadAmtButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Overtime Amount Have been Uploaded");
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				OvertimeAmount(webDB.getDataFromProperties("OTAmount"));
				flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimePostButton, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(5000);
					webDB.clickAnElement(AttendanceLeaveLocators.OvertimePostButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Overtime post button is clicked");
						approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
								VariableHandler.getOvertimeDocNo(), webDB.getDataFromProperties("HodEmpId"),
								commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
						flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeLineSaveButton, ElementType.Xpath);
						flag = commonFunc.logoutUser();
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Post The Overtime Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void EmployeeOvertimeDocumentReturn() {
		webDB.waitForElement(AttendanceLeaveLocators.OvertimeDocNoField, ElementType.Xpath);
		String OvertimeDocNo = webDB.getTextFromElement(AttendanceLeaveLocators.OvertimeDocNoField, ElementType.Xpath);
		VariableHandler.setOvertimeDocNo(OvertimeDocNo);
		logs.logging("info", "Overtime No is:" + VariableHandler.getOvertimeDocNo());
		flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeUploadAmtButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(5000);
			webDB.clickAnElement(AttendanceLeaveLocators.OvertimeUploadAmtButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Overtime Amount Have been Uploaded");
				commonFunc.hardWait(200);
				commonFunc.checkRefreshCellsDisappear();
				OvertimeAmount(webDB.getDataFromProperties("OTAmount"));
				flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimePostButton, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(5000);
					webDB.clickAnElement(AttendanceLeaveLocators.OvertimePostButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Overtime post button is clicked");
						approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
								VariableHandler.getOvertimeDocNo(), webDB.getDataFromProperties("HodEmpId"),
								commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"),
								webDB.getDataFromProperties("HRUSEREmpId"));
						commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
								VariableHandler.getOvertimeDocNo(), webDB.getDataFromProperties("HRUSEREmpId"),
								commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HrRqtUsername"),
								webDB.getDataFromProperties("HRUSEREmpId"));
						flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeLineSaveButton, ElementType.Xpath);
						flag = commonFunc.logoutUser();
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Select the Employee Overtime Data Select
	 * 
	 * @author Kishore-RMITS
	 * @param OvertimeHours
	 */
	public void EmployeeOvertimeDataCancel(String OvertimeHours) {
		webDB.pressTabKey();
		flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeHoursField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.OvertimeHoursField, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeHoursField, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.OvertimeHoursField, OvertimeHours,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Hours is Entered");
				flag = webDB.waitForElementUntilGivenTime(AttendanceLeaveLocators.OvertimeLineSaveButton,
						ElementType.Xpath, 45);
				if (flag) {
					commonFunc.hardWait(2000);
					webDB.clickAnElement(AttendanceLeaveLocators.OvertimeLineSaveButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Overtime Entry IS Saved");
						flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeSAVEButton, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(5000);
							webDB.clickAnElement(AttendanceLeaveLocators.OvertimeSAVEButton, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Overtime Document Have been Saved");
								commonFunc.checkSuccessToastMessageDisappear();
								webDB.waitForElement(AttendanceLeaveLocators.OvertimeDocNoField, ElementType.Xpath);
								String OvertimeDocNo = webDB.getTextFromElement(
										AttendanceLeaveLocators.OvertimeDocNoField, ElementType.Xpath);
								VariableHandler.setOvertimeDocNo(OvertimeDocNo);
								logs.logging("info", "Overtime No is:" + VariableHandler.getOvertimeDocNo());
								flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeUploadAmtButton,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(5000);
									webDB.clickAnElement(AttendanceLeaveLocators.OvertimeUploadAmtButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Overtime Amount Have been Uploaded");
										flag = webDB.waitForElement(AttendanceLeaveLocators.OvertimeCancelButton,
												ElementType.Xpath);
										if (flag) {
											commonFunc.hardWait(5000);
											webDB.clickAnElement(AttendanceLeaveLocators.OvertimeCancelButton,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Overtime Amount Has Been Cancelled");
												flag = webDB.waitForElement(
														AttendanceLeaveLocators.OvertimeFindSearchButton,
														ElementType.Xpath);
												flag = commonFunc.logoutUser();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Check The Overtime Amount Validation
	 * 
	 * @author Kishore-RMITS
	 * @param
	 */
	public void OvertimeAmount(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "Overtime Calculation N.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(ItemMultiple.length);
		for (int i = 2; i < ItemMultiple.length; i++) {
			String EmpId = ItemMultiple[i][0];
			String OTCal = ItemMultiple[i][9];

			if (!EmpId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.OvertimeLinesDetails(VariableHandler.getOvertimeDocNo(), EmpId,
						commonFunc.getDataFromTestDataSheet("OTAmount")), OTCal);
				logs.logging("INFO", "Overtime Amount Has Been Updated For :" + EmpId);
			}
		}
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value 8: " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Check Travel Request Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTravelRequestBasedOnEmployeeWise() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTravelRequest();
		AddTravelEmployee(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("MobileNo"), commonFunc.getDataFromTestDataSheet("EmailID"),
				commonFunc.getDataFromTestDataSheet("PurposeOfTravel"));
		EmployeeTraveldates(commonFunc.getDataFromTestDataSheet("DepartCity"),
				commonFunc.getDataFromTestDataSheet("DestCity"));
		EmployeeTravelExpenses(commonFunc.getDataFromTestDataSheet("Expenses"),
				commonFunc.getDataFromTestDataSheet("ExpenseAmount"),
				commonFunc.getDataFromTestDataSheet("ExpenseRef"));
		EmployeeAdditionalTravellers(commonFunc.getDataFromTestDataSheet("AddTravEmployee"));
		return flag;
	}

	/**
	 * This method is used To Check Travel Request Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTravelRequestBasedOnForwardToApproval() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTravelRequest();
		AddTravelEmployee(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("MobileNo"), commonFunc.getDataFromTestDataSheet("EmailID"),
				commonFunc.getDataFromTestDataSheet("PurposeOfTravel"));
		EmployeeTraveldates(commonFunc.getDataFromTestDataSheet("DepartCity"),
				commonFunc.getDataFromTestDataSheet("DestCity"));
		EmployeeTravelExpenses(commonFunc.getDataFromTestDataSheet("Expenses"),
				commonFunc.getDataFromTestDataSheet("ExpenseAmount"),
				commonFunc.getDataFromTestDataSheet("ExpenseRef"));
		EmployeeAdditionalTravellers(commonFunc.getDataFromTestDataSheet("AddTravEmployee"));
		return flag;
	}

	/**
	 * This method is used To Check Travel Request Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTravelRequestBasedOnReturnToApproval() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTravelRequest();
		AddTravelEmployee(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("MobileNo"), commonFunc.getDataFromTestDataSheet("EmailID"),
				commonFunc.getDataFromTestDataSheet("PurposeOfTravel"));
		EmployeeTraveldates(commonFunc.getDataFromTestDataSheet("DepartCity"),
				commonFunc.getDataFromTestDataSheet("DestCity"));
		EmployeeTravelExpenses(commonFunc.getDataFromTestDataSheet("Expenses"),
				commonFunc.getDataFromTestDataSheet("ExpenseAmount"),
				commonFunc.getDataFromTestDataSheet("ExpenseRef"));
		EmployeeAdditionalTravellersReturn(commonFunc.getDataFromTestDataSheet("AddTravEmployee"));
		return flag;
	}

	/**
	 * This method is used To Check Travel Request Based on Cancelling The Travel
	 * Request
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTravelRequestBasedOnCancellingTheTravelRequest() {
//		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
//				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToTravelRequest();
		AddTravelEmployee(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("MobileNo"), commonFunc.getDataFromTestDataSheet("EmailID"),
				commonFunc.getDataFromTestDataSheet("PurposeOfTravel"));
		EmployeeTraveldateswithCancellingDoc(commonFunc.getDataFromTestDataSheet("DepartCity"),
				commonFunc.getDataFromTestDataSheet("DestCity"));
		return flag;
	}

	/**
	 * This method is used To Add the Travel Employee
	 * 
	 * @author Kishore-RMITS
	 * @param EmployeeDurai
	 * @param MobileNo
	 * @param EmailID
	 * @param PurposeOfTravel
	 */
	public void AddTravelEmployee(String EmployeeDurai, String MobileNo, String EmailID, String PurposeOfTravel) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelRequestFindAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.TravelRequestFindAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Travel Request Add Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.TravelRequestEntryEmployeeLOV, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TravelRequestEntryEmployeeLOV,
							EmployeeDurai);
					logs.logging("INFO", "Travel Request Employee Is Selected");
					flag = webDB.waitForElement(AttendanceLeaveLocators.TravelMobileField, ElementType.Xpath);
					if (flag) {
						flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.TravelMobileField, MobileNo,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Mobile No Entered");
							flag = webDB.waitForElement(AttendanceLeaveLocators.TravelEmailField, ElementType.Xpath);
							if (flag) {
								flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.TravelEmailField, EmailID,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Email Is Entered");
									flag = webDB.waitForElement(AttendanceLeaveLocators.TravelPurposeOfTravelField,
											ElementType.Xpath);
									if (flag) {
										flag = webDB.sendTextToAnElement(
												AttendanceLeaveLocators.TravelPurposeOfTravelField, PurposeOfTravel,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Purpose Of Travel Is Entered");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Select the Departure and Travel Dates
	 * 
	 * @author Kishore-RMITS
	 * @param DepartCity
	 * @param DestCity
	 * @param EmployeeDurai
	 */
	public void EmployeeTraveldates(String DepartCity, String DestCity) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String tomorrowDate = myDateObj.format(myFormatObj);
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelDepartureDate, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.TravelDepartureDate, ElementType.Xpath);
			webDB.sendTextToAnElement(AttendanceLeaveLocators.TravelDepartureDate, Today, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Departure Date is Entered");
				flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReturnDate, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.TravelReturnDate, ElementType.Xpath);
					webDB.sendTextToAnElement(AttendanceLeaveLocators.TravelReturnDate, Today, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Return Date is Entered");
						flag = webDB.waitForElement(AttendanceLeaveLocators.TravelDepartureCity, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TravelDepartureCity,
									DepartCity);
							logs.logging("INFO", "Departure City Is Selected");
							flag = webDB.waitForElement(AttendanceLeaveLocators.TravelDestinationCity,
									ElementType.Xpath);
							if (flag) {
								commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TravelDestinationCity,
										DestCity);
								logs.logging("INFO", "Destination City Is Selected");
								flag = webDB.waitForElement(AttendanceLeaveLocators.TravelRequestSaveButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AttendanceLeaveLocators.TravelRequestSaveButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Travel Request Document Is Saved");
										commonFunc.checkSuccessToastMessageDisappear();
										commonFunc.hardWait(2000);
										webDB.waitForElement(AttendanceLeaveLocators.TravelRequestDocNoField,
												ElementType.Xpath);
										String TravelRequestDocNo = webDB.getDomPropertyFromElement(
												AttendanceLeaveLocators.TravelRequestDocNoField, ElementType.Xpath,
												commonFunc.getDataFromTestDataSheet("Value"));
										VariableHandler.setTravelRequestDocNo(TravelRequestDocNo);
										logs.logging("info",
												"Travel Request No is:" + VariableHandler.getTravelRequestDocNo());
										flag = webDB.waitForElement(AttendanceLeaveLocators.TravelRequestExpensesButton,
												ElementType.Xpath);
										if (flag) {
											commonFunc.hardWait(6000);
											webDB.clickAnElement(AttendanceLeaveLocators.TravelRequestExpensesButton,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Expenses Button Is Clicked");
												flag = webDB.waitForElement(
														AttendanceLeaveLocators.TravelExpenseAddButton,
														ElementType.Xpath);
												if (flag) {
													commonFunc.hardWait(4000);
													webDB.clickAnElement(AttendanceLeaveLocators.TravelExpenseAddButton,
															ElementType.Xpath);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Add the Travel Expenses
	 * 
	 * @author Kishore-RMITS
	 * @param Expenses
	 * @param ExpenseAmount
	 * @param ExpenseRef
	 */
	public void EmployeeTravelExpenses(String Expenses, String ExpenseAmount, String ExpenseRef) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelExpenseLOV, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TravelExpenseLOV, Expenses);
			if (flag) {
				logs.logging("INFO", "Expenses Is Selected");
				flag = webDB.waitForElement(AttendanceLeaveLocators.TravelExpenseAmount, ElementType.Xpath);
				if (flag) {
					webDB.pressTabKey();
					webDB.sendTextToAnElement(AttendanceLeaveLocators.TravelExpenseAmount, ExpenseAmount,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Expenses Amount Is Entered");
						flag = webDB.waitForElement(AttendanceLeaveLocators.TravelExpenseRefField, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(AttendanceLeaveLocators.TravelExpenseRefField, ElementType.Xpath);
							webDB.sendTextToAnElement(AttendanceLeaveLocators.TravelExpenseRefField, ExpenseRef,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Expenses Reference Is Entered");
								flag = webDB.waitForElement(AttendanceLeaveLocators.TravelExpenseSaveButton,
										ElementType.Xpath);
								if (flag) {
									flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelExpenseSaveButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Expenses Line Data Is Saved");
										flag = webDB.waitForElement(AttendanceLeaveLocators.TravelExpenseCloseButton,
												ElementType.Xpath);
										if (flag) {
											flag = webDB.clickAnElement(
													AttendanceLeaveLocators.TravelExpenseCloseButton,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Expenses Line Data Is Saved");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Add Additional Travellers
	 * 
	 * @author Kishore-RMITS
	 * @param AddTravEmployee
	 */
	public void EmployeeAdditionalTravellers(String AddTravEmployee) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAdditionalTravellersButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelAdditionalTravellersButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Additional Travellers Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAttTraAddButton, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelAttTraAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Additional Travellers Add Button Is Clicked");
						flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAttTraEMPLOV, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TravelAttTraEMPLOV,
									AddTravEmployee);
							if (flag) {
								logs.logging("INFO", "Expenses Is Selected");
								flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAttTraSaveButton,
										ElementType.Xpath);
								if (flag) {
									flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelAttTraSaveButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Additional Travellers Employee Is Saved");
										flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAttTraCloseButton,
												ElementType.Xpath);
										if (flag) {
											flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelAttTraCloseButton,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Additional Travellers Screen Is Closed");
												flag = webDB.waitForElement(
														AttendanceLeaveLocators.TravelRequestPostButton,
														ElementType.Xpath);
												if (flag) {
													commonFunc.hardWait(4000);
													flag = webDB.clickAnElement(
															AttendanceLeaveLocators.TravelRequestPostButton,
															ElementType.Xpath);
													if (flag) {
														logs.logging("INFO", "Travel Request Post Button Is Clicked");
														approvalFunc.waitingForApprovalWithFordwarder(
																commonFunc.getDataFromTestDataSheet("HodValue"));
														commonFunc.checkRefreshCellsDisappear();
														commonFunc.approveDocuments(
																webDB.getDataFromProperties("ROAU-BU"),
																VariableHandler.getTravelRequestDocNo(),
																webDB.getDataFromProperties("HodEmpId"),
																commonFunc.getDataFromTestDataSheet("Unit_100"),
																System.getProperty("HariUsername"));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Add Additional Travellers
	 * 
	 * @author Kishore-RMITS
	 * @param AddTravEmployee
	 */
	public void EmployeeAdditionalTravellersReturn(String AddTravEmployee) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAdditionalTravellersButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelAdditionalTravellersButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Additional Travellers Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAttTraAddButton, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelAttTraAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Additional Travellers Add Button Is Clicked");
						flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAttTraEMPLOV, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TravelAttTraEMPLOV,
									AddTravEmployee);
							if (flag) {
								logs.logging("INFO", "Expenses Is Selected");
								flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAttTraSaveButton,
										ElementType.Xpath);
								if (flag) {
									flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelAttTraSaveButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Additional Travellers Employee Is Saved");
										flag = webDB.waitForElement(AttendanceLeaveLocators.TravelAttTraCloseButton,
												ElementType.Xpath);
										if (flag) {
											flag = webDB.clickAnElement(AttendanceLeaveLocators.TravelAttTraCloseButton,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Additional Travellers Screen Is Closed");
												flag = webDB.waitForElement(
														AttendanceLeaveLocators.TravelRequestPostButton,
														ElementType.Xpath);
												if (flag) {
													commonFunc.hardWait(4000);
													flag = webDB.clickAnElement(
															AttendanceLeaveLocators.TravelRequestPostButton,
															ElementType.Xpath);
													if (flag) {
														logs.logging("INFO", "Travel Request Post Button Is Clicked");
														approvalFunc.waitingForApprovalWithFordwarder(
																commonFunc.getDataFromTestDataSheet("HodValue"));
														commonFunc.checkRefreshCellsDisappear();
														commonFunc.returnDocuments(
																webDB.getDataFromProperties("ROAU-BU"),
																VariableHandler.getTravelRequestDocNo(),
																webDB.getDataFromProperties("HodEmpId"),
																commonFunc.getDataFromTestDataSheet("Unit_100"),
																System.getProperty("HariUsername"),
																webDB.getDataFromProperties("HRUSEREmpId"));
														commonFunc.returnDocuments(
																webDB.getDataFromProperties("ROAU-BU"),
																VariableHandler.getTravelRequestDocNo(),
																webDB.getDataFromProperties("HRUSEREmpId"),
																commonFunc.getDataFromTestDataSheet("Unit_100"),
																System.getProperty("HrRqtUsername"),
																webDB.getDataFromProperties("HRUSEREmpId"));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Select the Departure and Travel Dates
	 * 
	 * @author Kishore-RMITS
	 * @param DepartCity
	 * @param DestCity
	 * @param EmployeeDurai
	 */
	public void EmployeeTraveldateswithCancellingDoc(String DepartCity, String DestCity) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String tomorrowDate = myDateObj.format(myFormatObj);
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelDepartureDate, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.TravelDepartureDate, ElementType.Xpath);
			webDB.sendTextToAnElement(AttendanceLeaveLocators.TravelDepartureDate, Today, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Departure Date is Entered");
				flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReturnDate, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.TravelReturnDate, ElementType.Xpath);
					webDB.sendTextToAnElement(AttendanceLeaveLocators.TravelReturnDate, Today, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Return Date is Entered");
						flag = webDB.waitForElement(AttendanceLeaveLocators.TravelDepartureCity, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TravelDepartureCity,
									DepartCity);
							logs.logging("INFO", "Departure City Is Selected");
							flag = webDB.waitForElement(AttendanceLeaveLocators.TravelDestinationCity,
									ElementType.Xpath);
							if (flag) {
								commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.TravelDestinationCity,
										DestCity);
								logs.logging("INFO", "Destination City Is Selected");
								flag = webDB.waitForElement(AttendanceLeaveLocators.TravelRequestSaveButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(AttendanceLeaveLocators.TravelRequestSaveButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Travel Request Document Is Saved");
										commonFunc.checkSuccessToastMessageDisappear();
										commonFunc.hardWait(2000);
										webDB.waitForElement(AttendanceLeaveLocators.TravelRequestDocNoField,
												ElementType.Xpath);
										String TravelRequestDocNo = webDB.getDomPropertyFromElement(
												AttendanceLeaveLocators.TravelRequestDocNoField, ElementType.Xpath,
												commonFunc.getDataFromTestDataSheet("Value"));
										VariableHandler.setTravelRequestDocNo(TravelRequestDocNo);
										logs.logging("info",
												"Travel Request No is:" + VariableHandler.getTravelRequestDocNo());
										webDB.waitForElement(AttendanceLeaveLocators.TravelRequestCancelButton,
												ElementType.Xpath);
										if (flag) {
											commonFunc.hardWait(4000);
											webDB.clickAnElement(AttendanceLeaveLocators.TravelRequestCancelButton,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Travel Request Document Is Cancelled");
												flag = webDB.waitForElement(
														AttendanceLeaveLocators.TravelDestinationCity,
														ElementType.Xpath);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Check Travel Reimbursement Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTravelReimbursementBasedOnEmployeeWise() {
//		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
//				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToTravelRequest();
		AddTravelEmployee(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("MobileNo"), commonFunc.getDataFromTestDataSheet("EmailID"),
				commonFunc.getDataFromTestDataSheet("PurposeOfTravel"));
		EmployeeTraveldates(commonFunc.getDataFromTestDataSheet("DepartCity"),
				commonFunc.getDataFromTestDataSheet("DestCity"));
		EmployeeTravelExpenses(commonFunc.getDataFromTestDataSheet("Expenses"),
				commonFunc.getDataFromTestDataSheet("ExpenseAmount"),
				commonFunc.getDataFromTestDataSheet("ExpenseRef"));
		EmployeeAdditionalTravellers(commonFunc.getDataFromTestDataSheet("AddTravEmployee"));
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTravelReimbursement();
		TravelReimbursementDocEntry(commonFunc.getDataFromTestDataSheet("TravelCity"));
		TravelReimbursementExpensesDetails(commonFunc.getDataFromTestDataSheet("TravelReimAmount"));
		return flag;
	}

	/**
	 * This method is used To Check Travel Reimbursement Based on Forward To
	 * Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTravelReimbursementBasedOnForwardToApproval() {
//		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
//				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToTravelRequest();
		AddTravelEmployee(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("MobileNo"), commonFunc.getDataFromTestDataSheet("EmailID"),
				commonFunc.getDataFromTestDataSheet("PurposeOfTravel"));
		EmployeeTraveldates(commonFunc.getDataFromTestDataSheet("DepartCity"),
				commonFunc.getDataFromTestDataSheet("DestCity"));
		EmployeeTravelExpenses(commonFunc.getDataFromTestDataSheet("Expenses"),
				commonFunc.getDataFromTestDataSheet("ExpenseAmount"),
				commonFunc.getDataFromTestDataSheet("ExpenseRef"));
		EmployeeAdditionalTravellers(commonFunc.getDataFromTestDataSheet("AddTravEmployee"));
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTravelReimbursement();
		TravelReimbursementDocEntry(commonFunc.getDataFromTestDataSheet("TravelCity"));
		TravelReimbursementExpensesDetails(commonFunc.getDataFromTestDataSheet("TravelReimAmount"));
		return flag;
	}

	/**
	 * This method is used To Check Travel Reimbursement Based on Forward To
	 * Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean CheckTravelReimbursementBasedOnReturnToApproval() {
//		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
//				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToTravelRequest();
		AddTravelEmployee(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("MobileNo"), commonFunc.getDataFromTestDataSheet("EmailID"),
				commonFunc.getDataFromTestDataSheet("PurposeOfTravel"));
		EmployeeTraveldates(commonFunc.getDataFromTestDataSheet("DepartCity"),
				commonFunc.getDataFromTestDataSheet("DestCity"));
		EmployeeTravelExpenses(commonFunc.getDataFromTestDataSheet("Expenses"),
				commonFunc.getDataFromTestDataSheet("ExpenseAmount"),
				commonFunc.getDataFromTestDataSheet("ExpenseRef"));
		EmployeeAdditionalTravellers(commonFunc.getDataFromTestDataSheet("AddTravEmployee"));
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToTravelReimbursement();
		TravelReimbursementDocEntry(commonFunc.getDataFromTestDataSheet("TravelCity"));
		TravelReimbursementExpensesDetails(commonFunc.getDataFromTestDataSheet("TravelReimAmount"));
		return flag;
	}

	/**
	 * This method is used To Enter the Data For Travel Reimbursement
	 * 
	 * @author Kishore-RMITS
	 * @param TravelCity
	 */
	public void TravelReimbursementDocEntry(String TravelCity) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String tomorrowDate = myDateObj.format(myFormatObj);
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimbFindSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.TravelReimbFindSearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Find Search Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimbDocSelect, ElementType.CssSelector);
				if (flag) {
					commonFunc.hardWait(2000);
					webDB.clickAnElement(AttendanceLeaveLocators.TravelReimbDocSelect, ElementType.CssSelector);
					flag = webDB.waitForElement(AttendanceLeaveLocators.TravelRePlaceOfVisitAddButton,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.TravelRePlaceOfVisitAddButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Add Button Is Clicked");
							flag = webDB.waitForElement(AttendanceLeaveLocators.DateFromField, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(AttendanceLeaveLocators.DateFromField, ElementType.Xpath);
								webDB.sendTextToAnElement(AttendanceLeaveLocators.DateFromField, Today,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Date From Is Selected");
									flag = webDB.waitForElement(AttendanceLeaveLocators.DateToField, ElementType.Xpath);
									if (flag) {
										webDB.pressTabKey();
										webDB.clickAnElement(AttendanceLeaveLocators.DateToField, ElementType.Xpath);
										webDB.sendTextToAnElement(AttendanceLeaveLocators.DateToField, Today,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Date To Is Selected");
											flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimCityLOV,
													ElementType.Xpath);
											if (flag) {
												webDB.pressTabKey();
												commonFunc.hardWait(5000);
												commonFunc.searchAndSelectValueInFilters(
														AttendanceLeaveLocators.TravelReimCityLOV, TravelCity);
												if (flag) {
													logs.logging("INFO", "Travel City Was Selected");
													webDB.pressTabKey();
													commonFunc.hardWait(5000);
													flag = webDB.waitForElement(
															AttendanceLeaveLocators.PlaceOfVisitSaveButton,
															ElementType.Xpath);
													if (flag) {
														webDB.clickAnElement(
																AttendanceLeaveLocators.PlaceOfVisitSaveButton,
																ElementType.Xpath);
														if (flag) {
															logs.logging("INFO", "Place Of Visit Line Is Saved");
															commonFunc.checkSuccessToastMessageDisappear();
															commonFunc.hardWait(5000);
															webDB.waitForElement(
																	AttendanceLeaveLocators.TravelReimDocNoField,
																	ElementType.Xpath);
															String TravelReimbursementDocNo = webDB.getTextFromElement(
																	AttendanceLeaveLocators.TravelReimDocNoField,
																	ElementType.Xpath);
															VariableHandler.setTravelReimbursementDocNo(
																	TravelReimbursementDocNo);
															logs.logging("info", "Travel Reimbursement No is:"
																	+ VariableHandler.getTravelReimbursementtDocNo());
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Enter the Expenses Details In Travel Reimbursement
	 * 
	 * @author Kishore-RMITS
	 * @param TravelReimAmount
	 */
	public void TravelReimbursementExpensesDetails(String TravelReimAmount) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimExpensesButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(5000);
			webDB.clickAnElement(AttendanceLeaveLocators.TravelReimExpensesButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Travel Reimbursement Expenses Button Is Clicked");
				webDB.switchToFrame(AttendanceLeaveLocators.ExpenseFrame, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.ExpenseAmountField, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(AttendanceLeaveLocators.ExpenseAmountField, ElementType.Xpath);
					logs.logging("INFO", "Expense Amount Field Is Clicked");
					webDB.pressTabKey();
					// webDB.clickAnElement(AttendanceLeaveLocators.ExpenseAmountInput,
					// ElementType.Xpath);
					// webDB.doubleClickAnElement(AttendanceLeaveLocators.ExpenseAmountInput,
					// ElementType.Xpath);
					webDB.clearText(AttendanceLeaveLocators.ExpenseAmountInput, ElementType.Xpath);
					webDB.sendTextToAnElement(AttendanceLeaveLocators.ExpenseAmountInput, TravelReimAmount,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Travel Reimbursement Expenses Button Is Clicked");
						webDB.pressTabKey();
						flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimExpenseSaveButton,
								ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							webDB.clickAnElement(AttendanceLeaveLocators.TravelReimExpenseSaveButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Travel Reimbursement Document Is Saved");
								webDB.pressEscapeKey();
								commonFunc.hardWait(3000);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimPostButton,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(1000);
									webDB.clickAnElement(AttendanceLeaveLocators.TravelReimPostButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Travel Reimbursement Document Is Posted");
										approvalFunc.waitingForApprovalWithFordwarder(
												commonFunc.getDataFromTestDataSheet("HodValue"));
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
												VariableHandler.getTravelReimbursementtDocNo(),
												webDB.getDataFromProperties("HodEmpId"),
												commonFunc.getDataFromTestDataSheet("Unit_100"),
												System.getProperty("HariUsername"),
												webDB.getDataFromProperties("HRUSEREmpId"));
										commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
												VariableHandler.getTravelReimbursementtDocNo(),
												webDB.getDataFromProperties("HRUSEREmpId"),
												commonFunc.getDataFromTestDataSheet("Unit_100"),
												System.getProperty("HrRqtUsername"),
												webDB.getDataFromProperties("HRUSEREmpId"));
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Enter the Expenses Details In Travel Reimbursement
	 * 
	 * @author Kishore-RMITS
	 * @param TravelReimAmount
	 */
	public void TravelReimbursementExpensesDetailsReturnProcess(String TravelReimAmount) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimExpensesButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(5000);
			webDB.clickAnElement(AttendanceLeaveLocators.TravelReimExpensesButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Travel Reimbursement Expenses Button Is Clicked");
				webDB.switchToFrame(AttendanceLeaveLocators.ExpenseFrame, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.ExpenseAmountField, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(AttendanceLeaveLocators.ExpenseAmountField, ElementType.Xpath);
					logs.logging("INFO", "Expense Amount Field Is Clicked");
					webDB.pressTabKey();
					// webDB.clickAnElement(AttendanceLeaveLocators.ExpenseAmountInput,
					// ElementType.Xpath);
					// webDB.doubleClickAnElement(AttendanceLeaveLocators.ExpenseAmountInput,
					// ElementType.Xpath);
					webDB.clearText(AttendanceLeaveLocators.ExpenseAmountInput, ElementType.Xpath);
					webDB.sendTextToAnElement(AttendanceLeaveLocators.ExpenseAmountInput, TravelReimAmount,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Travel Reimbursement Expenses Button Is Clicked");
						webDB.pressTabKey();
						flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimExpenseSaveButton,
								ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							webDB.clickAnElement(AttendanceLeaveLocators.TravelReimExpenseSaveButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Travel Reimbursement Document Is Saved");
								webDB.pressEscapeKey();
								commonFunc.hardWait(3000);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(AttendanceLeaveLocators.TravelReimPostButton,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(1000);
									webDB.clickAnElement(AttendanceLeaveLocators.TravelReimPostButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Travel Reimbursement Document Is Posted");
										approvalFunc.waitingForApprovalWithFordwarder(
												commonFunc.getDataFromTestDataSheet("HodValue"));
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
												VariableHandler.getTravelReimbursementtDocNo(),
												webDB.getDataFromProperties("HodEmpId"),
												commonFunc.getDataFromTestDataSheet("Unit_100"),
												System.getProperty("HariUsername"),
												webDB.getDataFromProperties("HRUSEREmpId"));
										commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
												VariableHandler.getTravelReimbursementtDocNo(),
												webDB.getDataFromProperties("HRUSEREmpId"),
												commonFunc.getDataFromTestDataSheet("Unit_100"),
												System.getProperty("HrRqtUsername"),
												webDB.getDataFromProperties("HRUSEREmpId"));
										flag = commonFunc.logoutUser();
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Check General Expense Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkGeneralExpenseBasedOnEmployeeWise() {
//		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
//				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToGeneralExpense();
		genaralExpenseAddingData(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("GeneralReference"));
		genaralExpenseAddLineData(commonFunc.getDataFromTestDataSheet("GeneralExpense"),
				commonFunc.getDataFromTestDataSheet("GeneralExpAmount"),
				commonFunc.getDataFromTestDataSheet("GeneralExpAmount"));
		return flag;
	}

	/**
	 * To Check General Expense Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkGeneralExpenseBasedOnForwardToApproval() {
//		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
//				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToGeneralExpense();
		genaralExpenseAddingData(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("GeneralReference"));
		genaralExpenseAddLineData(commonFunc.getDataFromTestDataSheet("GeneralExpense"),
				commonFunc.getDataFromTestDataSheet("GeneralExpAmount"),
				commonFunc.getDataFromTestDataSheet("GeneralExpAmount"));
		return flag;
	}

	/**
	 * To Check General Expense Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkGeneralExpenseBasedOnReturnToApproval() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToGeneralExpense();
		genaralExpenseAddingData(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("GeneralReference"));
		genaralExpenseAddLineData(commonFunc.getDataFromTestDataSheet("GeneralExpense"),
				commonFunc.getDataFromTestDataSheet("GeneralExpAmount"),
				commonFunc.getDataFromTestDataSheet("GeneralExpAmount"));
		return flag;
	}

	/**
	 * To Check General Expense Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkGeneralExpenseBasedOnCancellingTheDocument() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToGeneralExpense();
		genaralExpenseAddingData(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
				commonFunc.getDataFromTestDataSheet("GeneralReference"));
		genaralExpenseAddLineDataWithCancelling(commonFunc.getDataFromTestDataSheet("GeneralExpense"),
				commonFunc.getDataFromTestDataSheet("GeneralExpAmount"),
				commonFunc.getDataFromTestDataSheet("GeneralExpAmount"));
		return flag;
	}

	/**
	 * This method is used To Add General Expense Data
	 * 
	 * @author Kishore-RMITS
	 * @param EmployeeDurai
	 * @param GeneralReference
	 */
	public void genaralExpenseAddingData(String EmployeeDurai, String GeneralReference) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpFindAddButton, ElementType.Xpath);
		if (flag) {
			flag = webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpFindAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "General Expense Add Button IS Clicked");
			}
			flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpEmpLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.GeneralExpEmpLov, EmployeeDurai);
				if (flag) {
					logs.logging("INFO", "Employee Is Selected");
					flag = webDB.waitForElement(AttendanceLeaveLocators.GeneraalExpReference, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(AttendanceLeaveLocators.GeneraalExpReference, GeneralReference,
								ElementType.Xpath);
						flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpSaveButton, ElementType.Xpath);
						if (flag) {
							flag = webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpSaveButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "General Reimbursement Document Is Saved");
								commonFunc.checkSuccessToastMessageDisappear();
								commonFunc.hardWait(5000);
								webDB.waitForElement(AttendanceLeaveLocators.GeneralReimDocNoField, ElementType.Xpath);
								String GeneralReimbursementDocNo = webDB.getTextFromElement(
										AttendanceLeaveLocators.GeneralReimDocNoField, ElementType.Xpath);
								VariableHandler.setGeneralReimbursementDocNo(GeneralReimbursementDocNo);
								logs.logging("info", " General Reimbursment Doc No is:"
										+ VariableHandler.getGeneralReimbursementtDocNo());
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Add General Expense Line Data
	 * 
	 * @author Kishore-RMITS
	 * @param GeneralExpense
	 * @param ExpenseDate
	 * @param GeneralExpAmount
	 * @param Today
	 */
	public void genaralExpenseAddLineData(String GeneralExpense, String ExpenseDate, String GeneralExpAmount) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String tomorrowDate = myDateObj.format(myFormatObj);
		flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpLineAddButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(5000);
			flag = webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpLineAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "General Expense Line Data Add Button");
				flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpenseLOV, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(2000);
					commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.GeneralExpenseLOV, GeneralExpense);
					if (flag) {
						logs.logging("INFO", "General Expense Is Selected");
						webDB.pressTabKey();
						flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpDateField, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpDateField, ElementType.Xpath);
							webDB.moveToElement(AttendanceLeaveLocators.GeneralExpDateField, ElementType.Xpath);
							flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.GeneralExpDateField, Today,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "General Expense Date Is Selected");
								webDB.pressTabKey();
								flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpAmountField,
										ElementType.Xpath);
								commonFunc.hardWait(2000);

								webDB.clearTextField(AttendanceLeaveLocators.GeneralExpAmountField, ElementType.Xpath);
//								webDB.moveToElement(AttendanceLeaveLocators.GeneralExpAmountField, ElementType.Xpath);
//								flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.GeneralExpAmountField,
//										GeneralExpAmount, ElementType.Xpath);
								commonFunc.verifyAddTableSpecs(AttendanceLeaveLocators.GeneralExpAmountField,
										AttendanceLeaveLocators.GeneralExpAmountInput, GeneralExpAmount);
								if (flag) {
									logs.logging("INFO", "General Expense Amount Is Entered");
									webDB.pressTabKey();
									flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpRemarksField,
											ElementType.Xpath);
									commonFunc.verifyAddTableSpecs(AttendanceLeaveLocators.GeneralExpRemarksField,
											AttendanceLeaveLocators.GeneralExpRemarksField, GeneralExpense);
									if (flag) {
										logs.logging("INFO", "General Expense Remarks Entered");
										webDB.pressTabKey();
										commonFunc.hardWait(5000);
										flag = webDB.waitForElement(
												AttendanceLeaveLocators.GeneralExpenseLineSaveButton,
												ElementType.Xpath);
										webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpenseLineSaveButton,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "General Expense line Saved Successfully");
											if (flag) {
												commonFunc.hardWait(5000);
												webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpensePostButton,
														ElementType.Xpath);
												if (flag) {
													logs.logging("INFO", "General Reimbursement Document Is Posted");
													approvalFunc.waitingForApprovalWithFordwarder(
															commonFunc.getDataFromTestDataSheet("HodValue"));
													commonFunc.checkRefreshCellsDisappear();
													commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
															VariableHandler.getGeneralReimbursementtDocNo(),
															webDB.getDataFromProperties("HodEmpId"),
															commonFunc.getDataFromTestDataSheet("Unit_100"),
															System.getProperty("HariUsername"));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Add General Expense Line Data
	 * 
	 * @author Kishore-RMITS
	 * @param GeneralExpense
	 * @param ExpenseDate
	 * @param GeneralExpAmount
	 * @param Today
	 */
	public void genaralExpenseAddLineDataWithCancelling(String GeneralExpense, String ExpenseDate,
			String GeneralExpAmount) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String tomorrowDate = myDateObj.format(myFormatObj);
		flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpLineAddButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(5000);
			flag = webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpLineAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "General Expense Line Data Add Button");
				flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpenseLOV, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(2000);
					commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.GeneralExpenseLOV, GeneralExpense);
					if (flag) {
						logs.logging("INFO", "General Expense Is Selected");
						webDB.pressTabKey();
						flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpDateField, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpDateField, ElementType.Xpath);
							webDB.moveToElement(AttendanceLeaveLocators.GeneralExpDateField, ElementType.Xpath);
							flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.GeneralExpDateField, Today,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "General Expense Date Is Selected");
								webDB.pressTabKey();
								flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpAmountField,
										ElementType.Xpath);
								commonFunc.hardWait(2000);

								webDB.clearTextField(AttendanceLeaveLocators.GeneralExpAmountField, ElementType.Xpath);
//								webDB.moveToElement(AttendanceLeaveLocators.GeneralExpAmountField, ElementType.Xpath);
//								flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.GeneralExpAmountField,
//										GeneralExpAmount, ElementType.Xpath);
								commonFunc.verifyAddTableSpecs(AttendanceLeaveLocators.GeneralExpAmountField,
										AttendanceLeaveLocators.GeneralExpAmountInput, GeneralExpAmount);
								if (flag) {
									logs.logging("INFO", "General Expense Amount Is Entered");
									webDB.pressTabKey();
									flag = webDB.waitForElement(AttendanceLeaveLocators.GeneralExpRemarksField,
											ElementType.Xpath);
									commonFunc.verifyAddTableSpecs(AttendanceLeaveLocators.GeneralExpRemarksField,
											AttendanceLeaveLocators.GeneralExpRemarksField, GeneralExpense);
									if (flag) {
										logs.logging("INFO", "General Expense Remarks Entered");
										webDB.pressTabKey();
										commonFunc.hardWait(5000);
										flag = webDB.waitForElement(
												AttendanceLeaveLocators.GeneralExpenseLineSaveButton,
												ElementType.Xpath);
										webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpenseLineSaveButton,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "General Expense line Saved Successfully");
											commonFunc.hardWait(5000);
											flag = webDB.waitForElement(
													AttendanceLeaveLocators.GeneralExpenseCancelButton,
													ElementType.Xpath);
											webDB.clickAnElement(AttendanceLeaveLocators.GeneralExpenseCancelButton,
													ElementType.Xpath);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Check Resign/Termination Request Based on Request Date
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkResignTerminationRequestBasedOnRequestDate() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.navigateToResignationRequest();
		resignRequestAddingEmployee(commonFunc.getDataFromTestDataSheet("ResignDate"),
				commonFunc.getDataFromTestDataSheet("ResignEmployee"),
				commonFunc.getDataFromTestDataSheet("ResignReference"));
		resignRequestEmployeepost();
		commonFunc.navigateToFullAndFinalSettlement();
		pendingForFNFReverseProcess();
		commonFunc.navigateToResignationRequest();
		resignationRequestCancel();
		return flag;
	}

	/**
	 * This method is used To Check Resign/Termination Request Based on Forward To
	 * Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkResignTerminationRequestBasedOnForwardToApproval() {
//		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
//				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToResignationRequest();
		resignRequestAddingEmployee(commonFunc.getDataFromTestDataSheet("ResignDate"),
				commonFunc.getDataFromTestDataSheet("ResignEmployee"),
				commonFunc.getDataFromTestDataSheet("ResignReference"));
		resignRequestEmployeepost();
		commonFunc.navigateToFullAndFinalSettlement();
		pendingForFNFReverseProcess();
		commonFunc.navigateToResignationRequest();
		resignationRequestCancel();
		return flag;
	}

	/**
	 * This method is used To Check Resign/Termination Request Based on Adjust
	 * Encash Leave
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkResignTerminationRequestBasedOnAdjustEncashLeave() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToResignationRequest();
		resignRequestAddingEmployeewithAdjustEncashLeave(commonFunc.getDataFromTestDataSheet("ResignDate"),
				commonFunc.getDataFromTestDataSheet("ResignEmployee"),
				commonFunc.getDataFromTestDataSheet("ResignReference"));
		resignRequestEmployeepost();
		commonFunc.navigateToFullAndFinalSettlement();
		pendingForFNFReverseProcess();
		commonFunc.navigateToResignationRequest();
		resignationRequestCancel();
		return flag;
	}

	/**
	 * This method is used To Check Resign/Termination Request Based on Employee Pay
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkResignTerminationRequestBasedOnEmployeePay() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToResignationRequest();
		resignRequestAddingEmployeeWithEmployeePay(commonFunc.getDataFromTestDataSheet("ResignDate"),
				commonFunc.getDataFromTestDataSheet("ResignEmployee"),
				commonFunc.getDataFromTestDataSheet("ResignReference"));
		resignRequestEmployeepost();
		commonFunc.navigateToFullAndFinalSettlement();
		pendingForFNFReverseProcess();
		commonFunc.navigateToResignationRequest();
		resignationRequestCancel();
		return flag;
	}

	/**
	 * To Check Resign/Termination Request Based on Return To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean checkResignTerminationRequestBasedOnReturnToApproval() {
		commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
				"HrRqtUsername");
		commonFunc.hardWait(3000);
		commonFunc.navigateToResignationRequest();
		resignRequestAddingEmployee(commonFunc.getDataFromTestDataSheet("ResignDate"),
				commonFunc.getDataFromTestDataSheet("ResignEmployee"),
				commonFunc.getDataFromTestDataSheet("ResignReference"));
		resignRequestEmployeeReverset();
		resignationRequestCancel();
		return flag;
	}

	/**
	 * This method is used To Add Resignation Request
	 * 
	 * @author Kishore-RMITS
	 * @param ResignDate
	 * @param ResignEmployee
	 * @param ResignReference
	 */
	public void resignRequestAddingEmployee(String ResignDate, String ResignEmployee, String ResignReference) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.ResigRequestFindAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.ResigRequestFindAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Resign Request Add Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtDocDate, ElementType.Xpath);
				if (flag) {
					webDB.clearText(AttendanceLeaveLocators.ResignRqtDocDate, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.ResignRqtDocDate, ResignDate,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Resign Request Doc Date is Entered");
						webDB.pressTabKey();
						webDB.pressTabKey();
						flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtEmployeeLOV, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.ResignRqtEmployeeLOV,
									ResignEmployee);
							if (flag) {
								logs.logging("INFO", "Resign Request Employee is Selected");
								webDB.pressTabKey();
								flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtReferenceField,
										ElementType.Xpath);
								if (flag) {
									flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.ResignRqtReferenceField,
											ResignReference, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Resign Request Reference is Entered");
										flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtEntrySaveButton,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(AttendanceLeaveLocators.ResignRqtEntrySaveButton,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Resign Request Document is Saved");
												commonFunc.checkSuccessToastMessageDisappear();
												commonFunc.hardWait(4000);
												webDB.waitForElement(AttendanceLeaveLocators.ResignRqtDocNoField,
														ElementType.Xpath);
												String ResignationRequestDocNo = webDB.getDomPropertyFromElement(
														AttendanceLeaveLocators.ResignRqtDocNoField, ElementType.Xpath,
														commonFunc.getDataFromTestDataSheet("Value"));
												VariableHandler.setResignationRequestDocNo(ResignationRequestDocNo);
												logs.logging("info", "Resignation Requst No is:"
														+ VariableHandler.getResignationRequestDocNo());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Post the Resign Request Employee
	 * 
	 * @author Kishore-RMITS
	 */
	public void resignRequestEmployeepost() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtEntryPostButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(4000);
			webDB.clickAnElement(AttendanceLeaveLocators.ResignRqtEntryPostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Resign Request Document is Posted");
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getResignationRequestDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				flag = webDB.waitForElement(AttendanceLeaveLocators.ResigRequestFindAddButton, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Reverse The Employee Resignation Request
	 * 
	 * @author Kishore-RMITS
	 */
	public void pendingForFNFReverseProcess() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.FFSFindPendingForFNF, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.FFSFindPendingForFNF, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PendingForFNF Is Clicked");
				webDB.switchToFrame(AttendanceLeaveLocators.PendingForFNFFrame, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.FFSFindPendingForFNFReverseButton,
						ElementType.CssSelector);
				commonFunc.hardWait(2000);
				webDB.clickAnElement(AttendanceLeaveLocators.FFSFindPendingForFNFReverseButton,
						ElementType.CssSelector);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(AttendanceLeaveLocators.FFSFindPendingForFNFReverseOk, ElementType.Xpath);
				commonFunc.hardWait(2000);
				webDB.clickAnElement(AttendanceLeaveLocators.FFSFindPendingForFNFReverseOk, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.FFSFindPendingForFNFClose, ElementType.Xpath);
				commonFunc.hardWait(4000);
				webDB.clickAnElement(AttendanceLeaveLocators.FFSFindPendingForFNFClose, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Cancel The Resignation Request Document
	 * 
	 * @author Kishore-RMITS
	 */
	public void resignationRequestCancel() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRequestFindSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.ResignRequestFindSearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Find Search Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRequestFindDocSelect,
						ElementType.CssSelector);
				if (flag) {
					commonFunc.hardWait(2000);
					webDB.clickAnElement(AttendanceLeaveLocators.ResignRequestFindDocSelect, ElementType.CssSelector);
					if (flag) {
						logs.logging("INFO", "Resignation Request Document Is Selected");
						flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRequestDocCancel, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(2000);
							webDB.clickAnElement(AttendanceLeaveLocators.ResignRequestDocCancel, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Resignation Request Document Is Cancelled");
								flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRequestFindSearchButton,
										ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Add Resignation Request Adjust Encash Leave
	 * 
	 * @author Kishore-RMITS
	 * @param ResignDate
	 * @param ResignEmployee
	 * @param ResignReference
	 */
	public void resignRequestAddingEmployeewithAdjustEncashLeave(String ResignDate, String ResignEmployee,
			String ResignReference) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.ResigRequestFindAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.ResigRequestFindAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Resign Request Add Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtDocDate, ElementType.Xpath);
				if (flag) {
					webDB.clearText(AttendanceLeaveLocators.ResignRqtDocDate, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.ResignRqtDocDate, ResignDate,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Resign Request Doc Date is Entered");
						webDB.pressTabKey();
						webDB.pressTabKey();
						flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtEmployeeLOV, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.ResignRqtEmployeeLOV,
									ResignEmployee);
							if (flag) {
								logs.logging("INFO", "Resign Request Employee is Selected");
								webDB.pressTabKey();
								flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtReferenceField,
										ElementType.Xpath);
								if (flag) {
									flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.ResignRqtReferenceField,
											ResignReference, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Resign Request Reference is Entered");
										webDB.pressTabKey();
										flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtAdjustEncashLeave,
												ElementType.Xpath);
										commonFunc.hardWait(2000);
										if (flag) {
											webDB.clickAnElement(AttendanceLeaveLocators.ResignRqtAdjustEncashLeave,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Adjust Encash Leave Flag is Checked");
												flag = webDB.waitForElement(
														AttendanceLeaveLocators.ResignRqtEntrySaveButton,
														ElementType.Xpath);
												if (flag) {
													webDB.clickAnElement(
															AttendanceLeaveLocators.ResignRqtEntrySaveButton,
															ElementType.Xpath);
													if (flag) {
														logs.logging("INFO", "Resign Request Document is Saved");
														commonFunc.checkSuccessToastMessageDisappear();
														commonFunc.hardWait(4000);
														webDB.waitForElement(
																AttendanceLeaveLocators.ResignRqtDocNoField,
																ElementType.Xpath);
														String ResignationRequestDocNo = webDB
																.getDomPropertyFromElement(
																		AttendanceLeaveLocators.ResignRqtDocNoField,
																		ElementType.Xpath,
																		commonFunc.getDataFromTestDataSheet("Value"));
														VariableHandler
																.setResignationRequestDocNo(ResignationRequestDocNo);
														logs.logging("info", "Resignation Requst No is:"
																+ VariableHandler.getResignationRequestDocNo());
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Add Resignation Request
	 * 
	 * @author Kishore-RMITS
	 * @param ResignDate
	 * @param ResignEmployee
	 * @param ResignReference
	 */
	public void resignRequestAddingEmployeeWithEmployeePay(String ResignDate, String ResignEmployee,
			String ResignReference) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.ResigRequestFindAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.ResigRequestFindAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Resign Request Add Button Is Clicked");
				flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtDocDate, ElementType.Xpath);
				if (flag) {
					webDB.clearText(AttendanceLeaveLocators.ResignRqtDocDate, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.ResignRqtDocDate, ResignDate,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Resign Request Doc Date is Entered");
						webDB.pressTabKey();
						webDB.pressTabKey();
						flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtEmployeeLOV, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.ResignRqtEmployeeLOV,
									ResignEmployee);
							if (flag) {
								logs.logging("INFO", "Resign Request Employee is Selected");
								webDB.pressTabKey();
								flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtReferenceField,
										ElementType.Xpath);
								if (flag) {
									flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.ResignRqtReferenceField,
											ResignReference, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Resign Request Reference is Entered");
										webDB.pressTabKey();
										flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtEmployeePay,
												ElementType.Xpath);
										commonFunc.hardWait(2000);
										if (flag) {
											webDB.clickAnElement(AttendanceLeaveLocators.ResignRqtEmployeePay,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Adjust Encash Leave Flag is Checked");
												flag = webDB.waitForElement(
														AttendanceLeaveLocators.ResignRqtEntrySaveButton,
														ElementType.Xpath);
												if (flag) {
													webDB.clickAnElement(
															AttendanceLeaveLocators.ResignRqtEntrySaveButton,
															ElementType.Xpath);
													if (flag) {
														logs.logging("INFO", "Resign Request Document is Saved");
														commonFunc.checkSuccessToastMessageDisappear();
														commonFunc.hardWait(4000);
														webDB.waitForElement(
																AttendanceLeaveLocators.ResignRqtDocNoField,
																ElementType.Xpath);
														String ResignationRequestDocNo = webDB
																.getDomPropertyFromElement(
																		AttendanceLeaveLocators.ResignRqtDocNoField,
																		ElementType.Xpath,
																		commonFunc.getDataFromTestDataSheet("Value"));
														VariableHandler
																.setResignationRequestDocNo(ResignationRequestDocNo);
														logs.logging("info", "Resignation Requst No is:"
																+ VariableHandler.getResignationRequestDocNo());
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used To Post the Resign Request Employee
	 * 
	 * @author Kishore-RMITS
	 */
	public void resignRequestEmployeeReverset() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.ResignRqtEntryPostButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(4000);
			webDB.clickAnElement(AttendanceLeaveLocators.ResignRqtEntryPostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Resign Request Document is Posted");
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getResignationRequestDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"),
						webDB.getDataFromProperties("HRUSEREmpId"));
				commonFunc.returnDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getResignationRequestDocNo(), webDB.getDataFromProperties("HRUSEREmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HrRqtUsername"),
						webDB.getDataFromProperties("HRUSEREmpId"));
				flag = webDB.waitForElement(AttendanceLeaveLocators.ResigRequestFindAddButton, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}
}
