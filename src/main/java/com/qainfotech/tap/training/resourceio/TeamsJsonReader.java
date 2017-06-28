package com.qainfotech.tap.training.resourceio;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;
import java.util.List;

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
    JSONParser parser = new JSONParser();
	Map<String, Object> map = null;
    public List<Individual> getListOfIndividuals(){
       Object reader = null;

		Individual member = null;
		JSONObject jsonobj2 = null;
		List<Individual> listofindividual = new ArrayList<>();
		map = new HashMap<String, Object>();
		try {
			reader = parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = (JSONObject) reader;
		;
		JSONArray arrayOfIndividual = (JSONArray) jsonObject.get("individuals");
		for (int index = 0; index < arrayOfIndividual.size(); index++) {
			jsonobj2 = (JSONObject) arrayOfIndividual.get(index);
			map.put("name", jsonobj2.get("name"));
			map.put("id", ((Long) jsonobj2.get("id")).intValue());
			map.put("active", jsonobj2.get("active"));
			member = new Individual(map);
			listofindividual.add(member);
		}
		return listofindividual;
    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
       List<Individual> listofindividual = new ArrayList<>();
		try {
			listofindividual = (new TeamsJsonReader()).getListOfIndividuals();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Individual individual = null;
		int index, match = 0;
		for (index = 0; index < listofindividual.size(); index++) {
			individual = listofindividual.get(index);
			if (individual.getId().compareTo(id) == 0) {
				match = 1;
				break;
			}
		}
		if (match == 0) {
			throw new ObjectNotFoundException("individual", "id", id.toString());
		} else
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
        List<Individual> listofindividual = new ArrayList<>();
		try {
			listofindividual = (new TeamsJsonReader()).getListOfIndividuals();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Individual individual = null;
		int index, match = 0;
		for (index = 0; index < listofindividual.size(); index++) {
			individual = listofindividual.get(index);
			if (individual.getName().compareTo(name) == 0) {
				match = 1;
				break;
			}
		}
		if (match == 0) {
			throw new ObjectNotFoundException("individual", "name", name);
		} else
			return individual;
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
       List<Individual> lisofindividual = new ArrayList<>();
		try {
			lisofindividual = (new TeamsJsonReader()).getListOfIndividuals();
		} catch (Exception e) {
			e.printStackTrace();

		}
		List<Individual> listofinactivemember = new ArrayList<>();
		for (int index = 0; index < lisofindividual.size(); index++) {
			Individual individual = lisofindividual.get(index);
			if (!individual.isActive()) {
				listofinactivemember.add(individual);
			}
		}
		return listofinactivemember;
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
        List<Individual> listofindividual = new ArrayList<>();
		try {
			listofindividual = (new TeamsJsonReader()).getListOfIndividuals();
		} catch (Exception e) {
			e.printStackTrace();

		}
		List<Individual> listofactivemember = new ArrayList<>();
		for (int index = 0; index < listofindividual.size(); index++) {
			Individual individual = listofindividual.get(index);
			if (individual.isActive()) {
				listofactivemember.add(individual);
			}
		}
		return listofactivemember;
    }
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
        Object obj = null;
		Map<String, Object> map1 = new HashMap<String, Object>();
		JSONObject jsonobj2 = null;
		List<Team> listofteam = new ArrayList<>();
		TeamsJsonReader reader = new TeamsJsonReader();

		try {

			obj = parser.parse(new FileReader("src/main/resources/db.json"));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray jsonarr = (JSONArray) jsonObject.get("teams");
			for (int index = 0; index < jsonarr.size(); index++) {
				List<Individual> individualList = new ArrayList<>();

				jsonobj2 = (JSONObject) jsonarr.get(index);

				map1.put("name", jsonobj2.get("name"));
				map1.put("id", ((Long) jsonobj2.get("id")).intValue());
				JSONArray memberArray = (JSONArray) jsonobj2.get("members");

				for (int loc = 0; loc < memberArray.size(); loc++) {

					individualList.add(reader.getIndividualById(((Long) memberArray.get(loc)).intValue()));

				}
				map1.put("members", individualList);
				listofteam.add(new Team(map1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listofteam;

    }
}
