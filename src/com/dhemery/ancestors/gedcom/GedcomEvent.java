package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.Event;
import org.gedcom4j.model.FamilyEvent;
import org.gedcom4j.model.IndividualEvent;
import org.gedcom4j.model.IndividualEventType;

import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.format;

public class GedcomEvent implements Event {
    private static final String DATE_PATTERN = "[['ABT']['AFT']['BEF']['BET'] ][[d ]MMM ][u]";
    private static final DateTimeFormatter DATE_PARSER = dateParser();
    private static final Function<String, TemporalAccessor> toTemporal = s -> DATE_PARSER.parse(s, new ParsePosition(0));
    private static final Function<String, LocalDate> toDate = toTemporal.andThen(LocalDate::from);
    private static final Map<IndividualEventType, Type> TYPE_MAP = typeMap();

    private final Optional<LocalDate> date;
    private final Type type;

    public GedcomEvent(IndividualEvent event) {
        this(event, TYPE_MAP.get(event.type));
    }

    public GedcomEvent(FamilyEvent event) {
        this(event, Type.MARRIAGE);
    }

    private GedcomEvent(org.gedcom4j.model.Event event, Type type) {
        date = Optional.ofNullable(event.date)
                .flatMap(d -> Optional.ofNullable(d.value))
                .map(toDate);
        this.type = type;
    }

    @Override  public Optional<LocalDate> date() { return date; }
    @Override  public Type type() { return type; }

    private static DateTimeFormatter dateParser() {
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .parseLenient()
                .appendPattern(DATE_PATTERN)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.YEAR, 1)
                .toFormatter();
    }

    private static Map<IndividualEventType, Type> typeMap() {
        Map<IndividualEventType, Type> map = new HashMap<>();
        map.put(IndividualEventType.BIRTH, Type.BIRTH);
        map.put(IndividualEventType.DEATH, Type.DEATH);
        return map;
    }

    @Override
    public String toString() {
        return format("%s.%s", type.toString().toLowerCase().charAt(0), date.map(Object::toString).orElse("UNKNOWN"));
    }
}
