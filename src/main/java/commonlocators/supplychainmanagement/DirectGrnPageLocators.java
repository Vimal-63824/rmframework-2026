package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
public class DirectGrnPageLocators {

	public static final String LocationLOV = "//input[@data-testid='P16151311031_PORH_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String GrnAddBtn = "//*[@id='active']/span";
	/** @locatorType Xpath */
	public static final String GrnLocLov = "//input[@id='P16151311031_PORH_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GrnType = "//select[@id='P16151311031_PORH_TYPE_1']";
	/** @locatorType Xpath */
	public static final String GrnSupplierLov = "//input[@data-testid='P16151311031_PORH_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GrnSupplierBillNo = "//input[@id='P16151311031_PORH_SUPLR_DOC_N']";
	/** @locatorType Xpath */
	public static final String GrnDcNo = "//input[@id='P16151311031_PORH_DC_NO_1']";
	/** @locatorType Xpath */
	public static final String GrnCreate = "//button[@data-testid='CREATE']";
	/** @locatorType Xpath */
	public static final String GrnLineAdd = "//button[@data-testid='Add_Line']";
	/** @locatorType Xpath */
	public static final String GrnLineItemLov = "//input[@data-testid='PORL_PROD_ID_REV']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GrnLineHsnLov = "//input[@data-testid='PORL_HSN_CODE']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String AddTableGRNScreen = "(//table[@data-testid='add_line_Result_Data'])[2]";

