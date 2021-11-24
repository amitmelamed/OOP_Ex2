import  api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import org.json.JSONException;

import javax.swing.*;
import java.io.IOException;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
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
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) throws IOException, JSONException {              //delete the throw
        //DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);                           //this is the correct line
       // DirectedWeightedGraph_ GUIgraph = new DirectedWeightedGraph_("data/G1.json");   //this line is not correct
        

        GUIgraph l = new GUIgraph();
        JFrame f = new JFrame("ze waze");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(l);
        f.setSize(300,250);

        f.setLocationRelativeTo(null);
        f.setVisible(true);

        // ****** Add your code here ******
        //
        // ********************************
    }

    public static void main(String[] args) throws IOException, JSONException {
        DirectedWeightedGraph_ t = new DirectedWeightedGraph_("data/G1.json");
        GUIgraph f = new GUIgraph();

        runGUI("data/G1.json");


    }
}

