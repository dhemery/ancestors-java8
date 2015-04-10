package com.dhemery.ancestors.internal;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.dhemery.ancestors.Family;
import com.dhemery.ancestors.Person;

public class GedcomFamily implements Family {
	private final Map<String, Person> people;
	private final String xref;
	private final Optional<String> husbandXref;
	private final Optional<String> wifeXref;
	private final Set<String> childXrefs;

	public GedcomFamily(org.gedcom4j.model.Family family, Map<String, Person> people) {
		this.people = people;
		xref = family.xref;
		husbandXref = Optional.ofNullable(family.husband).map(husband -> husband.xref);
		wifeXref = Optional.ofNullable(family.wife).map(wife -> wife.xref);
		childXrefs = family.children.stream().map(child -> child.xref).collect(toSet());
	}

	@Override
	public String xref() {
		return xref;
	}

	@Override
	public Optional<Person> husband() {
		return husbandXref.map(xref -> people.get(xref));
	}

	@Override
	public Optional<Person> wife() {
		return wifeXref.map(xref -> people.get(xref));
	}

	@Override
	public Collection<Person> children() {
		return childXrefs.stream()
				.map(xref -> people.get(xref))
				.collect(toSet());
	}

	
	@Override
	public String toString() {
		return String.format("Family %s: %s, %s", xref, husband(), wife());
	}

	@Override
	public int compareTo(Family other) {
		return xref().compareTo(other.xref());
	}
}
