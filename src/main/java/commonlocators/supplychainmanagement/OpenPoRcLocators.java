package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 01, 2025
 */
public class OpenPoRcLocators {

	/** @locatorType Xpath */
	public static final String OpenPO_RCButton = "(//input[@data-testid='P16151310256_TYPE']/following-sibling::label)[1]";
	/** @locatorType Xpath */
	public static final String CreateButton = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String LocationId = "//input[@data-testid='P1615131025_PRCHD_PLNT_LOC_ID']/following-sibling::button[@id='P16151310254_PRCHD_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String PoTypeSelect = "//select[@data-testid='P1615131025_PRCHD_SERV_TYPE']";
	/** @locatorType Xpath */
	public static final String SupplierButton = "//input[@data-testid='P1615131025_PRCHD_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String TickButton = "//button[@data-testid='Create']";
	/** @locatorType Xpath */
	public static final String OpenPoNo = "//span[@data-testid='P161513102501_PRCHD_PO_NO']";
	/** @locatorType Xpath */
	public static final String AddItem = "//button[@data-testid='Add']";

	/** @locatorType Xpath */
	public static final String AddScreenHeaderLocators = "(//table[@data-testid='ig_line_ig_report_settings_Result_Data'])[2]";
	/** @locatorType Xpath */
	public static final String ItemTable = "(//table[@data-testid='ig_line_ig_report_settings_Result_Data'])[2]/tbody/tr/td[count((//table[@data-testid='ig_line_ig_report_settings_Result_Data'])[2]/thead/tr[1]/th[normalize-space()='Item']/preceding-sibling::th) + 1]";

	/** @locatorType Xpath */
	public static final String UnitCost() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddScreenHeaderLocators, "UnitCost");
	}

	/** @locatorType Xpath */
	public static final String DiscPercentage() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddScreenHeaderLocators, "Disc%");
	}

	/** @locatorType Xpath */
	public static final String HsnCodeTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddScreenHeaderLocators, "HsnSacCode");
	}

	/** @locatorType Xpath */
	public static final String ItemLovButton = "//input[@data-testid='DUM_PROD']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String UnitCostInput = "//input[@data-testid='PRCLN_PRICE']";
	/** @locatorType Xpath */
	public static final String DiscPercentageInput = "//input[@data-testid='PRCLN_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String HSNCode = "//input[@data-testid='PRCLN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemInput = "//input[@id='C4503253906847541381']";
	/** @locatorType Xpath */
	public static final String ItemTickButton = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String HeaderPostButton = "//button[@data-testid='Entry_Complete']";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@data-testid='Update']";
	/** @locatorType Xpath */
	public static final String AggregateNumber = "//input[@data-testid='P161513102501_PRCHD_AGGR_NO']";
	/** @locatorType Xpath */
	public static final String AggregateDate = "//*[@data-testid='P161513102501_PRCHD_AGGR_DATE']/input";
	/** @locatorType Xpath */
	public static final String EffFrom = "//*[@data-testid='P161513102501_PRCHD_START_DATE']/input";
	/** @locatorType Xpath */
	public static final String EffTo = "//*[@data-testid='P161513102501_PRCHD_END_DATE']/input";
	/** @locatorType Xpath */
	public static final String OpenPoLovButton = "//input[@data-testid='P1615131025_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String OpenPoSearchButton = "//button/span[text()='Search']";
	/** @locatorType Xpath */
	public static final String HeaderCheckBox = "//label[contains(text(),'Header')]";
	/** @locatorType Xpath */
	public static final String LineCancelButton = "(//table[@data-testid='ig_line_ig_report_settings_Result_Data'])[2]/tbody/tr[1]/td[last()]/a";
	/** @locatorType Xpath */
	public static final String HeaderCancelButton = "//span[text()='Cancel']/..";
	/** @locatorType Xpath */
	public static final String OpenPOTable = "//table[@data-testid='L_Result_Data']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String FindLocationButton = "//input[@data-testid='P1615131025_LOC_NAME']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindUnitButton = "//input[@data-testid='P1615131025_PLNT']/following::button";
	/** @locatorType Xpath */
	public static final String FindSupplierButton = "//input[@data-testid='P1615131025_SUPPLIER_NAME']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindItemButton = "//input[@data-testid='P1615131025_ITEM']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindOpenPoNoButton = "//input[@data-testid='P1615131025_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ClearButton = "//button[@title='Clear']";
	/** @locatorType Xpath */
	public static final String OpenPOAmendmentLabel = "(//input[@data-testid='P16151310256_TYPE']/following-sibling::label)[2]";
	/** @locatorType Xpath */
	public static final String AmendHeaderCancelButton = "//button[@data-testid='CANCEL']";

	/** @locatorType Xpath */
	public static final String OpenPoStatus(String text) {
		return "//a[text()='" + text + "']/../../td/div[contains(@style,'COLOR')]";
	}

	/** @locatorType Xpath */
	public static final String CreateAmendButton(String text) {
		return "(//td[text()='" + text + "']/../td/a)[1]";
	}

	/** @locatorType Xpath */
	public static final String FindTableData(String header) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(AddScreenOpenPoValue, header);
	}

	/** @locatorType Xpath */
	public static final String AddScreenOpenPoValue = "//table[@data-testid='L_Result_Title']/tbody/tr/th";
	/** @locatorType Xpath */
	public static final String AmendOpenPoButton = "//button[@data-testid='Amend']";
	/** @locatorType Xpath */
	public static final String AmendOpenPOLovButton = "//input[@data-testid='P1615131022_PO_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendTickButton = "//button[@data-testid='Result']";
	/** @locatorType Xpath */
	public static final String AmendValueAttributeTickButton = "//button[@data-testid='Save_Value']";
	/** @locatorType Xpath */
	public static final String CreateOpenPOAmendHeader = "//h1[text()='Create Open PO Amendment']";
	/** @locatorType Xpath */
	public static final String AmendReasonButton = "//input[@data-testid='P1615131023_REASON']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendOkButton = "//button[@data-testid='Ok']";
	/** @locatorType Xpath */
	public static final String AmendCreateFrame = "//iframe[@title='Create Open PO Amendment']";
	/** @locatorType Xpath */
	public static final String AmendCreateItemButton = "//button[@data-testid='Add_Amd']";
	/** @locatorType Xpath */
	public static final String AmendTable = "(//table[@data-testid='ig_line_ig_report_settings_Result_Data'])[2]";

