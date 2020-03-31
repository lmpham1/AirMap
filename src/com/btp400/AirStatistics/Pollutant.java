package com.btp400.AirStatistics;

public class Pollutant {
	private String fullName;
	private String codeName;
	private int    id;
	private String sources;
	private String effects;
	private String unit;
	//private LimitConcentration	limit;
	private static int counter = 0;
	
	public Pollutant() {
		fullName = "";
		codeName = "";
		id = counter++;
		sources = "";
		effects = "";
		unit = "";
	}
	
	public Pollutant(String name, String code, String un) {
		this();
		try {
			setFullName(name);
			setCodeName(code);
			setUnit(un);
		}
		catch(IllegalArgumentException iae) {
			throw iae;
		}
	}
	
	public void setFullName(String name) {
		if (name != null && name.length() > 0)
			fullName = name;
		else
			throw new IllegalArgumentException("Invalid full name input!");
	}
	
	public void setCodeName(String code) {
		if (code != null && code.length() > 0)
			codeName = code;
		else
			throw new IllegalArgumentException("Invalid code name input!");
	}
	
	public void setUnit(String un) {
		if (un != null && un.length() > 0)
			unit = un;
		else
			throw new IllegalArgumentException("Invalid unit input!");
	}
	
	public void setDescription(String source, String effect) {
		if (source != null && source.length() > 0)
			sources = source;
		else sources = "";
		if (effect != null && source.length() > 0)
			effects = effect;
		else effects = "";
	}
	
	public int getID() {
		return id;
	}
	
	public String getCodeName() {
		return codeName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public String getSources() {
		return sources;
	}
	
	public String getEffects() {
		return effects;
	}
	/*
	public LimitConcentration getLimitConcentration() {
		return limit;
	}
	*/
	
	@Override
	public boolean equals(Object x) {
		if (x instanceof Pollutant) {
			Pollutant obj = (Pollutant) x;
			return (
					id == obj.id
					&& fullName.equals(obj.fullName)
					&& codeName.equals(obj.codeName)
					&& sources.equals(obj.sources)
					&& effects.equals(obj.effects)
					&& unit.equals(obj.unit)
			);
		}
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result * id;
		result = prime * result + fullName.hashCode();
		result = prime * result + codeName.hashCode();
		result = prime * result + sources.hashCode();
		result = prime * result + effects.hashCode();
		result = prime * result + unit.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += codeName + " (" + fullName + ") Details:\n";
		s += String.format("%25s: %d\n", "Pullutant ID", id);
		s += String.format("%25s: %s\n", "Display Unit", unit);
		s += String.format("%25s: %s\n", "Sources", sources);
		s += String.format("%25s: %s\n", "Effects", effects);
		/*
		s += limit.toString();
		*/
		return s;
	}
}
