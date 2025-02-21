package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;
import java.math.BigDecimal;
import commonfunctions.CommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.ItemDetailsHandler.OpenSOAmdDetails;
import commonfunctions.ItemDetailsHandler.OpenSoAmdLinesDetails;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.OpenSoAmdLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class OpenSoAmdFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();

	/**
	 * To Click a Open Sales Order Amd
	 * 
	 * @author Kowshik
	 */
	public void ClickOpenSoAmdendmentButton() {
		flag = webDB.waitForElement(OpenSoAmdLocators.OpenSoAmdRadio, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.OpenSoAmdRadio, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.OpensoAmdPlusBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Open So Amd screen Opened Sucessfully");
			} else {
				logs.logging("Info", "Failed To Open The Open Amendment Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Add button in Find Page
	 * 
	 * @author Kowshik
	 */
	public void clickAddButtoninFindPage() {
		webDB.waitForElement(OpenSoAmdLocators.OpensoAmdPlusBtn, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(OpenSoAmdLocators.OpensoAmdPlusBtn, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.CurrentRateContractSearch, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Open So Add Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Passes a Open Sales Order Document In Search Box & Click Go Button
	 * 
	 * @author Kowshik
	 */
	public void takenOpenSoDocumentFromCurrentRateContract(String oso_Docnum) {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(OpenSoAmdLocators.CurrentRateContractSearch, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSoAmdLocators.CurrentRateContractSearch, oso_Docnum, ElementType.Xpath);
			logs.logging("Info", "Open So Document is Sended Successfully " + oso_Docnum);
			flag = webDB.waitForElement(OpenSoAmdLocators.CurrentRateGObtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSoAmdLocators.CurrentRateGObtn, ElementType.Xpath);
				logs.logging("Info", "Go Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Select a Check Flag
	 * 
	 * @author Kowshik
	 */
	public void clickSelectFlagInCurrentRateContract() {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(OpenSoAmdLocators.currentRateDefault, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.currentRateDefault, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.soAmdBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INfo", "Check Box Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a So Amdendment Button In Current Rate Contract Screen
	 * 
	 * @author Kowshik
	 */
	public void clickSoAmdendmentButtonInCurrentRateContract() {
		flag = webDB.waitForElement(OpenSoAmdLocators.soAmdBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.soAmdBtn, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.priceChange, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Succussfully Clicked SoAmd Button");
				commonFunc.hardWait(3000);
			} else {
				logs.logging("Info", "Failed to click So Amdenment Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Changes a Price Class in Open So Amdendment Screen
	 * 
	 * @author Kowshik
	 * @param OsoamdReason
	 */
	public void clickPriceChanges(String OsoamdReason) {
		flag = webDB.waitForElement(OpenSoAmdLocators.priceChange, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.priceChange, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.AmdLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Clicked A Price Changes Button");
				commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.AmdLovBtn, OsoamdReason);
				flag = webDB.waitForElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
					flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("Info", "Ok Button Clicked Successfully & Open So Amd Doc Created Successfully");
					} else {
						logs.logging("Info", "Open So Document Not Created");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Addition Of Item Radio Button in Current Rate Screen
	 * 
	 * @author Kowshik
	 * @param OsoamdReason
	 */
	public void clickAdditionfItemOfRadioButton(String OsoamdReason) {
		flag = webDB.waitForElement(OpenSoAmdLocators.AdditionofItemRadioButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.AdditionofItemRadioButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.AmdLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Clicked a Addition Of Item");
				commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.AmdLovBtn, OsoamdReason);
				flag = webDB.waitForElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
					flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("Info", "Ok Button Clicked Successfully & Open So Amd Doc Created Successfully");
					} else {
						logs.logging("Info", "Open So Document Not Created");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Deletion Of Item in Current Rate Contract Screen
	 * 
	 * @author Kowshik
	 * @param OsoamdReason
	 */
	public void clickDeletationofItem(String OsoamdReason) {
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.DeletionofItem, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.AmdLovBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.AmdLovBtn, OsoamdReason);
				flag = webDB.waitForElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Deletion Of Item Clicked Successfully");
					webDB.clickAnElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
					flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("Info", "Ok Button Clicked Successfully & Open So Amd Doc Created Successfully");

					} else {
						logs.logging("Info", "Failed To Creates Open So Document");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Close a Popup Alert Messages
	 * 
	 * @author Kowshik
	 */
	public void closeAlertMessage() {
		commonFunc.checkRefreshCellsDisappear();
		if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
			webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Update a Open So Linedetails In Revision Price & Mrp Price InModifiacation
	 * Type
	 * 
	 * @author Kowshik
	 * @param Revisised_Price
	 * @param Mrp
	 */
	public void verifyOSOAlinedetailsRevPriceInModificationType(String Revisised_Price, String Mrp) {
		closeAlertMessage();
		verifyAddTableSpecs(OpenSoAmdLocators.revField, OpenSoAmdLocators.RevPrice, Revisised_Price);
		verifyAddTableSpecs(OpenSoAmdLocators.MrpField, OpenSoAmdLocators.Mrpprice, Mrp);
		flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnLineWise, ElementType.Xpath);
		if (flag) {
			logs.logging("Info", "Revision price & Mrp Price Inserted Successfully ");
		}
		assertTrue(flag);
	}

	/**
	 * Update a Open So Linedetails In Revision Price & Mrp Price InModifiacation
	 * Type
	 * 
	 * @author Kowshik
	 * @param Revisised_Price
	 * @param Mrp
	 */
	public void verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(String Revisised_Price, String Mrp) {
		flag = webDB.waitForElement(OpenSoAmdLocators.revField, ElementType.Xpath);
		if (flag) {
			verifyAddTableSpecs(OpenSoAmdLocators.revField, OpenSoAmdLocators.RevPrice, Revisised_Price);
			flag = webDB.waitForElement(OpenSoAmdLocators.MrpField, ElementType.Xpath);
			if (flag) {
				verifyAddTableSpecs(OpenSoAmdLocators.MrpField, OpenSoAmdLocators.Mrpprice, Mrp);
				flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Revision price & Mrp Price Inserted Successfully ");
				} else {
					logs.logging("Info", "Unable to Updated a Revision Price & Mrp Price ");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert a Discount Percent Field in Line Details in Addition Type
	 * 
	 * @author Kowshik
	 * @param Disc_Perent
	 */
	public void insertDiscPercentInAdditionType(String Disc_Perent) {
		flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
		if (flag) {
			verifyAddTableSpecs(OpenSoAmdLocators.DiscFieldClick, OpenSoAmdLocators.DiscPercentInsert, Disc_Perent);
			flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnLineWise, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Disc Percentage Updated By Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Save Button
	 * 
	 * @author Kowshik
	 */
	public void toClickaSaveButton() {
		flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnLineWise, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.SaveBtnLineWise, ElementType.Xpath);
			closeAlertMessage();
			logs.logging("Info", "Save Button Clicked Successfully");
		}
		assertTrue(flag);
	}

	/**
	 * To Update a Effective Date & Effective To Date
	 * 
	 * @author Kowshik
	 * @param Effdate
	 * @param EffTo
	 */
	public void insertEffdateAndEfftoDateInHeaderDetails(String Effdate, String EffTo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenSoAmdLocators.EffFromDate, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSoAmdLocators.EffFromDate, Effdate, ElementType.Xpath);
			webDB.pressTabKey();
			flag = webDB.waitForElement(OpenSoAmdLocators.EffToDate, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(OpenSoAmdLocators.EffToDate, EffTo, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnHeader, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Successfully Inserted a Eff From Date & Eff To Date");
				} else {
					logs.logging("Info", "Unable Passing A Eff From Date & Eff To Date");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Save Button in Header Details
	 * 
	 * @author Kowshik
	 */
	public void toClickSaveButtonInHeader() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnHeader, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.SaveBtnHeader, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Clicked a SaveButton");
				closeAlertMessage();
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click A Post Button In Header Details
	 * 
	 * @author Kowshik
	 */
	public void ToClickPostButtonInHeader() {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Clicked a Post Button Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Get Document Number In Transaction Screen
	 * 
	 * @author Kowshik
	 * @param OsoaDoc
	 */
	public void GetDocumentFromScreen(String OsoaDoc) {
		flag = webDB.waitForElement(OpenSoAmdLocators.OsoaDocNumber, ElementType.Xpath);
		if (flag) {
			OsoaDoc = webDB.getTextFromElement(OpenSoAmdLocators.OsoaDocNumber, ElementType.Xpath);
			VariableHandler.setOSOAdocnum(OsoaDoc);
			logs.logging("info", "Get openso Amendment DocNo Is: " + VariableHandler.getOSOAdocnum());
		}
		assertTrue(flag);
	}

	/**
	 * @author @author Kowshik
	 * 
	 *         This Method is used to Click And Enter A Value In A Table
	 * 
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
				webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue);
				String value = webDB.getAttributeFromElement(InputParameter, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Attribute Value Is: " + value);
				flag = value.contains(Enteredvalue);
				if (flag) {
					logs.logging("INFO", "Verified Entered Value With Get Attribute Value");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * verify Waiting For Aprorval
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyWaitingForApproval1() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			// commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.OpsoForwardApproval,
			// commonFunc.getDataFromTestDataSheet("HodValue"));
			// commonFunc.hardWait(2000);
			// webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame,
			// ElementType.Xpath);
			// logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpsoOkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.OpsoOkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * To Updated a Price Class In Line Details
	 * 
	 * @author Kowshik
	 * @param Price_Class_OSOA
	 */
	public void ToChangesPriceChanges1(String Price_Class_OSOA) {
		flag = webDB.waitForElement(OpenSoAmdLocators.PriceClassClick, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSoAmdLocators.PriceClassClick, ElementType.Xpath);
			webDB.clickAnElement(OpenSoAmdLocators.PriceClassClick, ElementType.Xpath);
			webDB.selectDropDownByText(OpenSoAmdLocators.PriceClassChange, Price_Class_OSOA, ElementType.Xpath);
			logs.logging("INfo", "Successfully Moves To Price Class");
		} else {
			logs.logging("Info", "Unable Passes a Price Changes");
		}
		assertTrue(flag);
	}

	/**
	 * To Click A Add Button Item In Line Details In Addition Type
	 * 
	 * @author Kowshik
	 */
	public void clickAddButtonItemWiseInLineDetailsinAdditionMethod() {
		flag = webDB.waitForElement(OpenSoAmdLocators.AddButtoninAdditionType, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.AddButtoninAdditionType, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Clicked A Add Button ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert A Item In Line Details In Addition Type
	 * 
	 * @author Kowshik
	 * @param Item_Add_Type
	 */
	public void insertIteminLineDetailsinAdditionMethod(String Item_Add_Type) {
		flag = webDB.waitForElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.ItemLovButton, Item_Add_Type);
			flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Insert A Item");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Cancel Stage In Transaction Screen While Navigates To Find Page
	 * 
	 * @author Kowshik
	 */
	public void cancelStageinTransactions() {
		flag = webDB.waitForElement(OpenSoAmdLocators.CancelButton, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(OpenSoAmdLocators.CancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.OsoaDocSearchField, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Clicked a Cancel Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Cancel Status Record In Find Page
	 * 
	 * @author Kowshik
	 * @param OsoaDoc
	 */
	public void verifyCancelRecordDocInFindPage(String OsoaDoc) {
		flag = webDB.waitForElement(OpenSoAmdLocators.OsoaDocSearchField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSoAmdLocators.OsoaDocSearchField, OsoaDoc, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSoAmdLocators.SearchButton, ElementType.Xpath);
				logs.logging("Info", "Successfully Executed a Cancel Stages Record ");
			} else {
				logs.logging("Info", "Unable appears a Record");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Hsn Code Field Null or Not
	 * 
	 * @author Kowshik
	 * @param Hsn_Code
	 */
	public void verifyHsnCodeFieldIsNullOrNot(String Hsn_Code) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
		if (flag) {
			String Text = webDB.getTextFromElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
			logs.logging("Info", "Hsn Code " + Text);
			if (Text == null || Text.trim().isEmpty()) {
				webDB.clickAnElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
				logs.logging("Info", "Hsn Code Is Clicked");
				commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.HsnCodeLov, Hsn_Code);
				toClickaSaveButton();
			} else {
				toClickaSaveButton();
				logs.logging("Info", "Item has been Already Inserted a Hsn Code ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Valid Tax Amount In Curcy Against To The Customer
	 * 
	 * 
	 * @author Kowshik
	 */
	public void validTaxAmountInLineDetailsForExportCustomerbigdecimal() {
		flag = webDB.waitForElement(OpenSoAmdLocators.CurcyField, ElementType.Xpath);
		if (flag) {
			String Curcy = webDB.getTextFromElement(OpenSoAmdLocators.CurcyField, ElementType.Xpath);
			logs.logging("Info", "Successfully validated currency: " + Curcy);
			if (Curcy.contains("INR")) {
				String Igst = webDB.getTextFromElement(OpenSoAmdLocators.IgstFieldInLineDetails, ElementType.Xpath);
				String Cgst = webDB.getTextFromElement(OpenSoAmdLocators.CgstFieldLineDetails, ElementType.Xpath);
				String Sgst = webDB.getTextFromElement(OpenSoAmdLocators.SgstFieldLineDetails, ElementType.Xpath);
				BigDecimal igstValue = new BigDecimal(Igst);
				BigDecimal cgstValue = new BigDecimal(Cgst);
				BigDecimal sgstValue = new BigDecimal(Sgst);
				if (igstValue.compareTo(BigDecimal.ZERO) > 0 || cgstValue.compareTo(BigDecimal.ZERO) > 0
						|| sgstValue.compareTo(BigDecimal.ZERO) > 0) {
					logs.logging("Info",
							"Tax values are valid for INR: IGST=" + Igst + ", CGST=" + Cgst + ", SGST=" + Sgst);
				} else {
					logs.logging("Error", "For INR, at least one of IGST, CGST, or SGST should be greater than zero.");
				}
			} else {
				String Igst = webDB.getTextFromElement(OpenSoAmdLocators.IgstFieldInLineDetails, ElementType.Xpath);
				String Cgst = webDB.getTextFromElement(OpenSoAmdLocators.CgstFieldLineDetails, ElementType.Xpath);
				String Sgst = webDB.getTextFromElement(OpenSoAmdLocators.SgstFieldLineDetails, ElementType.Xpath);
				BigDecimal igstValue = new BigDecimal(Igst);
				BigDecimal cgstValue = new BigDecimal(Cgst);
				BigDecimal sgstValue = new BigDecimal(Sgst);
				if (igstValue.compareTo(BigDecimal.ZERO) == 0 && cgstValue.compareTo(BigDecimal.ZERO) == 0
						&& sgstValue.compareTo(BigDecimal.ZERO) == 0) {
					logs.logging("Info", "Tax values are correctly set to zero for non-INR currency: IGST=" + Igst
							+ ", CGST=" + Cgst + ", SGST=" + Sgst);
				} else {
					logs.logging("Error", "For non-INR, all IGST, CGST, and SGST should be zero.");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Dom Property Validations
	 * 
	 * @author Deepan-GWL
	 * @param locators
	 * @param Value
	 */
	public void verifyDomPropertyValidation(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(locators, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.equals(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Get Text VAlidations
	 * 
	 * @author Deepan-GWL
	 * @param locators
	 * @param Value
	 */
	public void verifyGetTextValidation(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(locators, ElementType.Xpath);
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.equals(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Verify Revision Amoount Against to the Tax Amount
	 * 
	 * @author Kowshik
	 */
	public void validateTaxAmountInLineDetails() {
		flag = webDB.waitForElement(OpenSoAmdLocators.RevPriceField, ElementType.Xpath);
		if (flag) {
			String RevValue = webDB.getTextFromElement(OpenSoAmdLocators.RevPriceField, ElementType.Xpath);
			logs.logging("Info", "Get Revision Price in Line Details " + RevValue);
			flag = webDB.waitForElement(OpenSoAmdLocators.TaxPercent, ElementType.Xpath);
			if (flag) {
				String TaxPercent = webDB.getTextFromElement(OpenSoAmdLocators.TaxPercent, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
				if (flag) {
					String DiscPercent = webDB.getTextFromElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
					if (flag) {
						BigDecimal RevValue1 = new BigDecimal(RevValue);
						BigDecimal TaxPercent1 = new BigDecimal(TaxPercent);
						BigDecimal DiscPercent1 = new BigDecimal(DiscPercent);
						BigDecimal Hundred = new BigDecimal("100");
						BigDecimal Two = new BigDecimal("2");

						BigDecimal discAmountGet = RevValue1.multiply(DiscPercent1).divide(Hundred);
						logs.logging("Info", "Amount Calculated by price & Disc percent " + discAmountGet);

						BigDecimal DiscamountNRevPrice = RevValue1.subtract(discAmountGet);
						logs.logging("Info", "Successfully dedcution by disc Amount" + DiscamountNRevPrice);

						BigDecimal TaxAmount = DiscamountNRevPrice.multiply(TaxPercent1).divide(Hundred);
						logs.logging("Info", "Successfully deducted a Tax amount" + TaxAmount);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Validates Header Details in Open SO Amd
	 * 
	 * @author Kowshik
	 * 
	 * @param Location
	 */
	public void validatesOpenSoAmendmenHeader(String Location) {
		if (flag) {
			verifyGetTextValidation(OpenSoAmdLocators.LocationId,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.LOCATION_ID));
			logs.logging("Info", "Location Id Is Valid");
			verifyGetTextValidation(OpenSoAmdLocators.UnitField,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.UNIT));
			logs.logging("Info", "Unit Id Is Verified");
			verifyGetTextValidation(OpenSoAmdLocators.CustomerIdField,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.CUSTOMER_ID));
			logs.logging("Info", "Customer Id is Verified ");
			verifyDomPropertyValidation(OpenSoAmdLocators.CustomerNameField,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.CUSTOMER_NAME));
			logs.logging("Info", "Customer Name is Verified");
			verifyDomPropertyValidation(OpenSoAmdLocators.OpenSoDocumentNumber,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.OSO_NO));
			logs.logging("Info", "Open Sales Order Document Is Verified ");
		} else {
			logs.logging("Info", "Record is MisMatched");
		}
		assertTrue(flag);
	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyDomesticCustomerLocalGstTypePriceChange() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validatesOpenSoAmendmenHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(1000);
		commonFunc.logoutUser();
		commonFunc.hardWait(1000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Class Change - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyDomesticCustomerLocalGstTypePriceClassChange() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		validatesOpenSoAmendmenHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Addition of Item - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean veriftyLocalCustomerDomesticAdditionofItem() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validatesOpenSoAmendmenHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - Local
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyLocalCustomerDomesticDeletationofItem() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickDeletationofItem(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		validatesOpenSoAmendmenHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify a Cancel Scenrios With Line Items
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyaCancelStagesRecord() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		cancelStageinTransactions();
		verifyCancelRecordDocInFindPage(VariableHandler.getOSOAdocnum());
		return flag;
	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyDomesticCustomerInterstateGstTypePriceChange() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();

		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Class Change - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyDomesticCustomerInterstateGstTypePriceClassChange() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Addition of Item - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean veriftyInterStateCustomerDomesticAdditionofItem() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - Interstate
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyInterStateCustomerDomesticDeletationofItem() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickDeletationofItem(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Change - Export Customer
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyExportCustomerPriceChange() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Class Change - Export Customer
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyExportCustomerPriceClassChanges() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Addition of Item - Export Customer
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyExportCustomerAdditionOfItem() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSoAmdendmentButtonInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Deletion of Item - Export Customer
	 * 
	 * @author Kowshik
	 * @return boolean
	 */
	public boolean verifyExportCustomerDeletationOfItem() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum"));
		clickSoAmdendmentButtonInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickDeletationofItem(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();
		return flag;
	}
}
