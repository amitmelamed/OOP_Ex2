import  api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

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

        /***     JFrame frame = new JFrame("Points");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.add(points);
         frame.setSize(250, 200);
         frame.setLocationRelativeTo(null);
         frame.setVisible(true); ***/

        libs l = new libs();
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
        t.getEdge(16, 15).setInfo("BLA BLA");

        Iterator<EdgeData> g = t.edgeIter();
        while(g.hasNext()){
            System.out.println(g.next());
        }

        t.removeNode(16);
        Iterator<EdgeData> s = t.edgeIter();
        while(s.hasNext()){
            System.out.println(s.next());
        }

        runGUI("data/G1.json");


    }
}

