package model;

import java.time.LocalDate;
import java.util.Random;

public class Trade {
	/**
	 * random generated tradeid
	 */
	private final long tradeId;
	/**
	 * tradeEntity
	 */
	private final String tradeEntity;
	/**
	 * instructionDate
	 */
	private final LocalDate instructDate;
	/**
	 * currency
	 */
	private final String currency;
	/**
	 * fxRate
	 */
	private final float fxRate;
	/**
	 * units
	 */
	private final long units;
	/**
	 * tradeType
	 */
	private final TradeType tradeType;
	/**
	 * unitPrice
	 */
	private double unitPrice;
	
	Random rand = new Random(); 
	/**
	 * Constructor to set object
	 * @param tradeEntity
	 * @param instructDate
	 * @param currency
	 * @param fxRate
	 * @param units
	 * @param tradeType
	 * @param unitPrice
	 */
	public Trade(String tradeEntity, LocalDate instructDate, 
			String currency, float fxRate,
			long units, TradeType tradeType, double unitPrice) {
		long rand_long = rand.nextLong(); 
		this.tradeId = rand_long;
		this.tradeEntity = tradeEntity;
		this.instructDate = instructDate;
		this.currency = currency;
		this.fxRate = fxRate;
		this.units = units;
		this.tradeType = tradeType;
		this.unitPrice = unitPrice;
	}


	public long getTradeId() {
		return tradeId;
	}


	public String getTradeEntity() {
		return tradeEntity;
	}


	public LocalDate getInstructDate() {
		return instructDate;
	}


	public String getCurrency() {
		return currency;
	}


	public float getFxRate() {
		return fxRate;
	}


	public long getUnits() {
		return units;
	}


	public TradeType getTradeType() {
		return tradeType;
	}


	public double getUnitPrice() {
		return unitPrice;
	}

	
}
