package com.dhemery.ancestors.genealogy;

import java.time.LocalDate;
import java.util.Optional;

public interface Event {
    enum Type { BIRTH, DEATH, MARRIAGE }
    Optional<LocalDate> date();
    Type type();
}
