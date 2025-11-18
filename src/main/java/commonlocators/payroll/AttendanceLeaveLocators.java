package commonlocators.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
public class AttendanceLeaveLocators {
	/** @locatorType Xpath */
	public static final String ScreenSearch = "//*[@id=P0_SEARCH_input]";
	/** @locatorType Xpath */
	public static final String EmployeePermissionButton = "(//input[@name='P818510608_TAB']/following-sibling::label)[3]";
	/** @locatorType Xpath */
	public static final String EmpoloyeeFindPermissionAddButton = "//button[@data-testid='PER_Add_Button']";
	/** @locatorType Xpath */
	public static final String PermissionEmployeeLOV = "//Button[@data-testid='EMP_lov']";
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
	public static final String PermissionFindPageEmployeeLOV = "//Button[@data-testid='EMP_PER_find_lov']";
	/** @locatorType Xpath */
	public static final String EmployeePermissionTypeSelectField = "//Select[@data-testid='PER_TYPE']";
	/** @locatorType Xpath */
	public static final String EmployeeFindScreenPermissionTypeSelectField = "//Select[@data-testid='PER_FIND_TYP']";
	/** @locatorType Xpath */
	public static final String PermissionForwardApproval = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String PermissionOkButtonForward = "//*[@id='B1612675370193662596']";
	/** @locatorType Xpath */
	public static final String PermissionWorkApprovalFrame = "//iframe[contains(@title,'Workflow Approval')]";
	/** @locatorType Xpath */
	public static final String PermissionDocumentNo = "//input[@data-testid='PERM_DOC']";
	/** @locatorType Xpath */
	public static final String WorkApprovalOkButton = "//*[text()='Ok']/parent::button";
	/** @locatorType Xpath */
	public static final String ApprovalReturnButton = "//*[@id='P236131010_WFDC_ACT']/div/div/div[3]/label";
	/** @locatorType Xpath */
	public static final String ReturnUserLOV = "//*[@id='P236131010_WFDC_NXT_FWD_PERS_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String PermissionFindEmployeeColumn = "//*[@id='C1249623153083282334']/a";
	/** @locatorType Xpath */
	public static final String PermissionFindDocumentNoClick = "//*[@id=\"1921225137182250564_orig\"]/tbody/tr[2]/td[2]/a";
	/** @locatorType Xpath */
	public static final String PermissionReverse = "//button[@data-testid='PER_REVER']";
	/** @locatorType Xpath */
	public static final String PermissionReverseOk = "//*[@id='t_PageBody']/div[4]/div[3]/div/button[2]";
	/** @locatorType Xpath */
	public static final String PermissionCancelButton = "//button[@data-testid='PER_CANCEL']";
	/** @locatorType Xpath */
	public static final String EncashmentButton = "//*[@id='P818510608_TAB']/div/div/div[2]/label";
	/** @locatorType Xpath */
	public static final String EncashmentAddButon = "//Button[@data-testid='ENCAH_Add_Button']";
	/** @locatorType Xpath */
	public static final String EncashmentRequestDateField = "//input[@data-testid='LR_REQ_DATE']";
	/** @locatorType Xpath */
	public static final String EncashmentEmployeeLOV = "//Button[@data-testid='LR_EMP_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String EncashmentLeaveLOV = "//Button[@data-testid='LR_LEAVE_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String EncashDaysField = "//Input[@data-testid='LR_APPLIED_DAYS_1']";
	/** @locatorType Xpath */
	public static final String EncashmentReasonField = "//Input[@data-testid='LR_BRIEFDESC_1']";
	/** @locatorType Xpath */
	public static final String EncashmentViewAmount = "//Button[@data-testid='ENCAH_VIW_AMT']";
	/** @locatorType Xpath */
	public static final String EncashmentViewAmountClose = "//*[@id='wwvFlowForm']/div[3]/div[1]/button";
	/** @locatorType Xpath */
	public static final String EncashmentSaveButton = "//Button[@data-testid='ENCAH_SAV_BTN']";
	/** @locatorType Xpath */
	public static final String EncashmentDocNumber = "//Input[@data-testid='P41_LR_REQ_NO']";
	/** @locatorType Xpath */
	public static final String EncashmentFindDocSelect = "//*[@id=\"1925381684399899474_orig\"]/tbody/tr[2]/td[2]/a";
	/** @locatorType Xpath */
	public static final String EncashmentPostButton = "//button[@data-testid='ENCAH_POST_BTN']";
	/** @locatorType Xpath */
	public static final String EncashmentFindIcon = "//*[@id='B171495937485719290']";
	/** @locatorType Xpath */
	public static final String EncashmentFindSearchButon = "//button[@data-testid='ENC_SER_BTN']";
	/** @locatorType Xpath */
	public static final String EncashmentFindPageEmployeeLOV = "//Button[@data-testid='EMP_ENC_find_EMP_lov']";
	/** @locatorType Xpath */
	public static final String PendingEncashmentButton = "//Button[@data-testid='PEN_ENCAS_BTN']";
	/** @locatorType Xpath */
	public static final String PendingEncashmentscreen = "//iframe[@title='Pending Encashment']";
	/** @locatorType Xpath */
	public static final String PendingEncashmentSelectFlagIcon = "(//input[@type='checkbox'])[2]";
	/** @locatorType Xpath */
	public static final String CreateEncashmentLeave = "//Button[@data-testid='CRE_ENC_BTN']";
	/** @locatorType Xpath */
	public static final String CreateEncashmentOkButton = "//Button[@data-testid='ENC_PAY_OK_BTN']";
	/** @locatorType Xpath */
	public static final String EncashmentCancelButton = "//button[@data-testid='LEV_Cancel_Button']";
	/** @locatorType Xpath */
	public static final String CompOffButtonClick = "//*[@id='P818510608_TAB']/div/div/div[4]/label";
	/** @locatorType Xpath */
	public static final String CompOffFindAddIcon = "//Button[@data-testid='COMP_Add_Button']";
	/** @locatorType Xpath */
	public static final String CompOffEmployeeLOV = "//Button[@data-testid='COM_EMP_ID_lov']";
	/** @locatorType Xpath */
	public static final String CompOffDateFieldToday = "//button[@class='a-Button a-DatePicker--today']";
	/** @locatorType Xpath */
	public static final String CompOffDateField = "//Input[@data-testid='COM_OFF_DATE']";
	/** @locatorType Xpath */
	public static final String CompOffDaysField = "//Input[@data-testid='COM_OFF_DAYS']";
	/** @locatorType Xpath */
	public static final String CompOffReferenceField = "//*[@id='P8185106003_HECO_REFERENCE']";
	/** @locatorType Xpath */
	public static final String CompOffEntrySaveIcon = "//Button[@data-testid='COM_OFF_SAVE']";
	/** @locatorType Xpath */
	public static final String CompOffEntryPostIcon = "//Button[@data-testid='COM_OFF_POST']";
	/** @locatorType Xpath */
	public static final String CompOffDocumentNo = "//Input[@data-testid='COM_OFF_DOC_NO']";
	/** @locatorType Xpath */
	public static final String CompOffFindEmployeeLOV = "//*[@id='P818510608_EMPLOYEE_COM_lov_btn']/span";
	/** @locatorType Xpath */
	public static final String CompOffFindpageSearchButton = "//Button[@data-testid='COM_OFF_SEARCH']";
	/** @locatorType Xpath */
	public static final String CompOffFindpageClearButton = "//Button[@data-testid='COM_OFF_CLEAR']";
	/** @locatorType Xpath */
	public static final String LeaveRadioBtnLeaveRequest = "(//input[@name='P818510608_TAB']/following-sibling::label)[1]";
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
	public static final String LeaveEntryDisplayDocNoAndStatus = "//h2[@id='R1580086767959187340_heading']/b";
	/** @locatorType Xpath */
	public static final String LeaveRequestMigrationBtn = "//button[@data-testid='LR_MIG_BTN']";
	/** @locatorType Xpath */
	public static final String LeaveRequestMigrationIframe = "//iframe[@title='Leave Migration Type']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualLeaveRadioBtn = "//label[text()='Accrual Leave Upload']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualAddNewBtn = "//button[@data-testid='LR_ACCR_ADD_BTN']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualCalendarLovBtn = "//button[@id='P8185102503_ELAHD_CLNDR_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualYearLovBtn = "//button[@id='P8185102503_ELAHD_YEAR_lov_btn']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualPeriodLovBtn = "//button[@id='P8185102503_ELAHD_PERIOD_lov_btn']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualReferenceInput = "//input[@id='P8185102503_ELAHD_REF']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualCreateSaveIcon = "//button[@id='B3872530767904030752']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualUploadIcon = "//button[@id='B3872531113820030752']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualUploadInput = "//input[@id='P8185102503_LOAD_input']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualUploadBtn = "//button[@id='B3872508845490030713']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualCheckExcptionBtn = "//button[@id='B3872531504738030753']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualHeaderSaveIcon = "//button[@id='B3872530320322030752']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccuralDisplayDocNo = "//input[@id='P8185102503_ELAHD_DOC_NO']";
	/** @locatorType Xpath */
	public static final String LRMigrationAccrualPostBtn = "//button[@id='B3872532351353030753']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPRadioBtn = "//label[text()='LOP Leave  Upload']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPAddNewBtn = "//button[@id='B2522810846866038902']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPCalendarLovBtn = "//button[@id='P8185102501_ELHD_CLNDR_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPYearLovBtn = "//button[@id='P8185102501_ELHD_YEAR_lov_btn']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPPeriodLovBtn = "//button[@id='P8185102501_ELHD_PERIOD_lov_btn']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPReferenceInput = "//input[@id='P8185102501_ELHD_REF']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPCreateSaveIcon = "//button[@id='B3872465256759022422']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPUploadIcon = "//button[@id='B3872465615014022422']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPUploadInput = "//input[@id='P8185102501_LOAD_input']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPUploadBtn = "//button[@id='B3872462064185022416']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPCheckExcptionBtn = "//button[@id='B3872466082734022424']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPHeaderSaveIcon = "//button[@id='B3872464902207022422']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPDisplayDocNo = "//span[@id='P8185102501_ELHD_DOC_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String LRMigrationLOPPostBtn = "//button[@id='B3872466866658022424']";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindEmpIdLovBtn = "//*[@data-testid='Emp_ID_EL_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindDateFromInput = "//*[@data-testid='DATE_From_EL_Input']/input";
	/** @locatorType Xpath */
	public static final String LeaveEntryFindLeaveCategorySelect = "//select[@data-testid='CAT_EL_Input']";
	/** @locatorType Xpath */
	public static final String LeaveEntryDocNoWithPostedStatus = "(//table[@id='5802120366826427894_orig']/tbody/tr/td[13]/div[text()='Posted']/../../td[2])[1]";

