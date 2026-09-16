package commonlocators.payroll;

import commonfunctions.CommonFunctions;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
public class AttendanceLeaveLocators {
	/** @locatorType Xpath */
	public static final String EmployeePermissionButton = "(//input[contains(@name,'TAB')]/following-sibling::label)[3]";
	/** @locatorType Xpath */
	public static final String EmpoloyeeFindPermissionAddButton = "//button[@data-testid='PER_Add_Button']";
	/** @locatorType Xpath */
	public static final String PermissionEmployeeLOV = "//button[@data-testid='EMP_lov']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionFromDateField = "//input[@data-testid='EMPPER_PRMN_DATE']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionTimeFromField = "//input[@data-testid='PER_TIME_FRM']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionTimeToField = "//input[@data-testid='PER_TIME_TO']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionReasonField = "//input[@data-testid='PER_RESN']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionSaveButton = "//button[@data-testid='PER_SAVE']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionPostButon = "//button[@data-testid='PER_POST']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionEntryFindButton = "//button[@data-testid='EMPPER_PRMN_search']";
	/** @locatorType Xpath */
	public static final String PermissionFindPageSearchButton = "//button[@data-testid='PER_SEARCH']";
	/** @locatorType Xpath */
	public static final String PermissionFindPageEmployeeLOV = "//button[@data-testid='EMP_PER_find_lov']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionTypeSelectField = "//Select[@data-testid='PER_TYPE']";
	/** @locatorType Xpath */
	public static final String EmployeeFindScreenPermissionTypeSelectField = "//select[@data-testid='PER_FIND_TYP']";
	/** @locatorType Xpath */
	public static final String PermissionDocumentNo = "//input[@data-testid='PERM_DOC']";
	/** @locatorType Xpath */
	public static final String PermissionFindTable = "(//div[@id='perm_detail_data_panel']//table)[2]//tr//th";

	/** @locatorType Xpath */
	public static final String PermissionFindDocumentNoClick() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(PermissionFindTable, "Doc. No.") + ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String PermissionReverse = "//button[@data-testid='PER_REVER']";
	/** @locatorType Xpath */
	public static final String PermissionReverseOk = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String PermissionCancelButton = "//button[@data-testid='PER_CANCEL']";
	/** @locatorType Xpath */
	public static final String EncashmentButton = "(//input[contains(@name,'TAB')]/following-sibling::label)[2]";
	/** @locatorType Xpath */
	public static final String EncashmentAddButon = "//button[@data-testid='ENCAH_Add_Button']";
	/** @locatorType Xpath */
	public static final String EncashmentRequestDateField = "//input[@data-testid='LR_REQ_DATE']";
	/** @locatorType Xpath */
	public static final String EncashmentEmployeeLOV = "//button[@data-testid='LR_EMP_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String EncashmentLeaveLOV = "//button[@data-testid='LR_LEAVE_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String EncashDaysField = "//input[@data-testid='LR_APPLIED_DAYS_1']";
	/** @locatorType Xpath */
	public static final String EncashmentReasonField = "//input[@data-testid='LR_BRIEFDESC_1']";
	/** @locatorType Xpath */
	public static final String EncashmentViewAmount = "//button[@data-testid='ENCAH_VIW_AMT']";
	/** @locatorType Xpath */
	public static final String EncashmentSaveButton = "//button[@data-testid='ENCAH_SAV_BTN']";
	/** @locatorType Xpath */
	public static final String EncashmentDocNumber = "//input[@data-testid='P41_LR_REQ_NO']";
	/** @locatorType Xpath */
	public static final String EncashmentFindResultTable = "(//table[contains(@data-testid,'encash_detail_Result')])[2]//tr/th";

