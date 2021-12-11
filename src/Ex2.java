import  api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;


import javax.swing.*;


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
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithms_(json_file);
        return ans;
    }


    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);

        JFrame screen = new JFrame("Directed Weighted Graph");
        screen.setSize(515,500);

        GUI graph = new GUI(alg);
        screen.add(graph);

        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);

    }

    public static void runGUI(DirectedWeightedGraphAlgorithms alg) {

        JFrame screen = new JFrame("Directed Weighted Graph");
        screen.setSize(515,500);

        GUI graph = new GUI(alg);
        screen.add(graph);

        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);

    }

    public static void main(String[] args) {

        /**
         * this is how to run the jar:
         * out\artifacts\OOP_Ex2_jar\OOP_Ex2.jar
         */

        runGUI(args[0]);
    }
}


