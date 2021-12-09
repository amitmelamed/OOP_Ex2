
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeData_Test {
    int source = 1;
    int destination = 2;
    double weight = 23.24;
    int ID = 0;
    public EdgeData_ newEdge = new EdgeData_(source,destination,weight,ID);
    public double weight1 = 23.23;
    public double weight2 = 2.02;
    public double weight3 = 1.43;


    @Test
    void setWeight() {
        double s = newEdge.getWeight();
        assertEquals(23.24,newEdge.getWeight());
        newEdge.setWeight(weight1);
        assertEquals(weight1,newEdge.getWeight());
    }

    @Test
    void getId() {
        assertEquals(0,newEdge.getTag());
    }

    @Test
    void getSrc() {
        assertEquals(1,newEdge.getSrc());
    }

    @Test
    void getDest() {
        assertEquals(2,newEdge.getDest());
    }

    @Test
    void getWeight() {
        assertEquals(23.24,newEdge.getWeight());
        newEdge.setWeight(weight2);
        assertEquals(weight2,newEdge.getWeight());
    }

    @Test
    void getTag() {
        assertEquals(0,newEdge.getTag());
    }

    @Test
    void setTag() {
        assertEquals(0,newEdge.getTag());
        newEdge.setTag(2);
        assertEquals(2,newEdge.getTag());

    }
}