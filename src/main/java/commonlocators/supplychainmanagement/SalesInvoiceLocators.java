package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
public class SalesInvoiceLocators {

	/** @Locator XPATH */
	public static final String LOGINBTN = "//button[@id='loginbtn']";
	/** @Locator XPATH */
	public static final String UserName = "//input[@id ='P9999_USERNAME']";
	/** @Locator XPATH */
	public static final String Password = "//input[@name ='P9999_PASSWORD']";
	/** @Locator XPATH */
	public static final String SignBtn = "//button[@class='-Button t-Button--hot t-Button--stretch']";
	/** @Locator XPATH */
	public static final String USER_ID = "TVSAADMIN";
	/** @Locator XPATH */
	public static final String PASS = "TvsA@2024";
	/** @Locator XPATH */
	public static final String DashboardBtn = "//button[@id='t_Button_navControl']";
	/** @Locator XPATH */
	public static final String Supplychain = "(//span[@class='a-TreeView-toggle'])[5]";
	/** @Locator XPATH */
	public static final String sales_Node = "//*[@id='t_TreeNav_19']/div[2]/span[2]";
	/** @Locator XPATH */
	public static final String Sales_Trans = "//*[@id='t_TreeNav_22']/div[2]/span[2]";
	/** @Locator XPATH */
	public static final String Sales_Inv = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[10]";
	/** @Locator XPATH */
	public static final String Sales_Inv_Pend_Btn = "//button[@data-testid='SALES INVOICE PENDING ICON']";
	/** @Locator XPATH */
	public static final String SI_Add_Icon = "//button[@data-testid='SALES INVOICE ADD ICON']";
	/** @Locator XPATH */
	public static final String SI_header = "//h1[contains(@id,'ui-id-2')]";
	/** @Locator XPATH */
	public static final String Loc_ID_Btn = "//input[@data-testid='P1915131343_SIHD_PLNT_LOC_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String SI_Type_Btn = "//input[@data-testid='P1915131343_SIHD_TYPE']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Customer_ID_Btn = "//input[@data-testid='P1915131343_SIHD_SUPLR_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Create_SI = "//button[@data-testid='SI CREATE TICK ICON']";
	/** @Locator XPATH */
	public static final String Invoice_column = "//*[@id='P191513109001_SIHD_EXC_INV_PFX']";
	/** @Locator XPATH */
	public static final String Invoice_Pfx = "//input[@data-testid='P191513109001_SIHD_EXC_INV_PFX']/following-sibling::button";
	/** @Locator XPATH */
	public static final String LineWise_Add_Btn = "//button[@data-testid='SI LINE ADD ICON']";
	/** @Locator XPATH */
	public static final String SILinesTable = "(//div[@id='ig_line_ig_grid_vc']//table)[2]";

