package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.Family;
import com.dhemery.ancestors.genealogy.Name;
import com.dhemery.ancestors.genealogy.Person;
import com.dhemery.ancestors.genealogy.Sex;
import org.gedcom4j.model.Individual;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

public class GedcomPerson implements Person {
	private final Map<Integer, Family> families;
	private final int id;
	private final Optional<Integer> familyOfOrigin;
	private final Collection<Integer> familiesWhereSpouse;
	private final Name name;
    private final Optional<Sex> sex;

    public GedcomPerson(Individual individual, Map<Integer, Person> people, Map<Integer, Family> families) {
		this.families = families;
		id = GedcomID.parse(individual.xref);
		name = individual.names.stream()
				.findFirst()
				.map(GedcomName::new).get();
		familyOfOrigin = individual.familiesWhereChild.stream()
				.findFirst()
				.map(childFamily -> childFamily.family)
				.map(family -> family.xref)
				.map(GedcomID::parse);
		familiesWhereSpouse = individual.familiesWhereSpouse.stream()
				.map(spousalFamily -> spousalFamily.family)
				.map(family -> family.xref)
				.map(GedcomID::parse)
				.collect(toSet());
		sex = Optional.ofNullable(individual.sex).map(Object::toString).flatMap(Sex::withInitial);
		people.put(id, this);
	}

	@Override
	public Name name() {
		return name;
	}

    @Override
    public Optional<Sex> sex() {
        return sex;
    }

    @Override
	public Optional<Family> familyOfOrigin() {
		return familyOfOrigin.map(families::get);
	}

	@Override
	public Collection<Family> familiesWhereSpouse() {
		return familiesWhereSpouse.stream()
				.map(families::get)
				.collect(toSet());
	}
	
	@Override
	public String toString() {
		return String.format("%s (%d)", name(), id);
	}
}
