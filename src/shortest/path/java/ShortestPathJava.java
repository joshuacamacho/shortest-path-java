/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortest.path.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Josh
 */
public class ShortestPathJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, City> cities = new HashMap<String, City>();
        ArrayList<Vertex> nodes;
        ArrayList<Edge> edges;
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        
        Scanner filein = new Scanner(new FileInputStream("city.dat"));
        while (filein.hasNext()) {      // while there is another token to read
           String line = filein.nextLine();
           if(line.length()>0 && (line.charAt(0)==' ')){
                line = line.substring(1);
            }
           String[] stuff = line.split("\\s+");
//           System.out.println(stuff.length);
           
           boolean foundID=false;
           String cityCode="";
           String cityID="";
           String cityName="";
//           System.out.println("Line was " +line);
           
           for(int i=0; i<stuff.length; i++){
//               System.out.println("I came to"+stuff[0]+ "and ");
               if(stuff[0].length()>0 && Character.isDigit(stuff[0].charAt(0)) && !foundID){
//                   System.out.print("found digit! it was"+stuff[0].charAt(i)+"\n");
                   foundID=true;
                   cityID=stuff[i];
                   cityCode=stuff[i+1];
                   int j=2;
                   while(stuff[i+j].length()>0 && Character.isAlphabetic(stuff[i+j].charAt(0))){
                       cityName=cityName +" "+ stuff[i+j];
                       j++;
                   }
               }else{
//                   System.out.print("didnt find digit\n");
               }
               
           }
           
           City toAdd = new City(cityID,cityCode,line,cityName);
           
           nodes.add(new Vertex(toAdd.getId(), toAdd));
//           test.print();
           cities.put(stuff[1],toAdd);
//           System.out.print("\n");
           
        }
        
        
        
         for(String key: cities.keySet()){
            City toAdd = cities.get(key);
           
//            System.out.print(key + " ");
//            cities.get(key).print();
//            System.out.print("\n");
         }
        filein.close();
        filein = new Scanner(new FileInputStream("road.dat"));
        int count=0;
        while (filein.hasNext()) {      // while there is another token to read
           String line = filein.nextLine();
           if(line.length()>0 && (line.charAt(0)==' ')){
                line = line.substring(1);
            }
           String[] stuff = line.split("\\s+");
           
           for(int i=0; i<stuff.length; i++){
//               System.out.print(stuff[i]+ " ");
           }
           String edgeName="edge"+Integer.toString(count);
           count++;
           edges.add(new Edge(edgeName,
                   nodes.get(Integer.parseInt(stuff[0])-1), 
                   nodes.get(Integer.parseInt(stuff[1])-1), 
                   Integer.parseInt(stuff[2])));
           
//           System.out.print("\n");
//           System.out.println(stuff.length);
        
        }
        //City test2 = cities.get("AN");
        
    
      
        
        
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
        
  }
    
}
