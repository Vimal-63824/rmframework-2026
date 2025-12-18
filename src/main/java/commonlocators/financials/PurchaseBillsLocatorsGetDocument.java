package commonlocators.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 17, 2025
 */
public class PurchaseBillsLocatorsGetDocument {
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
	public static final String PurchaseBillSearchButton = "//button[@data-testid='Search']";
	/** @locatorType Xpath */
	public static final String ApproveOkButton = "//button[@id='B1457704718951134857']";

	/** @locatorType Xpath */
	public static final String ApprovedStatus(String PbNo) {
		return "//a[text()='" + PbNo + "']/../../td[14]";
	}

	/** @locatorType Xpath */
	public static final String TableData = "//div[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String TDSLovButton = "//button[@id='tds_lov_btn']";
	/** @locatorType Xpath */
	public static final String ExpandButton = "//button[@id='t_Button_rightControlButton'][@aria-expanded='true']";

	/** @locatorType Xpath */
	public static final String LocLovButton = "//input[@data-testid='LocID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String DocAddBtn = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String SubVouType = "//input[@data-testid='P11613101017_SUPHD_GRN_REFER']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PurchaseBillsframe = "//iframe[@title='Purchase Bills']";
	/** @locatorType Xpath */
	public static final String PurchaseBillTickIcon = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String LocID = "//input[@data-testid='P11613101017_SUPHD_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PartyID = "//input[@data-testid='P11613101017_SUPHD_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BillNumInput = "//input[@data-testid='P11613101017_SUPHD_SUPLR_DOC_N']";
	/** @locatorType Xpath */
	public static final String GetDoc = "//button[@data-testid='GetDoc']";
	/** @locatorType Xpath */
	public static final String GetDocumentFrame = "//iframe[@title='Pending LC']";
	/** @locatorType Xpath */
	public static final String GetDocumentSearchbutton = "//button[@data-testid='SearchLC']";
	/** @locatorType Xpath */
	public static final String GetDocumentFromGrnNumber = "//input[@data-testid='P116131014_GRN_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GetDocumentSelectAllButton = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String GetDocumentOkbtn = "	//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String ReferenceTab = "//input[@id='P11613101001_TABS_3']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String ExemtedTDSFlag = "//input[@id='P11613101001_SUPHD_TDS_EXMP_FL']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String OkButtonForward = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String TDSU_SForLandedCost = "//div[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[27]";
	/** @locatorType Xpath */
	public static final String PBAddSave = "//button[@id='IN_SAVE']";
	/** @locatorType Xpath */
	public static final String HsnLov = "//input[@data-testid=\"SUPLN_HSN_CODE\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String HsnFieldGrid = "//div[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String PBAddTypeExp = "//select[@id='C2487000269160585286']";
	/** @locatorType Xpath */
	public static final String PBCostInput = "//input[@data-testid='SUPLN_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String PBCostField = "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[10]";
	public static String PBCostField(int i) {
		return "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String PBAddLine = "//button[@data-testid='ADDLN']";
	/** @locatorType Xpath */
	public static final String GlAccDescField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String GlAccLov = "//input[@data-testid='SUPLN_ACCT_DESC']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PBItemField = "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String PBItemLov = "//input[@data-testid='PROD_ID_REV']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PendingLc = "//button[@data-testid='PendingLC']";
	/** @locatorType Xpath */
	public static final String IframeForLc = "//iframe[@title='Pending LC']";
	/** @locatorType Xpath */
	public static final String PendingGrnLc = "//button[@id='P116131014_GRN_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButtonForLc = "//button[@data-testid='SearchLC']";
	/** @locatorType Xpath */
	public static final String CheckAllForLc = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String CreateDocForLc = "//button[@data-testid='CreateDoc']";
	/** @locatorType Xpath */
	public static final String InvoiceLovForLc = "//button[@id='P116131014_INV_PFX_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingGrnLovInput = "//input[@id='P116131014_INV_PFX']";
	/** @locatorType Xpath */
	public static final String OkBtnForLc = "//button[@data-testid='INV']";
	/** @locatorType Xpath */
	public static final String PendingSt = "//button[@data-testid='PendingSTK']";
	/** @locatorType Xpath */
	public static final String IframeForSt = "//h1[contains(text(),'Pending ST')]/ancestor::div/following-sibling::div/iframe";
	/** @locatorType Xpath */
	public static final String GrnLovBtn = "//input[@data-testid='P116131004_GRN_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButtonForSt = "//button[@data-testid='SearchGRN']";
	/** @locatorType Xpath */
	public static final String CheckAllForSt = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String CreateDocForSt = "//button[@data-testid='CreateDoc']";
	/** @locatorType Xpath */
	public static final String PendingGrnStInvLovInput = "//input[@id='P116131004_INV_PFX']";
	/** @locatorType Xpath */
	public static final String InvoiceLovForSt = "//button[@id='P116131004_INV_PFX_lov_btn']";
	/** @locatorType Xpath */
	public static final String OkBtnForSt = "//button[@data-testid='INV']";
	/** @locatorType Xpath */
	public static final String StockTransferTypeTypeField = "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String StockTransferQtyInput = "//input[@id='qty']";
	/** @locatorType Xpath */
	public static final String StockTransferTypeQtyField = "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String StockTransferTypeHsnField = "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String StockTransferHsnLov = "//input[@data-testid='SUPLN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AllowIgstFlag = "//input[@id='P11613101001_ALOW_IGST_CSGST']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String OverAllSaveBtn = "//button[@data-testid='SAVEHD']";
	/** @locatorType Xpath */
	public static final String InvoiceLineTab = "//input[@id='P11613101001_TABS_0']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String AllowRcmflag = "//input[@id='P11613101001_SUHD_ALOW_RCM_TAX']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String AllowIgstFlagEnabled = "//input[@id='P11613101001_ALOW_IGST_CSGST']";
	/** @locatorType Xpath */
	public static final String AllowRCMFlagEnabled = "//input[@data-testid='P11613101001_SUHD_ALOW_RCM_TAX']";
}