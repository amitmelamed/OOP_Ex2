import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphAlgorithms_Test {

    DirectedWeightedGraphAlgorithms_ graphAlgorithmsG0=new DirectedWeightedGraphAlgorithms_("data/G0.json");
    DirectedWeightedGraphAlgorithms_ graphAlgorithmsG1=new DirectedWeightedGraphAlgorithms_("data/G1.json");
    DirectedWeightedGraphAlgorithms_ graphAlgorithmsG2=new DirectedWeightedGraphAlgorithms_("data/G2.json");
    DirectedWeightedGraphAlgorithms_ graphAlgorithmsG3=new DirectedWeightedGraphAlgorithms_("data/G3.json");





    @Test
    void getTransposeGraph() {
        Iterator<NodeData> NodesI = graphAlgorithmsG1.getGraph().nodeIter();
        Iterator<NodeData> NodesT = graphAlgorithmsG1.getTransposeGraph().nodeIter();
        NodeData n;
        NodeData nT;
        while (NodesI.hasNext()) {
            n = NodesI.next();
            nT = NodesT.next();
            Iterator<EdgeData> Edges = graphAlgorithmsG1.getGraph().edgeIter();
            Iterator<EdgeData> EdgesT = graphAlgorithmsG1.getTransposeGraph().edgeIter();
            while (Edges.hasNext()) {
                EdgeData e = Edges.next();
                EdgeData eT = EdgesT.next();
                assertEquals(eT.getSrc(),graphAlgorithmsG1.getGraph().getEdge(e.getSrc(),e.getDest()).getDest());
                assertEquals(eT.getDest(),graphAlgorithmsG1.getGraph().getEdge(e.getSrc(),e.getDest()).getSrc());
                assertEquals(eT.getWeight(),graphAlgorithmsG1.getGraph().getEdge(e.getSrc(),e.getDest()).getWeight());
                assertEquals(eT.getId(),graphAlgorithmsG1.getGraph().getEdge(e.getSrc(),e.getDest()).getId());
            }
        }
    }


    @Test
    void getGraph() {
    }

    @Test
    void isConnected() {
        assertEquals(true,graphAlgorithmsG1.isConnected());
        assertEquals(true,graphAlgorithmsG2.isConnected());
        assertEquals(true,graphAlgorithmsG3.isConnected());
        assertTrue(graphAlgorithmsG0.isConnected());
        assertTrue(graphAlgorithmsG1.isConnected());
        assertTrue(graphAlgorithmsG2.isConnected());
        assertTrue(graphAlgorithmsG3.isConnected());
        graphAlgorithmsG1.getGraph().removeNode(8);
        graphAlgorithmsG1.getGraph().removeNode(15);
        assertFalse(graphAlgorithmsG1.isConnected());

    }

    @Test
    void shortestPathDist() {
        assertEquals(10.104946498752744,graphAlgorithmsG1.shortestPathDist(0,10));
        assertEquals(5.702973325568125,graphAlgorithmsG2.shortestPathDist(0,10));
        assertEquals(1.93165589266364,graphAlgorithmsG3.shortestPathDist(0,10));


    }

    @Test
    void shortestPath() {
        List<NodeData> shortestPathG1= graphAlgorithmsG1.shortestPath(0,10);
        List<NodeData> shortestPathG2= graphAlgorithmsG2.shortestPath(0,10);
        List<NodeData> shortestPathG3= graphAlgorithmsG3.shortestPath(0,10);

        int [] G1Index=new int[shortestPathG1.size()];
        int [] G2Index=new int[shortestPathG2.size()];
        int [] G3Index=new int[shortestPathG3.size()];
        int [] G1IndexExpected={0,16,15,14,13,12,11,10};
        int [] G2IndexExpected={0,21,22,23,9,10};
        int [] G3IndexExpected={0,1,10};

        for(int i=0;i<G1Index.length;i++){
            G1Index[i]=shortestPathG1.get(i).getKey();
        }
        for(int i=0;i<G2Index.length;i++){

            G2Index[i]=shortestPathG2.get(i).getKey();

        }
        for(int i=0;i<G3Index.length;i++){
            G3Index[i]=shortestPathG3.get(i).getKey();
        }
        assertArrayEquals(G1Index,G1IndexExpected);
        assertArrayEquals(G2Index,G2IndexExpected);
        assertArrayEquals(G3Index,G3IndexExpected);
    }

    @Test
    void center() {
        assertEquals(8,graphAlgorithmsG1.center().getKey());
        assertEquals(0,graphAlgorithmsG2.center().getKey());
        assertEquals(40,graphAlgorithmsG3.center().getKey());
    }



    @Test
    void tsp() {
        List < NodeData> tspCities=new ArrayList<>();
        tspCities.add(graphAlgorithmsG1.getGraph().getNode(0));
        tspCities.add(graphAlgorithmsG1.getGraph().getNode(10));
        List <NodeData> tspGraph1=graphAlgorithmsG1.tsp(tspCities);
        int [] tspArray=new int[tspGraph1.size()];
        for(int i=0;i<tspArray.length;i++){
            tspArray[i]=tspGraph1.get(i).getKey();
        }
        int [] expectedArray={0,16,15,14,13,12,11,10,11,12,13,14,15,16,0};
        assertArrayEquals(tspArray,expectedArray);
    }

    @Test
    void save() {
        assertEquals(true,graphAlgorithmsG1.save("src/tests/testG1"));
        assertEquals(true,graphAlgorithmsG2.save("src/tests/testG2"));
        assertEquals(true,graphAlgorithmsG3.save("src/tests/testG3"));

    }

    @Test
    void load() {
        assertEquals(true,graphAlgorithmsG1.load("data/G1.json"));
        assertEquals(true,graphAlgorithmsG2.load("data/G2.json"));
        assertEquals(true,graphAlgorithmsG3.load("data/G3.json"));
        assertEquals(false,graphAlgorithmsG3.load("wrong_json_location"));
    }
}