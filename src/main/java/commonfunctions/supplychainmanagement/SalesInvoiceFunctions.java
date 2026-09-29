package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.ItemDetailsHandler.SICustomerDetailsName;
import commonfunctions.VariableHandler;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SalesInvoiceFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	SalesOrderFunctions salesOrderFunctions = new SalesOrderFunctions();
	DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static ExcelUtils excelUtils = new ExcelUtils();
	static MaterialReceiptVoucherFunctions mrvFunc = new MaterialReceiptVoucherFunctions();
	static InspectionDocumentFunctions qcFunc = new InspectionDocumentFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	public ArrayList<String> MultiSIDocNumber = new ArrayList<String>();
	String BRFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "Financials.xlsx";

	/**
	 * Verify Clicking Add Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyClickingAddButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Add_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_header, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button In Sales Invoice Is Clicked");
				String Text = webDB.getTextFromElement(SalesInvoiceLocators.SI_header, ElementType.Xpath);
				logs.logging("INFO", "Get Text Header From Sales Invoice Is: " + Text);
				flag = Text.contains(commonFunc.getDataFromTestDataSheet("Sales_Invoice"));
				if (flag) {
					logs.logging("INFO", "Sales Invoice Screeen Displayed");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param CustomerName
	 * @param SIType
	 */
	public void verifyCreateDirectSalesInvoiceAddingParameters(String LocationId, String SI_Type, String CustomerName) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Loc_ID_Btn, ElementType.Xpath);
		if (flag) {
			String GetTextLocation = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_Location_Field,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!GetTextLocation.equals(LocationId)) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Loc_ID_Btn, LocationId);
			}
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Type_Btn, ElementType.Xpath);
			if (flag) {
				String GetTextSIType = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_Type_Field,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				if (!GetTextSIType.equals(SI_Type)) {
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Type_Btn, SI_Type);
				}
				flag = webDB.waitForElement(SalesInvoiceLocators.Customer_ID_Btn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Customer_ID_Btn, CustomerName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify click Tick Icon in Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyClickingTickButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Create_SI, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Create_SI, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_Pfx, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", " Sales Invoice tick Icon Is Clicked");
				String SIdocno = webDB.getTextFromElement(SalesInvoiceLocators.SI_Doc_NO, ElementType.Xpath);
				logs.logging("INFO", "Get SI DocNo Is: " + SIdocno);
				VariableHandler.setSIdocno(SIdocno);
				MultiSIDocNumber.add(VariableHandler.getSIdocno());
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Sales Invoice Prefix
	 * 
	 * @author Vimal - RMITS
	 * @param InvoicePrefix
	 */
	public void verifyInvoicePfx(String InvoicePrefix) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Invoice_Pfx, InvoicePrefix);
			logs.logging("INFO", "Invoice Prefix Is Displayed Successfully");
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Header_CPC_LOV_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CPC Header Lov Is Displayed Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify LUT NO
	 * 
	 * @author Vimal - RMITS
	 * @param LUT_No
	 */
	public void verifyLUTNO(String LUT_No) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Lut_No_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Lut_No_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Lut_No_LOV_Btn, LUT_No);
			flag = webDB.waitForElement(SalesInvoiceLocators.CPC_Column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CPC Column Is Displayed Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Header CPC Code
	 * 
	 * @author Vimal - RMITS
	 * @param CPC_Code
	 */
	public void verifyHeaderCPCCode(String CPC_Code) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Header_CPC_LOV_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Header_CPC_LOV_Btn, CPC_Code);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Header_Date_field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_Header_Date_field, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_Header_Date, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.SI_Header_Date, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(SalesInvoiceLocators.Header_Tick_Icon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Header Tick Icon Is Displayed Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Header Tick Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyHeaderTickIcon() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Header_Tick_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Header_Tick_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (flag) {
				logs.logging("INFO", "Header Tick is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Linewise Add Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyLinewiseAddButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Linewise Add Icon Is Displayed Successfully");
			webDB.clickAnElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
		}
		assertTrue(flag);

	}

	/**
	 * Verify Item ID
	 * 
	 * @author Vimal - RMITS
	 * @param Item_ID
	 */
	public void verifyItemID(String Item_ID) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, Item_ID);
			logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item_ID);
			flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Sales GL Acct Desc
	 * 
	 * @author Vimal - RMITS
	 * @param SalesGL_Acct
	 */
	public void verifySalesGlAccount(String SalesGL_Acct) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Sales_GL_Acc_Desc_LOV_Btn, SalesGL_Acct);
			logs.logging("INFO", "Sales GL Acct Desc Inserted Successfully");
		}
		assertTrue(flag);
	}

	/**
	 * Verify TableSpec InputParameter
	 * 
	 * @author Vimal - RMITS
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearText(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
				webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue);
				commonFunc.checkRefreshCellsDisappear();
				String value = webDB.getDomPropertyFromElement(InputParameter, ElementType.Xpath,
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
	 * Verify Invoice Qty Unitcost value
	 * 
	 * @author Vimal - RMITS
	 * @param InvoiceQty
	 * @param UnitPrice
	 */
	public void verifyItemQtyUnitcost(String InvoiceQty, String UnitPrice) {
		commonFunc.hardWait(1000);
		verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty(), SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
		verifyAddTableSpecs(SalesInvoiceLocators.UnitCost_Column(), SalesInvoiceLocators.UnitCost_Input, UnitPrice);
	}

	/**
	 * Verify Rate Contract Price List Standard Price Catalog Price
	 * 
	 * @author Vimal - RMITS
	 * @param InvoiceQty
	 */
	public void verifyRateContractInvoiceQty(String InvoiceQty) {
		commonFunc.hardWait(1000);
		verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty(), SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
	}

	/**
	 * Verify Header HSN Code
	 * 
	 * @author Vimal - RMITS
	 * @param HSNcode_SI
	 */
	public void verifyHSNcode(String HSNcode_SI) {
		flag = webDB.waitForElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "HSN Code Table Displayed Successfully");
			webDB.clickAnElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.HSN_Code_LOV_Btn, HSNcode_SI);
			logs.logging("INFO", "HSN Code Inserted Successfully");
			flag = webDB.waitForElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verify HeaderwiseTickIcon
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyHeaderwiseTickIcon() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Headerwise Tick Icon Displayed Successfully");
			flag = webDB.clickAnElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Headerwise Tick Icon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Linewise Detail Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyLineWiseDetailsButton(int i) {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(SalesInvoiceLocators.LineDetails(i - 3), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.LineDetails(i - 3), ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_detail_Button, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_detail_Button, ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Detail_Btn_Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Linewise Detail Button Clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Linewise Detail Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyLineWiseDetailsButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Details_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Details_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Detail_Btn_Iframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Linewise Detail Button Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used to click Total Discount Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickTotalDiscountForLineDetails() {
		flag = webDB.waitForElement(SalesInvoiceLocators.TotalDiscountData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.TotalDiscountData(), ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Detail_Btn_Iframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Linewise Detail Button Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Line Details Price Basis Field
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyPriceBasisField() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Detail_Btn_Iframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(SalesInvoiceLocators.Detail_Btn_Iframe, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Price_Basis_Field, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Switched to Line Wise Details frame");
				String pricebasis = webDB.getTextFromElement(SalesInvoiceLocators.Price_Basis_Field, ElementType.Xpath);
				logs.logging("INFO", "GetText PriceBasis of the Item is :" + pricebasis);
				flag = pricebasis.equalsIgnoreCase("User defined");
				if (flag) {
					logs.logging("INFO", "Verified Price Basis of the Item is User Defined");
				} else if (pricebasis.equalsIgnoreCase("Open Order")) {
					logs.logging("INFO", "Verified Price Basis of the Item is Rate Contract");
				} else if (pricebasis.equalsIgnoreCase("Standard Price")) {
					logs.logging("INFO", "Verified Price Basis of the Item is Standard Price");
				} else if (pricebasis.equalsIgnoreCase("Cataloug")) {
					logs.logging("INFO", "Verified Price Basis of the Item is Cataloug Price");
				} else if (pricebasis.equalsIgnoreCase("Price List")) {
					logs.logging("INFO", "Verified Price Basis of the Item is Price List");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * verify LineDetails Save Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyLineDetailsSaveIcon() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.Line_Det_save_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Line_Det_save_Btn, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (flag) {
				logs.logging("INFO", "Line Detail Save Button Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify AllocLSbatch
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyAllocLSbatch() {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.Alloc_ls_batch_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.Alloc_ls_batch_Btn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
//				flag = webDB.waitForElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
//					commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Post_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Allocate LS Batch clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify PickButton
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyPickButton() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.Pick_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pick Button Displayed Successfully");
				webDB.clickAnElement(SalesInvoiceLocators.Pick_Btn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				if (flag) {
					logs.logging("INFO", "Pick Button clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify CreateInvoiceNo
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyCreateInvoiceNo() {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.Cre_Inv_No, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cre.Inv.No Button Displayed Successfully");
				webDB.clickAnElement(SalesInvoiceLocators.Cre_Inv_No, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				String SIInvNo = webDB.getTextFromElement(SalesInvoiceLocators.SI_INV_No, ElementType.Xpath);
				logs.logging("INFO", "Get SI Inv.No Is: " + SIInvNo);
				VariableHandler.setSIInvNo(SIInvNo);
				commonFunc.hardWait(1000);
//			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				if (flag) {
					logs.logging("INFO", "Cre.Inv.No Button clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Post Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickPostButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Post_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Post_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click pending and search PR no and verify
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingSOAndSearchSONo(String SOnum) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesInvoiceLocators.PendingSORadioBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PendingSORadioBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesInvoiceLocators.PendingSOIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated to the Pending SO Successfully");
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToFrame(SalesInvoiceLocators.PendingSOIframe, ElementType.Xpath);
				flag = webDB.waitForElement(SalesInvoiceLocators.PendingSO_SearchBar, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(SalesInvoiceLocators.PendingSO_SearchBar, SOnum, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SalesInvoiceLocators.PendingSO_SearchBarGOBtn, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(SalesInvoiceLocators.PendingSO_SearchBarGOBtn, ElementType.Xpath);
						flag = webDB.clickAnElement(SalesInvoiceLocators.PendingSO_SearchBarGOBtn, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						logs.logging("INFO", "Pending SO Search Button Clicked");
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This method used to click select all check box in pending PR and click
	 * 'create PO'
	 * 
	 * @author Sakthi-GWL
	 */
	public void selectAllPendingSOAndClickCreateSI(String SONum) {
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitForElement(SalesInvoiceLocators.PendingSOIframe, ElementType.Xpath);
		webDB.switchToFrame(SalesInvoiceLocators.PendingSOIframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.PendingSO_SOCheckBox(SONum), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesInvoiceLocators.PendingSO_SOCheckBox(SONum), ElementType.Xpath);
			webDB.clickAnElement(SalesInvoiceLocators.PendingSO_SOCheckBox(SONum), ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(SalesInvoiceLocators.PendingSO_CreateInvoiceBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.isElementEnabled(SalesInvoiceLocators.PendingSO_CreateInvoiceBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "SONumber Checkbox clicked in Pending SO and Create Sales Invoice Enabled");
				webDB.clickAnElement(SalesInvoiceLocators.PendingSO_CreateInvoiceBtn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_Pfx, ElementType.Xpath);
				if (flag) {
					String SIdocno = webDB.getTextFromElement(SalesInvoiceLocators.SI_Doc_NO, ElementType.Xpath);
					logs.logging("INFO", "Get SI DocNo Is: " + SIdocno);
					VariableHandler.setSIdocno(SIdocno);
					logs.logging("INFO", "Pending SO - 'Create Invoice' Clicked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify Item From Pending Sale Order and Click Alloc
	 * Serial
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 */
	public void verifyItemFromPendingSaleOrderAndClickAllocLotSerial(String Item) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
			String DisplayedText = webDB.getTextFromElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
			flag = Item.equalsIgnoreCase(DisplayedText);
			if (flag) {
				logs.logging("INFO", "Verified Item Name in Pending SO: " + Item);
				flag = webDB.waitForElement(SalesInvoiceLocators.Alloc_ls_batch_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.Alloc_ls_batch_Btn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					commonFunc.checkSuccessToastMessageDisappear();
					logs.logging("INFO", "Allocate Lot Serial Button Clicked");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to store Sale GST Type in variable handler
	 * 
	 * @author Vimal - RMITS
	 */
	public void storeSaleGSTType() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Sale_GST_cls, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesInvoiceLocators.Sale_GST_cls, ElementType.Xpath);
			String GSTType = webDB.getDomPropertyFromElement(SalesInvoiceLocators.Sale_GST_cls, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setCustomerSaleGSTType(GSTType);
			logs.logging("INFO", "Customer Sale GST Type: " + GSTType);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Check Sales Invoice Tax Calculation
	 * 
	 * @author Sakthi-GWL
	 */
	public void checkSaleInvoiceTaxCalculationForLastAddedItem() {
		logs.logging("INFO", "Sales Invoice Tax Calculation!!!");

		BigDecimal decimal_Qnty = new BigDecimal(
				webDB.getTextFromElement(SalesInvoiceLocators.InvoiceQntyTableData, ElementType.Xpath));
		decimal_Qnty = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Qnty);
		logs.logging("INFO", "Quantity Passed for Calculation without tax: " + decimal_Qnty);
		BigDecimal decimal_Cost = new BigDecimal(
				webDB.getTextFromElement(SalesInvoiceLocators.UnitPriceTableData, ElementType.Xpath));
		decimal_Cost = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Cost);
		logs.logging("INFO", "Cost Passed for Calculation without tax: " + decimal_Cost);
		BigDecimal totalCostWithoutTax = decimal_Qnty.multiply(decimal_Cost);
		logs.logging("INFO", "Multiply value Cost and Quantity without tax: " + totalCostWithoutTax);
		flag = webDB.waitForElement(SalesInvoiceLocators.DiscountAmountTableData(), ElementType.Xpath);
		if (flag) {
			BigDecimal DiscountAmnt = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.DiscountAmountTableData(), ElementType.Xpath));
			DiscountAmnt = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscountAmnt);
			logs.logging("INFO", "Discount Amount Displayed without tax: " + DiscountAmnt);
			totalCostWithoutTax = totalCostWithoutTax.subtract(DiscountAmnt);
			logs.logging("INFO", "After Subtract Discount in Calculation Amount without tax: " + totalCostWithoutTax);

			BigDecimal PerValue = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.TaxPercentField(), ElementType.Xpath));
			PerValue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(PerValue);
			logs.logging("INFO", "Displayed tax Percentage: " + PerValue.intValue() + "%");

			BigDecimal TaxValue = totalCostWithoutTax.divide(new BigDecimal("100")).multiply(PerValue);
			BigDecimal IndividualTax = purchaseOrderFunc
					.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TaxValue.divide(new BigDecimal("2")));
			logs.logging("INFO", "Individual Tax Amount: " + IndividualTax);
			TaxValue = IndividualTax.multiply(new BigDecimal("2"));
			logs.logging("INFO", "Expected Tax Value After Tax calculation: " + TaxValue);

			BigDecimal IGSTAmount = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.IGSTTaxAmountField(), ElementType.Xpath));
			IGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(IGSTAmount);
			BigDecimal CGSTAmount = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.CGSTTaxAmountField(), ElementType.Xpath));
			CGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(CGSTAmount);
			BigDecimal SGSTAmount = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.SGSTTaxAmountField(), ElementType.Xpath));
			SGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(SGSTAmount);
			BigDecimal UTGSTAmount = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.UTGSTTaxAmountField(), ElementType.Xpath));
			UTGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(UTGSTAmount);

			BigDecimal taxAmount = IGSTAmount.add(CGSTAmount).add(SGSTAmount).add(UTGSTAmount);
			logs.logging("INFO", "After Adding Displayed GST Values: " + taxAmount);

			flag = taxAmount.equals(TaxValue);
			if (flag) {
				logs.logging("INFO", "Tax Amount Verified, Tax added value: " + taxAmount);
				logs.logging("INFO", "verifing total amount after calculating tax amount!!!");
				String Value = webDB.getDomPropertyFromElement(SalesInvoiceLocators.TotalAmountDisplayField,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				if (Value.contains(",")) {
					Value = Value.replace(",", "").trim();
				}
				BigDecimal DisplayLineAmnt = new BigDecimal(Value);
				DisplayLineAmnt = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DisplayLineAmnt);
				logs.logging("INFO", "Displayed Total Amount: " + DisplayLineAmnt);

				BigDecimal TotalAmountAddedTax = purchaseOrderFunc
						.verifyRoundOffBigDecimalValueWithReturnBigDecimal(totalCostWithoutTax.add(taxAmount));
				logs.logging("INFO", "Calculated Total Amount with Added Tax: " + TotalAmountAddedTax);
				flag = DisplayLineAmnt.equals(TotalAmountAddedTax);
				if (flag) {
					logs.logging("INFO", "Verified Total Amount Displayed and Calculated Total Amount Matched");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch Line Details Iframe in Sales Invoice
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToLineDetailsIframeInSaleInvoice() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Detail_Btn_Iframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(SalesInvoiceLocators.Detail_Btn_Iframe, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_detail_Promotional, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_detail_Promotional, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Line_Det_save_Btn, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to check Stock and Invoice Quantity with Warehouse
	 * 
	 * @author Sakthi-GWL
	 * @param Warehouse
	 */
	public void verifyShipFromWarehouseAndStockAndInvoiceQuantity(String Warehouse) {
		flag = webDB.waitForElement(SalesInvoiceLocators.SaleInvoiceWarehouseShipFrom, ElementType.Xpath);
		if (flag) {
			String DisplayWH = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SaleInvoiceWarehouseShipFrom,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Displayed Warehouse ship from: " + DisplayWH);
			logs.logging("INFO", "Expected Warehouse ship from: " + Warehouse);
			flag = Warehouse.equalsIgnoreCase(DisplayWH);
			if (flag) {
				logs.logging("INFO", "Verified Warehouse of Shiping From");
				flag = webDB.waitForElement(SalesInvoiceLocators.SaleInvoiceStockQuantity, ElementType.Xpath);
				if (flag) {
					String StockQnty = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SaleInvoiceStockQuantity,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					String OrderInvoiceQnty = webDB.getDomPropertyFromElement(
							SalesInvoiceLocators.SaleInvoiceOrderInvoiceQuantity, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					flag = Integer.parseInt(StockQnty) > Integer.parseInt(OrderInvoiceQnty);
					if (flag) {
						logs.logging("INFO", "Displayed Stock Quantity ship from: " + StockQnty);
						logs.logging("INFO", "Displayed Invoice Order Quantity ship from: " + OrderInvoiceQnty);
						logs.logging("INFO", "Verified Stock Quantity Greater than Invoice Quantity");
					}
				}
			}
		}
		assertTrue(flag);
	}

	public void validateSalesInvoiceDocumentStatus(String SINumber, String ExpStatus) {
		flag = webDB.waitForElement(SalesInvoiceLocators.FindSalesInvoiceNoLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.FindSalesInvoiceNoLovBtn, SINumber);
			flag = webDB.waitForElement(SalesInvoiceLocators.FindSalesInvoiceSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.FindSalesInvoiceSearchBtn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesInvoiceLocators.SIStatusTableText(SINumber), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(SalesInvoiceLocators.SIStatusTableText(SINumber),
							ElementType.Xpath);
					flag = Status.contains(ExpStatus);
					if (flag) {
						logs.logging("INFO", "Displayed Status :" + Status);
						logs.logging("INFO", "Expected Status :" + ExpStatus);
						logs.logging("INFO", "Verified Sales Invoice Document Status");

					} else {
						logs.logging("INFO", "Sales Invoice Doucment Status Verification Failed");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void forwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_Add_Icon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To PO Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to Calculate Line Amount in Sales Invoice
	 * 
	 * @author Sakthi-GWL
	 */
	public void validateLineAmountOfLastAddedItem() {
		flag = webDB.waitForElement(SalesInvoiceLocators.LineAmountTableData(), ElementType.Xpath);
		if (flag) {
			String lineAmount = webDB.getTextFromElement(SalesInvoiceLocators.LineAmountTableData(), ElementType.Xpath);
			BigDecimal TotalAmount = purchaseOrderFunc
					.verifyRoundOffBigDecimalValueWithReturnBigDecimal(new BigDecimal(lineAmount));
			logs.logging("INFO", "Total Amount Displayed: " + TotalAmount);
			flag = webDB.waitForElement(SalesInvoiceLocators.UnitPriceTableData, ElementType.Xpath);
			if (flag) {
				String UnitCost = webDB.getTextFromElement(SalesInvoiceLocators.UnitPriceTableData, ElementType.Xpath);
				BigDecimal UnitPrice = purchaseOrderFunc
						.verifyRoundOffBigDecimalValueWithReturnBigDecimal(new BigDecimal(UnitCost));
				logs.logging("INFO", "Unit Price Displayed: " + UnitPrice);
				flag = webDB.waitForElement(SalesInvoiceLocators.InvoiceQntyTableData, ElementType.Xpath);
				if (flag) {
					String Qnty = webDB.getTextFromElement(SalesInvoiceLocators.InvoiceQntyTableData,
							ElementType.Xpath);
					BigDecimal InvQnty = purchaseOrderFunc
							.verifyRoundOffBigDecimalValueWithReturnBigDecimal(new BigDecimal(Qnty));
					logs.logging("INFO", "Invoice Quantity Displayed: " + InvQnty);
					BigDecimal WithoutDicount = InvQnty.multiply(UnitPrice);
					logs.logging("INFO", "Without Discount Amount For Multiply Unit and Cost: " + WithoutDicount);
					flag = webDB.waitForElement(SalesInvoiceLocators.DiscountAmountTableData(), ElementType.Xpath);
					if (flag) {
						String Discount = webDB.getTextFromElement(SalesInvoiceLocators.DiscountAmountTableData(),
								ElementType.Xpath);
						BigDecimal DiscountAmnt = purchaseOrderFunc
								.verifyRoundOffBigDecimalValueWithReturnBigDecimal(new BigDecimal(Discount));
						logs.logging("INFO", "Discount Amount Displayed: " + DiscountAmnt);
						BigDecimal AfterDiscount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(
								WithoutDicount.subtract(DiscountAmnt));
						logs.logging("INFO", "After Discount Expected Line Amount: " + AfterDiscount);
						flag = AfterDiscount.equals(TotalAmount);
						if (flag) {
							logs.logging("INFO", "After Calculating Unit and Qnty, Line Amount was Verified..");
						}
					}
				}
			}
			assertTrue(flag);
		}
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"), commonFunc.getDataFromTestDataSheet("CustomerName"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DBSales"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		VariableHandler.setMultipleSIDocs(MultiSIDocNumber);
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DBSales"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceBR(String SheetName, String Customer) {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"), Customer);
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
//		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		AddingUserDefinedItemLineFromReadingExcelSheet(SheetName);
		commonFunc.checkRefreshCellsDisappear();
//		verifyAllocLSbatch();
//		commonFunc.checkRefreshCellsDisappear();
//		verifyPickButton();
//		commonFunc.checkRefreshCellsDisappear();
//		verifyCreateInvoiceNo();
//		commonFunc.checkRefreshCellsDisappear();
		verifySITotalAmountWithExcelAndDB(SheetName);
		VariableHandler.setMultipleSIDocs(MultiSIDocNumber);
		clickPostButton();
		forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
//		flag = commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(SheetName);
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceBRMulti(String Customer, String SheetName, String SheetName2, int i1) {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		for (int i = 1; i <= 2; i++) {
			verifyClickingAddButton();
			verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("SI_Type"), Customer);
			commonFunc.checkRefreshCellsDisappear();
			verifyClickingTickButton();
			verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
			verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("SO_CPC_Code"));
			verifyHeaderTickIcon();
			commonFunc.checkRefreshCellsDisappear();
//			CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
			AddingUserDefinedItemLineFromReadingExcelSheet(SheetName);
			commonFunc.checkRefreshCellsDisappear();
//			verifyAllocLSbatch();
//			commonFunc.checkRefreshCellsDisappear();
//			verifyPickButton();
//			commonFunc.checkRefreshCellsDisappear();
//			verifyCreateInvoiceNo();
//			commonFunc.checkRefreshCellsDisappear();
//			verifySITotalAmountWithExcelAndDB(SheetName);
			VariableHandler.setMultipleSIDocs(MultiSIDocNumber);
			clickPostButton();
			forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
//		flag = commonFunc.logoutUser();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.checkRefreshCellsDisappear();
			checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
			salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(SheetName);
			excelUtils.writeDataInExcel(BRFilepath, SheetName2, i1, 25 + i,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		}
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceBRMultiUnits(String SheetName, String Customer, String Location,
			String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(Location, commonFunc.getDataFromTestDataSheet("SI_Type"),
				Customer);
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(Prefix);
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
//		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		AddingUserDefinedItemLineFromReadingExcelSheet(SheetName);
		commonFunc.checkRefreshCellsDisappear();
//		verifyAllocLSbatch();
//		commonFunc.checkRefreshCellsDisappear();
//		verifyPickButton();
//		commonFunc.checkRefreshCellsDisappear();
//		verifyCreateInvoiceNo();
//		commonFunc.checkRefreshCellsDisappear();
		verifySITotalAmountWithExcelAndDB(SheetName);
		VariableHandler.setMultipleSIDocs(MultiSIDocNumber);
		clickPostButton();
		forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
//		flag = commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
//		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(SheetName);
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceBRMultiUnits(String Customer, String SheetName, String SheetName2, int i1,
			String Location, String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		for (int i = 1; i <= 2; i++) {
			verifyClickingAddButton();
			verifyCreateDirectSalesInvoiceAddingParameters(Location, commonFunc.getDataFromTestDataSheet("SI_Type"),
					Customer);
			commonFunc.checkRefreshCellsDisappear();
			verifyClickingTickButton();
			verifyInvoicePfx(Prefix);
			verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
			verifyHeaderTickIcon();
			commonFunc.checkRefreshCellsDisappear();
//			CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
			AddingUserDefinedItemLineFromReadingExcelSheet(SheetName);
			commonFunc.checkRefreshCellsDisappear();
//			verifyAllocLSbatch();
//			commonFunc.checkRefreshCellsDisappear();
//			verifyPickButton();
//			commonFunc.checkRefreshCellsDisappear();
//			verifyCreateInvoiceNo();
//			commonFunc.checkRefreshCellsDisappear();
//			verifySITotalAmountWithExcelAndDB(SheetName);
			VariableHandler.setMultipleSIDocs(MultiSIDocNumber);
			clickPostButton();
			forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
//		flag = commonFunc.logoutUser();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.checkRefreshCellsDisappear();
			checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
			salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(SheetName);
			excelUtils.writeDataInExcel(BRFilepath, SheetName2, i1, 25 + i,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		}
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceExport() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceExportBR(String SheetName, String Customer) {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"), Customer);
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
//		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		AddingUserDefinedItemLineFromReadingExcelSheet(SheetName);
		commonFunc.checkRefreshCellsDisappear();
//		verifyAllocLSbatch();
//		commonFunc.checkRefreshCellsDisappear();
//		verifyPickButton();
//		commonFunc.checkRefreshCellsDisappear();
//		verifyCreateInvoiceNo();
//		commonFunc.checkRefreshCellsDisappear();
		verifySITotalAmountWithExcelAndDB(SheetName);
		VariableHandler.setMultipleSIDocs(MultiSIDocNumber);
		clickPostButton();
		forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
//		flag = commonFunc.logoutUser();
//		commonFunc.checkRefreshCellsDisappear();
//		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
//		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportSD"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(SheetName);
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceExportBRMulti(String Customer, String SheetName, String SheetName2, int i1) {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		for (int i = 1; i <= 2; i++) {
			verifyClickingAddButton();
			verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("SI_Type"), Customer);
			commonFunc.checkRefreshCellsDisappear();
			verifyClickingTickButton();
			verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
			verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
			verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
			verifyHeaderTickIcon();
			commonFunc.checkRefreshCellsDisappear();
//		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
			AddingUserDefinedItemLineFromReadingExcelSheet(SheetName);
			commonFunc.checkRefreshCellsDisappear();
//			verifyAllocLSbatch();
//			commonFunc.checkRefreshCellsDisappear();
//			verifyPickButton();
//			commonFunc.checkRefreshCellsDisappear();
//			verifyCreateInvoiceNo();
//			commonFunc.checkRefreshCellsDisappear();
			verifySITotalAmountWithExcelAndDB(SheetName);
			VariableHandler.setMultipleSIDocs(MultiSIDocNumber);
			clickPostButton();
			forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
//		flag = commonFunc.logoutUser();
//		commonFunc.checkRefreshCellsDisappear();
//		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
//		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportSD"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.checkRefreshCellsDisappear();
			checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
			salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(SheetName);
			excelUtils.writeDataInExcel(BRFilepath, SheetName2, i1, 25 + i,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		}
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Rate
	 * Contract- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceRateContractDomestic() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DomesticRateContSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DomesticRateContSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Rate
	 * Contract- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceRateContractExport() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportRateContSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportRateContSD"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis user
	 * defined- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceDomesticUserDefinedDisimilarStockUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"), commonFunc.getDataFromTestDataSheet("CustomerName"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DBSales"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DBSales"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis user
	 * defined- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceExportUserDefinedDisimilarStockUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportSD"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Rate
	 * Contract- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceRateContractDomesticWithDisimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DomesticRateContSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DomesticRateContSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Rate
	 * Contract- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceRateContractExportWithDisimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportRateContSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportRateContSD"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Price
	 * List- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoicePriceListDomesticWithsimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DomesticPriceListSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DomesticPriceListSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Price List-
	 * Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoicePriceListExportWithsimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportPriceListSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportPriceListSD"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Price
	 * List- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoicePriceListDomesticWithDisimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DomesticPriceListSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DomesticPriceListSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Price List-
	 * Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoicePriceListExportWithDisimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportPriceListSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportPriceListSD"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Standard
	 * Price- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceStandardPriceDomesticWithsimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DomesticStandardSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DomesticStandardSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Standard
	 * Price- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceStandardPriceExportWithsimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportStandardSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportStandardSD"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Standard
	 * Price- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceStandardPriceDomesticWithDisimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DomesticStandardSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DomesticStandardSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Standard
	 * Price- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceStandardPriceExportWithDisimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportStandardSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportStandardSD"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Catalog
	 * Price- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceCatalogPriceDomesticWithsimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DomesticCatalogSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DomesticCatalogSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Catalog
	 * Price- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceCatalogPriceExportWithsimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportCatalogSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportCatalogSD"));
		return flag;
	}

	/**
	 * create direct Domestic Sales Invoice for the customer - price basis Catalog
	 * Price- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceCatalogPriceDomesticWithDisimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Domestic_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("DomesticCatalogSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("DomesticCatalogSD"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis Catalog
	 * Price- Disimilar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceCatalogPriceExportWithDisimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_RC_Customer_Name"));
		AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportCatalogSD"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportCatalogSD"));
		return flag;
	}

	/**
	 * verify FindPage Add Button
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	public void verifyFindPageAddButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_FindPage_Add_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Proforma_FindPage_Add_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_Inv_Loc_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Proforma Invoice Add Button Clicked Sucessfully");
			} else {
				logs.logging("INFO", "Failed To Click the Add Button In the Find Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used for verify Proforma Invoice PendingSO Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyProformaInvoicePendingSO() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_Inv_Pend_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Proforma_Inv_Pend_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Pend_Iframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Proforma Invoice Pending Button Clicked Sucessfully");
			} else {
				logs.logging("INFO", "Failed To Click the Pending Button In the Find Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used for verify Proforma Invoice Pending SaleOrderNo
	 * 
	 * @author Vimal - RMITS
	 * @param PI_Pend_SO_No
	 */
	public void verifyProformaInvoicePendingSaleOrderNo(String PI_Pend_SO_No) {
		webDB.switchToFrame(SalesInvoiceLocators.PI_Pend_Iframe, ElementType.Xpath);
		commonFunc.hardWait(2000);
		commonFunc.expandFindTable();
		commonFunc.hardWait(2000);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_Pend_SO_No, PI_Pend_SO_No);
		webDB.switchToFrame(SalesInvoiceLocators.PI_Pend_Iframe, ElementType.Xpath);
		logs.logging("INFO", "Proforma Invoice Pending SaleOrder No Inserted Sucessfully");
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_FindPage_Add_Btn, ElementType.Xpath);
		assertTrue(flag);
	}

	/**
	 * This Method used for verify Proforma Invoice Pending Search Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyProformaInvoicePendingSearchButton() {
		webDB.switchToFrame(SalesInvoiceLocators.PI_Pend_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_Pend_Search_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_Pend_Search_Btn, ElementType.Xpath);
			logs.logging("INFO", "Proforma Invoice Pending Search Button Clicked Sucessfully");
			commonFunc.hardWait(2000);
		} else {
			logs.logging("INFO", "Failed To Click the Search Button In The Pending Screen");
		}
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_FindPage_Add_Btn, ElementType.Xpath);
		assertTrue(flag);
	}

	/**
	 * This Method Used for verify Proforma Invoice Pending SelectAll
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyProformaInvoicePendingSelectAll() {
		webDB.switchToFrame(SalesInvoiceLocators.PI_Pend_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_Pend_SelectAll, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_Pend_SelectAll, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Pend_Create_PI, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Proforma Invoice Pending SelectAll Button Clicked Sucessfully");
			} else {
				logs.logging("INFO", "Failed To Click the Select All button");
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used for verify Proforma Invoice Pending Clicking Create Proforma
	 * Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyProformaInvoicePendingClickingCreateProformaInvoice() {
		webDB.switchToFrame(SalesInvoiceLocators.PI_Pend_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_Pend_Create_PI, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_Pend_Create_PI, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_Inv_Pfx_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Proforma Invoice Pending Create Proforma Invoice Button Clicked Sucessfully");
			} else {
				logs.logging("INFO", "Failed to Click the Create Proforma Invoice Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Used To Verify Proforma Invoice Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param PI_Loc_Id
	 * @param PI_Type
	 * @param PI_Domestic_Customer
	 */
	public void VerifyProformaInvoiceParameter(String PI_Loc_Id, String PI_Type, String PI_Domestic_Customer) {
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Proforma_Inv_Loc_Btn, PI_Loc_Id);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_Type_Field, PI_Type);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_Customer_Name, PI_Domestic_Customer);
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_TickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Proforma Invoice Parameter Inserted Sucessfully");
			webDB.clickAnElement(SalesInvoiceLocators.PI_TickIcon, ElementType.Xpath);
			logs.logging("INFO", "Proforma Invoice Creation Tick Icon CLicked Sucessfully");
			commonFunc.hardWait(2000);
			if (webDB.isElementDisplayed(SalesInvoiceLocators.PI_Header_Close_Success_msg, ElementType.Xpath)) {
				webDB.clickAnElement(SalesInvoiceLocators.PI_Header_Close_Success_msg, ElementType.Xpath);
				logs.logging("INFO", "Navigated Sucessfully To Proforma Invoice Transaction Screen");
				commonFunc.checkRefreshCellsDisappear();
			} else {
				logs.logging("INFO", "Failed To Navigate To Proforma Invoice Transaction Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used verify Header Proforma Invoice Prefix
	 * 
	 * @author Vimal - RMITS
	 * @param Proforma_Inv_Pfx
	 */
	public void verifyHeaderProformaInvoicePrefix(String Proforma_Inv_Pfx, String PI_PaymentTerm) {
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Proforma_Inv_Pfx_Btn, Proforma_Inv_Pfx);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_Header_PaymentTerm_LOV_Btn, PI_PaymentTerm);
		flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_inv_Header_TickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Proforma_inv_Header_TickIcon, ElementType.Xpath);
			logs.logging("INFO", "Proforma Invoice Prefix and Payment Term Inserted Sucessfully");
			commonFunc.hardWait(2000);
			if (webDB.isElementDisplayed(SalesInvoiceLocators.PI_Header_Close_Success_msg, ElementType.Xpath)) {
				webDB.clickAnElement(SalesInvoiceLocators.PI_Header_Close_Success_msg, ElementType.Xpath);
				logs.logging("INFO", "Note Alert Close icon Is Clicked");
				flag = webDB.waitForElement(SalesInvoiceLocators.PI_DocNo, ElementType.Xpath);
				if (flag) {
					String PI_DocNo = webDB.getTextFromElement(SalesInvoiceLocators.PI_DocNo, ElementType.Xpath);
					VariableHandler.setPI_DocNo(PI_DocNo);
					logs.logging("INFO", "Get SI DocNo Is: " + VariableHandler.getPI_DocNo());
					commonFunc.hardWait(3000);
				}
			} else {
				logs.logging("INFO", "Failed To Click Note Alert CloseIcon");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used verify Proforma Invoice LineWise AddIcon Click
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyProformaInvoiceLineWiseAddIconClick() {
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_Line_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_Line_Add_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.PIPageItemTable(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LineWise Add Icon Clicked Sucessfully");
			} else {
				logs.logging("INFO", "Failed To Click LineWise Add Icon");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used for verify Proforma Invoice ItemID
	 * 
	 * @author Vimal - RMITS
	 * @param PI_Item_ID
	 */
	public void verifyProformaInvoiceItemID(String PI_Item_ID) {
		flag = webDB.waitForElement(SalesInvoiceLocators.PIPageItemTable(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PIPageItemTable(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_Item_Id_Btn, PI_Item_ID);
			flag = webDB.waitForElement(SalesInvoiceLocators.PIPageQtyTable(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LineWise Item Inserted Sucessfully - " + PI_Item_ID);
			} else {
				logs.logging("INFO", "Failed To Insert LineWise Item");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is Used For verify Proforma Invoice Advance SOReference
	 * 
	 * @param PI_SO_Ref_Advance
	 */
	public void verifyProformaInvoiceAdvanceSOReference(String PI_SO_Ref_Advance) {
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_SO_Ref_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_SO_Ref_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_SO_Ref_Btn, PI_SO_Ref_Advance);
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Item_ID_column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LineWise SO Reference Inserted Sucessfully - " + PI_SO_Ref_Advance);
			} else {
				logs.logging("INFO", "Failed To Insert LineWise SO Reference");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used For verify Proforma Invoice Advance LineItem
	 * 
	 * @param PI_SO_Ref_Advance_Item_ID
	 */
	public void verifyProformaInvoiceAdvanceLineItem(String PI_SO_Ref_Advance_Item_ID) {
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_Item_ID_column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_Item_ID_column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_Item_Id_Btn, PI_SO_Ref_Advance_Item_ID);
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Qty_Column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LineWise Item Inserted Sucessfully - " + PI_SO_Ref_Advance_Item_ID);
			} else {
				logs.logging("INFO", "Failed To Insert LineWise Item");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used for verify Proforma InvoiceQty Unitcost
	 * 
	 * @author Vimal - RMITS
	 * @param PI_Inv_Qty
	 * @param PI_Inv_UnitCost
	 */
	public void verifyProformaInvoiceQtyUnitcost(String PI_Inv_Qty, String PI_Inv_UnitCost) {
//		commonFunc.hardWait(1000);
		verifyAddTableSpecs(SalesInvoiceLocators.PIPageQtyTable(), SalesInvoiceLocators.PI_Inv_Qty, PI_Inv_Qty);
//		commonFunc.hardWait(1000);
		verifyAddTableSpecs(SalesInvoiceLocators.PIPageUnitPriceTable(), SalesInvoiceLocators.PI_UnitCost,
				PI_Inv_UnitCost);
	}

	/**
	 * This Method is used for verify Proforma Invoice HSNCode
	 * 
	 * @author Vimal - RMITS
	 * @param PI_HSN_code
	 */
	public void verifyProformaInvoiceHSNCode(String PI_HSN_code) {
		flag = webDB.waitForElement(SalesInvoiceLocators.PIPageHSNTable(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PIPageHSNTable(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_HSN_Btn, PI_HSN_code);
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Line_tickIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Linewise HSN Code Inserted Sucessfully");
			} else {
				logs.logging("INFO", "Failed To Insert LineWise HSN Code");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method is used for verify Proforma Invoice LineWise TickIcon
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyProformaInvoiceLineWiseTickIcon() {
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_Line_tickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_Line_tickIcon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Post_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LineWise TickIcon Clicked Sucessfully");
			} else {
				logs.logging("INFO", "Failed to Click The Line Wise Tick Icon");
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Is used For Clicking Post Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyProformaInvoicePostButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_Post_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_Post_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			} else {
				logs.logging("INFO", "Failed To Click The Post Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void ProformaInvoiceforwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_FindPage_Add_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To PO Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * verify Direct Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean flag
	 */
	public boolean verifyDirectProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_Type"),
				commonFunc.getDataFromTestDataSheet("PI_Domestic_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"),
				commonFunc.getDataFromTestDataSheet("PI_PaymentTerm"));
		commonFunc.checkRefreshCellsDisappear();
		checkProformaHeaderDetailsDataBaseValuesWithUIValues();
		AddingprofomaInvoiceLineINExcelSheet(webDB.getDataFromProperties("PIDirectType"));
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPI_DocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * verify Direct Advance Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean flag
	 */
	public boolean verifyDirectAdvanceProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_Advance_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Service_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"),
				commonFunc.getDataFromTestDataSheet("PI_PaymentTerm"));
		commonFunc.checkRefreshCellsDisappear();
		checkProformaHeaderDetailsDataBaseValuesWithUIValues();
		AddingProfomaInvoiceAdvanceLineINExcelSheet(webDB.getDataFromProperties("PIDirectType"));
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPI_DocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * verify Direct PreDispatch Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean flag
	 */
	public boolean verifyDirectPreDispatchProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_PreDispatch_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Service_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"),
				commonFunc.getDataFromTestDataSheet("PI_PaymentTerm"));
		commonFunc.checkRefreshCellsDisappear();
		checkProformaHeaderDetailsDataBaseValuesWithUIValues();
		AddingProfomaInvoiceAdvanceLineINExcelSheet(webDB.getDataFromProperties("PIDirectType"));
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPI_DocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * verify Direct Export Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectExportProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"),
				commonFunc.getDataFromTestDataSheet("PI_PaymentTerm"));
		commonFunc.checkRefreshCellsDisappear();
		checkProformaHeaderDetailsDataBaseValuesWithUIValues();
		AddingprofomaInvoiceLineINExcelSheet(webDB.getDataFromProperties("PIDirectTypeExport"));
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPI_DocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * verify Direct Export Advance Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectExportAdvanceProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_Advance_Type"),
				commonFunc.getDataFromTestDataSheet("Loc49ExportCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"),
				commonFunc.getDataFromTestDataSheet("PI_PaymentTerm"));
		commonFunc.checkRefreshCellsDisappear();
		checkProformaHeaderDetailsDataBaseValuesWithUIValues();
		AddingProfomaInvoiceAdvanceExportLineINExcelSheet(webDB.getDataFromProperties("PIDirectTypeExport"));
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPI_DocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * verify Direct Export PreDispatch Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectExportPreDispatchProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_PreDispatch_Type"),
				commonFunc.getDataFromTestDataSheet("Loc49ExportCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"),
				commonFunc.getDataFromTestDataSheet("PI_PaymentTerm"));
		commonFunc.checkRefreshCellsDisappear();
		checkProformaHeaderDetailsDataBaseValuesWithUIValues();
		AddingProfomaInvoiceAdvanceExportLineINExcelSheet(webDB.getDataFromProperties("PIDirectTypeExport"));
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPI_DocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * verify Direct Domestic Proforma Invoice Creation PendingSO
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectDomesticProformaInvoiceCreationPendingSO() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateProformaInvoice();
		verifyProformaInvoicePendingSO();
		verifyProformaInvoicePendingSaleOrderNo(commonFunc.getDataFromTestDataSheet("PI_Pend_Domestic_SO_No"));
		verifyProformaInvoicePendingSearchButton();
		verifyProformaInvoicePendingSelectAll();
		verifyProformaInvoicePendingClickingCreateProformaInvoice();
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"),
				commonFunc.getDataFromTestDataSheet("PI_PaymentTerm"));
		verifyProformaInvoicePostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPI_DocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * verify Direct Export Proforma Invoice Creation PendingSO
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectExportProformaInvoiceCreationPendingSO() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateProformaInvoice();
		verifyProformaInvoicePendingSO();
		verifyProformaInvoicePendingSaleOrderNo(commonFunc.getDataFromTestDataSheet("PI_Pend_Export_SO_No"));
		verifyProformaInvoicePendingSearchButton();
		verifyProformaInvoicePendingSelectAll();
		verifyProformaInvoicePendingClickingCreateProformaInvoice();
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"),
				commonFunc.getDataFromTestDataSheet("PI_PaymentTerm"));
		verifyProformaInvoicePostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPI_DocNo(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * Verify SI Pending Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifySalesInvoicePendingButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Sales_Inv_Pend_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Sales_Inv_Pend_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_SO_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_SO_Btn, ElementType.Xpath);
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Sales Invoice Pending Button Clicked Successfully");
					commonFunc.hardWait(2000);
				} else {
					logs.logging("INFO", "Failed to click Sales Invoice Pending Button");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to check Stock and Invoice Quantity with Warehouse
	 * 
	 * @author Sakthi-GWL,Vimal-RMITS
	 * @param Warehouse
	 */
	public void verifyLineDetailsShipFromWarehouseAndStockAndInvoiceQuantity(String Ship_From) {
		flag = webDB.waitForElement(SalesInvoiceLocators.SaleInvoiceWarehouseShipFrom, ElementType.Xpath);
		if (flag) {
			String DisplayWH = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SaleInvoiceWarehouseShipFrom,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Displayed Warehouse ship from: " + DisplayWH);
			logs.logging("INFO", "Expected Warehouse ship from: " + Ship_From);
			flag = Ship_From.equalsIgnoreCase(DisplayWH);
			if (flag) {
				logs.logging("INFO", "Verified Warehouse of Shiping From");
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.SaleInvoiceStockQuantity, ElementType.Xpath);
				if (flag) {
					String StockQnty = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SaleInvoiceStockQuantity,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					String OrderInvoiceQnty = webDB.getDomPropertyFromElement(
							SalesInvoiceLocators.SaleInvoiceOrderInvoiceQuantity, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Displayed Stock Quantity ship from: " + StockQnty);
					logs.logging("INFO", "Displayed Invoice Order Quantity ship from: " + OrderInvoiceQnty);
//					flag = Integer.parseInt(StockQnty) > Integer.parseInt(OrderInvoiceQnty);
					flag = (new BigDecimal(StockQnty).compareTo(new BigDecimal(OrderInvoiceQnty))) == 1;
					if (flag) {
						logs.logging("INFO", "Verified Stock Quantity Greater than Invoice Quantity");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Verify Sales Invoice Document Status
	 * 
	 * @param SIdocno
	 * @param Exp_Status
	 */
	public void verifySalesInvoiceDocumentStatus(String SIdocno, String Exp_Status) {
		flag = webDB.waitForElement(SalesInvoiceLocators.FindSalesInvoiceNoLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.FindSalesInvoiceNoLovBtn, SIdocno);
			flag = webDB.waitForElement(SalesInvoiceLocators.FindSalesInvoiceSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.FindSalesInvoiceSearchBtn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesInvoiceLocators.SIStatusTableText(SIdocno), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(SalesInvoiceLocators.SIStatusTableText(SIdocno),
							ElementType.Xpath);
					logs.logging("INFO", "get text from Sales Invoice status :" + Status);

					flag = Status.contains(Exp_Status);
					if (flag) {
						logs.logging("INFO", "Displayed Status :" + Status);
						logs.logging("INFO", "Expected Status :" + Exp_Status);
						logs.logging("INFO", "Verified Sales Invoice Document Status");

					} else {
						logs.logging("INFO", "Sales Invoice Doucment Status Verification Failed");
					}
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
	 * This Method used to verify Location Details
	 * 
	 * @param Customer_Name
	 */
	public void verifyLocationDetails(String Customer_Name) {
		verifyDomPropertyValidation(SalesInvoiceLocators.Customer_Name,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.Customer_Name));
		verifyGetTextValidation(SalesInvoiceLocators.SI_Currency,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.Currency));
		verifyGetTextValidation(SalesInvoiceLocators.SI_Header_Type,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.SI_Type));
		verifyGetTextValidation(SalesInvoiceLocators.SI_Header_Loc_ID,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.Loc_Id));
		verifyDomPropertyValidation(SalesInvoiceLocators.SI_Header_Unit,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.Unit));
		commonFunc.hardWait(2000);
		verifyLocationDetailsButton();
		storeSaleGSTType();
		verifyDomPropertyValidation(SalesInvoiceLocators.Sale_GST_cls, itemDetailHandler
				.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.GST_Classification));
		verifyDomPropertyValidation(SalesInvoiceLocators.Sale_GST_Type,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.GST_Type));
		flag = webDB.waitForElement(SalesInvoiceLocators.Location_det_tickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Location_det_tickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Location Detail Tickicon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Location Details with Customer Name
	 * 
	 * @author Vimal - RMITS
	 * @param Customer_Name
	 */
	public void LocationDetailsVerification(String Customer_Name) {
		verifyGetTextValidation(SalesInvoiceLocators.Pend_From_Saleinvoice_Customer,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.Customer_Name));
		verifyGetTextValidation(SalesInvoiceLocators.SI_Currency,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.Currency));
		verifyGetTextValidation(SalesInvoiceLocators.SI_Header_Type,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.SI_Type));
		verifyGetTextValidation(SalesInvoiceLocators.SI_Header_Loc_ID,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.Loc_Id));
		verifyDomPropertyValidation(SalesInvoiceLocators.SI_Header_Unit,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.Unit));
		commonFunc.hardWait(2000);
		verifyLocationDetailsButton();
		storeSaleGSTType();
		verifyDomPropertyValidation(SalesInvoiceLocators.Sale_GST_cls, itemDetailHandler
				.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.GST_Classification));
		verifyDomPropertyValidation(SalesInvoiceLocators.Sale_GST_Type,
				itemDetailHandler.getSILineDetailsforGivenItem(Customer_Name, SICustomerDetailsName.GST_Type));
		flag = webDB.waitForElement(SalesInvoiceLocators.Location_det_tickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Location_det_tickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Location Detail Tickicon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used To GetText The Currency in the
	 *         Header of the Sales Order Screen And Return As String
	 * @return
	 */
	public String getTextCurrencyInHeader() {
		String HeaderCurrency = null;
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Currency, ElementType.Xpath);
		if (flag) {
			HeaderCurrency = webDB.getTextFromElement(SalesInvoiceLocators.SI_Currency, ElementType.Xpath);
			logs.logging("INFO", "Get Text Header Currency Is : " + HeaderCurrency);
		}
		return HeaderCurrency;
	}

	/**
	 * This Method used to Check Sales Invoice Tax Calculation
	 * 
	 * @author Sakthi-GWL
	 */
	public void checkSaleInvoiceLineTaxCalculationForLastAddedItem() {
		logs.logging("INFO", "Sales Invoice Tax Calculation!!!");

		BigDecimal decimal_Qnty = new BigDecimal(
				webDB.getTextFromElement(SalesInvoiceLocators.InvoiceQntyTableData, ElementType.Xpath));
		decimal_Qnty = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Qnty);
		logs.logging("INFO", "Quantity Passed for Calculation: " + decimal_Qnty);
		BigDecimal decimal_Cost = new BigDecimal(
				webDB.getTextFromElement(SalesInvoiceLocators.UnitPriceTableData, ElementType.Xpath));
		decimal_Cost = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Cost);
		logs.logging("INFO", "UnitCost Passed for Calculation: " + decimal_Cost);
		BigDecimal totalCostWithoutTax = decimal_Qnty.multiply(decimal_Cost);
		logs.logging("INFO", "Multiply value Cost and Quantity without tax: " + totalCostWithoutTax);
		flag = webDB.waitForElement(SalesInvoiceLocators.DiscountAmountTableData(), ElementType.Xpath);
		if (flag) {
			BigDecimal DiscountAmnt = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.DiscountAmountTableData(), ElementType.Xpath));
			DiscountAmnt = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscountAmnt);
			logs.logging("INFO", "Discount Amount Displayed without tax: " + DiscountAmnt);
			totalCostWithoutTax = totalCostWithoutTax.subtract(DiscountAmnt);
			logs.logging("INFO", "After Subtract Discount in Calculation Amount without tax: " + totalCostWithoutTax);
			if ((getTextCurrencyInHeader().equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("CurrencyInr")))
					&& !VariableHandler.getCustomerSaleGSTType().contains("SEZ")) {
				flag = webDB.waitForElement(SalesInvoiceLocators.TaxPercentField(), ElementType.Xpath);
				if (flag) {
					BigDecimal PerValue = new BigDecimal(
							webDB.getTextFromElement(SalesInvoiceLocators.TaxPercentField(), ElementType.Xpath));
					PerValue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(PerValue);
					logs.logging("INFO", "Displayed tax Percentage: " + PerValue.intValue() + "%");

					BigDecimal IGSTAmount = new BigDecimal(
							webDB.getTextFromElement(SalesInvoiceLocators.IGSTTaxAmountField(), ElementType.Xpath));
					IGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(IGSTAmount);
					BigDecimal CGSTAmount = new BigDecimal(
							webDB.getTextFromElement(SalesInvoiceLocators.CGSTTaxAmountField(), ElementType.Xpath));
					CGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(CGSTAmount);
					BigDecimal SGSTAmount = new BigDecimal(
							webDB.getTextFromElement(SalesInvoiceLocators.SGSTTaxAmountField(), ElementType.Xpath));
					SGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(SGSTAmount);
					BigDecimal UTGSTAmount = new BigDecimal(
							webDB.getTextFromElement(SalesInvoiceLocators.UTGSTTaxAmountField(), ElementType.Xpath));
					UTGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(UTGSTAmount);

					BigDecimal taxAmount = IGSTAmount.add(CGSTAmount).add(SGSTAmount).add(UTGSTAmount);
					BigDecimal TaxValue = totalCostWithoutTax.divide(new BigDecimal("100")).multiply(PerValue);
					if (!IGSTAmount.equals(taxAmount)) {
						BigDecimal IndividualTax = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(
								TaxValue.divide(new BigDecimal("2")));
						logs.logging("INFO", "Individual Tax Amount: " + IndividualTax);
						TaxValue = IndividualTax.multiply(new BigDecimal("2"));
					} else {
						TaxValue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TaxValue);
					}
					logs.logging("INFO", "Expected Tax Value After Tax calculation: " + TaxValue);
					logs.logging("INFO", "After Adding Displayed GST Values: " + taxAmount);
					flag = taxAmount.equals(TaxValue);
					if (flag) {
						logs.logging("INFO", "Tax Amount Verified, Tax added value: " + taxAmount);
						logs.logging("INFO", "verifing total amount after calculating tax amount!!!");
						BigDecimal DisplayLineAmnt = new BigDecimal(
								webDB.getDomPropertyFromElement(SalesInvoiceLocators.TotalAmountDisplayField,
										ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value")));
						DisplayLineAmnt = purchaseOrderFunc
								.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DisplayLineAmnt);
						logs.logging("INFO", "Displayed Total Amount: " + DisplayLineAmnt);
						BigDecimal TotalAmountAddedTax = purchaseOrderFunc
								.verifyRoundOffBigDecimalValueWithReturnBigDecimal(totalCostWithoutTax.add(taxAmount));
						logs.logging("INFO", "Calculated Total Amount with Added Tax: " + TotalAmountAddedTax);
						flag = DisplayLineAmnt.equals(TotalAmountAddedTax);
						if (flag) {
							logs.logging("INFO", "Verified Total Amount Displayed and Calculated Total Amount Matched");
						}
					}
				}
			} else {
				BigDecimal taxAmount = getTaxValue();
				logs.logging("INFO", "After Adding Displayed GST Values: " + taxAmount);
				flag = taxAmount.equals(new BigDecimal("0"));
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to add IGST,CGST,SGST and UTGST Amounts Displayed in Sales
	 * Invoice
	 * 
	 * @author Vimal - RMITS
	 * @return BigDecimal
	 */
	public BigDecimal getTaxValue() {
		BigDecimal IGSTAmount = new BigDecimal(
				webDB.getTextFromElement(SalesInvoiceLocators.IGSTTaxAmountField(), ElementType.Xpath));
//		IGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(IGSTAmount);
		BigDecimal CGSTAmount = new BigDecimal(
				webDB.getTextFromElement(SalesInvoiceLocators.CGSTTaxAmountField(), ElementType.Xpath));
//		CGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(CGSTAmount);
		BigDecimal SGSTAmount = new BigDecimal(
				webDB.getTextFromElement(SalesInvoiceLocators.SGSTTaxAmountField(), ElementType.Xpath));
//		SGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(SGSTAmount);
		BigDecimal UTGSTAmount = new BigDecimal(
				webDB.getTextFromElement(SalesInvoiceLocators.UTGSTTaxAmountField(), ElementType.Xpath));
//		UTGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(UTGSTAmount);

		BigDecimal taxAmount = IGSTAmount.add(CGSTAmount).add(SGSTAmount).add(UTGSTAmount);
		return taxAmount;
	}

	/**
	 * This Method Used To LineDetailsVerification
	 * 
	 * @author Vimal - RMITS
	 */
	public void CustomerDetailsVerification(String Customer_Name) {
		verifyLocationDetails(Customer_Name);
	}

	/**
	 * Verify Location Detail Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyLocationDetailsButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Location_Details_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Location_Details_Btn, ElementType.Xpath);
			logs.logging("INFO", "Location Detail Button Clicked Successfully");
			flag = webDB.waitForElement(SalesInvoiceLocators.Location_Detail_Iframe, ElementType.Xpath);
			webDB.switchToFrame(SalesInvoiceLocators.Location_Detail_Iframe, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For PendingSO Click SalesOrder No
	 * 
	 * @param SI_Pend_Domestic_SO_No
	 * @author Vimal - RMITS
	 */
	public void verifySIPendingSOSaleorderNo(String SI_Pend_Domestic_SO_No) {
		webDB.switchToFrame(SalesInvoiceLocators.SI_Pend_Iframe, ElementType.Xpath);
		commonFunc.hardWait(2000);
		commonFunc.expandFindTable();
		commonFunc.hardWait(1000);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Pend_SO_No_Lov, SI_Pend_Domestic_SO_No);
		logs.logging("INFO", "Sales Invoice PendingSO SaleOrder No Inserted Successfully");
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For PendingSO Click SalesOrder No
	 * 
	 * @param SI_Pend_Export_SO_No
	 * @author Vimal - RMITS
	 */
	public void verifySIPendingCSSaleorderNo(String SI_Pend_Export_SO_No) {
		webDB.switchToFrame(SalesInvoiceLocators.SI_Pend_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_SO_No_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_SO_No_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Pend_SO_No_Lov, SI_Pend_Export_SO_No);
			logs.logging("INFO", "Sales Invoice PendingSO SaleOrder No Inserted Successfully");
			commonFunc.hardWait(1000);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used for Verify PeningSO Search Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyPendingSoSearchButton() {
		webDB.switchToFrame(SalesInvoiceLocators.SI_Pend_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_Search_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_Search_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_SelectAll, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Invoice PendingSO Search Button Clicked Successfully");
				commonFunc.hardWait(1000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used for Verify Pening CS Search Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyPendingCSSearchButton() {
		webDB.switchToFrame(SalesInvoiceLocators.SI_Pend_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_Search_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_Search_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_CS_SelectAll, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Invoice PendingSO Search Button Clicked Successfully");
				commonFunc.hardWait(1000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used for Verify PeningSO SelectAll Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyPendingSOSelectAllButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_SelectAll, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_SelectAll, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_CreateInvoice_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Invoice PendingSO SelectAll Button Successfully");
				commonFunc.hardWait(2000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used for Verify Pening CS SelectAll Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyPendingCSSelectAllButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_CS_SelectAll, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_CS_SelectAll, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_CS_CreateInvoice_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Invoice PendingSO SelectAll Button Successfully");
				commonFunc.hardWait(2000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used for Verify PeningSO Create Invoice Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyPendingSOCreateInvoiceButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_CreateInvoice_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_CreateInvoice_Btn, ElementType.Xpath);
			logs.logging("INFO", "Sales Invoice PendingSO Create Invoice Button Clicked Successfully");
			webDB.switchToDefaultContent();
			commonFunc.hardWait(6000);
			String SIdocno = webDB.getTextFromElement(SalesInvoiceLocators.SI_Doc_NO, ElementType.Xpath);
			logs.logging("INFO", "Get SI DocNo Is: " + SIdocno);
			VariableHandler.setSIdocno(SIdocno);
			MultiSIDocNumber.add(VariableHandler.getSIdocno());
			commonFunc.hardWait(2000);
		}
	}

	/**
	 * This Method used for Verify Pening CS Create Invoice Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyPendingCSCreateInvoiceButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_CS_CreateInvoice_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_CS_CreateInvoice_Btn, ElementType.Xpath);
			logs.logging("INFO", "Sales Invoice PendingSO Create Invoice Button Clicked Successfully");
			webDB.switchToDefaultContent();
			commonFunc.hardWait(3000);
			String SIdocno = webDB.getTextFromElement(SalesInvoiceLocators.SI_Doc_NO, ElementType.Xpath);
			logs.logging("INFO", "Get SI DocNo Is: " + SIdocno);
			VariableHandler.setSIdocno(SIdocno);
		}
	}

	/**
	 * Verify SI Pending Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifySalesInvoicePendingCSButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Sales_Inv_Pend_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Sales_Inv_Pend_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_CS_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_Pend_CS_Btn, ElementType.Xpath);
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_Pend_Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Sales Invoice Pending Button Clicked Successfully");
					commonFunc.hardWait(2000);
				} else {
					logs.logging("INFO", "Failed to click Sales Invoice Pending Button");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Sales Free Sample Invoice Prefix
	 * 
	 * @author Vimal - RMITS
	 * @param FS_Invoice_Pfx
	 */
	public void verifyFreeSampleInvoicePfx(String FS_Invoice_Pfx) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Invoice_Pfx, FS_Invoice_Pfx);
			logs.logging("INFO", "Invoice Prefix Is Displayed Successfully");
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Header_CPC_LOV_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CPC Header Lov Is Displayed Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Sales Free Replacement Invoice Prefix
	 * 
	 * @author Vimal - RMITS
	 * @param FR_Invoice_Pfx
	 */
	public void verifyFreeReplacementInvoicePfx(String FR_Invoice_Pfx) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Invoice_Pfx, FR_Invoice_Pfx);
			logs.logging("INFO", "Invoice Prefix Is Displayed Successfully");
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Header_CPC_LOV_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CPC Header Lov Is Displayed Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Sales Free Replacement Invoice Prefix
	 * 
	 * @author Vimal - RMITS
	 * @param FR_Invoice_Pfx
	 */
	public void verifyScrapInvoicePfx(String SCR_Invoice_Pfx) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Invoice_Pfx, SCR_Invoice_Pfx);
			logs.logging("INFO", "Invoice Prefix Is Displayed Successfully");
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Header_CPC_LOV_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CPC Header Lov Is Displayed Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param CustomerName
	 * @param SI_FS_Type
	 */
	public void verifyCreateDirectSalesInvoiceFreeSampleAddingParameters(String LocationId, String SI_FS_Type,
			String CustomerName) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Loc_ID_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Loc_ID_Btn, LocationId);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Type_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Type_Btn, SI_FS_Type);
				flag = webDB.waitForElement(SalesInvoiceLocators.Customer_ID_Btn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Customer_ID_Btn, CustomerName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param CustomerName
	 * @param SI_FR_Type
	 */
	public void verifyCreateDirectSalesInvoiceFreeReplacementAddingParameters(String LocationId, String SI_FR_Type,
			String CustomerName) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Loc_ID_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Loc_ID_Btn, LocationId);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Type_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Type_Btn, SI_FR_Type);
				flag = webDB.waitForElement(SalesInvoiceLocators.Customer_ID_Btn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Customer_ID_Btn, CustomerName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param CustomerName
	 * @param SI_Scrarp_Type
	 */
	public void verifyCreateDirectSalesInvoiceScrapAddingParameters(String LocationId, String SI_Scrarp_Type,
			String CustomerName) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Loc_ID_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Loc_ID_Btn, LocationId);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Type_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Type_Btn, SI_Scrarp_Type);
				flag = webDB.waitForElement(SalesInvoiceLocators.Customer_ID_Btn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Customer_ID_Btn, CustomerName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify SaleInvoice Scarp Item ID
	 * 
	 * @author Vimal - RMITS
	 * @param SI_Scrarp_Item
	 */
	public void verifySaleInvoiceScarpItemID(String SI_Scrarp_Item) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, SI_Scrarp_Item);
			logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + SI_Scrarp_Item);
			flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verify UnpickButton
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyUnpickButton() {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Unpick_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pick Button Displayed Successfully");
				webDB.clickAnElement(SalesInvoiceLocators.SI_Unpick_Btn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				if (flag) {
					logs.logging("INFO", "Unpick Button clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to check Stock and Invoice Quantity with Warehouse
	 * 
	 * @author Sakthi-GWL,Vimal-RMITS
	 * @param Warehouse
	 */
	public void verifyLineDetailsShipFromScrapWarehouseAndStockAndInvoiceQuantity(String SI_Ship_From_WH) {
		flag = webDB.waitForElement(SalesInvoiceLocators.SaleInvoiceWarehouseShipFrom, ElementType.Xpath);
		if (flag) {
			String DisplayWH = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SaleInvoiceWarehouseShipFrom,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Displayed Warehouse ship from: " + DisplayWH);
			logs.logging("INFO", "Expected Warehouse ship from: " + SI_Ship_From_WH);
			flag = SI_Ship_From_WH.equalsIgnoreCase(DisplayWH);
			if (flag) {
				logs.logging("INFO", "Verified Warehouse of Shiping From");
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.SaleInvoiceStockQuantity, ElementType.Xpath);
				if (flag) {
					String StockQnty = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SaleInvoiceStockQuantity,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					String OrderInvoiceQnty = webDB.getDomPropertyFromElement(
							SalesInvoiceLocators.SaleInvoiceOrderInvoiceQuantity, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Displayed Stock Quantity ship from: " + StockQnty);
					logs.logging("INFO", "Displayed Invoice Order Quantity ship from: " + OrderInvoiceQnty);
//					flag = Integer.parseInt(StockQnty) > Integer.parseInt(OrderInvoiceQnty);
					flag = (new BigDecimal(StockQnty).compareTo(new BigDecimal(OrderInvoiceQnty))) == 1;
					if (flag) {
						logs.logging("INFO", "Verified Stock Quantity Greater than Invoice Quantity");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Cancel Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyCancelButton(String SI_Cancel_Reason) {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Cancel_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Cancel_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Cancel_OK_Btn, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(SalesInvoiceLocators.SI_Cancel_Reason, SI_Cancel_Reason, ElementType.Xpath);
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_Cancel_OK_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.SI_Cancel_OK_Btn, ElementType.Xpath);
					logs.logging("INFO", "Sales Invoice Document Cancelled Sucessfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Sales Invoice LineWise Delete Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void VerifySalesInvoiceLineWiseDeleteButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_linedetail_Button, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_linedetail_Button, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Linewise_Delete_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_Linewise_Delete_Btn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Linewise Delete Icon Clicked Sucessfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Verify Sales Invoice Document Cancelled Status
	 * 
	 * @param SIdocno
	 * @param Exp_Cancel_Status
	 */
	public void verifySalesInvoiceDocumentCancelStatus(String SIdocno, String Exp_Cancel_Status) {
		flag = webDB.waitForElement(SalesInvoiceLocators.FindSalesInvoiceNoLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.FindSalesInvoiceNoLovBtn, SIdocno);
			flag = webDB.waitForElement(SalesInvoiceLocators.FindSalesInvoiceSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.FindSalesInvoiceSearchBtn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesInvoiceLocators.SIStatusTableText(SIdocno), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(SalesInvoiceLocators.SIStatusTableText(SIdocno),
							ElementType.Xpath);
					logs.logging("INFO", "get text from Sales Invoice status :" + Status);

					flag = Status.contains(Exp_Cancel_Status);
					if (flag) {
						logs.logging("INFO", "Displayed Status :" + Status);
						logs.logging("INFO", "Expected Status :" + Exp_Cancel_Status);
						logs.logging("INFO", "Verified Sales Invoice Document Status");

					} else {
						logs.logging("INFO", "Sales Invoice Doucment Status Verification Failed");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Domestic Local Customer
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceDomesticLocalCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("LocalCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("LocalCustomer"));
		return flag;
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Domestic InterState
	 * Customer
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceDomesticInterStateCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_InterState_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_InterState_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("InterStateCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("InterStateCustomer"));
		return flag;
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Domestic UTGST Customer
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceDomesticUTGSTCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_UTGST_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_UTGST_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("UTGSTCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("UTGSTCustomer"));
		return flag;
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Domestic SEZ Customer
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceDomesticSEZCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_SEZ_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_SEZ_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("SEZCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("SEZCustomer"));
		return flag;
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Domestic From Approved
	 * Salesorder
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public void verifyCreateSalesInvoiceDomesticFromApprovedSalesorders() {
		salesOrderFunctions.verifyDomesticSOGeneralSimilarStockAndCustomerUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifySalesInvoicePendingButton();
		verifySIPendingSOSaleorderNo(VariableHandler.getSONumber());
		verifyPendingSoSearchButton();
		verifyPendingSOSelectAllButton();
		verifyPendingSOCreateInvoiceButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		LocationDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
//		salesOrderFunctions
//				.SIverifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("SODomUserDefined"));
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Export From Approved
	 * Salesorder
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceExportFromApprovedSalesorders() {
		salesOrderFunctions.verifyExportSOGeneralSimilarStockAndCustomerUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifySalesInvoicePendingButton();
		verifySIPendingSOSaleorderNo(VariableHandler.getSONumber());
		verifyPendingSoSearchButton();
		verifyPendingSOSelectAllButton();
		verifyPendingSOCreateInvoiceButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		LocationDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
//		salesOrderFunctions
//				.SIverifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("SOExpUserDefined"));
		return flag;
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Domestic From Approved
	 * Customer Schedules
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceFromApprovedDomesticCustomerSchedules() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifySalesInvoicePendingCSButton();
		verifySIPendingSOSaleorderNo(commonFunc.getDataFromTestDataSheet("SI_Pend_Domestic_SO_No"));
		verifyPendingCSSearchButton();
		verifyPendingCSSelectAllButton();
		verifyPendingCSCreateInvoiceButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		LocationDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		checkSaleInvoiceLineTaxCalculationForLastAddedItem();
//		verifyLineWiseDetailsButton();
		switchToLineDetailsIframeInSaleInvoice();
		verifyLineDetailsShipFromWarehouseAndStockAndInvoiceQuantity(commonFunc.getDataFromTestDataSheet("Ship_From"));
		verifyLineDetailsSaveIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Export From Approved
	 * Customer Schedules
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceFromApprovedExportCustomerSchedules() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifySalesInvoicePendingCSButton();
		verifySIPendingCSSaleorderNo(commonFunc.getDataFromTestDataSheet("SI_Pend_Export_SO_No"));
		verifyPendingCSSearchButton();
		verifyPendingCSSelectAllButton();
		verifyPendingCSCreateInvoiceButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		LocationDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		checkSaleInvoiceLineTaxCalculationForLastAddedItem();
//		verifyLineWiseDetailsButton();
		switchToLineDetailsIframeInSaleInvoice();
		verifyLineDetailsShipFromWarehouseAndStockAndInvoiceQuantity(commonFunc.getDataFromTestDataSheet("Ship_From"));
		verifyLineDetailsSaveIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		return flag;
	}

	/**
	 * This Method is Used To verify create Free Sample type of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateFreeSampletypeofSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceFreeSampleAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_FS_Type"),
				commonFunc.getDataFromTestDataSheet("SI_FS_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyFreeSampleInvoicePfx(commonFunc.getDataFromTestDataSheet("FS_Invoice_Pfx"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_FS_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("FreeSampleType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("FreeSampleType"));
		return flag;
	}

	/**
	 * This Method is Used To verify create FREE REPLACEMENT type of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateFreeReplacementTypeofSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceFreeReplacementAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_FR_Type"),
				commonFunc.getDataFromTestDataSheet("SI_FR_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyFreeReplacementInvoicePfx(commonFunc.getDataFromTestDataSheet("FR_Invoice_Pfx"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_FR_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("FreeReplacement"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("FreeReplacement"));
		return flag;
	}

	/**
	 * This Method is Used To verify create ScrapType of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateScrapTypeofSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceFreeReplacementAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Scrarp_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Scarp_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyFreeReplacementInvoicePfx(commonFunc.getDataFromTestDataSheet("SCR_Invoice_Pfx"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Scarp_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ScrapType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ScrapType"));
		return flag;
	}

	/**
	 * This Method is Used To verify Unpick The Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public void verifyUnpickTheSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("LocalCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyUnpickButton();
	}

	/**
	 * This Method is Used To verify Cancel The Sales Invoice Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCancelTheSalesInvoiceDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("LocalCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyCancelButton(commonFunc.getDataFromTestDataSheet("SI_Cancel_Reason"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method is Used To verify Cancel The Sales Invoice Lines
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCancelSalesInvoiceLines() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("LocalCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		VerifySalesInvoiceLineWiseDeleteButton();
		return flag;
	}

	/**
	 * This Method Used For Adding UserDefined Item Line From Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddingUserDefinedItemLineFromReadingExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			salesOrderFunctions.StockQtyUpdateInExcelSI(i, ArrayList, SheetName);
			String Item = itemMultiples[i][1];
			String SalesGL_Acct = itemMultiples[i][35];
			String InvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			String Promotional = itemMultiples[i][36];
			if (!Item.equals("")) {
				flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.SIPageItemTable(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.SIPageItemTable(), ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, Item);
						logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item);
						flag = webDB.waitForElement(SalesInvoiceLocators.SIPageGLAcctTable(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.SIPageGLAcctTable(), ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Sales_GL_Acc_Desc_LOV_Btn,
									SalesGL_Acct);
							logs.logging("INFO", "Sales GL Acct Desc Inserted Successfully");
							commonFunc.hardWait(1000);
							verifyAddTableSpecs(SalesInvoiceLocators.SIPageInvQtyTable(),
									SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
							commonFunc.checkRefreshCellsDisappear();
							verifyAddTableSpecs(SalesInvoiceLocators.SIPageUnitPriceTable(),
									SalesInvoiceLocators.UnitCost_Input, UnitPrice);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
							if (flag) {
								String GetTextHsnCode = webDB.getTextFromElement(SalesInvoiceLocators.HSNcode_Column(),
										ElementType.Xpath);
								if (!GetTextHsnCode.equals(HSNcode_SI)) {
									webDB.clickAnElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
									commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.HSN_Code_LOV_Btn,
											HSNcode_SI);
									logs.logging("INFO", "HSN Code Inserted Successfully");
									webDB.pressTabKey();
								}
								webDB.pressTabKey();
								commonFunc.checkRefreshCellsDisappear();
							}
						}
					}
				}
				flag = webDB.waitForElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Headerwise Tick Icon Displayed Successfully");
					flag = webDB.clickAnElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Headerwise Tick Icon Clicked Successfully");
					}
				}
				commonFunc.checkRefreshCellsDisappear();
				verifyLineWiseDetailsButton(i);
				switchToLineDetailsIframeInSaleInvoice();
				checkSILineDetailsDataBaseValuesWithUIValues();
				if (Promotional.equalsIgnoreCase("Promotional")) {
					verifyLineDetailsPromotionalDropDown(Promotional);
				}
				verifyLineDetailsSaveIcon();
			}
		}
		VariableHandler.setExpectedStockQty2(ArrayList);
	}

	/**
	 * This Method Used For Check Sales Invoiced Header Status Details DataBase
	 * Values WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues() {
		String SIDocumentStatus = dbCommonFunctions.SIHeaderDetails(VariableHandler.getSIdocno(),
				commonFunc.getDataFromTestDataSheet("SIDBStatusColumn"));
		salesOrderFunctions.verifyAssertionBothValues(SIDocumentStatus,
				commonFunc.getDataFromTestDataSheet("SIInvoicedStatus"));
		logs.logging("INFO", "Verified Sales Invoice Document Status Is " + SIDocumentStatus
				+ " Against This Sales Invoice Number " + VariableHandler.getSIdocno());
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For verify Expected Stock Qty With DBValues After Invoice
	 * 
	 * @author Harikrishnan - RMITS
	 * @param SheetName
	 */
	public void verifyExpectedStockQtyWithDBValuesAfterInvoice(String SheetName) {
		String[][] SOItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOItemDetails.length; i++) {
			String ItemId = SOItemDetails[i][1];
			String WareHouse = SOItemDetails[i][34];
			if (!ItemId.equals("")) {
				String AfterStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"After Invoiced Actual Stock Qty For This Item " + ItemId + "Is : " + AfterStockQty);
				salesOrderFunctions.verifyAssertionBothValues(AfterStockQty,
						VariableHandler.getExpectedStockQty2().get(i - 4));
				logs.logging("INFO", "Verified Expected Stock Qty For This Item " + ItemId
						+ " With Database Values After Invoiced The Document");
			}
		}
	}

	/**
	 * This Method Is Used To Verify The Line Details UI Values With Database Values
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkSILineDetailsDataBaseValuesWithUIValues() {

//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_Store_ID, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBStoreIDColumn")));
//		logs.logging("INFO", "Line Detail StoreID Matched With DataBase Values");

//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_UnitCost, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBUnitCostColumn")));
//		logs.logging("INFO", "Line Detail UnitCost Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_Grossamt, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBGrossAmtColumn")));
//		logs.logging("INFO", "Line Detail GrossAmt Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_TaxAmt, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBTaxAmtColumn")));
//		logs.logging("INFO", "Line Detail TaxAmt Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_NetAmt, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBNetAmtColumn")));
//		logs.logging("INFO", "Line Detail NetAmt Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_Stk_Inv_Qty, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBStkInvQtyColumn")));
//		logs.logging("INFO", "Line Detail NetAmt Matched With DataBase Values");
	}

	/**
	 * This method used to Line details Promotional Type selecting
	 * 
	 * @param Text
	 * @author Vimal - RMITS
	 */
	public void verifyLineDetailsPromotionalDropDown(String Text) {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Detials_Promotional_DropDown, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(SalesInvoiceLocators.SI_Detials_Promotional_DropDown, Text, ElementType.Xpath);
			logs.logging("INFO", "Promotional Type Selected Successfully");
			commonFunc.hardWait(2000);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding Other Than UserDefined Item Line From Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddingOtherthanUserDefinedItemLineFromReadingExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			salesOrderFunctions.StockQtyUpdateInExcelSI(i, ArrayList, SheetName);
			String Item = itemMultiples[i][1];
			String SalesGL_Acct = itemMultiples[i][35];
			String InvoiceQty = itemMultiples[i][6];
//			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			if (!Item.equals("")) {
				flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, Item);
						logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item);
						flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Sales_GL_Acc_Desc_LOV_Btn,
									SalesGL_Acct);
							logs.logging("INFO", "Sales GL Acct Desc Inserted Successfully");
							commonFunc.hardWait(1000);
							verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty(),
									SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
//							verifyAddTableSpecs(SalesInvoiceLocators.UnitCost_Column,
//									SalesInvoiceLocators.UnitCost_Input, UnitPrice);
							flag = webDB.waitForElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
								commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.HSN_Code_LOV_Btn,
										HSNcode_SI);
								logs.logging("INFO", "HSN Code Inserted Successfully");

							}
						}

					}
				}
				flag = webDB.waitForElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Headerwise Tick Icon Displayed Successfully");
					flag = webDB.clickAnElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Headerwise Tick Icon Clicked Successfully");
						GetUIUnitpriceAndwriteUnitPriceInExcelSheet(Filepath, SheetName, i, Item);
					}

				}
				commonFunc.checkRefreshCellsDisappear();
				verifyLineWiseDetailsButton(i);
				switchToLineDetailsIframeInSaleInvoice();
				checkSILineDetailsDataBaseValuesWithUIValues();
				verifyLineDetailsSaveIcon();
			}
		}
		VariableHandler.setExpectedStockQty2(ArrayList);
	}

	/**
	 * This Method used For Get UI UnitPrice And Write UnitPrice In Excel Sheet
	 * 
	 * @author Harikrishna -RMITS
	 * @param FilePath
	 * @param SheetName
	 * @param i
	 * @param Item
	 */
	public void GetUIUnitpriceAndwriteUnitPriceInExcelSheet(String FilePath, String SheetName, int i, String Item) {
		commonFunc.checkRefreshCellsDisappear();
		String UnitCost = webDB.getDriver().findElement(By.xpath(SalesInvoiceLocators.InvoiceUnitCostTable(i - 3)))
				.getText();
		logs.logging("INFO", "Get Text Unit Cost For This Item  " + Item + " Is : " + UnitCost);
		excelUtils.writeDataInExcel(FilePath, SheetName, i, 7, UnitCost);

	}

	/**
	 * This Method Used For check Proforma Header Details DataBase Values With
	 * UIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkProformaHeaderDetailsDataBaseValuesWithUIValues() {
		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.PI_LocID, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.PIHeaderDetails(VariableHandler.getPI_DocNo(),
						commonFunc.getDataFromTestDataSheet("PIDBLocIDColumn")));
		logs.logging("INFO", "Header Detail LocationID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.PI_Unit, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.PIHeaderDetails(VariableHandler.getPI_DocNo(),
						commonFunc.getDataFromTestDataSheet("PIDBUnitColumn")));
		logs.logging("INFO", "Header Detail Unit Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.PI_Type, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.PIHeaderDetails(VariableHandler.getPI_DocNo(),
						commonFunc.getDataFromTestDataSheet("PIDBPITypeColumn")));
		logs.logging("INFO", "Header Detail PI Type Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.PI_Doc_Pfx, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.PIHeaderDetails(VariableHandler.getPI_DocNo(),
						commonFunc.getDataFromTestDataSheet("PIDBDocPfxColumn")));
		logs.logging("INFO", "Header Detail DocPfx Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.PI_Doc_No, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.PIHeaderDetails(VariableHandler.getPI_DocNo(),
						commonFunc.getDataFromTestDataSheet("PIDBDocNoColumn")));
		logs.logging("INFO", "Header Detail DocNo Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.PI_Customer_ID, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.PIHeaderDetails(VariableHandler.getPI_DocNo(),
						commonFunc.getDataFromTestDataSheet("PIDBCustomerColumn")));
		logs.logging("INFO", "Header Detail CustomerID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.Pro_Inv_Pfx, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.PIHeaderDetails(VariableHandler.getPI_DocNo(),
						commonFunc.getDataFromTestDataSheet("PIDBInvPfxColumn")));
		logs.logging("INFO", "Header Detail CustomerName Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.Pro_Inv_Doc, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.PIHeaderDetails(VariableHandler.getPI_DocNo(),
						commonFunc.getDataFromTestDataSheet("PIDBInvNoColumn")));
		logs.logging("INFO", "Header Detail CustomerName Matched With DataBase Values");
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used To Adding profoma Invoice Line IN ExcelSheet
	 * 
	 * @author Vimal - RMITS
	 */
	public void AddingprofomaInvoiceLineINExcelSheet(String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			String Item = itemMultiples[i][1];
			String ProformaInvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			if (!Item.equals("")) {
				verifyProformaInvoiceLineWiseAddIconClick();
				verifyProformaInvoiceItemID(Item);
				verifyProformaInvoiceQtyUnitcost(ProformaInvoiceQty, UnitPrice);
				verifyProformaInvoiceHSNCode(HSNcode_SI);
				verifyProformaInvoiceLineWiseTickIcon();
				// checkProformaLineDetailsDataBaseValuesWithUIValues(i, Filepath,
				// itemMultiples);
			}
		}
	}

	/**
	 * This Method Used For check Proforma Line Details DataBase Values With
	 * UIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkProformaLineDetailsDataBaseValuesWithUIValues(int i1, String FilePath, String[][] ExcelName) {
		for (int i = i1; i <= i1; i++) {
			String Item = ExcelName[i][1];
			String Qty = ExcelName[i][6];
			String UnitCost = ExcelName[i][7];
			String Discount = ExcelName[i][18];
			String HSNCode = ExcelName[i][21];
			String Tax = ExcelName[i][22];
			String IGST = ExcelName[i][25];
			String CGST = ExcelName[i][23];
			String SGST = ExcelName[i][24];

			salesOrderFunctions.verifyAssertionBothValues(Item, dbCommonFunctions.PILineDetails(
					VariableHandler.getPI_DocNo(), commonFunc.getDataFromTestDataSheet("PIDBItemColumn")));
			logs.logging("INFO", "Line Detail Item Matched With DataBase Values");

			salesOrderFunctions.verifyAssertionBothValues(Qty, dbCommonFunctions.PILineDetails(
					VariableHandler.getPI_DocNo(), commonFunc.getDataFromTestDataSheet("PIDBPIQtyColumn")));
			logs.logging("INFO", "Line Detail PI Qty Matched With DataBase Values");

			salesOrderFunctions.verifyAssertionBothValues(UnitCost, dbCommonFunctions.PILineDetails(
					VariableHandler.getPI_DocNo(), commonFunc.getDataFromTestDataSheet("PIDBUnitPricecolumn")));
			logs.logging("INFO", "Line Detail PI Unitcost Matched With DataBase Values");

			salesOrderFunctions.verifyAssertionBothValues(Discount, dbCommonFunctions.PILineDetails(
					VariableHandler.getPI_DocNo(), commonFunc.getDataFromTestDataSheet("PIDBDiscAmtColumn")));
			logs.logging("INFO", "Line Detail DiscountAmt Matched With DataBase Values");

			salesOrderFunctions.verifyAssertionBothValues(HSNCode, dbCommonFunctions.PILineDetails(
					VariableHandler.getPI_DocNo(), commonFunc.getDataFromTestDataSheet("PIDBHSNColumn")));
			logs.logging("INFO", "Line Detail HSN Code Matched With DataBase Values");

//			salesOrderFunctions.verifyAssertionBothValues(IGST, dbCommonFunctions.PILineDetails(
//					VariableHandler.getPI_DocNo(), commonFunc.getDataFromTestDataSheet("PIDBIGSTAmtColumn")));
//			logs.logging("INFO", "Line Detail IGST Amount Matched With DataBase Values");
//
//			salesOrderFunctions.verifyAssertionBothValues(CGST, dbCommonFunctions.PILineDetails(
//					VariableHandler.getPI_DocNo(), commonFunc.getDataFromTestDataSheet("PIDBCGSTAmtColumn")));
//			logs.logging("INFO", "Line Detail CGST Amount Matched With DataBase Values");
//
//			salesOrderFunctions.verifyAssertionBothValues(SGST, dbCommonFunctions.PILineDetails(
//					VariableHandler.getPI_DocNo(), commonFunc.getDataFromTestDataSheet("PIDBSGSTAmtColumn")));
//			logs.logging("INFO", "Line Detail SGST Amount Matched With DataBase Values");
			commonFunc.hardWait(1000);
		}
	}

	/**
	 * This Method Used To Adding profoma Invoice Advance, PreDispatch Line IN
	 * ExcelSheet
	 * 
	 * @author Vimal - RMITS
	 */
	public void AddingProfomaInvoiceAdvanceLineINExcelSheet(String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			String Item = itemMultiples[i][1];
			String ProformaInvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			String SORef = itemMultiples[i][36];

			if (!Item.equals("")) {
				verifyProformaInvoiceLineWiseAddIconClick();
				verifyProformaInvoiceAdvanceSOReference(SORef);
				verifyProformaInvoiceItemID(Item);
				verifyProformaInvoiceQtyUnitcost(ProformaInvoiceQty, UnitPrice);
				verifyProformaInvoiceHSNCode(HSNcode_SI);
				verifyProformaInvoiceLineWiseTickIcon();
//				checkProformaLineDetailsDataBaseValuesWithUIValues(i, Filepath, itemMultiples);
			}
		}
	}

	/**
	 * This Method Used To Adding profoma Invoice Advance, PreDispatch Line IN
	 * ExcelSheet Export
	 * 
	 * @author Vimal - RMITS
	 */
	public void AddingProfomaInvoiceAdvanceExportLineINExcelSheet(String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			String Item = itemMultiples[i][1];
			String ProformaInvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			String SORef = itemMultiples[i][37];

			if (!Item.equals("")) {
				verifyProformaInvoiceLineWiseAddIconClick();
				verifyProformaInvoiceAdvanceSOReference(SORef);
				verifyProformaInvoiceItemID(Item);
				verifyProformaInvoiceQtyUnitcost(ProformaInvoiceQty, UnitPrice);
				verifyProformaInvoiceHSNCode(HSNcode_SI);
				verifyProformaInvoiceLineWiseTickIcon();
//				checkProformaLineDetailsDataBaseValuesWithUIValues(i, Filepath, itemMultiples);
			}
		}
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Domestic CESS Customer
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceDomesticCESSCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"), commonFunc.getDataFromTestDataSheet("CustomerName"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("CESScustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("CESScustomer"));
		return flag;
	}

	/**
	 * Verify Sales Free Replacement Invoice Prefix
	 * 
	 * @author Vimal - RMITS
	 * @param Service_Invoice_Pfx
	 */
	public void verifyServiceInvoicePfx(String Service_Invoice_Pfx) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Invoice_Pfx, Service_Invoice_Pfx);
			logs.logging("INFO", "Invoice Prefix Is Displayed Successfully");
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_ShowMore, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Header_CPC_LOV_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CPC Header Lov Is Displayed Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify SaleInvoice Service Item ID
	 * 
	 * @author Vimal - RMITS
	 * @param SI_Service_Item
	 */
	public void verifySaleInvoiceServiceItemID(String SI_Service_Item) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, SI_Service_Item);
			logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + SI_Service_Item);
			flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param CustomerName
	 * @param SI_Service_Type
	 */
	public void verifyCreateDirectSalesInvoiceServiceAddingParameters(String LocationId, String SI_Service_Type,
			String CustomerName) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Loc_ID_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Loc_ID_Btn, LocationId);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Type_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Type_Btn, SI_Service_Type);
				flag = webDB.waitForElement(SalesInvoiceLocators.Customer_ID_Btn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Customer_ID_Btn, CustomerName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param CustomerName
	 * @param SI_Service_Type
	 */
	public void verifyExportCreateDirectSalesInvoiceServiceAddingParameters(String LocationId, String SI_Service_Type,
			String Export_Customer_Name) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Loc_ID_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Loc_ID_Btn, LocationId);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Type_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Type_Btn, SI_Service_Type);
				flag = webDB.waitForElement(SalesInvoiceLocators.Customer_ID_Btn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Customer_ID_Btn,
							Export_Customer_Name);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Verify sales Invoice LineDetails Tab2 and Clicking
	 * Shipset Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifysalesInvoiceLineDetailsTab2() {
//		flag = webDB.waitForElement(SalesInvoiceLocators.SI_LineDetails2_Tab, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(SalesInvoiceLocators.SI_LineDetails2_Tab, ElementType.Xpath);
//			commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Shipset_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Shipset_Btn, ElementType.Xpath);
			commonFunc.hardWait(2000);
			webDB.pressEscapeKey();
			commonFunc.hardWait(2000);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to check Stock and Invoice Quantity with Warehouse
	 * 
	 * @author Sakthi-GWL,Vimal-RMITS
	 * @param SI_Ship_From_Service_WH
	 */
	public void verifyLineDetailsShipFromServiceWarehouseAndStockAndInvoiceQuantity(String SI_Ship_From_Service_WH) {
		flag = webDB.waitForElement(SalesInvoiceLocators.SaleInvoiceWarehouseShipFrom, ElementType.Xpath);
		if (flag) {
			String DisplayWH = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SaleInvoiceWarehouseShipFrom,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Displayed Warehouse ship from: " + DisplayWH);
			logs.logging("INFO", "Expected Warehouse ship from: " + SI_Ship_From_Service_WH);
			flag = SI_Ship_From_Service_WH.equalsIgnoreCase(DisplayWH);
			if (flag) {
				logs.logging("INFO", "Verified Warehouse of Shiping From");
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.SaleInvoiceStockQuantity, ElementType.Xpath);
				if (flag) {
//						String StockQnty = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SaleInvoiceStockQuantity,
//								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					String OrderInvoiceQnty = webDB.getDomPropertyFromElement(
							SalesInvoiceLocators.SaleInvoiceOrderInvoiceQuantity, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
//						logs.logging("INFO", "Displayed Stock Quantity ship from: " + StockQnty);
					logs.logging("INFO", "Displayed Invoice Order Quantity ship from: " + OrderInvoiceQnty);
//						flag = Integer.parseInt(StockQnty) > Integer.parseInt(OrderInvoiceQnty);
//						flag = (new BigDecimal(StockQnty).compareTo(new BigDecimal(OrderInvoiceQnty))) == 1;
					if (flag) {
						logs.logging("INFO", "Verified Stock Quantity Greater than Invoice Quantity");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Service Type Adding UserDefined Item Line From Excel
	 * Sheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddingUserDefinedServiceTypeItemLineFromReadingExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			String Item = itemMultiples[i][1];
			String SalesGL_Acct = itemMultiples[i][35];
			String InvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			String Promotional = itemMultiples[i][36];
			if (!Item.equals("")) {
				flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, Item);
						logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item);
						flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Sales_GL_Acc_Desc_LOV_Btn,
									SalesGL_Acct);
							logs.logging("INFO", "Sales GL Acct Desc Inserted Successfully");
							commonFunc.hardWait(1000);
							verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty(),
									SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
							verifyAddTableSpecs(SalesInvoiceLocators.UnitCost_Column(),
									SalesInvoiceLocators.UnitCost_Input, UnitPrice);
							flag = webDB.waitForElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
								commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.HSN_Code_LOV_Btn,
										HSNcode_SI);
								logs.logging("INFO", "HSN Code Inserted Successfully");
								webDB.pressTabKey();
							}
						}

					}
				}
				flag = webDB.waitForElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Headerwise Tick Icon Displayed Successfully");
					flag = webDB.clickAnElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Headerwise Tick Icon Clicked Successfully");
					}

				}
				commonFunc.checkRefreshCellsDisappear();
				verifyLineWiseDetailsButton(i);
				switchToLineDetailsIframeInSaleInvoice();
				checkSILineDetailsDataBaseValuesWithUIValues();
				if (Promotional.equalsIgnoreCase("Promotional")) {
					verifyLineDetailsPromotionalDropDown(Promotional);
				}
				verifyLineDetailsSaveIcon();
			}
		}
	}

	/**
	 * This Method is Used To verify Create Service Of Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateServiceOfSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceServiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Service_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Service_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyServiceInvoicePfx(commonFunc.getDataFromTestDataSheet("Service_Invoice_Pfx"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Service_Local_Customer"));
		AddingUserDefinedServiceTypeItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ServiceType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Adding UserDefined ShipSet Item Line From Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddingUserDefinedItemLineShipsetFromReadingExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			salesOrderFunctions.StockQtyUpdateInExcelSI(i, ArrayList, SheetName);
			String Item = itemMultiples[i][1];
			String SalesGL_Acct = itemMultiples[i][35];
			String InvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			String Promotional = itemMultiples[i][36];
			if (!Item.equals("")) {
				flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, Item);
						logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item);
						flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Sales_GL_Acc_Desc_LOV_Btn,
									SalesGL_Acct);
							logs.logging("INFO", "Sales GL Acct Desc Inserted Successfully");
							commonFunc.hardWait(1000);
							verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty(),
									SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
							verifyAddTableSpecs(SalesInvoiceLocators.UnitCost_Column(),
									SalesInvoiceLocators.UnitCost_Input, UnitPrice);
							flag = webDB.waitForElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
								commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.HSN_Code_LOV_Btn,
										HSNcode_SI);
								logs.logging("INFO", "HSN Code Inserted Successfully");
								webDB.pressTabKey();
							}
						}
					}
				}
				flag = webDB.waitForElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Headerwise Tick Icon Displayed Successfully");
					flag = webDB.clickAnElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Headerwise Tick Icon Clicked Successfully");
					}
				}
				commonFunc.checkRefreshCellsDisappear();
				verifyLineWiseDetailsButton(i);
				switchToLineDetailsIframeInSaleInvoice();
				checkSILineDetailsShipsetDataBaseValuesWithUIValues();
				if (Promotional.equalsIgnoreCase("Promotional")) {
					verifyLineDetailsPromotionalDropDown(Promotional);
				}
				verifyLineDetailsSaveIcon();
			}
		}
		VariableHandler.setExpectedStockQty2(ArrayList);
	}

	/**
	 * This Method Is Used To Verify The Line Details ShipSet UI Values With
	 * Database Values
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkSILineDetailsShipsetDataBaseValuesWithUIValues() {
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_Store_ID, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBStoreIDColumn")));
//		logs.logging("INFO", "Line Detail StoreID Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_UnitCost, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBUnitCostColumn")));
//		logs.logging("INFO", "Line Detail UnitCost Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_Grossamt, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBGrossAmtColumn")));
//		logs.logging("INFO", "Line Detail GrossAmt Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_TaxAmt, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBTaxAmtColumn")));
//		logs.logging("INFO", "Line Detail TaxAmt Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_NetAmt, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBNetAmtColumn")));
//		logs.logging("INFO", "Line Detail NetAmt Matched With DataBase Values");
//
//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_LineDet_Stk_Inv_Qty, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.SILineDetails(VariableHandler.getSIdocno(),
//						commonFunc.getDataFromTestDataSheet("SIDBStkInvQtyColumn")));
//		logs.logging("INFO", "Line Detail NetAmt Matched With DataBase Values");
		commonFunc.hardWait(1000);
		verifysalesInvoiceLineDetailsTab2();
	}

	/**
	 * This Method is Used To verify Create Sales Invoice With Shipset Item
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreateSalesInvoiceWithShipsetItem() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		AddingUserDefinedItemLineShipsetFromReadingExcelSheet(webDB.getDataFromProperties("ShipSet"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ShipSet"));
		return flag;
	}

	/**
	 * This Method Used For Create Sales Invoice With Promotional Items
	 * 
	 * @return boolean
	 * @author Vimal - RMITS
	 */
	public boolean verifyCreateSalesInvoiceWithPromotionalItems() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("PromotionalType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("PromotionalType"));
		return flag;
	}

	/**
	 * This Method Used For Clicking Pending CreditNote Icon in Pending Invoice
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingCreditNotePendingIcon() {
		flag = webDB.waitForElement(SalesInvoiceLocators.CreditNote_Pending_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CreditNote_Pending_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.CreditNote_Pending_Invoiced, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.CreditNote_Pending_Invoiced, ElementType.Xpath);
				webDB.switchToFrame(SalesInvoiceLocators.CR_Pending_Iframe, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.expandFindTable();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Pending_Invoiced_NO_LOV_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Credit Note Pending Invoiced Button Clicked Sucessfully");
					webDB.switchToDefaultContent();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Pending CreditNote Icon in Pending GateEntry
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingCreditNoteGateEntryPendingIcon() {
		flag = webDB.waitForElement(SalesInvoiceLocators.CreditNote_Pending_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CreditNote_Pending_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_GateEntry_Pend, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.CR_GateEntry_Pend, ElementType.Xpath);
				webDB.switchToFrame(SalesInvoiceLocators.CR_GE_Iframe, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.expandFindTable();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_GE_Doc_No, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Credit Note Pending GateEntry Button Clicked Sucessfully");
					webDB.switchToDefaultContent();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Sales Invoice Document Inserted And Document Select and
	 * Created From Pending.
	 * 
	 * @author Vimal - RMITS
	 * @param CreditNote_Inv_No
	 */
	public void InsertingInvoicedNoSelectAndCreate(String CreditNote_Inv_No) {
		webDB.switchToFrame(SalesInvoiceLocators.CR_Pending_Iframe, ElementType.Xpath);
		logs.logging("INFO", "Switched to Frame successfully");
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Pending_Invoiced_NO_LOV_Btn,
				CreditNote_Inv_No);
		// webDB.switchToDefaultContent();
		commonFunc.hardWait(2000);
		webDB.switchToFrame(SalesInvoiceLocators.CR_Pending_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_Pend_Search_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CR_Pend_Search_Btn, ElementType.Xpath);
			commonFunc.hardWait(2000);
			webDB.moveToElement(SalesInvoiceLocators.CR_Pend_SelectAll, ElementType.Xpath);
			webDB.clickAnElement(SalesInvoiceLocators.CR_Pend_SelectAll, ElementType.Xpath);
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_Create_SalesReturn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.CR_Create_SalesReturn, ElementType.Xpath);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Pend_Ok_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.CR_Pend_Ok_Btn, ElementType.Xpath);
					webDB.switchToDefaultContent();
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(SalesInvoiceLocators.CR_Close_Toast_Msg, ElementType.Xpath);
					String CRdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Doc_No, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					VariableHandler.setCRdocno(CRdocno);
					logs.logging("INFO", "Get CR DocNo Is: " + VariableHandler.getCRdocno());
					if (flag) {
						logs.logging("INFO",
								"Sales Invoice Document Inserted And Document Select and Created From Pending Sucessfully");
						commonFunc.checkRefreshCellsDisappear();
					}
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Used For Pending Credit Note GateEntry Inserted And Document
	 * Select and Created From Pending.
	 * 
	 * @author Vimal - RMITS
	 * @param CreditNote_Inv_No
	 */
	public void PendingGateEntryInsertingInvoicedNoSelectAndCreate(String GEdocno) {
		webDB.switchToFrame(SalesInvoiceLocators.CR_GE_Iframe, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_GE_Doc_No, GEdocno);
		commonFunc.hardWait(2000);
		webDB.switchToFrame(SalesInvoiceLocators.CR_GE_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_GE_Pend_Search, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CR_GE_Pend_Search, ElementType.Xpath);
			commonFunc.hardWait(2000);
			webDB.moveToElement(SalesInvoiceLocators.CR_GE_Pend_SelectAll, ElementType.Xpath);
			webDB.clickAnElement(SalesInvoiceLocators.CR_GE_Pend_SelectAll, ElementType.Xpath);
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_GE_CreateReturn_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.CR_GE_CreateReturn_Btn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_GE_ToastMsg, ElementType.Xpath);
				String CRdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Doc_No, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				VariableHandler.setCRdocno(CRdocno);
				logs.logging("INFO", "Get CR DocNo Is: " + VariableHandler.getCRdocno());
				if (flag) {
					logs.logging("INFO",
							"Sales Invoice Document Inserted And Document Select and Created From Pending Sucessfully");
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting CNPrefix CreditNoteReason CreInvoiceNo
	 * UpdNarration CreateJour And Post
	 * 
	 * @author Vimal - RMITS
	 * @param CR_CN_Prefix
	 * @param CR_Return_Reason
	 */
	public void CNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(String CR_CN_Prefix,
			String CR_Return_Reason) {
//		flag = webDB.waitForElement(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, CR_CN_Prefix);
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_ShowMore, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CR_ShowMore, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_Reason_Lov_Btn, ElementType.Xpath);
			if (flag) {
//				webDB.clickAnElement(SalesInvoiceLocators.CR_Reason_Lov_Btn, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Reason_Lov_Btn, CR_Return_Reason);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					checkCreditNoteHeaderDetailsDataBaseValuesWithUIValues();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO",
								"CreditNote Prefix , Return Reason Inserted and Save Icon Clicked Sucessfully");
						flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							String CNdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_CN_Doc_No,
									ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
							VariableHandler.setCNdocno(CNdocno);
							logs.logging("INFO", "Get CN DocNo Is: " + VariableHandler.getCNdocno());
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CreditNote Cre.Inv No Clicked Sucessfully");
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
									commonFunc.checkRefreshCellsDisappear();
									flag = webDB.waitForElement(SalesInvoiceLocators.CR_Create_Journal_Btn,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(SalesInvoiceLocators.CR_Create_Journal_Btn,
												ElementType.Xpath);
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(1000);
										if (webDB.verifyIsElementDisplayed(
												SalesInvoiceLocators.CR_Linewise_Save_ToastMsg, ElementType.Xpath)) {
											webDB.clickAnElementWithoutException(
													SalesInvoiceLocators.CR_Linewise_Save_ToastMsg, ElementType.Xpath);
											commonFunc.hardWait(2000);
											flag = webDB.waitForElement(SalesInvoiceLocators.CR_Post_Btn,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO",
														"CreditNote Update Narration , CRe. Journal Clicked Sucessfully");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For GateEntry CNPrefix CreditNoteReason CreInvoiceNo
	 * UpdNarration CreateJour And Post
	 * 
	 * @author Vimal - RMITS
	 * @param CR_CN_Prefix
	 * @param CR_Return_Reason
	 */
	public void GateEntryCNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(String CR_CN_GE_Pfx,
			String CR_Return_Reason) {
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, CR_CN_GE_Pfx);
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_ShowMore, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CR_ShowMore, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_Reason_Lov_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Reason_Lov_Btn, CR_Return_Reason);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					checkCreditNoteHeaderDetailsDataBaseValuesWithUIValues();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO",
								"CreditNote Prefix , Return Reason Inserted and Save Icon Clicked Sucessfully");
						flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							String CNdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_CN_Doc_No,
									ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
							VariableHandler.setCNdocno(CNdocno);
							logs.logging("INFO", "Get CN DocNo Is: " + VariableHandler.getCNdocno());
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CreditNote Cre.Inv No Clicked Sucessfully");
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
									commonFunc.checkRefreshCellsDisappear();
									flag = webDB.waitForElement(SalesInvoiceLocators.CR_Create_Journal_Btn,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(SalesInvoiceLocators.CR_Create_Journal_Btn,
												ElementType.Xpath);
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(1000);
										if (webDB.verifyIsElementDisplayed(
												SalesInvoiceLocators.CR_Linewise_Save_ToastMsg, ElementType.Xpath)) {
											webDB.clickAnElementWithoutException(
													SalesInvoiceLocators.CR_Linewise_Save_ToastMsg, ElementType.Xpath);
											commonFunc.hardWait(2000);
											flag = webDB.waitForElement(SalesInvoiceLocators.CR_Post_Btn,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO",
														"CreditNote Update Narration , CRe. Journal Clicked Sucessfully");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Post Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void CreditNoteclickPostButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_Post_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(2000);
			webDB.clickAnElement(SalesInvoiceLocators.CR_Post_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void creditnoteforwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesInvoiceLocators.CreditNote_Pending_Icon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To CreditNote Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	public void verifyAssertionGetTextValidation(String Locution, String ExpectedDBValue) {
		flag = webDB.waitForElement(Locution, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(Locution, ElementType.Xpath);
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	public void verifyAssertionDomPropertyValidation(String UIFieldLocator, String ExpectedDBValue) {
		flag = webDB.waitForElement(UIFieldLocator, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(UIFieldLocator, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	public void verfityAssertionBothValues(String Actual, String Expected) {
		logs.logging("Info", "Actual Value Is " + Actual);
		logs.logging("Info", "Expected Value Is " + Expected);
		flag = Actual.contains(Expected);
		if (flag) {
			logs.logging("Info", "Verified Actual And Expected Values");
		} else {
			logs.logging("INFO", "Mismatch With Actual And Expected");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Verify Sales Invoice Document Status
	 * 
	 * @param SIdocno
	 * @param Exp_Status
	 */
	public void verifySalesInvoiceDocumentStatus(String SIdocno) {
		flag = webDB.waitForElement(SalesInvoiceLocators.FindSalesInvoiceNoLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.FindSalesInvoiceNoLovBtn, SIdocno);
			flag = webDB.waitForElement(SalesInvoiceLocators.FindSalesInvoiceSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.FindSalesInvoiceSearchBtn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_DocNo_DrillDown, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.SI_DocNo_DrillDown, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(SalesInvoiceLocators.SI_Reverse_Btn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.SI_Reverse_Btn, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						webDB.switchToFrame(SalesInvoiceLocators.SI_Reverse_Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(SalesInvoiceLocators.SI_Reverse_OK_Btn, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.SI_Reverse_OK_Btn, ElementType.Xpath);
							webDB.switchToDefaultContent();
							commonFunc.checkRefreshCellsDisappear();
							forwardForApprovalInSalesInvoiceReverse();
							commonFunc.checkRefreshCellsDisappear();
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void forwardForApprovalInSalesInvoiceReverse() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.SI_Reverse_Iframe, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.SI_Reverse_Iframe, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.SI_Reverse_Iframe, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Reverse_ForwardApproval, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_Reverse_ForwardApproval, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesInvoiceLocators.Header_Tick_Icon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To SI Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Reverse The Invoiced Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean reverseTheInvoicedSalesInvoice() {
		verifyCreateSalesInvoiceDomesticLocalCustomer();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifySalesInvoiceDocumentStatus(VariableHandler.getSIdocno());
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Used For check Credit Note Header Details DataBase Values With
	 * UIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkCreditNoteHeaderDetailsDataBaseValuesWithUIValues() {
		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Header_LocID, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
						commonFunc.getDataFromTestDataSheet("CRDBLocationColumn")));
		logs.logging("INFO", "Header Detail LocationID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Header_Unit, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
						commonFunc.getDataFromTestDataSheet("CRDBUnitColumn")));
		logs.logging("INFO", "Header Detail Unit Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Header_Doc_Pfx, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
						commonFunc.getDataFromTestDataSheet("CRDBDocPfxColumn")));
		logs.logging("INFO", "Header Detail Doc Pfx Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Header_Doc_No, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
						commonFunc.getDataFromTestDataSheet("CRDBDocNoColumn")));
		logs.logging("INFO", "Header Detail DocNo Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_CustID, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
						commonFunc.getDataFromTestDataSheet("CRDBCustIDColumn")));
		logs.logging("INFO", "Header Detail CustomerID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Cust_Name, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
						commonFunc.getDataFromTestDataSheet("CRDBCustNameColumn")));
		logs.logging("INFO", "Header Detail CustomerName Matched With DataBase Values");

//		salesOrderFunctions.verifyAssertionBothValues(
//				webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_GoodsType, ElementType.Xpath,
//						commonFunc.getDataFromTestDataSheet("Value")),
//				dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
//						commonFunc.getDataFromTestDataSheet("CRDBGoodsTypeColumn")));
//		logs.logging("INFO", "Header Detail Goods Type Matched With DataBase Values");

		checkCreditNoteDraftStatusDetailsDataBaseValuesWithUIValues();
	}

	/**
	 * This Method Used For Check CreditNote Draft Status Details DataBase Values
	 * WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkCreditNoteDraftStatusDetailsDataBaseValuesWithUIValues() {
		String CRDocumentStatus = dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
				commonFunc.getDataFromTestDataSheet("CRDBStatusColumn"));
		salesOrderFunctions.verifyAssertionBothValues(CRDocumentStatus,
				commonFunc.getDataFromTestDataSheet("CRHeaderDraftStatus"));
		logs.logging("INFO", "Verified CreditNote Document Status Is " + CRDocumentStatus
				+ " Against This CreditNote Number " + VariableHandler.getCRdocno());
	}

	/**
	 * This Method Used For create direct Sales return WO Reference - Domestic
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDirectSalesReturnWithOutReferenceDomestic() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToCreditNoteScreen();
		creditNoteAddIcon();
		creditNoteAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CR_Customer"),
				commonFunc.getDataFromTestDataSheet("CR_Good_Type_Text"));
		creatingCreditNoteHeader(commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		checkCreditNoteHeaderDetailsDataBaseValuesWithUIValues();
		AddingCreditNoteLineINExcelSheet("CRNoteDomestic");
		clickingCreInvoiceNo_UpdNarration_CreJournal();
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Create SalesReturn with Reference from Pending Sales
	 * Return Domestic
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesReturnwithReferencefromPendingSalesReturnDomestic() {
		verifyCreateSalesInvoiceDomesticLocalCustomer();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNotePendingIcon();
		InsertingInvoicedNoSelectAndCreate(VariableHandler.getSIInvNo());
		CNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(
				commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		mrvFunc.verifyCreditNotePostMaterialReceipt();
		salesOrderFunctions.verifyAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(
				webDB.getDataFromProperties("LocalCustomer"));
		return flag;
	}

	/**
	 * This Method Used For Create SalesReturn with Goods from Pending SalesReturn
	 * Export
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesReturnwithGoodsfromPendingSalesReturnExport() {
		verifyDirectSalesInvoiceForCreditNoteInvoiceExport();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNotePendingIcon();
		InsertingInvoicedNoSelectAndCreate(VariableHandler.getSIInvNo());
		CNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(
				commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		mrvFunc.verifyCreditNotePostMaterialReceipt();
		salesOrderFunctions
				.verifyAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportCRNote"));
		return flag;
	}

	/**
	 * create direct Export Sales Invoice For Credit Note for the customer - price
	 * basis user defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectSalesInvoiceForCreditNoteInvoiceExport() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("ExportCRNote"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportCRNote"));
		return flag;
	}

	/**
	 * This Method Used For Create SalesReturn without Goods from Pending
	 * SalesReturn Export
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesReturnwithoutGoodsfromPendingSalesReturnExport() {
		verifyCreditNoteForServiceExport();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNotePendingIcon();
		InsertingInvoicedNoSelectAndCreate(VariableHandler.getSIInvNo());
		CNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(
				commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * create direct Export Sales Invoice for the customer - price basis user
	 * defined- Similar Stock and Customer UOM
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyCreditNoteForServiceExport() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		AddingUserDefinedServiceTypeItemLineFromReadingExcelSheet(webDB.getDataFromProperties("CRNoteExportService"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For create direct Sales return WO Reference - Export
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDirectSalesReturnWithOutReferenceExport() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToCreditNoteScreen();
		creditNoteAddIcon();
		ExportcreditNoteAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"),
				commonFunc.getDataFromTestDataSheet("CR_Good_Type_Text"));
		creatingCreditNoteHeader(commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		checkCreditNoteHeaderDetailsDataBaseValuesWithUIValues();
		AddingCreditNoteLineINExcelSheet("CRNoteExport");
		clickingCreInvoiceNo_UpdNarration_CreJournal();
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param CustomerName
	 * @param SIType
	 */
	public void ExportcreditNoteAddingParameters(String LocationId, String Export_Customer_Name,
			String CR_Good_Type_Text) {
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_Loc_LOV_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Loc_LOV_Btn, LocationId);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_Customer_Lov_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Customer_Lov_Btn,
						Export_Customer_Name);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Goods_Type, ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownByText(SalesInvoiceLocators.CR_Goods_Type, CR_Good_Type_Text,
							ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.CR_Parameter_Inserting_Tick_Icon,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.CR_Parameter_Inserting_Tick_Icon, ElementType.Xpath);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SalesInvoiceLocators.CR_Close_Toast_Msg, ElementType.Xpath);
						String CRdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Doc_No,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setCRdocno(CRdocno);
						logs.logging("INFO", "Get CR DocNo Is: " + VariableHandler.getCRdocno());
						if (flag) {
							logs.logging("INFO",
									"Credit Note Location, Customer, Goods Type Parameters Inserted Successfully");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Create SalesReturn with Reference from Pending Sales
	 * Return Export
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesReturnwithReferencefromPendingSalesReturnExport() {
		verifyDirectSalesInvoiceForCreditNoteInvoiceExport();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNotePendingIcon();
		InsertingInvoicedNoSelectAndCreate(VariableHandler.getSIInvNo());
		CNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(
				commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		mrvFunc.verifyCreditNotePostMaterialReceipt();
		salesOrderFunctions
				.verifyAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("ExportCRNote"));
		return flag;
	}

	/**
	 * This Method Used For Clicking Find Screen Add Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void creditNoteAddIcon() {
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_Find_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CR_Find_Add_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_Loc_LOV_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Credit Note Add Icon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param CustomerName
	 * @param SIType
	 */
	public void creditNoteAddingParameters(String LocationId, String CR_Customer, String CR_Good_Type_Text) {
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_Loc_LOV_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Loc_LOV_Btn, LocationId);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_Customer_Lov_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Customer_Lov_Btn, CR_Customer);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Goods_Type, ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownByText(SalesInvoiceLocators.CR_Goods_Type, CR_Good_Type_Text,
							ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.CR_Parameter_Inserting_Tick_Icon,
							ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(2000);
						webDB.clickAnElement(SalesInvoiceLocators.CR_Parameter_Inserting_Tick_Icon, ElementType.Xpath);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SalesInvoiceLocators.CR_Close_Toast_Msg, ElementType.Xpath);
						String CRdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Doc_No,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setCRdocno(CRdocno);
						logs.logging("INFO", "Get CR DocNo Is: " + VariableHandler.getCRdocno());
						if (flag) {
							logs.logging("INFO",
									"Credit Note Location, Customer, Goods Type Parameters Inserted Successfully");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting CNPrefix CreditNoteReason
	 * 
	 * 
	 * @author Vimal - RMITS
	 * @param CR_CN_Prefix
	 * @param CR_Return_Reason
	 */
	public void creatingCreditNoteHeader(String CR_CN_Prefix, String CR_Return_Reason) {
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, CR_CN_Prefix);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_ShowMore, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.CR_ShowMore, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Reason_Lov_Btn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Reason_Lov_Btn, CR_Return_Reason);
					flag = webDB.waitForElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
						String CRdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.CR_Doc_No,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setCRdocno(CRdocno);
						logs.logging("INFO", "Get CR DocNo Is: " + VariableHandler.getCRdocno());
						flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"CreditNote Prefix , Return Reason Inserted and Save Icon Clicked Sucessfully");
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding CreditNote Line IN ExcelSheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddingCreditNoteLineINExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			// salesOrderFunctions.StockQtyUpdateInExcel(i, ArrayList, SheetName);
			String Item = itemMultiples[i][1];
			String GlAcct = itemMultiples[i][35];
			String CPC = itemMultiples[i][36];
			String InvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			if (!Item.equals("")) {
				creditNoteLineWiseAddIcon();
				verifyCreditNoteServiceItemID(Item);
				InsertingSalesGlAccount(GlAcct);
				verifyLinewiseCPCCode(CPC);
				verifyItemReturnQty(InvoiceQty, UnitPrice);
				InsertingLinewiseHSNcode(HSNcode_SI);
				checkCreditNoteLineDetailsDataBaseValuesWithUIValues(i, Filepath, itemMultiples);
			}
		}
	}

	/**
	 * This Method Used To Credit Note LineWise Add Icon Clicking
	 * 
	 * @author Vimal - RMITS
	 */
	public void creditNoteLineWiseAddIcon() {
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_Linewise_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CR_Linewise_Add_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_linewise_Type_Field, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Credit Note linewise Add Icon Clicked successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Sales GL Acct Desc
	 * 
	 * @author Vimal - RMITS
	 * @param SalesGL_Acct
	 */
	public void InsertingSalesGlAccount(String SalesGL_Acct) {
//		flag = webDB.waitForElement(SalesInvoiceLocators.CR_Gl_Acct_Column, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(SalesInvoiceLocators.CR_Gl_Acct_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_GL_Acct_LOV_Btn, SalesGL_Acct);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.CRPageCPCTable(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales GL Account Inserted Successfully");
			}
		assertTrue(flag);
	}

	/**
	 * Verify Header CPC Code
	 * 
	 * @author Vimal - RMITS
	 * @param CPC_Code
	 */
	public void verifyLinewiseCPCCode(String CPC_Code) {
		flag = webDB.waitForElement(SalesInvoiceLocators.CRPageCPCTable(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CRPageCPCTable(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_CPC_Lov_Btn, CPC_Code);
			flag = webDB.waitForElement(SalesInvoiceLocators.CRPageReturnQtyTable(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CPC Code Inserted Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Linewise HSN Code Inserting
	 * 
	 * @author Vimal - RMITS
	 * @param HSNcode_SI
	 */
	public void InsertingLinewiseHSNcode(String CR_HSN_Code) {
		flag = webDB.waitForElement(SalesInvoiceLocators.CRPageHSNTable(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CRPageHSNTable(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_HSN_Lov_Btn, CR_HSN_Code);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_LineWise_Save_Icon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.CR_LineWise_Save_Icon, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Linewise_Save_ToastMsg, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "LineWise Save Icon Clicked Successfully");
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For check Proforma Line Details DataBase Values With
	 * UIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkCreditNoteLineDetailsDataBaseValuesWithUIValues(int i1, String FilePath, String[][] ExcelName) {
		for (int i = i1; i <= i1; i++) {
			String Item = ExcelName[i][1];
			String Qty = ExcelName[i][6];
			String UnitCost = ExcelName[i][7];
			String HSNCode = ExcelName[i][21];
			String Tax = ExcelName[i][22];
			String IGST = ExcelName[i][25];
			String CGST = ExcelName[i][23];
			String SGST = ExcelName[i][24];

			salesOrderFunctions.verifyAssertionBothValues(Item, dbCommonFunctions.CRLineDetails(
					VariableHandler.getCRdocno(), commonFunc.getDataFromTestDataSheet("CRDBItemColumn")));
			logs.logging("INFO", "Item Matched With DataBase Values");

			salesOrderFunctions.verifyAssertionBothValues(Qty, dbCommonFunctions
					.CRLineDetails(VariableHandler.getCRdocno(), commonFunc.getDataFromTestDataSheet("CRDQtyColumn")));
			logs.logging("INFO", "Credit Note Qty Matched With DataBase Values");

			salesOrderFunctions.verifyAssertionBothValues(UnitCost, dbCommonFunctions.CRLineDetails(
					VariableHandler.getCRdocno(), commonFunc.getDataFromTestDataSheet("CRDBUnitPriceColumn")));
			logs.logging("INFO", "Unitcost Matched With DataBase Values");

			salesOrderFunctions.verifyAssertionBothValues(HSNCode, dbCommonFunctions
					.CRLineDetails(VariableHandler.getCRdocno(), commonFunc.getDataFromTestDataSheet("CRDBHSNColumn")));
			logs.logging("INFO", "Line Detail HSN Code Matched With DataBase Values");

//			salesOrderFunctions.verifyAssertionBothValues(IGST, dbCommonFunctions.CRLineDetails(
//					VariableHandler.getCRdocno(), commonFunc.getDataFromTestDataSheet("CRDBIGSTColumn")));
//			logs.logging("INFO", "Line Detail IGST Amount Matched With DataBase Values");
//
//			salesOrderFunctions.verifyAssertionBothValues(CGST, dbCommonFunctions.CRLineDetails(
//					VariableHandler.getCRdocno(), commonFunc.getDataFromTestDataSheet("CRDBCGSTColumn")));
//			logs.logging("INFO", "Line Detail CGST Amount Matched With DataBase Values");
//
//			salesOrderFunctions.verifyAssertionBothValues(SGST, dbCommonFunctions.CRLineDetails(
//					VariableHandler.getCRdocno(), commonFunc.getDataFromTestDataSheet("CRDBSGSTColumn")));
//			logs.logging("INFO", "Line Detail SGST Amount Matched With DataBase Values");
			commonFunc.hardWait(1000);
		}
	}

	/**
	 * This Method Used For Inserting Return Qty
	 * 
	 * @author Vimal - RMITS
	 * @param ReturnQty
	 * @param CR_UnitPrice
	 */
	public void verifyItemReturnQty(String ReturnQty, String CR_UnitPrice) {
		commonFunc.hardWait(1000);
		verifyAddTableSpecs(SalesInvoiceLocators.CRPageReturnQtyTable(), SalesInvoiceLocators.CR_Return_Qty, ReturnQty);
		webDB.pressTabKey();
		commonFunc.hardWait(2000);
		verifyAddTableSpecs(SalesInvoiceLocators.CRPageUnitPriceTable(), SalesInvoiceLocators.CR_UnitPrice,
				CR_UnitPrice);
	}

	/**
	 * This Method Used For Inserting Credit Note Item
	 * 
	 * @author Vimal - RMITS
	 * @param SI_Service_Item
	 */
	public void verifyCreditNoteServiceItemID(String SI_Service_Item) {
		flag = webDB.waitForElement(SalesInvoiceLocators.CRPageItemTable(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CRPageItemTable(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Item_LOV_Btn, SI_Service_Item);
			logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + SI_Service_Item);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			flag = webDB.waitForElement(SalesInvoiceLocators.CRPageGLAcctTable(), ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For clickingCreInvoiceNo_UpdNarration_CreJournal
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingCreInvoiceNo_UpdNarration_CreJournal() {
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "CreditNote Prefix , Return Reason Inserted and Save Icon Clicked Sucessfully");
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CreditNote Cre.Inv No Clicked Sucessfully");
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.CR_Upd_Narr_Btn, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(SalesInvoiceLocators.CR_Create_Journal_Btn, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.CR_Create_Journal_Btn, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.CR_Linewise_Save_ToastMsg,
									ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(SalesInvoiceLocators.CR_Linewise_Save_ToastMsg,
										ElementType.Xpath);
								commonFunc.hardWait(2000);
								flag = webDB.waitForElement(SalesInvoiceLocators.CR_Post_Btn, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO",
											"CreditNote Update Narration , CRe. Journal Clicked Sucessfully");
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Check CreditNote Invoiced Status Details DataBase Values
	 * WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues() {
		String CRDocumentStatus = dbCommonFunctions.CRHeaderDetails(VariableHandler.getCRdocno(),
				commonFunc.getDataFromTestDataSheet("CRDBStatusColumn"));
		salesOrderFunctions.verifyAssertionBothValues(CRDocumentStatus,
				commonFunc.getDataFromTestDataSheet("CRInvoicedStatus"));
		logs.logging("INFO", "Verified CreditNote Document Status Is " + CRDocumentStatus
				+ " Against This CreditNote Number " + VariableHandler.getCRdocno());
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For create Sales Return through gateEntry Domestic
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesReturnthroughgateEntryDomestic() {
		createSalesInvoiceDomesticForGateEntry();
		commonFunc.navigateTogateEntry();
		clickingPendingGateEntry();
		InsertingpPendingInvoiceNoAndCreateTheDocument(VariableHandler.getSIInvNo(),
				commonFunc.getDataFromTestDataSheet("DriverName"));
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNoteGateEntryPendingIcon();
		PendingGateEntryInsertingInvoicedNoSelectAndCreate(VariableHandler.getGEdocno());
		GateEntryCNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(
				commonFunc.getDataFromTestDataSheet("CR_CN_GE_Pfx"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		mrvFunc.verifyCreditNotePostMaterialReceipt();
		salesOrderFunctions.verifyAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(
				webDB.getDataFromProperties("CRNoteGateEntry"));
		return flag;
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Domestic Local Customer
	 * For Gate Entry
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesInvoiceDomesticForGateEntry() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Location49"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Cust_ID_GateEntry"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("Inv_Pfx_Loc49"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Loc49"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Cust_ID_GateEntry"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("CRNoteGateEntry"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("CRNoteGateEntry"));
		return flag;
	}

	/**
	 * This Method Used For Clicking Pending GateEntry
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingPendingGateEntry() {
		flag = webDB.waitForElement(SalesInvoiceLocators.GateEntryPendingIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.GateEntryPendingIcon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.GE_Pend_SalesReturn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.GE_Pend_SalesReturn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.GE_Pend_Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Gate Entry Pending sales Return Clicked Sucessfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used For Document Filtered SelectAll Button Clicked..Lot Serial
	 * Qty Inserted..Create GE button Clicked
	 * 
	 * @param SIInvNo
	 * @param DriverName
	 */
	public void InsertingpPendingInvoiceNoAndCreateTheDocument(String SIInvNo, String DriverName) {
		webDB.switchToFrame(SalesInvoiceLocators.GE_Pend_Iframe, ElementType.Xpath);
		commonFunc.hardWait(2000);
		commonFunc.expandFindTable();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.GE_SR_Pend_Inv_Lov_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.GE_SR_Pend_Inv_Lov_Btn, SIInvNo);
			webDB.switchToDefaultContent();
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.GE_Pend_Iframe, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.GE_SR_Search_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.GE_SR_Search_Btn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.GE_Pend_SelectAll, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.GE_Pend_SelectAll, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					String Curr_ProcQty = webDB.getTextFromElement(SalesInvoiceLocators.GE_Pend_Curr_ProcQty,
							ElementType.Xpath);
					logs.logging("INFO", "Get Gate Entry Proc Qty Is: " + Curr_ProcQty);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(SalesInvoiceLocators.GE_Pend_LS_Tab, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.GE_Pend_LS_Tab, ElementType.Xpath);
						commonFunc.hardWait(2000);
						webDB.switchToDefaultContent();
						webDB.switchToFrame(SalesInvoiceLocators.GE_LS_Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(SalesInvoiceLocators.GE_Pend_Curr_Qty_Column, ElementType.Xpath);
						if (flag) {
							webDB.doubleClickAnElement(SalesInvoiceLocators.GE_Pend_Curr_Qty_Column, ElementType.Xpath);
							commonFunc.hardWait(1000);
							webDB.sendTextToAnElement(SalesInvoiceLocators.GE_Pend_Entering_Curr_Qty, Curr_ProcQty,
									ElementType.Xpath);
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(SalesInvoiceLocators.GE_LS_Save_Icon, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SalesInvoiceLocators.GE_LS_Save_Icon, ElementType.Xpath);
								commonFunc.hardWait(2000);
								webDB.pressEscapeKey();
								webDB.switchToDefaultContent();
								webDB.switchToFrame(SalesInvoiceLocators.GE_Pend_Iframe, ElementType.Xpath);
								flag = webDB.waitForElement(SalesInvoiceLocators.Create_GE, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(SalesInvoiceLocators.Create_GE, ElementType.Xpath);
									webDB.switchToDefaultContent();
									commonFunc.checkRefreshCellsDisappear();
									commonFunc.hardWait(2000);
									flag = webDB.waitForClickElement(SalesInvoiceLocators.After_Pend_NoteAlert,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO",
												"Document Filtered SelectAll Button Clicked..Lot Serial Qty Inserted..Create GE button Clicked");
										String GEdocno = webDB.getTextFromElement(SalesInvoiceLocators.GE_Doc_No,
												ElementType.Xpath);
										logs.logging("INFO", "Get GE DocNo Is: " + GEdocno);
										VariableHandler.setGEdocno(GEdocno);
										commonFunc.hardWait(1000);
										flag = webDB.waitForElement(SalesInvoiceLocators.GE_Driver_Name,
												ElementType.Xpath);
										if (flag) {
											webDB.sendTextToAnElement(SalesInvoiceLocators.GE_Driver_Name, DriverName,
													ElementType.Xpath);
											commonFunc.hardWait(1000);
											flag = webDB.waitForElement(SalesInvoiceLocators.GE_Header_Save_Icon,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(SalesInvoiceLocators.GE_Header_Save_Icon,
														ElementType.Xpath);
												commonFunc.checkRefreshCellsDisappear();
												commonFunc.hardWait(3000);
												flag = webDB.waitForElement(SalesInvoiceLocators.GE_Header_Post_Btn,
														ElementType.Xpath);
												if (flag) {
													webDB.clickAnElement(SalesInvoiceLocators.GE_Header_Post_Btn,
															ElementType.Xpath);
													commonFunc.checkRefreshCellsDisappear();
													commonFunc.hardWait(1000);
													flag = webDB.waitForElement(
															SalesInvoiceLocators.GateEntryPendingIcon,
															ElementType.Xpath);
													if (flag) {
														logs.logging("INFO",
																"Driver Name Inserted and Documemt Posted Sucessfully");
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Create SalesReturn with Goods from Pending SalesReturn
	 * Domestic
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesReturnwithGoodsfromPendingSalesReturnDomestic() {
		verifyCreateSalesInvoiceDomesticLocalCustomer();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNotePendingIcon();
		InsertingInvoicedNoSelectAndCreate(VariableHandler.getSIInvNo());
		CNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(
				commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		mrvFunc.verifyCreditNotePostMaterialReceipt();
		salesOrderFunctions.verifyAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(
				webDB.getDataFromProperties("LocalCustomer"));
		return flag;
	}

	/**
	 * This Method Used For Create SalesReturn without Goods from Pending
	 * SalesReturn Domestic
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesReturnwithoutGoodsfromPendingSalesReturnDomestic() {
		verifyCreateServiceOfSalesInvoice();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNotePendingIcon();
		InsertingInvoicedNoSelectAndCreate(VariableHandler.getSIInvNo());
		CNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(
				commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For create Sales Return through gateEntry Export
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesReturnthroughgateEntryExport() {
		createSalesInvoiceExportForGateEntry();
		commonFunc.navigateTogateEntry();
		clickingPendingGateEntry();
		InsertingpPendingInvoiceNoAndCreateTheDocument(VariableHandler.getSIInvNo(),
				commonFunc.getDataFromTestDataSheet("DriverName"));
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNoteGateEntryPendingIcon();
		PendingGateEntryInsertingInvoicedNoSelectAndCreate(VariableHandler.getGEdocno());
		GateEntryCNPrefix_CreditNoteReason_CreInvoiceNo_UpdNarration_CreateJour_Post(
				commonFunc.getDataFromTestDataSheet("CR_CN_GE_Pfx"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		mrvFunc.verifyCreditNotePostMaterialReceipt();
		salesOrderFunctions.verifyAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(
				webDB.getDataFromProperties("CRNoteGateEntryExport"));
		return flag;
	}

	/**
	 * This Method is Used To verify Create Sales Invoice Export Customer For Gate
	 * Entry
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSalesInvoiceExportForGateEntry() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Location49"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("Loc49ExportCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("Inv_Pfx_Loc49"));
		verifyLUTNO(commonFunc.getDataFromTestDataSheet("LUT_No"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Loc49"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("Loc49ExportCustomer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("CRNoteGateEntryExport"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("CRNoteGateEntryExport"));
		return flag;
	}

	/**
	 * This method used for Create Sales Invoice With Charge Item
	 * 
	 * @author Vimal -RMITS
	 * @return boolean
	 */
	public boolean createSalesInvoiceWithChargeItem() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("Charges"));
		commonFunc.checkRefreshCellsDisappear();
		AddChargesInSalesInvoice(webDB.getDataFromProperties("Charges"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		clickIncludeCharge();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("Charges"));
		return flag;
	}

	/**
	 * This method used for adding charges multilpe line
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddChargesInSalesInvoice(String SheetName) {
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 10; i < SOMultipleline.length; i++) {
			String CHARGEITEM_ID = SOMultipleline[i][3];
			String CHARGE_ITEMBASIS = SOMultipleline[i][6];
			String Charges_Percentage = SOMultipleline[i][7];
			String Charges_Amount = SOMultipleline[i][8];
			String Charges_SAC = SOMultipleline[i][9];
			if (!CHARGEITEM_ID.equals("")) {
				navigateToChargeItem();
				addingChargeItemLine(CHARGE_ITEMBASIS);
				InsertingChargeItem(CHARGEITEM_ID);
				AddChargeItemPercentage(Charges_Percentage, Charges_Amount);
				InsertingSACcodeAndSaveIcon(Charges_SAC);
			}
		}
	}

	/**
	 * This Method Used For Adding Charge Item Line and selecting Item Basis
	 * 
	 * @author Vimal - RMITS
	 * @param Percentage_Basis
	 */
	public void addingChargeItemLine(String Percentage_Basis) {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charges_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Charges_Add_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charge_Basis_Column, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_Charge_Basis_Column, ElementType.Xpath);
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charge_Basis_Select_Cls, ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownByText(SalesInvoiceLocators.SI_Charge_Basis_Select_Cls, Percentage_Basis,
							ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charge_ItemColumn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Charge Item Add Icon Clicked, Item Basis Is Selected");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding Charge Item Line and selecting Item Basis
	 * 
	 * @author Vimal - RMITS
	 * @param Charge_Item
	 */
	public void InsertingChargeItem(String CHARGEITEM_ID) {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charge_ItemColumn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Charge_ItemColumn, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Charge_Item_Lov_Btn, CHARGEITEM_ID);
			commonFunc.hardWait(1000);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting SAC Code
	 * 
	 * @author Vimal - RMITS
	 * @param ChargeSAC
	 */
	public void InsertingSACcodeAndSaveIcon(String Charges_SAC) {
		webDB.pressTabKey();
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charge_SAC_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Charge_SAC_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Charge_SAC_Lov_Btn, Charges_SAC);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charge_Save_Icon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_Charge_Save_Icon, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_General_Tab, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Charge item insert and line saved");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Enter the Value in Both Percentage And Value Field
	 * Based On the Charge Item Basis (% or Value)
	 * 
	 * @author Vimal -RMITS
	 * @param Percentage
	 * @param Amount
	 */
	public void AddChargeItemPercentage(String Charges_Percentage, String Charges_Amount) {
		String ChargeBasis = getTextChargeItemBais();
		if (ChargeBasis.equals("%")) {
			salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesInvoiceLocators.SI_Charge_Percentage_Column,
					SalesInvoiceLocators.SI_Charge_Percentage_Input, Charges_Percentage);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		} else {
			salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesInvoiceLocators.SI_Charge_Amount_Column,
					SalesInvoiceLocators.SI_Charge_Amount_Input, Charges_Amount);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This method Is Used To Get the Text Of the Charge Item Basis
	 * 
	 * @author Vimal - RMITS
	 * @return String - ChargeItemBasis
	 */
	public String getTextChargeItemBais() {
		String ChargeItemBasis = webDB.getTextFromElement(SalesInvoiceLocators.SI_Charge_Basis_Column,
				ElementType.Xpath);
		logs.logging("INFO", "Get Text Is :" + ChargeItemBasis);
		return ChargeItemBasis;
	}

	/**
	 * This Method Used For Navigate to Charges
	 * 
	 * @author Vimal - RMITS
	 */
	public void navigateToChargeItem() {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charges_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Charges_Tab, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Charges_Add_Icon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigate To Charges Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used for Create SalesInvoice With PreDispatch Document
	 * 
	 * @author Vimal -RMITS
	 * @return boolean
	 */
	public boolean createSalesInvoiceWithPreDispatchDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"),
				commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("SI_Domestic_Local_Customer"));
		AddingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("LocalCustomer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		clickingPreDispatchButton();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
				"HariUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions
				.verifyExpectedStockQtyWithDBValuesAfterInvoice(webDB.getDataFromProperties("LocalCustomer"));
		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
				"HariUsername-Admin");
		commonFunc.navigateInspectionDocumentsPage();
		qcFunc.verifyPendingInspectionDocumentForSalesInvoice(VariableHandler.getSIdocno());
		qcFunc.clickTickAndPostIcon();
		flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("AshokValue"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getIDNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getIDNumber());
			flag = commonFunc.logoutUser();
		}
		return flag;
	}

	/**
	 * This Method Used For Clicking PreDisPatch button
	 * 
	 * @author Vimal -RMITS
	 */
	public void clickingPreDispatchButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.SI_Predispatch_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SI_Predispatch_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesInvoiceLocators.Cre_Inv_No, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pre - Dispatch Button Clicked Sucessfully");
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method is Used To Multiple Lines In Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean multipleLinesInSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"), commonFunc.getDataFromTestDataSheet("CustomerName"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		MultipleLineAddingUserDefinedItemLineFromReadingExcelSheet(
				webDB.getDataFromProperties("MultipleSalesInvoiceLine"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkSalesInvoicedStatusDetailsDataBaseValuesWithUIValues();
//		salesOrderFunctions.verifyExpectedStockQtyWithDBValuesAfterInvoice(
//				webDB.getDataFromProperties("MultipleSalesInvoiceLine"));
		return flag;
	}

	/**
	 * This Method Used For Multiple Line Adding UserDefined Item Line From Excel
	 * Sheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void MultipleLineAddingUserDefinedItemLineFromReadingExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			salesOrderFunctions.StockQtyUpdateInExcelSI(i, ArrayList, SheetName);
			String Item = itemMultiples[i][1];
			String SalesGL_Acct = itemMultiples[i][35];
			String InvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			String Promotional = itemMultiples[i][36];
			if (!Item.equals("")) {
				flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, Item);
						logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item);
						flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Sales_GL_Acc_Desc_LOV_Btn,
									SalesGL_Acct);
							logs.logging("INFO", "Sales GL Acct Desc Inserted Successfully");
							commonFunc.hardWait(1000);
							verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty(),
									SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
							commonFunc.checkRefreshCellsDisappear();
							verifyAddTableSpecs(SalesInvoiceLocators.UnitCost_Column(),
									SalesInvoiceLocators.UnitCost_Input, UnitPrice);
							commonFunc.checkRefreshCellsDisappear();
							webDB.pressTabKey();
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
								commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.HSN_Code_LOV_Btn,
										HSNcode_SI);
								logs.logging("INFO", "HSN Code Inserted Successfully");
								commonFunc.hardWait(2000);
							}
						}
					}
				}
				flag = webDB.waitForElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Headerwise Tick Icon Displayed Successfully");
					flag = webDB.clickAnElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Headerwise Tick Icon Clicked Successfully");
					}
				}
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				// SalesInvoiceLineDetailsVerificationWithDBvalidation(i, Filepath,
				// itemMultiples);
				// commonFunc.hardWait(2000);
			}
		}
		// VariableHandler.setExpectedStockQty2(ArrayList);
	}

	/**
	 * This Method Used For Sales Invoice Line Details Verification With
	 * DBvalidation
	 * 
	 * @author Vimal - RMITS
	 */
	public void SalesInvoiceLineDetailsVerificationWithDBvalidation(int i1, String FilePath, String[][] ExcelName) {
		for (int i = i1; i <= i1; i++) {
			String StoreID = ExcelName[i][34];
			String LineTaxAmt = ExcelName[i][26];
			String GrossAmt = ExcelName[i][8];
			String NetAmt = ExcelName[i][27];
			salesOrderFunctions.verifyAssertionBothValues(StoreID, dbCommonFunctions.SILineDetails(
					VariableHandler.getSIdocno(), commonFunc.getDataFromTestDataSheet("SIDBStoreIDColumn")));
			logs.logging("INFO", "StoreID Matched With DataBase Values");
			commonFunc.hardWait(2000);
			salesOrderFunctions.verifyAssertionBothValues(LineTaxAmt, dbCommonFunctions.SILineDetails(
					VariableHandler.getSIdocno(), commonFunc.getDataFromTestDataSheet("SIDBTaxAmtColumn")));
			logs.logging("INFO", "LineTaxAmt Matched With DataBase Values");
			commonFunc.hardWait(2000);
			salesOrderFunctions.verifyAssertionBothValues(GrossAmt, dbCommonFunctions.SILineDetails(
					VariableHandler.getSIdocno(), commonFunc.getDataFromTestDataSheet("SIDBGrossAmtColumn")));
			logs.logging("INFO", "GrossAmt Matched With DataBase Values");
			commonFunc.hardWait(2000);
			salesOrderFunctions.verifyAssertionBothValues(NetAmt, dbCommonFunctions.SILineDetails(
					VariableHandler.getSIdocno(), commonFunc.getDataFromTestDataSheet("SIDBNetAmtColumn")));
			logs.logging("INFO", "NetAmt Matched With DataBase Values");
			commonFunc.hardWait(2000);
		}
	}

	/**
	 * This Method Used For clicking Rework Order Pending
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingReworkOrderPending() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Rework_Pending_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Rework_Pending_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Rework_Pend_SalesRtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Icon Clicked Successfully");
				flag = webDB.waitForElement(SalesInvoiceLocators.Rework_Pend_SalesRtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.Rework_Pend_SalesRtn, ElementType.Xpath);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(SalesInvoiceLocators.Rework_Pend_Iframe, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Rework Sales Return Pending Icon Clicked Successfully");
					}
				}
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Invoice No and Create Rework salesRtn
	 * 
	 * @author Vimal - RMITS
	 */
	public void InsertingInvoiceNoandCreateReworksalesRtn(String CNdocno) {
		webDB.switchToFrame(SalesInvoiceLocators.Rework_Pend_Iframe, ElementType.Xpath);
		commonFunc.hardWait(2000);
		commonFunc.expandFindTable();
		commonFunc.hardWait(2000);
//		flag = webDB.waitForElement(SalesInvoiceLocators.RW_Pend_Inv_Lov_Btn, ElementType.Xpath);
//		if (flag) {
//			webDB.javaScriptClickAnElement(SalesInvoiceLocators.RW_Pend_Inv_Lov_Btn, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.RW_Pend_Inv_Lov_Btn, CNdocno);
		commonFunc.hardWait(2000);
		webDB.switchToFrame(SalesInvoiceLocators.Rework_Pend_Iframe, ElementType.Xpath);
		flag = webDB.waitForElement(SalesInvoiceLocators.RW_Pend_Search_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.RW_Pend_Search_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(4000);
			String RtnQty = webDB.getTextFromElement(SalesInvoiceLocators.RW_Get_Rej_Qty, ElementType.Xpath);
			logs.logging("INFO", "Get Reject Qty Is: " + RtnQty);
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(SalesInvoiceLocators.Rw_Rej_Qty, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(SalesInvoiceLocators.Rw_Rej_Qty, ElementType.Xpath);
				webDB.sendTextToAnElement(SalesInvoiceLocators.RW_Rej_Qty_input, RtnQty, ElementType.Xpath);
				commonFunc.hardWait(3000);
				webDB.moveToElement(SalesInvoiceLocators.RW_Pend_CheckBox, ElementType.Xpath);
				webDB.javaScriptClickAnElement(SalesInvoiceLocators.RW_Pend_CheckBox, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.RW_Pend_CreateRework_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.RW_Pend_CreateRework_Btn, ElementType.Xpath);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(SalesInvoiceLocators.RW_Create_OK_Btn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.RW_Create_OK_Btn, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(2000);
						String RWdocno = webDB.getTextFromElement(SalesInvoiceLocators.RW_Doc_No, ElementType.Xpath);
						logs.logging("INFO", "Get RW DocNo Is: " + RWdocno);
						VariableHandler.setRWdocno(RWdocno);
						commonFunc.hardWait(3000);
						checkReworkOrderHeaderDetailsDataBaseValuesWithUIValues();
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(SalesInvoiceLocators.RW_Post_btn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Pending Sales Return Rework Order Document Created");
						}
					}
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Rework Order Post Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingReworkOrderPostButton() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.RW_Post_btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.RW_Post_btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesInvoiceLocators.RWWorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For create Repair Rework order for Sales return rejection
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createRepairReworkorderforSalesreturnrejection() {
		verifyCreateSalesInvoiceDomesticLocalCustomer();
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNotePendingIcon();
		InsertingInvoicedNoSelectAndCreate(VariableHandler.getSIInvNo());
		InsertingRejectedQtyCNPrefixCreditNoteReasonCreInvoiceNoUpdNarration(
				commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"),
				commonFunc.getDataFromTestDataSheet("CR_Acpt_Qty"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		mrvFunc.verifyCreditNotePostMaterialReceipt();
		commonFunc.navigateToReworkOrder();
		clickingReworkOrderPending();
		InsertingInvoiceNoandCreateReworksalesRtn(VariableHandler.getCNdocno());
		ClickingReworkOrderPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRWdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.navigateToReworkOrderCompletion();
		ClickingReworkOrderCompletionPendingIcon();
		InsertingRWCompletionDocNoandCreateTheRecord(VariableHandler.getRWdocno());
		InsertingResourceUsage(VariableHandler.getRWdocno(), commonFunc.getDataFromTestDataSheet("WorkCenter"),
				commonFunc.getDataFromTestDataSheet("Process"), commonFunc.getDataFromTestDataSheet("Resource"));
//		InsertingLocatorItem(commonFunc.getDataFromTestDataSheet("ReworkLocator"));
		ClickingReworkOrderCompletionPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRWCdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		checkReworkCompletionPostedStatusDetailsDataBaseValuesWithUIValues();
//		commonFunc.checkRefreshCellsDisappear();
		mrvFunc.verifyReworkCompletionPostMaterialReceipt();
//		checkReworkCompletionApprovedStatusDetailsDataBaseValuesWithUIValues();
		salesOrderFunctions.verifyAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(
				webDB.getDataFromProperties("LocalCustomer"));
		return flag;
	}

	/**
	 * Verify Rework Order forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void ReworkOrderforwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.RWWorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.RWWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.RWWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SalesInvoiceLocators.RW_Forward_OK_btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.RW_Forward_OK_btn, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesInvoiceLocators.Rework_Pending_Icon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To RW Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Rework Order Completion Pending Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingReworkOrderCompletionPendingIcon() {
		flag = webDB.waitForElement(SalesInvoiceLocators.ReworkOrd_Completion_Pend_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.ReworkOrd_Completion_Pend_Icon, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Pend_Iframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Rework Order Completion Pending Icon Clicked Sucessfully");
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting RW Completion DocNo and Create The Record
	 * 
	 * @author Vimal - RMITS
	 */
	public void InsertingRWCompletionDocNoandCreateTheRecord(String RWdocno) {
		webDB.switchToFrame(SalesInvoiceLocators.RWC_Pend_Iframe, ElementType.Xpath);
		commonFunc.hardWait(2000);
		commonFunc.expandFindTable();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Doc_No_Lov_Btn, ElementType.Xpath);
		if (flag) {
//			webDB.clickAnElement(SalesInvoiceLocators.RWC_Doc_No_Lov_Btn, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.RWC_Doc_No_Lov_Btn, RWdocno);
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.RWC_Pend_Iframe, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Pend_Search_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.RWC_Pend_Search_Btn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				String RWCReworkQty = webDB.getTextFromElement(SalesInvoiceLocators.RWC_Pend_Rework_Column,
						ElementType.Xpath);
				logs.logging("INFO", "Get Reject Qty Is: " + RWCReworkQty);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Pend_Repair_Column, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(SalesInvoiceLocators.RWC_Pend_Repair_Column, ElementType.Xpath);
					webDB.sendTextToAnElement(SalesInvoiceLocators.RWC_Pend_Repair_input, RWCReworkQty,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					webDB.moveToElement(SalesInvoiceLocators.RWC_Pend_CheckBox, ElementType.Xpath);
					webDB.javaScriptClickAnElement(SalesInvoiceLocators.RWC_Pend_CheckBox, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Pend_Save_Icon, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.RWC_Pend_Save_Icon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Create_Completion_Btn, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.RWC_Create_Completion_Btn, ElementType.Xpath);
							commonFunc.hardWait(2000);
							flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Create_OK_Btn, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SalesInvoiceLocators.RWC_Create_OK_Btn, ElementType.Xpath);
								commonFunc.checkRefreshCellsDisappear();
								webDB.switchToDefaultContent();
								commonFunc.hardWait(1000);
								flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Close_Toast_Msg,
										ElementType.Xpath);
								if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.RWC_Close_Toast_Msg,
										ElementType.Xpath)) {
									webDB.clickAnElementWithoutException(SalesInvoiceLocators.RWC_Close_Toast_Msg,
											ElementType.Xpath);
									commonFunc.hardWait(2000);
									String RWCPdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.RWC_Doc_No,
											ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
									logs.logging("INFO", "Get RWC DocNo Is: " + RWCPdocno);
									VariableHandler.setRWCdocno(RWCPdocno);
									commonFunc.hardWait(2000);
									checkReworkOrderCompletionHeaderDetailsDataBaseValuesWithUIValues();
									commonFunc.hardWait(2000);
									flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Post_Btn, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Rework Order Completion Record Created From Pending");
									}
								}
							}

						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting RW Completion Scrap DocNo and Create The
	 * Record
	 * 
	 * @author Vimal - RMITS
	 */
	public void InsertingRWCompletionDocNoandScrapCreateTheRecord(String RWdocno) {
		webDB.switchToFrame(SalesInvoiceLocators.RWC_Pend_Iframe, ElementType.Xpath);
		commonFunc.hardWait(2000);
		commonFunc.expandFindTable();
		commonFunc.hardWait(2000);
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.RWC_Doc_No_Lov_Btn, RWdocno);
		commonFunc.hardWait(2000);
		webDB.switchToFrame(SalesInvoiceLocators.RWC_Pend_Iframe, ElementType.Xpath);
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Pend_Search_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.RWC_Pend_Search_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			String RWCReworkQty = webDB.getTextFromElement(SalesInvoiceLocators.RWC_Pend_Rework_Column,
					ElementType.Xpath);
			logs.logging("INFO", "Get Reject Qty Is: " + RWCReworkQty);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Scrap_Qty_Column, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(SalesInvoiceLocators.RWC_Scrap_Qty_Column, ElementType.Xpath);
				webDB.sendTextToAnElement(SalesInvoiceLocators.RWC_Scrap_Qty_Input, RWCReworkQty, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.moveToElement(SalesInvoiceLocators.RWC_Pend_CheckBox, ElementType.Xpath);
				webDB.javaScriptClickAnElement(SalesInvoiceLocators.RWC_Pend_CheckBox, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Pend_Save_Icon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.RWC_Pend_Save_Icon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Create_Completion_Btn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.RWC_Create_Completion_Btn, ElementType.Xpath);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Create_OK_Btn, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.RWC_Create_OK_Btn, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							webDB.switchToDefaultContent();
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Close_Toast_Msg, ElementType.Xpath);
							if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.RWC_Close_Toast_Msg,
									ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(SalesInvoiceLocators.RWC_Close_Toast_Msg,
										ElementType.Xpath);
								commonFunc.hardWait(2000);
								String RWCPdocno = webDB.getDomPropertyFromElement(SalesInvoiceLocators.RWC_Doc_No,
										ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
								logs.logging("INFO", "Get RWC DocNo Is: " + RWCPdocno);
								VariableHandler.setRWCdocno(RWCPdocno);
								commonFunc.hardWait(2000);
								checkReworkOrderCompletionHeaderDetailsDataBaseValuesWithUIValues();
								commonFunc.hardWait(2000);
								flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Post_Btn, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Rework Order Completion Record Created From Pending");
								}
							}
						}

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Resource Usage
	 * 
	 * @author Vimal - RMITS
	 */
	public void InsertingResourceUsage(String RWdocno, String WorkCenter, String Process, String Resource) {
		flag = webDB.waitForElement(SalesInvoiceLocators.ResourceUsageTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.ResourceUsageTab, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.ResourceUsageAdd_Icon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.ResourceUsageAdd_Icon, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.pressTabKey();
				flag = webDB.waitForElement(SalesInvoiceLocators.ResourceUsage_RW_Seq_No, ElementType.Xpath);
				if (flag) {
					// webDB.clickAnElement(SalesInvoiceLocators.ResourceUsage_RW_Seq_No,
					// ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ResourceUsage_RW_Seq_No_Lov, RWdocno);
					webDB.pressTabKey();
					commonFunc.hardWait(2000);
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.WorkCenter_Lov_btn, WorkCenter);
					webDB.pressTabKey();
					commonFunc.hardWait(2000);
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Proc_Lov_Btn, Process);
					webDB.pressTabKey();
					commonFunc.hardWait(2000);
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Resource_Lov_Btn, Resource);
					commonFunc.hardWait(2000);
					webDB.pressTabKey();
					flag = webDB.waitForElement(SalesInvoiceLocators.RWC_FromDate_Today, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.RWC_FromDate_Today, ElementType.Xpath);
						commonFunc.hardWait(2000);
						webDB.clickAnElement(SalesInvoiceLocators.RWC_FromDate_Done, ElementType.Xpath);
						commonFunc.hardWait(2000);
						webDB.pressTabKey();
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SalesInvoiceLocators.RWC_ToDate_Next, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.RWC_ToDate_Next, ElementType.Xpath);
							commonFunc.hardWait(2000);
							webDB.clickAnElement(SalesInvoiceLocators.RWC_ToDate_Done, ElementType.Xpath);
							commonFunc.hardWait(1000);
							webDB.pressTabKey();
							commonFunc.hardWait(2000);
							flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Line_save_Icon, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SalesInvoiceLocators.RWC_Line_save_Icon, ElementType.Xpath);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Post_Btn, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO",
											"Resource Usage Inserting Workcenter, Process, Resource Updated");
								}
							}
						}

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Locator Item
	 * 
	 * @author Vimal - RMITS
	 */
	public void InsertingLocatorItem(String ReworkLocator) {
//		commonFunc.hardWait(2000);
//		flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Comp_Details, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(SalesInvoiceLocators.RWC_Comp_Details, ElementType.Xpath);
//			commonFunc.hardWait(2000);
//			String RWCComplQty = webDB.getTextFromElement(SalesInvoiceLocators.RWC_Compl_Qty, ElementType.Xpath);
//			logs.logging("INFO", "Get Compl Qty Is: " + RWCComplQty);
//			commonFunc.hardWait(2000);
//			webDB.moveToElement(SalesInvoiceLocators.RWC_Locator_Icon, ElementType.Xpath);
//			commonFunc.hardWait(2000);
//			webDB.clickAnElement(SalesInvoiceLocators.RWC_Locator_Icon, ElementType.Xpath);
//			commonFunc.checkRefreshCellsDisappear();
//			commonFunc.hardWait(2000);
//			webDB.switchToFrame(SalesInvoiceLocators.RWC_Locator_Iframe, ElementType.Xpath);
//			commonFunc.hardWait(1000);
//			flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Locator_Add_icon, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(SalesInvoiceLocators.RWC_Locator_Add_icon, ElementType.Xpath);
//				flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Locator_Column, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(SalesInvoiceLocators.RWC_Locator_Column, ElementType.Xpath);
//					commonFunc.hardWait(1000);
//					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.RWC_Locator_Column_Lov,
//							ReworkLocator);
//					webDB.switchToFrame(SalesInvoiceLocators.RWC_Locator_Iframe, ElementType.Xpath);
//					commonFunc.hardWait(2000);
//					verifyAddTableSpecs(SalesInvoiceLocators.RWC_Locator_Qty_Column,
//							SalesInvoiceLocators.RWC_Locator_Qty_Input, RWCComplQty);
//					commonFunc.hardWait(2000);
//					flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Locator_Save_Icon, ElementType.Xpath);
//					if (flag) {
//						webDB.clickAnElement(SalesInvoiceLocators.RWC_Locator_Save_Icon, ElementType.Xpath);
//						commonFunc.checkRefreshCellsDisappear();
//						commonFunc.hardWait(2000);
//						flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Locator_Close_Toast_Msg,
//								ElementType.Xpath);
//						if (flag) {
//							webDB.clickAnElement(SalesInvoiceLocators.RWC_Locator_Close_Toast_Msg, ElementType.Xpath);
//							commonFunc.hardWait(2000);
//							webDB.pressEscapeKey();
//							webDB.switchToDefaultContent();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.RWC_CreJournal_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.RWC_CreJournal_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Post_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Loactor Inserted And Cre.Journal Clicked Sucessfully");
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Create Journal
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingReworkCompletionCreatejournal() {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(SalesInvoiceLocators.Scrap_Details_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Scrap_Details_Tab, ElementType.Xpath);
			commonFunc.hardWait(2000);
			GetUIScrapItemAndwriteScrapItemColumnInExcelSheet(webDB.getDataFromProperties("LocalCustomer"));
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Post_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cre.Journal Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used For Get UI Scarp Item And Write Scarp Item In Excel Sheet
	 * 
	 * @author Vimal -RMITS
	 * @param FilePath
	 * @param SheetName
	 * @param i
	 * @param Item
	 */
	public void GetUIScrapItemAndwriteScrapItemColumnInExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < itemMultiples.length; i++) {
			String Item = itemMultiples[i][1];
			if (!Item.equals("")) {
				GetUIScrapItemColumnAndwriteScrapItemColumnInExcelSheet(Filepath, SheetName, i);
				commonFunc.hardWait(3000);
				salesOrderFunctions.ScrapStockQtyUpdateInExcelSI(i, ArrayList, SheetName);
				commonFunc.hardWait(3000);
			}
		}
	}

	/**
	 * This Method used For Get UI Scarp Item And Write Scarp Item In Excel Sheet
	 * 
	 * @author Vimal -RMITS
	 * @param FilePath
	 * @param SheetName
	 * @param i
	 * @param Item
	 */
	public void GetUIScrapItemColumnAndwriteScrapItemColumnInExcelSheet(String FilePath, String SheetName, int i) {
		commonFunc.checkRefreshCellsDisappear();
		String ScrapItem = webDB.getDriver().findElement(By.xpath(SalesInvoiceLocators.ScrapItemTable(i - 3)))
				.getText();
		logs.logging("INFO", "Get Text Unit Cost For This Item Is : " + ScrapItem);
		excelUtils.writeDataInExcel(FilePath, SheetName, i, 38, ScrapItem);

	}

	/**
	 * This Method Used For Clicking Rework Order Post Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingReworkOrderCompletionPostButton() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesInvoiceLocators.RWC_Post_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.RWC_Post_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Rework Order Completion forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void ReworkOrderCompletionforwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SalesInvoiceLocators.RW_Forward_OK_btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.RW_Forward_OK_btn, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesInvoiceLocators.ReworkOrd_Completion_Pend_Icon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To RW Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For create Scrapr Rework order for Sales return rejection
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createScrapReworkorderforSalesreturnrejection() {
		verifyCreateSalesInvoiceDomesticLocalCustomer();
		commonFunc.navigateToCreditNoteScreen();
		ClickingCreditNotePendingIcon();
		InsertingInvoicedNoSelectAndCreate(VariableHandler.getSIInvNo());
		InsertingRejectedQtyCNPrefixCreditNoteReasonCreInvoiceNoUpdNarration(
				commonFunc.getDataFromTestDataSheet("CR_CN_Prefix"),
				commonFunc.getDataFromTestDataSheet("CR_Return_Reason"),
				commonFunc.getDataFromTestDataSheet("CR_Acpt_Qty"));
		CreditNoteclickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCRdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkCreditNoteInvoicedStatusDetailsDataBaseValuesWithUIValues();
		mrvFunc.verifyCreditNotePostMaterialReceipt();
		commonFunc.navigateToReworkOrder();
		clickingReworkOrderPending();
		InsertingInvoiceNoandCreateReworksalesRtn(VariableHandler.getCNdocno());
		ClickingReworkOrderPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRWdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.navigateToReworkOrderCompletion();
		ClickingReworkOrderCompletionPendingIcon();
		InsertingRWCompletionDocNoandScrapCreateTheRecord(VariableHandler.getRWdocno());
		InsertingResourceUsage(VariableHandler.getRWdocno(), commonFunc.getDataFromTestDataSheet("WorkCenter"),
				commonFunc.getDataFromTestDataSheet("Process"), commonFunc.getDataFromTestDataSheet("Resource"));
		ClickingReworkCompletionCreatejournal();
		ClickingReworkOrderCompletionPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRWCdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		checkReworkCompletionPostedStatusDetailsDataBaseValuesWithUIValues();
		mrvFunc.verifyReworkCompletionPostMaterialReceipt();
//		checkReworkCompletionApprovedStatusDetailsDataBaseValuesWithUIValues();
//		salesOrderFunctions.verifyScrapAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(
//				webDB.getDataFromProperties("LocalCustomer"));
		return flag;
	}

	/**
	 * This Method Is Used To Verify Rework Order Header Details UI Values With
	 * Database Values
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkReworkOrderHeaderDetailsDataBaseValuesWithUIValues() {

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Header_LocID, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBLocIDColumn")));
		logs.logging("INFO", "Header Detail LocID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Header_Unit, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBUnitColumn")));
		logs.logging("INFO", "Header Detail Unit Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Header_Rework_Ord_Pfx, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBPfxColumn")));
		logs.logging("INFO", "Header Detail Rework Order Pfx Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Document_No, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBOrderNoColumn")));
		logs.logging("INFO", "Header Detail  Document no With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Item_Field, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBItemColumn")));
		logs.logging("INFO", "Header Detail Item Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Item_Desc_Field, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBItemDescColumn")));
		logs.logging("INFO", "Header Detail Item Desc Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Lot_Field, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBLotColumn")));
		logs.logging("INFO", "Header Detail Lot Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Source_Doc_No, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBSourceDocColumn")));
		logs.logging("INFO", "Header Detail Source Doc Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Sou_Pfx, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBSourceDocPfxColumn")));
		logs.logging("INFO", "Header Detail Source Prefix Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RW_Qty_Field, ElementType.Xpath),
				dbCommonFunctions.RWHeaderDetails(VariableHandler.getRWdocno(),
						commonFunc.getDataFromTestDataSheet("RWDBQtyColumn")));
		logs.logging("INFO", "Header Detail Rework Qty Matched With DataBase Values");
	}

	/**
	 * This Method Is Used To Verify Rework Order Header Details UI Values With
	 * Database Values
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkReworkOrderCompletionHeaderDetailsDataBaseValuesWithUIValues() {

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RWC_Header_LocID, ElementType.Xpath),
				dbCommonFunctions.RWCHeaderDetails(VariableHandler.getRWCdocno(),
						commonFunc.getDataFromTestDataSheet("RWCDBLocIDColumn")));
		logs.logging("INFO", "Header Detail LocID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RWC_Header_Unit, ElementType.Xpath),
				dbCommonFunctions.RWCHeaderDetails(VariableHandler.getRWCdocno(),
						commonFunc.getDataFromTestDataSheet("RWCDBUnitColumn")));
		logs.logging("INFO", "Header Detail Unit Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SalesInvoiceLocators.RWC_Document_No, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.RWCHeaderDetails(VariableHandler.getRWCdocno(),
						commonFunc.getDataFromTestDataSheet("RWCDBOrderNoColumn")));
		logs.logging("INFO", "Header Detail Rework Order Pfx Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RWC_Item_Field, ElementType.Xpath),
				dbCommonFunctions.RWCHeaderDetails(VariableHandler.getRWCdocno(),
						commonFunc.getDataFromTestDataSheet("RWCDBItemColumn")));
		logs.logging("INFO", "Header Detail  Document no With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SalesInvoiceLocators.RWC_SourceWH_Field, ElementType.Xpath),
				dbCommonFunctions.RWCHeaderDetails(VariableHandler.getRWCdocno(),
						commonFunc.getDataFromTestDataSheet("RWCDBSourceWHColumn")));
		logs.logging("INFO", "Header Detail Item Matched With DataBase Values");
		commonFunc.hardWait(1000);
		checkReworkCompletionDraftStatusDetailsDataBaseValuesWithUIValues();
	}

	/**
	 * This Method Used For Check Rework Completion Draft Status Details DataBase
	 * Values WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkReworkCompletionDraftStatusDetailsDataBaseValuesWithUIValues() {
		String RWCDocumentStatus = dbCommonFunctions.RWCHeaderDetails(VariableHandler.getRWCdocno(),
				commonFunc.getDataFromTestDataSheet("RWCDBStatusColumn"));
		salesOrderFunctions.verifyAssertionBothValues(RWCDocumentStatus,
				commonFunc.getDataFromTestDataSheet("RWCDBDraftColumn"));
		logs.logging("INFO", "Verified Rework Completion Document Status Is " + RWCDocumentStatus
				+ " Against This Rework Completion Number " + VariableHandler.getRWCdocno());
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For Check Rework Completion Posted Status Details DataBase
	 * Values WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkReworkCompletionPostedStatusDetailsDataBaseValuesWithUIValues() {
		String RWCDocumentStatus = dbCommonFunctions.RWCHeaderDetails(VariableHandler.getRWCdocno(),
				commonFunc.getDataFromTestDataSheet("RWCDBStatusColumn"));
		salesOrderFunctions.verifyAssertionBothValues(RWCDocumentStatus,
				commonFunc.getDataFromTestDataSheet("RWCDBPostedColumn"));
		logs.logging("INFO", "Verified Rework Completion Document Status Is " + RWCDocumentStatus
				+ " Against This Rework Completion Number " + VariableHandler.getRWCdocno());
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For Check Rework Completion Approved Status Details DataBase
	 * Values WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkReworkCompletionApprovedStatusDetailsDataBaseValuesWithUIValues() {
		String RWCDocumentStatus = dbCommonFunctions.RWCHeaderDetails(VariableHandler.getRWCdocno(),
				commonFunc.getDataFromTestDataSheet("RWCDBStatusColumn"));
		salesOrderFunctions.verifyAssertionBothValues(RWCDocumentStatus,
				commonFunc.getDataFromTestDataSheet("RWCDBApprovedColumn"));
		logs.logging("INFO", "Verified Rework Completion Document Status Is " + RWCDocumentStatus
				+ " Against This Rework Completion Number " + VariableHandler.getRWCdocno());
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For Inserting Rejected Qty CNPrefix CreditNoteReason
	 * CreInvoiceNo UpdNarration CreateJour And Post
	 * 
	 * @author Vimal - RMITS
	 * @param CR_CN_Prefix
	 * @param CR_Return_Reason
	 */
	public void InsertingRejectedQtyCNPrefixCreditNoteReasonCreInvoiceNoUpdNarration(String CR_CN_Prefix,
			String CR_Return_Reason, String CR_Acpt_Qty) {
		flag = webDB.waitForElement(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, ElementType.Xpath);
		if (flag) {
//			webDB.clickAnElement(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_CN_Pfx_Lov_Btn, CR_CN_Prefix);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SalesInvoiceLocators.CR_ShowMore, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.CR_ShowMore, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.CR_Reason_Lov_Btn, ElementType.Xpath);
				if (flag) {
//				webDB.clickAnElement(SalesInvoiceLocators.CR_Reason_Lov_Btn, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.CR_Reason_Lov_Btn, CR_Return_Reason);
					flag = webDB.waitForElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						checkCreditNoteHeaderDetailsDataBaseValuesWithUIValues();
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"CreditNote Prefix , Return Reason Inserted and Save Icon Clicked Sucessfully");
							commonFunc.hardWait(2000);
							flag = webDB.waitForElement(SalesInvoiceLocators.CR_AcptQty_Column, ElementType.Xpath);
							if (flag) {
								webDB.doubleClickAnElement(SalesInvoiceLocators.CR_AcptQty_Column, ElementType.Xpath);
								webDB.sendTextToAnElement(SalesInvoiceLocators.CR_AcptQty_Input, CR_Acpt_Qty,
										ElementType.Xpath);
								commonFunc.hardWait(2000);
								webDB.pressTabKey();
								commonFunc.hardWait(1000);
								webDB.pressTabKey();
								commonFunc.hardWait(2000);
								flag = webDB.waitForElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(3000);
									webDB.clickAnElement(SalesInvoiceLocators.CR_Save_Icon, ElementType.Xpath);
									commonFunc.checkRefreshCellsDisappear();
									commonFunc.hardWait(2000);
									webDB.scrollToAnElement(SalesInvoiceLocators.CR_LS_Acpt_Qty, ElementType.Xpath);
									commonFunc.hardWait(2000);
									flag = webDB.waitForElement(SalesInvoiceLocators.CR_LS_Acpt_Qty, ElementType.Xpath);
									if (flag) {
										webDB.doubleClickAnElement(SalesInvoiceLocators.CR_LS_Acpt_Qty,
												ElementType.Xpath);
										webDB.sendTextToAnElement(SalesInvoiceLocators.CR_LS_Acpt_Input, CR_Acpt_Qty,
												ElementType.Xpath);
										commonFunc.hardWait(2000);
										webDB.pressTabKey();
										commonFunc.hardWait(2000);
										flag = webDB.waitForElement(SalesInvoiceLocators.CR_LS_Save_Icon,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(SalesInvoiceLocators.CR_LS_Save_Icon,
													ElementType.Xpath);
											commonFunc.hardWait(1000);
											commonFunc.checkRefreshCellsDisappear();
											flag = webDB.waitForElement(SalesInvoiceLocators.CR_Cre_Inv_Btn,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(SalesInvoiceLocators.CR_Cre_Inv_Btn,
														ElementType.Xpath);
												commonFunc.hardWait(1000);
												commonFunc.checkRefreshCellsDisappear();
												String CNdocno = webDB.getDomPropertyFromElement(
														SalesInvoiceLocators.CR_CN_Doc_No, ElementType.Xpath,
														commonFunc.getDataFromTestDataSheet("Value"));
												VariableHandler.setCNdocno(CNdocno);
												logs.logging("INFO",
														"Get CN DocNo Is: " + VariableHandler.getCNdocno());
												commonFunc.hardWait(2000);
												flag = webDB.waitForElement(SalesInvoiceLocators.CR_Upd_Narr_Btn,
														ElementType.Xpath);
												if (flag) {
													logs.logging("INFO", "CreditNote Cre.Inv No Clicked Sucessfully");
													commonFunc.checkRefreshCellsDisappear();
													commonFunc.hardWait(2000);
													flag = webDB.waitForElement(SalesInvoiceLocators.CR_Upd_Narr_Btn,
															ElementType.Xpath);
													if (flag) {
														webDB.clickAnElement(SalesInvoiceLocators.CR_Upd_Narr_Btn,
																ElementType.Xpath);
														commonFunc.hardWait(2000);
														commonFunc.checkRefreshCellsDisappear();
														flag = webDB.waitForElement(
																SalesInvoiceLocators.CR_Create_Journal_Btn,
																ElementType.Xpath);
														if (flag) {
															webDB.clickAnElement(
																	SalesInvoiceLocators.CR_Create_Journal_Btn,
																	ElementType.Xpath);
															commonFunc.checkRefreshCellsDisappear();
															commonFunc.hardWait(2000);
															flag = webDB.waitForElement(
																	SalesInvoiceLocators.CR_Post_Btn,
																	ElementType.Xpath);
															if (flag) {
																logs.logging("INFO",
																		"CreditNote Update Narration , CRe. Journal Clicked Sucessfully");
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The SO Total Amount With DataBase
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifySITotalAmountWithExcelAndDB(String SheetName) {
		String[][] SIMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SIMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SIMultipleline.length; i++) {
			String ITEM_ID = SIMultipleline[i][1];
			String SI_Total_Amount = SIMultipleline[i][27];
			if (ITEM_ID.equals("")) {
				if (!SI_Total_Amount.equals("")) {
					salesOrderFunctions
							.verifyAssertionBothValues(
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getSIdocno(),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")),
									SI_Total_Amount);
					logs.logging("INFO",
							"Expected Sales Invoice Total Amount Is Matched With DataBase Values For This Sales Invoice Document "
									+ VariableHandler.getSIdocno());
				}
			}
		}
	}

	/**
	 * Verify forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void forwardForApproval(String ForwardPerson) {
		commonFunc.hardWait(1000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			String GetTextFrwdPerson = webDB.getDomPropertyFromElement(SalesInvoiceLocators.SI_Frwd_Person,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!GetTextFrwdPerson.equals(ForwardPerson)) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov, ForwardPerson);
				commonFunc.hardWait(500);
				webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched To WorkFlow Frame");
			}
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesInvoiceLocators.SI_Add_Icon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To PO Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct Sales Invoice For PreDispatch
	 * 
	 * @author Deepan-GWL
	 * @return boolean
	 */
	public void verifyDirectSalesInvoiceForPreDispatch() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesInvoicePage();
		verifyClickingAddButton();
		verifyCreateDirectSalesInvoiceAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SI_Type"), commonFunc.getDataFromTestDataSheet("CustomerName"));
		commonFunc.checkRefreshCellsDisappear();
		verifyClickingTickButton();
		verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		verifyHeaderCPCCode(commonFunc.getDataFromTestDataSheet("CPC_Code"));
		verifyHeaderTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		CustomerDetailsVerification(commonFunc.getDataFromTestDataSheet("CustomerName"));
		addingUserDefinedItemLineFromReadingExcelSheet(webDB.getDataFromProperties("Ins-PreDispatchSheet"),
				webDB.getDataFromProperties("QualityInspectionSheet"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		VariableHandler.setMultipleSIDocs(MultiSIDocNumber);
		verifyClickCreatePreDispatchButton();
		clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
	}

	/**
	 * This Method Used Adding User Defined Item Line From Reading Excel
	 * 
	 * @author Deepan-GWL
	 * @param SheetName
	 */
	public void addingUserDefinedItemLineFromReadingExcelSheet(String SheetName, String FileName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			salesOrderFunctions.StockQtyUpdateInExcelSI(i, ArrayList, SheetName, FileName);
			String Item = itemMultiples[i][1];
			String SalesGL_Acct = itemMultiples[i][35];
			String InvoiceQty = itemMultiples[i][6];
			String UnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			String Promotional = itemMultiples[i][36];
			if (!Item.equals("")) {
				flag = webDB.waitForElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.LineWise_Add_Btn, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesInvoiceLocators.Item_Column(), ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, Item);
						logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item);
						flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column(), ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Sales_GL_Acc_Desc_LOV_Btn,
									SalesGL_Acct);
							logs.logging("INFO", "Sales GL Acct Desc Inserted Successfully");
							commonFunc.hardWait(1000);
							verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty(),
									SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
							commonFunc.checkRefreshCellsDisappear();
							verifyAddTableSpecs(SalesInvoiceLocators.UnitCost_Column(),
									SalesInvoiceLocators.UnitCost_Input, UnitPrice);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
							if (flag) {
								String GetTextHsnCode = webDB.getTextFromElement(SalesInvoiceLocators.HSNcode_Column(),
										ElementType.Xpath);
								if (!GetTextHsnCode.equals(HSNcode_SI)) {
									webDB.clickAnElement(SalesInvoiceLocators.HSNcode_Column(), ElementType.Xpath);
									commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.HSN_Code_LOV_Btn,
											HSNcode_SI);
									logs.logging("INFO", "HSN Code Inserted Successfully");
									webDB.pressTabKey();
								}
								webDB.pressTabKey();
								commonFunc.checkRefreshCellsDisappear();
							}
						}
					}
				}
				flag = webDB.waitForElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Headerwise Tick Icon Displayed Successfully");
					flag = webDB.clickAnElement(SalesInvoiceLocators.Headerwise_TickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Headerwise Tick Icon Clicked Successfully");
					}
				}
				commonFunc.checkRefreshCellsDisappear();
				verifyLineWiseDetailsButton(i);
				switchToLineDetailsIframeInSaleInvoice();
				checkSILineDetailsDataBaseValuesWithUIValues();
				if (Promotional.equalsIgnoreCase("Promotional")) {
					verifyLineDetailsPromotionalDropDown(Promotional);
				}
				verifyLineDetailsSaveIcon();
			}
		}
		VariableHandler.setExpectedStockQty2(ArrayList);
	}

	/**
	 * This Method Is Used To Verify Click Create PreDispatch Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickCreatePreDispatchButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.CreatePreDispatchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CreatePreDispatchButton, ElementType.Xpath);
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Create PreDispatch Button Is Clicked");
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
		}
	}

	/**
	 * This Method Is Used For Click Include Charges
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickIncludeCharge() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Include_Charge, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_Include_Charge, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
//				flag = webDB.waitForElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(SalesInvoiceLocators.Header_Other_Tab, ElementType.Xpath);
//					commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SalesInvoiceLocators.Post_Btn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Include Charges clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}
}
