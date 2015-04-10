
package com.dhemery.ancestors;

import java.util.Collection;

public interface Person extends Comparable<Person> {
	String xref();
	Collection<Family> familiesWhereSpouse();
	Collection<Family> familiesWhereChild();
	Name name();
}
