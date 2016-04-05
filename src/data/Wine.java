/**
 * @(#)Wine.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * Object to store Wine Data/Record Type
 */

package data;

import Jama.Matrix;

public class Wine extends BaseData {
	private int type;
	private double alcohol;
	private double malicAsid;
	private double ash;
	private double alcalinityOfAsh;
	private double magnesium;
	private double totalPhenols;
	private double flavanoids;
	private double nonFlavanoidPhenols;
	private double proanthocyanins;
	private double colorIntensity;
	private double hue;
	private double diluted;
	private double proline;

	public Wine(String[] line) {
		super();
		this.type = Integer.parseInt(line[0]);
		this.alcohol = Double.parseDouble(line[1]);
		this.malicAsid = Double.parseDouble(line[2]);
		this.ash = Double.parseDouble(line[3]);
		this.alcalinityOfAsh = Double.parseDouble(line[4]);
		this.magnesium = Double.parseDouble(line[5]);
		this.totalPhenols = Double.parseDouble(line[6]);
		this.flavanoids = Double.parseDouble(line[7]);
		this.nonFlavanoidPhenols = Double.parseDouble(line[8]);
		this.proanthocyanins = Double.parseDouble(line[9]);
		this.colorIntensity = Double.parseDouble(line[10]);
		this.hue = Double.parseDouble(line[11]);
		this.diluted = Double.parseDouble(line[12]);
		this.proline = Double.parseDouble(line[13]);
	}

	public Wine(int type, double alcohol, double malicAsid, double ash, double alcalinityOfAsh, double magnesium,
			double totalPhenols, double flavanoids, double nonFlavanoidPhenols, double proanthocyanins,
			double colorIntensity, double hue, double diluted, double proline) {
		super();
		this.type = type;
		this.alcohol = alcohol;
		this.malicAsid = malicAsid;
		this.ash = ash;
		this.alcalinityOfAsh = alcalinityOfAsh;
		this.magnesium = magnesium;
		this.totalPhenols = totalPhenols;
		this.flavanoids = flavanoids;
		this.nonFlavanoidPhenols = nonFlavanoidPhenols;
		this.proanthocyanins = proanthocyanins;
		this.colorIntensity = colorIntensity;
		this.hue = hue;
		this.diluted = diluted;
		this.proline = proline;
	}

	@Override
	public String toString() {
		return "Wine [type=" + type + ", alcohol=" + alcohol + ", malicAsid=" + malicAsid + ", ash=" + ash
				+ ", alcalinityOfAsh=" + alcalinityOfAsh + ", magnesium=" + magnesium + ", totalPhenols=" + totalPhenols
				+ ", flavanoids=" + flavanoids + ", nonFlavanoidPhenols=" + nonFlavanoidPhenols + ", proanthocyanins="
				+ proanthocyanins + ", colorIntensity=" + colorIntensity + ", hue=" + hue + ", diluted=" + diluted
				+ ", proline=" + proline + "]";
	}

	@Override
	public int getClassNumber() {
		if (this.type < 1 || this.type > 3)
			throw new RuntimeException("Bad class");

		return type;
	}

	@Override
	public Matrix toMatrix() {
		Matrix aVector = new Matrix(DataTypeEnum.WINE.getNumberOfAttributes(), 1);
		int i = 0;

		aVector.set(i++, 0, this.alcohol);
		aVector.set(i++, 0, this.malicAsid);
		aVector.set(i++, 0, this.ash);
		aVector.set(i++, 0, this.alcalinityOfAsh);
		aVector.set(i++, 0, this.magnesium);
		aVector.set(i++, 0, this.totalPhenols);
		aVector.set(i++, 0, this.flavanoids);
		aVector.set(i++, 0, this.nonFlavanoidPhenols);
		aVector.set(i++, 0, this.proanthocyanins);
		aVector.set(i++, 0, this.colorIntensity);
		aVector.set(i++, 0, this.hue);
		aVector.set(i++, 0, this.diluted);
		aVector.set(i++, 0, this.proline);

		return aVector;
	}

	@Override
	public String getAttributesClassByNumber(int attributeNumber) {
		String ret = null;

		if (attributeNumber == 0)
			ret = getAlcoholClass();
		if (attributeNumber == 1)
			ret = getMalicAsidClass();
		if (attributeNumber == 2)
			ret = getAshClass();
		if (attributeNumber == 3)
			ret = getAlcalinityOfAshClass();
		if (attributeNumber == 4)
			ret = getMagnesiumClass();
		if (attributeNumber == 5)
			ret = getTotalPhenolsClass();
		if (attributeNumber == 6)
			ret = getFlavanoidsClass();
		if (attributeNumber == 7)
			ret = getNonFlavanoidPhenolsClass();
		if (attributeNumber == 8)
			ret = getProanthocyaninsClass();
		if (attributeNumber == 9)
			ret = getColorIntensityClass();
		if (attributeNumber == 10)
			ret = getHueClass();
		if (attributeNumber == 11)
			ret = getDilutedClass();
		if (attributeNumber == 12)
			ret = getProlineClass();
		if (attributeNumber == 13)
			ret = new Integer(getClassNumber()).toString();

		return ret;
	}

