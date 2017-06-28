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
		
	  	
	    Map<String, Object> myMap = teamMap;
	    	
	    name = myMap.get("name").toString();
	    id =Integer.parseInt(myMap.get("id").toString());
	    members = (List<Individual>) teamMap.get("memberobject");
	    
		
		//throw new UnsupportedOperationException("Not implemented.");
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
	JSONParser parser = new JSONParser();
	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public List<Individual> getMembers() throws FileNotFoundException, IOException 
	{
	
		
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
			 e.printStackTrace();
		}

		JSONArray jsonTeamarray = (JSONArray) jsonObject.get("teams");


		JSONObject jsonInnerObject;
		JSONArray jsonMemberArray;

		for (int index = 0; index < jsonTeamarray.size(); index++) {

			jsonInnerObject = (JSONObject) jsonTeamarray.get(index);

			if ((int) Integer.parseInt(jsonInnerObject.get("id").toString()) == (int) this.id) {
				jsonMemberArray = (JSONArray) jsonInnerObject.get("members");
				for (int index2 = 0; index2 < jsonMemberArray.size(); index2++) {
					out:for (int index3 = 0; index3 < members.size(); index3++) {

	
						if ((int)( Integer.parseInt(jsonMemberArray.get(index2).toString())) == (int) (members.get(index3).getId())) {
                                                {
						membersInTeam.add(members.get(index3));
						break out;
						}	
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

		
		JSONObject mainJsonobj = null;
		try {
			mainJsonobj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
			 e.printStackTrace();
		}



		JSONArray teamJsonarray = (JSONArray) mainJsonobj.get("teams");


		JSONObject innerJsonobj;
		JSONArray innerJsonArray;

		for (int i = 0; i < teamJsonarray.size(); i++) {

			innerJsonobj = (JSONObject) teamJsonarray.get(i);

			if ((int) Integer.parseInt(innerJsonobj.get("id").toString()) == (int) this.id) {
				innerJsonArray = (JSONArray) innerJsonobj.get("members");
				for (int y = 0; y < innerJsonArray.size(); y++) {
					out:for (int q = 0; q < members.size(); q++) {

	
						if (members.get(q).isActive() && (int)( Integer.parseInt(innerJsonArray.get(y)+"")) == (int) (members.get(q).getId())) {
							{
							myActiveTeam.add(members.get(q));
							break out;
							}
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

				JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
		}

	
		JSONArray innerJsonArray = (JSONArray) jsonObject.get("teams");


		JSONObject innerObj;
		JSONArray tempJsonArray;


		for (int index = 0; index < innerJsonArray.size(); index++) {

			innerObj = (JSONObject) innerJsonArray.get(index);


			if ((int) Integer.parseInt(innerObj.get("id").toString()) == (int) this.id) {
				tempJsonArray = (JSONArray) innerObj.get("members");

				for (int arrayindex = 0; arrayindex < tempJsonArray.size(); arrayindex++) {

					out:for (int innerarray = 0; innerarray < members.size(); innerarray++) {

	
						if (!members.get(innerarray).isActive() && (int)( Integer.parseInt(tempJsonArray.get(arrayindex)+"")) == (int) (members.get(innerarray).getId())) {
						{
						mynotActiveTeam.add(members.get(innerarray));
						break out;
						}
						}

					}

				}

			}

		}

		return mynotActiveTeam;

	}
	
	
}