	/** @locatorType Xpath */
	public static final String EncashmentFindDocSelect() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(EncashmentFindResultTable, "Req. No.")
				+ ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String EncashmentPostButton = "//button[@data-testid='ENCAH_POST_BTN']";
	/** @locatorType Xpath */
	public static final String EncashmentFindIcon = "//*[@id='B171495937485719290']";
	/** @locatorType Xpath */
	public static final String EncashmentFindSearchButon = "//button[@data-testid='ENC_SER_BTN']";
	/** @locatorType Xpath */
	public static final String EncashmentFindPageEmployeeLOV = "//button[@data-testid='EMP_ENC_find_EMP_lov']";
	/** @locatorType Xpath */
	public static final String PendingEncashmentButton = "//button[@data-testid='PEN_ENCAS_BTN']";
	/** @locatorType Xpath */
	public static final String PendingEncashmentscreen = "//iframe[@title='Pending Encashment']";
	/** @locatorType Xpath */
	public static final String PendingEncashmentSelectFlagIcon = "(//input[@type='checkbox'])[2]";
	/** @locatorType Xpath */
	public static final String CreateEncashmentLeave = "//button[@data-testid='CRE_ENC_BTN']";
	/** @locatorType Xpath */
	public static final String CreateEncashmentOkButton = "//button[@data-testid='ENC_PAY_OK_BTN']";
	/** @locatorType Xpath */
	public static final String EncashmentCancelButton = "//button[@data-testid='LEV_Cancel_Button']";
	/** @locatorType Xpath */
	public static final String CompOffButtonClick = "//*[@id='P818510608_TAB']/div/div/div[4]/label";
	/** @locatorType Xpath */
	public static final String CompOffFindAddIcon = "//button[@data-testid='COMP_Add_Button']";
	/** @locatorType Xpath */
	public static final String CompOffEmployeeLOV = "//button[@data-testid='COM_EMP_ID_lov']";
	/** @locatorType Xpath */
	public static final String CompOffDateFieldToday = "//button[@class='a-Button a-DatePicker--today']";
	/** @locatorType Xpath */
	public static final String CompOffDateField = "//input[@data-testid='COM_OFF_DATE']";
	/** @locatorType Xpath */
	public static final String CompOffDaysField = "//input[@data-testid='COM_OFF_DAYS']";
	/** @locatorType Xpath */
	public static final String CompOffReferenceField = "//*[@id='P8185106003_HECO_REFERENCE']";
	/** @locatorType Xpath */
	public static final String CompOffEntrySaveIcon = "//button[@data-testid='COM_OFF_SAVE']";
	/** @locatorType Xpath */
	public static final String CompOffEntryPostIcon = "//button[@data-testid='COM_OFF_POST']";
	/** @locatorType Xpath */
	public static final String CompOffDocumentNo = "//input[@data-testid='COM_OFF_DOC_NO']";
	/** @locatorType Xpath */
	public static final String CompOffFindEmployeeLOV = "//*[@id='P818510608_EMPLOYEE_COM_lov_btn']/span";
	/** @locatorType Xpath */
	public static final String CompOffFindpageSearchButton = "//button[@data-testid='COM_OFF_SEARCH']";
	/** @locatorType Xpath */
	public static final String CompOffFindpageClearButton = "//button[@data-testid='COM_OFF_CLEAR']";
	/** @locatorType Xpath */
	public static final String LeaveRadioBtnLeaveRequest = "//input[@aria-label='Leave']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String LeaveAddButton = "//*[@data-testid='LEV_Add_Button']";
	/** @locatorType Xpath */
	public static final String LeaveRequestDateInput = "//input[@data-testid='LR_REQ_DATE']";
	/** @locatorType Xpath */
	public static final String LeaveRequestEmpIdLovBtn = "//*[@data-testid='ENCAH_EMP_FILED']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LeaveRequestLeaveTypeLovBtn = "//input[@data-testid='ENCAH_LEV']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LeaveRequestBalanceDisplay = "//span[@data-testid='LR_LEAVE_BAL']";
	/** @locatorType Xpath */
	public static final String LeaveRequestStartDateInput = "//*[@data-testid='LR_LEAVE_ST_DATE']/input";
	/** @locatorType Xpath */
	public static final String LeaveRequestStartSessionSelect = "//select[@data-testid='LR_LEAVE_ST_SESS']";
	/** @locatorType Xpath */
	public static final String LeaveRequestAppliedDaysInput = "//input[@data-testid='LR_LEAVE_APP_DAYS']";
	/** @locatorType Xpath */
	public static final String LeaveRequestEndDateInput = "//input[@data-testid='LR_LEAVE_END_DATE']";
	/** @locatorType Xpath */
	public static final String LeaveRequestEndSessionSelect = "//select[@data-testid='LR_LEAVE_END_SESS']";
	/** @locatorType Xpath */
	public static final String LeaveRequestLeaveReason = "//input[@data-testid='LR_LEAVE_BRIEF']";
	/** @locatorType Xpath */
	public static final String LeaveRequestHeaderSaveIcon = "//button[@data-testid='LR_LEAVE_SAVE']";
	/** @locatorType Xpath */
	public static final String LeaveRequestPostBtn = "//button[@data-testid='ENCAH_POST_BTN']";
	/** @locatorType Xpath */
	public static final String LeaveRequestNumberDisplay = "//input[@data-testid='P41_LR_REQ_NO']";
	/** @locatorType Xpath */
	public static final String LeaveEntryFetchBtn = "//button[@data-testid='LE_LEAVE_FETCH']";
	/** @locatorType Xpath */
	public static final String LeaveEntryPendingRequestIframe = "//iframe[@title='Pend. Leave Request']";
	/** @locatorType Xpath */
	public static final String LeaveEntryPendReqSearchBar = "//input[@id='user_search_field']";
	/** @locatorType Xpath */
	public static final String LeaveEntryPendReqSearchGoBtn = "//button[@id='user_search_button']";
	/** @locatorType Xpath */
	public static final String LeaveEntryPendReqCreateLeaveBtn = "//button[@data-testid='LE_CRE_LEAVE_BTN']";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindSearchBtn = "//button[@data-testid='LE_FIND_SEARCH_BTN']";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindSearchBar = "//input[@id='leave_detail_search_field']";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindSearchGoBtn = "//button[@id='leave_detail_search_button']";
	/** @locatorType Xpath */
	public static final String LeaveEntryHeaderSaveIcon = "//button[@data-testid='LE_SAVE_BTN']";
	/** @locatorType Xpath */
	public static final String LeaveEntryPostBtn = "//button[@data-testid='LE_POST_BTN']";
	/** @locatorType Xpath */
	public static final String LeaveEntryReverseBtn = "//button[@data-testid='LE_REV_BTN']";
	/** @locatorType Xpath */
	public static final String LeaveEntryReverseOkBtn = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String LeaveEntryCancelBtn = "//button[@data-testid='LE_CANCEL_Button']";
	/** @locatorType Xpath */
	public static final String LeaveEntryCancelOkBtn = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String LeaveEntryNumberDisplay = "//*[@data-testid='LE_DOC_NO_INPUT']";
	/** @locatorType Xpath */
	public static final String LeaveEntryDisplayDocNoAndStatus = "(//h2[contains(@id,'heading')]/b)[1]";
	/** @locatorType Xpath */
	public static final String LeaveRequestMigrationBtn = "//button[@data-testid='LR_MIG_BTN']";
	/** @locatorType Xpath */
	public static final String LeaveRequestMigrationIframe = "//iframe[@title='Leave Migration Type']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualLeaveRadioBtn = "//label[text()='Accrual Leave Upload']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualAddNewBtn = "//button[@data-testid='LR_ACCR_ADD_BTN']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualCalendarLovBtn = "//button[contains(@id,'ELAHD_CLNDR_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualYearLovBtn = "//button[contains(@id,'ELAHD_YEAR_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualPeriodLovBtn = "//button[contains(@id,'ELAHD_PERIOD_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualReferenceInput = "//input[contains(@id,'ELAHD_REF')]";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualCreateSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualUploadIcon = "//button[@title='Load']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualUploadInput = "//a-file-upload[contains(@id,'LOAD')]/input";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualUploadBtn = "//span[text()='Upload']/parent::button";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualCheckExcptionBtn = "//*[normalize-space()='Check Exception']/parent::button";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualHeaderSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccuralDisplayDocNo = "//input[contains(@id,'ELAHD_DOC_NO')]";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualPostBtn = "//*[normalize-space()='Post']/parent::button";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPRadioBtn = "//label[normalize-space()='LOP Leave Upload']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPAddNewBtn = "//div[@aria-label='LOP Leave Entry Migration']//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPCalendarLovBtn = "//button[contains(@id,'ELHD_CLNDR_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPYearLovBtn = "//button[contains(@id,'ELHD_YEAR_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPPeriodLovBtn = "//button[contains(@id,'ELHD_PERIOD_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPReferenceInput = "//input[contains(@id,'ELHD_REF')]";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPCreateSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPUploadIcon = "//button[@title='Load']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPUploadInput = "//a-file-upload[contains(@id,'LOAD')]/input";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPUploadBtn = "//span[text()='Upload']/parent::button";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPCheckExcptionBtn = "//*[normalize-space()='Check Exception']/parent::button";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPHeaderSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPDisplayDocNo = "//span[contains(@id,'ELHD_DOC_NO_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPPostBtn = "//*[normalize-space()='Post']/parent::button";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindStatusSelect = "//*[@data-testid='Status_EL_Input']";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindEmpIdLovBtn = "//*[@data-testid='Emp_ID_EL_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindDateFromInput = "//*[@data-testid='DATE_From_EL_Input']/input";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindLeaveCategorySelect = "//select[@data-testid='CAT_EL_Input']";
	/** @locatorType Xpath */
	public static final String LeaveEntryDocNoWithPostedStatus = "(//table[contains(@aria-label,'Result(s)')]/tbody/tr/td[13]/div[text()='Posted']/../../td[2])[1]";

