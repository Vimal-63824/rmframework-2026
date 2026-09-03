package commonlocators.productionplanning;

import commonfunctions.CommonFunctions;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 19, 2025
 */
public class MRPLocators {

	/** @locatorType XPATH */
	public static final String FindLinesBtn = "//button[@title='Find Line']";
	/** @locatorType XPATH */
	public static final String FindMRPNoLovBtn = "//input[@data-testid='FindMRPNoLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindSearchBtn = "//button[@data-testid='FindSearchBtn']";

	/** @locatorType XPATH */
	public static final String Find_Status_TableData(String data) {
		return "//td/a[text()='" + data + "']/../../td/div";
	}

	/** @locatorType XPATH */
	public static final String Find_MRPNo_TableData(String data) {
		return "//td/a[text()='" + data + "']";
	}

	/** @locatorType XPATH */
	public static final String MRP_AddNewBtn = "//button[@data-testid='MRP_AddNewBtn']";
	/** @locatorType XPATH */
	public static final String MRP_CreateMRPIframe = "//iframe[@title='Create MRP']";
	/** @locatorType XPATH */
	public static final String MRP_AddMRPDateInput = "//*[@data-testid='MRP_AddMRPDateInput']/input";
	/** @locatorType XPATH */
	public static final String MRP_AddMRPNameInput = "//input[@data-testid='MRP_AddMRPNameInput']";
	/** @locatorType XPATH */
	public static final String MRP_AddSafetyStkDateInput = "//*[@data-testid='MRP_AddSafetyStkDateInput']/input";
	/** @locatorType XPATH */
	public static final String MRP_AddSupplyExceptionSelect = "//select[@data-testid='MRP_AddSupplyExceptionSelect']";
	/** @locatorType XPATH */
	public static final String MRP_TickIcon = "//button[@data-testid='MRP_TickIcon']";
	/** @locatorType XPATH */
	public static final String MRP_MRPNumberDisplayInput = "//input[@data-testid='MRP_MRPNumberDisplayInput']";
	/** @locatorType XPATH */
	public static final String MRP_StatusDisplayInput = "//span[@data-testid='MRP_StatusDisplayInput']";
	/** @locatorType XPATH */
	public static final String MRP_SubTab_Sources = "//label[text()='Sources']";
	/** @locatorType XPATH */
	public static final String MRP_SubTab_Demand = "//label[text()='Demand']";
	/** @locatorType XPATH */
	public static final String MRP_SubTab_Supply = "//label[text()='Supply']";
	/** @locatorType XPATH */
	public static final String MRP_SubTab_BomAndSob = "//label[text()='BOM & SOB']";
	/** @locatorType XPATH */
	public static final String MRP_SubTab_Exceptions = "//label[text()='Exceptions']";
	/** @locatorType XPATH */
	public static final String MRP_SubTab_Results = "//label[text()='Results']";
	/** @locatorType XPATH */
	public static final String MRP_SubTab_CRP = "//label[text()='CRP']";
	/** @locatorType XPATH */
	public static final String MRP_SubTab_Reports = "//label[text()='Reports']";
	/** @locatorType XPATH */
	public static final String MRP_CheckExceptionLabel = "//label[@id='P131816101001_MRPHD_EXCEP_FLAG_LABEL']";
	/** @locatorType XPATH */
	public static final String MRP_AddNewLineSourcesBtn = "//button[@data-testid='MRP_AddNewLineSourcesBtn']";
	/** @locatorType XPATH */
	public static final String MRP_SaveLineSourcesBtn = "//button[@data-testid='MRP_SaveLineSourcesBtn']";
	/** @locatorType XPATH */
	public static final String MRP_SourceTable = "(//table[@data-testid='ig_unit_Result_Data'])[2]";

	/** @locatorType XPATH */
	public static final String MRP_SourceTableData_Unit() {
		return checkAndReturnIndexForSourceLocatorWithHeaderValue(MRP_SourceTable, "Unit");
	}

	/** @locatorType XPATH */
	public static final String MRP_Source_UnitLovBtn() {
		return checkAndReturnIndexForSourceLocatorWithHeaderValue(MRP_SourceTable, "Unit") + "//button";
	}

	/** @locatorType XPATH */
	public static final String MRP_SourceTableData_CutOffDate() {
		return checkAndReturnIndexForSourceLocatorWithHeaderValue(MRP_SourceTable, "Cutoff Date");
	}

	/** @locatorType XPATH */
	public static final String MRP_Source_CutOffDateInput() {
		return checkAndReturnIndexForSourceLocatorWithHeaderValue(MRP_SourceTable, "Cutoff Date") + "//input";
	}

	/** @locatorType XPATH */
	public static final String MRP_Source_SalesOrderTableData() {
		return checkAndReturnIndexForSourceLocatorWithHeaderValue(MRP_SourceTable, "Sales Order");
	}

	/** @locatorType XPATH */
	public static final String MRP_Source_SalesOrderCheckbox = "//div[@class='apex-item-single-checkbox']/input[@aria-label='Sales Order']";

