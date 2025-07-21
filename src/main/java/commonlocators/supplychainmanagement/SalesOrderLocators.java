package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class SalesOrderLocators {
	/** @Locator Xpath */
	public static final String AddButtonSalesOrder = "//BUTTON[@data-testid='SO_Add_Button']";
	/** @Locator Xpath */
	public static final String SalesOrderHeader = "//h1[@id='ui-id-1']";
	/** @Locator Xpath */
	public static final String LocationLOV = "//button[contains(@id,'P1915131339_UNIT_LOC_lov_btn')]";
	/** @Locator Xpath */
	public static final String SalesSubVoucherTypeLOV = "//button[contains(@id,'P1915131339_SOH_ORDER_TYPE_lov_btn')]";
	/** @Locator Xpath */
	public static final String SalesTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]";
	/** @Locator Xpath */
	public static final String SalesTransactionTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]";
	/** @Locator Xpath */
	public static final String SalesOrderTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[6]";
	/** @Locator Xpath */
	public static final String CustomerLov = "//button[@id='P1915131339_SOH_CUST_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String TickIcon = "//button[@data-testid='TickIcon']";
	/** @Locator Xpath */
	public static final String SoNumber = "//span[@data-testid='P191513134001_SOH_ORDER_NO']";
	/** @Locator Xpath */
	public static final String NoteAlertCloseIcon = "//button[@title='Close']";
	/** @Locator Xpath */
	public static final String HeaderLocationField = "//span[@data-testid='HeaderLocationField']";
	/** @Locator Xpath */
	public static final String HeaderUnitField = "//span[@data-testid='HeaderUnitField']";
	/** @Locator Xpath */
	public static final String HeaderCustIDField = "//span[@id='P191513134001_SOH_CUST_ID_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderCustDescField = "//INPUT[@data-testid='P191513134001_SOH_CUST_NAME']";
	/** @Locator Xpath */
	public static final String HeaderCustIDFieldQuery = "//span[@data-testid='HeaderCustIDField']";
	/** @Locator Xpath */
	public static final String HeaderCustDescFieldQuery = "//span[@data-testid='P191513134001_SOH_CUST_NAME']";
	/** @Locator Xpath */
	public static final String LineWiseAddIcon = "//BUTTON[@data-testid='SO_LineWise_AddButton']";
	/** @Locator Xpath */
	public static final String ItemField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @Locator Xpath */
	public static final String ItemLov = "//button[@id='C9414537342012146836_lov_btn']/span";
	/** @Locator Xpath */
	public static final String ItemLovSearchField = "//input[contains(@class,'a-PopupLOV-search')]";
	/** @Locator Xpath */
	public static final String ItemInputField = "//input[@data-testid='SO_LN_PROD_ID']";
	/** @Locator Xpath */
	public static final String QtyField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @Locator Xpath */
	public static final String UnitCostField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @Locator Xpath */
	public static final String QtyInputField = "//input[@data-testid='SOQ_QTY_ORDERED']";
	/** @Locator Xpath */
	public static final String UnitCostInputField = "//input[@data-testid='SOQ_PRICE1']";
	/** @Locator Xpath */
	public static final String HSNCodeField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @Locator Xpath */
	public static final String HSNCodeLOV = "//button[@id='C11990275358524342024_lov_btn']/span";
	/** @Locator Xpath */
	public static final String HSNCodeInputField = "//input[@data-testid='HSNCodeLOV']";
	/** @Locator Xpath */
	public static final String LineWiseSaveIcon = "//BUTTON[@data-testid='SO_LineWise_SaveButton']";
	/** @Locator Xpath */
	public static final String PostButton = "//BUTTON[@data-testid='SO_Header_Wise_PostButton']";
	/** @Locator Xpath */
	public static final String WorkApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @Locator Xpath */
	public static final String ForwardApprovalLov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";

	public static final String CPForwardLOV = "//button[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @Locator Xpath */
	public static final String OkButtonForward = "//BUTTON[@ID='B1614624587133182023']";
	/** @Locator Xpath */
	public static final String FindSONumberLOV = "//button[@id='P1915131339_SO_NO_lov_btn']";
	/** @Locator Xpath */
	public static final String FindSearchButton = "//BUTTON[@data-testid='SO_Srch_Button']";
	/** @Locator Xpath */
	public static final String SOTotalAmountFieldTrans = "//input[@data-testid='SOTotalAmountFieldQuery']";
	/** @Locator Xpath */
	public static final String SOTotalAmountFieldQuery = "//span[@id='P191513134001_SOH_TOT_AMT_DISPLAY']";
	/** Locator @CssSelector */
	public static final String SOLineWiseDetailsButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(17) > a";
	/** @Locator Xpath */
	public static final String LinewisedetailsHeader = "(//h1[@class='ui-dialog-title'])[5]";
	/** @Locator Xpath */
	public static final String SOTradeDiscountpercentageField = "//input[@data-testid='SOTradeDiscountpercentageField']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountpercentageField = "//input[@data-testid='SOSpecialDiscountpercentageField']";
	/** @Locator Xpath */
	public static final String SOCashDiscountpercentageField = "//input[@data-testid='SOCashDiscountpercentageField']";
	/** @Locator Xpath */
	public static final String SOTradeDiscountAmountField = "//input[@data-testid='SOTradeDiscountAmountField']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountAmountField = "//input[@data-testid='SOSpecialDiscountAmountField']";
	/** @Locator Xpath */
	public static final String SOCashDiscountAmountField = "//input[@data-testid='SOCashDiscountAmountField']";
	/** @Locator Xpath */
	public static final String LineWiseDetailsSaveIcon = "//BUTTON[@data-testid='SO_LineWise_Details_SaveButton']";
	/** @Locator Xpath */
	public static final String SOGrossAmountField = "//input[@data-testid='SOGrossAmountField']";
	/** @Locator Xpath */
	public static final String SOTaxAmountField = "//input[@data-testid='TaxAmt']";
	/** @Locator Xpath */
	public static final String SODiscAmountField = "//input[@data-testid='SODiscAmountField']";
	/** @Locator Xpath */
	public static final String SONetamountField = "//input[@data-testid='SONetamountField']";
	/** @Locator Xpath */
	public static final String PriceBasisField = "//input[@data-testid='PriceBasisField']";
	/** Locator Xpath */
	public static final String node = "//*[@id='t_Button_navControl']";
	/** Locator Xpath */
	public static final String supplychain = "//*[@id='t_TreeNav_4']/div[2]/span[2]";
	/** Locator Xpath */
	public static final String sales = "//span[text()='Sales']";
	/** Locator Xpath */
	public static final String setup = "( //span[contains(@class,'fa fa fa-shopping-bag')])[2]";
	/** Locator Xpath */
	public static final String setupSales = "(//span[contains(@class,'fa fa fa-columns')])[1]";
	/** Locator Xpath */
	public static final String LineDetailsFrame = "//iframe[@title='Line Details']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountAssblValue = "//input[@data-testid='SOSpecialDiscountAssblValue']";
	/** @Locator Xpath */
	public static final String SOCashDiscountAssblValue = "//input[@data-testid='SOCashDiscountAssblValue']";
	/** @Locator Xpath */
	public static final String TaxPercentField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[10]";
	/** @Locator Xpath */
	public static final String IGSTTaxAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[11]";
	/** @Locator Xpath */
	public static final String CGSTTaxAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[12]";
	/** @Locator Xpath */
	public static final String SGSTTaxAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @Locator Xpath */
	public static final String UTGSTTaxAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @Locator Xpath */
	public static final String DiscountAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @Locator Xpath */
	public static final String SOTradeAssesableValueField = "//input[@data-testid='SOTradeAssesableValueField']";
	/** @Locator Xpath */
	public static final String SOSpecialAssesableValueField = "//input[@data-testid='SOSpecialDiscountAssblValue']";
	/** @Locator Xpath */
	public static final String SOCashAssesableValueField = "//input[@data-testid='SOCashDiscountAssblValue']";
	/** @Locator Xpath */
	public static final String DiscountAmtPerQty = "//input[@data-testid='SODiscAmountField']";
	/** @Locator Xpath */
	public static final String Currency = "//span[@data-testid='P191513134001_SOH_CURRENCY']";
	/** @Locator Xpath */
	public static final String LinedetailsTab2 = "//label[@for='P191513134002_TAB_1']";
	/** @Locator Xpath */
	public static final String CustItemID = "//input[@id='P191513134002_SOQ_CUST_PROD_ID']";
	/** @Locator Xpath */
	public static final String CustIdDesc = "//input[@id='P191513134002_SOQ_CUST_PROD_DESC']";
	/** @Locator Xpath */
	public static final String WarehouseDesc = "//input[@data-testid='WarehouseDesc']";
	/** @Locator Xpath */
	public static final String SubClass = "//input[@data-testid='SubClass']";
	/** @Locator Xpath */
	public static final String Class = "//input[@data-testid='Class']";
	/** @Locator Xpath */
	public static final String Promotional = "//input[@data-testid='Promotional']";
	/** @Locator Xpath */
	public static final String PriceClass = "//input[@data-testid='PriceClass']";
	/** @Locator Xpath */
	public static final String StockUOM = "//input[@data-testid='StockUOM']";
	/** @Locator Xpath */
	public static final String ConvFact = "//input[@data-testid='ConvFact']";
	/** @Locator Xpath */
	public static final String ToleranceType = "//span[@data-testid='ToleranceType']";
	/** @Locator Xpath */
	public static final String TaxAmt = "//input[@data-testid='TaxAmt']";
	/** @Locator Xpath */
	public static final String LocationDetails = "//button[@data-testid='SO_LineWise_LocationButton']";
	/** @Locator Xpath */
	public static final String locationDetailsFrame = "//iframe[@title='Location Details']";
	/** @Locator Xpath */
	public static final String LocationDetailsHeaderName = "//h1[@id='ui-id-4']";
	/** @Locator Xpath */
	public static final String SalesGSTtype = "//input[@data-testid='SalesGSTtype']";
	/** @Locator Xpath */
	public static final String GSTClassification = "//input[@data-testid='GSTClassification']";
	/** @Locator Xpath */
	public static final String LocationDetailSave = "//button[@id='B1637807413146704277']";
	/** @Locator Xpath */
	public static final String GSTtype = "//input[@data-testid='GSTtype']";
	/** @Locator Xpath */
	public static final String PrintCustDesc = "//input[@data-testid='P191513134001_CUST_NAME1']";
	/** @Locator Xpath */
	public static final String CustSubGroup = "//input[@data-testid='P191513134001_SOH_CUST_SUB_GRP']";
	/** @Locator Xpath */
	public static final String CustGroup = "//input[@data-testid='P191513134001_CUST_GROUP']";
	/** @Locator Xpath */
	public static final String GeneralTab = "//label[@for='P191513134001_OVERALL_REG_PRM_3']";
	/** @Locator Xpath */
	public static final String SalesZone = "//input[@data-testid='P191513134001_TERR_NAME']";
	/** @Locator Xpath */
	public static final String SalesRegion = "//input[@data-testid='P14001_SOH_SALES_AREA_NAME']";
	/** @Locator Xpath */
	public static final String LineTab = "//label[@for='P191513134001_OVERALL_REG_PRM_0']";
	/** @Locator Xpath */
	public static final String PrintButton = "//button[@data-testid='print']";
	/** Locator @CssSelector */
	public static final String PrintIconInFind = "#\\31 960290421519866117_orig > tbody > tr:nth-child(2) > td:nth-child(1) > a";
	/** @Locator Xpath */
	public static final String SOTradeDiscountPercentageReadOnlyField = "//input[@id='P191513134002_SOQ_DISC_PCT']";
	/** @Locator Xpath */
	public static final String ContractNumberInLineDetails1 = "//input[@id='P191513134002_SOQ_CONT_CAT_NO']";
	/** @Locator Xpath */
	public static final String IGSTtable = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[11]";
	/** @Locator Xpath */
	public static final String CGSTtable = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[12]";
	/** @Locator Xpath */
	public static final String SGSTtable = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @Locator Xpath */
	public static final String UTGSTtable = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @Locator Xpath */
	public static final String HeaderCancelButton = "//BUTTON[@data-testid='SO_Header_Wise_CancelButton']";
	/** @Locator Xpath */
	public static final String NoteAlertMessage = "//h6[@class='succ_msg']";
	/** @Locator Xpath */
	public static final String FindHeaderRadioButton = "//label[@for='P1915131339_RADIO_0']";
	/** @Locator Xpath */
	public static final String ChargesTab = "//label[@for='P191513134001_OVERALL_REG_PRM_1']";
	/** @Locator Xpath */
	public static final String ChargesAddButton = "//BUTTON[@data-testid='SO_Charges_AddButton']";
	/** @Locator Xpath */
	public static final String ChargeSavebutton = "//BUTTON[@data-testid='SO_Charges_SaveButton']";
	/** @Locator Xpath */
	public static final String ChargeItemField = "//*[@id='HC_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String ChargeItemLov = "//BUTTON[@ID='C8553533651494120643_lov_btn']";
	/** @Locator Xpath */
	public static final String ChargeItemInputField = "//input[@data-testid='SOTC_PROD_ID']";
	/** @Locator Xpath */
	public static final String ChargeBasisDropDown = "//select[@data-testid='SOTC_CHRG_BASIS']";
	/** @Locator Xpath */
	public static final String ChargePercentageInputField = "//input[@data-testid='SOTC_CHRG_PCT1']";
	/** @Locator Xpath */
	public static final String Chargepercentagefield = "//*[@id='HC_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]";
	/** @Locator Xpath */
	public static final String ChargeItemLineDeletebutton = "#HC_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td.a-GV-cell.u-tC.is-readonly > a";
	/** @Locator Xpath */
	public static final String ChargeBasisField = "//*[@id='HC_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @Locator Xpath */
	public static final String ChargeValueField = "//*[@id='HC_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @Locator Xpath */
	public static final String ChargeValueInputField = "//input[@data-testid='SOTC_CHRG_AMT1']";
	/** @Locator Xpath */
	public static final String IncludeChargesButton = "//BUTTON[@data-testid='SO_Header_Wise_InclCharges']";
	/** @Locator Xpath */
	public static final String includeChargescheckBox = "//span[@class='t-Icon t-Icon--left fa fa-check-square']";
	/** @Locator Xpath */
	public static final String AdvanceReceiptsAddButton = "//button[@id='B423790259775949576']";
	/** @Locator Xpath */
	public static final String AdvanceReceiptsPostButton = "//button[@data-testid='Post']";
	/** @Locator Xpath */
	public static final String AdvanceTypeDropDownButton = "//select[@data-testid='AdvanceType']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsCustomerLov = "//button[@id='P11813101101_PARD_PARTY_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsSOLov = "//button[@id='P11813101101_ORD_PFX_NO_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsPercentageField = "//input[@data-testid='Advance']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsCPCcodeLov = "//button[@id='P11813101101_PROJECT_LVL_DESC_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsHeaderWiseSaveButton = "//button[@data-testid='Save']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsLocationLov = "//button[@id='P11813101101_PARD_PLNT_LOC_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsDocumentNumber = "//span[@data-testid='DocNo']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsCPCcodeField = "//input[@data-testid='CPCDesc']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsLovSearchField = "(//input[@aria-label='Search'])[3]";
	/** @Locator Xpath */
	public static final String AdvancereceiptsOrderAmount = "//input[@data-testid='OrderAmt']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsAdvanceAmount = "//input[@data-testid='AdvanceAmt']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsBalanceAmount = "//input[@data-testid='BalanceAmt']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsDocumentNoteAlertCloseIcon = "//span[@class='t-Icon icon-close']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsworkflowOkButton = "//button[@id='B1459367661417504650']";
	/** @Locator Xpath */
	public static final String SalesOrderGeneralTab = "//label[@for='P191513134001_OVERALL_REG_PRM_3']";
	/** @Locator Xpath */
	public static final String SalesOrderincludeAdvAmtCheckBox = "//label[@id='P191513134001_SOH_ADV_REQ_FLAG_LABEL']";
	/** @Locator Xpath */
	public static final String SalesOrderHeaderSaveButton = "//BUTTON[@data-testid='SO_Header_Wise_SaveButton']";
	/** @Locator Xpath */
	public static final String SalesOrderInputIncludeAdvAmtCheckBox = "//input[@data-testid='P191513134001_SOH_ADV_REQ_FLAG']";
	/** @Locator Xpath */
	public static final String PaymentTermLov = "//button[@id='P191513134001_SOH_TERM_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String PaymentTermLOVSearchField = "//input[@aria-label='Search']";
	/** @Locator Xpath */
	public static final String PaymentTermClickSearchIcon = "//button[@class='a-Button a-PopupLOV-doSearch']";
	/** @Locator Xpath */
	public static final String PaymentTermClickHighLightText = "//span[@class='popup-lov-highlight']";
	/** Locator @CssSelector */
	public static final String SalesOrderDocumentDrillDown = "#\\31 960290421519866117_orig > tbody > tr:nth-child(2) > td:nth-child(8) > a";
	/** @Locator Xpath */
	public static final String AdvDocNumberFieldInso = "//span[@data-testid='P191513134001_SOH_ADV_DOC_NO']";
	/** @Locator Xpath */
	public static final String AdvPercentage = "//span[@data-testid='P191513134001_SOH_ADV_PCT']";
	/** @Locator Xpath */
	public static final String AdvtoBeReceived = "//span[@data-testid='P191513134001_SOH_ADV_TO_RCVD']";
	/** @Locator Xpath */
	public static final String SalesOrderHeaderFindButton = "//button[@data-testid='SO_Header_Wise_FindButton']";
	/** @Locator Xpath */
	public static final String AdvReceiptDocumentNumberLovInFind = "//button[@id='P11813101102_DOC_NO_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvReceiptsFindSearchButton = "//button[@id='B423796573044949621']";
	/** Locator @CssSelector */
	public static final String AdvReciptsDocumentDrillDown = "#\\31 648622322342378747_orig > tbody > tr:nth-child(2) > td:nth-child(5) > a";
	/** @Locator Xpath */
	public static final String AdvreceiptWorkFlowHistoryButton = "//button[@id='B2671428328286964510']";
	/** @Locator Xpath */
	public static final String AdvReceiptsQueryOrderAmount = "//span[@data-testid='OrderAmt']";
	/** @Locator Xpath */
	public static final String AdvreceiptsQueryAdvanceAmount = "//span[@id='P11813101101_PARD_PROP_ADV_AMT_1_DISPLAY']";
	/** @Locator Xpath */
	public static final String AdvreceiptsQueryBalanceAmount = "//span[@id='P11813101101_PARD_BAL_AMT_SUM_DISPLAY']";
	/** @Locator Xpath */
	public static final String PendingReceivablesAdvance = "//button[@id='B1542613326053122044']";
	/** @Locator Xpath */
	public static final String PendingReceivableFrame = "//iframe[@title='Pend. Receivable - Advance']";
	/** @Locator Xpath */
	public static final String DocumentNumberLov = "//button[@id='P341310231_DOC_NO_FND_lov_btn']";
	/** @Locator Xpath */
	public static final String PaymentReceiptSearchButton = "//button[@id='B1896591399049140413']";
	/** @Locator Xpath */
	public static final String PayementReceiptCreateButton = "//button[@id='B1568332492973947677']";
	/** @Locator Xpath */
	public static final String BankReceiptsLovButton = "//button[@id='P341310231_BANK_DESC_lov_btn']";
	/** @Locator Xpath */
	public static final String BankReceiptsOkButton = "//button[@id='B1568333378045947686']";
	/** @Locator Xpath */
	public static final String BankReceiptpostButton = "//button[@id='POST']";
	/** @Locator Xpath */
	public static final String AdvanceReceiptsFindButton = "//button[@id='SEARCH']";
	/** @Locator Xpath */
	public static final String BankReceiptsRefNumber = "//input[@id='P3413103005_BTRANS_TRANS_REF_N']";
	/** @Locator Xpath */
	public static final String BankReceiptsHeaderSave = "//button[@id='SAVE']";
	/** @Locator Xpath */
	public static final String BankReceiptCreateJournalButton = "//button[@id='CREATE_JOURNAL']";
	/** @Locator Xpath */
	public static final String BankReceiptDocumentNumber = "//span[@id='P3413103005_BTRANS_ORD_NO_DISPLAY']";
	/** @Locator Xpath */
	public static final String BankReceiptPendingBalanceAmtField = "//*[@id='1566221334302514474_orig']/tbody/tr[2]/td[11]";
	/** @Locator Xpath */
	public static final String AdvInprogressFieldInSO = "//span[@data-testid='P191513134001_SOH_ADV_RC_INPRG']";
	/** @Locator Xpath */
	public static final String AdvReceivedFieldInSO = "//span[@data-testid='P191513134001_SOH_ADV_RCVD']";
	/** @Locator Xpath */
	public static final String BankReceiptDocumentLovInFind = "//button[@id='P341310009_ORD_PFXNO_lov_btn']";
	/** @Locator Xpath */
	public static final String BankReceiptsSearchButtonInFind = "//button[@id='B175959722172441265']";
	/** Locator @CssSelector */
	public static final String BankReceiptsdDocumentDrillDownField = "#\\31 863229930614390233_orig > tbody > tr:nth-child(2) > td:nth-child(3) > a";
	/** @Locator Xpath */
	public static final String SONumberClickInLov = "//li[@class='a-IconList-item']";
	/** @Locator Xpath */
	public static final String SONumberFieldInFind = "//input[@data-testid='FindSONumberLOV']";
	/** @Locator Xpath */
	public static final String HeaderSubVoucherType = "//span[@data-testid='P191513134001_SOH_ORDER_TYPE']";
	/** @Locator Xpath */
	public static final String HeaderSOPrefixField = "//span[@data-testid='P191513134001_SOH_ORDER_PFX']";
	/** @Locator Xpath */
	public static final String HeaderSONumberField = "//span[@data-testid='P191513134001_SOH_ORDER_NO']";
	/** @Locator Xpath */
	public static final String HeaderExchangeRateField = "//input[@id='P191513134001_SOH_EXRATE']";
	/** @Locator Xpath */
	public static final String HeaderCPCcodeIDField = "//input[@data-testid='P191513134001_SOH_CC_ID']";
	/** @Locator Xpath */
	public static final String HeaderCPCcodeDescField = "//input[@data-testid='P191513134001_SOH_CC_DESC']";
	/** @Locator Xpath */
	public static final String HeaderCustomerIDField = "//input[@data-testid='P191513134001_SOH_CUST_ID']";
	/** @Locator Xpath */
	public static final String HeaderCustomerDescField = "//span[@id='P191513134001_SOH_CUST_NAME_DISPLAY']";
	/** @Locator Xpath */
	public static final String LineWiseItemIDField = "//TD[@CLASS='a-GV-cell u-tS a-GV-frozen a-GV-frozen--start2']";
	/** @Locator Xpath */
	public static final String HeaderPaymentTermField = "//input[@data-testid='P191513134001_SOH_TERM_ID']";
	/** @Locator Xpath */
	public static final String HeaderINCOTermField = "//input[@data-testid='P191513134001_SOH_FOB_ID']";
	/** @Locator Xpath */
	public static final String HeaderShipViaField = "//input[@data-testid='P191513134001_SOH_SHIPVIA_ID']";
	/** @Locator Xpath */
	public static final String AdvMaterialPercentage = "//input[@id='P191513134001_SOH_ADV_PCT']";
	/** @Locator Xpath */
	public static final String AdvTaxPercentage = "//input[@id='P191513134001_SOH_ADV_TAX_PCT']";
	/** @Locator Xpath */
	public static final String CESSPct = "//input[@id='P191513134002_SOQ_CESS_PCT']";
	/** @Locator Xpath */
	public static final String CESSAmt = "//input[@id='P191513134002_SOQ_CESS_AMT']";
	/** @Locator Xpath */
	public static final String Promotional1 = "//span[@id='P191513134002_SOQ_PROMOTION_FLAG_DISPLAY']";
	/** @Locator Xpath */
	public static final String LovPopupHighlight = "//span[@class='popup-lov-highlight']";
	/** @Locator Xpath */
	public static final String PromotionalLov = "//button[@id='P191513134002_SOQ_PROMOTION_FLAG_lov_btn']";
	/** Locator @CssSelector */
	public static final String LineWiseDeleteButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(22) > a > span";
	/** @Locator Xpath */
	public static final String ShipSetFrame = "//iframe[@title='Sales Order Shipset Item']";
	/** @Locator Xpath */
	public static final String SOAmendmentScreen = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[8]";
	/** @Locator Xpath */
	public static final String SOAmendmentPendingButton = "//BUTTON[@data-testid='SO AMD CREATE SO AMD BUTTON']";
	/** @Locator Xpath */
	public static final String SingleSOAmendment = "//BUTTON[@data-testid='SO AMD SINGLE CLS BUTTON']";
	/** @Locator Xpath */
	public static final String MultiSoAmendment = "//BUTTON[@data-testid='SO AMD MULTI CLOSESHORT BUTTON']";
	/** @Locator Xpath */
	public static final String UpdateCloseshortButton = "//BUTTON[@data-testid='SO AMD SINGLE HEADER WISE UPD CLS BUTTON']";
	/** @Locator Xpath */
	public static final String MultiCloseShortAddButton = "//button[@data-testid='MultiCloseShortAddButton']";
	/** @Locator Xpath */
	public static final String SoAmdHeaderWiseSavebutton = "//BUTTON[@id='B7541550253528188121']/span";
	/** @Locator Xpath */
	public static final String SoAmdFetchButton = "//BUTTON[@data-testid='SO AMD HEADER WISE FETCH BUTTON']";
	/** @Locator Xpath */
	public static final String SoAmdFetchOkbutton = "//button[@data-testid='SoAmdFetchOkbutton']/span";
	/** @Locator Xpath */
	public static final String SoAmdFetchSelectAllbutton = "(//input[@id='check_all'])[1]";
	/** @Locator Xpath */
	public static final String SoAmdpostbutton = "//BUTTON[@data-testid='SO AMD SINGLE HEADER WISE POST BUTTON']";
	/** @Locator Xpath */
	public static final String SoAmdNewUnitPriceField = "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[11]";
	/** @Locator Xpath */
	public static final String SoAmdNewQtyField = "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @Locator Xpath */
	public static final String SoAmdlocationLov = "//button[@id='P1915131072_SOH_PLNT_LOC_ID_lov_btn']/span";
	/** @Locator Xpath */
	public static final String SoAmdPrefixLov = "//button[@id='P1915131072_SOH_DOC_PFX_lov_btn']/span";
	/** @Locator Xpath */
	public static final String SoAmdSoNumberlov = "//button[@id='P1915131072_SO_PFX_NO_DUM_lov_btn']/span";
	/** @Locator Xpath */
	public static final String SoAmdDocNumber = "//span[@data-testid='P1915131072_SOH_DOC_NO']";
	/** @Locator Xpath */
	public static final String SoAmdfetchFrame = "//iframe[@title='Fetch']";
	/** @Locator Xpath */
	public static final String SoAmdNewUnitPriceInputField = "//input[@data-testid='SoAmdNewUnitPriceField']";
	/** @Locator Xpath */
	public static final String SoAmdNewQtyInputField = "//input[@data-testid='SoAmdNewQtyField']";
	/** @Locator Xpath */
	public static final String SoAmdLineWiseSaveButton = "//BUTTON[@data-testid='SO AMD SINLE LINE WISE SAVE BUTTON']";
	/** @Locator Xpath */
	public static final String ShipSetAlertMsg = "//a[@class='a-Notification-link']";
	/** @Locator Xpath */
	public static final String PriceClassLov = "//BUTTON[@ID='P191513134002_SOQ_SALES_PRICE_CLASS_lov_btn']";
	/** @Locator Xpath */
	public static final String ShipSetUOM = "//*[@id='ship_item_ig_grid_vc']/div[2]/div[3]/table/tbody/tr";
	/** @Locator Xpath */
	public static final String SoAmdDetailsFrame = "//iframe[@title='Details']";
	/** @Locator Xpath */
	public static final String SoAmdTradeDiscountPercentage = "//input[@data-testid='SoAmdTradeDiscountPercentage']";
	/** @Locator Xpath */
	public static final String SoAmdSpecialDicountPercentage = "//input[@data-testid='SoAmdSpecialDicountPercentage']";
	/** @Locator Xpath */
	public static final String SoAmdCashDiscountPercentage = "//input[@data-testid='SoAmdCashDiscountPercentage']";
	/** @Locator Xpath */
	public static final String SoAmdLineWiseDetailSaveButton = "//button[@data-testid='SoAmdLineWiseDetailSaveButton']";
	/** @Locator Xpath */
	public static final String SoAmdCancelButton = "//BUTTON[@data-testid='SO AMD SINGLE HEADER WISE CANCEL BUTTON']";
	/** @Locator Xpath */
	public static final String SoAmdUpdateCloseshortButton = "//BUTTON[@data-testid='SO AMD SINGLE HEADER WISE UPD CLS BUTTON']/SPAN";
	/** @Locator Xpath */
	public static final String SoAmdUpdatePriceButton = "//BUTTON[@data-testid='SO AMD SINGLE HEADER WISE UPD PRICE BUTTON']";
	/** @Locator Xpath */
	public static final String SoAmdLineWiseAddButton = "//button[@data-testid='SO AMD SINGLE LINE WIS EADD BUTTON']/span";
	/** @Locator Xpath */
	public static final String SoAmdItemIDLovField = "//BUTTON[@ID='C11333658107979551235_lov_btn']";
	/** @Locator Xpath */
	public static final String SoAmdItemField = "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[4]";
	/** @Locator Xpath */
	public static final String SoAmdChargesTab = "//label[@for='P1915131072_TAB_FILTER_1']";
	/** @Locator Xpath */
	public static final String SoAmdAddCharges = "//BUTTON[@data-testid='SO AMD SINGLE CHARGES TAB ADD BUTTON']/span";
	/** @Locator Xpath */
	public static final String SoAmdChargeItemLov = "//button[@id='C8537401039788802449_lov_btn']";
	/** @Locator Xpath */
	public static final String SoAmdChargeItemField = "//*[@id='Crg_itm_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String SoAmdChargesAmountField = "//*[@id='Crg_itm_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @Locator Xpath */
	public static final String SoAmdChargesAmountInputField = "//input[@data-testid='SAOTC_CHRG_AMT']";
	/** @Locator Xpath */
	public static final String SoAmdChargeSaveButton = "//BUTTON[@data-testid='SO AMD SINGLE CHARGES TAB SAVE BUTTON']/span";
	/** @Locator Xpath */
	public static final String SoAmdLineDetails2 = "//label[@for='P1915131076_TAB_1']";
	/** @Locator Xpath */
	public static final String SoAmdPriceClassLov = "//button[@id='P1915131076_SALC_SALES_PRICE_CLASS_lov_btn']";
	/** @Locator Xpath */
	public static final String SoAmdLineDetails2SaveButton = "//button[@data-testid='SoAmdLineDetails2SaveButton']/span";
	/** @Locator Xpath */
	public static final String SoAmdHeaderCurrencyField = "//input[@data-testid='SoAmdHeaderCurrencyField']";
	/** @Locator Xpath */
	public static final String SOLocatioField = "//input[@id='P1915131339_UNIT_LOC']";
	/** @Locator Xpath */
	public static final String SOSubVoucherType = "//input[@id='P1915131339_SOH_ORDER_TYPE']";
	/** @Locator Xpath */
	public static final String SOAmdTypeField = "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String ForwardPersonField = "//input[@id='P236131090_FWD_PERSON']";
	/** @Locator Xpath */
	public static final String MultiCloseShortLocationLov = "//button[@id='P1915131078_SAMH_PLNT_LOC_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String MultiCloseShortCustomerLov = "//button[@id='P1915131078_SAMH_CUST_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String MultiCloseShortDocPrefix = "//button[@id='P1915131078_SAMH_DOC_PFX_lov_btn']";
	/** @Locator Xpath */
	public static final String MultiCloseShortHeaderWiseSaveButton = "//button[@data-testid='MultiCloseShortHeaderWiseSaveButton']";
	/** @Locator Xpath */
	public static final String MultiCloseShortPendingSoButton = "//button[@data-testid='MultiCloseShortPendingSoButton']";
	/** @Locator Xpath */
	public static final String MultiCloseShortDocPrefixField = "//input[@data-testid='MultiCloseShortDocPrefix']";
	/** @locatorType Xpath */
	public static final String SearchBoxContainer = "//div[@class='a-IRR-searchFieldContainer']";
	/** @locatorType Xpath */
	public static final String SearchBox = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String MultiCloseShortGOButton = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String MultiCloseShortFilterRefreshButton = "//button[@data-testid='MultiCloseShortFilterRefreshButton']";
	/** @locatorType Xpath */
	public static final String MultiCloseShortCheckBoxInputField = "//table[@id='1538407851102796706_orig']/tbody/tr/td[14]/input";
	/** @locatorType Xpath */
	public static final String MultiCloseShortPostButton = "//button[@data-testid='MultiCloseShortPostButton']";
	/** @locatorType Xpath */
	public static final String MultiCloseShortDocNumber = "//input[@data-testid='MultiCloseShortDocNumber']";

	/** @locatorType Xpath */
	public static final String selectCheckBoxInMultiCloseShort(String SONumber, int i) {
		return "(//td[contains(text(),'" + SONumber + "')]/../td[14]/*)[" + i + "]";
	}

	/** @Locator Xpath */
	public static final String SOStatusLineTableText(String SOno) {
		return "//a[text()='" + SOno + "']/../../td[28]/div";
	}

	/** @Locator Xpath */
	public static final String SOStatusHeaderTableText(String SOno) {
		return "//a[text()='" + SOno + "']/../../td[12]";
	}

	/** @locatorType Xpath */
	public static final String ItemName(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String ItemRev(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String OrderedQty(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType Xpath */
	public static final String UnitCostTable(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String DiscountAmountTable(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercentage(String text) {
		return "//td[text()='" + text + "']/../td[10]";
	}

	/** @locatorType Xpath */
	public static final String TaxIGST(String text) {
		return "//td[text()='" + text + "']/../td[11]";
	}

	/** @locatorType Xpath */
	public static final String TaxCGST(String text) {
		return "//td[text()='" + text + "']/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String TaxSGST(String text) {
		return "//td[text()='" + text + "']/../td[13]";
	}

	/** @locatorType Xpath */
	public static final String TaxUTGST(String text) {
		return "//td[text()='" + text + "']/../td[14]";
	}

	/** @locatorType Xpath */
	public static final String ItemDescription(int i) {
		return "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + " ]/td[4]";
	}

	/** @locatorType Xpath */
	public static final String UOM(int i) {
		return "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType Xpath */
	public static final String HSNSACCode(int i) {
		return "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercent(int i) {
		return "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String LineDetails(int i) {
		return "(//*[@id='ig_line_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[18]/a";
	}

	/** @Locator Xpath */
	public static final String AdvreceiptsStatusLineTableText(String Advno) {
		return "//a[text()='" + Advno + "']/../../td[14]";
	}

	/** @locatorType Xpath */
	public static final String PendingPaymentReceiptCheckBox(String AdvRecDocNum) {
		return "//td[text()='" + AdvRecDocNum + "']/../td[13]";
	}

	/** @Locator Xpath */
	public static final String BankReceiptStatusLineTableText(String BRVno) {
		return "//a[text()='" + BRVno + "']/../../td[9]";
	}

	/** @locatorType Xpath */
	public static final String ShipSetDetails(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i
				+ "]/td[19]/a/span[contains(@style,'blue')]";
	}

	/** @locatorType Css Selector */
	public static final String ShipSetDetails2(int i) {
		return "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(" + i
				+ ") > td:nth-child(16) > a";
	}

	/** @locatorType Xpath */
	public static final String ShipSetItemID(int i) {
		return "//*[@id='ship_item_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String ShipSetItemUOM(int i) {
		return "//*[@id='ship_item_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType Xpath */
	public static final String ShipSetItemQty(int i) {
		return "//*[@id='ship_item_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType Xpath */
	public static final String SoAmdQtyField(int i) {
		return "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String SoAmdUnitCostField(int i) {
		return "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[11]";
	}

	/** @locatorType Xpath */
	public static final String SoAmdChargeUnitPriceField(int i) {
		return "//*[@id='Crg_itm_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String SOAmdLineDetails(int i) {
		return "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i
				+ "]/td[16]/a/span[contains(@style,'blue')]	";
	}

	/** @locatorType Xpath */
	public static final String AmdType(int i) {
		return "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[ " + i + " ]/td[3]";
	}
}
