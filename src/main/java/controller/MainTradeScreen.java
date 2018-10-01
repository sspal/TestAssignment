package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			
			double outgoingGross = controller.getTradeAggregate(getDate("21/Sep/2018"), TradeType.BUY);
			
			generateOutgoingAggregrateReport(getDate("21/Sep/2018"), outgoingGross);
			
			double incomingGross = controller.getTradeAggregate(getDate("23/Sep/2018"), TradeType.SELL);
			
			generateIncomingAggregrateReport(getDate("23/Sep/2018"), incomingGross);
			
			String topOutgoingEntity = controller.getEntityRanking(getDate("21/Sep/2018"), TradeType.BUY);
			
			generateBuyEntityReport(getDate("21/Sep/2018"), topOutgoingEntity);
			
			String topIncomingEntity = controller.getEntityRanking(getDate("23/Sep/2018"), TradeType.SELL);			
		
			generateSellEntityReport(getDate("23/Sep/2018"), topIncomingEntity);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void generateSellEntityReport(Date date, String topIncomingEntity) {
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
	private static void generateBuyEntityReport(Date date, String topOutgoingEntity) {
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
		
		controller.createTrade(new SettledTrade("Cipla", getDate("21/Sep/2018"), "INR", 0.014f, 1000, TradeType.BUY, 95.01));
		controller.createTrade(new SettledTrade("Axis", getDate("21/Sep/2018"),	"AED", 0.014f, 900, TradeType.BUY, 121.01));
		controller.createTrade(new SettledTrade("ABC", getDate("21/Sep/2018"), "SAR", 0.73f, 100, TradeType.BUY, 5.01));
		controller.createTrade(new SettledTrade("Cipla", getDate("21/Sep/2018"), "INR", 0.014f, 100, TradeType.BUY, 95.01));
		controller.createTrade(new SettledTrade("Cipla", getDate("21/Sep/2018"), "INR", 0.014f, 600, TradeType.SELL, 95.01));
		controller.createTrade(new SettledTrade("Axis", getDate("21/Sep/2018"), "AED", 0.014f, 100, TradeType.SELL, 121.01));
		controller.createTrade(new SettledTrade("ABC", getDate("21/Sep/2018"), 	"SAR", 0.73f, 500, TradeType.SELL, 5.01));
		controller.createTrade(new SettledTrade("Cipla", getDate("21/Sep/2018"), "INR", 0.014f, 200, TradeType.SELL, 95.01));		
		
	}
	private static Date getDate(String date) throws ParseException {		
		return format.parse(date);
	}
	/**
	 * Simpledateformat instance
	 */
	
	private static SimpleDateFormat format = new SimpleDateFormat(Constants.Date_Pattern);
	
	
	/**
	 * This method generates report for everyday basis Gross Incoming/Outgoing figures.
	 * @param outgoingGross 
	 * @param date	
	 */
	private static void generateIncomingAggregrateReport(Date date, double incomingGross) {
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
	 * @param date	
	 */
	private static void generateOutgoingAggregrateReport(Date date, double outgoingGross) {
		log.info("                                                                                                ");
		log.info("                                                                                                ");
		log.info("Trade Amount Settled Everyday in USD Outgoing ");
		log.info("Settlement-Date                 Amount-Outgoing");
		log.info("---------------------------------------------------");		
		 
		 log.info(date+"                        "+outgoingGross);
		
		log.info("--------------------------------------------------------");
	}
}
