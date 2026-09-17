package commonfunctions;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import java.io.File;
import utils.ExcelUtils;
import utils.WebDriverBase;

public class ItemDetailsHandler {
	private static ExcelUtils excelUtils = new ExcelUtils();
	private static WebDriverBase webDB = new WebDriverBase();
	private static ThreadLocal<String[][]> PR_LineDetails = ThreadLocal.withInitial(() -> null);
	private static String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "ItemDetails.xlsx";
	private static ThreadLocal<String[][]> PO_LineDetails = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> POA_LineDetails = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> POItem_LineDetails = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> SO_LineDetails = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> SO_CustDetails = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> SI_CustomerDetails = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> Open_SO_Lines_details = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> Open_Sale_Order = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> Open_SO_HeaderDetails = ThreadLocal.withInitial(() -> null);

	/**
	 * This Enum 'PR Line Details Name column Name'
	 */
	public enum PRLineDetailsName {
		ITEM_NAME, CLASS, SUBCLASS, WAREHOUSE, CONVENTION_FACTOR, PURCHASE_UOM, GL_ACC_NO, GL_ACC_DESC, CPC_CODE,
		CPC_DESC
	}

	/**
	 * This Enum 'PO Line Details Name column Name'
	 */
	public enum POLineDetailsName {
		ITEMID, ITEM_DESCRIPTION, UOM, HSN_CODE, TAX, SUBCLASS, CLASS, WH_DEPTNAME, COST_BASIS, STOCK_UOM,
		CONVERSION_FACTOR, GL_ACC_NO, GL_ACC_DESC, CPC_CODE, CPC_DESC, CONTRACT_NO, CONTRACT_PRFX, CONTRACT_AMEND_NO,
		PO_CLS_QTY, RCM_CAT_ID
	}

	/**
	 * This Enum 'POA Line Details Name column Name'
	 */
	public enum POALineDetailsName {
		ITEMID, ITEM_DESCRIPTION, UOM, HSN_CODE, WH_DEPTNAME, COST_BASIS, SUPL_UOM, CONVERSION_FACTOR, GL_ACC_NO,
		GL_ACC_DESC, CPC_CODE, CPC_DESC
	}

	/**
	 * This Enum 'PO Line Item Details Name column Name'
	 */
	public enum POItemLineDetailsName {
		ITEM_NAME, QUANTITY, UNIT_COST

	}

	/**
	 * This method used to return specific items particular Line Detail Data from
	 * Item Details Sheet.
	 * 
	 * @author Sakthi-GWL
	 * @param itemName
	 * @param LineDetailName
	 * @return String resultData
	 */
	public String getPRLineDetailsforGivenItem(String itemName, PRLineDetailsName LineDetailName) {
		String resultData = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (PR_LineDetails.get() == null || PR_LineDetails.get().length == 0) {
			PR_LineDetails.set(excelUtils.readAllDataInExcel(Filepath, "PR Details"));
		}
		String[] headers = PR_LineDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(PRLineDetailsName.ITEM_NAME.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < PR_LineDetails.get().length; i++) {
			if (PR_LineDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(itemName.trim())) {
				resultData = PR_LineDetails.get()[i][columnIndex];
				break;
			}
		}
		return resultData;
	}

	/**
	 * get PO Line Details for Given Item
	 * 
	 * @author Deepan-GWL
	 * @param itemName
	 * @param LineDetailName
	 * @return String result
	 */
	public String getPOLineDetailsforGivenItem(String itemName, POLineDetailsName LineDetailName) {
		String result = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (PO_LineDetails.get() == null || PO_LineDetails.get().length == 0) {
			PO_LineDetails.set(excelUtils.readAllDataInExcel(Filepath, "PO Details"));
		}
		String[] headers = PO_LineDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(POLineDetailsName.ITEMID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < PO_LineDetails.get().length; i++) {
			if (PO_LineDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(itemName.trim())) {
				result = PO_LineDetails.get()[i][columnIndex];
				break;
			}
		}
		return result;
	}