	/** @locatorType Xpath */
	public static final String LeaveEntryPendReqCheckBox(String reqNo) {
		return "//input[contains(@id,'checkbox_" + reqNo + "')]";
	}

	/** @locatorType Xpath */
	public static final String LeaveEntrySearchDocNo(String DocNo) {
		return "//table[contains(@aria-label,'Result(s)')]/tbody/tr/td/a[text()='" + DocNo + "']";
	}

	/** @locatorType Xpath */
	public static final String LeaveEntrySearchFirstEmpName(String EmpName) {
		return "(//table[contains(@aria-label,'Result(s)')]/tbody/tr/td[text()='" + EmpName + "']/../td[2])[1]";
	}

	/** @locatorType Xpath */
	public static final String LeaveEntryAttachmentBtn = "//button[@data-testid='LE_ATT_Button']";
	/** @locatorType Xpath */
	public static final String DocumentManagementIframe = "//iframe[@title='Document Management View']";
	/** @locatorType Xpath */
	public static final String DocumentManagementAddButton = "//button[@title='ADD']";
	/** @locatorType Xpath */
	public static final String LeaveEntryAttachmentIframe = "//iframe[@title='Attachment']";
	/** @locatorType Xpath */
	public static final String AttachmentIframeCloseIcon = "//*[text()='Attachment']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String DocManagementIframeCloseIcon = "//*[text()='Document Management View']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LEAttachmentDocTypeLovBtn = "//input[contains(@id,'DOC_TYPE')]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LEAttachmentDocNoInput = "//input[@contains(@id,'DM_REQ_DOC_NO')]";
	/** @locatorType Xpath */
	public static final String LEAttachmentDocRevInput = "//input[contains(@id,'DM_REQ_DOC_REV')]";
	/** @locatorType Xpath */
	public static final String LEAttachmentDateFromInput = "//input[contains(@id,'DM_DUE_DATE_input')]";
	/** @locatorType Xpath */
	public static final String LEAttachmentDateToInput = "//input[contains(@id,'DM_RET_DATE_input')]";
	/** @locatorType Xpath */
	public static final String LEAttachmentChooseFileInput = "//input[contains(@id,'FILE_input')]";
	/** @locatorType Xpath */
	public static final String LEAttachmentUploadBtn = "//button[@title='Upload']";
	/** @locatorType Xpath */
	public static final String LEAttachmentDisplayedFileName = "//span[@class='a-FileDrop-heading']";
	/** @locatorType Xpath */
	public static final String LeaveEntryReportBtn = "//button[@data-testid='LE_PRINT_EN_Button']";
	/** @locatorType Xpath */
	public static final String LeaveAdjustmentLabel = "//input[@aria-label='Adjustment']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String AdjustmentAddIcon = "//button[@data-testid='ADD_ADJ_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentRequestDateInput = "//*[@data-testid='LE_REQ_INPUT']/input";
	/** @locatorType Xpath */
	public static final String AdjustmentEmpIdLovBtn = "//*[@data-testid='LE_EMP_INPUT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AdjustmentLeaveLovBtn = "//*[@data-testid='LE_LEAVE_ID_INPUT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AdjustmentLeavePrefixLovBtn = "//*[@data-testid='LE_PFX_INPUT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AdjustmentDaysInput = "//input[@data-testid='LE_APPR_INPUT']";
	/** @locatorType Xpath */
	public static final String AdjustmentReasonInput = "//input[@data-testid='LE_REASON_INPUT']";
	/** @locatorType Xpath */
	public static final String AdjustmentHeaderSaveIcon = "//button[@data-testid='LE_SAVE_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentPostBtn = "//button[@data-testid='LE_POST_BTN']";
	/** @locatorType Xpath */
	public static final String AdjustmentCancelBtn = "//button[@data-testid='LE_CANCEL_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentAttachmentBtn = "//button[@data-testid='LE_ATT_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentReportBtn = "//button[@data-testid='LE_PRINT_AD_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentDisplayDocNo = "//input[@data-testid='LE_DOC_NO_INPUT']";
	/** @locatorType Xpath */
	public static final String AdjustmentDisplayStatus = "(//h2[contains(@id,'heading')]/b)[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentReverseBtn = "//button[@data-testid='LE_ADJ_REV_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentReverseOkBtn = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String AdjustmentFindSearchBtn = "//button[@data-testid='GO_Rpt_ADJ_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentMigrationBtn = "//button[@data-testid='MIG_ADJ_Button']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationCreateAddBtn = "//button[@aria-label='Add ']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationCalendarLovBtn = "//button[@id='P367011501_ELHD_CLNDR_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationYearSelect = "//select[contains(@id,'ELHD_YEAR')]";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationPeriodSelect = "//select[contains(@id,'ELHD_PERIOD')]";

