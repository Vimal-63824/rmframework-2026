package commonlocators.supplychainmanagement;

public class ShareOfBusinessLocators {

	/** @locatorType Xpath */
	public static final String LocationIdButton = "//button[@id='P1615131010_PDSHD_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String ShareOfBusinessHeader = "//h1[text()='Share of Business']";
	/** @locatorType Xpath */
	public static final String EffectiveFromInput = "//input[@id='P1615131010_PDSHD_EFF_FROM_input']";
	/** @locatorType Xpath */
	public static final String EffectiveToInput = "//input[@id='P1615131010_PDSHD_EFF_TO_input']";
	/** @locatorType Xpath */
	public static final String ItemLovButton = "//button[@id='P1615131010_PDSHD_PROD_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String TickButton = "//button[@id='B2083379916834815216']";
	/** @locatorType Xpath */
	public static final String ShareOfBusinessInput = "//input[@id='P161513101001_PDSHD_DOC_NO']";
	/** @locatorType Xpath */
	public static final String LocationPage = "//span[@id='P161513101001_PDSHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String ProcessLovButton = "//button[@id='P161513101001_PDSHD_PROC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String AddButton = "//button[@id='B2068945434583533010']";
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
	public static final String SOBTickButton = "//button[@id='B2068945594358533011']";
	/** @locatorType Xpath */
	public static final String TypeSelect = "//select[@id='C2073493354379946044']";
	/** @locatorType Xpath */
	public static final String SupplierIdLovButton = "//button[@id='C2073493456583946045_lov_btn']";
	/** @locatorType Xpath */
	public static final String UnitLovButton = "//button[@id='C2073493718725946048_lov_btn']";
	/** @locatorType Xpath */
	public static final String PercentageInput = "//input[@id='C2073493588058946046']";
	/** @locatorType Xpath */
	public static final String PriorityInput = "//input[@id='C2073493617704946047']";
	/** @locatorType Xpath */
	public static final String HeaderTickButton = "//button[@id='B2072973198585236637']";
	/** @locatorType Xpath */
	public static final String HeaderPostButton = "//button[@id='B2073490063107946011']";
	/** @locatorType Xpath */
	public static final String SupplierIdInput = "//input[@id='C2073493456583946045']";
	/** @locatorType Xpath */
	public static final String UnitInput = "//input[@id='C2073493718725946048']";
	/** @locatorType Xpath */
	public static final String SobNumberLovButton = "//button[@id='P1615131010_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String DeleteLine = "//div[@id='SOB_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[8]/a";
	/** @locatorType Xpath */
	public static final String CancelDocuments = "//button[@id='B2073490138987946012']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//table[@id='2072975497432236651_orig']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//table[@id='2068946608094533022_orig']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String SOBLocId = "//button[@id='P1615131010_LOC_NAME_lov_btn']";
	/** @locatorType Xpath */
	public static final String SOBUnit = "//button[@id='P1615131010_PLNT_lov_btn']";
	/** @locatorType Xpath */
	public static final String SOBSupplierId = "//button[@id='P1615131010_SUPPLIER_NAME_lov_btn']";
	/** @locatorType Xpath */
	public static final String SOBDocNo = "//button[@id='P1615131010_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SOBItemName = "//button[@id='P1615131010_ITEM_lov_btn']";
	/** @locatorType Xpath */
	public static final String SOBProcess = "//button[@id='P1615131010_PROCESS_lov_btn']";
	/** @locatorType Xpath */
	public static final String SOBStatus = "//select[@id='P1615131010_STATUS']";
	/** @locatorType Xpath */
	public static final String EffFromInput = "//input[@id='P1615131010_EFF_FROM_input']";
	/** @locatorType Xpath */
	public static final String EffToInput = "//input[@id='P1615131010_EFF_TO_input']";

	/** @locatorType Xpath */
	public static final String SobStatus(String text) {
		return "(//a[text()='" + text + "']/../../td/div[contains(@style,'COLOR')])[1]";
	}

	/** @locatorType Xpath */
	public static final String AmendLineFindTableData(int i) {
		return "//table[@id='2068946608094533022_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String AmendFindTableData(int i) {
		return "//table[@id='2072975497432236651_orig']/tbody/tr/td[" + i + "]";
	}
}
