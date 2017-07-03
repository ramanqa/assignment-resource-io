package com.qainfotech.tap.training.resourceio;

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

import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader {

	/**
	 * get a list of individual objects from db yaml file
	 * 
	 * @return
	 */
	public List<Individual> getListOfIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("db.yaml").getFile());

		InputStream is = null;
		List<Individual> individualList = new ArrayList<>();

		Individual individualObject = null;
		try {
			is = new FileInputStream(file);

			Yaml yaml = new Yaml();
			@SuppressWarnings("unchecked")
			Map<String, ArrayList> yamlParsers = (Map<String, ArrayList>) yaml.load(is);

			ArrayList indi = (ArrayList) yamlParsers.get("individuals");

			for (int i = 0; i < indi.size(); i++) {
				Map getValue = (Map<String, ArrayList>) indi.get(i);
				individualObject = new Individual(getValue );
				individualList.add(individualObject);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		individualList = (new TeamsYamlReader()).getListOfIndividuals();
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
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> individualList = new ArrayList<>();
		individualList = (new TeamsYamlReader()).getListOfIndividuals();
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
	public List<Individual> getListOfInactiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> individualList = new ArrayList<>();
		List<Individual> inactiveIndividualList = new ArrayList<>();
		individualList = (new TeamsYamlReader()).getListOfIndividuals();
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
	public List<Individual> getListOfActiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> individualList = new ArrayList<>();
		List<Individual> activeIndividualList = new ArrayList<>();
		individualList = (new TeamsYamlReader()).getListOfIndividuals();
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
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 * @throws ObjectNotFoundException
	 */
	public List<Team> getListOfTeams() {
		// throw new UnsupportedOperationException("Not implemented.");
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("db.yaml").getFile());

		Map<String, Object> myMap = new HashMap<String, Object>();
		InputStream is = null;
		List<Team> teamList = new ArrayList<>();

		TeamsYamlReader reader = new TeamsYamlReader();
		Team teamObject = null;
		try {
			is = new FileInputStream(file);

			Yaml yaml = new Yaml();
			@SuppressWarnings("unchecked")
			Map<String, ArrayList> yamlParsers = (Map<String, ArrayList>) yaml.load(is);

			ArrayList indi = (ArrayList) yamlParsers.get("teams");

			for (int i = 0; i < indi.size(); i++) {
				List<Individual> individualList = new ArrayList<>();
				Map room = (Map<String, ArrayList>) indi.get(i);

				myMap.put("name", room.get("name"));
				myMap.put("id", room.get("id"));

				ArrayList members = (ArrayList) room.get("members");
				for (int index = 0; index < members.size(); index++) {

					try {
						individualList.add(reader.getIndividualById((Integer) (members.get(index))));
					} catch (ObjectNotFoundException e) {

						e.printStackTrace();
					}
				}
				myMap.put("members", individualList);
				teamList.add(new Team(myMap));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return teamList;
	}
}
