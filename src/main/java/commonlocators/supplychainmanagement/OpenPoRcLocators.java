package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 01, 2025
 */
public class OpenPoRcLocators {

	/** @locatorType Xpath */
	public static final String OpenPO_RCButton = "//label[@for='P16151310256_TYPE_0']";
	/** @locatorType Xpath */
	public static final String CreateButton = "//button[@id='B1166571066928323489']";
	/** @locatorType Xpath */
	public static final String LocationId = "//button[@id='P1615131025_PRCHD_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String PoTypeSelect = "//select[@id='P1615131025_PRCHD_SERV_TYPE']";
	/** @locatorType Xpath */
	public static final String SupplierButton = "//button[@id='P1615131025_PRCHD_SUPLR_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String TickButton = "//button[@id='B1514471082854326740']";
	/** @locatorType Xpath */
	public static final String OpenPoNo = "//span[@id='P161513102501_PRCHD_PO_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String AddItem = "//button[@id='B3689383519491930672']";
	/** @locatorType Xpath */
	public static final String ItemTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String UnitCost = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String DiscPercentage = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String HsnCodeTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String ItemLovButton = "//button[@id='C3897485352951750384_lov_btn']";
	/** @locatorType Xpath */
	public static final String UnitCostInput = "//input[@id='C3688668859066548163']";
	/** @locatorType Xpath */
	public static final String DiscPercentageInput = "//input[@id='C3688668996105548164']";
	/** @locatorType Xpath */
	public static final String HSNCode = "//button[@id='C4503253906847541381_lov_btn']";
	/** @locatorType Xpath */
	public static final String ItemInput = "//input[@id='C4503253906847541381']";
	/** @locatorType Xpath */
	public static final String ItemTickButton = "//button[@id='B3689383662593930673']";
	/** @locatorType Xpath */
	public static final String HeaderPostButton = "//button[@id='B3685307088265067197']";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@id='B3685401567659106913']";
	/** @locatorType Xpath */
	public static final String AggregateNumber = "//input[@id='P161513102501_PRCHD_AGGR_NO']";
	/** @locatorType Xpath */
	public static final String AggregateDate = "//input[@id='P161513102501_PRCHD_AGGR_DATE_input']";
	/** @locatorType Xpath */
	public static final String EffFrom = "//input[@id='P161513102501_PRCHD_START_DATE_input']";
	/** @locatorType Xpath */
	public static final String EffTo = "//input[@id='P161513102501_PRCHD_END_DATE_input']";
	/** @locatorType Xpath */
	public static final String OpenPoLovButton = "//button[@id='P1615131025_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String OpenPoSearchButton = "//button/span[text()='Search']";
	/** @locatorType Xpath */
	public static final String HeaderCheckBox = "//label[contains(text(),'Header')]";
	/** @locatorType Xpath */
	public static final String LineCancelButton = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[last()]/a/span";
	/** @locatorType Xpath */
	public static final String HeaderCancelButton = "//button[@id='B3685400345924106902']";
	/** @locatorType Xpath */
	public static final String OpenPOTable = "//table[@id='1944270716454143242_orig']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String FindLocationButton = "//button[@id='P1615131025_LOC_NAME_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindUnitButton = "//button[@id='P1615131025_PLNT_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindSupplierButton = "//button[@id='P1615131025_SUPPLIER_NAME_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindItemButton = "//button[@id='P1615131025_ITEM_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindOpenPoNoButton = "//button[@id='P1615131025_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String ClearButton = "//button[@title='Clear']";
	/** @locatorType Xpath */
	public static final String OpenPOAmendmentLabel = "//label[@for='P16151310256_TYPE_1']";
	/** @locatorType Xpath */
	public static final String AmendHeaderCancelButton = "//button[@id='B3723531790612956819']";

	/** @locatorType Xpath */
	public static final String OpenPoStatus(String text) {
		return "//a[text()='" + text + "']/../../td/div[contains(@style,'COLOR')]";
	}

	/** @locatorType Xpath */
	public static final String CreateAmendButton(String text) {
		return "(//td[text()='" + text + "']/../td/a)[1]";
	}

	/** @locatorType Xpath */
	public static final String FindTableData(int i) {
		return "//table[@id='1944270716454143242_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String AmendOpenPoButton = "//button[@id='B1168876399592149567']";
	/** @locatorType Xpath */
	public static final String AmendOpenPOLovButton = "//button[@id='P1615131022_PO_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendTickButton = "//button[@id='B118920413783510414']";
	/** @locatorType Xpath */
	public static final String AmendValueAttributeTickButton = "//button[@id='B3732941663334895073']";
	/** @locatorType Xpath */
	public static final String CreateOpenPOAmendHeader = "//h1[text()='Create Open PO Amendment']";
	/** @locatorType Xpath */
	public static final String AmendReasonButton = "//button[@id='P1615131023_REASON_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendOkButton = "//button[@id='B4767732010489050160']";
	/** @locatorType Xpath */
	public static final String AmendCreateFrame = "//iframe[@title='Create Open PO Amendment']";
	/** @locatorType Xpath */
	public static final String AmendCreateItemButton = "//button[@id='B3724902384713536803']";
	/** @locatorType Xpath */
	public static final String AmendItemTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String AmendItemLovButton = "//button[@id='C4119666595864396875_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendNewUnitCostTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @locatorType Xpath */
	public static final String AmendHSNCodeTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[11]";
	/** @locatorType Xpath */
	public static final String AmendDiscTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String AmendNewUnitCostInput = "//input[@id='C3723637487887013493']";
	/** @locatorType Xpath */
	public static final String AmendDiscInput = "//input[@id='C3723637544868013494']";
	/** @locatorType Xpath */
	public static final String HsnCodeLovButton = "//button[@id='C3723638945000013508_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendAddButton = "//button[@id='B3724902384713536803']";
	/** @locatorType Xpath */
	public static final String AmendHeaderSaveButton = "//button[@id='B3723533007196956828']";
	/** @locatorType Xpath */
	public static final String AmendHeaderPostButton = "//button[@id='B3733654262305480093']";
	/** @locatorType Xpath */
	public static final String AmendOPONo = "//span[@id='P161513102101_PRCAHD_DOC_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String AmendItemSaveButton = "//button[@id='B3724902505005536804']";
	/** @locatorType Xpath */
	public static final String AmendOOPANoLovButton = "//button[@id='P1615131021_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendSearchButton = "//button[@id='B1168874725680149564']";
	/** @locatorType Xpath */
	public static final String AmendAggrCalendarDate = "//td[@class='is-current']";
	/** @locatorType Xpath */
	public static final String AmendFetchOpenPOLine = "//button[@id='B3723635725960013476']";
	/** @locatorType Xpath */
	public static final String AmendLineDetailsHeader = "//h1[text()='Line Details']";
	/** @locatorType Xpath */
	public static final String AmendLineDetailsIframe = "//iframe[@title='Line Details']";
	/** @locatorType Xpath */
	public static final String AmendInputCheckBox = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String AmendLineOkButton = "//button[@id='B3733117207589285187']";

	/** @locatorType Xpath */
	public static final String AmendOpenPoStatus(String text) {
		return "(//td/a[text()='" + text + "']/../../td/div[contains(@style,'COLOR')])[last()]";
	}

	/** @locatorType Xpath */
	public static final String AmendType = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String AmendTypeSelect = "//select[@id='C3723636653983013485']";
	/** @locatorType Xpath */
	public static final String TermsAndCondition = "//label[@for='P161513102501_TAB_LINES_1']";
	/** @locatorType Xpath */
	public static final String AddTCButton = "//button[@id='B3698313715327536679']";
	/** @locatorType Xpath */
	public static final String TCAttribute = "//div[@id='ig_line2_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String AttributeLovButton = "//button[@id='C3698312097175536662_lov_btn']";
	/** @locatorType Xpath */
	public static final String TermConditionTickIcon = "//button[@id='B3698313838442536680']";
	/** @locatorType Xpath */
	public static final String ValueAddButton = "//button[@id='B3698635746440708767']/span";
	/** @locatorType Xpath */
	public static final String ValueAttributeTable = "//div[@id='ig_line3_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String AttributeValueInput = "//input[@id='C3698316120819536703']";
	/** @locatorType Xpath */
	public static final String AmendAttributeValueInput = "//input[@id='C3732573347235008493']";
	/** @locatorType Xpath */
	public static final String ValueTickButton = "//button[@id='B3698635831950708768']";
	/** @locatorType Xpath */
	public static final String AmendTermsAndCondition = "//label[@for='P161513102101_TAB_LINE_1']";
	/** @locatorType Xpath */
	public static final String AmendDeleteValue = "//div[@id='ig_line3_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]/a";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String AmendValueAddButton = "//button[@id='B3732941558858895072']/span";
	/** @locatorType Xpath */
	public static final String AmendValueTickButton = "//button[@id='B3732941663334895073']";
	/** @locatorType Xpath */
	public static final String AmendEffTo = "//input[@id='P161513102101_PRCAHD_TO_DATE_input']";
	/** @locatorType Xpath */
	public static final String OpenPoTC = "//label[@for='P1615131020_TAB_LINES_1']";
	/** @locatorType Xpath */
	public static final String AmendHeaderCancel = "//button[@id='B3723531790612956819']";
	/** @locatorType Xpath */
	public static final String AmendEffFrom = "//input[@id='P161513102101_PRCAHD_FROM_DATE_input']";
	/** @locatorType Xpath */
	public static final String AmendAggrNumber = "//input[@id='P161513102101_PRCAHD_AGGR_NO']";
	/** @locatorType Xpath */
	public static final String AmendAggrDate = "//input[@id='P161513102101_PRCAHD_AGGR_DATE_input']";
	/** @locatorType Xpath */
	public static final String AggrCalendarDate = "//td[@class='is-current']";
	/** @locatorType Xpath */
	public static final String AmendLocationId = "//button[@id='P1615131021_LOC_NAME_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendUnitId = "//button[@id='P1615131021_PLNT_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendOOAPNo = "//button[@id='P1615131021_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendOpenPONo = "//button[@id='P1615131021_PO_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendItemButton = "//button[@id='P1615131021_PROD_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String AmendSelectStatus = "//select[@id='P1615131021_STATUS']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//table[@id='1950966915442829331_orig']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//table[@id='387662055082151248_orig']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String LineCheckBox = "//label[text()='Line(s)']";

	/** @locatorType Xpath */
	public static final String OpenPONumber(String Number) {
		return "(//a[text()='" + Number + "'])[1]";
	}

	/** @locatorType Xpath */
	public static final String AmendFindTableData(int i) {
		return "//table[@id='1950966915442829331_orig']/tbody/tr/td[" + i + "]";
	}
}
