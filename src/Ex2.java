import  api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

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
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgoritems_(json_file);
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
        screen.setSize(600,505);

        GUI graph = new GUI(alg);
        screen.add(graph);

        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);

    }
    public static void runGUI(DirectedWeightedGraph alg) {

        JFrame screen = new JFrame("ze waze");
        screen.setSize(600,505);

        GUI graph = new GUI(alg);
        screen.add(graph);

        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);

    }

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
        String jsonfile = "data/G5.json";
        DirectedWeightedGraphAlgoritems_ t = new DirectedWeightedGraphAlgoritems_(jsonfile);

        t.printPathData();
        System.out.println(t.isConnected());
        runGUI(t);



    }
}


