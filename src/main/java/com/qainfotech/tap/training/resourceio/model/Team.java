package com.qainfotech.tap.training.resourceio.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qainfotech.tap.training.resourceio.TeamsJsonReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */


/*
working on following class
*/
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members = new ArrayList<Individual>();

	Individual individual = null;
	Map<String, Object> myMap = null;

	public Team(Map<String, Object> teamMap) {
		// throw new UnsupportedOperationException("Not implemented.");

		myMap = new HashMap<String, Object>();

		individual = new Individual(myMap);

		this.name = (String) teamMap.get("name");
		this.id = (Integer) teamMap.get("id");

		// members.add((Individual)teamMap.get("members"));

		// this.members.addAll((Individual)teamMap.get("members"));

		members.add((Individual) teamMap.get("members"));

		// this.active = (Boolean)teamMap.get("members");

	}

	/**
	 * get team name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get team id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return
	 */
	public List<Individual> getMembers() {
		return members;
	}

	/**
	 * get a list of individuals that are members of this team and are also
	 * active
	 * 
	 * @return
	 */
	public List<Individual> getActiveMembers() {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Team> teamList = new ArrayList();

		Team team = null;

		Object obj = null;
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		List<String> list = null;
		JSONObject jsonObject2 = null;
		Map<String, Object> myMap = null;

		List<Individual> members = new ArrayList<Individual>();

		Map<String, Object> myMapInd = null;
		System.out.println("lol");
		Individual individualObject;

		TeamsJsonReader red = new TeamsJsonReader();

		try {
			obj = (new JSONParser()).parse(new FileReader("src/main/resources/db.json"));
			jsonObject = (JSONObject) obj;
			jsonArray = (JSONArray) jsonObject.get("teams");

			list = new ArrayList<String>();

			for (int i = 0; i < jsonArray.size(); i++) {
				jsonObject2 = (JSONObject) jsonArray.get(i);

				System.out.println((String) jsonObject2.get("name") + "   here");

				myMap = new HashMap<String, Object>();

				myMap.put("name", jsonObject2.get("name"));
				myMap.put("id", ((Long) jsonObject2.get("id")).intValue());

				JSONArray memberArray = (JSONArray) jsonObject2.get("members");

				System.out.println(memberArray);

				// Object myObj = memberArray.get(1);
				// System.out.println(myObj);

				// System.out.println((JSONObject)memberArray.get(0)+"here
				// also");

				// System.out.println(myObj.get(0)+"here also");

				for (int j = 0; j < memberArray.size(); j++) {

					Object myObj = memberArray.get(j);

					individualObject = (Individual) red.getIndividualById(((Long) myObj).intValue());

					members = red.getListOfActiveIndividuals();

					myMap.put("members", individualObject);
					// myMap.put("members", jsonObject2.get("members"));

					System.out.println("here also");
					// members.add(individualObject);
				}

				team = new Team(myMap);

				teamList.add(team);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return members;

	}

	/**
	 * get a list of individuals that are members of this team but are inactive
	 * 
	 * @return
	 */
	public List<Individual> getInactiveMembers() {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Team> teamList = new ArrayList();

		Team team = null;

		Object obj = null;
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		List<String> list = null;
		JSONObject jsonObject2 = null;
		Map<String, Object> myMap = null;

		List<Individual> members = new ArrayList<Individual>();

		Map<String, Object> myMapInd = null;
		System.out.println("lol");
		Individual individualObject;

		TeamsJsonReader red = new TeamsJsonReader();

		try {
			obj = (new JSONParser()).parse(new FileReader("src/main/resources/db.json"));
			jsonObject = (JSONObject) obj;
			jsonArray = (JSONArray) jsonObject.get("teams");

			list = new ArrayList<String>();

			for (int i = 0; i < jsonArray.size(); i++) {
				jsonObject2 = (JSONObject) jsonArray.get(i);

				System.out.println((String) jsonObject2.get("name") + "   here");

				myMap = new HashMap<String, Object>();

				myMap.put("name", jsonObject2.get("name"));
				myMap.put("id", ((Long) jsonObject2.get("id")).intValue());

				JSONArray memberArray = (JSONArray) jsonObject2.get("members");

				System.out.println(memberArray);

				// Object myObj = memberArray.get(1);
				// System.out.println(myObj);

				// System.out.println((JSONObject)memberArray.get(0)+"here
				// also");

				// System.out.println(myObj.get(0)+"here also");

				for (int j = 0; j < memberArray.size(); j++) {

					Object myObj = memberArray.get(j);

					individualObject = (Individual) red.getIndividualById(((Long) myObj).intValue());

					members = red.getListOfInactiveIndividuals();

					myMap.put("members", individualObject);
					// myMap.put("members", jsonObject2.get("members"));

					System.out.println("here also");
					// members.add(individualObject);
				}

				team = new Team(myMap);

				teamList.add(team);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return members;
	}
}