	/** @locatorType XPATH */
	public static final String MRP_LoadDemandBtn = "//button[@data-testid='MRP_LoadDemandBtn']";
	/** @locatorType XPATH */
	public static final String MRP_RunMRPBtn = "//button[@data-testid='MRP_RunMRPBtn']";
	/** @locatorType XPATH */
	public static final String MRP_HeaderSaveBtn = "//button[@data-testid='MRP_HeaderSaveBtn']";
	/** @locatorType XPATH */
	public static final String MRP_PostBtn = "//button[@data-testid='MRP_PostBtn']";
	/** @locatorType XPATH */
	public static final String MRP_DemandSearchBar = "//input[@id='ContainerSummary_search_field']";
	/** @locatorType XPATH */
	public static final String MRP_DemandSearchGoBtn = "//button[@id='ContainerSummary_search_button']";
	/** @locatorType XPATH */
	public static final String MRP_DemandTable = "(//div[@id='ContainerSummary_data_panel']//table)//tr/th";

	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_SONumber() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(MRP_DemandTable, "SO. No.") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_Item() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(MRP_DemandTable, "Item") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_ItemUOM() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(MRP_DemandTable, "Item UOM") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_OrderDate() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(MRP_DemandTable, "Order Date") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_ActlRqrdDate() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(MRP_DemandTable, "Act. Rqrd. Date") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_RequiredDate() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(MRP_DemandTable, "Rqrd. Date") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_CustomerID() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(MRP_DemandTable, "Customer") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String MRP_DemandSelectAllCheckBox = "//div[@id='ContainerSummary_data_panel']//table//tr/th[@id='CHECKBOX']//input";
	/** @locatorType XPATH */
	public static final String MRP_ExceptionMessageTableHeader = "(//div[contains(@id,'ig_grid_vc')]//table//*[text()='Exception Message'])[1]";
	/** @locatorType XPATH */
	public static final String MRP_ResultsTab_Summary = "//div[@id='P131816101001_TAB_RESULT']//label[text()='Summary']";
	/** @locatorType XPATH */
	public static final String MRP_ResultSummary_UnitTableDataList = "//table[contains(@aria-label,'Summary, Report')]//tbody/tr/td[1]";
	/** @locatorType XPATH */
	public static final String MRP_ResultSummary_ItemTableDataList = "//table[contains(@aria-label,'Summary, Report')]//tbody/tr/td[4]";
	/** @locatorType XPATH */
	public static final String MRP_ResultSummary_UOMTableDataList = "//table[contains(@aria-label,'Summary, Report')]//tbody/tr/td[8]";
	/** @locatorType XPATH */
	public static final String MRP_ReleaseBtn = "//button[@data-testid='MRP_ReleaseBtn']";
	/** @locatorType XPATH */
	public static final String MRP_ReleaseIframe = "//iframe[@title='Release']";
	/** @locatorType XPATH */
	public static final String MRP_ReleaseIframeCloseIcon = "//h1[text()='Release']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String MRP_Release_OrderTypeSelect = "//select[@data-testid='MRP_Release_OrderTypeSelect']";
	/** @locatorType XPATH */
	public static final String MRP_Release_FromData = "//*[@data-testid='MRP_Release_FromData']/input";
	/** @locatorType XPATH */
	public static final String MRP_Release_ToData = "//*[@data-testid='MRP_Release_ToData']/input";
	/** @locatorType XPATH */
	public static final String MRP_Release_TentFromData = "//*[@data-testid='MRP_Release_TentFromData']/input";
	/** @locatorType XPATH */
	public static final String MRP_Release_TentToData = "//*[@data-testid='MRP_Release_TentToData']/input";
	/** @locatorType XPATH */
	public static final String MRP_Release_ReleaseLotSelect = "//select[@data-testid='MRP_Release_ReleaseLotSelect']";
	/** @locatorType XPATH */
	public static final String MRP_Release_FirmTypeSelect = "//select[@data-testid='MRP_Release_FirmTypeSelect']";
	/** @locatorType XPATH */
	public static final String MRP_Release_TentTypeSelect = "//select[@data-testid='MRP_Release_TentTypeSelect']";
	/** @locatorType XPATH */
	public static final String MRP_Release_LotSizeDays = "//input[@data-testid='MRP_Release_LotSizeDays']";
	/** @locatorType XPATH */
	public static final String MRP_Release_ReleaseBtn = "//button[@data-testid='MRP_Release_ReleaseBtn']";
	/** @locatorType XPATH */
	public static final String MRP_ExceptionNoDataFoundContainer = "//div[@aria-label='Exceptions']//span[text()='No data found']";

	/** @locatorType XPATH */
	public static final String MRP_ResultSummary_TotalDemandWithItem(String Item) {
		return "//td[text()='" + Item + "']/../td[14]";
	}

	/** @locatorType XPATH */
	public static final String MRP_ResultSummary_DependDemandOfRM(String Item) {
		return "//td[text()='" + Item + "']/../td[11]";
	}

	/** @locatorType XPATH */
	public static final String MRP_Demand_CheckBoxWithSONo(String SONo) {
		return "//td[text()='" + SONo + "']/../td[@headers='CHECKBOX']//input";
	}

	public static String checkAndReturnIndexForSourceLocatorWithHeaderValue(String Locator, String HeaderValue) {
		Locator = Locator + "/tbody/tr/*[count(" + Locator + "/thead/tr[2]/th[normalize-space()='" + HeaderValue
				+ "']/preceding-sibling::th) + 1]";
		return Locator;
	}
}
