import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The GUI class builds The Graphical User Interface of the current Graph.
 * The class gets an input of a Directed Weighted Graph Algorithm and print
 * the graph that is loaded on the algorithm the user interface.
 * We used JPanel interface to display the current graph.
 */
public class GUI extends JPanel {

    private DirectedWeightedGraphAlgorithms_ GUIgraph;
    private DirectedWeightedGraph transposedGUIgraph;
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
    private int center = -1;

    private JButton removeButton = new JButton();
    private JButton showEdgesButton = new JButton();
    private JButton centerButton = new JButton();
    private JButton loadButton = new JButton();
    private ArrayList<String> nodes = new ArrayList<>();;
    private SpinnerListModel nodesModel;
    private JSpinner idSpinner;


//    public GUI(DirectedWeightedGraph graph) {
//        this.GUIgraph = graph;
//        updateMinMax();
//        nodesListModel();
//        algoFlag = false;
//    }

    /**
     * The constructor gets an input of a Graph Algorithm.
     * @param graph
     */
    public GUI(DirectedWeightedGraphAlgorithms graph) {
        this.GUIgraph = (DirectedWeightedGraphAlgorithms_) graph;
        updateMinMax();
        nodesListModel();
        algoFlag = true;
        transposedGUIgraph = GUIgraph.getTransposeGraph();
    }

    /** FOLLOWING METHODS ARE CALLED FROM THE CONSTRUCTOR**/
    private void nodesListModel() {
        if (GUIgraph.getGraph().nodeSize()>100) {
            Iterator<NodeData> NodeI = GUIgraph.getGraph().nodeIter();
            while (NodeI.hasNext()) {
                NodeData currNode = NodeI.next();
                nodes.add(Integer.toString(currNode.getKey()));
            }
            nodesModel = new SpinnerListModel(nodes);
            idSpinner = new JSpinner(nodesModel);
        }
        else {
            for (int i = 1; i < 101; i++) {
                nodes.add(Integer.toString(i));
            }
            nodesModel = new SpinnerListModel(nodes);
            idSpinner = new JSpinner(nodesModel);
        }
    } //Called from the constructor

