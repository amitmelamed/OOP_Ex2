import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * this class contains function that we use in all the project.
 */
public class lib {

    /**
     * the function will get Json file name and will return JSON object.
     * @param filename
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
    
    public static DirectedWeightedGraph create1M() {
        DirectedWeightedGraph G1M = new DirectedWeightedGraph_();
        for (int i = 0; i < 1000000; i++) {
            int x = (int)(Math.random()*1000);
            int y = (int)(Math.random()*1000);
            NodeData newNode = new NodeData_(i, ""+x+","+y+",0");
            G1M.addNode(newNode);

        }
        System.out.println("CREATED 1M NODES");
//        for (int i = 0; i < 20000000; i++) {
//                int src = (int)(Math.random()*1000000);
//                int dest = (int)(Math.random()*1000000);
//                while (dest==src) dest = (int)(Math.random()*1000000);
//
//                G1M.connect(src,dest,1);
//        }
        return G1M;
    }


}


