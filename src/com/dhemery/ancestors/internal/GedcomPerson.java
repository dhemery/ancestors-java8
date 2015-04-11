package com.dhemery.ancestors.internal;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Map;

import org.gedcom4j.model.Individual;

import com.dhemery.ancestors.Family;
import com.dhemery.ancestors.Name;
import com.dhemery.ancestors.Person;

public class GedcomPerson implements Person {
	private final Map<Integer, Family> families;
	private final int id;
	private final Collection<Integer> familiesWhereChild;
	private final Collection<Integer> familiesWhereSpouse;
	private final Name name;

	public GedcomPerson(Individual individual, Map<Integer, Person> people, Map<Integer, Family> families) {
		this.families = families;
		id = GedcomID.parse(individual.xref);
		familiesWhereChild = individual.familiesWhereChild.stream()
			.map(childFamily -> childFamily.family)
			.map(family -> family.xref)
			.map(GedcomID::parse)
			.collect(toSet());
		familiesWhereSpouse = individual.familiesWhereSpouse.stream()
			.map(spousalFamily -> spousalFamily.family)
			.map(family -> family.xref)
			.map(GedcomID::parse)
			.collect(toSet());
		name = individual.names.stream()
			.findFirst()
			.map(GedcomName::new).get();
		people.put(id, this);
	}

	@Override
	public Name name() {
		return name;
	}

	@Override
	public Collection<Family> familiesWhereChild() {
		return familiesWhereChild.stream()
			.map(families::get)
			.collect(toSet());
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

	@Override
	public int compareTo(Person o) {
		GedcomPerson other = (GedcomPerson) o;
		return Integer.compare(id, other.id);
	}
}
