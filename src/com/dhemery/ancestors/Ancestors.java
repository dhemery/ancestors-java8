package com.dhemery.ancestors;

import com.dhemery.ancestors.internal.GedcomFamilyTree;

public class Ancestors {
	private static final FamilyTree TREE = new GedcomFamilyTree();

	public static void main(String[] args) {
        for (Person person : TREE.people()) {
            if (person.name().full().contains("/Emery/")) {
                System.out.println(person.name().full());
            }
        }
    }
}
