package com.dhemery.ancestors;

import java.util.Collection;
import java.util.Optional;

public interface Family extends Comparable<Family> {
	String xref();
	Optional<Person> husband();
	Optional<Person> wife();
	Collection<Person> children();
}