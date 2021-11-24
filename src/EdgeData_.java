import api.EdgeData;

/**
 * this class will represent an edge in a (directional or not) weighted graph.
 * each edge will have source,destination,weight, info and tag(for implement algorithms use
 */
public class EdgeData_ implements EdgeData {
    private int source;
    private int destination;
    private double weight;
    private String info;
    private int tag;

    private static int ID = 0;
    private int id;

    public EdgeData_(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.id = ID;
        ID++;
    }
    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int getId() {return id;}
    @Override
    public int getSrc() {
        return source;
    }

    @Override
    public int getDest() {
        return destination;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }

    @Override
    public String toString() {
        return "EdgeData_{" +
                "source=" + source +
                ", destination=" + destination +
                ", weight=" + weight +
                ", info='" + info + '\'' +
                ", tag=" + tag +
                "ID = "+id;
    }
}