package dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import model.SettledTrade;
import model.TradeType;
/**
 * class for Storing and retrieving trades in memory
 */
public interface ITradeSettlementDao {
	/**
	 * public saveSettledTrades() method for storing settled trades in Memory(DB)
	 * @param trade - SettledTrade
	 * @return void
	 */
	void saveSettledTrade(SettledTrade trade) throws Exception;
	/**
	 * public getSettledTrades() method for retrieving settled trades from Memory(DB)
	 
	 * @return Map<Date, Map<TradeType, SettledTrade>>
	 */
	 Map<Date, Map<TradeType, List<SettledTrade>>> getSettledTrades() throws Exception;
}
