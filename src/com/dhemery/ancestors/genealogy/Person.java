
package com.dhemery.ancestors.genealogy;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface Person extends Identifiable {
	Name name();
    Optional<Sex> sex();
	Optional<Family> familyOfOrigin();
	Collection<Family> familiesWhereSpouse();

	default Collection<Person> parents() {
		return familyOfOrigin()
				.map(Family::spouses)
				.orElse(Collections.emptySet());
	}
}
