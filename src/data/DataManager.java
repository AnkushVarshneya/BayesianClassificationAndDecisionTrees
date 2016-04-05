/**
 * @(#)DataManager.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * A singleton that returns objects from cvs files
 */

package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import Jama.Matrix;
import javafx.util.Pair;

public class DataManager {
	private static DataManager instance = null;

	private DataManager() {
	}

	public static synchronized DataManager getInstance() {
		if (instance == null)
			instance = new DataManager();
		return instance;
	}

	/**
	 * @param type
	 * @return a list of data from cvs file based on type
	 */
	public static ArrayList<BaseData> getAllOfType(DataTypeEnum type) {
		ArrayList<BaseData> ret = new ArrayList<BaseData>();
		File file = new File("src/Data/DataSets/" + type + ".csv");

		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNextLine()) {
				switch (type) {
				case HEARTDISEASE:
					ret.add(new HeartDisease(inputStream.nextLine().split(",")));
					break;
				case IRIS:
					ret.add(new Iris(inputStream.nextLine().split(",")));
					break;
				case WINE:
					ret.add(new Wine(inputStream.nextLine().split(",")));
					break;
				case WEATHER:
					ret.add(new Weather(inputStream.nextLine().split(",")));
					break;
				}
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * @param arr
	 * @param classType
	 * @return a list of data from array based on class
	 */
	public static ArrayList<BaseData> getAllFromArrayOfClass(ArrayList<BaseData> arr, int classType) {
		ArrayList<BaseData> ret = new ArrayList<BaseData>();

		for (BaseData a : arr)
			if (a.getClassNumber() == classType)
				ret.add(a);

		return ret;
	}

	/**
	 * @param arr
	 * @return a array of vector of the data
	 */
	public static ArrayList<Matrix> dataToMatrix(ArrayList<BaseData> arr) {
		ArrayList<Matrix> ret = new ArrayList<Matrix>();
		int i = 0;

		for (BaseData a : arr)
			ret.add(i++, a.toMatrix());

		return ret;
	}

	/**
	 * 
	 * @param arr
	 *            array to search
	 * @param attribute
	 *            attribute to get thats at zero
	 * @param value
	 *            value of attribute
	 * @return
	 */
	public static double getClassProbability(ArrayList<BaseData> arr, int attribute, String value) {
		double count = 0.0;
		for (BaseData data : arr) {
			String dataValue = data.getAttributesClassByNumber(attribute);
			if (dataValue != null && dataValue.equals(value))
				count++;
		}
		return count == 0.0 ? 0 : count / new Double(arr.size());
	}

	/**
	 * 
	 * @param arr
	 *            array to search
	 * @param attribute
	 *            attribute to get thats at zero
	 * @param value
	 *            value of attribute
	 * @return
	 */
	public static ArrayList<BaseData> getSubSet(ArrayList<BaseData> arr, int attribute, String value) {
		ArrayList<BaseData> ret = new ArrayList<BaseData>();
		for (BaseData data : arr) {
			String dataValue = data.getAttributesClassByNumber(attribute);
			if (dataValue != null && dataValue.equals(value))
				ret.add(data);
		}
		return ret;
	}

	/**
	 * clone attribute map
	 * 
	 * @param type
	 * @return a hashmap of attributes
	 */
	public static HashMap<Integer, Pair<String, ArrayList<String>>> clonePossibleAttributeValues(
			HashMap<Integer, Pair<String, ArrayList<String>>> source) {
		HashMap<Integer, Pair<String, ArrayList<String>>> ret = new HashMap<Integer, Pair<String, ArrayList<String>>>();

		for (Entry<Integer, Pair<String, ArrayList<String>>> entry : source.entrySet()) {
			ret.put(entry.getKey(), null);

			if (entry.getValue() != null) {
				ret.replace(entry.getKey(), new Pair<String, ArrayList<String>>(new String(entry.getValue().getKey()),
						new ArrayList<String>()));
				int i = 0;
				for (String value : entry.getValue().getValue()) {
					ret.get(entry.getKey()).getValue().add(i, value);
					i++;
				}
			}
		}
		return ret;
	}

	/**
	 * get all attributes for a given data type
	 * 
	 * @param type
	 * @return a hashmap of attributes
	 */
	public static HashMap<Integer, Pair<String, ArrayList<String>>> possibleAttributeValues(DataTypeEnum type) {
		HashMap<Integer, Pair<String, ArrayList<String>>> ret = new HashMap<Integer, Pair<String, ArrayList<String>>>();
		switch (type) {
		case HEARTDISEASE:
			ret.put(0, new Pair<String, ArrayList<String>>(new String("Age"), new ArrayList<String>()));
			ret.get(0).getValue().add(0, "Working-Age");
			ret.get(0).getValue().add(1, "Retiring-Age");
			ret.get(0).getValue().add(2, "Retired-Age");
			ret.get(0).getValue().add(3, "Old-Age");

			ret.put(1, new Pair<String, ArrayList<String>>(new String("Cp"), new ArrayList<String>()));
			ret.get(1).getValue().add(0, "Low-Cp");
			ret.get(1).getValue().add(1, "High-Cp");

			ret.put(2, new Pair<String, ArrayList<String>>(new String("Trestbps"), new ArrayList<String>()));
			ret.get(2).getValue().add(0, "Low-Trestbps");
			ret.get(2).getValue().add(1, "Average-Trestbps");
			ret.get(2).getValue().add(2, "High-Trestbps");
			ret.get(2).getValue().add(3, "Criticle-Trestbps");

			ret.put(3, new Pair<String, ArrayList<String>>(new String("Chol"), new ArrayList<String>()));
			ret.get(3).getValue().add(0, "Low-Chol");
			ret.get(3).getValue().add(1, "Average-Chol");
			ret.get(3).getValue().add(2, "High-Chol");
			ret.get(3).getValue().add(3, "Criticle-Chol");

			ret.put(4, new Pair<String, ArrayList<String>>(new String("Restecg"), new ArrayList<String>()));
			ret.get(4).getValue().add(0, "Restecg-Lvl-0.0");
			ret.get(4).getValue().add(1, "Restecg-Lvl-1.0");
			ret.get(4).getValue().add(2, "Restecg-Lvl-2.0");

			ret.put(5, new Pair<String, ArrayList<String>>(new String("Thalach"), new ArrayList<String>()));
			ret.get(5).getValue().add(0, "Low-Thalach");
			ret.get(5).getValue().add(1, "Average-Thalach");
			ret.get(5).getValue().add(2, "High-Thalach");
			ret.get(5).getValue().add(3, "Criticle-Thalach");

			ret.put(6, new Pair<String, ArrayList<String>>(new String("Oldpeak"), new ArrayList<String>()));
			ret.get(6).getValue().add(0, "Low-Oldpeak");
			ret.get(6).getValue().add(1, "Average-Oldpeak");
			ret.get(6).getValue().add(2, "High-Oldpeak");
			ret.get(6).getValue().add(3, "Criticle-Oldpeak");

			ret.put(7, new Pair<String, ArrayList<String>>(new String("Dlope"), new ArrayList<String>()));
			ret.get(7).getValue().add(0, "Low-Dlope");
			ret.get(7).getValue().add(1, "High-Dlope");

			ret.put(8, new Pair<String, ArrayList<String>>(new String("Ca"), new ArrayList<String>()));
			ret.get(8).getValue().add(0, "Low-Ca");
			ret.get(8).getValue().add(1, "High-Ca");

			ret.put(9, new Pair<String, ArrayList<String>>(new String("Oldpeak"), new ArrayList<String>()));
			ret.get(9).getValue().add(0, "Low-Oldpeak");
			ret.get(9).getValue().add(1, "High-Oldpeak");
			/*
			 * ret.put(10, new Pair<String, ArrayList<String>>(new
			 * String("class"), new ArrayList<String>()));
			 * ret.get(10).getValue().add(0, "1"); ret.get(10).getValue().add(1,
			 * "2"); ret.get(10).getValue().add(2, "3");
			 * ret.get(10).getValue().add(3, "4"); ret.get(10).getValue().add(4,
			 * "5");
			 */
			break;
		case IRIS:
			ret.put(0, new Pair<String, ArrayList<String>>(new String("sepalLength"), new ArrayList<String>()));
			ret.get(0).getValue().add(0, "small-sepalLength");
			ret.get(0).getValue().add(1, "medium-sepalLength");
			ret.get(0).getValue().add(2, "average-sepalLength");
			ret.get(0).getValue().add(3, "big-sepalLength");

			ret.put(1, new Pair<String, ArrayList<String>>(new String("sepalWidth"), new ArrayList<String>()));
			ret.get(1).getValue().add(0, "small-sepalWidth");
			ret.get(1).getValue().add(1, "medium-sepalWidth");
			ret.get(1).getValue().add(2, "average-sepalWidth");
			ret.get(1).getValue().add(3, "big-sepalWidth");

			ret.put(2, new Pair<String, ArrayList<String>>(new String("petalLength"), new ArrayList<String>()));
			ret.get(2).getValue().add(0, "small-petalLength");
			ret.get(2).getValue().add(1, "medium-petalLength");
			ret.get(2).getValue().add(2, "average-petalLength");
			ret.get(2).getValue().add(3, "big-petalLength");

			ret.put(3, new Pair<String, ArrayList<String>>(new String("petalWidth"), new ArrayList<String>()));
			ret.get(3).getValue().add(0, "small-petalWidth");
			ret.get(3).getValue().add(1, "medium-petalWidth");
			ret.get(3).getValue().add(2, "average-petalWidth");
			ret.get(3).getValue().add(3, "big-petalWidth");
			/*
			 * ret.put(4, new Pair<String, ArrayList<String>>(new
			 * String("class"), new ArrayList<String>()));
			 * ret.get(4).getValue().add(0, "1"); ret.get(4).getValue().add(1,
			 * "2"); ret.get(4).getValue().add(2, "3");
			 */
			break;
		case WINE:
			ret.put(0, new Pair<String, ArrayList<String>>(new String("alcohol"), new ArrayList<String>()));
			ret.get(0).getValue().add(0, "low-alcohol");
			ret.get(0).getValue().add(1, "medium-alcohol");
			ret.get(0).getValue().add(2, "average-alcohol");
			ret.get(0).getValue().add(3, "high-alcohol");

			ret.put(1, new Pair<String, ArrayList<String>>(new String("malicAsid"), new ArrayList<String>()));
			ret.get(1).getValue().add(0, "low-malicAsid");
			ret.get(1).getValue().add(1, "medium-malicAsid");
			ret.get(1).getValue().add(2, "average-malicAsid");
			ret.get(1).getValue().add(3, "high-malicAsid");

			ret.put(2, new Pair<String, ArrayList<String>>(new String("ash"), new ArrayList<String>()));
			ret.get(2).getValue().add(0, "low-ash");
			ret.get(2).getValue().add(1, "medium-ash");
			ret.get(2).getValue().add(2, "average-ash");
			ret.get(2).getValue().add(3, "high-ash");

			ret.put(3, new Pair<String, ArrayList<String>>(new String("alcalinityOfAsh"), new ArrayList<String>()));
			ret.get(3).getValue().add(0, "low-alcalinityOfAsh");
			ret.get(3).getValue().add(1, "medium-alcalinityOfAsh");
			ret.get(3).getValue().add(2, "average-alcalinityOfAsh");
			ret.get(3).getValue().add(3, "high-alcalinityOfAsh");

			ret.put(4, new Pair<String, ArrayList<String>>(new String("magnesium"), new ArrayList<String>()));
			ret.get(4).getValue().add(0, "low-magnesium");
			ret.get(4).getValue().add(1, "medium-magnesium");
			ret.get(4).getValue().add(2, "average-magnesium");
			ret.get(4).getValue().add(3, "high-magnesium");

			ret.put(5, new Pair<String, ArrayList<String>>(new String("totalPhenols"), new ArrayList<String>()));
			ret.get(5).getValue().add(0, "low-totalPhenols");
			ret.get(5).getValue().add(1, "medium-totalPhenols");
			ret.get(5).getValue().add(2, "average-totalPhenols");
			ret.get(5).getValue().add(3, "high-totalPhenols");

			ret.put(6, new Pair<String, ArrayList<String>>(new String("flavanoids"), new ArrayList<String>()));
			ret.get(6).getValue().add(0, "low-flavanoids");
			ret.get(6).getValue().add(1, "medium-flavanoids");
			ret.get(6).getValue().add(2, "average-flavanoids");
			ret.get(6).getValue().add(3, "high-flavanoids");

			ret.put(7, new Pair<String, ArrayList<String>>(new String("nonFlavanoidPhenols"), new ArrayList<String>()));
			ret.get(7).getValue().add(0, "low-nonFlavanoidPhenols");
			ret.get(7).getValue().add(1, "medium-nonFlavanoidPhenols");
			ret.get(7).getValue().add(2, "average-nonFlavanoidPhenols");
			ret.get(7).getValue().add(3, "high-nonFlavanoidPhenols");

			ret.put(8, new Pair<String, ArrayList<String>>(new String("proanthocyanins"), new ArrayList<String>()));
			ret.get(8).getValue().add(0, "low-proanthocyanins");
			ret.get(8).getValue().add(1, "medium-proanthocyanins");
			ret.get(8).getValue().add(2, "average-proanthocyanins");
			ret.get(8).getValue().add(3, "high-proanthocyanins");

			ret.put(9, new Pair<String, ArrayList<String>>(new String("colorIntensity"), new ArrayList<String>()));
			ret.get(9).getValue().add(0, "low-colorIntensity");
			ret.get(9).getValue().add(1, "medium-colorIntensity");
			ret.get(9).getValue().add(2, "average-colorIntensity");
			ret.get(9).getValue().add(3, "high-colorIntensity");

			ret.put(10, new Pair<String, ArrayList<String>>(new String("hue"), new ArrayList<String>()));
			ret.get(10).getValue().add(0, "low-hue");
			ret.get(10).getValue().add(1, "medium-hue");
			ret.get(10).getValue().add(2, "average-hue");
			ret.get(10).getValue().add(3, "high-hue");

			ret.put(11, new Pair<String, ArrayList<String>>(new String("diluted"), new ArrayList<String>()));
			ret.get(11).getValue().add(0, "low-diluted");
			ret.get(11).getValue().add(1, "medium-diluted");
			ret.get(11).getValue().add(2, "average-diluted");
			ret.get(11).getValue().add(3, "high-diluted");

			ret.put(12, new Pair<String, ArrayList<String>>(new String("proline"), new ArrayList<String>()));
			ret.get(12).getValue().add(0, "low-proline");
			ret.get(12).getValue().add(1, "medium-proline");
			ret.get(12).getValue().add(2, "average-proline");
			ret.get(12).getValue().add(3, "high-proline");
			/*
			 * ret.put(13, new Pair<String, ArrayList<String>>(new
			 * String("class"), new ArrayList<String>()));
			 * ret.get(13).getValue().add(0, "1"); ret.get(13).getValue().add(1,
			 * "2"); ret.get(13).getValue().add(2, "3");
			 */
			break;
		case WEATHER:
			ret.put(0, new Pair<String, ArrayList<String>>(new String("Outlook"), new ArrayList<String>()));
			ret.get(0).getValue().add(0, "sunny");
			ret.get(0).getValue().add(1, "overst");
			ret.get(0).getValue().add(2, "rain");

			ret.put(1, new Pair<String, ArrayList<String>>(new String("Temp"), new ArrayList<String>()));
			ret.get(1).getValue().add(0, "hot");
			ret.get(1).getValue().add(1, "mild");
			ret.get(1).getValue().add(2, "cool");

			ret.put(2, new Pair<String, ArrayList<String>>(new String("Humidity"), new ArrayList<String>()));
			ret.get(2).getValue().add(0, "high");
			ret.get(2).getValue().add(1, "normal");

			ret.put(3, new Pair<String, ArrayList<String>>(new String("Wind"), new ArrayList<String>()));
			ret.get(3).getValue().add(0, "weak");
			ret.get(3).getValue().add(1, "strong");

			/*
			 * ret.put(4, new Pair<String, ArrayList<String>>(new
			 * String("Play"), new ArrayList<String>()));
			 * ret.get(4).getValue().add(0, "1"); ret.get(4).getValue().add(1,
			 * "2");
			 */
			break;
		}

		return ret;
	}

	/**
	 * Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DataManager.dataToMatrix(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE)).forEach((elem) -> {
			elem.print(10, 3);
		});
		DataManager.dataToMatrix(DataManager.getAllOfType(DataTypeEnum.IRIS)).forEach((elem) -> {
			elem.print(10, 3);
		});
		DataManager.dataToMatrix(DataManager.getAllOfType(DataTypeEnum.WINE)).forEach((elem) -> {
			elem.print(10, 3);
		});
	}
}
