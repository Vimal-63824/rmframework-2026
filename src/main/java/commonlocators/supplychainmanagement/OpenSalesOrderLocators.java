package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class OpenSalesOrderLocators {
	
		/** @locatorType Xpath */
		public static final String OpenSORadioBtn = "//input[@data-display='Open SO']/following-sibling::label";
		/** @locatorType Xpath */
		public static final String OpenSOAmendRadioBtn = "//input[@data-display='Open SO Amendment']/following-sibling::label";
		/** @locatorType Xpath */
		public static final String FindHeadingText = "//h2[@class='t-Region-title']/b";
		/** @locatorType Xpath */
		public static final String OpenSO_CreatePlusIcon = "//button[@data-testid='Open_So_Add_Button']";
		/** @locatorType Xpath */
		public static final String OpenSOTypeSelect = "//select[@data-testid='P19151310131_SRCHD_TYPE']";
		/** @locatorType Xpath */
		public static final String OpenSOLocationLovBtn = "//*[@data-testid='P19151310131_SRCHD_PLNT_LOC_ID']/following-sibling::button";
		/** @locatorType Xpath */
		public static final String OpenSOCustomerLovBtn = "//*[@data-testid='P19151310131_SRCHD_CUST_ID']/following-sibling::button";
		/** @locatorType Xpath */
		public static final String OpenSoPrefixSelect = "//select[@data-testid='P19151310131_SRCHD_DOC_PFX']";
		/** @locatorType Xpath */
		public static final String OpenSO_PONumberInput = "//input[@data-testid='P19151310131_SRCHD_PO_NO']";
		/** @locatorType Xpath */
		public static final String OpenSO_PODateInput = "//*[@data-testid='P191513101301_SRCHD_PO_DATE']/input";
		/** @locatorType Xpath */
		public static final String OpenSO_FormTickIcon = "//button[@data-testid='SAVE']";
		/** @locatorType Xpath */
		public static final String FetchCustomerItemBtn = "//button[@data-testid='Open_So_Fetch_Button']";

		/** @locatorType Xpath */
		public static final String fetchCustomerItemCheckedBox(String item) {
			return "//td[text()='" + item + "']/ancestor::tr/td[9]";
		}

		/** @locatorType Xpath */
		public static final String FetchCustomerIframe = "//iframe[@title='Details']";
		/** @locatorType Xpath */
		public static final String FetchCustomerOkBtn = "//button[@id='B1']";
		/** @locatorType Xpath */
		public static final String OpenSOAddLineBtn = "//button[@data-testid='Open_So_Add_Button_Tlines']";
		/** @locatorType Xpath */
		public static final String ItemLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[2])[1]";
		/** @locatorType Xpath */
		public static final String ItemLastLineDataLov = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[2])[1]//button";
		/** @locatorType Xpath */
		public static final String PriceLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[6])[1]";
		/** @locatorType Xpath */
		public static final String PriceLastLineInput = "//input[@data-testid='SRCLN_PRICE']";
		/** @locatorType Xpath */
		public static final String MRPLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[7])[1]";
		/** @locatorType Xpath */
		public static final String MRPLastLineInput = "//input[@data-testid='SRCLN_MRP_PRICE']";
		/** @locatorType Xpath */
		public static final String DiscountLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[8])[1]";
		/** @locatorType Xpath */
		public static final String DiscountLastLineInput = "//input[@data-testid='SRCLN_DISC_PCT']";
		/** @locatorType Xpath */
		public static final String HSNCodeLastLineData = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[9])[1]";
		/** @locatorType Xpath */
		public static final String HSNCodeLastLineLovBtn = "//input[@data-testid='SRCLN_HSN_CODE']/following-sibling::button";
		/** @locatorType Xpath */
		public static final String OpenSOLinesSaveBtn = "//button[@data-testid='Open_So_Save_Button_Lines']";
		/** @locatorType Xpath */
		public static final String OpenSOHeaderSaveBtn = "//button[@data-testid='Open_So_Header_Save_Button']";
		/** @locatorType Xpath */
		public static final String OpenSOPostBTN = "//button[@data-testid='Open_So_Post_Button']";
		/** @locatorType Xpath */
		public static final String OpenSOEffDateFrom = "//*[@data-testid='Open_So_Eff_From']/input";
		/** @locatorType Xpath */
		public static final String OpenSOEffDateTo = "//*[@data-testid='Open_So_Eff_To']/input";
		/** @locatorType Xpath */
		public static final String OpenSODisplayDocNumber = "//span[@data-testid='P191513101301_SRCHD_DOC_NO']";
		/** @locatorType Xpath */
		public static final String OpenSOWorkApprovalFrame = "//iframe[@title='Workflow Approval']";
		/** @locatorType Xpath */
		public static final String OpenSOForwardApproval = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
		/** @locatorType Xpath */
		public static final String OpenSOOkButtonForward = "//*[@id='B1612723241586671539']";
		/** @locatorType Xpath */
		public static final String OpenSOFindPageSearchBtn = "//button[@data-testid='Find_line']";
		/** @locatorType Xpath */
		public static final String FindOpenSOItemLovBtn = "//*[@data-testid='P19151310131_ITEM_1']/following-sibling::button";
		/** @locatorType Xpath */
		public static final String FindOpenSODocNoLovBtn = "//*[@data-testid='P19151310131_DOC_NO']/following-sibling::button";
		/** @locatorType Xpath */
		public static final String FindOpenSOCustomerLovBtn = "//*[@data-testid='P19151310131_CUSTOMER_1']/following-sibling::button";
		/** @locatorType Xpath */
		public static final String FindApprovedDateTableList = "(//table//tbody/tr/td[19])";
		/** @locatorType Xpath */
		public static final String OpenSO_CancelHeaderBtn = "//button[@data-testid='Open_So_Cancel_Button']";
		/** @locatorType Xpath */
		public static final String OpenSOBackIcon = "//button[@id='B1760795187445820096']";
		/** @locatorType Xpath */
		public static final String FindOpenSODocDateInput = "//*[@id='P19151310131_DOC_DATE']/input";
		/** @locatorType Xpath */
		public static final String FindOpenSOStatusSelect = "//select[@data-testid='P19151310131_STATUS']";
		/** @locatorType Xpath */
		public static final String FindNoDataMessageContainer = "//div[@class='a-IRR-noDataMsg-icon']";
		/** @locatorType Xpath */
		public static final String OpsoWorkApprovalFrame = "//iframe[@title='Workflow Approval']";
		/** @locatorType Xpath */
		public static final String OpsoOkButtonForward = "//button[@data-testid='Entry']";
		/** @locatorType Xpath */
		public static final String Transactions = "//span[contains(text(),'Transaction')]";
		/** @locatorType Xpath */
		public static final String OpensoScreens = "//a[contains(text(),'Open SO')]";
		/** @locatorType Xpath */
		public static final String OpenSoAmdScreens = "//INPUT[@id='P191513101322_TYPE_1']" ;

		/** @locatorType Xpath */
		public static final String FindStatusTableWithDocNo(String Docno) {
			return "//*[text()='" + Docno + "']/../../td[43]/div";
		}

		/** @locatorType Xpath */
		public static final String FindStatusTableWithText(String Docno) {
			return "//*[text()='" + Docno + "']/../td[43]/div";
		}

		/** @locatorType Xpath */
		public static final String FindCostOfLastDateApprovedOpenSO(String text) {
			return "//*[text()='" + text + "']/../td[14]";
		}

		/** @locatorType Xpath */
		public static final String OpsoOkButtonForwardCat = "//*[text()='Ok']/parent::button";
		/** @locatorType Xpath */
		public static final String OpsoForwardApproval = "//BUTTON[@ID='P236131090_FWD_PERSON_lov_btn']";
		/** @locatorType Xpath */
		public static final String fetchButtonSearchButton = "//input[@id='QC_search_field']" ;
		/** @locatorType Xpath */
		public static final String fetchButtonGoButton = "//button[@id='QC_search_button']" ;
		/** @locatorType Xpath */
		public static final String SelectItem = "//*[@id='7788732405652705160_orig']/tbody/tr[2]/td[9]" ;
		/** @locatorType Xpath */
		public static final String Gobutton = "//button[@id='QC_search_button']" ;
		/** @locatorType Xpath */
		public static final String SearchInputField = "//input[@id='QC_search_field']";
		/** @locatorType Xpath */
		public static final String SelecCheckBox = "//table[@id='8288670618826679601_orig']/tbody/tr[2]/td[9]" ;
		/** @locatorType Xpath */
		public static final String HeaderStatus = "//span[@data-testid='P191513101301_SRCHD_STATUS']" ;
		
	
}
