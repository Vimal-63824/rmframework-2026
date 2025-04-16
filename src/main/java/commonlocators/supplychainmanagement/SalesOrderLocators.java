package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class SalesOrderLocators {
	/** @Locator Xpath */
	public static final String AddButtonSalesOrder = "//button[@id='B583506114587524405']";
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
	public static final String TickIcon = "//button[@id='B1161069731701389989']";
	/** @Locator Xpath */
	public static final String SoNumber = "//span[@id='P191513134001_SOH_ORDER_NO_DISPLAY']";
	/** @Locator Xpath */
	public static final String NoteAlertCloseIcon = "//button[@title='Close']";
	/** @Locator Xpath */
	public static final String HeaderLocationField = "//span[@id='P191513134001_UNIT_LOC_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderUnitField = "//span[@id='P191513134001_SOH_PLANT_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderCustIDField = "//span[@id='P191513134001_SOH_CUST_ID_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderCustDescField = "//span[@id='P191513134001_SOH_CUST_NAME_DISPLAY']";
	/** @Locator Xpath */
	public static final String LineWiseAddIcon = "//button[@id='B6827150304658499342']";
	/** @Locator Xpath */
	public static final String ItemField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @Locator Xpath */
	public static final String ItemLov = "//button[@id='C9412635996465636352_lov_btn']/span";
	/** @Locator Xpath */
	public static final String ItemLovSearchField = "//input[contains(@class,'a-PopupLOV-search')]";
	/** @Locator Xpath */
	public static final String ItemInputField = "//input[@id='C9412635996465636352']";
	/** @Locator Xpath */
	public static final String QtyField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @Locator Xpath */
	public static final String UnitCostField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @Locator Xpath */
	public static final String QtyInputField = "//input[@id='C11988364664888831497']";
	/** @Locator Xpath */
	public static final String UnitCostInputField = "//input[@id='C2095943635705630808']";
	/** @Locator Xpath */
	public static final String HSNCodeField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @Locator Xpath */
	public static final String HSNCodeLOV = "//button[@id='C11988374012977831540_lov_btn']/span";
	/** @Locator Xpath */
	public static final String HSNCodeInputField = "//input[@id='C11988374012977831540']";
	/** @Locator Xpath */
	public static final String LineWiseSaveIcon = "//button[@id='B6827150692680499344']";
	/** @Locator Xpath */
	public static final String PostButton = "//button[@id='B6827454009175500661']";
	/** @Locator Xpath */
	public static final String WorkApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @Locator Xpath */
	public static final String ForwardApprovalLov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @Locator Xpath */
	public static final String OkButtonForward = "//BUTTON[@ID='B1612723241586671539']";
	/** @Locator Xpath */
	public static final String FindSONumberLOV = "//button[@id='P1915131339_SO_NO_lov_btn']";
	/** @Locator Xpath */
	public static final String FindSearchButton = "//button[@id='B583508927599524433']";
	/** @Locator Xpath */
	public static final String SOTotalAmountFieldTrans = "//input[@id='P191513134001_SOH_TOT_AMT']";
	/** @Locator Xpath */
	public static final String SOTotalAmountFieldQuery = "//span[@id='P191513134001_SOH_TOT_AMT_DISPLAY']";
	/** Locator @CssSelector */
	public static final String SOLineWiseDetailsButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(17) > a";
	/** @Locator Xpath */
	public static final String LinewisedetailsHeader = "//h1[@id='ui-id-4']";
	/** @Locator Xpath */
	public static final String SOTradeDiscountpercentageField = "//input[@id='P191513134002_SOQ_DISC_PCT']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountpercentageField = "//input[@id='P191513134002_SOQ_SPL_DISC_PCT']";
	/** @Locator Xpath */
	public static final String SOCashDiscountpercentageField = "//input[@id='P191513134002_SOQ_CASH_DISC_PCT']";
	/** @Locator Xpath */
	public static final String SOTradeDiscountAmountField = "//input[@id='P191513134002_SOQ_DISC_AMT']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountAmountField = "//input[@id='P191513134002_SOQ_SPL_DISC_AMT']";
	/** @Locator Xpath */
	public static final String SOCashDiscountAmountField = "//input[@id='P191513134002_SOQ_CASH_DISC_AMT']";
	/** @Locator Xpath */
	public static final String LineWiseDetailsSaveIcon = "//button[@id='B1433003526594741402']/span";
	/** @Locator Xpath */
	public static final String SOGrossAmountField = "//input[@id='P191513134002_SOQ_GROSS_AMT']";
	/** @Locator Xpath */
	public static final String SOTaxAmountField = "//input[@id='P191513134002_SOQ_TAX_AMT']";
	/** @Locator Xpath */
	public static final String SODiscAmountField = "//input[@id='P191513134002_SOQ_DISC_AMT_PER_QTY']";
	/** @Locator Xpath */
	public static final String SONetamountField = "//input[@id='P191513134002_SOQ_NET_AMT']";
	/** @Locator Xpath */
	public static final String PriceBasisField = "//input[@id='P191513134002_SOQ_PRICE_BASIS_1']";
	/** Locator Xpath */
	public static final String node = "//*[@id='t_Button_navControl']";
	/** Locator Xpath */
	public static final String supplychain = "//*[@id='t_TreeNav_4']/div[2]/span[2]";
	/** Locator Xpath */
	public static final String sales = "//*[@id='t_TreeNav_19']/div[2]/span[2]";
	/** Locator Xpath */
	public static final String setup = "( //span[contains(@class,'fa fa fa-shopping-bag')])[2]";
	/** Locator Xpath */
	public static final String setupSales = "(//span[contains(@class,'fa fa fa-columns')])[1]";
	/** Locator Xpath */
	public static final String LineDetailsFrame = "//iframe[@title='Line Details']";
	/** @Locator Xpath */
	public static final String SOSpecialDiscountAssblValue = "//input[@id='P191513134002_SOQ_SPL_ASSBL_VAL']";
	/** @Locator Xpath */
	public static final String SOCashDiscountAssblValue = "//input[@id='P191513134002_SOQ_CASH_ASSBL_VAL']";
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
	public static final String SOTradeAssesableValueField = "//input[@id='P191513134002_SOQ_TRD_ASSBL_VAL']";
	/** @Locator Xpath */
	public static final String SOSpecialAssesableValueField = "//input[@id='P191513134002_SOQ_SPL_ASSBL_VAL']";
	/** @Locator Xpath */
	public static final String SOCashAssesableValueField = "//input[@id='P191513134002_SOQ_CASH_ASSBL_VAL']";
	/** @Locator Xpath */
	public static final String DiscountAmtPerQty = "//input[@id='P191513134002_SOQ_DISC_AMT_PER_QTY']";
	/** @Locator Xpath */
	public static final String Currency = "//span[@id='P191513134001_SOH_CURRENCY_DISPLAY']";
	/** @Locator Xpath */
	public static final String LinedetailsTab2 = "//label[@for='P191513134002_TAB_1']";
	/** @Locator Xpath */
	public static final String CustItemID = "//input[@id='P191513134002_SOQ_CUST_PROD_ID']";
	/** @Locator Xpath */
	public static final String CustIdDesc = "//input[@id='P191513134002_SOQ_CUST_PROD_DESC']";
	/** @Locator Xpath */
	public static final String WarehouseDesc = "//input[@id='P191513134002_SOQ_STORE_NAME']";
	/** @Locator Xpath */
	public static final String SubClass = "//input[@id='P191513134002_SOQ_SUB_DESC']";
	/** @Locator Xpath */
	public static final String Class = "//input[@id='P191513134002_CLASS']";
	/** @Locator Xpath */
	public static final String Promotional = "//input[@id='P191513134002_SOQ_PROMOTION_FLAG']";
	/** @Locator Xpath */
	public static final String PriceClass = "//input[@id='P191513134002_SOQ_SALES_PRICE_CLASS']";
	/** @Locator Xpath */
	public static final String StockUOM = "//input[@id='P191513134002_SOQ_PROD_UOM']";
	/** @Locator Xpath */
	public static final String ConvFact = "//input[@id='P191513134002_SOQ_CONV_FACTOR']";
	/** @Locator Xpath */
	public static final String ToleranceType = "//span[@id='P191513134002_SOQ_TOLR_TYPE_DISPLAY']";
	/** @Locator Xpath */
	public static final String TaxAmt = "//input[@id='P191513134002_SOQ_TAX_AMT']";
	/** @Locator Xpath */
	public static final String LocationDetails = "//button[@id='B1356770695644896542']";
	/** @Locator Xpath */
	public static final String locationDetailsFrame = "//iframe[@title='Location Details']";
	/** @Locator Xpath */
	public static final String LocationDetailsHeaderName = "//h1[@id='ui-id-4']";
	/** @Locator Xpath */
	public static final String SalesGSTtype = "//input[@id='P191513134004_SOH_GST_CUST_TYPE_DESC']";
	/** @Locator Xpath */
	public static final String GSTClassification = "//input[@id='P191513134004_SOH_SOU_GST_TYPE_DESC']";
	/** @Locator Xpath */
	public static final String LocationDetailSave = "//button[@id='B1635906067600193793']";
	/** @Locator Xpath */
	public static final String GSTtype = "//input[@id='P191513134004_SOH_GST_REG_TYPE_DESC']";
	/** @Locator Xpath */
	public static final String PrintCustDesc = "//input[@id='P191513134001_CUST_NAME1']";
	/** @Locator Xpath */
	public static final String CustSubGroup = "//input[@id='P191513134001_SOH_CUST_SUB_GRP']";
	/** @Locator Xpath */
	public static final String CustGroup = "//input[@id='P191513134001_CUST_GROUP']";
	/** @Locator Xpath */
	public static final String GeneralTab = "//label[@for='P191513134001_OVERALL_REG_PRM_3']";
	/** @Locator Xpath */
	public static final String SalesZone = "//input[@id='P191513134001_TERR_NAME']";
	/** @Locator Xpath */
	public static final String SalesRegion = "//input[@id='P14001_SOH_SALES_AREA_NAME']";
	/** @Locator Xpath */
	public static final String LineTab = "//label[@for='P191513134001_OVERALL_REG_PRM_0']";
	/** @Locator Xpath */
	public static final String PrintButton = "//button[@id='B6827455128004500662']";
	/** Locator @CssSelector */
	public static final String PrintIconInFind = "#\\31 960290421519866117_orig > tbody > tr:nth-child(2) > td:nth-child(1) > a";
	/** @Locator Xpath */
	public static final String SOTradeDiscountPercentageReadOnlyField = "//span[@id='P191513134002_SOQ_DISC_PCT_DISPLAY']";
	/** @Locator Xpath */
	public static final String ContractNumberInLineDetails1 = "//input[@id='P191513134002_SOQ_CONT_CAT_NO']";
	/** @Locator Xpath */
	public static final String IGSTtable = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[11]";
	/** @Locator Xpath */
	public static final String CGSTtable = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[12]";
	/** @Locator Xpath */
	public static final String SGSTtable = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @Locator Xpath */
	public static final String UTGSTtable = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @Locator Xpath */
	public static final String HeaderCancelButton = "//button[@id='B6827454401045500662']";
	/** @Locator Xpath */
	public static final String NoteAlertMessage = "//h6[@class='succ_msg']";
	/** @Locator Xpath */
	public static final String FindHeaderRadioButton = "//label[@for='P1915131339_RADIO_0']";
	/** @Locator Xpath */
	public static final String ChargesTab = "//label[@for='P191513134001_OVERALL_REG_PRM_1']";
	/** @Locator Xpath */
	public static final String ChargesAddButton = "//button[@id='B1710251629897318145']";
	/** @Locator Xpath */
	public static final String ChargeSavebutton = "//button[@id='B1710251993742318151']";
	/** @Locator Xpath */
	public static final String ChargeItemField = "//*[@id=\"HC_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String ChargeItemLov = "//button[@id='C8551632305947610159_lov_btn']/span";
	/** @Locator Xpath */
	public static final String ChargeItemInputField = "//input[@id='C8551632305947610159']";
	/** @Locator Xpath */
	public static final String ChargeBasisDropDown = "//SELECT[@ID='C8551636304666610163']";
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
	public static final String IncludeChargesButton = "//button[@id='inctax']";
	/** @Locator Xpath */
	public static final String includeChargescheckBox = "//span[@class='t-Icon t-Icon--left fa fa-check-square']";
	/** @Locator Xpath */
	public static final String AdvanceReceiptsAddButton = "//button[@id='B423790259775949576']";
	/** @Locator Xpath */
	public static final String AdvanceReceiptsPostButton = "//button[@id='B1507544529784076681']";
	/** @Locator Xpath */
	public static final String AdvanceTypeDropDownButton = "//select[@id='P11813101101_PARD_ORD_TYPE']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsCustomerLov = "//button[@id='P11813101101_PARD_PARTY_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsSOLov = "//button[@id='P11813101101_ORD_PFX_NO_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsPercentageField = "//input[@id='P11813101101_PARD_PROP_ADV_PCT']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsCPCcodeLov = "//button[@id='P11813101101_PROJECT_LVL_DESC_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsHeaderWiseSaveButton = "//button[@id='B1494912032205698090']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsLocationLov = "//button[@id='P11813101101_PARD_PLNT_LOC_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsDocumentNumber = "//span[@id='P11813101101_PARD_DOC_NO_DISPLAY']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsCPCcodeField = "//input[@id='P11813101101_PROJECT_LVL_DESC']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsLovSearchField = "(//input[@aria-label='Search'])[3]";
	/** @Locator Xpath */
	public static final String AdvancereceiptsOrderAmount = "//input[@id='P11813101101_PARD_ORD_AMT']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsAdvanceAmount = "//input[@id='P11813101101_PARD_PROP_ADV_AMT']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsBalanceAmount = "//input[@id='P11813101101_RECEIVED_AMT']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsDocumentNoteAlertCloseIcon = "//span[@class='t-Icon icon-close']";
	/** @Locator Xpath */
	public static final String AdvancereceiptsworkflowOkButton = "//button[@id='B1457704718951134857']";
	/** @Locator Xpath */
	public static final String SalesOrderGeneralTab = "//label[@for='P191513134001_OVERALL_REG_PRM_3']";
	/** @Locator Xpath */
	public static final String SalesOrderincludeAdvAmtCheckBox = "//label[@id='P191513134001_SOH_ADV_REQ_FLAG_LABEL']";
	/** @Locator Xpath */
	public static final String SalesOrderHeaderSaveButton = "//button[@id='B6827452363240500661']";
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
	public static final String SalesOrderHeaderFindButton = "//button[@id='B1252630236654900444']";
	/** @Locator Xpath */
	public static final String AdvReceiptDocumentNumberLovInFind = "//button[@id='P11813101102_DOC_NO_lov_btn']";
	/** @Locator Xpath */
	public static final String AdvReceiptsFindSearchButton = "//button[@id='B423796573044949621']";
	/** Locator @CssSelector */
	public static final String AdvReciptsDocumentDrillDown = "#\\31 648622322342378747_orig > tbody > tr:nth-child(2) > td:nth-child(5) > a";
	/** @Locator Xpath */
	public static final String AdvreceiptWorkFlowHistoryButton = "//button[@id='B2671428328286964510']";
	/** @Locator Xpath */
	public static final String AdvReceiptsQueryOrderAmount = "//span[@id='P11813101101_PARD_ORD_AMT_DISPLAY']";
	/** @Locator Xpath */
	public static final String AdvreceiptsQueryAdvanceAmount = "//span[@id='P11813101101_PARD_PROP_ADV_AMT_1_DISPLAY']";
	/** @Locator Xpath */
	public static final String AdvreceiptsQueryBalanceAmount = "//span[@id='P11813101101_PARD_BAL_AMT_SUM_DISPLAY']";
	/** @Locator Xpath */
	public static final String PendingReceivablesAdvance = "//button[@id='B1540950383586752251']";
	/** @Locator Xpath */
	public static final String PendingReceivableFrame = "//iframe[@title='Pend. Receivable - Advance']";
	/** @Locator Xpath */
	public static final String DocumentNumberLov = "//button[@id='P341310231_DOC_NO_FND_lov_btn']";
	/** @Locator Xpath */
	public static final String PaymentReceiptSearchButton = "//button[@id='B1894928456582770620']";
	/** @Locator Xpath */
	public static final String PayementReceiptCreateButton = "//button[@id='B1566669550507577884']";
	/** @Locator Xpath */
	public static final String BankReceiptsLovButton = "//button[@id='P341310231_BANK_DESC_lov_btn']";
	/** @Locator Xpath */
	public static final String BankReceiptsOkButton = "//button[@id='B1566670435579577893']";
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
	public static final String BankReceiptPendingBalanceAmtField = "//*[@id=\"1566221334302514474_orig\"]/tbody/tr[2]/td[11]";
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
	public static final String SONumberFieldInFind = "//input[@id='P1915131339_SO_NO']";
	/** @Locator Xpath */
	public static final String HeaderSubVoucherType = "//span[@id='P191513134001_SOH_ORDER_TYPE_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderSOPrefixField = "//span[@id='P191513134001_SOH_ORDER_PFX_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderSONumberField = "//span[@id='P191513134001_SOH_ORDER_NO_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderExchangeRateField = "//input[@id='P191513134001_SOH_EXRATE']";
	/** @Locator Xpath */
	public static final String HeaderCPCcodeIDField = "//input[@id='P191513134001_SOH_CC_ID']";
	/** @Locator Xpath */
	public static final String HeaderCPCcodeDescField = "//input[@id='P191513134001_SOH_CC_DESC']";
	/** @Locator Xpath */
	public static final String HeaderCustomerIDField = "//span[@id='P191513134001_SOH_CUST_ID_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderCustomerDescField = "//span[@id='P191513134001_SOH_CUST_NAME_DISPLAY']";
	/** @Locator Xpath */
	public static final String LineWiseItemIDField = "//TD[@CLASS='a-GV-cell u-tS a-GV-frozen a-GV-frozen--start2']";
	/** @Locator Xpath */
	public static final String HeaderPaymentTermField = "//input[@id='P191513134001_SOH_TERM_ID']";
	/** @Locator Xpath */
	public static final String HeaderINCOTermField = "//span[@id='P191513134001_SOH_FOB_ID_DISPLAY']";
	/** @Locator Xpath */
	public static final String HeaderShipViaField = "//span[@id='P191513134001_SOH_SHIPVIA_ID_DISPLAY']";
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
	public static final String LineWiseDeleteButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(19) > a";

	/** @Locator Xpath */
	public static final String SOStatusLineTableText(String SOno) {
		return "//a[text()='" + SOno + "']/../../td[24]";
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
		return "(//*[@id=\"ig_line_ig_grid_vc\"]/div/div/table[1])[2]/tbody/tr[" + i
				+ "]/td[15]/a/span[contains(@style,'blue')]";
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
}
