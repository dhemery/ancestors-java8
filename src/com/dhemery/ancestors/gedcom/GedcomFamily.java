package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.Family;
import com.dhemery.ancestors.genealogy.Person;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class GedcomFamily implements Family {
	private final Map<Integer, Person> people;
	private final Integer id;
	private final Optional<Integer> husbandID;
	private final Optional<Integer> wifeID;
	private final Set<Integer> childIDs;

	public GedcomFamily(org.gedcom4j.model.Family family, Map<Integer, Family> families, Map<Integer, Person> people) {
		this.people = people;
		id = GedcomID.parse(family.xref);
		husbandID = Optional.ofNullable(family.husband)
						.map(husband -> husband.xref)
						.map(GedcomID::parse);
		wifeID = Optional.ofNullable(family.wife)
						.map(wife -> wife.xref)
						.map(GedcomID::parse);
		childIDs = family.children.stream()
						.map(child -> child.xref)
						.map(GedcomID::parse)
						.collect(toSet());
		families.put(id, this);
	}

	@Override
	public Optional<Person> husband() {
		return husbandID.map(people::get);
	}

	@Override
	public Optional<Person> wife() {
		return wifeID.map(people::get);
	}

	@Override
	public Collection<Person> children() {
		return childIDs.stream()
				.map(people::get)
				.collect(toSet());
	}

	
	@Override
	public String toString() {
		return String.format("Family %d: %s and %s", id,
				husband().map(Person::toString).orElse("UNKNOWN"),
				wife().map(Person::toString).orElse("UNKNOWN")
		);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GedcomFamily that = (GedcomFamily) o;

		return id.equals(that.id);

	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
