package com.dhemery.ancestors;

import java.util.Optional;

public interface Name extends Comparable<Name> {
	Optional<String> prefix();
	String basic();
	Optional<String> suffix();
}
