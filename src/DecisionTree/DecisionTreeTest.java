/**
 * @(#)ClassificationTest.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * Classifier test singleton
 */

package DecisionTree;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

import data.BaseData;
import data.DataManager;
import data.DataTypeEnum;

public class DecisionTreeTest {
	private static DecisionTreeTest instance = null;

	private DecisionTreeTest() {
	}

	public static synchronized DecisionTreeTest getInstance() {
		if (instance == null)
			instance = new DecisionTreeTest();
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
	public static double tenFoldTest(DataTypeEnum dataType) {
		int tries = 0;
		int passed = 0;

		// get all the data and randomize it
		ArrayList<BaseData> fullDataSet = DataManager.getAllOfType(dataType);
		Collections.shuffle(fullDataSet);

		// split array into 10 parts
		ArrayList<ArrayList<BaseData>> fullDataSetCunks = chopIntoParts(fullDataSet, 10);

		for (ArrayList<BaseData> testSet : fullDataSetCunks) {
			ArrayList<BaseData> fullTrainingSet = joinPartsExcluding(fullDataSetCunks, testSet);
			DecisionTree tree = new DecisionTree(dataType, fullTrainingSet);

			// test over testSet
			for (BaseData test : testSet) {
				Node root = tree.buildTree();

				// classify
				int result = tree.classify(root, test);

				// made a try
				tries++;

				// passed!
				if (test.getClassNumber() == result)
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
	public static double leaveOneOutTest(DataTypeEnum dataType) {
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

			DecisionTree tree = new DecisionTree(dataType, fullTrainingSet);
			Node root = tree.buildTree();

			// classify
			int result = tree.classify(root, test);

			// made a try
			tries++;

			// passed!
			if (test.getClassNumber() == result)
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
				Graph frame = new Graph(new DecisionTree(dataType, DataManager.getAllOfType(dataType)));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(1200, 700);
				frame.setVisible(true);
			}
		}

		for (DataTypeEnum dataType : DataTypeEnum.values()) {
			if (!dataType.equals(DataTypeEnum.WEATHER)) {
				System.out.println("Leave One Out Test for decision tree for " + dataType.toString() + ": "
						+ DecisionTreeTest.leaveOneOutTest(dataType) + "%");
				System.out.println("");
			}
		}

		for (DataTypeEnum dataType : DataTypeEnum.values()) {
			if (!dataType.equals(DataTypeEnum.WEATHER)) {
				System.out.println("Ten Fold Test for decision tree for " + dataType.toString()
						+ ": " + DecisionTreeTest.tenFoldTest(dataType) + "%");
				System.out.println("");
			}
		}

	}
}
