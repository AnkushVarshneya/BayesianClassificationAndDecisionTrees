/**
 * @(#)weather.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * testing only
 */

package data;

import Jama.Matrix;

public class Weather extends BaseData {
	public static final String FILE_NAME = "src/Data/DataSets/weather.csv";

	private String outlook;
	private String temperature;
	private String humidity;
	private String windy;
	private String decision;

	public Weather(String[] line) {
		super();
		this.outlook = line[0];
		this.temperature = line[1];
		this.humidity = line[2];
		this.windy = line[3];
		this.decision = line[4];
	}

	public Weather(String outlook, String temperature, String humidity, String windy, String decision) {
		super();
		this.outlook = outlook;
		this.temperature = temperature;
		this.humidity = humidity;
		this.windy = windy;
		this.decision = decision;
	}

	@Override
	public String toString() {
		return "weather [outlook=" + outlook + ", temperature=" + temperature + ", humidity=" + humidity + ", windy="
				+ windy + ", decision=" + decision + "]";
	}

	@Override
	public int getClassNumber() {
		if (this.decision.equals("yes"))
			return 1;
		if (this.decision.equals("no"))
			return 2;

		throw new RuntimeException("Bad class");
	}

	@Override
	public Matrix toMatrix() {
		Matrix aVector = new Matrix(DataTypeEnum.WEATHER.getNumberOfAttributes(), 1);
		int i = 0;

		aVector.set(i++, 0, this.outlook.hashCode());
		aVector.set(i++, 0, this.temperature.hashCode());
		aVector.set(i++, 0, this.humidity.hashCode());
		aVector.set(i++, 0, this.windy.hashCode());

		return aVector;
	}

	public String getOutlook() {
		return outlook;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getWindy() {
		return windy;
	}

	@Override
	public String getAttributesClassByNumber(int attributeNumber) {
		String ret = null;

		if (attributeNumber == 0)
			ret = getOutlook();
		if (attributeNumber == 1)
			ret = getTemperature();
		if (attributeNumber == 2)
			ret = getHumidity();
		if (attributeNumber == 3)
			ret = getWindy();
		if (attributeNumber == 4)
			ret = new Integer(getClassNumber()).toString();

		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((decision == null) ? 0 : decision.hashCode());
		result = prime * result + ((humidity == null) ? 0 : humidity.hashCode());
		result = prime * result + ((outlook == null) ? 0 : outlook.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result + ((windy == null) ? 0 : windy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (decision == null) {
			if (other.decision != null)
				return false;
		} else if (!decision.equals(other.decision))
			return false;
		if (humidity == null) {
			if (other.humidity != null)
				return false;
		} else if (!humidity.equals(other.humidity))
			return false;
		if (outlook == null) {
			if (other.outlook != null)
				return false;
		} else if (!outlook.equals(other.outlook))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		if (windy == null) {
			if (other.windy != null)
				return false;
		} else if (!windy.equals(other.windy))
			return false;
		return true;
	}
}
