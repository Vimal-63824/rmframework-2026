package commonlocators.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PurchaseBillsLocators {
	/** @locatorType Xpath */
	public static final String PendingGrn = "//button[@data-testid='PendingGRN']";
	/** @locatorType Xpath */
	public static final String GrnNo = "//input[@data-testid='GRNNo']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Iframe = "//iframe";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1874148864248723410']";

	/** @locatorType Xpath */
	public static final String CheckBox(String GrnNo) {
		return "//td[text()='" + GrnNo + "']/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String CreateDoc = "//button[@data-testid='CreateDoc']";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[@data-testid='INV']";
	/** @locatorType Xpath */
	public static final String LocationId = "//span[@data-testid='LocID']";
	/** @locatorType Xpath */
	public static final String CreateJournal = "//button[@data-testid='CreJournal']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@data-testid='POST']";
	/** @locatorType Xpath */
	public static final String VoucherNumber = "//span[@data-testid='VouNo']";
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
	public static final String TDSU_S = "//div[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[27]";
	/** @locatorType Xpath */
	public static final String TDSLovButton = "//button[@id='tds_lov_btn']";
	/** @locatorType Xpath */
	public static final String ExpandButton = "//button[@id='t_Button_rightControlButton'][@aria-expanded='true']";
	/** @locatorType Xpath */
	public static final String PendingSCO = "//button[@data-testid='PendingSCO']";
	/** @locatorType Xpath */
	public static final String ScoFrame = "//iframe[@title='Pending SCO']";
	/** @locatorType Xpath */
	public static final String GrnLovButton = "//input[@data-testid='GRNNo']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ScoSearchButton = "//button[@id='B1894742037718975077']";

	/** @locatorType Xpath */
	public static final String ScoCheckBox(String ScoGrnNo) {
		return "//td[text()='" + ScoGrnNo + "']/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String ScoLocationId = "//span[@id='P11613101005_SUPHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String ScoVoucherNumber = "//input[@id='P11613101005_SUPHD_DOC_NO']";
	/** @locatorType Xpath */
	public static final String ScoOkButton = "//button[@data-testid='INV']";
}