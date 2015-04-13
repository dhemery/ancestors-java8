package com.dhemery.ancestors.genealogy;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public interface Name extends Comparable<Name> {
	Optional<String> prefix();
	List<String> given();
	Optional<String> surname();
	Optional<String> suffix();

	default String simple() {
		StringJoiner joiner = new StringJoiner(" ");
		given().forEach(joiner::add);
		surname().ifPresent(joiner::add);
		return joiner.toString();
	}

	default String full() {
		StringJoiner joiner = new StringJoiner(" ");
		prefix().ifPresent(joiner::add);
		joiner.add(simple());
		suffix().ifPresent(joiner::add);
		return joiner.toString();
	}

	@Override
	default int compareTo(Name other) {
		return this.toString().compareTo(other.toString());
	}
}
