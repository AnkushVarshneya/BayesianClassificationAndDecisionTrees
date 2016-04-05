/**
 * @(#)BaseData.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * BAse Object to store Data/Record Type
 */

package data;

import Jama.Matrix;

public abstract class BaseData {
	public abstract int getClassNumber();

	public abstract Matrix toMatrix();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();

	public abstract String getAttributesClassByNumber(int attributeNumber);
}
