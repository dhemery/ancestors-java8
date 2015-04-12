package com.dhemery.ancestors;

public enum Sex {
    FEMALE("F"),
    MALE("M");

    private final String abbreviation;

    Sex(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
