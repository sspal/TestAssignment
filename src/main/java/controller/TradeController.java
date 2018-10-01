package controller;

import java.util.Date;

import dao.TradeSettlementDaoImpl;
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
public class TradeController {
	 	
		/**
		 * This method creates trade entry after doing settlement.
		 * @param trade - SettledTrade
		 * @throws Exception 
		 */
		public void createTrade(SettledTrade trade) throws Exception {
			settlementService = getTradeSettlementService();
			settlementService.saveSettledTrade(trade);
		}
		
		/**
		 * This method gets aggregated figures of trades for a date.
		 * @param requestDate -  Date
		 * @param tradeType - TradeType
		 * @return double  -double value of gross trade value
		 * @throws Exception 
		 */
		public double getTradeAggregate(Date requestDate, TradeType tradeType) throws Exception {
				settlementService = getTradeSettlementService();
				return settlementService.getTradeAggregate(requestDate, tradeType);
			}
			
	/**
	 * This method gets ranking figures of incoming trade entities.
	 * 
	 * @param requestDate
	 *            - Date
	 * @param tradeType
	 *            - TradeType
	 * @return String -Entity value of highest trade as per gross value
	 * @throws Exception
	 */
		public String getEntityRanking(Date requestDate, TradeType tradeType) throws Exception {
			settlementService = getTradeSettlementService();
			return settlementService.getEntityRanking(requestDate, tradeType);
		}
		/**
		 * ITradeSettlementService reference.
		 */
		private ITradeSettlementService settlementService;
		/**
		 * ITradeSettlementService instance generation if its null else returns existing instance.
		 */
		public ITradeSettlementService getTradeSettlementService() {
		if(settlementService == null) {
			settlementService = new TradeSettlementServiceImpl(new TradeSettlementDaoImpl()); 
			return settlementService;
		}
		else {
			return settlementService;
		}
	}

}
