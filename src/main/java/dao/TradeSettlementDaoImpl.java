package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.SettledTrade;
import model.TradeType;

public class TradeSettlementDaoImpl implements ITradeSettlementDao{

	private Map<Date, Map<TradeType, List<SettledTrade>>> allTradesMap;
	
	public void saveSettledTrade(SettledTrade trade) throws Exception {
		Map<TradeType, List<SettledTrade>> tradeMapbyDate;
		List<SettledTrade> tradeListbyType;
		if(trade == null || trade.getSettleDate() == null || trade.getTradeType() == null) {
			throw new Exception("Trade or Trade Settlement Date or Trade Type cannot be Null");
		}
		if(allTradesMap == null) {
			allTradesMap = new HashMap<Date, Map<TradeType, List<SettledTrade>>>();		
			tradeMapbyDate = new HashMap<TradeType, List<SettledTrade>>();
			
			tradeListbyType = new ArrayList<SettledTrade>();
			tradeListbyType.add(trade);
		
			tradeMapbyDate.put(trade.getTradeType(), tradeListbyType);		
			allTradesMap.put(trade.getSettleDate(), tradeMapbyDate);
		}
		else {
			tradeMapbyDate = allTradesMap.get(trade.getSettleDate());
			if(tradeMapbyDate == null) {
				tradeMapbyDate = new HashMap<TradeType, List<SettledTrade>>();				
				tradeListbyType = new ArrayList<SettledTrade>();
				
				tradeListbyType.add(trade);
			
				tradeMapbyDate.put(trade.getTradeType(), tradeListbyType);			
				allTradesMap.put(trade.getSettleDate(), tradeMapbyDate);
			}
			else {
				tradeListbyType = tradeMapbyDate.get(trade.getTradeType());
				if(tradeListbyType == null) {
					tradeListbyType = new ArrayList<SettledTrade>();
					
					tradeListbyType.add(trade);					
					tradeMapbyDate.put(trade.getTradeType(), tradeListbyType);
				}
				else {
					tradeListbyType.add(trade);					
				}
			}
		}		
	}

	public Map<Date, Map<TradeType, List<SettledTrade>>> getSettledTrades() throws Exception {

		return allTradesMap;
	}
}
