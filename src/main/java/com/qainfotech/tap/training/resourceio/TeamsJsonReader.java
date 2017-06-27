package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Check_individual;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qainfotech.tap.training.resourceio.model.Individual;;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader {

	/**
	 * get a list of individual objects from db json file
	 * 
	 * @return
	 */

	List<Individual> individualList;

	public List<Individual> getListOfIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Individual> myList = null;

		Individual individualObject = null;

		Map<String, Object> myMap = null;

		Object obj = null;
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		List<String> list = null;
		JSONObject jsonObject2 = null;

		individualList = new ArrayList<>();

		String name;
		int id;
		boolean active;

		// System.out.println("here also 1");

		try {

			obj = (new JSONParser()).parse(new FileReader("src/main/resources/db.json"));
			jsonObject = (JSONObject) obj;
			jsonArray = (JSONArray) jsonObject.get("individuals");

			list = new ArrayList<String>();

			for (int i = 0; i < jsonArray.size(); i++) {
				System.out.println("here,,," + jsonArray.get(i));
				// list.add(jsonArray.getJSONObject(i).getString("name"));
				jsonObject2 = (JSONObject) jsonArray.get(i);

				System.out.println((String) jsonObject2.get("name"));

				myMap = new HashMap<String, Object>();

				myMap.put("name", jsonObject2.get("name"));

				myMap.put("id", ((Long) jsonObject2.get("id")).intValue());
				myMap.put("active", jsonObject2.get("active"));

				individualObject = new Individual(myMap);

				individualList.add(individualObject);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return individualList;

	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Individual> individualList = new ArrayList<>();

		List<Individual> ls = new ArrayList<>();
		TeamsJsonReader my = new TeamsJsonReader();

		individualList = (new TeamsJsonReader()).getListOfIndividuals();

		Map<String, Object> myMap = null;

		Individual individual = null;

		// System.out.println(individualList.size());

		int i, j = 0;
		for (i = 0; i < individualList.size(); i++) {
			j = 0;

			individual = individualList.get(i);

			System.out.println(individual.getId() + " " + id);

			// id=((Long)individual.getId().intValue());

			if (individual.getId().compareTo(id) == 0) {
				j = 1;
				System.out.println(individual.getId() + "found...");

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

	public Individual getIndividualByName(String name) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Individual> individualList = new ArrayList<>();

		List<Individual> ls = new ArrayList<>();
		TeamsJsonReader my = new TeamsJsonReader();

		individualList = (new TeamsJsonReader()).getListOfIndividuals();

		Map<String, Object> myMap = null;

		Individual individual = null;

		// System.out.println(individualList.size());

		int i, j = 0;
		for (i = 0; i < individualList.size(); i++) {
			j = 0;

			individual = individualList.get(i);

			// System.out.println(individual.getId()+" "+id);

			// id=((Long)individual.getId().intValue());

			if (individual.getName().equals(name)) {
				j = 1;
				System.out.println(individual.getName() + "found...");

				break;
			}
		}

		if (i == individualList.size() && j == 0)
			throw new ObjectNotFoundException("individual", "name", name);

		return individual;

	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Individual> individualList = new ArrayList<>();

		List<Individual> inactiveIndividualList = new ArrayList<>();

		List<Individual> ls = new ArrayList<>();
		TeamsJsonReader my = new TeamsJsonReader();

		individualList = (new TeamsJsonReader()).getListOfIndividuals();

		Map<String, Object> myMap = null;

		Individual individual = null;

	
		int i, j = 0;
		for (i = 0; i < individualList.size(); i++) {
			j = 0;

			individual = individualList.get(i);

			if (!individual.isActive()) {
				j = 1;
		
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
	public List<Individual> getListOfActiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Individual> individualList = new ArrayList<>();

		List<Individual> inactiveIndividualList = new ArrayList<>();

		List<Individual> ls = new ArrayList<>();
		TeamsJsonReader my = new TeamsJsonReader();

		individualList = (new TeamsJsonReader()).getListOfIndividuals();

		Map<String, Object> myMap = null;

		Individual individual = null;

		// System.out.println(individualList.size());

		int i, j = 0;
		for (i = 0; i < individualList.size(); i++) {
			j = 0;

			individual = individualList.get(i);

			if (individual.isActive()) {
				j = 1;
			//	System.out.println(individual.getName() + "found...");

				inactiveIndividualList.add(individual);

			}
		}

		return inactiveIndividualList;

	}

	/**
	 * get a list of team objects from db json
	 * 
	 * @return
	 */
    
    
    /**
     * working on following method 
     *
     *    
     */
	public List<Team> getListOfTeams() {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Team> teamList = new ArrayList();

		Team team = null;

		Object obj = null;
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		List<String> list = null;
		JSONObject jsonObject2 = null;
		Map<String, Object> myMap = null;

		try {

			obj = (new JSONParser()).parse(new FileReader("src/main/resources/db.json"));
			jsonObject = (JSONObject) obj;
			jsonArray = (JSONArray) jsonObject.get("teams");

			list = new ArrayList<String>();

			for (int i = 0; i < jsonArray.size(); i++) {
			//	System.out.println("here,,," + jsonArray.get(i));
				// list.add(jsonArray.getJSONObject(i).getString("name"));
				jsonObject2 = (JSONObject) jsonArray.get(i);

				System.out.println((String) jsonObject2.get("name"));

				myMap = new HashMap<String, Object>();

				myMap.put("name", jsonObject2.get("name"));
				myMap.put("id", jsonObject2.get("id"));
				myMap.put("active", jsonObject2.get("active"));

				// individualObject = new Individual(myMap);

				// individualList.add(individualObject);

			}

			for (String s : list) {
				System.out.println("here also" + s);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return teamList;

	}
}
