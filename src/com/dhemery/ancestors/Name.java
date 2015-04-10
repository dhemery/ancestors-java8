package com.dhemery.ancestors;

import java.util.Optional;

public interface Name {
	Optional<String> prefix();
	Optional<String> givenName();
	Optional<String> surnamePrefix();
	Optional<String> surname();
	Optional<String> suffix();
}
