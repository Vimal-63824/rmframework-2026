package commonlocators.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 20, 2025
 */
public class ProductionOrderLocators {

	/** @locatorType XPATH */
	public static final String FindMRPNoLovBtn = "//button[@id='P6419631010_FIND_SCH_MRP_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String FindSearchBtn = "//button[@id='B1131360267064276001']";

	/** @locatorType XPATH */
	public static final String Find_TableData_ProdNo(String MRPNo) {
		return "//td[contains(text(),'" + MRPNo + "')]/../td[4]/a";
	}

	/** @locatorType XPATH */
	public static final String Find_TableData_Status(String MRPNo) {
		return "//td[contains(text(),'" + MRPNo + "')]/../td[12]/div";
	}

}
