package DecisionTree;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class Graph extends JFrame {
	private mxGraph graph;
	private mxGraphComponent graphComponent;
	private Object parent;

	public Graph(DecisionTree tree) {
		// Set JFrameTitle to the Data's name
		super(tree.getType().getDisplayName());

		graph = new mxGraph();
		parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {
			Object rootVertex = graph.insertVertex(parent, null, "Blah ", 50, 50, 200, 100);
			insertGraph(tree.buildTree(), rootVertex, graph);
		} finally {
			graph.getModel().endUpdate();
		}

		graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent, BorderLayout.CENTER);

		layoutGraph(graph);
	}

	private void insertGraph(Node node, Object nodeVertex, mxGraph graph) {
		if (node.getChildren().isEmpty()) {
			((mxCell) nodeVertex).setValue("Pick Class " + node.getLabel());
			((mxCell) nodeVertex).setStyle("fillColor=lightgreen;" + "shape=ellipse");
		} else {
			((mxCell) nodeVertex).setValue(node.getLabel());
			for (Node c : node.getChildren()) {
				Object childVertex = graph.insertVertex(parent, null, "Blahh ", 50, 50, 200, 100);
				graph.insertEdge(parent, null, c.getBranch(), nodeVertex, childVertex);
				insertGraph(c, childVertex, graph);
			}
		}
	}

	public void layoutGraph(mxGraph graph) {
		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
		layout.setOrientation(SwingConstants.NORTH);
		layout.execute(graph.getDefaultParent());
	}
}
