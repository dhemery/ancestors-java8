package com.dhemery.ancestors.genealogy;

import java.util.Collection;

public interface FamilyTree {
	Collection<Family> families();
	Collection<Person> people();
}
