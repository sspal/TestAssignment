package dao;

import java.time.LocalDate;
import java.util.List;

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
	 * @param requestDate - LocalDate
	 * @param TradeType - tradeType
	 * @return List<SettledTrade>
	 */
	List<SettledTrade> getSettledTrades(LocalDate requestDate, TradeType tradeType) throws Exception;
}
