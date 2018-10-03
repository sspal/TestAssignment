package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.SettlementException;
import model.SettledTrade;
import model.TradeType;

public class TradeSettlementDaoImpl implements ITradeSettlementDao{

	private Map<LocalDate, Map<TradeType, List<SettledTrade>>> allTradesMap;
	/**
	 * This method creates trade entry after doing settlement.
	 * @param trade - SettledTrade
	 * @throws Exception 
	 */
	public void saveSettledTrade(SettledTrade trade) throws Exception {
		Map<TradeType, List<SettledTrade>> tradeMapbyDate;
		List<SettledTrade> tradeListbyType;
		if(trade == null || trade.getSettleDate() == null || trade.getTradeType() == null) {
			throw new SettlementException("Trade or Trade Settlement Date or Trade Type cannot be Null");
		}
		allTradesMap = (allTradesMap == null) ? (new HashMap<LocalDate, Map<TradeType, List<SettledTrade>>>()) : allTradesMap;
		tradeMapbyDate = (allTradesMap.get(trade.getSettleDate()) == null) ? (new HashMap<TradeType, List<SettledTrade>>()) : allTradesMap.get(trade.getSettleDate());
		
		tradeListbyType = (tradeMapbyDate.get(trade.getTradeType()) == null) ? new ArrayList<SettledTrade>() : (tradeMapbyDate.get(trade.getTradeType()));
		
		tradeListbyType.add(trade);
	
		tradeMapbyDate.put(trade.getTradeType(), tradeListbyType);			
		allTradesMap.put(trade.getSettleDate(), tradeMapbyDate);
	}
	/**
	 * This method return  trade map according to the date.
	 * @param requestDate - LocalDate
	 * @param TradeType - tradeType
	 * @return List<SettledTrade> - List<SettledTrade>
	 * @throws Exception 
	 */
	public List<SettledTrade> getSettledTrades(LocalDate requestDate,
			TradeType tradeType) throws Exception {
		List<SettledTrade> tradeList = null;
		Map<TradeType, List<SettledTrade>> tradeMapbyDate = allTradesMap.get(requestDate);
		if(tradeMapbyDate != null) {
			tradeList = tradeMapbyDate.get(tradeType);
		}
		return tradeList;
	}
}
