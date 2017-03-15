/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortest.path.java;

/**
 *
 * @author Joshua
 */
import java.util.List;

public class Graph {
    public List<Edge> edges;
    public List<Vertex> verticies;


  public Graph(List<Vertex> vertexes, List<Edge> edges) {
    this.verticies = vertexes;
    this.edges = edges;
  }

  public List<Vertex> getVertexes() {
    return verticies;
  }

  public List<Edge> getEdges() {
    return edges;
  }
  
  
  
} 