package com.btp400.AirStatistics;

public class PollutantStatistic extends Pollutant{
	private double concentration = 0;
	//private int dangerLevel = 0;
	private int aqi = 0;
	private String status = "";
	
	public PollutantStatistic() {
		super();
	}
	
	public PollutantStatistic(String name, String code, String un) {
		super(name, code, un);
	}
	
	public void setConcentration(double c) {
		if (c > 0)
			concentration = c;
	/*
		if (super.getLimitConcentration().getSecondLevel() != 0) {
			if (c >= super.getLimitConcentration().getSecondLevel())
				dangerLevel = 2;
			else if (c >= super.getLimitConcentration().getFirstLevel())
				dangerLevel = 1;
			else
				dangerLevel = 0;
		}
		else {
			if (c >= super.getLimitConcentration().getFirstLevel())
				dangerLevel = 2;
			else
				dangerLevel = 0;
		}
	*/
	}
	
	public double getConcentration() {
		return concentration;
	}
	
	public void setAQI(int a) {
		if (a >= 0) {
			aqi = a;
		}
	}
	
	public int getAQI() {
		return aqi;
	}
	
	/*
	public int getDangerLevel() {
		return dangerLevel;
	}
	*/
	
	public void setStatus(String s) {
		if (s != null)
			status = s;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String toString() {
		String s ="";
		s += String.format("%25s: %s\n", "Pollution Code Name", this.getCodeName());
		//s += super.getLimitConcentration().toString();
		s += String.format("%25s: %.2f %s\n", " Actual Concentration", concentration, this.getUnit());
		s += String.format("%25s: %d\n", "Air Quality Index", aqi);
		s += String.format("%25s: %s\n", "Condition", status);
		return s;
	}
}
