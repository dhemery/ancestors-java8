package com.dhemery.ancestors;

import java.util.Collection;

public interface FamilyTree {
	public abstract Person dale();
	public abstract Collection<Family> families();
	public abstract Collection<Person> people();
}
