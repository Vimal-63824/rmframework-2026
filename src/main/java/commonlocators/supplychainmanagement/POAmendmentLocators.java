package commonlocators.supplychainmanagement;

public class POAmendmentLocators {
	/** @locatorType Xpath */
	public static final String POPendingIcon = "//button[@id='B1542579773567124656']";
	/** @locatorType Xpath */
	public static final String SingleAmendButton = "//button[@id='B1542586401121126023']";
	/** @locatorType Xpath */
	public static final String POLocationLovButton = "//button[@id='P59_PAH_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String PoPrefixLovButton = "//button[@id='P59_PFX_lov_btn']";
	/** @locatorType Xpath */
	public static final String PoNumberInput = "//input[@id='P59_PAH_PO_NO']";
	/** @locatorType Xpath */
	public static final String BuyerName = "//input[@id='P59_BUYER_NAME']";
	/** @locatorType Xpath */
	public static final String SupplierName = "//input[@id='P59_SUPLR_NAME']";
	/** @locatorType Xpath */
	public static final String Currency = "//input[@id='P59_PO_CURCY']";
	/** @locatorType Xpath */
	public static final String TickButton = "//button[@id='B3842107840119423713']";
	/** @locatorType Xpath */
	public static final String HeaderLocationId = "//span[@id='P59_PAH_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PONumberHeaderInput = "//input[@id='P59_PAH_PO_NO']";
	/** @locatorType Xpath */
	public static final String SupplierNameHeaderInput = "//input[@id='P59_SUPLR_NAME']";
	/** @locatorType Xpath */
	public static final String CurrencyHeaderInput = "//input[@id='P59_PO_CURCY']";
	/** @locatorType Xpath */
	public static final String StatusHeader = "//span[@id='P59_PAH_STATUS_DISPLAY']";
	/** @locatorType Xpath */
	public static final String AddItemButton = "//button[@id='B3835875345988262779']";
	/** @locatorType Xpath */
	public static final String ItemInput = "//input[@id='C3844873038362461661']";
	/** @locatorType Xpath */
	public static final String ItemInputButton = "//button[@id='C3844873038362461661_lov_btn']";

	/** @locatorType Xpath */
	public static final String ItemTable(int i) {
		return "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[4]";
	}

