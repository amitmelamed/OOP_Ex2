import  api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.util.Iterator;

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
        DirectedWeightedGraph ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
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
        //DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);                           //this is the correct line
        DirectedWeightedGraph_ GUIgraph = new DirectedWeightedGraph_(json_file);   //this line is not correct


        JFrame screen = new JFrame("ze waze");
        screen.setSize(600,500);
        Iterator I = GUIgraph.edgeIter();
        I.forEachRemaining(System.out::println);

        I = GUIgraph.edgeIter();
        I.forEachRemaining(System.out::println);

        GUI graph = new GUI(GUIgraph);
        screen.add(graph);



        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);

        // ****** Add your code here ******
        //
        // ********************************
    }

    public static void main(String[] args) {
        DirectedWeightedGraph_ t = new DirectedWeightedGraph_("data/G1.json");

        runGUI("data/G4ARIEL.json");


    }
}


