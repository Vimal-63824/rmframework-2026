package commonlocators.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 19, 2025
 */
public class MRPLocators {

	/** @locatorType XPATH */
	public static final String FindLinesBtn = "//button[@id='B1334407739562232708']";
	/** @locatorType XPATH */
	public static final String FindMRPNoLovBtn = "//button[@id='P13181610101_MRP_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String FindSearchBtn = "//button[@id='B337943820726963815']";

	/** @locatorType XPATH */
	public static final String Find_Status_TableData(String data) {
		return "//td/a[text()='" + data + "']/../../td[8]/div";
	}
	
	/** @locatorType XPATH */
	public static final String Find_MRPNo_TableData(String data) {
		return "//td/a[text()='" + data + "']";
	}

	/** @locatorType XPATH */
	public static final String MRP_AddNewBtn = "//button[@id='B337943478039963809']";
	/** @locatorType XPATH */
	public static final String MRP_CreateMRPIframe = "//iframe[@title='Create MRP']";
	/** @locatorType XPATH */
	public static final String MRP_AddMRPDateInput = "//input[@id='P13181610102_MRP_DT_input']";
	/** @locatorType XPATH */
	public static final String MRP_AddMRPNameInput = "//input[@id='P13181610102_MRP_NAME']";
	/** @locatorType XPATH */
	public static final String MRP_AddSafetyStkDateInput = "//input[@id='P13181610102_SFTY_STK_DT_input']";
	/** @locatorType XPATH */
	public static final String MRP_AddSupplyExceptionSelect = "//select[@id='P13181610102_MRPHD_TYPE']";
	/** @locatorType XPATH */
	public static final String MRP_TickIcon = "//button[@id='B1348277280741238629']";
	/** @locatorType XPATH */
	public static final String MRP_MRPNumberDisplayInput = "//input[@id='P131816101001_MRPHD_MRP_NO']";
	/** @locatorType XPATH */
	public static final String MRP_StatusDisplayInput = "//span[@id='P131816101001_MRPHD_STATUS_DISPLAY']";
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
	public static final String MRP_AddNewLineSourcesBtn = "//button[@id='B1405000491018243892']";
	/** @locatorType XPATH */
	public static final String MRP_SaveLineSourcesBtn = "//button[@id='B1405000562948243893']";
	/** @locatorType XPATH */
	public static final String MRP_SourceTableData_Unit = "(//table[@class='a-GV-table']//tr/td[1])[1]";
	/** @locatorType XPATH */
	public static final String MRP_Source_UnitLovBtn = "(//table[@class='a-GV-table']//tr/td[1])[1]//button";
	/** @locatorType XPATH */
	public static final String MRP_SourceTableData_CutOffDate = "(//table[@class='a-GV-table']//tr/td[6])[1]";
	/** @locatorType XPATH */
	public static final String MRP_Source_CutOffDateInput = "(//table[@class='a-GV-table']//tr/td[6])[1]//input";
	/** @locatorType XPATH */
	public static final String MRP_Source_SalesOrderTableData = "(//table[@class='a-GV-table']//tr/td[7])[1]";
	/** @locatorType XPATH */
	public static final String MRP_Source_SalesOrderCheckbox = "//div[@class='apex-item-single-checkbox']/input[@aria-label='Sales Order']";
	/** @locatorType XPATH */
	public static final String MRP_LoadDemandBtn = "//button[@id='Load_Demand_1']";
	/** @locatorType XPATH */
	public static final String MRP_RunMRPBtn = "//button[@id='B1410128083520913811']";
	/** @locatorType XPATH */
	public static final String MRP_HeaderSaveBtn = "//button[@id='B1370868183685579918']";
	/** @locatorType XPATH */
	public static final String MRP_PostBtn = "//button[@id='B1370870222954579921']";
	/** @locatorType XPATH */
	public static final String MRP_DemandSearchBar = "//input[@id='ContainerSummary_search_field']";
	/** @locatorType XPATH */
	public static final String MRP_DemandSearchGoBtn = "//button[@id='ContainerSummary_search_button']";
	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_SONumber = "//table[@id='1771312705715877064']//tr/td[3]";
	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_Item = "//table[@id='1771312705715877064']//tr/td[4]";
	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_ItemUOM = "//table[@id='1771312705715877064']//tr/td[7]";
	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_OrderDate = "//table[@id='1771312705715877064']//tr/td[8]";
	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_ActlRqrdDate = "//table[@id='1771312705715877064']//tr/td[9]";
	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_RequiredDate = "//table[@id='1771312705715877064']//tr/td[10]";
	/** @locatorType XPATH */
	public static final String MRP_DemandTableData_CustomerID = "//table[@id='1771312705715877064']//tr/td[25]";
	/** @locatorType XPATH */
	public static final String MRP_DemandSelectAllCheckBox = "//table[@id='1771312705715877064']//tr/th[@id='CHECKBOX']//input";
	/** @locatorType XPATH */
	public static final String MRP_ExceptionMessageTableHeader = "//span[@id='C1401290127037360284_HDR']";
	/** @locatorType XPATH */
	public static final String MRP_ExceptionMsg_TableData = "//span[@id='C1401290127037360284_HDR']/../../../tr[2]/td[2]";
	/** @locatorType XPATH */
	public static final String MRP_ResultsTab_Summary = "//div[@id='P131816101001_TAB_RESULT']//label[text()='Summary']";
	/** @locatorType XPATH */
	public static final String MRP_ResultSummary_UnitTableDataList = "//table[@id='677350558498427034_orig']//tbody/tr/td[1]";
	/** @locatorType XPATH */
	public static final String MRP_ResultSummary_ItemTableDataList = "//table[@id='677350558498427034_orig']//tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String MRP_ResultSummary_UOMTableDataList = "//table[@id='677350558498427034_orig']//tbody/tr/td[6]";
	/** @locatorType XPATH */
	public static final String MRP_ReleaseBtn = "//button[@id='B1418494073427832265']";
	/** @locatorType XPATH */
	public static final String MRP_ReleaseIframe = "//iframe[@title='Release']";
	/** @locatorType XPATH */
	public static final String MRP_ReleaseIframeCloseIcon = "//h1[text()='Release']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String MRP_Release_OrderTypeSelect = "//select[@id='P1318161010003_ORDER_TYPE']";
	/** @locatorType XPATH */
	public static final String MRP_Release_FromData = "//input[@id='P1318161010003_FROM_DATE_input']";
	/** @locatorType XPATH */
	public static final String MRP_Release_ToData = "//input[@id='P1318161010003_TO_DATE_input']";
	/** @locatorType XPATH */
	public static final String MRP_Release_TentFromData = "//input[@id='P1318161010003_TENT_FROM_DATE_input']";
	/** @locatorType XPATH */
	public static final String MRP_Release_TentToData = "//input[@id='P1318161010003_TENT_TO_DATE_input']";
	/** @locatorType XPATH */
	public static final String MRP_Release_ReleaseLotSelect = "//select[@id='P1318161010003_LOT_SIZE']";
	/** @locatorType XPATH */
	public static final String MRP_Release_FirmTypeSelect = "//select[@id='P1318161010003_FIRM_TYPE']";
	/** @locatorType XPATH */
	public static final String MRP_Release_TentTypeSelect = "//select[@id='P1318161010003_TENT_TYPE']";
	/** @locatorType XPATH */
	public static final String MRP_Release_LotSizeDays = "//input[@id='P1318161010003_LOT_SIZE_DAYS']";
	/** @locatorType XPATH */
	public static final String MRP_Release_ReleaseBtn = "//button[@id='B1421566413936647875']";
	/** @locatorType XPATH */
	public static final String MRP_ExceptionNoDataFoundContainer = "//div[@id='R1401289497759360278_ig_content_container']//span[text()='No data found']";

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

}
