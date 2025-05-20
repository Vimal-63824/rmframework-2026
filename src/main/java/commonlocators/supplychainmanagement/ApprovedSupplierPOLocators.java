package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
public class ApprovedSupplierPOLocators {

	/** @locatorType XPATH */
	public static final String UnitLovButton = "//button[@id='P367018501_SUPRPROD_PLNT_lov_btn']";
	/** @locatorType XPATH */
	public static final String DocLovButton = "//button[@id='P367018501_SUPRPROD_DOC_PFX_lov_btn']";
	/** @locatorType XPATH */
	public static final String SupplierLovButton = "//button[@id='P367018501_SUPRPROD_SUPLR_ID_lov_btn']";
	/** @locatorType XPATH */
	public static final String ItemLovButton = "//button[@id='P367018501_PROD_ID_lov_btn']";
	/** @locatorType XPATH */
	public static final String PriceBasisSelect = "//select[@id='P367018501_SUPPROD_PUR_PRICE_BASIS']";
	/** @locatorType XPATH */
	public static final String HsnCodeButton = "//button[@id='P367018501_SUPRPROD_HSN_CODE_lov_btn']";
	/** @locatorType XPATH */
	public static final String SaveButton = "//button[@id='B3245400177054517028']";
	/** @locatorType XPATH */
	public static final String PostButton = "//button[@id='B1187882492788356201']";
	/** @locatorType XPATH */
	public static final String DocNumberLovButton = "//button[@id='P36700185_DOC_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String FindButton = "//button[@id='B1183371159186794287']";

	/** @locatorType XPATH */
	public static final String ApprovedStatus(String DocNo) {
		return "//td[text()='" + DocNo + "']/../td[last()]/div[contains(@style,color)]";
	}
}
