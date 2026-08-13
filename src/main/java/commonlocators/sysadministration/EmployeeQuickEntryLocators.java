package commonlocators.sysadministration;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 14, 2025
 */
public class EmployeeQuickEntryLocators {

	/** @locatorType XPATH */
	public static final String EmployeeQuickEntryHeader = "//h1[text()='Employee Quick Entry']";
	/** @locatorType XPATH */
	public static final String EmployeeQuickEntryIframe = "//iframe[@title='Employee Quick Entry']";
	/** @locatorType XPATH */
	public static final String EmpName = "//input[@data-testid='EMP_quk_enry_empname']";
	/** @locatorType XPATH */
	public static final String Designation = "//input[@data-testid='EMP_quk_enry_desig']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String Department = "//input[@data-testid='EMP_quk_enry_dept']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String MobileNumber = "//input[@data-testid='EMP_quk_enry_phone']";
	/** @locatorType XPATH */
	public static final String MailId = "//input[@data-testid='EMP_quk_enry_mail']";
	/** @locatorType XPATH */
	public static final String TickButton = "//button[@title='Create']";
	/** @locatorType XPATH */
	public static final String EmpId = "//input[@data-testid='EMP_quk_enry_empid']";
	/** @locatorType XPATH */
	public static final String Dob = "//input[contains(@id,'EMP_DOB_input')]";

}