	/** @Locator XPATH */
	public static final String Item_Column() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "Item") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String Item_Lov_Btn = "//input[@data-testid='SILN_PROD_ID_REV']/following-sibling::button";

	/** @Locator XPATH */
	public static final String Sales_GL_Acc_Desc_Column() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "Sales GL A/C Desc.")
				+ ")[1]";
	}

	/** @Locator XPATH */
	public static final String Sales_GL_Acc_Desc_LOV_Btn = "//input[@data-testid='SILN_SAL_ACCT_ID']/following-sibling::button";

	/** @Locator XPATH */
	public static final String Invoice_Column_Qty() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "Invoice Qty.")
				+ ")[1]";
	}

	/** @Locator XPATH */
	public static final String Invoice_Qty = "//input[@data-testid='SILN_INV_QTY']";

	/** @Locator XPATH */
	public static final String UnitCost_Column() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "Unit Price") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String UnitCost_Input = "//input[@data-testid='SILN_PRICE1']";
	/** @Locator XPATH */
	public static final String SI_Doc_NO = "//span[@data-testid='P191513109001_SIHD_DOC_NO']";
	/** @Locator XPATH */
	public static final String Header_Tick_Icon = "//button[@data-testid='SAVE']";
	/** @Locator XPATH */
	public static final String Header_CPC_LOV_Btn = "//input[@data-testid='P191513109001_SIHD_CC_CODE']/following-sibling::button";

	/** @Locator XPATH */
	public static final String HSNcode_Column() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "HSN Code") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String HSN_Code_LOV_Btn = "//input[@data-testid='SILN_HSN_CODE']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Headerwise_TickIcon = "//button[@data-testid='SAVE']";
	/** @Locator XPATH */
	public static final String Alloc_ls_batch_Btn = "//button[@data-testid='SI ALLOC.LS/BATCH BUTTON']";
	/** @Locator XPATH */
	public static final String Pick_Btn = "//button[@data-testid='SI PICK BUTTON']";
	/** @Locator XPATH */
	public static final String Cre_Inv_No = "//button[@data-testid='SI CRE.INVOICE BUTTON']";
	/** @Locator XPATH */
	public static final String Post_Btn = "//button[@data-testid='SI POST BUTTON']";
	/** @Locator XPATH */
	public static final String WorkApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @Locator XPATH */
	public static final String RWWorkApprovalFrame = "//iframe[@title='Approval']";
	/** @Locator XPATH */
	public static final String ForwardApprovalLov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @Locator XPATH */
	public static final String OkButtonForward = "//button[@data-testid='Entry']";
	/** @Locator XPATH */
	public static final String Lut_No_LOV_Btn = "//input[@data-testid='P191513109001_LUT_NO']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Lut_No_Column = "//*[contains(@id,'LUT_NO')]";
	/** @Locator XPATH */
	public static final String CPC_Column = "//*[contains(@id,'SIHD_CC_CODE')]";
	/** @Locator XPATH */
	public static final String Details_Btn = "(//span[contains(@class,'fa fa-indent')])[1]";

	/** @Locator XPATH */
	public static final String TotalDiscountData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "Tot. Disc. Amt.")
				+ ")[1]//a";
	}

	/** @Locator XPATH */
	public static final String Detail_Btn_Iframe = "//iframe[contains(@title,'Sales Invoice Details')]";
	/** @Locator XPATH */
	public static final String Price_Basis_Field = "//span[contains(@id,'SILN_PRICE_BASIS_DISPLAY')]";
	/** @Locator XPATH */
	public static final String Line_Det_save_Btn = "//button[@data-testid='SAVE'][@title='Apply Changes']";
	/** @Locator XPATH */
	public static final String PendingSORadioBtn = "//button[@data-testid='Pending_SO']";
	/** @Locator XPATH */
	public static final String PendingSOIframe = "//iframe[@title='Pending Invoice']";
	/** @Locator XPATH */
	public static final String PendingSO_SearchBar = "//input[@title='Search Report']";
	/** @Locator XPATH */
	public static final String PendingSO_SearchBarGOBtn = "//button[@id='sales_order_search_button']";
	/** @Locator XPATH */
	public static final String PendingSO_CreateInvoiceBtn = "//span[text()='Create Invoice']/parent::button";

	/** @Locator XPATH */
	public static final String PendingSO_SOCheckBox(String SONum) {
		return "//input[@id='checkbox_" + SONum + "1']";
	}

	/** @Locator XPATH */
	public static final String SalesInvoiceTradeAssemblePrice = "//input[contains(@id,'SILN_TRD_ASSBL_VAL')]";
	/** @Locator XPATH */
	public static final String SalesInvoiceTradeDiscountPrice = "//input[contains(@id,'SILN_DISC_PCT')]";
	/** @Locator XPATH */
	public static final String SalesInvoiceTradeAssembleAmount = "//input[contains(@id,'SILN_DISC_AMT')]";
	/** @Locator XPATH */
	public static final String SalesInvoiceSpecialAssblPrice = "//input[contains(@id,'SILN_SPL_ASSBL_VAL')]";
	/** @Locator XPATH */
	public static final String SalesInvoiceSpecialDiscountPrice = "//input[contains(@id,'SILN_SPL_DISC_PCT')]";
	/** @Locator XPATH */
	public static final String SalesInvoiceSpecialAssblAmount = "//input[contains(@id,'SILN_SPL_DISC_AMT')]";
	/** @Locator XPATH */
	public static final String SalesInvoiceCashAssemblePrice = "//input[contains(@id,'SILN_CASH_ASSBL_VAL')]";
	/** @Locator XPATH */
	public static final String SalesInvoiceCashDiscountPrice = "//input[contains(@id,'SILN_CASH_DISC_PCT')]";
	/** @Locator XPATH */
	public static final String SalesInvoiceCashAssembleAmount = "//input[contains(@id,'SILN_CASH_DISC_AMT')]";
	/** @Locator XPATH */
	public static final String SaleInvoiceWarehouseShipFrom = "//input[contains(@id,'SILN_STORE_ID')]";
	/** @Locator XPATH */
	public static final String SaleInvoiceStockQuantity = "//input[contains(@id,'ONHAND_QTY')]";
	/** @Locator XPATH */
	public static final String SaleInvoiceOrderInvoiceQuantity = "//input[contains(@id,'SILN_STK_INV_QTY')]";
	/** @Locator XPATH */
	public static final String FindSalesInvoiceNoLovBtn = "//button[contains(@id,'DOC_NO_lov_btn')]";
	/** @Locator XPATH */
	public static final String FindSalesInvoiceSearchBtn = "//button[@data-testid='Find_line']";
	/** @Locator XPATH */
	public static final String InvoiceQntyTableData = "//table/tbody/tr/td[9]";
	/** @Locator XPATH */
	public static final String UnitPriceTableData = "//table/tbody/tr/td[10]";

	/** @Locator XPATH */
	public static final String DiscountAmountTableData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "Tot. Disc. Amt.")
				+ ")[1]";
	};

	/** @Locator XPATH */
	public static final String LineAmountTableData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "Line Amt") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String TaxPercentField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "Tax %") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String IGSTTaxAmountField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "IGST Amt.") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String CGSTTaxAmountField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "CGST Amt.") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String SGSTTaxAmountField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "SGST Amt.") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String UTGSTTaxAmountField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SILinesTable, "UTGST Amt.") + ")[1]";
	}

	/** @Locator XPATH */
	public static final String TotalAmountDisplayField = "//input[@id='P191513109001_SIHD_TOT_AMT']";

	/** @Locator XPATH */
	public static final String SIStatusTableText(String SINumber) {
		return "//*[text()='" + SINumber + "']/../td[11]/div";
	}

	/** @Locator XPATH */
	public static final String Proforma_Inv_Node = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[9]";
	/** @Locator XPATH */
	public static final String Proforma_Inv_Pend_Btn = "//button[@data-testid='PROFORMA INVOICE PENDING ICON']";
	/** @Locator XPATH */
	public static final String Proforma_FindPage_Add_Btn = "//button[@data-testid='PROFORMA INVOICE ADD ICON']";
	/** @Locator XPATH */
	public static final String Proforma_Inv_Loc_Btn = "//input[@data-testid='P1915131085_PIHD_PLNT_LOC_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String PI_Type_Field = "//input[@data-testid='P1915131085_PIHD_TYPE']/following-sibling::button";
	/** @Locator XPATH */
	public static final String PI_Customer_Name = "//input[@data-testid='P1915131085_PIHD_CUST_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String PI_TickIcon = "//button[@data-testid='PI CREATE TICK ICON']";
	/** @Locator XPATH */
	public static final String Proforma_Inv_Pfx_Btn = "//input[@data-testid='P191513108501_PIHD_INV_PFX']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Proforma_inv_Header_TickIcon = "//button[@data-testid='PI HEADER TICK ICON']";
	/** @Locator XPATH */
	public static final String PI_Header_Success_Msg = "//h6[@class='succ_msg']";
	/** @Locator XPATH */
	public static final String PI_Header_Close_Success_msg = "(//button[@title='Close'])[1]";
	/** @Locator XPATH */
	public static final String PI_Line_Add_Icon = "//button[@data-testid='PI LINE ADD ICON']";
	/** @Locator XPATH */
	public static final String PI_SO_Ref_Column = "//*[@id=\"line1_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @Locator XPATH */
	public static final String PI_SO_Ref_Btn = "//input[@data-testid='PILN_SO_SCHLD_DESC']/following-sibling::button";
	/** @Locator XPATH */
	public static final String PI_Item_ID_column = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @Locator XPATH */
	public static final String PI_Item_Id_Btn = "//input[@data-testid='DUM_PROD_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String PI_Inv_Qty = "//input[@data-testid='PILN_PROD_QTY1']";
	/** @Locator XPATH */
	public static final String PI_Qty_Column = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @Locator XPATH */
	public static final String PI_UnitCost_Column = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[8]";
	/** @Locator XPATH */
	public static final String PI_UnitCost = "//input[@data-testid='PILN_SALES_PRICE1']";
	/** @Locator XPATH */
	public static final String PI_Discount_Column = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]";
	/** @Locator XPATH */
	public static final String PI_HSN_Column = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @Locator XPATH */
	public static final String PI_HSN_Btn = "//input[@data-testid='PILN_HSN_CODE']/following-sibling::button";
	/** @Locator XPATH */
	public static final String PI_Line_tickIcon = "//button[@data-testid='save_ln']";
	/** @Locator XPATH */
	public static final String PI_Post_Btn = "//button[@data-testid='PI POST BUTTON']";
	/** @Locator XPATH */
	public static final String PI_DocNo = "//span[contains(@id,'P191513108501_PIHD_DOC_NO_DISPLAY')]";
	/** @Locator XPATH */
	public static final String PI_Pend_Loc_Id_Btn = "//button[@id='P1915131084_LOCATION_lov_btn']";
	/** @Locator XPATH */
	public static final String PI_Pend_Iframe = "//iframe[@title='Pending Proforma Invoice ']";
	/** @Locator XPATH */
	public static final String PI_Pend_SO_No = "//input[@data-testid='P1915131084_SO_NO']/following-sibling::button";
	/** @Locator XPATH */
	public static final String PI_Pend_SO_No_Column = "//input[contains(@id,'SO_NO')]";
	/** @Locator XPATH */
	public static final String PI_Pend_Search_Btn = "//button[@data-testid='Save']";
	/** @Locator XPATH */
	public static final String PI_Pend_SelectAll = "//input[@id='check_all']";
	/** @Locator XPATH */
	public static final String PI_Pend_Create_PI = "//button[@id='B1']";
	/** @Locator XPATH */
	public static final String Sale_GST_cls = "//input[contains(@id,'SIHD_GST_CUST_TYPE_DESC')]";
	/** @Locator XPATH */
	public static final String Sale_GST_Type = "//input[contains(@id,'SIHD_GST_REG_TYPE_DESC')]";
	/** @Locator XPATH */
	public static final String Location_Detail_Iframe = "//iframe[@title='Location Details']";
	/** @Locator XPATH */
	public static final String Location_Details_Btn = "//button[@data-testid='Location_Details']";
	/** @Locator XPATH */
	public static final String Customer_Name = "//input[@id='P191513109001_SIHD_SUPLR_NAME']";
	/** @Locator XPATH */
	public static final String Location_det_tickIcon = "//button[@data-testid='Save']";
	/** @Locator XPATH */
	public static final String SI_Currency = "//span[contains(@id,'SIHD_CURRENCY_DISPLAY')]";
	/** @Locator XPATH */
	public static final String SI_Header_Loc_ID = "//span[contains(@id,'SIHD_PLNT_LOC_ID_DISPLAY')]";
	/** @Locator XPATH */
	public static final String SI_Header_Unit = "//input[contains(@id,'SIHD_PLANT')]";
	/** @Locator XPATH */
	public static final String SI_Header_Type = "//span[contains(@id,'SIHD_TYPE_DISPLAY')]";
	/** @Locator XPATH */
	public static final String SI_Pend_SO_Btn = "//button[@data-testid='Pending_SO']";
	/** @Locator XPATH */
	public static final String SI_Pend_Iframe = "//iframe[@title='Pending Invoice']";
	/** @Locator XPATH */
	public static final String SI_Pend_SO_No_Lov = "//input[@id='P1915131087_ORD_NO']/following-sibling::button";
	/** @Locator XPATH */
	public static final String SI_Pend_SO_No_Column = "//input[@id='P1915131087_ORD_NO']";
	/** @Locator XPATH */
	public static final String SI_Pend_Search_Btn = "//button[@data-testid='Find']";
	/** @Locator XPATH */
	public static final String SI_Pend_SelectAll = "(//input[@id='check_all'])[1]";
	/** @Locator XPATH */
	public static final String SI_Pend_CreateInvoice_Btn = "//button[@data-testid='Sales_Invoice']";
	/** @Locator XPATH */
	public static final String PI_Header_PaymentTermColumn = "//input[@id='P191513108501_PIHD_PAY_TERM']";
	/** @Locator XPATH */
	public static final String PI_Header_PaymentTerm_LOV_Btn = "//button[@id='P191513108501_PIHD_PAY_TERM_lov_btn']";
	/** @Locator XPATH */
	public static final String Pend_From_Saleinvoice_Customer = "//span[contains(@id,'SIHD_SUPLR_NAME_DISPLAY')]";
	/** @Locator XPATH */
	public static final String SI_Pend_CS_Btn = "//button[@id='B990529750209838108']";
	/** @Locator XPATH */
	public static final String SI_Pend_CS_SelectAll = "//input[@id='check1_all']";
	/** @Locator XPATH */
	public static final String SI_Pend_CS_CreateInvoice_Btn = "//button[@id='B2']";
	/** @Locator XPATH */
	public static final String SI_Unpick_Btn = "//button[@data-testid='Unpick']";
	/** @Locator XPATH */
	public static final String SI_Cancel_Btn = "//button[@data-testid='Cancel']";
	/** @Locator XPATH */
	public static final String SI_Cancel_OK_Btn = "//button[@data-testid='Cancel_OK']";
	/** @Locator XPATH */
	public static final String SI_Cancel_Reason = "//input[@data-testid='P191513109001_CANCEL']";
	/** @Locator XPATH */
	public static final String SI_Linewise_Delete_Btn = "//span[@class='fa fa fa-trash-o']";
	/** @Locator XPATH */
	public static final String SI_LineWise_NoteAlert = "(//button[@title='Close'])[1]";
	/** @Locator XPATH */
	public static final String SalesInvoiceTabForHodUser = "//*[@id='t_TreeNav_291']/div[2]/a";
	/** @Locator XPATH */
	public static final String SalesInvoiceSelectAllBtn = "//*[@id='check_all']";
	/** @Locator XPATH */
	public static final String PendingSalesOrderSearchLOVBTN = "//*[@id='P1915131087_ORD_NO_lov_btn']";
	/** @Locator XPATH */
	public static final String PendingSalesOrderSearchBtn = "//button[@data-testid='Find']";
	/** @Locator XPATH */
	public static final String SalesInvoiceOverAllSaveBtn = "//button[@id='Save']";
	/** @Locator XPATH */
	public static final String SalesInvoiceNumberTextField = "//*[contains(@id,'SIHD_EXC_INV_NO_DISPLAY')]";

	/** @Locator XPATH */
	public static final String SI_LineDetails2_Tab = "//label[@for='P191513109011_TAB_1']";
	/** @Locator XPATH */
	public static final String SI_Shipset_Btn = "//button[@data-testid='Shipset_Item']";
	/** @Locator XPATH */
	public static final String SI_Shipset_Close_Btn = "(//button[@title='Close'])[7]";
	/** @Locator XPATH */
	public static final String SI_Detials_Promotional_DropDown = "//select[@id='P191513109011_SILN_PROMOTION_FLAG']";
	/** @Locator XPATH */
	public static final String Finance_node = "(//span[@class='a-TreeView-toggle'])[3]";
	/** @Locator XPATH */
	public static final String Finance_Transaction = "//span[@class='fa fa fa-paper-plane-o']";
	/** @Locator XPATH */
	public static final String Finance_Party_Document = "//span[@class='fa fa fa-file-o']";
	/** @Locator XPATH */
	public static final String Finance_Creditnote = "//span[@class='fa fa fa-sticky-note']";
	/** @Locator XPATH */
	public static final String CreditNote_Pending_Icon = "//button[@data-testid='Create_Sales_Return']";
	/** @Locator XPATH */
	public static final String CreditNote_Pending_Invoiced = "//button[@data-testid='Pending_Invoiced']";
	/** @Locator XPATH */
	public static final String CR_Pending_Invoiced_NO_LOV_Btn = "//input[@data-testid='P316151310943_INV_NO']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_Pending_Iframe = "//iframe[@title='Pending Invoice']";
	/** @Locator XPATH */
	public static final String CR_Pend_Search_Btn = "//button[@data-testid='Find']";
	/** @Locator XPATH */
	public static final String CR_Pend_SelectAll = "//*[@id=\"check_all\"]";
	/** @Locator XPATH */
	public static final String CR_Create_SalesReturn = "//button[@data-testid='Create_Sales_Return']";
	/** @Locator XPATH */
	public static final String CR_Pend_Ok_Btn = "//button[@data-testid='OK']";
	/** @Locator XPATH */
	public static final String CR_Close_Toast_Msg = "(//button[@title='Close'])[1]";
	/** @Locator XPATH */
	public static final String CR_CN_Pfx_Lov_Btn = "//input[@data-testid='P191513109101_SIHD_INV_PFX']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_Reason_Lov_Btn = "//input[@data-testid='P191513109101_SIHD_PUR_RTN_REAS_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_Upd_Narr_Btn = "//button[@data-testid='Upd_Narration']";
	/** @Locator XPATH */
	public static final String CR_Cre_Inv_Btn = "//button[@data-testid='Cre_Inv_No']";
	/** @Locator XPATH */
	public static final String CR_Create_Journal_Btn = "//button[@data-testid='Cre_Jrnl']";
	/** @Locator XPATH */
	public static final String CR_Post_Btn = "//button[@data-testid='Entry_Completed']";
	/** @Locator XPATH */
	public static final String CR_Save_Icon = "//button[@data-testid='SAVE']";
	/** @Locator XPATH */
	public static final String CR_Doc_No = "//input[@data-testid='P191513109101_SIHD_DOC_NO']";
	/** @Locator XPATH */
	public static final String CR_CN_Doc_No = "//input[@id='P191513109101_SIHD_INV_NO']";
	/** @Locator XPATH */
	public static final String SI_INV_No = "//span[contains(@id,'SIHD_EXC_INV_NO_DISPLAY')]";
	/** @Locator XPATH */
	public static final String CR_Find_Add_Icon = "//button[@data-testid='Add_Customer_Return']";
	/** @Locator XPATH */
	public static final String CR_Loc_LOV_Btn = "//input[@data-testid='P316151310942_SIHD_PLNT_LOC_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_Goods_Type = "//select[@data-testid='P316151310942_SIHD_RTN_GOODS_DTLS']";
	/** @Locator XPATH */
	public static final String CR_Customer_Lov_Btn = "//input[@data-testid='P316151310942_SIHD_CUST_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_Parameter_Inserting_Tick_Icon = "//button[@data-testid='Save']";
	/** @Locator XPATH */
	public static final String CR_Linewise_Add_Icon = "//button[@data-testid='Add_Line']";
	/** @Locator XPATH */
	public static final String CR_Item_Column = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @Locator XPATH */
	public static final String CR_Item_LOV_Btn = "//input[@data-testid='SILN_PROD_REV_DUM']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_Gl_Acct_Column = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @Locator XPATH */
	public static final String CR_GL_Acct_LOV_Btn = "//input[@data-testid='SILN_SAL_ACCT_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_CPC_Column = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @Locator XPATH */
	public static final String CR_CPC_Lov_Btn = "//input[@data-testid='SILN_SAL_CC_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_linewise_Type_Field = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @Locator XPATH */
	public static final String CR_Return_Qty_Column = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @Locator XPATH */
	public static final String CR_Return_Qty = "//input[@data-testid='SILN_INV_QTY']";
	/** @Locator XPATH */
	public static final String CR_HSN_Column = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @Locator XPATH */
	public static final String CR_HSN_Lov_Btn = "//input[@data-testid='SILN_HSN_CODE']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_LineWise_Save_Icon = "//button[@data-testid='Save_Line']";
	/** @Locator XPATH */
	public static final String CR_UnitPrice_Column = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[12]";
	/** @Locator XPATH */
	public static final String CR_UnitPrice = "//input[@data-testid='SILN_PRICE']";
	/** @Locator XPATH */
	public static final String CR_Linewise_Save_ToastMsg = "(//button[@title='Close'])[1]";
	/** @Locator XPATH */
	public static final String GateEntryNode = "//span[text()='Supply Chain']/ancestor::li/*/li[5]";
	/** @Locator XPATH */
	public static final String GateEntryTransaction = "//span[text()='Supply Chain']/ancestor::li/*/li[5]/*/li[2]";
	/** @Locator XPATH */
	public static final String GateEntryInward = "//span[text()='Supply Chain']/ancestor::li/*/li[5]/*/li[2]/*/li[1]";
	/** @Locator XPATH */
	public static final String GateEntryPendingIcon = "//button[@data-testid='Create_GE']";
	/** @Locator XPATH */
	public static final String GE_Pend_SalesReturn = "//button[@data-testid='Pending_sr']";
	/** @Locator XPATH */
	public static final String GE_Pend_Iframe = "//iframe[@title='Pending  Sales Return']";
	/** @Locator XPATH */
	public static final String GE_Pend_SI_Doc_Lov = "//input[@data-testid='P1615131107_INV_NO']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String LineDetails(int i) {
		return "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i
				+ "]/td[1]/a/span[contains(@class,'fa fa-ellipsis-v')]";
	}

	/** @locatorType Xpath */
	public static final String InvoiceUnitCostTable(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[9]";
	}

	public static final String SI_Store_ID = "//input[@id='P191513109011_SILN_STORE_ID']";
	/** @Locator XPATH */
	public static final String SI_LineDet_UnitCost = "//input[@id='P191513109011_SILN_UNIT_COST']";
	/** @Locator XPATH */
	public static final String SI_LineDet_Grossamt = "//input[@id='P191513109011_SILN_GROSS_AMT']";
	/** @Locator XPATH */
	public static final String SI_LineDet_TaxAmt = "//input[@id='P191513109011_SILN_TAX_AMT']";
	/** @Locator XPATH */
	public static final String SI_LineDet_NetAmt = "//input[@id='P191513109011_SILN_NET_AMT']";
	/** @Locator XPATH */
	public static final String SI_LineDet_Stk_Inv_Qty = "//input[@id='P191513109011_SILN_STK_INV_QTY']";
	/** @Locator XPATH */
	public static final String PI_LocID = "//input[@id='P191513108501_PIHD_PLNT_LOC_ID']";
	/** @Locator XPATH */
	public static final String PI_Unit = "//input[@id='P191513108501_PIHD_PLNT']";
	/** @Locator XPATH */
	public static final String PI_Type = "//input[@id='P191513108501_PIHD_TYPE']";
	/** @Locator XPATH */
	public static final String PI_Doc_Pfx = "//input[@id='P191513108501_PIHD_DOC_PFX']";
	/** @Locator XPATH */
	public static final String PI_Doc_No = "//input[@id='P191513108501_PIHD_DOC_NO']";
	/** @Locator XPATH */
	public static final String PI_Customer_ID = "//input[@id='P191513108501_PIHD_CUST_ID']";
	/** @Locator XPATH */
	public static final String PI_CustomerName = "//input[@id='P191513108501_CUST_DESC']";
	/** @Locator XPATH */
	public static final String Pro_Inv_Pfx = "//input[@id='P191513108501_PIHD_INV_PFX']";
	/** @Locator XPATH */
	public static final String Pro_Inv_Doc = "//input[@id='P191513108501_PIHD_INV_NO']";
	/** @Locator XPATH */
	public static final String PI_Line_Item = "//input[@id='C24211453294370612_HIDDENVALUE']";
	/** @Locator XPATH */
	public static final String PI_Line_Disc = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]";
	/** @Locator XPATH */
	public static final String PI_Line_Tax = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[11]";
	/** @Locator XPATH */
	public static final String PI_Line_IGST_AMT = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[12]";
	/** @Locator XPATH */
	public static final String PI_Line_CGST_AMT = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[13]";
	/** @Locator XPATH */
	public static final String PI_Line_SGST_AMT = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[14]";
	/** @Locator XPATH */
	public static final String PI_Line_UTGST = "//*[@id='line1_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[15]";
	/** @Locator XPATH */
	public static final String CR_Header_LocID = "//input[@id='P191513109101_SIHD_PLNT_LOC_ID']";
	/** @Locator XPATH */
	public static final String CR_Header_Unit = "//input[@id='P191513109101_SIHD_PLANT']";
	/** @Locator XPATH */
	public static final String CR_Header_Doc_Pfx = "//input[@id='P191513109101_SIHD_DOC_PFX']";
	/** @Locator XPATH */
	public static final String CR_Header_Doc_No = "//input[@id='P191513109101_SIHD_DOC_NO']";
	/** @Locator XPATH */
	public static final String CR_Header_Type = "//input[@id='P191513109101_SIHD_SUB_VOU_TYPE_DESC']";
	/** @Locator XPATH */
	public static final String CR_CustID = "//input[@id='P191513109101_SIHD_CUST_ID']";
	/** @Locator XPATH */
	public static final String CR_Cust_Name = "//input[@id='P191513109101_SIHD_CUST_NAME']";
	/** @Locator XPATH */
	public static final String CR_GoodsType = "//input[@id='P191513109101_SIHD_RTN_GOODS_DTLS']";
	/** @Locator XPATH */
	public static final String CR_Ref = "//input[@id='P191513109101_SIHD_RTN_SOURCE']";
	/** @Locator XPATH */
	public static final String CR_Header_Status = "//input[@id='P191513109101_SIHD_STATUS']";
	/** @Locator XPATH */
	public static final String GE_SR_Pend_Inv_Lov_Btn = "//button[@id='P1615131107_INV_NO_lov_btn']";
	/** @Locator XPATH */
	public static final String GE_SR_Search_Btn = "//button[@data-testid='Find']";
	/** @Locator XPATH */
	public static final String GE_Pend_SelectAll = "(//input[@id='check_all'])[1]";
	/** @Locator XPATH */
	public static final String GE_Pend_Curr_ProcQty = "//*[@id=\"2397415601543505368_orig\"]/tbody/tr[2]/td[14]";//TODO
	/** @Locator XPATH */
	public static final String GE_Pend_LS_Tab = "//span[@class='fa fa-balance-scale']";
	/** @Locator XPATH */
	public static final String GE_Pend_Curr_Qty_Column = "//*[@id=\"LS_GE_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @Locator XPATH */
	public static final String GE_Pend_Entering_Curr_Qty = "//input[@data-testid='process_qty']";
	/** @Locator XPATH */
	public static final String GE_LS_Iframe = "//iframe[@title='Lot /Serial']";
	/** @Locator XPATH */
	public static final String GE_LS_Save_Icon = "//button[@title='Save']";
	/** @Locator XPATH */
	public static final String Create_GE = "//button[@data-testid='CREATE_GE']";
	/** @Locator XPATH */
	public static final String After_Pend_NoteAlert = "//button[@data-testid='Back']";
	/** @Locator XPATH */
	public static final String GE_Doc_No = "//span[@id='P751310201_GEHD_DOC_NO_DISPLAY']";
	/** @Locator XPATH */
	public static final String GE_Driver_Name = "//input[@id='P751310201_GEHD_DRIVER_NAME']";
	/** @Locator XPATH */
	public static final String GE_Header_Save_Icon = "//button[@data-testid='SAVE']";
	/** @Locator XPATH */
	public static final String GE_Header_Post_Btn = "//button[@data-testid='Post']";
	/** @Locator XPATH */
	public static final String CR_GateEntry_Pend = "//button[@data-testid='Pending_Gate_Entry']";
	/** @Locator XPATH */
	public static final String CR_GE_Iframe = "//iframe[@title='Pending Gate Entry']";
	/** @Locator XPATH */
	public static final String CR_GE_Doc_No = "//input[@data-testid='P316151310944_DOC_NO']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CR_GE_Pend_Search = "//button[@data-testid='Find']";
	/** @Locator XPATH */
	public static final String CR_GE_Pend_SelectAll = "(//input[@id='check_all'])[1]";
	/** @Locator XPATH */
	public static final String CR_GE_CreateReturn_Btn = "//button[@data-testid='Create_Return']";
	/** @Locator XPATH */
	public static final String CR_GE_ToastMsg = "(//button[@title='Close'])[1]";
	/** @Locator XPATH */
	public static final String SI_Reverse_Btn = "//button[@data-testid='Rev_Sales_Inv']";

	/** @locatorType Xpath */
	public static final String SIDocNumberStatus(String SIdocno) {
		return "(//td[text()='" + SIdocno + "']/../td/a)[3]";
	}

	/** @Locator XPATH */
	public static final String SI_Reverse_Iframe = "//iframe[@title='Doc. Reversal']";
	/** @Locator XPATH */
	public static final String SI_Reverse_OK_Btn = "//button[@data-testid='btn_ok']";
	/** @Locator XPATH */
	public static final String SI_Reverse_ForwardApproval = "//button[@data-testid='Entry']";
	/** @Locator XPATH */
	public static final String SI_Charges_Tab = "//label[@for='P191513109001_TAB_LINE_1']";
	/** @Locator XPATH */
	public static final String SI_Charges_Add_Icon = "//button[@data-testid='add_tax']";
	/** @Locator XPATH */
	public static final String SI_Charge_ItemColumn = "//*[@id='IG_TAX_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @Locator XPATH */
	public static final String SI_Charge_Item_Lov_Btn = "//input[@data-testid='SIOTC_PROD_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String SI_Charge_Percentage_Column = "//*[@id='IG_TAX_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @Locator XPATH */
	public static final String SI_Charge_Percentage_Input = "//input[@data-testid='SIOTC_CHRG_PCT']";
	/** @Locator XPATH */
	public static final String SI_Charge_SAC_Column = "//*[@id=\"IG_TAX_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @Locator XPATH */
	public static final String SI_Charge_SAC_Lov_Btn = "//input[@data-testid='SIOTC_HSN_CODE']/following-sibling::button";
	/** @Locator XPATH */
	public static final String SI_Charge_Basis_Column = "//*[@id='IG_TAX_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @Locator XPATH */
	public static final String SI_Charge_Basis_Select_Cls = "//select[@data-testid='SIOTC_CHRG_BASIS']";
	/** @Locator XPATH */
	public static final String SI_Charge_Save_Icon = "//button[@data-testid='save_tax']";
	/** @Locator XPATH */
	public static final String SI_Charge_Amount_Column = "//*[@id='IG_TAX_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @Locator XPATH */
	public static final String SI_Charge_Amount_Input = "//input[@data-testid='SIOTC_CHRG_AMT']";
	/** @Locator XPATH */
	public static final String SI_General_Tab = "//label[@for='P191513109001_TAB_LINE_2']";
	/** @Locator XPATH */
	public static final String SI_Predispatch_Btn = "//button[@data-testid='Cre_pre_disp']";
	/** @Locator XPATH */
	public static final String CR_AcptQty_Column = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[11]";
	/** @Locator XPATH */
	public static final String CR_AcptQty_Input = "//input[@data-testid='SILN_ACCEPTED_QTY']";
	/** @Locator XPATH */
	public static final String SI_Detail_LabelName = "//h1[@id='ui-id-7']";

	// Recent Update

	public static final String CR_LS_Acpt_Qty = "//*[@id=\"ig_line1_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[5]";
	/** @Locator XPATH */
	public static final String CR_LS_Save_Icon = "//button[@data-testid='Save_line']";
	/** @Locator XPATH */
	public static final String CR_LS_Acpt_Input = "//input[@data-testid='SISLN_INV_QTY']";
	/** @Locator XPATH */
	public static final String Rework_Node = "//span[contains(text(),'PP & Shop Floor')]/ancestor::li/*/li[2]/*/li[3]";

	public static final String Rework_Order_Node = "//span[contains(text(),'PP & Shop Floor')]/ancestor::li/*/li[2]/*/li[3]/*/li[1]";
	/** @Locator XPATH */
	public static final String Rework_Pending_Icon = "//button[@data-testid='Rework_Pending_Icon']";
	/** @Locator XPATH */
	public static final String Rework_Pend_SalesRtn = "//button[@data-testid='Sales_Return']";
	/** @Locator XPATH */
	public static final String Rework_Pend_Iframe = "//iframe[@title='Sales Return']";
	/** @Locator XPATH */
	public static final String RW_Pend_Inv_Lov_Btn = "//input[@data-testid='P2017131225_INV_NO']/following-sibling::button";
	/** @Locator XPATH */
	public static final String RW_Pend_Search_Btn = "//button[@data-testid='FILTER']";
	/** @Locator XPATH */
	public static final String RW_Pend_CheckBox = "(//input[@type='checkbox'])[1]";
	/** @Locator XPATH */
	public static final String RW_Pend_CreateRework_Btn = "//button[@data-testid='Create_Rework']";
	/** @Locator XPATH */
	public static final String CR_Rtn_Qty = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[11]";
	/** @Locator XPATH */
	public static final String Rw_Rej_Qty = "//*[@id='IG_LINE_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[10]";
	/** @Locator XPATH */
	public static final String RW_Rej_Qty_input = "//input[@data-testid='RW_SR_RWK_QTY_Field']";
	/** @Locator XPATH */
	public static final String RW_Get_Rej_Qty = "//*[@id='IG_LINE_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[11]";
	/** @Locator XPATH */
	public static final String RW_Create_OK_Btn = "//button[@data-testid='RW_OK_btn']";
	/** @Locator XPATH */
	public static final String RW_Post_btn = "//button[@data-testid='RW_Post_btn']";
	/** @Locator XPATH */
	public static final String RW_Doc_No = "//span[@data-testid='RW_ORD_NO']";
	/** @Locator XPATH */
	public static final String ReworkOrd_Completion_Note = "//span[contains(text(),'PP & Shop Floor')]/ancestor::li/*/li[2]/*/li[3]/*/li[2]";
	/** @Locator XPATH */
	public static final String ReworkOrd_Completion_Pend_Icon = "//button[@data-testid='RWC_Pend_Btn']";
	/** @Locator XPATH */
	public static final String RWC_Pend_Iframe = "//iframe[@title='Pend. Rework Order']";
	/** @Locator XPATH */
	public static final String RWC_Doc_No_Lov_Btn = "//input[@data-testid='RWC_Pend_Ord_lov']/following-sibling::button";
	/** @Locator XPATH */
	public static final String RW_Forward_OK_btn = "//button[@id='B479819303601188310']";
	/** @Locator XPATH */
	public static final String RWC_Pend_Search_Btn = "//button[@data-testid='RWC_Pend_Search_btn']";
	/** @Locator XPATH */
	public static final String RWC_Pend_Repair_Column = "//*[@id='pendrework_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[11]";
	/** @Locator XPATH */
	public static final String RWC_Pend_Repair_input = "//input[@data-testid='RWC_Pend_Repair_Field']";
	/** @Locator XPATH */
	public static final String RWC_Pend_CheckBox = "(//input[@type='checkbox'])[1]";
	/** @Locator XPATH */
	public static final String RWC_Create_Completion_Btn = "//button[@data-testid='RWC_Pend_Create_btn']";
	/** @Locator XPATH */
	public static final String RWC_Create_OK_Btn = "//button[@data-testid='RWC_Pend_Ok_btn']";
	/** @Locator XPATH */
	public static final String RWC_Pend_Save_Icon = "//button[@data-testid='RWC_Pend_Save_btn']";
	/** @Locator XPATH */
	public static final String RWC_Close_Toast_Msg = "(//button[@title='Close'])[1]";
	/** @Locator XPATH */
	public static final String RWC_CreJournal_Btn = "//button[@data-testid='RWC_CJ_btn']";
	/** @Locator XPATH */
	public static final String RWC_Pend_Rework_Column = "//*[@id='pendrework_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[8]";
	/** @Locator XPATH */
	public static final String RWC_Doc_No = "//input[@data-testid='RWC_DOC_NO']";
	/** @Locator XPATH */
	public static final String ResourceUsageTab = "//label[@for='P1818131050_TAB_1']";
	/** @Locator XPATH */
	public static final String ResourceUsageAdd_Icon = "//button[@data-testid='RWC_Res_Add_btn']";
	/** @Locator XPATH */
	public static final String ResourceUsage_RW_Seq_No = "//*[@id='Resource_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @Locator XPATH */
	public static final String ResourceUsage_RW_Seq_No_Lov = "//input[@data-testid='RWC_Res_Seq_Field']/following-sibling::button";
	/** @Locator XPATH */
	public static final String WorkCenter_Lov_btn = "//input[@data-testid='RWC_Res_WC_Field']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Proc_Lov_Btn = "//input[@data-testid='RWC_Res_Proc_Field']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Resource_Lov_Btn = "//input[@data-testid='RWC_Res_Res_Field']/following-sibling::button";
	/** @Locator XPATH */
	public static final String RWC_FromDate_Today = "(//button[@class='a-Button a-DatePicker--today'])[1]";
	/** @Locator XPATH */
	public static final String RWC_FromDate_Done = "(//button[@class='a-Button a-DatePicker--close'])[1]";
	/** @Locator XPATH */
	public static final String RWC_ToDate_Next = "//td[@class='is-current']/following::td[1]";
	/** @Locator XPATH */
	public static final String RWC_ToDate_Today = "//*[@id=\"C14485702157279956998_dialog\"]/div/div[3]/div[2]/button[1]";
	/** @Locator XPATH */
	public static final String RWC_ToDate_Done = "//*[@id=\"C14485702157279956998_dialog\"]/div/div[3]/div[2]/button[2]";
	/** @Locator XPATH */
	public static final String RWC_Line_save_Icon = "//button[@data-testid='RWC_Res_Save_btn']";
	/** @Locator XPATH */
	public static final String RWC_Comp_Details = "//label[@for='P1818131050_TAB_0']";
	/** @Locator XPATH */
	public static final String RWC_Locator_Icon = "(//span[@class='fa fa-list'])[1]";
	/** @Locator XPATH */
	public static final String RWC_Locator_Iframe = "//iframe[@title='Locator Details']";
	/** @Locator XPATH */
	public static final String RWC_Locator_Add_icon = "//button[@data-testid='RWC_Loc_Add_Btn']";
	/** @Locator XPATH */
	public static final String RWC_Locator_Column = "//*[@id='ldls_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @Locator XPATH */
	public static final String RWC_Locator_Column_Lov = "//input[@data-testid='RWC_Loc_Field']/following-sibling::button";
	/** @Locator XPATH */
	public static final String RWC_Locator_Qty_Column = "//*[@id='ldls_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @Locator XPATH */
	public static final String RWC_Locator_Qty_Input = "//input[@data-testid='RWC_Qty_Field']";
	/** @Locator XPATH */
	public static final String RWC_Locator_Save_Icon = "//button[@data-testid='RWC_Loc_Save_Btn']";
	/** @Locator XPATH */
	public static final String RWC_Compl_Qty = "//*[@id='CD_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]";
	/** @Locator XPATH */
	public static final String RWC_Post_Btn = "//button[@data-testid='RWC_Post_btn']";
	/** @Locator XPATH */
	public static final String RWC_Locator_Close_Toast_Msg = "//span[@class='t-Icon icon-close']";
	/** @Locator XPATH */
	public static final String RWC_Scrap_Qty_Column = "//*[@id='pendrework_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[12]";
	/** @Locator XPATH */
	public static final String RWC_Scrap_Qty_Input = "//input[@data-testid='RWC_Pend_Scrap_Field']";
	/** @Locator XPATH */
	public static final String GetScrapItem = "//*[@id='scraps_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";

	/** @locatorType Xpath */
	public static final String ScrapItemTable(int i) {
		return "//*[@id='scraps_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[' + i + ']/td[3]";
	}

	/** @Locator XPATH */
	public static final String Scrap_Details_Tab = "//label[@for='P1818131050_TAB_3']";
	/** @Locator XPATH */
	public static final String RW_Header_LocID = "//span[@id='P1818131040_RWOHD_PLNT_LOC_ID_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Header_Unit = "//span[@id='P1818131040_RWOHD_PLNT_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Header_Rework_Ord_Pfx = "//span[@id='P1818131040_RWOHD_ORD_PFX_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Document_No = "//span[@id='P1818131040_RWOHD_ORD_NO_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Item_Field = "//span[@id='P1818131040_RWOHD_PROD_ID_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Item_Desc_Field = "//span[@id='P1818131040_ITEM_DESC_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Type_Field = "//input[@id='P18181310402_RWOHD_ORD_TYPE']";
	/** @Locator XPATH */
	public static final String RW_Lot_Field = "//span[@id='P1818131040_RWOHD_LOT_NO_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Source_Doc_No = "//span[@id='P1818131040_RWOHD_SOU_DOC_NO_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Sou_Pfx = "//span[@id='P1818131040_RWOHD_SOU_DOC_PFX_DISPLAY']";
	/** @Locator XPATH */
	public static final String RW_Qty_Field = "//span[@id='P1818131040_RWOHD_REWORK_QTY_DISPLAY']";
	/** @Locator XPATH */
	public static final String RWC_Header_LocID = "//span[@id='P1818131050_RWOCHD_PLNT_LOC_ID_DISPLAY']";
	/** @Locator XPATH */
	public static final String RWC_Header_Unit = "//span[@id='P1818131050_RWOCHD_PLNT_DISPLAY']";
	/** @Locator XPATH */
	public static final String RWC_Document_No = "//input[@id='P1818131050_RWOCHD_DOC_NO']";
	/** @Locator XPATH */
	public static final String RWC_Item_Field = "//span[@id='P1818131050_RWOCHD_PROD_ID_DISPLAY']";
	/** @Locator XPATH */
	public static final String RWC_SourceWH_Field = "//span[@id='P1818131050_RWOCHD_SOU_STORE_DISPLAY']";
	/** @Locator XPATH */
	public static final String SI_Header_Date_field = "//input[@id='P109001_SIHD_EXC_INV_DATE_input']";
	/** @Locator XPATH */
	public static final String SI_Header_Date = "//button[@class='a-Button a-DatePicker--today']";
	/** @Locator XPATH */
	public static final String SI_DocNo_DrillDown = "//table[@data-testid='SIR_Result_Data']/tbody/tr[2]/td[12]/a";
	/** @Locator XPATH */
	public static final String SI_Location_Field = "//INPUT[@data-testid='P1915131343_SIHD_PLNT_LOC_ID']";
	/** @Locator XPATH */
	public static final String SI_Type_Field = "//INPUT[@data-testid='P1915131343_SIHD_TYPE']";
	/** @Locator XPATH */
	public static final String SI_Frwd_Person = "//input[@data-testid='P236131090_FWD_PERSON']";
	/** @Locator XPATH */
	public static final String InvoiceDocNo = "//span[@data-testid='P191513109001_SIHD_EXC_INV_NO']";
	/** @Locator XPATH */
	public static final String Rework_Pend_Subcontract = "//button[@data-testid='Rework_Subcontract_btn']";
	/** @Locator XPATH */
	public static final String Rework_Pend_Subcontract_Iframe = "//iframe[@title='Insp .Cre Rtn / Rwk SCO']";
	/** @Locator XPATH */
	public static final String RW_Pend_SCO_Search_Btn = "//button[@data-testid='RW_SCO_Search_Btn']";
	/** @Locator XPATH */
	public static final String RW_SCO_Get_Rej_Qty = "//*[@id=\"ig_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[4]";
	/** @Locator XPATH */
	public static final String RW_SCO_InsideOutside_Qty = "//*[@id=\"ig_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[8]";
	/** @Locator XPATH */
	public static final String RW_SCO_InsideOutside_Qty_Input = "//input[@data-testid='Rework_Prim_OS_Field']";
	/** @Locator XPATH */
	public static final String RW_Pend_SCO_Save_Btn = "//button[@data-testid='RW_SCO_Save_Btn']";
	/** @Locator XPATH */
	public static final String RW_Pend_SCO_Create_Btn = "//button[@data-testid='RW_SCO_Create_Btn']";
	/** @Locator XPATH */
	public static final String RW_Pend_SCO_Create_OK_Btn = "//button[@data-testid='RW_SCO_Depart_OK_Btn']";
	/** @Locator XPATH */
	public static final String RW_Pend_SCO_Create_Dept_Lov = "//input[@id='P201713122002_DEPT']/following-sibling::button";
	/** @Locator XPATH */
	public static final String RW_Pend_GRN_Lov_Btn = "//input[@data-testid='RW_SCO_GRN_lov']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Rw_SCO_Inside_QtyField = "//*[@id=\"ig_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @Locator XPATH */
	public static final String Rw_SCO_Inside_QtyField_Input = "//input[@data-testid='Rework_Prim_IS_Field']";
	/** @Locator XPATH */
	public static final String RW_SCO_Create_OK_Btn = "//button[@data-testid='RW_SCO_OK_Btn']";
	/** @Locator XPATH */
	public static final String RW_Pend_Outside_Supl_Lov = "//input[@data-testid='Rework_Prim_SUP_Lov']/following-sibling::button";
	/** @Locator XPATH */
	public static final String CreatePreDispatchButton = "//button[@data-testid='Cre_pre_disp']";
	/** @Locator XPATH */
	public static final String SI_LineTable = "(//table[@data-testid='ig_line_ig_report_settings_Result_Data'])[2]";

	/** @locatorType Xpath */
	public static final String SIPageItemTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SI_LineTable, "Item");
	}

	/** @locatorType Xpath */
	public static final String SIPageGLAcctTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SI_LineTable, "Sales GL A/C Desc.");
	}

	/** @locatorType Xpath */
	public static final String SIPageInvQtyTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SI_LineTable, "Invoice Qty.");
	}

	/** @locatorType Xpath */
	public static final String SIPageUnitPriceTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SI_LineTable, "Unit Price");
	}

	/** @locatorType Xpath */
	public static final String SIPageHSNTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SI_LineTable, "HSN Code");
	}

	/** @Locator XPATH */
	public static final String PI_LineTable = "(//table[@data-testid='line1_ig_report_settings_Result_Data'])[2]";

	/** @locatorType Xpath */
	public static final String PIPageItemTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PI_LineTable, "Item ID");
	}

	/** @locatorType Xpath */
	public static final String PIPageSOrefTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PI_LineTable, "SO/Prj.Ref.");
	}

	/** @locatorType Xpath */
	public static final String PIPageQtyTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PI_LineTable, "PI Qty.");
	}

	/** @locatorType Xpath */
	public static final String PIPageUnitPriceTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PI_LineTable, "Unit Price");
	}

	/** @locatorType Xpath */
	public static final String PIPageHSNTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PI_LineTable, "HSN/SAC Code");
	}

	/** @Locator XPATH */
	public static final String CR_LineTable = "(//table[@data-testid='ig_line_ig_report_settings_Result_Data'])[2]";

	/** @locatorType Xpath */
	public static final String CRPageItemTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CR_LineTable, "Item");
	}

	/** @locatorType Xpath */
	public static final String CRPageGLAcctTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CR_LineTable, "GL Acct.Desc.");
	}

	/** @locatorType Xpath */
	public static final String CRPageCPCTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CR_LineTable, "CPC Desc.");
	}

	/** @locatorType Xpath */
	public static final String CRPageReturnQtyTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CR_LineTable, "Return Qty.");
	}

	/** @locatorType Xpath */
	public static final String CRPageHSNTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CR_LineTable, "HSN / SAC Code");
	}

	/** @locatorType Xpath */
	public static final String CRPageUnitPriceTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CR_LineTable, "Unit Price");
	}

	/** @Locator XPATH */
	public static final String SI_ShowMore = "//button[@data-testid='SHOW_MORE']";
	/** @Locator XPATH */
	public static final String SI_detail_Button = "//*[text()='Details']";
	/** @Locator XPATH */
	public static final String SI_detail_Promotional = "//div[@aria-label='Tax Details']//button";
	/** @Locator XPATH */
	public static final String SI_linedetail_Button = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[1]/a/span";
	/** @Locator XPATH */
	public static final String SI_Include_Charge = "//button[@data-testid='INC_CHARGE']";
	/** @Locator XPATH */
	public static final String CR_ShowMore = "//button[@data-testid='show_more']";
	/** @Locator XPATH */
	public static final String Header_Other_Tab = "//button[@data-testid='Others']";

}