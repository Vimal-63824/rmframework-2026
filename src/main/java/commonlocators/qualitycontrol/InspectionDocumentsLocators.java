package commonlocators.qualitycontrol;

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
	public static final String InspectionStatusTableText(String Idno) {
		return "//a[text()='" + Idno + "']/../../td[20]";
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
	public static final String InspectiondocumentShowMore = "//button[@data-testid='Show_More']";
	/** @locatorType Xpath */
	public static final String observationTab = "//label[@for='P20171310201_TAB_4']";
	/** @locatorType Xpath */
	public static final String Observationvalue = "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String ObservationSaveBtn = "//button[@data-testid='Save_variable']";
	/** @locatorType Xpath */
	public static final String ObservationValueInput = "//input[@data-testid='TQOB_VALUE']";
	/** @locatorType Xpath */
	public static final String InspectiondocumentShowLess = "//button[@data-testid='Show_Less']";
	/** @locatorType Xpath */
	public static final String InspectionDocumentSuccessMsg = "//*[@id='t_Alert_Success']/div/div[2]/div/h6";
	/** @locatorType Xpath */
	public static final String InspectionDocumentNumberLov = "//*[@id='P2017131020_QC_NO_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String InspectionDocumentNumberDrillDown = "//*[@id=\"11575179625135231735_orig\"]/tbody/tr[2]/td[7]";
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
}
