package commonlocators.financials;

import commonfunctions.CommonFunctions;
/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PurchaseBillsLocators {
	/** @locatorType Xpath */
	public static final String PendingGrn = "//button[@data-testid='PendingGRN']";
	/** @locatorType Xpath */
	public static final String GrnNo = "//input[contains(@data-testid,'GRN_NO')]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Iframe = "//iframe";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='SearchGRN']";

	/** @locatorType Xpath */
	public static final String CheckBox = "(//div[@id='PEND_data_panel']//table)[1]/tbody/tr[2]//input[@type='checkbox']";

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
	public static final String ApproveOkButton = "//span[text()='Ok']/..";

	/** @locatorType Xpath */
	public static final String ApprovedStatus(String PbNo) {
		return "//a[text()='" + PbNo + "']/../../td[15]";
	}

	/** @locatorType Xpath */
	public static final String TableData = "//div[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String TDSU_S = "//div[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[27]";
	/** @locatorType Xpath */
	public static final String TDSLovButton = "//input[@data-testid='SUPLN_TDS_US_ID']/following-sibling::button";
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
	public static final String PendingFrame = "//iframe[@title='Create Purchase Bills']";
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
	public static final String GlAccLoc = "//input[@data-testid='SUPLN_ACCT_DESC']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String UnitCostField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[10] ";
	/** @locatorType Xpath */
	public static final String UnitCost = "//input[@data-testid='SUPLN_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String HsnCodeField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @locatorType Xpath */
	public static final String HsnCodeLov = "//input[@data-testid='SUPLN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SaveButtonLines = "//button[@data-testid='IN_SAVE']";
	/** @locatorType Xpath */
	public static final String CreateJvButton = "//button[@data-testid='CreJournal']";
	/** @locatorType Xpath */
	public static final String PostButtonH = "//button[@data-testid='POST']";
	/** @locatorType Xpath */
	public static final String WfOkButtton = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String gstClafField = "//*[@id='report_2482465621170058410_catch']/dl/dd[12]";
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
	public static final String AllowIgstCheckFlag = "//label[text()='Allow Local/IGST Tax ']";
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
	public static final String InSectionField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[64]";
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
	public static final String CheckBoxPendingPo = "(//div[@id='PEND_data_panel']//table/tbody/tr/td)[13]//input[@type='checkbox']";
	/** @locatorType Xpath */
	public static final String OkButtonPendingPO = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String ItemField = "(//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td)[3]";
	/** @locatorType Xpath */
	public static final String ItemLovField = "//input[@data-testid='PROD_ID_REV']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String InvQty = "//input[@data-testid='SUPLN_INV_QTY']";
	/** @locatorType Xpath */
	public static final String InvQtyField = "(//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td)[9]";
	/** @locatorType Xpath */
	public static final String PBAddTypeExp = "//select[@data-testid='SUPLN_TYPE']";
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
	/** @locatorType Xpath */
	public static final String ViewButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(9) > a";
	/** @locatorType Xpath */
	public static final String ViewButtonFrame = "//*[@id='apex_dialog_1']/iframe";
	/** @locatorType Xpath */
	public static final String AddButtonViewTap = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String BillDocNo = "//input[@id='C1241015438493988573']";//TODO
	/** @locatorType Xpath */
	public static final String PayAmt = "//input[@id='C1241009465375988566']";//TODO
	/** @locatorType Xpath */
	public static final String SaveButtonView = "//button[@id='B1144948769391115711']";//TODO
	/** @locatorType Xpath */
	public static final String CloseButton = "//*[@id=\"t_PageBody\"]/div[11]/div[1]/button/span";
	/** @locatorType Xpath */
	public static final String BillDocField = "//*[@id=\"ig_line1_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[2]";//TODO
	/** @locatorType Xpath */
	public static final String PayamountField = "//*[@id=\"ig_line1_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[4]";//TODO
	/** @locatorType Xpath */
	public static final String AdjButton = "//label[text()='Adjustments']";
	/** @locatorType Xpath */
	public static final String LoadButton = "//button[@id='loadadj']";
	/** @locatorType Xpath */
	public static final String CheckBoxAdjTap = "//*[@id='5938213119089255408_orig']/tbody/tr[2]/td[11]";
	/** @locatorType Xpath */
	public static final String FrameAdjTap = "//iframe[@title='Advance Adjustment ']";
	/** @locatorType Xpath */
	public static final String OkbuttonAdjTap = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String PB_Adj_InputGO_Btn = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String PB_Adj_InputField_Btn = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String TdsExemptedFlag = "//label[@id='P11613101001_SUPHD_TDS_EXMP_FL_LABEL']";
	/** @locatorType Xpath */
	public static final String checkboxall = "//*[@id='C3519425619083125876']";
	/** @locatorType Xpath */
	public static final String TdsSectioninput = "//input[@data-testid='SUPLN_TDS_US_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String TdsSectionField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[27]";

	/** @locatorType Xpath */
	public static final String TdsSectionFieldk(int i) {
		return "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[27]";

	}

	/** @locatorType Xpath */
	public static final String pendigPayableAdv = "//span[text()='Pending  Payables - Advance']";
	/** @locatorType Xpath */
	public static final String framePendingPayablesBP = "//iframe[@title='Pend. Payables Advance']";
	/** @locatorType Xpath */
	public static final String SearchButton1 = "//button[@id='B3540088164584403664']";
	/** @locatorType Xpath */
	public static final String DocNoInput = "//input[@data-testid='DocNo']";
	/** @locatorType Xpath */
	public static final String SelectAllFlag = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String CreateButton = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String VouSelect = "//select[@id='P34131022_BCV_DOC_TYPE']";
	/** @locatorType Xpath */
	public static final String ModeSelect = "//select[@id='P34131022_BCV_PAY_TYPE']";
	/** @locatorType Xpath */
	public static final String BankLov = "//BUTTON[@ID='P34131022_BCV_BC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String Okbuttonbankpay = "//button[@id='B3245068807575221734']";
	/** @locatorType Xpath */
	public static final String PaymentAdvancePoSearchField = "//input[@data-testid='PONo']";

	/** @locatorType Xpath */
	public static final String SelectBPPendingPayablesAdvCheckBox(String poList) {
		return "//td[contains(text(),' " + poList + " ' )]/../td[13]/";
	}

	/** @locatorType Xpath */
	public static final String EraseButton = "(//button[@id='Clear'])[1]";
	/** @locatorType Xpath */
	public static final String PendingPoSearchInputField = "//input[@id='P1615131100_PO_NO']";
	/** @locatorType Xpath */
	public static final String SearchButtonInPendingGrn = "//button[@data-testid='Search']";

	/** @locatorType Xpath */
	public static final String SelectCheckboxAgainstPO(String PoDocs) {
		return "//td[normalize-space(text())='" + PoDocs + "']/ancestor::tr//input[@type='checkbox']";
	}

	/** @locatorType Xpath */
	public static final String CreateGrnButton = "//button[@data-testid='GRNP_PEND_PUR_CRT_GRN']";
	/** @locatorType Xpath */
	public static final String PO_Inv_Prefix_Lov = "//button[@id='P116131005_INV_PFX_lov_btn']";
	/** @locatorType Xpath */
	public static final String Po_Inv_Prefix_Field = "//input[@id='P116131005_INV_PFX']";
	/** @locatorType Xpath */
	public static final String Pending_Prefix_Lov_button = "//input[contains(@data-testid,'INV_PFX')]/following-sibling::button";
	
	/** @locatorType Xpath */
	public static final String GlAccDescField1  = "(//TABLE[@data-testid='IN_Result_Data'])[2]";
	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLoc() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "GL Acct. Desc.");
	}
	
	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocunitprice() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "Unit Cost");
	}
	
	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocHsnCode() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "HSN/SAC Code");
	}
	
	/** @locatorType Xpath */
	public static final String PurchaseBillsLineWiseGstType() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "GST Supply");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstType() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "Type");
	}
	
	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstInelSection() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "Inel. Section");
	}
	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstTds() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "TDS u/s ID");
	}
	
	/** @locatorType Xpath */
	public static final String PurchaseBillsLineWiseItem() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "Item");
	}
	
	/** @locatorType Xpath */
	public static final String PurchaseBillsLineWiseInvQty() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(GlAccDescField1, "Inv. Qty.");
	}
	
	/** @locatorType Xpath */
	public static final String PurchaseBillsExpandButton = "(//div[@aria-label='Purchase, Tax & Other Details']//button)[1]" ;
	/** @locatorType Xpath */
	public static final String Payment_LineDetails_Btn = "//table[@data-testid='R1088051364275366746_Result_Data']/tbody/tr/td[1]/a/span";
	/** @locatorType Xpath */
	public static final String Payment_ViewButton = "//span[@class='fa fa-code-group']";

}