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
        }
       
        
    
      filein.close();
      filein= new Scanner(System.in);
      boolean CONTINUE = true;
      while(CONTINUE){
            switch (option(filein)){
                case 1:
                {
                    System.out.print("City Code: ");
                    String code = filein.next();
                    if(cities.containsKey(code)){
                        cities.get(code).print();
                    }else{
                        System.out.println("Invalid City Code, City not found");
                    }
                    break;
                }
                case 2:
                {
                    System.out.print("City Codes: ");
                    String code1 = filein.next();
                    String code2 = filein.next();
//                    System.out.println("Code 1 =" +code1+ "Code 2 = "+code2);
                    if(!(cities.containsKey(code1)&&cities.containsKey(code2))){
                        System.out.println("Code(s) invalid, cities not found: " + (cities.containsKey(code1)?"":code1)
                        +" "+(cities.containsKey(code2)?"":code2));
                        break;
                    }
                    Graph graph = new Graph(nodes, edges);
                    DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
                    
                    Vertex node2= nodes.get(Integer.parseInt(cities.get(code2).id)-1);
                    Vertex node1= nodes.get(Integer.parseInt(cities.get(code1).id)-1);
                    dijkstra.execute(node1);
                    LinkedList<Vertex> path = dijkstra.getPath(node2);
                    System.out.print("\nMinimum distance between ");
                    System.out.print(cities.get(code1).getName());
                    System.out.print(" and ");
                    System.out.print(cities.get(code2).getName());
                    System.out.print(" is "+dijkstra.getShortestDistance(node2));
                    System.out.print("\nRoute:\n");
                    for (Vertex v : path) {
                      System.out.println(v);
                    }
                    

    
                    break;
                }
                case 3:{
                    System.out.print("City codes and distance: ");
                    String code1= filein.next();
                    String code2= filein.next();
                    int pos1= Integer.parseInt(cities.get(code1).id)-1;
                    int pos2= Integer.parseInt(cities.get(code2).id)-1;
                    int dist= filein.nextInt();
                    if(hasEdge(nodes.get(pos1),nodes.get(pos2),edges)){
                        System.out.println("Edge already exists");
                    }else{
                        edges.add(new Edge(" ", nodes.get(pos1),nodes.get(pos2),dist));
                        System.out.println("You have inserted a road from"+
                                cities.get(code1).name+" to "+
                                cities.get(code2).name+
                                " with a distance of "+dist);
                    }
                    break;
                }
                case 4:{
                    System.out.print("City codes: ");
                    String code1= filein.next();
                    String code2= filein.next();
                    int pos1= Integer.parseInt(cities.get(code1).id)-1;
                    int pos2= Integer.parseInt(cities.get(code2).id)-1;
                    if(hasEdge(nodes.get(pos1),nodes.get(pos2),edges)){
                        deleteEdge(nodes.get(pos1),nodes.get(pos2),edges);
                        System.out.println("Road between"+
                                cities.get(code1).name+" and "+
                                cities.get(code2).name+" deleted");
                    }else{
                        System.out.println("Road between"+
                                cities.get(code1).name+" and "+
                                cities.get(code2).name+" doesn't exist");
                    }
                    break;
                }
                case 0:{
                    CONTINUE=false;
                    break;
                }
                default:{
                    System.out.println("Not an acceptable value. try again");
                    break;
                }
            }
            
            
        }
      
        
   
    
    // Lets check from location Loc_1 to Loc_10
   
    
    
    }
    
    

    private static int option(Scanner scan) {
        System.out.print("\nCommand? ");
       switch (scan.next()){
           case "Q":
           case "q":
               return 1;
           case "D":
           case "d":
               return 2;
           case "I":
           case "i":
               return 3;
           case "R":
           case "r":
               return 4;
           case "E":
           case "e":
               return 0;
           case "H":
           case "h":
               System.out.print(
                       "Q Query the city information by entering the city code\n"+
                       "D Find the minimum distance between two cities\n"+
                       "I Insert a road by entering two city codes and distance\n"+
                       "R Remove an existing road by entering two city codes\n"+
                       "H Display this message\n"+
                       "E Exit\n");
               return 6;
           default: return -1;
       }
       
    }

    private static boolean hasEdge(Vertex v1, Vertex v2, ArrayList<Edge> edges) {
        for(int i=0; i<edges.size();i++){
            if(edges.get(i).start.equals(v1) && edges.get(i).end.equals(v2)){
                return true;
            }
            
        }
        return false;
    }

    private static void deleteEdge(Vertex v1, Vertex v2, ArrayList<Edge> edges) {
        for(int i=0; i<edges.size();i++){
            if(edges.get(i).start.equals(v1) && edges.get(i).end.equals(v2)){
                edges.remove(i);
            }
            
        }
        
    }
    
}
