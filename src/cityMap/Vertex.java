package cityMap;

import streets.StreetCrossroad;

public class Vertex {

    private int uniqueLabel;
    private StreetCrossroad entryPoint;

    public Vertex(int uniqueLabel) {
        super();
        this.uniqueLabel = uniqueLabel;
    }

    public Vertex(int uniqueLabel, StreetCrossroad entryPoint) {
        super();
        this.uniqueLabel = uniqueLabel;
        this.entryPoint = entryPoint;

    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vertex)) return false;

        Vertex _obj = (Vertex) obj;
        return _obj.uniqueLabel == uniqueLabel;
    }

    @Override
    public int hashCode() {
        return uniqueLabel;
    }

    public int getLabel() {
        return uniqueLabel;
    }

    public void setLabel(int uniqueLabel) {
        this.uniqueLabel = uniqueLabel;
    }

    @Override
    public String toString() {
        return "Crossroad name: " + entryPoint;
    }

    public StreetCrossroad getEntryPoint() {
        return entryPoint;
    }
}
