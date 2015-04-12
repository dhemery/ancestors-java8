
package com.dhemery.ancestors;

import java.util.Collection;
import java.util.Optional;

public interface Person extends Comparable<Person> {
	Name name();
    Optional<Sex> sex();
	Collection<Family> familiesWhereSpouse();
	Collection<Family> familiesWhereChild();
}
