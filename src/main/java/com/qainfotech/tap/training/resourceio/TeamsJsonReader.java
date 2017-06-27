package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader{
    
    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     */
	
	JSONParser parser =new JSONParser();
	 Object obj;
	 private List<Team> teamList;
	 private List<Individual> individualList;
	
	 List<Individual> activeList=new ArrayList<Individual>();
	 List<Individual> notActiveList=new ArrayList<Individual>();

    
   public List<Individual> getListOfIndividuals(){
	   try {
		obj = parser.parse(new FileReader("src/main/resources/db.json"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
   	

	    JSONObject jsonObject = (JSONObject) obj;
	    
	   
   	 individualList=new ArrayList<Individual>();
     
        JSONArray arr=(JSONArray)jsonObject.get("individuals");
        JSONObject myobj;
        
        Map<String, Object> map;
        for(int index=0;index<arr.size();index++)
        {
        	
        	 myobj = (JSONObject) arr.get(index);
        	 map=(Map<String,Object>)myobj.clone();
        	 
        	 
        	 Individual individual=new Individual(map);
          individualList.add(individual);
          
        	 
            		
        }


        return individualList;
   }
   
     // throw new UnsupportedOperationException("Not implemented.");
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
        public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
    	
        	if(this.getListOfIndividuals()==null)
            	this.getListOfIndividuals();
    	
     	for(int index=0;index<individualList.size();index++)
    	{
    		if(individualList.get(index).getId()==(int)id)
    		{
    			return individualList.get(index);
    		}
    			
    	}
        throw new ObjectNotFoundException(null,null,null);
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
    	
    	if(this.getListOfIndividuals()==null)
        	this.getListOfIndividuals();
    	for(int index=0;index<individualList.size();index++)
    	{
    		if(individualList.get(index).getName().equals(name))
    		{
    			return individualList.get(index);
    		}		
    			
    	}
		
    
    	//return nameList;
    
       throw new ObjectNotFoundException(null,null,null);
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
    	
    	if(this.getListOfIndividuals()==null)
    	this.getListOfIndividuals();
    	
       	for(int index=0;index<individualList.size();index++)
    	{
    		if(individualList.get(index).isActive()==false)
notActiveList.add(individualList.get(index));
    				
    	}
    	return (List<Individual>)notActiveList;
        //throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
    	if(this.getListOfIndividuals()==null)
        	this.getListOfIndividuals();
    	
      	for(int index=0;index<individualList.size();index++)
    	{
    		if(individualList.get(index).isActive()==true)
activeList.add(individualList.get(index));
    				
    	}
      	return (List<Individual>)activeList;
    
       // throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
    	
 	   try {
 			obj = parser.parse(new FileReader("src/main/resources/db.json"));
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
 	   
 	    JSONObject jsonObject = (JSONObject) obj;
 	   teamList=new ArrayList<Team>();
 
 	    
         JSONArray arr=(JSONArray)jsonObject.get("teams");
         JSONObject myobj;
        
         Map<String, Object> map;
         Team team;
         for(int index=0;index<arr.size();index++)
         {
         	
         	 myobj = (JSONObject) arr.get(index);
         
        
         	 map=(Map<String,Object>)myobj.clone();
         	 
         	team=new Team(map);
           teamList.add(team);
      
         		
         }
		return teamList;
 	   	 
      //throw new UnsupportedOperationException("Not implemented.");
    }
    
    
    
}
