package commonfunctions.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.payroll.AttendanceLeaveLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class AttendanceLeaveFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private CommonFunctions commonFunc = new CommonFunctions();

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
				webDB.clickAnElement(AttendanceLeaveLocators.EmpoloyeePermissionAddButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionFromDateField, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Permission Add Button Is Clicked");
				}
			}
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
					flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionReferenceField,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionReferenceField,
								ElementType.Xpath);
						flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.EmployeePermissionReferenceField,
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
					webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionFindButon, ElementType.Xpath);
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
		flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionReferenceField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionReferenceField, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.EmployeePermissionReferenceField,
					PermissionReference, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "On Duty Reference is entered");
			}
		}
		Assert.assertTrue(flag);
	}

	public boolean verifyCheckpermissionEntryWithEmployeeData() {
		try {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
			commonFunc.navigateLeaveEncashmentScreen();
			addPermissionEntry();
			permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
			permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
					commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
					commonFunc.getDataFromTestDataSheet("PermissionReference"));
			permissionSaveAndPostDocument();
			checkPermissionreportfetchesvaluescorrectly(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
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
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateLeaveEncashmentScreen();
				addPermissionEntry();
				permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
				checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
				permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
						commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
						commonFunc.getDataFromTestDataSheet("PermissionReference"));
				permissionSaveAndPostDocument();
				checkPermissionreportfetchesvaluescorrectly(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
						commonFunc.getDataFromTestDataSheet("PermissionFindType"));
			}
			permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			permissionSelctType(commonFunc.getDataFromTestDataSheet("PermissionType"));
			checkOnDutyBasedonDateWise(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
			permissionReference(commonFunc.getDataFromTestDataSheet("PermissionReference"));
			permissionSaveAndPostDocument();
			checkOnDutyreportfetchesvaluescorrectly(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
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
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
				ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
					PermissionFindType, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Permission Type is" + PermissionFindType);
				webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindPageSearchButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocument, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocument, ElementType.Xpath);
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
								webDB.clickAnElement(AttendanceLeaveLocators.EmployeePermissionFindButon,
										ElementType.Xpath);
								flag = webDB.waitForElement(AttendanceLeaveLocators.EmpoloyeePermissionAddButton,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "On-Duty Entry Add Button Clicked");
									webDB.clickAnElement(AttendanceLeaveLocators.EmpoloyeePermissionAddButton,
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
	 * This method is used to select Employee in find page LOV and all datas and
	 * verify reports
	 * 
	 * @param Employee
	 * @param OnDutyFindType
	 * @author Kishore-RMITS
	 */
	public void checkOnDutyreportfetchesvaluescorrectly(String EmployeeDurai, String OnDutyFindType) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.PermissionFindPageEmployeeLOV, EmployeeDurai);
		flag = webDB.waitForClickElement(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
				ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.selectDropDownByText(AttendanceLeaveLocators.EmployeeFindScreenPermissionTypeSelectField,
					OnDutyFindType, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Type selected is" + OnDutyFindType);
				webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindPageSearchButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocument, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocument, ElementType.Xpath);
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

}
