
package com.dhemery.ancestors.genealogy;

import java.util.Collection;
import java.util.Optional;

public interface Person {
	Name name();
    Optional<Sex> sex();
	Optional<Family> familyOfOrigin();
	Collection<Family> familiesWhereSpouse();
}