	public String getAlcoholClass() {
		String ret;
		if (alcohol <= 12.355)
			ret = "low-alcohol";
		else if (alcohol > 12.355 && alcohol <= 13.05)
			ret = "medium-alcohol";
		else if (alcohol > 13.05 && alcohol <= 13.6825)
			ret = "average-alcohol";
		else
			ret = "high-alcohol";

		return ret;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public String getMalicAsidClass() {
		String ret;
		if (malicAsid <= 1.5975)
			ret = "low-malicAsid";
		else if (malicAsid > 1.5975 && malicAsid <= 1.865)
			ret = "medium-malicAsid";
		else if (malicAsid > 1.865 && malicAsid <= 3.105)
			ret = "average-malicAsid";
		else
			ret = "high-malicAsid";

		return ret;
	}

	public double getMalicAsid() {
		return malicAsid;
	}

	public void setMalicAsid(double malicAsid) {
		this.malicAsid = malicAsid;
	}

	public String getAshClass() {
		String ret;
		if (ash <= 2.21)
			ret = "low-ash";
		else if (ash > 2.21 && ash <= 2.36)
			ret = "medium-ash";
		else if (ash > 2.36 && ash <= 2.56)
			ret = "average-ash";
		else
			ret = "high-ash";

		return ret;
	}

	public double getAsh() {
		return ash;
	}

	public void setAsh(double ash) {
		this.ash = ash;
	}

	public String getAlcalinityOfAshClass() {
		String ret;
		if (alcalinityOfAsh <= 17.175)
			ret = "low-alcalinityOfAsh";
		else if (alcalinityOfAsh > 17.175 && alcalinityOfAsh <= 19.5)
			ret = "medium-alcalinityOfAsh";
		else if (alcalinityOfAsh > 19.5 && alcalinityOfAsh <= 21.5)
			ret = "average-alcalinityOfAsh";
		else
			ret = "high-alcalinityOfAsh";

		return ret;
	}

	public double getAlcalinityOfAsh() {
		return alcalinityOfAsh;
	}

	public void setAlcalinityOfAsh(double alcalinityOfAsh) {
		this.alcalinityOfAsh = alcalinityOfAsh;
	}

	public String getMagnesiumClass() {
		String ret;
		if (magnesium <= 88)
			ret = "low-magnesium";
		else if (magnesium > 88 && magnesium <= 98)
			ret = "medium-magnesium";
		else if (magnesium > 98 && magnesium <= 107.25)
			ret = "average-magnesium";
		else
			ret = "high-magnesium";

		return ret;
	}

	public double getMagnesium() {
		return magnesium;
	}

	public void setMagnesium(double magnesium) {
		this.magnesium = magnesium;
	}

	public String getTotalPhenolsClass() {
		String ret;
		if (totalPhenols <= 1.735)
			ret = "low-totalPhenols";
		else if (totalPhenols > 1.735 && totalPhenols <= 2.355)
			ret = "medium-totalPhenols";
		else if (totalPhenols > 2.355 && totalPhenols <= 2.8)
			ret = "average-totalPhenols";
		else
			ret = "high-totalPhenols";

		return ret;
	}

	public double getTotalPhenols() {
		return totalPhenols;
	}

	public void setTotalPhenols(double totalPhenols) {
		this.totalPhenols = totalPhenols;
	}

	public String getFlavanoidsClass() {
		String ret;
		if (flavanoids <= 1.175)
			ret = "low-flavanoids";
		else if (flavanoids > 1.175 && flavanoids <= 2.135)
			ret = "medium-flavanoids";
		else if (flavanoids > 2.135 && flavanoids <= 2.8825)
			ret = "average-flavanoids";
		else
			ret = "high-flavanoids";

		return ret;
	}

	public double getFlavanoids() {
		return flavanoids;
	}

	public void setFlavanoids(double flavanoids) {
		this.flavanoids = flavanoids;
	}

	public String getNonFlavanoidPhenolsClass() {
		String ret;
		if (nonFlavanoidPhenols <= 0.2675)
			ret = "low-nonFlavanoidPhenols";
		else if (nonFlavanoidPhenols > 0.2675 && nonFlavanoidPhenols <= 0.34)
			ret = "medium-nonFlavanoidPhenols";
		else if (nonFlavanoidPhenols > 0.34 && nonFlavanoidPhenols <= 0.4425)
			ret = "average-nonFlavanoidPhenols";
		else
			ret = "high-nonFlavanoidPhenols";

		return ret;
	}

	public double getNonFlavanoidPhenols() {
		return nonFlavanoidPhenols;
	}

	public void setNonFlavanoidPhenols(double nonFlavanoidPhenols) {
		this.nonFlavanoidPhenols = nonFlavanoidPhenols;
	}

	public String getProanthocyaninsClass() {
		String ret;
		if (proanthocyanins <= 1.2475)
			ret = "low-proanthocyanins";
		else if (proanthocyanins > 1.2475 && proanthocyanins <= 1.555)
			ret = "medium-proanthocyanins";
		else if (proanthocyanins > 1.555 && proanthocyanins <= 1.9525)
			ret = "average-proanthocyanins";
		else
			ret = "high-proanthocyanins";

		return ret;
	}

	public double getProanthocyanins() {
		return proanthocyanins;
	}

	public void setProanthocyanins(double proanthocyanins) {
		this.proanthocyanins = proanthocyanins;
	}

	public String getColorIntensityClass() {
		String ret;
		if (colorIntensity <= 3.2)
			ret = "low-colorIntensity";
		else if (colorIntensity > 3.2 && colorIntensity <= 4.69)
			ret = "medium-colorIntensity";
		else if (colorIntensity > 4.69 && colorIntensity <= 6.2125)
			ret = "average-colorIntensity";
		else
			ret = "high-colorIntensity";

		return ret;
	}

	public double getColorIntensity() {
		return colorIntensity;
	}

	public void setColorIntensity(double colorIntensity) {
		this.colorIntensity = colorIntensity;
	}

	public String getHueClass() {
		String ret;
		if (hue <= 0.78)
			ret = "low-hue";
		else if (hue > 0.78 && hue <= 0.965)
			ret = "medium-hue";
		else if (hue > 0.965 && hue <= 1.12)
			ret = "average-hue";
		else
			ret = "high-hue";

		return ret;
	}

	public double getHue() {
		return hue;
	}

	public void setHue(double hue) {
		this.hue = hue;
	}

	public String getDilutedClass() {
		String ret;
		if (diluted <= 1.9275)
			ret = "low-diluted";
		else if (diluted > 1.9275 && diluted <= 2.78)
			ret = "medium-diluted";
		else if (diluted > 2.78 && diluted <= 3.1725)
			ret = "average-diluted";
		else
			ret = "high-diluted";

		return ret;
	}

	public double getDiluted() {
		return diluted;
	}

	public void setDiluted(double diluted) {
		this.diluted = diluted;
	}

	public String getProlineClass() {
		String ret;
		if (proline <= 500)
			ret = "low-proline";
		else if (proline > 500 && proline <= 673.5)
			ret = "medium-proline";
		else if (proline > 673.5 && proline <= 986.25)
			ret = "average-proline";
		else
			ret = "high-proline";

		return ret;
	}

	public double getProline() {
		return proline;
	}

	public void setProline(double proline) {
		this.proline = proline;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(alcalinityOfAsh);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(alcohol);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ash);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(colorIntensity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(diluted);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(flavanoids);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(hue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(magnesium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(malicAsid);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(nonFlavanoidPhenols);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(proanthocyanins);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(proline);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalPhenols);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + type;
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
		Wine other = (Wine) obj;
		if (Double.doubleToLongBits(alcalinityOfAsh) != Double.doubleToLongBits(other.alcalinityOfAsh))
			return false;
		if (Double.doubleToLongBits(alcohol) != Double.doubleToLongBits(other.alcohol))
			return false;
		if (Double.doubleToLongBits(ash) != Double.doubleToLongBits(other.ash))
			return false;
		if (Double.doubleToLongBits(colorIntensity) != Double.doubleToLongBits(other.colorIntensity))
			return false;
		if (Double.doubleToLongBits(diluted) != Double.doubleToLongBits(other.diluted))
			return false;
		if (Double.doubleToLongBits(flavanoids) != Double.doubleToLongBits(other.flavanoids))
			return false;
		if (Double.doubleToLongBits(hue) != Double.doubleToLongBits(other.hue))
			return false;
		if (Double.doubleToLongBits(magnesium) != Double.doubleToLongBits(other.magnesium))
			return false;
		if (Double.doubleToLongBits(malicAsid) != Double.doubleToLongBits(other.malicAsid))
			return false;
		if (Double.doubleToLongBits(nonFlavanoidPhenols) != Double.doubleToLongBits(other.nonFlavanoidPhenols))
			return false;
		if (Double.doubleToLongBits(proanthocyanins) != Double.doubleToLongBits(other.proanthocyanins))
			return false;
		if (Double.doubleToLongBits(proline) != Double.doubleToLongBits(other.proline))
			return false;
		if (Double.doubleToLongBits(totalPhenols) != Double.doubleToLongBits(other.totalPhenols))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
