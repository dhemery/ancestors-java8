package com.dhemery.ancestors;

import com.dhemery.ancestors.internal.GedcomFamilyTree;

public class Ancestors {
	public static void main(String[] args) {
		FamilyTree tree = new GedcomFamilyTree();
		tree.families().stream()
			.sorted()
			.forEach(person -> System.out.println(person));
	}
}
