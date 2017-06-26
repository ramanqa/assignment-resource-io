package com.qainfotech.tap.training.resourceio.model;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Individual {
    
    private final String name;
    private final Integer id;
    private final Boolean active;
    
    
    public Individual(Map<String, Object> individualMap){
    	
    Map<String, Object> myMap = individualMap;
    String variables = myMap.get("key").toString();
    
    String divide[]=variables.split("="); 
    	
    name=divide[0];
    id=Integer.parseInt(divide[1]);
    active = Boolean.parseBoolean(divide[2]);
    
    
    //System.out.println(name+"  "+id+"   "+active);
    
    	
       // throw new UnsupportedOperationException("Not implemented.");
    }
  
    
//    
//   public Individual(String a, Integer b, Boolean c)
//   {
//    	
//   	name=a;
//    	id=b;
//    	active=c;
//  	
//   }
//  
    /**
     * get the name of individual
     * 
     * @return individual name
     */
    public String getName(){
        return name;
    }
    
    /**
     * get the employee of of individual
     * @return id
     */
    public Integer getId(){
        return id;
    }
    
    /**
     * get the active status of individual
     * 
     * @return 
     */
    public Boolean isActive(){
        return active;
    }
    
    public static void main(String... S)
    {
    	
    	
    	Map<String, Object> individualMap = new HashMap() ;
    	individualMap.put("key", "hey=525=false");
		Individual myobj = new Individual(individualMap);
    }
    
}
