package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 05, 2025
 */
public class CompletionProductionOrderLocators {

	/** @locatorType XPATH */
	public static final String PendingProdOrderIframe = "//iframe[contains(@title,'Pend. Prod. Ord.')]";
	/** @locatorType XPATH */
	public static final String PendingProdOrderIframeCloseIcon = "//h1[text()='Pend. Prod. Ord.']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String PendingProdOrderSearchBar = "//input[@id='PEND_search_field']";
	/** @locatorType XPATH */
	public static final String PendingProdOrderSearchGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType XPATH */
	public static final String PendingProdOrder_ProdNoTableData = "//div[@id='PEND_content']//table/tbody/tr/td[4]";
	/** @locatorType XPATH */
	public static final String PendingProdOrder_ItemTableData = "//div[@id='PEND_content']//table/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String PendingProdOrder_ProcessTableData = "//div[@id='PEND_content']//table/tbody/tr/td[14]";

	/** @locatorType XPATH */
	public static final String PendingProdOrder_SelectCheckboxTableData(String ProdNo) {
		return "(//div[@id='PEND_content']//table/tbody/tr/td[text()='" + ProdNo + "']/../td/input)[2]";
	}

	/** @locatorType XPATH */
	public static final String CreateCompletionProcessBtn = "//button[@data-testid='CreateCompletionProcessBtn']";
	/** @locatorType XPATH */
	public static final String CreateCompletionProcessOkBtn = "//button[@data-testid='CreateCompletionProcessOkBtn']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderShiftLovBtn = "//input[@data-testid='ComplProdOrderShiftLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayProcess = "//span[@data-testid='ComplProdOrderDisplayProcess']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayCompletionProdOrderNo = "//span[@data-testid='ComplProdOrderDisplayCompletionProdOrderNo']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayProdutionOrderNo = "//span[@data-testid='ComplProdOrderDisplayProdutionOrderNo']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderCompQuantityInput = "//input[@data-testid='ComplProdOrderCompQuantityInput']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderAcceptQuantityInput = "//input[@data-testid='ComplProdOrderAcceptQuantityInput']";
	/** @locatorType XPATH */
	public static final String ConsumptionWH_TableData = "//div[@id='consumption_ig_content_container']//table/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String ComplProdOrderRejectQuantityInput = "//input[@data-testid='ComplProdOrderRejectQuantityInput']";
	/** @locatorType XPATH */
	public static final String ComplProdHeaderSaveIcon = "//button[@data-testid='ComplProdHeaderSaveIcon']";
	/** @locatorType XPATH */
	public static final String ComplProdPostBtn = "//button[@data-testid='ComplProdPostBtn']";
	/** @locatorType XPATH */
	public static final String CompletionProcessPendingIcon = "//button[@data-testid='CompletionProcessPendingIcon']";
	/** @locatorType XPATH */
	public static final String ProductionOrderNumber = "//input[@data-testid='ProductionOrderNumber']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SearchButton = "//button[@data-testid='SearchButton']";
	/** @locatorType XPATH */
	public static final String SelectStatus = "//select[@data-testid='SelectStatus']";
	/** @locatorType XPATH */
	public static final String TransLovButton = "//input[@data-testid='TransLovButton']/following-sibling::button";

	/** @locatorType XPATH */
	public static final String CompletionTransNumber(String Number) {
		return "//a[text()='" + Number + "']";
	}

	/** @locatorType XPATH */
	public static final String MultiProc_PendingIcon = "//button[@data-testid='MultiProc_PendingIcon']";
	/** @locatorType XPATH */
	public static final String MultiProc_PendingIframe = "//iframe[@title='Pend. Prod. Ord.']";
	/** @locatorType XPATH */
	public static final String MultiProc_SelectAllCheckBox = "//div[@id='PEND_content']//table/tbody/tr/th//input[@id='check_all']";
	/** @locatorType XPATH */
	public static final String MultiProc_ShiftLovBtn = "//input[@data-testid='MultiProc_ShiftLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String MultiProc_SaveHeaderBtn = "//button[@data-testid='MultiProc_SaveHeaderBtn']";
	/** @locatorType XPATH */
	public static final String MultiProc_PostBtn = "//button[@data-testid='MultiProc_PostBtn']";
	/** @locatorType XPATH */
	public static final String MultiProc_DisplayComplProdOrderNo = "//span[@data-testid='MultiProc_DisplayComplProdOrderNo']";
}
