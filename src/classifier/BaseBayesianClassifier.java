/**
 * @(#)BaseClassifier.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * Classifier base class
 */

package classifier;

import java.util.ArrayList;

import Jama.Matrix;
import data.DataManager;
import data.DataTypeEnum;

public abstract class BaseBayesianClassifier {
	protected ArrayList<Matrix> trainingDataA; // list of vectors with all the
												// training data
	protected ArrayList<Matrix> trainingDataB; // list of vectors with all the
												// training data

	protected Matrix meanA; // vector with all the means
	protected Matrix meanB; // vector with all the means

	protected Matrix varianceA; // matrix with all the variances
	protected Matrix varianceB; // matrix with all the variances

	protected int classAID; // ID of class A
	protected int classBID; // ID of class B

	protected double classAProb; // probability of class A
	protected double classBProb; // probability of class B

	public void setTrainingDataA(ArrayList<Matrix> trainingDataA, int classID, double prob) {
		this.trainingDataA = trainingDataA;
		// after setting this regenerate mean and variance
		this.generateMeanA();
		this.generateVarianceA();
		this.classAID = classID;
		this.classAProb = prob;
	}

	public void setTrainingDataB(ArrayList<Matrix> trainingDataB, int classID, double prob) {
		this.trainingDataB = trainingDataB;
		// after setting this regenerate mean and variance
		this.generateMeanB();
		this.generateVarianceB();
		this.classBID = classID;
		this.classBProb = prob;
	}

	protected void generateMeanA() {
		if (this.trainingDataA != null) {
			// number of entries
			int n = this.trainingDataA.size();
			// number of features
			int d = this.trainingDataA.get(0).getRowDimension();

			// dx1 mean vector/matrix
			this.meanA = new Matrix(d, 1);

			// go over each feature i
			for (int i = 0; i < d; i++) {
				// take the sum of all entries feature i
				for (int j = 0; j < n; j++) {
					// ith features of mean += jth entries ith feature
					this.meanA.set(i, 0, this.meanA.get(i, 0) + this.trainingDataA.get(j).get(i, 0));
				}
				// divide by number of entries to get feature mean
				this.meanA.set(i, 0, this.meanA.get(i, 0) / n);
			}
		}
	}

	protected void generateMeanB() {
		if (this.trainingDataB != null) {
			// number of entries
			int n = this.trainingDataB.size();
			// number of features
			int d = this.trainingDataB.get(0).getRowDimension();

			// dx1 mean vector/matrix
			this.meanB = new Matrix(d, 1);

			// go over each feature i
			for (int i = 0; i < d; i++) {
				// take the sum of all entries feature i
				for (int j = 0; j < n; j++) {
					// ith features of mean += jth entries ith feature
					this.meanB.set(i, 0, this.meanB.get(i, 0) + this.trainingDataB.get(j).get(i, 0));
				}
				// divide by number of entries to get feature mean
				this.meanB.set(i, 0, this.meanB.get(i, 0) / n);
			}
		}
	}

	protected abstract void generateVarianceA();

	protected abstract void generateVarianceB();

	/**
	 * Get classification results
	 * 
	 * @param x
	 *            the x vector to classify
	 * @return class a or b
	 */
	public int classify(Matrix x) {
		double c = .5*(
						Math.log(this.getVarianceB().det())
						-
						Math.log(this.getVarianceA().det())
						+
						((x.minus(this.getMeanB())).transpose())
						.times(this.getVarianceB().inverse())
						.times(x.minus(this.getMeanB()))		
						.get(0, 0)
						-
						((x.minus(this.getMeanA())).transpose())
						.times(this.getVarianceA().inverse())
						.times(x.minus(this.getMeanA()))
						.get(0, 0)
					  )
				+ Math.log(this.classAProb/this.classBProb);
		return c > 0 ? this.classAID : this.classBID;
	}

	public Matrix getMeanA() {
		if (this.meanA == null)
			this.generateMeanA();
		return this.meanA;
	}

	public Matrix getMeanB() {
		if (this.meanB == null)
			this.generateMeanB();
		return this.meanB;
	}

	public Matrix getVarianceA() {
		if (this.varianceA == null)
			this.generateVarianceA();
		return this.varianceA;
	}

	public Matrix getVarianceB() {
		if (this.varianceB == null)
			this.generateVarianceB();
		return this.varianceB;
	}

	/**
	 * Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		BaseBayesianClassifier classifier1 = new OptimalBayesianClassifier();
		classifier1.setTrainingDataA(DataManager.dataToMatrix(
				DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 1)), 1, 0);
		classifier1.setTrainingDataB(DataManager.dataToMatrix(
				DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 3)), 3, 0);
		int a = classifier1.classify(DataManager
				.dataToMatrix(
						DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 1))
				.get(0));

		BaseBayesianClassifier classifier2 = new LinearBayesianClassifier();
		classifier2.setTrainingDataA(DataManager.dataToMatrix(
				DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 1)), 1, 0);
		classifier2.setTrainingDataB(DataManager.dataToMatrix(
				DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 3)), 3, 0);
		int b = classifier2.classify(DataManager
				.dataToMatrix(
						DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 1))
				.get(0));

		BaseBayesianClassifier classifier3 = new NaiveBayesianClassifier();
		classifier3.setTrainingDataA(DataManager.dataToMatrix(
				DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 1)), 1, 0);
		classifier3.setTrainingDataB(DataManager.dataToMatrix(
				DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 3)), 3, 0);
		int c = classifier3.classify(DataManager
				.dataToMatrix(
						DataManager.getAllFromArrayOfClass(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE), 1))
				.get(0));

		System.out.println(a + " " + b + " " + c + " ");

		boolean equal = true;
		for (int i = 0; i < classifier2.getVarianceA().getRowDimension(); i++)
			for (int j = 0; j < classifier2.getVarianceA().getColumnDimension(); j++)
				equal = equal
						&& (classifier1.getVarianceA().plus(classifier1.getVarianceB())).times(0.5).get(i,
								j) == classifier2.getVarianceA().get(i, j)
						&& (classifier1.getVarianceA().plus(classifier1.getVarianceB())).times(0.5).get(i,
								j) == classifier2.getVarianceB().get(i, j);
		System.out.println(equal);

		equal = true;
		for (int i = 0; i < classifier2.getVarianceA().getRowDimension(); i++)
			for (int j = 0; j < classifier2.getVarianceA().getColumnDimension(); j++)
				if (i == j)
					equal = equal && classifier1.getVarianceA().get(i, j) == classifier3.getVarianceA().get(i, j)
							&& classifier1.getVarianceB().get(i, j) == classifier3.getVarianceB().get(i, j);
		System.out.println(equal);

		BaseBayesianClassifier classifier4 = new OptimalBayesianClassifier();
		classifier4.setTrainingDataA(DataManager.dataToMatrix(DataManager.getAllOfType(DataTypeEnum.HEARTDISEASE)), 0, 0);
		classifier4.setTrainingDataB(DataManager.dataToMatrix(DataManager.getAllOfType(DataTypeEnum.IRIS)), 0, 0);

		BaseBayesianClassifier classifier5 = new OptimalBayesianClassifier();
		classifier5.setTrainingDataA(DataManager.dataToMatrix(DataManager.getAllOfType(DataTypeEnum.WINE)), 0, 0);

		classifier4.getMeanA().print(10, 4);
		classifier4.getVarianceA().print(10, 4);

		classifier4.getMeanB().print(10, 4);
		classifier4.getVarianceB().print(10, 4);

		classifier5.getMeanA().print(10, 4);
		classifier5.getVarianceA().print(10, 4);
	}
}
