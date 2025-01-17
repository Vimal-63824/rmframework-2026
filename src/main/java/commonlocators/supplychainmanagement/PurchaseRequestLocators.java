package commonlocators.supplychainmanagement;

public class PurchaseRequestLocators {
	/** @locatorType Xpath */
	public static final String PurchaseIDHeader = "//span[contains(@id,'PRH_RQST_NO_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String PRLocationButton = "//button[contains(@id,'PRH_PLNT_LOC_ID')]";
	/** @locatorType Xpath */
	public static final String PRCPCCodeButton = "//button[contains(@id,'PRH_CC_CODE')]";
	/** @locatorType Xpath */
	public static final String PRTypeSelect = "//select[contains(@id,'PRH_REC_SOURCE')]";
	/** @locatorType Xpath */
	public static final String PRDepartmentSelect = "//select[contains(@id,'PRH_RQST_DEPT_ID')]";
	/** @locatorType Xpath */
	public static final String PRRequesterSelect = "//select[contains(@id,'PRH_REQSTR_ID')]";
	/** @locatorType Xpath */
	public static final String PRPrefixSelect = "//select[contains(@id,'PRH_RQST_PFX')]";
	/** @locatorType Xpath */
	public static final String FindPR_PRNoButton = "//button[contains(@id,'DOC_NO')]";
	/** @locatorType Xpath */
	public static final String FindSearchButton = "//button[@id='B148414063445984831']";
	/** @locatorType Xpath */
	public static final String PRStatusBoldText = "//div[contains(@style,'bold')]";
	
	
	//
	/** @locatorType Xpath */
	public static final String AddLineIcon = "//button[@title='Add Line']";
	/** @locatorType Xpath */
	public static final String SaveLineBtn = "//button[@title='Save Line']";
	/** @locatorType Xpath */
	public static final String SaveHeaderBtn = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String BackIconBtn = "//button[@title='Back']";
	/** @locatorType Xpath */
	public static final String PostBtn = "//button[@id='post']";
	/** @locatorType Xpath */
	public static final String ItemDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[2])[1]";
	/** @locatorType Xpath */
	public static final String QuantityDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[6])[1]";
	/** @locatorType Xpath */
	public static final String QuantityDataLastRowInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[6])[1]/div//input";
	/** @locatorType Xpath */
	public static final String UnitCostDataLastRow = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[7])[1]";
	/** @locatorType Xpath */
	public static final String UnitCostDataLastRowInput = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[7])[1]/div//input";
	/** @locatorType Xpath */
	public static final String ItemDataListIcon = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[2])[1]//span[@class='a-Icon icon-popup-lov'][1]";
	/** @locatorType Xpath */
	public static final String ItemDataPopupSearchBox = "//input[contains(@class,'a-PopupLOV-search')]";
	/** @locatorType Xpath */
	public static final String ItemDataSearchFirstResult = "//tr[contains(@data-id,'FG00011')]";
	/** @locatorType Xpath */
	public static final String ItemDataPopupHighlights = "//span[@class='popup-lov-highlight']";
	/** @locatorType Xpath */
	public static final String LastrowLineNo = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[1])[last()]";
}
