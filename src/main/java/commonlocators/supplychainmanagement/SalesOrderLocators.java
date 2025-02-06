package commonlocators.supplychainmanagement;

public class SalesOrderLocators {
	/** Locator Xpath */
	public static final String AddButtonSalesOrder = "//button[@id='B583506114587524405']";
	/** Locator Xpath */
	public static final String SalesOrderHeader = "//h1[@id='ui-id-1']";
	/** Locator Xpath */
	public static final String LocationLOV = "//button[contains(@id,'P1915131339_UNIT_LOC_lov_btn')]";
	/** Locator Xpath */
	public static final String SalesSubVoucherTypeLOV = "//button[contains(@id,'P1915131339_SOH_ORDER_TYPE_lov_btn')]";
	
	public static final String SalesTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]";
	
	public static final String SalesTransactionTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]";
	
	public static final String SalesOrderTab = "//span[text()='Supply Chain']/ancestor::li/*/li[4]/*/li[2]/*/li[5]";
	
	
}
