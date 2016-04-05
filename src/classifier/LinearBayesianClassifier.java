/**
 * @(#)LinearBaysianClassifier.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * LinearBaysian Classifier class
 */

package classifier;

import Jama.Matrix;

public class LinearBayesianClassifier extends BaseBayesianClassifier {

	@Override
	protected void generateVarianceA() {
		super.varianceA = new Matrix(this.getAverage().getArray());
		super.varianceB = new Matrix(this.getAverage().getArray());
	}

	@Override
	protected void generateVarianceB() {
		super.varianceB = new Matrix(this.getAverage().getArray());
		super.varianceA = new Matrix(this.getAverage().getArray());
	}

	private Matrix getAverage() {
		Matrix a = null;
		Matrix b = null;

		if (super.trainingDataA != null) {
			// generate mean if we dont have it already
			if (super.meanA == null)
				super.generateMeanA();

			// number of entries
			int n = this.trainingDataA.size();

			// number of features
			int d = super.meanA.getRowDimension();

			// dxd covariance matrix
			a = new Matrix(d, d);

			// go over each feature i
			for (int i = 0; i < d; i++) {
				// go over each feature j
				for (int j = 0; j < d; j++) {
					// the sum of the differences of feature i and its mean
					// and multiple it by
					// the sum of the differences of feature j and its mean
					for (int k = 0; k < n; k++) {
						// ijth features variance += (ith features - ith
						// mean)*(jth features - jth mean)
						a.set(i, j, a.get(i, j) + ((super.trainingDataA.get(k).get(i, 0) - super.meanA.get(i, 0))
								* (super.trainingDataA.get(k).get(j, 0) - super.meanA.get(j, 0))));
					}
					// divide by number of entries - 1 to get feature variance
					// n - 1 to get rid of bias from estimated mean
					a.set(i, j, a.get(i, j) / (n - 1));
				}
			}
		}

		if (super.trainingDataB != null) {
			// generate mean if we dont have it already
			if (super.meanB == null)
				super.generateMeanB();

			// number of entries
			int n = this.trainingDataB.size();

			// number of features
			int d = super.meanB.getRowDimension();

			// dxd covariance matrix
			b = new Matrix(d, d);

			// go over each feature i
			for (int i = 0; i < d; i++) {
				// go over each feature j
				for (int j = 0; j < d; j++) {
					// the sum of the differences of feature i and its mean
					// and multiple it by
					// the sum of the differences of feature j and its mean
					for (int k = 0; k < n; k++) {
						// ijth features variance += (ith features - ith
						// mean)*(jth features - jth mean)
						b.set(i, j, b.get(i, j) + ((super.trainingDataB.get(k).get(i, 0) - super.meanB.get(i, 0))
								* (super.trainingDataB.get(k).get(j, 0) - super.meanB.get(j, 0))));
					}
					// divide by number of entries - 1 to get feature variance
					// n - 1 to get rid of bias from estimated mean
					b.set(i, j, b.get(i, j) / (n - 1));
				}
			}
		}

		if (a == null && b == null)
			return null;
		else if (a != null && b == null)
			return a;
		else if (a == null && b != null)
			return b;
		else
			return (a.plus(b)).times(0.5);
	}
}