package com.dhemery.ancestors.genealogy;

import java.util.Optional;

public interface Name extends Comparable<Name> {
	String simple();
	Optional<String> prefix();
	Optional<String> suffix();
}
