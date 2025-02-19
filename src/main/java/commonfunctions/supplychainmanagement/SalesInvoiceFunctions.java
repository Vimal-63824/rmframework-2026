package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SalesInvoiceFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

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
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Loc_ID_Btn, LocationId);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_Type_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.SI_Type_Btn, SI_Type);
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
			flag = webDB.waitForElement(SalesInvoiceLocators.Header_CPC_LOV_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CPC Header Lov Is Displayed Successfully");
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
		flag = webDB.waitForElement(SalesInvoiceLocators.CPC_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.CPC_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Header_CPC_LOV_Btn, CPC_Code);
			flag = webDB.waitForElement(SalesInvoiceLocators.Header_Tick_Icon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Tick Icon Is Displayed Successfully");
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
			flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column, ElementType.Xpath);
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
		flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Item_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Item_Lov_Btn, Item_ID);
			logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item_ID);
			flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column, ElementType.Xpath);
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
		flag = webDB.waitForElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Sales_GL_Acc_Desc_Column, ElementType.Xpath);
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
			webDB.clickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(Tablespec, ElementType.Xpath);
				webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue);
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
		verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty, SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
		verifyAddTableSpecs(SalesInvoiceLocators.UnitCost_Column, SalesInvoiceLocators.UnitCost_Input, UnitPrice);
	}

	/**
	 * Verify Rate Contract Price List Standard Price Catalog Price
	 * 
	 * @author Vimal - RMITS
	 * @param InvoiceQty
	 */
	public void verifyRateContractInvoiceQty(String InvoiceQty) {
		commonFunc.hardWait(1000);
		verifyAddTableSpecs(SalesInvoiceLocators.Invoice_Column_Qty, SalesInvoiceLocators.Invoice_Qty, InvoiceQty);
	}

	/**
	 * Verify Header HSN Code
	 * 
	 * @author Vimal - RMITS
	 * @param HSNcode_SI
	 */
	public void verifyHSNcode(String HSNcode_SI) {
		flag = webDB.waitForElement(SalesInvoiceLocators.HSNcode_Column, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "HSN Code Table Displayed Successfully");
			webDB.clickAnElement(SalesInvoiceLocators.HSNcode_Column, ElementType.Xpath);
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
	public void verifyLineWiseDetailsButton() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Details_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Details_Btn, ElementType.Xpath);
			logs.logging("INFO", "Linewise Detail Button Clicked Successfully");
			flag = webDB.waitForElement(SalesInvoiceLocators.Detail_Btn_Iframe, ElementType.Xpath);
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
		flag = webDB.waitForElement(SalesInvoiceLocators.Alloc_ls_batch_Btn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Allocate LS Batch Displayed Successfully");
			flag = webDB.clickAnElement(SalesInvoiceLocators.Alloc_ls_batch_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Allocate LS Batch clicked Successfully");
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
		flag = webDB.waitForElement(SalesInvoiceLocators.Pick_Btn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pick Button Displayed Successfully");
			webDB.clickAnElement(SalesInvoiceLocators.Pick_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (flag) {
				logs.logging("INFO", "Pick Button clicked Successfully");
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
		flag = webDB.waitForElement(SalesInvoiceLocators.Cre_Inv_No, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Cre.Inv.No Button Displayed Successfully");
			webDB.clickAnElement(SalesInvoiceLocators.Cre_Inv_No, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (flag) {
				logs.logging("INFO", "Cre.Inv.No Button clicked Successfully");
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
			flag = webDB.waitForElement(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
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

	public void verifyItemFromPendingSaleOrderAndClickAllocLotSerial(String Item) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Item_Column, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesInvoiceLocators.Item_Column, ElementType.Xpath);
			String DisplayedText = webDB.getTextFromElement(SalesInvoiceLocators.Item_Column, ElementType.Xpath);
			flag = Item.equalsIgnoreCase(DisplayedText);
			if (flag) {
				flag = webDB.waitForElement(SalesInvoiceLocators.Alloc_ls_batch_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesInvoiceLocators.Alloc_ls_batch_Btn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					commonFunc.checkSuccessToastMessageDisappear();
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
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
		flag = webDB.waitForElement(SalesInvoiceLocators.DiscountAmountTableData, ElementType.Xpath);
		if (flag) {
			BigDecimal DiscountAmnt = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.DiscountAmountTableData, ElementType.Xpath));
			DiscountAmnt = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscountAmnt);
			logs.logging("INFO", "Discount Amount Displayed without tax: " + DiscountAmnt);
			totalCostWithoutTax = totalCostWithoutTax.subtract(DiscountAmnt);
			logs.logging("INFO", "After Subtract Discount in Calculation Amount without tax: " + totalCostWithoutTax);

			BigDecimal PerValue = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.TaxPercentField, ElementType.Xpath));
			PerValue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(PerValue);
			logs.logging("INFO", "Displayed tax Percentage: " + PerValue.intValue() + "%");

			BigDecimal TaxValue = totalCostWithoutTax.divide(new BigDecimal("100")).multiply(PerValue);
			BigDecimal IndividualTax = purchaseOrderFunc
					.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TaxValue.divide(new BigDecimal("2")));
			logs.logging("INFO", "Individual Tax Amount: " + IndividualTax);
			TaxValue = IndividualTax.multiply(new BigDecimal("2"));
			logs.logging("INFO", "Expected Tax Value After Tax calculation: " + TaxValue);

			BigDecimal IGSTAmount = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.IGSTTaxAmountField, ElementType.Xpath));
			IGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(IGSTAmount);
			BigDecimal CGSTAmount = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.CGSTTaxAmountField, ElementType.Xpath));
			CGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(CGSTAmount);
			BigDecimal SGSTAmount = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.SGSTTaxAmountField, ElementType.Xpath));
			SGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(SGSTAmount);
			BigDecimal UTGSTAmount = new BigDecimal(
					webDB.getTextFromElement(SalesInvoiceLocators.UTGSTTaxAmountField, ElementType.Xpath));
			UTGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(UTGSTAmount);

			BigDecimal taxAmount = IGSTAmount.add(CGSTAmount).add(SGSTAmount).add(UTGSTAmount);
			logs.logging("INFO", "After Adding Displayed GST Values: " + taxAmount);

			flag = taxAmount.equals(TaxValue);
			if (flag) {
				logs.logging("INFO", "Tax Amount Verified, Tax added value: " + taxAmount);
				logs.logging("INFO", "verifing total amount after calculating tax amount!!!");
				BigDecimal DisplayLineAmnt = new BigDecimal(
						webDB.getDomPropertyFromElement(SalesInvoiceLocators.TotalAmountDisplayField, ElementType.Xpath,
								commonFunc.getDataFromTestDataSheet("Value")));
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
			flag = webDB.waitForElement(SalesInvoiceLocators.Line_Det_save_Btn, ElementType.Xpath);
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
		flag = webDB.waitForElement(SalesInvoiceLocators.LineAmountTableData, ElementType.Xpath);
		if (flag) {
			String lineAmount = webDB.getTextFromElement(SalesInvoiceLocators.LineAmountTableData, ElementType.Xpath);
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
					flag = webDB.waitForElement(SalesInvoiceLocators.DiscountAmountTableData, ElementType.Xpath);
					if (flag) {
						String Discount = webDB.getTextFromElement(SalesInvoiceLocators.DiscountAmountTableData,
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyItemQtyUnitcost(commonFunc.getDataFromTestDataSheet("InvoiceQty"),
				commonFunc.getDataFromTestDataSheet("UnitPrice"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyItemQtyUnitcost(commonFunc.getDataFromTestDataSheet("InvoiceQty"),
				commonFunc.getDataFromTestDataSheet("UnitPrice"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Domestic_RC_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Export_RC_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("UD_Disimilar_Domestic_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyItemQtyUnitcost(commonFunc.getDataFromTestDataSheet("InvoiceQty"),
				commonFunc.getDataFromTestDataSheet("UnitPrice"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("UD_Disimilar_Domestic_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyItemQtyUnitcost(commonFunc.getDataFromTestDataSheet("InvoiceQty"),
				commonFunc.getDataFromTestDataSheet("UnitPrice"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Domestic_RC_Item_ID_Disimilar"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Export_RC_Item_ID_Disimilar"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Domestic_PL_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Export_PL_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Domestic_PL_Item_ID_Disimilar"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Export_PL_Item_ID_Disimilar"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Domestic_SP_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Export_SP_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Domestic_SP_Item_ID_Disimalar"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Export_SP_Item_ID_Disimalar"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Domestic_CG_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Export_CG_Item_ID"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Domestic_CG_Item_ID_Disimalar"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
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
		verifyLinewiseAddButton();
		verifyItemID(commonFunc.getDataFromTestDataSheet("Export_CG_Item_ID_Disimalar"));
		verifySalesGlAccount(commonFunc.getDataFromTestDataSheet("SalesGL_Acct"));
		verifyRateContractInvoiceQty(commonFunc.getDataFromTestDataSheet("InvoiceQty"));
		verifyHSNcode(commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
		verifyHeaderwiseTickIcon();
		commonFunc.checkRefreshCellsDisappear();
		verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyCreateInvoiceNo();
		commonFunc.checkRefreshCellsDisappear();
		clickPostButton();
		forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
		flag = commonFunc.logoutUser();
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
		flag = webDB.clickAnElement(SalesInvoiceLocators.PI_Pend_SO_No_Column, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_Pend_SO_No, PI_Pend_SO_No);
			webDB.switchToFrame(SalesInvoiceLocators.PI_Pend_Iframe, ElementType.Xpath);
			logs.logging("INFO", "Proforma Invoice Pending SaleOrder No Inserted Sucessfully");
		}
		webDB.switchToDefaultContent();
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
		}
		webDB.switchToDefaultContent();
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
	public void verifyHeaderProformaInvoicePrefix(String Proforma_Inv_Pfx) {
		commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Proforma_Inv_Pfx_Btn, Proforma_Inv_Pfx);
		flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_inv_Header_TickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Proforma_inv_Header_TickIcon, ElementType.Xpath);
			logs.logging("INFO", "Proforma Invoice Prefix Inserted Sucessfully");
			if (webDB.isElementDisplayed(SalesInvoiceLocators.PI_Header_Close_Success_msg, ElementType.Xpath)) {
				webDB.clickAnElement(SalesInvoiceLocators.PI_Header_Close_Success_msg, ElementType.Xpath);
				logs.logging("INFO", "Note Alert Close icon Is Clicked");
			}
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_DocNo, ElementType.Xpath);
			if (flag) {
				String PI_DocNo = webDB.getTextFromElement(SalesInvoiceLocators.PI_DocNo, ElementType.Xpath);
				VariableHandler.setPI_DocNo(PI_DocNo);
				logs.logging("INFO", "Get SI DocNo Is: " + VariableHandler.getPI_DocNo());
				commonFunc.hardWait(5000);
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
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Item_ID_column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LineWise Add Icon Clicked Sucessfully");
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
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_Item_ID_column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_Item_ID_column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_Item_Id_Btn, PI_Item_ID);
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Qty_Column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LineWise Item Inserted Sucessfully - " + PI_Item_ID);
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
		commonFunc.hardWait(1000);
		verifyAddTableSpecs(SalesInvoiceLocators.PI_Qty_Column, SalesInvoiceLocators.PI_Inv_Qty, PI_Inv_Qty);
		verifyAddTableSpecs(SalesInvoiceLocators.PI_UnitCost_Column, SalesInvoiceLocators.PI_UnitCost, PI_Inv_UnitCost);
	}

	/**
	 * This Method is used for verify Proforma Invoice HSNCode
	 * 
	 * @author Vimal - RMITS
	 * @param PI_HSN_code
	 */
	public void verifyProformaInvoiceHSNCode(String PI_HSN_code) {
		flag = webDB.waitForElement(SalesInvoiceLocators.PI_HSN_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.PI_HSN_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PI_HSN_Btn, PI_HSN_code);
			flag = webDB.waitForElement(SalesInvoiceLocators.PI_Line_tickIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Linewise HSN Code Inserted Sucessfully");
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
			}
		}
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
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_Type"),
				commonFunc.getDataFromTestDataSheet("PI_Domestic_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"));
		commonFunc.checkRefreshCellsDisappear();
		verifyProformaInvoiceLineWiseAddIconClick();
		verifyProformaInvoiceItemID(commonFunc.getDataFromTestDataSheet("PI_Item_ID"));
		verifyProformaInvoiceQtyUnitcost(commonFunc.getDataFromTestDataSheet("PI_Inv_Qty"),
				commonFunc.getDataFromTestDataSheet("PI_Inv_UnitCost"));
		verifyProformaInvoiceHSNCode(commonFunc.getDataFromTestDataSheet("PI_HSN_code"));
		verifyProformaInvoiceLineWiseTickIcon();
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		ProformaInvoiceforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getPI_DocNo());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPI_DocNo());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * verify Direct Advance Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean flag
	 */
	public boolean verifyDirectAdvanceProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_Advance_Type"),
				commonFunc.getDataFromTestDataSheet("PI_Domestic_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Adv_Pfx"));
		commonFunc.checkRefreshCellsDisappear();
		verifyProformaInvoiceLineWiseAddIconClick();
		verifyProformaInvoiceAdvanceSOReference(commonFunc.getDataFromTestDataSheet("PI_SO_Ref_Advance"));
		verifyProformaInvoiceItemID(commonFunc.getDataFromTestDataSheet("PI_SO_Ref_Advance_Item_ID"));
		verifyProformaInvoiceLineWiseTickIcon();
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		ProformaInvoiceforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getPI_DocNo());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPI_DocNo());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * verify Direct PreDispatch Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean flag
	 */
	public boolean verifyDirectPreDispatchProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_PreDispatch_Type"),
				commonFunc.getDataFromTestDataSheet("PI_Domestic_Customer"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_PreDisp_Pfx"));
		commonFunc.checkRefreshCellsDisappear();
		verifyProformaInvoiceLineWiseAddIconClick();
		verifyProformaInvoiceAdvanceSOReference(commonFunc.getDataFromTestDataSheet("PI_SO_Ref_Advance"));
		verifyProformaInvoiceItemID(commonFunc.getDataFromTestDataSheet("PI_SO_Ref_Advance_Item_ID"));
		verifyProformaInvoiceLineWiseTickIcon();
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		ProformaInvoiceforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getPI_DocNo());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPI_DocNo());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * verify Direct Export Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectExportProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_Type"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"));
		commonFunc.checkRefreshCellsDisappear();
		verifyProformaInvoiceLineWiseAddIconClick();
		verifyProformaInvoiceItemID(commonFunc.getDataFromTestDataSheet("PI_Item_ID"));
		verifyProformaInvoiceQtyUnitcost(commonFunc.getDataFromTestDataSheet("PI_Inv_Qty"),
				commonFunc.getDataFromTestDataSheet("PI_Inv_UnitCost"));
		verifyProformaInvoiceHSNCode(commonFunc.getDataFromTestDataSheet("PI_HSN_code"));
		verifyProformaInvoiceLineWiseTickIcon();
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		ProformaInvoiceforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getPI_DocNo());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPI_DocNo());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * verify Direct Export Advance Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectExportAdvanceProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_Advance_Type"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Adv_Pfx"));
		commonFunc.checkRefreshCellsDisappear();
		verifyProformaInvoiceLineWiseAddIconClick();
		verifyProformaInvoiceAdvanceSOReference(commonFunc.getDataFromTestDataSheet("PI_SO_Ref_Advance_Export"));
		verifyProformaInvoiceItemID(commonFunc.getDataFromTestDataSheet("PI_SO_Ref_Advance_Export_Item_ID"));
		verifyProformaInvoiceLineWiseTickIcon();
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		ProformaInvoiceforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getPI_DocNo());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPI_DocNo());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * verify Direct Export PreDispatch Proforma Invoice Creation
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectExportPreDispatchProformaInvoiceCreation() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateProformaInvoice();
		verifyFindPageAddButton();
		VerifyProformaInvoiceParameter(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("PI_PreDispatch_Type"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_PreDisp_Pfx"));
		commonFunc.checkRefreshCellsDisappear();
		verifyProformaInvoiceLineWiseAddIconClick();
		verifyProformaInvoiceAdvanceSOReference(commonFunc.getDataFromTestDataSheet("PI_SO_Ref_Advance_Export"));
		verifyProformaInvoiceItemID(commonFunc.getDataFromTestDataSheet("PI_SO_Ref_Advance_Export_Item_ID"));
		verifyProformaInvoiceLineWiseTickIcon();
		commonFunc.hardWait(2000);
		verifyProformaInvoicePostButton();
		ProformaInvoiceforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getPI_DocNo());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPI_DocNo());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * verify Direct Domestic Proforma Invoice Creation PendingSO
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectDomesticProformaInvoiceCreationPendingSO() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateProformaInvoice();
		verifyProformaInvoicePendingSO();
		verifyProformaInvoicePendingSaleOrderNo(commonFunc.getDataFromTestDataSheet("PI_Pend_Domestic_SO_No"));
		verifyProformaInvoicePendingSearchButton();
		verifyProformaInvoicePendingSelectAll();
		verifyProformaInvoicePendingClickingCreateProformaInvoice();
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"));
		verifyProformaInvoicePostButton();
		ProformaInvoiceforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getPI_DocNo());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPI_DocNo());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * verify Direct Export Proforma Invoice Creation PendingSO
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean verifyDirectExportProformaInvoiceCreationPendingSO() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateProformaInvoice();
		verifyProformaInvoicePendingSO();
		verifyProformaInvoicePendingSaleOrderNo(commonFunc.getDataFromTestDataSheet("PI_Pend_Export_SO_No"));
		verifyProformaInvoicePendingSearchButton();
		verifyProformaInvoicePendingSelectAll();
		verifyProformaInvoicePendingClickingCreateProformaInvoice();
		commonFunc.checkRefreshCellsDisappear();
		verifyHeaderProformaInvoicePrefix(commonFunc.getDataFromTestDataSheet("Proforma_Inv_Pfx"));
		verifyProformaInvoicePostButton();
		ProformaInvoiceforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getPI_DocNo());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPI_DocNo());
		flag = commonFunc.logoutUser();
		return flag;
	}
}
