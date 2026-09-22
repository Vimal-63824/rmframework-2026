package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PurchaseRequestLocators {
	/** @locatorType Xpath */
	public static final String PurchaseIDHeader = "//span[@data-testid='PR_DOC_NO_TEXT']";
	/** @locatorType Xpath */
	public static final String PRLocationButton = "//input[@data-testid='PR_LOC_ID_SELECTION']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PRCPCCodeButton = "//input[@data-testid='PR_CPC_CODE_SELECTION']/following::button[@id='P16151310301_PRH_CC_CODE_lov_btn']";
	/** @locatorType Xpath */
	public static final String PRTypeSelect = "//select[@data-testid='PR_TYPE_SELECTION']";
	/** @locatorType Xpath */
	public static final String PRDepartmentSelect = "//select[contains(@id,'PRH_RQST_DEPT_ID')]";
	/** @locatorType Xpath */
	public static final String PRRequesterSelect = "//select[@data-testid='PR_REQUESTER_SELECTION']";
	/** @locatorType Xpath */
	public static final String PRPrefixSelect = "//input[@data-testid='PR_PREFIX_SELECTION']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_PRNoButton = "//input[@data-testid='PR_FIND_PR.NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindSearchButton = "//span[text()='Search']/../../button[1]";

	/** @locatorType Xpath */
	public static final String PRStatusBoldText(String value) {
		return "(//td/a[text()='" + value + "']/../../td/div[contains(@style,'bold')][last()])[last()]";
	}

	/** @locatorType Xpath */
	public static final String AddLineIcon = "//button[@data-testid='PR_ADDLINE']";
	/** @locatorType Xpath */
	public static final String SaveLineBtn = "//button[@data-testid='Save_line']";
	/** @locatorType Xpath */
	public static final String SaveHeaderBtn = "//button[@testid='PR_SAVEHEADER']";
	/** @locatorType Xpath */
	public static final String BackIconBtn = "//button[@title='Back']";
	/** @locatorType Xpath */
	public static final String PostBtn = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String ItemDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[3])[1]";
	/** @locatorType Xpath */
	public static final String PRTable = "(//table[@data-testid='HD_Result_Data'])[2]";
	/** @locatorType Xpath */
	public static final String PRATable = "(//div[@id='LINE_ig_grid_vc']//table)[2]";

	/** @locatorType Xpath */
	public static final String ItemDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PRTable, "Item");
	}

	/** @locatorType Xpath */
	public static final String ItemDataLastRowInput = ItemDataLastRow() + "//input";

	/** @locatorType Xpath */
	public static final String QuantityDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PRTable, "RequestQuantity");
	}

	/** @locatorType Xpath */
	public static final String QuantityDataLastRowInput = QuantityDataLastRow() + "//input";

	/** @locatorType Xpath */
	public static final String UnitCostDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PRTable, "UnitCost");
	}

	/** @locatorType Xpath */
	public static final String PRAUnitCostDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PRATable, "UnitCost");
	}

	/** @locatorType Xpath */
	public static final String UnitCostDataLastRowInput = UnitCostDataLastRow() + "//input";
	/** @locatorType Xpath */
	public static final String ItemDataListIcon = ItemDataLastRow() + "//button";
	/** @locatorType Xpath */
	public static final String ItemDataPopupSearchBox = "//input[contains(@class,'a-PopupLOV-search')]";
	/** @locatorType Xpath */
	public static final String ItemDataSearchFirstResult = "//tr/td[1][contains(@data-id,'FG00011')]";

	/** @locatorType Xpath */
	public static final String FindPR_LocIDBtn = "//input[@data-testid='PR_FIND_LOCATION']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_UnitBtn = "//input[@data-testid='PR_FIND_UNIT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_ItemBtn = "//input[@data-testid='PR_FIND_ITEM']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_RequesterBtn = "//input[@data-testid='PR_FIND_REQUESTER']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_ClassBtn = "//input[@data-testid='PR_FIND_CLASS']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_SubclassBtn = "//input[@data-testid='PR_FIND_SUBCLASS']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_SOPrjRefBtn = "//input[@data-testid='PR_FIND_Prj-Ref(SO)']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_WHDeptBtn = "//input[@data-testid='PR_FIND_WAREHOUSE-DEPT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_MRPNoBtn = "//input[@data-testid='PR_FIND_MRP-NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPR_PRTypeSelect = "//select[contains(@id,'DOC_TYPE')]";
	/** @locatorType Xpath */
	public static final String FindPR_PRStatusSelect = "//select[@data-testid='PR_FIND_STATUS']";
	/** @locatorType Xpath */
	public static final String FindPR_ReqDateFROMINPUT = "//input[contains(@id,'REQ_DATE_FROM_input')]";
	/** @locatorType Xpath */
	public static final String FindPR_ReqDateTOINPUT = "//input[contains(@id,'REQ_DATE_TO_input')]";
	/** @locatorType Xpath */
	public static final String PR_TableViewCountSelect = "//select[@id='ld_row_select']";
	/** @locatorType Xpath */
	public static final String PR_Cancelbtn = "//button[@data-testid='PR_CANCEL_BUTTON']";

	/** @locatorType Xpath */
	public static final String PR_LineDeleteIconWithData(String Text) {
		return "(//td[text()='" + Text + "']/..//span[@data-testid='PR_LINE_DELETE_ICON'])[1]";
	}

	/** @locatorType Xpath */
	public static final String PR_UploadTemplateBtn = "//button[@title='Upload Template']";
	/** @locatorType Xpath */
	public static final String PR_UploadFileInput = "//input[contains(@id,'FILE_input')]";
	/** @locatorType Xpath */
	public static final String PR_UploadedFileText = "//div[contains(@id,'FILE_DROPZON')]//span[@class='a-FileDrop-heading']";
	/** @locatorType Xpath */
	public static final String PR_UploadOkBtn = "//button[@data-testid='Upload_OK']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_AddNewBtn = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_FindDocNo = "//button[contains(@id,'DOC_NO')]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_SearchBtn = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_LocId = "//input[@id='P1615131122011_PCH_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PRCloseShort_Prefix = "//input[@id='P1615131122011_PFX']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PRCloseShort_Reference = "//input[@id='P1615131122011_PCH_REF']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_TickIcon = "//button[@data-testid='Create' and @id='B2010164271553681647']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_DocNoText = "//input[contains(@id,'PCH_DOC_NO')]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_LoadPRBtn = "//button[@data-testid='Load_PR']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_FetchPRIframe = "//iframe[@title='Load PR']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_SelectAllPRItem = "(//span[@class='a-IRR-headerLabel'])[1]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_OkBtn = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_SaveLinesBtn = "//button[@id='B2727201100997978947']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_SaveHeaderBtn = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_CloseShortBtn = "//button[@data-testid='Closeshort']";

	/** @locatorType Xpath */
	public static final String LineAmountDataLastRow(String Value) {
		return "//td[text()='" + Value + "']/../td[9]";

	}

	/** @locatorType Xpath */
	public static final String PRHeader_Location = "//span[@id='P161513103001_PRH_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PRHeader_Unit = "//input[@id='P161513103001_PRH_PLANT']";
	/** @locatorType Xpath */
	public static final String PRHeader_PRType = "//span[@id='P161513103001_PRH_REC_SOURCE_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PRHeader_PRDate = "//span[@id='P161513103001_PRH_RQST_DATE_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PRHeader_PRPrefix = "//span[@id='P161513103001_PRH_RQST_PFX_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PRHeader_Requester = "//input[@id='P161513103001_PRH_REQSTR_ID']";
	/** @locatorType Xpath */
	public static final String PRHeader_CPCCode = "//input[@id='P161513103001_PRH_CC_CODE']";
	/** @locatorType Xpath */
	public static final String UOMDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[5])[1]";

	/** @locatorType Xpath */
	public static final String PR_LastItemDetailIcon(String Value) {
		return "//td[text()='" + Value + "']/../td[1]";

	}

	/** @locatorType Xpath */
	public static final String PR_LineDetailsIframe = "//iframe[@title='Line Details']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_Class = "//input[@data-testid='P161513103003_PRL_STORE_NAME']/..";
	/** @locatorType Xpath */
	public static final String PRLineDetails_SubClass = "//span[@id='P161513103003_PRL_SUB_CLS_DESC_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_PurchaseUOM = "//span[@id='P161513103003_PRL_UOM_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_Warehouse = "//input[@id='P161513103003_PRL_STORE_NAME']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_ConventionFactor = "//input[@id='P161513103003_PRL_CONV_FACTOR']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_ItemCPCCode = "//input[@id='P161513103003_PRL_CC_CODE']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_ItemCPCDesc = "//input[@id='P161513103003_CPC_DESC']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_GLAccNo = "//input[@id='P161513103003_PRL_PUR_ACCT']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_GLAccDesc = "//input[@id='P161513103003_PRL_PUR_ACCT_DESC']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_CertificateInput = "//input[@id='P161513103003_PRL_CERT_ID']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_Certificatebtn = "//button[@id='P161513103003_PRL_CERT_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_RequiredCertificateInputBox = "//input[@id='P161513103003_PRL_TEST_REQ_FLAG']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_RequiredCertificateLabel = "//label[@id='P161513103003_PRL_TEST_REQ_FLAG_LABEL']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_CloseIcon = "//h1[text()='Line Details']/following-sibling::button[@title='Close']";
	/** @locatorType Xpath */
	public static final String PRFindClearIcon = "//select[@id='Clear']";
	/** @locatorType Xpath */
	public static final String PRFindResultSearchBar = "//input[@id='SSR_search_field']";
	/** @locatorType Xpath */
	public static final String PRResultTableStatusText = "//div[@data-testid='PR_STATUS_TABLE_TEXT']";

	/** @locatorType Xpath */
	public static final String PRFindTable = "//table[@data-testid='SSR_Result_Data']/tbody/tr/th";

	/** @locatorType Xpath */
	public static final String PRResultTablePRType() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(PRFindTable, "Sub Vou. Type");
	}

	/** @locatorType Xpath */
	public static final String PRResultTableUnit() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(PRFindTable, "Unit");
	}

	/** @locatorType Xpath */
	public static final String PRResultTableLocation() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(PRFindTable, "Loc. ID");
	}

	/** @locatorType Xpath */
	public static final String PRResultTableRequestor() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(PRFindTable, "Requestor");
	}

	/** @locatorType Xpath */
	public static final String PRResultTableItem() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(PRFindTable, "Item");
	}

	/** @locatorType Xpath */
	public static final String GlAccountNoLovButton = "//button[@id='P161513103003_PRL_PUR_ACCT_lov_btn']";
	/** @locatorType Xpath */
	public static final String LineDetailSaveButton = "//button[contains(@onclick,\"apex.submit({request:'Save'\")]";

	/** @locatorType Xpath */
	public static final String LineDetailsButton(String Item) {
		return "//td[text()='" + Item + "']/../td/*/span[@class='fa fa-info-circle']";
	}

	/** @locatorType Xpath */
	public static final String RequiredDateTable = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[9])[1]";
	/** @locatorType Xpath */
	public static final String RequiredDateInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[9])[1]//input";
	/** @locatorType Xpath */
	public static final String PRAddFrame = "//iframe[@title='Create Purchase Request']";
	/** @locatorType Xpath */
	public static final String HeaderCheckbox = "//label[@for='P161513103_RADIO_GRP_HD_LN_0']";
	/** @locatorType Xpath */
	public static final String AddButtonPRCs = "//button[@data-testid='add']";
	/** @locatorType Xpath */
	public static final String HeaderCheckBox = "(//input[@data-testid='P1615131122_HEAD_LINE']/following-sibling::label)[1]";
	/** @locatorType Xpath */
	public static final String PRHeaderCheckBox = "//input[@data-display='Line(s)']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String GlAccNo = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[13]";
	/** @locatorType Xpath */
	public static final String GlAccNoLovButton = "//input[@data-testid='PRL_PUR_ACCT']/following-sibling::button";
	/** @Locator XPATH */
	public static final String PendingPR = "//button[@data-testid='ADD_PR']";
	/** @Locator XPATH */
	public static final String NewQuantityTable = "//div[@id='LINE_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[11]";
	/** @Locator XPATH */
	public static final String NewQuantityInput = "//div[@id='LINE_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[11]//input";
	/** @Locator XPATH */
	public static final String LineSaveButton = "//button[@data-testid='Save']";
	/** @Locator XPATH */
	public static final String HeaderSaveButton = "//button[@data-testid='SAVE']";
	/** @Locator XPATH */
	public static final String HeaderPostButton = "//button[@data-testid='Closeshort']";
	/** @Locator XPATH */
	public static final String CreatePRButton = "//span[text()='Create PR Amd.']/..";
	/** @Locator XPATH */
	public static final String PRAmendFrame = "//iframe[contains(@title,'Create')]";
	/** @Locator XPATH */
	public static final String ItemDetailsButton = "//div[@aria-label='Item Details']//button";

	/** @Locator XPATH */
	public static String PurchaseNo(String HeaderValue) {
		String Locator = "(//table[@data-testid='HD_Result_Data'])[2]";
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(Locator, HeaderValue);
	}

	/** @locatorType Xpath */
	public static final String LineDelete = "//a[@data-action='LN_DEL']";

}
