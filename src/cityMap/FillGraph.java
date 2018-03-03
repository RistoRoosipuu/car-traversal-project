package cityMap;

import streets.Street;
import streets.StreetCrossroad;

public class FillGraph {

    private CityMapGraph graph;

    public FillGraph(CityMapGraph graph) {
        this.graph = graph;
        fillGraph();
    }


    private void fillGraph() {

        Vertex vertex1 = new Vertex(1, new StreetCrossroad("Pelgulinna", true, false));
        Vertex vertex2 = new Vertex(2, new StreetCrossroad("Uus Maaiilm"));
        Vertex vertex3 = new Vertex(3, new StreetCrossroad("Nõmme", true, false));
        Vertex vertex4 = new Vertex(4, new StreetCrossroad("Kose"));
        Vertex vertex5 = new Vertex(5, new StreetCrossroad("Vismeistri"));
        Vertex vertex6 = new Vertex(6, new StreetCrossroad("Kassisaba"));
        Vertex vertex7 = new Vertex(7, new StreetCrossroad("Pikaliiva"));
        Vertex vertex8 = new Vertex(8, new StreetCrossroad("Pääsküla", false, true));
        Vertex vertex9 = new Vertex(9, new StreetCrossroad("Sadama"));
        Vertex vertex10 = new Vertex(10, new StreetCrossroad("Priisle"));
        Vertex vertex11 = new Vertex(11, new StreetCrossroad("Tatari", false, true));
        Vertex vertex12 = new Vertex(12, new StreetCrossroad("Kakumäe"));
        Vertex vertex13 = new Vertex(13, new StreetCrossroad("Merivälja", true, false));
        Vertex vertex14 = new Vertex(14, new StreetCrossroad("Vanalinn"));
        Vertex vertex15 = new Vertex(15, new StreetCrossroad("Kadaka"));
        Vertex vertex16 = new Vertex(16, new StreetCrossroad("Mähe", false, true));
        Vertex vertex17 = new Vertex(17, new StreetCrossroad("Järve"));
        Vertex vertex18 = new Vertex(18, new StreetCrossroad("Kadriorg"));
        Vertex vertex19 = new Vertex(19, new StreetCrossroad("Kalamaja"));
        Vertex vertex20 = new Vertex(20, new StreetCrossroad("Kopli", false, true));
        Vertex vertex21 = new Vertex(21, new StreetCrossroad("Loopealse"));
        Vertex vertex22 = new Vertex(22, new StreetCrossroad("Karjamaa"));
        Vertex vertex23 = new Vertex(23, new StreetCrossroad("Pikaliiva", true, false));


        Street b1 = new Street("Allika tn");
        Street b2 = new Street("Astla tee");
        Street b3 = new Street("Auna tn");
        Street b4 = new Street("Armatuuri tn");
        Street b5 = new Street("Bensiini tn");
        Street b6 = new Street("Betooni põik");
        Street b7 = new Street("Edela tn");
        Street b8 = new Street("Eha tn");
        Street b9 = new Street("Ehitajate tee");
        Street b10 = new Street("Energia tn");
        Street b11 = new Street("Falgi tee");
        Street b12 = new Street("Filmi tn");
        Street b13 = new Street("Hiiepuu tn");
        Street b14 = new Street("Hirve tn");
        Street b15 = new Street("Hobusepea tn");
        Street b16 = new Street("Härjapea tn");
        Street b17 = new Street("Ida tee");
        Street b18 = new Street("Ilvese tn");
        Street b19 = new Street("Kauka tn");
        Street b20 = new Street("Keava tn");
        Street b21 = new Street("Kiire tn");
        Street b22 = new Street("Luise tn");
        Street b23 = new Street("Luste tn");
        Street b24 = new Street("Linnuse tee");


        graph.addEdge(vertex1, vertex2, b1);
        graph.addEdge(vertex2, vertex3, b2);
        graph.addEdge(vertex2, vertex5, b3);
        graph.addEdge(vertex2, vertex4, b4);
        graph.addEdge(vertex5, vertex6, b5);
        graph.addEdge(vertex6, vertex7, b6);
        graph.addEdge(vertex7, vertex8, b7);
        graph.addEdge(vertex8, vertex9, b8);
        graph.addEdge(vertex9, vertex10, b9);
        graph.addEdge(vertex9, vertex11, b10);
        graph.addEdge(vertex11, vertex12, b12);
        graph.addEdge(vertex12, vertex13, b13);
        graph.addEdge(vertex12, vertex14, b14);
        graph.addEdge(vertex11, vertex15, b15);
        graph.addEdge(vertex15, vertex16, b16);
        graph.addEdge(vertex15, vertex17, b17);
        graph.addEdge(vertex4, vertex18, b18);
        graph.addEdge(vertex4, vertex11, b11);
        graph.addEdge(vertex4, vertex19, b19);
        graph.addEdge(vertex19, vertex22, b23);
        graph.addEdge(vertex19, vertex20, b20);
        graph.addEdge(vertex20, vertex21, b21);
        graph.addEdge(vertex21, vertex22, b22);
        graph.addEdge(vertex22, vertex23, b24);

    }
}
