package service;

import java.time.LocalDate;

import model.SettledTrade;
import model.TradeType;
/**
 * 
 * Trade Settlement Service Interface
 *
 */
public interface ITradeSettlementService {
	/**
	 * public saveTrade() method for storing settled trades in Memory(DB)
	 * @param trade - SettledTrade
	 * @return void
	 */
	void saveTrade(SettledTrade trade) throws Exception;
	/**
	 * public getTradeAggregate() method for trade aggregrated figure calculation
	 * @param requestDate - Date
	 * @param TradeType - tradeType
	 * @return double
	 */
	double getTradeAggregate(LocalDate requestDate, TradeType tradeType) throws Exception;
	/**
	 * public getEntityRanking() method for trade ranking figure calculation
	 * @param requestDate - LocalDate
	 * @param TradeType - tradeType
	 * @return String
	 * @throws Exception 
	 */
	String getEntityRanking(LocalDate requestDate, TradeType tradeType) throws Exception;
}
