package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks November 26, 2025
 */
import static org.testng.Assert.assertTrue;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.MaterialReturnLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialReturnFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * This Method used to switch to Create Material return Frame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToCreateMaterialReturnIframe() {
		flag = webDB.waitForElement(MaterialReturnLocators.MaterialReturnCreateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MaterialReturnLocators.MaterialReturnCreateIframe, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReturnLocators.MR_CreateLocationId, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch to Fetch BOM Frame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToFetchMaterialReturnIframe() {
		flag = webDB.waitForElement(MaterialReturnLocators.MaterialReturnFetchIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MaterialReturnLocators.MaterialReturnFetchIframe, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReturnLocators.FetchMIVDocLovBtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click add icon and fill details in creation of Material
	 * Return
	 * 
	 * @author Sakthi-GWL
	 * @param Location
	 * @param Prefix
	 * @param FromWh
	 * @param ToWh
	 * @param Shift
	 */
	public void clickAddIconAndFillUnitBOMNameAndItem(String Location, String Prefix, String Type, String FromWh,
			String ToWh, String Shift) {
		flag = webDB.waitForElement(MaterialReturnLocators.MaterialReturnAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialReturnLocators.MaterialReturnAddIcon, ElementType.Xpath);
			switchToCreateMaterialReturnIframe();
			logs.logging("INFO", "Create New BOM Add Button Clicked");
			commonFunc.searchAndSelectValueInFilters(MaterialReturnLocators.MR_CreateLocationId, Location);
			logs.logging("INFO", "Location Selected: " + Location);
			switchToCreateMaterialReturnIframe();
			commonFunc.searchAndSelectValueInFilters(MaterialReturnLocators.MR_CreatePrefixId, Prefix);
			logs.logging("INFO", "Prefix Selected: " + Prefix);
			switchToCreateMaterialReturnIframe();
			flag = webDB.selectDropDownByText(MaterialReturnLocators.MR_CreateTypeSelect, Type, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(MaterialReturnLocators.MR_CreateFromWHId, FromWh);
				logs.logging("INFO", "FromWH Selected: " + FromWh);
				switchToCreateMaterialReturnIframe();
				commonFunc.searchAndSelectValueInFilters(MaterialReturnLocators.MR_CreateToWHId, ToWh);
				logs.logging("INFO", "ToWH Selected: " + ToWh);
				switchToCreateMaterialReturnIframe();
				commonFunc.searchAndSelectValueInFilters(MaterialReturnLocators.MR_CreateShiftId, Shift);
				logs.logging("INFO", "Shift Selected: " + Shift);
				switchToCreateMaterialReturnIframe();
				logs.logging("INFO", "Type Selected: " + Type);
				flag = webDB.waitForElement(MaterialReturnLocators.MR_CreateTickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(MaterialReturnLocators.MR_CreateTickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForClickElement(MaterialReturnLocators.MR_DisplayDocNo, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Tick Icon Clicked and Material Return Created");
						commonFunc.hardWait(2000);
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						String DocNo = webDB.getTextFromElement(MaterialReturnLocators.MR_DisplayDocNo,
								ElementType.Xpath);
						VariableHandler.setMaterialReturnNumber(DocNo);
						logs.logging("INFO", "Displayed DocNo Number after created: " + DocNo);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Fetch Details and Select MIV
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 * @param MIVNo
	 */
	public void fillTypeAndMIVDocNumberAndFetchLoad(String Type, String MIVNo) {
		flag = webDB.waitForElement(MaterialReturnLocators.MR_FetchButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MaterialReturnLocators.MR_FetchButton, ElementType.Xpath);
			webDB.clickAnElement(MaterialReturnLocators.MR_FetchButton, ElementType.Xpath);
			switchToFetchMaterialReturnIframe();
			logs.logging("INFO", "Fetch Button Clicked Succesfuly");
			flag = webDB.selectDropDownByText(MaterialReturnLocators.FetchTypeSelect, Type, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Type Selected: " + Type);
				commonFunc.searchAndSelectValueInFilters(MaterialReturnLocators.FetchMIVDocLovBtn, MIVNo);
				logs.logging("INFO", "Miv Selected: " + MIVNo);
				switchToFetchMaterialReturnIframe();
				flag = webDB.waitForElement(MaterialReturnLocators.FetchLoadButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(MaterialReturnLocators.FetchLoadButton, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialReturnLocators.FetchTableCheckAll, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Fetch Load Button clicked");
						webDB.clickAnElement(MaterialReturnLocators.FetchTableCheckAll, ElementType.Xpath);
						flag = webDB.waitForElement(MaterialReturnLocators.FetchOkButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Check All Button clicked");
							webDB.clickAnElement(MaterialReturnLocators.FetchOkButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
							if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath);
							}
							flag = webDB.waitForClickElement(MaterialReturnLocators.MR_MoveToInspectionBtn,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Fetch Ok Button clicked");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Tool Life Ref
	 * 
	 * @author Sakthi-GWL
	 */
	public void fillToolLifeRefAndSaveHeader(String Data) {
		commonFunc.verifyAddTableSpecs(MaterialReturnLocators.ToolLifeRefTableData,
				MaterialReturnLocators.ToolLifeRefTableInput, Data);
		flag = webDB.waitForElement(MaterialReturnLocators.MR_HeaderSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MaterialReturnLocators.MR_HeaderSaveButton, ElementType.Xpath);
			webDB.clickAnElement(MaterialReturnLocators.MR_HeaderSaveButton, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
		}
		logs.logging("INFO", "Tool Life Ref Entered: " + Data);
	}

	/**
	 * This Method used to click move to inspection
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickMoveToInspection() {
		flag = webDB.waitForElement(MaterialReturnLocators.MR_MoveToInspectionBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MaterialReturnLocators.MR_MoveToInspectionBtn, ElementType.Xpath);
			webDB.clickAnElement(MaterialReturnLocators.MR_MoveToInspectionBtn, ElementType.Xpath);
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getMaterialReturnNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
		}
		assertTrue(flag);
	}
}