	/** @locatorType Xpath */
	public static final String AdjmntMigrationPeriodSelectOptions(String Period) {
		return "//select[contains(@id,'ELHD_PERIOD')]/option[contains(text(), '" + Period + "')]";
	}

	/** @locatorType Xpath */
	public static final String AdjmntMigrationModeSelect = "//select[contains(@id,'ELHD_MODE')]";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationDocNoDisplay = "//input[contains(@id,'ELHD_DOC_NO')]";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationReferenceInput = "//input[contains(@id,'ELHD_REF')]";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationHeaderSaveBtn = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationUploadIcon = "//button[@title='Upload']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationUploadIFrame = "//iframe[@title='Upload File']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationUploadInput = "//a-file-upload[contains(@id,'LOAD')]/input";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationUploadBtn = "//div[@aria-label='Upload File']//span[text()='Upload']/parent::button";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationCheckExceptionBtn = "//*[normalize-space()='Check Exception']/parent::button";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationPostBtn = "//*[normalize-space()='Post']/parent::button";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationReportBtn = "//button[@id='B700171373885104208']";
	/** @locatorType Xpath */
	public static final String FindLeaveLovBtn = "//*[@data-testid='LEV_EL_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindYearLovBtn = "//*[@data-testid='Year_EL_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindDesignationLovBtn = "//*[@data-testid='Desg_EL_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindDepartmentLovBtn = "//*[@data-testid='Dept_EL_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindUnitLovBtn = "//*[@data-testid='Unit_EL_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindLinesLeaveEntryScreen = "//table[contains(@aria-label,'Result(s)')]//tr/th";

