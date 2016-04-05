/**
 * @(#)Matrixex.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * Matrixex class with static operations
 */

package helper;

@Deprecated
public class Matrixex {

	private int rows;
	private int cols;
	private double[][] data;

	@Override
	public String toString() {
		String ret = new String();
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				ret += this.data[i][j] + "\t";
			}
			ret += "\n";
		}
		return ret;
	}

	/**
	 * make a row by col Matrixex
	 * 
	 * @param row
	 * @param col
	 */
	public Matrixex(int row, int col) {
		this.rows = row;
		this.cols = col;
		data = new double[row][col];
	}

	/**
	 * make Matrixex out of data
	 * 
	 * @param data
	 */
	public Matrixex(double[][] data) {
		this(data.length, data[0].length);
		this.setData(data);
	}

	/**
	 * copy a Matrixex
	 * 
	 * @param other
	 */
	public Matrixex(Matrixex other) {
		this(other.getData());
	}

	/**
	 * @return number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return number of cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @return data of Matrixex
	 */
	public double[][] getData() {
		return data;
	}

	/**
	 * @param i
	 * @param j
	 * @return data in Matrixex and row i col j
	 */
	public double getValueAt(int i, int j) {
		if ((i > this.rows || i < 0) || (j > this.cols || j < 0))
			throw new RuntimeException("Illegal Matrixex dimensions.");
		return data[i][j];
	}

	/**
	 * @return if this is a square Matrixex
	 */
	public boolean isSquare() {
		return this.rows == this.cols;
	}

	/**
	 * set number of rows
	 * 
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * set number of cols
	 * 
	 * @param cols
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * set data of Matrixex
	 * 
	 * @param data
	 */
	public void setData(double data[][]) {
		if (data.length != this.rows || data[0].length != this.cols)
			throw new RuntimeException("Illegal Matrixex dimensions.");
		for (int i = 0; i < this.rows; i++)
			for (int j = 0; j < this.cols; j++)
				this.data[i][j] = data[i][j];
	}

	/**
	 * set data at row i and col j
	 * 
	 * @param i
	 * @param j
	 * @param value
	 */
	public void setValueAt(int i, int j, double value) {
		if ((i > this.rows || i < 0) || (j > this.cols || j < 0))
			throw new RuntimeException("Illegal Matrixex dimensions.");
		this.data[i][j] = value;
	}

	/**
	 * @param value
	 * @return 1 is even -1 if odd
	 */
	private static int sign(int value) {
		return (value % 2) == 0 ? 1 : -1;
	}

	/**
	 * @param Matrixex
	 * @return the transpose of Matrixex
	 */
	public static Matrixex transpose(Matrixex Matrixex) {
		Matrixex transpose = new Matrixex(Matrixex.getRows(), Matrixex.getCols());
		for (int i = 0; i < Matrixex.getRows(); i++)
			for (int j = 0; j < Matrixex.getCols(); j++)
				transpose.setValueAt(j, i, Matrixex.getValueAt(i, j));
		return transpose;
	}

	/**
	 * @param Matrixex
	 * @return the determinant of Matrixex
	 */
	public static double determinant(Matrixex Matrixex) {
		if (!Matrixex.isSquare())
			throw new RuntimeException("Matrixex need to be square.");

		// base case 1
		if (Matrixex.getRows() == 1)
			return Matrixex.getValueAt(0, 0);

		// base case 2
		if (Matrixex.getRows() == 2)
			return (Matrixex.getValueAt(0, 0) * Matrixex.getValueAt(1, 1))
					- (Matrixex.getValueAt(0, 1) * Matrixex.getValueAt(1, 0));

		// recursive step
		double sum = 0.0;

		for (int i = 0; i < Matrixex.getCols(); i++)
			sum += sign(i) * Matrixex.getValueAt(0, i) * determinant(createSubMatrix(Matrixex, 0, i));

		return sum;
	}

	/**
	 * @param Matrixex
	 * @param excluding_row
	 * @param excluding_col
	 * @return a Matrixex excluding a row/column
	 */
	public static Matrixex createSubMatrix(Matrixex Matrixex, int excluding_row, int excluding_col) {
		Matrixex mat = new Matrixex(Matrixex.getRows() - 1, Matrixex.getCols() - 1);
		int r = -1;
		for (int i = 0; i < Matrixex.getRows(); i++) {
			if (i == excluding_row)
				continue;
			r++;
			int c = -1;
			for (int j = 0; j < Matrixex.getCols(); j++) {
				if (j == excluding_col)
					continue;
				mat.setValueAt(r, ++c, Matrixex.getValueAt(i, j));
			}
		}
		return mat;
	}

	/**
	 * @param Matrixex
	 * @return cofactor of Matrixex
	 */
	public static Matrixex cofactor(Matrixex Matrixex) {
		Matrixex mat = new Matrixex(Matrixex.getRows(), Matrixex.getCols());
		for (int i = 0; i < Matrixex.getRows(); i++)
			for (int j = 0; j < Matrixex.getCols(); j++)
				mat.setValueAt(i, j, sign(i) * sign(j) * determinant(createSubMatrix(Matrixex, i, j)));
		return mat;
	}

	/**
	 * @param Matrixex
	 * @return the inverse of Matrixex
	 */
	public static Matrixex inverse(Matrixex Matrixex) {
		return multiply(1.0 / determinant(Matrixex), transpose(cofactor(Matrixex)));
	}

	/**
	 * @param scaler
	 * @param Matrixex
	 * @return scaler multiplication of cA
	 */
	public static Matrixex multiply(double scaler, Matrixex Matrixex) {
		Matrixex mat = new Matrixex(Matrixex.getRows(), Matrixex.getCols());

		for (int i = 0; i < Matrixex.getRows(); i++)
			for (int j = 0; j < Matrixex.getCols(); j++)
				mat.setValueAt(i, j, scaler * Matrixex.getValueAt(i, j));

		return mat;
	}

	/**
	 * @param matrixA
	 * @param matrixB
	 * @return Matrixex multiplication of A*B
	 */
	public static Matrixex multiply(Matrixex matrixA, Matrixex matrixB) {
		if (matrixA.getCols() != matrixB.getRows())
			throw new RuntimeException("Illegal Matrixex dimensions.");

		Matrixex mat = new Matrixex(matrixA.getRows(), matrixB.getCols());

		for (int i = 0; i < matrixA.getRows(); i++)
			for (int j = 0; j < matrixB.getCols(); j++)
				mat.setValueAt(i, j, 0);

		for (int i = 0; i < matrixA.getRows(); i++)
			for (int j = 0; j < matrixB.getCols(); j++)
				for (int k = 0; k < matrixA.getCols(); k++)
					mat.setValueAt(i, j, mat.getValueAt(i, j) + (matrixA.getValueAt(i, k) * matrixB.getValueAt(k, j)));

		return mat;
	}

	/**
	 * @param n
	 * @return n-by-n identity Matrixex
	 */
	public static Matrixex identity(int n) {
		if (n < 1)
			throw new RuntimeException("Illegal Matrixex dimensions.");

		Matrixex mat = new Matrixex(n, n);

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				mat.setValueAt(i, j, 0);

		for (int i = 0; i < n; i++)
			mat.setValueAt(i, i, 1);

		return mat;
	}

	/**
	 * @param matrixA
	 * @param matrixB
	 * @return Matrixex addition of A+B
	 */
	public static Matrixex addition(Matrixex matrixA, Matrixex matrixB) {
		if ((matrixA.getCols() != matrixB.getCols()) || (matrixA.getRows() != matrixB.getRows()))
			throw new RuntimeException("Illegal Matrixex dimensions.");

		Matrixex mat = new Matrixex(matrixA.getRows(), matrixB.getCols());

		for (int i = 0; i < matrixA.getRows(); i++)
			for (int j = 0; j < matrixB.getCols(); j++)
				mat.setValueAt(i, j, matrixA.getValueAt(i, j) + matrixB.getValueAt(i, j));

		return mat;
	}

	/**
	 * @param matrixA
	 * @param matrixB
	 * @return Matrixex subtract of A-B
	 */
	public static Matrixex subtract(Matrixex matrixA, Matrixex matrixB) {
		if ((matrixA.getCols() != matrixB.getCols()) || (matrixA.getRows() != matrixB.getRows()))
			throw new RuntimeException("Illegal Matrixex dimensions.");

		Matrixex mat = new Matrixex(matrixA.getRows(), matrixB.getCols());

		for (int i = 0; i < matrixA.getRows(); i++)
			for (int j = 0; j < matrixB.getCols(); j++)
				mat.setValueAt(i, j, matrixA.getValueAt(i, j) - matrixB.getValueAt(i, j));

		return mat;
	}

	/**
	 * Test
	 * @param args
	 */
	public static void main(String[] args) {

		double[][] d = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 1, 2, 3 }, { 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6 },
				{ 7, 8, 9, 1, 2, 3 }, { 4, 5, 6, 7, 8, 9 } };

		double[][] d1 = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 1, 2, 3 }, { 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6 },
				{ 7, 8, 9, 1, 2, 3 }, { 4, 5, 6, 7, 8, 9 } };

		System.out.println(Matrixex.subtract(new Matrixex(d), new Matrixex(d1)).toString());

	}
}