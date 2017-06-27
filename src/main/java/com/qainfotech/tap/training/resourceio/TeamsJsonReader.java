package com.qainfotech.tap.training.resourceio;

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
			obj = parser.parse(new FileReader("D:\\db.json"));
			jsonObject = (JSONObject) obj;

			jsonArray = (JSONArray) jsonObject.get("individuals");

			int length = jsonArray.size();
			for (int i = 0; i < length; i++) {

				jsonObj = (JSONObject) jsonArray.get(i);
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

		int i, j = 0;
		for (i = 0; i < individualList.size(); i++) {
			j = 0;

			individual = individualList.get(i);

			System.out.println(individual.getId() + " " + id);

			if (individual.getId().compareTo(id) == 0) {
				j = 1;

				break;
			}
		}

		if (i == individualList.size() && j == 0)
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

		int i, j = 0;
		for (i = 0; i < individualList.size(); i++) {
			j = 0;

			individual = individualList.get(i);

			if (individual.getName().equals(name)) {
				j = 1;

				break;
			}
		}

		if (i == individualList.size() && j == 0)
			throw new ObjectNotFoundException("individual", "name", name);

		return individual;

	}

    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
        throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
        throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
        throw new UnsupportedOperationException("Not implemented.");
    }
}
