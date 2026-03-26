package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks December 23, 2025
 */
public class SupplierScheduleOrderLocators {

	/** @locatorType XPATH */
	public static final String PendingSSRFrame = "//iframe[@title='Pending SSR']";
	/** @locatorType XPATH */
	public static final String PendingSSRButton = "(//button[@data-testid='Pend_PR'])[2]";
	/** @locatorType XPATH */
	public static final String SSRNumberLovButton = "//input[@data-testid='P16151310261_RQST_NO']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SearchButton = "//button[@data-testid='Search']";
	/** @locatorType XPATH */
	public static final String SelectAllPendingSSR = "//input[@id='check_all']";
	/** @locatorType XPATH */
	public static final String CreateSSO = "//button[@data-testid='Create_SSO']";
	/** @locatorType XPATH */
	public static final String SupplierIdLovButton = "//input[@data-testid='P16151310261_SUPLR_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String OkButton = "//button[@data-testid='OK']";
	/** @locatorType XPATH */
	public static final String GenerateSchedule = "//button[@data-testid='Generate_Schedule']";
	/** @locatorType XPATH */
	public static final String HeaderSaveButton = "//button[contains(@onclick,\"request:'SAVE'\")]";
	/** @locatorType XPATH */
	public static final String HeaderPostButton = "//button[@data-testid='Post']";
	/** @locatorType XPATH */
	public static final String CheckBox = "//table[@id='3547693922266317149_orig']/tbody/tr/td[14]/input";
	/** @locatorType XPATH */
	public static final String SSODocNumber = "//input[@data-testid='P1615131028_SSHD_DOC_NO']";
	/** @locatorType XPATH */
	public static final String AddSSOButton = "//button[@data-testid='Add']";
	/** @locatorType XPATH */
	public static final String SSOLovButton = "//button[contains(@id,'PO_SSO_NO_lov_btn')]";

	/** @locatorType Xpath */
	public static final String ApprovedStatus(String SSONumber) {
		return "//a[text()='" + SSONumber + "']/../../td/div[contains(@style,'color:green')]";
	}

}
