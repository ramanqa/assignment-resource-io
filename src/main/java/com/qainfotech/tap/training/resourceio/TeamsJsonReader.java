package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class TeamsJsonReader {

	List<Individual> myObjList = new ArrayList<Individual>();
	List<Individual> myObjList1 = new ArrayList<Individual>();
	List<Individual> myObjList2 = new ArrayList<Individual>();
	List<Individual> myObjList3 = new ArrayList<Individual>();
	List<Individual> myObjList4 = new ArrayList<Individual>();

	public List<Individual> getListOfIndividuals() throws FileNotFoundException, IOException {

		JSONParser parser = new JSONParser();
			myObjList.clear();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
		}

		JSONObject jsonObject = (JSONObject) obj;

		
		JSONArray second = (JSONArray) jsonObject.get("individuals");

		Individual obj1;
		JSONObject myobj;

		for (int i = 0; i < second.size(); i++) {

			myobj = (JSONObject) second.get(i);
		
			
			Map<String, Object> individualMap = new HashMap() ;
	    	individualMap.put("key", myobj.get("name").toString().trim()+"="+
					Integer.parseInt(myobj.get("id").toString().trim())+"="+
					Boolean.parseBoolean(myobj.get("active").toString().trim()));
	    	
	    	System.out.println(myobj.get("name").toString().trim()+"="+
					Integer.parseInt(myobj.get("id").toString().trim())+"="+
					Boolean.parseBoolean(myobj.get("active").toString().trim()));
	    	
			Individual temp = new Individual(individualMap);
			
			obj1 = new Individual(individualMap);

			try {
				myObjList.add(obj1);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return myObjList;

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

		for (int i = 0; i < myObjList.size(); i++) {

			if ((int) myObjList.get(i).getId() == (int) id) {

			
				return myObjList.get(i);

			}

		}
		
		 throw new ObjectNotFoundException(null, null, null);

	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {

		for (int i = 0; i < myObjList.size(); i++) {

			
			if (myObjList.get(i).getName().equals(name)) {

					return myObjList.get(i);

			}

		}
	throw new ObjectNotFoundException(null, null, null)	;
	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfInactiveIndividuals() throws FileNotFoundException, IOException {

		JSONParser parser = new JSONParser();
		myObjList2.clear();

		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
		}

		JSONObject jsonObject = (JSONObject) obj;

		JSONArray second = (JSONArray) jsonObject.get("individuals");

		Individual obj1;
		JSONObject myobj;
		
		for (int i = 0; i < second.size(); i++) {

			myobj = (JSONObject) second.get(i);

			if (Boolean.parseBoolean(myobj.get("active").toString()) == false) {
			
				
				Map<String, Object> individualMap = new HashMap() ;
		    	individualMap.put("key", myobj.get("name").toString().trim()+"="+
						Integer.parseInt(myobj.get("id").toString().trim())+"="+
						Boolean.parseBoolean(myobj.get("active").toString().trim()));
		    	
		    	System.out.println(myobj.get("name").toString().trim()+"="+
						Integer.parseInt(myobj.get("id").toString().trim())+"="+
						Boolean.parseBoolean(myobj.get("active").toString().trim()));
		    	
				Individual temp = new Individual(individualMap);
				
				obj1 = new Individual(individualMap);

				myObjList2.add(obj1);
			}
		}

		
		return myObjList2;

	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfActiveIndividuals() throws FileNotFoundException, IOException {

		JSONParser parser = new JSONParser();
		myObjList3.clear();

		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
		}

		JSONObject jsonObject = (JSONObject) obj;

		JSONArray second = (JSONArray) jsonObject.get("individuals");

		Individual obj1;
		JSONObject myobj;

		for (int i = 0; i < second.size(); i++) {

			myobj = (JSONObject) second.get(i);

			if (Boolean.parseBoolean(myobj.get("active").toString()) == true) {
			
				Map<String, Object> individualMap = new HashMap() ;
		    	individualMap.put("key", myobj.get("name").toString().trim()+"="+
						Integer.parseInt(myobj.get("id").toString().trim())+"="+
						Boolean.parseBoolean(myobj.get("active").toString().trim()));
				
		    	
		    	
		    	obj1 = new Individual(individualMap);
				myObjList3.add(obj1);
			}
		}

		return myObjList3;

	}

	List<Team> myObjList5 = new ArrayList<Team>();

	/**
	 * get a list of team objects from db json
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Team> getListOfTeams() throws FileNotFoundException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		
		myObjList5.clear();
		
		try {
			obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
		} catch (org.json.simple.parser.ParseException e) {
		}

		if(myObjList.isEmpty())
		{
			myObjList4=this.getListOfIndividuals();
			myObjList=myObjList4;
		}
		
		JSONObject jsonObject = (JSONObject) obj;

		JSONArray second = (JSONArray) jsonObject.get("teams");

		Team obj1;
		JSONObject myobj;

		for (int i = 0; i < second.size(); i++) {

			myobj = (JSONObject) second.get(i);

			
			Map<String, Object> individualMap = new HashMap() ;
	    	individualMap.put("key", myobj.get("name").toString().trim()+"="+
					Integer.parseInt(myobj.get("id").toString().trim()));
	    	
	    	individualMap.put("memberobject", myObjList);
	    	
//	    	System.out.println(myobj.get("name").toString().trim()+"="+
	//				Integer.parseInt(myobj.get("id").toString().trim()));
	    	
	    	
	    	
	    	System.out.println(individualMap.get("memberobject"));
			Team temp = new Team(individualMap);
			myObjList5.add(temp);

		}

		return myObjList5;

	}

	public static void main(String... S) throws IOException, ObjectNotFoundException {

		TeamsJsonReader obj = new TeamsJsonReader();
		System.out.println("here"+obj.getListOfTeams());		

	}

}
