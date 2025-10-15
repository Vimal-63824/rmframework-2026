package commonlocators;

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
	public static final String LeftSideAddIcon = "//button[contains(@title,'Add')]";
	/** @locatorType Xpath */
	public static final String LeftSidePendingIcon = "//button[contains(@class,'warning')]";
	/** @locatorType Xpath */
	public static final String CreateTickIcon = "//span[contains(@class,'check')]/parent::button[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String PostToastMessage = "//*[@class='succ_msg'] | //*[@id='APEX_SUCCESS_MESSAGE']//h2";
	/** @locatorType Xpath */
	public static final String searchBar = "//input[@aria-label='Search']";
	/** @locatorType Xpath */
	public static final String FailedNotifyMessage = "//div[contains(@class,'Notification--error')]";
	/** @locatorType Xpath */
	public static final String ToastCloseIcon = "//button[@title='Close']";
	/** @locatorType Xpath */
	public static final String EntryTableRefreshCellImage = "//span[contains(@class,'u-Processing-spinner')]";
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
	public static final String SearchBarIcon = "//button[contains(@class,'doSearch')]";
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
	public static final String PendingReceiptVoucherButton = "//button[@id='B4728042559427458549']";
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
	public static final String PendingCrDocs = "//button[@id='B1920165536554853812']";
	/** Locator Xpath */
	public static final String SalesSetupTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[1]";
	/** Locator Xpath */
	public static final String SalesSetup_SalesScreen = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[1]/*/li[1]";
	/** @locatorType Xpath */
	public static final String OpenSOTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String WorkApprovalFrame = "//iframe[contains(@title,'Approval')]";
	/** @locatorType Xpath */
	public static final String WorkAppoval_ForwardLovBtn = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String WorkAppoval_OkBtn = "//*[text()='Ok']/parent::button";

	/** @locatorType Xpath */
	public static String convertListOfXPath(String text) {
		return "//td[text()='" + text + "'] | //li[text()='" + text + "'] | //*[text()='" + text + "']/parent::*";
	}

	/** @locatorType Xpath */
	public static final String MaterialRequest = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String CreateMaterialRequestButton = "//button[@id='B163869976007639589']";
	/** @locatorType Xpath */
	public static final String InventoryReportsTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[3]";
	/** @locatorType Xpath */
	public static final String StocksTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[3]/*/li[15]";
	/** @locatorType Xpath */
	public static final String StocksCheckBox = "//input[@id='P93132130_TYPE_0']/../label";
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
	public static final String PendingMIV = "//button[@id='B1496310898532082770']";
	/** @locatorType Xpath */
	public static final String ItemTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[1]/*//a[text()='Items']";
	/** @locatorType Xpath */
	public static final String ItemMasterAdd = "//button[@id='B1287636716616788407']";
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
	public static final String PendingChallan = "//button[@id='B1793441455940942839']";
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
	public static final String PendingSubcontractOrder = "//button[@id='B1477708597840489099']";
	/** @locatorType Xpath */
	public static final String GrnSco = "//span[text()='Supply Chain']/ancestor::li/*/li[2]/*/li[2]/*/li[3]";
	/** @locatorType Xpath */
	public static final String PendingGrn = "//button[@id='B1191369337279430568']";
	/** @locatorType Xpath */
	public static final String RfqTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[9]";
	/** @locatorType Xpath */
	public static final String PendingDrDocs = "//button[@id='B1918502607122484020']";
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
	public static final String BuyerTab = "//label[@for='P367018021_TAB_1']";
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
	public static final String AddEmployeeButton = "//button[@id='B258815408672299509']";
	/** @locatorType XPATH */
	public static final String ApprovedSupplierPo = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[1]/*/li[2]";
	/** @locatorType XPATH */
	public static final String AddSupplierButton = "//button[@id='B1183371576871794287']";
	/** @locatorType XPATH */
	public static final String ShareOfBusiness = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[1]";
	/** @locatorType XPATH */
	public static final String AddShareOfBusiness = "//button[@data-testid='Add']";
	/** @locatorType XPATH */
	public static final String BusFunctionSearchBox = "//input[@id='P0_SEARCH_input']";
	/** @locatorType XPATH */
	public static final String Sales_Inv_Pend_Btn = "//button[@data-testid='SALES INVOICE PENDING ICON']";
	/** @locatorType XPATH */
	public static final String DebitNote_Add_Icon = "//button[@id='B1012787680560179762']";
	/** @locatorType XPATH */
	public static final String Proforma_FindPage_Add_Btn = "//button[contains(@id,'B2133960405315551418')]";
	/** @Locator XPATH */
	public static final String GateEntryPendingIcon = "//button[@id='B1166918765449991606']";
	/** @Locator XPATH */
	public static final String CreditNote_Pending_Icon = "//button[@id='B1779749669062514607']";
	/** @Locator XPATH */
	public static final String Rework_Pending_Icon = "//button[@id='B1256995407744376701']";
	/** @Locator XPATH */
	public static final String ReworkOrd_Completion_Pend_Icon = "//button[@id='B1256998966041376736']";
	/** @Locator XPATH */
	public static final String FindScreenItemField = "//input[@id='P3670200_ITEM_1']";
	/** @Locator XPATH */
	public static final String AddPLusButton = "//span[@class='t-Icon fa  fa-plus']";
	/** @Locator XPATH */
	public static final String AddButton = "//button[@id='B1249682071652311227']";
	/** @locatorType Xpath */
	public static final String unitLov = "//*[@id='P367194_UNIT_lov_btn']";
	/** @locatorType Xpath */
	public static final String ItemLovA = "//*[@id='P367194_ITEM_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//*[@id='B1810147153696003981']";
	/** @locatorType Xpath */
	public static final String SearchedItemDrillDown = "//*[@id=\"11779181059649578874_orig\"]/tbody/tr[2]/td[2]/a";
	/** @locatorType Xpath */
	public static final String SalesTab = "//*[@id='P36719401_TYPE_ASSO']/div/div/div[4]/label";
	/** @locatorType Xpath */
	public static final String CustomerAssoFlag = "//label[text()='Customer Associate']";
	/** @locatorType Xpath */
	public static final String activeButton = "//*[@id='B147500912587778117']";
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
	public static final String SaveButton = "//button[@id='B150556062898604660']";
	/** @locatorType Xpath */
	public static final String ClearFindFilterIcon = "//button[@aria-label='Clear'] | //button[@aria-label='clear']";
	/** @locatorType Xpath */
	public static final String ToastErrorMsg = "//div[contains(@class,'a-Notification--error')]";
	/** @locatorType CSS Selector */
	public static final String ProfileIcon = "#t_Header > div > img";
	/** @locatorType Xpath */
	public static final String GetTextUserName = "//*[@id='accountMenu_menu']/div/div[2]/div[1]/span[1]";
	public static final String QualityTab="//label[text()='Quality']" ;
	/** @locatorType Xpath */
	public static final String TypeDropDown= "//select[@data-testid='P36719401_PRODPLNT_FREQ_TYPE_3']" ;

}