    public void updateMinMax() {

        Iterator<NodeData> NodeI = GUIgraph.getGraph().nodeIter();

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
    /**
     * this method creates buttons that we use in the GUI.
     * the buttons that we create are: Remove, Edge Toggle, Show Center and load new Graph.
     */
    private void createButtons() {
        removeButton.setLocation(0,0);
        removeButton.setSize(90,30);
        removeButton.setText("Remove");
        this.add(removeButton);
        idSpinner.setLocation(90,5);
        this.add(idSpinner);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int target = Integer.parseInt(idSpinner.getValue().toString());
                GUIgraph.getGraph().removeNode(target);
                GUIgraph.setPathCalculated(false);
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

            centerButton.setLocation(0,60);
            centerButton.setSize(119,30);
            centerButton.setText("Show Center");

            this.add(centerButton);
            centerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (GUIgraph.isConnected()) center = GUIgraph.center().getKey();
                    else center = -1;
                }
            });

            loadButton.setLocation(119,0);
            loadButton.setSize(90,30);
            loadButton.setText("Load");
            this.add(loadButton);
            loadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int target = Integer.parseInt(idSpinner.getValue().toString());
                    GUIgraph.load("data/G"+target+".json");
                    maxX = Integer.MIN_VALUE;
                    minX = Integer.MAX_VALUE;
                    maxY = Integer.MIN_VALUE;
                    minY = Integer.MAX_VALUE;
                    absX = 0;
                    absY = 0;
                    updateMinMax();
                    //nodesListModel();

                }
            });
        }

    } //Called from paintComponent

    /**
     * The following function calculate where each node is located in the screen by
     * the X,Y coordinates of the Geographic location of the node.
     * @param g
     */
    private void paintNodes(Graphics g) {
        double tX = getWidth()/absX*0.8;
        double tY = getHeight()/absY*0.8;
        Iterator<NodeData> NodeI = GUIgraph.getGraph().nodeIter();
        g.setColor(Color.red);
        while(NodeI.hasNext()) {
            NodeData currNode = NodeI.next();
            double Geox = currNode.getLocation().x();
            double Geoy = currNode.getLocation().y();

            double x = (Geox-minX)*tX+resize;
            double y = (Geoy-minY)*tY+resize;
            if (currNode.getKey()==center) g.setColor(Color.blue);
            g.fillOval((int)x-5, (int)y-5, 20, 20);
            g.setColor(Color.red);
            g.drawString(currNode.getKey()+"", (int)x-5, (int)y-5);
        }
    } //Called from paintComponent

    /**
     * The following function calculate where each Edge is located in the screen by
     *      * the X,Y coordinates of the Geographic location of the source node and destination node.
     * @param g
     */
    private void paintEdges(Graphics g) {
        double tX = getWidth()/absX*0.8;
        double tY = getHeight()/absY*0.8;
        Iterator<EdgeData> EdgeI = GUIgraph.getGraph().edgeIter();
        g.setColor(Color.black);
        while(EdgeI.hasNext()) {
            EdgeData currEdge = EdgeI.next();

            double srcGeox = GUIgraph.getGraph().getNode(currEdge.getSrc()).getLocation().x();
            double srcGeoy = GUIgraph.getGraph().getNode(currEdge.getSrc()).getLocation().y();
            double destGeox = GUIgraph.getGraph().getNode(currEdge.getDest()).getLocation().x();
            double destGeoy = GUIgraph.getGraph().getNode(currEdge.getDest()).getLocation().y();

            double srcx = (srcGeox-minX)*tX+resize;
            double srcy = (srcGeoy-minY)*tY+resize;
            double destx = (destGeox-minX)*tX+resize;
            double desty = (destGeoy-minY)*tY+resize;

            g.drawLine((int)srcx,(int)srcy,(int)destx,(int)desty);
          //  if (srcx>destx) g.drawString("R:"+currEdge.getWeight(), (int)((destx+srcx)/2),(int)((desty+srcy)/2));
           // else g.drawString("L:"+currEdge.getWeight(), (int)(((destx+srcx)/2)),(int)((desty+srcy)/2)-25);
            paintArrows(destx, srcx, desty, srcy, g);

        }


    } //Called from paintComponent

    /**
     * the following function draws an image of an arrow that represent the direction of an edge.
     * the function gets X and Y of the destination and source nodes.
     * then calculates the rotation angle that we need to rotate the arrow image.
     * @param destx
     * @param srcx
     * @param desty
     * @param srcy
     * @param g
     */
    public void paintArrows(double destx, double srcx, double desty, double srcy, Graphics g) {
        double arrowX=(destx-srcx)*0.8+srcx;
        double arrowY=(desty-srcy)*0.8+srcy;

        Image icon = null;
        try {
            icon = ImageIO.read(new File("data/arrow-left-icon-17.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }


        Line2D lineA=new Line2D.Double(srcx,srcy,destx,desty);
        Line2D lineB=new Line2D.Double(destx,srcy,destx,srcy);


        double tanAngle=angleBetween2Lines(lineA,lineB);
        BufferedImage bufferedIcon=toBufferedImage(icon);

        bufferedIcon=rotate(bufferedIcon, Math.toDegrees(tanAngle));
        g.drawImage(bufferedIcon,(int)arrowX-5,(int)arrowY-5,(int)(0.0159*getWidth()),(int)(0.0159*getHeight()),null);
    }

    /**
     * the function calculate the angle beetween 2 lines.
     * the calculate returns how much we need to rotate by tangents.
     * @param line1
     * @param line2
     * @return
     */
    public static double angleBetween2Lines(Line2D line1, Line2D line2)
    {
        double angle1 = Math.atan2(line1.getY1() - line1.getY2(),
                line1.getX1() - line1.getX2());
        double angle2 = Math.atan2(line2.getY1() - line2.getY2(),
                line2.getX1() - line2.getX2());
        return angle1-angle2;
    }

    /**
     * we need to convert the arrow Image to buffered image, to be able to use buffered Image rotation function.
     * so we use this function to convert regular Image to Buffered Image
     * @param img
     * @return
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null); // the fuck is this doing
        bGr.dispose();
        // Return the buffered image
        return bimage;
    }

    /**
     * this function is getting Buffered Image and rotate it with the angle needed.
     * @param bimg
     * @param angle
     * @return
     */
    public static BufferedImage rotate(BufferedImage bimg, Double angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        int neww = (int) Math.floor(w*cos + h*sin),
                newh = (int) Math.floor(h*cos + w*sin);
        BufferedImage rotated = new BufferedImage(neww, newh, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww-w)/2, (newh-h)/2);
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawRenderedImage(bimg, null);
        graphic.dispose();
        return rotated;
    }

    /**
     * function to create background.
     * not used currently in the project.
     * @param g
     */
    private void paintBackground(Graphics g) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image map=t.getImage("data/map.png");
        g.drawImage(map,10,10,getWidth(), getHeight(), null); //loads ariel's map to the background, and sticks it to the size of the screen
    } //Called from paintComponent

    /**
     *
     * @param g
     */
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

