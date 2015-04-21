package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.Family;
import com.dhemery.ancestors.genealogy.Person;
import org.gedcom4j.model.FamilyEventType;

import java.util.*;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

public class GedcomFamily implements Family {
	private final int id;
	private final Map<Integer, Person> people;
	private final Optional<Integer> husbandID;
	private final Optional<Integer> wifeID;
	private final Set<Integer> childIDs;
	private final Optional<Object> marriage;

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
		marriage = family.events.stream()
				.filter(e -> e.type == FamilyEventType.MARRIAGE)
				.findFirst()
				.map(GedcomEvent::new);
		families.put(id, this);
	}

	@Override
	public int id() {
		return id;
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
		String husband = husband().map(Person::name).map(Object::toString).orElse("UNKNOWN");
		String wife = wife().map(Person::name).map(Object::toString).orElse("UNKNOWN");
		StringJoiner out = new StringJoiner(" ")
				.add(format("%s and %s", husband, wife))
				.add(format("(F%d)", id));
		marriage.map(Object::toString).ifPresent(out::add);
		return out.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GedcomFamily that = (GedcomFamily) o;

		return id == that.id;

	}

	@Override
	public int hashCode() {
		return id;
	}
}

