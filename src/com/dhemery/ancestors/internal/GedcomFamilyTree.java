package com.dhemery.ancestors.internal;

import java.io.BufferedInputStream;
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
	private final Map<Integer,Person> peopleByID = new HashMap<>();
	private final Map<Integer,Family> familiesByID = new HashMap<>();

	public GedcomFamilyTree() {
		Gedcom gedcom = loadGedcom();
		loadPeople(gedcom);
		loadFamilies(gedcom);
	}

	@Override
	public Person dale() {
		return peopleByID.get("139");
	}

	@Override
	public Collection<Family> families() {
		return familiesByID.values();
	}

	@Override
	public Collection<Person> people() {
		return peopleByID.values();
	}

	private Gedcom loadGedcom() {
		GedcomParser parser = new GedcomParser();
		try {
            parser.load(gedcomStream());
		} catch (IOException | GedcomParserException cause) {
			throw new RuntimeException(cause);
		}
		return parser.gedcom;
	}

    private BufferedInputStream gedcomStream() {
        return new BufferedInputStream(getClass().getResourceAsStream("/ancestors.ged"));
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
