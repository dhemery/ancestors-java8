package com.dhemery.ancestors.internal;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Map;

import org.gedcom4j.model.Individual;

import com.dhemery.ancestors.Family;
import com.dhemery.ancestors.Name;
import com.dhemery.ancestors.Person;

public class GedcomPerson implements Person{
	private final Map<String, Family> families;
	private final String xref;
	private final Collection<String> familiesWhereChild;
	private final Collection<String> familiesWhereSpouse;
	private final Name name;

	public GedcomPerson(Individual individual, Map<String, Family> families) {
		this.families = families;
		xref = individual.xref;
		familiesWhereChild = individual.familiesWhereChild.stream()
			.map(childFamily -> childFamily.family)
			.map(family -> family.xref)
			.collect(toSet());
		familiesWhereSpouse = individual.familiesWhereSpouse.stream()
			.map(spousalFamily -> spousalFamily.family)
			.map(family -> family.xref)
			.collect(toSet());
		name = individual.names.stream()
			.findFirst()
			.map(name -> new GedcomName(name)).get();
	}

	@Override
	public String xref() {
		return xref;
	}
	
	@Override
	public Name name() {
		return name;
	}

	@Override
	public Collection<Family> familiesWhereChild() {
		return familiesWhereChild.stream()
			.map(xref -> families.get(xref))
			.collect(toSet());
	}
	
	@Override
	public Collection<Family> familiesWhereSpouse() {
		return familiesWhereSpouse.stream()
				.map(xref -> families.get(xref))
				.collect(toSet());
	}
	
	@Override
	public String toString() {
		return String.format("Person %s: %s", xref, name());
	}

	@Override
	public int compareTo(Person other) {
		return xref().compareTo(other.xref());
	}
}
