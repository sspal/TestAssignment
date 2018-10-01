package service;

import java.util.Date;

import model.SettledTrade;
import model.TradeType;
/**
 * 
 * Trade Settlement Service Interface
 *
 */
public interface ITradeSettlementService {
	/**
	 * public saveSettledTrades() method for storing settled trades in Memory(DB)
	 * @param trade - SettledTrade
	 * @return void
	 */
	void saveSettledTrade(SettledTrade trade) throws Exception;
	/**
	 * public getTradeAggregate() method for trade aggregrated figure calculation
	 * @param requestDate - Date
	 * @param TradeType - tradeType
	 * @return double
	 */
	double getTradeAggregate(Date requestDate, TradeType tradeType) throws Exception;
	/**
	 * public getEntityRanking() method for trade ranking figure calculation
	 * @param requestDate - Date
	 * @param TradeType - tradeType
	 * @return String
	 * @throws Exception 
	 */
	String getEntityRanking(Date requestDate, TradeType tradeType) throws Exception;
}
