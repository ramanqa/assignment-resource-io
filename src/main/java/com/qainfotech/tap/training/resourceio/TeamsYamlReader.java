package com.qainfotech.tap.training.resourceio;

import org.yaml.snakeyaml.Yaml;
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


/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader{
    
    /**
     * get a list of individual objects from db yaml file
     * 
     * @return 
     * @throws Exception 
     */
    @SuppressWarnings({ "rawtypes" })
	public List<Individual> getListOfIndividuals() throws Exception{
      
    	ClassLoader classLoader = getClass().getClassLoader();		
    	File file = new File(classLoader.getResource("db.yaml").getFile());
    			InputStream is = new FileInputStream(file);		
    			Yaml yaml = new Yaml();			
    		Map yamlParsers = (Map) yaml.load(is);			
    		ArrayList individuals = (ArrayList) yamlParsers.get("individuals");		
    			
		List<Individual> ind=new ArrayList<Individual>();
		
		Individual in=null;
		for(int i=0;i<individuals.size();i++){
			
			Map map1=(Map)individuals.get(i);
		Map<String,Object>map=new HashMap<String,Object>();
   		 map.put("name", map1.get("name"));
   		 map.put("id", ((Integer) map1.get("id")).intValue());
   		 map.put("active", map1.get("active"));
			
			in=new Individual(map);
			ind.add(in);
		}
		
		return ind;
    	
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
    	Individual in=null;
    	list=  (new TeamsYamlReader()).getListOfIndividuals(); 
    	int index,flag=0;
    	for(index=0;index<list.size();index++)
    	{
    		flag=0;
    		in=list.get(index);
    		if(in.getId().compareTo(id)==0){
    			flag=1;
    			break;
    		}
    		
    	}
    	if (index == list.size() && flag == 0)
			throw new ObjectNotFoundException("Individual", "id", id.toString());
    	
    	return in;
    	
    	
    	
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
    	Individual in=null;
    	list=  (new TeamsYamlReader()).getListOfIndividuals(); 
    	int index,flag=0;
    	for(index=0;index<list.size();index++)
    	{
    		flag=0;
    		in=list.get(index);
    		if(in.getName().equals(name)){
    			
    			flag=1;
    			break;
    		}
    		
    	}
    	if (index == list.size() && flag == 0)
			throw new ObjectNotFoundException("Individual", "name", name);
    	
    	return in;
    	
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
    	Individual in=null;
    	list=  (new TeamsYamlReader()).getListOfIndividuals(); 
    	int index;
    	for(index=0;index<list.size();index++)
    	{
    		
    		in=list.get(index);
    		if(!in.isActive()){
    			list2.add(in);
    			
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
    	Individual in=null;
    	list=  (new TeamsYamlReader()).getListOfIndividuals(); 
    	int index;
    	for(index=0;index<list.size();index++)
    	{
    		
    		in=list.get(index);
    		if(in.isActive()){
    			list2.add(in);
    			
    		}
    		
    	}
    	
    	
    	return list2;
    	
    }
    
    /**
     * get a list of team objects from db yaml
     * 
     * @return 
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
	public List<Team> getListOfTeams() throws Exception{
    	
    	ClassLoader classLoader = getClass().getClassLoader();		
    	File file = new File(classLoader.getResource("db.yaml").getFile());
    			InputStream is = new FileInputStream(file);		
    			Yaml yaml = new Yaml();			
    		Map yamlParsers = (Map) yaml.load(is);	
    		
    		ArrayList teams = (ArrayList) yamlParsers.get("teams");		
    		List<Team> ind=new ArrayList<Team>();
    		TeamsYamlReader reader = new TeamsYamlReader();
    		Team teamObject=null;
    		for(int i=0;i<teams.size();i++){
    			 List<Individual> individualList = new ArrayList<>();
    			Map map1=(Map)teams.get(i);
    		Map<String,Object>map=new HashMap<String,Object>();
       		 map.put("name", map1.get("name"));
       		 map.put("id", ((Integer) map1.get("id")).intValue());
       		 
       		 ArrayList members=(ArrayList)map1.get("members");
       		 
       		for (int index = 0; index < members.size(); index++) {

				individualList.add(reader.getIndividualById(((Integer) members.get(index)).intValue()));

			}
		 
       		 
       		 map.put("members",individualList);
    			
    			teamObject=new Team(map);
    			ind.add(teamObject);
    		}
    		
    		return ind;
    }
}
