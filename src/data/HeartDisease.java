/**
 * @(#)HeartDisease.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * Object to store Heart Disease Data/Record Type
 */

package data;

import Jama.Matrix;

public class HeartDisease extends BaseData {
	private double Age;
	private double Cp;
	private double Trestbps;
	private double Chol;
	private double Restecg;
	private double Thalach;
	private double Oldpeak;
	private double Dlope;
	private double Ca;
	private double Thal;
	private int condition;

	public HeartDisease(String[] line) {
		super();
		this.Age = Double.parseDouble(line[0]);
		this.Cp = Double.parseDouble(line[2]);
		this.Trestbps = Double.parseDouble(line[3]);
		this.Chol = Double.parseDouble(line[4]);
		this.Restecg = Double.parseDouble(line[6]);
		this.Thalach = Double.parseDouble(line[7]);
		this.Oldpeak = Double.parseDouble(line[9]);
		this.Dlope = Double.parseDouble(line[10]);
		this.Ca = Double.parseDouble(line[11]);
		this.Thal = Double.parseDouble(line[12]);
		this.condition = Integer.parseInt(line[13]);
	}

	public HeartDisease(double age, double gender, double cp, double trestbps, double chol, double fbs, double restecg,
			double thalach, double exang, double oldpeak, double dlope, double ca, double thal, int condition) {
		super();
		Age = age;
		Cp = cp;
		Trestbps = trestbps;
		Chol = chol;
		Restecg = restecg;
		Thalach = thalach;
		Oldpeak = oldpeak;
		Dlope = dlope;
		Ca = ca;
		Thal = thal;
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "HeartDisease [Age=" + Age + ", Cp=" + Cp + ", Trestbps=" + Trestbps + ", Chol=" + Chol + ", Fbs="
				+ Restecg + ", Thalach=" + Thalach + ", Oldpeak=" + Oldpeak + ", Dlope=" + Dlope + ", Ca=" + Ca
				+ ", Thal=" + Thal + ", condition=" + condition + "]";
	}

	@Override
	public int getClassNumber() {
		if (this.condition == 1)
			return 2;
		if (this.condition == 2)
			return 3;
		if (this.condition == 3)
			return 4;
		if (this.condition == 4)
			return 5;

		return 1;
	}

	@Override
	public Matrix toMatrix() {
		Matrix aVector = new Matrix(DataTypeEnum.HEARTDISEASE.getNumberOfAttributes(), 1);
		int i = 0;

		aVector.set(i++, 0, this.Age);
		aVector.set(i++, 0, this.Cp);
		aVector.set(i++, 0, this.Trestbps);
		aVector.set(i++, 0, this.Chol);
		aVector.set(i++, 0, this.Restecg);
		aVector.set(i++, 0, this.Thalach);
		aVector.set(i++, 0, this.Oldpeak);
		aVector.set(i++, 0, this.Dlope);
		aVector.set(i++, 0, this.Ca);
		aVector.set(i++, 0, this.Thal);

		return aVector;
	}

	@Override
	public String getAttributesClassByNumber(int attributeNumber) {
		String ret = null;

		if (attributeNumber == 0)
			ret = getAgeClass();
		if (attributeNumber == 1)
			ret = getCpClass();
		if (attributeNumber == 2)
			ret = getTrestbpsClass();
		if (attributeNumber == 3)
			ret = getCholClass();
		if (attributeNumber == 4)
			ret = getRestecgClass();
		if (attributeNumber == 5)
			ret = getThalachClass();
		if (attributeNumber == 6)
			ret = getOldpeakClass();
		if (attributeNumber == 7)
			ret = getDlopeClass();
		if (attributeNumber == 8)
			ret = getCaClass();
		if (attributeNumber == 9)
			ret = getThalClass();
		if (attributeNumber == 10)
			ret = new Integer(getClassNumber()).toString();
		return ret;
	}

	public String getAgeClass() {
		String ret;
		if (Age <= 48)
			ret = "Working-Age";
		else if (Age > 48 && Age <= 56)
			ret = "Retiring-Age";
		else if (Age > 56 && Age <= 61)
			ret = "Retired-Age";
		else
			ret = "Old-Age";

		return ret;
	}

	public double getAge() {
		return Age;
	}

	public void setAge(double age) {
		Age = age;
	}

	public String getCpClass() {
		String ret;
		if (Cp <= 3)
			ret = "Low-Cp";
		else
			ret = "High-Cp";

		return ret;
	}

	public double getCp() {
		return Cp;
	}

	public void setCp(double cp) {
		Cp = cp;
	}

