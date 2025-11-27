package commonlocators.qualitycontrol;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks November 20, 2025
 */
public class InspectionRequestLocators {
	/** @locatorType Xpath */
	public static final String InspectionRequestAddIcon = "//button[@id='B755774458318065964']";
	/** @locatorType Xpath */
	public static final String IRCreateTickIcon = "//button[@id='B1568359393035035506']";
	/** @locatorType Xpath */
	public static final String IRLoadButton = "//button[@id='B761912258092902652']";
	/** @locatorType Xpath */
	public static final String IRLoadPendingIframe = "//iframe[@title='Pending']";
	/** @locatorType Xpath */
	public static final String IRLoadPendingSearchBar = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String IRLoadPendingSearchGo = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String IRLoadPendingOkButton = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String InspectionRequestIframe = "//iframe[@title='Inspection Request']";
	/** @locatorType Xpath */
	public static final String IRCreateSubVoucherType = "//select[@id='P20171011_TQPHD_SUB_VOU']";
	/** @locatorType Xpath */
	public static final String IRHeaderSaveIcon = "//button[@id='B2170020504600438304']";
	/** @locatorType Xpath */
	public static final String IRPostButton = "//button[@id='post']";
	/** @locatorType Xpath */
	public static final String IRCreatePrefix = "//input[@data-testid='P20171010_TQPHD_PLN_PFX']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String IRCreateLocationId = "//input[@data-testid='P20171010_TQPHD_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String IRCreateWarehouseId = "//input[@data-testid='P20171010_TQPHD_QC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String IRCreateIncharge = "//input[@data-testid='P20171010_TQPHD_QLTY_INCHARGE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String IRCreateDocDateInput = "//*[@data-testid='P20171010_TQPHD_PLN_DATE']/input";
	/** @locatorType Xpath */
	public static final String IRDisplayDocNumber = "//span[@data-testid='P201710101_TQPHD_PLN_NO']";

	/** @locatorType Xpath */
	public static final String IRPendingDocQuantityInput(String DocNo) {
		return "//table[@id='825081717620827456_orig']//tr/td[text()='" + DocNo + "']/../td/input[@type='number']";
	}

	/** @locatorType Xpath */
	public static final String IRPendingDocCheckBox(String DocNo) {
		return "//table[@id='825081717620827456_orig']//tr/td[text()='" + DocNo + "']/../td/input[@type='checkbox']";
	}
}
