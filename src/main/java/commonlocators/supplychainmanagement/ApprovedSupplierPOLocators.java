package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
public class ApprovedSupplierPOLocators {

	/** @locatorType XPATH */
	public static final String UnitLovButton = "//input[@data-testid='P367018501_SUPRPROD_PLNT']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String DocLovButton = "//input[@data-testid='P367018501_SUPRPROD_DOC_PFX']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SupplierLovButton = "//input[@data-testid='P367018501_SUPRPROD_SUPLR_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String ItemLovButton = "//input[@data-testid='P367018501_PROD_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String PriceBasisSelect = "//select[@data-testid='P367018501_SUPPROD_PUR_PRICE_BASIS']";
	/** @locatorType XPATH */
	public static final String HsnCodeButton = "//input[@data-testid='P367018501_SUPRPROD_HSN_CODE']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SaveButton = "//button[@data-testid='CREATE']";
	/** @locatorType XPATH */
	public static final String PostButton = "//button[@data-testid='Entry_Completed']";
	/** @locatorType XPATH */
	public static final String DocNumberLovButton = "//input[@data-testid='P36700185_DOC_NO']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindButton = "//button[@data-testid='Find_Lines_s']";

	/** @locatorType XPATH */
	public static final String ApprovedStatus(String DocNo) {
		return "//td[text()='" + DocNo + "']/../td[last()-1]/div[contains(@style,color)]";
	}

	/** @locatorType XPATH */
	public static final String LeadTimeInput = "//input[@data-testid='P367018501_SUPRPROD_LEAD_TIME']";
	/** @locatorType XPATH */
	public static final String ApprovedSupplierPoCheckBox = "//input[@data-testid='P36719401_PRODPLNT_APP_SUP_PUR_F']";
	/** @locatorType XPATH */
	public static final String GrnInspectionSelect = "//select[@data-testid='P36719401_PRODPLNT_CRIT_TYPE_3']";
}