	/**
	 * get POA Line Details for Given Item
	 * 
	 * @author Deepan-GWL
	 * @param itemName
	 * @param LineDetailName
	 * @return String result
	 */
	public String getPOALineDetailsforGivenItem(String itemName, POALineDetailsName LineDetailName) {
		String result = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (POA_LineDetails.get() == null || POA_LineDetails.get().length == 0) {
			POA_LineDetails.set(excelUtils.readAllDataInExcel(Filepath, "POA Details"));
		}
		String[] headers = POA_LineDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(POALineDetailsName.ITEMID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < POA_LineDetails.get().length; i++) {
			if (POA_LineDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(itemName.trim())) {
				result = POA_LineDetails.get()[i][columnIndex];
				break;
			}
		}
		return result;
	}

	/**
	 * get POA Line Details for Given Item
	 * 
	 * @author Deepan-GWL
	 * @param itemName
	 * @param LineDetailName
	 * @return String result
	 */
	public String getPOItemLineDetailsforGivenItem(String itemName, POItemLineDetailsName LineDetailName) {
		String result = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (POItem_LineDetails.get() == null || POItem_LineDetails.get().length == 0) {
			POItem_LineDetails.set(excelUtils.readAllDataInExcel(Filepath, "PO MULTIPLE ITEM"));
		}
		String[] headers = POItem_LineDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(POItemLineDetailsName.ITEM_NAME.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < POItem_LineDetails.get().length; i++) {
			if (POItem_LineDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(itemName.trim())) {
				result = POItem_LineDetails.get()[i][columnIndex];
				break;
			}
		}
		return result;
	}

	/**
	 * This Enum Contains Sales Order Line Details Column Name
	 * 
	 * @author Harikrishna -RMITS
	 */
	public enum SOLineDetailsName {
		ITEM_ID, ITEM_REV, ITEM_DESC, SALE_UOM, HSN_CODE, TAX_PCT, PRICE_BASIS_TYPE, CONTRACT_NO, CATALOG_PRICELIST_NO,
		STOCK_UOM, CONV_FACT, TOLERANCE_TYPE, CUST_ITEM_ID, CUST_ITEM_DESC, WAREHOUSE_DESC, SUB_CLASS, CLASS,
		PRICECLASS, PROMOTIONAL
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used To Get Sales Order Line
	 *         Details
	 * @param itemName
	 * @param LineDetailName
	 * @return String result
	 */
	public String getSOLineDetailsforGivenItem(String itemName, SOLineDetailsName LineDetailName) {
		String result1 = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (SO_LineDetails.get() == null || SO_LineDetails.get().length == 0) {
			SO_LineDetails.set(excelUtils.readAllDataInExcel(Filepath, "SO Details"));
		}
		String[] headers = SO_LineDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(SOLineDetailsName.ITEM_ID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < SO_LineDetails.get().length; i++) {
			if (SO_LineDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(itemName.trim())) {
				result1 = SO_LineDetails.get()[i][columnIndex];
				break;
			}
		}
		return result1;
	}

	/**
	 * @author Harikrishna -RMITS This Enum Contains Sales Order Header Customer
	 *         Details Column Name
	 */
	public enum SOCustomerDetailsName {
		CUST_ID, CUST_DESC, GST_TYPE, SALES_GST_TYPE, GST_CLASSIFICATION, CUST_PRINT_NAME, SALES_ZONE, SALES_REGION,
		CUST_SUB_GRP, CUST_GRP, SALES_PERSON

	}

