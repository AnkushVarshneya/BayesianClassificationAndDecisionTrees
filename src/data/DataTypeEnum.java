/**
 * @(#)DataTypeEnum.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * either class a or b
 */

package data;

public enum DataTypeEnum {
	HEARTDISEASE("heartDisease", 5, 10), IRIS("iris", 3, 4), WINE("wine", 3, 13), WEATHER("weather", 2, 4);

	private String displayName;
	private int numberOfClases;
	private int numberOfAttributes;

	/**
	 * Main constructor
	 * 
	 * @param dn
	 *            display name
	 * @param classes
	 *            the number of clases for this type of dataset
	 */
	private DataTypeEnum(String dn, int numClasses, int numAttributes) {
		this.displayName = dn;
		this.numberOfClases = numClasses;
		this.numberOfAttributes = numAttributes;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getNumberOfClases() {
		return numberOfClases;
	}

	public int getNumberOfAttributes() {
		return numberOfAttributes;
	}

	/**
	 * toString does display name
	 */
	public String toString() {
		return displayName;
	}
}
