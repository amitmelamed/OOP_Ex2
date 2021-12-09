import api.EdgeData;

/**
 * this class will represent an edge in a (directional or not) weighted graph.
 * each edge will have source,destination,weight, info and tag(for implement algorithms use).
 */
public class EdgeData_ implements EdgeData {

    private int source;
    private int destination;
    private double weight;
    private String info;
    private int tag;
    private int id;

    /**
     * Constructor for deep copy.
     * will set tag to ZERO.
     * @param E
     */
    public EdgeData_(EdgeData E) {
        this.source = E.getSrc();
        this.destination = E.getDest();
        this.weight = E.getWeight();
        this.info = E.getInfo();
        this.tag = 0;
        this.id = E.getId();
    }



    /**
     * Constructor for edge.
     * will get source, destination, weight and ID.
     * the constractor will also set info to " ", and tag to default starting point.
     * @param source
     * @param destination
     * @param weight
     * @param ID
     */
    public EdgeData_(int source, int destination, double weight, int ID) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.id = ID;
        this.info = " ";
        this.tag = 0;
    }

    /**
     * getters and setters to the Edge class.
     * @param weight
     */
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

    /**
     * return a String with the data info of the current Edge.
     * @return
     */
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