	/** @locatorType Xpath */
	public static final String ItemDesc(int i) {
		return "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType Xpath */
	public static final String UOM(int i) {
		return "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String NewQuantity(int i) {
		return "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[13]";
	}

	/** @locatorType Xpath */
	public static final String NewUnitCost(int i) {
		return "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[15]";
	}

	/** @locatorType Xpath */
	public static final String Details(int i) {
		return "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[18]";
	}

	/** @locatorType Xpath */
	public static final String LocationIdInput = "//input[@id='P59_PAH_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String PONumberInput = "//input[@id='P59_PAH_PO_NO']";
	/** @locatorType Xpath */
	public static final String SupplierInput = "//input[@id='P59_SUPLR_NAME']";
	/** @locatorType Xpath */
	public static final String CurrencyInput = "//input[@id='P59_PO_CURCY']";
	/** @locatorType Xpath */
	public static final String NewQuantity = "//input[@id='C3495329369077066995']";
	/** @locatorType Xpath */
	public static final String NewUnitCost = "//input[@id='C2717150439837688550']";
	/** @locatorType Xpath */
	public static final String POANumber = "//input[@id='P59_PAH_DOC_NO']";
	/** @locatorType Xpath */
	public static final String ItemTickButton = "//button[@id='B3835875430928262780']";
	/** @locatorType Xpath */
	public static final String HeaderTickButton = "//button[@id='B3842107483554423711']";
	/** @locatorType Xpath */
	public static final String NewTradeAssembleValue = "//input[@id='P161513116813_PALC_NEW_TRD_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String NewTradeAssemblePct = "//input[@id='P161513116813_PALC_NEW_TRD_DISC_PCT_1']";
	/** @locatorType Xpath */
	public static final String NewTradeAssembleAmount = "//input[@id='P161513116813_PALC_NEW_TRD_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String NewSplAssembleValue = "//input[@id='P161513116813_PALC_NEW_SPL_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String NewSplAssemblePct = "//input[@id='P161513116813_PALC_NEW_SPL_DISC_PCT_1']";
	/** @locatorType Xpath */
	public static final String NewSplAssembleAmount = "//input[@id='P161513116813_PALC_NEW_SPL_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String NewCashAssembleValue = "//input[@id='P161513116813_PALC_NEW_CASH_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String NewCashAssemblePct = "//input[@id='P161513116813_PALC_NEW_CASH_DISC_PCT_1']";
	/** @locatorType Xpath */
	public static final String NewCashAssembleAmount = "//input[@id='P161513116813_PALC_NEW_CASH_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String Details = "//h1[text()='Details']";
	/** @locatorType Xpath */
	public static final String ListQuantity = "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @locatorType Xpath */
	public static final String SupplierUOM = "//span[@id='P161513116813_PALC_SUPLR_UOM_DISPLAY']";
	/** @locatorType Xpath */
	public static final String WareHouseName = "//input[@id='P161513116813_PALC_NEW_STORE_NAME']";
	/** @locatorType Xpath */
	public static final String PriceBasis = "//span[@id='P161513116813_PALC_COST_BASIS_DISPLAY']";
	/** @locatorType Xpath */
	public static final String CPCCode = "//span[@id='P161513116813_PALC_NEW_CC_CODE_DISPLAY']";
	/** @locatorType Xpath */
	public static final String CPC_Desc = "//span[@id='P161513116813_PALC_NEW_CC_CODE_DESC_DISPLAY']";
	/** @locatorType Xpath */
	public static final String ConvFactor = "//input[@id='P161513116813_PALC_CONV_FACTOR']";
	/** @locatorType Xpath */
	public static final String LineTickButton = "//button[@id='B1552589212233491101']";
	/** @locatorType Xpath */
	public static final String LineDetailsIframe = "//iframe[@title='Details']";
	/** @locatorType Xpath */
	public static final String GL_Acc_No = "//span[@id='P161513116813_PALC_NEW_PUR_ACCT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String GL_Acc_Desc = "//span[@id='P161513116813_PALC_NEW_PUR_ACCT_DESC_DISPLAY']";
	/** @locatorType Xpath */
	public static final String Line_CloseButton = "//h1[text()='Details']/following-sibling::button[@title='Close']";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@id='B3842107483554423711']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@id='B3842109903683423714']";
	/** @locatorType Xpath */
	public static final String HSNCode = "//button[@id='P161513116813_PALC_HSN_CODE_lov_btn']";
	/** @locatorType Xpath */
	public static final String LineDetailsTwo = "//label[text()='Line Details2']";
	/** @locatorType Xpath */
	public static final String POANumberButton = "//button[@id='P1615131168_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1542580827822124668']";
	/** @locatorType Xpath */
	public static final String POANumberLovButton = "//button[@id='P1615131168_DOC_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String PONumber(String Number) {
		return "//a[text()='" + Number + "']";
	}

	/** @locatorType Xpath */
	public static final String POANumberStatus(String POno) {
		return "//a[text()='" + POno + "']/../../td[18]";
	}

	/** @locatorType Xpath */
	public static final String UnitCostTable(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String LineAmount(String text) {
		return "//td[text()='" + text + "']/../td[10]";
	}

	/** @locatorType Xpath */
	public static final String HSNSACCode(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[11]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercent(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[12]";
	}

	/** @locatorType Xpath */
	public static final String LineDetailButton(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i
				+ "]/td/a/span[contains(@style,'Orange')]";
	}

	/** @locatorType Xpath */
	public static final String SubClass = "//span[@id='P161513106045_POL_PROD_SUB_CLS_DISPLAY']/../span";
	/** @locatorType Xpath */
	public static final String Class = "//input[@id='P161513106045_POL_PROD_CLS_DESC']/../span";
	/** @locatorType Xpath */
	public static final String Wh_Dept_Name = "//input[@id='P161513106045_POL_STORE_NAME']/../span";
	/** @locatorType Xpath */
	public static final String CostBasis = "//input[@id='P161513106045_POL_COST_BASIS']/../span";
	/** @locatorType Xpath */
	public static final String StockUOM = "//input[@id='P161513106045_POL_PROD_UOM']/../span";
	/** @locatorType Xpath */
	public static final String ConversionFactor = "//input[@id='P161513106045_POL_CONV_FACTOR']/../span";
	/** @locatorType Xpath */
	public static final String Gl_Acc_No = "//input[@id='P161513106045_POL_PUR_ACCT']/../span";
	/** @locatorType Xpath */
	public static final String Gl_Acc_Desc = "//input[@id='P161513106045_POL_PUR_ACCT_DESC']/../span";
	/** @locatorType Xpath */
	public static final String CPC_Code = "//input[@id='P161513106045_POL_CC_CODE']/../span";
	/** @locatorType Xpath */
	public static final String CPCDesc = "//input[@id='P161513106045_POL_CC_CODE_DESC']/../span";
	/** @locatorType Xpath */
	public static final String TradePercentage = "//input[@id='P161513106045_POL_TRD_DISC_PCT']/../span";
	/** @locatorType Xpath */
	public static final String TradeAssembleValue = "//input[@id='P161513106045_POL_TRD_ASSBL_VAL']/../span";
	/** @locatorType Xpath */
	public static final String TradeAmount = "//input[@id='P161513106045_POL_TRD_DISC_AMT']/../span";
	/** @locatorType Xpath */
	public static final String SpecialAssembleValue = "//input[@id='P161513106045_POL_SPL_ASSBL_VAL']/../span";
	/** @locatorType Xpath */
	public static final String SpecialDiscountPercentage = "//input[@id='P161513106045_POL_SPL_DISC_PCT']/../span";
	/** @locatorType Xpath */
	public static final String SpecialDiscountAmount = "//input[@id='P161513106045_POL_SPL_DISC_AMT']/../span";
	/** @locatorType Xpath */
	public static final String CashAssembleValue = "//input[@id='P161513106045_POL_CASH_ASSBL_VAL']/../span";
	/** @locatorType Xpath */
	public static final String CashDiscountPercentage = "//input[@id='P161513106045_POL_CASH_DISC_PCT']/../span";
	/** @locatorType Xpath */
	public static final String CashDiscountAmount = "//input[@id='P161513106045_POL_CASH_DISC_AMT']/../span";
	/** @locatorType Xpath */
	public static final String DiscAmountPerQty = "//input[@id='P161513106045_POL_DISC_UNIT_AMT']/../span";
	/** @locatorType Xpath */
	public static final String Supplier_Item = "//input[@id='P161513106045_POL_SUPLR_PROD_ID']/../span";
	/** @locatorType Xpath */
	public static final String Supplier_Item_Desc = "//input[@id='P161513106045_POL_SUPLR_PROD_DESC']/../span";
	/** @locatorType Xpath */
	public static final String ContractId = "//*[@id='P161513106045_POL_CONTRACT_ID']/../span";
	/** @locatorType Xpath */
	public static final String ContractPrefix = "//*[@id='P161513106045_POL_CONTR_PFX']/../span";
	/** @locatorType Xpath */
	public static final String ContractAmendNumber = "//*[@id='P161513106045_POL_CONTR_AMD_NO']/../span";
	/** @locatorType Xpath */
	public static final String POClsQuantity = "//*[@id='P161513106045_POL_CLS_QTY']/../span";
	/** @locatorType Xpath */
	public static final String LocationHeaderId = "//span[@id='P161513106044_POH_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String TotalPoValue = "//span[@id='P161513106044_POH_TOTAL_AMT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String LineAmountWE = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[10]";

	/** @locatorType Xpath */
	public static final String DiscountAmount(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercentage(String text) {
		return "//td[text()='" + text + "']/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String LineAmount(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String POTotalAmount = "//span[@id='P161513106044_POH_TOTAL_AMT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String MatAmountInput = "//span[@id='P161513106044_POH_MAT_AMT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String TaxAmountInput = "//span[@id='P161513106044_POH_TAX_AMT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String DiscountAmountInput = "//span[@id='P161513106044_POH_DISC_AMT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String TotalAmountInput = "//span[@id='P161513106044_POH_TOT_AMT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String RoundOffInput = "//span[@id='P161513106044_POH_RND_AMT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String GeneralTab = "//label[@for='P161513106044_TAB_LINE_2']";
	/** @locatorType Xpath */
	public static final String MatAmountLabel = "//label[@for='P161513106044_POH_MAT_AMT']";
	/** @locatorType Xpath */
	public static final String Old_Supplier_Item = "//span[@id='P161513116813_PALC_OLD_SUPLR_PROD_ID_DISPLAY']";

	/** @locatorType Xpath */
	public static final String ServiceDetails(int i) {
		return "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[19]";
	}

	/** @locatorType Xpath */
	public static final String ServiceLineDetailButton(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[21]";
	}
}
