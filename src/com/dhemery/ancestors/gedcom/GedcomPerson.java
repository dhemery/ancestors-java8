package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.*;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEventType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

public class GedcomPerson implements Person {
	private final Map<Integer, Family> families;
	private final int id;
	private final Optional<Integer> familyOfOrigin;
	private final Collection<Integer> familiesWhereSpouse;
	private final Name name;
    private final Optional<Sex> sex;
	private final Optional<Event> birth;
	private final Optional<Event> death;

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
		birth = individual.events.stream()
				.filter(e -> e.type == IndividualEventType.BIRTH)
				.findFirst()
				.map(GedcomEvent::new);
		death = individual.events.stream()
				.filter(e -> e.type == IndividualEventType.DEATH)
				.findFirst()
				.map(GedcomEvent::new);
		people.put(id, this);
	}

	@Override  public int id() { return id; }

	@Override  public Name name() { return name; }
	@Override  public Optional<Event> birth() { return birth; }
	@Override  public Optional<Event> death() { return death; }

    @Override  public Optional<Sex> sex() { return sex; }

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GedcomPerson that = (GedcomPerson) o;

		return id == that.id;

	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return format("%s (%s)", name, id);
	}
}
