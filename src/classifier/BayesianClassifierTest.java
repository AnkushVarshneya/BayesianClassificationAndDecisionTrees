/**
 * @(#)ClassificationTest.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * Classifier test singleton
 */

package classifier;

import java.util.ArrayList;
import java.util.Collections;

import Jama.Matrix;
import data.BaseData;
import data.DataManager;
import data.DataTypeEnum;

public class BayesianClassifierTest {
	private static BayesianClassifierTest instance = null;

	private BayesianClassifierTest() {
	}

	public static synchronized BayesianClassifierTest getInstance() {
		if (instance == null)
			instance = new BayesianClassifierTest();
		return instance;
	}

	/**
	 * Ten Fold test
	 * 
	 * @param dataType
	 *            the type of data to get
	 * @param classifierType
	 *            the classifier type to use for this test
	 * @return the accuracy of this test
	 */
	public static double tenFoldTest(DataTypeEnum dataType, BayesianClassifierTypeEnum classifierType) {
		int tries = 0;
		int passed = 0;

		// get all the data and randomize it
		ArrayList<BaseData> fullDataSet = DataManager.getAllOfType(dataType);
		Collections.shuffle(fullDataSet);

		// split array into 10 parts
		ArrayList<ArrayList<BaseData>> fullDataSetCunks = chopIntoParts(fullDataSet, 10);

		for (ArrayList<BaseData> testSet : fullDataSetCunks) {
			ArrayList<BaseData> fullTrainingSet = joinPartsExcluding(fullDataSetCunks, testSet);

			// test over testSet
			for (BaseData test : testSet) {
				// make a counter for each class, 0 not used
				int counter[] = new int[dataType.getNumberOfClases() + 1];

				// test for all pairwise classes classes start at 1
				for (int i = 1; i < dataType.getNumberOfClases(); i++) {
					for (int j = 2; j < (dataType.getNumberOfClases() + 1); j++) {
						// make the the classifier and train on class i and j
						BaseBayesianClassifier classifier = null;
						switch (classifierType) {
						case OPTIMAL:
							classifier = new OptimalBayesianClassifier();
							break;
						case NAIVE:
							classifier = new NaiveBayesianClassifier();
							break;
						case LINEAR:
							classifier = new LinearBayesianClassifier();
							break;
						default:
							// else its null should never reach here!
							throw new RuntimeException("Illegal classifier: " + classifierType);
						}

						// get the classes data
						ArrayList<Matrix> trainA = DataManager
								.dataToMatrix(DataManager.getAllFromArrayOfClass(fullTrainingSet, i));
						ArrayList<Matrix> trainB = DataManager
								.dataToMatrix(DataManager.getAllFromArrayOfClass(fullTrainingSet, j));

						// train the classes
						classifier.setTrainingDataA(trainA, i, DataManager.getClassProbability(fullDataSet,
								dataType.getNumberOfAttributes(), new Integer(i).toString()));
						classifier.setTrainingDataB(trainB, j, DataManager.getClassProbability(fullDataSet,
								dataType.getNumberOfAttributes(), new Integer(j).toString()));

						// get the result
						int result = classifier.classify(test.toMatrix());

						// increase this classes count,
						counter[result]++;
					}
				}

				// find the max value
				int max = Integer.MIN_VALUE;
				for (int i = 1; i <= dataType.getNumberOfClases(); i++)
					if (counter[i] > max)
						max = counter[i];

				// make an array with all indexes of max value
				// used for ties
				ArrayList<Integer> classGuessed = new ArrayList<Integer>();
				for (int i = 1; i <= dataType.getNumberOfClases(); i++)
					if (counter[i] == max)
						classGuessed.add(i);

				// made a try
				tries++;

				// passed!
				if (classGuessed.contains(test.getClassNumber()))
					passed++;
			}
		}

		return ((new Double(passed)) / (new Double(tries))) * new Double(100);
	}

