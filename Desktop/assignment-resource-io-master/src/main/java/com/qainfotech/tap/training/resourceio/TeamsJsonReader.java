package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader{
    
    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     * @throws  
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public List<Individual> getListOfIndividuals() throws Exception{
    	
    	
    	JSONObject jo=null;
    	List<Individual> list=new ArrayList<Individual>();
    	Individual individualObject = null;
    	Map<String,Object> map=new HashMap<String, Object>();
    	JSONParser parser=new JSONParser();
    	 Object obj = parser.parse(new FileReader("C:/Users/priyanka.sharma/Desktop/assignment-resource-io/src/main/resources/db.json"));
    	 JSONObject jsonObject = (JSONObject) obj;
    	 JSONArray jsonarray = (JSONArray) jsonObject.get("individuals");
    	 
    	 
    	 for (int i=0; i<jsonarray.size(); i++)
    	 {
    		 
    		 jo=(JSONObject) jsonarray.get(i);
    		 map.put("name", jo.get("name"));
    		 map.put("id", ((Long) jo.get("id")).intValue());
    		 map.put("active", jo.get("active"));
    		 individualObject = new Individual(map);
				list.add(individualObject);
					 
    	 }
    	 return list;
    	 

        
    	 
    	
    	
         
    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws Exception 
     */
    public Individual getIndividualById(Integer id) throws Exception{
    	
    	List<Individual> list=new ArrayList<Individual>();
    	Individual ind=null;
    	list=  (new TeamsJsonReader()).getListOfIndividuals(); 
    	int index,flag=0;
    	for(index=0;index<list.size();index++)
    	{
    		flag=0;
    		ind=list.get(index);
    		if(ind.getId().compareTo(id)==0){
    			flag=1;
    			break;
    		}
    		
    	}
    	if (index == list.size() && flag == 0)
			throw new ObjectNotFoundException("individual", "id", id.toString());
    	
    	return ind;
    	
    	
    	
        
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws Exception 
     */
    public Individual getIndividualByName(String name) throws Exception{
    	
    	
    	List<Individual> list=new ArrayList<Individual>();
    	Individual ind=null;
    	list=  (new TeamsJsonReader()).getListOfIndividuals(); 
    	int index,flag=0;
    	for(index=0;index<list.size();index++)
    	{
    		flag=0;
    		ind=list.get(index);
    		if(ind.getName().equals(name)){
    			
    			flag=1;
    			break;
    		}
    		
    	}
    	if (index == list.size() && flag == 0)
			throw new ObjectNotFoundException("individual", "name", name);
    	
    	return ind;
    	
    	
       
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     * @throws Exception 
     */
    public List<Individual> getListOfInactiveIndividuals() throws Exception{
    	
    	
    	List<Individual> list=new ArrayList<Individual>();
    	List<Individual> list2=new ArrayList<Individual>();
    	Individual ind=null;
    	list=  (new TeamsJsonReader()).getListOfIndividuals(); 
    	int index;
    	for(index=0;index<list.size();index++)
    	{
    		
    		ind=list.get(index);
    		if(!ind.isActive()){
    			list2.add(ind);
    			
    		}
    		
    	}
    	
    	
    	return list2;

    	
        
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     * @throws Exception 
     */
    public List<Individual> getListOfActiveIndividuals() throws Exception{
       
    	
    	List<Individual> list=new ArrayList<Individual>();
    	List<Individual> list2=new ArrayList<Individual>();
    	Individual ind=null;
    	list=  (new TeamsJsonReader()).getListOfIndividuals(); 
    	int index;
    	for(index=0;index<list.size();index++)
    	{
    		
    		ind=list.get(index);
    		if(ind.isActive()){
    			list2.add(ind);
    			
    		}
    		
    	}
    	
    	
    	return list2;
    	
    }
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     * @throws Exception 
     */
    public List<Team> getListOfTeams() throws Exception{
    	
    	
    	JSONObject jo=null;
    	List<Team> listTeam=new ArrayList<Team>();
    	Team teamObject = null;
    	Map<String,Object> map=new HashMap<String, Object>();
    	JSONParser parser=new JSONParser();
    	 Object obj = parser.parse(new FileReader("C:/Users/priyanka.sharma/Desktop/assignment-resource-io/src/main/resources/db.json"));
    	 JSONObject jsonObject = (JSONObject) obj;
    	 JSONArray jsonarray = (JSONArray) jsonObject.get("teams");
    	 TeamsJsonReader reader = new TeamsJsonReader();
    	 
    	 for (int i=0; i<jsonarray.size(); i++)
    	 {
    		 List<Individual> individualList = new ArrayList<>();
    		 jo=(JSONObject) jsonarray.get(i);
    		 map.put("name", jo.get("name"));
    		 map.put("id", ((Long) jo.get("id")).intValue());
    		 
    		 JSONArray memberArray = (JSONArray) jo.get("members");
    		 for (int index = 0; index < memberArray.size(); index++) {

					individualList.add(reader.getIndividualById(((Long) memberArray.get(index)).intValue()));

				}
    		 
    		 
    		 map.put("members", individualList);
    		teamObject= new Team(map);
				listTeam.add(teamObject);
					 
    	 }
    	 return listTeam;
    	 
       
    }
}
