package commonlocators.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PurchaseBillsLocators {
	/** @locatorType Xpath */
	public static final String PendingGrn = "//button[@id='B1552838491861175540']";
	/** @locatorType Xpath */
	public static final String GrnNo = "//button[@id='P116131004_GRN_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String Iframe = "//iframe";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1872485921782353617']";

	/** @locatorType Xpath */
	public static final String CheckBox(String GrnNo) {
		return "//td[text()='" + GrnNo + "']/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String CreateDoc = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[@id='B1882133998388489702']";
	/** @locatorType Xpath */
	public static final String LocationId = "//span[@id='P11613101001_SUPHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String CreateJournal = "//button[@id='Journal']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@id='Post']";
	/** @locatorType Xpath */
	public static final String VoucherNumber = "//span[@id='P11613101001_SUPHD_DOC_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String WorkFlowOkButton = "//button[@id='B1457704718951134857']";
	/** @locatorType Xpath */
	public static final String VoucherNumberLovButton = "//button[@id='P116131010_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String PurchaseBillSearchButton = "//button[@id='B1552825888922175484']";
	/** @locatorType Xpath */
	public static final String ApproveOkButton = "//button[@id='B1457704718951134857']";

	/** @locatorType Xpath */
	public static final String ApprovedStatus(String PbNo) {
		return "//a[text()='" + PbNo + "']/../../td[13]";
	}

	/** @locatorType Xpath */
	public static final String TableData = "//div[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String TDSU_S = "//div[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[26]";
	/** @locatorType Xpath */
	public static final String TDSLovButton = "//button[@id='C2385207596614310967_lov_btn']";
	/** @locatorType Xpath */
	public static final String ExpandButton = "//button[@id='t_Button_rightControlButton'][@aria-expanded='true']";
}