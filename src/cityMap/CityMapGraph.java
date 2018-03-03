package cityMap;

import streets.Street;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Modified Graph based on :
 * https://gist.github.com/smddzcy/bf8fc17dedf4d40b0a873fc44f855a58
 */
public class CityMapGraph {
    private Set<Vertex> vertices;
    private Set<Edge> edges;
    private Map<Vertex, Set<Edge>> adjList;
    private FillGraph graph;

    public CityMapGraph() {
        vertices = new HashSet<>();
        edges = new HashSet<>();
        adjList = new HashMap<>();
        graph = new FillGraph(this);
    }

    public boolean addVertex(int label) {
        return vertices.add(new Vertex(label));
    }

    public boolean addVertex(Vertex v) {
        return vertices.add(v);
    }

    public boolean addVertices(Collection<Vertex> vertices) {
        return this.vertices.addAll(vertices);
    }

    public boolean removeVertex(int label) {
        return vertices.remove(new Vertex(label));
    }

    public boolean removeVertex(Vertex v) {
        return vertices.remove(v);
    }

    public boolean addEdge(Edge e) {
        if (!edges.add(e)) return false;

        adjList.putIfAbsent(e.v1, new HashSet<>());
        adjList.putIfAbsent(e.v2, new HashSet<>());

        adjList.get(e.v1).add(e);
        adjList.get(e.v2).add(e);

        return true;
    }

    public boolean addEdge(Vertex firstEntryPoint, Vertex secondEntryPoint, Street street) {

        if (!vertices.contains(firstEntryPoint)) {
            vertices.add(firstEntryPoint);
        }

        if (!vertices.contains(secondEntryPoint)) {
            vertices.add(secondEntryPoint);
        }
        return addEdge(new Edge(firstEntryPoint,
                secondEntryPoint, street));
    }

    public boolean removeEdge(Edge e) {
        if (!edges.remove(e)) return false;
        Set<Edge> edgesOfV1 = adjList.get(e.v1);
        Set<Edge> edgesOfV2 = adjList.get(e.v2);

        if (edgesOfV1 != null) edgesOfV1.remove(e);
        if (edgesOfV2 != null) edgesOfV2.remove(e);

        return true;
    }

    public boolean removeEdge(int vertexLabel1, int vertexLabel2) {
        return removeEdge(new Edge(new Vertex(vertexLabel1),
                new Vertex(vertexLabel2)));
    }

    public Set<Vertex> getAdjVertices(Vertex v) {
        return adjList.get(v).stream()
                .map(e -> e.v1.equals(v) ? e.v2 : e.v1)
                .collect(Collectors.toSet());
    }

    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(vertices);
    }

    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    public Map<Vertex, Set<Edge>> getAdjList() {
        return Collections.unmodifiableMap(adjList);
    }


}
