package com.btp400.location;

import java.util.ArrayList;

import com.btp400.AirStatistics.*;

public class LocationStatistic extends Location{
	private ArrayList<PollutantStatistic> pollutantStats = new ArrayList<PollutantStatistic>();
	
	public LocationStatistic() {
		super(); 
	}
	
	public LocationStatistic(double _lat, double _long) {
		super(_lat, _long);
	}
	
	public void updatePollutants(ArrayList<Pollutant> arr) {
		for (int i = 0; i < arr.size(); ++i) {
			PollutantStatistic temp = new PollutantStatistic(arr.get(i).getFullName(), arr.get(i).getCodeName(), arr.get(i).getUnit());
			temp.setDescription(arr.get(i).getSources(), arr.get(i).getEffects());
			pollutantStats.add(temp);
		}
	}
	
	public ArrayList<PollutantStatistic> getAllPollutants(){
		return pollutantStats;
	}
	
	public boolean removePollutant(int pollutantID) {
		for (int i = 0; i < pollutantStats.size(); ++i) {
			if (pollutantStats.get(i).getID() == pollutantID) {
				pollutantStats.remove(pollutantStats.get(i));
				return true;
			}
		}
		return false;
	}
	
	public boolean equals(Object x) {
		if (x instanceof LocationStatistic && super.equals(x)) {
			LocationStatistic other = (LocationStatistic) x;
			return (
					pollutantStats.equals(other.pollutantStats)
				);
		}
		else
			return false;
	}
	
	public String toString() {
		String s = "";
		s += "Display air statistic for: \n\n";
		s += super.toString();
		s += "----------------------------------------------------\n";
		for (PollutantStatistic i : pollutantStats) {
			s += i.toString();
			s += "----------------------------------------------------\n";
		}
		
		s += "\n";
		return s;
	}
}
