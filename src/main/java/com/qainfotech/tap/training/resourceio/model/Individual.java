package com.qainfotech.tap.training.resourceio.model;

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
        throw new UnsupportedOperationException("Not implemented.");
    }
    
    
    
    public Individual(String a, Integer b, Boolean c)
    {
    	
    	name=a;
    	id=b;
    	active=c;
    	
    	
    }
    
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
}
