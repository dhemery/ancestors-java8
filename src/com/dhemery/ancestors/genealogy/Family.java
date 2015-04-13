package com.dhemery.ancestors.genealogy;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public interface Family extends Identifiable {
	Optional<Person> husband();
	Optional<Person> wife();
	Collection<Person> children();

	default Collection<Person> spouses() {
		return Stream.of(husband(), wife())
				.filter(Optional::isPresent)
				.map(Optional::get).collect(toSet());
	}
}
