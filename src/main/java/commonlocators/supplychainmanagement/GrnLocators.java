package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class GrnLocators {
	/** @locatorType Xpath */
	public static final String ClickPendingButton = "//button[contains(@id,'B1177501459619681687')]";
	/** @locatorType Xpath */
	public static final String PendingPO = "//span[contains(@class,'t-Icon t-Icon--left fa fa-dot-circle-o')]";
	/** @locatorType Xpath */
	public static final String PendingPOIframe = "//iframe[@title='Pending PO/SS/ST/GE']";
	/** @locatorType Xpath */
	public static final String PendingPOheader = "//h2[contains(@id,'R2323283483953540029_heading')]/..";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderSelectPONumberLOV = "//button[contains(@id,'P1615131100_PO_NO_lov_btn')]";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderPONumberInput = "//button[contains(@id,'P1615131100_PO_NO_lov_btn')]";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderPONumberSelectValue = "//button[contains(@id,'B1636066413123209003')]";
	/** @locatorType Xpath */
	public static final String PendingLocLov = "//button[contains(@id,'P1615131100_PLNT_LOC_lov_btn')]";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderSearchButton = "//*[@id=\"B2327879634622919302\"]/span";
	/** @locatorType Xpath */
	public static final String PendingPOSelectAllButton = "//input[contains(@id,'check_all')]/..";
	/** @locatorType Xpath */
	public static final String PendingPOCreateGRN = "//*[@id=\"B1\"]/span";
	/** @locatorType Xpath */
	public static final String PendingPOClickSupplierBillnumber = "//*[@id=\"P1615131100_SUPLR_BILL_NO\"]";
	/** @locatorType Xpath */
	public static final String PendingPOClickDCNumber = "//*[@id=\"P1615131100_DC_NO\"]";
	/** @locatorType Xpath */
	public static final String PendingPOClickOKButton = "//button[contains(@id,'B1640402214415628648')]";
	/** @locatorType Xpath */
	public static final String grnheader = "//*[@id=\"t_TreeNav_226\"]/div[2]/a";
	/** @locatorType Xpath */
	public static final String ClickGrnPurchase = "//*[@id=\"t_TreeNav_21\"]/div[2]/a";
	/** @locatorType Xpath */
	public static final String Lov = "//button[contains(@id,'P1615131103_UNIT_LOCATION_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LotSerial = "//button[contains(@id,'B3157814851916052373')]";
	/** @locatorType Xpath */
	public static final String OverAllSave = "//button[contains(@id,'B3081276464380430661')]";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[contains(@id,'B3151456351351901396')]";
	/** @locatorType Xpath */
	public static final String GrnDocNumber = "//span[contains(@id,'P161513110303_PORH_RECEIPT_NO_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String DocAprovalIfrmae = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String LotSerailButton = "//button[contains(@id,'B3157814851916052373')]";
	/** @locatorType Xpath */
	public static final String AddLotSerialItem = "//*[@id=\"B3161024549306520630\"]/span";
	/** @locatorType Xpath */
	public static final String LotSerialItemLov = "//button[contains(@id,'C1869923325925966449_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LotserialItemInput = "//input[contains(@class,'a-PopupLOV-search apex-item-text')]";
	/** @locatorType Xpath */
	public static final String LotNumberInput = "//input[contains(@id,'C1885841721969241901')]";
	/** @locatorType Xpath */
	public static final String SerialNumberInput = "//input[contains(@id,'C1885842032286241904')]";
	/** @locatorType Xpath */
	public static final String LotSerialSaveButton = "//button[contains(@id,'B3161025382308520635')]";
	/** @locatorType Xpath */
	public static final String AportionLsQty = "//button[contains(@id,'B1886589468336963511')]";
	/** @locatorType Xpath */
	public static final String LotSerialIframe = "//iframe[@title='GRN']";
	/** @locatorType Xpath */
	public static final String LotclickInput = "//div[@id='grn_lot_ig_grid_vc']/*[3]/*[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String LotSerialDate = "//div[@id='grn_lot_ig_grid_vc']/*[3]/*[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String DateInput = "08-02-2027";
	/** @locatorType Xpath */
	public static final String LotSerialSave = "//button[contains(@id,'B3161025382308520635')]";
	/** @locatorType Xpath */
	public static final String ApportionLSQty = "//button[contains(@id,'B1886589468336963511')]";
	/** @locatorType Xpath */
	public static final String SerialClickInput = "//div[@id='grn_lot_ig_grid_vc']/*[3]/*[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String PendingTable = "//div[@id='PEND_content']/div[6]/div/table/tbody/tr/td[1]";

	/** @locatorType Xpath */
	public static final String CheckBox(String PONumber) {
		return "//td[text()='PO100/00708/24-25']/../td[15]";
	}

	/** @locatorType Xpath */
	public static final String GrnNumberSelect = "//button[@id='P1615131103_GRN_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1177499091828681684']";
	/** @locatorType Xpath */
	public static final String ExpiryDate = "//input[@id='C1885842449099241908_input']";

	/** @locatorType Xpath */
	public static final String GrnStatusTableText(String Grnno) {
		return "//a[text()='" + Grnno + "']/../../td[14]";
	}

	/** @locatorType Xpath */
	public static final String row = "//*[@id=\"PopupLov_161513110304_C1869923325925966449_dlg\"]/div[2]/div[2]/div[3]/table/tbody/tr[1]";
	/** @locatorType Xpath */
	public static final String LotSerialItemSearch = "//button[@class='a-Button a-PopupLOV-doSearch']";
	/** @locatorType Xpath */
	public static final String LotserialitemsInput = "//input[contains(@class,'a-PopupLOV-search apex-item-text')]";
	/** @locatorType Xpath */
	public static final String SerialInputDisabled = "//input[@id='C1885842032286241904']";
	/** @locatorType Xpath */
	public static final String LotInputDisabled = "//input[@id='C1885841721969241901']";
	/** @locatorType Xpath */
	public static final String lotserialHeaderclick = "//*[@id=\"wwvFlowForm\"]/div[1]/div[2]/div";
	/** @locatorType Xpath */
	public static final String LotSerialQtyField = "//*[@id=\"grn_lot_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType Xpath */
	public static final String CustomerInvoiceInputNumber = "//input[@class='a-PopupLOV-search apex-item-text']";
	/** @locatorType Xpath */
	public static final String SelectInvoiceNumber = "//li[@class='a-IconList-item']";
	/** @locatorType Xpath */
	public static final String GrnHeaderLocation = "//*[@id=\"P161513110303_PORH_PLNT_LOC_ID_DISPLAY\"]";
	/** @locatorType Xpath */
	public static final String GrnStockTransferCreation = "//*[@id=\"BTN_GRN_STS\"]";
	/** @locatorType Xpath */
	public static final String LotserialItemSelect = "//*[@id=\"PopupLov_161513110304_C1869923325925966449_dlg\"]/div[2]/div[2]/div[3]/table/tbody/tr/td[1]/span";
	/** @locatorType Xpath */
	public static final String PendingStockTransfer = "//button[@id='B1636066803626209007']";
	/** @locatorType Xpath */
	public static final String CustomerInvoiceNumberLov = "//button[@id='P1615131100_INV_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String grn_row(int i) {
		return "//*[@id=\"PopupLov_161513110304_C1869923325925966449_dlg\"]/div[2]/div[2]/div[3]/table/tbody/tr[" + i
				+ "]";
	}

}
