
package com.dhemery.ancestors.genealogy;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface Person extends Identifiable {
	Name name();
	Optional<Family> familyOfOrigin();
	Optional<Event> birth();
	Optional<Sex> sex();
	Collection<Family> familiesWhereSpouse();
	Optional<Event> death();

	default Collection<Person> parents() {
		return familyOfOrigin()
				.map(Family::spouses)
				.orElse(Collections.emptySet());
	}
}
