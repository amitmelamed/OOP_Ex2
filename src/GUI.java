import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GUI extends JPanel {

    private DirectedWeightedGraph GUIgraph;
    private int minXid, maxXid, minYid, maxYid;
    private double maxX = Integer.MIN_VALUE;
    private double minX = Integer.MAX_VALUE;
    private double maxY = Integer.MIN_VALUE;
    private double minY = Integer.MAX_VALUE;
    private double absX = 0;
    private double absY = 0;
    private int resize = 30;
    private boolean edgeToggle = true;
    private boolean algoFlag;

    private JButton removeButton = new JButton();
    private JButton showEdgesButton = new JButton();
    private ArrayList<String> nodes = new ArrayList<>();;
    private SpinnerListModel nodesModel;
    private JSpinner removeSpinner;

    public GUI(DirectedWeightedGraph graph) {
        this.GUIgraph = graph;
        updateMinMax();
        nodesListModel();
        algoFlag = false;
    }

    public GUI(DirectedWeightedGraphAlgorithms graph) {
        this.GUIgraph = graph.getGraph();
        updateMinMax();
        nodesListModel();
        algoFlag = true;
    }

    /** FOLLOWING METHODS ARE CALLED FROM THE CONSTRUCTOR**/
    private void nodesListModel() {
        Iterator<NodeData> NodeI = GUIgraph.nodeIter();
        while(NodeI.hasNext()) {
            NodeData currNode = NodeI.next();
            nodes.add(Integer.toString(currNode.getKey()));
        }
        nodesModel = new SpinnerListModel(nodes);
        removeSpinner = new JSpinner(nodesModel);
    } //Called from the constructor

    public void updateMinMax() {

        Iterator<NodeData> NodeI = GUIgraph.nodeIter();

        while(NodeI.hasNext()) {
            NodeData currNode = NodeI.next();
            if (currNode.getLocation().x()<minX) {
                minX = currNode.getLocation().x();
                minXid = currNode.getKey();
            } else if (currNode.getLocation().x()>maxX) {
                maxX = currNode.getLocation().x();
                maxXid = currNode.getKey();
            }
            if (currNode.getLocation().y()<minY) {
                minY = currNode.getLocation().y();
                minYid = currNode.getKey();
            } else if (currNode.getLocation().y()>maxY) {
                maxY = currNode.getLocation().y();
                maxYid = currNode.getKey();
            }
        }

        absX = Math.abs(maxX-minX);
        absY = Math.abs(maxY-minY);

    } //Called from the constructor



    /** FOLLOWING METHODS ARE CALLED FROM PAINTCOMPONENT**/
    private void createButtons() {
        removeButton.setLocation(0,0);
        removeButton.setSize(90,30);
        removeButton.setText("Remove");
        this.add(removeButton);
        removeSpinner.setLocation(90,5);
        this.add(removeSpinner);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int target = Integer.parseInt(removeSpinner.getValue().toString());
                GUIgraph.removeNode(target);
            }
        });

        showEdgesButton.setLocation(0,30);
        showEdgesButton.setSize(119,30);
        showEdgesButton.setText("Edge Toggle");

        this.add(showEdgesButton);
        showEdgesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (edgeToggle) edgeToggle = false;
                else edgeToggle = true;
            }
        });

        if (algoFlag) {
            //HERE WILL HAVE BUTTONS FOR THE ALGORITHMS
        }

    } //Called from paintComponent

    private void paintNodes(Graphics g) {
        double tX = getWidth()/absX*0.8;
        double tY = getHeight()/absY*0.8;
        Iterator<NodeData> NodeI = GUIgraph.nodeIter();
        g.setColor(Color.red);
        while(NodeI.hasNext()) {
            NodeData currNode = NodeI.next();
            double Geox = currNode.getLocation().x();
            double Geoy = currNode.getLocation().y();

            double x = (Geox-minX)*tX+resize;
            double y = (Geoy-minY)*tY+resize;
            g.fillOval((int)x-5, (int)y-5, 10, 10);
            g.drawString(currNode.getKey()+"", (int)x-5, (int)y-5);
        }
    } //Called from paintComponent

    private void paintEdges(Graphics g) {
        double tX = getWidth()/absX*0.8;
        double tY = getHeight()/absY*0.8;
        Iterator<EdgeData> EdgeI = GUIgraph.edgeIter();
        g.setColor(Color.black);
        while(EdgeI.hasNext()) {
            EdgeData currEdge = EdgeI.next();

            double srcGeox = GUIgraph.getNode(currEdge.getSrc()).getLocation().x();
            double srcGeoy = GUIgraph.getNode(currEdge.getSrc()).getLocation().y();
            double destGeox = GUIgraph.getNode(currEdge.getDest()).getLocation().x();
            double destGeoy = GUIgraph.getNode(currEdge.getDest()).getLocation().y();

            double srcx = (srcGeox-minX)*tX+resize;
            double srcy = (srcGeoy-minY)*tY+resize;
            double destx = (destGeox-minX)*tX+resize;
            double desty = (destGeoy-minY)*tY+resize;

            g.drawLine((int)srcx,(int)srcy,(int)destx,(int)desty);
        }

    } //Called from paintComponent

    private void paintBackground(Graphics g) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image map=t.getImage("data/map.png");
        g.drawImage(map,10,10,getWidth(), getHeight(), null); //loads ariel's map to the background, and sticks it to the size of the screen
    } //Called from paintComponent


    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        this.setVisible(false);
        g.drawString(GUIgraph+" | ", 200, 10);
        //paintBackground(g);

        createButtons();
        paintNodes(g);
        if (edgeToggle) paintEdges(g);

        this.setVisible(true);
    }
}

