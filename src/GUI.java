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

            double arrowX=(destx-srcx)*0.8+srcx;
            double arrowY=(desty-srcy)*0.8+srcy;

            Image icon = null;
            try {
                icon = ImageIO.read(new File("data/arrow_icon.jpg"));

            } catch (IOException e) {
                e.printStackTrace();
            }


            Line2D lineA=new Line2D.Double(srcx,srcy,destx,desty);
            Line2D lineB=new Line2D.Double(destx,srcy,destx,srcy);


            double tanAngle=angleBetween2Lines(lineA,lineB);
            BufferedImage bufferedIcon=toBufferedImage(icon);
            bufferedIcon=rotate(bufferedIcon, Math.toDegrees(tanAngle));
            g.drawImage(bufferedIcon,(int)arrowX-5,(int)arrowY-5,25,25,null);





        }


    } //Called from paintComponent

    public static double angleBetween2Lines(Line2D line1, Line2D line2)
    {
        double angle1 = Math.atan2(line1.getY1() - line1.getY2(),
                line1.getX1() - line1.getX2());
        double angle2 = Math.atan2(line2.getY1() - line2.getY2(),
                line2.getX1() - line2.getX2());
        return angle1-angle2;
    }
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
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
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

