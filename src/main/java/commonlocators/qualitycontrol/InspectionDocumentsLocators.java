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
	public static final String CreateQCDoc = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String OKButton = "//button[@id='B3544221197950765660']";
	/** @locatorType Xpath */
	public static final String LocationId = "//span[@id='P20171310201_TQHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@id='B3490118160695547080']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@id='B3505599130370286602']";
	/** @locatorType Xpath */
	public static final String InspactionDocumentsNumber = "//span[@id='P20171310201_TQHD_QC_NO_DISPLAY']";
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
		return "(//a[text()='" + Idno + "']/../../td/div[contains(@style,'green')])[1]";
	}

	/** @locatorType Xpath */
	public static final String VoucherLov = "//*[@id='P20171310150_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingInspectionSearchBtn = "//*[@id='B2195636680544488712']";
	/** @locatorType Xpath */
	public static final String PendingInspectionSelectAllBtn = "//*[@id='check_all']";
	/** @locatorType Xpath */
	public static final String InspectionPlanNumber = "//*[@id='P20171310201_TQHD_INSP_PLAN_NO_1']";
	/** @locatorType Xpath */
	public static final String InspectiondocumentShowMore = "//*[@id='SHOW_MORE']";
	/** @locatorType Xpath */
	public static final String observationTab = "//*[@id='P20171310201_TAB']/div/div/div[5]/label";
	/** @locatorType Xpath */
	public static final String Observationvalue = "//*[@id='ig_line_var_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String ObservationSaveBtn = "//*[@id='B2163567216713391242']";
	/** @locatorType Xpath */
	public static final String ObservationValueInput = "//*[@id='C5659279342068436784']";
	/** @locatorType Xpath */
	public static final String InspectiondocumentShowLess = "//*[@id='SHOW_LESS']/span[2]";
	/** @locatorType Xpath */
	public static final String InspectionDocumentSuccessMsg = "//*[@id='t_Alert_Success']/div/div[2]/div/h6";
	/** @locatorType Xpath */
	public static final String InspectionDocumentNumberLov = "//*[@id='P2017131020_QC_NO_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String InspectionDocumentNumberDrillDown = "//*[@id='11572094511624389335_orig']/tbody/tr[2]/td[7]/a";
	/** @locatorType Xpath */
	public static final String FindPageSearchBtn = "//*[@id='B1239031076426494989']";

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

}
