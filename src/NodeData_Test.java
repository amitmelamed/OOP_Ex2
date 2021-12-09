import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeData_Test {
    public int key = 0;
    public String pos = "236,427,0.0";
    public NodeData_ n = new NodeData_(key,pos);
    public EdgeData_ newEdge = new EdgeData_(0,2,2,0);
    public EdgeData_ newEdge1 = new EdgeData_(1,4,1,1);
    public EdgeData_ newEdge2 = new EdgeData_(2,3,9,2);
    public EdgeData_ newEdge3 = new EdgeData_(3,5,6,3);
    public EdgeData_ newEdge4 = new EdgeData_(4,5,6,4);
    public EdgeData_ newEdge5 = new EdgeData_(5,0,5,5);



    @Test
    void getOutEdges() {
        n.getOutEdges().put(0,newEdge);
        n.getOutEdges().put(1,newEdge1);
        n.getOutEdges().put(2,newEdge2);
        n.getOutEdges().put(3,newEdge3);
        n.getOutEdges().put(4,newEdge4);
        n.getOutEdges().put(5,newEdge5);
        assertEquals(n.getOutEdges().get(0),newEdge);
        assertEquals(n.getOutEdges().get(1),newEdge1);
        assertEquals(n.getOutEdges().get(2),newEdge2);
        assertEquals(n.getOutEdges().get(3),newEdge3);
        assertEquals(n.getOutEdges().get(4),newEdge4);
        assertEquals(n.getOutEdges().get(5),newEdge5);
    }

    @Test
    void getInEdges() {
        n.getInEdges().put(0,newEdge);
        n.getInEdges().put(1,newEdge1);
        n.getInEdges().put(2,newEdge2);
        n.getInEdges().put(3,newEdge3);
        n.getInEdges().put(4,newEdge4);
        n.getInEdges().put(5,newEdge5);
        assertEquals(n.getInEdges().get(0),newEdge);
        assertEquals(n.getInEdges().get(1),newEdge1);
        assertEquals(n.getInEdges().get(2),newEdge2);
        assertEquals(n.getInEdges().get(3),newEdge3);
        assertEquals(n.getInEdges().get(4),newEdge4);
        assertEquals(n.getInEdges().get(5),newEdge5);
    }

    @Test
    void getLocation() {
        double x = 236;
        double y = 427;
        double z = 0;
        GeoLocation_ G = new GeoLocation_(x,y,z);
        assertEquals(G.x(),n.getLocation().x());
        assertEquals(G.y(),n.getLocation().y());
        assertEquals(G.z(),n.getLocation().z());

    }

    @Test
    void setLocation() {
        double x = 111;
        double y = 222;
        double z = 0;
        GeoLocation_ G = new GeoLocation_(x,y,z);
        n.setLocation(G);
        assertEquals(G,n.getLocation());
    }

    @Test
    void setWeight() {
        assertEquals(0,n.getWeight());
        double w = 1;
        n.setWeight(w);
        assertEquals(w,n.getWeight());
    }
}