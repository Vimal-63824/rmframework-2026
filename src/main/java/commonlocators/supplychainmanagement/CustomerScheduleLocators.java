package commonlocators.supplychainmanagement;

/**
 * @author Harikrishna -RMITS
 * @company RoadMap It Solutions May 05, 2025
 */
public class CustomerScheduleLocators {

	/** @Locator Xpath */
	public static final String CatalogFindButton = "//button[@data-testid='Search']";
	/** @Locator Xpath */
	public static final String CustomerScheduleNode = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[4]";
	/** @Locator Xpath */
	public static final String CSFrame = "//iframe[contains(@title,'Create')]";
	/** @Locator Xpath */
	public static final String CSButton = "//span[text()='Customer Schedule']";
	/** @Locator Xpath */
	public static final String CSAddButton = "//BUTTON[@data-testid='CS_AddButton']";
	/** @Locator Xpath */
	public static final String CSLocationLov = "//input[@data-testid='CSLocationLov']/following-sibling::button";
	/** @Locator Xpath */
	public static final String CSBatchPrefixLov = "//input[@data-testid='CSBatchPrefixLov']/following-sibling::button";
	/** @Locator Xpath */
	public static final String CSNarrationField = "//input[@data-testid='P191513102011_COHD_NARRATION']";
	/** @Locator Xpath */
	public static final String CSsaveButton = "//BUTTON[@data-testid='CS_SaveButton']";
	/** @Locator Xpath */
	public static final String CSHeaderWiseSavebutton = "//BUTTON[@data-testid='CS_HeaderWise_SaveButton']";
	/** @Locator Xpath */
	public static final String CSPostButton = "//BUTTON[@data-testid='CS_HeaderWise_PostButton']";
	/** @Locator Xpath */
	public static final String CSCancelButton = "//BUTTON[@data-testid='CS_HeaderWise_CancelButton']";
	/** @Locator Xpath */
	public static final String CSWfHistButton = "//BUTTON[@data-testid='CS_HEaderWiseWFHIst_Button']";
	/** @Locator Xpath */
	public static final String CSGenSchButton = "//BUTTON[@data-testid='CS_HeaderWiseGenSchButton']";
	/** @Locator Xpath */
	public static final String CSLineWiseAddButton = "//BUTTON[@data-testid='CS_LINE_WISE_ADD_BUTTON']";
	/** @Locator Xpath */
	public static final String CSLineWiseCustLov = "//input[@data-testid='COLN_CUST_ID']/following-sibling::button";
	/** @Locator Xpath */
	public static final String CSLineWiseCustField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String CSLineWiseItemField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[5]";
	/** @Locator Xpath */
	public static final String CSLineWiseItemLov = "//input[@data-testid='CSLineWiseItemField']/following-sibling::button";
	/** @Locator Xpath */
	public static final String CSLineWiseFirmQtyField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[10]";
	/** @Locator Xpath */
	public static final String CSLineWiseFirmQtyInputField = "//input[@data-testid='CSLineWiseFirmQtyField']";
	/** @Locator Xpath */
	public static final String CSLineWiseUnitPriceField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[12]";
	/** @Locator Xpath */
	public static final String CSLineWiseUnitPriceInputField = "//input[@data-testid='CSLineWiseUnitPriceField']";
	/** @Locator Xpath */
	public static final String CSLineWiseMRPField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @Locator Xpath */
	public static final String CSLineWiseMRPInputField = "//input[@data-testid='CSLineWiseMRPField']";
	/** @Locator Xpath */
	public static final String CSLineWiseDiscField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @Locator Xpath */
	public static final String CSLineWiseDiscInputField = "//input[@data-testid='CSLineWiseDiscField']";
	/** @Locator Xpath */
	public static final String CSLineWiseSaveButton = "//BUTTON[@data-testid='CS_LINE_WISE _SAVE _BUTTON']";
	/** @Locator Xpath */
	public static final String CSDocNumberField = "//span[@data-testid='P19151310201_COHD_BATCH_NO']";
	/** @Locator Xpath */
	public static final String CSLineWiseEndDateField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[18]";
	/** @Locator Xpath */
	public static final String CSLineWiseEndDateInputField = "//*[@data-testid='COLN_SCHD_FRM_END_DATE']/input";
	/** @Locator Xpath */
	public static final String CSLineWiseGenerateButton1 = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[1]";
	/** @Locator CSS Selector */
	public static final String CSLineWiseGenerateButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td.a-GV-cell.u-tC.is-readonly.a-GV-frozen.a-GV-frozen--start1 > a";
	/** @Locator Xpath */
	public static final String CSHsnCodeField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[27]";
	/** @Locator Xpath */
	public static final String CSHsnCodeLov = "//input[@data-testid='COLN_HSN_CODE']/following-sibling::button";
	/** @Locator Xpath */
	public static final String CSHsnCodeInputField = "//input[@data-testid='COLN_HSN_CODE']";
	/** @Locator Xpath */
	public static final String CSBucketTypeField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[19]";
	/** @Locator Xpath */
	public static final String CSBucketTypeDropDown = "//select[@data-testid='CSBucketTypeField']";
	/** @Locator Xpath */
	public static final String CSScheduleTypeField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @Locator Xpath */
	public static final String CSScheduleDropDown = "//select[@data-testid='COLN_FIRM_TYPE']";
	/** @Locator Xpath */
	public static final String CIDropDownType = "//select[@id='C6072444262869065829']";
	/** @Locator Xpath */
	public static final String CITypeField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[1]";
	/** @Locator Xpath */
	public static final String CSAmdAddButton = "//button[@data-testid='CSAmdAddButton']";
	/** @Locator Xpath */
	public static final String CSAmdLocationLov = "//INPUT[@data-testid='P191513102023_CAMH_PLNT_LOC_ID']/following-sibling::button";
	/** @Locator Xpath */
	public static final String CSAmdDocPfx = "//INPUT[@data-testid='P191513102023_CAMH_DOC_PFX']/following-sibling::button";
	/** @Locator Xpath */
	public static final String CSAmdCustomerId = "//INPUT[@data-testid='P191513102023_CAMH_CUST_ID']/following-sibling::button";
	/** @Locator Xpath */
	public static final String CSAmdHeaderWiseSaveButton = "//button[@title='Create']";
	/** @Locator Xpath */
	public static final String CSAmdFetchButton = "//button[@data-testid='CSAmdFetchButton']";
	/** @Locator Xpath */
	public static final String CSAmdCancelAlert = "//h6[@class='succ_msg']";
	/** @Locator Xpath */
	public static final String CSAmdPostButton = "//button[@data-testid='CSAmdPostButton']";
	/** @Locator Xpath */
	public static final String CSAmdCancelButton = "//button[@data-testid='CSAmdCancelButton']";
	/** @Locator Xpath */
	public static final String CSAmdFetchOkButton = "//button[@data-testid='Ok']";
	/** @Locator Xpath */
	public static final String CSAmdFetchGOButton = "//button[@id='CSA_search_button']";
	/** @Locator Xpath */
	public static final String CSAmdFetchSearchField = "//input[@id='CSA_search_field']";
	/** @Locator Xpath */
	public static final String CSAmdFetchFrame = "//IFRAME[@TITLE='Fetch']";
	/** @Locator Xpath */
	public static final String CSAmdFetchSelectAllCheckBox = "(//input[@id='check_all'])[1]";
	/** @Locator Xpath */
	public static final String CSAmdLineRow = "//TR[@DATA-ID='AAATk/AAHAADqecAAD']";
	/** @Locator Xpath */
	public static final String CSAmdTypeDropDown = "//select[@data-testid='CSAmdTypeField']";
	/** @Locator Xpath */
	public static final String CSAmdSchTypeDropDown = "//select[@data-testid='CSAmdSchTypeField']";
	/** @Locator Xpath */
	public static final String CSAmdLineWiseSaveButton = "//button[@data-testid='CSAmdLineWiseSaveButton']";
	/** @Locator Xpath */
	public static final String CSAmdTypeField = "//*[@id='cs_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @Locator Xpath */
	public static final String CSAmdSchTypeField = "//*[@id='cs_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @Locator Xpath */
	public static final String CSAmdDocNumberField = "//input[@data-testid='P191513102023_CAMH_DOC_NO']";
	/** @Locator Xpath */
	public static final String CSAmdQtyField = "//*[@id='cs_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[8]";
	/** @Locator Xpath */
	public static final String CSAmdQtyInputField = "//input[@data-testid='CAML_FRM_QTY']";
	/** @Locator Xpath */
	public static final String CSAmdPriceField = "//*[@id='cs_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @Locator Xpath */
	public static final String CSAmdTentativeQtyField = "//*[@id='cs_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]";
	/** @Locator Xpath */
	public static final String CSAmdPriceInputField = "//input[@data-testid='CAML_PRICE1']";
	/** @Locator Xpath */
	public static final String CSAmdTentativeQtyInputField = "//input[@data-testid='CSAmdTentativeQtyInputField']";
	/** @Locator Xpath */
	public static final String CSAmdTentativeDateInputField = "//*[@data-testid='CAML_SCHD_END_DATE']/input";
	/** @Locator Xpath */
	public static final String CSAmdTentaiveDateField = "//*[@id='cs_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[19]";
	/** @Locator Xpath */
	public static final String CSTemplateUploadButton = "//button[@data-testid='Upload_hd']";
	/** @Locator Xpath */
	public static final String CSFileInputField = "//input[@id='P19151310201_BRN_BROWSE_input']";
	/** @Locator Xpath */
	public static final String CSUploadFileText = "//div[contains(@id,'P19151310201_BRN_BROWSE_DROPZONE')]";
	/** @Locator Xpath */
	public static final String CSUploadOkButton = "//button[@data-testid='Upload']";
	/** @Locator Xpath */
	public static final String CSHeaderLocationField = "//input[@data-testid='P19151310201_COHD_PLNT_LOC_ID']";
	/** @Locator Xpath */
	public static final String CSHeaderUnitField = "//input[@data-testid='P19151310201_COHD_PLNT']";
	/** @Locator Xpath */
	public static final String CSHeaderBatchPrefix = "//span[@data-testid='P19151310201_COHD_BATCH_PFX']";
	/** @Locator Xpath */
	public static final String CSHeaderBatchNumberField = "//span[@data-testid='P19151310201_COHD_BATCH_NO']";
	/** @Locator Xpath */
	public static final String CSHeaderDocStatusField = "//span[@data-testid='P19151310201_COHD_STATUS']";
	/** @Locator Xpath */
	public static final String CSHeaderNarrationField = "//input[@data-testid='P19151310201_COHD_NARRATION']";
	/** @Locator Xpath */
	public static final String CSLineWiseDocNumberField = "//input[@data-testid='COLN_DOC_NO']";
	/** @Locator Xpath */
	public static final String CSDocLOVInFindScreen = "//button[@id='P191513102011_BATCH_NO_lov_btn']";
	/** @Locator Xpath */
	public static final String CSFindScreenSearchButton = "//button[@data-testid='CS_SearchButton']";
	/** @Locator Xpath */
	public static final String CSFindScreenHeaderradioButton = "//label[@for='P191513102011_HD_LN_0']";
	/** @Locator Xpath */
	public static final String CSAddFrameName = "//h1[@id='ui-id-1']";

