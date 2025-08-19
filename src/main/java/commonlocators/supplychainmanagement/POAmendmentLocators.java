package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class POAmendmentLocators {
	/** @locatorType Xpath */
	public static final String POPendingIcon = "//button[@data-testid='Add_PO']";
	/** @locatorType Xpath */
	public static final String SingleAmendButton = "//button[@data-testid='Single_Closeshort']";
	/** @locatorType Xpath */
	public static final String POLocationLovButton = "//input[@data-testid='P59_PAH_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PoPrefixLovButton = "//input[@data-testid='P59_PFX']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PoNumberInput = "//input[@data-testid='P59_PAH_PO_NO']";
	/** @locatorType Xpath */
	public static final String BuyerName = "//input[@data-testid='P59_BUYER_NAME']";
	/** @locatorType Xpath */
	public static final String SupplierName = "//input[@data-testid='P59_SUPLR_NAME']";
	/** @locatorType Xpath */
	public static final String Currency = "//input[@data-testid='P59_PO_CURCY']";
	/** @locatorType Xpath */
	public static final String TickButton = "//button[@data-testid='CREATE']";
	/** @locatorType Xpath */
	public static final String HeaderLocationId = "//span[@data-testid='P59_PAH_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String PONumberHeaderInput = "//input[@data-testid='P59_PAH_PO_NO']";
	/** @locatorType Xpath */
	public static final String SupplierNameHeaderInput = "//input[@data-testid='P59_SUPLR_NAME']";
	/** @locatorType Xpath */
	public static final String CurrencyHeaderInput = "//input[@data-testid='P59_PO_CURCY']";
	/** @locatorType Xpath */
	public static final String StatusHeader = "//span[@data-testid='P59_PAH_STATUS']";
	/** @locatorType Xpath */
	public static final String AddItemButton = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String ItemInput = "//input[@data-testid='PALC_PROD_ID_REV']";
	/** @locatorType Xpath */
	public static final String ItemInputButton = "//input[@data-testid='PALC_PROD_ID_REV']/following-sibling::button";

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
	public static final String PONumberInput = "//input[@data-testid='P59_PAH_PO_NO']";
	/** @locatorType Xpath */
	public static final String SupplierInput = "//input[@data-testid='P59_SUPLR_NAME']";
	/** @locatorType Xpath */
	public static final String CurrencyInput = "//input[@data-testid='P59_PO_CURCY']";
	/** @locatorType Xpath */
	public static final String NewQuantity = "//input[@data-testid='PALC_NEW_PO_QTY']";
	/** @locatorType Xpath */
	public static final String NewUnitCost = "//input[@data-testid='PALC_NEW_UNIT_COST1']";
	/** @locatorType Xpath */
	public static final String POANumber = "//input[@data-testid='P59_PAH_DOC_NO']";
	/** @locatorType Xpath */
	public static final String ItemTickButton = "//button[@data-testid='Save_Line']";
	/** @locatorType Xpath */
	public static final String HeaderTickButton = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String NewTradeAssembleValue = "//input[@data-testid='P161513116813_PALC_NEW_TRD_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String NewTradeAssemblePct = "//input[contains(@data-testid,'P161513116813_PALC_NEW_TRD_DISC_PCT_1')]";
	/** @locatorType Xpath */
	public static final String NewTradeAssembleAmount = "//input[@data-testid='P161513116813_PALC_NEW_TRD_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String NewSplAssembleValue = "//input[@data-testid='P161513116813_PALC_NEW_SPL_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String NewSplAssemblePct = "//input[@data-testid='P161513116813_PALC_NEW_SPL_DISC_PCT_1']";
	/** @locatorType Xpath */
	public static final String NewSplAssembleAmount = "//input[@data-testid='P161513116813_PALC_NEW_SPL_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String NewCashAssembleValue = "//input[@data-testid='P161513116813_PALC_NEW_CASH_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String NewCashAssemblePct = "//input[@data-testid='P161513116813_PALC_NEW_CASH_DISC_PCT_1']";
	/** @locatorType Xpath */
	public static final String NewCashAssembleAmount = "//input[@data-testid='P161513116813_PALC_NEW_CASH_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String Details = "//h1[text()='Details']";
	/** @locatorType Xpath */
	public static final String ListQuantity = "//div[@id='amd_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @locatorType Xpath */
	public static final String SupplierUOM = "//span[@data-testid='P161513116813_PALC_SUPLR_UOM']";
	/** @locatorType Xpath */
	public static final String WareHouseName = "//input[@data-testid='P161513116813_PALC_NEW_STORE_NAME']";
	/** @locatorType Xpath */
	public static final String PriceBasis = "//span[@data-testid='P161513116813_PALC_COST_BASIS']";
	/** @locatorType Xpath */
	public static final String CPCCode = "//span[@data-testid='P161513116813_PALC_NEW_CC_CODE']";
	/** @locatorType Xpath */
	public static final String CPC_Desc = "//span[@data-testid='P161513116813_PALC_NEW_CC_CODE_DESC']";
	/** @locatorType Xpath */
	public static final String ConvFactor = "//input[@data-testid='P161513116813_PALC_CONV_FACTOR']";
	/** @locatorType Xpath */
	public static final String LineTickButton = "//button[@data-testid='SAVE'and @title='Apply Changes']";
	/** @locatorType Xpath */
	public static final String LineDetailsIframe = "//iframe[@title='Details']";
	/** @locatorType Xpath */
	public static final String GL_Acc_No = "//span[@data-testid='P161513116813_PALC_NEW_PUR_ACCT']";
	/** @locatorType Xpath */
	public static final String GL_Acc_Desc = "//span[@data-testid='P161513116813_PALC_NEW_PUR_ACCT_DESC']";
	/** @locatorType Xpath */
	public static final String Line_CloseButton = "//h1[text()='Details']/following-sibling::button[@title='Close']";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@data-testid='Entry_Completed']";
	/** @locatorType Xpath */
	public static final String HSNCode = "//input[@data-testid='P161513116813_PALC_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LineDetailsTwo = "(//input[@data-testid='P161513116813_TYPE']/following-sibling::label)[2]";
	/** @locatorType Xpath */
	public static final String POANumberButton = "//input[@data-testid='P1615131168_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='Find_Lines']";
	/** @locatorType Xpath */
	public static final String POANumberLovButton = "//input[@data-testid='P1615131168_DOC_NO']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String PONumber(String Number) {
		return "//a[text()='" + Number + "']";
	}

	/** @locatorType Xpath */
	public static final String POANumberStatus(String POno) {
		return "//a[text()='" + POno + "']/../../td[20]";
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
				+ "]/td/a/span[contains(@style,'orange')]";
	}

	/** @locatorType Xpath */
	public static final String SubClass = "//span[@id='P161513106045_POL_PROD_SUB_CLS_DISPLAY']/../span";
	/** @locatorType Xpath */
	public static final String Class = "//input[@id='P161513106045_POL_PROD_CLS_DESC']/../span";
	/** @locatorType Xpath */
	public static final String Wh_Dept_Name = "//input[@id='P161513106045_POL_STORE_NAME']/../span";
	/** @locatorType Xpath */
	public static final String CostBasis = "//span[@data-testid='POL_COST_BASIS']";
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
	public static final String TradePercentage = "//span[@data-testid='POL_TRD_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String TradeAssembleValue = "//input[@id='P161513106045_POL_TRD_ASSBL_VAL']/../span";
	/** @locatorType Xpath */
	public static final String TradeAmount = "//input[@id='P161513106045_POL_TRD_DISC_AMT']/../span";
	/** @locatorType Xpath */
	public static final String SpecialAssembleValue = "//input[@id='P161513106045_POL_SPL_ASSBL_VAL']/../span";
	/** @locatorType Xpath */
	public static final String SpecialDiscountPercentage = "//span[@data-testid='POL_SPL_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String SpecialDiscountAmount = "//input[@id='P161513106045_POL_SPL_DISC_AMT']/../span";
	/** @locatorType Xpath */
	public static final String CashAssembleValue = "//input[@id='P161513106045_POL_CASH_ASSBL_VAL']/../span";
	/** @locatorType Xpath */
	public static final String CashDiscountPercentage = "//span[@data-testid='POL_CASH_DISC_PCT']";
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
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i
				+ "]/td/a/span[contains(@style,'orange')]";
	}

	/** @locatorType Xpath */
	public static final String AddPoLine = "//button[@data-testid='Add_PO']";
	/** @locatorType Xpath */
	public static final String CheckBox = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String AddPoLineFrame = "//iframe[@title='Add Lines']";
	/** @locatorType Xpath */
	public static final String OKButton = "//span[text()='OK']/..";
	/** @locatorType Xpath */
	public static final String AddLinesHeader = "//h1[text()='Add Lines']";

	/** @locatorType Xpath */
	public static final String LineDetails(String Item) {
		return "//td[text()='" + Item + "']/../td[20]";
	}

	/** @locatorType Xpath */
	public static final String NewQuantity(String text) {
		return "//td[text()='" + text + "']/../td[13]";
	}

	/** @locatorType Xpath */
	public static final String NewUnitCost(String text) {
		return "//td[text()='" + text + "']/../td[15]";
	}

	/** @locatorType Xpath */
	public static final String CheckBox(String text) {
		return "//td[text()='" + text + "']/../td/input[@type='checkbox']";
	}

	/** @locatorType Xpath */
	public static final String CloseShort = "//button[@data-testid='Closeshort']";

	/** @locatorType Xpath */
	public static final String CloseshortType(String text) {
		return "//td[text()='" + text + "']/../td[text()='Closeshort']";
	}

	/** @locatorType Xpath */
	public static final String CancelLine(String text) {
		return "//td[text()='" + text + "']/../td[last()]/a/span";
	}

	/** @locatorType Xpath */
	public static final String CancelDoc = "//button[@data-testid='Cancel']";

	/** @locatorType Xpath */
	public static final String Item(String text) {
		return "//td[text()='" + text + "']";
	}

	/** @locatorType Xpath */
	public static final String HeaderCheckbox = "(//input[@data-testid='P1615131168_HEAD_LINE']/following-sibling::label)[1]";
	/** @locatorType Xpath */
	public static final String LineCheckbox = "(//input[@data-testid='P1615131168_HEAD_LINE']/following-sibling::label)[2]";
	/** @locatorType Xpath */
	public static final String OKButtonLast = "(//button[text()='OK'])[last()]";

	/** @locatorType Xpath */
	public static final String POANumberCancelStatus(String POno) {
		return "//a[text()='" + POno + "']/../../td[11]";
	}
}
