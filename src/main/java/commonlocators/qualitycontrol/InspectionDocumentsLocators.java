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
		return "//td[text()='" + GrnNo + "']/../td[18]";
	}

	/** @locatorType Xpath */
	public static final String InspectionStatusTableText(String Idno) {
		return "//a[text()='" + Idno + "']/../../td[20]";
	}

}
