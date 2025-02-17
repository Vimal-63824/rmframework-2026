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
	public static final String PostToastMessage = "//*[@class='succ_msg']";
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
	public static final String PurchaseOrderTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[9]";
	/** @locatorType Xpath */
	public static final String PurchaseRequestTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[6]";
	/** @locatorType Xpath */
	public static final String PendingText = "//h1[text()='Pending']";
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
	public static final String PendingInspectionDocuments = "//button[@id='B1239030231502494975']";
	/** @locatorType Xpath */
	public static final String Inventory = "//span[text()='Supply Chain']/ancestor::li/*/li[3]";
	/** @locatorType Xpath */
	public static final String InventoryTransactionTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]";
	/** @locatorType Xpath */
	public static final String MaterialReceiptVoucherTab = "//span[text()='Supply Chain']/ancestor::li/*/li[3]/*/li[2]/*/li[5]";
	/** @locatorType Xpath */
	public static final String PendingReceiptVoucherButton = "//button[@id='B4724987409116632006']";
	/** @locatorType Xpath */
	public static final String FinancialTab = "//span[text()='Financials']/ancestor::li";
	/** @locatorType Xpath */
	public static final String FinancialTransactionTab = "//span[text()='Financials']/ancestor::li/*/li[2]";
	/** @locatorType Xpath */
	public static final String FinancialPartyDocuments = "//span[text()='Financials']/ancestor::li/*/li[2]/*/li[2]";
	/** @locatorType Xpath */
	public static final String FinancialPurchaseBill = "//span[text()='Financials']/ancestor::li/*/li[2]/*/li[2]/*/li[1]";
	/** @locatorType Xpath */
	public static final String PendingPurchaseBill = "//button[@id='B1552826677951175484']";
	/** @locatorType Xpath */
	public static final String PaymentReceiptTab = "//span[text()='Financials']/ancestor::li/*/li[2]/*/li[4]";
	/** @locatorType Xpath */
	public static final String PaymentsReceipts = "//span[text()='Financials']/ancestor::li/*/li[2]/*/li[4]/*/li[2]";
	/** @locatorType Xpath */
	public static final String PendingCrDocs = "//button[@id='B1918502594088484019']";
}