	/** @locatorType Xpath */
	public static final String LeaveEntryPendReqCheckBox(String reqNo) {
		return "//input[contains(@id,'checkbox_" + reqNo + "')]";
	}

	/** @locatorType Xpath */
	public static final String LeaveEntrySearchDocNo(String DocNo) {
		return "//table[@id='5802120366826427894_orig']/tbody/tr/td[2]/a[text()='" + DocNo + "']";
	}

	/** @locatorType Xpath */
	public static final String LeaveEntrySearchFirstEmpName(String EmpName) {
		return "(//table[@id='5802120366826427894_orig']/tbody/tr/td[text()='" + EmpName + "']/../td[2])[1]";
	}

	/** @locatorType Xpath */
	public static final String LeaveEntryAttachmentBtn = "//button[@data-testid='LE_ATT_Button']";
	/** @locatorType Xpath */
	public static final String LeaveEntryAttachmentIframe = "//iframe[@title='Attachment']";
	/** @locatorType Xpath */
	public static final String AttachmentIframeCloseIcon = "//*[text()='Attachment']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LEAttachmentDocTypeLovBtn = "//button[@id='P37_DOC_TYPE_lov_btn']";
	/** @locatorType Xpath */
	public static final String LEAttachmentDocNoInput = "//input[@id='P37_DM_REQ_DOC_NO']";
	/** @locatorType Xpath */
	public static final String LEAttachmentDocRevInput = "//input[@id='P37_DM_REQ_DOC_REV']";
	/** @locatorType Xpath */
	public static final String LEAttachmentDateFromInput = "//input[@id='P37_DATE_FROM_input']";
	/** @locatorType Xpath */
	public static final String LEAttachmentDateToInput = "//input[@id='P37_DATE_TO_input']";
	/** @locatorType Xpath */
	public static final String LEAttachmentChooseFileInput = "//input[@id='P37_FILE_input']";
	/** @locatorType Xpath */
	public static final String LEAttachmentUploadBtn = "//button[@id='B2961718712824722473']";
	/** @locatorType Xpath */
	public static final String LEAttachmentDisplayedFileName = "//span[@class='a-FileDrop-heading']";
	/** @locatorType Xpath */
	public static final String LeaveEntryReportBtn = "//button[@data-testid='LE_PRINT_EN_Button']";
	/** @locatorType Xpath */
	public static final String LeaveAdjustmentLabel = "//label[text()='Adjustment']";
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
	public static final String AdjustmentDisplayStatus = "//h2[@id='R1580086767959187340_heading']/b";
	/** @locatorType Xpath */
	public static final String AdjustmentReverseBtn = "//button[@data-testid='LE_ADJ_REV_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentReverseOkBtn = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String AdjustmentFindSearchBtn = "//button[@data-testid='GO_Rpt_ADJ_Button']";
	/** @locatorType Xpath */
	public static final String AdjustmentMigrationBtn = "//button[@data-testid='MIG_ADJ_Button']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationCreateAddBtn = "//button[@id='B1965965931680659535']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationCalendarLovBtn = "//button[@id='P367011501_ELHD_CLNDR_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationYearSelect = "//select[@id='P367011501_ELHD_YEAR']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationPeriodSelect = "//select[@id='P367011501_ELHD_PERIOD']";

