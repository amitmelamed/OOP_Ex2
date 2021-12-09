import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


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

    public static void loadPerformence() {
        DirectedWeightedGraphAlgorithms t = new DirectedWeightedGraphAlgorithms_("data/G1.json");
        ArrayList<Integer> Ks = new ArrayList<>();
        Ks.add(1);
        Ks.add(10);
        Ks.add(100);
        Ks.add(1000);
        for (int i : Ks) {
            double start = System.currentTimeMillis();
            t.init(lib.createK(i));
            double total = System.currentTimeMillis()-start;
            System.out.println("Loading of "+i+" K graph: " +total + "MS");
        }
    }

    public static void centerPerformence() {
        DirectedWeightedGraphAlgorithms t = new DirectedWeightedGraphAlgorithms_("data/G1.json");
        //ArrayList<Integer> Ks = new ArrayList<>();
        t.init(lib.createK(10));

        double start = System.currentTimeMillis();

        int c = t.center().getKey();
        double total = System.currentTimeMillis()-start;
        System.out.println("Found "+c+" center at "+total+"MS");

    }

    public static void isConnectedPerformence() {
        DirectedWeightedGraphAlgorithms t = new DirectedWeightedGraphAlgorithms_("data/G1.json");
        //ArrayList<Integer> Ks = new ArrayList<>();
        t.init(lib.createK(100));

        double start = System.currentTimeMillis();

        boolean c = t.isConnected();
        double total = System.currentTimeMillis()-start;
        System.out.println("Found "+c+" center at "+total+"MS");

    }

    public static DirectedWeightedGraph createK(int K) {
        DirectedWeightedGraph G = new DirectedWeightedGraph_();
        int nodesize = 1000*K;
        int edgesize = nodesize*10;
        for (int i = 0; i < nodesize; i++) {
            int x = (int)(Math.random()*nodesize);
            int y = (int)(Math.random()*nodesize);
            NodeData newNode = new NodeData_(i, ""+x+","+y+",0");
            G.addNode(newNode);
        }
        System.out.println("CREATED "+G.nodeSize()+" NODES");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < nodesize; j++) {
                int src = j;
                int dest = (int)(Math.random()*nodesize);
                while (dest==src) dest = (int)(Math.random()*nodesize);

                G.connect(src,dest,1);
            }

        }
        System.out.println("CREATED "+G.edgeSize()+" EDGES");
        return G;
    }


}


