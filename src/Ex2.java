import  api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.w3c.dom.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = new DirectedWeightedGraph_(json_file);
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        //DirectedWeightedGraph_ g = new DirectedWeightedGraph_(json_file);
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithms_(json_file);
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }


    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        //DirectedWeightedGraph_ alg = new DirectedWeightedGraph_(json_file);
        //DirectedWeightedGraph alg = getGrapg(json_file);
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file); //if we use the extends way it will work. (this is boaz's line)

        JFrame screen = new JFrame("ze waze");
        screen.setSize(600,500);

        GUI graph = new GUI(alg);
        screen.add(graph);

        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);

    }
//    public static void runGUI(DirectedWeightedGraph alg) {
//
//        JFrame screen = new JFrame("ze waze");
//        screen.setSize(600,505);
//
//        GUI graph = new GUI(alg);
//        screen.add(graph);
//
//        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        screen.setLocationRelativeTo(null);
//        screen.setVisible(true);
//
//    }

    public static void runGUI(DirectedWeightedGraphAlgorithms alg) {

        JFrame screen = new JFrame("ze waze");
        screen.setSize(600,500);

        GUI graph = new GUI(alg);
        screen.add(graph);

        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);

    }



    public static void main(String[] args) {
        DirectedWeightedGraphAlgorithms t = new DirectedWeightedGraphAlgorithms_("data/G1.json");
        //t.load("data/G1.json");

        Iterator<NodeData> I = t.getGraph().nodeIter();
        I.next();
        I.remove();
        I.remove();
        I.remove();
        runGUI(t);




        /**DONT DELETE THE FOLLOWING CODE THIS IS A REAL GOOD TEST**/
//        List<NodeData> l = t.shortestPath(1,2);
//        int counte = 0;
//        for (int i = 0; i < t.getGraph().nodeSize(); i++) {
//            for (int k = 0; k < t.getGraph().nodeSize(); k++) {
//                l = t.shortestPath(i, k);
//                double sumw = 0;
//                if (l != null) {
//                    for (int j = 0; j < l.size() - 1; j++) {
//                        //System.out.println(t.getGraph().getEdge(l.get(j).getKey(), l.get(j+1).getKey()));
//                        sumw += t.getGraph().getEdge(l.get(j).getKey(), l.get(j + 1).getKey()).getWeight();
//                    }
//                    if (sumw != t.shortestPathDist(i, k)) {
//                        System.out.println(i + ",  " + k + ":");
//                        System.out.println("sumw is " + sumw);
//                        System.out.println("func is " + t.shortestPathDist(i, k));
//                        System.out.println(l.toString());
//                        System.out.println();
//                        counte++;
//                    }
//                }
//            }
//        }
//        System.err.println(counte+" wrong calculations out of "+t.getGraph().nodeSize()*t.getGraph().nodeSize());



    }
}


