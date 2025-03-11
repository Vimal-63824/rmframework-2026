package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 05, 2025
 */
public class CompletionProductionOrderLocators {

	/** @locatorType XPATH */
	public static final String PendingProdOrderIframe = "//iframe";// iframe[@title='Pend. Prod. Ord.']
	/** @locatorType XPATH */
	public static final String PendingProdOrderIframeCloseIcon = "//h1[text()='Pend. Prod. Ord.']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String PendingProdOrderSearchBar = "//input[@id='PEND_search_field']";
	/** @locatorType XPATH */
	public static final String PendingProdOrderSearchGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType XPATH */
	public static final String PendingProdOrder_ProdNoTableData = "//div[@id='PEND_content']//table/tbody/tr/td[3]";
	/** @locatorType XPATH */
	public static final String PendingProdOrder_ItemTableData = "//div[@id='PEND_content']//table/tbody/tr/td[4]";
	/** @locatorType XPATH */
	public static final String PendingProdOrder_ProcessTableData = "//div[@id='PEND_content']//table/tbody/tr/td[9]";

	/** @locatorType XPATH */
	public static final String PendingProdOrder_SelectCheckboxTableData(String ProdNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + ProdNo + "']/../td[16]/input";
	}

	/** @locatorType XPATH */
	public static final String CreateCompletionProcessBtn = "//button[@id='B1']";
	/** @locatorType XPATH */
	public static final String CreateCompletionProcessOkBtn = "//button[@id='CREATE']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderShiftLovBtn = "//button[@id='P19631031010_PT_SHIFT_ID_lov_btn']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayProcess = "//span[@id='P19631031010_PT_FROM_OPRN_ID_DISPLAY']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayCompletionProdOrderNo = "//span[@id='P19631031010_PT_TRANS_NO_DISPLAY']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayProdutionOrderNo = "//span[@id='P19631031010_PT_PROD_ORD_NO_DISPLAY']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderCompQuantityInput = "//input[@id='P19631031010_PT_COMP_QTY']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderAcceptQuantityInput = "//input[@id='P19631031010_PT_ACCEPT_QTY']";
	/** @locatorType XPATH */
	public static final String ConsumptionWH_TableData = "//div[@id='consumption_ig_content_container']//table/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String ComplProdOrderRejectQuantityInput = "//input[@id='P19631031010_PT_PRIM_REJ_QTY']";
	/** @locatorType XPATH */
	public static final String ComplProdHeaderSaveIcon = "//button[@id='SAVE']";
	/** @locatorType XPATH */
	public static final String ComplProdPostBtn = "//button[@id='B2770520109568230593']";
	/** @locatorType XPATH */
	public static final String CompletionProcessPendingIcon = "//button[@id='B1164090042492814301']";
}
