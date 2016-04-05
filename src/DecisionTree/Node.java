/**
 * @(#)Node.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Used to represent a node in the search
 */

package DecisionTree;

import java.util.ArrayList;

public class Node {
	private ArrayList<Node>	children;			// the children of this node, can be null if root
	private int				attributeID;		// attribute ID
	private boolean			leafNode;			// is it a lead node
	private String			label;				// node label 
	private	String 			branch;				// branch to get here
	
	public ArrayList<Node> 	getChildren()				{ return children; }
	public int 				getAttributeID()			{ return attributeID; }
	public boolean			isLeafNode()				{ return leafNode; }
	public String 			getLabel() 					{ return label; }
	public String			getBranch()					{ return branch; }

	public void setChildren(ArrayList<Node> children)	{ this.children = children; }
	public void setAttributeID(int attributeID) 		{ this.attributeID = attributeID; }
	public void setLeafNode(boolean leafNode)			{ this.leafNode = leafNode; }
	public void setLabel(String label)					{ this.label = label; }
	public void setBranch(String branch)				{ this.branch = branch; }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attributeID;
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + (leafNode ? 1231 : 1237);
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
		Node other = (Node) obj;
		if (attributeID != other.attributeID)
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (leafNode != other.leafNode)
			return false;
		return true;
	}	
}
