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
import java.util.Iterator;
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
	List<Team> team_List=new ArrayList<>();
	List<Team> teamActiveList=new ArrayList<>();
	List<Team> teamInactiveList=new ArrayList<>();
	 List<Individual> individualList = new ArrayList<>();
	 Map<String,ArrayList> values;
	 Map<String,ArrayList> values2;
	 List<Individual> activeList=new ArrayList<Individual>();
	 List<Individual> notActiveList=new ArrayList<Individual>();

    public List<Individual> getListOfIndividuals() throws FileNotFoundException{
    	
    	try{
    		individualList.clear();
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
    	
    	individualList.clear();
    	if(this.getListOfIndividuals()==null)
        	this.getListOfIndividuals();
    	
     	for(int index=0;index<individualList.size();index++)
    	{
    		if(individualList.get(index).getId()==(int)id)
    		{
    			return individualList.get(index);
    		}
    			
    	}
     	 throw new ObjectNotFoundException("Individual","id",id.toString());
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
    	individualList.clear();
    	if(this.getListOfIndividuals()==null)
        	this.getListOfIndividuals();
    	for(int index=0;index<individualList.size();index++)
    	{
    		if(individualList.get(index).getName().equals(name))
    		{
    			return individualList.get(index);
    		}		
    			
    	}
    	
    	throw new ObjectNotFoundException("Individual","Name",name.toString());
       // throw new UnsupportedOperationException("Not implemented.");
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     * @throws FileNotFoundException 
     */
    public List<Individual> getListOfInactiveIndividuals() throws FileNotFoundException{
    	individualList.clear();
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
    	individualList.clear();
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
     * @throws ObjectNotFoundException 
     * @throws FileNotFoundException 
     */
    public List<Team> getListOfTeams() throws FileNotFoundException, ObjectNotFoundException{
    	
    	try{
    		team_List.clear();
    values2 = (Map<String, ArrayList>) yaml
    			.load(new FileInputStream(new File("src/main/resources/db.yaml")));
    	}
    	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Team teamlist;
    	Map<String , Object> teamMap = new HashMap<>();
    	ArrayList team= (ArrayList) values2.get("teams");
    	for (int index = 0; index < team.size(); index++)
    	{
    		 List<Individual> membersTeam=new ArrayList<>();
    		Map teams = (Map<String, ArrayList>)team.get(index);
    		int id = (Integer)teams.get("id");
    		teamMap.put("id", id);
    		String name=(String)teams.get("name");
    		teamMap.put("name", name);
    		ArrayList members=(ArrayList)teams.get("members");
    		
    		for(int i=0;i< members.size();i++)
         	{
         		  
         		int idTeam=Integer.parseInt(members.get(i).toString());
         		membersTeam.add(getIndividualById(idTeam));
        	}
        		
        	
    		teamMap.put("members", membersTeam);
    		teamlist=new Team(teamMap);
    		team_List.add(teamlist);
    		

    	}
    	
		return team_List;
		
		
    	
       // throw new UnsupportedOperationException("Not implemented.");
    }
  
    
    }
