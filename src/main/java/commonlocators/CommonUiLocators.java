package commonlocators;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
public class CommonUiLocators {
	// Login Locators
	/** @locatorType Xpath */
	public static final String LoginForm = "//div[contains(@class,'t-Login-region t-Form')]";
	/** @locatorType Xpath */
	public static final String LoginEmailTextBox = "//input[contains(@id,'USERNAME')][@placeholder='Username']";
	/** @locatorType Xpath */
	public static final String LoginPasswordTextBox = "//input[contains(@id,'PASSWORD')][@placeholder='Password']";
	/** @locatorType Xpath */
	public static final String RememberMeLabel = "//label[contains(@for,'REMEMBER')][text()='Remember username']";
	/** @locatorType Xpath */
	public static final String RememberMeInput = "//input[contains(@id,'REMEMBER')]";
	/** @locatorType Xpath */
	public static final String LoginBtn = "//button[@id='loginbtn']/span";
	/** @locatorType Xpath */
	public static final String InfoRememberIcon = "//button[contains(@title,'Remember Username')]";
	/** @locatorType Xpath */
	public static final String CloseInfoRememberIcon = "//button[@title='Close']/span";
	/** @locatorType Xpath */
	public static final String LogoutIcon = "//span[@class='t-Icon fa fa-sign-out']";

