package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class OpenSalesOrderLocators {
	/** @locatorType Xpath */
	public static final String OpenSORadioBtn = "//label[@for='P191513101322_TYPE_0']";
	/** @locatorType Xpath */
	public static final String OpenSOAmendRadioBtn = "//label[@for='P191513101322_TYPE_1']";
	/** @locatorType Xpath */
	public static final String FindHeadingText = "//h2[@class='t-Region-title']/b";
	/** @locatorType Xpath */
	public static final String OpenSO_CreatePlusIcon = "//button[@id='B844395797394281167']";
	/** @locatorType Xpath */
	public static final String OpenSOTypeSelect = "//select[@id='P19151310131_SRCHD_TYPE']";
	/** @locatorType Xpath */
	public static final String OpenSOLocationLovBtn = "//button[@id='P19151310131_SRCHD_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String OpenSOCustomerLovBtn = "//button[@id='P19151310131_SRCHD_CUST_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String OpenSoPrefixSelect = "//select[@id='P19151310131_SRCHD_DOC_PFX']";
	/** @locatorType Xpath */
	public static final String OpenSO_PONumberInput = "//input[@id='P19151310131_SRCHD_PO_NO']";
	/** @locatorType Xpath */
	public static final String OpenSO_PODateInput = "//input[@id='P191513101301_SRCHD_PO_DATE_input']";
	/** @locatorType Xpath */
	public static final String OpenSO_FormTickIcon = "//button[@id='B2096709921459736649']";
	/** @locatorType Xpath */
	public static final String FetchCustomerItemBtn = "//button[@id='B6858202336734539708']";

	/** @locatorType Xpath */
	public static final String fetchCustomerItemCheckedBox(String item) {
		return "//td[text()='" + item + "']/ancestor::tr/td[9]";
	}

	/** @locatorType Xpath */
	public static final String FetchCustomerIframe = "//iframe[@title='Details']";
	/** @locatorType Xpath */
	public static final String FetchCustomerOkBtn = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String OpenSOAddLineBtn = "//button[contains(@id,'B6897382655960973741')]";
	/** @locatorType Xpath */
	public static final String ItemLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[2])[1]";
	/** @locatorType Xpath */
	public static final String ItemLastLineDataLov = "//button[contains(@id,'C6911139583359649902_lov_btn')]";
	/** @locatorType Xpath */
	public static final String PriceLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[6])[1]";
	/** @locatorType Xpath */
	public static final String PriceLastLineInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[6])[1]//input";
	/** @locatorType Xpath */
	public static final String MRPLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[7])[1]";
	/** @locatorType Xpath */
	public static final String MRPLastLineInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[7])[1]//input";
	/** @locatorType Xpath */
	public static final String DiscountLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[8])[1]";
	/** @locatorType Xpath */
	public static final String DiscountLastLineInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[8])[1]//input";
	/** @locatorType Xpath */
	public static final String HSNCodeLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[9])[1]";
	/** @locatorType Xpath */
	public static final String HSNCodeLastLineLovBtn = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[9])[1]//button";
	/** @locatorType Xpath */
	public static final String OpenSOLinesSaveBtn = "//button[@id='B6897382732535973742']";
	/** @locatorType Xpath */
	public static final String OpenSOHeaderSaveBtn = "//button[@id='B6857351475657038192']";
	/** @locatorType Xpath */
	public static final String OpenSOPostBTN = "//button[@id='B6925905994051550718']";
	/** @locatorType Xpath */
	public static final String OpenSOEffDateFrom = "//input[@id='P191513101301_SRCHD_START_DATE_input']";
	/** @locatorType Xpath */
	public static final String OpenSOEffDateTo = "//input[@id='P191513101301_SRCHD_END_DATE_input']";
	/** @locatorType Xpath */
	public static final String OpenSODisplayDocNumber = "//span[@id='P191513101301_SRCHD_DOC_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String OpenSOWorkApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String OpenSOForwardApproval = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String OpenSOOkButtonForward = "//*[@id='B1612723241586671539']";
	/** @locatorType Xpath */
	public static final String OpenSOFindPageSearchBtn = "//button[contains(@id,'B844395320575281167')]";
	/** @locatorType Xpath */
	public static final String FindOpenSOItemLovBtn = "//button[@id='P19151310131_ITEM_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindOpenSODocNoLovBtn = "//button[@id='P19151310131_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindOpenSOCustomerLovBtn = "//button[@id='P19151310131_CUSTOMER_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindApprovedDateTableList = "(//table//tbody/tr/td[19])";
	/** @locatorType Xpath */
	public static final String OpenSO_CancelHeaderBtn = "//button[@id='B6857350295074038183']";
	/** @locatorType Xpath */
	public static final String OpenSOBackIcon = "//button[@id='B1758893841899309612']";

	/** @locatorType Xpath */
	public static final String FindStatusTableWithDocNo(String Docno) {
		return "//*[text()='" + Docno + "']/../../td[15]/div";
	}

}
