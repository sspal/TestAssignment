package model;

import java.time.LocalDate;

/**
 * 
 * SettledTrade Class extends to add extra behaviors of actualSettlementDate and TradeTotalValue
 *
 */
public class SettledTrade extends Trade{
	private LocalDate settleDate;
	private double tradeTotalValue;
/**
 * Constructor that sets values into super class constructor
 * @param tradeEntity
 * @param instructDate
 * @param currency
 * @param fxRate
 * @param units
 * @param tradeType
 * @param unitPrice
 */
	public SettledTrade(String tradeEntity, LocalDate instructDate, Currency currency, float fxRate,
			long units, TradeType tradeType, double unitPrice) {
		super(tradeEntity, instructDate, currency, fxRate, units, tradeType, unitPrice);
		}

	public LocalDate getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(LocalDate settleDate) {
		this.settleDate = settleDate;
	}

	public double getTradeTotalValue() {
		return tradeTotalValue;
	}

	public void setTradeTotalValue(double tradeTotalValue) {
		this.tradeTotalValue = tradeTotalValue;
	}

	
}
