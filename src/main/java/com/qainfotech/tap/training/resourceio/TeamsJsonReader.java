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
    
    
    
	List<Individual> myObjList = new ArrayList<Individual>();
	List<Individual> myObjList1 = new ArrayList<Individual>();
	List<Individual> myObjList2 = new ArrayList<Individual>();
	List<Individual> myObjList3 = new ArrayList<Individual>();
	List<Individual> myObjList4 = new ArrayList<Individual>();

    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     */
    
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
		
			obj1 = new Individual(myobj.get("name").toString().trim(),
					Integer.parseInt(myobj.get("id").toString().trim()),
					Boolean.parseBoolean(myobj.get("active").toString().trim()));

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
		
		myObjList2.clear();
		JSONParser parser = new JSONParser();

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
				obj1 = new Individual(myobj.get("name").toString(), Integer.parseInt(myobj.get("id").toString()),
						Boolean.parseBoolean(myobj.get("active").toString()));

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
				obj1 = new Individual(myobj.get("name").toString(), Integer.parseInt(myobj.get("id").toString()),
						Boolean.parseBoolean(myobj.get("active").toString()));

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
		myObjList5.clear();
		JSONObject obj = null;
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

			obj1 = new Team(myobj.get("name").toString(), Integer.parseInt(myobj.get("id").toString()), myObjList);

			myObjList5.add(obj1);

		}

		return myObjList5;

	}

	public static void main(String... S) throws IOException, ObjectNotFoundException {

		TeamsJsonReader obj = new TeamsJsonReader();
		System.out.println(obj.getListOfTeams().size());

	}

}
