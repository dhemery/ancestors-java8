package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.Family;
import com.dhemery.ancestors.genealogy.FamilyTree;
import com.dhemery.ancestors.genealogy.Person;
import org.gedcom4j.model.Gedcom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GedcomFamilyTree  implements FamilyTree {
	public static final int DALE_ID = 139;
	private final Map<Integer,Person> peopleByID = new HashMap<>();
	private final Map<Integer,Family> familiesByID = new HashMap<>();

	public GedcomFamilyTree(Gedcom gedcom) {
		loadPeople(gedcom);
		loadFamilies(gedcom);
	}

	@Override
	public Person dale() {
		return peopleByID.get(DALE_ID);
	}

	@Override
	public Collection<Family> families() {
		return familiesByID.values();
	}

	@Override
	public Collection<Person> people() {
		return peopleByID.values();
	}

    private void loadFamilies(Gedcom gedcom) {
		gedcom.families.values()
			.forEach(family -> new GedcomFamily(family, familiesByID, peopleByID));
	}

	private void loadPeople(Gedcom gedcom) {
		gedcom.individuals.values()
			.forEach(individual -> new GedcomPerson(individual, peopleByID, familiesByID));
	}
}
