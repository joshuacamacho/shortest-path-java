/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortest.path.java;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Josh
 */
public class ShortestPathJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Vertex> nodes;
        ArrayList<Edge> edges;
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
    
      
        nodes.add(new Vertex(Integer.toString(0), "SFO"));
        nodes.add(new Vertex(Integer.toString(1), "BOS"));
        nodes.add(new Vertex(Integer.toString(2), "ORD"));
        nodes.add(new Vertex(Integer.toString(3), "JFK"));
        nodes.add(new Vertex(Integer.toString(4), "DFW"));
        nodes.add(new Vertex(Integer.toString(5), "MIA"));
        nodes.add(new Vertex(Integer.toString(6), "LAX"));
     /*
        
        In the vertex and edge structure defined below
        Vertex	    Edge	Vertex
        0 SFO	    2704	1 BOS
        0 SFO	    1846	2 ORD
        2 ORD	     867	1 BOS
        2 ORD	     740	3 JFK
        3 JFK	     187	1 BOS
        0SFO	    1464	DFW
        DFW	     802	ORD
        DFW	    1121	MIA
        MIA	    1090	JFK
        MIA	    1258	BOS
        SFO	     337	LAX
        LAX	    1235	DFW
        LAX	    2342	MIA
        */
    addLane("Edge_0", 0, 1, 2704,nodes,edges);
    addLane("Edge_00", 1, 0, 2704,nodes,edges);
    addLane("Edge_1", 0, 2, 1846,nodes,edges);
    addLane("Edge_11", 2, 0, 1846,nodes,edges);
    addLane("Edge_2", 2, 1, 867,nodes,edges);
    addLane("Edge_22", 1, 2, 867,nodes,edges);
    addLane("Edge_3", 2, 3, 740,nodes,edges);
    addLane("Edge_33", 3, 2, 740,nodes,edges);
    addLane("Edge_4", 3, 1, 187,nodes,edges);
    addLane("Edge_44", 1, 3, 187,nodes,edges);
    addLane("Edge_5", 0, 4, 1464,nodes,edges);
    addLane("Edge_55", 4, 0, 1464,nodes,edges);
    addLane("Edge_6", 4, 2, 802,nodes,edges);
    addLane("Edge_66", 2, 4, 802,nodes,edges);
    addLane("Edge_7", 4, 5, 1121,nodes,edges);
    addLane("Edge_77", 5, 4, 1121,nodes,edges);
    addLane("Edge_8", 5, 3, 1090,nodes,edges);
    addLane("Edge_88", 3, 5, 1090,nodes,edges);
    addLane("Edge_9", 5, 1, 1258,nodes,edges);
    addLane("Edge_99", 1, 5, 1258,nodes,edges);
    addLane("Edge_10", 0, 6, 337,nodes,edges);
    addLane("Edge_100", 6, 0, 337,nodes,edges);
    addLane("Edge_11", 6, 4, 1235,nodes,edges);
    addLane("Edge_111", 4, 6, 1235,nodes,edges);
    addLane("Edge_12", 6, 5, 2342,nodes,edges);
    addLane("Edge_122", 5, 6, 2342,nodes,edges);
    // Lets check from location Loc_1 to Loc_10
    Graph graph = new Graph(nodes, edges);
    DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
    dijkstra.execute(nodes.get(3));
    LinkedList<Vertex> path = dijkstra.getPath(nodes.get(6));
    
    
    System.out.println("Shortest Path FROM JFK to LAX");
    
    for (Vertex v : path) {
      System.out.println(v);
    }
    System.out.println("Shortest Distance "+dijkstra.getShortestDistance(nodes.get(6)));    
   
    }
    
    private static void addLane(String laneId, int sourceLocNo, int destLocNo,
        int duration,ArrayList<Vertex> nodes,ArrayList<Edge> edges) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
  }
    
}
