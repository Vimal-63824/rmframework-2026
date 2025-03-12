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
	public static final String Find_TableData_ProdNoWithMRPNo(String MRPNo) {
		return "//*[contains(text(),'" + MRPNo + "')]/../td[4]/a";
	}
	
	/** @locatorType XPATH */
	public static final String Find_TableData_ProdNo(String ProdNo) {
		return "//*[contains(text(),'" + ProdNo + "')]/../../td[4]/a";
	}

	/** @locatorType XPATH */
	public static final String Find_TableData_Status(String DocNo) {
		return "//td[contains(text(),'" + DocNo + "')]/../td[12]/div";
	}

	/** @locatorType XPATH */
	public static final String FindProdOrderNoLovBtn = "//button[@id='P_FIND_PROD_ORD_ORD_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String ProdOrderReferenceDisplay = "//input[@id='P19631010001_PROHD_REFERENCE']";
	/** @locatorType XPATH */
	public static final String RoutingLabel = "//label[text()='Routing']";
	/** @locatorType XPATH */
	public static final String MaterialRequirementLabel = "//label[text()='Material Requirement']";
	/** @locatorType XPATH */
	public static final String ResourcesLabel = "//label[text()='Resource']";
	/** @locatorType XPATH */
	public static final String ScrapByProductLabel = "//label[text()='Scrap / By Product']";
	/** @locatorType XPATH */
	public static final String MultipleResourcesLabel = "//label[text()='Multiple Resources']";
	/** @locatorType XPATH */
	public static final String ExceptionLabel = "//label[text()='Exception']";
	/** @locatorType XPATH */
	public static final String RoutingProcessNameTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[4]";
	/** @locatorType XPATH */
	public static final String RoutingConsumptionWHTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[8]";
	/** @locatorType XPATH */
	public static final String RoutingRecipientWHTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[9]";
	/** @locatorType XPATH */
	public static final String MaterialReqProcessTableDataLists = "(//div[@id='material_ig_grid_vc']//table)[2]/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String MaterialReqRMItemTableDataLists = "(//div[@id='material_ig_grid_vc']//table)[2]/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String ResourcesProcessTableDataLists = "(//div[@id='resource_ig_grid_vc']//table)[2]/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String HeaderSaveIcon = "//button[@id='B862141228532932155']";
	/** @locatorType XPATH */
	public static final String ProdOrderPostBtn = "//button[@id='B944728616712869764']";
	/** @locatorType XPATH */
	public static final String ExceptionTableContainer = "//div[@id='R967011785583947865_ig']";
	/** @locatorType XPATH */
	public static final String ExceptionNoDataFoundContainer = "//div[@id='R967011785583947865_ig']//span[text()='No data found']";
}
