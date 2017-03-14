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
public class Edge  {
  public String id;
  public int weight; 
  public Vertex start;
  public Vertex end;
  
  
  public Edge(String id, Vertex source, Vertex destination, int weight) {
    this.id = id;
    this.start = source;
    this.end = destination;
    this.weight = weight;
  }
  
  public String getId() {
    return id;
  }
  public Vertex getDestination() {
    return end;
  }

  public Vertex getSource() {
    return start;
  }
  public int getWeight() {
    return weight;
  }
  
  @Override
  public String toString() {
    return start + " " + end;
  }
  
  
} 