	/** @locatorType Xpath */
	public static String FindLeaveResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Description");
	}

	/** @locatorType Xpath */
	public static String FindYearResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Year");
	}

	/** @locatorType Xpath */
	public static String FindDesignationResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Designation");
	}

	/** @locatorType Xpath */
	public static String FindDepartmentResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Department");
	}

	/** @locatorType Xpath */
	public static String FindUnitResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Unit");
	}

	/** @locatorType Xpath */
	public static final String Adj_FindSearchBar = "//input[@id='adjust_detail_search_field']";
	/** @locatorType Xpath */
	public static final String Adj_FindLeaveLovBtn = "//*[@data-testid='LEV_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Adj_FindYearLovBtn = "//*[@data-testid='Year_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Adj_FindDesignationLovBtn = "//*[@data-testid='DESG_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Adj_FindDepartmentLovBtn = "//*[@data-testid='DEPT_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Adj_FindUnitLovBtn = "//*[@data-testid='UNIT_ADJ_Input']/following-sibling::button";

	/** @locatorType Xpath */
	public static String Adj_FindLeaveResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Description");
	}

	/** @locatorType Xpath */
	public static String Adj_FindYearResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Year");
	}

	/** @locatorType Xpath */
	public static String Adj_FindDesignationResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Designation");
	}

	/** @locatorType Xpath */
	public static String Adj_FindDepartmentResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Department");
	}

	/** @locatorType Xpath */
	public static String Adj_FindUnitResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesLeaveEntryScreen, "Unit");
	}

	/** @locatorType Xpath */
	public static final String LoanRequestAddButton = "//button[@data-testid='LOAN_REQ_ADD_BUTTON']";
	/** @locatorType Xpath */
	public static final String LoanRequestEntryRequestDate = "//*[@id='P818121011_ELR_RQST_DATE_input']";
	/** @locatorType Xpath */
	public static final String LoanRequestEntryEmployeeLOV = "//button[@data-testid='ELR_EMP_find_lov']";
	/** @locatorType Xpath */
	public static final String LoanRequestEntryLoanLOV = "//button[@data-testid='ELR_LOAN_find_lov']";
	/** @locatorType Xpath */
	public static final String LoanRequestReferenceField = "//input[@data-testid='LOAN_REQ_REF']";
	/** @locatorType Xpath */
	public static final String LoanRequestRequiredDetailsDateField = "//*[contains(@id,'ELR_RQRD_DATE_input')]";
	/** @locatorType Xpath */
	public static final String LoanRequestAmountField = "//input[@data-testid='ELR_AMOUNT']";
	/** @locatorType Xpath */
	public static final String LoanRequestNoofInstallmentField = "//input[@data-testid='ELR_INST_NO']";
	/** @locatorType Xpath */
	public static final String LoanRequestSuretyEmployee1 = "//button[@data-testid='ELR_EMP1_find_LEV_lov']";
	/** @locatorType Xpath */
	public static final String LoanRequestSuretyEmployee2 = "//button[@data-testid='ELR_EMP2_find_EMP_lov']";
	/** @locatorType Xpath */
	public static final String LoanRequestSaveIcon = "//button[@data-testid='LOAN_REQ_CREATE_Button']";
	/** @locatorType Xpath */
	public static final String LoanRequestPostIcon = "//button[@data-testid='LOAN_REQ_ENTRY_Button']";
	/** @locatorType Xpath */
	public static final String LoanRequestDocNoField = "//input[@data-testid='LOAN_REQ_NO']";
	/** @locatorType Xpath */
	public static final String LoanRequestFindTable = "(//table[contains(@data-testid,'detail_Result')])[2]//tr/th";

	/** @locatorType Xpath */
	public static final String LoanRequestFindDocNoClick() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(LoanRequestFindTable, "Request No.")
				+ ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String LoanRequestFindSearch = "//button[@data-testid='LOAN_REQ_SEARCH_BUTTON']";
	/** @locatorType Xpath */
	public static final String LoanRequestDocCancel = "//button[@data-testid='LOAN_REQ_CANCEL_Button']";
	/** @locatorType Xpath */
	public static final String SetupLeaveLabel = "//label[text()='Leave']";
	/** @locatorType Xpath */
	public static final String SetupCreateLeaveEntryInputCheckBox = "//input[@id='P3670466_HLVC_PEND_FRM_LV_REQ']";
	/** @locatorType Xpath */
	public static final String SetupCreateLeaveEntryLabel = "//label[@id='P3670466_HLVC_PEND_FRM_LV_REQ_LABEL']";
	/** @locatorType Xpath */
	public static final String LeaveEntryAddIcon = "//button[@data-testid='Add_EL_Button']";

	/** @locatorType Xpath */
	public static final String LeaveEntryEmpIdLovBtn = "//*[@data-testid='LE_EMP_INPUT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LeaveEntryLeaveTypeLovBtn = "//input[@data-testid='LE_LEAVE_ID_INPUT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LeaveEntryStartDateInput = "//*[@data-testid='LE_ST_DATE_INPUT']/input";
	/** @locatorType Xpath */
	public static final String LeaveEntryStartSessionSelect = "//select[@data-testid='LE_ST_SESS_INPUT']";
	/** @locatorType Xpath */
	public static final String LeaveEntryAppliedDaysInput = "//input[@data-testid='LE_APP_DAYS_INPUT']";
	/** @locatorType Xpath */
	public static final String LeaveEntryEndDateInput = "//input[@data-testid='LE_END_DATE_INPUT']";
	/** @locatorType Xpath */
	public static final String LeaveEntryEndSessionSelect = "//select[@data-testid='LE_END_SESS_INPUT']";
	/** @locatorType Xpath */
	public static final String LeaveEntryLeaveReason = "//input[@data-testid='LE_BRIEF_INPUT']";
	/** @locatorType Xpath */
	public static final String LeaveEntryBalanceDisplay = "//span[@data-testid='LE_LEAVE_BAL_INPUT']";
	/** @locatorType Xpath */
	public static final String LeaveEntryDateInput = "//*[@data-testid='LE_REQ_INPUT']/input";
	/** @locatorType Xpath */
	public static final String LeaveRadioBtnLeaveEntry = "//input[@aria-label='Leave']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String LeaveEntryAddButton = "//*[@data-testid='Add_EL_Button']";

	/** @locatorType Xpath */
	public static final String LoanPaymentFindSearch = "//button[@data-testid='ELN_SEARCH_Button']";
	/** @locatorType Xpath */
	public static final String LoanPaymentFindSearchBar = "(//input[contains(@aria-label,'Search')])[1]";
	/** @locatorType Xpath */
	public static final String LoanPaymentFindTable = "(//table[contains(@data-testid,'loan_detail_Result')])[2]//tr/th";

	/** @locatorType Xpath */
	public static final String LoanPaymentDocNoClick() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(LoanPaymentFindTable, "Loan Rqst. No.")
				+ ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String LoanPaymentCreateLoanButton = "//button[@data-testid='ELP_CRE_LOAN_Button']";
	/** @locatorType Xpath */
	public static final String LoanCreateLoanPaymentFrame = "//iframe[@title='Create Loan Payment']";
	/** @locatorType Xpath */
	public static final String LoanCreateLoanPaymentpayrollOption = "//label[text()='Payroll']";
	/** @locatorType Xpath */
	public static final String LoanCreateLoanPaymentSaveOption = "//button[@title='Ok'][not(contains(@data-testid,'OK'))]";
	/** @locatorType Xpath */
	public static final String LoanPaymentReverseButton = "//button[@data-testid='ELP_REVERSE_Button']";

	public static final String TDSFindSearchButton = "//button[@data-testid='TDS_FindSearch_Btn']";

	public static final String TDSLabel = "//input[@aria-label='TDS Declaration']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String TDSDeclFindEmpLOV = "//button[@data-testid='EMP_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String TDSDeclFindStatusSelect = "//select[@data-testid='STATUS']";
	/** @locatorType Xpath */
	public static final String TDSFindTable = "(//table[contains(@data-testid,'detail_Result')])[2]//tr/th";

	/** @locatorType Xpath */
	public static final String TDSDocumentSelect() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(TDSFindTable, "View Declaration")
				+ ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String TDSConstructionCompletedSelect = "//*[@data-testid='HTDLD_HP_CONST_CO']";
	/** @locatorType Xpath */
	public static final String TDSFirstHomeLoanSelect = "//*[@data-testid='HTDLD_HP_FIRST_HL']";
	/** @locatorType Xpath */
	public static final String TDSDeclarationDocNoToPost = "//*[@data-testid='HTDH_DOC_NO']";
	/** @locatorType Xpath */
	public static final String TDSDeclarationReviseButton = "//button[@data-testid='Cre_rev_Btn']";
	/** @locatorType Xpath */
	public static final String TDSDeclarationPostButton = "//button[@data-testid='Post_Btn']";
	/** @locatorType Xpath */
	public static final String TDSDeclarationRegimeType = "//select[@data-testid='HTDH_TAX_TYPE']";
	/** @locatorType Xpath */
	public static final String TDSDeclarationSectionWiseField = "//input[@data-testid='HTDLD_SEC10_13A_H']";
	/** @locatorType Xpath */
	public static final String TDSDeclarationSaveButton = "//button[@data-testid='Save_Btn']";

	/** @locatorType Xpath */
	public static final String OvertimeFindSearchButton = "//button[@data-testid='prmn_goreport_btn']";
	/** @locatorType Xpath */
	public static final String OvertimeFindSearchAddButton = "//button[@data-testid='prmn_add_btn']";
	/** @locatorType Xpath */
	public static final String OvertimeFrameScreen = "//iframe[@title='Employee Overtime']";
	/** @locatorType Xpath */
	public static final String OvertimeDocCalSelectLOV = "//button[contains(@id,'CALANDER_lov_btn')]";
	/** @locatorType Xpath */
	public static final String OvertimeYearLOV = "//button[contains(@id,'YEAR_lov_btn')]";
	/** @locatorType Xpath */
	public static final String OvertimePeriodLOV = "//button[contains(@id,'PERIOD_lov_btn')]";
	/** @locatorType Xpath */
	public static final String OvertimeReferenceField = "//input[contains(@id,'REFERENCE')]";
	/** @locatorType Xpath */
	public static final String OvertimeDocSave = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String OvertimeLinesTable = "(//*[text()='Emp. ID']/ancestor::table)[2]";
	/** @locatorType Xpath */
	public static final String OvertimeDocNoField = "//span[contains(@id,'EOHD_DOC_NO_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String OvertimeEntryAddButton = "//button[@title='Download']/preceding-sibling::button[@title='Add']";

	/** @locatorType Xpath */
	public static final String OvertimeEntryEmpLOVField() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OvertimeLinesTable, "Emp. ID");
	}

	/** @locatorType Xpath */
	public static final String OvertimeEmpLOVButton() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OvertimeLinesTable, "Emp. ID") + "//button";
	}

	/** @locatorType Xpath */
	public static final String OvertimeDateInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OvertimeLinesTable, "Date") + "//input";
	}

	/** @locatorType Xpath */
	public static final String OvertimeDateField() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OvertimeLinesTable, "Date");
	}

	/** @locatorType Xpath */
	public static final String OvertimeHoursField() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OvertimeLinesTable, "Hrs.");
	}

	public static final String OvertimeHoursInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OvertimeLinesTable, "Hrs.") + "//input";
	}

	/** @locatorType Xpath */
	public static final String OvertimeLineSaveButton = "//button[@title='Download']/preceding-sibling::button[@title='Save']";
	/** @locatorType Xpath */
	public static final String OvertimeSAVEButton = "//*[text()='Cancel']/../preceding-sibling::button[@title='Save']";
	/** @locatorType Xpath */
	public static final String OvertimeUploadAmtButton = "//*[text()='Upd. Amount']/..";
	/** @locatorType Xpath */
	public static final String OvertimePostButton = "//*[text()='Post']/..";
	/** @locatorType Xpath */
	public static final String OvertimeFindPagetable = "//table[contains(@aria-label,'Result(s)')][contains(@data-testid,'L')]//tr/th";

	/** @locatorType Xpath */
	public static final String OvertimeDocNoSelect() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(OvertimeFindPagetable, "Doc. No.") + "/a";
	}

	/** @locatorType Xpath */
	public static final String OvertimeReverseButton = "//*[text()='Reverse']/..";
	/** @locatorType Xpath */
	public static final String OvertimeCancelButton = "//*[text()='Cancel']/..";

	/** @locatorType Xpath */
	public static final String OvertimePrefixField() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OvertimeLinesTable, "Prefix");
	}

	/** @locatorType Xpath */
	public static final String OvertimePrefixSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OvertimeLinesTable, "Prefix") + "//select";
	}

	/** @locatorType Xpath */
	public static final String TravelRequestFindAddButton = "//button[@title='New']";
	/** @locatorType Xpath */
	public static final String TravelRequestEntryEmployeeLOV = "//button[contains(@id,'HTRH_EMP_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String TravelMobileField = "//input[contains(@id,'HTRH_MOBILE_NO')]";
	/** @locatorType Xpath */
	public static final String TravelEmailField = "//input[contains(@id,'HTRH_EMAIL_ID')]";
	/** @locatorType Xpath */
	public static final String TravelPurposeOfTravelField = "//input[contains(@id,'HTRH_TRVL_PURPOSE')]";
	/** @locatorType Xpath */
	public static final String TravelDepartureDate = "//input[contains(@id,'HTRH_DEPART_DATE_input')]";
	/** @locatorType Xpath */
	public static final String TravelReturnDate = "//input[contains(@id,'HTRH_RETURN_DATE_input')]";
	/** @locatorType Xpath */
	public static final String TravelDepartureCity = "//button[contains(@id,'HTRH_DEPART_CITY_lov_btn')]";
	/** @locatorType Xpath */
	public static final String TravelDestinationCity = "//button[contains(@id,'HTRH_DESTN_CITY_lov_btn')]";
	/** @locatorType Xpath */
	public static final String TravelRequestSaveButton = "//span[contains(@class,'fa-check')]/parent::button[@title='Create']";
	/** @locatorType Xpath */
	public static final String TravelRequestExpensesButton = "//*[text()='Expenses']/..";
	/** @locatorType Xpath */
	public static final String TravelExpenseAddButton = "//button[@title='Add Expns']";
	/** @locatorType Xpath */
	public static final String TravelExpenseTable = "(//div[@aria-label='Expenses Estimation']//table)[2]";
	/** @locatorType Xpath */
	public static final String AdditionTravellersTable = "(//div[@aria-label='Additional Travellers']//table)[2]";

	/** @locatorType Xpath */
	public static final String TravelExpenseData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(TravelExpenseTable, "Expense");
	}

	/** @locatorType Xpath */
	public static final String TravelExpenseLOV() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(TravelExpenseTable, "Expense") + "//button";
	}

	/** @locatorType Xpath */
	public static final String TravelExpenseAmountData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(TravelExpenseTable, "Expense Amount");
	}

	/** @locatorType Xpath */
	public static final String TravelExpenseAmountInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(TravelExpenseTable, "Expense Amount")
				+ "//input";
	}

	/** @locatorType Xpath */
	public static final String TravelExpenseRefInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(TravelExpenseTable, "Reference")
				+ "//input";
	}

	/** @locatorType Xpath */
	public static final String TravelExpenseRefData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(TravelExpenseTable, "Reference");
	}

	/** @locatorType Xpath */
	public static final String TravelExpenseSaveButton = "//button[@title='Save Expns']";
	/** @locatorType Xpath */
	public static final String TravelExpenseCloseButton = "//h1[text()='Expenses Estimation']/following-sibling::button[@title='Close']/span"
			+ "";
	/** @locatorType Xpath */
	public static final String TravelAdditionalTravellersButton = "//*[text()='Additional Travellers']/..";
	/** @locatorType Xpath */
	public static final String TravelAttTraAddButton = "//button[@title='Add At']";

	/** @locatorType Xpath */
	public static final String TravelAttTraEMPData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AdditionTravellersTable, "Emp. ID");
	}

	/** @locatorType Xpath */
	public static final String TravelAttTraEMPLov() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AdditionTravellersTable, "Emp. ID")
				+ "//button";
	}

	/** @locatorType Xpath */
	public static final String TravelAttTraSaveButton = "//button[@title='save']";
	/** @locatorType Xpath */
	public static final String TravelAttTraCloseButton = "//h1[text()='Additional Travellers']/following-sibling::button[@title='Close']/span";
	/** @locatorType Xpath */
	public static final String TravelRequestPostButton = "//*[text()='Post']/..";
	/** @locatorType Xpath */
	public static final String TravelRequestDocNoField = "//input[contains(@id,'HTRH_RQST_NO')]";
	/** @locatorType Xpath */
	public static final String TravelRequestCancelButton = "//*[text()='Cancel']/..";

	/** @locatorType Xpath */
	public static final String TravelReimbFindSearchButton = "//button[@id='SEARCH']";
	/** @locatorType Xpath */
	public static final String TravelReqNoFindLov = "//button[contains(@id,'REQ_NO_lov_btn')]";
	/** @locatorType Xpath */
	public static final String TravelReimbFindTable = "//table[contains(@aria-label,'Result(s)')]//tr/th";

	/** @locatorType Xpath */
	public static final String TravelReimbDocNo() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(TravelReimbFindTable, "Doc.No") + ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String TravelRePlaceOfVisitAddButton = "//button[@title='Add Pov']";
	/** @locatorType Xpath */
	public static final String PlaceOfVisitTable = "(//table[@data-testid='ig_pov_Result_Data'])[2]";

	/** @locatorType Xpath */
	public static final String DateFromData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PlaceOfVisitTable, "Date From");
	}

	/** @locatorType Xpath */
	public static final String DateFromInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PlaceOfVisitTable, "Date From") + "//input";
	}

	/** @locatorType Xpath */
	public static final String DateToData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PlaceOfVisitTable, "Date To");
	}

	/** @locatorType Xpath */
	public static final String DateToInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PlaceOfVisitTable, "Date To") + "//input";
	}

	/** @locatorType Xpath */
	public static final String TravelReimCityLOV() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PlaceOfVisitTable, "City") + "//button";
	}

	/** @locatorType Xpath */
	public static final String TravelReimCityData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PlaceOfVisitTable, "City");
	}

	/** @locatorType Xpath */
	public static final String PlaceOfVisitSaveButton = "//button[@title='save Pov']";
	/** @locatorType Xpath */
	public static final String TravelReimExpensesButton = "//*[text()='Expenses']/..";
	/** @locatorType Xpath */
	public static final String ExpenseTable = "(//div[@aria-label='Expenses']//table)[2]";

	/** @locatorType Xpath */
	public static final String ExpenseAmountField() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ExpenseTable, "Actuals(Rs.)");
	}

	/** @locatorType Xpath */
	public static final String ExpenseAmountInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ExpenseTable, "Actuals(Rs.)") + "//input";
	}

	/** @locatorType Xpath */
	public static final String ExpenseFrame = "//h1[text()='Expenses']/../../div/iframe";
	/** @locatorType Xpath */
	public static final String TravelReimExpenseSaveButton = "//button[@title='save']";
	/** @locatorType Xpath */
	public static final String TravelReimExpenseClose = "//h1[text()='Expenses']/following-sibling::button[@title='Close']/span";
	/** @locatorType Xpath */
	public static final String TravelReimPostButton = "//*[text()='Post']/..";
	/** @locatorType Xpath */
	public static final String TravelReimDocNoField = "//*[contains(@id,'HTEH_DOC_NO_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String TravelReimDocCancel = "//*[text()='Cancel']/..";

	/** @locatorType Xpath */
	public static final String GeneralExpFindAddButton = "//button[@title='New']";
	/** @locatorType Xpath */
	public static final String GeneralExpEmpLov = "//button[contains(@id,'HGECH_EMP_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String GeneraalExpReference = "//input[contains(@id,'HGECH_REF')]";
	/** @locatorType Xpath */
	public static final String GeneralExpSaveButton = "//*[contains(@class,'fa fa-check')]/parent::button[@title='Create']";
	/** @locatorType Xpath */
	public static final String GeneralExpLineAddButton = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String GeneralExpLineTable = "(//table[@data-testid='ig_line_gen_exp_Result_Data'])[2]";

	/** @locatorType Xpath */
	public static final String GeneralExpenseData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GeneralExpLineTable, "Expense");
	}

	/** @locatorType Xpath */
	public static final String GeneralExpenseLOV() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GeneralExpLineTable, "Expense")
				+ "//button";
	}

	/** @locatorType Xpath */
	public static final String GeneralExpDateData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GeneralExpLineTable, "Date");
	}

	/** @locatorType Xpath */
	public static final String GeneralExpDateInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GeneralExpLineTable, "Date") + "//input";
	}

	/** @locatorType Xpath */
	public static final String GeneralExpAmountData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GeneralExpLineTable, "Amount");
	}

	/** @locatorType Xpath */
	public static final String GeneralExpAmountInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GeneralExpLineTable, "Amount") + "//input";
	}

	/** @locatorType Xpath */
	public static final String GeneralExpRemarksData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GeneralExpLineTable, "Remarks");
	}

	/** @locatorType Xpath */
	public static final String GeneralExpRemarksInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GeneralExpLineTable, "Remarks") + "//input";
	}

	/** @locatorType Xpath */
	public static final String GeneralExpenseLineSaveButton = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String GeneralExpensePostButton = "//*[text()='Post']/..";
	/** @locatorType Xpath */
	public static final String GeneralReimDocNoField = "//*[contains(@id,'HGECH_DOC_NO_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String GeneralReimStatusField = "//*[contains(@id,'HGECH_STATUS_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String GeneralExpenseCancelButton = "//*[text()='Cancel']/..";

	/** @locatorType Xpath */
	public static final String ResigRequestFindAddButton = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String ResignRqtDocDate = "//input[contains(@id,'EMPREL_DOC_DATE_input')]";
	/** @locatorType Xpath */
	public static final String ResignRqtEmployeeLOV = "//button[contains(@id,'EMPREL_EMP_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String ResignRqtReferenceField = "//input[contains(@id,'EMPREL_REFERENCE')]";
	/** @locatorType Xpath */
	public static final String ResignRqtEntrySaveButton = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String ResignRqtEntryPostButton = "//span[text()='Post']/..";
	/** @locatorType Xpath */
	public static final String ResignRqtDocNoField = "//input[contains(@id,'EMPREL_DOC_NO')]";
	/** @locatorType Xpath */
	public static final String FFSFindPendingForFNF = "//*[text()='Pending For FNF']/parent::button";
	/** @locatorType Xpath */
	public static final String PendingForFNFFrame = "//iframe[contains(@title,'Pending For FNF')]";
	/** @locatorType Xpath */
	public static final String FFSFindPendingForFNFReverseOk = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String ResignRequestFindEmpIdLov = "//button[contains(@id,'EMPLOYEE_lov_btn')]";
	/** @locatorType Xpath */
	public static final String ResignRequestFindSearchButton = "//button[@id='SEARCH']";
	/** @locatorType Xpath */
	public static final String ResignRequestFindTable = "//table[@data-testid='detail_Result_Data']//tr/th";

	/** @locatorType Xpath */
	public static final String ResignRequestFindDocSelect() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(ResignRequestFindTable, "Doc. No.")
				+ ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String ResignRequestDocCancel = "//span[text()='Cancel']/..";
	/** @locatorType Xpath */
	public static final String ResignRqtAdjustEncashLeave = "//*[contains(@id,'EMPREL_ADJUST_EL_LABEL')]";
	/** @locatorType Xpath */
	public static final String ResignRqtEmployeePay = "//*[contains(@id,'EMPREL_PAY_NOTICE_LABEL')]";
	/** @locatorType Xpath */
	public static final String PendingFNFTableSearchInput = "//div[@aria-label='Pending for FNF']//input[contains(@id,'search_field')]";
	/** @locatorType Xpath */
	public static final String PendingFNFTable = "(//div[@aria-label='Pending for FNF']//table)[2]//tr/th";

	/** @locatorType Xpath */
	public static final String FFSFindPendingForFNFReverseButton() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(PendingFNFTable, "Reverse the Doc.")
				+ ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String ResignationCreateFNF() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingFNFTable, "Create FNF") + "//a";
	}

	/** @locatorType Xpath */
	public static final String CreateFNFOkButton = "//button[text()='OK']";
}
