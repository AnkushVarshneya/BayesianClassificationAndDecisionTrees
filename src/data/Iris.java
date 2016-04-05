/**
 * @(#)Iris.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * Object to store Iris Data/Record Type
 */

package data;

import Jama.Matrix;

public class Iris extends BaseData {
	public static final String FILE_NAME = "src/Data/DataSets/iris.csv";

	private double sepalLength;
	private double sepalWidth;
	private double petalLength;
	private double petalWidth;
	private String type;

	public Iris(String[] line) {
		super();
		this.sepalLength = Double.parseDouble(line[0]);
		this.sepalWidth = Double.parseDouble(line[1]);
		this.petalLength = Double.parseDouble(line[2]);
		this.petalWidth = Double.parseDouble(line[3]);
		this.type = line[4];
	}

	public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String type) {
		super();
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Iris [sepalLength=" + sepalLength + ", sepalWidth=" + sepalWidth + ", petalLength=" + petalLength
				+ ", petalWidth=" + petalWidth + ", type=" + type + "]";
	}

	@Override
	public int getClassNumber() {
		if (this.type.equals("Iris-setosa"))
			return 1;
		if (this.type.equals("Iris-versicolor"))
			return 2;
		if (this.type.equals("Iris-virginica"))
			return 3;

		throw new RuntimeException("Bad class");
	}

	@Override
	public Matrix toMatrix() {
		Matrix aVector = new Matrix(DataTypeEnum.IRIS.getNumberOfAttributes(), 1);
		int i = 0;

		aVector.set(i++, 0, this.sepalLength);
		aVector.set(i++, 0, this.sepalWidth);
		aVector.set(i++, 0, this.petalLength);
		aVector.set(i++, 0, this.petalWidth);

		return aVector;
	}

	@Override
	public String getAttributesClassByNumber(int attributeNumber) {
		String ret = null;

		if (attributeNumber == 0)
			ret = getSepalLengthClass();
		if (attributeNumber == 1)
			ret = getSepalWidthClass();
		if (attributeNumber == 2)
			ret = getPetalLengthClass();
		if (attributeNumber == 3)
			ret = getPetalWidthClass();
		if (attributeNumber == 4)
			ret = new Integer(getClassNumber()).toString();

		return ret;
	}

	public String getSepalLengthClass() {
		String ret;
		if (sepalLength <= 5.1)
			ret = "small-sepalLength";
		else if (sepalLength > 5.1 && sepalLength <= 5.8)
			ret = "medium-sepalLength";
		else if (sepalLength > 5.8 && sepalLength <= 6.4)
			ret = "average-sepalLength";
		else
			ret = "big-sepalLength";

		return ret;
	}

	public double getSepalLength() {
		return sepalLength;
	}

	public void setSepalLength(double sepalLength) {
		this.sepalLength = sepalLength;
	}

	public String getSepalWidthClass() {
		String ret;
		if (sepalWidth <= 2.8)
			ret = "small-sepalWidth";
		else if (sepalWidth > 2.8 && sepalWidth <= 3)
			ret = "medium-sepalWidth";
		else if (sepalWidth > 3 && sepalWidth <= 3.3)
			ret = "average-sepalWidth";
		else
			ret = "big-sepalWidth";

		return ret;
	}

	public double getSepalWidth() {
		return sepalWidth;
	}

	public void setSepalWidth(double sepalWidth) {
		this.sepalWidth = sepalWidth;
	}

	public String getPetalLengthClass() {
		String ret;
		if (petalLength <= 1.575)
			ret = "small-petalLength";
		else if (petalLength > 1.575 && petalLength <= 4.35)
			ret = "medium-petalLength";
		else if (petalLength > 4.35 && petalLength <= 5.1)
			ret = "average-petalLength";
		else
			ret = "big-petalLength";

		return ret;
	}

	public double getPetalLength() {
		return petalLength;
	}

	public void setPetalLength(double petalLength) {
		this.petalLength = petalLength;
	}

	public String getPetalWidthClass() {
		String ret;
		if (petalWidth <= 0.3)
			ret = "small-petalWidth";
		else if (petalWidth > 0.3 && petalWidth <= 1.3)
			ret = "medium-petalWidth";
		else if (petalWidth > 1.3 && petalWidth <= 1.8)
			ret = "average-petalWidth";
		else
			ret = "big-petalWidth";

		return ret;
	}

	public double getPetalWidth() {
		return petalWidth;
	}

	public void setPetalWidth(double petalWidth) {
		this.petalWidth = petalWidth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(petalLength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(petalWidth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sepalLength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sepalWidth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Iris other = (Iris) obj;
		if (Double.doubleToLongBits(petalLength) != Double.doubleToLongBits(other.petalLength))
			return false;
		if (Double.doubleToLongBits(petalWidth) != Double.doubleToLongBits(other.petalWidth))
			return false;
		if (Double.doubleToLongBits(sepalLength) != Double.doubleToLongBits(other.sepalLength))
			return false;
		if (Double.doubleToLongBits(sepalWidth) != Double.doubleToLongBits(other.sepalWidth))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
