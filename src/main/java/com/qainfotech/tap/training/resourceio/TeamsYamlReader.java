package com.qainfotech.tap.training.resourceio;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public List<Individual> getListOfIndividuals() throws JsonParseException, JsonMappingException, IOException {
		// throw new UnsupportedOperationException("Not implemented.");

		File file = new File("src/main/resources/db.yaml");
		InputStream yamlFile = new FileInputStream(file);

		try {
			Yaml data = new Yaml();
			Map<String, ArrayList> yamlReader = (Map<String, ArrayList>) data.load(yamlFile);
			ArrayList individualYamlList = (ArrayList) yamlReader.get("individuals");
			Individual individual;
			List<Individual> individualList = new ArrayList();
			Map<String, Object> individualMap = new HashMap<>();

			for (int i = 0; i < individualYamlList.size(); i++) {
				Map<String, ArrayList> individualYamlMap = (Map<String, ArrayList>) individualYamlList.get(i);

				individualMap.put("id", individualYamlMap.get("id"));
				individualMap.put("name", individualYamlMap.get("name"));
				individualMap.put("active", individualYamlMap.get("active"));

				individual = new Individual(individualMap);
				individualList.add(individual);

			}
			return individualList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(".....yaml....."+individual.toString());

		return null;

	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public Individual getIndividualById(Integer id)
			throws ObjectNotFoundException, JsonParseException, JsonMappingException, IOException {
		// throw new UnsupportedOperationException("Not implemented.");

		int i, j = 0;
		TeamsYamlReader reader = new TeamsYamlReader();
		List<Individual> individualList = new ArrayList<>();
		individualList = reader.getListOfIndividuals();
		Individual individual = null;
		for (i = 0; i < individualList.size(); i++) {

			if (individualList.get(i).getId().equals(id)) {
				individual = individualList.get(i);
				j = 1;
				break;
			}
		}

		if (j == 0)
			throw new ObjectNotFoundException("Individual", "id", id.toString());
		else
			return individual;
	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public Individual getIndividualByName(String name)
			throws ObjectNotFoundException, JsonParseException, JsonMappingException, IOException {
		// throw new UnsupportedOperationException("Not implemented.");

		int i, j = 0;
		TeamsYamlReader reader = new TeamsYamlReader();
		List<Individual> individualList = new ArrayList<>();
		individualList = reader.getListOfIndividuals();
		Individual individual = null;
		for (i = 0; i < individualList.size(); i++) {
			if (individualList.get(i).getName().equals(name)) {
				individual = individualList.get(i);
				j = 1;
				break;
			}
		}

		if (j == 0)
			throw new ObjectNotFoundException("Individual", "Name", name.toString());
		else
			return individual;
	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public List<Individual> getListOfInactiveIndividuals()
			throws JsonParseException, JsonMappingException, IOException {

		int i;
		TeamsYamlReader reader = new TeamsYamlReader();
		List<Individual> individualList = new ArrayList<>();

		List<Individual> inactiveIndividualList = new ArrayList<>();
		individualList = reader.getListOfIndividuals();
		for (i = 0; i < individualList.size(); i++) {
			// System.out.println(individualList.get(i).getId().getClass().getName()
			// + " " + name);
			if (individualList.get(i).isActive().compareTo(false) == 0) {
				inactiveIndividualList.add(individualList.get(i));
			}
		}

		return inactiveIndividualList;

	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public List<Individual> getListOfActiveIndividuals() throws JsonParseException, JsonMappingException, IOException {

		int i;
		TeamsYamlReader reader = new TeamsYamlReader();
		List<Individual> individualList = new ArrayList<>();

		List<Individual> activeIndividualList = new ArrayList<>();
		individualList = reader.getListOfIndividuals();
		for (i = 0; i < individualList.size(); i++) {
			// System.out.println(individualList.get(i).getId().getClass().getName()
			// + " " + name);
			if (individualList.get(i).isActive().compareTo(true) == 0) {
				activeIndividualList.add(individualList.get(i));
			}
		}

		return activeIndividualList;

	}

	/**
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<Team> getListOfTeams() throws FileNotFoundException {

		File file = new File("src/main/resources/db.yaml");
		InputStream yamlFile = new FileInputStream(file);
		List<Team> teamList = new ArrayList<>();
		try {

			Yaml data = new Yaml();
			Map<String, ArrayList> yamlReader = (Map<String, ArrayList>) data.load(yamlFile);
			ArrayList teamYamlList = (ArrayList) yamlReader.get("teams");
			Team team;
			Individual individual;
			
			Map<String, Object> teamMap = new HashMap<>();
			TeamsYamlReader reader = new TeamsYamlReader();

			for (int i = 0; i < teamYamlList.size(); i++) {
				List<Individual> individualList = new ArrayList<>();
				
				Map<String, ArrayList> teamYamlMap = (Map<String, ArrayList>) teamYamlList.get(i);

				teamMap.put("name", teamYamlMap.get("name"));
				teamMap.put("id", teamYamlMap.get("id"));

				List memberArray = teamYamlMap.get("members");

				for (int j = 0; j < memberArray.size(); j++) {

					int id= (Integer)memberArray.get(j);
					individualList.add(reader.getIndividualById(id));
					
				}
				teamMap.put("members", individualList);
				teamList.add(new Team(teamMap));

			}
			return teamList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}