	/**
	 * Leave one out test
	 * 
	 * @param dataType
	 *            the type of data to get
	 * @param classifierType
	 *            the classifier type to use for this test
	 * @return the accuracy of this test
	 */
	public static double leaveOneOutTest(DataTypeEnum dataType, BayesianClassifierTypeEnum classifierType) {
		int tries = 0;
		int passed = 0;

		// get all the data and randomize it
		ArrayList<BaseData> fullDataSet = DataManager.getAllOfType(dataType);
		Collections.shuffle(fullDataSet);

		for (BaseData test : fullDataSet) {
			// make the full training set
			ArrayList<BaseData> fullTrainingSet = new ArrayList<BaseData>();
			fullTrainingSet.addAll(fullDataSet);
			fullTrainingSet.remove(test);

			// make a counter for each class, 0 not used
			int counter[] = new int[dataType.getNumberOfClases() + 1];

			// test for all pairwise classes classes start at 1
			for (int i = 1; i < dataType.getNumberOfClases(); i++) {
				for (int j = 2; j < (dataType.getNumberOfClases() + 1); j++) {
					// make the the classifier and train on class i and j
					BaseBayesianClassifier classifier = null;
					switch (classifierType) {
					case OPTIMAL:
						classifier = new OptimalBayesianClassifier();
						break;
					case NAIVE:
						classifier = new NaiveBayesianClassifier();
						break;
					case LINEAR:
						classifier = new LinearBayesianClassifier();
						break;
					default:
						// else its null should never reach here!
						throw new RuntimeException("Illegal classifier: " + classifierType);
					}

					// get the classes data
					ArrayList<Matrix> trainA = DataManager
							.dataToMatrix(DataManager.getAllFromArrayOfClass(fullTrainingSet, i));
					ArrayList<Matrix> trainB = DataManager
							.dataToMatrix(DataManager.getAllFromArrayOfClass(fullTrainingSet, j));

					// train the classes
					classifier.setTrainingDataA(trainA, i, DataManager.getClassProbability(fullDataSet,
							dataType.getNumberOfAttributes(), new Integer(i).toString()));
					classifier.setTrainingDataB(trainB, j, DataManager.getClassProbability(fullDataSet,
							dataType.getNumberOfAttributes(), new Integer(j).toString()));

					// get the result
					int result = classifier.classify(test.toMatrix());

					// increase this classes count,
					counter[result]++;
				}
			}

			// find the max value
			int max = Integer.MIN_VALUE;
			for (int i = 1; i <= dataType.getNumberOfClases(); i++)
				if (counter[i] > max)
					max = counter[i];

			// make an array with all indexes of max value
			// used for ties
			ArrayList<Integer> classGuessed = new ArrayList<Integer>();
			for (int i = 1; i <= dataType.getNumberOfClases(); i++)
				if (counter[i] == max)
					classGuessed.add(i);

			// made a try
			tries++;

			// passed!
			if (classGuessed.contains(test.getClassNumber()))
				passed++;
		}

		return ((new Double(passed)) / (new Double(tries))) * new Double(100);
	}

	/**
	 * Utility function to split an array
	 * 
	 * @param list
	 *            the list to split
	 * @param iParts
	 *            number of parts to split
	 * @return split array of arrays
	 */
	private static ArrayList<ArrayList<BaseData>> chopIntoParts(final ArrayList<BaseData> list, final int iParts) {
		final ArrayList<ArrayList<BaseData>> lsParts = new ArrayList<ArrayList<BaseData>>();
		final int iChunkSize = list.size() / iParts;
		int iLeftOver = list.size() % iParts;
		int iTake = iChunkSize;

		for (int i = 0, iT = list.size(); i < iT; i += iTake) {
			if (iLeftOver-- > 0)
				iTake = iChunkSize + 1;
			else
				iTake = iChunkSize;

			lsParts.add(new ArrayList<BaseData>(list.subList(i, Math.min(iT, i + iTake))));
		}
		return lsParts;
	}

	/**
	 * Utility function to join parts into an array excluding a part
	 * 
	 * @param parts
	 *            sub arrays
	 * @param toExclude
	 *            part to exclude
	 * @return joined array
	 * 
	 */
	private static ArrayList<BaseData> joinPartsExcluding(final ArrayList<ArrayList<BaseData>> parts,
			final ArrayList<BaseData> toExclude) {
		final ArrayList<BaseData> fullList = new ArrayList<BaseData>();

		for (ArrayList<BaseData> aPart : parts)
			if (aPart != toExclude)
				fullList.addAll(aPart);

		return fullList;
	}

	/**
	 * Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		for (DataTypeEnum dataType : DataTypeEnum.values()) {
			if (!dataType.equals(DataTypeEnum.WEATHER)) {
				for (BayesianClassifierTypeEnum classifieType : BayesianClassifierTypeEnum.values())
					System.out.println("Leave One Out Test for " + classifieType.toString()
							+ " Bayesian Classifier for " + dataType.toString() + ": "
							+ BayesianClassifierTest.leaveOneOutTest(dataType, classifieType) + "%");
				System.out.println("");
			}
		}

		for (DataTypeEnum dataType : DataTypeEnum.values()) {
			if (!dataType.equals(DataTypeEnum.WEATHER)) {
				for (BayesianClassifierTypeEnum classifieType : BayesianClassifierTypeEnum.values())
					System.out.println("Ten Fold Test for " + classifieType.toString() + " Bayesian Classifier for "
							+ dataType.toString() + ": " + BayesianClassifierTest.tenFoldTest(dataType, classifieType)
							+ "%");
				System.out.println("");
			}
		}

	}
}
