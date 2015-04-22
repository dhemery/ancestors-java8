package com.dhemery.ancestors.main;

import static com.dhemery.ancestors.gedcom.GedcomLoader.TREE;

public class AggregateOperations {
    public static void main(String[] ignored) {
        TREE.people().forEach(System.out::println);
    }
}
