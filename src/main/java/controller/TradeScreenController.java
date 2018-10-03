package controller;

import java.time.LocalDate;

import org.apache.log4j.Logger;

import dao.TradeSettlementDaoImpl;
import model.Currency;
import model.SettledTrade;
import model.TradeType;
import service.ITradeSettlementService;
import service.TradeSettlementServiceImpl;

/**
 * TradeController Class
 * This class used as controller class
 * to get settled trades along-with consolidated.
 * 
 */
public class TradeScreenController {
	/** 
	   * Getting Logging object to generate loggs.
	   */
	private	 static Logger log = Logger.getLogger(TradeScreenController.class);
		
		/**
		 * Main method to create trade and generate report
		 * @param args
		 * @throws Exception 
		 */
		public static void main(String args[]) throws Exception {		
				ITradeSettlementService settlementService =  new TradeSettlementServiceImpl(new TradeSettlementDaoImpl()); 
									
				createSampleTrades(settlementService);				
				
				double outgoingGross = settlementService.getTradeAggregate(LocalDate.of(2018, 9, 21), TradeType.BUY);
				
				generateOutgoingAggregrateReport(LocalDate.of(2018, 9, 21), outgoingGross);
				
				double incomingGross = settlementService.getTradeAggregate(LocalDate.of(2018, 9, 23), TradeType.SELL);
				
				generateIncomingAggregrateReport(LocalDate.of(2018, 9, 23), incomingGross);
				
				String topOutgoingEntity = settlementService.getEntityRanking(LocalDate.of(2018, 9, 21), TradeType.BUY);
				
				generateBuyEntityReport(LocalDate.of(2018, 9, 21), topOutgoingEntity);
				
				String topIncomingEntity = settlementService.getEntityRanking(LocalDate.of(2018, 9, 23), TradeType.SELL);			
			
				generateSellEntityReport(LocalDate.of(2018, 9, 23), topIncomingEntity);			
		}
		private static void generateSellEntityReport(LocalDate date, String topIncomingEntity) {
			log.info("                                                                                                ");
			log.info("                                                                                                ");
			log.info("Top Entity for Incoming based on Total Traded Amount in USD ");
			log.info("Settlement_Date         Entity-Name    ");
			log.info("------------------------------------------");				
		if(topIncomingEntity != null) {
				log.info(date+"             "+topIncomingEntity);
		}else {
			log.info(date+"               No Trade/Entity available");
		}
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
			if(topOutgoingEntity != null) {
				log.info(date+"             "+topOutgoingEntity);
			}else {
				log.info(date+"               No Trade/Entity available");
			}
			log.info("------------------------------------------");
			
		}
		/**
		 * Method to create sample trade data.
		 * @param settlementService 
		 
		 * @throws Exception 
		 */
		public static void createSampleTrades(ITradeSettlementService settlementService) throws Exception {		
			
			settlementService.saveTrade(new SettledTrade("ACC", LocalDate.of(2018, 9, 21), Currency.GBP, 1.3f, 1000, TradeType.BUY, 95.01));
			settlementService.saveTrade(new SettledTrade("ACC", LocalDate.of(2018, 9, 22),	Currency.GBP, 1.3f, 900, TradeType.BUY, 121.01));
			settlementService.saveTrade(new SettledTrade("ABC", LocalDate.of(2018, 9, 23), Currency.GBP, 1.3f, 100, TradeType.BUY, 5.01));
			settlementService.saveTrade(new SettledTrade("AXS", LocalDate.of(2018, 9, 24), Currency.GBP, 1.3f, 100, TradeType.BUY, 95.01));
			settlementService.saveTrade(new SettledTrade("AXS", LocalDate.of(2018, 9, 24), Currency.GBP, 1.3f, 780, TradeType.BUY, 95.01));
			settlementService.saveTrade(new SettledTrade("AXS", LocalDate.of(2018, 9, 25), Currency.GBP, 1.3f, 780, TradeType.SELL, 95.01));
			
			settlementService.saveTrade(new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), Currency.AED, 0.27f, 600, TradeType.SELL, 95.01));
			settlementService.saveTrade(new SettledTrade("AXS", LocalDate.of(2018, 9, 22), Currency.AED, 0.27f, 100, TradeType.SELL, 121.01));
			settlementService.saveTrade(new SettledTrade("ABCD", LocalDate.of(2018, 9, 23), 	Currency.AED, 0.27f, 500, TradeType.SELL, 5.01));
			settlementService.saveTrade(new SettledTrade("THG", LocalDate.of(2018, 9, 24), Currency.AED, 0.27f, 200, TradeType.SELL, 95.01));
			settlementService.saveTrade(new SettledTrade("ABCD", LocalDate.of(2018, 9, 23), 	Currency.AED, 0.27f, 800, TradeType.SELL, 5.01));
			settlementService.saveTrade(new SettledTrade("ABCD", LocalDate.of(2018, 9, 25), 	Currency.AED, 0.27f, 800, TradeType.BUY, 5.01));
			
			settlementService.saveTrade(new SettledTrade("DXB", LocalDate.of(2018, 9, 21), Currency.USD, 1.0f, 1000, TradeType.BUY, 95.01));
			settlementService.saveTrade(new SettledTrade("ACC", LocalDate.of(2018, 9, 22),	Currency.USD, 1.0f, 900, TradeType.BUY, 121.01));
			settlementService.saveTrade(new SettledTrade("ABC", LocalDate.of(2018, 9, 23), Currency.USD, 1.0f, 100, TradeType.SELL, 5.01));
			settlementService.saveTrade(new SettledTrade("AMZN", LocalDate.of(2018, 9, 24), Currency.USD, 1.0f, 100, TradeType.SELL, 95.01));
			settlementService.saveTrade(new SettledTrade("AMZN", LocalDate.of(2018, 9, 24), Currency.USD, 1.0f, 780, TradeType.SELL, 95.01));
			settlementService.saveTrade(new SettledTrade("IBM", LocalDate.of(2018, 9, 25), Currency.USD, 1.0f, 780, TradeType.SELL, 95.01));
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
			if(incomingGross != 0.0) {
				log.info(date+"                        "+incomingGross);
			}else {
				log.info(date+"               No Trade/Entity available");
				}			
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
			 if(outgoingGross != 0.0) {
				 log.info(localDate+"                        "+outgoingGross);
				}else {
					log.info(localDate+"               No Trade/Entity available");
				}
			log.info("--------------------------------------------------------");
		}

}
