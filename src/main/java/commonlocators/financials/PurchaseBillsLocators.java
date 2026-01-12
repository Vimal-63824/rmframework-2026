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
	public static final String CheckBox= "//*[@id='4695452701146094907']/tbody/tr[2]/td[13]";
	

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
	/** @locatorType Xpath */
	public static final String AddButton = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String PendingFrame = "//iframe[@title='Purchase Bills']";
	/** @locatorType Xpath */
	public static final String SubVouTypeLov = "//button[@id='P11613101017_SUPHD_GRN_REFER_lov_btn']";
	/** @locatorType Xpath */
	public static final String RefDropDown = "//select[@id='P11613101017_SUPHD_PUR_TYPE']";
	/** @locatorType Xpath */
	public static final String RefField = "//*[@id='P11613101017_SUPHD_PUR_TYPE_CONTAINER']";
	/** @locatorType Xpath */
	public static final String LocLov = "//button[@id='P11613101017_SUPHD_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String PrefixDropDown = "//select[@data-testid='VouPfx']";
	/** @locatorType Xpath */
	public static final String PartyDescLoc = "//button[@id='P11613101017_SUPHD_SUPLR_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String BillNumberField = "//input[@data-testid='P11613101017_SUPHD_SUPLR_DOC_N']";
	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String VouNumber = "//span[@id='P11613101001_SUPHD_DOC_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String AddNew = "//button[@data-testid='ADDLN']";
	/** @locatorType Xpath */
	public static final String GlAccDescField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String GlAccLoc = "//button[@id='C2691931750024847071_lov_btn']";
	/** @locatorType Xpath */
	public static final String UnitCostField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[10] ";
	/** @locatorType Xpath */
	public static final String UnitCost = "//input[@data-testid='SUPLN_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String HsnCodeField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @locatorType Xpath */
	public static final String HsnCodeLov = "//input[@data-testid='SUPLN_HSN_CODE']";
	/** @locatorType Xpath */
	public static final String SaveButtonLines = "//button[@data-testid='IN_SAVE']";
	/** @locatorType Xpath */
	public static final String CreateJvButton = "//button[@data-testid='CreJournal']";
	/** @locatorType Xpath */
	public static final String PostButtonH = "//button[@data-testid='POST']";
	/** @locatorType Xpath */
	public static final String WfOkButtton = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String gstClafField = "//*[@id='report_3336166932448528615_catch']/dl/dd[12]";
	/** @locatorType Xpath */
	public static final String SideScroll = "//button[@id='t_Button_rightControlButton']";
	/** @locatorType Xpath */
	public static final String GstTaxPercentField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[16]";
	/** @locatorType Xpath */
	public static final String IgstAmountField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[17]";
	/** @locatorType Xpath */
	public static final String CgstAmountField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[18]";
	/** @locatorType Xpath */
	public static final String SgstAmountField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[19]";
	/** @locatorType Xpath */
	public static final String UTgstAmountField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[20]";
	/** @locatorType Xpath */
	public static final String ReferenceField = "//label[text()='Reference']";
	/** @locatorType Xpath */
	public static final String AllowIgstCheckFlag = "//label[text()='Allow IGST Tax ']";
	/** @locatorType Xpath */
	public static final String SaveButtonHeader = "//button[@data-testid='SAVEHD']";
	/** @locatorType Xpath */
	public static final String InvoiceLines = "//label[text()='Invoice Lines']";
	/** @locatorType Xpath */
	public static final String AllowRcmFlag = "//*[@id='P11613101001_SUHD_ALOW_RCM_TAX_LABEL']";
	/** @locatorType Xpath */
	public static final String GstTypeDropDown = "//select[@data-testid='SUPLN_INPUT_TYPE']";
	/** @locatorType Xpath */
	public static final String GstTypeField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[24]";
	/** @locatorType Xpath */
	public static final String InSectionField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[63]";
	/** @locatorType Xpath */
	public static final String InSectionDropDown = "//select[@data-testid='SUPLN_INELGBL_TYPE']";
	/** @locatorType Xpath */
	public static final String InvoiceButtonGrid = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[72]";
	/** @locatorType Xpath */
	public static final String InvoiceDetailButton = "#IN_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(72) > a";
	/** @locatorType Xpath */
	public static final String InvDetailsFrame = "//iframe[@title='Inv. Details']";
	/** @locatorType Xpath */
	public static final String AddButtonInvDetails = "//button[@id='B1575278755465089314']";
	/** @locatorType Xpath */
	public static final String InvDocGridField = "//*[@id='ADJ_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String InvDetailLovField = "//button[@id='C1970609351292578984_lov_btn']";
	/** @locatorType Xpath */
	public static final String SaveButtonInvBT = "//button[@id='B1970621680320594383']";
	/** @locatorType Xpath */
	public static final String DistributeButton = "//button[@id='B1982474622291454075']";
	/** @locatorType Xpath */
	public static final String CloseIcon = "(//button[@title='Close'])[3]";
	/** @locatorType Xpath */
	public static final String TdsGridField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[26]";
	/** @locatorType Xpath */
	public static final String TdsFieldInput = "//input[@data-testid='SUPLN_TDS_US_ID']";
	/** @locatorType Xpath */
	public static final String TdsInputGrid = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[27]";
	/** @locatorType Xpath */
	public static final String PBAdjustmentTab = "//label[@for='P11613101001_TABS_2']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentLoadBtn = "//button[@id='loadadj']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentFrame = "//iframe[@title='Advance Adjustment ']";
	/** @locatorType Xpath */
	public static final String PBAdjustDocSelect = "//*[@id=\"1985325153421018015_orig\"]/tbody/tr[2]/td[11]";
	/** @locatorType Xpath */
	public static final String PBAdjustValueEnterField = "//*[@id=\"1985325153421018015_orig\"]/tbody/tr[2]/td[10]";
	/** @locatorType Xpath */
	public static final String PBAdjustValueEnterInput = "//input[@id='inputField_BPVBPV1-2526-00367-00001']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentOk = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentSearchField = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentSearchGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String GstClafField = "//*[@id='report_2407683130605970547_catch']/dl/dd[12]";
	/** @locatorType Xpath */
	public static final String SubvouTypeInput = "//input[@data-testid='P11613101017_SUPHD_GRN_REFER']";
	/** @locatorType Xpath */
	public static final String LocationInput = "//input[@data-testid='P11613101017_SUPHD_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String HeaderLocationField = " //span[@data-testid='P11613101001_SUPHD_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String HeaderStatusField = " //span[@data-testid='Status']";
	/** @locatorType Xpath */
	public static final String OpenExpandButton = "//button[@id='t_Button_rightControlButton']";
	/** @locatorType Xpath */
	public static final String PendingPo = "//button[@data-testid='PendingPO']";
	/** @locatorType Xpath */
	public static final String IframePendingPo = "//iframe[@title='Pending PO']";
	/** @locatorType Xpath */
	public static final String PrDocNo = "//button[@id='P116131005_PO_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButtonPo = "//button[@data-testid='SearchPO']";
	/** @locatorType Xpath */
	public static final String LocationIdField = "//span[@id='P11613101001_SUPHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String UnitIdField = "//input[@id='P11613101001_SUPHD_PLANT']";
	/** @locatorType Xpath */
	public static final String SupplierDescField = "//input[@id='P11613101001_SUPPLIER_NAME']";
	/** @locatorType Xpath */
	public static final String BillDate = "//input[@id='P11613101001_SUPHD_SUPLR_DOC_D_input']";
	/** @locatorType Xpath */
	public static final String BillNumber = "//input[@id='P11613101001_SUPHD_SUPLR_DOC_N']";
	/** @locatorType Xpath */
	public static final String GetDocument = "//button[@data-testid='GetDoc']";
	/** @locatorType Xpath */
	public static final String GetDocPendingPo = "//iframe[@title='Pending PO']";
	/** @locatorType Xpath */
	public static final String PurchaseDocInputField = "//input[@data-testid='P116131005_PO_NO']";
	/** @locatorType Xpath */
	public static final String SearchButtonGetDoc = "//button[@data-testid='SearchPO']";
	/** @locatorType Xpath */
	public static final String CheckBoxPendingPo = "//table[@id='4695452701146094907']/tbody/tr[2]/td[13]";
	/** @locatorType Xpath */
	public static final String OkButtonPendingPO = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String ItemField = "(//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td)[3]";
	/** @locatorType Xpath */
	public static final String ItemLovField = "//button[@id='C1937854037615995845_lov_btn']";
	/** @locatorType Xpath */
	public static final String InvQty = "//input[@data-testid='SUPLN_INV_QTY']";
	/** @locatorType Xpath */
	public static final String InvQtyField = "(//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td)[9]";
	/** @locatorType Xpath */
	public static final String PBAddTypeExp = "//select[@id='C2487000269160585286']";
	/** @locatorType Xpath */
	public static final String PBCostField = "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[10]";

	public static String PBCostField(int i) {
		return "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[10]";

	}

	/** @locatorType Xpath */
	public static final String PBCostInput = "//input[@data-testid='SUPLN_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String StockTransferHsnLov = "//input[@data-testid='SUPLN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String StockTransferQtyInput = "//input[@id='qty']";
	/** @locatorType Xpath */
	public static final String StockTransferTypeQtyField = "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String StockTransferTypeHsnField = "//*[@id=\"IN_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String GstSupplyType = "//SELECT[@data-testid='SUPLN_TAX_EXMPT_FLAG']";
	/** @locatorType Xpath */
	public static final String GstSupplyTypeField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[25]";
	/** @locatorType Xpath */
	public static final String TdDiscPercent = "//INPUT[@data-testid='SUPLN_TRD_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String TdDiscField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[33]";
	/** @locatorType Xpath */
	public static final String SdDiscField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[36]";
	/** @locatorType Xpath */
	public static final String SdDiscPercent = "//INPUT[@data-testid='SUPLN_SPL_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String CashDiscField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[39]";
	/** @locatorType Xpath */
	public static final String CashDiscPercent = "//INPUT[@data-testid='SUPLN_CASH_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String AllowIgstFlag = "//input[@data-testid='P11613101001_ALOW_IGST_CSGST']";
	/** @locatorType Xpath */
	public static final String PendingPurchaseBill = "//button[@data-testid='PendingBills']";

}