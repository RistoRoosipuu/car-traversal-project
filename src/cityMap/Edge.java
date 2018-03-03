package cityMap;

import streets.Street;

public class Edge {
    private static final int DEFAULT_WEIGHT = 1;

    Vertex v1, v2;
    int weight;
    Street street;

    public Edge(Vertex v1, Vertex v2) {
        this(v1, v2, DEFAULT_WEIGHT);
    }

    public Edge(Vertex v1, Vertex v2, int weight) {
        super();
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Edge(Vertex entryPointOne, Vertex entryPointTwo, Street connectingStreet) {
        this.v1 = entryPointOne;
        this.v2 = entryPointTwo;
        this.weight = DEFAULT_WEIGHT;
        this.street = connectingStreet;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Edge)) return false;

        Edge _obj = (Edge) obj;
        return _obj.v1.equals(v1) && _obj.v2.equals(v2) &&
                _obj.weight == weight;
    }

    @Override
    public int hashCode() {
        int result = v1.hashCode();
        result = 31 * result + v2.hashCode();
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                ", street=" + street +
                '}';
    }
}
