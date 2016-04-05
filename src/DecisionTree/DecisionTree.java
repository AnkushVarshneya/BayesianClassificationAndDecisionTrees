/**
 * @(#)DecisionTree.java
 * Assignment#3
 * @author Ankush Varshneya
 * @student# 100853074
 * Decision Tree
 */

package DecisionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import data.BaseData;
import data.DataManager;
import data.DataTypeEnum;
import javafx.util.Pair;

public class DecisionTree {
	private DataTypeEnum type;
	private ArrayList<BaseData> traingingData;

	public DecisionTree(DataTypeEnum type, ArrayList<BaseData> traingingData) {
		super();
		this.type = type;
		this.traingingData = traingingData;
	}

	public DataTypeEnum getType() {
		return type;
	}

	public void setType(DataTypeEnum type) {
		this.type = type;
	}

	public double entropy(ArrayList<BaseData> set, int attributeNumber) {
		double entropy = 0.0;

		if (attributeNumber < type.getNumberOfAttributes()) {
			for (String value : DataManager.possibleAttributeValues(type).get(attributeNumber).getValue()) {
				double prob = DataManager.getClassProbability(set, attributeNumber, value);
				entropy += prob == 0 ? 0 : (-prob) * (Math.log(prob) / Math.log(2));
			}
		} else {
			for (int i = 1; i <= type.getNumberOfClases(); i++) {
				double prob = DataManager.getClassProbability(set, type.getNumberOfAttributes(),
						new Integer(i).toString());
				entropy += prob == 0 ? 0 : (-prob) * (Math.log(prob) / Math.log(2));
			}
		}
		return entropy;
	}

	public double gain(ArrayList<BaseData> set, int attributeNumber, int attributeDesition) {
		double info = 0.0;

		for (String value : DataManager.possibleAttributeValues(type).get(attributeNumber).getValue())
			info += DataManager.getClassProbability(set, attributeNumber, value)
					* entropy(DataManager.getSubSet(set, attributeNumber, value), attributeDesition);

		return entropy(set, attributeDesition) - info;
	}

	public Node buildTree(ArrayList<BaseData> set, HashMap<Integer, Pair<String, ArrayList<String>>> attributes,
			String branch, int attributeDesition) {
		Node aNode = new Node();
		if (attributes.isEmpty() || this.entropy(set, type.getNumberOfAttributes()) == 0.0) {
			int popularClass = Integer.MIN_VALUE;
			double MaxProbability = Integer.MIN_VALUE;

			for (int i = 1; i <= type.getNumberOfClases(); i++) {
				double probability = DataManager.getClassProbability(set, type.getNumberOfAttributes(),
						new Integer(i).toString());
				if (probability > MaxProbability) {
					MaxProbability = probability;
					popularClass = i;
				}
			}

			if (popularClass < 1)
				throw new RuntimeException("Illegal class: " + popularClass);

			// set node
			aNode.setAttributeID(type.getNumberOfClases());
			aNode.setLabel(new Integer(popularClass).toString());
			aNode.setBranch(branch);
			aNode.setLeafNode(true);
			aNode.setChildren(new ArrayList<Node>());

			return aNode;
		}

		int maxAttribute = Integer.MIN_VALUE;
		double maxGain = Integer.MIN_VALUE;

		for (Entry<Integer, Pair<String, ArrayList<String>>> entry : attributes.entrySet()) {
			if (entry.getValue() != null) {
				double gain = this.gain(set, entry.getKey(), type.getNumberOfAttributes());
				if (gain > maxGain) {
					maxGain = gain;
					maxAttribute = entry.getKey();
				}
				// TODO:
				// System.out.println("Branch: " + branch + " Info: " +
				// entry.getKey() + ": " + entry.getValue().getKey() + ":\t" +
				// gain);
			}
		}

		if (maxAttribute < 0)
			throw new RuntimeException("Illegal atribute: " + maxAttribute);

		// set node
		aNode.setAttributeID(maxAttribute);
		aNode.setLabel(attributes.get(maxAttribute).getKey());
		aNode.setBranch(branch);
		aNode.setLeafNode(false);
		aNode.setChildren(new ArrayList<Node>());

		for (String value : attributes.get(maxAttribute).getValue()) {
			// remove the current attribute
			HashMap<Integer, Pair<String, ArrayList<String>>> subAttributes = DataManager
					.clonePossibleAttributeValues(attributes);
			subAttributes.remove(maxAttribute);
			ArrayList<BaseData> subSet = DataManager.getSubSet(set, maxAttribute, value);
			Node childNode = buildTree(subSet, subAttributes, value, maxAttribute);
			aNode.getChildren().add(childNode);
		}

		return aNode;
	}

	public Node buildTree() {
		return buildTree(this.traingingData, DataManager.possibleAttributeValues(type), null,
				type.getNumberOfAttributes());
	}

	public int classify(Node aNode, BaseData data) {
		if (aNode.getChildren().isEmpty())
			return Integer.parseInt(aNode.getLabel());
		else {
			for (Node c : aNode.getChildren()) {
				if (data.getAttributesClassByNumber(aNode.getAttributeID()).equals(c.getBranch())) {
					return classify(c, data);
				}
			}
		}
		throw new RuntimeException("Cannot classify");
	}

	/**
	 * Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * double total; for (Entry<Integer, Pair<String, ArrayList<String>>>
		 * entry : DataManager.possibleAttributeValues(tree.getType())
		 * .entrySet()) { total = 0; for (String value :
		 * entry.getValue().getValue()) { double probability =
		 * DataManager.getClassProbability(set, entry.getKey(), value); total +=
		 * probability; } System.out.println(entry.getKey() + ": " +
		 * entry.getValue().getKey() + ":\t" + total); } System.out.println("");
		 * 
		 * total = 0; for (Entry<Integer, Pair<String, ArrayList<String>>> entry
		 * : DataManager.possibleAttributeValues(tree.getType()) .entrySet()) {
		 * double entropy = tree.entropy(set, entry.getKey()); total += entropy;
		 * System.out.println(entry.getKey() + ": " + entry.getValue().getKey()
		 * + ":\t" + entropy); } System.out.println(total + "\n");
		 * 
		 * total = 0; for (Entry<Integer, Pair<String, ArrayList<String>>> entry
		 * : DataManager.possibleAttributeValues(tree.getType()) .entrySet()) {
		 * double gain = tree.gain(set, 4, entry.getKey()); total += gain;
		 * System.out.println(entry.getKey() + ": " + entry.getValue().getKey()
		 * + ":\t" + gain); } System.out.println(total + "\n");
		 * 
		 * set = DataManager.getSubSet(set, 0, "overst"); total = 0; for
		 * (Entry<Integer, Pair<String, ArrayList<String>>> entry :
		 * DataManager.possibleAttributeValues(tree.getType()) .entrySet()) { if
		 * (entry.getKey() == 0) continue; double gain = tree.gain(set, 4,
		 * entry.getKey()); total += gain; System.out.println(entry.getKey() +
		 * ": " + entry.getValue().getKey() + ":\t" + gain); }
		 * System.out.println(total + "\n");
		 */
	}
}