	// Other Common Locators
	/** @locatorType Xpath */
	public static final String PageHeaderText = "//div[@class='t-Header-logo']//span";
	/** @locatorType Xpath */
	public static final String LeftSideAddIcon = "//button[contains(@title,'Create PR/SSR')]";
	/** @locatorType Xpath */
	public static final String LeftSidePendingIcon = "//button[contains(@class,'warning')]";
	/** @locatorType Xpath */
	public static final String CreateTickIcon = "//span[contains(@class,'check')]/parent::button[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String PostToastMessage = "//*[@class='succ_msg'] | //*[@id='APEX_SUCCESS_MESSAGE']//h2";
	/** @locatorType Xpath */
	public static final String searchBar = "//div[@role='dialog' and not(contains(@style,'display: none')) and contains(@class,'ui-dialog-popuplov')]//input[@aria-label='Search']";
	/** @locatorType Xpath */
	public static final String searchBarAllList = "//input[@aria-label='Search']";
	/** @locatorType Xpath */
	public static final String searchBarResults = "//div[@role='dialog' and not(contains(@style,'display: none'))]//div[contains(@class,'PopupLOV-results')]//*[(self::tbody or self::li)]/..";
	/** @locatorType Xpath */
	public static final String searchBarHeader = "//div[@role='dialog' and not(contains(@style,'display: none'))]//*[contains(@class,'dialog-titlebar') and not(contains(@title,'Close'))]";
	/** @locatorType Xpath */
	public static final String FailedNotifyMessage = "//div[contains(@class,'Notification--error')]";
	/** @locatorType Xpath */
	public static final String ToastCloseIcon = "//div[contains(@class,'Alert')]//button[@title='Close']";
	/** @locatorType Xpath */
	public static final String EntryTableRefreshCellImage = "//*[contains(@class,'u-Processing-spinner')]";
	/** @locatorType Xpath */
	public static final String LeftNavigationBar = "//button[@id='t_Button_navControl']";
	/** @locatorType Xpath */
	public static final String SupplyChainTab = "//span[text()='Supply Chain']/ancestor::li";
	/** @locatorType Xpath */
	public static final String PurchaseTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]";
	/** @locatorType Xpath */
	public static final String PurchaseTransactionTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]";
	/** @locatorType Xpath */
	public static final String PurchaseOrderTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[11]";
	/** @locatorType Xpath */
	public static final String PurchaseRequestTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li/*/a[text()='Purchase Request(PR)']";
	/** @locatorType Xpath */
	public static final String PendingText = "//h1";
	/** @locatorType Xpath */
	public static final String SearchBarIcon = "//div[@role='dialog' and not(contains(@style,'display: none')) and contains(@class,'ui-dialog-popuplov')]//input[@aria-label='Search']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PurchaseRequestCloseShortTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[7]";
	/** @locatorType Xpath */
	public static final String PayrollTab = "//span[text()='Payroll']/ancestor::li";
	/** @locatorType Xpath */
	public static final String PayrollTransactionTab = "//span[text()='Payroll']/ancestor::li/*/li[2]";
	/** @locatorType Xpath */
	public static final String PayrollSetupTab = "//span[text()='Payroll']/ancestor::li/*/li[1]";
	/** @locatorType Xpath */
	public static final String PayrollEmployeeMasterTab = "//span[text()='Payroll']/ancestor::li/*/li[2]/*/li[1]";
	/** @locatorType Xpath */
	public static final String PayrollLeaveAndLoanTab = "//span[text()='Payroll']/ancestor::li/*/li[1]/*/li[4]";
	/** @locatorType Xpath */
	public static final String PayrollEmployeeMasterScreen = "//span[text()='Payroll']/ancestor::li/*/li[2]/*/li[1]/*/li[1]";
	/** @locatorType Xpath */
	public static final String EmployeePayrollAttendanceLeave = "//span[text()='Payroll']/ancestor::li/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String EmployeeLeaveRequestScreen = "//span[text()='Payroll']/ancestor::li/*/li[2]/*/li[2]/*/li[4]";
	/** @locatorType Xpath */
	public static final String SuccessToastMessage = "//span[@id='APEX_SUCCESS_MESSAGE']//h2";

	/** @locatorType Xpath */
	public static String convertXPath(String text) {
		return "(//*[text()='" + text + "'])[1]";
	}

	/** @locatorType Xpath */
	public static String searchHighlightText(String text) {
		return "(//*[text()='" + text + "'][@class='popup-lov-highlight'])[1]";
	}

	/** @locatorType Xpath */
	public static final String POAmendmentTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[10]";

	/** @locatorType Xpath */
	public static String convertXPathWithLi(String text) {
		return "(//li[text()='" + text + "'])[1]";
	}

	/** @locatorType Xpath */
	public static final String PricelistTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String QualityControl = "//span[text()='Quality Control']/ancestor::li";
	/** @locatorType Xpath */
	public static final String QualityTransactionTab = "//span[text()='Quality Control']/ancestor::li/*/li[2]";
	/** @locatorType Xpath */
	public static final String QualityInspectionDocuments = "//span[text()='Quality Control']/ancestor::li/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String PendingInspectionDocuments = "//button[@data-testid='Add_Ins']";
	/** @locatorType Xpath */
	public static final String Inventory = "//span[text()='Supply Chain']/ancestor::li/*/li[3]";
	/** @locatorType Xpath */
	public static final String InventoryTransactionTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]";
	/** @locatorType Xpath */
	public static final String MaterialReceiptVoucherTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]/*/li[5]";
	/** @locatorType Xpath */
	public static final String PendingReceiptVoucherButton = "//button[@title='Create Receipt Voucher']";
	/** @locatorType Xpath */
	public static final String FinancialTab = "//span[text()='Financials']/ancestor::li";
	/** @locatorType Xpath */
	public static final String FinancialTransactionTab = "//span[text()='Financials']/ancestor::li/*/li[2]";
	/** @locatorType Xpath */
	public static final String FinancialPartyDocuments = "//span[text()='Financials']/ancestor::li/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String FinancialPurchaseBill = "//span[text()='Financials']/ancestor::li/*/li[2]/*/li[2]/*/li[1]";
	/** @locatorType Xpath */
	public static final String PendingPurchaseBill = "//button[@data-testid='PendingBills']";
	/** @locatorType Xpath */
	public static final String PaymentReceiptTab = "//span[text()='Financials']/ancestor::li/*/li[2]/*/li[4]";
	/** @locatorType Xpath */
	public static final String PaymentsReceipts = "//span[text()='Financials']/ancestor::li/*/li[2]/*/li[4]/*/li[2]";
	/** @locatorType Xpath */
	public static final String PendingCrDocs = "//span[contains(text(),'Pending Cr. Docs.')]/ancestor::button";
	/** Locator Xpath */
	public static final String SalesSetupTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[1]";
	/** Locator Xpath */
	public static final String SalesSetup_SalesScreen = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[1]/*/li[1]";
	/** @locatorType Xpath */
	public static final String OpenSOTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String WorkApprovalFrame = "//iframe[contains(@title,'Approval') or @title='Update Employee Details']";
	/** @locatorType Xpath */
	public static final String WorkAppoval_ForwardLovBtn = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String WorkAppoval_OkBtn = "//*[text()='Ok']/parent::button";
	/** @locatorType Xpath */
	public static final String WorkFlow_Frwd_To_Emp = "//input[@id='P236131090_FWD_PERSON']";
	/** @locatorType Xpath */
	public static final String WF_Fwd_To_person = "//input[@data-testid='P236131090_FWD_PERSON']";

	/** @locatorType Xpath */
	public static String convertListOfXPath(String text) {
		return "//*[contains(@id,'PopupLov')]//td[text()='" + text + "'] | //*[contains(@id,'PopupLov')]//li[text()='"
				+ text + "'] | //*[contains(@id,'PopupLov')]//*[text()='" + text + "']/parent::*";
	}

	/** @locatorType Xpath */
	public static final String MaterialRequest = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String CreateMaterialRequestButton = "//button[@data-testid='Add_MR']";
	/** @locatorType Xpath */
	public static final String InventoryReportsTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[3]";
	/** @locatorType Xpath */
	public static final String StocksTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[3]/*/li[15]";
	/** @locatorType Xpath */
	public static final String StocksCheckBox = "//input[@data-testid='P93132130_TYPE']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String PurchaseOrderTabForPurchaseUser = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*//a[text()='Purchase Order']";
	/** @locatorType Xpath */
	public static final String ProductionPlanningTab = "//span[contains(text(),'Production Planning')]/ancestor::li";
	/** @locatorType Xpath */
	public static final String ProdPlan_TransactionTab = "//span[contains(text(),'Production Planning')]/ancestor::li/*/li[2]";
	/** @locatorType Xpath */
	public static final String ProdPlan_Transaction_MRPScreen = "//span[contains(text(),'Production Planning')]/ancestor::li/*/li[2]/*/li[3]";
	/** @locatorType Xpath */
	public static final String ProdPlan_Transaction_ProductionOrder = "//span[contains(text(),'Production Planning')]/ancestor::li/*/li[2]/*/li[4]";
	/** @locatorType Xpath */
	public static final String NoDataFoundContainerLists = "//div[@aria-label='No data found']";
	/** @locatorType Xpath */
	public static final String SupplyChainTabCheck = "//span[text()='Supply Chain']";
	/** @locatorType Xpath */
	public static final String PurchaseTabCheck = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/span[2]";
	/** @locatorType Xpath */
	public static final String InventoryCheck = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/span[2]";
	/** @locatorType Xpath */
	public static final String InventoryReportsTabCheck = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[3]/*/span[2]";
	/** @locatorType Xpath */
	public static final String MIVTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]/*/li[3]";
	/** @locatorType Xpath */
	public static final String PendingMIV = "//button[@data-testid='create_issuance']";
	/** @locatorType Xpath */
	public static final String ItemTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[1]/*//a[text()='Items']";
	/** @locatorType Xpath */
	public static final String ItemMasterAdd = "//button[@title='Add Item']";
	/** @locatorType Xpath */
	public static final String Invt = "//*[@id=\"t_TreeNav_204\"]/span";
	/** @locatorType Xpath */
	public static final String InvSetupTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[1]";
	/** @locatorType Xpath */
	public static final String ProdPlan_Transaction_CompletionProductionOrder = "//span[contains(text(),'Production Planning')]/ancestor::li/*/li[2]/*/li[11]";
	/** @locatorType Xpath */
	public static final String StockTransactionTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[3]/*/li/div/a[text()='Stock Transaction']";
	/** @locatorType Xpath */
	public static final String StockTransactionCheckBox = "//input[@id='P93132310_BS_STOCK_0']/..";
	/** @locatorType Xpath */
	public static final String ToastMessagePopup = "//h6[@class='succ_msg']";
	/** @locatorType Xpath */
	public static final String DeliveryChallan = "//span[text()='Supply Chain']/ancestor::li/*/li[5]/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String GateEntry = "//span[text()='Supply Chain']/ancestor::li/*/li[5]";
	/** @locatorType Xpath */
	public static final String GateEntryTransaction = "//span[text()='Supply Chain']/ancestor::li/*/li[5]/*/li[2]";
	/** @locatorType Xpath */
	public static final String PendingChallan = "//button[@data-testid='Create_DC']";
	/** @locatorType Xpath */
	public static final String ProdPlan_Transaction_BillOfMaterials = "//span[contains(text(),'Production Planning')]/ancestor::li/*/li[2]/*/li[1]";
	/** @locatorType Xpath */
	public static final String ClickGateEntryNode = "//*[@id='t_TreeNav_208']/div[2]/span[2]";
	/** @locatorType Xpath */
	public static final String ClickSupplyChaintabForAdminUser = "//span[text()='Supply Chain']";
	/** @locatorType Xpath */
	public static final String InventoryTabForAdminUser = "//span[text()='Supply Chain']/ancestor::li/*/li[3]";
	/** @locatorType Xpath */
	public static final String InventoryTransactionTabForAdminUser = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]";
	/** @locatorType Xpath */
	public static final String MaterialReceiptVoucherTabForAdminUser = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]/*//a[text()='Material Receipt Voucher']";
	/** @locatorType Xpath */
	public static final String Subcontracting = "//span[text()='Supply Chain']/ancestor::li/*/li[2]";
	/** @locatorType Xpath */
	public static final String SubcontractingTransaction = "//span[text()='Supply Chain']/ancestor::li/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String SubcontractOrder = "//span[text()='Supply Chain']/ancestor::li/*/li[2]/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String PendingSubcontractOrder = "//button[@data-testid='Create_SCO']";
	/** @locatorType Xpath */
	public static final String GrnSco = "//span[text()='Supply Chain']/ancestor::li/*/li[2]/*/li[2]/*/li[3]";
	/** @locatorType Xpath */
	public static final String PendingGrn = "//button[@data-testid='Pending_PO_SCO']";
	/** @locatorType Xpath */
	public static final String RfqTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[9]";
	/** @locatorType Xpath */
	public static final String PendingDrDocs = "//button[@data-testid='PendingDrDocs']";
	/** @locatorType Xpath */
	public static final String UsernameIcon = "//*[@title='Sign Out']/following::*[1]";
	/** @locatorType Xpath */
	public static final String OpenPO_RC = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li/*/a[text()='Open PO/RC']";
	/** @locatorType Xpath */
	public static final String OpenPO_RCButton = "//label[@for='P16151310256_TYPE_0']";
	/** @locatorType Xpath */
	public static final String AutoCreationMrvCheckBox = "//input[@id='P367019021_ICMCTRL_AUTO_MTR_FLAG_1']";
	/** @locatorType Xpath */
	public static final String ApplControlLabel = "//label[@for='P367019021_TAB_OVERALL_GROUP_0']";
	/** @locatorType Xpath */
	public static final String SetupInventoryTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[1]/*/li[1]";
	/** @locatorType Xpath */
	public static final String AutoPostMRVCheckBox = "//input[@id='P367019021_ICMCTRL_AUTO_MTR_COMPL_FLAG_1']";
	/** @locatorType Xpath */
	public static final String EmployeeLeaveEntryScreen = "//span[text()='Payroll']/ancestor::li/*/li[2]/*/li[2]/*/li[5]";
	/** @locatorType Xpath */
	public static final String ClearIcon = "//button[@id='Clear']";
	/** @locatorType Xpath */
	public static final String SSOTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[6]";
	/** @locatorType Xpath */
	public static final String OPOTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[3]";
	/** @locatorType Xpath */
	public static final String OPOClose = "//*[@id=\"B2664048724780355848\"]/span";
	/** @locatorType Xpath */
	public static final String GateEntryInward = "//span[text()='Supply Chain']/ancestor::li/*/li[5]/*/li[2]/*/li[1]";
	/** @locatorType XPATH */
	public static final String SupplyChainPurchaseSetup = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[1]";
	/** @locatorType XPATH */
	public static final String SetupPurchase = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[1]/*/li[1]";
	/** @locatorType XPATH */
	public static final String BuyerTab = "//label[@for='P367018021_TAB_2']";
	/** @locatorType Xpath */
	public static final String PayrollTransactionPayroll = "//span[text()='Payroll']/ancestor::li/*/li[2]/*/li[6]";
	/** @locatorType Xpath */
	public static final String PrepareProcessPayrollScreen = "//span[text()='Payroll']/ancestor::li/*/li[2]/*/li[6]/*/li[4]";
	/** @locatorType XPATH */
	public static final String SysAdminstrationTab = "//span[text()='Sys. Administration']/ancestor::li";
	/** @locatorType XPATH */
	public static final String SysAdminTransaction = "//span[text()='Sys. Administration']/ancestor::li/*/li[2]";
	/** @locatorType XPATH */
	public static final String EmployeeQuickEntryTab = "//span[text()='Sys. Administration']/ancestor::li/*/li[2]/*/li[2]";
	/** @locatorType XPATH */
	public static final String AddEmployeeButton = "//button[@data-testid='Emp_Qik_Etry_add']";
	/** @locatorType XPATH */
	public static final String ApprovedSupplierPo = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[1]/*/li[2]";
	/** @locatorType XPATH */
	public static final String AddSupplierButton = "//button[@data-testid='add']";
	/** @locatorType XPATH */
	public static final String ShareOfBusiness = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[1]";
	/** @locatorType XPATH */
	public static final String AddShareOfBusiness = "//button[@data-testid='Add']";
	/** @locatorType XPATH */
	public static final String BusFunctionSearchBox = "//input[@id='P0_SEARCH_input']";
	/** @locatorType XPATH */
	public static final String Sales_Inv_Pend_Btn = "//button[@data-testid='SALES INVOICE PENDING ICON']";
	/** @locatorType XPATH */
	public static final String DebitNote_Add_Icon = "//button[@data-testid='Add_Debit_Note']";
	/** @locatorType XPATH */
	public static final String Proforma_FindPage_Add_Btn = "//button[@data-testid='PROFORMA INVOICE ADD ICON']";
	/** @Locator XPATH */
	public static final String GateEntryPendingIcon = "//button[@data-testid='Create_GE']";
	/** @Locator XPATH */
	public static final String CreditNote_Pending_Icon = "//button[@data-testid='Create_Sales_Return']";
	/** @Locator XPATH */
	public static final String Rework_Pending_Icon = "//button[@data-testid='Rework_Pending_Icon']";
	/** @Locator XPATH */
	public static final String ReworkOrd_Completion_Pend_Icon = "//button[@data-testid='RWC_Pend_Btn']";
	/** @Locator XPATH */
	public static final String FindScreenItemField = "//input[@id='P3670200_ITEM_1']";
	/** @Locator XPATH */
	public static final String AddPLusButton = "//span[@class='t-Icon fa  fa-plus']";
	/** @Locator XPATH */
	public static final String AddButton = "//button[@data-testid='Add_Button']";
	/** @locatorType Xpath */
	public static final String unitLov = "//*[@id='P367194_UNIT_lov_btn']";
	/** @locatorType Xpath */
	public static final String ItemLovA = "//*[@id='P367194_ITEM_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String ItemAssoFindTable = "//table[@data-testid='RIA_Result_Data']//tr//th";

	/** @locatorType Xpath */
	public static final String SearchedItemDrillDown() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(ItemAssoFindTable, "Item") + ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String SalesTab = "(//div[@aria-label='Sales']//button)[1]";
	/** @locatorType Xpath */
	public static final String CustomerAssoFlag = "//label[text()='Sales Customer Associate']";
	/** @locatorType Xpath */
	public static final String activeButton = "//*[@data-testid='Activate']";
	/** @locatorType Xpath */
	public static final String EmployeeAdjustmentScreen = "//span[text()='Payroll']/ancestor::li/*/li[2]/*/li[6]/*/li[1]";
	/** Locator Xpath */
	public static final String salesRegion = "//label[text()='Sales Region']";
	/** Locator Xpath */
	public static final String SelectSearchTab = "//li[contains(@id,'P0_SEARCH_option_0')]";
	/** Locator Xpath */
	public static final String TransactionTab = "//a[text()='Transaction']/ancestor::li";
	/** Locator Xpath */
	public static final String ExpandAll = "//button[@id='EXP']";
	/** Locator Xpath */
	public static final String SalesHeader = "//h2[@id='a_Collapsible19_R1795689823716090493_heading']";
	/** Locator Xpath */
	public static final String CustomerSchedule = "//h3[text()='Customer Schedule Entry']/ancestor::li/a";
	/** Locator Xpath */
	public static final String CustomerScheduleAmendment = "//h3[text()='Cust. Schld. Amend/CS (multi)']/ancestor::li/a";
	/** Locator Xpath */
	public static final String SetupTab = "//a[text()='Setup']/ancestor::li";
	/** Locator Xpath */
	public static final String Items = "//h3[text()='Items']/ancestor::li/a";
	/** Locator Xpath */
	public static final String CustomerItems = "//h3[text()='Customer Item']/ancestor::li/a";
	/** Locator Xpath */
	public static final String OpenSO = "//h3[text()='Open SO']/ancestor::li/a";
	/** Locator Xpath */
	public static final String PriceList = "//h3[text()='Price List/Standard/Catalog']/ancestor::li/a";
	/** Locator Xpath */
	public static final String SalesOrder = "//h3[text()='Sales Order']/ancestor::li/a";
	/** Locator Xpath */
	public static final String SalesOrderAmendment = "//h3[text()='SO CS/Amendment']/ancestor::li/a";
	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@data-testid='Save_1']";
	/** @locatorType Xpath */
	public static final String ClearFindFilterIcon = "//button[contains(@aria-label,'Clear') or contains(@aria-label,'clear')]";
	/** @locatorType Xpath */
	public static final String ToastErrorMsg = "//div[contains(@class,'a-Notification--error')]";
	/** @locatorType CSS Selector */
	public static final String ProfileIcon = "#t_Header > div > img";
	/** @locatorType Xpath */
	public static final String GetTextUserName = "//li[@title='Sign Out']/../li[3]/a/span[2]";
	/** @locatorType Xpath */
	public static final String QualityTab = "(//div[@aria-label='Quality']//button)[1]";
	/** @locatorType Xpath */
	public static final String TypeDropDown = "//select[@data-testid='P36719401_PRODPLNT_FREQ_TYPE_3']";
	/** @locatorType Xpath */
	public static final String CreatePORadioButton = "//button[@id='B1596433513455453723']";
	/** @Locator XPATH */
	public static final String Open_SCO_Find_AddIcon = "//button[@data-testid='Add']";
	/** @Locator XPATH */
	public static final String OP_Find_DocNO = "//input[@data-testid='P193151010_FIND_PO_NO']/following-sibling::button";
	/** @Locator XPATH */
	public static final String SubcontractFindAddIcon = "//button[@data-testid='Add']";
	/** @Locator XPATH */
	public static final String Subcontract_Pending_Icon = "//button[@data-testid='Create_SCO']";
	/** @Locator XPATH */
	public static final String MaterialReq_AddIcon = "//button[@data-testid='Add_MR']";
	/** @Locator XPATH */
	public static final String GRNSCO_Pending_Icon = "//button[@data-testid='Pending_PO_SCO']";
	/** @locatorType XPATH */
	public static final String CreateProdOrderAddIcon = "//button[@data-testid='CreateProdOrderAddIcon']";
	/** @locatorType XPATH */
	public static final String WorkAppoval_OkBtn_SCO = "//button[@data-testid='Entry']";
	/** @locatorType Xpath */
	public static final String CreatePoButton = "//button[contains(@onclick,'ADD_PO')]";
	/** @locatorType Xpath */
	public static final String PoCreateFrame = "//iframe[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String PrCreateButton = "//button[@title='Create PR/SSR']";
	/** @locatorType Xpath */
	public static final String PrCreateFrame = "//iframe[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String CreatePrButton = "//button[@data-testid='ADD_PR']";
	/** @locatorType Xpath */
	public static final String PurchaseAddIcon = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String CreatePRTickIcon = "//div[@class='t-Region-body']//button[@aria-label='Create']";
	/** @locatorType Xpath */
	public static final String CreatePoTickIcon = "//button[@data-testid='Create_PO_Form']";
	/** @Locator XPATH */
	public static final String ProductionPlanning = "//label[@for='P3670133_TYPES_0']";
	/** @Locator XPATH */
	public static final String MachineAdd_Icon = "//button[@id='B1250846748385910432']";
	/** @Locator XPATH */
	public static final String ManpowerGrp_Add_Icon = "//button[@id='B1550518234307758885']";
	/** @locatorType Xpath */
	public static final String LeftSideAddIconPo = "//button[@data-testid=\"Add\"]";
	/** @Locator XPATH */
	public static final String Pend_SerialWise_Btn = "//button[@data-testid='Serialwise-Pending']";
	/** @Locator XPATH */
	public static final String PendingPR = "//button[@id='B1607785281688206710']";
	/** @locatorType Xpath */
	public static final String PurchaseRequestAddIcon = "//button[@title='Create PR/SSR']";
	/** @locatorType Xpath */
	public static final String PreDispatchLabel = "//input[@data-testid='P36719401_PRODPLNT_PDI_FLAG_3']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String PreDispatchInputBox = "//input[@data-testid='P36719401_PRODPLNT_PDI_FLAG_3']";
	/** @locatorType Xpath */
	public static final String SuccesToastCloseIcon = "//button[@title='Close' and contains(@onclick,\"Success').remove\")]";
	/** @Locator XPATH */
	public static final String SubcontractCSFindAddIcon = "//button[@data-testid='Add_SCO_Amd']";
	/** @locatorType Xpath */
	public static final String SSRAddButton = "//button[@id='B2390197649305356147']";
	/** @locatorType Xpath */
	public static final String FinalInspectionCheckBox = "//input[@data-testid='P36719401_PRODPLNT_FPI_FLAG_3']";
	/** @locatorType Xpath */
	public static final String OkayButton = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String CancelBtn = "//*[text()='Cancel']/..//button[1]";
	/** @locatorType Xpath */
	public static final String ItemLovButton = "//input[@data-testid='P93132130_ITEM']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ShowMoreIcon = "//span[contains(@class,'a-Collapsible-icon')]/parent::button";
	/** @locatorType Xpath */
	public static final String CollapseAllLinesBtn = "//button[@title='Collapse']";
	/** @locatorType Xpath */
	public static final String ExpandAllLinesBtn = "//button[@title='Expand']";
	/** @locatorType Xpath */
	public static final String FindShowMore = "//button[@id='M']";
	/** @locatorType Xpath */
	public static final String ReportSearchInput = "//input[@class='a-IRR-search-field']";
}