	/**
	 * @author Harikrishna -RMITS This Method Is used To Get Sales Order Header
	 *         Customer Details
	 * @param CustomerName
	 * @param CustomerDetailName
	 * @return String result
	 */
	public String getSOCustomerDetails(String CustomerName, SOCustomerDetailsName CustomerDetailName) {
		String result2 = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (SO_CustDetails.get() == null || SO_CustDetails.get().length == 0) {
			SO_CustDetails.set(excelUtils.readAllDataInExcel(Filepath, "Customer SO Details"));
		}
		String[] headers = SO_CustDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(CustomerDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(SOCustomerDetailsName.CUST_ID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < SO_CustDetails.get().length; i++) {
			if (SO_CustDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(CustomerName.trim())) {
				result2 = SO_CustDetails.get()[i][columnIndex];
				break;
			}
		}
		return result2;
	}

	/**
	 * This Enum 'SI Customer Details Name column Name'
	 */
	public enum SICustomerDetailsName {
		Customer_Name, Currency, SI_Type, Loc_Id, Unit, GST_Classification, GST_Type
	}

	/**
	 * get SI Line Details for Given Item
	 * 
	 * @author Vimal - RMITS
	 * @param Customer_Name
	 * @param LineDetailName
	 * @return String result
	 */
	public String getSILineDetailsforGivenItem(String Customer_Name, SICustomerDetailsName LineDetailName) {
		String result = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (SI_CustomerDetails.get() == null || SI_CustomerDetails.get().length == 0) {
			SI_CustomerDetails.set(excelUtils.readAllDataInExcel(Filepath, "SI Details"));
		}
		String[] headers = SI_CustomerDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(SICustomerDetailsName.Customer_Name.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < SI_CustomerDetails.get().length; i++) {
			if (SI_CustomerDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(Customer_Name.trim())) {
				result = SI_CustomerDetails.get()[i][columnIndex];
				break;
			}
		}
		return result;
	}

	/**
	 * This Enum 'Open So Amendment Header Details Name column Name'
	 */
	public enum OpenSOAmdDetails {
		LOCATION_ID, UNIT, CUSTOMER_ID, CUSTOMER_NAME, OSO_NO
	}

	/**
	 * This Enum 'Open So Amendment Line Details Name column Name'
	 */
	public enum OpenSoAmdLinesDetails {
		Item_ID, Item_Desc, UOM, Existing_Price,
	}

	/**
	 * Get Open So Amd Header Details
	 * 
	 * @author Kowshik
	 * 
	 * @param Location
	 * @param LineDetailName
	 * @return String result
	 */
	public String getOpensalesOrderAmdforGivenItem(String Location, OpenSOAmdDetails LineDetailName) {
		String result = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (Open_Sale_Order.get() == null || Open_Sale_Order.get().length == 0) {
			Open_Sale_Order.set(excelUtils.readAllDataInExcel(Filepath, "Open Sales"));
		}
		String[] headers = Open_Sale_Order.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(OpenSOAmdDetails.LOCATION_ID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < Open_Sale_Order.get().length; i++) {
			if (Open_Sale_Order.get()[i][itemColumnIndex].trim().equalsIgnoreCase(Location.trim())) {
				result = Open_Sale_Order.get()[i][columnIndex];
				break;
			}
		}
		return result;
	}

	/**
	 * Get Open so Line Details
	 * 
	 * @author Kowshik
	 * 
	 * @param ItemID
	 * @param LineDetailName
	 * @return String result
	 */
	public String getLinesDetailsforGivenItem(String ItemID, OpenSoAmdLinesDetails LineDetailName) {
		String result = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (Open_SO_Lines_details.get() == null || Open_SO_Lines_details.get().length == 0) {
			Open_SO_Lines_details.set(excelUtils.readAllDataInExcel(Filepath, "OpenSales_Lines"));
		}
		String[] headers = Open_SO_Lines_details.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(OpenSoAmdLinesDetails.Item_ID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < Open_SO_Lines_details.get().length; i++) {
			if (Open_SO_Lines_details.get()[i][itemColumnIndex].trim().equalsIgnoreCase(ItemID.trim())) {
				result = Open_SO_Lines_details.get()[i][columnIndex];
				break;
			}
		}
		return result;
	}

	/**
	 * This Enum of get Columns Of Open SO Amendment
	 * 
	 * @author Kowshik
	 */
	public enum OpenSOHeaderDetailsName {
		LOCATION_ID, UNIT, CUSTOMER_ID, CUSTOMER_NAME, OSO_NO
	}

	/**
	 * get Header Details Open So Amd
	 * 
	 * @param Location
	 * @param HeaderDetailName
	 * @return String
	 * @author Kowshik
	 */
	public String getOpenSOHeaderDetails(String Location, OpenSOHeaderDetailsName HeaderDetailName) {
		String result2 = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (Open_SO_HeaderDetails.get() == null || Open_SO_HeaderDetails.get().length == 0) {
			Open_SO_HeaderDetails.set(excelUtils.readAllDataInExcel(Filepath, "OPEN SALES"));
		}
		String[] headers = Open_SO_HeaderDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(HeaderDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(OpenSOHeaderDetailsName.LOCATION_ID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < Open_SO_HeaderDetails.get().length; i++) {
			if (Open_SO_HeaderDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(Location.trim())) {
				result2 = Open_SO_HeaderDetails.get()[i][columnIndex];
				break;
			}
		}
		return result2;
	}
}
