package com.dhemery.ancestors.main;

import com.dhemery.ancestors.genealogy.Person;

import static com.dhemery.ancestors.gedcom.GedcomLoader.TREE;

public class ForLoop {
    public static void main(String[] ignored) {
        for (Person person : TREE.people()) {
            System.out.println(person);
        }
    }
}

