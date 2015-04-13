package com.dhemery.ancestors.main;

import java.util.function.Consumer;

import static com.dhemery.ancestors.gedcom.GedcomLoader.TREE;

public class AggregateOperations {
    private static final Consumer<Object> PRINT = new Print();

    public static void main(String[] ignored) {
        TREE.people().forEach(PRINT);
    }

    private static class Print implements Consumer<Object> {
        @Override
        public void accept(Object item) {
            System.out.println(item);
        }
    }
}
