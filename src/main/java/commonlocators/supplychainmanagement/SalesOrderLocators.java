package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class SalesOrderLocators {

	/** @Locator Xpath */
	public static final String SOButton = "//span[text()='Sales Order']";
	/** @Locator Xpath */
	public static final String AddButtonSalesOrder = "//BUTTON[@TITLE='Create SO/CS']";
	/** @Locator Xpath */
	public static final String SalesOrderHeader = "//h1[@id='ui-id-1']";
	/** @Locator Xpath */
	public static final String LocationLOV = "//button[contains(@id,'P19151313402_UNIT_LOC_lov_btn')]";
	/** @Locator Xpath */
	public static final String SalesSubVoucherTypeLOV = "//input[@data-testid='SalesSubVoucherTypeLOV']/following-sibling::button";
	/** @Locator Xpath */
	public static final String SalesTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]";
	/** @Locator Xpath */
	public static final String SalesTransactionTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]";
	/** @Locator Xpath */
	public static final String SalesOrderTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[6]";
	/** @Locator Xpath */
	public static final String CustomerLov = "//input[@data-testid='CustomerLov']/following-sibling::button";
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
	public static final String SalesOrderLinesTable = "(//table[contains(@data-testid,'Result_Data')])[2]";

	/** @Locator Xpath */
	public static final String ItemField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Item ID")
				+ ")[1]";
	}

	/** @Locator Xpath */
	public static final String ItemLov() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Item ID")
				+ ")[1]//button";
	}

	/** @Locator Xpath */
	public static final String ItemLovSearchField = "//input[contains(@class,'a-PopupLOV-search')]";

	/** @Locator Xpath */
	public static final String ItemInputField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Item ID")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String QtyField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Ord. Qty.")
				+ ")[1]";
	}

	/** @Locator Xpath */
	public static final String UnitCostField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Unit Price")
				+ ")[1]";
	}

	/** @Locator Xpath */
	public static final String QtyInputField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Ord. Qty.")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String UnitCostInputField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Unit Price")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String HSNCodeField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "HSN/SAC Code")
				+ ")[1]";
	}

	/** @Locator Xpath */
	public static final String HSNCodeLOV() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "HSN/SAC Code")
				+ ")[1]//button";
	}

	/** @Locator Xpath */
	public static final String HSNCodeInputField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "HSN/SAC Code")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String LineWiseSaveIcon = "//BUTTON[@data-testid='SO_LineWise_SaveButton']";
	/** @Locator Xpath */
	public static final String PostButton = "//BUTTON[@data-testid='SO_Header_Wise_PostButton']";
	/** @Locator Xpath */
	public static final String WorkApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @Locator Xpath */
	public static final String ForwardApprovalLov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @Locator Xpath */
	public static final String CPForwardLOV = "//BUTTON[@ID='P236131090_FWD_PERSON_lov_btn']";
	/** @Locator Xpath */
	public static final String OkButtonForward = "//button[@data-testid='Entry']";
	/** @Locator Xpath */
	public static final String FindSONumberLOV = "//button[@id='P1915131339_SO_NO_lov_btn']";
	/** @Locator Xpath */
	public static final String FindSearchButton = "//BUTTON[@data-testid='SO_Srch_Button']";
	/** @Locator Xpath */
	public static final String SOTotalAmountFieldTrans = "//input[@data-testid='SOTotalAmountFieldQuery']";
	/** @Locator Xpath */
	public static final String SOTotalAmountFieldQuery = "//span[@id='P191513134001_SOH_TOT_AMT_DISPLAY']";

	/** Locator @CssSelector */
	public static final String SOLineWiseDetailsButton() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Details")
				+ ")[1]//a";
	}

	/** Locator @CssSelector */
	public static final String SOTotalDiscountData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SalesOrderLinesTable, "Tot. Disc. Amt.")
		+ ")[1]//a";
}
	/** @Locator Xpath */
	public static final String LinewisedetailsHeader = "//h1[text()='Line Details']";
	/** @Locator Xpath */
	public static final String DiscountEntryIframe = "//iframe[contains(@title,'Line Details')]";
	/** @Locator Xpath */
	public static final String SOTradeDiscountpercentageField = "//input[@id='P91_SOQ_DISC_PCT']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountpercentageField = "//input[@id='P91_SOQ_SPL_DISC_PCT']";
	/** @Locator Xpath */
	public static final String SOCashDiscountpercentageField = "//input[@id='P91_SOQ_CASH_DISC_PCT']";
	/** @Locator Xpath */
	public static final String SOTradeDiscountAmountField = "//input[@data-testid='SOTradeDiscountAmountField']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountAmountField = "//input[@data-testid='SOSpecialDiscountAmountField']";
	/** @Locator Xpath */
	public static final String SOCashDiscountAmountField = "//input[@data-testid='SOCashDiscountAmountField']";
	/** @Locator Xpath */
	public static final String LineWiseDetailsSaveIcon = "//button[@data-testid='SO_LineWise_Details_SaveButton']";
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
	public static final String LineDetailsFrame = "//iframe[contains(@title,'Line Details')]";
	/** @Locator Xpath */
	public static final String SOTradeDiscountAssblValue = "//input[@data-testid='SOTradeAssesableValueField']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountAssblValue = "//input[@data-testid='SOSpecialDiscountAssblValue']";
	/** @Locator Xpath */
	public static final String SOCashDiscountAssblValue = "//input[@data-testid='SOCashDiscountAssblValue']";
	/** @Locator Xpath */
	public static final String TaxPercentField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[12]";
	/** @Locator Xpath */
	public static final String IGSTTaxAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @Locator Xpath */
	public static final String CGSTTaxAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @Locator Xpath */
	public static final String SGSTTaxAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[15]";
	/** @Locator Xpath */
	public static final String UTGSTTaxAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[16]";
	/** @Locator Xpath */
	public static final String DiscountAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[10]";
	/** @Locator Xpath */
	public static final String SOTradeAssesableValueField = "//input[@data-testid='SOTradeAssesableValueField']";
	/** @Locator Xpath */
	public static final String SOSpecialAssesableValueField = "//input[@data-testid='SOSpecialDiscountAssblValue']";
	/** @Locator Xpath */
	public static final String SOCashAssesableValueField = "//input[@data-testid='SOCashDiscountAssblValue']";
	/** @Locator Xpath */
	public static final String DiscountAmtPerQty = "//input[@data-testid='SODiscAmountField']";
	/** @Locator Xpath */
	public static final String Currency = "//select[@data-testid='P191513134001_SOH_CURRENCY']";
	/** @Locator Xpath */
	public static final String AfterLineSaveCurrency = "//span[@data-testid='P191513134001_SOH_CURRENCY']";
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
	public static final String Promotional = "//input[@data-testid='P191513134002_SOQ_PROMOTION_FLAG']";
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
	public static final String HeaderCancelButton = "//BUTTON[@data-testid='Cancel_hd']";
	/** @Locator Xpath */
	public static final String NoteAlertMessage = "//h6[@class='succ_msg']";
	/** @Locator Xpath */
	public static final String FindHeaderRadioButton = "//label[@for='P1915131339_HD_LN_0']";
	/** @Locator Xpath */
	public static final String ChargesTab = "//label[@for='P191513134001_OVERALL_REG_PRM_1']";
	/** @Locator Xpath */
	public static final String ChargesAddButton = "//BUTTON[@data-testid='SO_Charges_AddButton']";
	/** @Locator Xpath */
	public static final String ChargeSavebutton = "//BUTTON[@data-testid='SO_Charges_SaveButton']";
	/** @Locator Xpath */
	public static final String ChargeItemField = "//*[@id='HC_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String ChargeItemLov = "//INPUT[@data-testid='SOTC_PROD_ID']/following-sibling::button";
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
	public static final String AdvancereceiptsworkflowOkButton = "//button[@data-testid='Entry' or @data-testid='OK']";
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
	public static final String PaymentTermClickHighLightText = "//mark[@class='popup-lov-highlight']";
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
	public static final String PendingReceivablesAdvance = "//span[contains(text(),'Pending Receivable - Advance')]/ancestor::button";
	/** @Locator Xpath */
	public static final String PendingReceivableFrame = "//iframe[@title='Pend. Receivable - Advance']";
	/** @Locator Xpath */
	public static final String DocumentNumberLov = "//button[@id='P341310231_DOC_NO_FND_lov_btn']";
	/** @Locator Xpath */
	public static final String PaymentReceiptSearchButton = "//button[@title='Home']/parent::div/button[@title='Clear']/parent::div/button/span[text()='Search']";
	/** @Locator Xpath */
	public static final String PayementReceiptCreateButton = "//span[text()='Create']/ancestor::button";
	/** @Locator Xpath */
	public static final String BankReceiptsLovButton = "//button[@id='P341310231_BANK_DESC_lov_btn']";
	/** @Locator Xpath */
	public static final String BankReceiptsOkButton = "//span[text()='Ok']";
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
	public static final String HeaderExchangeRateField = "//span[@data-testid='P191513134001_SOH_EXRATE']";
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
	public static final String AdvMaterialPercentage = "//input[@data-testid='P191513134001_SOH_ADV_PCT']";
	/** @Locator Xpath */
	public static final String AdvTaxPercentage = "//input[@data-testid='P191513134001_SOH_ADV_TAX_PCT']";
	/** @Locator Xpath */
	public static final String CESSPct = "//input[@id='P191513134002_SOQ_CESS_PCT']";
	/** @Locator Xpath */
	public static final String CESSAmt = "//input[@id='P191513134002_SOQ_CESS_AMT']";
	/** @Locator Xpath */
	public static final String Promotional1 = "//span[@id='P191513134002_SOQ_PROMOTION_FLAG_DISPLAY']";
	/** @Locator Xpath */
	public static final String LovPopupHighlight = "//mark[@class='popup-lov-highlight']";
	/** @Locator Xpath */
	public static final String PromotionalLov = "//button[@id='P191513134002_SOQ_PROMOTION_FLAG_lov_btn']";
	/** Locator @CssSelector */
	public static final String LineWiseDeleteButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(28) > a > span";
	/** @Locator Xpath */
	public static final String ShipSetFrame = "//iframe[@title='Sales Order Shipset Item']";
	/** @Locator Xpath */
	public static final String SOAmendmentScreen = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[8]";
	/** @Locator Xpath */
	public static final String SOAmendmentPendingButton = "//BUTTON[@data-testid='SO AMD CREATE SO AMD BUTTON']";
	/** @Locator Xpath */
	public static final String SingleSOAmendment = "//BUTTON[@data-testid='Single_Closeshort']";
	/** @Locator Xpath */
	public static final String MultiSoAmendment = "//BUTTON[@data-testid='Multi_Closeshort']";
	/** @Locator Xpath */
	public static final String UpdateCloseshortButton = "//BUTTON[@data-testid='SO AMD SINGLE HEADER WISE UPD CLS BUTTON']";
	/** @Locator Xpath */
	public static final String MultiCloseShortAddButton = "//button[@data-testid='MultiCloseShortAddButton']";
	/** @Locator Xpath */
	public static final String SoAmdHeaderWiseSavebutton = "//BUTTON[@DATA-TESTID='CREATE']";
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
	public static final String SoAmdlocationLov = "//input[@data-testid='P1915131072_SOH_PLNT_LOC_ID']/following-sibling::button";
	/** @Locator Xpath */
	public static final String SoAmdPrefixLov = "//input[@data-testid='SoAmdPrefixLov']/following-sibling::button";
	/** @Locator Xpath */
	public static final String SoAmdSoNumberlov = "//input[@data-testid='P1915131072_SO_PFX_NO_DUM']/following-sibling::button";
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
	public static final String SoAmdItemIDLovField = "//input[@data-testid='PRODID_REV']/following-sibling::button";
	/** @Locator Xpath */
	public static final String SoAmdItemField = "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @Locator Xpath */
	public static final String SoAmdChargesTab = "//label[@for='P1915131072_TAB_FILTER_1']";
	/** @Locator Xpath */
	public static final String SoAmdAddCharges = "//BUTTON[@data-testid='SO AMD SINGLE CHARGES TAB ADD BUTTON']/span";
	/** @Locator Xpath */
	public static final String SoAmdChargeItemLov = "//input[@data-testid='SoAmdChargeItemLov']/following-sibling::button";
	/** @Locator Xpath */
	public static final String SoAmdChargeItemField = "//*[@id='Crg_itm_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String SoAmdChargesAmountField = "//*[@id='Crg_itm_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @Locator Xpath */
	public static final String SoAmdChargesAmountInputField = "//input[@data-testid='SoAmdChargesAmountField']";
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
	public static final String SOLocationField = "//input[@data-testid='LocationLOV']";
	/** @Locator Xpath */
	public static final String SOSubVoucherType = "//input[@data-testid='SalesSubVoucherTypeLOV']";
	/** @Locator Xpath */
	public static final String SOAmdTypeField = "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String ForwardPersonField = "//input[@data-testid='P236131090_FWD_PERSON']";
	/** @Locator Xpath */
	public static final String MultiCloseShortLocationLov = "//input[@data-testid='P1915131078_SAMH_PLNT_LOC_ID']/following-sibling::button";
	/** @Locator Xpath */
	public static final String MultiCloseShortCustomerLov = "//input[@data-testid='P1915131078_SAMH_CUST_ID']/following-sibling::button";
	/** @Locator Xpath */
	public static final String MultiCloseShortDocPrefix = "//input[@data-testid='P1915131078_SAMH_DOC_PFX']/following-sibling::button";
	/** @Locator Xpath */
	public static final String MultiCloseShortHeaderWiseSaveButton = "//button[@title='Create Hd']";
	/** @Locator Xpath */
	public static final String MultiCloseShortPendingSoButton = "//button[@data-testid='MultiCloseShortPendingSoButton']";
	/** @Locator Xpath */
	public static final String MultiCloseShortDocPrefixField = "//input[@data-testid='P1915131078_SAMH_DOC_PFX']";
	/** @locatorType Xpath */
	public static final String SearchBoxContainer = "//div[@class='a-IRR-searchFieldContainer']";
	/** @locatorType Xpath */
	public static final String SearchBox = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String MultiCloseShortGOButton = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String MultiCloseShortFilterRefreshButton = "//button[@data-testid='MultiCloseShortFilterRefreshButton']";
	/** @locatorType Xpath */
	public static final String MultiCloseShortCheckBoxInputField = "//table[contains(@aria-label,'Multi Closeshot')]/tbody/tr/td/input";
	/** @locatorType Xpath */
	public static final String MultiCloseShortPostButton = "//button[@data-testid='MultiCloseShortPostButton']";
	/** @locatorType Xpath */
	public static final String MultiCloseShortDocNumber = "//input[@data-testid='MultiCloseShortDocNumber']";
	/** @locatorType Xpath */
	public static final String ShipFromLocIDField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[17]";
	/** @locatorType Xpath */
	public static final String ShipFromLocLovButton = "//button[@id='C121068505371972733_lov_btn']";
	/** @locatorType Xpath */
	public static final String CustomerMasterAddButton = "//button[@id='B561978827785799421']";
	/** @locatorType Xpath */
	public static final String CustomerIDLovButton = "//button[@id='P367006005_SUPLR_SUPLR_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String CustomerMasterSearchButton = "//button[@id='B561977247363799415']";
	/** @locatorType Xpath */
	public static final String CustomerDrillDown = "#\\31 166452744436430511_orig > tbody > tr:nth-child(2) > td:nth-child(1) > a";
	/** @locatorType Xpath */
	public static final String CustomerUpdateButton = "//button[@id='Update']";
	/** @locatorType Xpath */
	public static final String CustomerUpdDetailsFrame = "//button[@id='B24209226926315496']";
	/** @locatorType Xpath */
	public static final String CustomerUpdateCreditLimitButton = "//button[@id='B697540159875201178']";
	/** @locatorType Xpath */
	public static final String CustomerCLAddButton = "//label[@for='P367006006_CRE_ADD_INS_0']";
	/** @locatorType Xpath */
	public static final String UpdateCustomerDetailsFrame = "//iframe[@title='Update Customer Details']";
	/** @locatorType Xpath */
	public static final String UpdateCustomerDetailsSaveButton = "//button[@id='B696232377504141223']";
	/** @locatorType Xpath */
	public static final String UpdCLEffFromDateInputField = "//input[@id='P3670058_UCCL_EFF_FRM_input']";
	/** @locatorType Xpath */
	public static final String SOFrame = "//iframe[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String SOSearchButton = "//button[@data-testid='SO_Srch_Button']";
	/** @locatorType Xpath */
	public static final String PaymentsReceiptModeField = "//div[@id='P341310231_BANK_TYPE_CONTAINER']";
	/** @locatorType Xpath */
	public static final String ForwardEmpInputField = "//input[@id='P236131090_FWD_PERSON']";
	/** @locatorType Xpath */
	public static final String SearchBoxContainer1 = "//table[contains(@aria-label,'Multi Closeshot')]/tr/th[3]";
	/** @locatorType Xpath */
	public static final String SearchBox1 = "//input[@id='PEND_sort_widget_search_field']";
	/** @locatorType Xpath */
	public static final String SOCancelAlert = "//h1[text()='Confirmation']/ancestor::div/following-sibling::div/div/button[text()='Ok']";
	/** @locatorType Xpath */
	public static final String CFdescLOV = "//INPUT[@data-testid='CFDesc']/following-sibling::button";
	
	public static final String DetailsButton = "//a[@data-action='Details']";
	
	public static final String ItemDetails = "//h2[text()='Item Details']/parent::div/ancestor::div[1]/div/button";
	
	public static final String DetailsCloseButton = "//h1[text()='Line Details']/parent::div/button";
	
	public static final String PriceDetails = "(//h2[text()='Price Details']/parent::div/ancestor::div[1]/div/button)[1]";
	/** Locator Xpath */
	public static final String SODiscountLineDetailsFrame = "//iframe[contains(@title,'Sales Order Line Details')]";
	
	public static final String SODiscountFrameName = "//h1[text()='Sales Order Line Details']";
	
	public static final String CancelAlert = "//button[text()='OK']";
	
	public static final String ItemTypeFieldName = "//label[text()='Item Type']";
	
	public static final String ShipsetItemButton = "//a[@data-action='SHIPSET_ITEM']";
	
	public static final String ShipsetFrameName = "//h1[text()='Sales Order Shipset Item']";
	
	public static final String SOAmdDiscountDetails = "(//h2[text()='Discount Details']/parent::div/ancestor::div[1]/div/button)[1]";
	
	public static final String SOAmdNewDiscDetailsButton = "(//h2[text()='New Disc. Details']/parent::div/ancestor::div[1]/div/button)[1]";
	
	public static final String SOAmdSourceDetailsButton = "(//h2[text()='Source Details']/parent::div/ancestor::div[1]/div/button)[1]";
	
	public static final String SOGeneralAdvanceButton = "(//h2[text()='Advance']/parent::div/ancestor::div[1]/div/button)[1]";
	
	public static final String SOAmdTDDiscLabel = "//label[text()='Trade Ass.']";
	

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
		return "//a[text()='" + SOno + "']/../../td[49]";
	}

	/** @locatorType Xpath */
	public static final String ItemName(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String ItemRev(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[4]";
	}

	/** @locatorType Xpath */
	public static final String OrderedQty(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String UnitCostTable(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String DiscountAmountTable(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[12]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercentage(String text) {
		return "//td[text()='" + text + "']/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String TaxIGST(String text) {
		return "//td[text()='" + text + "']/../td[13]";
	}

	/** @locatorType Xpath */
	public static final String TaxCGST(String text) {
		return "//td[text()='" + text + "']/../td[14]";
	}

	/** @locatorType Xpath */
	public static final String TaxSGST(String text) {
		return "//td[text()='" + text + "']/../td[15]";
	}

	/** @locatorType Xpath */
	public static final String TaxUTGST(String text) {
		return "//td[text()='" + text + "']/../td[16]";
	}

	/** @locatorType Xpath */
	public static final String ItemDescription(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + " ]/td[5]";
	}

	/** @locatorType Xpath */
	public static final String UOM(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String HSNSACCode(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[13]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercent(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[14]";
	}

	/** @locatorType Xpath */
	public static final String LineDetails(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[1]/a";
	}

	/** @Locator Xpath */
	public static final String AdvreceiptsStatusLineTableText(String Advno) {
		return "//a[text()='" + Advno + "']/../../td[14]";
	}

	/** @locatorType Xpath */
	public static final String PendingPaymentReceiptCheckBox(String AdvRecDocNum) {
		return "//td[text()='" + AdvRecDocNum + "']/../td[14]";
	}

	/** @Locator Xpath */
	public static final String BankReceiptStatusLineTableText(String BRVno) {
		return "//a[text()='" + BRVno + "']/../../td[9]";
	}

	/** @locatorType Xpath */
	public static final String ShipSetDetails(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i
				+ "]/td[25]/a/span[contains(@style,'blue')]";
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
		return "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[13]";
	}

	/** @locatorType Xpath */
	public static final String SoAmdUnitCostField(int i) {
		return "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[15]";
	}

	/** @locatorType Xpath */
	public static final String SoAmdChargeUnitPriceField(int i) {
		return "//*[@id='Crg_itm_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String SOAmdLineDetails(int i) {
		return "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i
				+ "]/td[1]/a";
	}

	/** @locatorType Xpath */
	public static final String AmdType(int i) {
		return "//*[@id='sale_amend_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[ " + i + " ]/td[5]";
	}
	
	/** @locatorType Xpath */
	public static final String DiscountTable(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[12]/a";
	}

	/** @Locator Xpath */
	public static final String HeaderCPCcodeLovBtn = "//input[@data-testid='P191513134001_SOH_CC_ID']/following-sibling::button";
	/** @Locator Xpath */
	public static final String LineDetailBOMNoLov = "//input[contains(@id,'SOQ_BOM_NO')]/following-sibling::button";
	
	public static final String CancelLineButton = "//a[@data-action='CANCEL_LINE']";
	
	public static final String SOAmdDetailsButton = "//a[@data-action='DETAILS']";
}