	/** @locatorType Xpath */
	public static final String GRNPageItemTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "Item");
	}

	/** @locatorType Xpath */
	public static final String GRNPageHSNTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "HSN Code");
	}

	/** @locatorType Xpath */
	public static final String GRNPageSupQtyTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "Suplr. Bill Qty.");
	}

	/** @locatorType Xpath */
	public static final String GrnPageSupQtyInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "Suplr. Bill Qty.")
				+ ")[1]//input";
	}

	/** @locatorType Xpath */
	public static final String GRNPageRcptQtyTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "Rcpt. Qty.");
	}

	/** @locatorType Xpath */
	public static final String GRNPageRcptQtyInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "Rcpt. Qty.")
				+ ")[1]//input";
	}

	/** @locatorType Xpath */
	public static final String GRNPageUnitCostTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "Unit Cost");
	}

	/** @locatorType Xpath */
	public static final String GRNPageUnitCostInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "Unit Cost")
				+ ")[1]//input";
	}

	/** @locatorType Xpath */
	public static final String GrnLineSave = "//button[@data-testid='Save_Line']";
	/** @locatorType Xpath */
	public static final String InspAttrTab = "//*[@id='SR_R5658842567854189405_tab']/a/span";
	/** @locatorType Xpath */
	public static final String InspAttrTabBtn = "//span[text()='Attribute']/..";
	/** @locatorType Xpath */
	public static final String InspAttrSpecField = "//*[@id='ig_line_attr_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String InspAttrSpecLov = "//*[@id='ig_line_attr_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]//button[contains(@id,'lov_btn')]";
	/** @locatorType Xpath */
	public static final String InspBottomPage = "//*[@id='t_Body_content']/footer/div[1]/div[2]/table/tbody/tr/th[1]";
	/** @locatorType Xpath */
	public static final String InspTextTab = "//span[text()='Text']/..";
	/** @locatorType Xpath */
	public static final String InspTextField = "//*[@id='ig_line_text_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String InspTextInput = "//input[@data-testid='TQOB_TEXT_VALUE']";
	/** @locatorType Xpath */
	public static final String InspAttrSave = "//button[@data-testid='Save_attr']";
	/** @locatorType Xpath */
	public static final String InspTextSave = "//button[@data-testid='Save_text']";
	/** @locatorType Xpath */
	public static final String AttrNoDataFound = "//*[@id='ig_line_attr_ig_grid_vc_no_msg']";
	/** @locatorType Xpath */
	public static final String TextNoDataFound = "//*[@id='ig_line_text_ig_grid_vc_no_msg']";
	/** @locatorType Xpath */
	public static final String OverallSave = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String ObservationLineNum = "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String RejectQty = "//input[@data-testid='P20171310201_TQHD_REJ_QTY']";
	/** @locatorType Xpath */
	public static final String DefectTab = "//*[@id='P20171310201_TAB']/div/div/div[2]/label";
	/** @locatorType Xpath */
	public static final String DefectTabAdd = "//button[@data-testid='Defect_ADD']";
	/** @locatorType Xpath */
	public static final String DefectLov = "//input[@data-testid='TQDFCT_DFCT_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String DefectField = "//*[@id='defect_st_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String DefectQtyInput = "//input[@data-testid='TQDFCT_DFCT_QTY']";
	/** @locatorType Xpath */
	public static final String DefectQtyField = "//*[@id='defect_st_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String DefectSave = "//button[@data-testid='Defect_SAVE']";
	/** @locatorType Xpath */
	public static final String DefectOverallSave = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String DefectPrimaryQty = "//*[@id='defect_st_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String GrnLineDetails = "//table[@class='a-GV-table']/tbody/tr/td[25]";
	/** @locatorType Xpath */
	public static final String GrnLineTrdDisc = "//input[contains(@id,'PORL_TRD_DISC_PCT')]";
	/** @locatorType Xpath */
	public static final String GrnLineSpclDisc = "//input[contains(@id,'PORL_SPL_DISC_PCT')]";
	/** @locatorType Xpath */
	public static final String GrnLineCashDisc = "//input[contains(@id,'PORL_CASH_DISC_PCT')]";
	/** @locatorType Xpath */
	public static final String GrnLineDetailsFrame = "//iframe[@title='GRN Line']";
	/** @locatorType Xpath */
	public static final String GrnLineDetailsSave = "//button[@title='Btn Save']";

	/** @locatorType Xpath */
	public static final String StdOperatorType(int i) {
		return "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType Xpath */
	public static final String TolFrom(int i) {
		return "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String TolTo(int i) {
		return "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String GrnDetailsBtn(int i) {
		return "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[1]/a";
	}

	/** @locatorType Xpath */
	public static final String GrnLinesCustomMenuBtnThreeDots(int rowNumber) {
		return "(//table[@data-testid='add_line_Result_Data']//tr/td[1]//span)[" + rowNumber + "]";
	}

	/** @locatorType Xpath */
	public static final String DetailsMenuThreeDots = "//ul[@class='custom-row-menu']//*[text()='Details']";
	/** @locatorType Xpath */
	public static final String LnDts_DiscountExpandButton = "//div[@aria-label='Discount']//button";
	/** @locatorType Xpath */
	public static final String LocId = "//input[@data-testid=\"P1615131103_UNIT_LOCATION\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Unit = "//button[@id='P1615131103_UNIT_lov_btn']";
	/** @locatorType Xpath */
	public static final String GRNNO = "//input[@data-testid=\"P1615131103_GRN_NO\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SupplierNameLovButton = "//input[@data-testid=\"P1615131103_SUPPLIER\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemId = "//button[@id='P1615131103_ITEM_lov_btn']";
	/** @locatorType Xpath */
	public static final String ItemDesc = "//input[@id='P1615131103_ITEM_DESC']";
	/** @locatorType Xpath */
	public static final String PO_Status = "//select[@id='P1615131061_STATUS']";
	/** @locatorType Xpath */
	public static final String HeaderCheckBox = "//label[contains(text(),'Header')]";
	/** @locatorType Xpath */
	public static final String OpenPoSearchButton = "//button/span[text()='Search']";
	/** @locatorType Xpath */
	public static final String AddHeaderFrame = "//iframe[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr[2]/td[1]";

	/** @locatorType Xpath */
	public static final String AmendLineFindTableData(int i) {
		return "//table[@data-testid='H_Result_Data']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String AmendFindTableData(int i) {
		return "//table[@data-testid='L_Result_Title']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String GRNDtFrom = "//input[@id='P1615131103_DATE_FROM_input']";
	/** @locatorType Xpath */
	public static final String GRNDtTo = "//input[@id='P1615131103_DATE_TO_input']";
	/** @locatorType Xpath */
	public static final String ClearButton = "//*[@id='Clear']";
	/** @locatorType Xpath */
	public static final String LineCheckBox = "//label[text()='Line(s)']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//table[@data-testid='L_Result_Title']/tr/th[2]";
	/** @locatorType Xpath */
	public static final String GRN_Status = "//select[@id='P1615131103_STATUS']";
	/** @locatorType Xpath */
	public static final String GRN_ImportLRBtn = "//span[text()='Import/LR']/parent::button";
	/** @locatorType Xpath */
	public static final String GRN_ImportBillNoInput = "//input[contains(@id,'PID_BOE_NO')]";
	/** @locatorType Xpath */
	public static final String GRN_ImportBillDateInput = "//a-date-picker[contains(@id,'PID_BOE_DATE')]/input";
	/** @locatorType Xpath */
	public static final String GRN_ImportLROkBtn = "//button[@data-testid='OK_import_LR']";
	/** @locatorType Xpath */
	public static final String GRN_LotSerialButton = "//button[@data-testid='GRNP_GEN_LOT_SER_BTN']";
	/** @locatorType Xpath */
	public static final String GRN_LocationField = "//input[@data-testid='P16151311031_PORH_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String ImportLRButton = "//span[text()='Import/LR']/parent::button";
	/** @locatorType Xpath */
	public static final String BOEInputField = "//input[@id='P161513110303_PID_BOE_NO']";
	/** @locatorType Xpath */
	public static final String BOEDateInputField = "//input[@id='P161513110303_PID_BOE_DATE_input']";
	/** @locatorType Xpath */
	public static final String BOEOkButton = "//button[@data-testid='OK_import_LR']";

	/** @locatorType Xpath */
	public static final String GrnFOCTabledata() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "FOC") + ")[1]";
	}

	/** @locatorType Xpath */
	public static final String GrnFOCTableInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGRNScreen, "FOC")
				+ ")[1]//input";
	}

	/** @locatorType Xpath */
	public static final String ID_AcptQty_Field = "//input[@data-testid='P20171310201_TQHD_ACPT_QTY']";
	/** @locatorType Xpath */
	public static final String DCDateInput = "//a-date-picker[contains(@id,'PORH_DC_DATE')]//input";

	/** @locatorType Xpath */
	public static final String GRNAmendFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(GRNFindTable, i);
	}

	/** @locatorType Xpath */
	public static final String GRNFindTable = "//table[@data-testid='L_Result_Data']/tr/th";
	/** @locatorType Xpath */
	public static final String GRNLineFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr/th";

	/** @locatorType Xpath */
	public static final String GRNAmendLineFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(GRNLineFindTable, i);
	}

	/** @locatorType Xpath */
	public static final String GrnLineDetailsNew = "//*[@id=\"t_PageBody\"]/ul/li[1]/a";
	/** @locatorType Xpath */
	public static final String GrnLineDetDiscount = "//h2[text()='Discount']/../..//button";
	/** @locatorType Xpath */
	public static final String GrnLineDetailsexpandButton = "(//div[@aria-label='Discount']//button)[1]";
}
