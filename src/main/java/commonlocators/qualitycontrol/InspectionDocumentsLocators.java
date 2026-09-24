package commonlocators.qualitycontrol;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class InspectionDocumentsLocators {

	/** @locatorType Xpath */
	public static final String PendingSearchField = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String GoButton = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String CreateQCDoc = "//button[@data-testid='Create_QC_Doc']";
	/** @locatorType Xpath */
	public static final String OKButton = "//button[@data-testid='QC_OK']";
	/** @locatorType Xpath */
	public static final String LocationId = "//span[@id='P20171310201_TQHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@data-testid='Entry_Completed']";
	/** @locatorType Xpath */
	public static final String InspactionDocumentsNumber = "//span[@data-testid='P20171310201_TQHD_QC_NO']";
	/** @locatorType Xpath */
	public static final String QcDocumentsButton = "//button[@id='P2017131020_QC_NO_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1239031076426494989']";
	/** @locatorType Xpath */
	public static final String PendingInspectionFrame = "//iframe[@title='Pend. Inspection Request']";
	/** @locatorType Xpath */
	public static final String AcceptedQuantity = "//input[@id='P20171310201_TQHD_ACPT_QTY']";
	/** @locatorType Xpath */
	public static final String BackButton = "//button[@id='B2664662933700794505']";

	/** @locatorType Xpath */
	public static final String InspectionCheckBox(String GrnNo) {
		return "//td[text()='" + GrnNo + "']/../td/input[@type='checkbox']";
	}

	/** @locatorType Xpath */
	public static final String InspectionQuantityInput(String DocNo) {
		return "//input[@id='inputField_" + DocNo + "']";
	}

	/** @locatorType Xpath */
	public static final String InspectionStatusTableText(String Idno) {
		return "(//a[text()='" + Idno + "']/../../td/div[contains(@style,'green')])[last()]";
	}

	/** @locatorType Xpath */
	public static final String VoucherLov = "//button[@id='P20171310150_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingInspectionSearchBtn = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String PendingInspectionSelectAllBtn = "//*[@id='check_all']";
	/** @locatorType Xpath */
	public static final String InspectionPlanNumber = "//input[@data-testid='P20171310201_TQHD_INSP_PLAN_NO_1']";
	/** @locatorType Xpath */
	public static final String InspectiondocumentShowMore = "//button[@id='M']";
	/** @locatorType Xpath */
	public static final String observationTab = "//label[@for='P20171310201_TAB_4']";
	/** @locatorType Xpath */
	public static final String Observationvalue = "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String ObservationSaveBtn = "//button[@data-testid='Save_variable']";
	/** @locatorType Xpath */
	public static final String ObservationValueInput = "//input[@data-testid='TQOB_VALUE']";
	/** @locatorType Xpath */
	public static final String InspectiondocumentShowLess = "//button[@id='L']";
	/** @locatorType Xpath */
	public static final String InspectionDocumentSuccessMsg = "//*[@id='t_Alert_Success']/div/div[2]/div/h6";
	/** @locatorType Xpath */
	public static final String InspectionDocumentNumberLov = "//*[@id='P2017131020_QC_NO_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String InspectionDocumentNumberDrillDown = "//table[@data-testid='H_Result_Data']/tbody/tr[2]/td[5]";
	/** @locatorType Xpath */
	public static final String FindPageSearchBtn = "//button[@data-testid='Find_line']";

	/** @locatorType Xpath */
	public static final String InspectionCheckBox(int i) {
		return "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[4]";
	}

	/** @locatorType Xpath */
	public static final String Observationvalue(int i) {
		return "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String ObsLineNumber = "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String EnterAcceptQty = "//input[@id=\"P20171310201_TQHD_ACPT_QTY\"]";
	/** @locatorType Xpath */
	public static final String EnterAODtQty = "//input[@id=\"P20171310201_TQHD_AOD_QTY\"]";

	/** @locatorType Xpath */
	public static final String RejtQty = "//input[@id=\"P20171310201_TQHD_REJ_QTY\"]";
	/** @locatorType Xpath */
	public static final String OkButtonFwdForQcDocument = "//button[@data-testid='Entry']";
	/** @locatorType Xpath */
	public static final String PendingProdOrderLov = "//*[@data-testid='P20171310150_PROD_ORD_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PendingVoucherNoLov = "//*[@data-testid='P20171310150_VOU_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PendingInsReqNoLov = "//*[@data-testid='P20171310150_INSP_REQ_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String InspDocHeaderCancelButton = "//button[@id='B4177685788772509642']";
	/** @locatorType Xpath */
	public static final String InspDocPendingShowDoc = "//button[@id='show']";
	/** @locatorType Xpath */
	public static final String InspDocPendingTableDocNo = "//table[@id='8792142794784090710']//tr/td[5]";
	/** @locatorType Xpath */
	public static final String InspCharacter = "(//input[@data-testid='P20171310201_TAB'])[4]/following-sibling::label";
	/** @locatorType Xpath */
	public static final String SampleTableValue = "//div[@id='char_ig_content_container']/div[1]/div[3]/div[3]/table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String ObservationTableValue = "//div[@id='char_ig_content_container']/div[1]/div[3]/div[3]/table/tbody/tr/td[5]";

	/** @locatorType Xpath */
	public static final String ObservationTableValueInput(int i) {
		return "//div[@id='char_ig_content_container']/div[1]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[5]//input";

	}

	/** @locatorType Xpath */
	public static final String SampleTableValueInput(int i) {
		return "//div[@id='char_ig_content_container']/div[1]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[4]//input";
	}

	/** @locatorType Xpath */
	public static final String TickButtonInspChar = "//button[@data-testid='Char_SAVE']";
	/** @locatorType Xpath */
	public static final String ProductionOrderNoLovButton = "//input[@data-testid='P20171310150_PROD_ORD_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String InspectionPlanLovButton = "//input[@data-testid='P20171310201_TQHD_INSP_PLAN_NO_1']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String InsDocumentDrillDown = "//table[@data-testid='H_Result_Data']/tbody/tr/th";

	/** @locatorType Xpath */
	public static final String InsDocumentDrillDown() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(InsDocumentDrillDown, "Doc. No.");
	}

	/** @locatorType Xpath */
	public static final String PendingPOShowMyRecordBtn = "//button[@id='show']";
	/** @locatorType Xpath */
	public static final String PendingPOShowMyRecordHideBtn = "//button[@id='hide']";

}
