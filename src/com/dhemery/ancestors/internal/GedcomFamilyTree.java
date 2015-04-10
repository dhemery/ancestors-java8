package com.dhemery.ancestors.internal;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.gedcom4j.model.Gedcom;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.parser.GedcomParserException;

import com.dhemery.ancestors.Family;
import com.dhemery.ancestors.FamilyTree;
import com.dhemery.ancestors.Person;

public class GedcomFamilyTree  implements FamilyTree {
	private final Map<String,Person> people = new HashMap<>();
	private final Map<String,Family> families = new HashMap<>();

	public GedcomFamilyTree() {
		Gedcom gedcom = loadGedcom();
		loadPeople(gedcom);
		loadFamilies(gedcom);
	}

	@Override
	public Person dale() {
		return people.get("@I139@");
	}
	
	@Override
	public Collection<Family> families() {
		return families.values();
	}

	@Override
	public Collection<Person> people() {
		return people.values();
	}

	private static Gedcom loadGedcom() {
		GedcomParser parser = new GedcomParser();
		try {
			parser.load("data/ancestors.ged");
		} catch (IOException | GedcomParserException cause) {
			throw new RuntimeException(cause);
		}
		return parser.gedcom;
	}

	private void loadFamilies(Gedcom gedcom) {
		gedcom.families.values().stream()
			.map(family -> new GedcomFamily(family, people))
			.forEach(family -> families.put(family.xref(), family));
	}

	private void loadPeople(Gedcom gedcom) {
		gedcom.individuals.values().stream()
			.map(individual -> new GedcomPerson(individual, families))
			.forEach(person -> people.put(person.xref(), person));
	}
}
