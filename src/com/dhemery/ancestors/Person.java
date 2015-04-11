
package com.dhemery.ancestors;

import java.util.Collection;

public interface Person extends Comparable<Person> {
	Name name();
	Collection<Family> familiesWhereSpouse();
	Collection<Family> familiesWhereChild();
}
