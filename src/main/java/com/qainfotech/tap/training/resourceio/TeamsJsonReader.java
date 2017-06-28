package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
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

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader {

	/**
	 * get a list of individual objects from db json file
	 * 
	 * @return individualList
	 */
	public List<Individual> getListOfIndividuals() {

		try {
			List<Individual> individualList = new ArrayList<>();
			JSONObject jsonFile = (JSONObject) (new JSONParser()).parse(new FileReader("src/main/resources/db.json"));
			JSONArray individualArray = new JSONArray();
			individualArray = (JSONArray) jsonFile.get("individuals");
			Map<String, Object> individualMap = new HashMap<>();

			for (int index = 0; index < individualArray.size(); index++) {

				JSONObject object = (JSONObject) individualArray.get(index);
				individualMap.put("name", (Object) object.get("name"));
				individualMap.put("id", (Object) ((Long) object.get("id")).intValue());
				individualMap.put("active", (Object) object.get("active"));

				individualList.add(new Individual(individualMap));

			}
			return individualList;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {

		int index, temp = 0;
		TeamsJsonReader reader = new TeamsJsonReader();
		List<Individual> individualList = new ArrayList<>();
		individualList = reader.getListOfIndividuals();
		Individual individual = null;
		for (index = 0; index < individualList.size(); index++) {

			if (individualList.get(index).getId().equals(id)) {
				individual = individualList.get(index);
				temp = 1;
				break;
			}
		}

		if (temp == 0)
			throw new ObjectNotFoundException("individual", "id", id.toString());
		else
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

		int index, temp = 0;
		TeamsJsonReader reader = new TeamsJsonReader();
		List<Individual> individualList = new ArrayList<>();
		individualList = reader.getListOfIndividuals();
		Individual individual = null;
		for (index = 0; index < individualList.size(); index++) {
			if (individualList.get(index).getName().equals(name)) {
				individual = individualList.get(index);
				temp = 1;
				break;
			}
		}

		if (temp == 0)
			throw new ObjectNotFoundException("individual", "id", name.toString());
		else
			return individual;
	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return inactiveIndividualList
	 */
	public List<Individual> getListOfInactiveIndividuals() {

		int index;
		TeamsJsonReader reader = new TeamsJsonReader();
		List<Individual> individualList = new ArrayList<>();

		List<Individual> inactiveIndividualList = new ArrayList<>();
		individualList = reader.getListOfIndividuals();
		for (index = 0; index < individualList.size(); index++) {
			if (individualList.get(index).isActive().compareTo(false) == 0) {
				inactiveIndividualList.add(individualList.get(index));
			}
		}

		return inactiveIndividualList;

	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return activeIndividualList
	 */
	public List<Individual> getListOfActiveIndividuals() {

		int index;
		TeamsJsonReader reader = new TeamsJsonReader();
		List<Individual> individualList = new ArrayList<>();

		List<Individual> activeIndividualList = new ArrayList<>();
		individualList = reader.getListOfIndividuals();
		for (index = 0; index < individualList.size(); index++) {
			if (individualList.get(index).isActive().compareTo(true) == 0) {
				activeIndividualList.add(individualList.get(index));
			}
		}

		return activeIndividualList;

	}

	/**
	 * get a list of team objects from db json
	 * 
	 * @return
	 */
	public List<Team> getListOfTeams() {

		Map<String, Object> teamMap = new HashMap<>();
		try {
			JSONObject jsonFile = (JSONObject) (new JSONParser()).parse(new FileReader("src/main/resources/db.json"));
			JSONArray teamArray = new JSONArray();
			teamArray = (JSONArray) jsonFile.get("teams");
			List<Team> teamList = new ArrayList<>();
			TeamsJsonReader reader = new TeamsJsonReader();
			int index, index2;
			for (index = 0; index < teamArray.size(); index++) {

				List<Individual> individualList = new ArrayList<>();

				JSONObject singleObject = (JSONObject) teamArray.get(index);
				teamMap.put("name", (Object) singleObject.get("name"));
				teamMap.put("id", (Object) ((Long) singleObject.get("id")).intValue());

				JSONArray memberArray = (JSONArray) singleObject.get("members");

				for (index2 = 0; index2 < memberArray.size(); index2++) {
					individualList.add(reader.getIndividualById(((Long) memberArray.get(index2)).intValue()));

				}

				teamMap.put("members", individualList);
				teamList.add(new Team(teamMap));

			}

			return teamList;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;

	}
}
