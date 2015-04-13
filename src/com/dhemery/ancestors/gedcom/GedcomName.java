package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.Name;
import org.gedcom4j.model.PersonalName;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.partitioningBy;

public class GedcomName implements Name {
	private static final Predicate<String> IS_SURNAME = Pattern.compile("/(.*)/").asPredicate();
	private static final Pattern NAME_PARTS = Pattern.compile(" ");
	private Optional<String> prefix;
	private List<String> given;
	private Optional<String> surname;
	private Optional<String> suffix;

	public GedcomName(PersonalName name) {
		prefix = Optional.ofNullable(name.prefix).map(Object::toString);
		Map<Boolean, List<String>> nameParts = NAME_PARTS.splitAsStream(name.basic)
				.collect(partitioningBy(IS_SURNAME));
		given = nameParts.get(false);
		surname = nameParts.get(true).stream()
				.findFirst()
				.map(s -> s.substring(1, s.length()-1));
		suffix = Optional.ofNullable(name.suffix).map(Object::toString);
	}

	@Override
	public Optional<String> prefix() {
		return prefix;
	}

	@Override
	public List<String> given() {
		return given;
	}

	@Override
	public Optional<String> surname() {
		return surname;
	}

	@Override
	public Optional<String> suffix() {
		return suffix;
	}

	@Override
	public String toString() {
		return full();
	}
}
