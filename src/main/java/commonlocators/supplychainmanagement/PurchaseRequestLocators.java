package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PurchaseRequestLocators {
	/** @locatorType Xpath */
	public static final String PurchaseIDHeader = "//span[contains(@id,'PRH_RQST_NO_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String PRLocationButton = "//button[contains(@id,'PRH_PLNT_LOC_ID')]";
	/** @locatorType Xpath */
	public static final String PRCPCCodeButton = "//button[contains(@id,'PRH_CC_CODE')]";
	/** @locatorType Xpath */
	public static final String PRTypeSelect = "//select[contains(@id,'PRH_REC_SOURCE')]";
	/** @locatorType Xpath */
	public static final String PRDepartmentSelect = "//select[contains(@id,'PRH_RQST_DEPT_ID')]";
	/** @locatorType Xpath */
	public static final String PRRequesterSelect = "//select[contains(@id,'PRH_REQSTR_ID')]";
	/** @locatorType Xpath */
	public static final String PRPrefixSelect = "//button[@id='P161513103_PRH_RQST_PFX_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindPR_PRNoButton = "//button[contains(@id,'DOC_NO')]";
	/** @locatorType Xpath */
	public static final String FindSearchButton = "//button[@id='B148414063445984831']";
	/** @locatorType Xpath */
	public static final String PRStatusBoldText = "//div[contains(@style,'bold')]";
	/** @locatorType Xpath */
	public static final String AddLineIcon = "//button[@title='Add Line']";
	/** @locatorType Xpath */
	public static final String SaveLineBtn = "//button[@title='Save Line']";
	/** @locatorType Xpath */
	public static final String SaveHeaderBtn = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String BackIconBtn = "//button[@title='Back']";
	/** @locatorType Xpath */
	public static final String PostBtn = "//button[@id='post']";
	/** @locatorType Xpath */
	public static final String ItemDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[2])[1]";
	/** @locatorType Xpath */
	public static final String ItemDataLastRowInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[2])[1]/div/input";
	/** @locatorType Xpath */
	public static final String QuantityDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[6])[1]";
	/** @locatorType Xpath */
	public static final String QuantityDataLastRowInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[6])[1]/div//input";
	/** @locatorType Xpath */
	public static final String UnitCostDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[7])[1]";
	/** @locatorType Xpath */
	public static final String UnitCostDataLastRowInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[7])[1]/div//input";
	/** @locatorType Xpath */
	public static final String ItemDataListIcon = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[2])[1]//span[@class='a-Icon icon-popup-lov'][1]";
	/** @locatorType Xpath */
	public static final String ItemDataPopupSearchBox = "//input[contains(@class,'a-PopupLOV-search')]";
	/** @locatorType Xpath */
	public static final String ItemDataSearchFirstResult = "//tr/td[1][contains(@data-id,'FG00011')]";

	/** @locatorType Xpath */
	public static final String FindPR_LocIDBtn = "//button[contains(@id,'LOCATION')]";
	/** @locatorType Xpath */
	public static final String FindPR_UnitBtn = "//button[contains(@id,'PLNT_lov')]";
	/** @locatorType Xpath */
	public static final String FindPR_ItemBtn = "//button[contains(@id,'PROD_ID')]";
	/** @locatorType Xpath */
	public static final String FindPR_RequesterBtn = "//button[contains(@id,'REQ_ID')]";
	/** @locatorType Xpath */
	public static final String FindPR_ClassBtn = "//button[contains(@id,'CLASS')]";
	/** @locatorType Xpath */
	public static final String FindPR_SubclassBtn = "//button[contains(@id,'SUBCLS')]";
	/** @locatorType Xpath */
	public static final String FindPR_SOPrjRefBtn = "//button[contains(@id,'SO_SCHLD_DESC')]";
	/** @locatorType Xpath */
	public static final String FindPR_WHDeptBtn = "//button[contains(@id,'STORE_ID')]";
	/** @locatorType Xpath */
	public static final String FindPR_MRPNoBtn = "//button[contains(@id,'MRP_NO')]";
	/** @locatorType Xpath */
	public static final String FindPR_PRTypeSelect = "//select[contains(@id,'DOC_TYPE')]";
	/** @locatorType Xpath */
	public static final String FindPR_PRStatusSelect = "//select[contains(@id,'STATUS')]";
	/** @locatorType Xpath */
	public static final String FindPR_ReqDateFROMINPUT = "//input[contains(@id,'REQ_DATE_FROM_input')]";
	/** @locatorType Xpath */
	public static final String FindPR_ReqDateTOINPUT = "//input[contains(@id,'REQ_DATE_TO_input')]";
	/** @locatorType Xpath */
	public static final String PR_TableViewCountSelect = "//select[@id='ld_row_select']";
	/** @locatorType Xpath */
	public static final String PR_Cancelbtn = "//button[@id='B4691386861259104744']";

	/** @locatorType Xpath */
	public static final String PR_LineDeleteIconWithData(String Text) {
		return "(//td[text()='" + Text + "']/..//span[@class='fa fa-trash-o'])[1]";
	}

	/** @locatorType Xpath */
	public static final String PR_UploadTemplateBtn = "//button[@title='Upload Template']";
	/** @locatorType Xpath */
	public static final String PR_UploadFileInput = "//input[contains(@id,'FILE_input')]";
	/** @locatorType Xpath */
	public static final String PR_UploadedFileText = "//div[contains(@id,'FILE_DROPZON')]//span[@class='a-FileDrop-heading']";
	/** @locatorType Xpath */
	public static final String PR_UploadOkBtn = "//button[@id='B1122765554844427422']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_AddNewBtn = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_FindDocNo = "//button[contains(@id,'DOC_NO')]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_SearchBtn = "//button[@id='B1764878166242042203']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_LocId = "//button[contains(@id,'PCH_PLNT_LOC_ID')]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_Prefix = "//button[contains(@id,'PFX')]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_Reference = "//input[contains(@id,'PCH_REF')]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_TickIcon = "//button[@id='B1765045895689011518']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_DocNoText = "//input[contains(@id,'PCH_DOC_NO')]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_LoadPRBtn = "//button[@id='B1758795715993518741']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_FetchPRIframe = "//iframe[@title='Fetch PR']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_SelectAllPRItem = "(//span[@class='a-IRR-headerLabel'])[1]";
	/** @locatorType Xpath */
	public static final String PRCloseShort_OkBtn = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_SaveLinesBtn = "//button[@id='B2727201100997978947']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_SaveHeaderBtn = "//button[@id='B1757901165046819709']";
	/** @locatorType Xpath */
	public static final String PRCloseShort_CloseShortBtn = "//button[@id='B1762277391045906722']";
	/** @locatorType Xpath */
	public static final String LineAmountDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[8])[1]";
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
	public static final String PR_LastItemDetailIcon = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[15])[1]//span[@class='fa fa-info-circle']";
	/** @locatorType Xpath */
	public static final String PR_LineDetailsIframe = "//iframe[@title='Line Details']";
	/** @locatorType Xpath */
	public static final String PRLineDetails_Class = "//span[@id='P161513103003_PRL_CLASS_DESC_DISPLAY']";
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
	public static final String PRFindResultSearchBar = "//input[@id='ld_search_field']";
	/** @locatorType Xpath */
	public static final String PRResultTableStatusText = "//div[@data-testid='PR_STATUS_TABLE_TEXT']";
	/** @locatorType Xpath */
	public static final String PRResultTablePRType = "//table[@id='2095931872759625206_orig']//tr/td[4]";
	/** @locatorType Xpath */
	public static final String PRResultTableUnit = "//table[@id='2095931872759625206_orig']//tr/td[3]";
	/** @locatorType Xpath */
	public static final String PRResultTableLocation = "//table[@id='2095931872759625206_orig']//tr/td[2]";
	/** @locatorType Xpath */
	public static final String PRResultTableRequestor = "//table[@id='2095931872759625206_orig']//tr/td[8]";
	/** @locatorType Xpath */
	public static final String PRResultTableItem = "//table[@id='2095931872759625206_orig']//tr/td[9]";
}
