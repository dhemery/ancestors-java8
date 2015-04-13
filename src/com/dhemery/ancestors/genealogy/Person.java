
package com.dhemery.ancestors.genealogy;

import java.util.Collection;
import java.util.Optional;

public interface Person extends Comparable<Person> {
	Name name();
    Optional<Sex> sex();
	Optional<Family> familyOfOrigin();
	Collection<Family> familiesWhereSpouse();
}
