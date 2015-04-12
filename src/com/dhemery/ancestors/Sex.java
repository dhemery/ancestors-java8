package com.dhemery.ancestors;

import java.util.Arrays;
import java.util.Optional;

public enum Sex {
    FEMALE("F"),
    MALE("M");

    private final String initial;

    Sex(String initial) {
        this.initial = initial;
    }

    public String initial() {
        return initial;
    }

    public static Optional<Sex> withInitial(String initial) {
        return Arrays.stream(Sex.values()).filter(s -> s.initial().equals(initial)).findFirst();
    }
}