	public static final String CSLocationField = "//input[@data-testid='CSLocationLov']";

	/** @Locator Xpath */
	public static final String CSStatusHeaderTableText(String CSno) {
		return "//a[text()='" + CSno + "']/../../td[16]";
	}

	/** @locatorType Xpath */
	public static final String CSFetchSelectCheckBox(String CSNumber) {
		return "//td[contains(text(),'" + CSNumber + "')]/../td[17]/*";
	}

	/** @locatorType Xpath */
	public static final String CSLineDocNumberfield(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[33]";
	}
	
	/** @locatorType Xpath */
	public static final String CustomerScdhlLineDetailsTable = "(//table[@data-testid='ig_line_Result_Data'])[2]";
	
	/** @locatorType Xpath */
	public static final String CustomerField() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "Customer ID");
	}
	
	/** @locatorType Xpath */
	public static final String AddItemField() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "Item ID");
	}
	
	/** @locatorType Xpath */
	public static final String CustomerScdhlType() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "Schd. Type");
	}
	
	public static String checkAndReturnIndexForCSLinesLocatorWithHeaderValue(String Locator, String HeaderValue) {
		Locator = Locator + "/tbody/tr/*[count(" + Locator + "/thead/tr[2]/th[normalize-space()='" + HeaderValue
				+ "']/preceding-sibling::th) + 1]";
		return Locator;
	}
	
	/** @locatorType Xpath */
	public static final String FirmQtyLines() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "Firm Qty.");
	}
	
	/** @locatorType Xpath */
	public static final String UnitPriceLines() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "Price");
	}
	
	/** @locatorType Xpath */
	public static final String MrpPriceLines() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "MRP Price");
	}
	
	/** @locatorType Xpath */
	public static final String DiscFieldLines() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "Disc. %");
	}
	
	/** @locatorType Xpath */
	public static final String CsEndDate() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "End Date");
	}
	
	/** @locatorType Xpath */
	public static final String HsnCode() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlLineDetailsTable, "HSN Code");
	}
	
	/** @locatorType Xpath */
	public static final String CSamdButton = "//span[contains(text(),'Customer Schedule Amd./Close')]";

	
	/** @locatorType Xpath */
	public static final String CustomerScdhlAmdLineDetailsTable = "(//table[@data-testid='cs_line_Result_Data'])[2]";
	/** @locatorType Xpath */
	public static final String FirmQtyCsa() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlAmdLineDetailsTable, "Firm Qty.");
	}
	/** @locatorType Xpath */
	public static final String FirmTypeInCsa() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlAmdLineDetailsTable, "Schld. Type");
	}
	/** @locatorType Xpath */
	public static final String CsaTentiveQtyField() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlAmdLineDetailsTable, "Tent. Qty");
	}
	
	/** @locatorType Xpath */
	public static final String CsaPriceField() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlAmdLineDetailsTable, "Price");
	}
	
	/** @locatorType Xpath */
	public static final String CsaTentiveEndDate() {
		return checkAndReturnIndexForCSLinesLocatorWithHeaderValue(CustomerScdhlAmdLineDetailsTable, "End Date");
	}

}
