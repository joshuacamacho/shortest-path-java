/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortest.path.java;

/**
 *
 * @author Josh
 */
public class City {
    public String id;
    public String code;
    public String info;
    public String name;

    
    
    public City(String cityId, String c, String i, String n){
        id = cityId;
        code = c;
        info = i;
        name = n;
      
    }
    public City(){
        id = "";
        code = "";
        info = "";

    }
    public void print(){
        System.out.print(info);
    }
    
    public void setInfo(String num, String c, String i,String n){
        id = num;
        code = c;
        info = i;
        
    }
    
    public String getId(){
        return id;
    }
    
    public String getInfo(){
        return info;
    }
    public String getName(){
        return name;
    }
}
