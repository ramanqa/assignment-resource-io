package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
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
     */
    public List<Individual> getListOfIndividuals(){
      	List<Individual> individualList = new ArrayList<>();
		Individual individualObject = null;
		Map<String, Object> myMap = null;
		Object obj = null;
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		JSONObject jsonObj = null;
		JSONParser parser = new JSONParser();
		try {
			obj = parser.parse(new FileReader("src/main/resources/db.json"));
			jsonObject = (JSONObject) obj;

			jsonArray = (JSONArray) jsonObject.get("individuals");

			int length = jsonArray.size();
			for (int size = 0; size < length; size++) {

				jsonObj = (JSONObject) jsonArray.get(size);
				myMap = new HashMap<String, Object>();
				myMap.put("name", jsonObj.get("name"));
				myMap.put("id", ((Long) jsonObj.get("id")).intValue());
				myMap.put("active", jsonObj.get("active"));
				individualObject = new Individual(myMap);
				individualList.add(individualObject);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return individualList;
    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
        	List<Individual> individualList = new ArrayList<>();
		individualList = (new TeamsJsonReader()).getListOfIndividuals();
		Individual individual = null;
		int size, counter = 0;
		for (size = 0; size < individualList.size(); size++) {
			counter = 0;

			individual = individualList.get(size);
			if (individual.getId().compareTo(id) == 0) {
				counter = 1;

				break;
			}
		}

		if (size == individualList.size() && counter == 0)
			throw new ObjectNotFoundException("individual", "id", id.toString());

		return individual;

    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
     	
		List<Individual> individualList = new ArrayList<>();
		individualList = (new TeamsJsonReader()).getListOfIndividuals();
		Individual individual = null;
		int size, counter = 0;
		for (size = 0; size < individualList.size(); size++) {
			counter = 0;

			individual = individualList.get(size);

			if (individual.getName().equals(name)) {
				counter = 1;

				break;
			}
		}

		if (size == individualList.size() && counter == 0)
			throw new ObjectNotFoundException("individual", "name", name);

		return individual;


    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
	    	List<Individual> individualList = new ArrayList<>();
		List<Individual> inactiveIndividualList = new ArrayList<>();
		individualList = (new TeamsJsonReader()).getListOfIndividuals();
		Individual individual = null;
		int size, counter;
		for (size = 0; size < individualList.size(); size++) {
			counter = 0;

			individual = individualList.get(size);

			if (!individual.isActive()) {
				counter = 1;

				inactiveIndividualList.add(individual);

			}
		}

		return inactiveIndividualList;
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
   List<Individual> individualList = new ArrayList<>();
		List<Individual> activeIndividualList = new ArrayList<>();
		individualList = (new TeamsJsonReader()).getListOfIndividuals();
		Individual individual = null;
		int size, counter;
		for (size = 0; size < individualList.size(); size++) {
			counter = 0;

			individual = individualList.get(size);

			if (individual.isActive()) {
				counter = 1;

				activeIndividualList.add(individual);

			}
		}

		return activeIndividualList;

    }
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
        	Map<String, Object> myMap = new HashMap<String, Object>();
		JSONObject jsonobj = null;
		List<Team> team = new ArrayList<>();
		TeamsJsonReader reader = new TeamsJsonReader();
		JSONParser parser = new JSONParser();
		Object obj = null;

		try {
			obj = parser.parse(new FileReader("src/main/resources/db.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonarr = (JSONArray) jsonObject.get("teams");
			for (int size = 0; size < jsonarr.size(); size++) {
				List<Individual> individualList = new ArrayList<>();
				jsonobj = (JSONObject) jsonarr.get(size);
				myMap.put("name", jsonobj.get("name"));
				myMap.put("id", ((Long) jsonobj.get("id")).intValue());
				JSONArray memberArray = (JSONArray) jsonobj.get("members");

				for (int index = 0; index < memberArray.size(); index++) {

					individualList.add(reader.getIndividualById(((Long) memberArray.get(index)).intValue()));

				}
				myMap.put("members", individualList);
				team.add(new Team(myMap));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return team;
    }
}
