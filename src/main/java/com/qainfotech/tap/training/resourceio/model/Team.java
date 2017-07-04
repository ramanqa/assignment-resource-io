package com.qainfotech.tap.training.resourceio.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qainfotech.tap.training.resourceio.TeamsJsonReader;
import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {
	/**
	 * created object of TeamJsonReader class
	 */
	
	 TeamsJsonReader teamJsonReader=new TeamsJsonReader();
	
    private final String name;
    private final Integer id;
    private final List<Individual> members;
   // private List<Team> teamList;
    
    public Team(Map<String, Object> teamMap){
    	
    	this.name=(String)teamMap.get("name");
    	
    	this.members=(List<Individual>) teamMap.get("members");
    	
    	this.id=(Integer) Integer.parseInt(teamMap.get("id").toString());
    	
    }
    	
    //  throw new UnsupportedOperationException("Not implemented.");
  
    
    /**
     * get team name
     * 
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * get team id
     * 
     * @return 
     */
    public Integer getId(){
        return id;
    }
    
    /** 
     * get list of individuals that are members of this team
     * 
     * @return 
     */
    public List<Individual> getMembers(){
        return members;
    }
    
    /**
     * get a list of individuals that are members of this team and are also active
     * 
     * @return 
     * @throws ObjectNotFoundException 
     */
    public List<Individual> getActiveMembers() throws ObjectNotFoundException{
    	List<Individual> activeList=new ArrayList<Individual>();
    	Iterator<Individual> itr=this.members.iterator();
    	
    	
    	while(itr.hasNext()){
    		
    		Individual individual=itr.next();
    	
    
    		/*
    		 * Checked the value of isActive function to return the list of active members present in the team
    		 */
    		if(individual.isActive())
    		{
    			activeList.add(individual);
    		}
    		
    	}
    	 return activeList;
    
    }
  

	/**
     * get a list of individuals that are members of this team but are inactive
     * 
     * @return 
     */
    public List<Individual> getInactiveMembers(){
    	
    	List<Individual> inactiveList=new ArrayList<>();
    	Iterator<Individual> itr=this.members.iterator();
    	while(itr.hasNext()){
    		Individual individual=itr.next();
    		/*
    		 * Checked the value of isActive function to return the list of inactive members present in the team
    		 */
    		if(!(individual.isActive()))
    		{
    			inactiveList.add(individual);
    		}
    	}
       return inactiveList;
    }
 
}
