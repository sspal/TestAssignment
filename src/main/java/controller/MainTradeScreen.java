package controller;

import java.time.LocalDate;

import org.apache.log4j.Logger;

import model.Constants;
import model.SettledTrade;
import model.TradeType;


/**
 * TradeScreen Class
 * This class used as main controller class
 * to input trade data list and get consolidated reports.
 * 
 */
public class MainTradeScreen {
	/** 
	   * Getting Logging object to generate loggs.
	   */
	private	 static Logger log = Logger.getLogger(MainTradeScreen.class);
	public static void main(String args[]) {		
		TradeController controller = new TradeController();
		
		try {			
			createSampleTrades(controller);				
			
			double outgoingGross = controller.getTradeAggregate(LocalDate.of(2018, 9, 21), TradeType.BUY);
			
			generateOutgoingAggregrateReport(LocalDate.of(2018, 9, 21), outgoingGross);
			
			double incomingGross = controller.getTradeAggregate(LocalDate.of(2018, 9, 23), TradeType.SELL);
			
			generateIncomingAggregrateReport(LocalDate.of(2018, 9, 23), incomingGross);
			
			String topOutgoingEntity = controller.getEntityRanking(LocalDate.of(2018, 9, 21), TradeType.BUY);
			
			generateBuyEntityReport(LocalDate.of(2018, 9, 21), topOutgoingEntity);
			
			String topIncomingEntity = controller.getEntityRanking(LocalDate.of(2018, 9, 23), TradeType.SELL);			
		
			generateSellEntityReport(LocalDate.of(2018, 9, 23), topIncomingEntity);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void generateSellEntityReport(LocalDate date, String topIncomingEntity) {
		log.info("                                                                                                ");
		log.info("                                                                                                ");
		log.info("Top Entity for Incoming based on Total Traded Amount in USD ");
		log.info("Settlement_Date         Entity-Name    ");
		log.info("------------------------------------------");				
	
			log.info(date+"             "+topIncomingEntity);
		
		log.info("------------------------------------------");
		
	}
	/**
	 * This method generates Outgoing trades report.
	 * @param buyEntities - list of consolidated entities buy figure
	 */
	private static void generateBuyEntityReport(LocalDate date, String topOutgoingEntity) {
		log.info("                                                                                                ");
		log.info("                                                                                                ");
		log.info("Top Entity for Outgoing based on Total Traded Amount in USD ");
		log.info("Settlement_Date         Entity-Name    ");
		log.info("------------------------------------------");				
	
			log.info(date+"             "+topOutgoingEntity);
		
		log.info("------------------------------------------");
		
	}
	/**
	 * Method to create sample trade data.
	 * @param controller 
	 
	 * @throws Exception 
	 */
	public static void createSampleTrades(TradeController controller) throws Exception {		
		
		controller.createTrade(new SettledTrade("SUN", LocalDate.of(2018, 9, 21), Constants.CURRENCY_GBP, 1.3f, 1000, TradeType.BUY, 95.01));
		controller.createTrade(new SettledTrade("ACC", LocalDate.of(2018, 9, 22),	Constants.CURRENCY_GBP, 1.3f, 900, TradeType.BUY, 121.01));
		controller.createTrade(new SettledTrade("ABC", LocalDate.of(2018, 9, 23), Constants.CURRENCY_GBP, 1.3f, 100, TradeType.BUY, 5.01));
		controller.createTrade(new SettledTrade("AXS", LocalDate.of(2018, 9, 24), Constants.CURRENCY_GBP, 1.3f, 100, TradeType.BUY, 95.01));
		controller.createTrade(new SettledTrade("AXS", LocalDate.of(2018, 9, 24), Constants.CURRENCY_GBP, 1.3f, 780, TradeType.BUY, 95.01));
		controller.createTrade(new SettledTrade("AXS", LocalDate.of(2018, 9, 25), Constants.CURRENCY_GBP, 1.3f, 780, TradeType.SELL, 95.01));
		
		controller.createTrade(new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), Constants.CURRENCY_AED, 0.27f, 600, TradeType.SELL, 95.01));
		controller.createTrade(new SettledTrade("AXS", LocalDate.of(2018, 9, 22),Constants.CURRENCY_AED, 0.27f, 100, TradeType.SELL, 121.01));
		controller.createTrade(new SettledTrade("ABCD", LocalDate.of(2018, 9, 23), 	Constants.CURRENCY_AED, 0.27f, 500, TradeType.SELL, 5.01));
		controller.createTrade(new SettledTrade("THG", LocalDate.of(2018, 9, 24), Constants.CURRENCY_AED, 0.27f, 200, TradeType.SELL, 95.01));
		controller.createTrade(new SettledTrade("ABCD", LocalDate.of(2018, 9, 23), 	Constants.CURRENCY_AED, 0.27f, 800, TradeType.SELL, 5.01));
		controller.createTrade(new SettledTrade("ABCD", LocalDate.of(2018, 9, 25), 	Constants.CURRENCY_AED, 0.27f, 800, TradeType.BUY, 5.01));
	}
		
	/**
	 * This method generates report for everyday basis Gross Incoming/Outgoing figures.
	 * @param outgoingGross 
	 * @param date	
	 */
	private static void generateIncomingAggregrateReport(LocalDate date, double incomingGross) {
		log.info("                                                                                                ");
		log.info("                                                                                                ");
		log.info("Trade Amount Settled Everyday in USD Incoming ");
		log.info("Settlement-Date                 Amount-Incoming");
		log.info("---------------------------------------------------");		
		 
		 log.info(date+"                        "+incomingGross);
		
		log.info("--------------------------------------------------------");
	}
	/**
	 * This method generates report for everyday basis Gross Incoming/Outgoing figures.
	 * @param outgoingGross 
	 * @param localDate	
	 */
	private static void generateOutgoingAggregrateReport(LocalDate localDate, double outgoingGross) {
		log.info("                                                                                                ");
		log.info("                                                                                                ");
		log.info("Trade Amount Settled Everyday in USD Outgoing ");
		log.info("Settlement-Date                 Amount-Outgoing");
		log.info("---------------------------------------------------");		
		 
		 log.info(localDate+"                        "+outgoingGross);
		
		log.info("--------------------------------------------------------");
	}
}
