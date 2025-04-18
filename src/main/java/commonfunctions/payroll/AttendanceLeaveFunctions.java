package commonfunctions.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.payroll.AttendanceLeaveLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class AttendanceLeaveFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

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

	/**
	 * This method is used to To Check Permission based on Employee Data
	 * 
	 * @author Kishore-RMITS
	 */
	public boolean verifyCheckpermissionEntryWithEmployeeData() {
		try {
			commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
					"HRMRequestUser");
			commonFunc.navigateLeaveEncashmentScreen();
			addPermissionEntry();
			permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
			permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
					commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
					commonFunc.getDataFromTestDataSheet("PermissionReference"));
			permissionSaveAndForwardDocument();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"));
			approvalFunc.navigateToApprovals();
			filterDocumentsforPermissionApproval(VariableHandler.getPermissionDocNo());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPermissionDocNo());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
					"HRMRequestUser");
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
				commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"),
						System.getProperty("HRMRequestPassword"), "HRMRequestUser");
				commonFunc.navigateLeaveEncashmentScreen();
				addPermissionEntry();
				permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
				checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
				permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
						commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
						commonFunc.getDataFromTestDataSheet("PermissionReference"));
				permissionSaveAndForwardDocument();
				verifyWaitingForApproval();
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"));
				approvalFunc.navigateToApprovals();
				filterDocumentsforPermissionApproval(VariableHandler.getPermissionDocNo());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPermissionDocNo());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"),
						System.getProperty("HRMRequestPassword"), "HRMRequestUser");
				commonFunc.navigateLeaveEncashmentScreen();
				PermissionForwardApprovalReverse(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
						commonFunc.getDataFromTestDataSheet("PermissionFindType"));
			}
			commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
					"HRMRequestUser");
			commonFunc.navigateLeaveEncashmentScreen();
			addPermissionEntry();
			permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			permissionSelctType(commonFunc.getDataFromTestDataSheet("PermissionType"));
			checkOnDutyBasedonDateWise(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
			permissionReference(commonFunc.getDataFromTestDataSheet("PermissionReference"));
			permissionSaveAndForwardDocument();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"));
			approvalFunc.navigateToApprovals();
			filterDocumentsforPermissionApproval(VariableHandler.getPermissionDocNo());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPermissionDocNo());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
					"HRMRequestUser");
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
		webDB.switchToDefaultContent();
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
					flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocument, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(2000);
						webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocument, ElementType.Xpath);
						flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionReverse, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Permission Document is Clicked");
							commonFunc.hardWait(3000);
							webDB.clickAnElement(AttendanceLeaveLocators.PermissionReverse, ElementType.Xpath);
							flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionReverseOk, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Permission Document is Reversed");
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
	 * This method is used to Check Permission entry document to forward to approval
	 * user
	 * 
	 * @author Kishore-RMITS
	 * @return flag
	 */
	public boolean checkPermissionOnDutybasedonFowardtoApprovalReord() {
		commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
				"HRMRequestUser");
		commonFunc.navigateLeaveEncashmentScreen();
		addPermissionEntry();
		permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
		permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
				commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
				commonFunc.getDataFromTestDataSheet("PermissionReference"));
		permissionSaveAndForwardDocument();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"));
			approvalFunc.navigateToApprovals();
			filterDocumentsforPermissionApproval(VariableHandler.getPermissionDocNo());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPermissionDocNo());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
					"HRMRequestUser");
			commonFunc.navigateLeaveEncashmentScreen();
			PermissionForwardApprovalReverse(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
					commonFunc.getDataFromTestDataSheet("PermissionFindType"));
		}
		return flag;
	}

	/**
	 * This method is used to Return the Permission Document from Approval to
	 * Request User return process.
	 * 
	 * @author Kishore-RMITS
	 * @return flag
	 */
	public boolean checkPermissionOnDutyBasedOnReturnToRequestRecord() {
		commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
				"HRMRequestUser");
		commonFunc.navigateLeaveEncashmentScreen();
		addPermissionEntry();
		permissionEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		checkPermissionBasedonFromDate(commonFunc.getDataFromTestDataSheet("PermFromDate1"));
		permissionTimeFromEntry(commonFunc.getDataFromTestDataSheet("PermissionTimeFrom"),
				commonFunc.getDataFromTestDataSheet("PermissionTimeTo"),
				commonFunc.getDataFromTestDataSheet("PermissionReference"));
		permissionSaveAndForwardDocument();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"));
			approvalFunc.navigateToApprovals();
			filterDocumentsforPermissionApproval(VariableHandler.getPermissionDocNo());
			verifyReturnWithDocNumber(VariableHandler.getPermissionDocNo());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"),
						System.getProperty("HRMRequestPassword"));
				approvalFunc.navigateToApprovals();
				filterDocumentsforPermissionApproval(VariableHandler.getPermissionDocNo());
				verifyReturnWithDocNumber(VariableHandler.getPermissionDocNo());
				commonFunc.navigateLeaveEncashmentScreen();
				PermissionDocumentCancel(commonFunc.getDataFromTestDataSheet("EmployeeDurai"),
						commonFunc.getDataFromTestDataSheet("PermissionFindType"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
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
						flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionFindDocument, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							webDB.clickAnElement(AttendanceLeaveLocators.PermissionFindDocument, ElementType.Xpath);
							flag = webDB.waitForElement(AttendanceLeaveLocators.PermissionCancelButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Permission Document is Clicked");
								webDB.clickAnElement(AttendanceLeaveLocators.PermissionCancelButton, ElementType.Xpath);
								commonFunc.hardWait(5000);
								logs.logging("INFO", "Permission Document is Cancelled");
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Check Leave Encashment Based On RequestDate
	 * 
	 * @author Kishore-RMITS
	 * @return flag
	 */
	public boolean CheckLeaveEncashmentbasedonRequestDate() {
		{
			commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
					"HRMRequestUser");
			commonFunc.navigateLeaveEncashmentScreen();
			EncashmentEntry(commonFunc.getDataFromTestDataSheet("EncashmentRequestDate"));
			EncashmentEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
			EncashmentLeaveLOVEntry(commonFunc.getDataFromTestDataSheet("EncashmentLeaveLOV"));
			EncashmentDaysEntry(commonFunc.getDataFromTestDataSheet("EncashDaysField"),
					commonFunc.getDataFromTestDataSheet("EncashReasonField"));
			EncashmentForward();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"));
				approvalFunc.navigateToApprovals();
				filterDocumentsforPermissionApproval(VariableHandler.getEncashmentDocNo());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getEncashmentDocNo());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"),
						System.getProperty("HRMRequestPassword"), "HRMRequestUser");
				commonFunc.navigateLeaveEncashmentScreen();
				CheckLeaveEncashmentBasedOnPendingEncashment(VariableHandler.getEncashmentDocNo());
				CheckLeaveEncashmentBasedOnEncashInPayroll();
				CheckLeaveEncashmentReportFetchesValuesCorrectly(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This method is used to Forward The Encashment Document to Approval Person
	 * 
	 * @author Kishore-RMITS
	 * @return flag
	 */
	public boolean CheckLeaveEncashmentBasedOnForwardToApprovalRecord() {
		commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
				"HRMRequestUser");
		commonFunc.navigateLeaveEncashmentScreen();
		EncashmentEntry(commonFunc.getDataFromTestDataSheet("EncashmentRequestDate"));
		EncashmentEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EncashmentLeaveLOVEntry(commonFunc.getDataFromTestDataSheet("EncashmentLeaveLOV"));
		EncashmentDaysEntry(commonFunc.getDataFromTestDataSheet("EncashDaysField"),
				commonFunc.getDataFromTestDataSheet("EncashReasonField"));
		EncashmentForward();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"));
			approvalFunc.navigateToApprovals();
			filterDocumentsforPermissionApproval(VariableHandler.getEncashmentDocNo());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getEncashmentDocNo());
			flag = commonFunc.logoutUser();
		}
		return flag;
	}

	/**
	 * This method is used to Return The Encashment Document to the Request User
	 * 
	 * @author Kishore-RMITS
	 * @return flag
	 */
	public boolean CheckLeaveEncashmentBasedOnReturnToRequestRecord() {
		commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
				"HRMRequestUser");
		commonFunc.navigateLeaveEncashmentScreen();
		EncashmentEntry(commonFunc.getDataFromTestDataSheet("EncashmentRequestDate"));
		EncashmentEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		EncashmentLeaveLOVEntry(commonFunc.getDataFromTestDataSheet("EncashmentLeaveLOV"));
		EncashmentDaysEntry(commonFunc.getDataFromTestDataSheet("EncashDaysField"),
				commonFunc.getDataFromTestDataSheet("EncashReasonField"));
		EncashmentForward();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"));
			approvalFunc.navigateToApprovals();
			filterDocumentsforPermissionApproval(VariableHandler.getEncashmentDocNo());
			verifyReturnWithDocNumber(VariableHandler.getEncashmentDocNo());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"),
						System.getProperty("HRMRequestPassword"));
				approvalFunc.navigateToApprovals();
				filterDocumentsforPermissionApproval(VariableHandler.getEncashmentDocNo());
				verifyReturnWithDocNumber(VariableHandler.getEncashmentDocNo());
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
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
					webDB.clickAnElement(AttendanceLeaveLocators.EncashmentViewAmountClose, ElementType.Xpath);
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
	 * This method is used to Select Encashment Based on Payroll
	 * 
	 * @author Kishore-RMITS
	 */
	public void CheckLeaveEncashmentBasedOnEncashInPayroll() {
		flag = webDB.waitForElement(AttendanceLeaveLocators.CreateEncashmentOkButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			webDB.clickAnElement(AttendanceLeaveLocators.CreateEncashmentOkButton, ElementType.Xpath);
			commonFunc.hardWait(5000);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentFindPageEmployeeLOV, ElementType.Xpath);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Check Leave Encashment Report Fetches Values Correctly
	 * 
	 * @param EmployeeDurai
	 * @author Kishore-RMITS
	 */
	public void CheckLeaveEncashmentReportFetchesValuesCorrectly(String EmployeeDurai) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.EncashmentFindPageEmployeeLOV, EmployeeDurai);
		if (flag) {
			webDB.clickAnElement(AttendanceLeaveLocators.EncashmentFindPageSearchButton, ElementType.Xpath);
			webDB.waitForElement(AttendanceLeaveLocators.EncashmentFindPageEmployeeLOV, ElementType.Xpath);
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
	 * This method is used To Create Pending Encashment Leave
	 * 
	 * @author Kishore-RMITS
	 */
	public void CheckLeaveEncashmentBasedOnPendingEncashment(String Number) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.EncashmentButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Encashment Button is Clicked");
			webDB.clickAnElement(AttendanceLeaveLocators.EncashmentButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(AttendanceLeaveLocators.PendingEncashmentButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(AttendanceLeaveLocators.PendingEncashmentButton, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.PendingEncashmentscreen, ElementType.Xpath);
				webDB.switchToFrame(AttendanceLeaveLocators.PendingEncashmentscreen, ElementType.Xpath);
				flag = webDB.waitForElement(AttendanceLeaveLocators.PendingEncashmentSelectFlagIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(AttendanceLeaveLocators.PendingEncashmentSelectFlagIcon, ElementType.Xpath);
					logs.logging("INFO", "Pending Encashment Doc No Flag Is Checked");
					flag = webDB.waitForElement(AttendanceLeaveLocators.CreateEncashmentLeave, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(AttendanceLeaveLocators.CreateEncashmentLeave, ElementType.Xpath);
						logs.logging("INFO", "Encashment Leave Created");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used To Check Comp-Off Entry based on Leave Types
	 *
	 * @author Kishore-RMITS
	 * @return flag
	 */
	public boolean CheckCompOffEntryBasedOnLeaveTypes() {
		commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
				"HRMRequestUser");
		commonFunc.navigateLeaveEncashmentScreen();
		CompOffEntry();
		compOffEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		checkCompOffBasedonDatewise(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("CompOffDays"),
				commonFunc.getDataFromTestDataSheet("CompOffReferenceField"));
		CompOffEntrySaveAndPostDocument();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"));
			approvalFunc.navigateToApprovals();
			filterDocumentsforPermissionApproval(VariableHandler.getCompOffDocNo());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getCompOffDocNo());
			flag = commonFunc.logoutUser();
		}
		return flag;
	}

	/**
	 * This method is used to Return The CompOff Document to the Request User Return
	 * Document
	 * 
	 * @author Kishore-RMITS
	 * @return flag
	 */
	public boolean CheckCompOffBasedOnReturnToApprovalRecord() {
		commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"), System.getProperty("HRMRequestPassword"),
				"HRMRequestUser");
		commonFunc.navigateLeaveEncashmentScreen();
		CompOffEntry();
		compOffEmployeeLOVEntry(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
		checkCompOffBasedonDatewise(commonFunc.getDataFromTestDataSheet("PermFromDate1"),
				commonFunc.getDataFromTestDataSheet("CompOffDays"),
				commonFunc.getDataFromTestDataSheet("CompOffReferenceField"));
		CompOffEntrySaveAndPostDocument();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"));
			approvalFunc.navigateToApprovals();
			filterDocumentsforPermissionApproval(VariableHandler.getCompOffDocNo());
			verifyReturnWithDocNumber(VariableHandler.getCompOffDocNo());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HRMRequestUser"),
						System.getProperty("HRMRequestPassword"));
				approvalFunc.navigateToApprovals();
				filterDocumentsforPermissionApproval(VariableHandler.getCompOffDocNo());
				verifyReturnWithDocNumber(VariableHandler.getCompOffDocNo());
				CheckCompOffFindPageWithAllLineWiseRecord(commonFunc.getDataFromTestDataSheet("EmployeeDurai"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
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
	 * This method is used Select CompOff Employee LOVEntry
	 * 
	 * @param Employee
	 * @author Kishore-RMITS
	 */
	public void compOffEmployeeLOVEntry(String EmployeeDurai) {
		commonFunc.searchAndSelectValueInFilters(AttendanceLeaveLocators.CompOffEmployeeLOV, EmployeeDurai);
	}

	/**
	 * This method is used to Check the CompOff Date wise
	 * 
	 * @param PermFromDate2
	 * @param CompOffDays
	 * @param CompOffReferenceField
	 * @author Kishore-RMITS
	 */
	public void checkCompOffBasedonDatewise(String PermFromDate1, String CompOffDays, String CompOffReferenceField) {
		flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffDateField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(AttendanceLeaveLocators.CompOffDateField, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(AttendanceLeaveLocators.CompOffDateField, PermFromDate1,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Permission From Date is Entered");
				flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffDaysField, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(1000);
					webDB.clearTextField(AttendanceLeaveLocators.CompOffDaysField, ElementType.Xpath);
					webDB.sendTextToAnElement(AttendanceLeaveLocators.CompOffDaysField, CompOffDays, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Encashment Days" + CompOffDays + " is Entered");
						flag = webDB.waitForElement(AttendanceLeaveLocators.CompOffReferenceField, ElementType.Xpath);
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
	 * This method is used to Find the Line Wise Record
	 * 
	 * @author Kishore-RMITS
	 * @param EmployeeDurai
	 * @return flag
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
}