	public String getTrestbpsClass() {
		String ret;
		if (Trestbps <= 120)
			ret = "Low-Trestbps";
		else if (Trestbps > 120 && Trestbps <= 130)
			ret = "Average-Trestbps";
		else if (Trestbps > 130 && Trestbps <= 140)
			ret = "High-Trestbps";
		else
			ret = "Criticle-Trestbps";

		return ret;
	}

	public double getTrestbps() {
		return Trestbps;
	}

	public void setTrestbps(double trestbps) {
		Trestbps = trestbps;
	}

	public String getCholClass() {
		String ret;
		if (Chol <= 211)
			ret = "Low-Chol";
		else if (Chol > 211 && Chol <= 243)
			ret = "Average-Chol";
		else if (Chol > 243 && Chol <= 276.5)
			ret = "High-Chol";
		else
			ret = "Criticle-Chol";

		return ret;
	}

	public double getChol() {
		return Chol;
	}

	public void setChol(double chol) {
		Chol = chol;
	}

	public String getRestecgClass() {
		return "Restecg-Lvl-" + Restecg;
	}

	public double getRestecg() {
		return Restecg;
	}

	public void setRestecg(double restecg) {
		Restecg = restecg;
	}

	public String getThalachClass() {
		String ret;
		if (Thalach <= 133)
			ret = "Low-Thalach";
		else if (Thalach > 133 && Thalach <= 153)
			ret = "Average-Thalach";
		else if (Thalach > 153 && Thalach <= 166)
			ret = "High-Thalach";
		else
			ret = "Criticle-Thalach";

		return ret;
	}

	public double getThalach() {
		return Thalach;
	}

	public void setThalach(double thalach) {
		Thalach = thalach;
	}

	public String getOldpeakClass() {
		String ret;
		if (Oldpeak <= 0)
			ret = "Low-Oldpeak";
		else if (Oldpeak > 0 && Oldpeak <= 0.8)
			ret = "Average-Oldpeak";
		else if (Oldpeak > 0.8 && Oldpeak <= 1.7)
			ret = "High-Oldpeak";
		else
			ret = "Criticle-Oldpeak";

		return ret;
	}

	public double getOldpeak() {
		return Oldpeak;
	}

	public void setOldpeak(double oldpeak) {
		Oldpeak = oldpeak;
	}

	public String getDlopeClass() {
		String ret;
		if (Dlope <= 1)
			ret = "Low-Dlope";
		else
			ret = "High-Dlope";

		return ret;
	}

	public double getDlope() {
		return Dlope;
	}

	public void setDlope(double dlope) {
		Dlope = dlope;
	}

	public String getCaClass() {
		String ret;
		if (Ca <= 0)
			ret = "Low-Ca";
		else
			ret = "High-Ca";

		return ret;
	}

	public double getCa() {
		return Ca;
	}

	public void setCa(double ca) {
		Ca = ca;
	}

	public String getThalClass() {
		String ret;
		if (Thal <= 3)
			ret = "Low-Oldpeak";
		else
			ret = "High-Oldpeak";

		return ret;
	}

	public double getThal() {
		return Thal;
	}

	public void setThal(double thal) {
		Thal = thal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Age);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Ca);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Chol);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Cp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Dlope);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Oldpeak);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Restecg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Thal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Thalach);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Trestbps);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + condition;
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
		HeartDisease other = (HeartDisease) obj;
		if (Double.doubleToLongBits(Age) != Double.doubleToLongBits(other.Age))
			return false;
		if (Double.doubleToLongBits(Ca) != Double.doubleToLongBits(other.Ca))
			return false;
		if (Double.doubleToLongBits(Chol) != Double.doubleToLongBits(other.Chol))
			return false;
		if (Double.doubleToLongBits(Cp) != Double.doubleToLongBits(other.Cp))
			return false;
		if (Double.doubleToLongBits(Dlope) != Double.doubleToLongBits(other.Dlope))
			return false;
		if (Double.doubleToLongBits(Oldpeak) != Double.doubleToLongBits(other.Oldpeak))
			return false;
		if (Double.doubleToLongBits(Restecg) != Double.doubleToLongBits(other.Restecg))
			return false;
		if (Double.doubleToLongBits(Thal) != Double.doubleToLongBits(other.Thal))
			return false;
		if (Double.doubleToLongBits(Thalach) != Double.doubleToLongBits(other.Thalach))
			return false;
		if (Double.doubleToLongBits(Trestbps) != Double.doubleToLongBits(other.Trestbps))
			return false;
		if (condition != other.condition)
			return false;
		return true;
	}
}
