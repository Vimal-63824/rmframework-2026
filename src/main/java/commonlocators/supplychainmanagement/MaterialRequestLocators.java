package commonlocators.supplychainmanagement;

public class MaterialRequestLocators {

	/** @locatorType Xpath */
	public static final String CreateLocationLov = "//button[@id='P93131019_IMRHD_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String RequestType = "//select[@id='P93131019_IMRHD_RQSTBY_TYPE']";
	/** @locatorType Xpath */
	public static final String RequestorLovButton = "//button[@id='P93131019_IMRHD_RQSTBY_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String EmployeeLovButton = "//button[@id='P93131019_IMRHD_EMP_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String TickIconMaterialRequest = "//button[@id='B1149987200445046040']";
	/** @locatorType Xpath */
	public static final String FindSearchBtn = "//button[@id='B166177493378780619']";
	/** @locatorType Xpath */
	public static final String FindProdNoLovBtn = "//button[@id='P93131019_PROD_ORD_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String Find_Status_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[29]/div";
	}

	/** @locatorType Xpath */
	public static final String Find_MRNo_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[5]/a";
	}

	/** @locatorType Xpath */
	public static final String Find_RequestQnty_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[14]";
	}

	/** @locatorType Xpath */
	public static final String Find_RequesterName_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[7]";
	}

	/** @locatorType Xpath */
	public static final String Find_Warehouse_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[8]";
	}

	/** @locatorType Xpath */
	public static final String Find_Item_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[9]";
	}

}
