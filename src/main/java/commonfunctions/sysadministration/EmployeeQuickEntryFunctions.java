package commonfunctions.sysadministration;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 14, 2025
 */
import commonfunctions.CommonFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.sysadministration.EmployeeQuickEntryLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class EmployeeQuickEntryFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This Method Is Used To Create New Employee
	 * 
	 * @author Deepan-GWL
	 * @param EmployeeName
	 * @param Designation
	 * @param Department
	 * @param MobileNumber
	 * @param Email
	 */
	public void verifyCreateNewEmployee(String EmpId, String EmployeeName, String Designation, String Department,
			String MobileNumber, String Email) {
		flag = webDB.waitForElement(CommonUiLocators.AddEmployeeButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.AddEmployeeButton, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeQuickEntryLocators.EmployeeQuickEntryHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Employee Button Is Clicked");
				webDB.switchToFrame(EmployeeQuickEntryLocators.EmployeeQuickEntryIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeQuickEntryLocators.EmpName, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(EmployeeQuickEntryLocators.EmpId, EmpId, ElementType.Xpath);
					webDB.sendTextToAnElement(EmployeeQuickEntryLocators.EmpName, EmployeeName, ElementType.Xpath);
					logs.logging("INFO", "Send Text Employee Name Is: " + EmployeeName);
					webDB.sendTextToAnElement(EmployeeQuickEntryLocators.Dob,
							commonFunc.getDataFromTestDataSheet("DOB"), ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(EmployeeQuickEntryLocators.Designation, Designation);
					webDB.switchToFrame(EmployeeQuickEntryLocators.EmployeeQuickEntryIframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(EmployeeQuickEntryLocators.Department, Department);
					webDB.switchToFrame(EmployeeQuickEntryLocators.EmployeeQuickEntryIframe, ElementType.Xpath);
					webDB.sendTextToAnElement(EmployeeQuickEntryLocators.MobileNumber, MobileNumber, ElementType.Xpath);
					logs.logging("INFO", "Send Text Mobile Number Is: " + MobileNumber);
					webDB.sendTextToAnElement(EmployeeQuickEntryLocators.MailId, Email, ElementType.Xpath);
					logs.logging("INFO", "Send Text Email Is: " + Email);
					flag = webDB.waitForElement(EmployeeQuickEntryLocators.TickButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(EmployeeQuickEntryLocators.TickButton, ElementType.Xpath);
						webDB.switchToDefaultContent();
						if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
							logs.logging("INFO", "Employee Is Created Successfully");
						}
					}
				}
			}
		}
	}
}
