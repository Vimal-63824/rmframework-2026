package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

public class ShareOfBusinessLocators {

	/** @locatorType Xpath */
	public static final String LocationIdButton = "//input[@data-testid='P1615131010_PDSHD_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ShareOfBusinessHeader = "(//h1[text()='Share of Business'])[first()]";
	/** @locatorType Xpath */
	public static final String EffectiveFromInput = "//*[@data-testid='P1615131010_PDSHD_EFF_FROM']/input";
	/** @locatorType Xpath */
	public static final String EffectiveToInput = "//input[@id='P1615131011_PDSHD_EFF_TO_input']";
	/** @locatorType Xpath */
	public static final String ItemLovButton = "//input[@data-testid='P1615131010_PDSHD_PROD_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String TickButton = "//button[@data-testid='Create']";
	/** @locatorType Xpath */
	public static final String ShareOfBusinessInput = "//input[@data-testid='P161513101001_PDSHD_DOC_NO']";
	/** @locatorType Xpath */
	public static final String LocationPage = "//span[@data-testid='P161513101001_PDSHD_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String ProcessLovButton = "//input[@data-testid='P161513101001_PDSHD_PROC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String AddButton = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String TypeTable = "//div[@id='SOB_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String SupplierIdTable = "//div[@id='SOB_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String UnitTable = "//div[@id='SOB_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String PercentageTable = "//div[@id='SOB_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String PriorityTable = "//div[@id='SOB_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String SOBTickButton = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String TypeSelect = "//select[@data-testid='PDSLN_TYPE']";
	/** @locatorType Xpath */
	public static final String SupplierIdLovButton = "//input[@data-testid='PDSLN_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String UnitLovButton = "//input[@data-testid='PDSLN_PLNT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PercentageInput = "//input[@data-testid='PDSLN_PCT']";
	/** @locatorType Xpath */
	public static final String PriorityInput = "//input[@data-testid='PDSLN_PRIORITY']";
	/** @locatorType Xpath */
	public static final String HeaderTickButton = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String HeaderPostButton = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String SupplierIdInput = "//input[@data-testid='PDSLN_SUPLR_ID']";
	/** @locatorType Xpath */
	public static final String UnitInput = "//input[@data-testid='PDSLN_PLNT']";
	/** @locatorType Xpath */
	public static final String SobNumberLovButton = "//input[@data-testid='P1615131010_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String DeleteLine = "//div[@id='SOB_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[8]/a";
	/** @locatorType Xpath */
	public static final String CancelDocuments = "//button[@data-testid='Cancel']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//table[@data-testid='L_Result_Data']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String SOBLocId = "//input[@data-testid='P1615131010_LOC_NAME']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SOBUnit = "//input[@data-testid='P1615131010_PLNT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SOBSupplierId = "//input[@data-testid='P1615131010_SUPPLIER_NAME']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SOBDocNo = "//input[@data-testid='P1615131010_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SOBItemName = "//input[@data-testid='P1615131010_ITEM']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SOBProcess = "//input[@data-testid='P1615131010_PROCESS']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SOBStatus = "//select[@data-testid='P1615131010_STATUS']";
	/** @locatorType Xpath */
	public static final String EffFromInput = "//*[@data-testid='P1615131010_EFF_FROM']/input";
	/** @locatorType Xpath */
	public static final String EffToInput = "//*[@data-testid='P1615131010_EFF_TO']/input";

	/** @locatorType Xpath */
	public static final String SobStatus(String text) {
		return "(//a[text()='" + text + "']/../../td/div[contains(@style,'COLOR')])[1]";
	}

	/** @locatorType Xpath */
	public static final String AmendLineFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(SobLineTable, i);
	}

	/** @locatorType Xpath */
	public static final String AmendFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(SobHeaderTable, i);
	}

	/** @locatorType Xpath */
	public static final String SobLineTable = "//table[@data-testid='L_Result_Data']/tbody/tr/th";
	/** @locatorType Xpath */
	public static final String SobHeaderTable = "//table[@data-testid='H_Result_Data']/tbody/tr/th";
	/** @locatorType Xpath */
	public static final String SOBFrame = "//iframe[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[text()='OK']";
}
