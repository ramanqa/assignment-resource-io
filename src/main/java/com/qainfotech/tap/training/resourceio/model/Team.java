package com.qainfotech.tap.training.resourceio.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members;

	public Team(Map<String, Object> teamMap) {
		throw new UnsupportedOperationException("Not implemented.");
	}

	public Team(String aa, Integer bb, List<Individual> x) {
		name = aa;
		id = bb;
		members = x;

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

	
	List<Individual> membersInTeam = new ArrayList<Individual>();
	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public List<Individual> getMembers() throws FileNotFoundException, IOException 
	{
	
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
			 e.printStackTrace();
		}

		JSONObject jsonObject = (JSONObject) obj;

		JSONArray second = (JSONArray) jsonObject.get("teams");


		JSONObject myobj;
		JSONArray temp;

		for (int i = 0; i < second.size(); i++) {

			myobj = (JSONObject) second.get(i);

			if ((int) Integer.parseInt(myobj.get("id").toString()) == (int) this.id) {
				temp = (JSONArray) myobj.get("members");
				for (int y = 0; y < temp.size(); y++) {
					for (int q = 0; q < members.size(); q++) {

	
						if ((int)( Integer.parseInt(temp.get(y)+"")) == (int) (members.get(q).getId())) {

						membersInTeam.add(members.get(q));
						
						}

					}

				}

			}

		}

		return membersInTeam;

		
		
	}

	List<Individual> myActiveTeam = new ArrayList<Individual>();
	List<Individual> mynotActiveTeam = new ArrayList<Individual>();

	/**
	 * get a list of individuals that are members of this team and are also
	 * active
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getActiveMembers() throws FileNotFoundException, IOException {

		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
			 e.printStackTrace();
		}

		JSONObject jsonObject = (JSONObject) obj;

		JSONArray second = (JSONArray) jsonObject.get("teams");


		JSONObject myobj;
		JSONArray temp;

		for (int i = 0; i < second.size(); i++) {

			myobj = (JSONObject) second.get(i);

			if ((int) Integer.parseInt(myobj.get("id").toString()) == (int) this.id) {
				temp = (JSONArray) myobj.get("members");
				for (int y = 0; y < temp.size(); y++) {
					for (int q = 0; q < members.size(); q++) {

	
						if (members.get(q).isActive() && (int)( Integer.parseInt(temp.get(y)+"")) == (int) (members.get(q).getId())) {

							myActiveTeam.add(members.get(q));
						
						}

					}

				}

			}

		}

		return myActiveTeam;


	}

	/**
	 * get a list of individuals that are members of this team but are inactive
	 * 
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public List<Individual> getInactiveMembers() throws FileNotFoundException, IOException {

		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
		}

		JSONObject jsonObject = (JSONObject) obj;

		JSONArray second = (JSONArray) jsonObject.get("teams");


		JSONObject myobj;
		JSONArray temp;


		for (int i = 0; i < second.size(); i++) {

			myobj = (JSONObject) second.get(i);


			if ((int) Integer.parseInt(myobj.get("id").toString()) == (int) this.id) {
				temp = (JSONArray) myobj.get("members");

				for (int y = 0; y < temp.size(); y++) {

					for (int q = 0; q < members.size(); q++) {

	
						if (!members.get(q).isActive() && (int)( Integer.parseInt(temp.get(y)+"")) == (int) (members.get(q).getId())) {

						mynotActiveTeam.add(members.get(q));
						
						}

					}

				}

			}

		}

		return mynotActiveTeam;

	}
	
	
}
