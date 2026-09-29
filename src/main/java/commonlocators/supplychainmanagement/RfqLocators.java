package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
public class RfqLocators {

	/** @locatorType Xpath */
	public static final String RfqPending = "//button[@data-testid='Create_RFQ']";
	/** @locatorType Xpath */
	public static final String RfqPendingFrame = "//iframe[@title='RFQ Conversion']";
	/** @locatorType Xpath */
	public static final String RfqPOPendingFrame = "//iframe[@title='Pending RFQ']";
	/** @locatorType Xpath */
	public static final String RfqPODetailsFrame = "//iframe[@title='Line Details']";
	/** @locatorType Xpath */
	public static final String RfqPendingPrNolov = "//input[@data-testid='P16151310601_PR_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RfqPendingRfqNolov = "//input[@data-testid='P16151310601_RFQ_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RfqPenPRNumInput = "//input[@data-testid='P16151310601_PR_NO']";
	/** @locatorType Xpath */
	public static final String RfqPendingSearchbutton = "//button[@data-testid='Search']";
	/** @locatorType Xpath */
	public static final String RfqPendingSelectall = "//*[@id=\"check_all\"]";
	/** @locatorType Xpath */
	public static final String CreateRfq = "//button[@data-testid='create_rfq']";
	/** @locatorType Xpath */
	public static final String CreateRfqOK = "//button[@data-testid='RF_Ok']";
	/** @locatorType Xpath */
	public static final String CreateRfqDateField = "//*[@id=\"P16151310401_RFQ_DATE1_input\"]";
	/** @locatorType Xpath */
	public static final String RfqSupplierAdd = "//button[@data-testid='Add_supplier']";
	/** @locatorType Xpath */
	public static final String RfqSupplierLov = "//input[@data-testid='RLS_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RfqlineItemfield = "//*[@id=\"add_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String RfqlineSupplierfield = "//*[@id=\"ig_suplr_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String RfqSupplierfield = "//*[@id=\"ig_suplr_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String RfqlineSupplierSaveButton = "//button[@data-testid='Save_supplier']";
	/** @locatorType Xpath */
	public static final String RfqHeaderSendRfq = "//button[@data-testid='EntryCompleted']";
	/** @locatorType Xpath */
	public static final String RfqSupplierTab = "//*[@id=\"P161513105801_TAB\"]/div/div/div[2]/label";
	/** @locatorType Xpath */
	public static final String RfqInsideSupplierField = "//*[@id=\"sup_tab_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String RfqInsideSupplierField2 = "//*[@id=\"sup_tab_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String RfqSupplierCostInput = "//input[@data-testid='PQLN_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String RfqSupplierCostField = "//*[@id=\"ig_sup_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String Rfqpendprselect = "//*[@id=\"PopupLov_16151310401_P16151310401_PR_NO_dlg\"]/div[2]/div/div[3]/ul/li";
	/** @locatorType Xpath */
	public static final String RfqpendPRNumField = "//*[@id=\"P16151310401_PR_NO\"]";
	/** @locatorType Xpath */
	public static final String Rfqsuplinesave = "//button[@data-testid='Save_sup_line']";
	/** @locatorType Xpath */
	public static final String RfqsuplineHsnInput = "//input[@data-testid='PQLN_HSN_CODE']";
	/** @locatorType Xpath */
	public static final String RfqsuplineHsnlov = "//input[@data-testid='PQLN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RfqsuplineTrdDisField = "//*[@id=\"ig_sup_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[26]";
	/** @locatorType Xpath */
	public static final String RfqsuplineTrdDisinput = "//input[@data-testid='PQLN_TRD_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String RfqsuplineSpclDisField = "//*[@id=\"ig_sup_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[29]";
	/** @locatorType Xpath */
	public static final String RfqsuplineSpclDisinput = "//input[@data-testid='PQLN_SPL_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String RfqsuplineCashDisField = "//*[@id=\"ig_sup_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[32]";
	/** @locatorType Xpath */
	public static final String RfqsuplineCashlDisinput = "//input[@data-testid='PQLN_CASH_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String RfqRateFinalize = "//button[@data-testid='EntryCompleted']";
	/** @locatorType Xpath */
	public static final String RfqSupplcurrField1 = "//*[@id=\"sup_tab_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType Xpath */
	public static final String RfqSupplcurrField2 = "//*[@id=\"sup_tab_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[2]/td[4]";
	/** @locatorType Xpath */
	public static final String RfqOverallSave = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String RfqDocNum = "//input[@data-testid='P161513105801_RFQHD_RFQ_NO']";
	/** @locatorType Xpath */
	public static final String RfqHeaderSOB = "//button[@data-testid='SOB']";
	/** @locatorType Xpath */
	public static final String RfqSobItemField = "//*[@id=\"R753134531811453201_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String RfqSobPercField1 = "(//table[@data-testid=\"sob_sup_Result_Data\"])[2]/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String RfqSobFrame = "//iframe[@title='Share Of Business']";
	/** @locatorType Xpath */
	public static final String RfqSobPercInput = "//input[@data-testid='PQDS_PCT']";
	/** @locatorType Xpath */
	public static final String RfqSobSupplierField1 = "//*[@id=\"sob_sup_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String RfqSobSupplierField2 = "//*[@id=\"sob_sup_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[2]/td[3]";
	/** @locatorType Xpath */
	public static final String RfqSobPercField2 = "//*[@id=\"sob_sup_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[2]/td[9]";
	/** @locatorType Xpath */
	public static final String RfqSobPercInput2 = "//input[@data-testid='PQDS_PCT']";
	/** @locatorType Xpath */
	public static final String RfqSobSave = "//button[@data-testid='Save_suplr']";
	/** @locatorType Xpath */
	public static final String RfqSobClose = "//*[@id=\"t_PageBody\"]/div[26]/div[1]/button/span";// *[@id="t_PageBody"]/div[26]/div[1]/button/span
	/** @locatorType Xpath */
	public static final String RfqSobClose1 = "//button[@class='ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-close']";
	/** @locatorType Xpath */
	public static final String RfqPost = "//button[@data-testid='EntryCompleted']";

	// label[contains(text(),'Header')]
	/** @locatorType Xpath */
	public static final String RfqAfterpost = "//*[@id=\"P161513105806_RFQHD_STATUS_DISPLAY\"]";
	/** @locatorType Xpath */
	public static final String PRAdd = "//button[@data-testid='PR_ADDLINE']";
	/** @locatorType Xpath */
	public static final String PRItemLov = "//input[@data-testid='PR_ITEMS_TABLE_DATA']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PRQtyField = "//*[@id=\"ig_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String PRQtyInput = "//input[@data-testid='PR_QUANTITY_TABLE_DATA']";
	/** @locatorType Xpath */
	public static final String PRLineSave = "//button[@data-testid='Save_line']";
	/** @locatorType Xpath */
	public static final String PRItemsearch = "//*[@id=\"PopupLov_161513103001_C7900387663950353597_dlg\"]/div[1]/input";
	/** @locatorType Xpath */
	public static final String PRItemSelect = "//*[@id=\"PopupLov_161513103001_C7900387663950353597_dlg\"]/div[2]/div[2]/div[3]/table/tbody/tr/td[1]/span";
	/** @locatorType Xpath */
	public static final String PRHeaderUnit = "//*[@id=\"P161513103001_PRH_PLANT\"]";
	/** @locatorType Xpath */
	public static final String PurchaseIDHeader = "//span[@id='P161513103001_PRH_RQST_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String RfqQtyField = "//*[@id=\"add_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String PendingPo = "//button[@data-testid='Create_PO']";
	/** @locatorType Xpath */
	public static final String PendingRfq = "//button[@data-testid='QUOTE_PO']";
	/** @locatorType Xpath */
	public static final String PendingRfQNum = "//input[@id='P16151310601_RFQ_NO']";
	/** @locatorType Xpath */
	public static final String PORfqPendingSearchbutton = "//button[@data-testid='Search']";
	/** @locatorType Xpath */
	public static final String PoRfqPendingSelectall = "//input[@id='check_all'][1]";
	/** @locatorType Xpath */
	public static final String CreatePoBtn = "//button[@data-testid='Create_PO']";
	/** @locatorType Xpath */
	public static final String BtmPage = "//th[text()='ROADMAP AUTOMATION']";
	/** @locatorType Xpath */
	public static final String RfqLineNum = "//*[@id='add_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String SupLineNum = "//*[@id='ig_sup_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String OkForwardButton = "//button[@data-testid='PO_Ok_Button']";
	/** @locatorType Xpath */
	public static final String PORfqAdd = "//button[@data-testid='Add']";

	/** @locatorType Xpath */
	public static final String ItemName(int i) {
		return "//div[@id='add_line_ig_grid_vc']/div/div/table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String ItemNameInside(int i) {
		return "//*[@id=\"ig_sup_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String SupCostField(int i) {
		return "//*[@id='ig_sup_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String QuoteQtyField(int i) {
		return "//*[@id=\"ig_sup_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String HsnField(int i) {
		return "//*[@id='ig_sup_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[14]";
	}

	/** @locatorType Xpath */
	public static final String TrdDiscField(int i) {
		return "//*[@id=\'ig_sup_line_ig_grid_vc\']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[26]";
	}

	/** @locatorType Xpath */
	public static final String SpclDiscField(int i) {
		return "//*[@id=\'ig_sup_line_ig_grid_vc\']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[29]";
	}

	/** @locatorType Xpath */
	public static final String CashDiscField(int i) {
		return "//*[@id=\'ig_sup_line_ig_grid_vc\']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[32]";
	}

	/** @locatorType Xpath */
	public static final String SobItemField(int i) {
		return "//table[@data-testid='R1000711620521711065_Result_Data']/tbody/tr["+ i +"]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String BaseLineField(int i) {
		return "//*[@id=\"ig_sup_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[13]";
	}

	/** @locatorType Xpath */
	public static final String ItemMasterAdd = "//*[@id=\"B1284581566305961864\"]/span";
	/** @locatorType Xpath */
	public static final String FindScreenItemField = "//input[@id='P3670200_ITEM_1']";
	/** @locatorType Xpath */
	public static final String ItemSearchBtn = "//*[@id=\"B1284581952089961873\"]/span";
	/** @locatorType Xpath */
	public static final String ItemDrillDownBtn = "//*[@id=\"15686651324419853443_orig\"]/tbody/tr[2]/td[2]/a";
	/** @locatorType Xpath */
	public static final String ItemGeneral = "//*[@id=\"P367020004_TAB\"]/div/div/div[3]/label";
	/** @locatorType Xpath */
	public static final String ItemCopy = "//*[@id=\"B215754246394063050\"]/span[2]";
	/** @locatorType Xpath */
	public static final String ItemIdInputCopy = "//input[@id='P367020004_ITEM']";
	/** @locatorType Xpath */
	public static final String ItemDescInputCopy = "//input[@id='P367020004_ITEM_DESC']";
	/** @locatorType Xpath */
	public static final String ItemOkCopy = "//*[@id=\"B215751033222063042\"]/span";
	/** @locatorType Xpath */
	public static final String ItemPost = "//*[@id=\"B215434526869062034\"]/span[2]";
	/** @locatorType Xpath */
	public static final String ItemFindBtn = "//*[@id=\"B215430562028062028\"]/span";
	/** @locatorType Xpath */
	public static final String ItemGetText = "//input[@id='P367020004_PROD_ID']";
	/** @locatorType Xpath */
	public static final String RateFinalMessage = "//*[@id=\"t_Alert_Success\"]/div/div[3]/button/span";
	/** @locatorType Xpath */
	public static final String HsnInput = "//input[@data-testid='PQLN_HSN_CODE']";
	/** @locatorType Xpath */
	public static final String QuoteQty = "//*[@id=\"ig_sup_line_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String PoDetailsBtn = "//*[@id=\"ig_po_lineitems_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[32]/a/span";
	/** @locatorType Xpath */
	public static final String PoDetailsBtnCSS = "#ig_po_lineitems_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(1) > a";
	/** @locatorType Xpath */
	public static final String PoLineRfqDetails = "//button[@aria-labelledby=\"a_Collapsible13_R1535540252290798082_heading\"]";
	/** @locatorType Xpath */
	public static final String DetailsRfqNum = "//input[@id='P161513106045_POL_RFQ_NO']";
	/** @locatorType Xpath */
	public static final String PoFromRfqDocNum = "//*[@id=\"t_Alert_Success\"]/div/div[2]/div/h6";
	/** @locatorType Xpath */
	public static final String PORfqPost = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String PoLineDetails2close = "//*[@id=\"t_PageBody\"]/div[30]/div[1]/button/span";
	/** @locatorType Xpath */
	public static final String PoRefind = "//button[@data-testid=\"FIND_PO_1\"]";
	/** @locatorType Xpath */
	public static final String FindPoSearchField = "//button[@id='P1615131061_PO_SSO_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String FindPoSearchContainer = "//*[@id=\"P1615131061_DOC_NO_CONTAINER\"]";

	public String HSNCode(String Item) {
		return "//td[text()='" + Item + "']/../td[14]";
	}

	/** @locatorType Xpath */
	public static final String PRWorkflowFrame = "//*[@id=\"apex_dialog_1\"]/iframe";
	/** @locatorType Xpath */
	public static final String PRForwardlov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String PRworkflowOK = "//button[@data-testid='Entry']";
	/** @locatorType Xpath */
	public static final String PRToastClose = "//*[@id=\"B1612675370193662596\"]/span";
	/** @locatorType Xpath */
	public static final String RfqWorkflowFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String POSearchBtn = "//button[@id='B918972127010025440']";
	/** @locatorType Xpath */
	public static final String PODrillDown = "#\\38 098271753405592657_orig > tbody > tr:nth-child(2) > td:nth-child(9) > a";
	/** @locatorType Xpath */
	public static final String POSuccessMsgClose = "//*[@id=\"t_Alert_Success\"]/div/div[3]/button";
	/** @locatorType Xpath */
	public static final String ItmworkflowOK = "//*[@id=\"B1626605845635418489\"]/span";

	/** @locatorType Xpath */
	public static final String PendBTN = "//button[@data-testid='Create_RFQ']";
	/** @locatorType Xpath */
	public static final String LocId = "//input[@data-testid='P1615131058_LOCATION']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Unit = "//input[@data-testid='P1615131058_UNIT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RFQNO = "//input[@data-testid='P1615131058_RFQ_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SupplierNameLovButton = "//input[@data-testid='P1615131058_SUPPLIER']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemId = "//input[@data-testid='P1615131058_ITEM']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemDesc = "//input[@data-testid='P1615131058_ITEM_DESC']";
	/** @locatorType Xpath */
	public static final String RFQ_Status = "//button[@id='P1615131058_STATUS_lov_btn']";
	/** @locatorType Xpath */
	public static final String HeaderCheckBox = "(//input[@data-testid='P1615131058_RADIO_GRP']/following-sibling::label)[1]";
	/** @locatorType Xpath */
	public static final String RFQSearchButton = "//button[@data-testid='Line']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//table[@data-testid='L_Result_Data']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String LineCheckBox = "(//input[@data-testid='P1615131058_RADIO_GRP']/following-sibling::label)[2]";
	/** @locatorType Xpath */
	public static final String ClearButton = "//button[@data-testid='clear']";

	/** @locatorType Xpath */
	public static final String AmendLineFindTableData(int i) {
		return "//table[@id='1167616873603641043_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String AmendFindTableData(int i) {
		return "//table[@id='10319408940172414178_orig']/tbody/tr/td[" + i + "]";
	}
	
	/** @locatorType Xpath */
	public static final String RFQAmendFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(RFQFindTable, i);
	}

	/** @locatorType Xpath */
	public static final String RFQFindTable = "//table[@data-testid=\"L_Result_Data\"]/tbody/tr/th";
	/** @locatorType Xpath */
	public static final String RFQLineFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr/th";

	/** @locatorType Xpath */
	public static final String RFQAmendLineFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(RFQLineFindTable, i);
	}
	/** @locatorType Xpath */
	public static final String PRTable = "(//table[@data-testid='HD_Result_Data'])[2]";

	/** @locatorType Xpath */
	public static final String ItemDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PRTable, "Item");
	}

	/** @locatorType Xpath */
	public static final String ItemDataLastRowInput = ItemDataLastRow() + "//button";

	/** @locatorType Xpath */
	public static final String QuantityDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PRTable, "RequestQuantity");
	}

	/** @locatorType Xpath */
	public static final String QuantityDataLastRowInput = QuantityDataLastRow() + "//input";

	/** @locatorType Xpath */
	public static final String UnitCostDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PRTable, "UnitCost");
	}

	/** @locatorType Xpath */
	public static final String UnitCostDataLastRowInput = UnitCostDataLastRow() + "//input";
	
	/** @locatorType Xpath */
	public static final String PendingRfqShowMore = "//button[@aria-labelledby=\"a_Collapsible1_FS_heading\"]";
	
	
	
	
	
	

}