	/** @locatorType Xpath */
	public static final String AdjmntMigrationPeriodSelectOptions(String Period) {
		return "//select[@id='P367011501_ELHD_PERIOD']/option[contains(text(), '" + Period + "')]";
	}

	/** @locatorType Xpath */
	public static final String AdjmntMigrationModeSelect = "//select[@id='P367011501_ELHD_MODE']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationDocNoDisplay = "//input[@id='P367011501_ELHD_DOC_NO']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationReferenceInput = "//input[@id='P367011501_ELHD_REF']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationHeaderSaveBtn = "//button[@id='B4113087775633661975']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationUploadIcon = "//button[@id='B4109616029155791112']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationUploadIFrame = "//iframe[@title='Upload File']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationUploadInput = "//input[@id='P367011502_UPLOAD_input']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationUploadBtn = "//button[@id='B4116620483775021749']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationCheckExceptionBtn = "//button[@id='B4114574048443818500']";
	/** @locatorType Xpath */
	public static final String AdjmntMigrationPostBtn = "//button[@id='B4109616301294791114']";
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
	public static final String FindLeaveResultTable = "//table[@id='5802120366826427894_orig']//tr/td[6]";
	/** @locatorType Xpath */
	public static final String FindYearResultTable = "//table[@id='5802120366826427894_orig']//tr/td[10]";
	/** @locatorType Xpath */
	public static final String FindDesignationResultTable = "//table[@id='5802120366826427894_orig']//tr/td[15]";
	/** @locatorType Xpath */
	public static final String FindDepartmentResultTable = "//table[@id='5802120366826427894_orig']//tr/td[16]";
	/** @locatorType Xpath */
	public static final String FindUnitResultTable = "//table[@id='5802120366826427894_orig']//tr/td[14]";
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
	public static final String Adj_FindLeaveResultTable = "//table[@id='2026786604181972146_orig']//tr/td[9]";
	/** @locatorType Xpath */
	public static final String Adj_FindYearResultTable = "//table[@id='2026786604181972146_orig']//tr/td[15]";
	/** @locatorType Xpath */
	public static final String Adj_FindDesignationResultTable = "//table[@id='2026786604181972146_orig']//tr/td[5]";
	/** @locatorType Xpath */
	public static final String Adj_FindDepartmentResultTable = "//table[@id='2026786604181972146_orig']//tr/td[6]";
	/** @locatorType Xpath */
	public static final String Adj_FindUnitResultTable = "//table[@id='2026786604181972146_orig']//tr/td[7]";
	/** @locatorType Xpath */
	public static final String LoanRequestAddButton = "//button[@data-testid='LOAN_REQ_ADD_BUTTON']";
	/** @locatorType Xpath */
	public static final String LoanRequestEntryRequestDate = "//*[@id='P818121011_ELR_RQST_DATE_input']";
	/** @locatorType Xpath */
	public static final String LoanRequestEntryEmployeeLOV = "//button[@data-testid='ELR_EMP_find_lov']";
	/** @locatorType Xpath */
	public static final String LoanRequestEntryLoanLOV = "//button[@data-testid='ELR_LOAN_find_lov']";
	/** @locatorType Xpath */
	public static final String LoanRequestReferenceField = "//Input[@data-testid='LOAN_REQ_REF']";
	/** @locatorType Xpath */
	public static final String LoanRequestRequiredDetailsDateField = "//*[@id='P818121011_ELR_RQRD_DATE_input']";
	/** @locatorType Xpath */
	public static final String LoanRequestAmountField = "//Input[@data-testid='ELR_AMOUNT']";
	/** @locatorType Xpath */
	public static final String LoanRequestNoofInstallmentField = "//Input[@data-testid='ELR_INST_NO']";
	/** @locatorType Xpath */
	public static final String LoanRequestSuretyEmployee1 = "//Button[@data-testid='ELR_EMP1_find_LEV_lov']";
	/** @locatorType Xpath */
	public static final String LoanRequestSuretyEmployee2 = "//Button[@data-testid='ELR_EMP2_find_EMP_lov']";
	/** @locatorType Xpath */
	public static final String LoanRequestSaveIcon = "//button[@data-testid='LOAN_REQ_CREATE_Button']";
	/** @locatorType Xpath */
	public static final String LoanRequestPostIcon = "//button[@data-testid='LOAN_REQ_ENTRY_Button']";
	/** @locatorType Xpath */
	public static final String LoanRequestDocNoField = "//Input[@data-testid='LOAN_REQ_NO']";
	/** @locatorType Xpath */
	public static final String LoanRequestFindDocNoClick = "//*[@id='4342056657228940913_orig']/tbody/tr[2]/td[2]";
	/** @locatorType Xpath */
	public static final String LoanRequestFindSearch = "//button[@data-testid='LOAN_REQ_SEARCH_BUTTON']";
	/** @locatorType Xpath */
	public static final String LoanRequestDocCancel = "//Button[@data-testid='LOAN_REQ_CANCEL_Button']";
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
	public static final String LeaveRadioBtnLeaveEntry = "(//input[@id='P8185107006_TAB_0']/following-sibling::label)[1]";
	/** @locatorType Xpath */
	public static final String LeaveEntryAddButton = "//*[@data-testid='Add_EL_Button']";
	
	/** @locatorType Xpath */
	public static final String LoanPaymentFindSearch = "//button[@data-testid='ELN_SEARCH_Button']";
	/** @locatorType Xpath */
	public static final String LoanPaymentDocNoClick = "//*[@id='4522001890243159417_orig']/tbody/tr[2]/td[1]/a";
	/** @locatorType Xpath */
	public static final String LoanPaymentCreateLoanButton = "//button[@data-testid='ELP_CRE_LOAN_Button']";
	/** @locatorType Xpath */
	public static final String LoanCreateLoanPaymentFrame = "//iframe[@title='Create Loan Payment']";
	/** @locatorType Xpath */
	public static final String LoanCreateLoanPaymentpayrollOption = "//*[@id='P8181210302_PAY_MODE']/div/div/div[1]/label";
	/** @locatorType Xpath */
	public static final String LoanCreateLoanPaymentSaveOption = "//*[@id='B3265219208604470444']";
	/** @locatorType Xpath */
	public static final String LoanPaymentReverseButton = "//button[@data-testid='ELP_REVERSE_Button']";
}
