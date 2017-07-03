package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;




/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */


public class TeamsYamlReader{
    
    /**
     * get a list of individual objects from db yaml file
     * 
     * @return 
     */

	Yaml yaml = new Yaml();
	 List<Individual> individualList = new ArrayList<>();
	 Map<String,ArrayList> values;
	 List<Individual> activeList=new ArrayList<Individual>();
	 List<Individual> notActiveList=new ArrayList<Individual>();

    public List<Individual> getListOfIndividuals() throws FileNotFoundException{
    	
    	try{
    values = (Map<String, ArrayList>) yaml
    			.load(new FileInputStream(new File("src/main/resources/db.yaml")));
    	}
    	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Individual indList;
    	Map<String , Object> myMap = new HashMap<>();
    	ArrayList ind= (ArrayList) values.get("individuals");
    	for (int index = 0; index < ind.size(); index++)
    	{
    		
    		Map individuals = (Map<String, ArrayList>)ind.get(index);
    		int id = (Integer)individuals.get("id");
    		myMap.put("id", id);
    		
    		String name=(String)individuals.get("name");
    		myMap.put("name", name);
    		boolean active=(boolean)individuals.get("active");
    		myMap.put("active", active);
    	    indList=new Individual(myMap);

    	
    		individualList.add(indList);
    		
    	}
    
		return individualList;
    	
    	
		
    	
       // throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     * @throws FileNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException, FileNotFoundException{
    	
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
      //  throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     * @throws FileNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException, FileNotFoundException{
    	
    	if(this.getListOfIndividuals()==null)
        	this.getListOfIndividuals();
    	for(int index=0;index<individualList.size();index++)
    	{
    		if(individualList.get(index).getName().equals(name))
    		{
    			return individualList.get(index);
    		}		
    			
    	}
    	
    	throw new ObjectNotFoundException(null,null,null);
       // throw new UnsupportedOperationException("Not implemented.");
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     * @throws FileNotFoundException 
     */
    public List<Individual> getListOfInactiveIndividuals() throws FileNotFoundException{
    	
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
     * @throws FileNotFoundException 
     */
    public List<Individual> getListOfActiveIndividuals() throws FileNotFoundException{
    	
    	if(this.getListOfIndividuals()==null)
        	this.getListOfIndividuals();
    	
      	for(int index=0;index<individualList.size();index++)
    	{
    		if(individualList.get(index).isActive()==true)
activeList.add(individualList.get(index));
    				
    	}
      	return (List<Individual>)activeList;
    
    	
      //  throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get a list of team objects from db yaml
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
        throw new UnsupportedOperationException("Not implemented.");
    }
   public static void main(String args[]) throws FileNotFoundException{
	   TeamsYamlReader ob=new TeamsYamlReader();
	   ob.getListOfIndividuals();
   }
    }
