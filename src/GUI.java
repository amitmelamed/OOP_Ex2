import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

public class GUI extends JPanel {
    private DirectedWeightedGraph GUIgraph;
    private int minXid, maxXid, minYid, maxYid;
    private double maxX = Integer.MIN_VALUE;
    private double minX = Integer.MAX_VALUE;
    private double maxY = Integer.MIN_VALUE;
    private double minY = Integer.MAX_VALUE;
    private double absX = 0;
    private double absY = 0;

    public GUI(DirectedWeightedGraph graph) {
        this.GUIgraph = graph;
        arrfloose();
    }

    public void arrfloose() {
        int ans=0;

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

    }

    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        double tX = getWidth()/absX*0.8;
        double tY = getHeight()/absY*0.8;
        Toolkit t=Toolkit.getDefaultToolkit();
        Image map=t.getImage("data/map.png");
        Image node=t.getImage("data/node-js.png");
        //g.drawImage(map,10,10,getWidth(), getHeight(), null); //loads ariel's map to the background, and sticks it to the size of the screen
        Iterator<NodeData> NodeI = GUIgraph.nodeIter();
        g.setColor(Color.red);


        int resize = 50;
        while(NodeI.hasNext()) {
            NodeData currNode = NodeI.next();
            double Geox = currNode.getLocation().x();
            double Geoy = currNode.getLocation().y();

            double x = (Geox-minX)*tX+resize;
            double y = (Geoy-minY)*tY+resize;
            //g.drawImage(node, (int)x-5, (int)y-5,10,10, null);
            g.fillOval((int)x-5, (int)y-5, 10, 10);
        }

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


    }
}

