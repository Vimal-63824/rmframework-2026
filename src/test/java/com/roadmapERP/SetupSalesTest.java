package com.roadmapERP;

import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import commonfunctions.supplychainmanagement.SalesCataloguePrice;
import commonfunctions.supplychainmanagement.SalesPriceListFunctions;
import commonfunctions.supplychainmanagement.SetupSalesFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class SetupSalesTest extends RoadMapERPTest {

	private static SetupSalesFunctions SetUpSales = new SetupSalesFunctions();


	/**Create Catalog Price with Similar Stock and Customer UOM - Dom. Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Test(priority = 1, groups = {
	"Setup Sales Screen" }, description = "Sales Master Configurations")
	public void verifySetupSalesCreates()  {
		SetUpSales.CreatesSalesSetup();
	}
}
