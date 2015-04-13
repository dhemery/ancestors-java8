package com.dhemery.ancestors.genealogy;

import java.util.Collection;

public interface FamilyTree {
	Person dale();
	Collection<Family> families();
	Collection<Person> people();
}
