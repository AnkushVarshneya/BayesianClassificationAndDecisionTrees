/**
 * @(#)NaiveBaysianClassifier.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * NaiveBaysian Classifier class
 */

package classifier;

import Jama.Matrix;

public class NaiveBayesianClassifier extends BaseBayesianClassifier {

	@Override
	protected void generateVarianceA() {
		if (super.trainingDataA != null) {
			// generate mean if we dont have it already
			if (super.meanA == null)
				super.generateMeanA();

			// number of entries
			int n = this.trainingDataA.size();

			// number of features
			int d = super.meanA.getRowDimension();

			// dxd covariance matrix
			super.varianceA = new Matrix(d, d);

			// go over each feature i
			for (int i = 0; i < d; i++) {
				// go over each feature j
				for (int j = 0; j < d; j++) {
					// do not calculate covariances for naive basian
					if (i == j) {
						// the sum of the differences of feature i and its mean
						// and multiple it by
						// the sum of the differences of feature j and its mean
						for (int k = 0; k < n; k++) {
							// ijth features variance += (ith features - ith
							// mean)*(jth features - jth mean)
							super.varianceA
									.set(i, j,
											super.varianceA.get(i, j) + ((super.trainingDataA.get(k).get(i, 0)
													- super.meanA.get(i, 0))
													* (super.trainingDataA.get(k).get(j, 0) - super.meanA.get(j, 0))));
						}
						// divide by number of entries - 1 to get feature
						// variance
						// n - 1 to get rid of bias from estimated mean
						this.varianceA.set(i, j, this.varianceA.get(i, j) / (n - 1));
					}
				}
			}
		}
	}

	@Override
	protected void generateVarianceB() {
		if (super.trainingDataB != null) {
			// generate mean if we dont have it already
			if (super.meanB == null)
				super.generateMeanB();

			// number of entries
			int n = this.trainingDataB.size();

			// number of features
			int d = super.meanB.getRowDimension();

			// dxd covariance matrix
			super.varianceB = new Matrix(d, d);

			// go over each feature i
			for (int i = 0; i < d; i++) {
				// go over each feature j
				for (int j = 0; j < d; j++) {
					// do not calculate covariances for naive basian
					if (i == j) {
						// the sum of the differences of feature i and its mean
						// and multiple it by
						// the sum of the differences of feature j and its mean
						for (int k = 0; k < n; k++) {
							// ijth features variance += (ith features - ith
							// mean)*(jth features - jth mean)
							super.varianceB
									.set(i, j,
											super.varianceB.get(i, j) + ((super.trainingDataB.get(k).get(i, 0)
													- super.meanB.get(i, 0))
													* (super.trainingDataB.get(k).get(j, 0) - super.meanB.get(j, 0))));
						}
						// divide by number of entries - 1 to get feature
						// variance
						// n - 1 to get rid of bias from estimated mean
						this.varianceB.set(i, j, this.varianceB.get(i, j) / (n - 1));
					}
				}
			}
		}
	}
}