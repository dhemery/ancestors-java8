package com.dhemery.ancestors;

import java.util.Optional;

public interface Name extends Comparable<Name> {
	String full();
	Optional<String> prefix();
	Optional<String> suffix();
}