//	"OOAP/2309/26-27"
	/** @locatorType Xpath */
	public static final String AmendItemTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AmendTable, "Item");
	}

	/** @locatorType Xpath */
	public static final String AmendItemLovButton = "//input[@data-testid='PROD_REV']/following-sibling::button";

	/** @locatorType Xpath */
	public static String AmendNewUnitCostTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AmendTable, "NewUnitCost");
	}

	/** @locatorType Xpath */
	public static final String AmendHSNCodeTable = CommonFunctions
			.checkAndReturnIndexForLocatorWithHeaderValue(AmendTable, "HSN/SAC Code");

	/** @locatorType Xpath */
	public static final String AmendDiscTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AmendTable, "Disc%");
	}

	/** @locatorType Xpath */
	public static final String AmendNewUnitCostInput = "//input[@data-testid='PRCALN_NEW_PRICE']";
	/** @locatorType Xpath */
	public static final String AmendDiscInput = "//input[@data-testid='PRCALN_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String HsnCodeLovButton = "//input[@data-testid='PRCALN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendAddButton = "//button[@data-testid='Add_Amd']";
	/** @locatorType Xpath */
	public static final String AmendHeaderSaveButton = "//button[@data-testid='Update']";
	/** @locatorType Xpath */
	public static final String AmendHeaderPostButton = "//button[@data-testid='Entry_Complete']";
	/** @locatorType Xpath */
	public static final String AmendOPONo = "//span[@data-testid='P161513102101_PRCAHD_DOC_NO']";
	/** @locatorType Xpath */
	public static final String AmendItemSaveButton = "//button[@data-testid='Save_Amd']";
	/** @locatorType Xpath */
	public static final String AmendOOPANoLovButton = "//input[@data-testid='P1615131021_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendSearchButton = "//button[@data-testid='Find_line']";
	/** @locatorType Xpath */
	public static final String AmendAggrCalendarDate = "//td[@class='is-current']";
	/** @locatorType Xpath */
	public static final String AmendFetchOpenPOLine = "//button[@data-testid='Fetch_PO_Line']";
	/** @locatorType Xpath */
	public static final String AmendLineDetailsHeader = "//h1[text()='Line Details']";
	/** @locatorType Xpath */
	public static final String AmendLineDetailsIframe = "//iframe[@title='Line Details']";
	/** @locatorType Xpath */
	public static final String AmendInputCheckBox = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String AmendLineOkButton = "//button[@data-testid='Ok']";

	/** @locatorType Xpath */
	public static final String AmendOpenPoStatus(String text) {
		return "(//td/a[text()='" + text + "']/../../td/div[contains(@style,'COLOR')])[last()]";
	}

	/** @locatorType Xpath */
	public static final String AmendType() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AmendTable, "AmendType");
	}

	/** @locatorType Xpath */
	public static final String AmendTypeSelect = "//select[@data-testid='PRCALN_AMD_TYPE']";
	/** @locatorType Xpath */
	public static final String TermsAndCondition = "//input[@data-testid='P161513102501_TAB_LINES' and @aria-label='T & C']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String AddTCButton = "//button[@data-testid='Add_Attribute']";
	/** @locatorType Xpath */
	public static final String TCAttribute = "//div[@id='ig_line2_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String AttributeLovButton = "//input[@data-testid='OHTA_ATTR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String TermConditionTickIcon = "//button[@data-testid='Save_Attribute']";
	/** @locatorType Xpath */
	public static final String ValueAddButton = "//button[@data-testid='Add_Value']";
	/** @locatorType Xpath */
	public static final String ValueAttributeTable = "//div[@id='ig_line3_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String AttributeValueInput = "//input[@data-testid='OHTAV_ATTR_VAL']";
	/** @locatorType Xpath */
	public static final String AmendAttributeValueInput = "//input[@data-testid='OAHTAV_ATTR_VAL']";
	/** @locatorType Xpath */
	public static final String ValueTickButton = "//button[@data-testid='Save_Value']";
	/** @locatorType Xpath */
	public static final String AmendTermsAndCondition = "(//input[@data-testid='P161513102101_TAB_LINE']/following-sibling::label)[2]";
	/** @locatorType Xpath */
	public static final String AmendDeleteValue = "//div[@id='ig_line3_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]/a";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String AmendValueAddButton = "//button[@data-testid='Add_Value']";
	/** @locatorType Xpath */
	public static final String AmendValueTickButton = "//button[@data-testid='Save_Value']";
	/** @locatorType Xpath */
	public static final String AmendEffTo = "//*[@data-testid='P161513102101_PRCAHD_TO_DATE']/input";
	/** @locatorType Xpath */
	public static final String OpenPoTC = "//label[@for='P1615131020_TAB_LINES_1']";
	/** @locatorType Xpath */
	public static final String AmendHeaderCancel = "//button[@data-testid='CANCEL']";
	/** @locatorType Xpath */
	public static final String AmendEffFrom = "//*[@data-testid='P161513102101_PRCAHD_FROM_DATE']/input";
	/** @locatorType Xpath */
	public static final String AmendAggrNumber = "//input[@data-testid='P161513102101_PRCAHD_AGGR_NO']";
	/** @locatorType Xpath */
	public static final String AmendAggrDate = "//*[@data-testid='P161513102101_PRCAHD_AGGR_DATE']/input";
	/** @locatorType Xpath */
	public static final String AggrCalendarDate = "//td[@class='is-current']";
	/** @locatorType Xpath */
	public static final String AmendLocationId = "//input[@data-testid='P1615131021_LOC_NAME']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendUnitId = "//input[@data-testid='P1615131021_PLNT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendOOAPNo = "//input[@data-testid='P1615131021_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendOpenPONo = "//input[@data-testid='P1615131021_PO_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendItemButton = "//input[@data-testid='P1615131021_PROD_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AmendSelectStatus = "//select[@data-testid='P1615131021_STATUS']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//table[@data-testid='L_Result_Data']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String LineCheckBox = "//label[contains(text(),'Line')]";

	/** @locatorType Xpath */
	public static final String OpenPONumber(String Number) {
		return "(//a[text()='" + Number + "'])[1]";
	}

	/** @locatorType Xpath */
	public static final String AmendFindTableScreen = "//table[@data-testid='L_Result_Data']/tbody/tr[1]/th";
	/** @locatorType Xpath */
	public static final String AmendFindLineTableScreen = "//table[@data-testid='H_Result_Data']/tbody/tr[1]/th/tbody/tr[1]/th";

	/** @locatorType Xpath */
	public static final String AmendFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(AmendFindTableScreen, i);
	}

	/** @locatorType Xpath */
	public static final String AmendFindLineTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(AmendFindLineTableScreen, i);
	}

	/** @locatorType Xpath */
	public static final String CreateOpenPoFrame = "//iframe[contains(@title,'Create')]";
}
