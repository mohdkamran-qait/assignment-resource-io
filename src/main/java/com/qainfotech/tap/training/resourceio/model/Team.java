package com.qainfotech.tap.training.resourceio.model;

import java.util.*;

//import com.qainfotech.tap.training.resourceio.TeamsJsonReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members;

	public Team(Map<String, Object> teamMap) {

		this.name = (String) teamMap.get("name");
		this.id = (Integer) teamMap.get("id");
		List<Individual> list = (List<Individual>) teamMap.get("members");
		this.members = list;
	}

	/**
	 * get team name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * get team id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return members
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

		List<Individual> individualList;
		List<Individual> activeIndividualList = new ArrayList<>();

		individualList = this.getMembers();

		for (Individual individualobject : individualList) {
			if (individualobject.isActive().compareTo(true) == 0)
				activeIndividualList.add(individualobject);
		}

		return activeIndividualList;

	}

	/**
	 * get a list of individuals that are members of this team but are inactive
	 * 
	 * @return inactiveIndividualList
	 */
	public List<Individual> getInactiveMembers() {
		List<Individual> individualList;
		List<Individual> inactiveIndividualList = new ArrayList<>();

		individualList = this.getMembers();

		for (Individual individualobject : individualList) {
			if (individualobject.isActive().compareTo(false) == 0)
				inactiveIndividualList.add(individualobject);

		}

		return inactiveIndividualList;
	}
}
